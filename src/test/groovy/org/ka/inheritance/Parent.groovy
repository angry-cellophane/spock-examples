package org.ka.inheritance

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
class Parent extends Specification {

    @Shared String name

    def setupSpec() {
        println "Parent::setupSpec"
    }

    def setup() {
        println "Parent::setup"
    }

    def "test #1"() {
        given:
        println("Parent::test method #1")
        assert 1 == 2
    }

    def "teste #2"() {
        given:
        println("Parent::test method #2")
    }

    def cleanup() {
        println "Parent::cleanup"
    }

    def cleanupSpec() {
        println "Parent::cleanupSpec"
    }

}
