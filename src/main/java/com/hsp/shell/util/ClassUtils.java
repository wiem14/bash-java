package com.hsp.shell.util;


public final class ClassUtils {

   public static Class<?> loadClassFromFile(String fileName) throws ClassNotFoundException {

      int endIndex = fileName.length();

      if (fileName.endsWith(".class")) {
         endIndex -= ".class".length();
      }

      String className = fileName.substring(0, endIndex);
      className = className.replace("/", ".");

      return Thread.currentThread().getContextClassLoader().loadClass(className);
   }

   private ClassUtils() {
   }
}
