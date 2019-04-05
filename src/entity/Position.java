package entity;

public class Position implements Comparable<Position> {

    public final int rowPosition;
    public final int columnPosition;

    public Position(int rowPosition, int columnPosition) {
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return rowPosition == position.rowPosition && columnPosition == position.columnPosition;
    }

    @Override
    public int compareTo(Position compared) {
        if(equals(compared)){
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