package com.hsp.shell.core;

public class Argument {

   private String option;
   private String value;

   public String getOption() {
      return option;
   }

   public String getValue() {
      return value;
   }

   public Argument(String option, String value) {

      this.option = option;
      this.value = value;
   }
}
