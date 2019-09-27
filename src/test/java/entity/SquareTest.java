package entity;

import entity.exceptions.SameChoosenNumberSquareException;
import entity.exceptions.SameUniquePotentialNumberSquareException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SquareTest {

    @Test
    public void setChoosenNumber_Should_ClearPotentialNumbers(){
        SquareMock square = new SquareMock(new Position(1, 0));
        List<Integer> potentialsNumbers = new ArrayList<>();
        potentialsNumbers.add(1);
        potentialsNumbers.add(2);
        square.setPotentialNumbers(potentialsNumbers);
        square.setChoosenNumber(2);
        assertTrue(square.getPotentialNumbers().isEmpty());
        assertTrue(square.isValidNumber());
        assertEquals(2, square.choosenNumber);
    }

    @Test
    public void deletePotentialNumbers_WithValidNumber_Return_False(){
        SquareMock square = new SquareMock(new Position(1, 0), 1);
        assertFalse(square.deletePotentialNumbers(1));
    }

    @Test
    public void deletePotentialNumbers_WithPotentialNumberExists_Return_True(){
        Square square = new Square(new Position(1, 0));
        assertTrue(square.deletePotentialNumbers(1));
    }

    @Test
    public void deletePotentialNumbers_WithUniquePotentialNumberInTheEnd_Return_False(){
        SquareMock square = new SquareMock(new Position(1, 0));
        List<Integer> potentialsNumbers = new ArrayList<>();
        potentialsNumbers.add(1);
        potentialsNumbers.add(2);
        square.setPotentialNumbers(potentialsNumbers);
        assertFalse(square.deletePotentialNumbers(2));
    }

    @Test
    public void deletePotentialNumbers_WithUniquePotentialNumberInTheEnd_Return_SquareWithUniquePotentialNumberAsChoosenNumber(){
        SquareMock square = new SquareMock(new Position(1, 0));
        List<Integer> potentialsNumbers = new ArrayList<>();
        potentialsNumbers.add(1);
        potentialsNumbers.add(2);
        square.setPotentialNumbers(potentialsNumbers);
        square.deletePotentialNumbers(2);
        assertTrue(square.isValidNumber());
        assertEquals(1, square.choosenNumber);
    }

    @Test
    public void deletePotentialNumbers_WithOnlyPossiblePotentialNumber_Return_Exception(){
        SquareMock square = new SquareMock(new Position(1, 0));
        square.setPotentialNumbers(new ArrayList<>(1));
        assertFalse(square.deletePotentialNumbers(1));
    }

    @Test
    public void deletePotentialNumbers_NotPossibleInPotentialNumberAndValidNumber_Return_Exception(){
        Square square = new Square(new Position(1, 0));
        assertFalse(square.deletePotentialNumbers(Integer.MIN_VALUE));
    }

    @Test
    public void equals_WithSameObject_Return_True(){
        Square square = new Square(new Position(1, 0));
        assertTrue(square.equals(square));
    }

    @Test
    public void equals_WithDifferentClassObject_Return_False(){
        Square square = new Square(new Position(1, 0));
        assertFalse(square.equals(new Object()));
    }

    @Test
    public void equals_WithDifferentPosition_Return_False(){
        Square square = new Square(new Position(1, 0));
        Square squareEquals = new Square(new Position(0, 0));
        assertFalse(square.equals(squareEquals));
    }

    @Test
    public void equals_WithSamePosition_Return_True(){
        Square square = new Square(new Position(1, 0));
        Square squareEquals = new Square(new Position(1, 0));
        assertTrue(square.equals(squareEquals));
    }

    @Test
    public void compareTo_WithSameObject_Return_0(){
        SquareMock squareMock = new SquareMock(new Position(1, 0), 5);
        assertEquals(0, squareMock.compareTo(squareMock));
    }

    @Test
    public void compareTo_ChoosenNumberSquare_SamePositionSquare_Return_0(){
        SquareMock squareMock = new SquareMock(new Position(1, 0), 5);
        SquareMock squareCompared = new SquareMock(new Position(1, 0), 4);
        assertEquals(0, squareMock.compareTo(squareCompared));
    }

    @Test
    public void compareTo_PotentialNumberSquare_SamePositionSquare_Return_0(){
        SquareMock squareMock = generateSquareMockWithPotentialNumbers(1, 0);
        SquareMock squareCompared = generateSquareMockWithPotentialNumbers(1, 0);
        assertEquals(0, squareMock.compareTo(squareCompared));
    }

    @Test
    public void compareTo_PotentialNumberSquare_ChoosenNumberSquare_Return_Negative(){
        Square square = new Square(new Position(1, 0));
        SquareMock squareCompared = new SquareMock(new Position(2, 0), 5);
        assertEquals(-1, square.compareTo(squareCompared));
    }

    /**
     * Choosen Numbers square's checks
     */

    @Test
    public void compareTo_ChoosenNumberSquare_FurtherPositionAndChoosenNumberSquare_Return_Negative(){
        Square squareMock = new Square(new Position(1, 0));
        SquareMock squareCompared = generateSquareMockWithChoosenNumbers(2, 0);
        assertEquals(-1, squareMock.compareTo(squareCompared));
    }

    @Test
    public void compareTo_ChoosenNumberSquare_CloserPositionAndChoosenNumberSquare_Return_Positive(){
        SquareMock squareMock = new SquareMock(new Position(1, 0), 5);
        SquareMock squareCompared = generateSquareMockWithChoosenNumbers(0, 0);
        assertEquals(1, squareMock.compareTo(squareCompared));
    }

    @Test
    public void compareTo_ChoosenNumberSquare_FurtherPositionAndPotentialNumberSquare_Return_Positive(){
        SquareMock squareMock = new SquareMock(new Position(1, 0), 5);
        SquareMock squareCompared = generateSquareMockWithPotentialNumbers(5, 0);
        assertEquals(1, squareMock.compareTo(squareCompared));
    }

    @Test
    public void compareTo_ChoosenNumberSquare_CloserPositionAndPotentialNumberSquare_Return_Positive(){
        SquareMock squareMock = new SquareMock(new Position(1, 0), 5);
        SquareMock squareCompared = generateSquareMockWithPotentialNumbers(0, 5);
        assertEquals(1, squareMock.compareTo(squareCompared));
    }

    /**
     * Potential numbers square's checks
     */

    @Test
    public void compareTo_PotentialNumberSquare_CloserPositionAndSameQuantityPotentialNumberSquare_Return_Positive(){
        SquareMock squareMock = generateSquareMockWithPotentialNumbers(1, 0);
        SquareMock squareCompared = generateSquareMockWithPotentialNumbers(0, 5);
        assertEquals(1, squareMock.compareTo(squareCompared));
    }

    @Test
    public void compareTo_PotentialNumberSquare_FurtherPositionAndSameQuantityPotentialNumberSquare_Return_Negative(){
        SquareMock squareMock = generateSquareMockWithPotentialNumbers(1, 0);
        SquareMock squareCompared = generateSquareMockWithPotentialNumbers(2, 5);
        assertEquals(-1, squareMock.compareTo(squareCompared));
    }

    @Test
    public void compareTo_LessPotentialNumberSquare_AndMoreQuantityPotentialNumberSquare_Return_Negative(){
        SquareMock squareMock = generateSquareMockWithPotentialNumbers(1, 0);
        List<Integer> potentialNumbers = squareMock.getPotentialNumbers();
        potentialNumbers.remove(1);
        squareMock.setPotentialNumbers(potentialNumbers);

        SquareMock squareCompared = generateSquareMockWithPotentialNumbers(2, 5);
        assertEquals(-1, squareMock.compareTo(squareCompared));
    }

    @Test
    public void compareTo_MorePotentialNumberSquare_AndLessQuantityPotentialNumberSquare_Return_Positive(){
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
    public void compareTo_SameChoosenNumber_Return_Exception(){
        SquareMock squareMock = generateSquareMockWithChoosenNumbers(1, 0);
        SquareMock squareCompared = generateSquareMockWithChoosenNumbers(2, 5);
        assertThrows(SameChoosenNumberSquareException.class, () -> {squareMock.compareTo(squareCompared);});
    }

    @Test
    public void compareTo_SameUniquePotentialNumber_Return_Exception(){
        SquareMock squareMock = generateSquareMockWithUniquePotentialNumbers(1, 0);
        SquareMock squareCompared = generateSquareMockWithUniquePotentialNumbers(2, 5);
        assertThrows(SameUniquePotentialNumberSquareException.class, () -> {squareMock.compareTo(squareCompared);});
    }

    /**
     * TODO : Make reference to realAttribute, in case of rename
     * This will always generate a  SquareMock with 9 as @see Square#choosenNumber choosen number
     * @param rowPosition rowPosition used for Position creating for SquareMock
     * @param columnPosition columnPosition used for Position creating for SquareMock
     * @return SquareMock with a Position using rowPosition and columnPosition, and 9 as choosen number
     */
    private SquareMock generateSquareMockWithChoosenNumbers(Integer rowPosition, Integer columnPosition){
        return new SquareMock(new Position(rowPosition, columnPosition), 9);
    }

    private SquareMock generateSquareMockWithPotentialNumbers(Integer rowPosition, Integer columnPosition){
        return new SquareMock(new Position(rowPosition, columnPosition));
    }

    private SquareMock generateSquareMockWithUniquePotentialNumbers(Integer rowPosition, Integer columnPosition){
        SquareMock squareMock = new SquareMock(new Position(rowPosition, columnPosition));
        List<Integer> potentialNumbers = new ArrayList();
        potentialNumbers.add(1);
        squareMock.setPotentialNumbers(potentialNumbers);

        return squareMock;
    }

    private class SquareMock extends Square {

        public SquareMock(Position position, Integer choosenNumber){
            super(position, choosenNumber);
        }
        public SquareMock(Position position) {
            super(position);
        }

        @Override
        public void setChoosenNumber(Integer chiffre){
            this.choosenNumber = chiffre;
            potentialNumbers.clear();
        }

        public List<Integer> getPotentialNumbers(){
            return this.potentialNumbers;
        }

        public void setPotentialNumbers(List<Integer> potentialNumbers){
            this.potentialNumbers = potentialNumbers;
        }
    }
}
