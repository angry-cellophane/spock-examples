package org.ka.types

import groovy.transform.TypeChecked
import spock.lang.Specification

@TypeChecked
class TypesCheckedTest extends Specification {

    interface Eater<I> {
        void eat(I value);
    }

    static class A<I, O> implements Eater<I> {

        @Override
        void eat(I value) {}

        def <C extends Eater<? super O>> C andThen(C next) {
            return next;
        }

    }

    static class B<I> implements Eater<I> {

        @Override
        void eat(I value) {}
    }

    def "should chain"() {
        when:
        A<String, Integer> a1 = new A<>();
        A<Integer, Double> a2 = new A<>();
        B<Double> b = new B<>();

        a1.andThen(a2);
        a2.andThen(b);

        a1.andThen(a2).andThen(b);

        then:
        noExceptionThrown()
    }

}
