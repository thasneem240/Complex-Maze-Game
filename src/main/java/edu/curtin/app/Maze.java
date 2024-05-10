package edu.curtin.app;

/****************************************************************************
 * File: Maze.java												            *
 * Author: Mr.A.S.M.Thasneem                          				    	*
 * Date Created: 2022/04/18                            				    	*
 * Date Modified: 2022/04/25                             				    *
 * Purpose: To Contain all the Functionality of maze game 			        *
 ****************************************************************************/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Maze
{
    private Grid[][] gridArray;

//    /* row and colum of the maze */
//    private int row, column;

    /* Actual row and column of the maze created */
    private int actualRow, actualColumn;

    private int playerRow, playerColumn, endRow, endColumn;
    private static final Logger LOGGER = Logger.getLogger(Maze.class.getName());
    private List<Integer> listOfAcquiredKeys = new ArrayList<>();

    /* To Get input from the user to Move the player in the maze */
    private static Scanner input = new Scanner(System.in);

    /* Constructor to Create Maze */
    public Maze(int row, int column)
    {
//        this.row = row;
//        this.column = column;

        actualRow = row + (row-1) + 2;
        actualColumn = (column * 3) + (column+1);

        LOGGER.info(String.format("actualRow = %d, actualColumn = %d", actualRow, actualColumn)); //NOPMD Guard log statement is not needed here

        /* Create 2d Array of Grids */

        gridArray = new Grid[actualRow][actualColumn];
    }


    public Grid[][] getGridArray()
    {
        return gridArray;
    }

    public void setGridArray(Grid[][] gridArray)
    {
        this.gridArray = gridArray;
    }

    public int getPlayerRow()
    {
        return playerRow;
    }

    public int getPlayerColumn()
    {
        return playerColumn;
    }

    public int getEndRow()
    {
        return endRow;
    }

    public int getEndColumn()
    {
        return endColumn;
    }

    public void setPlayerRow(int playerRow)
    {
        this.playerRow = playerRow;
    }

    public void setPlayerColumn(int playerColumn)
    {
        this.playerColumn = playerColumn;
    }

    public void setEndRow(int endRow)
    {
        this.endRow = endRow;
    }

    public void setEndColumn(int endColumn)
    {
        this.endColumn = endColumn;
    }

    public boolean isEmptyListofKey()
    {
        return listOfAcquiredKeys.isEmpty();
    }

    /* Set the Borders for the maze game */
    public void setBorder()
    {

        for (int i = 0; i < actualRow; i++)
        {
            for (int j = 0; j < actualColumn; j++)
            {

                if (i == 0)
                {
                    /* will get default character */

                    gridArray[i][j] = new HorizantalWall();
                    LOGGER.info(String.format("Character of gridArray[%d][%d] = %c", i, j, gridArray[i][j].getCh())); //NOPMD

                    //first column
                    if (j == 0)
                    {
                        setGridCh(i, j, '\u250c');
                    } else
                    {
                        //last column
                        if (j == actualColumn - 1)
                        {
                            setGridCh(i, j, '\u2510');
                        }
                    }
                } else
                {
                    //last row of the maze
                    if (i == actualRow - 1)
                    {
                        gridArray[i][j] = new HorizantalWall();
                        LOGGER.info(String.format("Character of gridArray[%d][%d] = %c", i, j, gridArray[i][j].getCh())); //NOPMD

                        //first column
                        if (j == 0)
                        {
                            setGridCh(i, j, '\u2514');
                        } else
                        {
                            //last column
                            if (j == actualColumn - 1)
                            {
                                setGridCh(i, j, '\u2518');
                            }
                        }

                    } else
                    {
                        if ((j == 0 || j == actualColumn - 1) && !(i == 0 || i == actualRow - 1))
                        {
                            gridArray[i][j] = new VerticalWall();
                            LOGGER.info(String.format("Character of gridArray[%d][%d] = %c", i, j, gridArray[i][j].getCh())); //NOPMD
                        }
                    }
                }
            }

        }

    }

    /* Set the Grid character and add the logging Statement */

    private void setGridCh(int i, int j, char ch)
    {
        gridArray[i][j].setCh(ch);
        LOGGER.info(String.format("Character of gridArray[%d][%d] = %c", i, j, gridArray[i][j].getCh())); //NOPMD
    }


    /* Set the Vertical Wall and Vertical Doors */

    public void setVertical(int actualRowOfGrid, int actualColumnOfGrid, Grid grid)
    {
        if (gridArray[actualRowOfGrid][actualColumnOfGrid] == null)
        {
            gridArray[actualRowOfGrid][actualColumnOfGrid] = grid;
        }

        if (actualRowOfGrid == 0)
        {
            gridArray[actualRowOfGrid][actualColumnOfGrid].setCh('\u252c');
        } else
        {
            if (actualRowOfGrid == (actualRow - 1))
            {
                gridArray[actualRowOfGrid][actualColumnOfGrid].setCh('\u2534');
            }
            else
            {
                setAdjoining(actualRowOfGrid, actualColumnOfGrid);
            }
        }

    }


    /* Set the Horizantal Wall and Horizantal Doors */

    public void setHorizantal(int actualRowOfGrid, int actualColumnOfGrid, Grid grid)
    {

        if (gridArray[actualRowOfGrid][actualColumnOfGrid] == null)
        {
            gridArray[actualRowOfGrid][actualColumnOfGrid] = grid;
        }

        if (actualColumnOfGrid == 0)
        {
            gridArray[actualRowOfGrid][actualColumnOfGrid].setCh('\u251c');
        }
        else
        {
            if (actualColumnOfGrid == (actualColumn - 1))
            {
                gridArray[actualRowOfGrid][actualColumnOfGrid].setCh('\u2524');
            }
            else
            {
                setAdjoining(actualRowOfGrid,actualColumnOfGrid);
            }
        }

    }


    /* To check Adjoining characters when adding Walls and Doors to the Maze */

    private void setAdjoining(int actualR, int actualCol)
    {
        if ((gridArray[actualR][actualCol - 1] != null)
                && (gridArray[actualR][actualCol + 1] != null)
                && (gridArray[actualR - 1][actualCol] == null)
                && (gridArray[actualR + 1][actualCol] != null))
        {
            gridArray[actualR][actualCol].setCh('\u252c');
        }
        else
        {
            if ((gridArray[actualR][actualCol - 1] != null)
                    && (gridArray[actualR][actualCol + 1] != null)
                    && (gridArray[actualR - 1][actualCol] != null)
                    && (gridArray[actualR + 1][actualCol] == null))
            {
                gridArray[actualR][actualCol].setCh('\u2534');
            }
            else
            {
                if ((gridArray[actualR][actualCol - 1] == null)
                        && (gridArray[actualR][actualCol + 1] != null)
                        && (gridArray[actualR - 1][actualCol] != null)
                        && (gridArray[actualR + 1][actualCol] != null))
                {
                    gridArray[actualR][actualCol].setCh('\u251c');
                }
                else
                {
                    if ((gridArray[actualR][actualCol - 1] != null)
                            && (gridArray[actualR][actualCol + 1] == null)
                            && (gridArray[actualR - 1][actualCol] != null)
                            && (gridArray[actualR + 1][actualCol] != null))
                    {
                        gridArray[actualR][actualCol].setCh('\u2524');
                    }
                    else
                    {
                        if ((gridArray[actualR][actualCol - 1] != null)
                                && (gridArray[actualR][actualCol + 1] != null)
                                && (gridArray[actualR - 1][actualCol] != null)
                                && (gridArray[actualR + 1][actualCol] != null))
                        {
                            gridArray[actualR][actualCol].setCh('\u253c');
                        }
                        else
                        {
                            if ((gridArray[actualR][actualCol - 1] != null)
                                    && (gridArray[actualR][actualCol + 1] == null)
                                    && (gridArray[actualR - 1][actualCol] == null)
                                    && (gridArray[actualR + 1][actualCol] != null))
                            {
                                gridArray[actualR][actualCol].setCh('\u2510');
                            }
                            else
                            {
                                if ((gridArray[actualR][actualCol - 1] == null)
                                        && (gridArray[actualR][actualCol + 1] != null)
                                        && (gridArray[actualR - 1][actualCol] == null)
                                        && (gridArray[actualR + 1][actualCol] != null))
                                {
                                    gridArray[actualR][actualCol].setCh('\u250c');
                                }
                                else
                                {
                                    if ((gridArray[actualR][actualCol - 1] == null)
                                            && (gridArray[actualR][actualCol + 1] != null)
                                            && (gridArray[actualR - 1][actualCol] != null)
                                            && (gridArray[actualR + 1][actualCol] == null))
                                    {
                                        gridArray[actualR][actualCol].setCh('\u2514');
                                    }
                                    else
                                    {
                                        if ((gridArray[actualR][actualCol - 1] != null)
                                                && (gridArray[actualR][actualCol + 1] == null)
                                                && (gridArray[actualR - 1][actualCol] != null)
                                                && (gridArray[actualR + 1][actualCol] == null))
                                        {
                                            gridArray[actualR][actualCol].setCh('\u2518');
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    /* Decorate Grids by Setting Keys */

    public void setKeysIntoGrid(int actualR, int actualCol, int color)
    {

        /* To Display the Keys(Maximum 3) */

        if(gridArray[actualR][actualCol] == null)
        {
            (gridArray[actualR][actualCol] = new Empty()).setColor(color);
            gridArray[actualR][actualCol].setCh('\u2555');
        }
        else
        {
            if(gridArray[actualR][actualCol+1] == null)
            {
                (gridArray[actualR][actualCol+1] = new Empty()).setColor(color);
                gridArray[actualR][actualCol+1].setCh('\u2555');
            }
            else
            {
                if(gridArray[actualR][actualCol-1] == null)
                {
                    (gridArray[actualR][actualCol-1] = new Empty()).setColor(color);
                    gridArray[actualR][actualCol-1].setCh('\u2555');
                }
            }
        }

        /*** Use of Decorator pattern ***/
        /*** Decorate grid by Key ***/

        gridArray[actualR][actualCol] = new Key(gridArray[actualR][actualCol],color);

    }

    /* Decorate Grids by Setting Messages */

    public void setMessagesIntoGrid(int actualR, int actualCol, String message)
    {

        if(gridArray[actualR][actualCol] == null)
        {
            (gridArray[actualR][actualCol] = new Empty()).setCh('M');
        }

        /*** Use of Decorator pattern ***/
        /*** Decorate grid by Message ***/

        gridArray[actualR][actualCol] = new Message(gridArray[actualR][actualCol],message);
    }


    /* Finally, Initialize all Null GridArray in the maze as Empty Grid */

    public void setMazeGrid()
    {
        for (int i = 0; i < actualRow; i++)
        {
            for (int j = 0; j < actualColumn; j++)
            {
                /* Create an Empty grid */

                if (gridArray[i][j] == null)
                {
                    gridArray[i][j] = new Empty();

                    LOGGER.info(String.format("Empty Grid: ActualRow=%d, ActualColumn=%d", i,j)); //NOPMD

                }
            }
        }
    }

    /* Move the Player across the Maze */

    public String movePlayer()
    {
        char ch;
        String message = "";

        do
        {
            /* Getting input from the user */
            System.out.println("\n Please Input n/W -> UP, s/S -> DOWN, e/D -> RIGHT, w/A -> LEFT to Move the Player"); //NOPMD
            System.out.print(" Input: ");

            ch = input.next().charAt(0);

            /* Move Up */
            if(ch=='n' || ch=='W')
            {
                LOGGER.info(String.format("Player select Move UP: Current location of Player row=%d, column=%d" //NOPMD
                        , playerRow, playerColumn));
                message = doMove(playerRow-1,playerColumn,playerRow-2,playerColumn);
            }
            else
            {
                /* Move Down */
                if(ch=='s' || ch=='S')
                {
                    LOGGER.info(String.format("Player select Move DOWN: Current location of Player row=%d, column=%d" //NOPMD
                            , playerRow, playerColumn));
                    message = doMove(playerRow+1,playerColumn,playerRow+2,playerColumn);
                }
                else
                {
                    /* Move Right */
                    if(ch=='e' || ch=='D')
                    {
                        LOGGER.info(String.format("Player select Move RIGHT: Current location of Player row=%d, column=%d" //NOPMD
                                , playerRow, playerColumn));
                        message = doMove(playerRow,playerColumn+2,playerRow,playerColumn+4);
                    }
                    else
                    {
                        /* Move Left */
                        if(ch=='w' || ch=='A')
                        {
                            LOGGER.info(String.format("Player select Move LEFT: Current location of Player row=%d, column=%d" //NOPMD
                                    , playerRow, playerColumn));
                            message = doMove(playerRow,playerColumn-2,playerRow,playerColumn-4);
                        }
                        /* Invalid Input */
                        else
                        {
                            LOGGER.info(String.format("Player select Invalid OPTION: Current location of Player row=%d, column=%d" //NOPMD
                                    , playerRow, playerColumn));
                            System.out.println("\n Invalid Input!!! Please Enter correct input!");
                        }
                    }

                }
            }



        }while( !(ch=='n' || ch=='s' || ch=='e' || ch=='w'||
                ch=='W' || ch=='S' || ch=='D' || ch=='A'));

        return message;
    }


    /* Move Left,Right,Up,or Down  According to the user input */

    private String doMove(int a,int b,int c, int d)
    {
        char space = ' ';

        /* get the obstacle and color by polymorhically */

        char obstacle = gridArray[a][b].getCh();
        int color = gridArray[a][b].getColor();
        String message = "";

        if( (obstacle == space) || (obstacle == '\u2592' && listOfAcquiredKeys.contains(color)) )
        {

            /* Check current location contains any Keys */
            checkKeys(c,d);

            /* Check current location contains any Messages */
            if(!(gridArray[c][d].getMessages().equals("")))
            {
                /* If current location contains any message get that message by polymorphically at runtime */
                message = gridArray[c][d].getMessages();
            }

            /* Set the Player new location */
            gridArray[c][d] = gridArray[playerRow][playerColumn];

            /* Reset the player current location */
            gridArray[playerRow][playerColumn] = new Empty();

            /* update the Row AND Column of the Player */
            playerRow = c;
            playerColumn = d;

            LOGGER.info(String.format("Player Location After Move: row=%d, column=%d" //NOPMD
                    , playerRow, playerColumn));

            /* Clear the Screen AND re-position the curser */
            System.out.print("\033[2J\033[H");

            /* Print the maze */
            printMaze();
        }
        else
        {
            if(obstacle == '\u2592' )
            {
                System.out.println("\n Needs the key to the particular door!!");
            }
            else
            {
                System.out.println("\n Invalid Move!! Player cant move into a wall");
            }

        }

        return message;
    }


    /* Method to Check current location contains any Keys */

    public void checkKeys(int ro, int col)
    {

        if(!(gridArray[ro][col].getListOfKeys().isEmpty()))
        {
            /* If current location contains any keys add that in to listOfKeys */

            /* Get List of Keys of particular grid polymorphically at runtime */
            /* Iterate through the particular grid list and add the key into listOfAcquiredKeys */

            for (int i:gridArray[ro][col].getListOfKeys())
            {
                listOfAcquiredKeys.add(i);
            }

            /* Reset the Adjacent grid squares */

            gridArray[ro][col+1] = new Empty();
            gridArray[ro][col-1] = new Empty();
        }
    }


    /* Display Player Acquired keys */

    public void displayKeys()
    {
        String str = "";
        char ch = '\u2555';

        for (int i: listOfAcquiredKeys)
        {
            str = String.format(str + " \033[3%dm%c \033[m",i, ch);
        }

        System.out.println("\n Player Acquired Keys -> " + str);
    }

    public void printMaze()
    {
        for (int i = 0; i < actualRow; i++)
        {
            for (int j = 0; j < actualColumn; j++)
            {
                /* Print the maze by polymorphic! */

                System.out.print(gridArray[i][j].toString());
            }

            // Skip the Line
            System.out.println();
        }
    }

}
