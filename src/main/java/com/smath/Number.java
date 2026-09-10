package com.smath;

public abstract class Number {

    public abstract String toString();

    public abstract Number add(Number other);

    public abstract Number negate();

    public abstract Number subtract(Number other);

    public abstract Number mult(Number other);

    public abstract Number div(Number other);
}
