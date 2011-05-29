package com.hsp.shell.core;

/**
 *
 */
public class CommandLine {

   private String command;

   private Arguments arguments;

   public void setCommand(String command) {
      this.command = command;
   }

   public String getCommand() {
      return command;
   }

   public void setArguments(Arguments arguments) {
      this.arguments = arguments;
   }

   public Arguments getArguments() {
      return arguments;
   }
}
