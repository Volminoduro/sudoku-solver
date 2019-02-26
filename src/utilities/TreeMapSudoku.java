package utilities;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapSudoku<Square, Position> extends TreeMap {

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
}
