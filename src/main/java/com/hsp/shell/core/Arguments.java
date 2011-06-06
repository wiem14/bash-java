package com.hsp.shell.core;

import com.hsp.shell.ShellException;

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
   }


   public Argument getLast() {
      int argCount = args.size();

      if (argCount > 0) {
         return args.get(argCount - 1);
      }

      throw new ShellException("No arguments present");
   }
}
