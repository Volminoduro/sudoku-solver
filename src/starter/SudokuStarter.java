package starter;

import entity.Position;
import entity.Square;
import utilities.TreeMapSudoku;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SudokuStarter
{
    // TODO Whole Translation to most performant language (C ?)
    // TODO : https://dzone.com/articles/jmh-great-java-benchmarking
    // TODO : for benchmarking too Java VisualVM

    public final static int HEIGHT_SIDE = 3;
    public final static int WIDTH_SIDE = 3;
    public final static TreeMapSudoku sudoku = new TreeMapSudoku(3, 3);

    public static void main(String[] args)
    {
        initializeEmptySudoku();
        initializeSudoku();
        System.out.println(sudoku.toString());
        while(!sudoku.isSudokuComplete()){
            Iterator<Map.Entry<Square, Position>> iter = sudoku.entrySet().iterator();
            while(iter.hasNext()){
                Map.Entry<Square, Position> square = iter.next();
                Set<Square> positionsModified = square.getKey().treatmentForSudoku();
                while(positionsModified!=null && !positionsModified.isEmpty()){
                    positionsModified = positionsModified.iterator().next().treatmentForSudoku();
                }
            }
        }
        System.out.println(sudoku.toString());
    }

    public static void initializeEmptySudoku(){
        for(int iteratorHeight = 0;iteratorHeight<HEIGHT_SIDE*HEIGHT_SIDE;iteratorHeight++){
            for(int iteratorWidht = 0;iteratorWidht<WIDTH_SIDE*WIDTH_SIDE;iteratorWidht++){
                Position position = new Position(iteratorHeight, iteratorWidht);
                sudoku.put(new Square(position), position);
            }
        }
    }

    public static void initializeSudoku(){
        Position position;

        position = new Position(0, 0);
        sudoku.putAndReplace(new Square(position, 5), position);
        position = new Position(0, 1);
        sudoku.putAndReplace(new Square(position, 3), position);
        position = new Position(1, 0);
        sudoku.putAndReplace(new Square(position, 6), position);
        position = new Position(2, 1);
        sudoku.putAndReplace(new Square(position, 9), position);
        position = new Position(2, 2);
        sudoku.putAndReplace(new Square(position, 8), position);

        System.out.println(sudoku.toString());

        position = new Position(0, 4);
        sudoku.putAndReplace(new Square(position, 7), position);
        position = new Position(1, 3);
        sudoku.putAndReplace(new Square(position, 1), position);
        position = new Position(1, 4);
        sudoku.putAndReplace(new Square(position, 9), position);
        position = new Position(1, 5);
        sudoku.putAndReplace(new Square(position, 5), position);

        System.out.println(sudoku.toString());

        position = new Position(2, 7);
        sudoku.putAndReplace(new Square(position, 5), position);

        System.out.println(sudoku.toString());

        position = new Position(3, 0);
        sudoku.putAndReplace(new Square(position, 8), position);
        position = new Position(4, 0);
        sudoku.putAndReplace(new Square(position, 4), position);
        position = new Position(5, 0);
        sudoku.putAndReplace(new Square(position, 7), position);

        System.out.println(sudoku.toString());

        position = new Position(3, 4);
        sudoku.putAndReplace(new Square(position, 6), position);
        position = new Position(4, 3);
        sudoku.putAndReplace(new Square(position, 8), position);
        position = new Position(4, 5);
        sudoku.putAndReplace(new Square(position, 3), position);

        System.out.println(sudoku.toString());

        position = new Position(3, 8);
        sudoku.putAndReplace(new Square(position, 3), position);
        position = new Position(4, 8);
        sudoku.putAndReplace(new Square(position, 1), position);
        position = new Position(5, 4);
        sudoku.putAndReplace(new Square(position, 2), position);
        position = new Position(5, 8);
        sudoku.putAndReplace(new Square(position, 6), position);

        System.out.println(sudoku.toString());

        position = new Position(6, 1);
        sudoku.putAndReplace(new Square(position, 6), position);

        System.out.println(sudoku.toString());

        position = new Position(7, 3);
        sudoku.putAndReplace(new Square(position, 4), position);
        position = new Position(7, 4);
        sudoku.putAndReplace(new Square(position, 1), position);
        position = new Position(7, 5);
        sudoku.putAndReplace(new Square(position, 9), position);
        position = new Position(8, 4);
        sudoku.putAndReplace(new Square(position, 8), position);

        System.out.println(sudoku.toString());

        position = new Position(6, 6);
        sudoku.putAndReplace(new Square(position, 2), position);
        position = new Position(6, 7);
        sudoku.putAndReplace(new Square(position, 8), position);
        position = new Position(7, 8);
        sudoku.putAndReplace(new Square(position, 5), position);
        position = new Position(8, 7);
        sudoku.putAndReplace(new Square(position, 7), position);
        position = new Position(8, 8);
        sudoku.putAndReplace(new Square(position, 9), position);

        System.out.println(sudoku.toString());
    }
}

