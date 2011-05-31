package com.hsp.shell.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CommandParser {

   public CommandLine parse(String commandLineString) {
      CommandLine commandLine = new CommandLine();
      Arguments args = new Arguments();
      commandLine.setArguments(args);

      Stack<String> argStack = populateStackWithArgs(commandLineString);


      boolean firstToken = true;
      while (!argStack.isEmpty()) {
         if (firstToken) {
            commandLine.setCommand(argStack.pop());
            firstToken = false;
         } else {
            args.add(argStack.pop());
//            String value = null;
//            if (isOption(option)) {
//               if (!argStack.isEmpty()) {
//                  if (!isOption(argStack.peek())) {
//                     value = argStack.pop();
//                  }
//               }
//            }
//
//            args.add(new Argument(option, value));
         }

      }

      return commandLine;
   }

   private Stack<String> populateStackWithArgs(String commandLine) {
      List<String> args = new ArrayList<String>();

      StringBuilder argBuilder = new StringBuilder();

      commandLine = commandLine.trim();

      int commandLineLength = commandLine.length();
      boolean startQuote = false;

      for (int i = 0; i < commandLineLength; i++) {
         Character currentChar = commandLine.charAt(i);

         if (Character.isWhitespace(currentChar)) {

            if (startQuote) {
               argBuilder.append(currentChar);
               continue;
            }

            if (argBuilder.length() > 0) {
               args.add(argBuilder.toString());
               argBuilder = new StringBuilder();
            }

         } else if ('"' == currentChar) {

            startQuote = !startQuote;

         } else {

            argBuilder.append(currentChar);

         }

      }

      if (argBuilder.length() > 0) {
         args.add(argBuilder.toString());
      }

      Stack<String> argStack = new Stack<String>();
      int argCount = args.size();
      for (int i = argCount - 1; i >= 0; i--) {
         argStack.push(args.get(i));
      }

      return argStack;
   }

   private boolean isOption(String option) {
      return option.startsWith("-") || option.startsWith("--");
   }

}
