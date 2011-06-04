package com.hsp.shell.executable;

import com.hsp.shell.core.CommandLine;
import com.hsp.shell.core.Environment;

import java.io.PrintStream;

/**
 * Prints the current working directory
 */
public class Pwd extends AbstractExecutable {

   public Pwd() {
      super("pwd");
   }

   @Override
   protected int executeCommand(CommandLine commandLine, PrintStream out, Environment environment) {
      out.printf("%s\n", environment.getProperty(Environment.CWD));
      return SUCCESS;
   }

   public String getHelp() {
      return "return the working directory name";
   }
}
