package com.hsp.shell.core;

import java.util.Stack;
import java.util.StringTokenizer;

public class CommandParser {

   public CommandLine parse(String string) {
      CommandLine commandLine = new CommandLine();
      Arguments args = new Arguments();
      commandLine.setArguments(args);

      StringTokenizer tokenizer = new StringTokenizer(string);

      Stack<String> argStack = new Stack<String>();
      populateArgStack(argStack, tokenizer);

      boolean firstToken = true;
      while (!argStack.isEmpty()) {
         if (firstToken) {
            commandLine.setCommand(argStack.pop());
            firstToken = false;
         } else {
            String option = argStack.pop();
            String value = null;
            if (isOption(option)) {
               if (!argStack.isEmpty()) {
                  if (!isOption(argStack.peek())) {
                     value = argStack.pop();
                  }
               }
            }

            args.add(new Argument(option, value));
         }

      }

      return commandLine;
   }

   private boolean isOption(String option) {
      return option.startsWith("-") || option.startsWith("--");
   }

   private void populateArgStack(Stack argStack, StringTokenizer tokenizer) {
      while (tokenizer.hasMoreElements()) {
         String element = tokenizer.nextToken();
         populateArgStack(argStack, tokenizer);
         argStack.push(element);
      }
   }
}
