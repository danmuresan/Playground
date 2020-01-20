import kotlin.*

fun main(args: Array<String>) {
    StringProblems.testStringProblems()
    print("\n\n")
    readLine()
}

object StringProblems {
    // given string input String A, find starting index of B in input string
    // param: str - string to search if substring of input
    // abalaprotocoala => str: "ala" => return 2
    fun String.isSubstring(str: String): Int {
        for (chIndex in this.indices) {
            var offsetIndex = 0
            while (chIndex + offsetIndex < this.length &&
                   offsetIndex < str.length &&
                   this[chIndex + offsetIndex] == str[offsetIndex]) {
                offsetIndex++
            }

            if (offsetIndex == str.length) {
                return chIndex
            } else if (offsetIndex != 0) {
                offsetIndex = 0
            }
        }

        return -1
    }

    // Implement an algorithm to determine if a string has all unique characters. What
    // if you cannot use additional data structures?
    fun String.hasUniqueCharacters(): Boolean {
        // with additional data structures, use hashmap
        // without, additional d.s., bubble sort style comparisons
        for (i in this.indices) {
            for (j in i+1..this.length - 1) {
                if (this[i] == this[j]) {
                    return false
                }
            }
        }

        return true
    }

    // reverse a string (in place, recursively & iteratively)
    // e.g. arranca => acnarra
    fun String.reverseRec(): String {
        if (this.length == 1) {
            return this
        }

        return this.last() + (this.substring(0, this.length - 1)).reverseRec()
    }

    // Write a method to replace all spaces in a string with'%20'. You may assume that
    // the string has sufficient space at the end of the string to hold the additional
    // characters, and that you are given the "true" length of the string. (Note: if implementing
    // in Java, please use a character array so that you can perform this operation
    // in place.)
    fun String.replace(charToReplace: Char, subStr: String): String {
        return ""
    }

    // Implement a method to perform basic string compression using the counts
    // of repeated characters. For example, the string aabcccccaaa would become
    // a2blc5a3. If the "compressed" string would not become smaller than the original
    // string, your method should return the original string.
    fun String.compress(): String {
        return ""
    }

    // Given an input string of type {[()]}, check if string is balanced
    // (i.e. matching braces problem)
    fun String.checkIfBalanced(): Boolean {
        return false
    }

    fun testStringProblems() {
        print("Problem Set 1 - String Manipulation\n")
        print("Problem 1 - SubString\n")
        print("**************************************************************\n")
        print("Substring index: abalaprotocoala => str: 'ala' => should return 2")
        print(", returns " + "abalaprotocoala".isSubstring("ala") + "\n")

        print("Problem 2 - Has Unique Characters\n")
        print("**************************************************************\n")
        print("String: abalaprotocoala => should return false")
        print(", returns " + "abalaprotocoala".hasUniqueCharacters() + "\n")
        print("String: abcdefghijklmnopqrs => should return true")
        print(", returns " + "abcdefghijklmnopqrs".hasUniqueCharacters() + "\n")

        print("Problem 3 - Reverse String\n")
        print("**************************************************************\n")
        print("String: arranca => should return acnarra")
        print(", returns " + "arranca".reverseRec() + "\n")
        print("String: alabalaportcala => should return alactropalabala")
        print(", returns " + "alabalaportcala".reverseRec() + "\n")

    }
}

object ArrayProblems {

}

object LinkedListProblems {

}

object TreeProblems {

}

object GraphProblems {
    // bfs, dfs
}
