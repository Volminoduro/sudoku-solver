package second.version;

import java.util.List;
import java.util.Map;

public class SupprUtilities {

    public static void deletePotentialNumberFromZone(int startingRowPosition, int startingColumnPosition, int numberToFind){
        Map<String, Integer> zoneStart = FindUtilities.getStartOfZone(startingRowPosition, startingColumnPosition);

        for(int rowInterator = 0; rowInterator< Main.HEIGHT_SIDE; rowInterator++){
            for(int columnIterator = 0; columnIterator< Main.WIDTH_SIDE; columnIterator++){
                supprimerChiffreDuneListe(zoneStart.get("HEIGHT")+rowInterator, zoneStart.get("WIDTH")+columnIterator, numberToFind);
            }
        }
    }

    public static void supprimerChiffreDansLaLigneVerticale(int positionVerticalDepart, int positionHorizontalDepart, int chiffreCherche){
        int incrementRecherche = 0, incrementHorizontal = 0, incrementVertical = 0, pointDeDepartHorizontal = 0, pointDeDepartVertical = 0, longueurduCote;

        incrementVertical++;
        pointDeDepartHorizontal=positionHorizontalDepart;
        longueurduCote = Main.HEIGHT_SIDE * Main.HEIGHT_SIDE;

        while(incrementRecherche<longueurduCote){
            supprimerChiffreDuneListe(pointDeDepartVertical+(incrementVertical*incrementRecherche),
    pointDeDepartHorizontal+(incrementHorizontal*incrementRecherche),
                    chiffreCherche);
            incrementRecherche++;
        }
    }

    public static void supprimerChiffreDansLaLigneHorizontale(int positionVerticalDepart, int positionHorizontalDepart, int chiffreCherche){
        int incrementRecherche = 0, incrementHorizontal = 0, incrementVertical = 0, pointDeDepartHorizontal = 0, pointDeDepartVertical = 0, longueurduCote;

        incrementHorizontal++;
        pointDeDepartVertical=positionVerticalDepart;
        longueurduCote = Main.WIDTH_SIDE * Main.WIDTH_SIDE;

        while(incrementRecherche<longueurduCote){
            supprimerChiffreDuneListe(pointDeDepartVertical+(incrementVertical*incrementRecherche),
                    pointDeDepartHorizontal+(incrementHorizontal*incrementRecherche),
                    chiffreCherche);
            incrementRecherche++;
        }
    }

    public static void supprimerChiffreDuneListe(int positionVertical, int positionHorizontal, int chiffreCherche){
        List<Integer> listeModifiee = Main.sudoku[positionVertical][positionHorizontal].getPotentialNumbers();
        if(listeModifiee.contains(chiffreCherche)){
            listeModifiee.remove(listeModifiee.indexOf(chiffreCherche));
            Main.sudoku[positionVertical][positionHorizontal].setPotentialNumbers(listeModifiee);
        }
    }
}
