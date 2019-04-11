package entity;

import entity.exceptions.SquareException;
import starter.SudokuStarter;
import utilities.TreeMapSudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Square implements Comparable<Square> {

    protected Position position;
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
        for(int i = 1; i<(SudokuStarter.sudoku.getHEIGHT_SIDE() * SudokuStarter.sudoku.getWIDTH_SIDE())+1; i++){
            potentialNumbers.add(i);
        }
    }

    /**
     * Delete a number from potential numbers without repercussion
     * @param initialPotentialNumber number to delete from potential
     */
    public boolean deletePotentialNumbers(int initialPotentialNumber){
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

    public Set<Square> treatmentForSudoku(){
        if(!this.isValidNumber()){
            int potentialNumber = this.potentialNumbers.get(0);
            Set<Square> newAffectedSquares = SudokuStarter.sudoku.deletePotentialNumberFromZone(this.position, potentialNumber);
            newAffectedSquares.addAll(SudokuStarter.sudoku.deletePotentialNumberFromColumn(this.position, potentialNumber));
            newAffectedSquares.addAll(SudokuStarter.sudoku.deletePotentialNumberFromRow(this.position, potentialNumber));
            return newAffectedSquares;
        }
        return null;
    }

    public int getChoosenNumber(){
        return this.choosenNumber;
    }

    public void setInitialChoosenNumber(Integer chiffre){
        this.choosenNumber = chiffre;
        potentialNumbers.clear();
        SudokuStarter.sudoku.deleteInitialPotentialNumberFromZone(this.position, this.choosenNumber);
        SudokuStarter.sudoku.deleteInitialPotentialNumberFromRow(this.position, this.choosenNumber);
        SudokuStarter.sudoku.deleteInitialPotentialNumberFromColumn(this.position, this.choosenNumber);
    }

    public void setChoosenNumber(Integer chiffre){
        this.choosenNumber = chiffre;
        potentialNumbers.clear();
        SudokuStarter.sudoku.deletePotentialNumberFromZone(this.position, chiffre);
        SudokuStarter.sudoku.deletePotentialNumberFromColumn(this.position, chiffre);
        SudokuStarter.sudoku.deletePotentialNumberFromRow(this.position, chiffre);
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

    public Position getPosition() {
        return position;
    }

    public List<Integer> getPotentialNumbers() {
        return potentialNumbers;
    }

    @Override
    public String toString(){
        return "["+this.position.rowPosition+";"+this.position.columnPosition+"] " +
                "| Valid : "+this.choosenNumber+ " | Potentials : "+potentialNumbers.size();
    }
}
