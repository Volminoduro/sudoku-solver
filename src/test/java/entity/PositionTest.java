package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void equals_WithSameObject_Return_True(){
        Position position = new Position(1, 0);
        assertEquals(position, position);
    }

    @Test
    void equals_WithDifferentClassObject_Return_False(){
        Position position = new Position(1, 0);
        assertNotEquals(position, new Object());
    }

    @Test
    void equals_WithDifferentRowPosition_Return_False(){
        Position position = new Position(1, 0);
        Position positionEquals = new Position(0, 0);
        assertNotEquals(position, positionEquals);
    }

    @Test
    void equals_WithDifferentColumnPosition_Return_False(){
        Position position = new Position(1, 0);
        Position positionEquals = new Position(1, 1);
        assertNotEquals(position, positionEquals);
    }

    @Test
    void equals_WithSameRowPositionAndSameColumnPosition_Return_True(){
        Position position = new Position(1, 0);
        Position positionEquals = new Position(1, 0);
        assertEquals(position, positionEquals);
    }

    @Test
    void compareTo_WithSameObject_Return_0(){
        Position position = new Position(1, 0);
        assertEquals(0, position.compareTo(position));
    }

    @Test
    void compareTo_SameRowAndSameColumnPosition_Return_0(){
        Position position = new Position(1, 0);
        Position positionCompared = new Position(1, 0);
        assertEquals(0, position.compareTo(positionCompared));
    }

    @Test
    void compareTo_SameRowAndCloserColumnPosition_Return_Positive(){
        Position position = new Position(1, 2);
        Position positionCompared = new Position(1, 0);
        assertEquals(1, position.compareTo(positionCompared));
    }

    @Test
    void compareTo_SameRowAndFurtherColumnPosition_Return_Negative(){
        Position position = new Position(1, 0);
        Position positionCompared = new Position(1, 2);
        assertEquals(-1, position.compareTo(positionCompared));
    }

    @Test
    void compareTo_CloserRowAndSameColumnPosition_Return_Positive(){
        Position position = new Position(1, 0);
        Position positionCompared = new Position(0, 0);
        assertEquals(1, position.compareTo(positionCompared));
    }

    @Test
    void compareTo_FurtherRowAndSameColumnPosition_Return_Negative(){
        Position position = new Position(1, 0);
        Position positionCompared = new Position(2, 0);
        assertEquals(-1, position.compareTo(positionCompared));
    }
}
