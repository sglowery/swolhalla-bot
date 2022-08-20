package tech.stephenlowery.swolhallabot

import spock.lang.Shared
import spock.lang.Specification

class WorkoutHandlerTest extends Specification {

    @Shared
    def userId = 1L

    def "getWorkoutForUser picks a random workout and maps userid to that workout"() {
        when:
        def workout = instance().getWorkoutForUser(userId)

        then:
        instance().previousWorkoutMap.get(userId) == workout
    }

    def "getAvailableWorkouts properly filters workouts"() {
        given:
        instance().previousWorkoutMap.clear()
        instance().previousWorkoutMap.putAll(workoutMap)

        expect:
        def availableNames = instance().getAvailableWorkouts(userId).collect { it.name }
        def expectedNames = expectedListOfWorkouts.collect { it.name }
        assert availableNames == expectedNames

        where:
        workoutMap                                        || expectedListOfWorkouts
        new HashMap<Long, BodyweightWorkout>()            || instance().bodyweightWorkouts
        [(userId): instance().bodyweightWorkouts.first()] || instance().bodyweightWorkouts.drop(1)
        [(userId): instance().bodyweightWorkouts.last()]  || instance().bodyweightWorkouts.dropRight(1)
    }

    def instance() {
        return WorkoutHandler.INSTANCE
    }

}
