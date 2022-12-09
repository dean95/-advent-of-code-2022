package day8

import readInput
import kotlin.math.max

private fun main() {
    fun parseInput(input: List<String>): List<List<Int>> {
        val matrix = mutableListOf<List<Int>>()
        input.forEach { line ->
            val row = mutableListOf<Int>()
            line.forEach { char ->
                row.add(char.toString().toInt())
            }
            matrix.add(row)
        }
        return matrix
    }

    fun part1(input: List<String>): Int {
        val matrix = parseInput(input)

        val leftMaxMatrix = matrix.map { row ->
            var currentRowMax = Int.MIN_VALUE
            row.mapIndexed { columnIndex, number ->
                currentRowMax = if (columnIndex == 0) number else max(currentRowMax, number)
                currentRowMax
            }
        }

        val rightMaxMatrix = matrix.map { row ->
            var currentRowMax = Int.MIN_VALUE
            row.reversed().mapIndexed { columnIndex, number ->
                currentRowMax = if (columnIndex == 0) number else max(currentRowMax, number)
                currentRowMax
            }.reversed()
        }

        val topMax = mutableListOf<Int>()
        val topMaxMatrix = matrix.mapIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, number ->
                if (rowIndex == 0) {
                    topMax.add(number)
                } else {
                    topMax[columnIndex] = max(topMax[columnIndex], number)
                }
            }
            topMax.toList()
        }

        val bottomMax = mutableListOf<Int>()
        val bottomMaxMatrix = matrix
            .reversed()
            .mapIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, number ->
                    if (rowIndex == 0) {
                        bottomMax.add(number)
                    } else {
                        bottomMax[columnIndex] = max(bottomMax[columnIndex], number)
                    }
                }
                bottomMax.toList()
            }.reversed()

        var visibleTreesCount = 0
        matrix.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, number ->
                if (rowIndex == 0 || rowIndex == matrix.lastIndex || columnIndex == 0 || columnIndex == row.lastIndex) {
                    visibleTreesCount++
                    return@forEachIndexed
                }
                if (number > leftMaxMatrix[rowIndex][columnIndex - 1]) visibleTreesCount++
                else if (number > rightMaxMatrix[rowIndex][columnIndex + 1]) visibleTreesCount++
                else if (number > topMaxMatrix[rowIndex - 1][columnIndex]) visibleTreesCount++
                else if (number > bottomMaxMatrix[rowIndex + 1][columnIndex]) visibleTreesCount++
            }
        }

        return visibleTreesCount
    }

    fun part2(input: List<String>): Int {
        val matrix = parseInput(input)
        var maxScenicScore = 0
        matrix.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, number ->
                if (rowIndex == 0 || rowIndex == matrix.lastIndex || columnIndex == 0 || columnIndex == row.lastIndex) {
                    return@forEachIndexed
                }

                var leftDistance = 0
                var rightDistance = 0
                var topDistance = 0
                var bottomDistance = 0

                var currentPosition = columnIndex - 1
                while (currentPosition >= 0 && number > row[currentPosition]) {
                    leftDistance++
                    currentPosition--
                }
                if (row.getOrNull(currentPosition) != null) leftDistance++

                currentPosition = columnIndex + 1
                while (currentPosition < row.size && number > row[currentPosition]) {
                    rightDistance++
                    currentPosition++
                }
                if (row.getOrNull(currentPosition) != null) rightDistance++

                currentPosition = rowIndex - 1
                while (currentPosition >= 0 && number > matrix[currentPosition][columnIndex]) {
                    topDistance++
                    currentPosition--
                }
                if (matrix.getOrNull(currentPosition) != null) topDistance++

                currentPosition = rowIndex + 1
                while (currentPosition < matrix.size && number > matrix[currentPosition][columnIndex]) {
                    bottomDistance++
                    currentPosition++
                }
                if (matrix.getOrNull(currentPosition) != null) bottomDistance++

                maxScenicScore = max(maxScenicScore, leftDistance * rightDistance * topDistance * bottomDistance)
            }
        }

        return maxScenicScore
    }

    val testInput = readInput("day8/Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("day8/Day08")
    println(part1(input))
    println(part2(input))
}