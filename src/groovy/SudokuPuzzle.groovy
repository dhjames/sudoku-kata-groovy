class SudokuPuzzle {

  private Integer[][] tableData = new Integer[9][9]

  boolean isSolved() {
    for (row in tableData) {
      if (row.any {it == null}) return false
    }
    return true
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

  private void ensureValidColumn(Integer[][] data) {
    for (row in data) {
      if (row.length != 9)
        throw new IllegalArgumentException()
      if (!row.every {col -> isValidValue(col)})
        throw new IllegalArgumentException()
    }
  }

  private boolean isValidValue(Integer cell) {
    return cell == null || (1..9).contains(cell)
  }
}
