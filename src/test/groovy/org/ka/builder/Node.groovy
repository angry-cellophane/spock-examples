package org.ka.builder

import groovy.transform.stc.ClosureParams
import groovy.transform.stc.FirstParam
import groovy.transform.stc.SecondParam
import groovy.transform.stc.SimpleType

class Node {
    final int id
    final String value
    final List<Edge> edges

    Node(int id, String value) {
        this.id = id
        this.value = value
        this.edges = []
    }

    List<Node> findNodes(@ClosureParams(value = SimpleType, options = "org.ka.builder.Node") Closure<Boolean> filter) {
        List<Node> result = edges*.to.findAll(filter)

        for (Node n in edges*.to) {
            def res = n.findNode(filter)
            if (res != null) {
                result << res
            }
        }

        result
    }

    Node findNode(@ClosureParams(value = SimpleType, options = "org.ka.builder.Node") Closure<Boolean> filter) {
        Node result = edges*.to.find(filter)
        if (result != null) return result

        for (Node n in edges*.to) {
            def res = n.findNode(filter)
            if (res != null) {
                return res
            }
        }

        return null
    }

    @Override
    String toString() {
        """{id: $id, value: $value
    refs: ${String.join(',', edges.collect { it.to.toString()})}
}"""
    }
}
