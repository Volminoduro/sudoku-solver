package third.version;

public class Position {

    public int rowPosition;
    public int columnPosition;

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
    public int hashCode() {
        int result = rowPosition;
        result = 31 * result + columnPosition;
        return result;
    }
}