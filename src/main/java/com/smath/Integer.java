package com.smath;

import java.math.BigInteger;

public class Integer extends Number {

    public final BigInteger val;

    public Integer(int value) {
        val = BigInteger.valueOf(value);
    }

    public Integer(long value) {
        val = BigInteger.valueOf(value);
    }

    public Integer(String value) {
        val = new BigInteger(value);
    }
}
