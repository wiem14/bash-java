package com.hsp.shell.executable;

import com.hsp.shell.core.CommandLine;
import com.hsp.shell.core.Environment;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PwdTest {
   private ByteArrayOutputStream baos;
   private PrintStream ps;

   @Mock
   private CommandLine mockCommandLine;

   @Mock
   private Environment env;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      baos = new ByteArrayOutputStream();
      ps = new PrintStream(baos);
      env = new Environment();
   }

   @Test
   public void testPrintsCurrentWorkingDirectory() {
      System.setProperty("user.dir", "foobar");

      new Pwd().execute(mockCommandLine, baos, env);
      assertThat(baos.toString(), is("foobar\n"));
   }
}
