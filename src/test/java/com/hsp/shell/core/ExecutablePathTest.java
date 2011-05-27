package com.hsp.shell.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;


public class ExecutablePathTest {

   @Mock
   private Executable mockExecutable;

   private ExecutablePath path;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      path = new ExecutablePath();
   }

   @Test
   public void shouldLocateRegisteredCommand() {
      when(mockExecutable.name()).thenReturn("foo");
      path.registerExecutable(mockExecutable);

      assertThat(path.locateExecutable("foo"), equalTo(mockExecutable));
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
