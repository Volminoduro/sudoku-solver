package utilities;

import entity.Position;
import starter.SudokuStarter;

public class DeletionUtilities {

    public static void deletePotentialNumberFromZone(Position position, int potentialNumber){
        Position startOfZonePosition = FindUtilities.getStartOfZone(position);
        for(int rowInterator = 0; rowInterator< SudokuStarter.HEIGHT_SIDE; rowInterator++){
            for(int columnIterator = 0; columnIterator< SudokuStarter.WIDTH_SIDE; columnIterator++){
                SudokuStarter.getSquareFromPosition(new Position(startOfZonePosition.rowPosition+rowInterator, startOfZonePosition.columnPosition+columnIterator)).deleteFromPotentialNumbers(potentialNumber);
            }
        }
    }

    public static void deletePotentialNumberFromRow(Position position, int potentialNumber){
        for(int rowInterator = 0; rowInterator< SudokuStarter.HEIGHT_SIDE; rowInterator++){
            SudokuStarter.getSquareFromPosition(new Position(rowInterator, position.columnPosition)).deleteFromPotentialNumbers(potentialNumber);
        }
    }

    public static void deletePotentialNumberFromColumn(Position position, int potentialNumber){
        for(int columnIterator = 0; columnIterator< SudokuStarter.HEIGHT_SIDE; columnIterator++){
            SudokuStarter.getSquareFromPosition(new Position(position.rowPosition, columnIterator)).deleteFromPotentialNumbers(potentialNumber);
        }
    }

    public static void deleteInitialPotentialNumberFromZone(Position position, int potentialNumber){
        Position startOfZonePosition = FindUtilities.getStartOfZone(position);
        for(int rowInterator = 0; rowInterator< SudokuStarter.HEIGHT_SIDE; rowInterator++){
            for(int columnIterator = 0; columnIterator< SudokuStarter.WIDTH_SIDE; columnIterator++){
                SudokuStarter.getSquareFromPosition(new Position(startOfZonePosition.rowPosition+rowInterator, startOfZonePosition.columnPosition+columnIterator)).deleteFromPotentialNumbers(potentialNumber);
            }
        }
    }

    public static void deleteInitialPotentialNumberFromRow(Position position, int potentialNumber){
        for(int rowInterator = 0; rowInterator< SudokuStarter.HEIGHT_SIDE; rowInterator++){
            SudokuStarter.getSquareFromPosition(new Position(rowInterator, position.columnPosition)).deleteFromPotentialNumbers(potentialNumber);
        }
    }

    public static void deleteInitialPotentialNumberFromColumn(Position position, int potentialNumber){
        for(int columnIterator = 0; columnIterator< SudokuStarter.HEIGHT_SIDE; columnIterator++){
            SudokuStarter.getSquareFromPosition(new Position(position.rowPosition, columnIterator)).deleteFromPotentialNumbers(potentialNumber);
        }
    }
}
