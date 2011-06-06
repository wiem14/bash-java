package com.hsp.shell.io;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author georgepapas
 */
public class DirectoryFilterTest extends AbstractFileTestBase {

   @Test
   public void shouldAcceptDirectories() {
      when(mockFile.isDirectory()).thenReturn(true);
      assertThat(new FileFilters.DirectoryFilter().accept(mockFile), is(true));
   }

   @Test
   public void shouldIgnoreNonDirectories() {
      when(mockFile.isDirectory()).thenReturn(false);
      assertThat(new FileFilters.DirectoryFilter().accept(mockFile), is(false));
   }
}
