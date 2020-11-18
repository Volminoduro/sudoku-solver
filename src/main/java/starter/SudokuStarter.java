package starter;

import entity.Position;
import entity.Square;
import utilities.OrderedSetSudoku;

import java.util.Iterator;
import java.util.Set;

public class SudokuStarter {
    // TODO Whole Translation to most performant language (C ?)
    // TODO : https://dzone.com/articles/jmh-great-java-benchmarking
    // TODO : for benchmarking too Java VisualVM

    public static void main(final String[] args) {
        OrderedSetSudoku.initializeEmptySudoku();
        initializeSudoku();
        while (!OrderedSetSudoku.isSudokuComplete()) {
            final Iterator<Square> iter = OrderedSetSudoku.orderedSquare.iterator();
            while (iter.hasNext()) {
                final Square square = iter.next();
                Set<Square> positionsModified = square.treatmentForSudoku();
                while (positionsModified != null && !positionsModified.isEmpty()) {
                    positionsModified = positionsModified.iterator().next().treatmentForSudoku();
                }
            }
        }
        System.out.println(OrderedSetSudoku.displaySudoku());
    }

    private static void initializeSudoku() {
        OrderedSetSudoku.putAndReplace(new Square(new Position(0, 0), 5));
        OrderedSetSudoku.putAndReplace(new Square(new Position(0, 1), 3));
        OrderedSetSudoku.putAndReplace(new Square(new Position(1, 0), 6));
        OrderedSetSudoku.putAndReplace(new Square(new Position(2, 1), 9));
        OrderedSetSudoku.putAndReplace(new Square(new Position(2, 2), 8));

        OrderedSetSudoku.putAndReplace(new Square(new Position(0, 4), 7));
        OrderedSetSudoku.putAndReplace(new Square(new Position(1, 3), 1));
        OrderedSetSudoku.putAndReplace(new Square(new Position(1, 4), 9));
        OrderedSetSudoku.putAndReplace(new Square(new Position(1, 5), 5));

        OrderedSetSudoku.putAndReplace(new Square(new Position(2, 7), 5));

        OrderedSetSudoku.putAndReplace(new Square(new Position(3, 0), 8));
        OrderedSetSudoku.putAndReplace(new Square(new Position(4, 0), 4));
        OrderedSetSudoku.putAndReplace(new Square(new Position(5, 0), 7));

        OrderedSetSudoku.putAndReplace(new Square(new Position(3, 4), 6));
        OrderedSetSudoku.putAndReplace(new Square(new Position(4, 3), 8));
        OrderedSetSudoku.putAndReplace(new Square(new Position(4, 5), 3));

        OrderedSetSudoku.putAndReplace(new Square(new Position(3, 8), 3));
        OrderedSetSudoku.putAndReplace(new Square(new Position(4, 8), 1));
        OrderedSetSudoku.putAndReplace(new Square(new Position(5, 4), 2));
        OrderedSetSudoku.putAndReplace(new Square(new Position(5, 8), 6));

        OrderedSetSudoku.putAndReplace(new Square(new Position(6, 1), 6));

        OrderedSetSudoku.putAndReplace(new Square(new Position(7, 3), 4));
        OrderedSetSudoku.putAndReplace(new Square(new Position(7, 4), 1));
        OrderedSetSudoku.putAndReplace(new Square(new Position(7, 5), 9));
        OrderedSetSudoku.putAndReplace(new Square(new Position(8, 4), 8));

        OrderedSetSudoku.putAndReplace(new Square(new Position(6, 6), 2));
        OrderedSetSudoku.putAndReplace(new Square(new Position(6, 7), 8));
        OrderedSetSudoku.putAndReplace(new Square(new Position(7, 8), 5));
        OrderedSetSudoku.putAndReplace(new Square(new Position(8, 7), 7));
        OrderedSetSudoku.putAndReplace(new Square(new Position(8, 8), 9));
    }
}

