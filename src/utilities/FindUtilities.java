package utilities;

import entity.Position;
import starter.SudokuStarter;

public class FindUtilities {

    public static boolean findChoosenNumberFromZone(Position position, int choosenNumber){
        Position startOfZonePosition = FindUtilities.getStartOfZone(position);
        for(int rowInterator = 0; rowInterator< SudokuStarter.HEIGHT_SIDE; rowInterator++){
            for(int columnIterator = 0; columnIterator< SudokuStarter.WIDTH_SIDE; columnIterator++){
                if(SudokuStarter.getSquareFromPosition(new Position(startOfZonePosition.rowPosition+rowInterator, startOfZonePosition.columnPosition+columnIterator)).getChoosenNumber()==choosenNumber){
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean findPotentialNumberFromZone(Position position, int potentialNumber){
        Position startOfZonePosition = FindUtilities.getStartOfZone(position);
        for(int rowInterator = 0; rowInterator< SudokuStarter.HEIGHT_SIDE; rowInterator++){
            for(int columnIterator = 0; columnIterator< SudokuStarter.WIDTH_SIDE; columnIterator++){
                if(SudokuStarter.getSquareFromPosition(new Position(startOfZonePosition.rowPosition+rowInterator, startOfZonePosition.columnPosition+columnIterator)).containsPotentialNumber(potentialNumber)){
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean findChoosenNumberFromRow(Position position, int choosenNumber){
        for(int rowInterator = 0; rowInterator< SudokuStarter.HEIGHT_SIDE; rowInterator++){
            if(SudokuStarter.getSquareFromPosition(new Position(rowInterator, position.columnPosition)).getChoosenNumber()==choosenNumber){
                return true;
            }
        }
        return false;
    }

    public static boolean findPotentialNumberFromRow(Position position, int potentialNumber){
        for(int rowInterator = 0; rowInterator< SudokuStarter.HEIGHT_SIDE; rowInterator++){
            if(SudokuStarter.getSquareFromPosition(new Position(rowInterator, position.columnPosition)).containsPotentialNumber(potentialNumber)){
                return true;
            }
        }
        return false;
    }

    public static boolean findChoosenNumberFromColumn(Position position, int choosenNumber){
        for(int columnIterator = 0; columnIterator< SudokuStarter.HEIGHT_SIDE; columnIterator++){
            if(SudokuStarter.getSquareFromPosition(new Position(position.rowPosition, columnIterator)).getChoosenNumber()==choosenNumber){
                return true;
            }
        }
        return false;
    }

    public static boolean findPotentialNumberFromColumn(Position position, int potentialNumber){
        for(int columnIterator = 0; columnIterator< SudokuStarter.HEIGHT_SIDE; columnIterator++){
            if(SudokuStarter.getSquareFromPosition(new Position(position.rowPosition, columnIterator)).containsPotentialNumber(potentialNumber)){
                return true;
            }
        }
        return false;
    }

    public static Position getStartOfZone(Position position){
        int rowStartingZonePosition = position.rowPosition, columnStartingZonePosition = position.columnPosition;
        return new Position(rowStartingZonePosition - (rowStartingZonePosition % SudokuStarter.HEIGHT_SIDE), columnStartingZonePosition - (columnStartingZonePosition % SudokuStarter.WIDTH_SIDE));
    }
}
