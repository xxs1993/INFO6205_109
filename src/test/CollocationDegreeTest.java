import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/** 
* CollocationDegree Tester. 
* 
* @author <Authors name> 
* @since <pre>Dec 2, 2018</pre> 
* @version 1.0 
*/ 
public class CollocationDegreeTest {

    private GARun gaRun;

@Before
public void before() throws Exception {
   gaRun  = new GARun(3,3,3);
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getCollocationDegreeByGenes(List<Server> genes, List<Task> tasks) 
* 
*/ 
@Test
public void testGetCollocationDegreeByGenes() throws Exception { 
//TODO: Test goes here...
    List<Server> genes = new ArrayList<>();
    for(int i=0;i< gaRun.getTaskNum();i++){
        genes.add(gaRun.getServers().get(i));
    }
    List<Server> genes2 = genes;

    int degree =  CollocationDegree.getCollocationDegreeByGenes(genes,gaRun.getTasks());
    int degree2 = CollocationDegree.getCollocationDegreeByGenes(genes2,gaRun.getTasks());
    Assert.assertTrue(degree >0);
    Assert.assertEquals(degree,degree2);
} 

/** 
* 
* Method: fitness(List<Chromosome> list) 
* 
*/ 
@Test
public void testFitness() throws Exception { 
//TODO: Test goes here...
    List<Chromosome> chromosomes = new ArrayList<>();
    Chromosome c1 = new Chromosome();
    List<Server> genes = new ArrayList<>();
    for(int i=0;i< gaRun.getTaskNum();i++){
        genes.add(gaRun.getServers().get(i));
    }
    c1.setGenes(genes);
    c1.setCollocationDegree(CollocationDegree.getCollocationDegreeByGenes(genes,gaRun.getTasks()));
    chromosomes.add(c1);
    List<Chromosome> list = CollocationDegree.fitness(chromosomes);
    Assert.assertNotNull(list);
    Assert.assertEquals(2,list.size());
} 




/** 
* 
* Method: getSelectedIndex(int mid, int[]range, int r) 
* 
*/ 
@Test
public void testGetSelectedIndex() throws Exception { 
//TODO: Test goes here...
    double range[] = new double[5];
    range[0] = 5;
    range[1] = 10;
    range[2] = 15;
    range[3] = 20;
    range[4] = 25;
    int index = CollocationDegree.getSelectedIndex(range,14);
    int index2 = CollocationDegree.getSelectedIndex(range,24);
    int index3 = CollocationDegree.getSelectedIndex(range,15);

    Assert.assertEquals(2,index);
    Assert.assertEquals(4,index2);
    Assert.assertEquals(3,index3);


} 

} 
