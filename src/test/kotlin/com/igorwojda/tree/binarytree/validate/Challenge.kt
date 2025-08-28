package com.igorwojda.tree.binarytree.validate

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun isValidSearchBinaryTree(node: Node<Int>): Boolean {
    return if (node.left == null && node.right == null) true
    else isValidSearchBinaryTree_helper(node.left, node.data, Direction.LEFT) &&
            isValidSearchBinaryTree_helper(node.right, node.data, Direction.RIGHT)
}

private fun isValidSearchBinaryTree_helper(node: Node<Int>?, top: Int, dir: Direction): Boolean {
    return when {
        node == null -> true
        node.left == null && node.right == null -> true
        node.left == null && node.right != null -> {
            val right = node.right!!.data
            return if (right > node.data && (if (dir == Direction.RIGHT) right > top else right < top)) {
                isValidSearchBinaryTree_helper(node.right, top, dir)
            }else false
        }
        node.left != null && node.right == null -> {
            val left = node.left!!.data
            return if (left < node.data && (if (dir == Direction.RIGHT) left > top else left < top)) {
                isValidSearchBinaryTree_helper(node.left, top, dir)
            }else false
        }
        node.left != null && node.right != null -> {
            val left = node.left!!.data
            val right = node.right!!.data
            return when {
                left > node.data || right < node.data -> false
                dir == Direction.LEFT  && (left > top || right > top) -> false
                dir == Direction.RIGHT && (left < top || right < top) -> false
                else -> {
                    isValidSearchBinaryTree_helper(node.left, top, dir) &&
                        isValidSearchBinaryTree_helper(node.right, top, dir)
                }
            }
        }

        else -> false
    }
}

private enum class Direction { RIGHT, LEFT }

private class Test {
    @Test
    fun `Validate valid BST`() {
        // -- -------Tree------------
        //
        //           10
        //          /  \
        //         5    15
        //        /       \
        //       0         20
        // --------------------------

        val node = Node(10)
        node.insert(5)
        node.insert(15)
        node.insert(0)
        node.insert(20)

        isValidSearchBinaryTree(node) shouldBeEqualTo true
    }

    @Test
    fun `Validate invalid BST 1`() {
        // -- -------Tree------------
        //
        //           10
        //          /  \
        //         5    15
        //        /       \
        //       0         20
        //        \
        //        999
        // --------------------------

        val node = Node(10)
        node.insert(5)
        node.insert(15)
        node.insert(0)
        node.insert(20)
        node.left?.left?.right = Node(999)

        isValidSearchBinaryTree(node) shouldBeEqualTo false
    }

    @Test
    fun `Validate invalid BST 2`() {
        // -- -------Tree------------
        //
        //           10
        //          /  \
        //         5    15
        //        /       \
        //       0         20
        //     /  \
        //   -1   999
        // --------------------------

        val node = Node(10)
        node.insert(5)
        node.insert(15)
        node.insert(0)
        node.insert(-1)
        node.insert(20)
        node.left?.left?.right = Node(999)

        isValidSearchBinaryTree(node) shouldBeEqualTo false
    }

    @Test
    fun `Validate invalid BST 3`() {
        // -- -------Tree------------
        //
        //           10
        //          /  \
        //         5    15
        //        /       \
        //       0         20
        //                /  \
        //             999    21
        // --------------------------

        val node = Node(10)
        node.insert(5)
        node.insert(15)
        node.insert(0)
        node.insert(20)
        node.insert(21)
        node.right?.right?.left = Node(999)

        isValidSearchBinaryTree(node) shouldBeEqualTo false
    }
}

private data class Node<E : Comparable<E>>(
    var data: E,
    var left: Node<E>? = null,
    var right: Node<E>? = null,
) {
    fun insert(e: E) {
        if (e < data) { // left node
            if (left == null) {
                left = Node(e)
            } else {
                left?.insert(e)
            }
        } else if (e > data) { // right node
            if (right == null) {
                right = Node(e)
            } else {
                right?.insert(e)
            }
        }
    }
}
