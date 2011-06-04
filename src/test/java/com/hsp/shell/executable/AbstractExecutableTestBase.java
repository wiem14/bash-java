package com.hsp.shell.executable;

import com.hsp.shell.core.Arguments;
import com.hsp.shell.core.CommandLine;
import com.hsp.shell.core.Environment;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author georgepapas
 */
public abstract class AbstractExecutableTestBase {

   protected abstract void setup();

   @Mock
   protected Environment mockEnvironment;

   protected ByteArrayOutputStream baos;
   protected PrintStream ps;
   protected CommandLine commandLine;
   protected Arguments args;


   @Before
   public void abstractSetup() {
      MockitoAnnotations.initMocks(this);

      baos = new ByteArrayOutputStream();
      ps = new PrintStream(baos);

      commandLine = new CommandLine();
      args = new Arguments();
      commandLine.setArguments(args);

      setup();
   }
}
