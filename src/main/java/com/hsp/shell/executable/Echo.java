package com.hsp.shell.executable;

import com.hsp.shell.core.Argument;
import com.hsp.shell.core.CommandLine;
import com.hsp.shell.core.Environment;

import java.io.PrintStream;


public class Echo extends AbstractExecutable {

   public Echo() {
      super("echo");
   }

   @Override
   protected int executeCommand(CommandLine commandLine, PrintStream out, Environment environment) {

      StringBuilder builder = new StringBuilder();

      boolean ignoreNewLine = false;
      for (Argument arg : commandLine.getArguments().getArgs()) {

         String argument = arg.getArgument();
         if (isOption(argument) && isOption("n", argument)) {
            ignoreNewLine = true;
            continue;
         }

         if (builder.length() > 0) {
            builder.append(" ");
         }
         builder.append(argument.replace("\"", ""));
      }

      if (!ignoreNewLine) {
         builder.append("\n");
      }
      out.printf("%s", builder.toString());

      out.flush();
      return SUCCESS;
   }
}
