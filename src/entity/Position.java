package entity;

public class Position implements Comparable {

    public final int rowPosition;
    public final int columnPosition;

    public Position(int rowPosition, int columnPosition) {
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
    }

    @Override
    public int compareTo(Object o) {
        Position compared = (Position) o;
        if(this==compared || this.columnPosition==compared.columnPosition && this.rowPosition==compared.rowPosition){
            return 0;
        }
        if(this.rowPosition<compared.rowPosition){
            return -1;
        } else if (this.rowPosition==compared.rowPosition) {
            if(this.columnPosition<compared.columnPosition){
                return -1;
            } else if (this.columnPosition>compared.columnPosition) {
                return 1;
            }
        } else if(this.rowPosition>compared.rowPosition){
            return 1;
        }
        return 0;
    }
}