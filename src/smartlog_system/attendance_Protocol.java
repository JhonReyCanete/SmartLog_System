
package smartlog_system;

import bluetooth.OBEXPutServer;
import java.io.IOException;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.BluetoothStateException;
import javax.swing.JOptionPane;

public class attendance_Protocol{
    
    OBEXPutServer StartServer = new OBEXPutServer();
    Database_Queries DQ = new Database_Queries();
    ExecutorService executor = Executors.newCachedThreadPool();
    private LocalTime MorningIn_logTime;
    private LocalTime MorningOut_logTime;
    private LocalTime AfternoonIn_logTime;
    private LocalTime AfternoonOut_logTime;
    private String Grant_logname;

    public void attendanceProtocol() throws ClassNotFoundException, SQLException, IOException{
        
        Runnable r = () -> {
            try {
                System.out.println("Starting BT Server");
                StartServer.startServer();
            }catch (BluetoothStateException ex) {
                Logger.getLogger(attendance_Protocol.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(attendance_Protocol.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(attendance_Protocol.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(attendance_Protocol.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        executor.submit(r);
    }
    
    public String duplicate_Log(String ID, String Password, String CheckType) throws ClassNotFoundException, SQLException{
        String duplicated = null;
        Date date = new Date();
        SimpleDateFormat C_date = new SimpleDateFormat("MMMM dd yyyy");
        String currentDate = C_date.format(date);
        
        String CurrentLog = DQ.fetch("dailylogschedule", " Where Status = 'IN-USE'", "schName");
        System.out.println("Current Log: " + CurrentLog);
        
        String Duplicate = DQ.fetch("checkinout", " Where Log_Name = '"+CurrentLog+"' and Date = '"+currentDate+"' "
                                  + "and Check_type = '"+CheckType+"' and IDnumber = '"+ID+"'", "LogTime");
        if(Duplicate == null || "".equals(Duplicate)){}
        else{
            duplicated = Duplicate;
        }
        return duplicated;
    }
    
    public int[] difference(LocalTime logTime, LocalTime setTime){
        int dif[] = new int[3];
        
        long difference = Duration.between(logTime,setTime).toHours();
        long differencem = Duration.between(logTime,setTime).toMinutes() % 60;
        
        dif[0] = (int) difference; //Hours
        dif[1] = (int) differencem; //minutes
        
        return dif;
    }
    
    public boolean logType_available(LocalTime Time, String AmPm, String logType) throws ClassNotFoundException, SQLException{
        Date d = new Date();
        SimpleDateFormat day = new SimpleDateFormat("MMMM dd yyyy");
        String CurrentDate = day.format(d);
        LocalTime logTime = Time;
        getLogTime();
        
        System.out.println("Logged Time unChanged: "+logTime);
        
        LocalTime Morning_timeRange = LocalTime.parse("12:00:00");
        LocalTime timeRange = LocalTime.parse("12:59:00");
        LocalTime timeRange2 = LocalTime.parse("01:00:00");

        if(("PM".equalsIgnoreCase(AmPm) && Time.isAfter(Morning_timeRange) ) && (Time.isBefore(timeRange)||Time.equals(timeRange))){
            
        }else if("PM".equalsIgnoreCase(AmPm) && (Time.isAfter(timeRange2)||Time.equals(timeRange2))){
            logTime = Time.plusHours(12);
        }else{}
        
        System.out.println("Logged Time: "+logTime);
        System.out.println("Revised Current Time: "+logTime);
        
        String LogCycle = DQ.fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "LogIO_cycle");
        boolean grant = false;  
        String open = "";
        
        if(null != LogCycle)switch (LogCycle) {
            case "Morning In":
                if((logTime.isBefore(MorningIn_logTime) || logTime.equals(MorningIn_logTime)) && logTime.isAfter(Morning_timeRange)){
                    open = " Mi"; // <- Min
                }else if(logTime.isAfter(MorningIn_logTime) && (logTime.isBefore(Morning_timeRange) || logTime.equals(Morning_timeRange))){
                    open = " Mi"; // Min -> 12am
                }else{
                    open = "None";
                }   break;
            case "Afternoon In":
                if((logTime.isBefore(AfternoonIn_logTime) || logTime.equals(AfternoonIn_logTime)) && logTime.isAfter(Morning_timeRange)){
                    open = " Ai";//12am -> Ain
                }else if(logTime.isAfter(AfternoonIn_logTime)){
                    open = " Ai";//Ain ->
                }else{
                    open = "None";
                }   break;
            case "Morning In-Out":
                if(logTime.isBefore(MorningIn_logTime) || logTime.equals(MorningIn_logTime)){
                    open = "Mi";// <- Min
                }else if(logTime.isAfter(MorningIn_logTime) && logTime.isBefore(MorningOut_logTime)){
                    open = "Mi Mo";//Min -> Mout
                }else if(logTime.isAfter(MorningOut_logTime)){
                    open = "Mo";//Mout->
                }else{
                    open = "None";
                }   break;
            case "Afternoon In-Out":
                if(logTime.isAfter(Morning_timeRange) && logTime.isBefore(AfternoonIn_logTime)){
                    open = open + " Ai";//12am -> Ain
                }else if((logTime.isAfter(AfternoonIn_logTime) || logTime.equals(AfternoonIn_logTime)) && logTime.isBefore(AfternoonOut_logTime)){
                    open = open + " Ai Ao";//Ain -> Aout
                }else if(logTime.isAfter(AfternoonOut_logTime) || logTime.equals(AfternoonOut_logTime)){
                    open = open + " Ao";//Aout ->
                }else{
                    open = "None";
                }   break;
            case "Morning In and Afternoon Out":
                if(logTime.isBefore(MorningIn_logTime) || logTime.equals(MorningIn_logTime)){
                    open = open + " Mi";// <- Min
                }else if(logTime.isAfter(MorningIn_logTime) && (logTime.isBefore(Morning_timeRange) || logTime.equals(Morning_timeRange))){
                    open = open + " Mi";//Min -> 12am
                }else if(logTime.isAfter(Morning_timeRange) && (logTime.isBefore(AfternoonOut_logTime) || logTime.equals(AfternoonOut_logTime))){
                    open = open + " Ao";//12am -> Aout
                }else if(logTime.isAfter(AfternoonOut_logTime)){
                    open = open + " Ao";//Aout -> 
                }else{
                    open = "None";
                }   break;
            case "Morning In-Out and Afternoon In-Out":
                if(logTime.isBefore(MorningIn_logTime) || logTime.equals(MorningIn_logTime)){
                    open = open + " Mi";// <- Min
                }else if(logTime.isAfter(MorningIn_logTime) && logTime.isBefore(MorningOut_logTime)){
                    open = open + " Mi Mo";//Min -> Mout
                }else if((logTime.isAfter(MorningOut_logTime) || logTime.equals(MorningOut_logTime)) && logTime.isBefore(Morning_timeRange)){
                    open = open + " Mo Ai"; //Mout -> 12am
                }else if((logTime.isBefore(AfternoonIn_logTime) || logTime.equals(AfternoonIn_logTime)) && logTime.isAfter(Morning_timeRange)){
                    open = open + " Mo Ai"; //12am -> Ain
                }else if(logTime.isAfter(MorningOut_logTime) && logTime.isBefore(AfternoonOut_logTime)){
                    open = open + " Ai Ao";//Mout -> Aout
                }else if(logTime.isAfter(AfternoonOut_logTime) || logTime.equals(AfternoonOut_logTime)){
                    open = open + " Ao";//Aout ->
                }else{
                    open = "None";
                }   break;
            default:
                break;
        }
        
        String cut[] = open.split("\\s");
        for (String cut1 : cut) {
            if (logType.equals(cut1)) {
                grant = true;
                System.out.println("LOG GRANTED");
                Grant_logname = logType;
                break;
            }
        }
        
        for (String cut1 : cut) {
            System.out.println("Open Log is: " + cut1);
        }
        
        return grant;
    }
    
    public void getLogTime() throws ClassNotFoundException, SQLException{
        Date d = new Date();
        SimpleDateFormat day = new SimpleDateFormat("MMMM dd yyyy");
        String CurrentDate = day.format(d);
        String LogCycle = DQ.fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "LogIO_cycle");
        
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
                    String Time = DQ.fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "StartTime_1");
                    LocalTime time = LocalTime.parse(Time);
                    MorningIn_logTime = time;
                    MorningOut_logTime = default_time;
                    AfternoonIn_logTime = default_time;
                    AfternoonOut_logTime = default_time;
                    break;
                }
            case "Afternoon In":
                {
                    String Time = DQ.fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "StartTime_2");
                    LocalTime time = LocalTime.parse(Time);
                    MorningIn_logTime = default_time;
                    MorningOut_logTime = default_time;
                    AfternoonIn_logTime = time.plusHours(12);
                    AfternoonOut_logTime = default_time;
                    break;
                }
            case "Morning In-Out":
                {
                    String Time = DQ.fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "StartTime_1");
                    String Time2 = DQ.fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "EndTime_1");
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
                    String Time = DQ.fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "StartTime_2");
                    String Time2 = DQ.fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "EndTime_2");
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
                    String Time = DQ.fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "StartTime_1");
                    String Time2 = DQ.fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "EndTime_2");
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
                    String Time = DQ.fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "StartTime_1");
                    String Time2 = DQ.fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "EndTime_1");
                    String Time3 = DQ.fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "StartTime_2");
                    String Time4 = DQ.fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "EndTime_2");
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
    
    public boolean attendanceLogging_Protocol(String ID, String Password, String CheckType) throws ClassNotFoundException, SQLException{
        boolean Success;
        getLogTime(); // get Time 
        Date date = new Date();
        SimpleDateFormat C_time = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat C_timeAlone = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat C_date = new SimpleDateFormat("MMMM dd yyyy");
        
        String currentTime = C_time.format(date);
        String currentDate = C_date.format(date);
        String currentTimeAlone = C_timeAlone.format(date);
        
        String ampmcut[] = String.valueOf(currentTime).split("\\s");
        
        LocalTime current_Time = LocalTime.parse(currentTimeAlone);
        System.out.println("Current time: "+currentTime);
        System.out.println("AMPM: "+ampmcut[1]);
        
        String CurrentLog = DQ.fetch("dailylogschedule", " Where Status = 'IN-USE'", "schName");
        System.out.println("Current Log: " + CurrentLog);
        
        String accountMatchPassword = DQ.fetch("personnels", " Where IDnumber = '"+ID+"'", "Password");
        System.out.println("Password Submitted: " + Password +"\nReal Password: "+accountMatchPassword); 
        if(accountMatchPassword == null){accountMatchPassword = "l";}
        if(accountMatchPassword.equals(Password)){
            boolean granted = logType_available(current_Time,ampmcut[1],CheckType);
            System.out.println("Time Now: "+current_Time);
            
            /****/
            
            /*LocalTime TimeNow = current_Time;
            LocalTime TimeThen = current_Time;
            LocalTime Morning_timeRange = LocalTime.parse("12:00:00");
            LocalTime timeRange = LocalTime.parse("12:59:00");
            LocalTime timeRange2 = LocalTime.parse("01:00:00");

            if(("PM".equalsIgnoreCase(ampmcut[1]) && TimeNow.isAfter(Morning_timeRange) ) && (TimeNow.isBefore(timeRange)||TimeNow.equals(timeRange))){

            }else if("PM".equalsIgnoreCase(ampmcut[1]) && (TimeNow.isAfter(timeRange2)||TimeNow.equals(timeRange2))){
                TimeThen = TimeNow.plusHours(12);
            }else{}
            
            System.out.println("Time Then: "+TimeThen);
            /****/
            if(granted){
                
                String fetchTimeLog;
        
                if("Mi".equals(Grant_logname)){ fetchTimeLog = "StartTime_1";}
                else if("Mo".equals(Grant_logname)){fetchTimeLog = "EndTime_1";}
                else if("Ai".equals(Grant_logname)){fetchTimeLog = "StartTime_2";}
                else if("Ao".equals(Grant_logname)){fetchTimeLog = "EndTime_2";}
                else{fetchTimeLog = "";}
                System.out.println("Granted: "+Grant_logname);
                
                String getSetTime = DQ.fetch("dailylogschedule", " Where schName = '"+CurrentLog+"' and Status = 'IN-USE' and Date = '"+currentDate+"'", fetchTimeLog);
                String LateTime;
                String EarlyTime;
                System.out.println("Set Time: "+getSetTime);
                int[] diff = difference(current_Time, LocalTime.parse(getSetTime));
                String tHDiff,tMDiff;
                
                if(String.valueOf(Math.abs(diff[0])).length() == 1){
                    tHDiff = "0"+String.valueOf(Math.abs(diff[0]));
                }else{
                    tHDiff = String.valueOf(Math.abs(diff[0]));
                }
                
                if(String.valueOf(Math.abs(diff[1])).length() == 1){
                    tMDiff = "0"+String.valueOf(Math.abs(diff[1]));
                }else{
                    tMDiff = String.valueOf(Math.abs(diff[1]));
                }
                
                
                if(diff[0] <= -1){
                    LateTime = tHDiff;
                }else{
                    LateTime = "0";
                }
                
                if(diff[1] <= -1){
                    LateTime = LateTime+":"+tMDiff;
                }else{
                    LateTime = "0:00";
                }
                
                System.out.println("Late: "+LateTime);
                
                if(diff[0] > 0){
                    EarlyTime = tHDiff;
                }else{
                    EarlyTime = "0";
                }
                
                if(diff[1] > 0){
                    EarlyTime = EarlyTime+":"+tMDiff;
                }else{
                    EarlyTime = "0:00";
                }
                
                System.out.println("Early: "+EarlyTime);
                /*
                if(diff[0] <= -1 && diff[1] <= -1){
                    LateTime = String.valueOf(Math.abs(diff[0]))+":"+String.valueOf(Math.abs(diff[1]));
                    System.out.println("Late: "+LateTime);
                    EarlyTime = "";
                }else if(diff[0] <= -1 && diff[1] > 0){
                    LateTime = String.valueOf("0:"+String.valueOf(Math.abs(diff[1])));
                    System.out.println("Late: "+LateTime);
                    EarlyTime = "";
                }else if(diff[0] > 0 && diff[1] <= 0){
                    LateTime = String.valueOf((Math.abs(diff[0]))+":00");
                    System.out.println("Late: "+LateTime);
                    EarlyTime = "";
                }else if(diff[0] > 0 && diff[1] > 0){
                    LateTime = "";
                    EarlyTime = String.valueOf(diff[0])+":"+String.valueOf(diff[1]);
                    System.out.println("Early: "+EarlyTime);
                }else{
                    LateTime = "";
                    EarlyTime = "";
                    System.out.println("Early: "+EarlyTime);
                    System.out.println("Late: "+LateTime);
                }*/
                
                DQ.CRUDE("Insert into checkinout(Log_Name,IDnumber,Check_type,Date,LogTime,EarlyTime,LateTime) "
                       + "values('"+CurrentLog+"','"+ID+"','"+CheckType+"','"+currentDate+"','"+currentTime+"','"+EarlyTime+"','"+LateTime+"')");
                System.out.println("Log Successful");
                Success = true;
            }else{
                Success = false;
                System.out.println("Log: "+CheckType+" is not open.");
            }
        }else{
            System.out.println("Wrong Password or User ID"); 
            Success = false;
        }
        
        return Success;
    }
    
    public boolean infoBox(String Info, String titleBar, int Buttons){
        boolean answer;
        int dialogResult = JOptionPane.showConfirmDialog(null,Info,titleBar, Buttons);
        answer = dialogResult == 1;
        return answer;
    }
}
