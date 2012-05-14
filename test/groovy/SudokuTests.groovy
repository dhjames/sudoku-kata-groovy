import spock.lang.Specification

class SudokuTests extends Specification {

  SudokuPuzzle sudoku = new SudokuPuzzle()
  Integer[][] solution

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
    data[row][col] = value

    when:
    sudoku.setTableData(data)

    then:
    thrown(IllegalArgumentException)

    where:
    row | col | value
    0   | 0   | 0
    8   | 8   | 10
    1   | 8   | -1
  }

  def "given a correct puzzle solution, isSolved should return true"() {
    given:
    solution = [
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

    when:
    sudoku.setTableData(solution)

    then:
    sudoku.isSolved()
  }

  def "given a correct solution minus one missing value, isSolved should return false"() {
    given:
    solution = [
        [5, 3, 4, 6, 7, 8, 9, 1, 2],
        [6, 7, 2, 1, 9, 5, 3, 4, 8],
        [1, 9, 8, 3, 4, 2, 5, 6, 7],
        [8, 5, 9, 7, 6, 1, 4, 2, 3],
        [4, 2, 6, 8, 5, 3, 7, 9, 1],
        [7, 1, 3, 9, 2, 4, 8, 5, 6],
        [9, 6, 1, 5, 3, 7, 2, 8, 4],
        [2, 8, 7, 4, 1, 9, 6, 3, 5],
        [3, 4, 5, 2, 8, 6, 1, 7, null]
    ]

    when:
    sudoku.setTableData(solution)

    then:
    !sudoku.isSolved()
  }

  def "when solution has valid columns and boxes, but not rows, puzzle is not solved"() {
    when:
    solution = [
        [1, 3, 4, 6, 7, 8, 9, 1, 2],
        [6, 7, 2, 1, 9, 5, 3, 4, 8],
        [5, 9, 8, 3, 4, 2, 5, 6, 7],
        [8, 5, 9, 7, 6, 1, 4, 2, 3],
        [4, 2, 6, 8, 5, 3, 7, 9, 1],
        [7, 1, 3, 9, 2, 4, 8, 5, 6],
        [9, 6, 1, 5, 3, 7, 2, 8, 4],
        [2, 8, 7, 4, 1, 9, 6, 3, 5],
        [3, 4, 5, 2, 8, 6, 1, 7, 9]
    ]
    sudoku.setTableData(solution)

    then:
    !sudoku.isSolved()
  }

  def "when solution has valid rows and boxes, but not columns, puzzle is not solved"() {
    when:
    solution = [
        [3, 5, 4, 6, 7, 8, 9, 1, 2],
        [6, 7, 2, 1, 9, 5, 3, 4, 8],
        [1, 9, 8, 3, 4, 2, 5, 6, 7],
        [8, 5, 9, 7, 6, 1, 4, 2, 3],
        [4, 2, 6, 8, 5, 3, 7, 9, 1],
        [7, 1, 3, 9, 2, 4, 8, 5, 6],
        [9, 6, 1, 5, 3, 7, 2, 8, 4],
        [2, 8, 7, 4, 1, 9, 6, 3, 5],
        [3, 4, 5, 2, 8, 6, 1, 7, 9]
    ]
    sudoku.setTableData(solution)

    then:
    !sudoku.isSolved()
  }
}