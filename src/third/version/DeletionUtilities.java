package third.version;

public class DeletionUtilities {

    public static void deletePotentialNumberFromZone(Position position, int potentialNumber){
        Position startOfZonePosition = FindUtilities.getStartOfZone(position);
        for(int rowInterator = 0; rowInterator< Main.HEIGHT_SIDE; rowInterator++){
            for(int columnIterator = 0; columnIterator< Main.WIDTH_SIDE; columnIterator++){
                Main.getSquareFromPosition(new Position(startOfZonePosition.rowPosition+rowInterator, startOfZonePosition.columnPosition+columnIterator)).deletePotentialNumberFromPotentialNumbers(potentialNumber);
            }
        }
    }

    public static void deletePotentialNumberFromRow(Position position, int potentialNumber){
        for(int rowInterator = 0; rowInterator< Main.HEIGHT_SIDE; rowInterator++){
            Main.getSquareFromPosition(new Position(rowInterator, position.columnPosition)).deletePotentialNumberFromPotentialNumbers(potentialNumber);
        }
    }

    public static void deletePotentialNumberFromColumn(Position position, int potentialNumber){
        for(int columnIterator = 0; columnIterator< Main.HEIGHT_SIDE; columnIterator++){
            Main.getSquareFromPosition(new Position(position.rowPosition, columnIterator)).deletePotentialNumberFromPotentialNumbers(potentialNumber);
        }
    }

    public static void deleteInitialPotentialNumberFromZone(Position position, int potentialNumber){
        Position startOfZonePosition = FindUtilities.getStartOfZone(position);
        for(int rowInterator = 0; rowInterator< Main.HEIGHT_SIDE; rowInterator++){
            for(int columnIterator = 0; columnIterator< Main.WIDTH_SIDE; columnIterator++){
                Main.getSquareFromPosition(new Position(startOfZonePosition.rowPosition+rowInterator, startOfZonePosition.columnPosition+columnIterator)).deletePotentialNumberFromPotentialNumbers(potentialNumber);
            }
        }
    }

    public static void deleteInitialPotentialNumberFromRow(Position position, int potentialNumber){
        for(int rowInterator = 0; rowInterator< Main.HEIGHT_SIDE; rowInterator++){
            Main.getSquareFromPosition(new Position(rowInterator, position.columnPosition)).deletePotentialNumberFromPotentialNumbers(potentialNumber);
        }
    }

    public static void deleteInitialPotentialNumberFromColumn(Position position, int potentialNumber){
        for(int columnIterator = 0; columnIterator< Main.HEIGHT_SIDE; columnIterator++){
            Main.getSquareFromPosition(new Position(position.rowPosition, columnIterator)).deletePotentialNumberFromPotentialNumbers(potentialNumber);
        }
    }
}
