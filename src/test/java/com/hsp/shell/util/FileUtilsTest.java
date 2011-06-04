package com.hsp.shell.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.fail;

public class FileUtilsTest {

   private String tmpDirectoryName = System.getProperty("java.io.tmpdir");
   private File tmpFile;
   private File tmpDir;

   @Before
   public void setup() throws IOException {
      String name = System.currentTimeMillis() + "-" + System.nanoTime();
      tmpFile = File.createTempFile(name, "tmp");
      tmpDir = new File(tmpDirectoryName);
   }


   @After
   public void tearDown() {
      tmpFile.delete();
   }

   @Test
   public void testGetDir() {
      try {
         assertThat(FileUtils.getDir(tmpDir.getAbsolutePath()), notNullValue());
      } catch (FileNotFoundException e) {
         fail("should not have thrown an exception");
      }
   }

   @Test
   public void testGetDirThrowsExceptionWhenDirectoryDoesNotExist() {
      try {
         FileUtils.getDir("loremipsum");
         fail("Should have thrown an exception");
      } catch (FileNotFoundException e) {
         ;
      }
   }

   @Test
   public void testLenientFileCreation() throws IOException {
      String newFileName = System.currentTimeMillis() + "." + System.nanoTime() + "foobar.txt";

      File newFile = new File(tmpDir, newFileName);
      newFile.createNewFile();
      assertThat(newFile.exists(), is(true));

      File fooBar = FileUtils.leanientCreate(tmpDir.getAbsolutePath(), newFileName);
      assertThat(fooBar, notNullValue());
      assertThat(fooBar.exists(), is(true));
   }


   @Test
   public void shouldListFilesInJarFile() {
      final String testPackage = "org/junit";

      List<String> fileNames = FileUtils.getFileNamesFromUrl(Thread.currentThread().getContextClassLoader().getResource(testPackage));
      assertThat(fileNames.size(), greaterThanOrEqualTo(1));
      assertThat(new HashSet<String>(fileNames).contains("org/junit/Test.class"), is(true));

   }


   @Test
   public void shouldListFilesInDirectory() throws MalformedURLException {
      File tmpDir = tmpFile.getParentFile();
      List<String> fileNames = FileUtils.getFileNamesFromDirectory(tmpDir.toURI().toURL());

      assertThat(fileNames.size(), greaterThanOrEqualTo(1));
      assertThat(new HashSet<String>(fileNames).contains(tmpFile.getAbsolutePath()), is(true));
   }
}
