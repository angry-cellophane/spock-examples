package org.ka

import groovy.transform.Canonical
import spock.lang.Specification

class ListArgumentsTest extends Specification {

    @Canonical
    static class Item {
        int id
        String value
    }

    static interface Resolver {
        int resolve(Collection<Integer> items)
    }

    def test() {
        given:
        def resolver = Mock(Resolver) {
            resolve([1,2,3] as HashSet) >> 1
            resolve(_) >> 2
        }

        when:
        def request = [1,2,3] as HashSet
        int result = resolver.resolve(request)

        then:
        result == 1
    }

}
