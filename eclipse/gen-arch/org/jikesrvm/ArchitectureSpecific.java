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
package org.jikesrvm;

import org.vmmagic.unboxed.WordArray;

import org.jikesrvm.classloader.VM_Method;
import org.jikesrvm.compilers.baseline.VM_BaselineCompiledMethod;
import org.jikesrvm.compilers.opt.OPT_BURS;
import org.jikesrvm.compilers.opt.OPT_DepGraphNode;
import org.jikesrvm.compilers.opt.ir.OPT_IR;

public class ArchitectureSpecific {
  public static class VM_Assembler extends org.jikesrvm.compilers.common.assembler.ia32.VM_Assembler {
    public VM_Assembler (int bytecodeSize) {
      super(bytecodeSize, false);
    }
    public VM_Assembler (int bytecodeSize, boolean shouldPrint, VM_Compiler compiler) {
      super(bytecodeSize, shouldPrint, compiler);
    }
    public VM_Assembler (int bytecodeSize, boolean shouldPrint) {
      super(bytecodeSize, shouldPrint);
    }
  }
  public interface VM_ArchConstants extends org.jikesrvm.ia32.VM_ArchConstants {}
  public interface VM_BaselineConstants extends org.jikesrvm.ia32.VM_BaselineConstants {}
  public static final class VM_BaselineExceptionDeliverer extends org.jikesrvm.compilers.baseline.ia32.VM_BaselineExceptionDeliverer {}
  public static final class VM_BaselineGCMapIterator extends org.jikesrvm.compilers.baseline.ia32.VM_BaselineGCMapIterator {
    public VM_BaselineGCMapIterator(WordArray registerLocations) {
      super(registerLocations);
    }
  }
  public static final class VM_CodeArray extends org.jikesrvm.ia32.VM_CodeArray {
    public VM_CodeArray() { super(0);}
    public VM_CodeArray(int size) { super(size);}
    public static VM_CodeArray create (int size) { // only intended to be called from VM_CodeArray.factory
      if (VM.runningVM) VM._assert(false);  // should be hijacked
      return new VM_CodeArray(size);
    }
  }
  public static final class VM_Compiler extends org.jikesrvm.compilers.baseline.ia32.VM_Compiler {
    public VM_Compiler(VM_BaselineCompiledMethod cm, int[] genLocLoc, int[] floatLocLoc) {
      super(cm /**, genLocLoc, floatLocLoc  */ );
    }}
  public static final class VM_DynamicLinkerHelper extends org.jikesrvm.ia32.VM_DynamicLinkerHelper {}
  public static final class VM_InterfaceMethodConflictResolver extends org.jikesrvm.ia32.VM_InterfaceMethodConflictResolver {}
  public static final class VM_LazyCompilationTrampolineGenerator extends org.jikesrvm.ia32.VM_LazyCompilationTrampolineGenerator {}
  // */
  public static final class VM_MachineCode extends org.jikesrvm.ia32.VM_MachineCode {
  
    public VM_MachineCode(ArchitectureSpecific.VM_CodeArray array, int[] bm) {
      super(array, bm);
    }
  //*/
  }
  public static final class VM_MachineReflection extends org.jikesrvm.ia32.VM_MachineReflection {}
  public static final class VM_MultianewarrayHelper extends org.jikesrvm.ia32.VM_MultianewarrayHelper {}
  public static final class VM_OutOfLineMachineCode extends org.jikesrvm.ia32.VM_OutOfLineMachineCode {}
  public static final class VM_ProcessorLocalState extends org.jikesrvm.ia32.VM_ProcessorLocalState {}
  public interface VM_RegisterConstants extends org.jikesrvm.ia32.VM_RegisterConstants {}
  public static final class VM_Registers extends org.jikesrvm.ia32.VM_Registers {}
  public interface VM_StackframeLayoutConstants extends org.jikesrvm.ia32.VM_StackframeLayoutConstants {}
  public interface VM_TrapConstants extends org.jikesrvm.ia32.VM_TrapConstants {}
  public static final class VM_JNICompiler extends org.jikesrvm.jni.ia32.VM_JNICompiler {}
  public static final class VM_JNIGCMapIterator extends org.jikesrvm.jni.ia32.VM_JNIGCMapIterator {
    public VM_JNIGCMapIterator(WordArray registerLocations) {
      super(registerLocations);
    }}
  public static final class VM_JNIHelpers extends org.jikesrvm.jni.ia32.VM_JNIHelpers {}
  public static final class OPT_Assembler extends org.jikesrvm.compilers.opt.ia32.OPT_Assembler {
  
    public OPT_Assembler(int bcSize, boolean print, OPT_IR ir) {
      super(bcSize, print, ir);
    }
  //*/
  }
  public static final class OPT_BURS_Debug extends org.jikesrvm.compilers.opt.ia32.OPT_BURS_Debug {}
  public static final class OPT_BURS_STATE extends org.jikesrvm.compilers.opt.ia32.OPT_BURS_STATE {
    public OPT_BURS_STATE(OPT_BURS b) {
      super(b);
    }}
  public static class OPT_BURS_TreeNode extends org.jikesrvm.compilers.opt.ia32.OPT_BURS_TreeNode {
    public OPT_BURS_TreeNode(OPT_DepGraphNode node) {
      super(node);
    }
    public OPT_BURS_TreeNode(char int_constant_opcode) {
      super(int_constant_opcode);
    }}
  public static final class OPT_CallingConvention extends org.jikesrvm.compilers.opt.ia32.OPT_CallingConvention {}
  public static final class OPT_ComplexLIR2MIRExpansion extends org.jikesrvm.compilers.opt.ia32.OPT_ComplexLIR2MIRExpansion {}
  public static final class OPT_ConvertALUOperators extends org.jikesrvm.compilers.opt.ia32.OPT_ConvertALUOperators {}
  public static final class OPT_FinalMIRExpansion extends org.jikesrvm.compilers.opt.ia32.OPT_FinalMIRExpansion {}
  public static final class OPT_MIROptimizationPlanner extends org.jikesrvm.compilers.opt.ia32.OPT_MIROptimizationPlanner {}
  public static final class OPT_NormalizeConstants extends org.jikesrvm.compilers.opt.ia32.OPT_NormalizeConstants {}
  public interface OPT_PhysicalRegisterConstants extends org.jikesrvm.compilers.opt.ia32.OPT_PhysicalRegisterConstants {}
  public abstract static class OPT_PhysicalRegisterTools extends org.jikesrvm.compilers.opt.ia32.OPT_PhysicalRegisterTools {}
  public static final class OPT_RegisterPreferences extends org.jikesrvm.compilers.opt.ia32.OPT_RegisterPreferences {}
  public static final class OPT_RegisterRestrictions extends org.jikesrvm.compilers.opt.ia32.OPT_RegisterRestrictions {
    public OPT_RegisterRestrictions(OPT_PhysicalRegisterSet phys) {
      super(phys);
    }}
  public static final class OPT_StackManager extends org.jikesrvm.compilers.opt.ia32.OPT_StackManager {}
  public static final class VM_OptExceptionDeliverer extends org.jikesrvm.compilers.opt.ia32.VM_OptExceptionDeliverer {}
  public static final class VM_OptGCMapIterator extends org.jikesrvm.compilers.opt.ia32.VM_OptGCMapIterator {
    public VM_OptGCMapIterator(WordArray registerLocations) {
      super(registerLocations);
    }}
  public interface VM_OptGCMapIteratorConstants extends org.jikesrvm.compilers.opt.ia32.VM_OptGCMapIteratorConstants {}
  public static final class OPT_GenerateMachineSpecificMagic extends org.jikesrvm.compilers.opt.ir.ia32.OPT_GenerateMachineSpecificMagic {}
  public static final class OPT_PhysicalDefUse extends org.jikesrvm.compilers.opt.ir.ia32.OPT_PhysicalDefUse {}
  public static final class OPT_PhysicalRegisterSet extends org.jikesrvm.compilers.opt.ir.ia32.OPT_PhysicalRegisterSet {}
  public static final class OPT_RegisterPool extends org.jikesrvm.compilers.opt.ir.ia32.OPT_RegisterPool {
    public OPT_RegisterPool(VM_Method meth) {
      super(meth);
    }}
  public static final class OSR_BaselineExecStateExtractor extends org.jikesrvm.osr.ia32.OSR_BaselineExecStateExtractor {}
  public static final class OSR_CodeInstaller extends org.jikesrvm.osr.ia32.OSR_CodeInstaller {}
  public static final class OSR_OptExecStateExtractor extends org.jikesrvm.osr.ia32.OSR_OptExecStateExtractor {}
  public static final class OSR_PostThreadSwitch extends org.jikesrvm.osr.ia32.OSR_PostThreadSwitch {}
}
