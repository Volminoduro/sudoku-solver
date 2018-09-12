package third.version;

import third.version.FindUtilities;
import third.version.Position;
import third.version.SupprUtilities;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public final static int HEIGHT_SIDE = 3;
    public final static int WIDTH_SIDE = 3;

    public static Position[][] sudoku = new Position[HEIGHT_SIDE*HEIGHT_SIDE][WIDTH_SIDE*WIDTH_SIDE];


    public static void main(String[] args)
    {
        // Translate to C/C++ ?
        boolean solutionFound = false;
        double startTime, endTime, duration;
        List<Double> totalDuration = new ArrayList<Double>();

        for(int solutionFoundIteration=0;solutionFoundIteration<10000;solutionFoundIteration++){
            solutionFound=false;
            initializeEmptySudoku();
            duration = 0.0;
            startTime = System.nanoTime();
            while (!solutionFound){
                initializeSudoku();
                Position[][] actualSudoku = sudoku;
                // TODO : Try to get the positon with less potentials first
                for(int i=0; i<actualSudoku.length; i++) {
                    for(int j=0; j<actualSudoku[i].length; j++) {
                        if(0 == actualSudoku[i][j].getChoosenNumber()){
                            Position actualPosition = actualSudoku[i][j];
                            boolean numberFoundElsewhere = false;
                            int iteratorNumberPotential = 0, numberPotential;
                            do{
                                try{
                                    numberFoundElsewhere = false;
                                    numberPotential = actualPosition.getPotentialNumbers().get(iteratorNumberPotential);
                                    if(FindUtilities.findChoosenNumberFromZone(i, j, numberPotential)
                                            || FindUtilities.findChoosenNumberFromRow(i, j, numberPotential)
                                            ||FindUtilities.findChoosenNumberFromColumn(i, j, numberPotential)){
                                        if(FindUtilities.findChoosenNumberFromZone(i, j, numberPotential)){
                                            SupprUtilities.deletePotentialNumberFromZone(i, j, numberPotential);
                                        }
                                        if(FindUtilities.findChoosenNumberFromRow(i, j, numberPotential)){
                                            SupprUtilities.deletePotentialNumberFromRow(j, numberPotential);
                                        }
                                        if(FindUtilities.findChoosenNumberFromColumn(i, j, numberPotential)){
                                            SupprUtilities.deletePotentialNumberFromColumn(i, numberPotential);
                                        }
                                        iteratorNumberPotential--;
                                        numberFoundElsewhere = true;
                                        continue;
                                    }
                                    if(!numberFoundElsewhere &&
                                            (!FindUtilities.findPotentialNumberFromZone(i, j, numberPotential) ||
                                                    !FindUtilities.findPotentialNumberFromRow(i, j, numberPotential)||
                                                    !FindUtilities.findPotentialNumberFromColumn(i, j, numberPotential))){
                                        actualPosition.setChoosenNumber(numberPotential);
                                        sudoku[i][j]=actualPosition;
                                    }
                                    iteratorNumberPotential++;
                                } catch (Exception e){
                                    System.out.println(e);
                                }
                            }
                            while(!numberFoundElsewhere && iteratorNumberPotential<actualPosition.getPotentialNumbers().size());
                        }
                    }
                }
                solutionFound = isSudokuComplete();
            }
            endTime = System.nanoTime();
            duration = (endTime - startTime) / 1000000000.0;
            totalDuration.add(duration);
        }

        double totalDurationCalculated = 0.0;

        for(Double actuel : totalDuration){
            totalDurationCalculated+=actuel;
        }
        totalDurationCalculated/=totalDuration.size()+1;

        System.out.println("Solution found : "+solutionFound+"\nAverage treatment time : "+Double.toString(totalDurationCalculated));
        displaySudoku();
    }

    public static boolean isSudokuComplete(){
        int rowIterator = 0, columnIterator = 0;
        while(rowIterator< HEIGHT_SIDE * HEIGHT_SIDE){
            while(columnIterator< WIDTH_SIDE * WIDTH_SIDE){
                if(0 == sudoku[rowIterator][columnIterator].getChoosenNumber()){
                    return false;
                }
                columnIterator++;
            }
            rowIterator++;
        }
        return true;
    }

    public static void initializeEmptySudoku(){
        for(int iteratorHeight = 0;iteratorHeight<HEIGHT_SIDE*HEIGHT_SIDE;iteratorHeight++){
            for(int iteratorWidht = 0;iteratorWidht<WIDTH_SIDE*WIDTH_SIDE;iteratorWidht++){
                sudoku[iteratorHeight][iteratorWidht] = new Position();
            }
        }
    }

    public static void initializeSudoku(){
        sudoku[0][0] = new Position(5);
        SupprUtilities.deletePotentialNumberFromZone(0, 0, 5);
        SupprUtilities.deletePotentialNumberFromRow(0, 5);
        SupprUtilities.deletePotentialNumberFromColumn(0, 5);
        sudoku[0][1] = new Position(3);
        SupprUtilities.deletePotentialNumberFromZone(0, 1, 3);
        SupprUtilities.deletePotentialNumberFromRow(1, 3);
        SupprUtilities.deletePotentialNumberFromColumn(0, 3);
        sudoku[0][4] = new Position(7);
        SupprUtilities.deletePotentialNumberFromZone(0, 4, 7);
        SupprUtilities.deletePotentialNumberFromRow(4, 7);
        SupprUtilities.deletePotentialNumberFromColumn(0, 7);
        sudoku[1][0] = new Position(6);
        SupprUtilities.deletePotentialNumberFromZone(1, 0, 6);
        SupprUtilities.deletePotentialNumberFromRow(0, 6);
        SupprUtilities.deletePotentialNumberFromColumn(1, 6);
        sudoku[1][3] = new Position(1);
        SupprUtilities.deletePotentialNumberFromZone(1, 3, 1);
        SupprUtilities.deletePotentialNumberFromRow(3, 1);
        SupprUtilities.deletePotentialNumberFromColumn(1, 1);
        sudoku[1][4] = new Position(9);
        SupprUtilities.deletePotentialNumberFromZone(1, 4, 9);
        SupprUtilities.deletePotentialNumberFromRow(4, 9);
        SupprUtilities.deletePotentialNumberFromColumn(1, 9);
        sudoku[1][5] = new Position(5);
        SupprUtilities.deletePotentialNumberFromZone(1, 5, 5);
        SupprUtilities.deletePotentialNumberFromRow(5, 5);
        SupprUtilities.deletePotentialNumberFromColumn(1, 5);
        sudoku[2][1] = new Position(9);
        SupprUtilities.deletePotentialNumberFromZone(2, 1, 9);
        SupprUtilities.deletePotentialNumberFromRow(1, 9);
        SupprUtilities.deletePotentialNumberFromColumn(2, 9);
        sudoku[2][2] = new Position(8);
        SupprUtilities.deletePotentialNumberFromZone(2, 2, 8);
        SupprUtilities.deletePotentialNumberFromRow(2, 8);
        SupprUtilities.deletePotentialNumberFromColumn(2, 8);
        sudoku[2][7] = new Position(6);
        SupprUtilities.deletePotentialNumberFromZone(2, 7, 6);
        SupprUtilities.deletePotentialNumberFromRow(7, 6);
        SupprUtilities.deletePotentialNumberFromColumn(2, 6);
        sudoku[3][0] = new Position(8);
        SupprUtilities.deletePotentialNumberFromZone(3, 0, 8);
        SupprUtilities.deletePotentialNumberFromRow(0, 8);
        SupprUtilities.deletePotentialNumberFromColumn(3, 8);
        sudoku[3][4] = new Position(6);
        SupprUtilities.deletePotentialNumberFromZone(3, 4, 6);
        SupprUtilities.deletePotentialNumberFromRow(4, 6);
        SupprUtilities.deletePotentialNumberFromColumn(3, 6);
        sudoku[3][8] = new Position(3);
        SupprUtilities.deletePotentialNumberFromZone(3, 8, 3);
        SupprUtilities.deletePotentialNumberFromRow(8, 3);
        SupprUtilities.deletePotentialNumberFromColumn(3, 3);
        sudoku[4][0] = new Position(4);
        SupprUtilities.deletePotentialNumberFromZone(4, 0, 4);
        SupprUtilities.deletePotentialNumberFromRow(0, 4);
        SupprUtilities.deletePotentialNumberFromColumn(4, 4);
        sudoku[4][3] = new Position(8);
        SupprUtilities.deletePotentialNumberFromZone(4, 3, 8);
        SupprUtilities.deletePotentialNumberFromRow(3, 8);
        SupprUtilities.deletePotentialNumberFromColumn(4, 8);
        sudoku[4][5] = new Position(3);
        SupprUtilities.deletePotentialNumberFromZone(4, 5, 3);
        SupprUtilities.deletePotentialNumberFromRow(5, 3);
        SupprUtilities.deletePotentialNumberFromColumn(4, 3);
        sudoku[4][8] = new Position(1);
        SupprUtilities.deletePotentialNumberFromZone(4, 8, 1);
        SupprUtilities.deletePotentialNumberFromRow(8, 1);
        SupprUtilities.deletePotentialNumberFromColumn(4, 1);
        sudoku[5][0] = new Position(7);
        SupprUtilities.deletePotentialNumberFromZone(5, 0, 7);
        SupprUtilities.deletePotentialNumberFromRow(0, 7);
        SupprUtilities.deletePotentialNumberFromColumn(5, 7);
        sudoku[5][4] = new Position(2);
        SupprUtilities.deletePotentialNumberFromZone(5, 4, 2);
        SupprUtilities.deletePotentialNumberFromRow(4, 2);
        SupprUtilities.deletePotentialNumberFromColumn(5, 2);
        sudoku[5][8] = new Position(6);
        SupprUtilities.deletePotentialNumberFromZone(5, 8, 6);
        SupprUtilities.deletePotentialNumberFromRow(8, 6);
        SupprUtilities.deletePotentialNumberFromColumn(5, 6);
        sudoku[6][1] = new Position(6);
        SupprUtilities.deletePotentialNumberFromZone(6, 1, 6);
        SupprUtilities.deletePotentialNumberFromRow(1, 6);
        SupprUtilities.deletePotentialNumberFromColumn(6, 6);
        sudoku[6][6] = new Position(2);
        SupprUtilities.deletePotentialNumberFromZone(6, 6, 2);
        SupprUtilities.deletePotentialNumberFromRow(6, 2);
        SupprUtilities.deletePotentialNumberFromColumn(6, 2);
        sudoku[6][7] = new Position(8);
        SupprUtilities.deletePotentialNumberFromZone(6, 7, 8);
        SupprUtilities.deletePotentialNumberFromRow(7, 8);
        SupprUtilities.deletePotentialNumberFromColumn(6, 8);
        sudoku[7][3] = new Position(4);
        SupprUtilities.deletePotentialNumberFromZone(7, 3, 4);
        SupprUtilities.deletePotentialNumberFromRow(3, 4);
        SupprUtilities.deletePotentialNumberFromColumn(7, 4);
        sudoku[7][4] = new Position(1);
        SupprUtilities.deletePotentialNumberFromZone(7, 4, 1);
        SupprUtilities.deletePotentialNumberFromRow(4, 1);
        SupprUtilities.deletePotentialNumberFromColumn(7, 1);
        sudoku[7][5] = new Position(9);
        SupprUtilities.deletePotentialNumberFromZone(7, 5, 9);
        SupprUtilities.deletePotentialNumberFromRow(5, 9);
        SupprUtilities.deletePotentialNumberFromColumn(7, 9);
        sudoku[7][8] = new Position(5);
        SupprUtilities.deletePotentialNumberFromZone(7, 8, 5);
        SupprUtilities.deletePotentialNumberFromRow(8, 5);
        SupprUtilities.deletePotentialNumberFromColumn(7, 5);
        sudoku[8][4] = new Position(8);
        SupprUtilities.deletePotentialNumberFromZone(8, 4, 8);
        SupprUtilities.deletePotentialNumberFromRow(4, 8);
        SupprUtilities.deletePotentialNumberFromColumn(8, 8);
        sudoku[8][7] = new Position(7);
        SupprUtilities.deletePotentialNumberFromZone(8, 7, 7);
        SupprUtilities.deletePotentialNumberFromRow(7, 7);
        SupprUtilities.deletePotentialNumberFromColumn(8, 7);
        sudoku[8][8] = new Position(9);
        SupprUtilities.deletePotentialNumberFromZone(8, 8, 9);
        SupprUtilities.deletePotentialNumberFromRow(8, 9);
        SupprUtilities.deletePotentialNumberFromColumn(8, 9);
    }

    public static void displaySudoku(){
        for(int rowIterator = 0; rowIterator< HEIGHT_SIDE * HEIGHT_SIDE; rowIterator++){
            for(int columnIterator = 0; columnIterator< WIDTH_SIDE * WIDTH_SIDE; columnIterator++){
                System.out.printf(sudoku[rowIterator][columnIterator].getChoosenNumber()+", ");
            }
            System.out.printf("\n");
        }
    }

}

