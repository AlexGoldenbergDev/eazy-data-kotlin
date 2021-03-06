package com.goldenberg.data.structure.tables

import com.goldenberg.data.structure.tables.defaultImpl.DefaultCell

interface TableFactory {

    fun createTable(id: String): WritableTable

    fun createColumn(table: WritableTable, name:String, isComparable: Boolean = true, defaultValue: Any? = null): Column

    fun createColumn(table: WritableTable, col: Column): Column

    fun createRow(table: WritableTable, id: Int): Row

    fun createRow(table: WritableTable, row: Row, index: Int = row.getIndex(), isOverride: Boolean = false): Row

    fun createCell(table: WritableTable, row: Row, column: Column, value: Any?) : Cell

    fun createCell(table: WritableTable, cell: Cell): DefaultCell
}

