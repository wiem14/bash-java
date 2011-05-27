package com.hsp.shell.core;

import java.io.OutputStream;


public class CommandExecutor {

   private ExecutablePath path;

   public CommandExecutor(ExecutablePath executablePath) {
      this.path = executablePath;
   }

   public int executeCommand(CommandLine commandLine, OutputStream out) {
      return path.locateExecutable(commandLine.getCommand()).execute(commandLine, out);
   }
}
