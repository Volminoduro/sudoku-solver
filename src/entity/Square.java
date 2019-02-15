package entity;

import starter.SudokuStarter;
import utilities.FindUtilities;

import java.util.ArrayList;
import java.util.List;

public class Square implements Comparable {

    public Position position;
    private List<Integer> potentialNumbers = new ArrayList<Integer>();
    private int choosenNumber;

    public Square(Position position, Integer chiffre){
        this.position=position;
        setInitialChoosenNumber(chiffre);
    }

    public Square(Position position){
        this();
        this.position=position;
    }

    public Square(){
        choosenNumber = 0;
        for(int i = 1; i<(SudokuStarter.HEIGHT_SIDE * SudokuStarter.WIDTH_SIDE)+1; i++){
            potentialNumbers.add(i);
        }
    }

    // TODO A global treatment function for Square
    public void treatment(){
        if(this.isValidNumber()){
            return;
        }
        if(this.potentialNumbers.size()==1){
            setChoosenNumber(this.potentialNumbers.get(0));
        }
        for(int potentialNumber : this.potentialNumbers){
            // TODO : If i can found this potential number in the zone, column or row
            if(true){
                // TODO : If i can found this potential number in the zone, column or row out of this square
                if(true){
                    return;
                } else {
                    setChoosenNumber(potentialNumber);
                    return;
                }
            } else {
                deletePotentialNumbers(potentialNumber);
            }
        }

    }

    /**
     * Delete a number from potential numbers without repercussion
     * @param initialPotentialNumber number to delete from potential
     */
    private void deleteInitialPotentialNumbers(int initialPotentialNumber){
        if (containsPotentialNumber(initialPotentialNumber)){
            this.potentialNumbers.remove(this.potentialNumbers.indexOf(initialPotentialNumber));
            if(this.potentialNumbers.size()==1){
                this.setChoosenNumber(this.potentialNumbers.get(0));
            }
        }
    }

    private void deleteInitialPotentialNumberFromColumn(Position position, int choosenNumber) {
        for(int columnIterator = 0; columnIterator< SudokuStarter.HEIGHT_SIDE; columnIterator++){
            Square actualSquare = (Square) SudokuStarter.sudoku.getKey(new Position(position.rowPosition, columnIterator));
            actualSquare.deleteInitialPotentialNumbers(choosenNumber);
        }
    }

    private void deleteInitialPotentialNumberFromRow(Position position, int choosenNumber) {
        for(int rowIterator = 0; rowIterator< SudokuStarter.HEIGHT_SIDE; rowIterator++){
            Square actualSquare = (Square) SudokuStarter.sudoku.getKey(new Position(rowIterator, position.columnPosition));
            actualSquare.deleteInitialPotentialNumbers(choosenNumber);
        }
    }

    private void deleteInitialPotentialNumberFromZone(Position position, int choosenNumber) {
        Position startOfZonePosition = FindUtilities.getStartOfZone(position);
        for(int rowInterator = 0; rowInterator< SudokuStarter.HEIGHT_SIDE; rowInterator++){
            for(int columnIterator = 0; columnIterator< SudokuStarter.WIDTH_SIDE; columnIterator++){
                Square actualSquare = (Square) SudokuStarter.sudoku.getKey(new Position(startOfZonePosition.rowPosition+rowInterator, startOfZonePosition.columnPosition+columnIterator));
                actualSquare.deleteInitialPotentialNumbers(choosenNumber);
//                SudokuStarter.getSquareFromPosition(new Position(startOfZonePosition.rowPosition+rowInterator, startOfZonePosition.columnPosition+columnIterator)).deletePotentialNumbers(choosenNumber);
            }
        }
    }

    public void deletePotentialNumbers(int potentialNumber){
        if (containsPotentialNumber(potentialNumber)){
            this.potentialNumbers.remove(this.potentialNumbers.indexOf(potentialNumber));
            if(this.potentialNumbers.size()==1){
                this.setChoosenNumber(this.potentialNumbers.get(0));
            }
            updateSudokuFromChange();
        }
    }

    public static void deletePotentialNumberFromZone(Position position, int potentialNumber){
        Position startOfZonePosition = FindUtilities.getStartOfZone(position);
        for(int rowInterator = 0; rowInterator< SudokuStarter.HEIGHT_SIDE; rowInterator++){
            for(int columnIterator = 0; columnIterator< SudokuStarter.WIDTH_SIDE; columnIterator++){
                SudokuStarter.getSquareFromPosition(new Position(startOfZonePosition.rowPosition+rowInterator, startOfZonePosition.columnPosition+columnIterator)).deletePotentialNumbers(potentialNumber);
            }
        }
    }

    public static void deletePotentialNumberFromRow(Position position, int potentialNumber){
        for(int rowInterator = 0; rowInterator< SudokuStarter.HEIGHT_SIDE; rowInterator++){
            SudokuStarter.getSquareFromPosition(new Position(rowInterator, position.columnPosition)).deletePotentialNumbers(potentialNumber);
        }
    }

    public static void deletePotentialNumberFromColumn(Position position, int potentialNumber){
        for(int columnIterator = 0; columnIterator< SudokuStarter.HEIGHT_SIDE; columnIterator++){
            SudokuStarter.getSquareFromPosition(new Position(position.rowPosition, columnIterator)).deletePotentialNumbers(potentialNumber);
        }
    }

    public void updateSudokuFromChange(){
        SudokuStarter.sudoku.put(this.position, this);
        // TODO Force sudoku sorting
    }

    public int getChoosenNumber(){
        return this.choosenNumber;
    }

    public void setInitialChoosenNumber(Integer chiffre){
        this.choosenNumber = chiffre;
        potentialNumbers.clear();
        deleteInitialPotentialNumberFromZone(this.position, this.choosenNumber);
        deleteInitialPotentialNumberFromRow(this.position, this.choosenNumber);
        deleteInitialPotentialNumberFromColumn(this.position, this.choosenNumber);
    }

    public void setChoosenNumber(Integer chiffre){
        this.choosenNumber = chiffre;
        potentialNumbers.clear();
        deletePotentialNumberFromZone(this.position, chiffre);
        deletePotentialNumberFromColumn(this.position, chiffre);
        deletePotentialNumberFromRow(this.position, chiffre);
    }

    public boolean isValidNumber(){
        return !(0==choosenNumber);
    }

    public boolean containsPotentialNumber(int potentialNumber){
        return this.potentialNumbers.contains(potentialNumber);
    }

    @Override
    public int compareTo(Object o) {
        Square compared = (Square) o;
        if(this==compared){
            return 0;
        }
        if(this.potentialNumbers.size()==compared.potentialNumbers.size()){
            return this.position.compareTo(compared.position);
        }
        return this.potentialNumbers.size()<compared.potentialNumbers.size() ? -1 : 1;
    }

    public String toString(){
        return "["+this.position.rowPosition+";"+this.position.columnPosition+"] " +
                "| Valid : "+this.choosenNumber+ " | Potentials : "+potentialNumbers.size();
    }
}
