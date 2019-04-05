package entity;

import entity.exceptions.SquareException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SquareTest {

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

    /**
     * Choosen Numbers square's checks
     */

    @Test
    public void compareTo_ChoosenNumberSquare_FurtherPositionAndChoosenNumberSquare_Return_Negative(){
        SquareMock squareMock = new SquareMock(new Position(1, 0), 5);
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
        squareMock.potentialNumbers.remove(1);
        SquareMock squareCompared = generateSquareMockWithPotentialNumbers(2, 5);
        assertEquals(-1, squareMock.compareTo(squareCompared));
    }

    @Test
    public void compareTo_MorePotentialNumberSquare_AndLessQuantityPotentialNumberSquare_Return_Positive(){
        SquareMock squareMock = generateSquareMockWithPotentialNumbers(1, 0);
        SquareMock squareCompared = generateSquareMockWithPotentialNumbers(2, 5);
        squareCompared.potentialNumbers.remove(1);
        assertEquals(1, squareMock.compareTo(squareCompared));
    }

    /**
     * Exceptions checks
     */

    @Test
    public void compareTo_SameChoosenNumber_Return_Exception(){
        SquareMock squareMock = generateSquareMockWithChoosenNumbers(1, 0);
        SquareMock squareCompared = generateSquareMockWithChoosenNumbers(2, 5);
        assertThrows(SquareException.class, () -> {squareMock.compareTo(squareCompared);});
    }

    @Test
    public void compareTo_SameUniquePotentialNumber_Return_Exception(){
        SquareMock squareMock = generateSquareMockWithUniquePotentialNumbers(1, 0);
        SquareMock squareCompared = generateSquareMockWithUniquePotentialNumbers(2, 5);
        assertThrows(SquareException.class, () -> {squareMock.compareTo(squareCompared);});
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
        squareMock.potentialNumbers = new ArrayList();
        squareMock.potentialNumbers.add(1);
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
        public void setInitialChoosenNumber(Integer chiffre){
            this.choosenNumber = chiffre;
            potentialNumbers.clear();
        }
    }
}
