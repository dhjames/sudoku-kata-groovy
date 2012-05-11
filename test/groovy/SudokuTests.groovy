import spock.lang.Specification

class SudokuTests extends Specification {

  SudokuPuzzle sudoku = new SudokuPuzzle()

  def "a sudoku puzzle with no values should not be declared solved"() {
    when:
    sudoku.setTableData(new Integer[9][9])

    then:
    !sudoku.isSolved()
  }
}