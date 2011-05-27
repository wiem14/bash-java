package com.hsp.shell.util;

import java.io.IOException;
import java.io.Writer;


public final class StreamUtils {

   public static final void close(Writer writer) {
      if (writer != null) {
         try {
            writer.close();
         } catch (IOException e) {
         }
      }
   }

   private StreamUtils() {
   }
}
