package com.hsp.shell.executable;

import com.hsp.shell.core.exception.ExecutionResult;
import com.hsp.shell.util.FileUtils;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

public class TouchTest extends AbstractExecutableTestBase {

   private File currentDir;
   private Touch touch;
   private String testFileName;

   @Override
   protected void setup() {
      MockitoAnnotations.initMocks(this);

      currentDir = new File(System.getProperty("java.io.tmpdir"));
      touch = new Touch();

      testFileName = System.currentTimeMillis() + "" + System.nanoTime() + "-loremipsum.txt";
      when(mockEnvironment.getProperty("user.dir")).thenReturn(currentDir.getAbsolutePath());

   }

   @Test
   public void shouldCreateFileInCurrentDirIfNoDirSpecified() {
      args.add(testFileName);

      File theFile = new File(currentDir, testFileName);
      assertThat(theFile.exists(), is(false));

      touch.executeCommand(commandLine, ps, mockEnvironment);
      assertThat(theFile.exists(), is(true));
   }


   @Test
   public void shouldCreateFileInDirectoryWhenDirIsProvided() {
      String fullFileName = currentDir + FileUtils.FILE_SEPARATOR + testFileName;
      File touchedFile = new File(fullFileName);

      assertThat(touchedFile.exists(), is(false));

      args.add(fullFileName);
      touch.executeCommand(commandLine, ps, mockEnvironment);

      assertThat(touchedFile.exists(), is(true));
   }

   @Test
   public void shouldFailWithNonZeroReturnCodeIfDirectoryDoesNotExist() {
      String bogusDirectory = currentDir + FileUtils.FILE_SEPARATOR + System.currentTimeMillis() + FileUtils.FILE_SEPARATOR;

      File bogusDir = new File(bogusDirectory);
      assertThat(bogusDir.exists(), is(false));

      String bogusFileName = bogusDirectory + "loremipsum.txt";
      args.add(bogusFileName);
      ExecutionResult result = touch.executeCommand(commandLine, ps, mockEnvironment);
      assertThat(result.getStatusCode(), is(0));

      assertThat(baos.toString(), is("touch : " + bogusFileName + " : No such file or directory\n"));
   }

}
