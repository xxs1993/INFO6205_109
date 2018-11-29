
import java.util.List;
import java.util.Random;



public class Reproduction {

    public static List<Chromosome> getNextGeneration(List<Chromosome> list){return list;}

    private static Chromosome getNextGeneration(Chromosome fa , Chromosome mo){
        return null;
    }

    private static Chromosome mutate(Chromosome fa , Chromosome mo){
        Chromosome son = crossover( fa , mo);
        Random random = new Random();
        int N=0;
        for (Server item : fa.getGenes()) {
            N++;
        }
        int times = random.nextInt(N);
        for(int i=0; i<times; i++)
        {
            int posit = random.nextInt(N);
            int number = random.nextInt(20);
            Server server = new Server(N,number);
            son.getGenes().set(N, server);
        }

        return son;
    }

    private static Chromosome crossover(Chromosome fa , Chromosome mo){
        int N= fa.getGenes().size();
        Chromosome son = new Chromosome();
        Random random = new Random();
        int c = random.nextInt(2);

        if(c==0){
            son.getGenes().addAll(fa.getGenes().subList(0,N/2));
            son.getGenes().addAll(mo.getGenes().subList(N/2,N));

        }
        else
        {
            son.getGenes().addAll(mo.getGenes().subList(0,N/2));
            son.getGenes().addAll(fa.getGenes().subList(N/2,N));
        }
        return son;
    }
}

