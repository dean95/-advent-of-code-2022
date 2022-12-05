package day5

import readInput

private fun main() {

    fun part1(input: List<String>): String {
        val crates = input.takeWhile { it.contains(']') }

        val cratesToStack = mutableListOf<Pair<Char, Int>>()
        crates.forEach {
            for (i in 1..it.lastIndex step 4) {
                if (it[i].isLetter()) cratesToStack.add(it[i] to (i / 4))
            }
        }

        val numberOfStacks = cratesToStack.maxOf(Pair<Char, Int>::second).inc()

        val stacks = List(numberOfStacks) { ArrayDeque<Char>() }
        cratesToStack.forEach { (crate, stackIndex) ->
            stacks[stackIndex].addFirst(crate)
        }

        val commands = input
            .dropWhile { it.firstOrNull()?.isLetter()?.not() ?: true }
            .map { command ->
                val (numOfCrates, originStackIndex, destinationStackIndex) = command
                    .split(' ')
                    .filter { it.toIntOrNull() != null }
                    .map(String::toInt)
                Command(numOfCrates, originStackIndex.dec(), destinationStackIndex.dec())
            }

        commands.forEach { command ->
            val (numOfCrates, originStackIndex, destinationStackIndex) = command
            repeat(numOfCrates) {
                val crate = stacks[originStackIndex].removeLast()
                stacks[destinationStackIndex].add(crate)
            }
        }

        val topCrates = StringBuilder()
        stacks.forEach {
            topCrates.append(it.last())
        }
        return topCrates.toString()
    }

    fun part2(input: List<String>): String {
        val crates = input.takeWhile { it.contains(']') }

        val cratesToStack = mutableListOf<Pair<Char, Int>>()
        crates.forEach {
            for (i in 1..it.lastIndex step 4) {
                if (it[i].isLetter()) cratesToStack.add(it[i] to (i / 4))
            }
        }

        val numberOfStacks = cratesToStack.maxOf(Pair<Char, Int>::second).inc()

        val stacks = List(numberOfStacks) { ArrayDeque<Char>() }
        cratesToStack.forEach { (crate, stackIndex) ->
            stacks[stackIndex].addFirst(crate)
        }

        val commands = input
            .dropWhile { it.firstOrNull()?.isLetter()?.not() ?: true }
            .map { command ->
                val (numOfCrates, originStackIndex, destinationStackIndex) = command
                    .split(' ')
                    .filter { it.toIntOrNull() != null }
                    .map(String::toInt)
                Command(numOfCrates, originStackIndex.dec(), destinationStackIndex.dec())
            }

        commands.forEach { command ->
            val (numOfCrates, originStackIndex, destinationStackIndex) = command
            val stacksToRemove = stacks[originStackIndex].takeLast(numOfCrates)
            repeat(numOfCrates) { stacks[originStackIndex].removeLast() }
            stacks[destinationStackIndex].addAll(stacksToRemove)
        }

        val topCrates = StringBuilder()
        stacks.forEach {
            topCrates.append(it.last())
        }
        return topCrates.toString()
    }

    val testInput = readInput("day5/Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("day5/Day05")
    println(part1(input))
    println(part2(input))
}

private data class Command(
    val numOfCrates: Int,
    val originStackIndex: Int,
    val destinationStackIndex: Int
)