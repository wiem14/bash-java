package com.hsp.shell.core;

import java.io.OutputStream;


public interface Executable {

   int execute(CommandLine commandLine, OutputStream out);

   String name();
}
