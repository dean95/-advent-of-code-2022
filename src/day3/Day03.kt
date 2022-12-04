package day3

import readInput

private fun main() {
    fun part1(input: List<String>): Int {
        var prioritiesSum = 0

        input.forEach { item ->
            val middle = item.length / 2
            val firstRucksack = item.substring(0 until middle).toSet()
            val secondRucksack = item.substring(middle until item.length).toSet()

            firstRucksack.forEach charLoop@{
                if (it in secondRucksack) {
                    prioritiesSum += if (it.isUpperCase()) (it - 'A' + 27) else (it - 'a').inc()
                    return@charLoop
                }
            }
        }

        return prioritiesSum
    }

    fun part2(input: List<String>): Int {
        var prioritiesSum = 0

        for (i in 0..input.lastIndex step 3) {
            val map = mutableMapOf<Char, Int>()
            repeat(3) { groupIndex ->
                val s = input[i + groupIndex]
                s.toSet().forEach {
                    map[it] = map.getOrDefault(it, 0).inc()
                }
            }
            for ((char, count) in map) {
                if (count == 3) {
                    prioritiesSum += if (char.isUpperCase()) (char - 'A' + 27) else (char - 'a').inc()
                    break
                }
            }
        }

        return prioritiesSum
    }

    val testInput = readInput("day3/Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("day3/Day03")
    println(part1(input))
    println(part2(input))
}
