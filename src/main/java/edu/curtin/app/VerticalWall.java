package edu.curtin.app;

import java.util.List;

public class VerticalWall implements Grid
{
    private char ch = '\u2502';
    private int row;
    private int column;

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
        return null;
    }

    @Override
    public int getColor()
    {
        return 0;
    }

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
    public String toString()
    {
        return String.format("%c",ch);
    }
}
