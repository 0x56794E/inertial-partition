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


package api;

import java.util.ArrayList;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

/**
 * @author              Vy Thuy Nguyen
 * @version             1.0 Jan 22, 2013
 * Last modified:       
 */
public class InertialPartioner 
{
    /**
     * Given a graph, this function returns the line that partitions the graph
     * into two parts. 
     * The computation is as followed:
     * 1) Compute xbar and ybar: (N = number of nodes)
     *    xbar = (Sigmar(xi) i = [0, N - 1]) / N
     *    ybar = (Sigmar(yi) i = [0, N - 1))/ N
     * 
     * 2) Compute sum of squares of distance (x1, x2, x3)
     *    x1 = Sigmar[(xi - xbar)^2 i = [0, N - 1]]
     *    x2 = Sigmar[(xi - xbar) * (yi - ybar) i = [0, N - 1]]
     *    x3 = Sigmar[(yi - ybar)^2 i = [0, N - 1]]
     * 
     * 3) Compute lambda (the smallest eigenvalue of the 2x2 matrix A = [x1 x2, x2 x3])
     *    Let I be the 2x2 identity matrix, v = [a, b] be the 2x1 eigenvector corresponding
     *    to lambda.
     *    Then (A - lambda * I) * v = 0
     *    Therefore, det(A - lambda * I) = 0
     *   | x1  x2 |   | lambda  0      |   | x1 - lambda     x2          |
     *   |        | - |                | = |                             |
     *   | x2  x3 |   | 0       lambda |   | x2              x3 - lambda |
     *   <=> (x1 - lambda)(x3 - lambda) - x2 ^ 2 = 0
     *   <=> lambda ^ 2 - (x1 + x3) * lambda + x1 * x3 - x2^2 = 0
     *   Solve this equation and get the smallest solution. 
     *   An exception is thrown if no solution is found.
     * 
     * 4) Compute vector v = [a, b] (the eigenvector corresponding to lambda)
     *    As mentioned above, (A - lambda * I) * v = 0
     *    We can write this equation as a system of two linear equations of two vars a and b
     *    a * (x1 - lambda) + b * x2 = 0 
     *    a * x2 + b * (x3 - lambda) = 0 
     *    
     *    There're 3 possible cases:
     *      1-The system has one single solution <=> (a, b) = 0
     *      2-The system has infinitely many solutions. In other words, [a, b] is a
     *        vector in an eigenspace whose basis is [-x2 / (x1 - lambda), 1]
     *      3-The system has no solution.
     *    
     *    Since the 1st and 3rd cases are irrelevant, we only need to look for 
     *    one of the vectors in the eigenspace described above. The easiest
     *    one to find is the basis itself, which is [-x2 / (x1 - lambda), 1]
     * 
     *    An exception is thrown if the system falls into the 1st or 3rd case.
     * 
     * @param g
     * @return Line l
     * @throws Exception 
     */
    public static Line getLine(SimpleWeightedGraph<Node, DefaultWeightedEdge> g) throws Exception
    {
        //Compute xbar and ybar
        double xbar = 0, ybar = 0;
        final int N = g.vertexSet().size();
        
        for (Node node : g.vertexSet())
        {
            xbar += node.getX();
            ybar += node.getY();
        }
        
        xbar /= N;
        ybar /= N;
        System.out.printf("Done computing xbar and ybar; xbar = %f, ybar = %f\n",
                          xbar,
                          ybar);
        
        //Compute sum of squares of distance (x1, x2 and x3)
        //x1 = Sigma[(x - xbar)^2]
        //x2 = Sigma[(x-xbar)(y - ybar)]
        //x3 = Sigma[(y - ybar)^2]
        double x1 = 0, x3 = 0, x2 = 0;
        double xDif = 0, yDif = 0;
        for (Node node : g.vertexSet())
        {
            xDif = node.getX() - xbar;
            yDif = node.getY() - ybar;
            x1 += xDif * xDif;
            x3 += yDif * yDif;
            x2 += xDif * yDif;
        }
        System.out.printf("Done computing x1, x2 and x3; x1 = %f, x2 = %f, x3 = %f\n",
                           x1,
                           x2,
                           x3);
        
        //Compute a and b
        double a, b, lambda;
        //Compute lambda
        //The eigenvector of smallest eigenvalue of A = [X1 X2 X2 X3]
        //Let lambda be the eigenvalue, I be the 2x2 identity matrix,
        //X1 = sumXsq, X3 = sumYsq, X2 = sumXY
        //Then (A - lambda * I)[a b] = 0
        //=> det(A - lambda * I) = 0
        //<=> (X1 - lambda)(X3 - lambda) - X2 * X2 = 0
        //<=> lambda^2 -(X1 + X3) * lambda + X1 * X3 - X2 * X2
        //Solve this equation and the smallest solution is the eigenvalue we're looking for.
        ArrayList<Double> sols = getSolutions(1, //a
                                              0 - x1 - x3, //b
                                              x1 * x3 - x2 * x2); //c
        if (sols.isEmpty())
            throw new Exception("No eigenvalue found!");
        System.out.printf("Done finding eigenvalues; lambda1 = %f, lambda2 = %f\n",
                          sols.get(0),
                          sols.get(1));
        
        lambda = Math.min(sols.get(0), sols.get(1));
        System.out.printf("The smallest eigenvalue is: lambda = %f\n", lambda);
        
        //Compute a, b
        //v = [a b] is the eigenvector such that
        //(A - lambda * I) * v = [0 0]
        //Or
        //a * X1 + b * X2 = lambda * a <=> a * (X1 - lambda) + b * X2 = 0  (1)
        //a * X2 + b * X3 = lambda * b <=> a * X2 + b * (X3 - lambda) = 0  (2)
        //Note that this system's solution can only be one of the following:
        //  1) (a, b) = (0, 0)
        //  2) (a, b) is a vector in the eigenspace whose basis is (-X2 / (X1 - lambda), 1)
        //  3) the system has no solution.
        //Since the first and third cases are irrelevant, we only need to look for one of the
        //vectors in the eigenspace described above.
        //The easiest one to find is the basis itself.
        if (Double.compare(x2 * x2, 
                           (x1 - lambda) * (x3 - lambda)) != 0) //If the system doesn't have inf. number of solultions
            throw new Exception("The system must have inf. number of solutions. Otherwise, the eigenvector would be [0, 0]");
        
        a = (0 - x2) / (x1 -  lambda);
        b = 1;        
        
        return new Line(a, b, xbar, ybar);
    }
 
    
    /**
     * 
     * @param a
     * @param b
     * @param c
     * @return an arraylist container the solution(s) of the quad-eqn
     * aX^2 + bX + c = 0
     */
    public static ArrayList<Double> getSolutions(double a, double b, double c)
    {
        ArrayList<Double> sols = new ArrayList<Double>();
        
        double delta = b * b - 4 * a * c;
        
        //If there're solution
        if (delta >= 0)
        {
            //Sqrt of delta
            delta = Math.sqrt(delta);
         
            //The two solutions 
            sols.add(((0 - b) + delta) / (2 * a));
            sols.add(((0 - b) - delta) / (2 * a));
        }
        
        return sols;
    }
   
    /**
     * 
     * @param line has the form of a * (x - xbar) + b * (y - ybar) = 0
     * @param node1
     * @param node2
     * @return 
     */
    public static boolean areOnSameSide(Line line, Node node1, Node node2)
    {
        double prod = ( (line.getA() * (node1.getX() - line.getXbar())
                         + line.getB() * (node1.getY() - line.getYbar()))
                      * (line.getA() * (node2.getX() - line.getXbar())
                         + line.getB() * (node2.getY() - line.getYbar())));
        
        return (prod >= 0 ? true : false);
    }
   
    
    public static void setSide(SimpleWeightedGraph<Node, DefaultWeightedEdge> g, Line line)
    {
        
    }

}
