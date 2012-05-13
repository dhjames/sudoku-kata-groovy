class SudokuPuzzle {

  private Integer[][] tableData

  SudokuPuzzle() {
    initializeEmptyTable()
  }

  private void initializeEmptyTable() {
    tableData = new Integer[9][9]
  }

  boolean isSolved() {
    if (!getRowSets().every {isValidSudokuSet(it)}) return false
    return true
  }

  private boolean isValidSudokuSet(row) {
    row == 1..9 as Set
  }

  void setTableData(Integer[][] values) {
    ensureValidInput(values)
    tableData = values
  }

  private void ensureValidInput(Integer[][] values) {
    ensureRowSize(values)
    ensureValidColumn(values)
  }

  private void ensureRowSize(Integer[][] values) {
    if (values.length != 9)
      throw new IllegalArgumentException()
  }

  private void ensureValidColumn(Integer[][] solution) {
    for (row in solution) {
      if (row.length != 9)
        throw new IllegalArgumentException()
      if (!row.every {isValidCell(it)})
        throw new IllegalArgumentException()
    }
  }

  private boolean isValidCell(Integer cell) {
    return cell == null || (1..9).contains(cell)
  }

  private List getRowSets() {
    def rowSets = []
    for (Set row in tableData) {
      rowSets << row
    }
    return rowSets
  }
}
