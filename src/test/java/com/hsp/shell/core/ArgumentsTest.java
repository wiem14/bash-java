package com.hsp.shell.core;

import org.junit.Before;
import org.junit.Test;

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
