public class Treasure {
    private int axeH;
    private int axeV;
    private int nbTresor;

    public Treasure(int axeH, int axeV, int nbTresor) {
        this.axeH = axeH;
        this.axeV = axeV;
        this.nbTresor = nbTresor;
    }

    public int getAxeH() {
        return axeH;
    }

    public void setAxeH(int axeH) {
        this.axeH = axeH;
    }

    public int getAxeV() {
        return axeV;
    }

    public void setAxeV(int axeV) {
        this.axeV = axeV;
    }

    public int getNbTresor() {
        return nbTresor;
    }

    public void setNbTresor(int nbTresor) {
        this.nbTresor = nbTresor;
    }
}
