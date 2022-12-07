package day7

import readInput
import kotlin.math.min

private fun main() {
    fun extractDirectories(input: List<String>): Directory {
        val rootDir = Directory("/")
        val dirs = ArrayDeque<Directory>()

        input.forEach { line ->
            if (line.first() == '$') {
                val parts = line.split(" ")
                if (parts[1] == "cd") {
                    val (_, _, dirName) = parts
                    when (dirName) {
                        "/" -> {
                            dirs.clear()
                            dirs.add(rootDir)
                        }

                        ".." -> {
                            dirs.removeLast()
                        }

                        else -> {
                            dirs.add(dirs.last().directories.find { it.name == dirName }!!)
                        }
                    }
                }
            } else {
                val (first, second) = line.split(" ")
                if (first == "dir") {
                    dirs.last().directories.add(Directory(second))
                } else {
                    dirs.last().files.add(File(second, first.toLong()))
                }
            }
        }
        return rootDir
    }

    fun part1(input: List<String>): Long {
        val rootDir = extractDirectories(input)

        var totalSum = 0L
        fun sums(rootDir: Directory): Long {
            if (rootDir.directories.isEmpty()) {
                val sum = rootDir.files.sumOf(File::size)
                if (sum <= 100000) totalSum += sum
                return sum
            }

            var directoriesSum = 0L
            rootDir.directories.forEach {
                val filesSize = sums(it)
                directoriesSum += filesSize
            }

            val filesSize = rootDir.files.sumOf(File::size)
            if (filesSize + directoriesSum <= 100000) totalSum += (filesSize + directoriesSum)

            return directoriesSum + filesSize
        }

        sums(rootDir)
        return totalSum
    }

    fun part2(input: List<String>): Long {
        val rootDir = extractDirectories(input)

        val dirSizes = mutableListOf<Long>()
        fun sums(rootDir: Directory): Long {
            if (rootDir.directories.isEmpty()) {
                val filesSize = rootDir.files.sumOf(File::size)
                dirSizes.add(filesSize)
                return filesSize
            }

            var directoriesSum = 0L
            rootDir.directories.forEach {
                val filesSize = sums(it)
                directoriesSum += filesSize
            }

            val filesSize = rootDir.files.sumOf(File::size)
            dirSizes.add(directoriesSum + filesSize)
            return directoriesSum + filesSize
        }

        val totalSpaceUsed = sums(rootDir)
        val unusedSpaceSize = 70000000 - totalSpaceUsed
        val sizeToBeDeleted = 30000000 - unusedSpaceSize

        var currentMin = Long.MAX_VALUE
        dirSizes.forEach {
            if (it >= sizeToBeDeleted) {
                currentMin = min(currentMin, it)
            }
        }

        return currentMin
    }

    val testInput = readInput("day7/Day07_test")
    check(part1(testInput) == 95437L)
    check(part2(testInput) == 24933642L)

    val input = readInput("day7/Day07")
    println(part1(input))
    println(part2(input))
}

private data class Directory(
    val name: String,
    val directories: MutableList<Directory> = mutableListOf(),
    val files: MutableList<File> = mutableListOf()
)

private data class File(
    val name: String,
    val size: Long
)