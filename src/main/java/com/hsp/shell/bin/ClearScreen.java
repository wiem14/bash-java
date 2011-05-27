package com.hsp.shell.bin;

import com.hsp.shell.core.CommandLine;

import java.io.PrintStream;


public class ClearScreen extends AbstractExecutable {
   private static final char ESC = 27;

   public ClearScreen() {
      super("cls");
   }

   private String getHelpString() {
      return "cls - clears the terminal and moves the cursor to the top left hand corner just after the prompt";
   }

   @Override
   protected int executeCommand(CommandLine commandLine, PrintStream out) {
      out.println(ESC + "[2J");
      out.println(ESC + "[H");

      return SUCCESS;
   }
}
