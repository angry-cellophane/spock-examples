package org.ka

import org.ka.builder.Node
import org.ka.builder.NodeBuilder
import spock.lang.Specification

class DijkstraTest extends Specification {

    static class Meta {
        final double cost
        final Node prev
        final Node node
        boolean used

        Meta(double cost, Node node, Node prev) {
            this.cost = cost
            this.node = node
            this.prev = prev
            this.used = false
        }
    }

    List<Node> minPath(Node from, Node to) {
        Map<String, Meta> metaByNodeValue = [:]
        metaByNodeValue[from.value] = new Meta(0,from, null)

        Node node
        while ( (node = metaByNodeValue.findAll{ !it.value.used }?.min { it.value.cost }?.value?.node) != null) {
            def nMeta = metaByNodeValue[node.value]
            nMeta.used = true

            node.edges.each { e ->
                def meta = metaByNodeValue[e.to.value]
                if (meta == null || meta.cost > nMeta.cost + e.cost) {
                    meta = new Meta(nMeta.cost + e.cost, e.to, node)
                    metaByNodeValue[e.to.value] = meta
                }
            }
        }

        List<Node> result = []
        Meta meta = metaByNodeValue.find { it.value.node == to }?.value
        while (meta != null) {
            result.add(0, meta.node)
            meta = metaByNodeValue.find { it.value.node == meta.prev }?.value
        }

        return result
    }


    def test() {
        given:
        def builder = new NodeBuilder()

        Node root = builder.node1 {
            node2 {
                node8
            }
            node3 {
                node6 {
                    node5
                }
            }
            node4 {
                node7 {
                    node8 {
                        node5
                    }
                    node6
                }
            }
        }

        def node5 = root.findNode { it.value == 'node5' }

        expect:
        minPath(root, node5)*.value == [ 'node1', 'node2', 'node8', 'node5' ]
    }


}
