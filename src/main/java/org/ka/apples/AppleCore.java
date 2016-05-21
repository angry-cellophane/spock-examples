package org.ka.apples;


public class AppleCore {

    public final int id;

    public AppleCore(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppleCore appleCore = (AppleCore) o;

        return id == appleCore.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
