package day1

import readInput

private fun main() {
    fun part1(input: List<String>): Int {
        val res = mutableListOf<Int>()
        var i = 0
        while (i < input.size) {
            var totalCalories = 0
            while (i < input.size && input[i].isNotBlank()) {
                totalCalories += input[i].toInt()
                i++
            }
            res.add(totalCalories)
            i++
        }
        return res.max()
    }

    fun part2(input: List<String>): Int {
        val res = mutableListOf<Int>()
        var i = 0
        while (i < input.size) {
            var totalCalories = 0
            while (i < input.size && input[i].isNotBlank()) {
                totalCalories += input[i].toInt()
                i++
            }
            res.add(totalCalories)
            i++
        }
        return res.sortedDescending().take(3).sum()
    }

    val testInput = readInput("day1/Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("day1/Day01")
    println(part1(input))
    println(part2(input))
}
