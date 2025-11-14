class Solution {
    // Target value we want to achieve
    private val TARGET = 24.0
    // Small tolerance for floating-point comparisons
    private val EPSILON = 1e-6

    fun judgePoint24(cards: IntArray): Boolean {
        // Convert integer cards to a list of doubles to handle real division
        val doubleCards = cards.map { it.toDouble() }
        return solve(doubleCards)
    }

    private fun solve(list: List<Double>): Boolean {
        // Base case: If only one number is left, check if it's our target
        if (list.size == 1) {
            return abs(list[0] - TARGET) < EPSILON
        }

        // Recursive step: Pick any two numbers from the list
        for (i in list.indices) {
            for (j in i + 1 until list.size) {
                val a = list[i]
                val b = list[j]

                // Create a new list containing the remaining numbers
                val nextList = mutableListOf<Double>()
                for (k in list.indices) {
                    if (k != i && k != j) {
                        nextList.add(list[k])
                    }
                }

                // Apply all possible operations to the pair (a, b)
                // Note: a+b and a*b are commutative, so we only need one case.
                // a-b, b-a, a/b, b/a are not, so we need to check both orders.
                val possibleResults = mutableListOf<Double>()
                possibleResults.add(a + b)
                possibleResults.add(a * b)
                possibleResults.add(a - b)
                possibleResults.add(b - a)
                if (abs(b) > EPSILON) possibleResults.add(a / b)
                if (abs(a) > EPSILON) possibleResults.add(b / a)

                // For each result, recurse with the new smaller list
                for (res in possibleResults) {
                    val newList = nextList + res // Create a new list with the result
                    if (solve(newList)) {
                        // If a solution is found, short-circuit and return true
                        return true
                    }
                }
            }
        }

        // If no combination yields the target, return false
        return false
    }
}