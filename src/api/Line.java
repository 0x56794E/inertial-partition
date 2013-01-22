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

/**
 * Represents a line L such that 
 * a * (x - xbar) + b * (y - ybar) = 0
 * 
 * @author              Vy Thuy Nguyen
 * @version             1.0 Jan 22, 2013
 * Last modified:       
 */
public class Line
{
    private double a;
    private double b;
    private double xbar;
    private double ybar;

    /**
     * @return the a
     */
    public double getA()
    {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(double a)
    {
        this.a = a;
    }

    /**
     * @return the b
     */
    public double getB()
    {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(double b)
    {
        this.b = b;
    }

    /**
     * @return the xbar
     */
    public double getXbar()
    {
        return xbar;
    }

    /**
     * @param xbar the xbar to set
     */
    public void setXbar(double xbar)
    {
        this.xbar = xbar;
    }

    /**
     * @return the ybar
     */
    public double getYbar()
    {
        return ybar;
    }

    /**
     * @param ybar the ybar to set
     */
    public void setYbar(double ybar)
    {
        this.ybar = ybar;
    }
}
