import java.lang.Exception

object LinkedListProblems {
    class LinkedList(private var _head: LinkedListNode) {

        val head: LinkedListNode
            get() {
                return _head
            }

        fun insertToEnd(value: String) {
            var current = head
            while (current.next != null) {
                current = current.next!!
            }

            current.next = LinkedListNode(value)
        }

        fun insertAtIndex(value: String, index: Int) {
            var current = head
            var remainingCount = index - 1
            while (current.next != null && remainingCount > 0) {
                current = current.next!!
                remainingCount--
            }

            if (remainingCount > 0) {
                throw Exception("Index out of bounds")
            }

            val temp = current.next
            current.next = LinkedListNode(value)
            current.next!!.next = temp
        }

        fun insertAtStart(value: String) {
            val newNode = LinkedListNode(value)
            newNode.next = head
            _head = newNode
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

        fun checkIfContainsDuplicateNode(): Boolean {
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

        fun checkIfContainsCycleOptimal(): Boolean {
            // Tortoise & Hare (Floyd alg)
            // increment one pointer by 1 (tortoise), the other by 2 (hare)
            var tortoise = head
            var hare = head as LinkedListNode?

            while (hare != null && hare.next != null) {
                tortoise = tortoise.next!!
                hare = hare.next!!.next
                if (tortoise == hare) {
                    return true
                }
            }

            return false
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
        print("Insert 8 at the index 3 in the list: ")
        inputLinkedList.insertAtIndex("8", 3)
        inputLinkedList.print()

        print("\n**************************************************************\n")
        print("Insert 15 at start: ")
        inputLinkedList.insertAtStart("15")
        inputLinkedList.print()

        print("\n**************************************************************\n")
        print("Check if input list contains duplicate value: " + inputLinkedList.checkIfContainsDuplicateNode() + "\n")
        print("Input list 2: 1 -> 2 -> 3 -> 4 -> 3\n")
        val inputLinkedList2 = LinkedList(LinkedListNode("1", LinkedListNode("2", LinkedListNode("3", LinkedListNode("4", LinkedListNode("3"))))))
        print("Check if input list 2 contains duplicate value: " + inputLinkedList2.checkIfContainsDuplicateNode())

        print("\n**************************************************************\n")
        print("Check if input list contains cycle value: " + inputLinkedList.checkIfContainsCycleOptimal() + "\n")
        print("Input list 2: 1 -> 2 -> 3 -> 4 -> 5 -> back to 3\n")
        val cycleStartNode = LinkedListNode("3")
        cycleStartNode.next = LinkedListNode("4", LinkedListNode("5", cycleStartNode))
        val inputLinkedList3 = LinkedList(LinkedListNode("1", LinkedListNode("2", cycleStartNode)))
        print("Check if input list 2 contains cycle value: " + inputLinkedList3.checkIfContainsCycleOptimal())
    }
}
