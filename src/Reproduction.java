
import java.util.Iterator;
import java.util.List;
import java.util.Random;



public class Reproduction {

    public static List<Chromosome> getNextGeneration(List<Chromosome> list){return null;}

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
        int N=0;
        Chromosome son = new Chromosome();
        Random random = new Random();
        for (Server item : fa.getGenes()) {
            N++;
                    }
        Iterator<Server> he = fa.getGenes().iterator();
        Iterator<Server> she = mo.getGenes().iterator();
        int c = random.nextInt(2);

        if(c==0){
        for(int i=1;i<=N/2;i++) {
         son.getGenes().add(he.next());
        }
        for(int i=1;i<=N/2;i++) {
         son.getGenes().add(she.next());
            }

        }
        else
        {
            for(int i=1;i<=N/2;i++) {
                son.getGenes().add(she.next());
            }
            for(int i=1;i<=N/2;i++) {
                son.getGenes().add(he.next());
            }
        }
        return son;
    }
}

