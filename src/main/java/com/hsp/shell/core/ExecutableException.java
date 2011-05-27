package com.hsp.shell.core;

import com.hsp.shell.ShellException;

/**
 *
 */
public class ExecutableException extends ShellException {

   public ExecutableException(String message, Throwable t) {
      super(message, t);
   }
}
