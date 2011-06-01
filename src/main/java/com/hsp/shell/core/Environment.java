package com.hsp.shell.core;

import com.hsp.shell.core.exception.EnvironmentException;

import java.util.Properties;


public class Environment {

   public static final String PROPERTY_CHAR = "$";

   public static final String EXIT_STATUS = "?";
   public static final String USER_HOME = "user.home";

   private Properties properties;

   public Environment() {
      this.properties = System.getProperties();
      loadDefaults();
   }

   public Properties getProperties() {
      return properties;
   }

   public String getProperty(String propertyName) {
      return properties.getProperty(propertyName);
   }

   public String getProperty(String propertyName, String defaultValue) {
      return properties.getProperty(propertyName, defaultValue);
   }

   public void setProperty(String key, String value) {
      if (key == null || value == null) {
         throw new EnvironmentException("Property key and property value cannot be null");
      }
      properties.setProperty(key, value);
   }

   private void loadDefaults() {
      properties.put("HOME", properties.getProperty("user.home"));
   }
}
