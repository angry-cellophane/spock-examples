package org.ka

import spock.lang.Specification

class LongestNumbersSequenceTest extends Specification {

    List<Integer> findLongest(List<Integer> a) {
        if (a.size() == 1) return a

        int start = 0
        int maxLength = 0
        int finalStart = 0
        int finalEnd = 0
        for (int i = 1; i <a.size(); i++) {
            if (!isDiff(a[i-1], a[i])) {
                if (i - start > maxLength) {
                    finalStart = start
                    finalEnd = i
                    maxLength = finalEnd - finalStart
                }
                start = i
            }
        }
        if (a.size() - start > maxLength) {
            finalStart = start
            finalEnd = a.size()
        }
        return a.subList(finalStart, finalEnd)
    }

    boolean isDiff(int a, int b) {
        a * b < 0
    }

    def test() {
        when:
        def actual = findLongest(input)

        then:
        actual == expected

        where:
        input                              | expected
        [1, -1, 1, -1]                     | [1, -1, 1, -1]
        [-2, 4, -5, 1234]                  | [-2, 4, -5, 1234]
        [-1, -1, 1, -1]                    | [-1, 1, -1]
        [1, 1, 1, -1]                      | [1, -1]
        [-2312, 1, 3, -4, 32, -13, 23, 54] | [3, -4, 32, -13, 23]

    }

}
