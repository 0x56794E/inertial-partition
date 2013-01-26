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
    private double sbar;

    public Line(double a, double b, double xbar, double ybar, double sbar)
    {
        this.a = a;
        this.b = b;
        this.xbar = xbar;
        this.ybar = ybar;
        this.sbar = sbar;
    }
    
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
    
    public double getSbar()
    {
        return sbar;
    }
    
    public void setSbar(double sbar)
    {
        this.sbar = sbar;
    }
    
    @Override
    public boolean equals(Object rhs)
    {
        if (rhs == null || !(rhs instanceof Line))
            return false;
        else
        {
            Line rhsLine = (Line)rhs;
            return this.a == rhsLine.a
                    && this.b == rhsLine.b
                    && this.sbar == rhsLine.sbar
                    && this.xbar == rhsLine.xbar
                    && this.ybar == rhsLine.ybar;
        }
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.a) ^ (Double.doubleToLongBits(this.a) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.b) ^ (Double.doubleToLongBits(this.b) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.xbar) ^ (Double.doubleToLongBits(this.xbar) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.ybar) ^ (Double.doubleToLongBits(this.ybar) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.sbar) ^ (Double.doubleToLongBits(this.sbar) >>> 32));
        return hash;
    }
}
