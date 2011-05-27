package com.hsp.shell.core;

import java.util.StringTokenizer;

public class CommandParser {

   public CommandLine parse(String string) {
      CommandLine commandLine = new CommandLine();
      StringTokenizer tokenizer = new StringTokenizer(string);

      boolean firstToken = true;
      while (tokenizer.hasMoreTokens()) {
         if (firstToken) {
            commandLine.setCommand(tokenizer.nextToken());
         }
      }

      return commandLine;
   }
}
