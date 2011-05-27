package com.hsp.shell;

/**
 *
 */
public class ShellException extends RuntimeException {

   public ShellException(String message) {
      super(message);
   }

   public ShellException(String message, Throwable t) {
      super(message, t);
   }

}
