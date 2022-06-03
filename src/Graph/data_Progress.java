
package Graph;

import smartlog_system.Database_Queries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class data_Progress {
    
    Database_Queries DQ = new Database_Queries();
    
    public int progress(String LogType) throws ClassNotFoundException, SQLException{
        
        double numberOfPersonnels = numberOfPersonnels();
        double logged = numberOfLogs(LogType);
        
        double Logprogress = (logged / numberOfPersonnels)* 100.00;
        
        int progress = (int) Logprogress;
        return progress;
    }
    
    private int numberOfLogs(String LogType) throws ClassNotFoundException, SQLException{
        int numberOfLogs = 0;
        
        Date date = new Date();
        SimpleDateFormat C_date = new SimpleDateFormat("MMMM dd yyyy");
        String currentDate = C_date.format(date);
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        String Logname = DQ.fetch("Select * from dailylogschedule where Status = 'IN-USE' and Date = '"+currentDate+"'", "schName");
        
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartlogdb?useTimezone=true&serverTimezone=UTC&useSSL=false","root","Anonsawon27"); Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from checkinout where Log_Name = '"+Logname+"' and Check_type = '"+LogType+"' and Date = '"+currentDate+"'");
            
            while(rs.next()){
                numberOfLogs = numberOfLogs + 1;
            }
            stmt.close();
        }
        System.out.println("Logs: "+numberOfLogs);
        return numberOfLogs;
    }
    
    private int numberOfPersonnels() throws ClassNotFoundException, SQLException{
        int numberOfEmployee = 0;
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartlogdb?useTimezone=true&serverTimezone=UTC&useSSL=false","root","Anonsawon27"); Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM smartlogdb.personnels");
            
            while(rs.next()){
                numberOfEmployee = numberOfEmployee + 1;
            }
            
            stmt.close();
        }
        //System.out.println("Personnels: "+numberOfEmployee);
        return numberOfEmployee;
    }
    
}
