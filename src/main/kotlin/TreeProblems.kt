object TreeProblems {

    class BinaryTree(val root: BinaryTreeNode) {
        fun checkIfBst(): Boolean {
            fun BinaryTreeNode.isBst(): Boolean {
                var isBstLeft = true
                var isBstRight = true
                this.leftSubtree?.let { 
                    isBstLeft = this.value.compareTo(it.value) > 0
                }

                this.rightSubtree?.let { 
                    isBstRight = this.value.compareTo(it.value) < 0
                }

                return isBstLeft && 
                       isBstRight && 
                       (this.leftSubtree?.isBst() ?: true) && 
                       (this.rightSubtree?.isBst() ?: true)
            }

            return root.isBst()
        }

        fun lowestCommonAncestor() {
            // TODO: ...
        }

        fun getHeight(): Int {
            fun BinaryTreeNode.getHeight(): Int {
                return Math.max(1 + (this.leftSubtree?.getHeight() ?: 0), 1 + (this.rightSubtree?.getHeight() ?: 0))
            }

            return root.getHeight()
        }

        fun inorder() {
            fun BinaryTreeNode.inorder() {
                this.leftSubtree?.inorder()
                print(this.value)
                this.rightSubtree?.inorder()
            }

            return root.inorder()
        }

        fun preorder() {
            fun BinaryTreeNode.preorder() {
                print(this.value)
                this.leftSubtree?.preorder()
                this.rightSubtree?.preorder()
            }

            return root.preorder()
        }

        fun postorder() {
            fun BinaryTreeNode.postorder() {
                this.leftSubtree?.postorder()
                this.rightSubtree?.postorder()
                print(this.value)
            }

            return root.postorder()
        }

        fun prettyPrint() {
            val height = getHeight()
            root.prettyPrintNodeRec(height)
        }

        private fun BinaryTreeNode.prettyPrintNodeRec(height: Int) {
            fun BinaryTreeNode.printNodesAtLevel(level: Int) {
                if (level == 1) {
                    print(this.value + " ")
                } else if (level > 1) {
                    this.leftSubtree?.printNodesAtLevel(level - 1)
                    this.rightSubtree?.printNodesAtLevel(level - 1)
                }
            }

            for (level in 1..height) {
                this.printNodesAtLevel(level)
                print("\n")
            }
        }
    }

    data class BinaryTreeNode(val value: String, val leftSubtree: BinaryTreeNode? = null, val rightSubtree: BinaryTreeNode? = null)

    fun testBinaryTreeProblems() {
        print("Problem Set 3 - Binary Tree Problems\n")
        print("Input tree\n")
        print("    a\n")
        print("   b  e\n")
        print("  c d   f\n")
        print("       g  h\n")
        print("**************************************************************\n")
        print("Print tree pretty:\n")
        val sampleTree = BinaryTree(BinaryTreeNode("a",
            BinaryTreeNode("b", BinaryTreeNode("c"), BinaryTreeNode("d")),
            BinaryTreeNode("e", null, BinaryTreeNode("f", BinaryTreeNode("g"), BinaryTreeNode("h")))))
        print("Height of the sample tree: " + sampleTree.getHeight() + "\n")

        sampleTree.prettyPrint()
        val sampleTreeBst = BinaryTree(BinaryTreeNode("e",
            BinaryTreeNode("c", BinaryTreeNode("b"), BinaryTreeNode("d")),
            BinaryTreeNode("f", null, BinaryTreeNode("h", BinaryTreeNode("g"), BinaryTreeNode("i")))))
        print("\nPretty print a BST:\n")
        sampleTreeBst.prettyPrint()
        print("Is sample tree a BST: " + sampleTree.checkIfBst() + ", second sample tree is BST: " + sampleTreeBst.checkIfBst())
        print("\nTree traversals:\n")
        print("Inorder BST: ")
        sampleTreeBst.inorder()
        print(", preorder BST: ")
        sampleTreeBst.preorder()
        print(", postorder BST: ")
        sampleTreeBst.postorder()
    }
}