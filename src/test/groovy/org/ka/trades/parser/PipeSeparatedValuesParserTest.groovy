package org.ka.trades.parser

import org.ka.trades.model.Trade
import spock.lang.Specification


class PipeSeparatedValuesParserTest extends Specification {

    def parser = new PipeSeparatedValuesParser()

    def 'only obligatory and correct values'() {
        when:
        def actualTrade = parser.parse(message)
        then:
        expectedTrade == actualTrade
        where:
        message                                          || expectedTrade
        'id=123|type=Buy|legalEntityId=BigBank234'       || new Trade(id: 123, type: 'Buy', legalEntityId: 'BigBank234')
        'id=567|type=Sell|legalEntityId=BigBank235643'   || new Trade(id: 567, type: 'Sell', legalEntityId: 'BigBank235643')
        'id=98765|type=Buy|legalEntityId=BigBank654433|' || new Trade(id: 98765, type: 'Buy', legalEntityId: 'BigBank654433')
    }

    def 'only obligatory and correct values #2'() {
        when:
        def actualTrade = parser.parse(message)
        then:
        expectedTrade == actualTrade
        where:
        id    | type   | legalEntityId
        123   | 'Buy'  | 'BigBank12345'
        456   | 'Buy'  | 'BigBank45667'
        -1234 | 'Sell' | 'Qwerty Bank'

        message = "id=${id}|type=${type}|legalEntityId=${legalEntityId}"
        expectedTrade = new Trade(id: id, type: type, legalEntityId: legalEntityId)
    }

    def 'not all obligatory fields'() {
        when:
        parser.parse(message)
        then:
        thrown(Exception)
        where:
        message                              | _
        'type=Sell|legalEntityId=Bank123 AG' | _
        'id=666|legalEntityId=Bank567 AG'    | _
        'id=777|type=Sell|'                  | _
        'id=777|'                            | _
        '|'                                  | _
        ''                                   | _
    }
}
