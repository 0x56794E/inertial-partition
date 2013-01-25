/**
 * Inertial Partitioning
 * Copyright (C) 2013  Vy Thuy Nguyen
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 * 
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor,
 * Boston, MA  02110-1301, USA.
 */

package main;

import api.InertialPartioner;
import api.Line;
import api.Node;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

/**
 * @author              Vy Thuy Nguyen
 * @version             1.0 Jan 22, 2013
 * Last modified:       
 */
public class Main 
{
    public static final int ROW_COUNT = 10;
    public static final int COL_COUNT = 15;
    
    public static void main(String[] args) throws Exception
    {
        SimpleWeightedGraph<Node, DefaultWeightedEdge> g = genGraph();
        Line line = InertialPartioner.getLine(g);
        
        System.out.printf("a = %f, b = %f, xbar = %f, ybar = %f\n", 
                          line.getA(),
                          line.getB(), 
                          line.getXbar(),
                          line.getYbar());
    }
    
    /**
     * Generates a graph for testing purpose
     * 
     * @return a simple weighted graph 
     */
    private static SimpleWeightedGraph<Node, DefaultWeightedEdge> genGraph()
    {
        Node[][] nodeContainer = new Node[ROW_COUNT][COL_COUNT];
        SimpleWeightedGraph<Node, DefaultWeightedEdge> g = 
                new SimpleWeightedGraph<Node, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        generateVertices(nodeContainer, g);
        generateEdges(nodeContainer, g);
        return g;
    }
    
    /**
     * 
     */
    private static void generateVertices(Node[][] nodeContainer, 
                                  SimpleWeightedGraph<Node, DefaultWeightedEdge> g)
    {
        int id = 0;
        
        for (int row = 0; row < ROW_COUNT; ++row)
            for (int col = 0; col < COL_COUNT; ++col)
            {
                nodeContainer[row][col] = new Node(id, col, row);
                g.addVertex(nodeContainer[row][col]);
                ++id;
            }
    }
    
    private static void generateEdges(Node[][] nodeContainer, 
                               SimpleWeightedGraph<Node, DefaultWeightedEdge> g)
    {
        for (int row = 0; row < ROW_COUNT; ++row)
            for (int col = 0; col < COL_COUNT; ++col)
            {
                addEdges(nodeContainer, g, row, col);
            }
    }
    
    /**
     * Add edges connecting this cell and its 8 (or less) neighbors
     * 
     * @param row
     * @param col 
     */
    private static void addEdges(Node[][] nodeContainer,
                          SimpleWeightedGraph<Node, DefaultWeightedEdge> g,
                          int row, int col)
    {
        //Add weighted edge
        //North
        if (row > 0)
        {
            g.addEdge(nodeContainer[row][col], nodeContainer[row - 1][col]);
            g.setEdgeWeight(g.getEdge(nodeContainer[row][col],
                                      nodeContainer[row - 1][col]),
                            1);
            //NE
            if (col < COL_COUNT - 2)
            {
                g.addEdge(nodeContainer[row][col], nodeContainer[row - 1][col + 1]);
                g.setEdgeWeight(g.getEdge(nodeContainer[row][col],
                                          nodeContainer[row - 1][col + 1]),
                                Math.sqrt(2.0));
            }
        }

        //East
        if (col < COL_COUNT - 2)
        {
            g.addEdge(nodeContainer[row][col], nodeContainer[row][col + 1]);
            g.setEdgeWeight(g.getEdge(nodeContainer[row][col],
                                      nodeContainer[row][col + 1]),
                            1);


            //SE
            if (row < ROW_COUNT - 2)
            {
                g.addEdge(nodeContainer[row][col], nodeContainer[row + 1][col + 1]);
                g.setEdgeWeight(g.getEdge(nodeContainer[row][col],
                                          nodeContainer[row + 1][col + 1]),
                                Math.sqrt(2.0));

            }
        }

        //South
        if (row < ROW_COUNT - 2)
        {
            g.addEdge(nodeContainer[row][col], nodeContainer[row + 1][col]);
            g.setEdgeWeight(g.getEdge(nodeContainer[row][col],
                                      nodeContainer[row + 1][col]),
                            1);

            //SW
            if (col > 0)
            {
                g.addEdge(nodeContainer[row][col], nodeContainer[row + 1][col - 1]);
                g.setEdgeWeight(g.getEdge(nodeContainer[row][col],
                                          nodeContainer[row + 1][col - 1]),
                                Math.sqrt(2.0));
            }
        }

        //West
        if (col > 0)
        {
            g.addEdge(nodeContainer[row][col], nodeContainer[row][col - 1]);
            g.setEdgeWeight(g.getEdge(nodeContainer[row][col], nodeContainer[row][col - 1]),
                            1);

            //NW
            if (row > 0)
            {
                g.addEdge(nodeContainer[row][col], nodeContainer[row - 1][col - 1]);
                g.setEdgeWeight(g.getEdge(nodeContainer[row][col],
                                          nodeContainer[row - 1][col - 1]),
                                Math.sqrt(2.0));
            }
        }
    }
}
