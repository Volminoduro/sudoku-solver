package entity;

import entity.exceptions.SameChosenNumberSquareException;
import entity.exceptions.SameUniquePotentialNumberSquareException;
import starter.SudokuStarter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Square implements Comparable<Square> {

    private Position position;
    List<Integer> potentialNumbers = new ArrayList<>();
    int chosenNumber;

    public Square(Position position, Integer number){
        this.position=position;
        setChosenNumber(number);
    }

    public Square(Position position){
        this();
        this.position=position;
    }

    private Square(){
        chosenNumber = 0;
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
                this.setChosenNumber(this.potentialNumbers.get(0));
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

    public int getChosenNumber(){
        return this.chosenNumber;
    }

    void setChosenNumber(Integer number){
        this.chosenNumber = number;
        potentialNumbers.clear();
        SudokuStarter.sudoku.deletePotentialNumberFromZone(this.position, number);
        SudokuStarter.sudoku.deletePotentialNumberFromColumn(this.position, number);
        SudokuStarter.sudoku.deletePotentialNumberFromRow(this.position, number);
    }

    public boolean isValidNumber(){
        return potentialNumbers.isEmpty() && !(0== chosenNumber);
    }

    private boolean containsPotentialNumber(int potentialNumber){
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
        if(this.isValidNumber() && compared.isValidNumber() && this.chosenNumber ==compared.chosenNumber){
            throw new SameChosenNumberSquareException("Same chosen number detected, this can't be.");
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
                "| Valid : "+this.chosenNumber + " | Potentials : "+potentialNumbers.size();
    }
}
