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
package org.jikesrvm.osr;

/**
 * BC_LoadDoubleConst: ldc2_w
 */
public class BC_LoadDoubleConst extends OSR_PseudoBytecode {
  private static final int bsize = 10;
  private final long dbits;

  public BC_LoadDoubleConst(long bits) {
    this.dbits = bits;
  }

  public byte[] getBytes() {
    byte[] codes = initBytes(bsize, PSEUDO_LoadDoubleConst);
    long2bytes(codes, 2, dbits);
    return codes;
  }

  public int getSize() {
    return bsize;
  }

  public int stackChanges() {
    return +2;
  }

  public String toString() {
    return "LoadDouble 0x" + Long.toHexString(dbits) + " : " + Double.longBitsToDouble(dbits);
  }
}

