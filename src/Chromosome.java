import java.util.List;

public class Chromosome implements Comparable<Chromosome> {

    private int fitness;

    // server  list
    private List<Server> genes;

    private List<Double> phenotypes;

    public int getFitness() {
        return fitness;
    }

    public List<Double> getPhenotypes() {
        return phenotypes;
    }

    public void setPhenotypes(List<Double> phenotypes) {
        this.phenotypes = phenotypes;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public List<Server> getGenes() {
        return genes;
    }

    public void setGenes(List<Server> genes) {
        this.genes = genes;
    }

    @Override
    public int compareTo(Chromosome chromosome){
        return this.fitness - chromosome.fitness;
    }
}
