package utilities;

import entity.Position;
import entity.Square;

import java.util.*;
import java.util.stream.Collectors;

public class TreeMapSudoku extends TreeMap {

    private final int HEIGHT_SIDE;
    private final int WIDTH_SIDE;

    public TreeMapSudoku(int heightSide, int widthSide){
        HEIGHT_SIDE = heightSide;
        WIDTH_SIDE = widthSide;
    }

    public void initializeEmptySudoku(){
        for(int iteratorHeight = 0; iteratorHeight< this.HEIGHT_SIDE * this.HEIGHT_SIDE; iteratorHeight++){
            for(int iteratorWidht = 0; iteratorWidht< this.WIDTH_SIDE * this.WIDTH_SIDE; iteratorWidht++){
                Position position = new Position(iteratorHeight, iteratorWidht);
                this.put(new Square(position), position);
            }
        }
    }

    public void putAndReplace(Square squareToPut){
        this.putAndReplace(squareToPut, squareToPut.getPosition());
    }

    // TODO : Remove doesn't seems to work
    public void putAndReplace(Square squareToPut, Position positionToReplace){
        this.remove(this.getKeyFromValue(positionToReplace));
        this.put(squareToPut, positionToReplace);
    }

    public Set<Square> deletePotentialNumberFromRow(Position position, int potentialNumber){
        return this.getSquaresFromRow(position).stream().filter(square -> square.deletePotentialNumbers(potentialNumber)).collect(Collectors.toSet());
    }

    public Set<Square> deletePotentialNumberFromColumn(Position position, int potentialNumber){
        return this.getSquaresFromColumn(position).stream().filter(square -> square.deletePotentialNumbers(potentialNumber)).collect(Collectors.toSet());
    }

    public Set<Square> deletePotentialNumberFromZone(Position position, int potentialNumber){
        return this.getSquaresFromZone(position).stream().filter(square -> square.deletePotentialNumbers(potentialNumber)).collect(Collectors.toSet());
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

    public Collection<Square> getSquaresFromRow(Position position){
        Collection<Square> squares = new LinkedHashSet<>();
        for(int rowIterator = 0; rowIterator< this.HEIGHT_SIDE*this.HEIGHT_SIDE; rowIterator++){
            squares.add(this.getKeyFromValue(new Position(rowIterator, position.columnPosition)));
        }
        return squares;
    }

    public Collection<Square> getSquaresFromColumn(Position position){
        Collection<Square> squares = new LinkedHashSet<>();
        for(int columnIterator = 0; columnIterator< this.WIDTH_SIDE*this.WIDTH_SIDE; columnIterator++){
            squares.add(this.getKeyFromValue(new Position(position.rowPosition, columnIterator)));
        }
        return squares;
    }

    public Collection<Square> getSquaresFromZone(Position position){
        Collection<Square> squares = new LinkedHashSet<>();
        Position startOfZonePosition = this.getStartOfZone(position);
        for(int rowIterator = 0; rowIterator< this.HEIGHT_SIDE; rowIterator++){
            for(int columnIterator = 0; columnIterator< this.WIDTH_SIDE; columnIterator++){
                squares.add(this.getKeyFromValue(new Position(startOfZonePosition.rowPosition+rowIterator, startOfZonePosition.columnPosition+columnIterator)));
            }
        }
        return squares;
    }

    public Position getStartOfZone(Position position){
        int rowStartingZonePosition = position.rowPosition, columnStartingZonePosition = position.columnPosition;
        return new Position(rowStartingZonePosition - (rowStartingZonePosition % this.HEIGHT_SIDE), columnStartingZonePosition - (columnStartingZonePosition % this.WIDTH_SIDE));
    }

    public boolean isSudokuComplete(){
        final Square firstSquare = (Square) this.firstKey();
        return firstSquare.isValidNumber();
    }

    @Override
    public String toString(){
        String textToDisplay = "";
        // TODO : Use of entrySet would be prefered
        for(int rowIterator = 0; rowIterator< this.HEIGHT_SIDE * this.HEIGHT_SIDE; rowIterator++){
            for(int columnIterator = 0; columnIterator< this.WIDTH_SIDE * this.WIDTH_SIDE; columnIterator++){
                Square actualSquare = this.getKeyFromValue(new Position(rowIterator, columnIterator));
                textToDisplay += actualSquare.getChoosenNumber()+", ";
            }
            textToDisplay +="\n";
        }
        return textToDisplay;
    }

    public int getHEIGHT_SIDE() {
        return HEIGHT_SIDE;
    }

    public int getWIDTH_SIDE() {
        return WIDTH_SIDE;
    }
}
