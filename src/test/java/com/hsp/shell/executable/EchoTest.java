package com.hsp.shell.executable;

import com.hsp.shell.core.Argument;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class EchoTest extends AbstractExecutableTestBase {

   private Echo echo;

   @Override
   protected void setup() {
      MockitoAnnotations.initMocks(this);
      echo = new Echo();
   }

   @Test
   public void shouldEchoSimpleString() {
      args.add("hello");
      echo.execute(commandLine, ps, null);
      assertThat(baos.toString(), is("hello\n"));
   }

   @Test
   public void shouldHonourIgnoreNewLine() {
      args.add(new Argument("-n"));
      args.add("hello");
      echo.execute(commandLine, ps, null);
      assertThat(baos.toString(), is("hello"));
   }

   @Test
   public void shouldRemoveQuotesWhenEchoing() {
      args.add("\"hello\"");
      echo.execute(commandLine, ps, null);
      assertThat(baos.toString(), is("hello\n"));
   }

   @Test
   public void shouldEchoMultipleWordsOnOneLine() {
      args.add("\"hello world\"");
      echo.execute(commandLine, ps, null);
      assertThat(baos.toString(), is("hello world\n"));
   }

   @Test
   public void shouldEchoMultipleWordsNotEnclosedInQuotesOnOneLine() {
      args.add("hello");
      args.add("world");
      echo.execute(commandLine, ps, null);
      assertThat(baos.toString(), is("hello world\n"));
   }

}
