package edu.curtin.app;

/****************************************************************************
 * File: Key.java												            *
 * Author: Mr.A.S.M.Thasneem                          				    	*
 * Date Created: 2022/04/18                            				    	*
 * Date Modified: 2022/04/25                             				    *
 * Purpose: To Decorate Grid squares by adding Keys			                *
 ****************************************************************************/

import java.util.List;

public class Key extends GridDecorator
{
    private char ch = '\u2555';
    private int color;

    /* Decoration */
    public Key(Grid grid, int color)
    {
        super(grid);
        this.color = color;

        /* Add the color code of Key in to grid's ListOfKeys */
        grid.getListOfKeys().add(color);
    }

    @Override
    public char getCh()
    {
        return ch;
    }

    @Override
    public void setCh(char c)
    {
        this.ch = c;
    }

    @Override
    public int getColor()
    {
        return color;
    }

    @Override
    public void setRow(int row)
    {

    }

    @Override
    public int getRow()
    {
        return 0;
    }

    @Override
    public int getColumn()
    {
        return 0;
    }

    @Override
    public void setColumn(int column)
    {

    }

    @Override
    public void setColor(int color)
    {
        this.color = color;
    }

    @Override
    public String getMessages()
    {
        return grid.getMessages();
    }

    @Override
    public List<Integer> getListOfKeys()
    {
        return grid.getListOfKeys();
    }

    @Override
    public String toString()
    {
        return grid.toString();
    }

}
