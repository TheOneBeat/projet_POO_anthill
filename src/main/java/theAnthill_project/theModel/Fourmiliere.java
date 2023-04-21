package theAnthill_project.theModel;
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

    // Tableaux contenant les murs, les fourmis et les graines.
    // Attention : pour un terrain [1..hauteur]x[1..largeur], ces tableaux
    // sont indicés de [0..hauteur+1][0..largeur+1], cela permet de simplifier
    // certains traitements en ne traitant pas le cas particulier des bordures.
    private int qteGraines[][];
    private String cellValues[][];

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

    public void inits(int k,int qm)
    {
        qMax=qm;
        this.lesFourmis = new LinkedList<Fourmi>();

      /*à modifier en ajouter une limite

      de fourmi... qu'on pourrait rajouter */

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

    public void setLargeur(int l) {
        largeur = l;
    }

    public int getLargeur() {
        return largeur;
    }

    public void updateFourmiliere(int l)
    {
        setLargeur(l);
        inits(largeur,qMax);
    }

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

    //Ce que j'ai rajouté...
    public void setValueContenu(int x, int y, String s)
    {
        if (cellValues[y][x].equals("O") && s.equals(""))
            cellValues[y][x]=s;

        if (cellValues[y][x].equals("")||cellValues[y][x].contains("."))
        {
            if (s.contains(".") && qteGraines[y][x]+1<=qMax)
            {
                qteGraines[y][x]+=1;
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
     * Retourne la quantité de graine au point (x,y) du terrain
     *
     * @param x coordonnnee
     * @param y abcisse
     * @return la quantité de graine
     */
    public int getQteGraines(int x, int y) {
        return this.qteGraines[y][x];
    }

    /**
     * Positionne des graines au point (x,y) du terrain
     *
     * @param x   coordonnee
     * @param y   abcisse
     * @param qte le nombre de graines que l'on souhaite poser. Si qte !E [0..QMAX] rien n'est effectué
     */
    public void setQteGraines(int x, int y, int qte) {
        //assert (qte >=0 && qte <=QMAX);
        if (qte < 0 || qte > qMax) {
            return;
        }
        this.qteGraines[y][x] = qte;
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

    public List<Fourmi> getLesFourmis() {
        return lesFourmis;
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
        for(int i=0;i<lesFourmis.size();i++)
        {
            Fourmi f = lesFourmis.get(i);
            int posX = f.getX();
            int posY = f.getY();

            // la fourmi f prend ?
            if (!f.porte() && qteGraines[posY][posX] > 0)
            {
                if (Math.random() < Fourmi.probaPrend(compteGrainesVoisines(posX, posY)))
                {
                    f.prend();
                    qteGraines[posY][posX]--;
                    System.out.printf("la fourmi a pris le grain %n");
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
                switch (tirage)
                {
                    case 0:
                        deltaX--;
                        deltaY--;
                        break;
                    case 1:
                        deltaY--;
                        break;
                    case 2:
                        deltaX++;
                        deltaY--;
                        break;
                    case 3:
                        deltaX--;
                        break;
                    case 4:
                        deltaX++;
                        break;
                    case 5:
                        deltaX--;
                        deltaY++;
                        break;
                    case 6:
                        deltaY++;
                        break;
                    case 7:
                        deltaX++;
                        deltaY++;
                        break;
                }
                // On tire au sort jusqu'a ce qu'on soit tombe sur une case vide
                // ou bien qu'on ait essayé 99 fois.
            } while ((deltaX < 0 || deltaX >= largeur || deltaY < 0 || deltaY >= largeur) // Vérifiez les limites
                    || ((cellValues[deltaY][deltaX].equals("O")
                    || cellValues[deltaY][deltaX].equals("X")) && cptEssai < 100));

            boolean thefinalSpot = false;
            cellValues[posY][posX]="";
            int xx = -1,yy = -1;
            //System.out.printf("la fourmi à la position x %d et y %d s'est deplacé au %d %d %n",posX,posY,deltaX,deltaY);
            // la fourmi pose ?
            if (f.porte() && qteGraines[deltaY][deltaX] < qMax)
            {

                if (Math.random() < Fourmi.probaPose(compteGrainesVoisines(deltaX, deltaY)))
                {
                    //System.out.printf("Je vais poser %n");
                    f.pose();
                    //poser un grain ici avec la fonction setCellContenu ici...
                    //et déplacer la fourmi en conséquence...
                    //il faut 2 instructions
                    boolean theEnd = true;

                    //première instruction
                    while(theEnd)
                    {
                        for (int vx = -2; vx < 4; vx++)
                        {
                            for (int vy = -2; vy < 4; vy++)
                            {
                                if ((deltaX + vx >= 0) && (deltaX + vx < largeur) && (deltaY + vy >= 0)
                                        && (deltaY + vy < largeur))
                                {
                                    if (cellValues[deltaY + vy][deltaX + vx].equals(""))
                                    {
                                        cellValues[deltaY][deltaX] = "";
                                        xx=deltaX+vx;
                                        yy=deltaY+vy;
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
                    //cellValues[yy][xx] = "X";
                    setValueContenu(deltaX,deltaY,".");
                    //System.out.printf("la fourmi a posé un grain au pos %d %d %n",deltaX,deltaY);
                    qteGraines[deltaY][deltaX]++;
                }
            }
            ;
            if (thefinalSpot)
                cellValues[yy][xx]="";
            else
            {
                f.setX(deltaX);
                f.setY(deltaY);
                cellValues[deltaY][deltaX]="X";
            }
        }
    }

}