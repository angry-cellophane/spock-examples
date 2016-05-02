package org.ka.test.integration

import spock.lang.Specification

import java.util.concurrent.atomic.AtomicInteger


class SimpleSpockTest extends Specification {

    def 'example'() {
        given:
        def a = new AtomicInteger(0)
        when:
        a.incrementAndGet()
        then:
        def b = 1
        a.get() == b
    }
}
