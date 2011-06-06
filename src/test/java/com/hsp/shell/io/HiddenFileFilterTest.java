package com.hsp.shell.io;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author georgepapas
 */
public class HiddenFileFilterTest extends AbstractFileTestBase {

   @Test
   public void shouldAcceptHiddenFiles() {
      when(mockFile.isHidden()).thenReturn(true);
      assertThat(new FileFilters.HiddenFileFilter().accept(mockFile), is(true));
   }

   @Test
   public void shouldIgnoreNonDirectories() {
      when(mockFile.isHidden()).thenReturn(false);
      assertThat(new FileFilters.HiddenFileFilter().accept(mockFile), is(false));
   }
}
