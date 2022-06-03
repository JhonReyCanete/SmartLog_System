
package smartlog_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class Database_Queries {
    
    
    private LocalTime MorningIn_logTime;
    private LocalTime MorningOut_logTime;
    private LocalTime AfternoonIn_logTime;
    private LocalTime AfternoonOut_logTime;
    
    static private Connection con;
    
    public static Connection getDbConnection() throws ClassNotFoundException, SQLException{
        if(con==null){
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartlogdb?useTimezone=true&serverTimezone=UTC&useSSL=false","root","Anonsawon27");
        }
         return con;
    }
    
    public String[] arrayFetch(String TableCom, String Command, String dataFetched) throws ClassNotFoundException, SQLException{
        int count = Counting("SELECT * FROM "+TableCom+"");
        String[] fetchedData;
        fetchedData = new String[count]; 
        Statement stmt = Database_Queries.getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery(Command);
            int i = 0;
            while(rs.next()){
                String data = rs.getString(dataFetched);
                fetchedData[i] = data;
                i++;
            }
        
        
        return fetchedData;
    }
    
    public void CRUDE(String SQLquery) throws ClassNotFoundException, SQLException{
        Statement stmt = Database_Queries.getDbConnection().createStatement(); 
        stmt.execute(SQLquery);
        stmt.close();
   }
    
    public boolean verifyDevice(String IDnumber, String MCaddress) throws SQLException, ClassNotFoundException{
        boolean registered = false;
            Statement stmt = Database_Queries.getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM smartlogdb.devices where IDnumber = \""+IDnumber+"\" and BTaddress = \""+MCaddress+"\"");
         
            while(rs.next()){
                String data = rs.getString("IDnumber");
                if(!(data == null || data.isEmpty() || data.isBlank())){
                    registered = true;
                    break;
                }
            }
            stmt.close();
        
        return registered;
    }
    
    public String[] arrayDataFetch(String TableCom, String Table, String whereCommand, String dataFetched) throws ClassNotFoundException, SQLException{
       int count = Counting("SELECT * FROM "+TableCom+"");
       String[] fetchedData = new String[count];
        
            Statement stmt = Database_Queries.getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select * from "+Table+""+whereCommand+"");
            int i = 0;
            while(rs.next()){
                String data = rs.getString(dataFetched);
                fetchedData[i] = data;
                i++;
            }
            stmt.close();
        return fetchedData;
    }
    
    public String fetch(String Command, String dataFetched) throws ClassNotFoundException, SQLException{
        
            String fetchedData = "";
        
            Statement stmt = Database_Queries.getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery(Command);
            
            while(rs.next()){
                String data = rs.getString(dataFetched);
                fetchedData = data;
            }
            stmt.close();
        return fetchedData;
    }
    
    public String fetch(String Table, String whereCommand, String dataFetched) throws ClassNotFoundException, SQLException{
        
        String fetchedData = null;
        Statement stmt = Database_Queries.getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select * from "+Table+" "+whereCommand+"");
            
            while(rs.next()){
                String data = rs.getString(dataFetched);
                fetchedData = data;
            }
            stmt.close();
        return fetchedData;
    }
    
    public String User_Preveledge(String ID) throws ClassNotFoundException, SQLException{
        String Preveledge = "Level 1";
        Statement stmt = Database_Queries.getDbConnection().createStatement();
        ResultSet rs = stmt.executeQuery("select * from personnels where IDnumber = '"+ID+"'");
            
            while(rs.next()){
                String ID_num = rs.getString("IDnumber");
                Preveledge = rs.getString("Preveledge");
            }
            stmt.close();
        
        return Preveledge;
    }
    
    public boolean dataDuplicateSearch(String test) throws ClassNotFoundException, SQLException{
        
        boolean Result = false;
        
        Statement stmt = Database_Queries.getDbConnection().createStatement();
        ResultSet rs = stmt.executeQuery("select * from personnels");
            
            while(rs.next()){
                String ID_num = rs.getString("IDnumber");
                if(ID_num.equals(test)){
                    Result = true;
                    break;
                }
            }
            stmt.close();
        
        return Result;
    }
    
    public boolean isDuplicate(String cmd) throws ClassNotFoundException, SQLException{
        
        boolean Result = true;
        
        Statement stmt = Database_Queries.getDbConnection().createStatement();
        ResultSet rs = stmt.executeQuery(cmd);
            
            while(rs.next()){
                    Result = false;
                    break;
                }
            
            stmt.close();
        
        return Result;
    }
    
    public int Counting(String SQLquery) throws ClassNotFoundException, SQLException{
        int count = 0;
        
        Statement stmt = Database_Queries.getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery(SQLquery);
            while(rs.next()){
                count = count + 1;
            }
            stmt.close();
        
        return count;
    }
    
    public String[] getCurrentUser() throws ClassNotFoundException, SQLException{
        
        String[] UP = new String[10];
        
        Statement stmt = Database_Queries.getDbConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM smartlogdb.personnels WHERE Status = 'Current_User'");
            while(rs.next()){
                UP[5] = rs.getString("IDnumber");
                UP[6] = rs.getString("IDnumber");
                UP[1] = rs.getString("Lname");
                UP[2] = rs.getString("Fname");
                UP[7] = rs.getString("MI");
                UP[3] = UP[1] +", "+ UP[2];
                ///UP[4] = rs.getString("Position");
            }
            stmt.close();
        
        return UP;
    }
    
    public String[] getUser(String ID) throws ClassNotFoundException, SQLException{
        
        String[] Udetails = new String[15];
        
        Statement stmt = Database_Queries.getDbConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT personnels.IDnumber, personnels.Lname, personnels.HireDate, personnels.Fname, personnels.MI, personnels.Gender, personnels.Age, personnels.Preveledge,\n" +
            "departments.Position, departmentclass.Department, departmentclass.dept_ID, devices.BTname, devices.BTaddress, personnels.Password FROM personnels LEFT JOIN devices ON devices.Device_ID = personnels.Device_ID \n" +
            "RIGHT JOIN departments ON personnels.IDnumber = departments.IDnumber LEFT JOIN departmentclass ON departments.Department = departmentclass.Department where departments.IDnumber = '"+ID+"'");
            while(rs.next()){
                Udetails[2] = rs.getString("personnels.Lname");
                Udetails[3] = rs.getString("personnels.Fname");
                Udetails[4] = rs.getString("personnels.MI");
                Udetails[5] = rs.getString("personnels.Gender");
                Udetails[6] = rs.getString("personnels.Age");
                Udetails[7] = rs.getString("departments.Position");
                Udetails[8] = rs.getString("devices.BTname");
                Udetails[9] = rs.getString("devices.BTaddress");
                Udetails[10] = rs.getString("personnels.Password");
                Udetails[11] = rs.getString("personnels.HireDate");
                Udetails[12] = rs.getString("departmentclass.Department");
                Udetails[13] = rs.getString("personnels.Preveledge");
            }
            stmt.close();
        
        return Udetails;
    }
    
    public String[] CheckInOut(String WhereCondition) throws SQLException, ClassNotFoundException{
        String[] Udetails = new String[10];
        Statement stmt = Database_Queries.getDbConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM checkinout"+WhereCondition+"");
            
            while(rs.next()){
                Udetails[1] = rs.getString("CheckIO_ID");
                Udetails[2] = rs.getString("IDnumber");
                Udetails[3] = rs.getString("Check_type");
                Udetails[4] = rs.getString("Date");
                Udetails[5] = rs.getString("sch_ID");
                Udetails[6] = rs.getString("LogTime");
                Udetails[7] = rs.getString("EarlyTime");
                Udetails[8] = rs.getString("LateTime");
            }
            stmt.close();
        
        return Udetails;
    }
    
    public String[] getLogs(String date) throws ClassNotFoundException, SQLException{
        int i=0;
        int countLogs = Counting("Select * From dailylogschedule");
        String[] U = new String[countLogs];
        
        Statement stmt = Database_Queries.getDbConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM dailylogschedule WHERE Date = '"+date+"'");
            while(rs.next()){
                U[i] = rs.getString("schName");
                i++;
            }
            stmt.close();
        
        return U;        
    }
    
    public String[] getDepartments() throws ClassNotFoundException, SQLException{
        int dptCount = Counting("SELECT * FROM smartlogdb.departmentclass");
        int i = 0;
        String[] Depts = new String[dptCount];
        
        Statement stmt = Database_Queries.getDbConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM departmentclass");
            while(rs.next()){
                Depts[i] = rs.getString("Department");
                i++;
            }
            stmt.close();
        
        return Depts;
    }
    
    public String[] getLeaveRegister(String cmd) throws ClassNotFoundException, SQLException{

        String[] Udetails = new String[15];
        
        Statement stmt = Database_Queries.getDbConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM leaveclass"+cmd+"");
            while(rs.next()){
                Udetails[1] = rs.getString("LeaveID");
                Udetails[2] = rs.getString("LeaveName");
                Udetails[3] = rs.getString("IDnumber");
                Udetails[4] = rs.getString("StartDate");
                Udetails[5] = rs.getString("EndDate");
                Udetails[6] = rs.getString("State");
                Udetails[7] = rs.getString("DateFiled");
                Udetails[8] = rs.getString("Personnel");
            }
            stmt.close();
        
        return Udetails;
    }
    
    public String[] getSchedules(String cmd) throws ClassNotFoundException, SQLException{
        String[] Udetails = new String[15];
        
        Statement stmt = Database_Queries.getDbConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM dailylogschedule"+cmd+"");
            while(rs.next()){
                Udetails[1] = rs.getString("sch_ID");
                Udetails[2] = rs.getString("schName");
                Udetails[3] = rs.getString("Date");
                Udetails[4] = rs.getString("StartTime_1");
                Udetails[5] = rs.getString("EndTime_1");
                Udetails[6] = rs.getString("StartTime_2");
                Udetails[7] = rs.getString("EndTime_2");
                Udetails[10] = rs.getString("LogIO_cycle");
                Udetails[11] = rs.getString("Work_status");
                Udetails[12] = rs.getString("Day_Type");
                Udetails[13] = rs.getString("status");
            }
            stmt.close();
        
        return Udetails;        
    }
    
    public boolean Authentication(String User, String Pswrd) throws SQLException{
       
       boolean Verify = false;
       
       try {
               Statement stmt = Database_Queries.getDbConnection().createStatement();
               ResultSet rs = stmt.executeQuery("select * from personnels");
               while(rs.next()){
                   String N = rs.getString("IDnumber");
                   String A = rs.getString("Password");
                   
                   if(User.equals(N) && Pswrd.equals(A)){
                       
                       Verify = true;
                       break;
                   }
               }
               stmt.close();
           
                System.out.println("Verification Successfull");
                } catch (ClassNotFoundException ex) {
                System.out.println(ex);
                }
       
       return Verify;
   }
    
    public String[] fetchPresent(String Check_type, String Date, String Log_name) throws ClassNotFoundException, SQLException{
        
        String[] Present_IDs = arrayDataFetch("checkinout where Check_type = '"+Check_type+"' and Date = '"+Date+"' and Log_Name = '"+Log_name+"'", "checkinout"," where Check_type = '"+Check_type+"' and Date = '"+Date+"' and Log_Name = '"+Log_name+"'", "IDnumber");
        //System.out.println("Present ID fetched." + Present_IDs[0]);
        return Present_IDs;
    }
    
    public boolean logCheck(String ID, String Check_type, String Date, String Log_name) throws ClassNotFoundException, SQLException{
        boolean out = false;
        String log_ID = fetch("checkinout", " Where IDnumber='"+ID+"' and Log_Name='"+Log_name+"' and Date='"+Date+"' and Check_type='"+Check_type+"'", "CheckIO_ID");
        if("".equals(log_ID) || log_ID == null){out = true;}
        return out;
    }
    
    //Attendance matter
    public void getLogTime() throws ClassNotFoundException, SQLException{
        Date d = new Date();
        SimpleDateFormat day = new SimpleDateFormat("MMMM dd yyyy");
        String CurrentDate = day.format(d);
        String LogCycle = fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "LogIO_cycle");
        
        LocalTime default_time = LocalTime.parse("00:00:00");
        System.out.println("Log Cycle: " + LogCycle);
        
        if(null == LogCycle){
            MorningIn_logTime = default_time;
            MorningOut_logTime = default_time;
            AfternoonIn_logTime = default_time;
            AfternoonOut_logTime = default_time;
        }else switch (LogCycle) {
            case "Morning In":
                {
                    String Time = fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "StartTime_1");
                    LocalTime time = LocalTime.parse(Time);
                    MorningIn_logTime = time;
                    MorningOut_logTime = default_time;
                    AfternoonIn_logTime = default_time;
                    AfternoonOut_logTime = default_time;
                    break;
                }
            case "Afternoon In":
                {
                    String Time = fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "StartTime_2");
                    LocalTime time = LocalTime.parse(Time);
                    MorningIn_logTime = default_time;
                    MorningOut_logTime = default_time;
                    AfternoonIn_logTime = time.plusHours(12);
                    AfternoonOut_logTime = default_time;
                    break;
                }
            case "Morning In-Out":
                {
                    String Time = fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "StartTime_1");
                    String Time2 = fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "EndTime_1");
                    LocalTime time1 = LocalTime.parse(Time);
                    LocalTime time2 = LocalTime.parse(Time2);
                    MorningIn_logTime = time1;
                    MorningOut_logTime = time2;
                    AfternoonIn_logTime = default_time;
                    AfternoonOut_logTime = default_time;
                    break;
                }
            case "Afternoon In-Out":
                {
                    String Time = fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "StartTime_2");
                    String Time2 = fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "EndTime_2");
                    LocalTime time1 = LocalTime.parse(Time);
                    LocalTime time2 = LocalTime.parse(Time2);
                    MorningIn_logTime = default_time;
                    MorningOut_logTime = default_time;
                    AfternoonIn_logTime = time1.plusHours(12);
                    AfternoonOut_logTime = time2.plusHours(12);
                    break;
                }
            case "Morning In and Afternoon Out":
                {
                    String Time = fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "StartTime_1");
                    String Time2 = fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "EndTime_2");
                    LocalTime time1 = LocalTime.parse(Time);
                    LocalTime time2 = LocalTime.parse(Time2);
                    MorningIn_logTime = time1;
                    MorningOut_logTime = default_time;
                    AfternoonIn_logTime = default_time;
                    AfternoonOut_logTime = time2.plusHours(12);
                    break;
                }
            case "Morning In-Out and Afternoon In-Out":
                {
                    String Time = fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "StartTime_1");
                    String Time2 = fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "EndTime_1");
                    String Time3 = fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "StartTime_2");
                    String Time4 = fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "EndTime_2");
                    LocalTime time1 = LocalTime.parse(Time);
                    LocalTime time2 = LocalTime.parse(Time2);
                    LocalTime time3 = LocalTime.parse(Time3);
                    LocalTime time4 = LocalTime.parse(Time4);
                    MorningIn_logTime = time1;
                    MorningOut_logTime = time2;
                    AfternoonIn_logTime = time3.plusHours(12);
                    AfternoonOut_logTime = time4.plusHours(12);
                    break;
                }
            default:
                MorningIn_logTime = default_time;
                MorningOut_logTime = default_time;
                AfternoonIn_logTime = default_time;
                AfternoonOut_logTime = default_time;
                break;
        }
    }
    
    public String[] getCurrentUse_OpenLog() throws ClassNotFoundException, SQLException{
        Date d = new Date();
        SimpleDateFormat day = new SimpleDateFormat("MMMM dd yyyy");
        SimpleDateFormat amPm = new SimpleDateFormat("a");
        SimpleDateFormat timeF = new SimpleDateFormat("HH:MM:ss");
        String CurrentDate = day.format(d);
        String AmPm = amPm.format(d);
        LocalTime Time = LocalTime.parse(timeF.format(d));
        LocalTime logTime = Time;
        getLogTime();
        
        String LogCycle = fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "LogIO_cycle");
        String open[] = new String[3];
        
        LocalTime Morning_timeRange = LocalTime.parse("12:00:00");
        
        if(null != LogCycle)switch (LogCycle) {
            case "Morning In":
                if(logTime.isBefore(MorningIn_logTime) || logTime.equals(MorningIn_logTime)){
                    open[0] = "Mi"; // <- Min
                    open[1] = String.valueOf(MorningIn_logTime) + " AM";
                }else if(logTime.isAfter(MorningIn_logTime) && (logTime.isBefore(Morning_timeRange) || logTime.equals(Morning_timeRange))){
                    open[0] = "Mi"; // Min -> 12am
                    open[1] = String.valueOf(MorningIn_logTime) + " AM";
                }else{
                    open[0] = "None";
                    open[1] = "00:00 AM";
                }   break;
            case "Afternoon In":
                if((logTime.isBefore(AfternoonIn_logTime) || logTime.equals(AfternoonIn_logTime)) && logTime.isAfter(Morning_timeRange)){
                    open[0] = "Ai";//12am -> Ain
                    open[1] = String.valueOf(AfternoonIn_logTime) + " PM";
                }else if(logTime.isAfter(AfternoonIn_logTime)){
                    open[0] = "Ai";//Ain ->
                    open[1] = String.valueOf(AfternoonIn_logTime) + " PM";
                }else{
                    open[0] = "None";
                    open[1] = "00:00 AM";
                }   break;
            case "Morning In-Out":
                if(logTime.isBefore(MorningIn_logTime) || logTime.equals(MorningIn_logTime)){
                    open[0] = "Mi";// <- Min
                    open[1] = String.valueOf(MorningIn_logTime) + " AM";
                }else if(logTime.isAfter(MorningIn_logTime) && logTime.isBefore(MorningOut_logTime)){
                    open[0] = "Mi Mo";//Min -> Mout
                    open[1] = String.valueOf(MorningIn_logTime) + " AM";
                }else if(logTime.isAfter(MorningOut_logTime)){
                    open[0] = "Mo";//Mout->
                    open[1] = String.valueOf(MorningOut_logTime) + " AM";
                }else{
                    open[0] = "None";
                    open[1] = "00:00 AM";
                }   break;
            case "Afternoon In-Out":
                if(logTime.isAfter(Morning_timeRange) && logTime.isBefore(AfternoonIn_logTime)){
                    open[0] = "Ai";//12am -> Ain
                    open[1] = String.valueOf(AfternoonIn_logTime) + " PM";
                }else if((logTime.isAfter(AfternoonIn_logTime) || logTime.equals(AfternoonIn_logTime)) && logTime.isBefore(AfternoonOut_logTime)){
                    open[0] = "Ai Ao";//Ain -> Aout
                    open[1] = String.valueOf(AfternoonIn_logTime) + " PM";
                }else if(logTime.isAfter(AfternoonOut_logTime) || logTime.equals(AfternoonOut_logTime)){
                    open[0] = "Ao";//Aout -> 
                    open[1] = String.valueOf(AfternoonOut_logTime) + " PM";
                }else{
                    open[0] = "None";
                    open[1] = "00:00 AM";
                }   break;
            case "Morning In and Afternoon Out":
                if(logTime.isBefore(MorningIn_logTime) || logTime.equals(MorningIn_logTime)){
                    open[0] = "Mi";// <- Min
                    open[1] = String.valueOf(MorningIn_logTime) + " AM";
                }else if(logTime.isAfter(MorningIn_logTime) && (logTime.isBefore(Morning_timeRange) || logTime.equals(Morning_timeRange))){
                    open[0] = "Mi";//Min -> 12am
                    open[1] = String.valueOf(MorningIn_logTime) + " AM";
                }else if(logTime.isAfter(Morning_timeRange) && (logTime.isBefore(AfternoonOut_logTime) || logTime.equals(AfternoonOut_logTime))){
                    open[0] = "Ao";//12am -> Aout
                    open[1] = String.valueOf(AfternoonOut_logTime) + " PM";
                }else if(logTime.isAfter(AfternoonOut_logTime)){
                    open[0] = "Ao";//Aout -> 
                    open[1] = String.valueOf(AfternoonOut_logTime) + " PM";
                }else{
                    open[0] = "None";
                    open[1] = "00:00 AM";
                }   break;
            case "Morning In-Out and Afternoon In-Out":
                if(logTime.isBefore(MorningIn_logTime) || logTime.equals(MorningIn_logTime)){
                    open[0] = "Mi";// <- Min 
                    open[1] = String.valueOf(MorningIn_logTime) + " AM";
                }else if(logTime.isAfter(MorningIn_logTime) && logTime.isBefore(MorningOut_logTime)){
                    open[0] = "Mi Mo";//Min -> Mout
                    open[1] = String.valueOf(MorningIn_logTime) + " AM";
                }else if((logTime.isAfter(MorningOut_logTime) || logTime.equals(MorningOut_logTime)) && logTime.isBefore(Morning_timeRange)){
                    open[0] = "Mo Ai"; //Mout -> 12am
                    open[1] = String.valueOf(MorningOut_logTime) + " AM";
                }else if((logTime.isBefore(AfternoonIn_logTime) || logTime.equals(AfternoonIn_logTime)) && logTime.isAfter(Morning_timeRange)){
                    open[0] = "Mo Ai"; //12am -> Ain
                    open[1] = String.valueOf(AfternoonIn_logTime) + " PM";
                }else if(logTime.isAfter(MorningOut_logTime) && logTime.isBefore(AfternoonOut_logTime)){
                    open[0] = "Ai Ao";//Mout -> Aout
                    open[1] = String.valueOf(AfternoonIn_logTime) + " PM";
                }else if(logTime.isAfter(AfternoonOut_logTime) || logTime.equals(AfternoonOut_logTime)){
                    open[0] = "Ao";//Aout ->
                    open[1] = String.valueOf(AfternoonOut_logTime) + " PM";
                }else{
                    open[0] = "None";
                    open[1] = "00:00 AM";
                }   break;
            default:
                open[0] = "None";
                open[1] = "00:00 AM";
                break;
        }else{
            open[0] = "None";
            open[1] = "00:00 AM";
        }
        
        System.out.println("Open Logs: "+open[0]);
        
        return open;
    }
    
}
