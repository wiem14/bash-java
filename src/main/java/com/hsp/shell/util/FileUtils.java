package com.hsp.shell.util;

import com.hsp.shell.ShellException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public final class FileUtils {

   public static final String FILE_SEPARATOR = System.getProperty("file.separator");

   public static List<String> getFileNamesFromUrl(URL url) {
      List<String> fileNames = new ArrayList<String>();

      try {
         if (isFile(url)) {
            fileNames.addAll(getFileNamesFromDirectory(url));
         } else if (isJar(url)) {
            fileNames.addAll(getFileNamesFromJar(url));
         } else {
            throw new ShellException("Unable to extract contents from resource " + url);
         }
      } catch (IOException e) {
         throw new ShellException("Unable to extract contents from resource " + url);
      }

      return fileNames;
   }


   public static List<String> getFileNamesFromJar(URL url) throws IOException {
      List<String> fileNames = new ArrayList<String>();

      String pathToFile = url.getPath().substring("file:".length(), url.getPath().indexOf("!"));
      JarFile jar = new JarFile(pathToFile);

      Enumeration<JarEntry> entries = jar.entries();
      while (entries.hasMoreElements()) {
         fileNames.add(entries.nextElement().getName());
      }

      return fileNames;
   }


   public static List<String> getFileNamesFromDirectory(URL url) {
      File dir = new File(url.getFile());

      List<String> fileNames = new ArrayList<String>();
      for (File file : dir.listFiles()) {
         fileNames.add(file.getAbsolutePath());
      }
      return fileNames;
   }


   private static boolean isJar(URL url) {
      return "jar".equals(url.getProtocol());
   }

   private static boolean isFile(URL url) {
      return "file".equals(url.getProtocol());
   }


   private FileUtils() {
   }

   /**
    * Creates the specified file, or returns the existing file in the case the file already exists
    *
    * @param directoryName the directory in which the file will be created
    * @param newFileName   the name of the file to create.
    * @return file specified by the directory and file name
    */
   public static File leanientCreate(String directoryName, String newFileName) throws IOException {
      File directory = new File(directoryName);
      if (!directory.exists()) {
         directory.mkdirs();
      }

      File newFile = new File(directory, newFileName);
      newFile.createNewFile();

      return newFile;
   }


   public static File getDir(String directoryName) throws FileNotFoundException {
      File dir = new File(directoryName);

      if (dir.exists()) {
         return dir;
      }
      throw new FileNotFoundException(dir.getAbsolutePath());
   }
}
