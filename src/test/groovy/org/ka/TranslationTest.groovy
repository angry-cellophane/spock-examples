package org.ka

import spock.lang.Specification

class TranslationTest extends Specification {

    class Translation {
        final Map<String, String> map

        Translation(Map<String, String> map) {
            this.map = map
        }
    }

    def test() {
        given:
        def map = [
                "ss":"s",
                "asd":"a",
                "qwerty":"p",
                "ed": "h",
                "qwe": "q"
        ]
        def s = "asdqwertypqwed"
    }

}
