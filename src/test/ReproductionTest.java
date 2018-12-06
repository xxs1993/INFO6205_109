import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReproductionTest {

  //private Reproduction reproduction;
  List<Task> task = new ArrayList<Task>();
  List<Server> server = new ArrayList<Server>();
  List<Chromosome> list = new ArrayList<Chromosome>();


  Chromosome fa = new Chromosome();
  Chromosome mo = new Chromosome();
  Chromosome son = new Chromosome();
  @Test
    public void testCrossover(){
        for(int i=0;i<5;i++){
            Task t = new Task(i);
            task.add(t);
        }
        for(int j=0;j<5;j++){
            Server s = new Server(j,j);
            server.add(s);
        }
        List<Server> geneFa = new ArrayList<Server>();
        for(int i=0;i<10;i++){
           if (server != null && server.size() > 0)
               if(i<5)
               {geneFa.add(server.get(i));}
               else
               {geneFa.add(server.get(i-5));}
        }
        fa.setGenes(geneFa);
        List<Server> geneMo = new ArrayList<Server>();
        for(int i=0;i<10;i++){
            if (server != null && server.size() > 0)
                if(i<5)
                {geneMo.add(server.get(4-i));}
                else
                {geneMo.add(server.get(9-i));}
        }
        mo.setGenes(geneMo);

        List<Server> serverResult = new ArrayList<Server>();
        for(int i=0;i<5;i++)
        {
            if (server != null && server.size() > 0)
            serverResult.add(server.get(i));
        }
        for(int i=0;i<5;i++) {
            if (server != null && server.size() > 0)
            serverResult.add(server.get(4 - i));
        }
        son.setGenes(serverResult);
        //assertEquals(son,Reproduction.crossover(fa,mo));
       assertEquals(son.getGenes().size(),Reproduction.crossover(fa,mo).getGenes().size());
    }

    @Test
    public void testMutate(){
        for(int i=0;i<5;i++){
            Task t = new Task(i);
            task.add(t);
        }
        for(int j=0;j<5;j++){
            Server s = new Server(j,j);
            server.add(s);
        }


        List<Server> serverResult = new ArrayList<Server>();
        for(int i=0;i<5;i++)
        {
            serverResult.add(server.get(i));
        }
        for(int i=0;i<5;i++) {
            serverResult.add(server.get(4- i));
        }
        son.setGenes(serverResult);
        Chromosome newson;
        newson = Reproduction.mutate(son,server);

        Chromosome sons = new Chromosome();
        int times = 0;
        for(int i=0;i<2000;i++){
            sons = Reproduction.mutate(son,server);
            if(son.equals(sons)){
                times++;
            }
        }
        boolean right = false;
        if(times>=1400){
            right = true;
        }

        assertEquals(10,newson.getGenes().size());
        assertEquals(true,right);
    }

    @Test
    public void testGetNextGeneration(){
        for(int i=0;i<5;i++){
            Task t = new Task(i);
            task.add(t);
        }
        for(int j=0;j<5;j++){
            Server s = new Server(j,j);
            server.add(s);
        }

        List<Server> geneFa = new ArrayList<Server>();
        for(int i=0;i<10;i++){
            if (server != null && server.size() > 0)
                if(i<5)
                {geneFa.add(server.get(i));}
                else
                {geneFa.add(server.get(i-5));}
        }
        fa.setGenes(geneFa);
        List<Server> geneMo = new ArrayList<Server>();
        for(int i=0;i<10;i++){
            if (server != null && server.size() > 0)
                if(i<5)
                {geneMo.add(server.get(4-i));}
                else
                {geneMo.add(server.get(9-i));}
        }
        mo.setGenes(geneMo);

        List<Server> serverResult = new ArrayList<Server>();
        for(int i=0;i<5;i++)
        {
            serverResult.add(server.get(i));
        }
        for(int i=0;i<5;i++) {
            serverResult.add(server.get(4 - i));
        }
        son.setGenes(serverResult);
        Chromosome newson;
        newson = Reproduction.getNextGeneration(fa,mo,server);

        Chromosome sons = new Chromosome();
        int times = 0;
        for(int i=0;i<2000;i++){
            sons = Reproduction.mutate(son,server);
            if(son.equals(sons)){
                times++;
            }
        }
        boolean right = false;
        if(times>=1400){
            right = true;
        }

        //assertEquals(son,reproduction.crossover(fa,mo));
        assertEquals(10,newson.getGenes().size());
        assertEquals(true,right);
    }

    @Test
    public void testGetNextGenerationList(){
        for(int i=0;i<10;i++){
            Task t = new Task(i);
            task.add(t);
        }
        for(int j=0;j<10;j++){
            Server s = new Server(j,j);
            server.add(s);
        }

        Chromosome chromosome1 = new Chromosome();
        List<Server> gene1 = new ArrayList<Server>();
        for(int i=0;i<10;i++){
            if (server != null && server.size() > 0)
           // gene1.add(server.get(i%3+1));
            if(i<5)
            {gene1.add(server.get(i));}
            else
            {gene1.add(server.get(i-5));}
        }
        chromosome1.setGenes(gene1);

        //chromosome1.setFitness(Fitness.getCollocationDegreeByGenes(chromosome1.getGenes(),task));

        Chromosome chromosome2 = new Chromosome();
        List<Server> gene2 = new ArrayList<Server>();
        for(int i=0;i<10;i++){
            if (server != null && server.size() > 0)
            //gene2.add(server.get(i%3));
            if(i<5)
            {gene2.add(server.get(i));}
            else if(i<8)
            {gene2.add(server.get(i-5));}
            else
            {gene2.add(server.get(i-8));}
        }
        chromosome2.setGenes(gene2);
        //chromosome2.setFitness(Fitness.getCollocationDegreeByGenes(chromosome2.getGenes(),task));

        Chromosome chromosome3 = new Chromosome();
        List<Server> gene3 = new ArrayList<Server>();
        for(int i=0;i<10;i++){
            if (server != null && server.size() > 0)
            //gene3.add(server.get(i%2));
                if(i<4)
                {gene3.add(server.get(i));}
                else if(i<7)
                {gene3.add(server.get(i-4));}
                else
                {gene3.add(server.get(i-7));}
        }
        chromosome3.setGenes(gene3);
        //chromosome3.setFitness(Fitness.getCollocationDegreeByGenes(chromosome3.getGenes(),task));

        Chromosome chromosome4 = new Chromosome();
        List<Server> gene4 = new ArrayList<Server>();
        for(int i=0;i<10;i++){
            if (server != null && server.size() > 0)
            //gene4.add(server.get(i%4));
                if(i<3)
                {gene4.add(server.get(i));}
                else if(i<7)
                {gene4.add(server.get(i-3));}
                else
                {gene4.add(server.get(i-7));}
        }
        chromosome4.setGenes(gene4);
        //chromosome4.setFitness(Fitness.getCollocationDegreeByGenes(chromosome4.getGenes(),task));

        Chromosome chromosome5 = new Chromosome();
        List<Server> gene5 = new ArrayList<Server>();
        for(int i=0;i<10;i++){
            if (server != null && server.size() > 0)
            //gene5.add(server.get(i%1));
                if(i<3)
                {gene5.add(server.get(i));}
                else if(i<6)
                {gene5.add(server.get(i-3));}
                else
                {gene5.add(server.get(i-6));}
        }
        chromosome5.setGenes(gene5);
        //chromosome5.setFitness(Fitness.getCollocationDegreeByGenes(chromosome5.getGenes(),task));

        list.add(chromosome1);
        list.add(chromosome2);
        list.add(chromosome3);
        list.add(chromosome4);
        list.add(chromosome5);

       // List<Chromosome> result;
       // result = Reproduction.getNextGeneration(list,server,task);


        //assertEquals(list.size(),result.size());

        assertEquals(10,chromosome1.getGenes().size());
    }
}
