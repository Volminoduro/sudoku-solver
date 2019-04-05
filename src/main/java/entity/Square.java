package entity;

import entity.exceptions.SquareException;
import starter.SudokuStarter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Square implements Comparable<Square> {

    public Position position;
    protected List<Integer> potentialNumbers = new ArrayList<Integer>();
    protected int choosenNumber;

    public Square(Position position, Integer chiffre){
        this.position=position;
        setInitialChoosenNumber(chiffre);
    }

    public Square(Position position){
        this();
        this.position=position;
    }

    private Square(){
        choosenNumber = 0;
        for(int i = 1; i<(SudokuStarter.HEIGHT_SIDE * SudokuStarter.WIDTH_SIDE)+1; i++){
            potentialNumbers.add(i);
        }
    }

    /**
     * Delete a number from potential numbers without repercussion
     * @param initialPotentialNumber number to delete from potential
     */
    private boolean deletePotentialNumbers(int initialPotentialNumber){
        if(isValidNumber()){
            return false;
        }
        if (containsPotentialNumber(initialPotentialNumber)){
            this.potentialNumbers.remove(this.potentialNumbers.indexOf(initialPotentialNumber));
            if(this.potentialNumbers.size()==1){
                this.setChoosenNumber(this.potentialNumbers.get(0));
                return false;
            }
        }
        return true;
    }

    private void deleteInitialPotentialNumberFromColumn(Position position, int choosenNumber) {
        for(int columnIterator = 0; columnIterator< SudokuStarter.HEIGHT_SIDE; columnIterator++){
            Square actualSquare = (Square) SudokuStarter.sudoku.getKeyFromValue(new Position(position.rowPosition, columnIterator));
            actualSquare.deletePotentialNumbers(choosenNumber);
        }
    }

    private void deleteInitialPotentialNumberFromRow(Position position, int choosenNumber) {
        for(int rowIterator = 0; rowIterator< SudokuStarter.HEIGHT_SIDE; rowIterator++){
            Square actualSquare = (Square) SudokuStarter.sudoku.getKeyFromValue(new Position(rowIterator, position.columnPosition));
            actualSquare.deletePotentialNumbers(choosenNumber);
        }
    }

    private void deleteInitialPotentialNumberFromZone(Position position, int choosenNumber) {
        Position startOfZonePosition = SudokuStarter.sudoku.getStartOfZone(position);
        for(int rowIterator = 0; rowIterator< SudokuStarter.HEIGHT_SIDE; rowIterator++){
            for(int columnIterator = 0; columnIterator< SudokuStarter.WIDTH_SIDE; columnIterator++){
                Square actualSquare = (Square) SudokuStarter.sudoku.getKeyFromValue(new Position(startOfZonePosition.rowPosition+rowIterator, startOfZonePosition.columnPosition+columnIterator));
                actualSquare.deletePotentialNumbers(choosenNumber);
            }
        }
    }

    public Set<Square> treatmentForSudoku(){
        if(!this.isValidNumber()){
            int potentialNumber = this.potentialNumbers.get(0);
            Set<Square> newAffectedSquares = deletePotentialNumberFromZone(this.position, potentialNumber);
            newAffectedSquares.addAll(deletePotentialNumberFromColumn(this.position, potentialNumber));
            newAffectedSquares.addAll(deletePotentialNumberFromRow(this.position, potentialNumber));
            return newAffectedSquares;
        }
        return null;
    }

    public static Set<Square> deletePotentialNumberFromZone(Position position, int potentialNumber){
        Set<Square> affectedSquares = new TreeSet<>();
        Position startOfZonePosition = SudokuStarter.sudoku.getStartOfZone(position);
        for(int rowIterator = 0; rowIterator< SudokuStarter.HEIGHT_SIDE; rowIterator++){
            for(int columnIterator = 0; columnIterator< SudokuStarter.WIDTH_SIDE; columnIterator++){
                Square actualSquare = SudokuStarter.sudoku.getKeyFromValue(new Position(startOfZonePosition.rowPosition+rowIterator, startOfZonePosition.columnPosition+columnIterator));
                if(actualSquare.deletePotentialNumbers(potentialNumber)){
                    affectedSquares.add(actualSquare);
                }
            }
        }
        return affectedSquares;
    }

    public static Set<Square> deletePotentialNumberFromRow(Position position, int potentialNumber){
        Set<Square> affectedSquares = new TreeSet<>();
        for(int rowIterator = 0; rowIterator< SudokuStarter.HEIGHT_SIDE; rowIterator++){
            Square actualSquare = SudokuStarter.sudoku.getKeyFromValue(new Position(rowIterator, position.columnPosition));
            if(actualSquare.deletePotentialNumbers(potentialNumber)){
                affectedSquares.add(actualSquare);
            }
        }
        return affectedSquares;
    }

    public static Set<Square> deletePotentialNumberFromColumn(Position position, int potentialNumber){
        Set<Square> affectedSquares = new TreeSet<>();
        for(int columnIterator = 0; columnIterator< SudokuStarter.HEIGHT_SIDE; columnIterator++){
            Square actualSquare = SudokuStarter.sudoku.getKeyFromValue(new Position(position.rowPosition, columnIterator));
            if(actualSquare.deletePotentialNumbers(potentialNumber)){
                affectedSquares.add(actualSquare);
            }
        }
        return affectedSquares;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Square)) return false;
        Square square = (Square) o;
        return this.position.equals(square.position);
    }

    @Override
    public int compareTo(Square compared) {
        if(equals(compared)){
            return 0;
        }
        if(this.isValidNumber()&& compared.isValidNumber() && this.choosenNumber==compared.choosenNumber){
            throw new SquareException("Same choosen number detected, this can't be.");
        }
        if(this.potentialNumbers.size()==1 && this.potentialNumbers.size()==compared.potentialNumbers.size() && this.potentialNumbers.get(0)==compared.potentialNumbers.get(0)){
            throw new SquareException("Same unique potential number detected, this can't be.");
        }
        if(this.isValidNumber() && compared.isValidNumber() || this.potentialNumbers.size()==compared.potentialNumbers.size()){
            return this.position.compareTo(compared.position);
        }
        if(this.isValidNumber()){
            return 1;
        }
        if(compared.isValidNumber()){
            return -1;
        }
        return this.potentialNumbers.size()<compared.potentialNumbers.size() ? -1 : 1;
    }

    @Override
    public String toString(){
        return "["+this.position.rowPosition+";"+this.position.columnPosition+"] " +
                "| Valid : "+this.choosenNumber+ " | Potentials : "+potentialNumbers.size();
    }
}
