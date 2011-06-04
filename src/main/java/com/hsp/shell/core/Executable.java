package com.hsp.shell.core;

import com.hsp.shell.core.exception.ExecutionResult;

import java.io.OutputStream;


public interface Executable {

   ExecutionResult execute(CommandLine commandLine, OutputStream out, Environment environment);

   String name();

   String getHelp();
}
