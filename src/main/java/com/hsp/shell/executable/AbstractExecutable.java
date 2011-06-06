package com.hsp.shell.executable;

import com.hsp.shell.core.CommandLine;
import com.hsp.shell.core.Executable;
import com.hsp.shell.core.ExecutionContext;
import com.hsp.shell.core.exception.ExecutionResult;

import java.io.OutputStream;
import java.io.PrintStream;


public abstract class AbstractExecutable implements Executable {

   protected static final ExecutionResult SUCCESS = new ExecutionResult(0);
   protected static final ExecutionResult FAILURE = new ExecutionResult(1);

   private String executableName;

   protected abstract ExecutionResult executeCommand(CommandLine commandLine, PrintStream out, ExecutionContext context);

   protected AbstractExecutable(String executableName) {
      this.executableName = executableName;
   }

   public String getName() {
      return executableName;
   }

   public ExecutionResult execute(CommandLine commandLine, OutputStream out, ExecutionContext context) {
      return executeCommand(commandLine, new PrintStream(out), context);
   }

   protected boolean isOption(String str) {
      return str.startsWith("-") || str.startsWith("--");
   }

   protected boolean isOption(String expected, String actual) {
      return ("-" + expected).equals(actual) || ("--" + expected).equals(actual);
   }
}
