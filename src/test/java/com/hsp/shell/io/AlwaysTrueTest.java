package com.hsp.shell.io;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author georgepapas
 */
public class AlwaysTrueTest extends AbstractFileTestBase {

   @Test
   public void testAccept() throws Exception {
      assertThat(new FileFilters.AlwaysTrue().accept(mockFile), is(true));
   }
}
