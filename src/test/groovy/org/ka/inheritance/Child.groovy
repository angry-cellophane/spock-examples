package org.ka.inheritance

import org.spockframework.runtime.AbstractRunListener
import org.spockframework.runtime.model.ErrorInfo
import org.spockframework.runtime.model.SpecInfo
import spock.lang.Stepwise

@Stepwise
class Child extends Parent {

    def setupSpec() {
        def spec = this.getSpecificationContext().currentSpec
        spec.addListener(new AbstractRunListener() {
            @Override
            void error(ErrorInfo error) {
                spec.features.each { it.skipped = true }
            }
        })
    }

    def "test"() {
        given:
        println "Child::test#1"
    }

    def "test#2"() {
        given:
        println "Child::test#2"
    }

}
