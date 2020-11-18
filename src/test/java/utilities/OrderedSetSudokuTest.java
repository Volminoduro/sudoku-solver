package utilities;

import entity.Position;
import entity.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderedSetSudokuTest {

    @Test
    void putAndReplace() {
        OrderedSetSudoku.initializeEmptySudoku();

        final Square square = new Square(new Position(0, 0), 1);
        OrderedSetSudoku.putAndReplace(square);
        final Square squareFound = OrderedSetSudoku.orderedSquare.stream().filter(x -> x.compareTo(square) == 0).findFirst().get();
        Assertions.assertEquals(squareFound.getChosenNumber(), square.getChosenNumber());
    }

}
