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
    if (!getColSets().every {isValidSudokuSet(it)}) return false
    return true
  }

  private List getRowSets() {
    def rowSets = []
    for (Set row in tableData) {
      rowSets << row
    }
    return rowSets
  }

  private List getColSets() {
    def cols = []
    for (colIndex in 0..8) {
      Set<Integer> col = []
      cols << col
      for (rowIndex in 0..8) {
        col << tableData[rowIndex][colIndex]
      }
    }
    return cols
  }

  private boolean isValidSudokuSet(Set<Integer> set) {
    set == 1..9 as Set
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
}
