/*
 *  This file is part of the Jikes RVM project (http://jikesrvm.org).
 *
 *  This file is licensed to You under the Common Public License (CPL);
 *  You may not use this file except in compliance with the License. You
 *  may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/cpl1.0.php
 *
 *  See the COPYRIGHT.txt file distributed with this work for information
 *  regarding copyright ownership.
 */
package org.jikesrvm.compilers.opt.ppc;

import org.jikesrvm.VM_Registers;
import org.jikesrvm.VM;
import org.jikesrvm.VM_Constants;
import org.jikesrvm.classloader.VM_BytecodeConstants;
import org.jikesrvm.compilers.common.VM_CompiledMethod;
import org.jikesrvm.compilers.opt.VM_OptCompiledMethod;
import org.jikesrvm.runtime.VM_ExceptionDeliverer;
import org.jikesrvm.runtime.VM_Magic;
import org.vmmagic.unboxed.LocalAddress;
import org.vmmagic.unboxed.Offset;
import org.vmmagic.unboxed.Word;

/**
 * Handle exception delivery and stack unwinding for
 * opt compiled methods.
 */
public abstract class VM_OptExceptionDeliverer extends VM_ExceptionDeliverer
    implements VM_Constants, VM_BytecodeConstants {

  /**
   * Pass control to a catch block.
   */
  public void deliverException(VM_CompiledMethod cm, LocalAddress catchBlockInstructionAddress, Throwable exceptionObject,
                               VM_Registers registers) {

    // store exception object for later retrieval by catch block
    VM_OptCompiledMethod compiledMethod = (VM_OptCompiledMethod) cm;
    Offset offset = Offset.fromIntSignExtend(compiledMethod.getUnsignedExceptionOffset());
    if (!offset.isZero()) {
      // only put the exception object in the stackframe if the catch block is expecting it.
      // (if the method hasn't allocated a stack slot for caught exceptions, then we can safely
      //  drop the exceptionObject on the floor).
      LocalAddress fp = registers.getInnermostFramePointer();
      VM_Magic.setObjectAtOffset(VM_Magic.localAddressAsObject(fp), offset, exceptionObject);
    }

    // set address at which to resume executing frame
    registers.ip = catchBlockInstructionAddress;
    VM.enableGC(); // disabled right before VM_Runtime.deliverException was called

    if (VM.VerifyAssertions) VM._assert(registers.inuse);
    registers.inuse = false;

    // "branches" to catchBlockInstructionAddress
    VM_Magic.restoreHardwareExceptionState(registers);
    if (VM.VerifyAssertions) VM._assert(NOT_REACHED);
  }

  /**
   * Unwind a stackframe.
   */
  public void unwindStackFrame(VM_CompiledMethod cm, VM_Registers registers) {
    LocalAddress fp = registers.getInnermostFramePointer();
    VM_OptCompiledMethod compiledMethod = (VM_OptCompiledMethod) cm;

    // restore non-volatile registers
    Offset frameOffset = Offset.fromIntSignExtend(compiledMethod.getUnsignedNonVolatileOffset());
    int firstInteger = compiledMethod.getFirstNonVolatileGPR();
    if (firstInteger >= 0) {
      if (VM.BuildFor64Addr) {
        frameOffset = frameOffset.plus(7).toWord().and(Word.fromIntSignExtend(~7)).toOffset();
      }
      for (int i = firstInteger; i < 32; i++) {
        registers.gprs.set(i, fp.loadWord(frameOffset));
        frameOffset = frameOffset.plus(BYTES_IN_ADDRESS);
      }
    }
    int firstFloat = compiledMethod.getFirstNonVolatileFPR();
    if (firstFloat >= 0) {
      frameOffset = frameOffset.plus(7).toWord().and(Word.fromIntSignExtend(~7)).toOffset();
      for (int i = firstFloat; i < 32; i++) {
        long temp = VM_Magic.getLongAtOffset(VM_Magic.localAddressAsObject(fp), frameOffset);
        registers.fprs[i] = VM_Magic.longBitsAsDouble(temp);
        frameOffset = frameOffset.plus(BYTES_IN_DOUBLE);
      }
    }

    registers.unwindStackFrame();
  }
}



