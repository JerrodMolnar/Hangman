/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Slim74562
 */
public class DatabaseRead
{
   
    private static Statement st;
    private static PreparedStatement pst;
    private static ResultSet rs;
    
    static int intcount = 0;
 
    public static void getWords()
    {
       
        try
        {
            
            String dbURL = "jdbc:ucanaccess://" + "c:\\EDATimes\\SkipVocabWords.accdb";
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String username = "";
            String password = "";
            
            Connection con = DriverManager.getConnection(dbURL, username, password);
            String query = "select [Words] from [SpellingWords]";
            st = con.createStatement();
            rs = st.executeQuery(query);
            
                       
            try
            {
                
                while(rs.next())
                {
                    intcount += 1;
                    
                }
                
                HangmanUI.words = new String[intcount];
                st.close();
                con.close();
                
                con = DriverManager.getConnection(dbURL, username, password);
                st = con.createStatement();
                rs = st.executeQuery(query);
                
                intcount = 0;
                while(rs.next())
                {                  
                    
                    HangmanUI.words[intcount] = (rs.getString("Words"));
                    
                    intcount +=1;
                }
                        
            }
            catch (SQLException sql)
            {
                st.close();
                con.close();
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        } 
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(DatabaseRead.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(HangmanUI.words.length);
        
       
    }
    
    
}
