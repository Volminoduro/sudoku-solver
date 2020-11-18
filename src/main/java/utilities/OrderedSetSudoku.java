package utilities;

import entity.Position;
import entity.Square;
import parameters.Parameters;

import java.util.*;
import java.util.stream.Collectors;

public class OrderedSetSudoku {

    public static final int HEIGHT_SIDE = Parameters.SUDOKU_HEIGHT_SIDE;
    public static final int WIDTH_SIDE = Parameters.SUDOKU_WIDTH_SIDE;
    public static TreeSet<Square> orderedSquare = new TreeSet<>();

    public static void initializeEmptySudoku() {
        for (int iteratorHeight = 0; iteratorHeight < OrderedSetSudoku.HEIGHT_SIDE * OrderedSetSudoku.HEIGHT_SIDE; iteratorHeight++) {
            for (int iteratorWidth = 0; iteratorWidth < OrderedSetSudoku.WIDTH_SIDE * OrderedSetSudoku.WIDTH_SIDE; iteratorWidth++) {
                final Position position = new Position(iteratorHeight, iteratorWidth);
                OrderedSetSudoku.orderedSquare.add(new Square(position));
            }
        }
    }

    public static void putAndReplace(final Square squareToPut) {
        OrderedSetSudoku.orderedSquare.remove(squareToPut);
        OrderedSetSudoku.orderedSquare.add(squareToPut);
    }

    public static Set<Square> deletePotentialNumberFromRow(final Position position, final int potentialNumber) {
        return OrderedSetSudoku.getSquaresFromRow(position).stream().filter(square -> square != null && square.deletePotentialNumbers(potentialNumber)).collect(Collectors.toSet());
    }

    public static Set<Square> deletePotentialNumberFromColumn(final Position position, final int potentialNumber) {
        return OrderedSetSudoku.getSquaresFromColumn(position).stream().filter(square -> square != null && square.deletePotentialNumbers(potentialNumber)).collect(Collectors.toSet());
    }

    public static Set<Square> deletePotentialNumberFromZone(final Position position, final int potentialNumber) {
        return OrderedSetSudoku.getSquaresFromZone(position).stream().filter(square -> square != null && square.deletePotentialNumbers(potentialNumber)).collect(Collectors.toSet());
    }

    private static Square getKeyFromValue(final Position positionToFound) {
        final Optional<Square> squareFound = OrderedSetSudoku.orderedSquare.stream().filter(x -> x.getPosition().equals(positionToFound)).findFirst();
        if (squareFound.isPresent()) {
            return squareFound.get();
        }
        return null;
    }

    private static Collection<Square> getSquaresFromRow(final Position position) {
        final Collection<Square> squares = new LinkedHashSet<>();
        for (int rowIterator = 0; rowIterator < OrderedSetSudoku.HEIGHT_SIDE * OrderedSetSudoku.HEIGHT_SIDE; rowIterator++) {
            squares.add(OrderedSetSudoku.getKeyFromValue(new Position(rowIterator, position.columnPosition)));
        }
        return squares;
    }

    private static Collection<Square> getSquaresFromColumn(final Position position) {
        final Collection<Square> squares = new LinkedHashSet<>();
        for (int columnIterator = 0; columnIterator < OrderedSetSudoku.WIDTH_SIDE * OrderedSetSudoku.WIDTH_SIDE; columnIterator++) {
            squares.add(OrderedSetSudoku.getKeyFromValue(new Position(position.rowPosition, columnIterator)));
        }
        return squares;
    }

    private static Collection<Square> getSquaresFromZone(final Position position) {
        final Collection<Square> squares = new LinkedHashSet<>();
        final Position startOfZonePosition = OrderedSetSudoku.getStartOfZone(position);
        for (int rowIterator = 0; rowIterator < OrderedSetSudoku.HEIGHT_SIDE; rowIterator++) {
            for (int columnIterator = 0; columnIterator < OrderedSetSudoku.WIDTH_SIDE; columnIterator++) {
                squares.add(OrderedSetSudoku.getKeyFromValue(new Position(startOfZonePosition.rowPosition + rowIterator, startOfZonePosition.columnPosition + columnIterator)));
            }
        }
        return squares;
    }

    private static Position getStartOfZone(final Position position) {
        final int rowStartingZonePosition = position.rowPosition;
        final int columnStartingZonePosition = position.columnPosition;
        return new Position(rowStartingZonePosition - (rowStartingZonePosition % OrderedSetSudoku.HEIGHT_SIDE), columnStartingZonePosition - (columnStartingZonePosition % OrderedSetSudoku.WIDTH_SIDE));
    }

    public static boolean isSudokuComplete() {
        return OrderedSetSudoku.orderedSquare.first().isValidNumber();
    }

    public static String displaySudoku() {
        final StringBuilder textToDisplay = new StringBuilder();
        // TODO : Use of entrySet would be prefered
        for (int rowIterator = 0; rowIterator < OrderedSetSudoku.HEIGHT_SIDE * OrderedSetSudoku.HEIGHT_SIDE; rowIterator++) {
            for (int columnIterator = 0; columnIterator < OrderedSetSudoku.WIDTH_SIDE * OrderedSetSudoku.WIDTH_SIDE; columnIterator++) {
                final Square actualSquare = OrderedSetSudoku.getKeyFromValue(new Position(rowIterator, columnIterator));
                textToDisplay.append(actualSquare.getChosenNumber()).append(", ");
            }
            textToDisplay.append("\n");
        }
        return textToDisplay.toString();
    }
}
