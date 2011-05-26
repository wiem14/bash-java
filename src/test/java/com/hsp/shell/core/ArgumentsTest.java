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
      for (String arg : arguments.getArgs()) {
         switch (counter++) {
            case 1:
               assertThat("1", equalTo(arg));
               break;
            case 2:
               assertThat("2", equalTo(arg));
               break;
            case 3:
               assertThat("3", equalTo(arg));
               break;
         }
      }
   }
}
