package tech.stephenlowery.swolhallabot

enum class RepUnit(private val singular: String, private val plural: String) {
    SECOND("second", "seconds"),
    STEP("step", "steps"),
    SELF_NAMED("", "");

    fun getConjugated(amount: Int) = if (amount == 1) singular else plural
}