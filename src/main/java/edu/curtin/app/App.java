package edu.curtin.app;

/****************************************************************************
 * File: App.java												            *
 * Author: Mr.A.S.M.Thasneem                          				    	*
 * Date Created: 2022/04/18                            				    	*
 * Date Modified: 2022/04/25                             				    *
 * Purpose: To Run the Maze Game on terminal  			                    *
 ****************************************************************************/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class App
{
    private static List<Grid> endPoints = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

     // To Get input from the user
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        Maze maze = null;
        String message = ""; //NOPMD  Because this variable is used so many places to check some functionality.
        String file;


        try
        {

            System.out.print(" Please Enter the File Name of Maze data: ");
            file = input.next();

            /* Create the Maze by reading File */

            maze = readFile(file, maze);

            /* Check and Initialize Empty grid squares */

            maze.setMazeGrid();

            Grid[][] gridArray = maze.getGridArray();

            /* Display the Maze */

            maze.printMaze();

            /* Check Beginning position of player contains any Keys */
            maze.checkKeys(maze.getPlayerRow(), maze.getPlayerColumn());

            if(!(maze.isEmptyListofKey()))
            {
                /* Clear the Screen AND re-position the curser */
                System.out.print("\033[2J\033[H");

                /* Re display the maze when starting point have keys.*/
                maze.printMaze();
                maze.displayKeys();
            }

            /* Check Beginning position of player contains any Messages */

            if(!(gridArray[maze.getPlayerRow()][maze.getPlayerColumn()].getMessages().equals("")))
            {
                System.out.println("\n Messages ->" + gridArray[maze.getPlayerRow()][maze.getPlayerColumn()].getMessages());
            }

            /* Loop until player reach the End Point */

            while( checkLoop(maze) )
            {
                /* Move the Player */
                message = maze.movePlayer();

                /* Check current grid contains any messages */
                if(!message.equals(""))
                {
                    System.out.println("\n Messages ->" + message);
                }

                /* Check current grid contains any Keys */
                if(!(maze.isEmptyListofKey()))
                {
                    maze.displayKeys();
                }
            }

            System.out.println(" \n \033[31mCongratulations on Winning the \033[33mGame\033[m");

        } catch (MyException e)
        {
            System.out.println("\n" + e);
        }


    }


    /* Create the maze by reading the data file */

    private static Maze readFile(String fileName, Maze maze) throws MyException
    {
        FileReader fr; //NOPMD I already closed all the reader but it still gives error CloseResource.
        BufferedReader buReader; //NOPMD


        String line;
        int mazeRow =0;int mazeColumn = 0;
        int row = 0, column = 0;int color;
        int checkStart = 0; int checkEnd = 0;

        int actualRow, actualColumn;
        Grid[][] gridArray = null;



        try
        {
            fr = new FileReader(fileName);
            buReader = new BufferedReader(fr);


            line = buReader.readLine();

            while (line != null)
            {
                String[] splitline = line.split(" ");
                String str = splitline[0];


                if (splitline.length == 2)
                {
                    try
                    {
                        mazeRow = Integer.parseInt(splitline[0]);
                        mazeColumn = Integer.parseInt(splitline[1]);
                    }
                    catch (NumberFormatException e)
                    {
                        LOGGER.info(String.format("Number Format exception!!  ")); //NOPMD
                        throw new MyException(" Invalid Number Format \n",e);
                    }

                    LOGGER.info(String.format("Maze Size row=%d, column=%d", row, column)); //NOPMD Guard log statement is not needed here

                    // Create the maze Object
                    maze = new Maze(mazeRow, mazeColumn);
                    gridArray = maze.getGridArray();

                    /* Will create Borders to the maze */
                    maze.setBorder();

                }
                else
                {
                    try
                    {
                        row = Integer.parseInt(splitline[1]);
                        column = Integer.parseInt(splitline[2]);
                    }
                    catch (NumberFormatException e)
                    {
                        LOGGER.info(String.format("Number Format exception!!  ")); //NOPMD
                        throw new MyException(" Invalid Number Format \n",e);
                    }

                    /* Check input file Array bound */
                    if( (row >= mazeRow) || (column >= mazeColumn ) )
                    {
                        System.out.println("\n The row or column specified in the file is outside the bound of maze!!! ");
                        LOGGER.info( String.format(" Line %s %d %d is Ignored",str,row,column)); //NOPMD
                        System.out.println( String.format(" So the Line %s %d %d is Ignored",str,row,column));
                    }
                    else
                    {
                        actualRow = calcActualrow(row);
                        actualColumn = calcActualColumn(column);

                        switch (str)
                        {
                            case "S":
                                LOGGER.info(String.format("Player row=%d, column=%d", row, column)); //NOPMD
                                LOGGER.info(String.format("Player ActualRow=%d, ActualColumn=%d", actualRow, actualColumn)); //NOPMD


                                if(checkStart == 0)
                                {
                                    /* Set player row and column in maze */
                                    maze.setPlayerRow(actualRow);
                                    maze.setPlayerColumn(actualColumn);

                                    gridArray[actualRow][actualColumn] = new Player();
                                    checkStart++;
                                }
                                else
                                {
                                    System.out.println("\n Multiple Starting Location is not Allowed!!");
                                    LOGGER.info( String.format(" Line %s %d %d is Ignored",str,row,column)); //NOPMD
                                    System.out.println( String.format(" So the Line %s %d %d is Ignored",str,row,column));
                                }

                                break;

                            case "E":
                                LOGGER.info(String.format("End Point row=%d, column=%d", row, column)); //NOPMD
                                LOGGER.info(String.format("End Point ActualRow=%d, ActualColumn=%d", actualRow, actualColumn)); //NOPMD

                                /* Set End row and column to maze */
                                maze.setEndRow(actualRow);
                                maze.setEndColumn(actualColumn);

                                gridArray[actualRow][actualColumn] = new EndPoint();

                                /* Set the Row and Column Polymorphically  */
                                gridArray[actualRow][actualColumn].setRow(actualRow);
                                gridArray[actualRow][actualColumn].setColumn(actualColumn);

                                /* Add the End points to the list */
                                endPoints.add(gridArray[actualRow][actualColumn]);
                                checkEnd++;

                                break;

                            case "WV":
                                LOGGER.info(String.format("Vertical Wall row=%d, column=%d", row, column)); //NOPMD

                                actualColumn = actualColumn - 2;
                                gridArray[actualRow][actualColumn] = new VerticalWall();

                                actualRow = actualRow - 1;
                                maze.setVertical(actualRow, actualColumn, new VerticalWall());

                                actualRow = actualRow + 2;
                                maze.setVertical(actualRow, actualColumn, new VerticalWall());

                                break;

                            case "WH":
                                LOGGER.info(String.format("Horizantal Wall row=%d, column=%d", row, column)); //NOPMD

                                actualRow = actualRow - 1;
                                gridArray[actualRow][actualColumn] = new HorizantalWall();

                                actualColumn = actualColumn - 1;
                                gridArray[actualRow][actualColumn] = new HorizantalWall();

                                actualColumn = actualColumn - 1;
                                maze.setHorizantal(actualRow, actualColumn, new HorizantalWall());

                                actualColumn = actualColumn + 3;
                                gridArray[actualRow][actualColumn] = new HorizantalWall();

                                actualColumn = actualColumn + 1;
                                maze.setHorizantal(actualRow, actualColumn, new HorizantalWall());

                                break;

                            case "DV":
                                color = Integer.parseInt(splitline[3]);
                                LOGGER.info(String.format("Vertical Door row=%d, column=%d, color=%d", row, column, color)); //NOPMD

                                actualColumn = actualColumn - 2;
                                gridArray[actualRow][actualColumn] = new VerticalDoor();
                                gridArray[actualRow][actualColumn].setColor(color);

                                actualRow = actualRow - 1;
                                maze.setVertical(actualRow, actualColumn, new VerticalWall());

                                actualRow = actualRow + 2;
                                maze.setVertical(actualRow, actualColumn, new VerticalWall());
                                break;

                            case "DH":
                                try
                                {
                                    color = Integer.parseInt(splitline[3]);
                                }
                                catch (NumberFormatException e)
                                {
                                    LOGGER.info(String.format("Number Format exception!!  ")); //NOPMD
                                    throw new MyException(" Invalid Number Format \n",e);
                                }

                                LOGGER.info(String.format("Horizantal Door row=%d, column=%d, color=%d", row, column, color)); //NOPMD

                                actualRow = actualRow - 1;
                                gridArray[actualRow][actualColumn] = new HorizantalDoor();
                                gridArray[actualRow][actualColumn].setColor(color);

                                actualColumn = actualColumn - 1;
                                gridArray[actualRow][actualColumn] = new HorizantalDoor();
                                gridArray[actualRow][actualColumn].setColor(color);

                                actualColumn = actualColumn - 1;
                                maze.setHorizantal(actualRow, actualColumn, new VerticalWall());

                                actualColumn = actualColumn + 3;
                                gridArray[actualRow][actualColumn] = new HorizantalDoor();
                                gridArray[actualRow][actualColumn].setColor(color);

                                actualColumn = actualColumn + 1;
                                maze.setHorizantal(actualRow, actualColumn, new VerticalWall());


                                break;

                            case "M":
                                String message = "";

                                for (int i = 3; i < splitline.length; i++)
                                {
                                    message = message + splitline[i] + " ";
                                }

                                LOGGER.info(String.format("Message row=%d, column=%d, message=%s", row, column, message)); //NOPMD
                                maze.setMessagesIntoGrid(actualRow,actualColumn,message);
                                break;

                            case "K":

                                try
                                {
                                    color = Integer.parseInt(splitline[3]);
                                }
                                catch (NumberFormatException e)
                                {
                                    throw new MyException(" Invalid Number Format \n",e);
                                }

                                LOGGER.info(String.format("Key row=%d, column=%d, color=%d", row, column, color)); //NOPMD

                                maze.setKeysIntoGrid(actualRow,actualColumn,color);
                                break;

                            default:
                                System.out.println("Invalid File Format!!");
                                break;
                        }

                    }

                }

                line = buReader.readLine();
            }

            int defRow = calcActualrow(0);
            int defColumn = calcActualColumn(0);

            /* When data file has no S location */

            if(checkStart == 0)
            {
                System.out.println("\n There is no Starting location provided in the given data file " +
                        " So the default starting position added");

                for (int i = 0; i <mazeRow ; i++)
                {
                    for (int j = 0; j <mazeColumn ; j++)
                    {

                        if( gridArray[calcActualrow(i)][calcActualColumn(j)] == null)
                        {
                            defRow = calcActualrow(i);
                            defColumn = calcActualColumn(j);
                        }
                    }
                }

                maze.setPlayerRow(defRow);
                maze.setPlayerColumn(defColumn);

                gridArray[defRow][defColumn] = new Player();


            }

            /* When data file has no E location */
            if(checkEnd == 0)
            {
                System.out.println("\n There is no Ending location provided in the given data file " +
                        " So the default Ending position added");

                for (int i = 0; i <mazeRow ; i++)
                {
                    for (int j = 0; j <mazeColumn ; j++)
                    {

                        if( gridArray[calcActualrow(i)][calcActualColumn(j)] == null)
                        {
                            defRow = calcActualrow(i);
                            defColumn = calcActualColumn(j);
                        }
                    }
                }

                /* Set End row and column to maze */
                maze.setEndRow(defRow);
                maze.setEndColumn(defColumn);

                gridArray[defRow][defColumn] = new EndPoint();

                /* Set the Row and Column Polymorphically  */
                gridArray[defRow][defColumn].setRow(defRow);
                gridArray[defRow][defColumn].setColumn(defColumn);

                /* Add the End points to the list */
                endPoints.add(gridArray[defRow][defColumn]);
            }

            /* close the file reader */
            buReader.close();
            fr.close();


        }
        catch (IOException e)
        {
            LOGGER.info("IOException in Method readFile!!");
            throw new MyException(" Could not read from " + fileName, e);
        }


        return maze;


    }

    /* Calculate actual row of the maze */
    private static int calcActualrow(int row)
    {
        int x = row + 1;
        int actualRow = row + x;

        return actualRow;
    }

    /* Calculate actual column of the maze */
    private static int calcActualColumn(int column)
    {
        //Default when column == 0
        int actualColumn = 2;

        if (column != 0)
        {
            int x = 2 + (3 * column);
            actualColumn = column + x;
        }

        return actualColumn;
    }

    /* Check the Winning Condition */
    private static boolean checkLoop(Maze maze)
    {
        boolean bol = true;

        /* Have Multiple End Points */

        if(endPoints.size() > 1)
        {
            for (Grid o: endPoints)
            {
                if((maze.getPlayerRow() == o.getRow()) && (maze.getPlayerColumn() == o.getColumn()) )
                {
                    bol = false;
                }

            }
        }
        else
        {
           bol =  !((maze.getPlayerRow() == maze.getEndRow()) && (maze.getPlayerColumn() == maze.getEndColumn()));
        }

        return bol;
    }

}