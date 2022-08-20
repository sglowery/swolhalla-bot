package tech.stephenlowery.swolhallabot

object WorkoutHandler {

    private val previousWorkoutMap = mutableMapOf<Long, BodyweightWorkout>()

    private val bodyweightWorkouts = listOf(
        BodyweightWorkout("jumping jacks", RepUnit.SELF_NAMED, 20..50),
        BodyweightWorkout("sit ups", RepUnit.SELF_NAMED, 20..50),
        BodyweightWorkout("crunches", RepUnit.SELF_NAMED, 20..50),
        BodyweightWorkout("push ups", RepUnit.SELF_NAMED, 20..50),
        BodyweightWorkout("mountain climbers", RepUnit.SELF_NAMED, 20..50),
        BodyweightWorkout("Russian twists", RepUnit.SELF_NAMED, 20..50),
        BodyweightWorkout("plank", RepUnit.SECOND, 20..50, true),
        BodyweightWorkout("leg lifts", RepUnit.SECOND, 20..50),
        BodyweightWorkout("squats", RepUnit.SELF_NAMED, 20..50),
        BodyweightWorkout("lunges", RepUnit.SELF_NAMED, 20..50),
        BodyweightWorkout("door frame rows", RepUnit.SELF_NAMED, 20..50),
        BodyweightWorkout("wall sit", RepUnit.SECOND, 20..50, true),
        BodyweightWorkout("burpees", RepUnit.SELF_NAMED, 20..50),
        BodyweightWorkout("bear crawl", RepUnit.STEP, 20..50),
        BodyweightWorkout("lizard crawl", RepUnit.STEP, 20..50),
    )

    fun getWorkoutForUser(userId: Long): BodyweightWorkout {
        return getAvailableWorkouts(userId).random().also { previousWorkoutMap[userId] = it }
    }

    private fun getAvailableWorkouts(userId: Long): Collection<BodyweightWorkout> {
        return previousWorkoutMap[userId]?.let { previousWorkout ->
            bodyweightWorkouts.filterNot { it.name == previousWorkout.name }
        } ?: bodyweightWorkouts
    }
}

