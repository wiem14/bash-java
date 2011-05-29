package com.hsp.shell.core;

import com.hsp.shell.core.exception.EnvironmentException;

import java.util.Properties;


public class Environment {

   public static final String USER_HOME = "user.home";

   private Properties properties;

   public Environment() {
      this.properties = System.getProperties();
   }

   public Properties getProperties() {
      return properties;
   }

   public String getProperty(String propertyName) {
      return properties.getProperty(propertyName);
   }

   public void setProperty(String key, String value) {
      if (key == null || value == null) {
         throw new EnvironmentException("Property key and property value cannot be null");
      }
      properties.setProperty(key, value);
   }
}
