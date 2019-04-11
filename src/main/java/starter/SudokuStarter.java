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

    public final static TreeMapSudoku sudoku = new TreeMapSudoku(3, 3);

    public static void main(String[] args)
    {
        sudoku.initializeEmptySudoku();
        initializeSudoku();
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

    public static void initializeSudoku(){
        sudoku.putAndReplace(new Square(new Position(0, 0), 5));
        sudoku.putAndReplace(new Square(new Position(0, 1), 3));
        sudoku.putAndReplace(new Square(new Position(1, 0), 6));
        sudoku.putAndReplace(new Square(new Position(2, 1), 9));
        sudoku.putAndReplace(new Square(new Position(2, 2), 8));

        System.out.println(sudoku.toString());

        sudoku.putAndReplace(new Square(new Position(0, 4), 7));
        sudoku.putAndReplace(new Square(new Position(1, 3), 1));
        sudoku.putAndReplace(new Square(new Position(1, 4), 9));
        sudoku.putAndReplace(new Square(new Position(1, 5), 5));

        System.out.println(sudoku.toString());

        sudoku.putAndReplace(new Square(new Position(2, 7), 5));

        System.out.println(sudoku.toString());

        sudoku.putAndReplace(new Square(new Position(3, 0), 8));
        sudoku.putAndReplace(new Square(new Position(4, 0), 4));
        sudoku.putAndReplace(new Square(new Position(5, 0), 7));

        System.out.println(sudoku.toString());

        sudoku.putAndReplace(new Square(new Position(3, 4), 6));
        sudoku.putAndReplace(new Square(new Position(4, 3), 8));
        sudoku.putAndReplace(new Square(new Position(4, 5), 3));

        System.out.println(sudoku.toString());

        sudoku.putAndReplace(new Square(new Position(3, 8), 3));
        sudoku.putAndReplace(new Square(new Position(4, 8), 1));
        sudoku.putAndReplace(new Square(new Position(5, 4), 2));
        sudoku.putAndReplace(new Square(new Position(5, 8), 6));

        System.out.println(sudoku.toString());

        sudoku.putAndReplace(new Square(new Position(6, 1), 6));

        System.out.println(sudoku.toString());

        sudoku.putAndReplace(new Square(new Position(7, 3), 4));
        sudoku.putAndReplace(new Square(new Position(7, 4), 1));
        sudoku.putAndReplace(new Square(new Position(7, 5), 9));
        sudoku.putAndReplace(new Square(new Position(8, 4), 8));

        System.out.println(sudoku.toString());

        sudoku.putAndReplace(new Square(new Position(6, 6), 2));
        sudoku.putAndReplace(new Square(new Position(6, 7), 8));
        sudoku.putAndReplace(new Square(new Position(7, 8), 5));
        sudoku.putAndReplace(new Square(new Position(8, 7), 7));
        sudoku.putAndReplace(new Square(new Position(8, 8), 9));

        System.out.println(sudoku.toString());
    }
}

