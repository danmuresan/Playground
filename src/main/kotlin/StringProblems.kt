import java.util.Stack

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
    fun String.replace(charToReplace: Char, subStringToReplaceWith: String): String {
        // utility method for finding required length in the char array (to adjust for the padding)
        fun String.findRequiredLength(char: Char, substring: String): Int {
            var countOfCharsToReplace = 0
            this.forEach {
                if (it == char) {
                    countOfCharsToReplace++
                }
            }

            val extraCountNeeded = countOfCharsToReplace * substring.length
            return this.length + (extraCountNeeded - countOfCharsToReplace)
        }

        // prepare padded char array for the input string, accounting in necessary length
        val lengthNeededForReplacing = findRequiredLength(charToReplace, subStringToReplaceWith)
        val inputStrArr = CharArray(lengthNeededForReplacing)
        var currentIndex = 0
        this.forEach {
            inputStrArr[currentIndex] = it
            currentIndex++
        }

        // ex: "Ana are mere multe______" => "Ana%20are%20mere%20multe"
        currentIndex = 0
        val offset = subStringToReplaceWith.length - 1
        // loop through the char array from end to start
        for (currentIndex in inputStrArr.size - 1 downTo 0) {
            // if we found a target char (to be replaced by our substring)
            if (inputStrArr[currentIndex] == charToReplace) {
                // shift array chars starting from after the current target char
                // by an offset equal to the length of the substring we're using as replacement minus one
                for (i in inputStrArr.size - offset - 1 downTo currentIndex + 1) {
                    inputStrArr[i + offset] = inputStrArr[i]
                }

                // replace the target char with the target string now that we made space
                for (i in 0..offset) {
                    inputStrArr[currentIndex + i] = subStringToReplaceWith[i]
                }
            }
        }

        // convert char array back to string
        return String(inputStrArr)
    }

    // Implement a method to perform basic string compression using the counts
    // of repeated characters. For example, the string aabcccccaaa would become
    // a2blc5a3. If the "compressed" string would not become smaller than the original
    // string, your method should return the original string.
    fun String.compress(): String {
        var compressedString = StringBuilder()
        var chIndex = 0
        while (chIndex < this.length - 1) {
            var sameCharCount = 1
            val currentCharToBeCompressed = this[chIndex]
            while (chIndex + 1 < this.length &&
                   this[chIndex + 1] == this[chIndex]) {
                sameCharCount++
                chIndex++
            }

            compressedString.append(currentCharToBeCompressed + sameCharCount.toString())
            chIndex++
        }

        if (compressedString.toString().length >= this.length) 
            return this
        else
            return compressedString.toString()
    }

    // Given an input string of type {[()]}, check if string is balanced
    // (i.e. matching braces problem)
    // Input: exp = “[()]{}{[()()]()}”
    // Output: Balanced
    // Input: exp = “[(])”
    // Output: Not Balanced
    fun String.checkIfBalanced(): Boolean {
        val bracketsMap: HashMap<Char, Char> = hashMapOf('(' to ')', '[' to ']', '{' to '}')
        val isOpeningBrace = { ch: Char -> bracketsMap.containsKey(ch) }
        val bracketsStack = Stack<Char>()
        this.forEach { 
            if (isOpeningBrace(it)) {
                bracketsStack.push(it)
            } else {
                val lastOpenBracket = bracketsStack.pop()
                if (bracketsMap[lastOpenBracket] != it) {
                    // unbalanced
                    return false
                }
            }
         }

         return true
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

        print("Problem 5 - Compress String\n")
        print("**************************************************************\n")
        print("String: aabcccccaaa => should return a2blc5a3")
        print(", returns " + "aabcccccaaa".compress() + "\n")
        print("String: aghjijidasdas => should return aghjijidasdas")
        print(", returns " + "aghjijidasdas".compress() + "\n")

        print("Problem 6 - Balanced Brackets\n")
        print("**************************************************************\n")
        print("String: [()]{}{[()()]()} => should return true")
        print(", returns " + "[()]{}{[()()]()}".checkIfBalanced() + "\n")
        print("String: [(]) => should return false")
        print(", returns " + "[(])".checkIfBalanced() + "\n")
        print("String: ([({})]}]) => should return false")
        print(", returns " + "([({})]}])".checkIfBalanced() + "\n")

        print("Problem 7 - Smart char replace with custom string\n")
        print("**************************************************************\n")
        print("String: Ana are mere multe, charToReplace: ' ', subString: '%20' => should return Ana%20are%20mere%20multe")
        print(",\nreturns " + "Ana are mere multe".replace(' ', "%20") + "\n")
        print("String: Aladabalaportocala, charToReplace: 'a', subString: '####' => should return Al####ad####b####l####portoc####l####")
        print(",\nreturns " + "Aladabalaportocala".replace('a', "####") + "\n")
    }
}
