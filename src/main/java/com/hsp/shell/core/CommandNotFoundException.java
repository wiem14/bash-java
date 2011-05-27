package com.hsp.shell.core;

import com.hsp.shell.ShellException;

/**
 *
 */
public class CommandNotFoundException extends ShellException {
   public CommandNotFoundException(String message) {
      super(message);
   }

   public CommandNotFoundException(String message, Throwable t) {
      super(message, t);
   }
}
