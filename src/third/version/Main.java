package third.version;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main
{
    // TODO Whole Translation to most performant language (C ?)
    // TODO Transform it to a maven project
    // TODO : https://dzone.com/articles/jmh-great-java-benchmarking
    // TODO : for benchmarking too Java VisualVM

    public final static int HEIGHT_SIDE = 3;
    public final static int WIDTH_SIDE = 3;
    public static Map<Position, Square> sudoku = new HashMap<Position, Square>();

    public static void main(String[] args)
    {


        Comparator<Square> squareComparator = new Comparator<Square>() {
            @Override
            public int compare(Square o1, Square o2) {
                // TODO SMA
                return 0;
            }
        };

        // TODO Sorting a map by values

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

        }

        // How i'm supposed to do ?
        // I should through each iteration for the less possibilities
        // If i didn't change state of one position (so no valid number selected or deletion of potential numbers), i should go to next number of possibilities
        // I should have a list of all position, sorted by number of possibilities
        // Once a position have been modified, it should saved the list of position modified that way, and see if it can do smth about that, and so on if it's recursive.
        // By the way, it shouldn't be done by reference directly, seen the state of the position can be different from a parent modification and a child
        // (for e.g : Here a A1 position, it have been modified since A0 have been modified. But A2 have been modified too by A0. A2 is altered first beause it have less possibilities
        // but A2 is finally a valid number, so it have it own children modified, and one of this children is A1)
        // If my position is not modified, i should go to the next one, and so on
        // Once i did go through each position, i loop again until my solution is found

//        if(null!=modifiedSquare){
//            do{
//                // How to manage child being already modified by child ?
//                ModifiedSquare modifiedSquareFromParent = modifiedSquare.getModifiedSquare ?
//            } while()
//        }
//        boolean solutionFound = false;
//
//        for(int solutionFoundIteration=0;solutionFoundIteration<10000;solutionFoundIteration++){
//            solutionFound=false;

//            while (!solutionFound){
//                initializeSudoku();
//                Position[][] actualSudoku = sudoku;
//                // TODO : Try to get the positon with less potentials first (ordering)
//
//                // TODO : Delete from searchen positions those who are already valid (what's the point ?)
//                for(int i=0; i<actualSudoku.length; i++) {
//                    for(int j=0; j<actualSudoku[i].length; j++) {
//                        if(0 == actualSudoku[i][j].getChoosenNumber()){
//                            Position actualPosition = actualSudoku[i][j];
//                            boolean numberFoundElsewhere = false;
//                            int iteratorNumberPotential = 0, numberPotential;
//                            do{
//                                try{
//                                    numberFoundElsewhere = false;
//                                    numberPotential = actualPosition.getPotentialNumbers().get(iteratorNumberPotential);
//                                    if(FindUtilities.findChoosenNumberFromZone(i, j, numberPotential)
//                                            || FindUtilities.findChoosenNumberFromRow(i, j, numberPotential)
//                                            ||FindUtilities.findChoosenNumberFromColumn(i, j, numberPotential)){
//                                        if(FindUtilities.findChoosenNumberFromZone(i, j, numberPotential)){
//                                            DeletionUtilities.deletePotentialNumberFromZone(i, j, numberPotential);
//                                        }
//                                        if(FindUtilities.findChoosenNumberFromRow(i, j, numberPotential)){
//                                            DeletionUtilities.deletePotentialNumberFromRow(j, numberPotential);
//                                        }
//                                        if(FindUtilities.findChoosenNumberFromColumn(i, j, numberPotential)){
//                                            DeletionUtilities.deletePotentialNumberFromColumn(i, numberPotential);
//                                        }
//                                        iteratorNumberPotential--;
//                                        numberFoundElsewhere = true;
//                                        continue;
//                                    }
//                                    if(!numberFoundElsewhere &&
//                                            (!FindUtilities.findPotentialNumberFromZone(i, j, numberPotential) ||
//                                                    !FindUtilities.findPotentialNumberFromRow(i, j, numberPotential)||
//                                                    !FindUtilities.findPotentialNumberFromColumn(i, j, numberPotential))){
//                                        actualPosition.setChoosenNumber(numberPotential);
//                                        sudoku[i][j]=actualPosition;
//                                    }
//                                    iteratorNumberPotential++;
//                                } catch (Exception e){
//                                    System.out.println(e);
//                                }
//                            }
//                            while(!numberFoundElsewhere && iteratorNumberPotential<actualPosition.getPotentialNumbers().size());
//                        }
//                    }
//                }
//                solutionFound = isSudokuComplete();
//            }
//        }
        displaySudoku();
    }

    public static Square getSquareFromPosition(Position position){
        return sudoku.get(position);
    }
//
    public static boolean isSudokuComplete(){
       for(Map.Entry<Position, Square> square : sudoku.entrySet()) {
            if(!square.getValue().isValidNumber()) {
                return false;
            }
       }
       return true;
    }

    public static void initializeEmptySudoku(){
        for(int iteratorHeight = 0;iteratorHeight<HEIGHT_SIDE*HEIGHT_SIDE;iteratorHeight++){
            for(int iteratorWidht = 0;iteratorWidht<WIDTH_SIDE*WIDTH_SIDE;iteratorWidht++){
                Position position = new Position(iteratorHeight, iteratorWidht);
                sudoku.put(position, new Square(position));
            }
        }
    }

    public static void initializeSudoku(){
        Position position;

        position = new Position(0, 0);
        sudoku.put(position, new Square(position, 5));
        position = new Position(0, 1);
        sudoku.put(position, new Square(position, 3));
        position = new Position(0, 4);
        sudoku.put(position, new Square(position, 7));

        position = new Position(1, 0);
        sudoku.put(position, new Square(position, 6));
        position = new Position(1, 3);
        sudoku.put(position, new Square(position, 1));
        position = new Position(1, 4);
        sudoku.put(position, new Square(position, 9));
        position = new Position(1, 5);
        sudoku.put(position, new Square(position, 5));

        position = new Position(2, 1);
        sudoku.put(position, new Square(position, 9));
        position = new Position(2, 2);
        sudoku.put(position, new Square(position, 8));
        position = new Position(2, 7);
        sudoku.put(position, new Square(position, 5));

        position = new Position(3, 0);
        sudoku.put(position, new Square(position, 8));
        position = new Position(3, 4);
        sudoku.put(position, new Square(position, 6));
        position = new Position(3, 8);
        sudoku.put(position, new Square(position, 3));

        position = new Position(4, 0);
        sudoku.put(position, new Square(position, 4));
        position = new Position(4, 3);
        sudoku.put(position, new Square(position, 8));
        position = new Position(4, 5);
        sudoku.put(position, new Square(position, 3));
        position = new Position(4, 8);
        sudoku.put(position, new Square(position, 1));

        position = new Position(5, 0);
        sudoku.put(position, new Square(position, 7));
        position = new Position(5, 4);
        sudoku.put(position, new Square(position, 2));
        position = new Position(5, 8);
        sudoku.put(position, new Square(position, 6));

        position = new Position(6, 1);
        sudoku.put(position, new Square(position, 6));
        position = new Position(6, 6);
        sudoku.put(position, new Square(position, 2));
        position = new Position(6, 7);
        sudoku.put(position, new Square(position, 8));

        position = new Position(7, 3);
        sudoku.put(position, new Square(position, 4));
        position = new Position(7, 4);
        sudoku.put(position, new Square(position, 1));
        position = new Position(7, 5);
        sudoku.put(position, new Square(position, 9));
        position = new Position(7, 8);
        sudoku.put(position, new Square(position, 5));

        position = new Position(8, 4);
        sudoku.put(position, new Square(position, 8));
        position = new Position(8, 7);
        sudoku.put(position, new Square(position, 7));
        position = new Position(8, 8);
        sudoku.put(position, new Square(position, 9));
    }

    public static void displaySudoku(){
        for(int rowIterator = 0; rowIterator< HEIGHT_SIDE * HEIGHT_SIDE; rowIterator++){
            for(int columnIterator = 0; columnIterator< WIDTH_SIDE * WIDTH_SIDE; columnIterator++){
                System.out.printf(sudoku.get(new Position(rowIterator, columnIterator)).getChoosenNumber()+", ");
            }
            System.out.printf("\n");
        }
    }

}

