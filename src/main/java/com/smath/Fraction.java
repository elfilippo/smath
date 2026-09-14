package com.smath;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class Fraction extends Number {

    public final BigInteger numer;
    public final BigInteger denom;

    protected Fraction(BigInteger numerator, BigInteger denominator) {
        BigInteger divisor = numerator.gcd(denominator);
        numer = numerator.divide(divisor);
        denom = denominator.divide(divisor);
    }

    protected Fraction(Integer numerator, Integer denominator) {
        BigInteger divisor = numerator.val.gcd(denominator.val);
        numer = numerator.val.divide(divisor);
        denom = denominator.val.divide(divisor);
    }

    protected Fraction(double value) {
        this(new BigDecimal(value));
    }

    protected Fraction(float value) {
        this((double) value);
    }

    protected Fraction(BigDecimal value) {
        BigInteger multiplier = BigInteger.TEN.pow(value.scale());
        BigInteger rawNumer = value.multiply(new BigDecimal(multiplier)).toBigInteger();
        BigInteger divisor = multiplier.gcd(rawNumer);
        numer = rawNumer.divide(divisor);
        denom = multiplier.divide(divisor);
    }

    protected Fraction(Integer value) {
        numer = value.val;
        denom = BigInteger.ONE;
    }

    //TODO: add repeating digit detection
    @Override
    public ApproxResult approxTo(int decimalPlaces) {
        if (decimalPlaces < 1 || decimalPlaces > Number.MAX_PRECISION) throw new IllegalArgumentException(
            "precision has to be between 1 and 500 million"
        );
        BigDecimal result;
        try {
            result = new BigDecimal(numer).divide(new BigDecimal(denom));
            return new ApproxResult(result, true);
        } catch (ArithmeticException e) {
            var mc = new MathContext(decimalPlaces);
            result = new BigDecimal(numer).divide(new BigDecimal(denom), mc);
            return new ApproxResult(result, false);
        }
    }

    @Override
    protected int rank() {
        return 2;
    }

    @Override
    protected Number tryDemote() {
        if (denom.equals(BigInteger.ONE)) return new Integer(numer);
        else return this;
    }

    @Override
    public Number negate() {
        return new Fraction(numer.negate(), denom);
    }

    @Override
    protected Number addSame(Number other) {
        Fraction b = (Fraction) other;
        if (denom.equals(b.denom)) return new Fraction(numer.add(b.numer), denom);
        BigInteger multiplier = lcm(denom, b.denom);
        return new Fraction(numer.multiply(multiplier).add(b.numer.multiply(multiplier)), denom.multiply(multiplier));
    }

    @Override
    protected Number subtSame(Number other) {
        return addSame(other.negate());
    }

    @Override
    protected Number multSame(Number other) {
        Fraction b = (Fraction) other;
        return new Fraction(numer.multiply(b.numer), denom.multiply(b.denom));
    }

    @Override
    protected Number divSame(Number other) {
        return multSame(other.inverse());
    }

    @Override
    public Number inverse() {
        return new Fraction(denom, numer);
    }

    private BigInteger lcm(BigInteger a, BigInteger b) {
        return a.abs().divide(a.gcd(b)).multiply(b.abs());
    }

    @Override
    protected Number promoteOnce() {
        throw new UnsupportedOperationException("higher types are not yet implemented");
    }

    public String toStringSimple() {
        return numer + "/" + denom;
    }

    @Override
    public String toString() {
        return "\\frac{" + numer + "}{" + denom + "}";
    }
}
