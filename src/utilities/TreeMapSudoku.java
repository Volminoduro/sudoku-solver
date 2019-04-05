package utilities;

import entity.Position;
import entity.Square;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapSudoku extends TreeMap {

    private final int HEIGHT_SIDE;
    private final int WIDTH_SIDE;

    public TreeMapSudoku(int heightSide, int widthSide){
        HEIGHT_SIDE = heightSide;
        WIDTH_SIDE = widthSide;
    }

    @Override
    public String toString(){
        String textToDisplay = "";
        // TODO : Use of entrySet would be prefered
        // TODO : A real Utilities class for displaying purpose
        for(int rowIterator = 0; rowIterator< this.HEIGHT_SIDE * this.HEIGHT_SIDE; rowIterator++){
            for(int columnIterator = 0; columnIterator< this.WIDTH_SIDE * this.WIDTH_SIDE; columnIterator++){
                Square actualSquare = (Square) this.getKeyFromValue(new Position(rowIterator, columnIterator));
                textToDisplay += actualSquare.getChoosenNumber()+", ";
            }
            textToDisplay +="\n";
        }
        return textToDisplay;
    }

    public boolean isSudokuComplete(){
        final Square firstSquare = (Square) this.firstKey();
        return firstSquare.isValidNumber();
    }

    public Square getKeyFromValue(Position positionToFound){
        Iterator it = this.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if(positionToFound.equals(pair.getValue())){
                return (Square) pair.getKey();
            }
        }
        return null;
    }

    // TODO : Remove doesn't seems to work
    public void putAndReplace(Square squareToPut, Position positionToReplace){
        this.remove(this.getKeyFromValue(positionToReplace));
        this.put(squareToPut, positionToReplace);
    }

    public Position getStartOfZone(Position position){
        int rowStartingZonePosition = position.rowPosition, columnStartingZonePosition = position.columnPosition;
        return new Position(rowStartingZonePosition - (rowStartingZonePosition % this.HEIGHT_SIDE), columnStartingZonePosition - (columnStartingZonePosition % this.WIDTH_SIDE));
    }
}
