package org.ka.apples;


public class Apple {

    public final int id;

    public Apple(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apple apple = (Apple) o;

        return id == apple.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
