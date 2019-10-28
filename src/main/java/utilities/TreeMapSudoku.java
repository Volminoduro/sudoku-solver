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
            for(int iteratorWidth = 0; iteratorWidth< this.WIDTH_SIDE * this.WIDTH_SIDE; iteratorWidth++){
                Position position = new Position(iteratorHeight, iteratorWidth);
                this.put(new Square(position), position);
            }
        }
    }

    public void putAndReplace(Square squareToPut){
        this.putAndReplace(squareToPut, squareToPut.getPosition());
    }

    // TODO : Remove doesn't seems to work
    private void putAndReplace(Square squareToPut, Position positionToReplace){
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

    private Square getKeyFromValue(Position positionToFound){
        for (Object o : this.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            if (positionToFound.equals(pair.getValue())) {
                return (Square) pair.getKey();
            }
        }
        return null;
    }

    private Collection<Square> getSquaresFromRow(Position position){
        Collection<Square> squares = new LinkedHashSet<>();
        for(int rowIterator = 0; rowIterator< this.HEIGHT_SIDE*this.HEIGHT_SIDE; rowIterator++){
            squares.add(this.getKeyFromValue(new Position(rowIterator, position.columnPosition)));
        }
        return squares;
    }

    private Collection<Square> getSquaresFromColumn(Position position){
        Collection<Square> squares = new LinkedHashSet<>();
        for(int columnIterator = 0; columnIterator< this.WIDTH_SIDE*this.WIDTH_SIDE; columnIterator++){
            squares.add(this.getKeyFromValue(new Position(position.rowPosition, columnIterator)));
        }
        return squares;
    }

    private Collection<Square> getSquaresFromZone(Position position){
        Collection<Square> squares = new LinkedHashSet<>();
        Position startOfZonePosition = this.getStartOfZone(position);
        for(int rowIterator = 0; rowIterator< this.HEIGHT_SIDE; rowIterator++){
            for(int columnIterator = 0; columnIterator< this.WIDTH_SIDE; columnIterator++){
                squares.add(this.getKeyFromValue(new Position(startOfZonePosition.rowPosition+rowIterator, startOfZonePosition.columnPosition+columnIterator)));
            }
        }
        return squares;
    }

    private Position getStartOfZone(Position position){
        int rowStartingZonePosition = position.rowPosition, columnStartingZonePosition = position.columnPosition;
        return new Position(rowStartingZonePosition - (rowStartingZonePosition % this.HEIGHT_SIDE), columnStartingZonePosition - (columnStartingZonePosition % this.WIDTH_SIDE));
    }

    public boolean isSudokuComplete(){
        final Square firstSquare = (Square) this.firstKey();
        return firstSquare.isValidNumber();
    }

    @Override
    public String toString(){
        StringBuilder textToDisplay = new StringBuilder();
        // TODO : Use of entrySet would be prefered
        for(int rowIterator = 0; rowIterator< this.HEIGHT_SIDE * this.HEIGHT_SIDE; rowIterator++){
            for(int columnIterator = 0; columnIterator< this.WIDTH_SIDE * this.WIDTH_SIDE; columnIterator++){
                Square actualSquare = this.getKeyFromValue(new Position(rowIterator, columnIterator));
                textToDisplay.append(actualSquare.getChosenNumber()).append(", ");
            }
            textToDisplay.append("\n");
        }
        return textToDisplay.toString();
    }

    public int getHEIGHT_SIDE() {
        return HEIGHT_SIDE;
    }

    public int getWIDTH_SIDE() {
        return WIDTH_SIDE;
    }
}
