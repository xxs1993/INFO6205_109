import java.util.List;

public class Chromosome implements Comparable<Chromosome> {

    private int collocationDegree;

    // server  list
    private List<Server> genes;

    private List<Double> phenotypes;

    public int getCollocationDegree() {
        return collocationDegree;
    }

    public List<Double> getPhenotypes() {
        return phenotypes;
    }

    public void setPhenotypes(List<Double> phenotypes) {
        this.phenotypes = phenotypes;
    }

    public void setCollocationDegree(int collocationDegree) {
        this.collocationDegree = collocationDegree;
    }

    public List<Server> getGenes() {
        return genes;
    }

    public void setGenes(List<Server> genes) {
        this.genes = genes;
    }

    @Override
    public int compareTo(Chromosome chromosome){
        return this.collocationDegree - chromosome.collocationDegree;
    }
}
