package life.qbic.testing;

import life.qbic.model.beans.*;

import java.util.LinkedList;
import java.util.List;

public class TestData {

    public static List<ProjectBean> createProjectBeanList() {
        List<ProjectBean> projectBeanList = new LinkedList<>();

        ProjectBean campylobacterProject = new ProjectBean();
        campylobacterProject.setName("Campylobacter2013");
        campylobacterProject.setRegistrationDate("05-12-2017");
        projectBeanList.add(campylobacterProject);

        ProjectBean streptomycesProject = new ProjectBean();
        streptomycesProject.setName("Streptomyces2017");
        streptomycesProject.setRegistrationDate("05-12-2017");
        projectBeanList.add(streptomycesProject);

        return projectBeanList;
    }

    public static List<AlignmentFileBean> createAlignmentFileBeanList() {
        List<AlignmentFileBean> alignmentFileBeanList = new LinkedList<>();

        AlignmentFileBean campylobacter_alignment = new AlignmentFileBean();
        campylobacter_alignment.setName("alignment_campylobacter.xmfa");
        campylobacter_alignment.setSizeInKB(8370);
        campylobacter_alignment.setCreationDate("05-12-2017");
        alignmentFileBeanList.add(campylobacter_alignment);

        return alignmentFileBeanList;
    }

    public static List<FastaFileBean> createFastaFileBeanList() {
        List<FastaFileBean> fastaFileBeanList = new LinkedList<>();

        FastaFileBean nc_002163 = new FastaFileBean();
        nc_002163.setName("NC_002163.fa");
        nc_002163.setCreationDate("05-12-2017");
        nc_002163.setSizeInKB(1665);
        fastaFileBeanList.add(nc_002163);

        FastaFileBean nc_003912 = new FastaFileBean();
        nc_003912.setName("NC_003912.fa");
        nc_003912.setCreationDate("05-12-2017");
        nc_003912.setSizeInKB(1803);
        fastaFileBeanList.add(nc_003912);

        FastaFileBean nc_008787 = new FastaFileBean();
        nc_008787.setName("NC_008787.fa");
        nc_008787.setCreationDate("05-12-2017");
        nc_008787.setSizeInKB(1640);
        fastaFileBeanList.add(nc_008787);

        FastaFileBean nc_009839 = new FastaFileBean();
        nc_009839.setName("NC_009839.fa");
        nc_009839.setCreationDate("05-12-2017");
        nc_009839.setSizeInKB(1675);
        fastaFileBeanList.add(nc_009839);

        return fastaFileBeanList;
    }

    public static List<AnnotationFileBean> createAnnotationFileBeanList() {
        List<AnnotationFileBean> annotationFileBeanList = new LinkedList<>();

        AnnotationFileBean nc_002173_annotation = new AnnotationFileBean();
        nc_002173_annotation.setName("NC_002163_full.gff");
        nc_002173_annotation.setCreationDate("05-12-2017");
        nc_002173_annotation.setSizeInKB(6503);
        annotationFileBeanList.add(nc_002173_annotation);

        AnnotationFileBean nc_003912_annotation = new AnnotationFileBean();
        nc_003912_annotation.setName("NC_003912_full.gff");
        nc_003912_annotation.setCreationDate("05-12-2017");
        nc_003912_annotation.setSizeInKB(2615);
        annotationFileBeanList.add(nc_003912_annotation);

        AnnotationFileBean nc_008787_annotation = new AnnotationFileBean();
        nc_008787_annotation.setName("NC_008787_full.gff");
        nc_008787_annotation.setCreationDate("05-12-2017");
        nc_008787_annotation.setSizeInKB(2511);
        annotationFileBeanList.add(nc_008787_annotation);

        AnnotationFileBean nc_009839_annotation = new AnnotationFileBean();
        nc_009839_annotation.setName("NC_009839_full.gff");
        nc_009839_annotation.setCreationDate("05-12-2017");
        nc_009839_annotation.setSizeInKB(4580);
        annotationFileBeanList.add(nc_009839_annotation);


        return annotationFileBeanList;
    }


    public static List<GraphFileBean> createGraphFileBeanList() {
        List<GraphFileBean> graphFileBeanList = new LinkedList<>();

        GraphFileBean nc_009839_R1_e_minus = new GraphFileBean();
        nc_009839_R1_e_minus.setName("81116_R1_enriched_NC_009839_minus.gr");
        nc_009839_R1_e_minus.setCreationDate("05-12-2017");
        nc_009839_R1_e_minus.setSizeInKB(20128);
        graphFileBeanList.add(nc_009839_R1_e_minus);

        GraphFileBean nc_009839_R1_e_plus = new GraphFileBean();
        nc_009839_R1_e_plus.setName("81116_R1_enriched_NC_009839_plus.gr");
        nc_009839_R1_e_plus.setCreationDate("05-12-2017");
        nc_009839_R1_e_plus.setSizeInKB(18031);
        graphFileBeanList.add(nc_009839_R1_e_plus);

        GraphFileBean nc_009839_R1_n_minus = new GraphFileBean();
        nc_009839_R1_n_minus.setName("81116_R1_normal_NC_009839_minus.gr");
        nc_009839_R1_n_minus.setCreationDate("05-12-2017");
        nc_009839_R1_n_minus.setSizeInKB(26795);
        graphFileBeanList.add(nc_009839_R1_n_minus);

        GraphFileBean nc_009839_R1_n_plus = new GraphFileBean();
        nc_009839_R1_n_plus.setName("81116_R1_normal_NC_009839_plus.gr");
        nc_009839_R1_n_plus.setCreationDate("05-12-2017");
        nc_009839_R1_n_plus.setSizeInKB(24292);
        graphFileBeanList.add(nc_009839_R1_n_plus);


        GraphFileBean nc_009839_R2_e_minus = new GraphFileBean();
        nc_009839_R2_e_minus.setName("81116_R2_enriched_NC_009839_minus.gr");
        nc_009839_R2_e_minus.setCreationDate("05-12-2017");
        nc_009839_R2_e_minus.setSizeInKB(22922);
        graphFileBeanList.add(nc_009839_R2_e_minus);

        GraphFileBean nc_009839_R2_e_plus = new GraphFileBean();
        nc_009839_R2_e_plus.setName("81116_R2_enriched_NC_009839_plus.gr");
        nc_009839_R2_e_plus.setCreationDate("05-12-2017");
        nc_009839_R2_e_plus.setSizeInKB(20383);
        graphFileBeanList.add(nc_009839_R2_e_plus);

        GraphFileBean nc_009839_R2_n_minus = new GraphFileBean();
        nc_009839_R2_n_minus.setName("81116_R2_normal_NC_009839_minus.gr");
        nc_009839_R2_n_minus.setCreationDate("05-12-2017");
        nc_009839_R2_n_minus.setSizeInKB(25681);
        graphFileBeanList.add(nc_009839_R2_n_minus);

        GraphFileBean nc_009839_R2_n_plus = new GraphFileBean();
        nc_009839_R2_n_plus.setName("81116_R2_normal_NC_009839_plus.gr");
        nc_009839_R2_n_plus.setCreationDate("05-12-2017");
        nc_009839_R2_n_plus.setSizeInKB(23047);
        graphFileBeanList.add(nc_009839_R2_n_plus);


        GraphFileBean nc_008787_R1_e_minus = new GraphFileBean();
        nc_008787_R1_e_minus.setName("81-176_R1_enriched_NC_008787_minus.gr");
        nc_008787_R1_e_minus.setCreationDate("05-12-2017");
        nc_008787_R1_e_minus.setSizeInKB(9553);
        graphFileBeanList.add(nc_008787_R1_e_minus);

        GraphFileBean nc_008787_R1_e_plus = new GraphFileBean();
        nc_008787_R1_e_plus.setName("81-176_R1_enriched_NC_008787_plus.gr");
        nc_008787_R1_e_plus.setCreationDate("05-12-2017");
        nc_008787_R1_e_plus.setSizeInKB(8435);
        graphFileBeanList.add(nc_008787_R1_e_plus);

        GraphFileBean nc_008787_R1_n_minus = new GraphFileBean();
        nc_008787_R1_n_minus.setName("81-176_R1_normal_NC_008787_minus.gr");
        nc_008787_R1_n_minus.setCreationDate("05-12-2017");
        nc_008787_R1_n_minus.setSizeInKB(27118);
        graphFileBeanList.add(nc_008787_R1_n_minus);

        GraphFileBean nc_008787_R1_n_plus = new GraphFileBean();
        nc_008787_R1_n_plus.setName("81-176_R1_normal_NC_008787_plus.gr");
        nc_008787_R1_n_plus.setCreationDate("05-12-2017");
        nc_008787_R1_n_plus.setSizeInKB(24913);
        graphFileBeanList.add(nc_008787_R1_n_plus);


        GraphFileBean nc_008787_R2_e_minus = new GraphFileBean();
        nc_008787_R2_e_minus.setName("81-176_R2_enriched_NC_008787_minus.gr");
        nc_008787_R2_e_minus.setCreationDate("05-12-2017");
        nc_008787_R2_e_minus.setSizeInKB(20177);
        graphFileBeanList.add(nc_008787_R2_e_minus);


        GraphFileBean nc_008787_R2_e_plus = new GraphFileBean();
        nc_008787_R2_e_plus.setName("81-176_R2_enriched_NC_008787_plus.gr");
        nc_008787_R2_e_plus.setCreationDate("05-12-2017");
        nc_008787_R2_e_plus.setSizeInKB(18358);
        graphFileBeanList.add(nc_008787_R2_e_plus);

        GraphFileBean nc_008787_R2_n_minus = new GraphFileBean();
        nc_008787_R2_n_minus.setName("81-176_R2_normal_NC_008787_minus.gr");
        nc_008787_R2_n_minus.setCreationDate("05-12-2017");
        nc_008787_R2_n_minus.setSizeInKB(26210);
        graphFileBeanList.add(nc_008787_R2_n_minus);

        GraphFileBean nc_008787_R2_n_plus = new GraphFileBean();
        nc_008787_R2_n_plus.setName("81-176_R2_normal_NC_008787_plus.gr");
        nc_008787_R2_n_plus.setCreationDate("05-12-2017");
        nc_008787_R2_n_plus.setSizeInKB(24106);
        graphFileBeanList.add(nc_008787_R2_n_plus);


        GraphFileBean nc_002163_R1_e_minus = new GraphFileBean();
        nc_002163_R1_e_minus.setName("NCTC11168_R1_enriched_NC_002163_minus.gr");
        nc_002163_R1_e_minus.setCreationDate("05-12-2017");
        nc_002163_R1_e_minus.setSizeInKB(23625);
        graphFileBeanList.add(nc_002163_R1_e_minus);

        GraphFileBean nc_002163_R1_e_plus = new GraphFileBean();
        nc_002163_R1_e_plus.setName("NCTC11168_R1_enriched_NC_002163_plus.gr");
        nc_002163_R1_e_plus.setCreationDate("05-12-2017");
        nc_002163_R1_e_plus.setSizeInKB(20816);
        graphFileBeanList.add(nc_002163_R1_e_plus);

        GraphFileBean nc_002163_R1_n_minus = new GraphFileBean();
        nc_002163_R1_n_minus.setName("NCTC11168_R1_normal_NC_002163_minus.gr");
        nc_002163_R1_n_minus.setCreationDate("05-12-2017");
        nc_002163_R1_n_minus.setSizeInKB(25549);
        graphFileBeanList.add(nc_002163_R1_n_minus);

        GraphFileBean nc_002163_R1_n_plus = new GraphFileBean();
        nc_002163_R1_n_plus.setName("NCTC11168_R1_normal_NC_002163_plus.gr");
        nc_002163_R1_n_plus.setCreationDate("05-12-2017");
        nc_002163_R1_n_plus.setSizeInKB(22685);
        graphFileBeanList.add(nc_002163_R1_n_plus);


        GraphFileBean nc_002163_R2_e_minus = new GraphFileBean();
        nc_002163_R2_e_minus.setName("NCTC11168_R2_enriched_NC_002163_minus.gr");
        nc_002163_R2_e_minus.setCreationDate("05-12-2017");
        nc_002163_R2_e_minus.setSizeInKB(25501);
        graphFileBeanList.add(nc_002163_R2_e_minus);

        GraphFileBean nc_002163_R2_e_plus = new GraphFileBean();
        nc_002163_R2_e_plus.setName("NCTC11168_R2_enriched_NC_002163_plus.gr");
        nc_002163_R2_e_plus.setCreationDate("05-12-2017");
        nc_002163_R2_e_plus.setSizeInKB(22749);
        graphFileBeanList.add(nc_002163_R2_e_plus);

        GraphFileBean nc_002163_R2_n_minus = new GraphFileBean();
        nc_002163_R2_n_minus.setName("NCTC11168_R2_normal_NC_002163_minus.gr");
        nc_002163_R2_n_minus.setCreationDate("05-12-2017");
        nc_002163_R2_n_minus.setSizeInKB(25434);
        graphFileBeanList.add(nc_002163_R2_n_minus);

        GraphFileBean nc_002163_R2_n_plus = new GraphFileBean();
        nc_002163_R2_n_plus.setName("NCTC11168_R2_normal_NC_002163_plus.gr");
        nc_002163_R2_n_plus.setCreationDate("05-12-2017");
        nc_002163_R2_n_plus.setSizeInKB(22568);
        graphFileBeanList.add(nc_002163_R2_n_plus);

        GraphFileBean nc_003912_R1_e_minus = new GraphFileBean();
        nc_003912_R1_e_minus.setName("RM1221_R1_enriched_NC_003912_minus.gr");
        nc_003912_R1_e_minus.setCreationDate("05-12-2017");
        nc_003912_R1_e_minus.setSizeInKB(24154);
        graphFileBeanList.add(nc_003912_R1_e_minus);

        GraphFileBean nc_003912_R1_e_plus = new GraphFileBean();
        nc_003912_R1_e_plus.setName("RM1221_R1_enriched_NC_003912_plus.gr");
        nc_003912_R1_e_plus.setCreationDate("05-12-2017");
        nc_003912_R1_e_plus.setSizeInKB(21743);
        graphFileBeanList.add(nc_003912_R1_e_plus);

        GraphFileBean nc_003912_R1_n_minus = new GraphFileBean();
        nc_003912_R1_n_minus.setName("RM1221_R1_normal_NC_003912_minus.gr");
        nc_003912_R1_n_minus.setCreationDate("05-12-2017");
        nc_003912_R1_n_minus.setSizeInKB(28996);
        graphFileBeanList.add(nc_003912_R1_n_minus);

        GraphFileBean nc_003912_R1_n_plus = new GraphFileBean();
        nc_003912_R1_n_plus.setName("RM1221_R1_normal_NC_003912_plus.gr");
        nc_003912_R1_n_plus.setCreationDate("05-12-2017");
        nc_003912_R1_n_plus.setSizeInKB(26295);
        graphFileBeanList.add(nc_003912_R1_n_plus);

        GraphFileBean nc_003912_R2_e_minus = new GraphFileBean();
        nc_003912_R2_e_minus.setName("RM1221_R2_enriched_NC_003912_minus.gr");
        nc_003912_R2_e_minus.setCreationDate("05-12-2017");
        nc_003912_R2_e_minus.setSizeInKB(26250);
        graphFileBeanList.add(nc_003912_R2_e_minus);

        GraphFileBean nc_003912_R2_e_plus = new GraphFileBean();
        nc_003912_R2_e_plus.setName("RM1221_R2_enriched_NC_003912_plus.gr");
        nc_003912_R2_e_plus.setCreationDate("05-12-2017");
        nc_003912_R2_e_plus.setSizeInKB(21993);
        graphFileBeanList.add(nc_003912_R2_e_plus);

        GraphFileBean nc_003912_R2_n_minus = new GraphFileBean();
        nc_003912_R2_n_minus.setName("RM1221_R2_normal_NC_003912_minus.gr");
        nc_003912_R2_n_minus.setCreationDate("05-12-2017");
        nc_003912_R2_n_minus.setSizeInKB(28842);
        graphFileBeanList.add(nc_003912_R2_n_minus);

        GraphFileBean nc_003912_R2_n_plus = new GraphFileBean();
        nc_003912_R2_n_plus.setName("RM1221_R2_normal_NC_003912_plus.gr");
        nc_003912_R2_n_plus.setCreationDate("05-12-2017");
        nc_003912_R2_n_plus.setSizeInKB(26250);
        graphFileBeanList.add(nc_003912_R2_n_plus);


        return graphFileBeanList;
    }
}
