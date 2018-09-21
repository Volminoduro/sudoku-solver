package third.version;

import java.util.List;
import java.util.Map;

public class SupprUtilities {

    public static void deletePotentialNumberFromZone(int startingRowPosition, int startingColumnPosition, int numberToFind){
        Map<String, Integer> zoneStart = FindUtilities.getStartOfZone(startingRowPosition, startingColumnPosition);

        for(int rowInterator = 0; rowInterator< Main.HEIGHT_SIDE; rowInterator++){
            for(int columnIterator = 0; columnIterator< Main.WIDTH_SIDE; columnIterator++){
                deletePotentialNumberFromPosition(zoneStart.get("HEIGHT")+rowInterator, zoneStart.get("WIDTH")+columnIterator, numberToFind);
            }
        }
    }

    public static void deletePotentialNumberFromRow(int startingColumnPosition, int numberToFind){
        int incrementResearch = 0;
        int rowIterator = 1;
        int rowStartingPosition = 0;
        int heightLenght;

        heightLenght = Main.HEIGHT_SIDE * Main.HEIGHT_SIDE;

        while(incrementResearch<heightLenght){
            deletePotentialNumberFromPosition(rowStartingPosition+(rowIterator*incrementResearch),
                    startingColumnPosition,
                    numberToFind);
            incrementResearch++;
        }
    }

    public static void deletePotentialNumberFromColumn(int startingRowPosition, int numberToFind){
        int incrementResearch = 0;
        int incrementHorizontal = 0;
        int columnStartingPosition = 0;
        int widthLenght;

        incrementHorizontal++;
        widthLenght = Main.WIDTH_SIDE * Main.WIDTH_SIDE;

        while(incrementResearch<widthLenght){
            deletePotentialNumberFromPosition(startingRowPosition,
                    columnStartingPosition+(incrementHorizontal*incrementResearch),
                    numberToFind);
            incrementResearch++;
        }
    }

    public static void deletePotentialNumberFromPosition(int rowPosition, int columnPosition, int numberToFind){
        List<Integer> modifiedList = Main.sudoku[rowPosition][columnPosition].getPotentialNumbers();
        if(modifiedList.contains(numberToFind)){
            modifiedList.remove(modifiedList.indexOf(numberToFind));
            Main.sudoku[rowPosition][columnPosition].setPotentialNumbers(modifiedList);
        }
    }
}
