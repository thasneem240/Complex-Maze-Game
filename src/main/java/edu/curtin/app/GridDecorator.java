package edu.curtin.app;

/****************************************************************************
 * File: GridDecorator.java													*
 * Author: Mr.A.S.M.Thasneem                          				    	*
 * Date Created: 2022/04/18                            				    	*
 * Date Modified: 2022/04/25                             				    *
 * Purpose: To Decorate Grid squares in the Maze						    *
 ****************************************************************************/

/* Decoration super class */

public abstract class GridDecorator implements Grid
{
    /* Holds the Instance of Grid Square */

    protected Grid grid;

    public GridDecorator(Grid grid)
    {
        this.grid = grid;
    }

}
