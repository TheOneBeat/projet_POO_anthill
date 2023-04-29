package jeuDesFourmis.theModel;
import java.util.LinkedList;
import java.util.List;


public class Fourmiliere {
    private int largeur;
    // Le nombre maximal de graines par cases
    private int qMax;

    // la liste des fourmis de la fourmiliere.
    // Attention : la position X,Y d'une fourmi doit correspondre à un booleen true
    // dans le tableau fourmis
    private List<Fourmi> lesFourmis;


    // Attention : pour un terrain [1..hauteur]x[1..largeur], ces tableaux
    // sont indicés de [0..hauteur+1][0..largeur+1], cela permet de simplifier
    // certains traitements en ne traitant pas le cas particulier des bordures.
    private int[][] qteGraines;

    // Tableaux contenant les murs, les fourmis et les graines.
    private String[][] cellValues;

    /**
     * Crée une fourmiliere de largeur l et de hauteur h.
     *
     * @param l    largeur
     * @param qm la qte max de graines par case
     */
    public Fourmiliere(int l, int qm) {
        largeur = l;
         //la quantité max de graines pour chaque cellule...
        inits(largeur,qm);
    }

    /**
     * Cette fonction permet d'initialiser la fourmilière en initialisant tous les attributs du model
     *
     * @param k le premier entier
     * @param qm le deuxième entier
     * @return la somme de a et b
     */
    public void inits(int k,int qm)
    {
        qMax=qm;
        this.lesFourmis = new LinkedList<>();

        qteGraines = new int[k + 2][k + 2];
        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++) {
                qteGraines[i][j] = 0;
            }
        }

        cellValues = new String[k + 2][k + 2];

        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++) {
                cellValues[i][j] = "";
            }
        }
    }



    /**
     * change la largeur de la fourmiliere
     *
     * @param l largeur de la fourmiliere
     */
    public void setLargeur(int l) {
        largeur = l;
    }
    /**
     * Retourne la largeur de la fourmiliere
     *
     * @return la largeur
     */

    public int getLargeur() {
        return largeur;
    }

    /**
     * Cette fonction permet d'initialiser la fourmilière en fonction de la nouvelle largeur
     *
     * @param l la nouvelle largeur
     *
     */
    public void updateFourmiliere(int l)
    {
        setLargeur(l);
        inits(largeur,qMax);
    }


    /**
     * Cette fonction permet d'initialiser la fourmilière en fonction de la nouvelle largeur et de la nouvelle capacité
     *
     * @param l la nouvelle largeur
     * @param cap la nouvelle capacité
     */
    public void updateCapFourmiliere(int l,int cap)
    {
        setQMax(cap);
        inits(l,cap);
    }

    /**
     * Retourne le nombre max de graines par case
     *
     * @return le qMax
     */
    public int getQMax() {
        return qMax;
    }

    /**
     * Positionne la quantité max de graines par case
     *
     * @param qMax la nouvelle quantité max
     */
    public void setQMax(int qMax) {
        this.qMax = qMax;
    }

    /**
     * Presence d'un mur au point  (x,y) du terrain
     *
     * @param x coordonnée
     * @param y abcisse
     * @return vrai si il y a un mur
     */

    //Ce que j'ai rajouté...
    public String getCellContenu(int x, int y) {
        return cellValues[y][x];
    }


    /**
     * cette fonction permet de mettre à jour le contenu de la cellule (x,y) avec la chaine de caractère s
     * @param x coordonnée
     * @param y abcisse
     * @param s le contenu de la cellule
     */
    public void setValueContenu(int x, int y, String s)
    {
        if (cellValues[y][x].equals("O") && s.equals(""))
            cellValues[y][x]=s;

        if (cellValues[y][x].equals("")||cellValues[y][x].contains("."))
        {
            if (s.contains(".") && ((qteGraines[y][x]+1<=qMax)|| (qteGraines[y][x]==0)))
            {
                qteGraines[y][x]+=s.length();
                //System.out.println("la capacite max est fixé à "+qMax);
                cellValues[y][x] = s;
            }

            if (s.equals("X")||s.equals("O") || s.equals(""))
            {
                if (s.equals("X"))
                {
                    Fourmi f = new Fourmi(x, y, false);
                    lesFourmis.add(f);
                }
                cellValues[y][x] = s;
            }
        }
    }



    /**
     * Compte les graines du point (x,y) et des cellules voisines
     * Les voisines s'entendent au sens de 8-connexité.
     * On ne compte pas les graines sur les murs)
     *
     * @param x coordonnee
     * @param y abcisse
     * @return le nombre de graines
     */
    public int compteGrainesVoisines(int x, int y)
    {
        int nb = 0;
        for (int vx = -1; vx < 2; vx++) {
            for (int vy = -1; vy < 2; vy++) {
                if ((x+vx >=0) && (x+vx < largeur) && (y+vy>=0) && (y+vy< largeur))
                {
                    if (!cellValues[y + vy][x + vx].equals("O") && !cellValues[y + vy][x + vx].equals("X"))
                        nb = nb + qteGraines[y + vy][x + vx];
                }
            }
        }
        return nb;
    }

    /**
     * Evolution d'une étape de la fourmilière
     * Pour chaque fourmi f de la foumilière.
     * 1) si il y a une(ou des) graines sur la case, et que
     * la fourmi ne porte rien :
     * on choisit aléatoirement de charger ou non une graine,
     * en fonction du nombre de graines autour.
     * 2) f se deplace aléatoirement d'une case (en évitant les murs)
     * 3) si f est chargée et qu'il reste de la place pour une graine,
     * on choisit aléatoirement de poser ou non  la graine,
     * en fonction du nombre de graines autour.
     */
    public void evolue()
    {
        for (Fourmi f : lesFourmis) {
            int posX = f.getX();
            int posY = f.getY();

            // la fourmi f prend ?
            if (!f.porte() && qteGraines[posY][posX] > 0) {
                if (Math.random() < Fourmi.probaPrend(compteGrainesVoisines(posX, posY))) {
                    f.prend();
                    qteGraines[posY][posX]--;
                    if (qteGraines[posY][posX] > 0) {
                        boolean finale = false;
                        int x = 0, y = 0;
                        while (!finale) {
                            for (int vx = -2; vx < 4; vx++) {
                                for (int vy = -2; vy < 4; vy++) {
                                    if ((posX + vx >= 0) && (posX + vx < largeur)
                                            && (posY + vy >= 0)
                                            && (posY + vy < largeur)) {
                                        if (cellValues[posY + vy][posX + vx].equals("")) {
                                            x = posX + vx;
                                            y = posY + vy;
                                            finale = true;
                                        }
                                    }
                                }
                            }
                        }
                        setValueContenu(x, y, ".".repeat(qteGraines[posY][posX]));
                    }
                    //System.out.printf("la fourmi a pris le grain %n");
                }
            }
            // la fourmi f se déplace.
            int deltaX;
            int deltaY;
            // cptEssai compte les essais de déplacements pour eviter les blocages
            int cptEssai = 0;
            do {
                cptEssai++;
                deltaX = posX;
                deltaY = posY;
                int tirage = (int) (Math.random() * 7.99999999);
                switch (tirage) {
                    case 0 -> {
                        deltaX--;
                        deltaY--;
                    }
                    case 1 -> deltaY--;
                    case 2 -> {
                        deltaX++;
                        deltaY--;
                    }
                    case 3 -> deltaX--;
                    case 4 -> deltaX++;
                    case 5 -> {
                        deltaX--;
                        deltaY++;
                    }
                    case 6 -> deltaY++;
                    case 7 -> {
                        deltaX++;
                        deltaY++;
                    }
                }
                // On tire au sort jusqu'a ce qu'on soit tombe sur une case vide
                // ou bien qu'on ait essayé 99 fois.
            } while ((deltaX < 0 || deltaX >= largeur || deltaY < 0 || deltaY >= largeur) // Vérifiez les limites
                    || ((cellValues[deltaY][deltaX].equals("O")
                    || cellValues[deltaY][deltaX].equals("X")) && cptEssai < 100));

            boolean thefinalSpot = false;
            cellValues[posY][posX] = "";
            int xx = -1, yy = -1;
            //System.out.printf("la fourmi à la position x %d et y %d s'est deplacé au %d %d %n",posX,posY,deltaX,deltaY);
            // la fourmi pose ?
            if (f.porte() && qteGraines[deltaY][deltaX] < qMax) {

                if (Math.random() < Fourmi.probaPose(compteGrainesVoisines(deltaX, deltaY))) {
                    //System.out.printf("Je vais poser %n");
                    f.pose();
                    //poser un grain ici avec la fonction setCellContenu ici...
                    //et déplacer la fourmi en conséquence...
                    //il faut 2 instructions
                    boolean theEnd = true;

                    //première instruction
                    while (theEnd) {
                        for (int vx = -2; vx < 4; vx++) {
                            for (int vy = -2; vy < 4; vy++) {
                                if ((deltaX + vx >= 0) && (deltaX + vx < largeur) && (deltaY + vy >= 0)
                                        && (deltaY + vy < largeur)) {
                                    if (cellValues[deltaY + vy][deltaX + vx].equals("")) {
                                        cellValues[deltaY][deltaX] = "";
                                        xx = deltaX + vx;
                                        yy = deltaY + vy;
                                        theEnd = false;
                                    }
                                }
                            }
                        }
                    }

                    //deuxième instruction
                    //je pose le grain...
                    thefinalSpot = true;
                    cellValues[deltaY][deltaX] = "";
                    f.setX(xx);
                    f.setY(yy);
                    setValueContenu(deltaX, deltaY, ".");
                    //System.out.printf("la fourmi a posé un grain au pos %d %d %n", deltaX, deltaY);
                    qteGraines[deltaY][deltaX]++;
                }
            }
            if (thefinalSpot)
                cellValues[yy][xx] = "";
            else {
                f.setX(deltaX);
                f.setY(deltaY);
                cellValues[deltaY][deltaX] = "X";
            }
        }
    }

}