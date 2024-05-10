package edu.curtin.app;

/****************************************************************************
 * File: EndPoint.java													    *
 * Author: Mr.A.S.M.Thasneem                          				    	*
 * Date Created: 2022/04/18                            				    	*
 * Date Modified: 2022/04/25                             				    *
 * Purpose: To represent the End Point in the Maze						    *
 ****************************************************************************/

import java.util.ArrayList;
import java.util.List;

public class EndPoint implements Grid
{
    private char ch = 'E';
    private int row;
    private int column;
    private List<Integer> listOfKeys = new ArrayList<>();

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
        return 0;
    }

    @Override
    public void setColor(int color)
    {

    }

    @Override
    public String getMessages()
    {
        return "";
    }

    @Override
    public List<Integer> getListOfKeys()
    {
        return listOfKeys;
    }

    @Override
    public String toString()
    {
        return String.format("%c",ch);
    }

}
