package edu.curtin.app;

/****************************************************************************
 * File: Grid.java													        *
 * Author: Mr.A.S.M.Thasneem                          				    	*
 * Date Created: 2022/04/18                            				    	*
 * Date Modified: 2022/04/25                             				    *
 * Purpose: To define a protocol of behavior of the grid squares that can   *
 *          be implemented by any class anywhere in the class				*
 ****************************************************************************/

import java.util.List;

public interface Grid
{
    void setCh(char c);

    char getCh();

    @Override
    String toString();

    void setColor(int color);

    String getMessages();

    List<Integer> getListOfKeys();

    int getColor();
    void setRow(int row);
    int getRow();
    int getColumn();
    void setColumn(int column);


}
