package com.smath;

import java.math.BigInteger;

public class Integer extends Number {

    public final BigInteger val;

    @Override
    protected int rank() {
        return 1;
    }

    @Override
    protected Number promoteOnce() {
        return new Fraction(this);
    }

    @Override
    public Number inverse() {
        return new Fraction(BigInteger.ONE, val);
    }

    public Integer(int value) {
        val = BigInteger.valueOf(value);
    }

    public Integer(long value) {
        val = BigInteger.valueOf(value);
    }

    public Integer(String value) {
        val = new BigInteger(value);
    }

    public Integer(BigInteger value) {
        val = value;
    }

    @Override
    public Number negate() {
        return new Integer(val.negate());
    }

    @Override
    protected Number addSame(Number other) {
        return new Integer(val.add(((Integer) other).val));
    }

    @Override
    protected Number subtSame(Number other) {
        return new Integer(val.subtract(((Integer) other).val));
    }

    @Override
    protected Number multSame(Number other) {
        return new Integer(val.multiply(((Integer) other).val));
    }

    @Override
    protected Number divSame(Number other) {
        Integer denominator = (Integer) other;
        if (val.mod(denominator.val).equals(BigInteger.ZERO)) return new Integer(val.divide(((Integer) other).val));
        else return promoteOnce().div(other);
    }

    @Override
    protected Number tryDemote() {
        return this;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
