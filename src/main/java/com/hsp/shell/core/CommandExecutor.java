package com.hsp.shell.core;

import java.io.OutputStream;


public class CommandExecutor {

   private ExecutablePath path;
   private Environment environment;

   public CommandExecutor(ExecutablePath executablePath, Environment environment) {
      this.path = executablePath;
      this.environment = environment;
   }

   public int executeCommand(CommandLine commandLine, OutputStream out) {
      int exitStatus = path.locateExecutable(commandLine.getCommand()).execute(commandLine, out);
      environment.setProperty(Environment.EXIT_STATUS, String.valueOf(exitStatus));

      return exitStatus;
   }
}
