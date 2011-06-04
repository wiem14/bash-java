package com.hsp.shell.core;

import com.hsp.shell.core.exception.ExecutionResult;

import java.io.OutputStream;


public class CommandExecutor {

   private ExecutablePath path;
   private Environment environment;

   public CommandExecutor(ExecutablePath executablePath, Environment environment) {
      this.path = executablePath;
      this.environment = environment;
   }

   public ExecutionResult executeCommand(CommandLine commandLine, OutputStream out) {
      final Executable executable = path.locateExecutable(commandLine.getCommand());

      ExecutionContext context = new ExecutionContext(environment, path);
      ExecutionResult result = executable.execute(commandLine, out, context);

      environment.setProperty(Environment.EXIT_STATUS, String.valueOf(result.getStatusCode()));

      return result;
   }
}
