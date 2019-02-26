package utilities;

import entity.Position;
import starter.SudokuStarter;

public class FindUtilities {

    public static Position getStartOfZone(Position position){
        int rowStartingZonePosition = position.rowPosition, columnStartingZonePosition = position.columnPosition;
        return new Position(rowStartingZonePosition - (rowStartingZonePosition % SudokuStarter.HEIGHT_SIDE), columnStartingZonePosition - (columnStartingZonePosition % SudokuStarter.WIDTH_SIDE));
    }
}
