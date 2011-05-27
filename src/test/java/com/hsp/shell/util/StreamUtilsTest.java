package com.hsp.shell.util;

import org.junit.Test;


public class StreamUtilsTest {

   @Test
   public void shouldHandleNullOnClose() {
      StreamUtils.close(null);
   }
}
