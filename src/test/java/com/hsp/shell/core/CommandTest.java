package com.hsp.shell.core;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CommandTest {
   private CommandLine commandLine;

   @Before
   public void setup() {
      commandLine = new CommandLine();
   }

   @Test
   public void canConstructCommandLine() {
      CommandLine commandLine = new CommandLine();
      assertNotNull(commandLine);
   }

   @Test
   public void canSetCommand() {
      commandLine.setCommand("cd");
      assertThat("cd", equalTo(commandLine.getCommand()));
   }

}
