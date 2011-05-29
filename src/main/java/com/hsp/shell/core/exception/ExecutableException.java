package com.hsp.shell.core.exception;

import com.hsp.shell.ShellException;

/**
 *
 */
public class ExecutableException extends ShellException {

   public ExecutableException(String message, Throwable t) {
      super(message, t);
   }
}
