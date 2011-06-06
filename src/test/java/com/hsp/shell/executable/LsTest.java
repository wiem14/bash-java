package com.hsp.shell.executable;

import com.hsp.shell.core.Environment;
import com.hsp.shell.core.exception.ExecutionResult;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author georgepapas
 */
public class LsTest extends AbstractExecutableTestBase {

   private static final String FILE_SEPARATOR = System.getProperty("file.separator");

   private File tmpDir;
   private String randomDirName;
   private Ls ls;

   @Override
   public void setup() {
      ls = new Ls();
      randomDirName = FILE_SEPARATOR + timeAsString() + "loremipsum";

      tmpDir = new File(System.getProperty("java.io.tmpdir"));
   }

   private String timeAsString() {
      return System.currentTimeMillis() + "" + System.nanoTime();
   }

   @Test
   public void shouldHandleNonExistantDirectory() {
      args.add(randomDirName);
      ExecutionResult result = ls.executeCommand(commandLine, ps, context);
      assertThat(result.getStatusCode(), CoreMatchers.not(0));

      assertThat(baos.toString(), is("ls: " + randomDirName + ": No such file or directory\n"));
   }

   @Test
   public void shouldUseCurrentDirectoryWhenNoArgumentsPresent() {
      when(mockEnvironment.getProperty(Environment.CWD)).thenReturn(tmpDir.getAbsolutePath());
      ls.executeCommand(commandLine, ps, context);
      verify(mockEnvironment).getProperty(Environment.CWD);
   }

   @Test
   public void shouldSupportListingOfMultipleDirectories() throws IOException {
      String testDir = tmpDir.getAbsolutePath() + FILE_SEPARATOR + timeAsString() + "shouldSupportListingOfMultipleDirectories";

      File newDir = new File(testDir);
      newDir.mkdir();

      File dir1 = createDir(newDir.getAbsolutePath() + FILE_SEPARATOR + "dir1");
      File dir2 = createDir(newDir.getAbsolutePath() + FILE_SEPARATOR + "dir2");

      args.add(dir1.getAbsolutePath());
      args.add(dir2.getAbsolutePath());

      ExecutionResult result = ls.executeCommand(commandLine, ps, context);
      assertThat(result.getStatusCode(), is(0));

      String output = baos.toString();
      assertThat(output.contains(dir1.getAbsolutePath() + ":"), is(true));
      assertThat(output.contains(dir2.getAbsolutePath() + ":"), is(true));
   }

   private File createDir(String dirName) {
      File dir = new File(dirName);
      dir.mkdir();

      return dir;
   }
}
