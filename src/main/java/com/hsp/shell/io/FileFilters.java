package com.hsp.shell.io;

import java.io.File;
import java.io.FileFilter;

/**
 * @author georgepapas
 */
public final class FileFilters {

   public static final FileFilter ALWAYS_TRUE = new AlwaysTrue();

   public static final FileFilter DIRECTORY_FILTER = new DirectoryFilter();

   public static final FileFilter HIDDEN_FILE_FILTER = new DirectoryFilter();

   private FileFilters() {
   }


   static class AlwaysTrue implements FileFilter {
      public boolean accept(File file) {
         return true;
      }
   }


   static class HiddenFileFilter implements FileFilter {
      public boolean accept(File file) {
         return file.isHidden();
      }
   }


   static class DirectoryFilter implements FileFilter {
      public boolean accept(File file) {
         return file.isDirectory();
      }
   }
}
