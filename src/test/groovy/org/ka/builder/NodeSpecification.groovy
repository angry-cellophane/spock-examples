package org.ka.builder


class NodeSpecification {

    private static final double DEFAULT_COST = 1.0

    private int id = 1
    final Map<String, Node> nodesByValue = [:]
    final List<Node> stack = []

    def methodMissing(String name, def args) {
        if (!checkArgs(args)) throw new RuntimeException("""
Wrong node defining method usage.
Usage:
    node(value: 'my node', cost: 3.0) {
        ...
    }
    or
    nodeMyAnotherNode {
        ...
    }
""")
        double cost = DEFAULT_COST
        String nodeValue = name
        if (args.size() == 2) {
            Map<String, Object> params = args[0]
            nodeValue = params.getOrDefault('value', nodeValue)
            cost = params.getOrDefault('cost', cost)
        }

        def node = createNode(nodeValue, cost)

        stack.push(node)
        Closure c = extractClosure(args)
        c.setDelegate(this)
        c.setResolveStrategy(Closure.DELEGATE_FIRST)
        c.call(this)
        stack.pop()

        return node
    }

    private boolean checkArgs(args) {
        (args != null
        || (args.size() == 1 && args[0] instanceof Closure)
        || ((args.size() == 2 && args[0] instanceof Map && args[1] instanceof Closure)))
    }

    private Closure extractClosure(args) {
        args.size() == 1 ? args[0] : args[1]
    }


    def propertyMissing(String name) {
        def node = createNode(name, DEFAULT_COST)

        return node
    }

    Node createNode(String name, double cost) {
        String nodeValue = name
        def node = nodesByValue[nodeValue]
        if (!node) {
            node = new Node(id++, nodeValue)
            nodesByValue[nodeValue] = node
        }

        if (!stack.isEmpty()) {
            Node root = stack.last()
            root.edges << new Edge(root, node, cost)
        }

        return node
    }

}
