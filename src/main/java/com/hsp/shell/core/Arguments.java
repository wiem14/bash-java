package com.hsp.shell.core;

import java.util.*;

public class Arguments {

   private Set<Argument> args = new LinkedHashSet<Argument>();

   public boolean add(Argument argument) {
      int count = args.size();
      this.args.add(argument);

      return args.size() - count == 1;
   }

   public boolean add(String arg) {
      return add(new Argument(arg, null));
   }

   public int size() {
      return args.size();
   }

   public List<Argument> getArgs() {
      List<Argument> flattenedArgs = new ArrayList<Argument>();

      for (Iterator<Argument> argIter = args.iterator(); argIter.hasNext();) {
         flattenedArgs.add(argIter.next());
      }

      return flattenedArgs;
   }


}
