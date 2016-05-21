package org.ka.apples

import spock.lang.Specification


class InvocationCountInLambdas extends Specification {

    def 'count mock object method invocations in java 8 stream processing'() {
        given:
        def apple1 = new Apple(1)
        def apple2 = new Apple(2)
        def apples = [apple1, apple2]

        def appleFunc = Mock(AppleFunction)
        def appleProcessor = new AppleProcessor(appleFunc)
        when:
        appleProcessor.process(apples)
        then:
        1 * appleFunc.apply(apple1) >> new AppleCore(apple1.id)
        1 * appleFunc.apply(apple2) >> new AppleCore(apple2.id)
    }
}
