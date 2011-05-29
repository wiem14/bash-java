package com.hsp.shell.core;

import com.hsp.shell.ShellException;
import com.hsp.shell.core.exception.CommandNotFoundException;
import com.hsp.shell.util.ClassUtils;
import com.hsp.shell.util.FileUtils;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.*;

public class ExecutablePath {

   private static final String DEFAULT_EXECUTABLE_PACKAGE = "com/hsp/shell/executable";

   private Map<String, Executable> executables = new HashMap<String, Executable>();

   public ExecutablePath() {
      registerExecutablesFromDefaultPath();
   }

   public void registerExecutable(Executable executable) {
      executables.put(executable.name(), executable);
   }

   public Executable locateExecutable(String name) {
      Executable executable = executables.get(name);

      if (executable == null) {
         throw new CommandNotFoundException(name);
      }

      return executable;
   }

   private void registerExecutablesFromDefaultPath() {
      try {
         List<String> packageFiles = getFileNamesInPackage(DEFAULT_EXECUTABLE_PACKAGE);
         for (String fileName : packageFiles) {
            if (isClassFile(fileName)) {
               Class<?> clazz = ClassUtils.loadClassFromFile(sanitizeFileName(fileName, DEFAULT_EXECUTABLE_PACKAGE));

               if (isExecutable(clazz)) {
                  registerExecutable((Executable) clazz.newInstance());
               }
            }
         }
      } catch (IOException e) {
         throw new ShellException("Problem trying to create the default bin path", e);
      } catch (ClassNotFoundException e) {
         throw new ShellException("Problem trying to load class from executable package " + DEFAULT_EXECUTABLE_PACKAGE, e);
      } catch (InstantiationException e) {
         throw new ShellException("Problem trying to instantiate instance of executable", e);
      } catch (IllegalAccessException e) {
         throw new ShellException("Problem trying to instantiate instance of executable", e);
      }
   }

   private String sanitizeFileName(String fileName, String packageName) {
      String sanitizedName = fileName;

      if (fileName.contains(packageName)) {
         sanitizedName = fileName.substring(fileName.indexOf(packageName));
      }
      return sanitizedName;
   }

   private boolean isExecutable(Class<?> clazz) {
      int modifiers = clazz.getModifiers();
      if (Modifier.isAbstract(modifiers) || Modifier.isInterface(modifiers) || Modifier.isStatic(modifiers)) {
         return false;
      }

      // We have a non abstract class, now let's see whether we implement the Executable interface
      return Executable.class.isAssignableFrom(clazz);
   }

   private boolean isClassFile(String fileName) {
      return fileName.endsWith(".class");
   }

   private List<String> getFileNamesInPackage(String packageName) throws IOException {
      List<String> fileNames = new ArrayList<String>();

      Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packageName);
      while (resources.hasMoreElements()) {
         fileNames.addAll(FileUtils.getFileNamesFromUrl(resources.nextElement()));
      }

      return fileNames;
   }

}
