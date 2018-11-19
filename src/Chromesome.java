import java.util.List;

public class Chromesome implements Comparable<Chromesome> {

    private int collocationDegree;

    private List<Integer> genes;

    public int getCollocationDegree() {
        return collocationDegree;
    }

    public void setCollocationDegree(int collocationDegree) {
        this.collocationDegree = collocationDegree;
    }

    public List<Integer> getGenes() {
        return genes;
    }

    public void setGenes(List<Integer> genes) {
        this.genes = genes;
    }

    @Override
    public int compareTo(Chromesome chromesome){
        return this.collocationDegree - chromesome.collocationDegree;
    }
}
