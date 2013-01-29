/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import main.Main;
import org.jgrapht.Graph;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.*;

/**
 *
 * @author VyNguyen
 */
public class InertialPartitionerTest
{
    
    public InertialPartitionerTest()
    {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getLine method.
     * Case 1: nodes distributed as followed:
     * 2 * *
     * 1 * * * *
     * 0 * * * *
     *   0 1 2 3
     * 
     * Expected result L: y = 0.8
     */
    @Test
    public void testGetLine1() throws Exception
    {
        Collection<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node(0, 0, 0));
        nodes.add(new Node(1, 1, 0));
        nodes.add(new Node(2, 2, 0));
        nodes.add(new Node(3, 0, 1));
        nodes.add(new Node(4, 1, 1));
        nodes.add(new Node(5, 2, 1));
        nodes.add(new Node(6, 0, 2));
        nodes.add(new Node(7, 1, 2));
        nodes.add(new Node(8, 3, 0));
        nodes.add(new Node(9, 3, 1));
        
        Line expResult = new Line(0.329213, //a
                                  1,        //b
                                  1.3,      //xbar
                                  0.8,      //ybar
                                  0.201236, //sbar
                                  nodes);
        doTestGetLine(nodes, expResult, "testcase1");
    }
    
    @Test
    public void testGetLine2() throws Exception
    {
        Collection<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node(0, 5, -1));
        nodes.add(new Node(1, 3, 1));
        nodes.add(new Node(2, 6, 1));
        nodes.add(new Node(3, 5, 2));
        nodes.add(new Node(4, 5, 5));
        nodes.add(new Node(5, 1, 3));
        nodes.add(new Node(6, -1, 2));
        
        Line expResult = new Line(0.229640, //a
                                  1,        //b
                                  3.428571,      //xbar
                                  1.857143,      //ybar
                                  -0.849703, //sbar
                                  nodes);
        
        doTestGetLine(nodes, expResult, "testcase2");
    }
    
    private void doTestGetLine(Collection<Node> nodes, Line expResult, String testCase) throws Exception
    {
        Line result = InertialPartitioner.getLine(nodes);
        
        System.out.println("Result for " + testCase + " = " + result);
        assertEquals(expResult, result);
    }
            

    /**
     * Test of getLines method, of class InertialPartitioner.
     */
    @Test
    public void testGetLines() throws Exception
    {
        System.out.println("getLines");
        Collection<Node> nodes = null;
        int k = 0;
        List expResult = null;
        List result = InertialPartitioner.getLines(nodes, k);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSolutions method, of class InertialPartitioner.
     */
    @Test
    public void testGetSolutions()
    {
        System.out.println("getSolutions");
        double a = 0.0;
        double b = 0.0;
        double c = 0.0;
        ArrayList expResult = null;
        ArrayList result = InertialPartitioner.getSolutions(a, b, c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of areOnSameSide method, of class InertialPartitioner.
     */
    @Test
    public void testAreOnSameSide()
    {
        System.out.println("areOnSameSide");
        Line line = null;
        Node node1 = null;
        Node node2 = null;
        boolean expResult = false;
        boolean result = InertialPartitioner.areOnSameSide(line, node1, node2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
