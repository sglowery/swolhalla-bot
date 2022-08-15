package tech.stephenlowery.swolhallabot

import kotlin.ranges.IntRange
import spock.lang.Specification
import tech.stephenlowery.swolhallabot.BodyweightWorkout
import tech.stephenlowery.swolhallabot.RepUnit

class BodyweightWorkoutTest extends Specification {
    def "toString properly formats workout"() {
        expect:
        expected == new BodyweightWorkout("workouts", repUnit, new IntRange(amount, amount), isVerb).toString()

        where:
        repUnit        | amount | isVerb || expected
        RepUnit.SECOND | 2      | false  || "do workouts for 2 seconds"
        RepUnit.SECOND | 2      | true   || "workouts for 2 seconds"
        RepUnit.SELF_NAMED | 2      | false  || "do 2 workouts"
        RepUnit.STEP       | 2      | false  || "do 2 workouts steps"
    }
}
