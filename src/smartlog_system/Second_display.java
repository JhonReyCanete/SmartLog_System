
package smartlog_system;

import java.awt.Color;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public final class Second_display extends javax.swing.JFrame{

    Database_Queries DQ = new Database_Queries();
    private String DateNow;
    String[] Present;
    String[] Present_Official;
    String[] Present_Staff;
    DefaultTableModel dt_Official = new DefaultTableModel();
    DefaultTableModel dt_Staff = new DefaultTableModel();
    private String[] ID;
    
    Main_Frame MainF;
    
    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    //scheduledExecutorService.shutdown(); put this line of code to a method and call the method to stop running thread 
    
    private LocalTime MorningIn_logTime;
    private LocalTime MorningOut_logTime;
    private LocalTime AfternoonIn_logTime;
    private LocalTime AfternoonOut_logTime;
            
    
    public Second_display() throws ParseException, ClassNotFoundException, SQLException{
        initComponents();
        MainF = new Main_Frame();
        MainF.setVisible(true);
        scheduledExecutorService.scheduleAtFixedRate(ShowTime, 0L, 1L, TimeUnit.SECONDS);
    }
    
    public void start_process(){
        scheduledExecutorService.scheduleAtFixedRate(StartProtocol, 0L, 10L, TimeUnit.SECONDS);
    }
    
    //Scheduled threads
    Runnable ShowTime = () ->{ timeDisplay(); };
    Runnable StartProtocol = () -> {
        try { CurrentLog_Protocol();
        } catch (ClassNotFoundException | SQLException | ParseException ex) { Logger.getLogger(Second_display.class.getName()).log(Level.SEVERE, null, ex);}
    };
    
    public void timeDisplay(){
        Date d = new Date();
        SimpleDateFormat t = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat dte = new SimpleDateFormat("MMMM dd, yyyy");
        SimpleDateFormat DB_DF = new SimpleDateFormat("MMMM dd yyyy");
        SimpleDateFormat day = new SimpleDateFormat("EEEE");
        DateNow = DB_DF.format(d);
        Time_label.setText(t.format(d));
        Day_label.setText(day.format(d));
        Date_label.setText(dte.format(d));
    }
    
    //Finding the attendance Log that is set for use.
    public void CurrentLog_Protocol() throws ClassNotFoundException, SQLException, ParseException{
        getLogTime(); //Get current in and out Time logs
        String openLog = getCurrentUse_OpenLog();
        openLogs();
        Present(openLog);
    }
    
    public void Present(String LogType) throws ClassNotFoundException, SQLException{
        
        dt_Official = new DefaultTableModel(new String[]{"","",""}, 0);
        dt_Staff = new DefaultTableModel(new String[]{"","",""}, 0);
        
        String Log_inUSE = DQ.fetch("dailylogschedule", " Where Date='"+DateNow+"' and Status='IN-USE'", "schName");
        ID = DQ.arrayDataFetch("checkinout","checkinout", " Where Log_Name = '"+Log_inUSE+"' and Date = '"+DateNow+"' and Check_type = '"+LogType+"'", "IDnumber");
        
        String[] ID_Name_Official = new String[ID.length];
        String[] Pos_Official = new String[ID.length];
        
        String[] ID_Name_Staff = new String[ID.length];
        String[] Pos_Staff = new String[ID.length];
        
        String[] notOut = new String[ID.length];
        String Outname = "";
        if("Mi".equals(LogType)){Outname="Mo";}
        else if("Ai".equals(LogType)){Outname="Ao";}
        
        for(int i=0;i<ID.length;i++){
            boolean present = DQ.logCheck(ID[i], Outname, DateNow, Log_inUSE);
            if(present){
                notOut[i] = ID[i];
            }
        }
        
        for(int i=0;i<notOut.length;i++){
            String Name = DQ.fetch("SELECT concat(Lname,' ',Fname,' ',MI) as Fullname FROM smartlogdb.personnels where IDnumber = '"+notOut[i]+"'", "Fullname");
            String Position = DQ.fetch("SELECT * FROM smartlogdb.departments where IDnumber = '"+notOut[i]+"'", "Position");
            String Dept = DQ.fetch("SELECT * FROM smartlogdb.departments where IDnumber = '"+notOut[i]+"'", "Department");
            
            if("Barangay Official".equals(Dept)){
                Pos_Official[i] = Position;
                ID_Name_Official[i] = Name;
            }else{
                Pos_Staff[i] = Position;
                ID_Name_Staff[i] = Name;
            }
        }
        
        for(int i=0;i<ID_Name_Official.length;i++){
            if(ID_Name_Official[i] == null || ID_Name_Official[i].isBlank() || ID_Name_Official[i].isEmpty()){
            }else{
                dt_Official.addRow(new Object[]{Pos_Official[i],ID_Name_Official[i],"Present"});
            }
        }
        
        for(int i=0;i<ID_Name_Staff.length;i++){
            if(ID_Name_Staff[i] == null || ID_Name_Staff[i].isBlank() || ID_Name_Staff[i].isEmpty()){
            }else{
                dt_Staff.addRow(new Object[]{Pos_Staff[i],ID_Name_Staff[i],"Present"});
            }
        }
        
        Staff_table.setModel(dt_Staff);
        Officials_table.setModel(dt_Official);
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
    
    public void isActive(JLabel lbl){
        Border border = new LineBorder(new Color(4,153,19),2,true);
        lbl.setForeground(new Color(4,153,19));
        lbl.setBorder(border);
    }
    
    public void isNotActive(JLabel lbl){
        Border border = new LineBorder(new Color(204,204,204),2,true);
        lbl.setForeground(new Color(204,204,204));
        lbl.setBorder(border);
    }
    
    public void openLogs() throws ClassNotFoundException, SQLException{
        String Open[] = DQ.getCurrentUse_OpenLog();
        
        if(Open[0].equals("Mi")){
            isActive(morningIn_ind);
            isNotActive(morningOut_ind);
            isNotActive(afternoonIn_ind);
            isNotActive(afternoonOut_ind);
        }else if(Open[0].equals("Mi Mo")){
            isActive(morningIn_ind);
            isActive(morningOut_ind);
            isNotActive(afternoonIn_ind);
            isNotActive(afternoonOut_ind);
        }else if(Open[0].equals("Mo")){
            isNotActive(morningIn_ind);
            isActive(morningOut_ind);
            isNotActive(afternoonIn_ind);
            isNotActive(afternoonOut_ind);
        }else if(Open[0].equals("Mo Ai")){
            isNotActive(morningIn_ind);
            isActive(morningOut_ind);
            isActive(afternoonIn_ind);
            isNotActive(afternoonOut_ind);
        }else if(Open[0].equals("Ai")){
            isNotActive(morningIn_ind);
            isNotActive(morningOut_ind);
            isActive(afternoonIn_ind);
            isNotActive(afternoonOut_ind);
        }else if(Open[0].equals("Ai Ao")){
            isNotActive(morningIn_ind);
            isNotActive(morningOut_ind);
            isActive(afternoonIn_ind);
            isActive(afternoonOut_ind);
        }else if(Open[0].equals("Ao")){
            isNotActive(morningIn_ind);
            isNotActive(morningOut_ind);
            isNotActive(afternoonIn_ind);
            isActive(afternoonOut_ind);
        }else{
            isNotActive(morningIn_ind);
            isNotActive(morningOut_ind);
            isNotActive(afternoonIn_ind);
            isNotActive(afternoonOut_ind);
        }
    }
    
    public String getCurrentUse_OpenLog() throws ClassNotFoundException, SQLException{
        Date d = new Date();
        SimpleDateFormat day = new SimpleDateFormat("MMMM dd yyyy");
        SimpleDateFormat amPm = new SimpleDateFormat("a");
        SimpleDateFormat timeF = new SimpleDateFormat("HH:mm:ss");
        String CurrentDate = day.format(d);
        String AmPm = amPm.format(d);
        LocalTime Time = LocalTime.parse(timeF.format(d));
        LocalTime logTime = Time;
        getLogTime();
        
        String LogCycle = DQ.fetch("dailylogschedule", " Where Status = 'IN-USE' and Date = '"+CurrentDate+"'", "LogIO_cycle");
        String open = "";
        
        System.out.println("Morning in: "+ MorningIn_logTime);
        System.out.println("Morning out: "+ MorningOut_logTime);
        System.out.println("Afternoon in: "+ AfternoonIn_logTime);
        System.out.println("Afternoon out: "+ AfternoonOut_logTime);
        
        LocalTime Morning_timeRange = LocalTime.parse("12:00:00");
        
        if(null != LogCycle)switch (LogCycle) {
            case "Morning In":
                if(logTime.isBefore(MorningIn_logTime) || logTime.equals(MorningIn_logTime)){
                    open = "Mi"; // <- Min
                    
                }else if(logTime.isAfter(MorningIn_logTime) && (logTime.isBefore(Morning_timeRange) || logTime.equals(Morning_timeRange))){
                    open = "Mi"; // Min -> 12am
                }else{
                    open = "None";
                }   break;
            case "Afternoon In":
                if((logTime.isBefore(AfternoonIn_logTime) || logTime.equals(AfternoonIn_logTime)) && logTime.isAfter(Morning_timeRange)){
                    open = "Ai";//12am -> Ain
                }else if(logTime.isAfter(AfternoonIn_logTime)){
                    open = "Ai";//Ain ->
                }else{
                    open = "None";
                }   break;
            case "Morning In-Out":
                if(logTime.isBefore(MorningIn_logTime) || logTime.equals(MorningIn_logTime)){
                    open = "Mi";// <- Min
                }else if(logTime.isAfter(MorningIn_logTime) && logTime.isBefore(MorningOut_logTime)){
                    open = "Mi";//Min -> Mout ; Mo
                }else if(logTime.isAfter(MorningOut_logTime)){
                    open = "Mi";//Mout-> ; Mo
                }else{
                    open = "None";
                }   break;
            case "Afternoon In-Out":
                if(logTime.isAfter(Morning_timeRange) && logTime.isBefore(AfternoonIn_logTime)){
                    open = "Ai";//12am -> Ain
                }else if((logTime.isAfter(AfternoonIn_logTime) || logTime.equals(AfternoonIn_logTime)) && logTime.isBefore(AfternoonOut_logTime)){
                    open = "Ai";//Ain -> Aout ;Ao
                }else if(logTime.isAfter(AfternoonOut_logTime) || logTime.equals(AfternoonOut_logTime)){
                    open = "Ai";//Aout -> ;Ao
                }else{
                    open = "None";
                }   break;
            case "Morning In and Afternoon Out":
                if(logTime.isBefore(MorningIn_logTime) || logTime.equals(MorningIn_logTime)){
                    open = "Mi";// <- Min
                }else if(logTime.isAfter(MorningIn_logTime) && (logTime.isBefore(Morning_timeRange) || logTime.equals(Morning_timeRange))){
                    open = "Mi";//Min -> 12am
                }else if(logTime.isAfter(Morning_timeRange) && (logTime.isBefore(AfternoonOut_logTime) || logTime.equals(AfternoonOut_logTime))){
                    open = "Ai";//12am -> Aout ;Ao
                }else if(logTime.isAfter(AfternoonOut_logTime)){
                    open = "Ai";//Aout -> ;Ao
                }else{
                    open = "None";
                }   break;
            case "Morning In-Out and Afternoon In-Out":
                if(logTime.isBefore(MorningIn_logTime) || logTime.equals(MorningIn_logTime)){
                    open = "Mi";// <- Min 
                }else if(logTime.isAfter(MorningIn_logTime) && logTime.isBefore(MorningOut_logTime)){
                    open = "Mi";//Min -> Mout ; Mo
                }else if((logTime.isAfter(MorningOut_logTime) || logTime.equals(MorningOut_logTime)) && logTime.isBefore(Morning_timeRange)){
                    open = "Ai"; //Mout -> 12am ;Mo
                }else if((logTime.isBefore(AfternoonIn_logTime) || logTime.equals(AfternoonIn_logTime)) && logTime.isAfter(Morning_timeRange)){
                    open = "Ai"; //12am -> Ain
                }else if(logTime.isAfter(MorningOut_logTime) && logTime.isBefore(AfternoonOut_logTime)){
                    open = "Ai";//Mout -> Aout ;Ao
                }else if(logTime.isAfter(AfternoonOut_logTime) || logTime.equals(AfternoonOut_logTime)){
                    open = "Ai";//Aout -> ; Ao
                }else{
                    open = "None";
                }   break;
            default:
                break;
        }
        
        return open;
    }
    
    public int[] timeDifference(String Time) throws ParseException{
        int dif[] = new int[3];
        Date d = new Date();
        SimpleDateFormat day = new SimpleDateFormat("HH:mm:ss");
        String CurrentTime = day.format(d);
        String time2 = Time;
        
        long difference = Duration.between(LocalTime.parse(CurrentTime), LocalTime.parse(time2)).toHours();
        long differencem = Duration.between(LocalTime.parse(CurrentTime), LocalTime.parse(time2)).toMinutes() % 60;
        
        dif[0] = (int) difference; //Hours
        dif[1] = (int) differencem; //minutes
        
        return dif;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Date_label = new javax.swing.JLabel();
        Time_label = new javax.swing.JLabel();
        Day_label = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Officials_table = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Staff_table = new javax.swing.JTable();
        notif_container = new javax.swing.JPanel();
        morningIn_ind = new javax.swing.JLabel();
        morningOut_ind = new javax.swing.JLabel();
        afternoonIn_ind = new javax.swing.JLabel();
        afternoonOut_ind = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("second_display_Frame"); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Date_label.setBackground(new java.awt.Color(255, 255, 255));
        Date_label.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        Date_label.setForeground(new java.awt.Color(4, 8, 114));
        Date_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Date_label.setText("November 8, 2021");

        Time_label.setBackground(new java.awt.Color(255, 255, 255));
        Time_label.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        Time_label.setForeground(new java.awt.Color(4, 8, 114));
        Time_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Time_label.setText("12:59 PM");

        Day_label.setBackground(new java.awt.Color(255, 255, 255));
        Day_label.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        Day_label.setForeground(new java.awt.Color(4, 8, 114));
        Day_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Day_label.setText("Thursday");

        jPanel7.setLayout(new java.awt.BorderLayout());

        Officials_table.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Officials_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        Officials_table.setInheritsPopupMenu(true);
        Officials_table.setRowHeight(30);
        jScrollPane2.setViewportView(Officials_table);

        jPanel7.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.BorderLayout());

        Staff_table.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Staff_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        Staff_table.setRowHeight(30);
        jScrollPane1.setViewportView(Staff_table);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        notif_container.setBackground(new java.awt.Color(255, 255, 255));
        notif_container.setOpaque(false);

        morningIn_ind.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        morningIn_ind.setForeground(new java.awt.Color(204, 204, 204));
        morningIn_ind.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        morningIn_ind.setText("MORNING IN");
        morningIn_ind.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));

        morningOut_ind.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        morningOut_ind.setForeground(new java.awt.Color(204, 204, 204));
        morningOut_ind.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        morningOut_ind.setText("MORNING OUT");
        morningOut_ind.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));

        afternoonIn_ind.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        afternoonIn_ind.setForeground(new java.awt.Color(204, 204, 204));
        afternoonIn_ind.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        afternoonIn_ind.setText("AFTERNOON IN");
        afternoonIn_ind.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));

        afternoonOut_ind.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        afternoonOut_ind.setForeground(new java.awt.Color(204, 204, 204));
        afternoonOut_ind.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        afternoonOut_ind.setText("AFTERNOON OUT");
        afternoonOut_ind.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));

        javax.swing.GroupLayout notif_containerLayout = new javax.swing.GroupLayout(notif_container);
        notif_container.setLayout(notif_containerLayout);
        notif_containerLayout.setHorizontalGroup(
            notif_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notif_containerLayout.createSequentialGroup()
                .addComponent(morningIn_ind, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(morningOut_ind, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(afternoonIn_ind, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(afternoonOut_ind, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addContainerGap())
        );
        notif_containerLayout.setVerticalGroup(
            notif_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notif_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(morningOut_ind, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                .addComponent(afternoonIn_ind, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                .addComponent(afternoonOut_ind, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
            .addComponent(morningIn_ind, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 153, 19));
        jLabel1.setLabelFor(Officials_table);
        jLabel1.setText("BARANGGAY OFFICIALS");

        jLabel4.setBackground(new java.awt.Color(4, 153, 19));
        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(4, 153, 19));
        jLabel4.setLabelFor(Staff_table);
        jLabel4.setText("ADMINISTRATIVE STAFF");
        jLabel4.setToolTipText("");

        logo.setBackground(new java.awt.Color(255, 255, 255));
        logo.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/LOGO.png"))); // NOI18N
        logo.setLabelFor(Officials_table);
        logo.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(notif_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(Date_label, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Day_label, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Time_label, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Day_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Time_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Date_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(notif_container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Date_label;
    private javax.swing.JLabel Day_label;
    private javax.swing.JTable Officials_table;
    private javax.swing.JTable Staff_table;
    private javax.swing.JLabel Time_label;
    private javax.swing.JLabel afternoonIn_ind;
    private javax.swing.JLabel afternoonOut_ind;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel morningIn_ind;
    private javax.swing.JLabel morningOut_ind;
    private javax.swing.JPanel notif_container;
    // End of variables declaration//GEN-END:variables
}
