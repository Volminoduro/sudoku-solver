package entity;

import entity.exceptions.SameChoosenNumberSquareException;
import entity.exceptions.SameUniquePotentialNumberSquareException;
import starter.SudokuStarter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Square implements Comparable<Square> {

    protected Position position;
    protected List<Integer> potentialNumbers = new ArrayList<>();
    protected int choosenNumber;

    public Square(Position position, Integer chiffre){
        this.position=position;
        setChoosenNumber(chiffre);
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
     * @param potentialNumber number to delete from potential
     */
    public boolean deletePotentialNumbers(int potentialNumber){
        if(isValidNumber()){
            return false;
        }
        if (containsPotentialNumber(potentialNumber) && this.potentialNumbers.size()>1){
            this.potentialNumbers.remove(this.potentialNumbers.indexOf(potentialNumber));
            if(this.potentialNumbers.size()==1){
                this.setChoosenNumber(this.potentialNumbers.get(0));
                return false;
            }
            return true;
        }
        return false;
    }

    public Set<Square> treatmentForSudoku(){
        if(!this.isValidNumber()){
            int potentialNumber = this.potentialNumbers.get(0);
            Set<Square> newAffectedSquares = SudokuStarter.sudoku.deletePotentialNumberFromZone(this.position, potentialNumber);
            newAffectedSquares.addAll(SudokuStarter.sudoku.deletePotentialNumberFromColumn(this.position, potentialNumber));
            newAffectedSquares.addAll(SudokuStarter.sudoku.deletePotentialNumberFromRow(this.position, potentialNumber));
            return newAffectedSquares;
        }
        return Collections.emptySet();
    }

    public int getChoosenNumber(){
        return this.choosenNumber;
    }

    public void setChoosenNumber(Integer chiffre){
        this.choosenNumber = chiffre;
        potentialNumbers.clear();
        SudokuStarter.sudoku.deletePotentialNumberFromZone(this.position, chiffre);
        SudokuStarter.sudoku.deletePotentialNumberFromColumn(this.position, chiffre);
        SudokuStarter.sudoku.deletePotentialNumberFromRow(this.position, chiffre);
    }

    public boolean isValidNumber(){
        return potentialNumbers.isEmpty() && !(0==choosenNumber);
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
        if(this.isValidNumber() && compared.isValidNumber() && this.choosenNumber==compared.choosenNumber){
            throw new SameChoosenNumberSquareException("Same choosen number detected, this can't be.");
        }
        if(this.potentialNumbers.size()==1 && this.potentialNumbers.size()==compared.potentialNumbers.size() && this.potentialNumbers.get(0)==compared.potentialNumbers.get(0)){
            throw new SameUniquePotentialNumberSquareException("Same unique potential number detected, this can't be.");
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
