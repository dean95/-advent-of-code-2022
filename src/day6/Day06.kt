package day6

import readInput

private fun main() {
    fun part1(input: List<String>): Int {
        val dataStream = input.first()
        val charCountMap = mutableMapOf<Char, Int>()

        var l = 0
        var r = 0
        repeat(4) {
            charCountMap[dataStream[it]] = charCountMap.getOrDefault(dataStream[it], 0).inc()
            r++
        }

        while (r < dataStream.length) {
            if (charCountMap.all { (_, count) -> count == 1 }) return r

            val leftChar = dataStream[l]
            if (!charCountMap.remove(leftChar, 1)) {
                charCountMap[leftChar] = charCountMap.getValue(leftChar).dec()
            }
            l++
            charCountMap[dataStream[r]] = charCountMap.getOrDefault(dataStream[r], 0).inc()
            r++
        }

        throw IllegalStateException()
    }

    fun part2(input: List<String>): Int {
        val dataStream = input.first()
        val charCountMap = mutableMapOf<Char, Int>()

        var l = 0
        var r = 0
        repeat(14) {
            charCountMap[dataStream[it]] = charCountMap.getOrDefault(dataStream[it], 0).inc()
            r++
        }

        while (r < dataStream.length) {
            if (charCountMap.all { (_, count) -> count == 1 }) return r

            val leftChar = dataStream[l]
            if (!charCountMap.remove(leftChar, 1)) {
                charCountMap[leftChar] = charCountMap.getValue(leftChar).dec()
            }
            l++
            charCountMap[dataStream[r]] = charCountMap.getOrDefault(dataStream[r], 0).inc()
            r++
        }

        throw IllegalStateException()
    }

    val testInput = readInput("day6/Day06_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("day6/Day06")
    println(part1(input))
    println(part2(input))
}