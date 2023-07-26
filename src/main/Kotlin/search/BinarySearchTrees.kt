package search


class BinarySearchTrees {
    var root: NodeTree? = null

    fun insert(node: NodeTree) {
        root = insertHelper(root, node)
    }

    private fun insertHelper(root: NodeTree?, node: NodeTree): NodeTree? {
        val data = node.data
        if (root == null) {
            return node
        } else if (data < root.data) {
            root.left = insertHelper(root.left, node)
        } else {
            root.right = insertHelper(root.right, node)
        }
        return root
    }

    fun display() {
        displayHelper(root)
    }

    private fun displayHelper(root: NodeTree?) {
        root?.let { nodeRoot ->
            displayHelper(nodeRoot.left)
            println(root.data)
            displayHelper(nodeRoot.right)
        }
    }

    fun search(data: Int): Boolean {
        return searchHelper(root, data)
    }

    private fun searchHelper(root: NodeTree?, data: Int): Boolean {

        if (root == null) {
            return false
        } else if (root.data == data) {
            return true
        } else if (root.data > data) {
            return searchHelper(root.left, data)
        } else {
            return searchHelper(root.right, data)
        }
    }

    fun remove(data: Int) {
        if (search(data)) {
            removeHelper(root, data)
        } else {
            println("$data Could not be found")
        }
    }

    private fun removeHelper(node: NodeTree?, data: Int): NodeTree? {
        var localNode = node
        if (localNode == null) {
            return null
        } else if (data < localNode.data) {
            localNode.left = removeHelper(localNode.left, data)
        } else if (data > localNode.data) {
            localNode.right = removeHelper(localNode.right, data)
        } else {
            if (localNode.left == null && localNode.right == null) {
                localNode = null
            } else if (localNode.right != null){
                localNode.data = successor(localNode)
                localNode.right = removeHelper(localNode.right, data)
            }else{
                localNode.data = predecessor(localNode)
                localNode.left = removeHelper(localNode.left, data)
            }
        }
        return localNode
    }
    // find least value below the right child of this root
    private fun successor(nodeRoot: NodeTree?): Int {
        var localNode = nodeRoot?.right
        while (localNode?.left != null){
            localNode = localNode.left
        }
        return localNode!!.data
    }
    // find greatest value below the left child of this root node
    private fun predecessor(node: NodeTree): Int {
        var localNode = node.left
        while (localNode?.right !=null){
            localNode = localNode.right
        }
        return localNode!!.data
    }
}

data class NodeTree(
    var data: Int,
    var left: NodeTree? = null,
    var right: NodeTree? = null
)


fun main() {

    val tree = BinarySearchTrees()
    tree.insert(NodeTree(5))
    tree.insert(NodeTree(1))
    tree.insert(NodeTree(9))
    tree.insert(NodeTree(2))
    tree.insert(NodeTree(7))
    tree.insert(NodeTree(3))
    tree.insert(NodeTree(6))
    tree.insert(NodeTree(4))

    tree.remove(5)
    tree.display()

}
