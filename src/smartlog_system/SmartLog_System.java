package smartlog_system;

import bluetooth.OBEXPutServer;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.LocalDevice;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

public class SmartLog_System {
    
    static Database_Queries DQ = new Database_Queries();
    static attendance_Protocol AP = new attendance_Protocol();
    static OBEXPutServer ops = new OBEXPutServer();
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, IOException, BluetoothStateException, InterruptedException{
        
        try{
            //display local device address and name
            LocalDevice localDevice = LocalDevice.getLocalDevice();
            System.out.println("Address: "+localDevice.getBluetoothAddress());
            System.out.println("Name: "+localDevice.getFriendlyName());

            //ops.startServer();//Start Server for mobile BT communication testing
            //populate(); //populate the log_in programmaticaly
            //populateMonthwithSched(); // populate schedule

            //Original start sequence of the system
                AP.attendanceProtocol();

                clearLog_History();
                setAttendanceStatusToDefault();
                updateLeaveStatus();

                boolean FStart = isfreshStart();
                if(FStart){
                    LogIn_Frame LF = new LogIn_Frame();
                    LF.setVisible(true);
                }else{
                    Fresh_Start FS = new Fresh_Start();
                    FS.setVisible(true);
                }
        }catch(BluetoothStateException e){
            infoBox("Turn on Bluetooth and start program."
                        ,"ERROR",WARNING_MESSAGE);
        }
    
    }
    
    public static boolean infoBox(String Info, String titleBar, int Buttons){
        boolean answer = false;
        int dialogResult = JOptionPane.showConfirmDialog(null,Info,titleBar, Buttons);
        if(dialogResult == 0){
            answer = true;
        }else{
            answer = false;
        }
        return answer;
    }
    
    public static void populateMonthwithSched() throws ClassNotFoundException, SQLException{
        for(int x=1;x<31;x++){
            String dd = String.valueOf(x);
            if(dd.length()<2){dd = "0"+dd;}
            String date = "April "+dd+" 2022";
            System.out.println("Inserting Schedule: "+date);
            String cmd ="INSERT INTO `smartlogdb`.`dailylogschedule` (`schName`, `Date`, `StartTime_1`, `EndTime_1`, `StartTime_2`, `EndTime_2`, `LogIO_cycle`, `Work_status`, `Day_Type`, `Status`) "
                    + "                                       VALUES ('"+date+"', '"+date+"', '07:00:00', '11:00:00', '01:00:00', '05:00:00', 'Morning In-Out and Afternoon In-Out', 'Working Day', 'REGULAR DAY', 'DONE');" ;
                    DQ.CRUDE(cmd);
            }
    }
    
    public static void populate() throws ClassNotFoundException, SQLException{
        String data[] = DQ.arrayFetch("smartlogdb.dailylogschedule", "SELECT * FROM smartlogdb.dailylogschedule", "schName");
        String dataID[] = DQ.arrayFetch("smartlogdb.personnels", "SELECT * FROM smartlogdb.personnels", "IDnumber");
        for(int i=0;i<data.length;i++){
            System.out.println("Inserting to: "+data[i]);
            for(int x=0;x<dataID.length;x++){
            String cmd = "INSERT INTO `smartlogdb`.`checkinout` (`Log_Name`, `IDnumber`, `Check_type`, `Date`, `LogTime`, `EarlyTime`)"
                                                     + " VALUES ('"+data[i]+"', '"+dataID[x]+"', 'Ao', '"+data[i]+"', '05:00 PM', '00:00')";
            DQ.CRUDE(cmd);
            }
        }
    }
    
    public static boolean isfreshStart() throws ClassNotFoundException, SQLException{
        String hasAdmin = DQ.fetch("personnels", "where Preveledge = 'Level 3'", "IDnumber");
        System.out.println(hasAdmin);
        if(hasAdmin == null){return false;}
        else{return true;}
    }
    
    public static void clearLog_History(){
        try {
            DQ.CRUDE("update smartlogdb.personnels set Status = null where IDnumber > '0'");
        } catch (ClassNotFoundException | SQLException ex) {}
        System.out.println("All User status updated to default.");
    }
    
    public static void updateLeaveStatus() throws ClassNotFoundException, SQLException, ParseException{
        String LeaveID[]  = DQ.arrayDataFetch("leaveclass", "leaveclass", "", "LeaveID");
        for(int x=0;x<LeaveID.length;x++){
            String endDate = DQ.fetch("leaveclass", " Where LeaveID = '"+LeaveID[x]+"'", "EndDate");
            String startDate = DQ.fetch("leaveclass", " Where LeaveID = '"+LeaveID[x]+"'", "StartDate");
            SimpleDateFormat sdformat = new SimpleDateFormat("MMMM dd yyyy");
            LocalDate d = LocalDate.now();
            String c = d.format(DateTimeFormatter.ofPattern("MMMM dd yyyy"));
            Date dateN = sdformat.parse(c);
            Date dateE = sdformat.parse(endDate);
            Date dateS = sdformat.parse(startDate);
            
            if(dateN.before(dateS) && dateN.before(dateE)){ DQ.CRUDE("update smartlogdb.leaveclass set State = 'SCHEDULED' where LeaveID = '"+LeaveID[x]+"'"); }
            else if(dateN.after(dateS) && dateN.before(dateE)){ DQ.CRUDE("update smartlogdb.leaveclass set State = 'ONGOING' where LeaveID = '"+LeaveID[x]+"'");}
            else if(dateN.after(dateS) && dateN.after(dateE)){ DQ.CRUDE("update smartlogdb.leaveclass set State = 'DONE' where LeaveID = '"+LeaveID[x]+"'");}
        }
    }
    
    public static void setAttendanceStatusToDefault(){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy");
            formatter = formatter.withLocale(Locale.getDefault());
            LocalDate nowDate = LocalDate.now();
            String DateNow = String.valueOf(nowDate.format(formatter));
            LocalDate currentDateTime = LocalDate.parse(DateNow,formatter);
            
            //String date = DQ.fetch("dailylogschedule", " Where Status = 'IN-USE'", "Date");
            String schID[] = DQ.arrayFetch("dailylogschedule", "SELECT * FROM smartlogdb.dailylogschedule", "sch_ID");
            
            for(int y=0;y<schID.length;y++){
                String DatesPerID = DQ.fetch("dailylogschedule", " WHERE sch_ID='"+schID[y]+"'", "Date");
                LocalDate schDate = LocalDate.parse(DatesPerID,formatter);
                
                if(currentDateTime.isBefore(schDate) || currentDateTime.equals(schDate)){
                    DQ.CRUDE("update dailylogschedule set Status = 'SCHEDULED' where sch_ID = '"+schID[y]+"'");
                }else if(currentDateTime.isAfter(schDate)){
                    DQ.CRUDE("update dailylogschedule set Status = 'DONE' where sch_ID = '"+schID[y]+"'");
                }else{
                    System.out.println("Attendance state error");
                }

            }
            } catch (ClassNotFoundException | SQLException ex) {}
            System.out.println("Attendance has been set to default state. \nNo attendance has been set.");
    }
}
