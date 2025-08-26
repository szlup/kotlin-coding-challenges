package com.igorwojda.tree.multiway.traversal.breathfirst

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun traverseBreathFirst(tree: BinarySearchTree<Char>): List<Char> {
    val result = mutableListOf<BinaryNode<Char>>()

    if (tree.isEmpty()) return listOf<Char>()
    else {
        var allNull = false

        result.add(tree.root!!)
        //add left and right
        if (tree.root!!.left != null) result.add(tree.root!!.left!!)
        if (tree.root!!.right != null) result.add(tree.root!!.right!!)
        var i = 1
        var j = result.lastIndex

        while (!allNull) {//not sure about condition...
            //move through list to make sure we add left/right of added elements before moving on
            var currentLevel = result.subList(i, j+1)
            var nextLevel = mutableListOf<BinaryNode<Char>>()
            for (node in currentLevel) {
                if (node.left == null && node.right == null) allNull = true
                else if (node.left != null && node.right != null) {
                    nextLevel.add(node.left!!)
                    nextLevel.add(node.right!!)
                    allNull = false
                } else if (node.left != null) {
                    nextLevel.add(node.left!!)
                    allNull = false
                } else if (node.right != null) {
                    nextLevel.add(node.right!!)
                    allNull = false
                } else throw Exception("Unexpected state")
            }
            result.addAll(nextLevel)
            i = j + 1
            j = result.lastIndex
        }

        return result.map { it.data }
    }
}

private class BinarySearchTree<E : Comparable<E>> {
    var root: BinaryNode<E>? = null
        private set

    fun add(element: E) {
        val newNode = BinaryNode(element)

        if (root == null) {
            root = newNode
            return
        }

        var current: BinaryNode<E> = root ?: return

        while (true) {
            when {
                current.data == element -> {
                    return
                }
                element < current.data -> {
                    if (current.left == null) {
                        current.left = newNode
                        return
                    }

                    current.left?.let { current = it }
                }
                element > current.data -> {
                    if (current.right == null) {
                        current.right = newNode
                        return
                    }

                    current.right?.let { current = it }
                }
            }
        }
    }

    fun contains(element: E): Boolean {
        var current = root

        while (true) {
            if (current == null) {
                break
            } else if (current.data == element) {
                return true
            } else if (element < current.data) {
                current = current.left
            } else if (element > current.data) {
                current = current.right
            }
        }

        return false
    }

    fun isEmpty() = root == null
}

private data class BinaryNode<E : Comparable<E>>(
    val data: E,
    var left: BinaryNode<E>? = null,
    var right: BinaryNode<E>? = null,
)

private class Test {
    // ---------Tree------------
    //
    //           F
    //         /   \
    //        B     G
    //       / \     \
    //      A   D     I
    //         / \   /
    //        C   E H
    //
    // --------------------------
    private fun getTree() = BinarySearchTree<Char>().apply {
        add('F')
        add('B')
        add('A')
        add('D')
        add('C')
        add('E')
        add('G')
        add('I')
        add('H')
    }

    @Test
    fun `traverse breath first`() {
        traverseBreathFirst(getTree()) shouldBeEqualTo listOf(
            'F',
            'B',
            'G',
            'A',
            'D',
            'I',
            'C',
            'E',
            'H',
        )
    }
}
