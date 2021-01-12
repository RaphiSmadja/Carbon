public class Adventurer {
    private String name;
    private int axeH;
    private int axeV;
    private String orientation;
    private String motionSeq;
    private int nbTreasures;

    public Adventurer(String name, int axeH, int axeV, String orientation, String motionSeq) {
        this.name = name;
        this.axeH = axeH;
        this.axeV = axeV;
        this.orientation = orientation;
        this.motionSeq = motionSeq;
        this.nbTreasures = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getMotionSeq() {
        return motionSeq;
    }

    public void setMotionSeq(String motionSeq) {
        this.motionSeq = motionSeq;
    }

    public int getNbTreasures() {
        return nbTreasures;
    }

    public void setNbTreasures(int nbTreasures) {
        this.nbTreasures = nbTreasures;
    }
}
