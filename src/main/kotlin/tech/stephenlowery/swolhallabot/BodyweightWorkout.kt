package tech.stephenlowery.swolhallabot

import java.security.InvalidParameterException

data class BodyweightWorkout(
    private val name: String,
    private val unit: RepUnit,
    private val amountRange: IntRange,
    private val nameIsVerb: Boolean = false,
) {

    init {
        if (unit == RepUnit.SELF_NAMED && nameIsVerb) {
            throw InvalidParameterException("Workout cannot be self-named and a verb")
        }
    }

    override fun toString(): String {
        val amount = amountRange.random()
        return formatIfNameIsVerb(stringFromRepUnitAndAmount(amount))
    }

    private fun formatIfNameIsVerb(workoutString: String): String {
        return if (nameIsVerb) workoutString else "do $workoutString"
    }

    private fun stringFromRepUnitAndAmount(amount: Int): String = when (unit) {
        RepUnit.SELF_NAMED -> "$amount $name"
        RepUnit.STEP -> "$amount $name ${unit.getConjugated(amount)}"
        RepUnit.SECOND -> "$name for $amount ${unit.getConjugated(amount)}"
    }
}