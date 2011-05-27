package com.hsp.shell.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 *
 */
public class CommandExecutorTest {

   private CommandExecutor executor;

   @Mock
   private CommandLine mockCommandLine;

   @Mock
   private ExecutablePath mockPath;

   @Mock
   private Executable mockExecutable;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      executor = new CommandExecutor(mockPath);
   }

   @Test
   public void executeShouldReturnZero() {
      when(mockPath.locateExecutable("foobar")).thenReturn(mockExecutable);
      when(mockCommandLine.getCommand()).thenReturn("foobar");

      assertThat(0, equalTo(executor.executeCommand(mockCommandLine, System.out)));
   }
}
