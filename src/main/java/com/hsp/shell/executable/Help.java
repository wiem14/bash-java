package com.hsp.shell.executable;

import com.hsp.shell.core.CommandLine;
import com.hsp.shell.core.Executable;
import com.hsp.shell.core.ExecutionContext;
import com.hsp.shell.core.exception.ExecutionResult;

import java.io.PrintStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author georgepapas
 */
public class Help extends AbstractExecutable {

   public Help() {
      super("help");
   }

   @Override
   protected ExecutionResult executeCommand(CommandLine commandLine, PrintStream out, ExecutionContext context) {

      List<Executable> execs = context.getPath().getAllRegisteredExecutables();
      Collections.sort(execs, new Comparator<Executable>() {

         public int compare(Executable left, Executable right) {
            return left.getName().compareTo(right.getName());
         }
      });

      out.printf("\n");

      for (Executable executable : execs) {
         out.printf("%s - %s\n", executable.getName(), executable.getHelp());
      }

      out.printf("\n");

      return SUCCESS;
   }

   public String getHelp() {
      return "displays a help message for all known shell commands";
   }
}
