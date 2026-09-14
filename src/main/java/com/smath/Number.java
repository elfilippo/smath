package com.smath;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class Number {

    public static final int MAX_PRECISION = 500_000_000;

    protected record ApproxResult(BigDecimal value, boolean exact) {}

    public abstract String toString();

    protected abstract int rank();

    protected abstract Number promoteOnce();

    public abstract Number inverse();

    public abstract ApproxResult approxTo(int decimalPlaces);

    public static final Number of(int value) {
        return new Integer(value);
    }

    public static final Number of(long value) {
        return new Integer(value);
    }

    public static final Number of(double value) {
        return of(new BigDecimal(value));
    }

    public static final Number of(float value) {
        return of((double) value);
    }

    public static final Number of(BigInteger value) {
        return new Integer(value);
    }

    public static final Number of(BigDecimal value) {
        Fraction fraction = new Fraction(value);
        if (fraction.denom.equals(BigInteger.ONE)) return new Integer(fraction.numer);
        else return fraction;
    }

    public static final Number of(String value) {
        return of(new BigDecimal(value));
    }

    protected final Number promoteTo(int rank) {
        Number current = this;
        while (current.rank() < rank) current = promoteOnce();
        return current;
    }

    public final Number add(Number other) {
        int highestRank = Math.max(rank(), other.rank());
        Number x = promoteTo(highestRank);
        Number y = other.promoteTo(highestRank);
        return x.addSame(y).tryDemote();
    }

    public final Number add(int other) {
        return add(new Integer(other));
    }

    public final Number add(long other) {
        return add(new Integer(other));
    }

    protected abstract Number addSame(Number other);

    public Number subt(Number other) {
        int highestRank = Math.max(rank(), other.rank());
        Number x = promoteTo(highestRank);
        Number y = other.promoteTo(highestRank);
        return x.subtSame(y).tryDemote();
    }

    public final Number subt(int other) {
        return subt(new Integer(other));
    }

    public final Number subt(long other) {
        return subt(new Integer(other));
    }

    public Number mult(Number other) {
        int highestRank = Math.max(rank(), other.rank());
        Number x = promoteTo(highestRank);
        Number y = other.promoteTo(highestRank);
        return x.multSame(y).tryDemote();
    }

    public final Number mult(int other) {
        return mult(new Integer(other));
    }

    public final Number mult(long other) {
        return mult(new Integer(other));
    }

    public Number div(Number other) {
        int highestRank = Math.max(rank(), other.rank());
        Number x = promoteTo(highestRank);
        Number y = other.promoteTo(highestRank);
        return x.divSame(y).tryDemote();
    }

    public final Number div(int other) {
        return div(new Integer(other));
    }

    public final Number div(long other) {
        return div(new Integer(other));
    }

    protected abstract Number tryDemote();

    public abstract Number negate();

    protected abstract Number subtSame(Number other);

    protected abstract Number divSame(Number other);

    protected abstract Number multSame(Number other);
}
