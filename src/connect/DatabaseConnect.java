package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

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
    
    public ResultSet Rechercher(String query)
    { 
         try
         { 
		     re=sta.executeQuery(query); 
		     System.out.println("Trouvé.");
         } 
         catch (SQLException ex)
         { 
        	 System.err.println(ex); 
         } 
     return re; 
    }
    
    public void Ajouter(String query)
    { 
    	try
    	{ 
		     sta.executeUpdate(query); 
		     System.out.println("Ajouté.");
    	} 
    	catch (SQLException ex)
    	{ 
             System.err.println(ex); 
    	}      
    }
    
    public void Modifier(String query)
    { 
    	try
    	{ 
		     sta.executeUpdate(query); 
		     System.out.println("Modifié.");
    	} 
    	catch (SQLException ex)
    	{ 
    		 System.err.println(ex); 
    	}    
    }   
    
    public void Supprimer(String query)
    { 
    	try
    	{ 
		     sta.executeUpdate(query); 
		     System.out.println("Supprimé.");
    	} 
    	catch (SQLException ex)
    	{ 
    		 System.err.println(ex); 
    	}    
    }
    
    public void Deconnexion()
    { 
    	try
    	{ 
    		 re.close(); 
    	}
    	catch (SQLException e)
    	{ 
    		 System.out.println("Echec de fermeture de la connexion."); 
    	} 
    	System.out.println("-- Connexion fermée --");
    } 
} 
