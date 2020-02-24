object LinkedListProblems {
    class LinkedList(val head: LinkedListNode) {
        fun insertToEnd(value: String) {
            var current = head
            while (current.next != null) {
                current = current.next!!
            }

            current.next = LinkedListNode(value)
        }

        fun removeBasedOnValue(value: String) {
            var current = head
            var prev: LinkedListNode? = null
            while (current.next != null && current.value != value) {
                prev = current
                current = current.next!!
            }

            if (current.value == value) {
                prev?.next = current.next
                current.next = null
            }
        }

        fun checkIfContainsCycleSuboptimal(): Boolean {
            var current = head
            val visitedNodesHashSet = HashSet<String>()
            while (current.next != null) {
                if (!visitedNodesHashSet.contains(current.value)) {
                    visitedNodesHashSet.add(current.value)
                } else {
                    return true
                }

                current = current.next!!
            }

            return visitedNodesHashSet.contains(current.value)
        }

        fun checkIfContainsCycle(): Boolean {
            return checkIfContainsCycleSuboptimal() // TODO: tortoise and hare
            // unefficient method - go through list and add previously visited nodes to hashset, check if next is in hashset for a cycle
        }
    }

    data class LinkedListNode(val value: String, var next: LinkedListNode? = null)

    private fun LinkedListNode.printNode(ignoreLast: Boolean = false) {
        if (next != null || ignoreLast)
            print(value + " -> ")
        else
            print(value)
    }

    fun LinkedList.print() {
        var current = head
        while (current.next != null) {
            current.printNode()
            current = current.next!!
        }

        current.printNode()
    }

    fun LinkedList.printReversed() {
        // recurse until we reached end of list
        // exit recursion
        // then print as we bubble up from the recursion stack (we will start with last this way)
        fun reverseRecursively(node: LinkedListNode?) {
            if (node == null)
                return
            
            reverseRecursively(node.next)
            node.printNode(ignoreLast = true)
        }

        reverseRecursively(this.head)
    }

    fun testLinkedListProblems() {
        print("Problem Set 2 - Linked List Problems\n")
        print("Input list: 1 -> 2 -> 3 -> 4 -> 5\n")
        print("**************************************************************\n")
        print("Print list: ")
        val inputLinkedList = LinkedList(LinkedListNode("1", LinkedListNode("2", LinkedListNode("3", LinkedListNode("4", LinkedListNode("5"))))))
        inputLinkedList.print()
        print("\n**************************************************************\n")
        print("Print reversed list: ")
        inputLinkedList.printReversed()

        print("\n**************************************************************\n")
        print("Remove 3 from list: ")
        inputLinkedList.removeBasedOnValue("3")
        inputLinkedList.print()

        print("\n**************************************************************\n")
        print("Insert 10 at the end of the list: ")
        inputLinkedList.insertToEnd("10")
        inputLinkedList.print()

        print("\n**************************************************************\n")
        print("Check if input list contains cycle: " + inputLinkedList.checkIfContainsCycle() + "\n")
        print("Input list 2: 1 -> 2 -> 3 -> 4 -> 3\n")
        val inputLinkedList2 = LinkedList(LinkedListNode("1", LinkedListNode("2", LinkedListNode("3", LinkedListNode("4", LinkedListNode("3"))))))
        print("Check if input list 2 contains cycle: " + inputLinkedList2.checkIfContainsCycle())
    }
}
