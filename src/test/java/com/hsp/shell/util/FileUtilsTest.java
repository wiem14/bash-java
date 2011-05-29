package com.hsp.shell.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;

public class FileUtilsTest {

   private File tmpFile;

   @Before
   public void setup() throws IOException {
      String name = System.currentTimeMillis() + "-" + System.nanoTime();
      tmpFile = File.createTempFile(name, "tmp");
   }


   @After
   public void tearDown() {
      tmpFile.delete();
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
