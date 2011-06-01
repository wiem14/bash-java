package com.hsp.shell.executable;

import com.hsp.shell.core.CommandLine;
import com.hsp.shell.core.Environment;
import com.hsp.shell.core.Executable;

import java.io.OutputStream;
import java.io.PrintStream;


public abstract class AbstractExecutable implements Executable {

   protected static final int SUCCESS = 0;

   private String executableName;

   protected abstract int executeCommand(CommandLine commandLine, PrintStream out, Environment environment);

   protected AbstractExecutable(String executableName) {
      this.executableName = executableName;
   }

   public String name() {
      return executableName;
   }

   public int execute(CommandLine commandLine, OutputStream out, Environment environment) {
      return executeCommand(commandLine, new PrintStream(out), environment);
   }

   protected boolean isOption(String str) {
      return str.startsWith("-") || str.startsWith("--");
   }

   protected boolean isOption(String expected, String actual) {
      return ("-" + expected).equals(actual) || ("--" + expected).equals(actual);
   }
}
