package utilities;

import entity.Position;
import entity.Square;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapSudoku extends TreeMap {

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
}
