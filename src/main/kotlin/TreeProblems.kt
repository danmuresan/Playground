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

        fun doesNodeExistInTree(nodeToFind: String): Boolean {
            fun findNode(currentNode: BinaryTreeNode?, nodeToFind: String): Boolean {
                if (currentNode == null) {
                    return false
                }

                if (currentNode.value == nodeToFind) {
                    return true
                }

                val isNodeInLeftSubtree = findNode(currentNode.leftSubtree, nodeToFind)
                val isNodeInRightSubtree = findNode(currentNode.rightSubtree, nodeToFind)
                return isNodeInLeftSubtree || isNodeInRightSubtree
            }

            return findNode(root, nodeToFind)
        }

        fun findPathToNode(nodeToFind: String): List<BinaryTreeNode> {
            fun findNodeRec(currentNode: BinaryTreeNode?, nodeToFind: String, path: MutableList<BinaryTreeNode>): Boolean {
                // base case - we reached the end of the tree and found nothing
                if (currentNode == null) {
                    return false;
                }

                // base case - we found the node
                if (currentNode.value == nodeToFind) {
                    path.add(currentNode)
                    return true
                }

                // go search leftSubtree and add the nodes
                val isFoundInLeftSubTree = findNodeRec(currentNode.leftSubtree, nodeToFind, path)
                if (isFoundInLeftSubTree) {
                    path.add(currentNode)
                    return true
                }

                // go search rightSubtree and add the nodes
                val isFoundInRightSubTree = findNodeRec(currentNode.rightSubtree, nodeToFind, path)
                if (isFoundInRightSubTree) {
                    path.add(currentNode)
                    return true
                }

                return false
            }

            val path = mutableListOf<BinaryTreeNode>()
            findNodeRec(root, nodeToFind, path)
            return path
        }

        // double traversal (there's an optimize, single traversal solution)
        fun lowestCommonAncestor(node1: String, node2: String): String {
            val pathToNode1 = findPathToNode(node1)
            val pathToNode2 = findPathToNode(node2)

            var lca = "Not Found"
            val diff = pathToNode1.size - pathToNode2.size
            var idx = 0
            while (idx < Math.max(pathToNode1.size, pathToNode2.size)) {
                if (diff > 0) {
                    if (pathToNode1[idx + diff] == pathToNode2[idx]) {
                        return pathToNode2[idx].value
                    }
                } else {
                    if (pathToNode2[idx + diff] == pathToNode1[idx]) {
                        return pathToNode2[idx].value
                    }
                }
                
                idx++
            }

            return lca
        }

        fun findMinPathBetweenNodes(node1: String, node2: String): List<BinaryTreeNode> {
            val pathToNode1 = findPathToNode(node1)
            val pathToNode2 = findPathToNode(node2)

            //gfea
            // dba
            val diff = pathToNode1.size - pathToNode2.size
            var idx = 0
            val finalPath = mutableListOf<BinaryTreeNode>()

            while (idx < Math.max(pathToNode1.size, pathToNode2.size)) {
                if (diff > 0) {
                    if (pathToNode1[idx + diff] == pathToNode2[idx]) {
                        break
                    }
                } else {
                    if (pathToNode2[idx + diff] == pathToNode1[idx]) {
                        break
                    }
                }
                
                idx++
            }

            for (i in 0..idx+diff) {
                if (diff > 0)
                    finalPath.add(pathToNode1[i])
                else
                    finalPath.add(pathToNode2[i])
            }

            for (i in idx-1 downTo 0) {
                print("GOT HEREs")
                if (diff > 0)
                    finalPath.add(pathToNode2[i])
                else
                    finalPath.add(pathToNode1[i])
            }

            return finalPath
        }

        // find every path from root to leaf
        fun rootToLeafForAllPaths():  HashSet<List<BinaryTreeNode>> {
            fun rootToLeafsRec(currentNode: BinaryTreeNode?, setOfPaths: HashSet<List<BinaryTreeNode>>, currentPath: MutableList<BinaryTreeNode>) {
                // base case - end of tree (stop)
                if (currentNode == null) {
                    return
                }

                // add current node to current path
                currentPath.add(currentNode)

                // base case - leaf node, mark root as completed and remove leaf so we search remaining subtrees
                if (currentNode.leftSubtree == null && currentNode.rightSubtree == null) {
                    setOfPaths.add(currentPath.toList())
                    if (currentPath.size > 0) {
                        currentPath.removeAt(currentPath.lastIndex)
                    }

                    return 
                }

                // search subtrees recursively
                rootToLeafsRec(currentNode.leftSubtree, setOfPaths, currentPath)
                rootToLeafsRec(currentNode.rightSubtree, setOfPaths, currentPath)

                // we searched both branches already so we must have found the leaf, remove last node
                if (currentPath.size > 0) {
                    currentPath.removeAt(currentPath.lastIndex)
                }
            }

            val setOfPaths: HashSet<List<BinaryTreeNode>> = hashSetOf()
            rootToLeafsRec(root, setOfPaths, mutableListOf())
            return setOfPaths
        }

        // deepest leaf
        fun findMaxDepthOfTree(): Int {
            fun maxDepthRec(currentNode: BinaryTreeNode?): Int {
                if (currentNode == null) {
                    return 0
                }

                return Math.max(1 + maxDepthRec(currentNode.leftSubtree), 1 + maxDepthRec(currentNode.rightSubtree))
            }

            return maxDepthRec(root)
        }

        fun maxPathSumBetweenTwoLeaves(node1: String, node2: String): Int {
            return -1   // TODO: ...
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

        print("Input tree BST sample\n")
        print("    e\n")
        print("   c  f\n")
        print("  b d   h\n")
        print("       g  i\n")
        print("            m\n")
        val sampleTreeBst = BinaryTree(BinaryTreeNode("e",
            BinaryTreeNode("c", BinaryTreeNode("b"), BinaryTreeNode("d")),
            BinaryTreeNode("f", null, BinaryTreeNode("h", BinaryTreeNode("g"), BinaryTreeNode("i", null, BinaryTreeNode("m"))))))
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

        print("\nFind if node g exists in sample tree 1: " + sampleTree.doesNodeExistInTree("g"))
        print("\nFind if node x exists in sample tree 1: " + sampleTree.doesNodeExistInTree("x"))

        val path = sampleTree.findPathToNode("g")
        print("\nFind path to node g in sample tree 1: ")
        path.print()

        val lca = sampleTree.lowestCommonAncestor("g", "d")
        print("\nFind LCA between node g and node d in sample tree 1: " + lca)

        val allPaths = sampleTree.rootToLeafForAllPaths()
        print("\nFind all paths from root to leaf in sample tree 1:\n")
        allPaths.print()

        val depth = sampleTree.findMaxDepthOfTree()
        print("\nFind max depth in sample tree 1: " + depth)
        val depthBst = sampleTreeBst.findMaxDepthOfTree()
        print("\nFind max depth in sample tree 2 (BST): " + depthBst)

        val minPath = sampleTree.findMinPathBetweenNodes("g", "d")
        print("\nFind min path from node g to node d in sample tree 1: ")
        minPath.print()
    }

    fun List<TreeProblems.BinaryTreeNode>.print() {
        this.forEach {
            print(it.value + ", ")
        }
    }

    fun HashSet<List<TreeProblems.BinaryTreeNode>>.print() {
        var idx = 1
        this.forEach {
            print("path no. $idx: ")
            it.print()
            print("\n")
            idx++
        }
    }
}