package com.hsp.shell.util;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;


public class ClassUtilsTest {

   @Test
   public void shouldLoadClassWithDotClassExtension() throws ClassNotFoundException {
      assertClassLoaded("java.lang.String.class");
   }

   @Test
   public void shouldLoadClassWithNoExtension() throws ClassNotFoundException {
      final String classToLoad = "java.lang.String";
      assertClassLoaded(classToLoad);
   }

   private void assertClassLoaded(String classToLoad) throws ClassNotFoundException {
      Class<?> loadedClass = ClassUtils.loadClassFromFile(classToLoad);

      assertThat(String.class.isAssignableFrom(loadedClass), is(true));
      assertThat(loadedClass, notNullValue());
   }

}
