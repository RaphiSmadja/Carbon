import java.util.Objects;

public class Mountain {

    private int axeV;
    private int axeH;

    public Mountain(int axeH, int axeV) {
        this.axeH = axeH;
        this.axeV = axeV;
    }

    public int getAxeV() {
        return axeV;
    }

    public void setAxeV(int axeV) {
        this.axeV = axeV;
    }

    public int getAxeH() {
        return axeH;
    }

    public void setAxeH(int axeH) {
        this.axeH = axeH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mountain mountain = (Mountain) o;
        return axeV == mountain.axeV &&
                axeH == mountain.axeH;
    }

    @Override
    public int hashCode() {
        return Objects.hash(axeV, axeH);
    }
}
