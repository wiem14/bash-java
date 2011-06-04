package com.hsp.shell.executable;

import com.hsp.shell.core.CommandLine;
import com.hsp.shell.core.ExecutionContext;
import com.hsp.shell.core.exception.ExecutionResult;

import java.io.PrintStream;


public class ClearScreen extends AbstractExecutable {
   private static final char ESC = 27;

   public ClearScreen() {
      super("clear");
   }

   public String getHelp() {
      return "clears the terminal and moves the cursor to the top left hand corner just after the prompt";
   }

   @Override
   protected ExecutionResult executeCommand(CommandLine commandLine, PrintStream out, ExecutionContext context) {
      out.println(ESC + "[2J");
      out.println(ESC + "[H");

      return SUCCESS;
   }
}
