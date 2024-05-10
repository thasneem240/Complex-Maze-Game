package edu.curtin.app;

/****************************************************************************
 * File: Empty.java													        *
 * Author: Mr.A.S.M.Thasneem                          				    	*
 * Date Created: 2022/04/18                            				    	*
 * Date Modified: 2022/04/25                             				    *
 * Purpose: To represent small Empty grid squares in the Maze				*
 ****************************************************************************/

import java.util.ArrayList;
import java.util.List;

public class Empty implements Grid
{
    private char ch = ' ';
    private int color;
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
    public int getColor()
    {
        return 0;
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
        return String.format("\033[3%dm%c\033[m",color,ch);
    }

}
