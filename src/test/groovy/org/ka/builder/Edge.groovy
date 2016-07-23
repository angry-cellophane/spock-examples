package org.ka.builder

class Edge {
    final Node from
    final Node to
    final double cost

    Edge(Node from, Node to, double cost) {
        this.from = from
        this.to = to
        this.cost = cost
    }
}
