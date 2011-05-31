package com.hsp.shell.executable;

import com.hsp.shell.core.Argument;
import com.hsp.shell.core.Arguments;
import com.hsp.shell.core.CommandLine;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class EchoTest {

   private Echo echo;
   private CommandLine commandLine;
   private ByteArrayOutputStream baos;
   private PrintStream ps;
   private Arguments args;

   @Before
   public void setup() {
      commandLine = new CommandLine();
      args = new Arguments();
      commandLine.setArguments(args);

      echo = new Echo();
      baos = new ByteArrayOutputStream();
      ps = new PrintStream(baos);
   }

   @Test
   public void shouldEchoSimpleString() {
      args.add("hello");
      echo.execute(commandLine, ps);
      assertThat(baos.toString(), is("hello\n"));
   }

   @Test
   public void shouldHonourIgnoreNewLine() {
      args.add(new Argument("-n"));
      args.add("hello");
      echo.execute(commandLine, ps);
      assertThat(baos.toString(), is("hello"));
   }

   @Test
   public void shouldRemoveQuotesWhenEchoing() {
      args.add("\"hello\"");
      echo.execute(commandLine, ps);
      assertThat(baos.toString(), is("hello\n"));
   }

   @Test
   public void shouldEchoMultipleWordsOnOneLine() {
      args.add("\"hello world\"");
      echo.execute(commandLine, ps);
      assertThat(baos.toString(), is("hello world\n"));
   }

   @Test
   public void shouldEchoMultipleWordsNotEnclosedInQuotesOnOneLine() {
      args.add("hello");
      args.add("world");
      echo.execute(commandLine, ps);
      assertThat(baos.toString(), is("hello world\n"));
   }

}