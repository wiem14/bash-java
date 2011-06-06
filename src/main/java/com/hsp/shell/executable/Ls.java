package com.hsp.shell.executable;

import com.hsp.shell.core.*;
import com.hsp.shell.core.exception.ExecutionResult;
import com.hsp.shell.io.FileFilterChain;
import com.hsp.shell.io.FileFilters;
import com.hsp.shell.util.FileUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author georgepapas
 */
public class Ls extends AbstractExecutable {


   private static final DirectoryFormatter MULTIPLE_DIR_FORMATTER = new MultipleDirectoryFormatter();
   private static final DirectoryFormatter BASIC_DIR_FORMATTER = new BasicDirectoryFormatter();

   public Ls() {
      super("ls");
   }


   @Override
   protected ExecutionResult executeCommand(final CommandLine commandLine, final PrintStream out, final ExecutionContext context) {
      FileFilter filter = createFilterFromCommandLine(commandLine);
      List<DirectoryListingCandidate> listingCandidates = getDirectoryFromCommandLine(commandLine, context.getEnvironment());

      boolean hasNonListable = handleNonListableDirectories(listingCandidates, out);

      DirectoryFormatter formatter = getDirectoryFormatter(listingCandidates);
      produceListings(listingCandidates, filter, formatter, out);

      return hasNonListable ? FAILURE : SUCCESS;
   }

   private DirectoryFormatter getDirectoryFormatter(final List<DirectoryListingCandidate> listingCandidates) {
      return listingCandidates.size() > 1 ? MULTIPLE_DIR_FORMATTER : BASIC_DIR_FORMATTER;
   }


   private void produceListings(final List<DirectoryListingCandidate> listingCandidates, final FileFilter filter, final DirectoryFormatter formatter, final PrintStream out) {
      for (DirectoryListingCandidate candidate : listingCandidates) {

         if (candidate.isCanListContents()) {
            listDirectoryContents(out, filter, formatter, candidate.getDir());
         }
      }
   }


   private void produceError(File directory, PrintStream out) {
      out.printf("ls: %s: No such file or directory\n", directory.getAbsolutePath());
   }


   private boolean handleNonListableDirectories(final List<DirectoryListingCandidate> listingCandidates, final PrintStream out) {
      boolean containsNonListable = false;

      for (DirectoryListingCandidate candidate : listingCandidates) {
         if (!candidate.isCanListContents()) {
            produceError(candidate.getDir(), out);
            containsNonListable = true;
         }
      }
      return containsNonListable;
   }


   private void listDirectoryContents(final PrintStream out, final FileFilter filter, final DirectoryFormatter formatter, final File directory) {
      List<File> files = getFilesFromDirectoryMatchingFilter(directory, filter);

      formatter.format(directory, out);
      for (File file : files) {
         out.printf("%s\n", file.getName());
      }
   }

   private List<File> getFilesFromDirectoryMatchingFilter(final File directory, final FileFilter filter) {
      return Arrays.asList(directory.listFiles(filter));
   }


   private List<DirectoryListingCandidate> getDirectoryFromCommandLine(final CommandLine commandLine, final Environment environment) {
      List<DirectoryListingCandidate> candidates = new ArrayList<DirectoryListingCandidate>();

      Arguments args = commandLine.getArguments();

      if (args.size() > 0) {
         for (Argument arg : args.getArgs()) {
            if (!isRegisteredOption(arg)) {
               File dir = getDirectoryFromArgument(arg.getArgument(), environment);
               candidates.add(new DirectoryListingCandidate(dir, dir.isDirectory()));
            }
         }
      } else {
         File dir = new File(environment.getProperty(Environment.CWD));
         candidates.add(new DirectoryListingCandidate(dir, dir.isDirectory()));
      }

      return candidates;
   }


   private File getDirectoryFromArgument(final String argument, final Environment environment) {
      String dirName = argument;
      if (!argument.startsWith("/") && !argument.startsWith("./")) {
         dirName = environment.getProperty(Environment.CWD) + FileUtils.FILE_SEPARATOR + argument;
      }

      return new File(dirName);
   }

   private boolean isRegisteredOption(final Argument arg) {
      //TODO registered options!
      return false;
   }


   private FileFilter createFilterFromCommandLine(final CommandLine commandLine) {
      FileFilterChain filter = new FileFilterChain();

      filter.addFilter(FileFilters.ALWAYS_TRUE);

      return filter;
   }


   public String getHelp() {
      return "list directory contents";
   }


   private static final class DirectoryListingCandidate {
      private File dir;
      private boolean canListContents;

      private DirectoryListingCandidate(File directory, boolean canListContents) {
         this.dir = directory;
         this.canListContents = canListContents;
      }

      public File getDir() {
         return dir;
      }

      public boolean isCanListContents() {
         return canListContents;
      }
   }

   private static interface DirectoryFormatter {

      void format(File directory, PrintStream ps);
   }

   private static final class MultipleDirectoryFormatter implements DirectoryFormatter {

      public void format(File directory, PrintStream ps) {
         ps.printf("\n%s:\n", directory.getAbsolutePath());
      }
   }

   private static final class BasicDirectoryFormatter implements DirectoryFormatter {

      public void format(File directory, PrintStream ps) {
         //noop
      }
   }
}
