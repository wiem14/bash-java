package com.hsp.shell;

import com.hsp.shell.core.*;
import com.hsp.shell.core.exception.CommandNotFoundException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;


public final class Shell {

   public static void main(String[] args) {

      PrintStream out = System.out;

      ExecutablePath executablePath = new ExecutablePath();
      Environment environment = new Environment();

      CommandParser parser = new CommandParser(environment);
      CommandExecutor executor = new CommandExecutor(executablePath, environment);

      Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

      while (true) {
         out.print("Prompt :> ");
         CommandLine commandLine = parser.parse(scanner.nextLine());

         try {
            executor.executeCommand(commandLine, System.out);
         } catch (CommandNotFoundException e) {
            out.printf(" %s: command not found\n", e.getMessage());
         }
      }
   }

   private Shell() {
   }
}
