package com.hsp.shell.core;

import java.util.ArrayList;
import java.util.List;

public class Arguments {

   private List<Argument> args = new ArrayList<Argument>();

   public boolean add(Argument argument) {
      int count = args.size();
      this.args.add(argument);

      return args.size() - count == 1;
   }

   public boolean add(String arg) {
      return add(new Argument(arg));
   }

   public int size() {
      return args.size();
   }

   public Argument get(int index) {
      return args.get(index);
   }

   public List<Argument> getArgs() {
      return new ArrayList<Argument>(args);
//      List<Argument> flattenedArgs = new ArrayList<Argument>();
//
//      for (Iterator<Argument> argIter = args.iterator(); argIter.hasNext();) {
//         flattenedArgs.add(argIter.next());
//      }
//
//      return flattenedArgs;
   }


}
