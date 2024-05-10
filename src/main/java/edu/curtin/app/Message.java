package edu.curtin.app;

/****************************************************************************
 * File: Message.java												        *
 * Author: Mr.A.S.M.Thasneem                          				    	*
 * Date Created: 2022/04/18                            				    	*
 * Date Modified: 2022/04/25                             				    *
 * Purpose: To Decorate Grid squares by adding Messages			            *
 ****************************************************************************/

import java.util.List;

public class Message extends GridDecorator
{
    private char ch;
    private String str;

    /* Decoration */
    public Message(Grid grid, String str)
    {
        super(grid);

        // Message
        this.str = str;
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

    }

    @Override
    public String getMessages()
    {
        return String.format(this.grid.getMessages() + "\n " + str);
    }

    @Override
    public List<Integer> getListOfKeys()
    {
        return grid.getListOfKeys();
    }

    /* Method just passes on to grid */
    @Override
    public String toString()
    {
        return grid.toString();
    }

}
