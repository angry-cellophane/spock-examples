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
        Map<String, String> resolve(Collection<Item> items)
    }

    def test() {
        given:
        def resolver = Mock(Resolver) {
            resolve([new Item(id: 1, value: "1"), new Item(id: 2, value: "2")]) >> ["1":"1", "2" : "2"]
        }

        when:
        def map1 = resolver.resolve([new Item(id: 1, value: "1"), new Item(id: 2, value: "2")])
        def map2 = resolver.resolve([new Item(id: 1, value: "1")])

        then:
        map1 != null
        map2 == null
    }

}
