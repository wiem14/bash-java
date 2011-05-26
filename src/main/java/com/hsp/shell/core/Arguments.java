package com.hsp.shell.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Arguments {

   private List<String> args = new ArrayList<String>();

   public boolean add(String arg) {
      return this.args.add(arg);
   }

   public int size() {
      return args.size();
   }

   public List<String> getArgs() {
      return Collections.unmodifiableList(args);
   }
}
