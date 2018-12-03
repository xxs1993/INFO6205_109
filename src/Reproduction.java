
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Reproduction {

    public static List<Chromosome> getNextGeneration(List<Chromosome> list, List<Server> server, List<Task> task){
         List<Chromosome> newlist = new ArrayList<>();
        for(int i=0;i<list.size();i++) {
            List<Chromosome> temlist = CollocationDegree.fitness(list);
            Chromosome fa = temlist.get(0);
            Chromosome ma = temlist.get(1);
            //List<Chromosome> newlist = new ;
            Chromosome son1 = getNextGeneration(fa, ma, server);
            son1.setCollocationDegree(CollocationDegree.getCollocationDegreeByGenes(son1.getGenes(), task));
            Chromosome son2 = getNextGeneration(fa, ma, server);
            son2.setCollocationDegree(CollocationDegree.getCollocationDegreeByGenes(son2.getGenes(), task));
            newlist.add(son1);
            newlist.add(son2);
        }
        return pick(newlist);
    }

    private static List<Chromosome> pick (List<Chromosome> list){
        List<Chromosome> result = new ArrayList<>();
        Random random = new Random();
        int size = list.size();
        while(result.size() <size/2){
            int newSize = list.size();
            Chromosome c1 = list.get(random.nextInt(newSize));
            Chromosome c2 = list.get(random.nextInt(newSize));
            if(c1.getCollocationDegree()>=c2.getCollocationDegree()){
                result.add(c1);
                list.remove(c1);
            } else {
                result.add(c2);
                list.remove(c2);
            }
        }
        return result;
    }

    private static Chromosome getNextGeneration(Chromosome fa , Chromosome mo,List<Server> server){
        Chromosome son;
        son = crossover(fa , mo);
        son = mutate(son,server);
        return son;
    }

    private static Chromosome mutate(Chromosome son, List<Server> server){
        Random random = new Random();
        int serverN = server.size();
        Server newserver;
        int N=0;
        N = son.getGenes().size();
        List<Server> newgene = son.getGenes();
        for(int i=0; i<N; i++) {
            int chance = random.nextInt(100);
            if (chance < 3) {
                int id = random.nextInt(serverN);
                newserver = server.get(id);
                newgene.set(i,newserver);
            }
        }
        son.setGenes(newgene);
        return son;
    }

    private static Chromosome crossover(Chromosome fa , Chromosome mo){
        int N=0;
        Chromosome son = new Chromosome();
        Random random = new Random();
        N = fa.getGenes().size();
        son.setGenes(new ArrayList<>());
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

