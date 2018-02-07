package life.qbic.model.beans;

/**
 * @author jmueller
 * Represents an RNA-seq graph ("wiggle") file (*.gr)
 */
public class GraphFileBean {
    private String name, creationDate, path;
    private int sizeInKB;

    @Override
    public String toString() {
        return name + " (" + creationDate + ", " + sizeInKB + "kB)";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getSizeInKB() {
        return sizeInKB;
    }

    public void setSizeInKB(int sizeInKB) {
        this.sizeInKB = sizeInKB;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
