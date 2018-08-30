package second.version;

import java.util.HashMap;
import java.util.Map;

public class FindUtilities {

    public static boolean trouverChiffreDansLaLigneVerticale(int positionVerticalDepart, int positionHorizontalDepart, int chiffreCherche){
        int incrementRecherche = 0, longueurduCote;

        longueurduCote = Main.HEIGHT_SIDE * Main.HEIGHT_SIDE;

        while(incrementRecherche<longueurduCote){
            if (incrementRecherche!=positionVerticalDepart ){
                if(Main.sudoku[0+(1*incrementRecherche)][positionHorizontalDepart]
                        .getChoosenNumber() == chiffreCherche){
                    return true;
                }
            }
            incrementRecherche++;
        }
        return false;
    }

    public static boolean trouverChiffrePossibleDansLaLigneVerticale(int positionVerticalDepart, int positionHorizontalDepart, int chiffreCherche){
        int incrementRecherche = 0, longueurduCote;

        longueurduCote = Main.HEIGHT_SIDE * Main.HEIGHT_SIDE;

        while(incrementRecherche<longueurduCote){
            if (incrementRecherche!=positionVerticalDepart ){
                if(Main.sudoku[0+(1*incrementRecherche)][positionHorizontalDepart]
                        .getPotentialNumbers().contains(chiffreCherche)){
                    return true;
                }
            }
            incrementRecherche++;
        }
        return false;
    }

    public static boolean trouverChiffreDansLaLigneHorizontale(int positionVerticalDepart, int positionHorizontalDepart, int chiffreCherche){
        int incrementRecherche = 0, longueurduCote;

        longueurduCote = Main.WIDTH_SIDE * Main.WIDTH_SIDE;

        while(incrementRecherche<longueurduCote){
            if (incrementRecherche!=positionHorizontalDepart ){
                if(Main.sudoku[positionVerticalDepart][0+(1*incrementRecherche)]
                        .getChoosenNumber() == chiffreCherche){
                    return true;
                }
            }
            incrementRecherche++;
        }
        return false;
    }

    public static boolean trouverChiffrePossibleDansLaLigneHorizontale(int positionVerticalDepart, int positionHorizontalDepart, int chiffreCherche){
        int incrementRecherche = 0, longueurduCote;

        longueurduCote = Main.WIDTH_SIDE * Main.WIDTH_SIDE;

        while(incrementRecherche<longueurduCote){
            if (incrementRecherche!=positionHorizontalDepart ){
                if(Main.sudoku[positionVerticalDepart][0+(1*incrementRecherche)]
                        .getPotentialNumbers().contains(chiffreCherche)){
                    return true;
                }
            }
            incrementRecherche++;
        }
        return false;
    }

    public static boolean trouverChiffreDansLaZone(int positionVerticalDepart, int positionHorizontalDepart, int ChiffreCherche){
        int compteurVertical = 0, compteurHorizontal;
        // Cela me donne le début de la zone
        Map<String, Integer> debutDeZone = getStartOfZone(positionVerticalDepart, positionHorizontalDepart);

        while(compteurVertical< Main.HEIGHT_SIDE){
            compteurHorizontal = 0;
            while(compteurHorizontal< Main.WIDTH_SIDE){
                if(Main.sudoku[debutDeZone.get("HEIGHT")+compteurVertical][debutDeZone.get("WIDTH")+compteurHorizontal].getChoosenNumber() == ChiffreCherche){
                    return true;
                }
                compteurHorizontal++;
            }
            compteurVertical++;
        }

        return false;
    }

    public static boolean trouverChiffrePossibleDansLaZone(int positionVerticalDepart, int positionHorizontalDepart, int chiffreCherche){
        int compteurVertical = 0, compteurHorizontal = 0;
        // Cela me donne le début de la zone
        Map<String, Integer> debutDeZone = getStartOfZone(positionVerticalDepart, positionHorizontalDepart);

        while(compteurVertical< Main.HEIGHT_SIDE){
            compteurHorizontal=0;
            while(compteurHorizontal< Main.WIDTH_SIDE){
                if(Main.sudoku[debutDeZone.get("HEIGHT")+compteurVertical][debutDeZone.get("WIDTH")+compteurHorizontal].getPotentialNumbers().contains(chiffreCherche)){
                    return true;
                }
                compteurHorizontal++;
            }
            compteurVertical++;
        }
        return false;
    }

    public static Map<String, Integer> getStartOfZone(int positionVerticalDepart, int positionHorizontalDepart){
        int positionVerticalDepartZone, positionHorizontalDepartZone;
        Map<String, Integer> positionDeRetours = new HashMap<>();

        positionVerticalDepartZone = positionVerticalDepart - (positionVerticalDepart % Main.HEIGHT_SIDE);
        positionHorizontalDepartZone = positionHorizontalDepart - (positionHorizontalDepart % Main.WIDTH_SIDE);

        positionDeRetours.put("HEIGHT", positionVerticalDepartZone);
        positionDeRetours.put("WIDTH", positionHorizontalDepartZone);

        return positionDeRetours;
    }
}
