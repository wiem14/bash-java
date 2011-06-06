package com.hsp.shell.io;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author georgepapas
 */
public class FileFilterChain implements FileFilter {
   private List<FileFilter> filters = new ArrayList<FileFilter>();

   public boolean accept(File file) {
      for (FileFilter filter : filters) {
         if (filter.accept(file)) {
            return true;
         }
      }
      return false;
   }

   public void addFilter(FileFilter filter) {
      filters.add(filter);
   }
}
