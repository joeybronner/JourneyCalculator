package connect;

import java.sql.*;

/**
 * Class for Database interactions
 */
public class DatabaseConnect
{
    Connection con = null; 
    Statement sta; 
    ResultSet re; 
    ResultSetMetaData metaBase; 
    String login;
    String passwd;
    public static final String DRIVER = "com.mysql.jdbc.Driver"; 
    public static final String URL = "jdbc:mysql://109.190.180.29:3306/journey";


    /**
     * Connection opening
     */
    public void Connexion()
    {     
    	login="journey";
    	passwd="calculator";
    
        try
        { 
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, login, passwd);
            sta = con.createStatement(); 
            System.out.println("-- Connexion ouverte --");
        } 
        catch (ClassNotFoundException ex)
        { 
            System.out.println("Erreur de connexion : "); 
            System.out.println(ex); 
        } 
        catch (SQLException ex) { 
            System.out.println("Erreur de connexion : "); 
            System.out.println(ex); 
        } 
    }

    /**
     * Database searching
     *
     * @param query
     * @return result of the query
     */
    public ResultSet Rechercher(String query)
    { 
         try
         { 
		     re=sta.executeQuery(query); 
         } 
         catch (SQLException ex)
         { 
        	 System.err.println(ex); 
         } 
     return re;
    }

    /**
     * Database adding
     * @param query
     */
    public void Ajouter(String query)
    { 
    	try
    	{ 
		     sta.executeUpdate(query);
    	} 
    	catch (SQLException ex)
    	{ 
             System.err.println(ex); 
    	}
    }

    /**
     * Database modification
     * @param query
     */
    public void Modifier(String query)
    {
        try {
            sta.executeUpdate(query);
            System.out.println("Modifié.");
    	} 
    	catch (SQLException ex)
    	{ 
    		 System.err.println(ex);
        }
    }

    /**
     * Trucate table
     * @param table
     */
    public void ViderTable(String table)
    { 
    	try
    	{ 
		     sta.executeUpdate("TRUNCATE TABLE " + table); 
		     System.out.println("Table "+table+" videe");
    	} 
    	catch (SQLException ex)
    	{ 
    		 System.err.println(ex); 
    	}
    }

    /**
     * Closing database connection
     */
    public void Deconnexion()
    { 
    	try
    	{
            re.close();
            System.out.println("-- Connexion fermée --");
        } catch (SQLException e)
    	{ 
    		 System.out.println("Echec de fermeture de la connexion."); 
    	} 
    	
    } 
} 
