import spock.lang.Specification

class SudokuTests extends Specification {

  SudokuPuzzle sudoku = new SudokuPuzzle()

  def "an empty sudoku puzzle should not be declared solved"() {
    when:
    sudoku.setTableData(new Integer[9][9])

    then:
    !sudoku.isSolved()
  }

  def "should throw exception if table data is not a 9x9 array"() {
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

  def "should throw exception when a cell value is not in the range 1..9 or null"() {
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
}