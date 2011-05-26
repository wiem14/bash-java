package com.hsp.shell.core;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EnvironmentTest {

   private Environment environment;


   @Before
   public void setup() {
      environment = new Environment();
   }


   @Test
   public void canInstantiateEnvironment() {
      assertNotNull(new Environment());
   }

   @Test
   public void shouldHaveDefaultSetOfPropertiesInitialized() {
      assertThat(environment.getProperties().size(), greaterThan(0));
   }

   @Test
   public void shouldReturnNullForPropertyThatDoesNotExist() {
      assertThat(environment.getProperty("loreumipsum"), nullValue());
   }

   @Test
   public void userHomePropertyShouldNotBeNull() {
      assertThat(environment.getProperty(Environment.USER_HOME), notNullValue());
   }

   @Test
   public void shouldBeAbleToAddNewProperty() {
      final int originalPropertyCount = environment.getProperties().size();
      environment.setProperty("foo", "bar");
      assertThat(originalPropertyCount + 1, is(environment.getProperties().size()));
   }

   @Test
   public void newPropertiesMustHaveAValue() {
      try {
         environment.setProperty("foo", null);
         fail("Should have thrown an environment exception");
      } catch (EnvironmentException e) {
         ; // this is what we expect
      }
   }

   @Test
   public void newPropertyNamesCannotBeNull() {
      try {
         environment.setProperty(null, "bar");
         fail("Should have thrown an environment exception");
      } catch (EnvironmentException e) {
         ; // this is what we expect
      }
   }

   @Test
   public void addingNewPropertyShouldReplaceExistingProperty() {
      environment.setProperty("foo", "one");
      assertThat(environment.getProperty("foo"), equalTo("one"));

      environment.setProperty("foo", "two");
      assertThat(environment.getProperty("foo"), equalTo("two"));
   }
}
