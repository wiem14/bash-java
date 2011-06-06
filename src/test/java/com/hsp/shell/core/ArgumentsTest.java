package com.hsp.shell.core;

import com.hsp.shell.ShellException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ArgumentsTest {
   private Arguments arguments;

   @Before
   public void setup() {
      arguments = new Arguments();
   }

   @Test
   public void canAddArguments() {
      assertThat(arguments.add("foo"), is(true));
   }

   @Test
   public void testSize() {
      assertThat(arguments.size(), is(0));
      arguments.add("foo");
      assertThat(arguments.size(), is(1));
   }

   @Test
   public void shouldReturnLastArgument() {
      arguments.add("foo");
      assertThat(arguments.getLast().getArgument(), is("foo"));
   }

   @Test
   public void shouldThrowShellExceptionIfNoArgumentsPresent() {
      try {
         arguments.getLast();
         fail("Should have thrown exception");
      } catch (ShellException e) {
         ;
      }
   }

   @Test
   public void argumentsShouldIterateInTheOrderTheyWereAdded() {
      arguments.add("1");
      arguments.add("2");
      arguments.add("3");

      int counter = 1;
      for (Argument arg : arguments.getArgs()) {
         String argOption = arg.getArgument();
         switch (counter++) {
            case 1:
               assertThat(argOption, equalTo("1"));
               break;
            case 2:
               assertThat(argOption, equalTo("2"));
               break;
            case 3:
               assertThat(argOption, equalTo("3"));
               break;
         }
      }
   }
}
