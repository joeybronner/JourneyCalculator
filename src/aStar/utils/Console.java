package aStar.utils;

import java.util.Scanner;

public class Console
{
	Scanner sc = new Scanner(System.in);
	
        public void ecrireConsole(String s)
        {
            System.out.println(s);
        }
        
        public int poserQuestionOneTwo(String question)
        {
        	System.out.println(question);
        	String reponse = sc.nextLine();
        	if (Integer.parseInt(reponse) == 1)
        	{
        		return 1;
        	}
        	else if (Integer.parseInt(reponse) == 2)
        	{
        		return 2;
        	}
        	else
        	{
        		return 99;
        	}
        }
}