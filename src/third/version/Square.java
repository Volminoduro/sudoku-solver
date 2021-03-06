package third.version;

import java.util.ArrayList;
import java.util.List;

import static third.version.DeletionUtilities.*;

public class Square {

    public Position position;
    private List<Integer> potentialNumbers = new ArrayList<Integer>();
    private int choosenNumber;

    public Square(){
        choosenNumber = 0;
        for(int i = 1; i<(Main.HEIGHT_SIDE * Main.WIDTH_SIDE)+1; i++){
            potentialNumbers.add(i);
        }
    }

    public Square(Position position){
        this();
        this.position=position;
    }

    public Square(Position position, Integer chiffre){
        this.position=position;
        setChoosenNumber(chiffre);
        // TODO Deletion of potential numbers from zone, column and row
        deleteInitialPotentialNumberFromZone(this.position, this.choosenNumber);
        deleteInitialPotentialNumberFromRow(this.position, this.choosenNumber);
        deleteInitialPotentialNumberFromColumn(this.position, this.choosenNumber);
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
                deletePotentialNumberFromPotentialNumbers(potentialNumber);
            }
        }

    }



    public boolean deletePotentialNumberFromPotentialNumbers(int potentialNumber){
        if (containsPotentialNumber(potentialNumber)){
            this.potentialNumbers.remove(this.potentialNumbers.indexOf(potentialNumber));
            if(this.potentialNumbers.size()==1){
                this.setChoosenNumber(this.potentialNumbers.get(0));
            }
            updateSudokuFromChange();
            return true;
        }
        return false;
    }

    public boolean deleteInitialPotentialNumberFromPotentialNumbers(int potentialNumber){
        if (containsPotentialNumber(potentialNumber)){
            this.potentialNumbers.remove(this.potentialNumbers.indexOf(potentialNumber));
            if(this.potentialNumbers.size()==1){
                this.setChoosenNumber(this.potentialNumbers.get(0));
            }
            updateSudokuFromChange();
            return true;
        }
        return false;
    }

    public void updateSudokuFromChange(){
        Main.sudoku.put(this.position, this);
        // TODO Force sudoku sorting
    }

    public int getChoosenNumber(){
        return this.choosenNumber;
    }

    public void setChoosenNumber(Integer chiffre){
        this.choosenNumber = chiffre;
        potentialNumbers.clear();
        // TODO : I have to put this number out of possibilities for others Square in Zone, row and column (then call treatment for each of them)
    }

    public boolean isValidNumber(){
        return !(0==choosenNumber);
    }

    public boolean containsPotentialNumber(int potentialNumber){
        return this.potentialNumbers.contains(potentialNumber);
    }

    public String toString(){
        return "Square ("+this.position.rowPosition+", "+this.position.columnPosition+") Valid number : "+this.choosenNumber;
    }
}
