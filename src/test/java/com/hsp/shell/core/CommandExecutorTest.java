package com.hsp.shell.core;

import com.hsp.shell.core.exception.ExecutionResult;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 */
public class CommandExecutorTest {

   private CommandExecutor executor;

   @Mock
   private Environment mockEnvironment;

   @Mock
   private CommandLine mockCommandLine;

   @Mock
   private ExecutablePath mockPath;

   @Mock
   private Executable mockExecutable;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      executor = new CommandExecutor(mockPath, mockEnvironment);
   }

   @Test
   public void shouldSetExitStatusWhenCommandCompletes() {
      ExecutionResult expectedResult = new ExecutionResult(888);

      when(mockPath.locateExecutable("cd")).thenReturn(mockExecutable);
      when(mockCommandLine.getCommand()).thenReturn("cd");
      when(mockExecutable.execute(mockCommandLine, System.out, mockEnvironment)).thenReturn(expectedResult);

      assertThat(executor.executeCommand(mockCommandLine, System.out), equalTo(expectedResult));
      verify(mockEnvironment).setProperty(Environment.EXIT_STATUS, String.valueOf(888));
   }

}
