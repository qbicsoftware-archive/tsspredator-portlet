package life.qbic.model.config;

import java.util.ArrayList;

/**
 * An object of this class contains all the information about a dataset that are written to the config file
 * This includes the name of the genome, the paths to its fasta and alignment file, its alignment id and
 * a list of the genome's Replicates (see Replicate class)
 * @author jmueller
 */
public class Dataset {
    private String name;
    private String fasta; //Only set if it's a genome and not a condition
    private String gff; //Only set if it's a genome and not a condition
    private String alignmentID;
    private ArrayList<Replicate> replicateList;

    public Dataset(){
        replicateList = new ArrayList<>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFasta() {
        return fasta;
    }

    public void setFasta(String fasta) {
        this.fasta = fasta;
    }

    public String getAlignmentID() {
        return alignmentID;
    }

    public void setAlignmentID(String alignmentID) {
        this.alignmentID = alignmentID;
    }

    public String getGff() {
        return gff;
    }

    public void setGff(String gff) {
        this.gff = gff;
    }

    public ArrayList<Replicate> getReplicateList() {
        return replicateList;
    }

}
