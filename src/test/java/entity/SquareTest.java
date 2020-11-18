package entity;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SquareTest {

    @Test
    void setChosenNumber_Should_ClearPotentialNumbers() {
        final SquareMock square = new SquareMock(new Position(1, 0));
        final int[] potentialsNumbers = new int[2];
        potentialsNumbers[0] = 1;
        potentialsNumbers[1] = 2;
        square.setPotentialNumbers(potentialsNumbers);
        square.setChosenNumber(2);
        assertTrue(square.getPotentialNumbers().length == 1);
        assertTrue(square.isValidNumber());
        assertEquals(2, square.getChosenNumber());
    }

    @Test
    void deletePotentialNumbers_WithValidNumber_Return_False() {
        final SquareMock square = new SquareMock(new Position(1, 0), 1);
        assertFalse(square.deletePotentialNumbers(1));
    }

    @Test
    void deletePotentialNumbers_WithPotentialNumberExists_Return_True() {
        final Square square = new Square(new Position(1, 0));
        assertTrue(square.deletePotentialNumbers(1));
    }

    @Test
    void deletePotentialNumbers_WithUniquePotentialNumberInTheEnd_Return_False() {
        final SquareMock square = new SquareMock(new Position(1, 0));
        final int[] potentialsNumbers = new int[2];
        potentialsNumbers[0] = 1;
        potentialsNumbers[1] = 2;
        square.setPotentialNumbers(potentialsNumbers);
        assertFalse(square.deletePotentialNumbers(2));
    }

    @Test
    void deletePotentialNumbers_WithUniquePotentialNumberInTheEnd_Return_SquareWithUniquePotentialNumberAsChosenNumber() {
        final SquareMock square = new SquareMock(new Position(1, 0));
        final int[] potentialsNumbers = new int[2];
        potentialsNumbers[0] = 1;
        potentialsNumbers[1] = 2;
        square.setPotentialNumbers(potentialsNumbers);
        square.deletePotentialNumbers(2);
        assertTrue(square.isValidNumber());
        assertEquals(1, square.getChosenNumber());
    }

    @Test
    void deletePotentialNumbers_WithOnlyPossiblePotentialNumber_Return_Exception() {
        final SquareMock square = new SquareMock(new Position(1, 0));
        final int[] potentialsNumbers = new int[2];
        potentialsNumbers[0] = 1;
        square.setPotentialNumbers(potentialsNumbers);
        assertFalse(square.deletePotentialNumbers(1));
    }

    @Test
    void deletePotentialNumbers_NotPossibleInPotentialNumberAndValidNumber_Return_Exception() {
        final Square square = new Square(new Position(1, 0));
        assertFalse(square.deletePotentialNumbers(Integer.MIN_VALUE));
    }

    @Test
    void equals_WithSameObject_Return_True() {
        final Square square = new Square(new Position(1, 0));
        assertEquals(square, square);
    }

    @Test
    void equals_WithDifferentClassObject_Return_False() {
        final Square square = new Square(new Position(1, 0));
        assertNotEquals(square, new Object());
    }

    @Test
    void equals_WithDifferentPosition_Return_False() {
        final Square square = new Square(new Position(1, 0));
        final Square squareEquals = new Square(new Position(0, 0));
        assertNotEquals(square, squareEquals);
    }

    @Test
    void equals_WithSamePosition_Return_True() {
        final Square square = new Square(new Position(1, 0));
        final Square squareEquals = new Square(new Position(1, 0));
        assertEquals(square, squareEquals);
    }

    @Test
    void compareTo_WithSameObject_Return_0() {
        final SquareMock squareMock = new SquareMock(new Position(1, 0), 5);
        assertEquals(0, squareMock.compareTo(squareMock));
    }

    @Test
    void compareTo_ChosenNumberSquare_SamePositionSquare_Return_0() {
        final SquareMock squareMock = new SquareMock(new Position(1, 0), 5);
        final SquareMock squareCompared = new SquareMock(new Position(1, 0), 4);
        assertEquals(0, squareMock.compareTo(squareCompared));
    }

    @Test
    void compareTo_PotentialNumberSquare_SamePositionSquare_Return_0() {
        final SquareMock squareMock = generateSquareMockWithPotentialNumbers(1, 0);
        final SquareMock squareCompared = generateSquareMockWithPotentialNumbers(1, 0);
        assertEquals(0, squareMock.compareTo(squareCompared));
    }

    @Test
    void compareTo_PotentialNumberSquare_ChosenNumberSquare_Return_Negative() {
        final Square square = new Square(new Position(1, 0));
        final SquareMock squareCompared = new SquareMock(new Position(2, 0), 5);
        assertEquals(-1, square.compareTo(squareCompared));
    }

    /**
     * Chosen Numbers square's checks
     */

    @Test
    void compareTo_ChosenNumberSquare_FurtherPositionAndChosenNumberSquare_Return_Negative() {
        final Square squareMock = new Square(new Position(1, 0));
        final SquareMock squareCompared = generateSquareMockWithChosenNumbers(2, 0);
        assertEquals(-1, squareMock.compareTo(squareCompared));
    }

    @Test
    void compareTo_ChosenNumberSquare_CloserPositionAndChosenNumberSquare_Return_Positive() {
        final SquareMock squareMock = new SquareMock(new Position(1, 0), 5);
        final SquareMock squareCompared = generateSquareMockWithChosenNumbers(0, 0);
        assertEquals(1, squareMock.compareTo(squareCompared));
    }

    @Test
    void compareTo_ChosenNumberSquare_FurtherPositionAndPotentialNumberSquare_Return_Positive() {
        final SquareMock squareMock = new SquareMock(new Position(1, 0), 5);
        final SquareMock squareCompared = generateSquareMockWithPotentialNumbers(5, 0);
        assertEquals(1, squareMock.compareTo(squareCompared));
    }

    @Test
    void compareTo_ChosenNumberSquare_CloserPositionAndPotentialNumberSquare_Return_Positive() {
        final SquareMock squareMock = new SquareMock(new Position(1, 0), 5);
        final SquareMock squareCompared = generateSquareMockWithPotentialNumbers(0, 5);
        assertEquals(1, squareMock.compareTo(squareCompared));
    }

    /**
     * Potential numbers square's checks
     */

    @Test
    void compareTo_SameQuantityPotentialNumberSquare_CloserPosition_Return_Positive() {
        final SquareMock squareMock = generateSquareMockWithPotentialNumbers(1, 0);
        final SquareMock squareCompared = generateSquareMockWithPotentialNumbers(0, 5);
        assertEquals(1, squareMock.compareTo(squareCompared));
    }

    @Test
    void compareTo_SameQuantityPotentialNumberSquare_FurtherPosition_Return_Negative() {
        final SquareMock squareMock = generateSquareMockWithPotentialNumbers(1, 0);
        final SquareMock squareCompared = generateSquareMockWithPotentialNumbers(2, 5);
        assertEquals(-1, squareMock.compareTo(squareCompared));
    }

    @Test
    void compareTo_LessPotentialNumberSquare_Return_Negative() {
        final SquareMock squareMock = generateSquareMockWithPotentialNumbers(1, 0);
        int[] potentialNumbers = squareMock.getPotentialNumbers();
        potentialNumbers = Arrays.stream(potentialNumbers)
                .filter(x -> x != 1).toArray();
        squareMock.setPotentialNumbers(potentialNumbers);

        final SquareMock squareCompared = generateSquareMockWithPotentialNumbers(2, 5);
        assertEquals(-1, squareMock.compareTo(squareCompared));
    }

    @Test
    void compareTo_MorePotentialNumberSquare_Return_Positive() {
        final SquareMock squareMock = generateSquareMockWithPotentialNumbers(1, 0);

        final SquareMock squareCompared = generateSquareMockWithPotentialNumbers(2, 5);
        int[] potentialNumbers = squareCompared.getPotentialNumbers();
        potentialNumbers = Arrays.stream(potentialNumbers)
                .filter(x -> x != 1).toArray();
        squareCompared.setPotentialNumbers(potentialNumbers);

        assertEquals(1, squareMock.compareTo(squareCompared));
    }

    /**
     * TODO : Make reference to realAttribute, in case of rename
     * This will always generate a SquareMock with 9 as @see Square#ChosenNumber Chosen number
     *
     * @param rowPosition    rowPosition used for Position creating for SquareMock
     * @param columnPosition columnPosition used for Position creating for SquareMock
     * @return SquareMock with a Position using rowPosition and columnPosition, and 9 as Chosen number
     */
    private SquareMock generateSquareMockWithChosenNumbers(final Integer rowPosition, final Integer columnPosition) {
        return new SquareMock(new Position(rowPosition, columnPosition), 9);
    }

    private SquareMock generateSquareMockWithPotentialNumbers(final Integer rowPosition, final Integer columnPosition) {
        return new SquareMock(new Position(rowPosition, columnPosition));
    }

    private class SquareMock extends Square {

        SquareMock(final Position position, final Integer ChosenNumber) {
            super(position, ChosenNumber);
        }

        SquareMock(final Position position) {
            super(position);
        }

        @Override
        void setChosenNumber(final int number) {
            this.potentialNumbers = new int[1];
            this.potentialNumbers[0] = number;
        }

        public int[] getPotentialNumbers() {
            return this.potentialNumbers;
        }

        void setPotentialNumbers(final int[] potentialNumbers) {
            this.potentialNumbers = potentialNumbers;
        }
    }
}
