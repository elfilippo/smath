package com.smath;

import java.io.IO;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        IO.println(Number.of(5).div(Number.of("42")).approxTo(10000));
    }
}
