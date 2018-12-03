
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.List;
import java.util.Map;

/** 
* GARun Tester. 
* 
* @author <Authors name> 
* @since <pre>Dec 3, 2018</pre> 
* @version 1.0 
*/ 
public class GARunTest { 

    private GARun gaRun;
@Before
public void before() throws Exception {
    gaRun = new GARun(5,5,5);
} 

@After
public void after() throws Exception { 
}


@Test
public void testGetSolutions() {

    List<Chromosome> solutions  = gaRun.getSolutions();
    Assert.assertEquals(solutions.size(), gaRun.getSolutionNum());
}

    /**
* 
* Method: survive(Map<String,List<Chromosome>> map) 
* 
*/ 
@Test
public void testSurvive() throws Exception { 
//TODO: Test goes here...
    Map<String, List<Chromosome>> map = gaRun.getMap();
    map = gaRun.survive(map);
    Assert.assertEquals(GAConfiguration.sample_number/2,map.get("mature").size());
} 

/** 
* 
* Method: mature(Map<String,List<Chromosome>> map) 
* 
*/ 
@Test
public void testMature() throws Exception { 
//TODO: Test goes here...
    Map<String, List<Chromosome>> map = gaRun.getMap();
    int matureSize = map.get("mature").size();
    int toBeMatureSize = map.get("toBeMature")==null?0:map.get("toBeMature").size();
    int immatureSize = map.get("immature").size();
    map = gaRun.mature(map);
    List<Chromosome> mature = map.get("mature");
    List<Chromosome> toBeMature = map.get("toBeMature");
    List<Chromosome> immature = map.get("immature");

    Assert.assertEquals(null,immature);
    Assert.assertEquals(immatureSize,toBeMature.size());
    Assert.assertEquals(matureSize+toBeMatureSize,mature.size());
}

/** 
* 
* Method: breed(Map<String,List<Chromosome>> map) 
* 
*/ 
@Test
public void testBreed() throws Exception { 
//TODO: Test goes here...
    Map<String, List<Chromosome>> map = gaRun.getMap();
    map = gaRun.breed(map);
    Assert.assertEquals(map.get("mature").size(),map.get("immature").size());
} 




} 
