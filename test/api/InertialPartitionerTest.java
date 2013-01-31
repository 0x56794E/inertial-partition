/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
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

    public void testGetLine0() throws Exception
    {
        List<Node> nodes = new ArrayList<Node>();
        for (int x = 0; x < 4; ++x)
            for (int y = 0; y < 4; ++y)
                nodes.add(new Node(0, x, y));
        Line expected = null;
        doTestGetLine(nodes, expected, "getLine_0");
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
//        nodes.add(new Node(0, 0, 0));
//        nodes.add(new Node(1, 1, 0));
//        nodes.add(new Node(2, 2, 0));
//        nodes.add(new Node(3, 0, 1));
//        nodes.add(new Node(4, 1, 1));
//        nodes.add(new Node(5, 2, 1));
//        nodes.add(new Node(6, 0, 2));
//        nodes.add(new Node(7, 1, 2));
//        nodes.add(new Node(8, 3, 0));
//        nodes.add(new Node(9, 3, 1));
//        
        
        for (int i = 0; i <= 3; ++i)
            for (int j = 0; j <= 3; ++j)
                nodes.add(new Node(0, i,j));
        Line expected = new Line(0.329213, //a
                                  1,        //b
                                  1.3,      //xbar
                                  0.8,      //ybar
                                  0.201236, //sbar
                                  nodes);
        doTestGetLine(nodes, expected, "getLine_11");
    }
    
    //@Test
//    public void testGetLine2() throws Exception
//    {
//        Collection<Node> nodes = new ArrayList<Node>();
//        nodes.add(new Node(0, 5, -1));
//        nodes.add(new Node(1, 3, 1));
//        nodes.add(new Node(2, 6, 1));
//        nodes.add(new Node(3, 5, 2));
//        nodes.add(new Node(4, 5, 5));
//        nodes.add(new Node(5, 1, 3));
//        nodes.add(new Node(6, -1, 2));
//        
//        Line expected = new Line(0.229640, //a
//                                  1,        //b
//                                  3.428571,      //xbar
//                                  1.857143,      //ybar
//                                  -0.849703, //sbar
//                                  nodes);
//        
//        doTestGetLine(nodes, expected, "getLine_2");
//    }
//    
    private void doTestGetLine(Collection<Node> nodes, 
                               Line expected, 
                               String testName) throws Exception
    {
        Line actual = InertialPartitioner.getLine(nodes);
        
        System.out.println("Result for " + testName + " = " + actual);
        assertEquals(expected, actual);
    }
            

    /**
     * A sq area
     */
//    @Test
//    public void testGetLines1() throws Exception
//    {
//        List<Node> nodes = new ArrayList<Node>();
//        int k = 4;
//        for (int x = 0; x < 4; ++x)
//            for (int y = 0; y < 4; ++y)
//                nodes.add(new Node(0, x, y));
//        
//        List expected = null;
//        doTestGetLines(nodes,
//                       k,
//                       expected,
//                       "testGetLines1");
//    }

    
    private void doTestGetLines(Collection<Node> nodes, 
                                int k, 
                                List<Line> expected,
                                String testName) throws Exception
    {
        List<Line> actual = InertialPartitioner.getLines(nodes, k);
        System.out.println("Result for " + testName + ": ");
        for (Line l : actual)
            System.out.println(l);
        
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testWithFloorPlan() throws FileNotFoundException, Exception
    {   
        List<Node> nodes = new ArrayList<Node>();
        int k = 4;
        File file = new File("floor_plan.png_1374_availCell.txt");
        Scanner sc = new Scanner(file);
        String line;
        String tokens[];
        
        //List of nodes (available nodes)
        while (sc.hasNextLine())
        {
            line = sc.nextLine();
            tokens = line.split("\\s");
            nodes.add(new Node(0, Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])));
        }
        
        List<Line> expected = null;
        doTestGetLines(nodes, k, expected, "testWithFloorPlan");
    }
}
