package com.hsp.shell.core;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class CommandParserTest {
   private CommandParser parser;

   @Before
   public void setup() {
      parser = new CommandParser();
   }

   @Test
   public void shouldParseSimpleCommandWithoutArgs() {
      CommandLine commandLine = parser.parse("cd");
      assertThat(commandLine, notNullValue());
   }
}
