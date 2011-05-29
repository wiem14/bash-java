package com.hsp.shell.util;

import com.hsp.shell.ShellException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public final class FileUtils {

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
}
