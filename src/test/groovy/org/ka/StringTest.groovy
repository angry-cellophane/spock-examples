package org.ka

import spock.lang.Specification

class StringTest extends Specification {

    List<String> marks(String s) {
        def a = s.toCharArray()
        List<char[]> result = [a]

        for (int i = 0; i<a.length; i++) {
            if (a[i] != '?' ) continue;

            def end = result.size()
            for (int j = 0; j < end; j++) {
                def newArray = Arrays.copyOf(result.get(j), result.get(j).length)

                result.get(j)[i] = '0'
                newArray[i] = '1'
                result << newArray
            }
        }

        result.collect { new String(it) }
    }

    def '1011?01?01'() {
        given:
        def s = '1011?01?01?0'

        when:
        def result = marks(s)
        then:
        result as Set == [
                '101100100100',
                '101100110100',
                '101110100100',
                '101110110100',
                '101100100110',
                '101100110110',
                '101110100110',
                '101110110110'] as Set
    }

}
