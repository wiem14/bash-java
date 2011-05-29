package com.hsp.shell.core;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

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
      assertThat(commandLine.getCommand(), is("cd"));
   }


   @Test
   public void shouldIngoreTrailingAndLeadingWhiteSpace() {
      CommandLine commandLine = parser.parse(" cd   ");
      assertThat(commandLine, notNullValue());
      assertThat(commandLine.getCommand(), is("cd"));
   }

   @Test
   public void shouldAcceptSingleArgument() {
      CommandLine command = parser.parse("cd arg1");
      assertThat(command.getCommand(), is("cd"));

      Arguments args = command.getArguments();
      assertThat(args, notNullValue());

      Argument arg0 = args.getArgs().get(0);
      assertThat(arg0.getOption(), is("arg1"));
   }

   @Test
   public void shouldHandleSingleAndDoubleDashesAsOptionDelimiter() {
      CommandLine command = parser.parse("cd -opt1 val1 --opt2 val2");

      Arguments args = command.getArguments();
      assertThat(args, notNullValue());

      Argument arg0 = args.getArgs().get(0);
      assertThat(arg0.getOption(), is("-opt1"));
      assertThat(arg0.getValue(), is("val1"));

      arg0 = args.getArgs().get(1);
      assertThat(arg0.getOption(), is("--opt2"));
      assertThat(arg0.getValue(), is("val2"));
   }

   @Test
   public void shouldAcceptOptions() {
      CommandLine command = parser.parse("cd --option value");

      Arguments args = command.getArguments();
      assertThat(args, notNullValue());

      Argument arg0 = args.getArgs().get(0);
      assertThat(arg0.getOption(), is("--option"));
      assertThat(arg0.getValue(), is("value"));
   }

   @Test
   public void shoulHandleArgWithNoValues() {
      CommandLine command = parser.parse("cd --opt1 --opt2 val2");

      Arguments args = command.getArguments();
      assertThat(args, notNullValue());

      assertThat(args.size(), is(2));

      Argument arg = args.getArgs().get(0);
      assertThat(arg.getOption(), is("--opt1"));
      assertThat(arg.getValue(), nullValue());

      arg = args.getArgs().get(1);
      assertThat(arg.getOption(), is("--opt2"));
      assertThat(arg.getValue(), is("val2"));
   }

   @Test
   public void shouldAcceptMultipleOptions() {
      CommandLine command = parser.parse("cd --opt1 val1 --opt2 val2");

      Arguments args = command.getArguments();
      assertThat(args, notNullValue());

      assertThat(args.size(), is(2));

      Argument arg = args.getArgs().get(0);
      assertThat(arg.getOption(), is("--opt1"));
      assertThat(arg.getValue(), is("val1"));

      arg = args.getArgs().get(1);
      assertThat(arg.getOption(), is("--opt2"));
      assertThat(arg.getValue(), is("val2"));
   }
}
