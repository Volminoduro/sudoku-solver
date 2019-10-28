package entity;

import entity.exceptions.SameChosenNumberSquareException;
import entity.exceptions.SameUniquePotentialNumberSquareException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SquareTest {

    @Test
    void setChosenNumber_Should_ClearPotentialNumbers(){
        SquareMock square = new SquareMock(new Position(1, 0));
        List<Integer> potentialsNumbers = new ArrayList<>();
        potentialsNumbers.add(1);
        potentialsNumbers.add(2);
        square.setPotentialNumbers(potentialsNumbers);
        square.setChosenNumber(2);
        assertTrue(square.getPotentialNumbers().isEmpty());
        assertTrue(square.isValidNumber());
        assertEquals(2, square.chosenNumber);
    }

    @Test
    void deletePotentialNumbers_WithValidNumber_Return_False(){
        SquareMock square = new SquareMock(new Position(1, 0), 1);
        assertFalse(square.deletePotentialNumbers(1));
    }

    @Test
    void deletePotentialNumbers_WithPotentialNumberExists_Return_True(){
        Square square = new Square(new Position(1, 0));
        assertTrue(square.deletePotentialNumbers(1));
    }

    @Test
    void deletePotentialNumbers_WithUniquePotentialNumberInTheEnd_Return_False(){
        SquareMock square = new SquareMock(new Position(1, 0));
        List<Integer> potentialsNumbers = new ArrayList<>();
        potentialsNumbers.add(1);
        potentialsNumbers.add(2);
        square.setPotentialNumbers(potentialsNumbers);
        assertFalse(square.deletePotentialNumbers(2));
    }

    @Test
    void deletePotentialNumbers_WithUniquePotentialNumberInTheEnd_Return_SquareWithUniquePotentialNumberAsChosenNumber(){
        SquareMock square = new SquareMock(new Position(1, 0));
        List<Integer> potentialsNumbers = new ArrayList<>();
        potentialsNumbers.add(1);
        potentialsNumbers.add(2);
        square.setPotentialNumbers(potentialsNumbers);
        square.deletePotentialNumbers(2);
        assertTrue(square.isValidNumber());
        assertEquals(1, square.chosenNumber);
    }

    @Test
    void deletePotentialNumbers_WithOnlyPossiblePotentialNumber_Return_Exception(){
        SquareMock square = new SquareMock(new Position(1, 0));
        square.setPotentialNumbers(new ArrayList<>(1));
        assertFalse(square.deletePotentialNumbers(1));
    }

    @Test
    void deletePotentialNumbers_NotPossibleInPotentialNumberAndValidNumber_Return_Exception(){
        Square square = new Square(new Position(1, 0));
        assertFalse(square.deletePotentialNumbers(Integer.MIN_VALUE));
    }

    @Test
    void equals_WithSameObject_Return_True(){
        Square square = new Square(new Position(1, 0));
        assertEquals(square, square);
    }

    @Test
    void equals_WithDifferentClassObject_Return_False(){
        Square square = new Square(new Position(1, 0));
        assertNotEquals(square, new Object());
    }

    @Test
    void equals_WithDifferentPosition_Return_False(){
        Square square = new Square(new Position(1, 0));
        Square squareEquals = new Square(new Position(0, 0));
        assertNotEquals(square, squareEquals);
    }

    @Test
    void equals_WithSamePosition_Return_True(){
        Square square = new Square(new Position(1, 0));
        Square squareEquals = new Square(new Position(1, 0));
        assertEquals(square, squareEquals);
    }

    @Test
    void compareTo_WithSameObject_Return_0(){
        SquareMock squareMock = new SquareMock(new Position(1, 0), 5);
        assertEquals(0, squareMock.compareTo(squareMock));
    }

    @Test
    void compareTo_ChosenNumberSquare_SamePositionSquare_Return_0(){
        SquareMock squareMock = new SquareMock(new Position(1, 0), 5);
        SquareMock squareCompared = new SquareMock(new Position(1, 0), 4);
        assertEquals(0, squareMock.compareTo(squareCompared));
    }

    @Test
    void compareTo_PotentialNumberSquare_SamePositionSquare_Return_0(){
        SquareMock squareMock = generateSquareMockWithPotentialNumbers(1, 0);
        SquareMock squareCompared = generateSquareMockWithPotentialNumbers(1, 0);
        assertEquals(0, squareMock.compareTo(squareCompared));
    }

    @Test
    void compareTo_PotentialNumberSquare_ChosenNumberSquare_Return_Negative(){
        Square square = new Square(new Position(1, 0));
        SquareMock squareCompared = new SquareMock(new Position(2, 0), 5);
        assertEquals(-1, square.compareTo(squareCompared));
    }

    /**
     * Chosen Numbers square's checks
     */

    @Test
    void compareTo_ChosenNumberSquare_FurtherPositionAndChosenNumberSquare_Return_Negative(){
        Square squareMock = new Square(new Position(1, 0));
        SquareMock squareCompared = generateSquareMockWithChosenNumbers(2, 0);
        assertEquals(-1, squareMock.compareTo(squareCompared));
    }

    @Test
    void compareTo_ChosenNumberSquare_CloserPositionAndChosenNumberSquare_Return_Positive(){
        SquareMock squareMock = new SquareMock(new Position(1, 0), 5);
        SquareMock squareCompared = generateSquareMockWithChosenNumbers(0, 0);
        assertEquals(1, squareMock.compareTo(squareCompared));
    }

    @Test
    void compareTo_ChosenNumberSquare_FurtherPositionAndPotentialNumberSquare_Return_Positive(){
        SquareMock squareMock = new SquareMock(new Position(1, 0), 5);
        SquareMock squareCompared = generateSquareMockWithPotentialNumbers(5, 0);
        assertEquals(1, squareMock.compareTo(squareCompared));
    }

    @Test
    void compareTo_ChosenNumberSquare_CloserPositionAndPotentialNumberSquare_Return_Positive(){
        SquareMock squareMock = new SquareMock(new Position(1, 0), 5);
        SquareMock squareCompared = generateSquareMockWithPotentialNumbers(0, 5);
        assertEquals(1, squareMock.compareTo(squareCompared));
    }

    /**
     * Potential numbers square's checks
     */

    @Test
    void compareTo_PotentialNumberSquare_CloserPositionAndSameQuantityPotentialNumberSquare_Return_Positive(){
        SquareMock squareMock = generateSquareMockWithPotentialNumbers(1, 0);
        SquareMock squareCompared = generateSquareMockWithPotentialNumbers(0, 5);
        assertEquals(1, squareMock.compareTo(squareCompared));
    }

    @Test
    void compareTo_PotentialNumberSquare_FurtherPositionAndSameQuantityPotentialNumberSquare_Return_Negative(){
        SquareMock squareMock = generateSquareMockWithPotentialNumbers(1, 0);
        SquareMock squareCompared = generateSquareMockWithPotentialNumbers(2, 5);
        assertEquals(-1, squareMock.compareTo(squareCompared));
    }

    @Test
    void compareTo_LessPotentialNumberSquare_AndMoreQuantityPotentialNumberSquare_Return_Negative(){
        SquareMock squareMock = generateSquareMockWithPotentialNumbers(1, 0);
        List<Integer> potentialNumbers = squareMock.getPotentialNumbers();
        potentialNumbers.remove(1);
        squareMock.setPotentialNumbers(potentialNumbers);

        SquareMock squareCompared = generateSquareMockWithPotentialNumbers(2, 5);
        assertEquals(-1, squareMock.compareTo(squareCompared));
    }

    @Test
    void compareTo_MorePotentialNumberSquare_AndLessQuantityPotentialNumberSquare_Return_Positive(){
        SquareMock squareMock = generateSquareMockWithPotentialNumbers(1, 0);

        SquareMock squareCompared = generateSquareMockWithPotentialNumbers(2, 5);
        List<Integer> potentialNumbers = squareCompared.getPotentialNumbers();
        potentialNumbers.remove(1);
        squareCompared.setPotentialNumbers(potentialNumbers);

        assertEquals(1, squareMock.compareTo(squareCompared));
    }

    /**
     * Exceptions checks
     */

    @Test
    void compareTo_SameChosenNumber_Return_Exception(){
        SquareMock squareMock = generateSquareMockWithChosenNumbers(1, 0);
        SquareMock squareCompared = generateSquareMockWithChosenNumbers(2, 5);
        assertThrows(SameChosenNumberSquareException.class, () -> squareMock.compareTo(squareCompared));
    }

    @Test
    void compareTo_SameUniquePotentialNumber_Return_Exception(){
        SquareMock squareMock = generateSquareMockWithUniquePotentialNumbers(1, 0);
        SquareMock squareCompared = generateSquareMockWithUniquePotentialNumbers(2, 5);
        assertThrows(SameUniquePotentialNumberSquareException.class, () -> squareMock.compareTo(squareCompared));
    }

    /**
     * TODO : Make reference to realAttribute, in case of rename
     * This will always generate a  SquareMock with 9 as @see Square#ChosenNumber Chosen number
     * @param rowPosition rowPosition used for Position creating for SquareMock
     * @param columnPosition columnPosition used for Position creating for SquareMock
     * @return SquareMock with a Position using rowPosition and columnPosition, and 9 as Chosen number
     */
    private SquareMock generateSquareMockWithChosenNumbers(Integer rowPosition, Integer columnPosition){
        return new SquareMock(new Position(rowPosition, columnPosition), 9);
    }

    private SquareMock generateSquareMockWithPotentialNumbers(Integer rowPosition, Integer columnPosition){
        return new SquareMock(new Position(rowPosition, columnPosition));
    }

    private SquareMock generateSquareMockWithUniquePotentialNumbers(Integer rowPosition, Integer columnPosition){
        SquareMock squareMock = new SquareMock(new Position(rowPosition, columnPosition));
        ArrayList potentialNumbers = new ArrayList();
        potentialNumbers.add(1);
        squareMock.setPotentialNumbers(potentialNumbers);

        return squareMock;
    }

    private class SquareMock extends Square {

        SquareMock(Position position, Integer ChosenNumber){
            super(position, ChosenNumber);
        }
        SquareMock(Position position) {
            super(position);
        }

        @Override
        void setChosenNumber(Integer number){
            this.chosenNumber = number;
            potentialNumbers.clear();
        }

        public List<Integer> getPotentialNumbers(){
            return this.potentialNumbers;
        }

        void setPotentialNumbers(List<Integer> potentialNumbers){
            this.potentialNumbers = potentialNumbers;
        }
    }
}
