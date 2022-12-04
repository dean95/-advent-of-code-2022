package day4

import readInput

private fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        input.forEach { line ->
            val (range1, range2) = line.split(",").map {
                val (s, e) = it.split("-")
                s.toInt() to e.toInt()
            }

            val (range1Start, range1End) = range1
            val (range2Start, range2End) = range2

            if (range1End - range1Start < range2End - range2Start) {
                if (range1Start >= range2Start && range1End <= range2End) result++
            } else {
                if (range2Start >= range1Start && range2End <= range1End) result++
            }
        }

        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        input.forEach { line ->
            val (range1, range2) = line.split(",").map {
                val (s, e) = it.split("-")
                s.toInt() to e.toInt()
            }

            val (range1Start, range1End) = range1
            val (range2Start, range2End) = range2

            if (range1Start < range2Start) {
                if (range1End >= range2Start) result++
            } else {
                if (range2End >= range1Start) result++
            }
        }

        return result
    }

    val testInput = readInput("day4/Day04_test")
    println(part2(testInput))
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("day4/Day04")
    println(part1(input))
    println(part2(input))
}