package com.hsp.shell.core;

import com.hsp.shell.core.exception.CommandNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;


public class ExecutablePathTest {

   @Mock
   private Executable mockExecutable1;

   @Mock
   private Executable mockExecutable2;

   private ExecutablePath path;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      path = new ExecutablePath();
   }

   @Test
   public void shouldLocateRegisteredCommand() {
      when(mockExecutable1.getName()).thenReturn("foo");
      path.registerExecutable(mockExecutable1);

      assertThat(path.locateExecutable("foo"), equalTo(mockExecutable1));
   }

   @Test
   public void shouldReturnAllRegisteredCommands() {
      int execsCount = path.getAllRegisteredExecutables().size();

      when(mockExecutable1.getName()).thenReturn("foo");
      when(mockExecutable2.getName()).thenReturn("bar");
      path.registerExecutable(mockExecutable1);
      path.registerExecutable(mockExecutable2);

      List<Executable> execs = path.getAllRegisteredExecutables();
      assertThat(execs.size(), equalTo(execsCount + 2));
   }

   @Test
   public void shouldThrowCommandNotFoundWhenNullCommandNameUsed() {
      assertCommandNotFound(null);
   }

   @Test
   public void shouldThrowCommandNotFoundWhenCommandDoesNotExist() {
      assertCommandNotFound("lorem");
   }

   private void assertCommandNotFound(String commandName) {
      try {
         path.locateExecutable(commandName);
         fail("Expected CommandNotFoundException to be thrown");
      } catch (CommandNotFoundException e) {
         assertThat(commandName, equalTo(e.getMessage()));
      }
   }
}
