package com.goldenberg.data.structure.tables.defaultImpl.row

import com.goldenberg.data.structure.tables.Cell
import com.goldenberg.data.structure.tables.Column
import com.goldenberg.data.structure.tables.TableFactory
import com.goldenberg.data.structure.tables.WritableTable
import com.goldenberg.data.structure.tables.defaultImpl.DefaultCell
import com.goldenberg.data.structure.tables.defaultImpl.DefaultColumn
import com.goldenberg.data.structure.tables.defaultImpl.DefaultTableFactory
import com.goldenberg.data.structure.tables.row.AbstractTableRowGetCellTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@Suppress("ReplaceGetOrSet")
class DefaultTableRowGetCellTest: AbstractTableRowGetCellTest() {

    lateinit var col1: Column
    private lateinit var col2: Column

    private lateinit var cell1: Cell
    private lateinit var cell2: Cell

    private lateinit var writableTable: WritableTable

    @BeforeEach
    override fun setup() {
        super.setup()
        writableTable = this.row.getTable() as WritableTable

        col1 = tableFactory.createColumn(writableTable, "col1", true)
        col2 = tableFactory.createColumn(writableTable, "col2", true)

        cell1 = tableFactory.createCell(writableTable, row, col1, "col1val")
        cell2 = tableFactory.createCell(writableTable, row, col2, "col2val")
    }

    @Test
    override fun `Get Cell By Column`() {
        assertEquals("col1val", this.row.get(col1)!!.getValue())
    }

    @Test
    override fun `Get Cell By Same Name Column From Another Table`() {
        val col = DefaultColumn(tableFactory.createTable("random"), "col1", true)
        assertEquals("col1val", this.row.get(col)!!.getValue())
    }

    @Test
    override fun `Get Cell By not added Column`() {
        val col = DefaultColumn(tableFactory.createTable("random"), "col3", true)
        assertNull(this.row.get(col))
    }

    @Test
    override fun `Get Cell Value By Column Name`() {
        assertEquals("col1val", this.row.getCellValue(("col1")))
    }

    @Test
    override fun `Get Cell Value By Non-Existed Column Name`() {
        assertNull(this.row.getCellValue("col"))
    }

    @Test
    override fun `Get Cell Value By Column`() {
        assertEquals("col1val", this.row.getCellValue(col1))
    }

    @Test
    override fun `Get Cell Value By Same Name Column From Another Table`() {
        val col = DefaultColumn(tableFactory.createTable("random"), "col1", true)
        assertEquals("col1val", this.row.getCellValue(col))
    }

    @Test
    override fun `Get Cell Value By not added Column`() {
        val col = DefaultColumn(tableFactory.createTable("random"), "col3", true)
        assertNull(this.row.getCellValue(col))
    }

    @Test
    override fun `Get Cell By Column Name`() {
        assertEquals("col1val", this.row.get("col1")!!.getValue())
    }

    @Test
    override fun `Get Cell By Non-Existed Column Name`() {
        assertNull(this.row.get("col"))
    }

    @Test
    override fun `Get Cell By Column By Operator`() {
        assertEquals("col1val", this.row[col1]!!.getValue())
    }

    @Test
    override fun `Get Cell By not added Column By Operator`() {
        val col = DefaultColumn(tableFactory.createTable("random"), "col3", true)
        assertNull(this.row[col])
    }

    @Test
    override fun `Get Cell By Same Name Column From Another Table By Operator`() {
        val col = DefaultColumn(tableFactory.createTable("random"), "col1", true)
        assertEquals("col1val", this.row[col]!!.getValue())
    }

    @Test
    override fun `Get Cell By Column Name By Operator`() {
        assertEquals("col1val", this.row["col1"]!!.getValue())
    }

    @Test
    override fun `Get Cell By Non-Existed Column Name By Operator`() {
        assertNull(this.row["col"])
    }

    @Test
    override fun `Is Row Contains Existed Cell`() {
        assertTrue { row.contains(cell1) }
    }

    @Test
    override fun `Is Row Contains Another Cell With Existed Value`() {
        val tab = tableFactory.createTable("random")
        val row0 = tableFactory.createRow(tab, 0)
        val contains = row.contains(
            DefaultCell(tab, row0, col1, "col1val"))
        assertTrue{ contains }
    }

    @Test
    override fun `Is Row Contains Non-Existed Cell`() {
        val tab = tableFactory.createTable("random")
        val row0 = tableFactory.createRow(tab, 0)
        val contains = row.contains(
            DefaultCell(tab, row0, col1, "col1val3"))
                    assertFalse { contains }
    }

    @Test
    override fun `Is Row Contains Set Of Existed Cells`() {
        assertTrue { row.containsAll(listOf(cell1, cell2)) }
    }

    @Test
    override fun `Is Row Contains Set Of Another Cells With Existed Values`() {
        val tab = tableFactory.createTable("random")
        val row0 = tableFactory.createRow(tab, 0)
        val row1 = tableFactory.createRow(tab, 1)
        val varCell0 = DefaultCell(tab, row0, col1, "col1val")
        val varCell1 = DefaultCell(tab, row1, col1, "col1val")
        assertTrue{ row.containsAll(listOf(varCell0, varCell1)) }
    }

    @Test
    override fun `Is Row Contains Non-Existed Cells`() {
        val tab = tableFactory.createTable("random")
        val row0 = tableFactory.createRow(tab, 0)
        val row1 = tableFactory.createRow(tab, 1)
        val varCell0 = DefaultCell(tab, row0, col1, "col1val3")
        val varCell1 = DefaultCell(tab, row1, col1, "col1val4")
        assertFalse { row.containsAll(listOf(varCell0, varCell1)) }
    }

    @Test
    override fun `Is Row Contains Non-Existed And Existed Cells`() {
        val tab = tableFactory.createTable("random")
        val row0 = tableFactory.createRow(tab, 0)
        val varCell0 = DefaultCell(tab, row0, col1, "col1val3")
        assertFalse { row.containsAll(listOf(varCell0, cell1)) } }

    override fun setTableFactory(): TableFactory {
        return DefaultTableFactory.instance
    }
}
