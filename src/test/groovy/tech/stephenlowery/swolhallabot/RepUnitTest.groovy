package tech.stephenlowery.swolhallabot

import spock.lang.Specification
import tech.stephenlowery.swolhallabot.RepUnit

class RepUnitTest extends Specification {
    def "getConjugated properly conjugates based on amount"() {
        expect:
        repUnit.getConjugated(1) == expectedSingular
        repUnit.getConjugated(2) == expectedPlural

        where:
        repUnit        | expectedSingular | expectedPlural
        RepUnit.SECOND | "second"         | "seconds"
        RepUnit.STEP   | "step"           | "steps"
    }
}
