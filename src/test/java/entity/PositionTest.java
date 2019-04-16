package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    public void equals_WithSameObject_Return_True(){
        Position position = new Position(1, 0);
        assertTrue(position.equals(position));
    }

    @Test
    public void equals_WithDifferentClassObject_Return_False(){
        Position position = new Position(1, 0);
        assertFalse(position.equals(new Object()));
    }

    @Test
    public void equals_WithDifferentRowPosition_Return_False(){
        Position position = new Position(1, 0);
        Position positionEquals = new Position(0, 0);
        assertFalse(position.equals(positionEquals));
    }

    @Test
    public void equals_WithDifferentColumnPosition_Return_False(){
        Position position = new Position(1, 0);
        Position positionEquals = new Position(1, 1);
        assertFalse(position.equals(positionEquals));
    }

    @Test
    public void equals_WithSameRowPositionAndSameColumnPosition_Return_True(){
        Position position = new Position(1, 0);
        Position positionEquals = new Position(1, 0);
        assertTrue(position.equals(positionEquals));
    }

    @Test
    public void compareTo_WithSameObject_Return_0(){
        Position position = new Position(1, 0);
        assertEquals(0, position.compareTo(position));
    }

    @Test
    public void compareTo_SameRowAndSameColumnPosition_Return_0(){
        Position position = new Position(1, 0);
        Position positionCompared = new Position(1, 0);
        assertEquals(0, position.compareTo(positionCompared));
    }

    @Test
    public void compareTo_SameRowAndCloserColumnPosition_Return_Positive(){
        Position position = new Position(1, 2);
        Position positionCompared = new Position(1, 0);
        assertEquals(1, position.compareTo(positionCompared));
    }

    @Test
    public void compareTo_SameRowAndFurtherColumnPosition_Return_Negative(){
        Position position = new Position(1, 0);
        Position positionCompared = new Position(1, 2);
        assertEquals(-1, position.compareTo(positionCompared));
    }

    @Test
    public void compareTo_CloserRowAndSameColumnPosition_Return_Positive(){
        Position position = new Position(1, 0);
        Position positionCompared = new Position(0, 0);
        assertEquals(1, position.compareTo(positionCompared));
    }

    @Test
    public void compareTo_FurtherRowAndSameColumnPosition_Return_Negative(){
        Position position = new Position(1, 0);
        Position positionCompared = new Position(2, 0);
        assertEquals(-1, position.compareTo(positionCompared));
    }
}
