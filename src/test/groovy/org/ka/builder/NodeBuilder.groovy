package org.ka.builder


class NodeBuilder {

    def methodMissing(String name, def args) {
        if (!name.startsWith('node')) throw new RuntimeException('NodeBuilder supports methods starting with node only')

        def spec = new NodeSpecification()
        def rootNode = spec.invokeMethod(name, args)

        return rootNode
    }
}
