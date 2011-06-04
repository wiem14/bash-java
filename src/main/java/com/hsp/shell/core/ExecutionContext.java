package com.hsp.shell.core;

/**
 * @author georgepapas
 */
public class ExecutionContext {

   private Environment environment;
   private ExecutablePath path;

   public ExecutionContext(Environment environment, ExecutablePath path) {
      this.environment = environment;
      this.path = path;
   }

   public ExecutablePath getPath() {
      return path;
   }

   public Environment getEnvironment() {
      return environment;

   }
}

