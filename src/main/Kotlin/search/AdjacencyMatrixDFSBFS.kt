package search

import java.util.LinkedList
import java.util.Queue


class Graph(size: Int) {
    val nodes: ArrayList<Node> = arrayListOf()
    private val matrix = Array(size) { IntArray(size) }

    fun addNode(node: Node) {
        nodes.add(node)
    }

    fun addEdge(src: Int, dst: Int) {
        matrix[src][dst] = 1
    }

    fun checkEdge(src: Int, dst: Int): Boolean {
        return matrix[src][dst] == 1
    }

    fun print() {
        print("  ")
        for (node in nodes) {
            print(node.data + " ")
        }
        println()

        for (element in matrix.withIndex()) {
            print(nodes[element.index].data + " ")
            for (j in matrix.indices) {
                print(element.value[j].toString() + " ")
            }
            println()
        }

    }

    // implement depth first search

    fun depthFirstSearch(src: Int) {
        val visited = Array(matrix.size) { false }
        dFSHelper(src, visited)
    }

    private fun dFSHelper(src: Int, visited: Array<Boolean>) {
        if (visited[src]) {
            return
        } else {
            visited[src] = true
            println(nodes[src].data + " = visited")
        }
        for (i in 0..<matrix[src].size) {
            if (matrix[src][i] == 1) {
                dFSHelper(i, visited)
            }
        }
        return
    }


    // implement breadth first search

    fun breadthFirstSearch(src: Int) {
        var localSrc = src
        val queue = LinkedList<Int>()
        val visited = Array(matrix.size) { false }

        queue.offer(localSrc)
        visited[localSrc] = true
        while (queue.size != 0){
            localSrc = queue.poll()
            println(nodes[localSrc].data + " = visited")

            for (i in 0..<matrix[localSrc].size){
                if (matrix[localSrc][i] == 1 && !visited[i]){
                    queue.offer(i)
                    visited[i] = true
                }
            }
        }
    }
}


data class Node(
    val data: Char,
)


fun main() {
    val graph = Graph(5)

    graph.addNode(Node('A'))
    graph.addNode(Node('B'))
    graph.addNode(Node('C'))
    graph.addNode(Node('D'))
    graph.addNode(Node('E'))

    graph.addEdge(0, 1)
    graph.addEdge(1, 2)
    graph.addEdge(2, 3)
    graph.addEdge(2, 4)
    graph.addEdge(4, 0)
    graph.addEdge(4, 2)
    graph.print()
   // graph.depthFirstSearch(0)
    graph.breadthFirstSearch(0)
}