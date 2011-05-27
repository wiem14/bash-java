package com.hsp.shell.core;

import java.util.HashMap;
import java.util.Map;

public class ExecutablePath {

   private Map<String, Executable> executables = new HashMap<String, Executable>();

   public void registerExecutable(Executable executable) {
      executables.put(executable.name(), executable);
   }

   public Executable locateExecutable(String name) {
      Executable executable = executables.get(name);

      if (executable == null) {
         throw new CommandNotFoundException(name);
      }

      return executable;
   }
}
