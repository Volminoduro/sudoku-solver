package second.version;

import java.util.HashMap;
import java.util.Map;

public class FindUtilities {

    public static boolean findChoosenNumberFromRow(int rowStartingPosition, int columnStartingPosition, int numberToFind){
        int researchIterator = 0, heightLength;

        heightLength = Main.HEIGHT_SIDE * Main.HEIGHT_SIDE;

        while(researchIterator<heightLength){
            if (researchIterator!=rowStartingPosition ){
                if(Main.sudoku[0+(1*researchIterator)][columnStartingPosition]
                        .getChoosenNumber() == numberToFind){
                    return true;
                }
            }
            researchIterator++;
        }
        return false;
    }

    public static boolean findPotentialNumberFromRow(int rowStartingPosition, int columnStartingPosition, int numberToFind){
        int researchIterator = 0, heightLength;

        heightLength = Main.HEIGHT_SIDE * Main.HEIGHT_SIDE;

        while(researchIterator<heightLength){
            if (researchIterator!=rowStartingPosition ){
                if(Main.sudoku[0+(1*researchIterator)][columnStartingPosition]
                        .getPotentialNumbers().contains(numberToFind)){
                    return true;
                }
            }
            researchIterator++;
        }
        return false;
    }

    public static boolean findChoosenNumberFromColumn(int rowStartingPosition, int columnStartingPosition, int numberToFind){
        int researchIterator = 0, widthLength;

        widthLength = Main.WIDTH_SIDE * Main.WIDTH_SIDE;

        while(researchIterator<widthLength){
            if (researchIterator!=columnStartingPosition ){
                if(Main.sudoku[rowStartingPosition][0+(1*researchIterator)]
                        .getChoosenNumber() == numberToFind){
                    return true;
                }
            }
            researchIterator++;
        }
        return false;
    }

    public static boolean findPotentialNumberFromColumn(int rowStartingPosition, int columnStartingPosition, int numberToFind){
        int researchIterator = 0, widthLength;

        widthLength = Main.WIDTH_SIDE * Main.WIDTH_SIDE;

        while(researchIterator<widthLength){
            if (researchIterator!=columnStartingPosition ){
                if(Main.sudoku[rowStartingPosition][0+(1*researchIterator)]
                        .getPotentialNumbers().contains(numberToFind)){
                    return true;
                }
            }
            researchIterator++;
        }
        return false;
    }

    public static boolean findChoosenNumberFromZone(int rowStartingPosition, int columnStartingPosition, int numberToFind){
        int rowIterator = 0, columnIterator;
        // Cela me donne le début de la zone
        Map<String, Integer> startingZone = getStartOfZone(rowStartingPosition, columnStartingPosition);

        while(rowIterator< Main.HEIGHT_SIDE){
            columnIterator = 0;
            while(columnIterator< Main.WIDTH_SIDE){
                if(Main.sudoku[startingZone.get("HEIGHT")+rowIterator][startingZone.get("WIDTH")+columnIterator].getChoosenNumber() == numberToFind){
                    return true;
                }
                columnIterator++;
            }
            rowIterator++;
        }

        return false;
    }

    public static boolean findPotentialNumberFromZone(int rowStartingPosition, int columnStartingPosition, int numberToFind){
        int rowIterator = 0, columnIterator = 0;
        // Cela me donne le début de la zone
        Map<String, Integer> startingZone = getStartOfZone(rowStartingPosition, columnStartingPosition);

        while(rowIterator< Main.HEIGHT_SIDE){
            columnIterator = 0;
            while(columnIterator< Main.WIDTH_SIDE){
                if(Main.sudoku[startingZone.get("HEIGHT")+rowIterator][startingZone.get("WIDTH")+columnIterator].getPotentialNumbers().contains(numberToFind)){
                    return true;
                }
                columnIterator++;
            }
            rowIterator++;
        }
        return false;
    }

    public static Map<String, Integer> getStartOfZone(int rowStartingPosition, int columnStartingPosition){
        int rowStartingZonePosition, columnStartingZonePosition;
        Map<String, Integer> returnPositions = new HashMap<>();

        rowStartingZonePosition = rowStartingPosition - (rowStartingPosition % Main.HEIGHT_SIDE);
        columnStartingZonePosition = columnStartingPosition - (columnStartingPosition % Main.WIDTH_SIDE);

        returnPositions.put("HEIGHT", rowStartingZonePosition);
        returnPositions.put("WIDTH", columnStartingZonePosition);

        return returnPositions;
    }
}
