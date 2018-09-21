package third.version;

import java.util.ArrayList;
import java.util.List;

public class Position {

    public int choosenNumber;

    public List<Integer> potentialNumbers = new ArrayList<Integer>();

    public Position(){
        choosenNumber =0;
        for(int i = 1; i<(Main.HEIGHT_SIDE * Main.WIDTH_SIDE)+1; i++){
            potentialNumbers.add(i);
        }
    }

    public Position(Integer chiffre){
        setChoosenNumber(chiffre);
    }

    public int getChoosenNumber(){
        return this.choosenNumber;
    }

    public void setChoosenNumber(Integer chiffre){
        this.choosenNumber =chiffre;
        potentialNumbers.clear();
    }

    public List<Integer> getPotentialNumbers(){
        return this.potentialNumbers;
    }

    public void setPotentialNumbers(List<Integer> potentialNumbers){
        this.potentialNumbers = potentialNumbers;
    }

    public String toString(){
        return "Valid number : "+this.choosenNumber;
    }

}