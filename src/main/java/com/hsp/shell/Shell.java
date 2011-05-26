package com.hsp.shell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;


public class Shell {

   public static void main(String[] args) {

      Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
      OutputStream out = System.out;

      while (true) {
         System.out.print("Prompt :> ");
         System.out.println("You entered : " + scanner.nextLine());
      }
   }
}
