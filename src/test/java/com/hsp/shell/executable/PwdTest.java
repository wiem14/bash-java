package com.hsp.shell.executable;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

public class PwdTest extends AbstractExecutableTestBase {

   private Pwd pwd;

   @Override
   protected void setup() {
      pwd = new Pwd();
   }

   @Test
   public void testPrintsCurrentWorkingDirectory() {
      when(mockEnvironment.getProperty("user.dir")).thenReturn("foobar");

      pwd.execute(commandLine, baos, context);
      assertThat(baos.toString(), is("foobar\n"));
   }

}
