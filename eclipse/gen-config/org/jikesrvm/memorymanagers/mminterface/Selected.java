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
package org.jikesrvm.memorymanagers.mminterface;

import org.jikesrvm.scheduler.VM_Processor;
import org.jikesrvm.annotations.NoSubArchCompile;

import org.vmmagic.pragma.*;

@NoSubArchCompile
public class Selected {
  public static final String name = "org.mmtk.plan.nogc.NoGC";
  @Uninterruptible
  public static final class Plan extends org.mmtk.plan.nogc.NoGC
  {
    private static final Plan plan = new Plan();

    @Inline
    public static Plan get() { return plan; }
  }

  @Uninterruptible
  public static final class Constraints extends org.mmtk.plan.nogc.NoGCConstraints
  {
    private static final Constraints constraints = new Constraints();

    @Inline
    public static Constraints get() { return constraints; }
  }

  @Uninterruptible
  public static class Collector extends org.mmtk.plan.nogc.NoGCCollector
  {
    private VM_Processor processor;
    public Collector(VM_Processor parent) { processor = parent; }
    @Inline
    public final VM_Processor getProcessor() { return processor; }
    @Inline
    public static Collector get() { return VM_Processor.getCurrentProcessor().collectorContext; }
  }

  @Uninterruptible
  public static class Mutator extends org.mmtk.plan.nogc.NoGCMutator
  {
    @Inline
    public final VM_Processor getProcessor() { return (VM_Processor) this; }
    @Inline
    public static Mutator get() { return VM_Processor.getCurrentProcessor(); }
  }
}
