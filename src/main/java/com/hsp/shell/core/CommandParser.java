package com.hsp.shell.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CommandParser {

   private static final String PROPERTY_CHAR = Environment.PROPERTY_CHAR;
   private static final int PROPERTY_CHAR_LENGTH = PROPERTY_CHAR.length();

   private Environment environment;

   public CommandParser() {
      this.environment = new Environment();
   }

   public CommandParser(Environment environment) {
      this.environment = environment;
   }

   /**
    * Crude command line parser which supports treating quoted strings as a single command.
    * Does not support nested quotes.  Whitespace serves to delimit the command line and the first
    * token is assumed to be the actual command, with all remaining tokens treated as arguments
    *
    * @param commandLineString the command line to parse.
    * @return A populated command line.
    */
   public CommandLine parse(String commandLineString) {
      CommandLine commandLine = new CommandLine();
      Arguments args = new Arguments();
      commandLine.setArguments(args);

      Stack<String> argStack = populateStackWithArgs(commandLineString.trim());

      boolean firstToken = true;
      while (!argStack.isEmpty()) {
         if (firstToken) {
            commandLine.setCommand(argStack.pop());
            firstToken = false;
         } else {
            args.add(translateEnvironmentVariable(argStack.pop()));
         }
      }

      return commandLine;
   }

   private String translateEnvironmentVariable(String arg) {
      int propCharIndex = arg.indexOf(PROPERTY_CHAR);
      if (propCharIndex >= 0) {

         int endOfEnvVar = arg.indexOf(" ", propCharIndex);
         if (endOfEnvVar <= 0) {
            endOfEnvVar = arg.length();
         }

         String envVarProperty = arg.substring(propCharIndex + PROPERTY_CHAR_LENGTH, endOfEnvVar);
         String envVarValue = environment.getProperty(envVarProperty, envVarProperty);

         if (!envVarProperty.equals(envVarValue)) {
            return arg.replace(PROPERTY_CHAR + envVarProperty, envVarValue);
         }

         // environment variable does not exist, just return the original arg...
      }
      return arg;
   }


   private Stack<String> populateStackWithArgs(String commandLine) {
      List<String> args = new ArrayList<String>();

      StringBuilder argBuilder = new StringBuilder();

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

}
