package com.hsp.shell.core.exception;

/**
 * @author georgepapas
 */
public class ExecutionResult {

   private int statusCode;
   private boolean exitShell = false;

   public ExecutionResult(int statusCode) {
      this.statusCode = statusCode;
   }

   public ExecutionResult(int statusCode, boolean exitShell) {
      this.statusCode = statusCode;
      this.exitShell = exitShell;
   }

   public int getStatusCode() {
      return statusCode;
   }

   public boolean isExitShell() {
      return exitShell;
   }

}
