package com.smath;

import java.io.IO;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        IO.println(Number.of("24").div(Number.of("250").mult(5)).approxTo(2));
    }
}
