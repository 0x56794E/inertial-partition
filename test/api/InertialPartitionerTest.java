/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

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
     * Test of getLine method, of class InertialPartitioner.
     */
    @Test
    public void testGetLine() throws Exception
    {
        System.out.println("getLine");
        Collection<Node> nodes = null;
        Line expResult = null;
        Line result = InertialPartitioner.getLine(nodes);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
