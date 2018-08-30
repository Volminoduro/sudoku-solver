package second.version;

public class Main
{
    public final static int HEIGHT_SIDE = 3;
    public final static int WIDTH_SIDE = 3;

    public static Position[][] sudoku ={
            {new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(),},
            {new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(),},
            {new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(),},
            {new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(),},
            {new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(),},
            {new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(),},
            {new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(),},
            {new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(),},
            {new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(), new Position(),}
    };

    public static void main(String[] args)
    {
        initializeSudoku();
        boolean solutionTrouvee = false;
        long startTime = System.nanoTime();
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000000.0;

        while (!solutionTrouvee && duration < 600){
            Position[][] plateauItere = sudoku;
            // Tant que je n'ai pas trouvé la solution, je continue de parcourir
            for(int i=0; i<plateauItere.length; i++) {
                for(int j=0; j<plateauItere[i].length; j++) {
                    // Si je n'ai pas déjà un chiffre valide
                    if(0 == plateauItere[i][j].getChoosenNumber()){
                        Position positionActuelle = plateauItere[i][j];
                        // Pour chaque possibilité que j'ai
                        boolean chiffreTrouveAilleurs = false;
                        int indexParcoursChiffrePossible = 0, chiffrePossible;
                        do{
                            try{
                                chiffreTrouveAilleurs = false;
                                chiffrePossible = positionActuelle.getPotentialNumbers().get(indexParcoursChiffrePossible);
                                // Je vérifie si elle n'existe pas ailleurs dans les chiffres valides tant que je ne la trouve pas (en passant d'abord par la zone)
                                // Si je la trouve ailleurs
                                // je la supprime de mes possibilités, et de la zone, et des lignes.
                                if(FindUtilities.trouverChiffreDansLaZone(i, j, chiffrePossible)
                                        || FindUtilities.trouverChiffreDansLaLigneVerticale(i, j, chiffrePossible)
                                        ||FindUtilities.trouverChiffreDansLaLigneHorizontale(i, j, chiffrePossible)){
                                    if(FindUtilities.trouverChiffreDansLaZone(i, j, chiffrePossible)){
                                        SupprUtilities.deletePotentialNumberFromZone(i, j, chiffrePossible);
                                    }
                                    if(FindUtilities.trouverChiffreDansLaLigneVerticale(i, j, chiffrePossible)){
                                        SupprUtilities.supprimerChiffreDansLaLigneVerticale(i, j, chiffrePossible);
                                    }
                                    if(FindUtilities.trouverChiffreDansLaLigneHorizontale(i, j, chiffrePossible)){
                                        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(i, j, chiffrePossible);
                                    }
                                    indexParcoursChiffrePossible--;
                                    chiffreTrouveAilleurs = true;
                                    continue;
                                }

                                if(!chiffreTrouveAilleurs &&
                                        (!FindUtilities.trouverChiffrePossibleDansLaZone(i, j, chiffrePossible) ||
                                        !FindUtilities.trouverChiffrePossibleDansLaLigneVerticale(i, j, chiffrePossible)||
                                        !FindUtilities.trouverChiffrePossibleDansLaLigneHorizontale(i, j, chiffrePossible))){
                                    positionActuelle.setChoosenNumber(chiffrePossible);
                                    sudoku[i][j]=positionActuelle;
                                }
                                indexParcoursChiffrePossible++;
                            } catch (Exception e){
                                System.out.println(e);
                            }
                        }
                        while(!chiffreTrouveAilleurs && indexParcoursChiffrePossible<positionActuelle.getPotentialNumbers().size());
                    }
                }
            }
            // Timing
            endTime = System.nanoTime();
            duration = (endTime - startTime) / 1000000000.0;

            solutionTrouvee = isSudokuComplete();
        }

        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000000.0;

        System.out.println("Solution trouvée : "+solutionTrouvee+" temps de traitement : "+duration);

        afficherTableau();
    }

    public static boolean isSudokuComplete(){
        int compteurVertical = 0, compteurHorizontal = 0;
        while(compteurVertical< HEIGHT_SIDE * HEIGHT_SIDE){
            while(compteurHorizontal< WIDTH_SIDE * WIDTH_SIDE){
                if(0 == sudoku[compteurVertical][compteurHorizontal].getChoosenNumber()){
                    return false;
                }
                compteurHorizontal++;
            }
            compteurVertical++;
        }
        return true;
    }

    public static void initializeSudoku(){
        sudoku[0][0] = new Position(5);
        SupprUtilities.deletePotentialNumberFromZone(0, 0, 5);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(0, 0, 5);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(0, 0, 5);
        sudoku[0][1] = new Position(3);
        SupprUtilities.deletePotentialNumberFromZone(0, 1, 3);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(0, 1, 3);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(0, 1, 3);
        sudoku[0][4] = new Position(7);
        SupprUtilities.deletePotentialNumberFromZone(0, 4, 7);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(0, 4, 7);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(0, 4, 7);
        sudoku[1][0] = new Position(6);
        SupprUtilities.deletePotentialNumberFromZone(1, 0, 6);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(1, 0, 6);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(1, 0, 6);
        sudoku[1][3] = new Position(1);
        SupprUtilities.deletePotentialNumberFromZone(1, 3, 1);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(1, 3, 1);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(1, 3, 1);
        sudoku[1][4] = new Position(9);
        SupprUtilities.deletePotentialNumberFromZone(1, 4, 9);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(1, 4, 9);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(1, 4, 9);
        sudoku[1][5] = new Position(5);
        SupprUtilities.deletePotentialNumberFromZone(1, 5, 5);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(1, 5, 5);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(1, 5, 5);
        sudoku[2][1] = new Position(9);
        SupprUtilities.deletePotentialNumberFromZone(2, 1, 9);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(2, 1, 9);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(2, 1, 9);
        sudoku[2][2] = new Position(8);
        SupprUtilities.deletePotentialNumberFromZone(2, 2, 8);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(2, 2, 8);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(2, 2, 8);
        sudoku[2][7] = new Position(6);
        SupprUtilities.deletePotentialNumberFromZone(2, 7, 6);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(2, 7, 6);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(2, 7, 6);
        sudoku[3][0] = new Position(8);
        SupprUtilities.deletePotentialNumberFromZone(3, 0, 8);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(3, 0, 8);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(3, 0, 8);
        sudoku[3][4] = new Position(6);
        SupprUtilities.deletePotentialNumberFromZone(3, 4, 6);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(3, 4, 6);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(3, 4, 6);
        sudoku[3][8] = new Position(3);
        SupprUtilities.deletePotentialNumberFromZone(3, 8, 3);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(3, 8, 3);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(3, 8, 3);
        sudoku[4][0] = new Position(4);
        SupprUtilities.deletePotentialNumberFromZone(4, 0, 4);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(4, 0, 4);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(4, 0, 4);
        sudoku[4][3] = new Position(8);
        SupprUtilities.deletePotentialNumberFromZone(4, 3, 8);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(4, 3, 8);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(4, 3, 8);
        sudoku[4][5] = new Position(3);
        SupprUtilities.deletePotentialNumberFromZone(4, 5, 3);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(4, 5, 3);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(4, 5, 3);
        sudoku[4][8] = new Position(1);
        SupprUtilities.deletePotentialNumberFromZone(4, 8, 1);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(4, 8, 1);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(4, 8, 1);
        sudoku[5][0] = new Position(7);
        SupprUtilities.deletePotentialNumberFromZone(5, 0, 7);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(5, 0, 7);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(5, 0, 7);
        sudoku[5][4] = new Position(2);
        SupprUtilities.deletePotentialNumberFromZone(5, 4, 2);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(5, 4, 2);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(5, 4, 2);
        sudoku[5][8] = new Position(6);
        SupprUtilities.deletePotentialNumberFromZone(5, 8, 6);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(5, 8, 6);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(5, 8, 6);
        sudoku[6][1] = new Position(6);
        SupprUtilities.deletePotentialNumberFromZone(6, 1, 6);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(6, 1, 6);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(6, 1, 6);
        sudoku[6][6] = new Position(2);
        SupprUtilities.deletePotentialNumberFromZone(6, 6, 2);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(6, 6, 2);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(6, 6, 2);
        sudoku[6][7] = new Position(8);
        SupprUtilities.deletePotentialNumberFromZone(6, 7, 8);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(6, 7, 8);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(6, 7, 8);
        sudoku[7][3] = new Position(4);
        SupprUtilities.deletePotentialNumberFromZone(7, 3, 4);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(7, 3, 4);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(7, 3, 4);
        sudoku[7][4] = new Position(1);
        SupprUtilities.deletePotentialNumberFromZone(7, 4, 1);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(7, 4, 1);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(7, 4, 1);
        sudoku[7][5] = new Position(9);
        SupprUtilities.deletePotentialNumberFromZone(7, 5, 9);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(7, 5, 9);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(7, 5, 9);
        sudoku[7][8] = new Position(5);
        SupprUtilities.deletePotentialNumberFromZone(7, 8, 5);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(7, 8, 5);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(7, 8, 5);
        sudoku[8][4] = new Position(8);
        SupprUtilities.deletePotentialNumberFromZone(8, 4, 8);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(8, 4, 8);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(8, 4, 8);
        sudoku[8][7] = new Position(7);
        SupprUtilities.deletePotentialNumberFromZone(8, 7, 7);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(8, 7, 7);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(8, 7, 7);
        sudoku[8][8] = new Position(9);
        SupprUtilities.deletePotentialNumberFromZone(8, 8, 9);
        SupprUtilities.supprimerChiffreDansLaLigneVerticale(8, 8, 9);
        SupprUtilities.supprimerChiffreDansLaLigneHorizontale(8, 8, 9);
    }

    public static void afficherTableau(){
        // Affichage du tableau
        for(int compteurVertical = 0; compteurVertical< HEIGHT_SIDE * HEIGHT_SIDE; compteurVertical++){
            for(int compteurHorizontal = 0; compteurHorizontal< WIDTH_SIDE * WIDTH_SIDE; compteurHorizontal++){
                System.out.printf(sudoku[compteurVertical][compteurHorizontal].getChoosenNumber()+", ");
            }
            System.out.printf("\n");
        }
    }

}

