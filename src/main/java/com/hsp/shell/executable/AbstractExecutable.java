package com.hsp.shell.executable;

import com.hsp.shell.core.CommandLine;
import com.hsp.shell.core.Executable;

import java.io.OutputStream;
import java.io.PrintStream;


public abstract class AbstractExecutable implements Executable {

   protected static final int SUCCESS = 0;

   private String executableName;

   protected abstract int executeCommand(CommandLine commandLine, PrintStream out);

   protected AbstractExecutable(String executableName) {
      this.executableName = executableName;
   }

   public String name() {
      return executableName;
   }

   public int execute(CommandLine commandLine, OutputStream out) {
      return executeCommand(commandLine, new PrintStream(out));
   }
}
