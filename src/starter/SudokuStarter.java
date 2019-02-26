package starter;

import entity.Position;
import entity.Square;
import utilities.TreeMapSudoku;

import java.util.*;

public class SudokuStarter
{
    // TODO Whole Translation to most performant language (C ?)
    // TODO : https://dzone.com/articles/jmh-great-java-benchmarking
    // TODO : for benchmarking too Java VisualVM

    public final static int HEIGHT_SIDE = 3;
    public final static int WIDTH_SIDE = 3;
    public static TreeMapSudoku<Square, Position> sudoku = new TreeMapSudoku<>();

    public static void main(String[] args)
    {
        // TODO : Pour chaque carré :
        // TODO : Je checke s'il ne lui reste plus qu'un seul possible, ou si son chiffre est possible ailleurs => je sélectionne le chiffre le cas échéant
        // TODO : Si je sélectionne ou que j'enlève un des possibles, je constitue un set de Square (ordered) qui devra être modifié en fonction
        // TODO : En appelant une méthode se chargant de ce set, pour chaque square modifié, je les récupère et les ajoute à mon set, et ainsi de suite tant que la liste des sets n'est pas vide.
        // TODO : Une fois que c'est fait, je réorder ma map de sudoku et je continue tant que le sudoku n'est pas résolu

        // TODO : Processing
        // TODO : While solution is not found
        // TODO : I go through each item of the map, picking the firsts ones (as it should have been sorted by numbers of possibilities ascending)
        // TODO : If it alter the state of square (selecting valid number, reducing number of possibilities),
        // TODO : the square save in itself all the others squared compromised, for each one of them, it should check for possibilities and valid number, and so on.
        // TODO : How to do if A have altered B and C, and B will altered C ? It's not a problem, as we work through references (C second iteration will be just fine after B modification), it still should work BUT we will have a serious lack of performance as the second (C itself) iteration for C should be pointless as B treatment is picking off possibilities
        // TODO : If i did alter any square, i resort the map and should iterate through the start of the map again
        // TODO : In a way, the end of modification of any square is the start of verification of solution found

        initializeEmptySudoku();
        initializeSudoku();
        while(!isSudokuComplete()){
            // TODO : Get first of potential numbers (change ordering compareTo)
        }
        displaySudoku();
    }

    public static void treatmentForSquare(Set<Square> squareSet){
        Iterator iter = squareSet.iterator();
        boolean modif = false;
        Set<Square> newAffectedSquares = new TreeSet<Square>();
        while(iter.hasNext() && !modif){
            Square actualSquare = (Square) iter.next();
            newAffectedSquares = actualSquare.treatmentForSudoku();
            if(!newAffectedSquares.isEmpty()){
                modif = true;
            }
        }
        if(modif){
            treatmentForSquare(newAffectedSquares);
        }
    }

    public static boolean isSudokuComplete(){
        final Square lastSquare = (Square) sudoku.lastKey();
        return lastSquare.isValidNumber();
    }

    public static void displaySudoku(){
        // TODO : Use of entrySet would be prefered
        // TODO : A real Utilities class for displaying purpose
        for(int rowIterator = 0; rowIterator< HEIGHT_SIDE * HEIGHT_SIDE; rowIterator++){
            for(int columnIterator = 0; columnIterator< WIDTH_SIDE * WIDTH_SIDE; columnIterator++){
                Square actualSquare = (Square) sudoku.getKeyFromValue(new Position(rowIterator, columnIterator));
                System.out.printf(actualSquare.getChoosenNumber()+", ");
            }
            System.out.printf("\n");
        }
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
        sudoku.put(new Square(position, 5), position);
        position = new Position(0, 1);
        sudoku.put(new Square(position, 3), position);
        position = new Position(0, 4);
        sudoku.put(new Square(position, 7), position);

        position = new Position(1, 0);
        sudoku.put(new Square(position, 6), position);
        position = new Position(1, 3);
        sudoku.put(new Square(position, 1), position);
        position = new Position(1, 4);
        sudoku.put(new Square(position, 9), position);
        position = new Position(1, 5);
        sudoku.put(new Square(position, 5), position);

        position = new Position(2, 1);
        sudoku.put(new Square(position, 9), position);
        position = new Position(2, 2);
        sudoku.put(new Square(position, 8), position);
        position = new Position(2, 7);
        sudoku.put(new Square(position, 5), position);

        position = new Position(3, 0);
        sudoku.put(new Square(position, 8), position);
        position = new Position(3, 4);
        sudoku.put(new Square(position, 6), position);
        position = new Position(3, 8);
        sudoku.put(new Square(position, 3), position);

        position = new Position(4, 0);
        sudoku.put(new Square(position, 4), position);
        position = new Position(4, 3);
        sudoku.put(new Square(position, 8), position);
        position = new Position(4, 5);
        sudoku.put(new Square(position, 3), position);
        position = new Position(4, 8);
        sudoku.put(new Square(position, 1), position);

        position = new Position(5, 0);
        sudoku.put(new Square(position, 7), position);
        position = new Position(5, 4);
        sudoku.put(new Square(position, 2), position);
        position = new Position(5, 8);
        sudoku.put(new Square(position, 6), position);

        position = new Position(6, 1);
        sudoku.put(new Square(position, 6), position);
        position = new Position(6, 6);
        sudoku.put(new Square(position, 2), position);
        position = new Position(6, 7);
        sudoku.put(new Square(position, 8), position);

        position = new Position(7, 3);
        sudoku.put(new Square(position, 4), position);
        position = new Position(7, 4);
        sudoku.put(new Square(position, 1), position);
        position = new Position(7, 5);
        sudoku.put(new Square(position, 9), position);
        position = new Position(7, 8);
        sudoku.put(new Square(position, 5), position);

        position = new Position(8, 4);
        sudoku.put(new Square(position, 8), position);
        position = new Position(8, 7);
        sudoku.put(new Square(position, 7), position);
        position = new Position(8, 8);
        sudoku.put(new Square(position, 9), position);
    }

    public static <K, V extends Comparable<V>> Map<K, V>
    sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator =
                new Comparator<K>() {
                    public int compare(K k1, K k2) {
                        int compare =
                                map.get(k1).compareTo(map.get(k2));
                        if (compare == 0)
                            return 1;
                        else
                            return compare;
                    }
                };

        Map<K, V> sortedByValues =
                new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }

}

