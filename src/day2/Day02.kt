package day2

import day2.Choice.*
import readInput

private fun main() {
    val myPlayCodes = mutableMapOf(
        "X" to ROCK,
        "Y" to PAPER,
        "Z" to SCISSORS
    )

    val opponentPlayCodes = mutableMapOf(
        "A" to ROCK,
        "B" to PAPER,
        "C" to SCISSORS
    )

    val choiceToDefeat = mutableMapOf(
        ROCK to SCISSORS,
        SCISSORS to PAPER,
        PAPER to ROCK
    )

    fun part1(input: List<String>): Int {
        var totalScore = 0
        input.forEach {
            val (opponentPlayCode, myPlayCode) = it.split(" ")
            val opponentPlay = opponentPlayCodes.getValue(opponentPlayCode)
            val myPlay = myPlayCodes.getValue(myPlayCode)
            totalScore += myPlay.score

            if (myPlay == opponentPlay) {
                totalScore += 3
            } else if (choiceToDefeat.getValue(myPlay) == opponentPlay) {
                totalScore += 6
            }
        }
        return totalScore
    }

    fun part2(input: List<String>): Int {
        val defeatToChoice = mutableMapOf(
            SCISSORS to ROCK,
            PAPER to SCISSORS,
            ROCK to PAPER
        )
        var totalScore = 0
        input.forEach {
            val (opponentPlayCode, myPlayCode) = it.split(" ")
            val opponentPlay = opponentPlayCodes.getValue(opponentPlayCode)

            val myPlay = when (myPlayCode) {
                "X" -> choiceToDefeat.getValue(opponentPlay)
                "Y" -> {
                    totalScore += 3
                    opponentPlay
                }

                "Z" -> {
                    totalScore += 6
                    defeatToChoice.getValue(opponentPlay)
                }

                else -> throw IllegalStateException()
            }
            totalScore += myPlay.score
        }
        return totalScore
    }

    val testInput = readInput("day2/Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("day2/Day02")
    println(part1(input))
    println(part2(input))
}

enum class Choice(val score: Int) {
    ROCK(1), PAPER(2), SCISSORS(3)
}