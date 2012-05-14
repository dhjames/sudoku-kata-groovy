import spock.lang.Specification

class SudokuTests extends Specification {

  SudokuPuzzle sudoku = new SudokuPuzzle()

  Integer[][] solution = [
      [5, 3, 4, 6, 7, 8, 9, 1, 2],
      [6, 7, 2, 1, 9, 5, 3, 4, 8],
      [1, 9, 8, 3, 4, 2, 5, 6, 7],
      [8, 5, 9, 7, 6, 1, 4, 2, 3],
      [4, 2, 6, 8, 5, 3, 7, 9, 1],
      [7, 1, 3, 9, 2, 4, 8, 5, 6],
      [9, 6, 1, 5, 3, 7, 2, 8, 4],
      [2, 8, 7, 4, 1, 9, 6, 3, 5],
      [3, 4, 5, 2, 8, 6, 1, 7, 9]
  ]

  def "an empty sudoku puzzle should not be declared solved"() {
    expect:
    !sudoku.isSolved()
  }

  def "should throw exception if solution data is not a 9x9 array"() {
    when:
    sudoku.setTableData(new Integer[rows][cols])

    then:
    thrown(IllegalArgumentException)

    where:
    rows | cols
    0    | 0
    8    | 9
    9    | 10
  }

  def "should throw exception when a cell value is not in the set {1..9} or null"() {
    given:
    def data = new Integer[9][9]
    data[row][col] = cellValue

    when:
    sudoku.setTableData(data)

    then:
    thrown(IllegalArgumentException)

    where:
    row | col | cellValue
    0   | 0   | 0
    8   | 8   | 10
    1   | 8   | -1
  }

  def "given a correct puzzle solution, puzzle is solved"() {
    when:
    sudoku.setTableData(solution)

    then:
    sudoku.isSolved()
  }

  def "given a correct solution minus one missing value, puzzle is not solved"() {
    when:
    solution[8][8] = null
    sudoku.setTableData(solution)

    then:
    !sudoku.isSolved()
  }

  def "when solution has valid columns and boxes, but not rows, puzzle is not solved"() {
    when:
    solution[0][0] = 1
    solution[2][0] = 5
    sudoku.setTableData(solution)

    assert sudoku.hasValidColumns()
    assert sudoku.hasValidBoxes()
    assert !sudoku.hasValidRows()

    then:
    !sudoku.isSolved()
  }

  def "when solution has valid rows and boxes, but not columns, puzzle is not solved"() {
    when:
    solution[0][0] = 3
    solution[0][1] = 5
    sudoku.setTableData(solution)

    assert sudoku.hasValidRows()
    assert sudoku.hasValidBoxes()
    assert !sudoku.hasValidColumns()

    then:
    !sudoku.isSolved()
  }

  def "when solution has valid rows and columns, but not boxes, puzzle is not solved"() {
    when:
    swapRowsInSolution(2, 3)
    sudoku.setTableData(solution)

    assert sudoku.hasValidRows()
    assert sudoku.hasValidColumns()
    assert !sudoku.hasValidBoxes()

    then:
    !sudoku.isSolved()
  }

  private void swapRowsInSolution(int row1, int row2) {
    def tempRow = solution[row1]
    solution[row1] = solution[row2]
    solution[row2] = tempRow
  }
}