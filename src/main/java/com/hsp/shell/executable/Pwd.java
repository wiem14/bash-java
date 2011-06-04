package com.hsp.shell.executable;

import com.hsp.shell.core.CommandLine;
import com.hsp.shell.core.Environment;
import com.hsp.shell.core.ExecutionContext;
import com.hsp.shell.core.exception.ExecutionResult;

import java.io.PrintStream;

/**
 * Prints the current working directory
 */
public class Pwd extends AbstractExecutable {

   public Pwd() {
      super("pwd");
   }

   @Override
   protected ExecutionResult executeCommand(CommandLine commandLine, PrintStream out, ExecutionContext context) {
      out.printf("%s\n", context.getEnvironment().getProperty(Environment.CWD));
      return SUCCESS;
   }

   public String getHelp() {
      return "return the working directory name";
   }
}
