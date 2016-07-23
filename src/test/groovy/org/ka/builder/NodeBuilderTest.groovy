package org.ka.builder

import spock.lang.Specification


class NodeBuilderTest extends Specification {

    def test() {
        given:
        def builder = new NodeBuilder()

        Node root = builder.node1 {
            node2(value: 'node 2', cost: 3.0) {
                node3
                node4 {
                    node6
                }
                node5 {
                    node4
                }
            }
            node6 {
                node8 {
                    node9
                }
            }
            node7
        }

        expect:
        root.value == 'node1'
        root.id == 1
        root.edges.size() == 3
        root.edges.collect { it.from.value } as Set == ['node1'] as Set

        root.edges.find { it.to.value == 'node 2' } != null
        root.edges.find { it.to.value == 'node 2' }.to.edges.size() == 3

        root.edges.find { it.to.value == 'node 2' }.to.edges.find {it.to.value == 'node4'}.to == root.edges.find { it.to.value == 'node 2' }.to.edges.find {it.to.value == 'node5'}.to.edges.find {it.to.value == 'node4'}.to
    }

}
