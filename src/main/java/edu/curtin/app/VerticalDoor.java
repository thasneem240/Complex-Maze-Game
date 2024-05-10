package edu.curtin.app;

/****************************************************************************
 * File: VerticalDoor.java												    *
 * Author: Mr.A.S.M.Thasneem                          				    	*
 * Date Created: 2022/04/18                            				    	*
 * Date Modified: 2022/04/25                             				    *
 * Purpose: To represent the Vertical Door in Maze Grid squares.			*
 ****************************************************************************/

import java.util.List;

public class VerticalDoor implements  Grid
{
    private char ch = '\u2592';
    private int row;
    private int column;
    private int color;

    @Override
    public char getCh()
    {
        return ch;
    }

    @Override
    public void setCh(char ch)
    {
        this.ch = ch;
    }

    @Override
    public int getRow()
    {
        return row;
    }

    @Override
    public void setRow(int row)
    {
        this.row = row;
    }

    @Override
    public int getColumn()
    {
        return column;
    }

    @Override
    public void setColumn(int column)
    {
        this.column = column;
    }

    @Override
    public int getColor()
    {
        return color;
    }

    @Override
    public void setColor(int color)
    {
        this.color = color;
    }

    @Override
    public String getMessages()
    {
        return "";
    }

    /* Doors will not have any keys */

    @Override
    public List<Integer> getListOfKeys()
    {
        return null;
    }

    @Override
    public String toString()
    {
        return String.format("\033[3%dm%c\033[m",color, ch);
    }

}
