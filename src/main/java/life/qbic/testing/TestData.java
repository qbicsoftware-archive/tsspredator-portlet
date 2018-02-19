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

        ProjectBean staphylococcusProject = new ProjectBean();
        staphylococcusProject.setName("Staphylococcus2017");
        staphylococcusProject.setRegistrationDate("12-02-2018");
        projectBeanList.add(staphylococcusProject);

        return projectBeanList;
    }

    public static List<AlignmentFileBean> createAlignmentFileBeanList() {
        List<AlignmentFileBean> alignmentFileBeanList = new LinkedList<>();

        AlignmentFileBean campylobacter_alignment = new AlignmentFileBean();
        campylobacter_alignment.setName("alignment_campylobacter.xmfa");
        campylobacter_alignment.setSizeInKB(8370);
        campylobacter_alignment.setCreationDate("05-12-2017");
        campylobacter_alignment.setPath("/home/julian/tsspredator_test/campylobacter_data/alignment_campylobacter.xmfa");
        alignmentFileBeanList.add(campylobacter_alignment);

        return alignmentFileBeanList;
    }

    public static List<FastaFileBean> createFastaFileBeanList() {
        List<FastaFileBean> fastaFileBeanList = new LinkedList<>();

        FastaFileBean nc_002163 = new FastaFileBean();
        nc_002163.setName("NC_002163.fa");
        nc_002163.setCreationDate("05-12-2017");
        nc_002163.setSizeInKB(1665);
        nc_002163.setPath("/home/julian/tsspredator_test/campylobacter_data/fasta/NC_002163.fa");
        fastaFileBeanList.add(nc_002163);

        FastaFileBean nc_003912 = new FastaFileBean();
        nc_003912.setName("NC_003912.fa");
        nc_003912.setCreationDate("05-12-2017");
        nc_003912.setSizeInKB(1803);
        nc_003912.setPath("/home/julian/tsspredator_test/campylobacter_data/fasta/NC_003912.fa");
        fastaFileBeanList.add(nc_003912);

        FastaFileBean nc_008787 = new FastaFileBean();
        nc_008787.setName("NC_008787.fa");
        nc_008787.setCreationDate("05-12-2017");
        nc_008787.setSizeInKB(1640);
        nc_008787.setPath("/home/julian/tsspredator_test/campylobacter_data/fasta/NC_008787.fa");
        fastaFileBeanList.add(nc_008787);

        FastaFileBean nc_009839 = new FastaFileBean();
        nc_009839.setName("NC_009839.fa");
        nc_009839.setCreationDate("05-12-2017");
        nc_009839.setSizeInKB(1675);
        nc_009839.setPath("/home/julian/tsspredator_test/campylobacter_data/fasta/NC_009839.fa");
        fastaFileBeanList.add(nc_009839);

        FastaFileBean nc_009641 = new FastaFileBean();
        nc_009641.setName("NC_009641.fa");
        nc_009641.setCreationDate("12-02-2018");
        nc_009641.setSizeInKB(2920);
        nc_009641.setPath("/home/julian/tsspredator_test/staphylococcus_data/genome_anno/NC_009641.fa");
        fastaFileBeanList.add(nc_009641);

        return fastaFileBeanList;
    }

    public static List<AnnotationFileBean> createAnnotationFileBeanList() {
        List<AnnotationFileBean> annotationFileBeanList = new LinkedList<>();

        AnnotationFileBean nc_002173_annotation = new AnnotationFileBean();
        nc_002173_annotation.setName("NC_002163_full.gff");
        nc_002173_annotation.setCreationDate("05-12-2017");
        nc_002173_annotation.setSizeInKB(6503);
        nc_002173_annotation.setPath("/home/julian/tsspredator_test/campylobacter_data/gff/NC_002163_full.gff");
        annotationFileBeanList.add(nc_002173_annotation);

        AnnotationFileBean nc_003912_annotation = new AnnotationFileBean();
        nc_003912_annotation.setName("NC_003912_full.gff");
        nc_003912_annotation.setCreationDate("05-12-2017");
        nc_003912_annotation.setSizeInKB(2615);
        nc_003912_annotation.setPath("/home/julian/tsspredator_test/campylobacter_data/gff/NC_003912_full.gff");
        annotationFileBeanList.add(nc_003912_annotation);

        AnnotationFileBean nc_008787_annotation = new AnnotationFileBean();
        nc_008787_annotation.setName("NC_008787_full.gff");
        nc_008787_annotation.setCreationDate("05-12-2017");
        nc_008787_annotation.setSizeInKB(2511);
        nc_008787_annotation.setPath("/home/julian/tsspredator_test/campylobacter_data/gff/NC_008787_full.gff");
        annotationFileBeanList.add(nc_008787_annotation);

        AnnotationFileBean nc_009839_annotation = new AnnotationFileBean();
        nc_009839_annotation.setName("NC_009839_full.gff");
        nc_009839_annotation.setCreationDate("05-12-2017");
        nc_009839_annotation.setSizeInKB(4580);
        nc_009839_annotation.setPath("/home/julian/tsspredator_test/campylobacter_data/gff/NC_009839_full.gff");
        annotationFileBeanList.add(nc_009839_annotation);

        AnnotationFileBean nc_009641_annotation = new AnnotationFileBean();
        nc_009641_annotation.setName("NC_009641.gff");
        nc_009641_annotation.setCreationDate("12-02-2018");
        nc_009641_annotation.setSizeInKB(1101);
        nc_009641_annotation.setPath("/home/julian/tsspredator_test/staphylococcus_data/genome_anno/NC_009641.gff");
        annotationFileBeanList.add(nc_009641_annotation);

        return annotationFileBeanList;
    }


    public static List<GraphFileBean> createGraphFileBeanList() {
        List<GraphFileBean> graphFileBeanList = new LinkedList<>();

        //Campylobacter Graph Files
        GraphFileBean nc_009839_R1_e_minus = new GraphFileBean();
        nc_009839_R1_e_minus.setName("81116_R1_enriched_NC_009839_minus.gr");
        nc_009839_R1_e_minus.setCreationDate("05-12-2017");
        nc_009839_R1_e_minus.setSizeInKB(20128);
        nc_009839_R1_e_minus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/81116_R1_enriched_NC_009839_minus.gr");
        graphFileBeanList.add(nc_009839_R1_e_minus);


        GraphFileBean nc_009839_R1_e_plus = new GraphFileBean();
        nc_009839_R1_e_plus.setName("81116_R1_enriched_NC_009839_plus.gr");
        nc_009839_R1_e_plus.setCreationDate("05-12-2017");
        nc_009839_R1_e_plus.setSizeInKB(18031);
        nc_009839_R1_e_plus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/81116_R1_enriched_NC_009839_plus.gr");
        graphFileBeanList.add(nc_009839_R1_e_plus);

        GraphFileBean nc_009839_R1_n_minus = new GraphFileBean();
        nc_009839_R1_n_minus.setName("81116_R1_normal_NC_009839_minus.gr");
        nc_009839_R1_n_minus.setCreationDate("05-12-2017");
        nc_009839_R1_n_minus.setSizeInKB(26795);
        nc_009839_R1_n_minus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/81116_R1_normal_NC_009839_minus.gr");
        graphFileBeanList.add(nc_009839_R1_n_minus);


        GraphFileBean nc_009839_R1_n_plus = new GraphFileBean();
        nc_009839_R1_n_plus.setName("81116_R1_normal_NC_009839_plus.gr");
        nc_009839_R1_n_plus.setCreationDate("05-12-2017");
        nc_009839_R1_n_plus.setSizeInKB(24292);
        nc_009839_R1_n_plus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/81116_R1_normal_NC_009839_plus.gr");
        graphFileBeanList.add(nc_009839_R1_n_plus);


        GraphFileBean nc_009839_R2_e_minus = new GraphFileBean();
        nc_009839_R2_e_minus.setName("81116_R2_enriched_NC_009839_minus.gr");
        nc_009839_R2_e_minus.setCreationDate("05-12-2017");
        nc_009839_R2_e_minus.setSizeInKB(22922);
        nc_009839_R2_e_minus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/81116_R2_enriched_NC_009839_minus.gr");
        graphFileBeanList.add(nc_009839_R2_e_minus);

        GraphFileBean nc_009839_R2_e_plus = new GraphFileBean();
        nc_009839_R2_e_plus.setName("81116_R2_enriched_NC_009839_plus.gr");
        nc_009839_R2_e_plus.setCreationDate("05-12-2017");
        nc_009839_R2_e_plus.setSizeInKB(20383);
        nc_009839_R2_e_plus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/81116_R2_enriched_NC_009839_plus.gr");
        graphFileBeanList.add(nc_009839_R2_e_plus);

        GraphFileBean nc_009839_R2_n_minus = new GraphFileBean();
        nc_009839_R2_n_minus.setName("81116_R2_normal_NC_009839_minus.gr");
        nc_009839_R2_n_minus.setCreationDate("05-12-2017");
        nc_009839_R2_n_minus.setSizeInKB(25681);
        nc_009839_R2_n_minus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/81116_R2_normal_NC_009839_minus.gr");
        graphFileBeanList.add(nc_009839_R2_n_minus);

        GraphFileBean nc_009839_R2_n_plus = new GraphFileBean();
        nc_009839_R2_n_plus.setName("81116_R2_normal_NC_009839_plus.gr");
        nc_009839_R2_n_plus.setCreationDate("05-12-2017");
        nc_009839_R2_n_plus.setSizeInKB(23047);
        nc_009839_R2_n_plus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/81116_R2_normal_NC_009839_plus.gr");
        graphFileBeanList.add(nc_009839_R2_n_plus);


        GraphFileBean nc_008787_R1_e_minus = new GraphFileBean();
        nc_008787_R1_e_minus.setName("81-176_R1_enriched_NC_008787_minus.gr");
        nc_008787_R1_e_minus.setCreationDate("05-12-2017");
        nc_008787_R1_e_minus.setSizeInKB(9553);
        nc_008787_R1_e_minus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/81-176_R1_enriched_NC_008787_minus.gr");
        graphFileBeanList.add(nc_008787_R1_e_minus);

        GraphFileBean nc_008787_R1_e_plus = new GraphFileBean();
        nc_008787_R1_e_plus.setName("81-176_R1_enriched_NC_008787_plus.gr");
        nc_008787_R1_e_plus.setCreationDate("05-12-2017");
        nc_008787_R1_e_plus.setSizeInKB(8435);
        nc_008787_R1_e_plus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/81-176_R1_enriched_NC_008787_plus.gr");
        graphFileBeanList.add(nc_008787_R1_e_plus);

        GraphFileBean nc_008787_R1_n_minus = new GraphFileBean();
        nc_008787_R1_n_minus.setName("81-176_R1_normal_NC_008787_minus.gr");
        nc_008787_R1_n_minus.setCreationDate("05-12-2017");
        nc_008787_R1_n_minus.setSizeInKB(27118);
        nc_008787_R1_n_minus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/81-176_R1_normal_NC_008787_minus.gr");
        graphFileBeanList.add(nc_008787_R1_n_minus);

        GraphFileBean nc_008787_R1_n_plus = new GraphFileBean();
        nc_008787_R1_n_plus.setName("81-176_R1_normal_NC_008787_plus.gr");
        nc_008787_R1_n_plus.setCreationDate("05-12-2017");
        nc_008787_R1_n_plus.setSizeInKB(24913);
        nc_008787_R1_n_plus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/81-176_R1_normal_NC_008787_plus.gr");
        graphFileBeanList.add(nc_008787_R1_n_plus);


        GraphFileBean nc_008787_R2_e_minus = new GraphFileBean();
        nc_008787_R2_e_minus.setName("81-176_R2_enriched_NC_008787_minus.gr");
        nc_008787_R2_e_minus.setCreationDate("05-12-2017");
        nc_008787_R2_e_minus.setSizeInKB(20177);
        nc_008787_R2_e_minus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/81-176_R2_enriched_NC_008787_minus.gr");
        graphFileBeanList.add(nc_008787_R2_e_minus);


        GraphFileBean nc_008787_R2_e_plus = new GraphFileBean();
        nc_008787_R2_e_plus.setName("81-176_R2_enriched_NC_008787_plus.gr");
        nc_008787_R2_e_plus.setCreationDate("05-12-2017");
        nc_008787_R2_e_plus.setSizeInKB(18358);
        nc_008787_R2_e_plus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/81-176_R2_enriched_NC_008787_plus.gr");
        graphFileBeanList.add(nc_008787_R2_e_plus);

        GraphFileBean nc_008787_R2_n_minus = new GraphFileBean();
        nc_008787_R2_n_minus.setName("81-176_R2_normal_NC_008787_minus.gr");
        nc_008787_R2_n_minus.setCreationDate("05-12-2017");
        nc_008787_R2_n_minus.setSizeInKB(26210);
        nc_008787_R2_n_minus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/81-176_R2_normal_NC_008787_minus.gr");
        graphFileBeanList.add(nc_008787_R2_n_minus);

        GraphFileBean nc_008787_R2_n_plus = new GraphFileBean();
        nc_008787_R2_n_plus.setName("81-176_R2_normal_NC_008787_plus.gr");
        nc_008787_R2_n_plus.setCreationDate("05-12-2017");
        nc_008787_R2_n_plus.setSizeInKB(24106);
        nc_008787_R2_n_plus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/81-176_R2_normal_NC_008787_plus.gr");
        graphFileBeanList.add(nc_008787_R2_n_plus);


        GraphFileBean nc_002163_R1_e_minus = new GraphFileBean();
        nc_002163_R1_e_minus.setName("NCTC11168_R1_enriched_NC_002163_minus.gr");
        nc_002163_R1_e_minus.setCreationDate("05-12-2017");
        nc_002163_R1_e_minus.setSizeInKB(23625);
        nc_002163_R1_e_minus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/NCTC11168_R1_enriched_NC_002163_minus.gr");
        graphFileBeanList.add(nc_002163_R1_e_minus);

        GraphFileBean nc_002163_R1_e_plus = new GraphFileBean();
        nc_002163_R1_e_plus.setName("NCTC11168_R1_enriched_NC_002163_plus.gr");
        nc_002163_R1_e_plus.setCreationDate("05-12-2017");
        nc_002163_R1_e_plus.setSizeInKB(20816);
        nc_002163_R1_e_plus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/NCTC11168_R1_enriched_NC_002163_plus.gr");
        graphFileBeanList.add(nc_002163_R1_e_plus);

        GraphFileBean nc_002163_R1_n_minus = new GraphFileBean();
        nc_002163_R1_n_minus.setName("NCTC11168_R1_normal_NC_002163_minus.gr");
        nc_002163_R1_n_minus.setCreationDate("05-12-2017");
        nc_002163_R1_n_minus.setSizeInKB(25549);
        nc_002163_R1_n_minus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/NCTC11168_R1_normal_NC_002163_minus.gr");
        graphFileBeanList.add(nc_002163_R1_n_minus);

        GraphFileBean nc_002163_R1_n_plus = new GraphFileBean();
        nc_002163_R1_n_plus.setName("NCTC11168_R1_normal_NC_002163_plus.gr");
        nc_002163_R1_n_plus.setCreationDate("05-12-2017");
        nc_002163_R1_n_plus.setSizeInKB(22685);
        nc_002163_R1_n_plus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/NCTC11168_R1_normal_NC_002163_plus.gr");
        graphFileBeanList.add(nc_002163_R1_n_plus);


        GraphFileBean nc_002163_R2_e_minus = new GraphFileBean();
        nc_002163_R2_e_minus.setName("NCTC11168_R2_enriched_NC_002163_minus.gr");
        nc_002163_R2_e_minus.setCreationDate("05-12-2017");
        nc_002163_R2_e_minus.setSizeInKB(25501);
        nc_002163_R2_e_minus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/NCTC11168_R2_enriched_NC_002163_minus.gr");
        graphFileBeanList.add(nc_002163_R2_e_minus);

        GraphFileBean nc_002163_R2_e_plus = new GraphFileBean();
        nc_002163_R2_e_plus.setName("NCTC11168_R2_enriched_NC_002163_plus.gr");
        nc_002163_R2_e_plus.setCreationDate("05-12-2017");
        nc_002163_R2_e_plus.setSizeInKB(22749);
        nc_002163_R2_e_plus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/NCTC11168_R2_enriched_NC_002163_plus.gr");
        graphFileBeanList.add(nc_002163_R2_e_plus);

        GraphFileBean nc_002163_R2_n_minus = new GraphFileBean();
        nc_002163_R2_n_minus.setName("NCTC11168_R2_normal_NC_002163_minus.gr");
        nc_002163_R2_n_minus.setCreationDate("05-12-2017");
        nc_002163_R2_n_minus.setSizeInKB(25434);
        nc_002163_R2_n_minus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/NCTC11168_R2_normal_NC_002163_minus.gr");
        graphFileBeanList.add(nc_002163_R2_n_minus);

        GraphFileBean nc_002163_R2_n_plus = new GraphFileBean();
        nc_002163_R2_n_plus.setName("NCTC11168_R2_normal_NC_002163_plus.gr");
        nc_002163_R2_n_plus.setCreationDate("05-12-2017");
        nc_002163_R2_n_plus.setSizeInKB(22568);
        nc_002163_R2_n_plus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/NCTC11168_R2_normal_NC_002163_plus.gr");
        graphFileBeanList.add(nc_002163_R2_n_plus);

        GraphFileBean nc_003912_R1_e_minus = new GraphFileBean();
        nc_003912_R1_e_minus.setName("RM1221_R1_enriched_NC_003912_minus.gr");
        nc_003912_R1_e_minus.setCreationDate("05-12-2017");
        nc_003912_R1_e_minus.setSizeInKB(24154);
        nc_003912_R1_e_minus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/RM1221_R1_enriched_NC_003912_minus.gr");
        graphFileBeanList.add(nc_003912_R1_e_minus);

        GraphFileBean nc_003912_R1_e_plus = new GraphFileBean();
        nc_003912_R1_e_plus.setName("RM1221_R1_enriched_NC_003912_plus.gr");
        nc_003912_R1_e_plus.setCreationDate("05-12-2017");
        nc_003912_R1_e_plus.setSizeInKB(21743);
        nc_003912_R1_e_plus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/RM1221_R1_enriched_NC_003912_plus.gr");
        graphFileBeanList.add(nc_003912_R1_e_plus);

        GraphFileBean nc_003912_R1_n_minus = new GraphFileBean();
        nc_003912_R1_n_minus.setName("RM1221_R1_normal_NC_003912_minus.gr");
        nc_003912_R1_n_minus.setCreationDate("05-12-2017");
        nc_003912_R1_n_minus.setSizeInKB(28996);
        nc_003912_R1_n_minus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/RM1221_R1_normal_NC_003912_minus.gr");
        graphFileBeanList.add(nc_003912_R1_n_minus);

        GraphFileBean nc_003912_R1_n_plus = new GraphFileBean();
        nc_003912_R1_n_plus.setName("RM1221_R1_normal_NC_003912_plus.gr");
        nc_003912_R1_n_plus.setCreationDate("05-12-2017");
        nc_003912_R1_n_plus.setSizeInKB(26295);
        nc_003912_R1_n_plus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/RM1221_R1_normal_NC_003912_plus.gr");
        graphFileBeanList.add(nc_003912_R1_n_plus);

        GraphFileBean nc_003912_R2_e_minus = new GraphFileBean();
        nc_003912_R2_e_minus.setName("RM1221_R2_enriched_NC_003912_minus.gr");
        nc_003912_R2_e_minus.setCreationDate("05-12-2017");
        nc_003912_R2_e_minus.setSizeInKB(26250);
        nc_003912_R2_e_minus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/RM1221_R2_enriched_NC_003912_minus.gr");
        graphFileBeanList.add(nc_003912_R2_e_minus);

        GraphFileBean nc_003912_R2_e_plus = new GraphFileBean();
        nc_003912_R2_e_plus.setName("RM1221_R2_enriched_NC_003912_plus.gr");
        nc_003912_R2_e_plus.setCreationDate("05-12-2017");
        nc_003912_R2_e_plus.setSizeInKB(21993);
        nc_003912_R2_e_plus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/RM1221_R2_enriched_NC_003912_plus.gr");
        graphFileBeanList.add(nc_003912_R2_e_plus);

        GraphFileBean nc_003912_R2_n_minus = new GraphFileBean();
        nc_003912_R2_n_minus.setName("RM1221_R2_normal_NC_003912_minus.gr");
        nc_003912_R2_n_minus.setCreationDate("05-12-2017");
        nc_003912_R2_n_minus.setSizeInKB(28842);
        nc_003912_R2_n_minus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/RM1221_R2_normal_NC_003912_minus.gr");
        graphFileBeanList.add(nc_003912_R2_n_minus);

        GraphFileBean nc_003912_R2_n_plus = new GraphFileBean();
        nc_003912_R2_n_plus.setName("RM1221_R2_normal_NC_003912_plus.gr");
        nc_003912_R2_n_plus.setCreationDate("05-12-2017");
        nc_003912_R2_n_plus.setSizeInKB(26250);
        nc_003912_R2_n_plus.setPath("/home/julian/tsspredator_test/campylobacter_data/graphs/RM1221_R2_normal_NC_003912_plus.gr");
        graphFileBeanList.add(nc_003912_R2_n_plus);



        //Staphylococcus Graph Files

        //path: /home/julian/tsspredator_test/staphylococcus_data/graphs
        GraphFileBean gm_SA_rny1_minus_TEX_forward = new GraphFileBean();
        gm_SA_rny1_minus_TEX_forward.setName("GM_SA_rny1_minus_TEX_forward.fa_div_by_6538401.0_multi_by_5727088.0_forward.wig_GM_SA_rny1_minus_TEX.fa_forward_in_NC_009641.1.gr");
        gm_SA_rny1_minus_TEX_forward.setCreationDate("12-02-2018");
        gm_SA_rny1_minus_TEX_forward.setSizeInKB(31750);
        gm_SA_rny1_minus_TEX_forward.setPath("/home/julian/tsspredator_test/staphylococcus_data/graphs/GM_SA_rny1_minus_TEX_forward.fa_div_by_6538401.0_multi_by_5727088.0_forward.wig_GM_SA_rny1_minus_TEX.fa_forward_in_NC_009641.1.gr");
        graphFileBeanList.add(gm_SA_rny1_minus_TEX_forward);

        GraphFileBean gm_SA_rny1_minus_TEX_reverse = new GraphFileBean();
        gm_SA_rny1_minus_TEX_reverse.setName("GM_SA_rny1_minus_TEX_reverse.fa_div_by_6538401.0_multi_by_5727088.0_reverse.wig_GM_SA_rny1_minus_TEX.fa_reverse_in_NC_009641.1.gr");
        gm_SA_rny1_minus_TEX_reverse.setCreationDate("12-02-2018");
        gm_SA_rny1_minus_TEX_reverse.setSizeInKB(32833);
        gm_SA_rny1_minus_TEX_reverse.setPath("/home/julian/tsspredator_test/staphylococcus_data/graphs/GM_SA_rny1_minus_TEX_reverse.fa_div_by_6538401.0_multi_by_5727088.0_reverse.wig_GM_SA_rny1_minus_TEX.fa_reverse_in_NC_009641.1.gr");
        graphFileBeanList.add(gm_SA_rny1_minus_TEX_reverse);

        GraphFileBean gm_SA_rny1_plus_TEX_forward = new GraphFileBean();
        gm_SA_rny1_plus_TEX_forward.setName("GM_SA_rny1_plus_TEX_forward.fa_div_by_8638586.0_multi_by_5727088.0_forward.wig_GM_SA_rny1_plus_TEX.fa_forward_in_NC_009641.1.gr");
        gm_SA_rny1_plus_TEX_forward.setCreationDate("12-02-2018");
        gm_SA_rny1_plus_TEX_forward.setSizeInKB(8952);
        gm_SA_rny1_plus_TEX_forward.setPath("/home/julian/tsspredator_test/staphylococcus_data/graphs/GM_SA_rny1_plus_TEX_forward.fa_div_by_8638586.0_multi_by_5727088.0_forward.wig_GM_SA_rny1_plus_TEX.fa_forward_in_NC_009641.1.gr");
        graphFileBeanList.add(gm_SA_rny1_plus_TEX_forward);

        GraphFileBean gm_SA_rny1_plus_TEX_reverse = new GraphFileBean();
        gm_SA_rny1_plus_TEX_reverse.setName("GM_SA_rny1_plus_TEX_reverse.fa_div_by_8638586.0_multi_by_5727088.0_reverse.wig_GM_SA_rny1_plus_TEX.fa_reverse_in_NC_009641.1.gr");
        gm_SA_rny1_plus_TEX_reverse.setCreationDate("12-02-2018");
        gm_SA_rny1_plus_TEX_reverse.setSizeInKB(9247);
        gm_SA_rny1_plus_TEX_reverse.setPath("/home/julian/tsspredator_test/staphylococcus_data/graphs/GM_SA_rny1_plus_TEX_reverse.fa_div_by_8638586.0_multi_by_5727088.0_reverse.wig_GM_SA_rny1_plus_TEX.fa_reverse_in_NC_009641.1.gr");
        graphFileBeanList.add(gm_SA_rny1_plus_TEX_reverse);



        GraphFileBean gm_SA_rny3_minus_TEX_forward = new GraphFileBean();
        gm_SA_rny3_minus_TEX_forward.setName("GM_SA_rny3_minus_TEX_forward.fa_div_by_7199910.0_multi_by_5727088.0_forward.wig_GM_SA_rny3_minus_TEX.fa_forward_in_NC_009641.1.gr");
        gm_SA_rny3_minus_TEX_forward.setCreationDate("12-02-2018");
        gm_SA_rny3_minus_TEX_forward.setSizeInKB(33556);
        gm_SA_rny3_minus_TEX_forward.setPath("/home/julian/tsspredator_test/staphylococcus_data/graphs/GM_SA_rny3_minus_TEX_forward.fa_div_by_7199910.0_multi_by_5727088.0_forward.wig_GM_SA_rny3_minus_TEX.fa_forward_in_NC_009641.1.gr");
        graphFileBeanList.add(gm_SA_rny3_minus_TEX_forward);

        GraphFileBean gm_SA_rny3_minus_TEX_reverse = new GraphFileBean();
        gm_SA_rny3_minus_TEX_reverse.setName("GM_SA_rny3_minus_TEX_reverse.fa_div_by_7199910.0_multi_by_5727088.0_reverse.wig_GM_SA_rny3_minus_TEX.fa_reverse_in_NC_009641.1.gr");
        gm_SA_rny3_minus_TEX_reverse.setCreationDate("12-02-2018");
        gm_SA_rny3_minus_TEX_reverse.setSizeInKB(34627);
        gm_SA_rny3_minus_TEX_reverse.setPath("/home/julian/tsspredator_test/staphylococcus_data/graphs/GM_SA_rny3_minus_TEX_reverse.fa_div_by_7199910.0_multi_by_5727088.0_reverse.wig_GM_SA_rny3_minus_TEX.fa_reverse_in_NC_009641.1.gr");
        graphFileBeanList.add(gm_SA_rny3_minus_TEX_reverse);

        GraphFileBean gm_SA_rny3_plus_TEX_forward = new GraphFileBean();
        gm_SA_rny3_plus_TEX_forward.setName("GM_SA_rny3_plus_TEX_forward.fa_div_by_10178348.0_multi_by_5727088.0_forward.wig_GM_SA_rny3_plus_TEX.fa_forward_in_NC_009641.1.gr");
        gm_SA_rny3_plus_TEX_forward.setCreationDate("12-02-2018");
        gm_SA_rny3_plus_TEX_forward.setSizeInKB(24611);
        gm_SA_rny3_plus_TEX_forward.setPath("/home/julian/tsspredator_test/staphylococcus_data/graphs/GM_SA_rny3_plus_TEX_forward.fa_div_by_10178348.0_multi_by_5727088.0_forward.wig_GM_SA_rny3_plus_TEX.fa_forward_in_NC_009641.1.gr");
        graphFileBeanList.add(gm_SA_rny3_plus_TEX_forward);

        GraphFileBean gm_SA_rny3_plus_TEX_reverse = new GraphFileBean();
        gm_SA_rny3_plus_TEX_reverse.setName("GM_SA_rny3_plus_TEX_reverse.fa_div_by_10178348.0_multi_by_5727088.0_reverse.wig_GM_SA_rny3_plus_TEX.fa_reverse_in_NC_009641.1.gr");
        gm_SA_rny3_plus_TEX_reverse.setCreationDate("12-02-2018");
        gm_SA_rny3_plus_TEX_reverse.setSizeInKB(25149);
        gm_SA_rny3_plus_TEX_reverse.setPath("/home/julian/tsspredator_test/staphylococcus_data/graphs/GM_SA_rny3_plus_TEX_reverse.fa_div_by_10178348.0_multi_by_5727088.0_reverse.wig_GM_SA_rny3_plus_TEX.fa_reverse_in_NC_009641.1.gr");
        graphFileBeanList.add(gm_SA_rny3_plus_TEX_reverse);


        GraphFileBean gm_SA_wt2_minus_TEX_forward = new GraphFileBean();
        gm_SA_wt2_minus_TEX_forward.setName("GM_SA_WT2_minus_TEX_forward.fa_div_by_5978747.0_multi_by_5727088.0_forward.wig_GM_SA_WT2_minus_TEX.fa_forward_in_NC_009641.1.gr");
        gm_SA_wt2_minus_TEX_forward.setCreationDate("12-02-2018");
        gm_SA_wt2_minus_TEX_forward.setSizeInKB(30574);
        gm_SA_wt2_minus_TEX_forward.setPath("/home/julian/tsspredator_test/staphylococcus_data/graphs/GM_SA_WT2_minus_TEX_forward.fa_div_by_5978747.0_multi_by_5727088.0_forward.wig_GM_SA_WT2_minus_TEX.fa_forward_in_NC_009641.1.gr");
        graphFileBeanList.add(gm_SA_wt2_minus_TEX_forward);

        GraphFileBean gm_SA_wt2_minus_TEX_reverse = new GraphFileBean();
        gm_SA_wt2_minus_TEX_reverse.setName("GM_SA_WT2_minus_TEX_reverse.fa_div_by_5978747.0_multi_by_5727088.0_reverse.wig_GM_SA_WT2_minus_TEX.fa_reverse_in_NC_009641.1.gr");
        gm_SA_wt2_minus_TEX_reverse.setCreationDate("12-02-2018");
        gm_SA_wt2_minus_TEX_reverse.setSizeInKB(31202);
        gm_SA_wt2_minus_TEX_reverse.setPath("/home/julian/tsspredator_test/staphylococcus_data/graphs/GM_SA_WT2_minus_TEX_reverse.fa_div_by_5978747.0_multi_by_5727088.0_reverse.wig_GM_SA_WT2_minus_TEX.fa_reverse_in_NC_009641.1.gr");
        graphFileBeanList.add(gm_SA_wt2_minus_TEX_reverse);

        GraphFileBean gm_SA_wt2_plus_TEX_forward = new GraphFileBean();
        gm_SA_wt2_plus_TEX_forward.setName("GM_SA_WT2_plus_TEX_forward.fa_div_by_11756179.0_multi_by_5727088.0_forward.wig_GM_SA_WT2_plus_TEX.fa_forward_in_NC_009641.1.gr");
        gm_SA_wt2_plus_TEX_forward.setCreationDate("12-02-2018");
        gm_SA_wt2_plus_TEX_forward.setSizeInKB(28646);
        gm_SA_wt2_plus_TEX_forward.setPath("/home/julian/tsspredator_test/staphylococcus_data/graphs/GM_SA_WT2_plus_TEX_forward.fa_div_by_11756179.0_multi_by_5727088.0_forward.wig_GM_SA_WT2_plus_TEX.fa_forward_in_NC_009641.1.gr");
        graphFileBeanList.add(gm_SA_wt2_plus_TEX_forward);

        GraphFileBean gm_SA_wt2_plus_TEX_reverse = new GraphFileBean();
        gm_SA_wt2_plus_TEX_reverse.setName("GM_SA_WT2_plus_TEX_reverse.fa_div_by_11756179.0_multi_by_5727088.0_reverse.wig_GM_SA_WT2_plus_TEX.fa_reverse_in_NC_009641.1.gr");
        gm_SA_wt2_plus_TEX_reverse.setCreationDate("12-02-2018");
        gm_SA_wt2_plus_TEX_reverse.setSizeInKB(29468);
        gm_SA_wt2_plus_TEX_reverse.setPath("/home/julian/tsspredator_test/staphylococcus_data/graphs/GM_SA_WT2_plus_TEX_reverse.fa_div_by_11756179.0_multi_by_5727088.0_reverse.wig_GM_SA_WT2_plus_TEX.fa_reverse_in_NC_009641.1.gr");
        graphFileBeanList.add(gm_SA_wt2_plus_TEX_reverse);


        GraphFileBean gm_SA_wt3_minus_TEX_forward = new GraphFileBean();
        gm_SA_wt3_minus_TEX_forward.setName("GM_SA_WT3_minus_TEX_forward.fa_div_by_5727088.0_multi_by_5727088.0_forward.wig_GM_SA_WT3_minus_TEX.fa_forward_in_NC_009641.1.gr");
        gm_SA_wt3_minus_TEX_forward.setCreationDate("12-02-2018");
        gm_SA_wt3_minus_TEX_forward.setSizeInKB(17001);
        gm_SA_wt3_minus_TEX_forward.setPath("/home/julian/tsspredator_test/staphylococcus_data/graphs/GM_SA_WT3_minus_TEX_forward.fa_div_by_5727088.0_multi_by_5727088.0_forward.wig_GM_SA_WT3_minus_TEX.fa_forward_in_NC_009641.1.gr");
        graphFileBeanList.add(gm_SA_wt3_minus_TEX_forward);

        GraphFileBean gm_SA_wt3_minus_TEX_reverse = new GraphFileBean();
        gm_SA_wt3_minus_TEX_reverse.setName("GM_SA_WT3_minus_TEX_reverse.fa_div_by_5727088.0_multi_by_5727088.0_reverse.wig_GM_SA_WT3_minus_TEX.fa_reverse_in_NC_009641.1.gr");
        gm_SA_wt3_minus_TEX_reverse.setCreationDate("12-02-2018");
        gm_SA_wt3_minus_TEX_reverse.setSizeInKB(18143);
        gm_SA_wt3_minus_TEX_reverse.setPath("/home/julian/tsspredator_test/staphylococcus_data/graphs/GM_SA_WT3_minus_TEX_reverse.fa_div_by_5727088.0_multi_by_5727088.0_reverse.wig_GM_SA_WT3_minus_TEX.fa_reverse_in_NC_009641.1.gr");
        graphFileBeanList.add(gm_SA_wt3_minus_TEX_reverse);

        GraphFileBean gm_SA_wt3_plus_TEX_forward = new GraphFileBean();
        gm_SA_wt3_plus_TEX_forward.setName("GM_SA_WT3_plus_TEX_forward.fa_div_by_9543947.0_multi_by_5727088.0_forward.wig_GM_SA_WT3_plus_TEX.fa_forward_in_NC_009641.1.gr");
        gm_SA_wt3_plus_TEX_forward.setCreationDate("12-02-2018");
        gm_SA_wt3_plus_TEX_forward.setSizeInKB(6622);
        gm_SA_wt3_plus_TEX_forward.setPath("/home/julian/tsspredator_test/staphylococcus_data/graphs/GM_SA_WT3_plus_TEX_forward.fa_div_by_9543947.0_multi_by_5727088.0_forward.wig_GM_SA_WT3_plus_TEX.fa_forward_in_NC_009641.1.gr");
        graphFileBeanList.add(gm_SA_wt3_plus_TEX_forward);

        GraphFileBean gm_SA_wt3_plus_TEX_reverse = new GraphFileBean();
        gm_SA_wt3_plus_TEX_reverse.setName("GM_SA_WT3_plus_TEX_reverse.fa_div_by_9543947.0_multi_by_5727088.0_reverse.wig_GM_SA_WT3_plus_TEX.fa_reverse_in_NC_009641.1.gr");
        gm_SA_wt3_plus_TEX_reverse.setCreationDate("12-02-2018");
        gm_SA_wt3_plus_TEX_reverse.setSizeInKB(6622);
        gm_SA_wt3_plus_TEX_reverse.setPath("/home/julian/tsspredator_test/staphylococcus_data/graphs/GM_SA_WT3_plus_TEX_reverse.fa_div_by_9543947.0_multi_by_5727088.0_reverse.wig_GM_SA_WT3_plus_TEX.fa_reverse_in_NC_009641.1.gr");
        graphFileBeanList.add(gm_SA_wt3_plus_TEX_reverse);



        return graphFileBeanList;
    }
}
