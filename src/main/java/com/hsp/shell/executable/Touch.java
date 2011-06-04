package com.hsp.shell.executable;

import com.hsp.shell.core.Arguments;
import com.hsp.shell.core.CommandLine;
import com.hsp.shell.core.Environment;
import com.hsp.shell.core.ExecutionContext;
import com.hsp.shell.core.exception.ExecutableException;
import com.hsp.shell.core.exception.ExecutionResult;
import com.hsp.shell.util.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;


public class Touch extends AbstractExecutable {

   public Touch() {
      super("touch");
   }

   @Override
   protected ExecutionResult executeCommand(CommandLine commandLine, PrintStream out, ExecutionContext context) {
      Arguments arguments = commandLine.getArguments();

      try {
         DirectoryAndFileName dirAndFileName = extractDirectoryAndFileName(arguments.get(0).getArgument());

         File directory = getDirectoryForFile(dirAndFileName, context.getEnvironment());
         File touchedFile = FileUtils.leanientCreate(directory.getAbsolutePath(), dirAndFileName.fileName);
         touchedFile.setLastModified(System.currentTimeMillis());

      } catch (FileNotFoundException e) {
         out.printf("touch : %s : No such file or directory\n", arguments.get(0).getArgument());
      }
      catch (IOException e) {
         throw new ExecutableException("Could not create requested file", e);
      }
      return SUCCESS;
   }

   private File getDirectoryForFile(DirectoryAndFileName dirAndFileName, Environment environment) throws FileNotFoundException {
      String dirName = environment.getProperty(Environment.CWD);

      if (dirAndFileName.hasDir()) {
         dirName = dirAndFileName.dirName;
      }

      return FileUtils.getDir(dirName);
   }

   private DirectoryAndFileName extractDirectoryAndFileName(String argument) {
      DirectoryAndFileName dirAndFile = new DirectoryAndFileName();

      int indexOfLastSlash = argument.lastIndexOf('/');
      if (indexOfLastSlash < 0) {
         dirAndFile.fileName = argument;
      } else {
         dirAndFile.fileName = argument.substring(indexOfLastSlash);
         dirAndFile.dirName = argument.substring(0, indexOfLastSlash);
      }

      return dirAndFile;
   }

   public String getHelp() {
      return "change file modification time";
   }

   private static final class DirectoryAndFileName {
      private String dirName;
      private String fileName;

      private boolean hasDir() {
         return dirName != null && ("".equals(dirName) == false);
      }
   }
}
