package com.hsp.shell.core;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

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
   public void shouldTreatQuotedArgumentsAsASingleArgument() {
      CommandLine commandLine = parser.parse("echo \"foo bar\"");
      assertThat(commandLine, notNullValue());
      assertThat(commandLine.getCommand(), is("echo"));

      Arguments args = commandLine.getArguments();
      assertThat(args, notNullValue());
      assertThat(args.size(), is(1));

      Argument arg0 = args.getArgs().get(0);
      assertThat(arg0.getArgument(), is("foo bar"));
   }


   @Test
   public void shouldIgnoreTrailingAndLeadingWhiteSpace() {
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
      assertThat(arg0.getArgument(), is("arg1"));
   }

   @Test
   public void shouldHandleSingleAndDoubleDashesAsOptionDelimiter() {
      CommandLine command = parser.parse("cd -opt1 val1 --opt2 val2");

      Arguments args = command.getArguments();
      assertThat(args, notNullValue());

      assertThat(args.getArgs().get(0).getArgument(), is("-opt1"));
      assertThat(args.getArgs().get(1).getArgument(), is("val1"));

      assertThat(args.getArgs().get(2).getArgument(), is("--opt2"));
      assertThat(args.getArgs().get(3).getArgument(), is("val2"));
   }

   @Test
   public void shouldAcceptOptions() {
      CommandLine command = parser.parse("cd --option value");

      Arguments args = command.getArguments();
      assertThat(args, notNullValue());

      assertThat(args.getArgs().get(0).getArgument(), is("--option"));
      assertThat(args.getArgs().get(1).getArgument(), is("value"));
   }

   @Test
   public void shoulHandleArgWithNoValues() {
      CommandLine command = parser.parse("cd --opt1 --opt2 val2");

      Arguments args = command.getArguments();
      assertThat(args, notNullValue());

      assertThat(args.size(), is(3));

      assertThat(args.getArgs().get(0).getArgument(), is("--opt1"));
      assertThat(args.getArgs().get(1).getArgument(), is("--opt2"));
      assertThat(args.getArgs().get(2).getArgument(), is("val2"));
   }

   @Test
   public void shouldAcceptMultipleOptions() {
      CommandLine command = parser.parse("cd --opt1 val1 --opt2 val2");

      Arguments args = command.getArguments();
      assertThat(args, notNullValue());

      assertThat(args.size(), is(4));

      assertThat(args.getArgs().get(0).getArgument(), is("--opt1"));
      assertThat(args.getArgs().get(1).getArgument(), is("val1"));

      assertThat(args.getArgs().get(2).getArgument(), is("--opt2"));
      assertThat(args.getArgs().get(3).getArgument(), is("val2"));
   }
}
