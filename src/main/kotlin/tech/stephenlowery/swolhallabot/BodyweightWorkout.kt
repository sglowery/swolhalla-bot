package tech.stephenlowery.swolhallabot

import java.security.InvalidParameterException

data class BodyweightWorkout(
    val name: String,
    private val unit: RepUnit,
    private val amountRange: IntRange,
    private val nameIsVerb: Boolean = false,
    private val step: Int = 5,
) {

    init {
        if (unit == RepUnit.SELF_NAMED && nameIsVerb) {
            throw InvalidParameterException("Workout cannot be self-named and a verb")
        }
    }

    override fun toString(): String = formatIfNameIsVerb(stringFromRepUnitAndAmount(getRepAmount()))

    private fun formatIfNameIsVerb(workoutString: String): String =
        if (nameIsVerb) workoutString else "do $workoutString"


    private fun stringFromRepUnitAndAmount(amount: Int): String = when (unit) {
        RepUnit.SELF_NAMED -> "$amount $name"
        RepUnit.STEP -> "$amount $name ${unit.getConjugated(amount)}"
        RepUnit.SECOND -> "$name for $amount ${unit.getConjugated(amount)}"
    }

    private fun getRepAmount(): Int = getStepSizedIntRange().random() * step

    private fun getStepSizedIntRange(): IntRange = when (step) {
        1 -> amountRange
        else -> (amountRange.first / step)..(amountRange.last / step)
    }
}