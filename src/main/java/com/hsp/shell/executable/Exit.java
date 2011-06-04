package com.hsp.shell.executable;

import com.hsp.shell.core.CommandLine;
import com.hsp.shell.core.ExecutionContext;
import com.hsp.shell.core.exception.ExecutionResult;

import java.io.PrintStream;

/**
 * @author georgepapas
 */
public class Exit extends AbstractExecutable {

   public Exit() {
      super("exit");
   }

   @Override
   protected ExecutionResult executeCommand(CommandLine commandLine, PrintStream out, ExecutionContext context) {
      return new ExecutionResult(0, true);
   }

   public String getHelp() {
      return "exits the current shell";
   }
}
