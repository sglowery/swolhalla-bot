package tech.stephenlowery.swolhallabot

import kotlin.ranges.IntRange
import spock.lang.Specification

import java.security.InvalidParameterException

class BodyweightWorkoutTest extends Specification {

    def "toString properly formats workout"() {
        expect:
        new BodyweightWorkout("workouts", repUnit, new IntRange(amount, amount), isVerb, 1).toString() == expected

        where:
        repUnit            | amount | isVerb || expected
        RepUnit.SECOND     | 2      | false  || "do workouts for 2 seconds"
        RepUnit.SECOND     | 2      | true   || "workouts for 2 seconds"
        RepUnit.SELF_NAMED | 2      | false  || "do 2 workouts"
        RepUnit.STEP       | 2      | false  || "do 2 workouts steps"
    }

    def "rep amounts scale properly with step size"() {
        given:
        def workout = new BodyweightWorkout('', RepUnit.SECOND, new IntRange(start, end), true, step)

        expect:
        workout.getStepSizedIntRange() == expectedRange

        where:
        start | end | step || expectedRange
        10    | 100 | 10   || new IntRange(1, 10)
        25    | 100 | 5    || new IntRange(5, 20)
        1     | 50  | 1    || new IntRange(1, 50)
    }

    def "improper configuration of bodyweight workout parameters throws exception"() {
        when:
        new BodyweightWorkout('', RepUnit.SELF_NAMED, GroovyMock(IntRange), true, 1)

        then:
        thrown(InvalidParameterException)
    }

}
