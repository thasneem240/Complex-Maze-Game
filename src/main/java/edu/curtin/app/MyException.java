package edu.curtin.app;

/****************************************************************************
 * File: MyException.java												    *
 * Author: Mr.A.S.M.Thasneem                          				    	*
 * Date Created: 2022/04/18                            				    	*
 * Date Modified: 2022/04/25                             				    *
 * Purpose: To do a appropriate error handling		                        *
 ****************************************************************************/

public class MyException extends Exception
{
    public MyException(String message)
    {
        super(message);
    }

    public MyException(String message,Throwable cause)
    {
        super(message, cause);
    }
    
}