package com.goldenberg.data.sorting.algorithms

import com.goldenberg.data.enums.Order
import com.goldenberg.data.sorting.getSortingAlgorithmPredicate

class SelectionSort: Sorting {

    override fun <T : Comparable<T>> sort(values: MutableList<T>, order: Order): MutableList<T> {
        val predicate = getSortingAlgorithmPredicate<T>(order)

        for (index1 in 0 until values.size) {
            var minIndex =index1
            var min = values[index1]

            for (index2 in index1 + 1 until values.size) {
                if (predicate.invoke(min, values[index2])) {
                    min = values[index2]
                    minIndex = index2 }
            }

            val tmp = values[index1]
            values[index1] = min
            values[minIndex] = tmp
        }
        return values
    }
}
