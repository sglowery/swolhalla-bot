package tech.stephenlowery

data class BodyweightWorkout(
    private val name: String,
    private val unit: RepUnit,
    private val amountRange: IntRange,
    private val nameIsVerb: Boolean = false,
) {

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
        else -> "$name for $amount ${unit.getConjugated(amount)}"
    }
}