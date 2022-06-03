
package smartlog_system;

import Graph.PanelDate;
import Graph.PanelSlide;
import Graph.Afternoon_in_Graph;
import Graph.Afternoon_out_Graph;
import Graph.Morning_in_Graph;
import Graph.Morning_out_Graph;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.BluetoothStateException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.CLOSED_OPTION;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public final class Main_Frame extends javax.swing.JFrame {

    private int NumberOfPersonnel;
    private int NumberOfOfficials;
    attendance_Protocol Att = new attendance_Protocol();
    private String lastday;
    private int lastdayOftheMonth;
    private int month;
    private int year;
    private int weekNumber;
    private String DepartmentHP;
    LogIn_Frame LF = new LogIn_Frame();
    Database_Queries DQ = new Database_Queries();
    Bluetooth_action bluetoothQueries = new Bluetooth_action();
    String[][] BT_Devices_Discovered;
    Afternoon_in_Graph Ain_G = new Afternoon_in_Graph();
    Afternoon_out_Graph Aout_G = new Afternoon_out_Graph();
    Morning_in_Graph Min_G = new Morning_in_Graph();
    Morning_out_Graph Mout_G = new Morning_out_Graph();
    
    private String[] BT_name;
    private String[] BT_add;
    public String CurrentUser = null;
    public String CurrentUser_IDnum = null;
    public boolean allAction_Confirmation = false;
    String selectedLeave = null;
    String selectedDept = null;
    String LogCycle_type = null; // Mi,Mio,Ai,Aio,MiAo,MioAio
    DefaultComboBoxModel model = new DefaultComboBoxModel();
    DefaultComboBoxModel LogDatemodel;
    DefaultComboBoxModel Logsmodel = new DefaultComboBoxModel();
    DefaultComboBoxModel Deptmodel = new DefaultComboBoxModel();
    DefaultComboBoxModel DP_model = new DefaultComboBoxModel();
    DefaultComboBoxModel DeptPositionmodel = new DefaultComboBoxModel();
    DefaultTableModel DPTdtm = new DefaultTableModel();
    //Reports Global Variable in use
    DefaultTableModel ReportsDTM = new DefaultTableModel();
    String[] IDnum;
    String[] LogTime_mi;
    String[] LogTime_mo;
    String[] LogTime_ai;
    String[] LogTime_ao;
    String[] LogTime_oi;
    String[] LogTime_oo;
    String Datenow;
    
    private int UnderTime[] = new int[2];
    private String RDsearchFrom[] = new String[3];
    
    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    ExecutorService executor = Executors.newCachedThreadPool();
    
    
    public Main_Frame() throws ClassNotFoundException, SQLException {
        initComponents();
        
        getCurrentUser();
        ShowTime();
        
        currentLogDetails_display();
        
        currentLogType_label.setText("NOT SET");
        TimeInOut_label.setText("00:00");
        currentAttendance_label.setText("NO ATTENDANCE SET");
        //get current month and year for calendar predisplay
        Date d = new Date();
        SimpleDateFormat dateAlone = new SimpleDateFormat("MM");
        SimpleDateFormat yy = new SimpleDateFormat("yyyy");
        int currentMonth = Integer.valueOf(dateAlone.format(d));
        int currentYear = Integer.valueOf(yy.format(d));
        
        
        thisMonth();
        //set calendar predisplay
        slide.show(new PanelDate(currentMonth, currentYear), PanelSlide.AnimateType.TO_RIGHT);
        showMonthYear();
    }
    
    //second screen display
    public static void showOnScreen(int screen, JFrame frame){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        if(screen > -1 && screen < gs.length){
            gs[screen].setFullScreenWindow(frame);
        }
        else if(gs.length>0){
            gs[0].setFullScreenWindow(frame);
        }else{
            throw new RuntimeException("No screen found");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Date_label = new javax.swing.JLabel();
        Day_label = new javax.swing.JLabel();
        Time_label = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        User_name = new javax.swing.JLabel();
        User_Position = new javax.swing.JLabel();
        Dashboard_btn = new javax.swing.JButton();
        Attendance_btn = new javax.swing.JButton();
        Reports_btn = new javax.swing.JButton();
        Personnels_btn = new javax.swing.JButton();
        AboutUs_btn = new javax.swing.JButton();
        CARD_LAYOUT = new javax.swing.JPanel();
        Dashboard_pane = new javax.swing.JPanel();
        M_in = new javax.swing.JPanel();
        morning_in_Graph1 = new Graph.Morning_in_Graph();
        M_out = new javax.swing.JPanel();
        morning_out_Graph1 = new Graph.Morning_out_Graph();
        A_in = new javax.swing.JPanel();
        afternoon_in_Graph1 = new Graph.Afternoon_in_Graph();
        A_out = new javax.swing.JPanel();
        afternoon_out_Graph1 = new Graph.Afternoon_out_Graph();
        Calendar_Panel = new javax.swing.JPanel();
        cmdNext = new javax.swing.JButton();
        lbMonthYear = new javax.swing.JLabel();
        cmdBack = new javax.swing.JButton();
        slide = new Graph.PanelSlide();
        currentLogType_label = new javax.swing.JLabel();
        TimeInOut_label = new javax.swing.JLabel();
        currentAttendance_label = new javax.swing.JLabel();
        Min_label = new javax.swing.JLabel();
        currentAttendanceCycle_label = new javax.swing.JLabel();
        Aout_label = new javax.swing.JLabel();
        Ain_label = new javax.swing.JLabel();
        Mout_label = new javax.swing.JLabel();
        NumberOfOfficials_label = new javax.swing.JLabel();
        NumberOfStaff_label = new javax.swing.JLabel();
        NumberOfPersonnels_bg = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        NumberOfOfficials_bg = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        NumberOfStaff_bg = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Attendance_pane = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        LOG_panel = new javax.swing.JPanel();
        LogName_in = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        LogDate_in = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        LogDayType_in = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        LogWorkStatus_in = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        LogEntry_in = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        LogStatus_in = new javax.swing.JTextField();
        LogDelete_btn = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        Logs_table = new javax.swing.JTable();
        jLabel54 = new javax.swing.JLabel();
        LogID_in = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        UseLog_checkbox = new javax.swing.JCheckBox();
        useLogSave_btn = new javax.swing.JButton();
        DEPARTMENTS_panel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        Departments_table = new javax.swing.JTable();
        dptPositions_btn = new javax.swing.JButton();
        dptHead_btn = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        dptPositions_panel = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        PositionID_in = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        DepartmentPositionName_in = new javax.swing.JTextField();
        DepartmentPositionDelete_btn = new javax.swing.JButton();
        DepartmentPositionUpdate_btn = new javax.swing.JButton();
        DepartmentAddPosition_btn = new javax.swing.JButton();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        PositionDepartmentName_in = new javax.swing.JComboBox<>();
        label7 = new java.awt.Label();
        label8 = new java.awt.Label();
        label9 = new java.awt.Label();
        dptHead_panel = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        DepartmentID_in = new javax.swing.JTextField();
        DepartmentName_in = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        DepartmentPersonnels_in = new javax.swing.JTextField();
        DepartmentHead_in = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        DepartmentView_btn = new javax.swing.JButton();
        DepartmentAdd_btn = new javax.swing.JButton();
        DepartmentUpdate_btn = new javax.swing.JButton();
        DepartmentDelete_btn = new javax.swing.JButton();
        label4 = new java.awt.Label();
        label5 = new java.awt.Label();
        label6 = new java.awt.Label();
        SHIFTS_panel = new javax.swing.JPanel();
        ShiftPosition_in = new javax.swing.JTextField();
        ShiftIdNumber_in = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        Shifts_table = new javax.swing.JTable();
        ShiftDelete_btn = new javax.swing.JButton();
        ShiftUpdate_btn = new javax.swing.JButton();
        ShiftAdd_btn = new javax.swing.JButton();
        ShiftPersonnel_in = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        ShiftTimeEnd_in = new com.github.lgooddatepicker.components.TimePicker();
        ShiftTimeStart_in = new com.github.lgooddatepicker.components.TimePicker();
        ShiftCRN_in = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        ShiftDay_in = new javax.swing.JComboBox<>();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        activateShift_checkbox = new javax.swing.JCheckBox();
        SCHEDULE_panel = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        ScheduleAdd_btn = new javax.swing.JButton();
        ScheduleUpdate_btn = new javax.swing.JButton();
        ScheduleDelete_btn = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        Schedule_table = new javax.swing.JTable();
        jLabel51 = new javax.swing.JLabel();
        ScheduleName_in = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        ScheduleLogEntry_cb = new javax.swing.JComboBox<>();
        ScheduleWorkStatus_cb = new javax.swing.JComboBox<>();
        ScheduleDate_dc = new com.toedter.calendar.JDateChooser();
        ScheduleMorningOut_Pin = new com.github.lgooddatepicker.components.TimePicker();
        ScheduleMorningIn_Pin = new com.github.lgooddatepicker.components.TimePicker();
        ScheduleAfternoonOut_Pin = new com.github.lgooddatepicker.components.TimePicker();
        ScheduleAfternoonIn_Pin = new com.github.lgooddatepicker.components.TimePicker();
        jLabel55 = new javax.swing.JLabel();
        ScheduleCRN_in = new javax.swing.JTextField();
        ScheduleDayType_in = new javax.swing.JComboBox<>();
        LEAVE_panel = new javax.swing.JPanel();
        LeavePersonnel_in = new javax.swing.JTextField();
        LeaveExcuse_in = new javax.swing.JTextField();
        LeaveIdNumber_in = new javax.swing.JTextField();
        LeaveStatus_in = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        Leave_table = new javax.swing.JTable();
        LeaveDelete_btn = new javax.swing.JButton();
        LeaveUpdate_btn = new javax.swing.JButton();
        LeaveAdd_btn = new javax.swing.JButton();
        LeavePrint_btn = new javax.swing.JButton();
        LeaveIdNumberSearch_btn = new javax.swing.JButton();
        LeaveMonth_in = new com.toedter.calendar.JDateChooser();
        LeaveDateFiled_dc = new com.toedter.calendar.JDateChooser();
        LeaveDateStart_dc = new com.toedter.calendar.JDateChooser();
        LeaveDateEnd_dc = new com.toedter.calendar.JDateChooser();
        jLabel63 = new javax.swing.JLabel();
        LeaveYear_in = new com.toedter.calendar.JDateChooser();
        jLabel67 = new javax.swing.JLabel();
        LOGS_btn = new javax.swing.JButton();
        LEAVE_btn = new javax.swing.JButton();
        SCHEDULE_btn = new javax.swing.JButton();
        SHIFTS_btn = new javax.swing.JButton();
        DEPARTMENTS_btn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Reports_pane = new javax.swing.JPanel();
        weeklyR_btn = new javax.swing.JButton();
        KensinaR_btn = new javax.swing.JButton();
        dailyR_btn = new javax.swing.JButton();
        monthlyR_btn = new javax.swing.JButton();
        reportsViewer_panel = new javax.swing.JPanel();
        dailyR_panel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DailyReport_table = new javax.swing.JTable();
        SearchSDR_btn = new javax.swing.JButton();
        IDnumberSDR_in = new javax.swing.JTextField();
        SDRLogName_in = new javax.swing.JComboBox<>();
        goDailyReport_btn = new javax.swing.JButton();
        printDailyReport_btn = new javax.swing.JButton();
        DateSDR_in = new com.toedter.calendar.JDateChooser();
        jComboBox1 = new javax.swing.JComboBox<>();
        weeklyR_panel = new javax.swing.JPanel();
        YearWDR_in = new javax.swing.JTextField();
        DateWDR_in = new javax.swing.JTextField();
        IDnumberWDR_in = new javax.swing.JTextField();
        RWeekly_container = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        WeeklyReport_table = new javax.swing.JTable();
        two = new javax.swing.JLabel();
        four = new javax.swing.JLabel();
        three = new javax.swing.JLabel();
        seven = new javax.swing.JLabel();
        six = new javax.swing.JLabel();
        five = new javax.swing.JLabel();
        one = new javax.swing.JLabel();
        Date_lbl1 = new javax.swing.JLabel();
        seven2 = new javax.swing.JLabel();
        seven3 = new javax.swing.JLabel();
        seven4 = new javax.swing.JLabel();
        seven5 = new javax.swing.JLabel();
        seven6 = new javax.swing.JLabel();
        seven7 = new javax.swing.JLabel();
        seven8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        seven_log = new javax.swing.JComboBox<>();
        six_log = new javax.swing.JComboBox<>();
        five_log = new javax.swing.JComboBox<>();
        four_log = new javax.swing.JComboBox<>();
        three_log = new javax.swing.JComboBox<>();
        two_log = new javax.swing.JComboBox<>();
        one_log = new javax.swing.JComboBox<>();
        GoWDR_btn = new javax.swing.JButton();
        SearchWDR_btn = new javax.swing.JButton();
        WeeklyReportPrint = new javax.swing.JButton();
        prev_btn = new javax.swing.JButton();
        weekNum = new javax.swing.JLabel();
        fwd_btn = new javax.swing.JButton();
        PLcomboBox = new javax.swing.JComboBox<>();
        KensinaR_panel = new javax.swing.JPanel();
        halfMonthSearch_btn = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        searchByLogDTR_btn = new javax.swing.JButton();
        searchDTR_btn = new javax.swing.JButton();
        setMDR_in = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        idMDR_in = new javax.swing.JTextField();
        nameMDR_in = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        DTRtable = new javax.swing.JTable();
        jLabel38 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        LogPerDates = new javax.swing.JPanel();
        LogDate1 = new javax.swing.JComboBox<>();
        LogDate2 = new javax.swing.JComboBox<>();
        LogDate3 = new javax.swing.JComboBox<>();
        LogDate4 = new javax.swing.JComboBox<>();
        LogDate5 = new javax.swing.JComboBox<>();
        LogDate6 = new javax.swing.JComboBox<>();
        LogDate7 = new javax.swing.JComboBox<>();
        LogDate8 = new javax.swing.JComboBox<>();
        LogDate9 = new javax.swing.JComboBox<>();
        LogDate10 = new javax.swing.JComboBox<>();
        LogDate11 = new javax.swing.JComboBox<>();
        LogDate12 = new javax.swing.JComboBox<>();
        LogDate13 = new javax.swing.JComboBox<>();
        LogDate14 = new javax.swing.JComboBox<>();
        LogDate15 = new javax.swing.JComboBox<>();
        LogDate16 = new javax.swing.JComboBox<>();
        monthMDR_in = new com.toedter.calendar.JDateChooser();
        monthlyR_panel = new javax.swing.JPanel();
        scrollPane1 = new java.awt.ScrollPane();
        MonthlyDTRviewer = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        DTRtableMM = new javax.swing.JTable();
        LogDate17 = new javax.swing.JComboBox<>();
        LogDate18 = new javax.swing.JComboBox<>();
        LogDate19 = new javax.swing.JComboBox<>();
        LogDate20 = new javax.swing.JComboBox<>();
        LogDate21 = new javax.swing.JComboBox<>();
        LogDate22 = new javax.swing.JComboBox<>();
        LogDate23 = new javax.swing.JComboBox<>();
        LogDate24 = new javax.swing.JComboBox<>();
        LogDate25 = new javax.swing.JComboBox<>();
        LogDate26 = new javax.swing.JComboBox<>();
        LogDate27 = new javax.swing.JComboBox<>();
        LogDate28 = new javax.swing.JComboBox<>();
        LogDate29 = new javax.swing.JComboBox<>();
        LogDate30 = new javax.swing.JComboBox<>();
        LogDate31 = new javax.swing.JComboBox<>();
        LogDate32 = new javax.swing.JComboBox<>();
        LogDate63 = new javax.swing.JComboBox<>();
        LogDate64 = new javax.swing.JComboBox<>();
        LogDate65 = new javax.swing.JComboBox<>();
        LogDate66 = new javax.swing.JComboBox<>();
        LogDate67 = new javax.swing.JComboBox<>();
        LogDate68 = new javax.swing.JComboBox<>();
        LogDate69 = new javax.swing.JComboBox<>();
        LogDate70 = new javax.swing.JComboBox<>();
        LogDate71 = new javax.swing.JComboBox<>();
        LogDate72 = new javax.swing.JComboBox<>();
        LogDate73 = new javax.swing.JComboBox<>();
        LogDate74 = new javax.swing.JComboBox<>();
        LogDate75 = new javax.swing.JComboBox<>();
        LogDate76 = new javax.swing.JComboBox<>();
        LogDate77 = new javax.swing.JComboBox<>();
        searchByLogMM_btn = new javax.swing.JButton();
        MonthPrint_btn = new javax.swing.JButton();
        idNumberMM_in = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        nameMM_in = new javax.swing.JTextField();
        monthMM_in = new com.toedter.calendar.JDateChooser();
        jLabel66 = new javax.swing.JLabel();
        searchMM_btn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Personnels_pane = new javax.swing.JPanel();
        printPersonnels = new javax.swing.JButton();
        PersonnelSearch_in = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        Age_in = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        MI_in = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        ID_in = new javax.swing.JTextField();
        Lname_in = new javax.swing.JTextField();
        Fname_in = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        HireDate_in = new com.toedter.calendar.JDateChooser();
        Preveledge_in = new javax.swing.JComboBox<>();
        Department_in = new javax.swing.JComboBox<>();
        Gender_in = new javax.swing.JComboBox<>();
        Position_in = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        Personnels_table = new javax.swing.JTable();
        PersonnelSearch_btn = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        BTreg_panel = new javax.swing.JPanel();
        BTname = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        BTaddress = new javax.swing.JTextField();
        BTscan_btn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        PersonnelPassword_in = new javax.swing.JPasswordField();
        Add_btn = new javax.swing.JButton();
        Delete_btn = new javax.swing.JButton();
        Update_btn = new javax.swing.JButton();
        prompt_Message = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        About_pane = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        SideBar = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();
        LogOut_btn = new javax.swing.JButton();
        BG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Date_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Date_label.setText("July 8, 2021");
        Date_label.setBackground(new java.awt.Color(255, 255, 255));
        Date_label.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        Date_label.setForeground(new java.awt.Color(4, 8, 114));
        jPanel1.add(Date_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 90, 220, 30));

        Day_label.setBackground(new java.awt.Color(255, 255, 255));
        Day_label.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        Day_label.setForeground(new java.awt.Color(4, 8, 114));
        Day_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Day_label.setText("Thursday");
        jPanel1.add(Day_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 90, 150, 30));

        Time_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Time_label.setText("3:00 PM");
        Time_label.setBackground(new java.awt.Color(255, 255, 255));
        Time_label.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        Time_label.setForeground(new java.awt.Color(4, 8, 114));
        jPanel1.add(Time_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 90, 90, 30));

        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 220, 200));

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        User_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        User_name.setText("Name");
        User_name.setBackground(new java.awt.Color(255, 255, 255));
        User_name.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        User_name.setForeground(new java.awt.Color(4, 154, 19));
        jPanel4.add(User_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 240, 30));

        User_Position.setBackground(new java.awt.Color(255, 255, 255));
        User_Position.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        User_Position.setForeground(new java.awt.Color(102, 102, 102));
        User_Position.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        User_Position.setText("Position");
        User_Position.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel4.add(User_Position, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 240, 20));

        Dashboard_btn.setBorder(null);
        Dashboard_btn.setBorderPainted(false);
        Dashboard_btn.setContentAreaFilled(false);
        Dashboard_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dashboard_btnActionPerformed(evt);
            }
        });
        jPanel4.add(Dashboard_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 255, 180, 30));

        Attendance_btn.setBorder(null);
        Attendance_btn.setBorderPainted(false);
        Attendance_btn.setContentAreaFilled(false);
        Attendance_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Attendance_btnActionPerformed(evt);
            }
        });
        jPanel4.add(Attendance_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 180, 30));

        Reports_btn.setBorder(null);
        Reports_btn.setBorderPainted(false);
        Reports_btn.setContentAreaFilled(false);
        Reports_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Reports_btnActionPerformed(evt);
            }
        });
        jPanel4.add(Reports_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 180, 30));

        Personnels_btn.setBorder(null);
        Personnels_btn.setBorderPainted(false);
        Personnels_btn.setContentAreaFilled(false);
        Personnels_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Personnels_btnActionPerformed(evt);
            }
        });
        jPanel4.add(Personnels_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 180, 30));

        AboutUs_btn.setBorder(null);
        AboutUs_btn.setBorderPainted(false);
        AboutUs_btn.setContentAreaFilled(false);
        AboutUs_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutUs_btnActionPerformed(evt);
            }
        });
        jPanel4.add(AboutUs_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 445, 180, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 520));

        CARD_LAYOUT.setOpaque(false);
        CARD_LAYOUT.setLayout(new java.awt.CardLayout());

        Dashboard_pane.setOpaque(false);
        Dashboard_pane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        M_in.setFocusCycleRoot(true);
        M_in.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout morning_in_Graph1Layout = new javax.swing.GroupLayout(morning_in_Graph1);
        morning_in_Graph1.setLayout(morning_in_Graph1Layout);
        morning_in_Graph1Layout.setHorizontalGroup(
            morning_in_Graph1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        morning_in_Graph1Layout.setVerticalGroup(
            morning_in_Graph1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        M_in.add(morning_in_Graph1, java.awt.BorderLayout.CENTER);

        Dashboard_pane.add(M_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 170, 150));

        M_out.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout morning_out_Graph1Layout = new javax.swing.GroupLayout(morning_out_Graph1);
        morning_out_Graph1.setLayout(morning_out_Graph1Layout);
        morning_out_Graph1Layout.setHorizontalGroup(
            morning_out_Graph1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        morning_out_Graph1Layout.setVerticalGroup(
            morning_out_Graph1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        M_out.add(morning_out_Graph1, java.awt.BorderLayout.CENTER);

        Dashboard_pane.add(M_out, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, 170, 150));

        A_in.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout afternoon_in_Graph1Layout = new javax.swing.GroupLayout(afternoon_in_Graph1);
        afternoon_in_Graph1.setLayout(afternoon_in_Graph1Layout);
        afternoon_in_Graph1Layout.setHorizontalGroup(
            afternoon_in_Graph1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        afternoon_in_Graph1Layout.setVerticalGroup(
            afternoon_in_Graph1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        A_in.add(afternoon_in_Graph1, java.awt.BorderLayout.CENTER);

        Dashboard_pane.add(A_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, 170, 150));

        A_out.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout afternoon_out_Graph1Layout = new javax.swing.GroupLayout(afternoon_out_Graph1);
        afternoon_out_Graph1.setLayout(afternoon_out_Graph1Layout);
        afternoon_out_Graph1Layout.setHorizontalGroup(
            afternoon_out_Graph1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        afternoon_out_Graph1Layout.setVerticalGroup(
            afternoon_out_Graph1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        A_out.add(afternoon_out_Graph1, java.awt.BorderLayout.CENTER);

        Dashboard_pane.add(A_out, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 30, 170, 150));

        Calendar_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Calendar_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        Calendar_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmdNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons/next.png"))); // NOI18N
        cmdNext.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cmdNext.setContentAreaFilled(false);
        cmdNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNextActionPerformed(evt);
            }
        });
        Calendar_Panel.add(cmdNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 30, 40));

        lbMonthYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMonthYear.setText("Month - Year");
        lbMonthYear.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        lbMonthYear.setForeground(new java.awt.Color(4, 8, 114));
        lbMonthYear.setToolTipText("");
        Calendar_Panel.add(lbMonthYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 350, 40));

        cmdBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons/back.png"))); // NOI18N
        cmdBack.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cmdBack.setContentAreaFilled(false);
        cmdBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBackActionPerformed(evt);
            }
        });
        Calendar_Panel.add(cmdBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 30, 40));
        Calendar_Panel.add(slide, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 690, 270));

        Dashboard_pane.add(Calendar_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 710, 320));

        currentLogType_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        currentLogType_label.setText("NOT SET");
        currentLogType_label.setBackground(new java.awt.Color(4, 8, 114));
        currentLogType_label.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        currentLogType_label.setForeground(new java.awt.Color(204, 204, 204));
        currentLogType_label.setOpaque(true);
        currentLogType_label.setToolTipText("");
        Dashboard_pane.add(currentLogType_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(1015, 45, 135, 25));

        TimeInOut_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TimeInOut_label.setText("00:00");
        TimeInOut_label.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        TimeInOut_label.setForeground(new java.awt.Color(255, 153, 0));
        Dashboard_pane.add(TimeInOut_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(1015, 50, 135, 80));

        currentAttendance_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        currentAttendance_label.setText("NO ATTENDANCE SET");
        currentAttendance_label.setBackground(new java.awt.Color(4, 8, 114));
        currentAttendance_label.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        currentAttendance_label.setForeground(new java.awt.Color(255, 255, 255));
        currentAttendance_label.setOpaque(true);
        Dashboard_pane.add(currentAttendance_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(1015, 110, 135, 30));

        Min_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Min_label.setText("MORNING LOG IN");
        Min_label.setBackground(new java.awt.Color(255, 255, 255));
        Min_label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        Min_label.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        Min_label.setForeground(new java.awt.Color(4, 8, 114));
        Min_label.setOpaque(true);
        Min_label.setToolTipText("");
        Dashboard_pane.add(Min_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, 170, 20));

        currentAttendanceCycle_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        currentAttendanceCycle_label.setText("CURRENT ATTENDANCE");
        currentAttendanceCycle_label.setBackground(new java.awt.Color(4, 8, 114));
        currentAttendanceCycle_label.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        currentAttendanceCycle_label.setForeground(new java.awt.Color(204, 204, 204));
        currentAttendanceCycle_label.setOpaque(true);
        currentAttendanceCycle_label.setToolTipText("");
        Dashboard_pane.add(currentAttendanceCycle_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(1015, 130, 135, 30));

        Aout_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Aout_label.setText("AFTERNOON LOG OUT");
        Aout_label.setBackground(new java.awt.Color(255, 255, 255));
        Aout_label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        Aout_label.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        Aout_label.setForeground(new java.awt.Color(4, 8, 114));
        Aout_label.setOpaque(true);
        Aout_label.setToolTipText("");
        Dashboard_pane.add(Aout_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 180, 170, 20));

        Ain_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Ain_label.setText("AFTERNOON LOG IN");
        Ain_label.setBackground(new java.awt.Color(255, 255, 255));
        Ain_label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        Ain_label.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        Ain_label.setForeground(new java.awt.Color(4, 8, 114));
        Ain_label.setOpaque(true);
        Ain_label.setToolTipText("");
        Dashboard_pane.add(Ain_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 180, 170, 20));

        Mout_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Mout_label.setText("MORNING LOG OUT");
        Mout_label.setBackground(new java.awt.Color(255, 255, 255));
        Mout_label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        Mout_label.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        Mout_label.setForeground(new java.awt.Color(4, 8, 114));
        Mout_label.setOpaque(true);
        Mout_label.setToolTipText("");
        Dashboard_pane.add(Mout_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, 170, 20));

        NumberOfOfficials_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NumberOfOfficials_label.setText("0");
        NumberOfOfficials_label.setFont(new java.awt.Font("Century Gothic", 1, 56)); // NOI18N
        NumberOfOfficials_label.setForeground(new java.awt.Color(255, 153, 0));
        Dashboard_pane.add(NumberOfOfficials_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(1015, 240, 135, 80));

        NumberOfStaff_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NumberOfStaff_label.setText("0");
        NumberOfStaff_label.setFont(new java.awt.Font("Century Gothic", 1, 56)); // NOI18N
        NumberOfStaff_label.setForeground(new java.awt.Color(255, 153, 0));
        Dashboard_pane.add(NumberOfStaff_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(1015, 410, 135, 80));

        NumberOfPersonnels_bg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NumberOfPersonnels_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bar.png"))); // NOI18N
        Dashboard_pane.add(NumberOfPersonnels_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 20, 160, 160));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("OFFICIALS");
        jLabel8.setBackground(new java.awt.Color(4, 8, 114));
        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setOpaque(true);
        Dashboard_pane.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1015, 300, 135, 30));

        NumberOfOfficials_bg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NumberOfOfficials_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bar.png"))); // NOI18N
        Dashboard_pane.add(NumberOfOfficials_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 200, 160, 160));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("STAFF");
        jLabel7.setBackground(new java.awt.Color(4, 8, 114));
        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setOpaque(true);
        jLabel7.setToolTipText("");
        Dashboard_pane.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1015, 470, 135, 30));

        NumberOfStaff_bg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NumberOfStaff_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bar.png"))); // NOI18N
        Dashboard_pane.add(NumberOfStaff_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 370, 160, 160));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Dashboard_BG.png"))); // NOI18N
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Dashboard_pane.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1200, 560));

        CARD_LAYOUT.add(Dashboard_pane, "card1");

        Attendance_pane.setOpaque(false);
        Attendance_pane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new java.awt.CardLayout());

        LOG_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LogName_in.setEditable(false);
        LogName_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        LogName_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        LOG_panel.add(LogName_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 220, 30));

        jLabel25.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(4, 153, 19));
        jLabel25.setText("LOG NAME");
        LOG_panel.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, 80, 30));

        jLabel26.setText("DATE");
        jLabel26.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(4, 153, 19));
        LOG_panel.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 80, 30));

        LogDate_in.setEditable(false);
        LogDate_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        LogDate_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        LOG_panel.add(LogDate_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 150, 220, 30));

        jLabel27.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(4, 153, 19));
        jLabel27.setText("DAY TYPE");
        LOG_panel.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 80, 30));

        LogDayType_in.setEditable(false);
        LogDayType_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        LogDayType_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        LOG_panel.add(LogDayType_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, 220, 30));

        jLabel28.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(4, 153, 19));
        jLabel28.setText("WORK STATUS");
        LOG_panel.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 230, 80, 30));

        LogWorkStatus_in.setEditable(false);
        LogWorkStatus_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        LogWorkStatus_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        LOG_panel.add(LogWorkStatus_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 230, 220, 30));

        jLabel29.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(4, 153, 19));
        jLabel29.setText("LOG CYCLE");
        LOG_panel.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 270, 80, 30));

        LogEntry_in.setEditable(false);
        LogEntry_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        LogEntry_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        LOG_panel.add(LogEntry_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 270, 220, 30));

        jLabel30.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(4, 153, 19));
        jLabel30.setText("STATUS");
        LOG_panel.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 310, 80, 30));

        LogStatus_in.setEditable(false);
        LogStatus_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        LogStatus_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        LOG_panel.add(LogStatus_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 310, 220, 30));

        LogDelete_btn.setText("DELETE");
        LogDelete_btn.setBackground(new java.awt.Color(254, 90, 57));
        LogDelete_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        LogDelete_btn.setBorderPainted(false);
        LogDelete_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        LogDelete_btn.setForeground(new java.awt.Color(255, 255, 255));
        LogDelete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogDelete_btnActionPerformed(evt);
            }
        });
        LOG_panel.add(LogDelete_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 360, 310, 30));

        Logs_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "LOG NAME", "DATE", "STATUS"
            }
        ));
        Logs_table.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Logs_table.setRowHeight(30);
        Logs_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Logs_tableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(Logs_table);

        LOG_panel.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 500, 380));

        jLabel54.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(4, 153, 19));
        jLabel54.setText("CRN");
        LOG_panel.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 80, 30));

        LogID_in.setEditable(false);
        LogID_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        LogID_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        LOG_panel.add(LogID_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 220, 30));

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 153, 19)));
        jPanel8.setToolTipText("");
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        UseLog_checkbox.setText(" Use this Log for today's attendance.");
        UseLog_checkbox.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        UseLog_checkbox.setForeground(new java.awt.Color(4, 153, 19));
        UseLog_checkbox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                UseLog_checkboxItemStateChanged(evt);
            }
        });
        jPanel8.add(UseLog_checkbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, 30));

        useLogSave_btn.setText("SAVE");
        useLogSave_btn.setBackground(new java.awt.Color(4, 8, 114));
        useLogSave_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 153, 19)));
        useLogSave_btn.setFocusCycleRoot(true);
        useLogSave_btn.setFocusPainted(false);
        useLogSave_btn.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        useLogSave_btn.setForeground(new java.awt.Color(255, 255, 255));
        useLogSave_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useLogSave_btnActionPerformed(evt);
            }
        });
        jPanel8.add(useLogSave_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 40, 30));

        LOG_panel.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 310, 50));

        jPanel3.add(LOG_panel, "card2");

        DEPARTMENTS_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Departments_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DEPARTMENT", "PERSONNELS", "HEAD"
            }
        ));
        Departments_table.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Departments_table.setRowHeight(30);
        Departments_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Departments_tableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(Departments_table);

        DEPARTMENTS_panel.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 500, 380));

        dptPositions_btn.setText("POSITIONS");
        dptPositions_btn.setBackground(new java.awt.Color(255, 255, 255));
        dptPositions_btn.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        dptPositions_btn.setFocusPainted(false);
        dptPositions_btn.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        dptPositions_btn.setForeground(new java.awt.Color(4, 8, 114));
        dptPositions_btn.setToolTipText("");
        dptPositions_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dptPositions_btnActionPerformed(evt);
            }
        });
        DEPARTMENTS_panel.add(dptPositions_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, 140, 30));

        dptHead_btn.setText("DEPARTMENT");
        dptHead_btn.setBackground(new java.awt.Color(4, 8, 114));
        dptHead_btn.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        dptHead_btn.setFocusPainted(false);
        dptHead_btn.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        dptHead_btn.setForeground(new java.awt.Color(255, 255, 255));
        dptHead_btn.setToolTipText("");
        dptHead_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dptHead_btnActionPerformed(evt);
            }
        });
        DEPARTMENTS_panel.add(dptHead_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 140, 30));

        jPanel7.setLayout(new java.awt.CardLayout());

        jLabel59.setText("CRN");
        jLabel59.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(4, 153, 19));

        PositionID_in.setEditable(false);
        PositionID_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        PositionID_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));

        jLabel60.setText("DEPARTMENT");
        jLabel60.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(4, 153, 19));

        jLabel61.setText("POSITION");
        jLabel61.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(4, 153, 19));

        DepartmentPositionName_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        DepartmentPositionName_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        DepartmentPositionName_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                DepartmentPositionName_inKeyTyped(evt);
            }
        });

        DepartmentPositionDelete_btn.setText("DELETE");
        DepartmentPositionDelete_btn.setBackground(new java.awt.Color(254, 90, 57));
        DepartmentPositionDelete_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        DepartmentPositionDelete_btn.setBorderPainted(false);
        DepartmentPositionDelete_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        DepartmentPositionDelete_btn.setForeground(new java.awt.Color(255, 255, 255));
        DepartmentPositionDelete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepartmentPositionDelete_btnActionPerformed(evt);
            }
        });

        DepartmentPositionUpdate_btn.setText("UPDATE");
        DepartmentPositionUpdate_btn.setBackground(new java.awt.Color(252, 226, 5));
        DepartmentPositionUpdate_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        DepartmentPositionUpdate_btn.setBorderPainted(false);
        DepartmentPositionUpdate_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        DepartmentPositionUpdate_btn.setForeground(new java.awt.Color(255, 255, 255));
        DepartmentPositionUpdate_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepartmentPositionUpdate_btnActionPerformed(evt);
            }
        });

        DepartmentAddPosition_btn.setText("ADD");
        DepartmentAddPosition_btn.setBackground(new java.awt.Color(4, 153, 19));
        DepartmentAddPosition_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        DepartmentAddPosition_btn.setBorderPainted(false);
        DepartmentAddPosition_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        DepartmentAddPosition_btn.setForeground(new java.awt.Color(255, 255, 255));
        DepartmentAddPosition_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepartmentAddPosition_btnActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        label1.setForeground(new java.awt.Color(245, 90, 57));
        label1.setText("DELETE : remove position.");

        label2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        label2.setForeground(new java.awt.Color(252, 226, 5));
        label2.setText("UPDATE: update position title.");

        label3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        label3.setForeground(new java.awt.Color(4, 153, 19));
        label3.setText("ADD : add position to a department.");

        PositionDepartmentName_in.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CHOOSE DEPARTMENT" }));
        PositionDepartmentName_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PositionDepartmentName_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        PositionDepartmentName_in.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                PositionDepartmentName_inItemStateChanged(evt);
            }
        });

        label7.setAlignment(java.awt.Label.CENTER);
        label7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        label7.setForeground(new java.awt.Color(4, 8, 114));
        label7.setName(""); // NOI18N
        label7.setText("SELECT DEPARTMENT TO VIEW AVAILABLE POSITIONS");

        label8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label8.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        label8.setForeground(new java.awt.Color(4, 8, 114));
        label8.setName(""); // NOI18N
        label8.setText("Officials: Barangay Official");

        label9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label9.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        label9.setForeground(new java.awt.Color(4, 8, 114));
        label9.setName(""); // NOI18N
        label9.setText("Staff: Any Department");

        javax.swing.GroupLayout dptPositions_panelLayout = new javax.swing.GroupLayout(dptPositions_panel);
        dptPositions_panel.setLayout(dptPositions_panelLayout);
        dptPositions_panelLayout.setHorizontalGroup(
            dptPositions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dptPositions_panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(dptPositions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dptPositions_panelLayout.createSequentialGroup()
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(PositionID_in, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dptPositions_panelLayout.createSequentialGroup()
                        .addComponent(DepartmentPositionDelete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(DepartmentPositionUpdate_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(DepartmentAddPosition_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dptPositions_panelLayout.createSequentialGroup()
                        .addGroup(dptPositions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(dptPositions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PositionDepartmentName_in, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DepartmentPositionName_in, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)))))
            .addGroup(dptPositions_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dptPositions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(dptPositions_panelLayout.createSequentialGroup()
                .addGroup(dptPositions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        dptPositions_panelLayout.setVerticalGroup(
            dptPositions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dptPositions_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dptPositions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PositionID_in, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(dptPositions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PositionDepartmentName_in, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(dptPositions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DepartmentPositionName_in, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dptPositions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DepartmentPositionDelete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DepartmentPositionUpdate_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DepartmentAddPosition_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jPanel7.add(dptPositions_panel, "card3");

        jLabel53.setText("CRN");
        jLabel53.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(4, 153, 19));

        DepartmentID_in.setEditable(false);
        DepartmentID_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        DepartmentID_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));

        DepartmentName_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        DepartmentName_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));

        jLabel33.setText("DEPARTMENT");
        jLabel33.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(4, 153, 19));

        jLabel32.setText("PERSONNELS");
        jLabel32.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(4, 153, 19));

        DepartmentPersonnels_in.setEditable(false);
        DepartmentPersonnels_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        DepartmentPersonnels_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));

        DepartmentHead_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        DepartmentHead_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));

        jLabel31.setText("HEAD");
        jLabel31.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(4, 153, 19));

        DepartmentView_btn.setText("VIEW");
        DepartmentView_btn.setBackground(new java.awt.Color(4, 8, 114));
        DepartmentView_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        DepartmentView_btn.setBorderPainted(false);
        DepartmentView_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        DepartmentView_btn.setForeground(new java.awt.Color(255, 255, 255));
        DepartmentView_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepartmentView_btnActionPerformed(evt);
            }
        });

        DepartmentAdd_btn.setText("ADD");
        DepartmentAdd_btn.setBackground(new java.awt.Color(4, 153, 19));
        DepartmentAdd_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        DepartmentAdd_btn.setBorderPainted(false);
        DepartmentAdd_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        DepartmentAdd_btn.setForeground(new java.awt.Color(255, 255, 255));
        DepartmentAdd_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepartmentAdd_btnActionPerformed(evt);
            }
        });

        DepartmentUpdate_btn.setText("UPDATE");
        DepartmentUpdate_btn.setBackground(new java.awt.Color(252, 226, 5));
        DepartmentUpdate_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        DepartmentUpdate_btn.setBorderPainted(false);
        DepartmentUpdate_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        DepartmentUpdate_btn.setForeground(new java.awt.Color(255, 255, 255));
        DepartmentUpdate_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepartmentUpdate_btnActionPerformed(evt);
            }
        });

        DepartmentDelete_btn.setText("DELETE");
        DepartmentDelete_btn.setBackground(new java.awt.Color(254, 90, 57));
        DepartmentDelete_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        DepartmentDelete_btn.setBorderPainted(false);
        DepartmentDelete_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        DepartmentDelete_btn.setForeground(new java.awt.Color(255, 255, 255));
        DepartmentDelete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepartmentDelete_btnActionPerformed(evt);
            }
        });

        label4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        label4.setForeground(new java.awt.Color(4, 153, 19));
        label4.setText("ADD : add department.");

        label5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        label5.setForeground(new java.awt.Color(252, 226, 5));
        label5.setText("UPDATE: update position department");

        label6.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        label6.setForeground(new java.awt.Color(245, 90, 57));
        label6.setText("DELETE : remove department.");

        javax.swing.GroupLayout dptHead_panelLayout = new javax.swing.GroupLayout(dptHead_panel);
        dptHead_panel.setLayout(dptHead_panelLayout);
        dptHead_panelLayout.setHorizontalGroup(
            dptHead_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dptHead_panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(dptHead_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dptHead_panelLayout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(DepartmentID_in, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dptHead_panelLayout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(DepartmentName_in, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dptHead_panelLayout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(DepartmentPersonnels_in, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dptHead_panelLayout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(DepartmentHead_in, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(DepartmentView_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dptHead_panelLayout.createSequentialGroup()
                        .addComponent(DepartmentDelete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(DepartmentUpdate_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(DepartmentAdd_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(dptHead_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dptHead_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dptHead_panelLayout.setVerticalGroup(
            dptHead_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dptHead_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(dptHead_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DepartmentID_in, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(dptHead_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DepartmentName_in, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(dptHead_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DepartmentPersonnels_in, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(dptHead_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DepartmentHead_in, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(DepartmentView_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(dptHead_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DepartmentDelete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DepartmentUpdate_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DepartmentAdd_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel7.add(dptHead_panel, "card2");

        DEPARTMENTS_panel.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 310, 340));

        jPanel3.add(DEPARTMENTS_panel, "card3");

        SHIFTS_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ShiftPosition_in.setEditable(false);
        ShiftPosition_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ShiftPosition_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        SHIFTS_panel.add(ShiftPosition_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, 220, 30));

        ShiftIdNumber_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ShiftIdNumber_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        ShiftIdNumber_in.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ShiftIdNumber_inPropertyChange(evt);
            }
        });
        ShiftIdNumber_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ShiftIdNumber_inKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ShiftIdNumber_inKeyTyped(evt);
            }
        });
        SHIFTS_panel.add(ShiftIdNumber_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 240, 220, 30));

        jLabel34.setText("SHIFT DAY");
        jLabel34.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(4, 153, 19));
        SHIFTS_panel.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 80, 30));

        jLabel35.setText("END");
        jLabel35.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(4, 153, 19));
        SHIFTS_panel.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 130, 100, 30));

        jLabel36.setText("ID NUMBER");
        jLabel36.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(4, 153, 19));
        SHIFTS_panel.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 240, 80, 30));

        jLabel37.setText("POSITION");
        jLabel37.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(4, 153, 19));
        SHIFTS_panel.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 280, 80, 30));

        Shifts_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CRN", "SHIFT DAY", "PERSONNEL", "START TIME", "END TIME"
            }
        ));
        Shifts_table.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Shifts_table.setRowHeight(30);
        Shifts_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Shifts_tableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(Shifts_table);

        SHIFTS_panel.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 500, 380));

        ShiftDelete_btn.setText("DELETE");
        ShiftDelete_btn.setBackground(new java.awt.Color(254, 90, 57));
        ShiftDelete_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        ShiftDelete_btn.setBorderPainted(false);
        ShiftDelete_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        ShiftDelete_btn.setForeground(new java.awt.Color(255, 255, 255));
        ShiftDelete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShiftDelete_btnActionPerformed(evt);
            }
        });
        SHIFTS_panel.add(ShiftDelete_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 360, 90, 30));

        ShiftUpdate_btn.setText("UPDATE");
        ShiftUpdate_btn.setBackground(new java.awt.Color(252, 226, 5));
        ShiftUpdate_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        ShiftUpdate_btn.setBorderPainted(false);
        ShiftUpdate_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        ShiftUpdate_btn.setForeground(new java.awt.Color(255, 255, 255));
        ShiftUpdate_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShiftUpdate_btnActionPerformed(evt);
            }
        });
        SHIFTS_panel.add(ShiftUpdate_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 360, 90, 30));

        ShiftAdd_btn.setText("ADD");
        ShiftAdd_btn.setBackground(new java.awt.Color(4, 153, 19));
        ShiftAdd_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        ShiftAdd_btn.setBorderPainted(false);
        ShiftAdd_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        ShiftAdd_btn.setForeground(new java.awt.Color(255, 255, 255));
        ShiftAdd_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShiftAdd_btnActionPerformed(evt);
            }
        });
        SHIFTS_panel.add(ShiftAdd_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 360, 90, 30));

        ShiftPersonnel_in.setEditable(false);
        ShiftPersonnel_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ShiftPersonnel_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        SHIFTS_panel.add(ShiftPersonnel_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 200, 220, 30));

        jLabel50.setText("PERSONNEL");
        jLabel50.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(4, 153, 19));
        SHIFTS_panel.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, 80, 30));

        ShiftTimeEnd_in.setBackground(new java.awt.Color(255, 255, 255));
        ShiftTimeEnd_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ShiftTimeEnd_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ShiftTimeEnd_in.setOpaque(false);
        SHIFTS_panel.add(ShiftTimeEnd_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 160, 100, 30));

        ShiftTimeStart_in.setBackground(new java.awt.Color(255, 255, 255));
        ShiftTimeStart_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ShiftTimeStart_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ShiftTimeStart_in.setOpaque(false);
        SHIFTS_panel.add(ShiftTimeStart_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 160, 100, 30));

        ShiftCRN_in.setEditable(false);
        ShiftCRN_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ShiftCRN_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        SHIFTS_panel.add(ShiftCRN_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, 220, 30));

        jLabel56.setText("CRN");
        jLabel56.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(4, 153, 19));
        SHIFTS_panel.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 80, 30));

        ShiftDay_in.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" }));
        ShiftDay_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        SHIFTS_panel.add(ShiftDay_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 100, 220, 30));

        jLabel57.setText("SHIFT TIME");
        jLabel57.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(4, 153, 19));
        SHIFTS_panel.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 160, 90, 30));

        jLabel58.setText("START");
        jLabel58.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(4, 153, 19));
        SHIFTS_panel.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 130, 100, 30));

        activateShift_checkbox.setSelected(true);
        activateShift_checkbox.setText("  Activate this time Shift.");
        activateShift_checkbox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        activateShift_checkbox.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        activateShift_checkbox.setForeground(new java.awt.Color(4, 153, 19));
        activateShift_checkbox.setToolTipText("");
        SHIFTS_panel.add(activateShift_checkbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 320, 220, 30));

        jPanel3.add(SHIFTS_panel, "card4");

        SCHEDULE_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setText("AFTERNOON");
        jLabel39.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(4, 153, 19));
        SCHEDULE_panel.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 300, 80, 30));

        jLabel40.setText("MORNING");
        jLabel40.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(4, 153, 19));
        SCHEDULE_panel.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 260, 80, 30));

        jLabel41.setText("LOG CYCLE");
        jLabel41.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(4, 153, 19));
        SCHEDULE_panel.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 220, 80, 30));

        jLabel42.setText("WORK STATUS");
        jLabel42.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(4, 153, 19));
        SCHEDULE_panel.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, 80, 30));

        jLabel43.setText("DATE");
        jLabel43.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(4, 153, 19));
        SCHEDULE_panel.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 80, 30));

        ScheduleAdd_btn.setText("ADD");
        ScheduleAdd_btn.setBackground(new java.awt.Color(4, 153, 19));
        ScheduleAdd_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        ScheduleAdd_btn.setBorderPainted(false);
        ScheduleAdd_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        ScheduleAdd_btn.setForeground(new java.awt.Color(255, 255, 255));
        ScheduleAdd_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ScheduleAdd_btnActionPerformed(evt);
            }
        });
        SCHEDULE_panel.add(ScheduleAdd_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 380, 90, 30));

        ScheduleUpdate_btn.setText("UPDATE");
        ScheduleUpdate_btn.setBackground(new java.awt.Color(252, 226, 5));
        ScheduleUpdate_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        ScheduleUpdate_btn.setBorderPainted(false);
        ScheduleUpdate_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        ScheduleUpdate_btn.setForeground(new java.awt.Color(255, 255, 255));
        ScheduleUpdate_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ScheduleUpdate_btnActionPerformed(evt);
            }
        });
        SCHEDULE_panel.add(ScheduleUpdate_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 380, 90, 30));

        ScheduleDelete_btn.setText("DELETE");
        ScheduleDelete_btn.setBackground(new java.awt.Color(254, 90, 57));
        ScheduleDelete_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        ScheduleDelete_btn.setBorderPainted(false);
        ScheduleDelete_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        ScheduleDelete_btn.setForeground(new java.awt.Color(255, 255, 255));
        ScheduleDelete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ScheduleDelete_btnActionPerformed(evt);
            }
        });
        SCHEDULE_panel.add(ScheduleDelete_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, 90, 30));

        Schedule_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CRN", "DATE", "NAME", "WORK STATUS", "LOG ENTRY CYCLE", "STATUS"
            }
        ));
        Schedule_table.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Schedule_table.setRowHeight(30);
        Schedule_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Schedule_tableMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(Schedule_table);

        SCHEDULE_panel.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 500, 380));

        jLabel51.setText("LOG NAME");
        jLabel51.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(4, 153, 19));
        SCHEDULE_panel.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 80, 30));

        ScheduleName_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ScheduleName_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        SCHEDULE_panel.add(ScheduleName_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, 220, 30));

        jLabel52.setText("DAY TYPE");
        jLabel52.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(4, 153, 19));
        SCHEDULE_panel.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 80, 30));

        ScheduleLogEntry_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Log Cycle", "Morning In", "Afternoon In", "Morning In-Out", "Afternoon In-Out", "Morning In and Afternoon Out", "Morning In-Out and Afternoon In-Out" }));
        ScheduleLogEntry_cb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ScheduleLogEntry_cb.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ScheduleLogEntry_cb.setOpaque(false);
        ScheduleLogEntry_cb.setToolTipText("");
        ScheduleLogEntry_cb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ScheduleLogEntry_cbItemStateChanged(evt);
            }
        });
        SCHEDULE_panel.add(ScheduleLogEntry_cb, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 220, 220, 30));

        ScheduleWorkStatus_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Working Status", "Working Day", "Non-Working Day" }));
        ScheduleWorkStatus_cb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ScheduleWorkStatus_cb.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ScheduleWorkStatus_cb.setOpaque(false);
        ScheduleWorkStatus_cb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ScheduleWorkStatus_cbItemStateChanged(evt);
            }
        });
        SCHEDULE_panel.add(ScheduleWorkStatus_cb, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 180, 220, 30));

        ScheduleDate_dc.setBackground(new java.awt.Color(255, 255, 255));
        ScheduleDate_dc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ScheduleDate_dc.setDateFormatString("MMMM dd yyyy");
        ScheduleDate_dc.setFocusable(false);
        ScheduleDate_dc.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        SCHEDULE_panel.add(ScheduleDate_dc, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 140, 220, 30));

        ScheduleMorningOut_Pin.setBackground(new java.awt.Color(255, 255, 255));
        ScheduleMorningOut_Pin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ScheduleMorningOut_Pin.setFocusable(false);
        ScheduleMorningOut_Pin.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ScheduleMorningOut_Pin.setOpaque(false);
        ScheduleMorningOut_Pin.setToolTipText("OUT");
        ScheduleMorningOut_Pin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ScheduleMorningOut_PinFocusLost(evt);
            }
        });
        SCHEDULE_panel.add(ScheduleMorningOut_Pin, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, 100, 30));

        ScheduleMorningIn_Pin.setBackground(new java.awt.Color(255, 255, 255));
        ScheduleMorningIn_Pin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ScheduleMorningIn_Pin.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ScheduleMorningIn_Pin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ScheduleMorningIn_PinFocusLost(evt);
            }
        });
        ScheduleMorningIn_Pin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ScheduleMorningIn_PinPropertyChange(evt);
            }
        });
        SCHEDULE_panel.add(ScheduleMorningIn_Pin, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 260, 100, 30));

        ScheduleAfternoonOut_Pin.setBackground(new java.awt.Color(255, 255, 255));
        ScheduleAfternoonOut_Pin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ScheduleAfternoonOut_Pin.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ScheduleAfternoonOut_Pin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ScheduleAfternoonOut_PinFocusLost(evt);
            }
        });
        ScheduleAfternoonOut_Pin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ScheduleAfternoonOut_PinPropertyChange(evt);
            }
        });
        SCHEDULE_panel.add(ScheduleAfternoonOut_Pin, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 300, 100, 30));

        ScheduleAfternoonIn_Pin.setBackground(new java.awt.Color(255, 255, 255));
        ScheduleAfternoonIn_Pin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ScheduleAfternoonIn_Pin.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ScheduleAfternoonIn_Pin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ScheduleAfternoonIn_PinFocusLost(evt);
            }
        });
        SCHEDULE_panel.add(ScheduleAfternoonIn_Pin, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 300, 100, 30));

        jLabel55.setText("CRN");
        jLabel55.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(4, 153, 19));
        SCHEDULE_panel.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 80, 30));

        ScheduleCRN_in.setEditable(false);
        ScheduleCRN_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ScheduleCRN_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        SCHEDULE_panel.add(ScheduleCRN_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, 220, 30));

        ScheduleDayType_in.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "REGULAR DAY", "HOLIDAY", "WEEKEND" }));
        ScheduleDayType_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        SCHEDULE_panel.add(ScheduleDayType_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 100, 220, 30));

        jPanel3.add(SCHEDULE_panel, "card5");

        LEAVE_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LeavePersonnel_in.setEditable(false);
        LeavePersonnel_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        LeavePersonnel_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        LEAVE_panel.add(LeavePersonnel_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 150, 220, 30));

        LeaveExcuse_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        LeaveExcuse_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        LeaveExcuse_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LeaveExcuse_inKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                LeaveExcuse_inKeyTyped(evt);
            }
        });
        LEAVE_panel.add(LeaveExcuse_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 230, 220, 30));

        LeaveIdNumber_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        LeaveIdNumber_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        LeaveIdNumber_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LeaveIdNumber_inKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                LeaveIdNumber_inKeyTyped(evt);
            }
        });
        LEAVE_panel.add(LeaveIdNumber_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 220, 30));

        LeaveStatus_in.setEditable(false);
        LeaveStatus_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        LeaveStatus_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        LEAVE_panel.add(LeaveStatus_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 310, 220, 30));

        jLabel44.setText("STATUS");
        jLabel44.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(4, 153, 19));
        LEAVE_panel.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 310, 80, 30));

        jLabel45.setText("DATE");
        jLabel45.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(4, 153, 19));
        LEAVE_panel.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 270, 80, 30));

        jLabel46.setText("EXCUSE");
        jLabel46.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(4, 153, 19));
        LEAVE_panel.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 230, 80, 30));

        jLabel47.setText("YEAR");
        jLabel47.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(4, 153, 19));
        LEAVE_panel.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, 80, 30));

        jLabel48.setText("DATE FILED");
        jLabel48.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(4, 153, 19));
        LEAVE_panel.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 80, 30));

        jLabel49.setText("PERSONNEL");
        jLabel49.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(4, 153, 19));
        LEAVE_panel.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 80, 30));

        Leave_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CRN", "ID NUMBER", "LEAVE EXCUSE", "DATE FILED"
            }
        ));
        Leave_table.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Leave_table.setRowHeight(30);
        Leave_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Leave_tableMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(Leave_table);

        LEAVE_panel.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 500, 380));

        LeaveDelete_btn.setText("DELETE");
        LeaveDelete_btn.setBackground(new java.awt.Color(254, 90, 57));
        LeaveDelete_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        LeaveDelete_btn.setBorderPainted(false);
        LeaveDelete_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        LeaveDelete_btn.setForeground(new java.awt.Color(255, 255, 255));
        LeaveDelete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeaveDelete_btnActionPerformed(evt);
            }
        });
        LEAVE_panel.add(LeaveDelete_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 360, 90, 30));

        LeaveUpdate_btn.setText("UPDATE");
        LeaveUpdate_btn.setBackground(new java.awt.Color(252, 226, 5));
        LeaveUpdate_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        LeaveUpdate_btn.setBorderPainted(false);
        LeaveUpdate_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        LeaveUpdate_btn.setForeground(new java.awt.Color(255, 255, 255));
        LeaveUpdate_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeaveUpdate_btnActionPerformed(evt);
            }
        });
        LEAVE_panel.add(LeaveUpdate_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 360, 90, 30));

        LeaveAdd_btn.setText("ADD");
        LeaveAdd_btn.setBackground(new java.awt.Color(4, 153, 19));
        LeaveAdd_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        LeaveAdd_btn.setBorderPainted(false);
        LeaveAdd_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        LeaveAdd_btn.setForeground(new java.awt.Color(255, 255, 255));
        LeaveAdd_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeaveAdd_btnActionPerformed(evt);
            }
        });
        LEAVE_panel.add(LeaveAdd_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 360, 90, 30));

        LeavePrint_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons/ic_local_printshop_24px.png"))); // NOI18N
        LeavePrint_btn.setText(" PRINT");
        LeavePrint_btn.setBackground(new java.awt.Color(4, 8, 114));
        LeavePrint_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        LeavePrint_btn.setBorderPainted(false);
        LeavePrint_btn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        LeavePrint_btn.setForeground(new java.awt.Color(255, 255, 255));
        LeavePrint_btn.setToolTipText("Search personnel by ID Number");
        LeavePrint_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeavePrint_btnActionPerformed(evt);
            }
        });
        LEAVE_panel.add(LeavePrint_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 105, 30));

        LeaveIdNumberSearch_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons/ic_search_24px.png"))); // NOI18N
        LeaveIdNumberSearch_btn.setText("SEARCH");
        LeaveIdNumberSearch_btn.setBackground(new java.awt.Color(4, 8, 114));
        LeaveIdNumberSearch_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        LeaveIdNumberSearch_btn.setBorderPainted(false);
        LeaveIdNumberSearch_btn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        LeaveIdNumberSearch_btn.setForeground(new java.awt.Color(255, 255, 255));
        LeaveIdNumberSearch_btn.setToolTipText("Search personnel by ID Number");
        LeaveIdNumberSearch_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeaveIdNumberSearch_btnActionPerformed(evt);
            }
        });
        LEAVE_panel.add(LeaveIdNumberSearch_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 70, 105, 30));

        LeaveMonth_in.setBackground(new java.awt.Color(255, 255, 255));
        LeaveMonth_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LeaveMonth_in.setDateFormatString("MMMM");
        LeaveMonth_in.setFocusable(false);
        LeaveMonth_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        LEAVE_panel.add(LeaveMonth_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, 100, 30));

        LeaveDateFiled_dc.setBackground(new java.awt.Color(255, 255, 255));
        LeaveDateFiled_dc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LeaveDateFiled_dc.setDateFormatString("MMMM dd yyyy");
        LeaveDateFiled_dc.setFocusable(false);
        LeaveDateFiled_dc.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        LEAVE_panel.add(LeaveDateFiled_dc, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, 220, 30));

        LeaveDateStart_dc.setBackground(new java.awt.Color(255, 255, 255));
        LeaveDateStart_dc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LeaveDateStart_dc.setDateFormatString("MMMM dd yyyy");
        LeaveDateStart_dc.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        LeaveDateStart_dc.setOpaque(false);
        LEAVE_panel.add(LeaveDateStart_dc, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 270, 110, 30));

        LeaveDateEnd_dc.setBackground(new java.awt.Color(255, 255, 255));
        LeaveDateEnd_dc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LeaveDateEnd_dc.setDateFormatString("MMMM dd yyyy");
        LeaveDateEnd_dc.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        LeaveDateEnd_dc.setOpaque(false);
        LEAVE_panel.add(LeaveDateEnd_dc, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 270, 100, 30));

        jLabel63.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(4, 153, 19));
        jLabel63.setText("ID NUMBER");
        LEAVE_panel.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, 80, 30));

        LeaveYear_in.setBackground(new java.awt.Color(255, 255, 255));
        LeaveYear_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LeaveYear_in.setDateFormatString("YYYY");
        LeaveYear_in.setFocusable(false);
        LeaveYear_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        LEAVE_panel.add(LeaveYear_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 30, 105, 30));

        jLabel67.setText("MONTH");
        jLabel67.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(4, 153, 19));
        LEAVE_panel.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 0, 80, 30));

        jPanel3.add(LEAVE_panel, "card6");

        Attendance_pane.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 870, 420));

        LOGS_btn.setText("LOGS");
        LOGS_btn.setBackground(new java.awt.Color(4, 8, 114));
        LOGS_btn.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));
        LOGS_btn.setFocusPainted(false);
        LOGS_btn.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        LOGS_btn.setForeground(new java.awt.Color(255, 255, 255));
        LOGS_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOGS_btnActionPerformed(evt);
            }
        });
        Attendance_pane.add(LOGS_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 130, 30));

        LEAVE_btn.setText("LEAVE");
        LEAVE_btn.setBackground(new java.awt.Color(255, 255, 255));
        LEAVE_btn.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));
        LEAVE_btn.setFocusPainted(false);
        LEAVE_btn.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        LEAVE_btn.setForeground(new java.awt.Color(4, 8, 114));
        LEAVE_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LEAVE_btnActionPerformed(evt);
            }
        });
        Attendance_pane.add(LEAVE_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 50, 130, 30));

        SCHEDULE_btn.setText("SCHEDULE");
        SCHEDULE_btn.setBackground(new java.awt.Color(255, 255, 255));
        SCHEDULE_btn.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));
        SCHEDULE_btn.setFocusPainted(false);
        SCHEDULE_btn.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        SCHEDULE_btn.setForeground(new java.awt.Color(4, 8, 114));
        SCHEDULE_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SCHEDULE_btnActionPerformed(evt);
            }
        });
        Attendance_pane.add(SCHEDULE_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 50, 130, 30));

        SHIFTS_btn.setText("SHIFTS");
        SHIFTS_btn.setBackground(new java.awt.Color(255, 255, 255));
        SHIFTS_btn.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));
        SHIFTS_btn.setFocusPainted(false);
        SHIFTS_btn.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        SHIFTS_btn.setForeground(new java.awt.Color(4, 8, 114));
        SHIFTS_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SHIFTS_btnActionPerformed(evt);
            }
        });
        Attendance_pane.add(SHIFTS_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 50, 130, 30));

        DEPARTMENTS_btn.setText("DEPARTMENTS");
        DEPARTMENTS_btn.setBackground(new java.awt.Color(255, 255, 255));
        DEPARTMENTS_btn.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));
        DEPARTMENTS_btn.setFocusPainted(false);
        DEPARTMENTS_btn.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        DEPARTMENTS_btn.setForeground(new java.awt.Color(4, 8, 114));
        DEPARTMENTS_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DEPARTMENTS_btnActionPerformed(evt);
            }
        });
        Attendance_pane.add(DEPARTMENTS_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 150, 30));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Attendance_BG.png"))); // NOI18N
        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        Attendance_pane.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1180, 540));

        CARD_LAYOUT.add(Attendance_pane, "card2");

        Reports_pane.setOpaque(false);
        Reports_pane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        weeklyR_btn.setText("WEEKLY");
        weeklyR_btn.setBackground(new java.awt.Color(255, 255, 255));
        weeklyR_btn.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)), null));
        weeklyR_btn.setFocusPainted(false);
        weeklyR_btn.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        weeklyR_btn.setForeground(new java.awt.Color(4, 8, 114));
        weeklyR_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weeklyR_btnActionPerformed(evt);
            }
        });
        Reports_pane.add(weeklyR_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, 130, 30));

        KensinaR_btn.setText("15 DAYS");
        KensinaR_btn.setBackground(new java.awt.Color(255, 255, 255));
        KensinaR_btn.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)), null));
        KensinaR_btn.setFocusPainted(false);
        KensinaR_btn.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        KensinaR_btn.setForeground(new java.awt.Color(4, 8, 114));
        KensinaR_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KensinaR_btnActionPerformed(evt);
            }
        });
        Reports_pane.add(KensinaR_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 130, 30));

        dailyR_btn.setText("DAILY");
        dailyR_btn.setBackground(new java.awt.Color(4, 8, 114));
        dailyR_btn.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));
        dailyR_btn.setFocusPainted(false);
        dailyR_btn.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        dailyR_btn.setForeground(new java.awt.Color(255, 255, 255));
        dailyR_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dailyR_btnActionPerformed(evt);
            }
        });
        Reports_pane.add(dailyR_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 130, 30));

        monthlyR_btn.setText("MONTHLY");
        monthlyR_btn.setBackground(new java.awt.Color(255, 255, 255));
        monthlyR_btn.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)), null));
        monthlyR_btn.setFocusPainted(false);
        monthlyR_btn.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        monthlyR_btn.setForeground(new java.awt.Color(4, 8, 114));
        monthlyR_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthlyR_btnActionPerformed(evt);
            }
        });
        Reports_pane.add(monthlyR_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 130, 30));

        reportsViewer_panel.setOpaque(false);
        reportsViewer_panel.setLayout(new java.awt.CardLayout());

        dailyR_panel.setOpaque(false);
        dailyR_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        DailyReport_table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        DailyReport_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "Morning In", "Morning Out", "Afternoon In", "Afternoon Out"
            }
        ));
        DailyReport_table.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        DailyReport_table.setOpaque(false);
        DailyReport_table.setRowHeight(40);
        DailyReport_table.setSelectionBackground(new java.awt.Color(255, 255, 255));
        DailyReport_table.setSelectionForeground(new java.awt.Color(4, 153, 19));
        DailyReport_table.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(DailyReport_table);

        dailyR_panel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 880, 360));

        SearchSDR_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons/ic_search_24px.png"))); // NOI18N
        SearchSDR_btn.setBackground(new java.awt.Color(4, 8, 114));
        SearchSDR_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        SearchSDR_btn.setBorderPainted(false);
        SearchSDR_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchSDR_btnActionPerformed(evt);
            }
        });
        dailyR_panel.add(SearchSDR_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 20, 40, 30));

        IDnumberSDR_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        IDnumberSDR_in.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        IDnumberSDR_in.setText("ID Number");
        IDnumberSDR_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        IDnumberSDR_in.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                IDnumberSDR_inFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                IDnumberSDR_inFocusLost(evt);
            }
        });
        IDnumberSDR_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDnumberSDR_inActionPerformed(evt);
            }
        });
        dailyR_panel.add(IDnumberSDR_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 240, 30));

        SDRLogName_in.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG NAME" }));
        SDRLogName_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        dailyR_panel.add(SDRLogName_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 340, 30));

        goDailyReport_btn.setText("GO");
        goDailyReport_btn.setBackground(new java.awt.Color(4, 8, 114));
        goDailyReport_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        goDailyReport_btn.setBorderPainted(false);
        goDailyReport_btn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        goDailyReport_btn.setForeground(new java.awt.Color(255, 255, 255));
        goDailyReport_btn.setToolTipText("Print");
        goDailyReport_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goDailyReport_btnActionPerformed(evt);
            }
        });
        dailyR_panel.add(goDailyReport_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 40, 30));

        printDailyReport_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons/ic_local_printshop_24px.png"))); // NOI18N
        printDailyReport_btn.setBackground(new java.awt.Color(4, 8, 114));
        printDailyReport_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        printDailyReport_btn.setBorderPainted(false);
        printDailyReport_btn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        printDailyReport_btn.setForeground(new java.awt.Color(255, 255, 255));
        printDailyReport_btn.setToolTipText("Print");
        printDailyReport_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printDailyReport_btnActionPerformed(evt);
            }
        });
        dailyR_panel.add(printDailyReport_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 50, 30));

        DateSDR_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        DateSDR_in.setDateFormatString("MMMM dd yyyy");
        DateSDR_in.setDoubleBuffered(false);
        DateSDR_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        DateSDR_in.setOpaque(false);
        DateSDR_in.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                DateSDR_inFocusLost(evt);
            }
        });
        DateSDR_in.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DateSDR_inPropertyChange(evt);
            }
        });
        DateSDR_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DateSDR_inKeyPressed(evt);
            }
        });
        dailyR_panel.add(DateSDR_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 200, 30));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GENERAL REPORT", "ABSENT SUMMARY REPORT", "TARDINESS SUMMARY REPORT" }));
        jComboBox1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        dailyR_panel.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 55, 190, 30));

        reportsViewer_panel.add(dailyR_panel, "card1");

        weeklyR_panel.setOpaque(false);
        weeklyR_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        YearWDR_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        YearWDR_in.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        YearWDR_in.setText("2021");
        YearWDR_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        YearWDR_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YearWDR_inActionPerformed(evt);
            }
        });
        YearWDR_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                YearWDR_inKeyTyped(evt);
            }
        });
        weeklyR_panel.add(YearWDR_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 70, 30));

        DateWDR_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        DateWDR_in.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        DateWDR_in.setText("August");
        DateWDR_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        DateWDR_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DateWDR_inActionPerformed(evt);
            }
        });
        DateWDR_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                DateWDR_inKeyTyped(evt);
            }
        });
        weeklyR_panel.add(DateWDR_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 110, 30));

        IDnumberWDR_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        IDnumberWDR_in.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        IDnumberWDR_in.setText("ID NUMBER");
        IDnumberWDR_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        IDnumberWDR_in.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                IDnumberWDR_inFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                IDnumberWDR_inFocusLost(evt);
            }
        });
        IDnumberWDR_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDnumberWDR_inActionPerformed(evt);
            }
        });
        IDnumberWDR_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                IDnumberWDR_inKeyTyped(evt);
            }
        });
        weeklyR_panel.add(IDnumberWDR_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 160, 30));

        RWeekly_container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        WeeklyReport_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID NUMBER", "LOG", "AM", "PM", "AM", "PM", "AM", "PM", "AM", "PM", "AM", "PM", "AM", "PM", "AM", "PM"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        WeeklyReport_table.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        WeeklyReport_table.setOpaque(false);
        WeeklyReport_table.setRowHeight(30);
        WeeklyReport_table.setRowSelectionAllowed(false);
        WeeklyReport_table.setSelectionBackground(new java.awt.Color(255, 255, 255));
        WeeklyReport_table.setSelectionForeground(new java.awt.Color(4, 153, 19));
        WeeklyReport_table.setShowHorizontalLines(false);
        WeeklyReport_table.setShowVerticalLines(false);
        WeeklyReport_table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(WeeklyReport_table);
        if (WeeklyReport_table.getColumnModel().getColumnCount() > 0) {
            WeeklyReport_table.getColumnModel().getColumn(0).setResizable(false);
            WeeklyReport_table.getColumnModel().getColumn(1).setResizable(false);
            WeeklyReport_table.getColumnModel().getColumn(2).setResizable(false);
            WeeklyReport_table.getColumnModel().getColumn(3).setResizable(false);
            WeeklyReport_table.getColumnModel().getColumn(4).setResizable(false);
            WeeklyReport_table.getColumnModel().getColumn(5).setResizable(false);
            WeeklyReport_table.getColumnModel().getColumn(6).setResizable(false);
            WeeklyReport_table.getColumnModel().getColumn(7).setResizable(false);
            WeeklyReport_table.getColumnModel().getColumn(8).setResizable(false);
            WeeklyReport_table.getColumnModel().getColumn(9).setResizable(false);
            WeeklyReport_table.getColumnModel().getColumn(10).setResizable(false);
            WeeklyReport_table.getColumnModel().getColumn(11).setResizable(false);
            WeeklyReport_table.getColumnModel().getColumn(12).setResizable(false);
            WeeklyReport_table.getColumnModel().getColumn(13).setResizable(false);
            WeeklyReport_table.getColumnModel().getColumn(14).setResizable(false);
            WeeklyReport_table.getColumnModel().getColumn(15).setResizable(false);
        }

        RWeekly_container.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 870, 250));

        two.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        two.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        two.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        RWeekly_container.add(two, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 0, 105, 30));

        four.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        four.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        four.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        RWeekly_container.add(four, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 0, 105, 30));

        three.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        three.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        three.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        RWeekly_container.add(three, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 0, 105, 30));

        seven.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seven.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        seven.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        RWeekly_container.add(seven, new org.netbeans.lib.awtextra.AbsoluteConstraints(755, 0, 105, 30));

        six.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        six.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        six.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        RWeekly_container.add(six, new org.netbeans.lib.awtextra.AbsoluteConstraints(651, 0, 105, 30));

        five.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        five.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        five.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        RWeekly_container.add(five, new org.netbeans.lib.awtextra.AbsoluteConstraints(547, 0, 105, 30));

        one.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        one.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        one.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        RWeekly_container.add(one, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 0, 105, 30));

        Date_lbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Date_lbl1.setText("DATE");
        Date_lbl1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Date_lbl1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        RWeekly_container.add(Date_lbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 132, 30));

        weeklyR_panel.add(RWeekly_container, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 870, 280));

        seven2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seven2.setText("Saturday");
        seven2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        seven2.setForeground(new java.awt.Color(4, 8, 114));
        weeklyR_panel.add(seven2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 130, 108, 25));

        seven3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seven3.setText("Friday");
        seven3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        seven3.setForeground(new java.awt.Color(4, 8, 114));
        weeklyR_panel.add(seven3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 130, 100, 25));

        seven4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seven4.setText("Thursday");
        seven4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        seven4.setForeground(new java.awt.Color(4, 8, 114));
        weeklyR_panel.add(seven4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, 110, 25));

        seven5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seven5.setText("Wednesday");
        seven5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        seven5.setForeground(new java.awt.Color(4, 8, 114));
        weeklyR_panel.add(seven5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, 100, 25));

        seven6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seven6.setText("Tuesday");
        seven6.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        seven6.setForeground(new java.awt.Color(4, 8, 114));
        weeklyR_panel.add(seven6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, 110, 25));

        seven7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seven7.setText("Monday");
        seven7.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        seven7.setForeground(new java.awt.Color(4, 8, 114));
        weeklyR_panel.add(seven7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 100, 25));

        seven8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seven8.setText("Sunday");
        seven8.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        seven8.setForeground(new java.awt.Color(245, 90, 57));
        weeklyR_panel.add(seven8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 110, 25));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " SELECT LOGS TO FETCH EACH DATE ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 3, 14), new java.awt.Color(4, 153, 19))); // NOI18N
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        seven_log.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG NAME" }));
        seven_log.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jPanel9.add(seven_log, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 30, 108, 30));

        six_log.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG NAME" }));
        six_log.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jPanel9.add(six_log, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 30, 101, 30));

        five_log.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG NAME" }));
        five_log.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jPanel9.add(five_log, new org.netbeans.lib.awtextra.AbsoluteConstraints(564, 30, 106, 30));

        four_log.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG NAME" }));
        four_log.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jPanel9.add(four_log, new org.netbeans.lib.awtextra.AbsoluteConstraints(459, 30, 106, 30));

        three_log.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG NAME" }));
        three_log.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jPanel9.add(three_log, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 108, 30));

        two_log.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG NAME" }));
        two_log.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jPanel9.add(two_log, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 30, 105, 30));

        one_log.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG NAME" }));
        one_log.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jPanel9.add(one_log, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 105, 30));

        GoWDR_btn.setText("GO");
        GoWDR_btn.setBackground(new java.awt.Color(4, 8, 114));
        GoWDR_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        GoWDR_btn.setBorderPainted(false);
        GoWDR_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        GoWDR_btn.setForeground(new java.awt.Color(255, 255, 255));
        GoWDR_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GoWDR_btnActionPerformed(evt);
            }
        });
        jPanel9.add(GoWDR_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 100, 30));

        weeklyR_panel.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 890, 70));

        SearchWDR_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons/ic_search_24px.png"))); // NOI18N
        SearchWDR_btn.setText("SEARCH");
        SearchWDR_btn.setBackground(new java.awt.Color(4, 8, 114));
        SearchWDR_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        SearchWDR_btn.setBorderPainted(false);
        SearchWDR_btn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        SearchWDR_btn.setForeground(new java.awt.Color(255, 255, 255));
        SearchWDR_btn.setMargin(new java.awt.Insets(2, 25, 2, 14));
        SearchWDR_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchWDR_btnActionPerformed(evt);
            }
        });
        weeklyR_panel.add(SearchWDR_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 100, 30));

        WeeklyReportPrint.setText("PRINT");
        WeeklyReportPrint.setBackground(new java.awt.Color(4, 8, 114));
        WeeklyReportPrint.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        WeeklyReportPrint.setBorderPainted(false);
        WeeklyReportPrint.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        WeeklyReportPrint.setForeground(new java.awt.Color(255, 255, 255));
        WeeklyReportPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WeeklyReportPrintActionPerformed(evt);
            }
        });
        weeklyR_panel.add(WeeklyReportPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 70, 30));

        prev_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons/ic_chevron_left_24px.png"))); // NOI18N
        prev_btn.setBackground(new java.awt.Color(4, 8, 114));
        prev_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        prev_btn.setBorderPainted(false);
        prev_btn.setContentAreaFilled(false);
        prev_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prev_btnActionPerformed(evt);
            }
        });
        weeklyR_panel.add(prev_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, -1, 30));

        weekNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        weekNum.setText("WEEK");
        weekNum.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        weekNum.setForeground(new java.awt.Color(4, 153, 19));
        weeklyR_panel.add(weekNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 80, 30));

        fwd_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons/ic_chevron_right_24px.png"))); // NOI18N
        fwd_btn.setBackground(new java.awt.Color(4, 8, 114));
        fwd_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        fwd_btn.setBorderPainted(false);
        fwd_btn.setContentAreaFilled(false);
        fwd_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fwd_btnActionPerformed(evt);
            }
        });
        weeklyR_panel.add(fwd_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 20, 20, 30));

        PLcomboBox.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        PLcomboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GENERAL REPORT", "ABSENT SUMMARY REPORT", "LATE SUMMARY REPORT" }));
        weeklyR_panel.add(PLcomboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 200, 30));

        reportsViewer_panel.add(weeklyR_panel, "card2");

        KensinaR_panel.setOpaque(false);
        KensinaR_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        halfMonthSearch_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons/ic_local_printshop_24px.png"))); // NOI18N
        halfMonthSearch_btn.setBackground(new java.awt.Color(4, 8, 114));
        halfMonthSearch_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        halfMonthSearch_btn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        halfMonthSearch_btn.setForeground(new java.awt.Color(255, 255, 255));
        halfMonthSearch_btn.setToolTipText("Print");
        halfMonthSearch_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                halfMonthSearch_btnActionPerformed(evt);
            }
        });
        KensinaR_panel.add(halfMonthSearch_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 50, 30));

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("NAME");
        jLabel22.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(4, 153, 19));
        KensinaR_panel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 60, -1));

        searchByLogDTR_btn.setText("VIEW");
        searchByLogDTR_btn.setBackground(new java.awt.Color(4, 8, 114));
        searchByLogDTR_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        searchByLogDTR_btn.setBorderPainted(false);
        searchByLogDTR_btn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        searchByLogDTR_btn.setForeground(new java.awt.Color(255, 255, 255));
        searchByLogDTR_btn.setToolTipText("View the records of attendance according to log name selected");
        searchByLogDTR_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByLogDTR_btnActionPerformed(evt);
            }
        });
        KensinaR_panel.add(searchByLogDTR_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, 80, 30));

        searchDTR_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons/ic_search_24px.png"))); // NOI18N
        searchDTR_btn.setBackground(new java.awt.Color(4, 8, 114));
        searchDTR_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        searchDTR_btn.setBorderPainted(false);
        searchDTR_btn.setToolTipText("Search");
        searchDTR_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchDTR_btnActionPerformed(evt);
            }
        });
        KensinaR_panel.add(searchDTR_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, 40, 30));

        setMDR_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        setMDR_in.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        setMDR_in.setText("01");
        setMDR_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        setMDR_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setMDR_inActionPerformed(evt);
            }
        });
        setMDR_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                setMDR_inKeyTyped(evt);
            }
        });
        KensinaR_panel.add(setMDR_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 48, 30));

        jLabel23.setText("ID NUMBER");
        jLabel23.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(4, 153, 19));
        KensinaR_panel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 80, 20));

        idMDR_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        idMDR_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        idMDR_in.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                idMDR_inFocusGained(evt);
            }
        });
        idMDR_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idMDR_inActionPerformed(evt);
            }
        });
        idMDR_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idMDR_inKeyTyped(evt);
            }
        });
        KensinaR_panel.add(idMDR_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 190, 30));

        nameMDR_in.setEditable(false);
        nameMDR_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        nameMDR_in.setAutoscrolls(false);
        nameMDR_in.setBackground(new java.awt.Color(255, 255, 255));
        nameMDR_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        nameMDR_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameMDR_inActionPerformed(evt);
            }
        });
        KensinaR_panel.add(nameMDR_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 240, 30));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setLayout(new java.awt.BorderLayout());

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));

        DTRtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "DATE", "MORNING IN", "MORNING OUT", "AFTERNOON IN", "AFTERNOON OUT", "TOTAL HOURS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DTRtable.setRowHeight(22);
        DTRtable.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(DTRtable);
        if (DTRtable.getColumnModel().getColumnCount() > 0) {
            DTRtable.getColumnModel().getColumn(0).setResizable(false);
            DTRtable.getColumnModel().getColumn(1).setResizable(false);
            DTRtable.getColumnModel().getColumn(2).setResizable(false);
            DTRtable.getColumnModel().getColumn(3).setResizable(false);
            DTRtable.getColumnModel().getColumn(4).setResizable(false);
            DTRtable.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel20.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        KensinaR_panel.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 680, 380));

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("SET 15");
        jLabel38.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(4, 153, 19));
        KensinaR_panel.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 50, -1));

        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel62.setText("MONTH - YEAR");
        jLabel62.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(4, 153, 19));
        KensinaR_panel.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 100, -1));

        LogPerDates.setBackground(new java.awt.Color(255, 255, 255));
        LogPerDates.setLayout(new java.awt.GridLayout(16, 1));

        LogDate1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        LogPerDates.add(LogDate1);
        LogDate1.getAccessibleContext().setAccessibleName("Log1");

        LogDate2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        LogPerDates.add(LogDate2);

        LogDate3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        LogPerDates.add(LogDate3);

        LogDate4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        LogPerDates.add(LogDate4);

        LogDate5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        LogPerDates.add(LogDate5);

        LogDate6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        LogPerDates.add(LogDate6);

        LogDate7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        LogPerDates.add(LogDate7);

        LogDate8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate8.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        LogPerDates.add(LogDate8);

        LogDate9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate9.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        LogPerDates.add(LogDate9);

        LogDate10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate10.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        LogPerDates.add(LogDate10);

        LogDate11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate11.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        LogPerDates.add(LogDate11);

        LogDate12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate12.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        LogPerDates.add(LogDate12);

        LogDate13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate13.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        LogPerDates.add(LogDate13);

        LogDate14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate14.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        LogPerDates.add(LogDate14);

        LogDate15.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate15.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        LogPerDates.add(LogDate15);

        LogDate16.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate16.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        LogPerDates.add(LogDate16);

        KensinaR_panel.add(LogPerDates, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 80, 180, 360));

        monthMDR_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        monthMDR_in.setDateFormatString("MMMM-yyyy");
        monthMDR_in.setDoubleBuffered(false);
        monthMDR_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        monthMDR_in.setOpaque(false);
        KensinaR_panel.add(monthMDR_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 170, 30));

        reportsViewer_panel.add(KensinaR_panel, "card3");

        monthlyR_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MonthlyDTRviewer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane10.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane10.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane10.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        DTRtableMM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "DATE", "MORNING IN", "MORNING OUT", "AFTERNOON IN", "AFTERNOON OUT", "UNDERTIME HRS", "UNDERTIME MIN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DTRtableMM.setRowHeight(22);
        DTRtableMM.getTableHeader().setReorderingAllowed(false);
        jScrollPane10.setViewportView(DTRtableMM);
        if (DTRtableMM.getColumnModel().getColumnCount() > 0) {
            DTRtableMM.getColumnModel().getColumn(0).setResizable(false);
            DTRtableMM.getColumnModel().getColumn(1).setResizable(false);
            DTRtableMM.getColumnModel().getColumn(2).setResizable(false);
            DTRtableMM.getColumnModel().getColumn(3).setResizable(false);
            DTRtableMM.getColumnModel().getColumn(4).setResizable(false);
            DTRtableMM.getColumnModel().getColumn(5).setResizable(false);
            DTRtableMM.getColumnModel().getColumn(6).setResizable(false);
        }

        MonthlyDTRviewer.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 741, 720));

        LogDate17.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate17.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate17, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 37, 150, -1));

        LogDate18.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate18.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate18, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 58, 150, -1));

        LogDate19.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate19.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate19, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 80, 150, -1));

        LogDate20.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate20.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate20, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 102, 150, -1));

        LogDate21.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate21.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate21, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 125, 150, -1));

        LogDate22.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate22.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate22, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 147, 150, -1));

        LogDate23.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate23.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate23, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 168, 150, -1));

        LogDate24.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate24.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate24, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 190, 150, -1));

        LogDate25.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate25.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate25, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 212, 150, -1));

        LogDate26.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate26.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate26, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 235, 150, -1));

        LogDate27.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate27.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        LogDate27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogDate27ActionPerformed(evt);
            }
        });
        MonthlyDTRviewer.add(LogDate27, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 278, 150, -1));

        LogDate28.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate28.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate28, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 300, 150, -1));

        LogDate29.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate29.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate29, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 256, 150, -1));

        LogDate30.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate30.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate30, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 322, 150, -1));

        LogDate31.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate31.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate31, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 345, 150, -1));

        LogDate32.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate32.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate32, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 368, 150, -1));

        LogDate63.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate63.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate63, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 390, 150, -1));

        LogDate64.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate64.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate64, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 411, 150, -1));

        LogDate65.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate65.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate65, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 432, 150, -1));

        LogDate66.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate66.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate66, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 454, 150, -1));

        LogDate67.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate67.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate67, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 475, 150, -1));

        LogDate68.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate68.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate68, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 498, 150, -1));

        LogDate69.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate69.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate69, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 520, 150, -1));

        LogDate70.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate70.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate70, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 542, 150, -1));

        LogDate71.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate71.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate71, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 565, 150, -1));

        LogDate72.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate72.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate72, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 586, 150, -1));

        LogDate73.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate73.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        LogDate73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogDate73ActionPerformed(evt);
            }
        });
        MonthlyDTRviewer.add(LogDate73, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 630, 150, -1));

        LogDate74.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate74.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate74, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 652, 150, -1));

        LogDate75.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate75.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate75, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 608, 150, -1));

        LogDate76.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate76.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate76, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 674, 150, -1));

        LogDate77.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOG" }));
        LogDate77.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MonthlyDTRviewer.add(LogDate77, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 695, 150, -1));

        searchByLogMM_btn.setText("VIEW BY LOG");
        searchByLogMM_btn.setBackground(new java.awt.Color(4, 8, 114));
        searchByLogMM_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        searchByLogMM_btn.setBorderPainted(false);
        searchByLogMM_btn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        searchByLogMM_btn.setForeground(new java.awt.Color(255, 255, 255));
        searchByLogMM_btn.setToolTipText("View the records of attendance according to log name selected");
        searchByLogMM_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByLogMM_btnActionPerformed(evt);
            }
        });
        MonthlyDTRviewer.add(searchByLogMM_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 9, 150, 23));

        scrollPane1.add(MonthlyDTRviewer);

        monthlyR_panel.add(scrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 53, 890, 387));

        MonthPrint_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons/ic_local_printshop_24px.png"))); // NOI18N
        MonthPrint_btn.setBackground(new java.awt.Color(4, 8, 114));
        MonthPrint_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        MonthPrint_btn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        MonthPrint_btn.setForeground(new java.awt.Color(255, 255, 255));
        MonthPrint_btn.setToolTipText("Print");
        MonthPrint_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MonthPrint_btnActionPerformed(evt);
            }
        });
        monthlyR_panel.add(MonthPrint_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 50, 30));

        idNumberMM_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        idNumberMM_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        idNumberMM_in.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                idNumberMM_inFocusGained(evt);
            }
        });
        idNumberMM_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idNumberMM_inActionPerformed(evt);
            }
        });
        idNumberMM_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idNumberMM_inKeyTyped(evt);
            }
        });
        monthlyR_panel.add(idNumberMM_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 190, 30));

        jLabel64.setText("ID NUMBER");
        jLabel64.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(4, 153, 19));
        monthlyR_panel.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 80, 20));

        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setText("NAME");
        jLabel65.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(4, 153, 19));
        monthlyR_panel.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 60, -1));

        nameMM_in.setEditable(false);
        nameMM_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        nameMM_in.setAutoscrolls(false);
        nameMM_in.setBackground(new java.awt.Color(255, 255, 255));
        nameMM_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        nameMM_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameMM_inActionPerformed(evt);
            }
        });
        monthlyR_panel.add(nameMM_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 390, 30));

        monthMM_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        monthMM_in.setDateFormatString("MMMM-yyyy");
        monthMM_in.setDoubleBuffered(false);
        monthMM_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        monthMM_in.setOpaque(false);
        monthlyR_panel.add(monthMM_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, 170, 30));

        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText("MONTH - YEAR");
        jLabel66.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(4, 153, 19));
        monthlyR_panel.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, 100, -1));

        searchMM_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons/ic_search_24px.png"))); // NOI18N
        searchMM_btn.setBackground(new java.awt.Color(4, 8, 114));
        searchMM_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        searchMM_btn.setBorderPainted(false);
        searchMM_btn.setToolTipText("Search");
        searchMM_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMM_btnActionPerformed(evt);
            }
        });
        monthlyR_panel.add(searchMM_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 20, 40, 30));

        reportsViewer_panel.add(monthlyR_panel, "card4");

        Reports_pane.add(reportsViewer_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 890, 450));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Reports_BG.png"))); // NOI18N
        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Reports_pane.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 10, 1190, 540));

        CARD_LAYOUT.add(Reports_pane, "card3");

        Personnels_pane.setOpaque(false);
        Personnels_pane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        printPersonnels.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons/ic_local_printshop_24px.png"))); // NOI18N
        printPersonnels.setBackground(new java.awt.Color(4, 8, 114));
        printPersonnels.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        printPersonnels.setBorderPainted(false);
        printPersonnels.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        printPersonnels.setForeground(new java.awt.Color(255, 255, 255));
        printPersonnels.setToolTipText("Print");
        printPersonnels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printPersonnelsActionPerformed(evt);
            }
        });
        Personnels_pane.add(printPersonnels, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 40, 50, 30));

        PersonnelSearch_in.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        PersonnelSearch_in.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        PersonnelSearch_in.setText("All ID Number");
        PersonnelSearch_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        PersonnelSearch_in.setToolTipText("");
        PersonnelSearch_in.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PersonnelSearch_inFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PersonnelSearch_inFocusLost(evt);
            }
        });
        PersonnelSearch_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PersonnelSearch_inKeyTyped(evt);
            }
        });
        Personnels_pane.add(PersonnelSearch_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 40, 210, 30));

        jLabel9.setText("Barangay Kahaponan Administration Personnel's ");
        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(4, 8, 114));
        Personnels_pane.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, -1, 30));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " PERSONNELS INFORMATION ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 14))); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setText("AGE");
        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(4, 153, 19));
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 100, 32, 30));

        Age_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Age_in.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Age_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        Age_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Age_inKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Age_inKeyTyped(evt);
            }
        });
        jPanel2.add(Age_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 50, 30));

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(4, 153, 19));
        jLabel14.setText("POSITION");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 80, 30));

        MI_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        MI_in.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        MI_in.setText("M.I.");
        MI_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        MI_in.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                MI_inFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                MI_inFocusLost(evt);
            }
        });
        MI_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MI_inKeyTyped(evt);
            }
        });
        jPanel2.add(MI_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 50, 30));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(4, 153, 19));
        jLabel15.setText("NAME");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 80, 30));

        jLabel17.setText("GENDER");
        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(4, 153, 19));
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 80, 30));

        ID_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ID_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        ID_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ID_inActionPerformed(evt);
            }
        });
        ID_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ID_inKeyTyped(evt);
            }
        });
        jPanel2.add(ID_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 400, 30));

        Lname_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Lname_in.setText("Last Name");
        Lname_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        Lname_in.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Lname_inFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Lname_inFocusLost(evt);
            }
        });
        Lname_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Lname_inKeyTyped(evt);
            }
        });
        jPanel2.add(Lname_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 160, 30));

        Fname_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Fname_in.setText("First Name");
        Fname_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        Fname_in.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Fname_inFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Fname_inFocusLost(evt);
            }
        });
        Fname_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Fname_inKeyTyped(evt);
            }
        });
        jPanel2.add(Fname_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 170, 30));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(4, 153, 19));
        jLabel18.setText("ID NO.");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 80, 30));

        jLabel21.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(4, 153, 19));
        jLabel21.setText("HIRE DATE");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 80, 30));

        jLabel24.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(4, 153, 19));
        jLabel24.setText("DEPARTMENT");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 80, 30));

        jLabel12.setText("PRIVILEGE");
        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(4, 153, 19));
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 90, 30));

        HireDate_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        HireDate_in.setDateFormatString("MMMM dd yyyy");
        HireDate_in.setDoubleBuffered(false);
        HireDate_in.setFocusable(false);
        HireDate_in.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        HireDate_in.setOpaque(false);
        jPanel2.add(HireDate_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 100, 155, 30));

        Preveledge_in.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Level 1", "Level 2", "Level 3" }));
        Preveledge_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Preveledge_in.setOpaque(false);
        Preveledge_in.setToolTipText("");
        Preveledge_in.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Preveledge_inItemStateChanged(evt);
            }
        });
        jPanel2.add(Preveledge_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 140, 155, 30));

        Department_in.setMaximumRowCount(5);
        Department_in.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Department" }));
        Department_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Department_in.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Department_inItemStateChanged(evt);
            }
        });
        Department_in.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Department_inFocusLost(evt);
            }
        });
        Department_in.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Department_inMouseClicked(evt);
            }
        });
        Department_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Department_inKeyPressed(evt);
            }
        });
        jPanel2.add(Department_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 400, 30));

        Gender_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Gender_in.setMaximumRowCount(3);
        Gender_in.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        Gender_in.setOpaque(false);
        jPanel2.add(Gender_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 75, 30));

        Position_in.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CHOOSE POSITION" }));
        Position_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Position_in.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Position_inItemStateChanged(evt);
            }
        });
        jPanel2.add(Position_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 160, 30));

        Personnels_pane.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 500, 220));

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Personnels_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NAME", "GENDER", "AGE", "POSITION", "BT NAME", "BT ADDRESS", "PASSWORD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Personnels_table.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Personnels_table.setRowHeight(30);
        Personnels_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Personnels_tableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(Personnels_table);

        Personnels_pane.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 890, 210));

        PersonnelSearch_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons/ic_search_24px.png"))); // NOI18N
        PersonnelSearch_btn.setBackground(new java.awt.Color(4, 8, 114));
        PersonnelSearch_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        PersonnelSearch_btn.setBorderPainted(false);
        PersonnelSearch_btn.setToolTipText("Search personnel by ID Number");
        PersonnelSearch_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PersonnelSearch_btnActionPerformed(evt);
            }
        });
        Personnels_pane.add(PersonnelSearch_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 40, 40, 30));

        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.CardLayout());

        BTreg_panel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("")), " BLUETOOTH DEVICE REGISTRATION ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 14))); // NOI18N
        BTreg_panel.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        BTreg_panel.setOpaque(false);
        BTreg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BTname.setMaximumRowCount(5);
        BTname.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DEVICE NAME" }));
        BTname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        BTname.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BTname.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BTnameItemStateChanged(evt);
            }
        });
        BTname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTnameActionPerformed(evt);
            }
        });
        BTreg_panel.add(BTname, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 220, 30));

        jLabel16.setText("BT NAME");
        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(4, 153, 19));
        BTreg_panel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 90, 30));

        jLabel10.setText("BT ADDRESS");
        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(4, 153, 19));
        BTreg_panel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 80, 30));

        BTaddress.setEditable(false);
        BTaddress.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BTaddress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        BTreg_panel.add(BTaddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 270, 30));

        BTscan_btn.setText("SCAN");
        BTscan_btn.setBackground(new java.awt.Color(4, 8, 114));
        BTscan_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        BTscan_btn.setBorderPainted(false);
        BTscan_btn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        BTscan_btn.setForeground(new java.awt.Color(255, 255, 255));
        BTscan_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTscan_btnActionPerformed(evt);
            }
        });
        BTreg_panel.add(BTscan_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 50, 30));

        jLabel11.setText("PASSWORD");
        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(4, 153, 19));
        BTreg_panel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 80, 30));

        PersonnelPassword_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        PersonnelPassword_in.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PersonnelPassword_in.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                PersonnelPassword_inFocusLost(evt);
            }
        });
        BTreg_panel.add(PersonnelPassword_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 270, 30));

        jPanel6.add(BTreg_panel, "card2");

        Personnels_pane.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 80, 380, 140));

        Add_btn.setText("ADD");
        Add_btn.setBackground(new java.awt.Color(4, 153, 19));
        Add_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        Add_btn.setBorderPainted(false);
        Add_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        Add_btn.setForeground(new java.awt.Color(255, 255, 255));
        Add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_btnActionPerformed(evt);
            }
        });
        Personnels_pane.add(Add_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 266, 110, 30));

        Delete_btn.setText("DELETE");
        Delete_btn.setBackground(new java.awt.Color(254, 90, 57));
        Delete_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        Delete_btn.setBorderPainted(false);
        Delete_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        Delete_btn.setForeground(new java.awt.Color(255, 255, 255));
        Delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_btnActionPerformed(evt);
            }
        });
        Personnels_pane.add(Delete_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 266, 110, 30));

        Update_btn.setText("UPDATE");
        Update_btn.setBackground(new java.awt.Color(252, 226, 5));
        Update_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 8, 114)));
        Update_btn.setBorderPainted(false);
        Update_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        Update_btn.setForeground(new java.awt.Color(255, 255, 255));
        Update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_btnActionPerformed(evt);
            }
        });
        Personnels_pane.add(Update_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 266, 120, 30));

        prompt_Message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prompt_Message.setText("* PLEASE FILL UP ALL THE FIELDS TO REGISTER *");
        prompt_Message.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        prompt_Message.setForeground(new java.awt.Color(254, 90, 57));
        Personnels_pane.add(prompt_Message, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 230, 380, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Personnels_BG.png"))); // NOI18N
        Personnels_pane.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 10, 1190, 540));

        CARD_LAYOUT.add(Personnels_pane, "card4");

        About_pane.setOpaque(false);
        About_pane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel71.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(4, 8, 114));
        jLabel71.setText("web development, database management, and a variety of other areas. \n");
        jLabel71.setToolTipText("");
        About_pane.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 270, 490, 60));

        jLabel80.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(4, 8, 114));
        jLabel80.setText("The name came from the collective ideas of its members.\n");
        jLabel80.setToolTipText("");
        About_pane.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 300, 410, 50));

        jLabel70.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(4, 153, 19));
        jLabel70.setText("SMARTLOG");
        jLabel70.setToolTipText("");
        About_pane.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, -1, 60));

        jLabel75.setFont(new java.awt.Font("Chaparral Pro Light", 1, 18)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(4, 8, 114));
        jLabel75.setText("\"Think, create, innovate\"\n");
        jLabel75.setToolTipText("");
        About_pane.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 230, 210, 30));

        jLabel77.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(4, 8, 114));
        jLabel77.setText("Developed by Code Line team.\n");
        jLabel77.setToolTipText("");
        About_pane.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 490, 30));

        jLabel76.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(4, 8, 114));
        jLabel76.setText("A bluetooth based attendance monitoring system.\n");
        jLabel76.setToolTipText("");
        About_pane.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 490, 30));

        jLabel74.setForeground(new java.awt.Color(4, 153, 19));
        jLabel74.setText("_______________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
        About_pane.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 210, -1));

        jLabel73.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(4, 8, 114));
        jLabel73.setText("CODE LINE");
        jLabel73.setToolTipText("");
        About_pane.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, -1, 60));

        jLabel81.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(4, 8, 114));
        jLabel81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/LOGO.png"))); // NOI18N
        jLabel81.setText("smartlog@gmail.com\n");
        jLabel81.setToolTipText("");
        jLabel81.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        About_pane.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 410, 340, 54));

        jLabel20.setForeground(new java.awt.Color(4, 153, 19));
        jLabel20.setText("_______________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
        About_pane.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 240, 420, -1));

        jLabel69.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(4, 8, 114));
        jLabel69.setText("codelinesupport@gmail.com\n");
        jLabel69.setToolTipText("");
        About_pane.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 480, 200, 30));

        jLabel72.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(4, 8, 114));
        jLabel72.setText("is made up of skilled IT students with expertise in programming, web design, \n");
        jLabel72.setToolTipText("");
        About_pane.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 240, 510, 70));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Vector 2.jpg"))); // NOI18N
        jLabel19.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        About_pane.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 580, 300));

        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/vector 1.jpg"))); // NOI18N
        jLabel78.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        About_pane.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 30, 160, 220));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/About_BG.png"))); // NOI18N
        About_pane.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 10, 1190, 540));

        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/vector 1.jpg"))); // NOI18N
        jLabel79.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        About_pane.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 30, 230, 230));

        jLabel82.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(4, 8, 114));
        jLabel82.setText("smartlog@gmail.com\n");
        jLabel82.setToolTipText("");
        About_pane.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 460, 200, 30));

        CARD_LAYOUT.add(About_pane, "card5");

        jPanel1.add(CARD_LAYOUT, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 115, 1180, 550));

        SideBar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SideBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Side_bar.png"))); // NOI18N
        SideBar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(SideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 550));

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/LOGO.png"))); // NOI18N
        jPanel1.add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 25, 500, 90));

        LogOut_btn.setText("LOG OUT");
        LogOut_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 153, 19)));
        LogOut_btn.setContentAreaFilled(false);
        LogOut_btn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        LogOut_btn.setForeground(new java.awt.Color(4, 153, 19));
        LogOut_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOut_btnActionPerformed(evt);
            }
        });
        jPanel1.add(LogOut_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 20, 110, 30));

        BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/BG_image.png"))); // NOI18N
        BG.setText("jLabel4");
        jPanel1.add(BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1205, 678));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
            
    public void ShowTime() {
        new Timer(0, (ActionEvent e) -> {
            Date d = new Date();
            SimpleDateFormat t = new SimpleDateFormat("hh:mm a");
            SimpleDateFormat dte = new SimpleDateFormat("MMMM dd, yyyy");
            SimpleDateFormat day = new SimpleDateFormat("EEEE");
            SimpleDateFormat dateAlone = new SimpleDateFormat("MMMM dd yyyy");
            Datenow = dateAlone.format(d);
            Time_label.setText(t.format(d));
            Day_label.setText(day.format(d));
            Date_label.setText(dte.format(d));
            
        }).start();
    }
    
    public void currentLogDetails_display(){
        new Timer(10000, (ActionEvent e) -> {
            try {
                if(Dashboard_pane.isVisible()){
                    Dashboard_pane.setVisible(false);
                    Dashboard_pane.setVisible(true);
                }
                dataOverview_display();
                openLogs();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }
    
    public void openLogs() throws ClassNotFoundException, SQLException{
        String Open[] = DQ.getCurrentUse_OpenLog();
        String Log = DQ.fetch("Select * from dailylogschedule where Status='IN-USE'", "schName");
        if("".equals(Log)){currentAttendance_label.setText("NO ATTENDANCE SET");}
        else{currentAttendance_label.setText(Log);}
        
        String cut[] = Open[1].split("\\s");
        LocalTime convert = LocalTime.parse(cut[0]);
        if("PM".equalsIgnoreCase(cut[1])){convert = convert.minusHours(12);}

        Open[1] = String.valueOf(convert);
        
        if(Open[0].equals("Mi")){
            setOpen(Min_label,"MORNING LOGIN OPEN");
            setClose(Mout_label,"MORNING LOGOUT LOGIN");
            setClose(Ain_label,"AFTERNOON LOGIN");
            setClose(Aout_label,"AFTERNOON LOGOUT");
            currentLogType_label.setText("MORNING LOGIN");
            TimeInOut_label.setText(Open[1]);
        }else if(Open[0].equals("Mi Mo")){
            setOpen(Min_label,"MORNING LOGIN OPEN");
            setOpen(Mout_label,"MORNING LOGOUT OPEN");
            setClose(Ain_label,"AFTERNOON LOGIN");
            setClose(Aout_label,"AFTERNOON LOGOUT");
            currentLogType_label.setText("MORNING LOGIN");
            TimeInOut_label.setText(Open[1]);
        }else if(Open[0].equals("Mo")){
            setClose(Min_label,"MORNING LOGIN");
            setOpen(Mout_label,"MORNING LOGOUT OPEN");
            setClose(Ain_label,"AFTERNOON LOGIN");
            setClose(Aout_label,"AFTERNOON LOGOUT");
            currentLogType_label.setText("MORNING LOGOUT");
            TimeInOut_label.setText(Open[1]);
        }else if(Open[0].equals("Mo Ai")){
            setClose(Min_label,"MORNING LOGIN");
            setOpen(Mout_label,"MORNING LOGOUT OPEN");
            setOpen(Ain_label,"AFTERNOON LOGIN OPEN");
            setClose(Aout_label,"AFTERNOON LOGOUT");
            currentLogType_label.setText("AFTERNOON LOGIN");
            TimeInOut_label.setText(Open[1]);
        }else if(Open[0].equals("Ai")){
            setClose(Min_label,"MORNING LOGIN");
            setClose(Mout_label,"MORNING LOGOUT");
            setOpen(Ain_label,"AFTERNOON LOGIN OPEN");
            setClose(Aout_label,"AFTERNOON LOGOUT");
            currentLogType_label.setText("AFTERNOON LOGIN");
            TimeInOut_label.setText(Open[1]);
        }else if(Open[0].equals("Ai Ao")){
            setClose(Min_label,"MORNING LOGIN");
            setClose(Mout_label,"MORNING LOGOUT");
            setOpen(Ain_label,"AFTERNOON LOGIN OPEN");
            setOpen(Aout_label,"AFTERNOON LOGOUT OPEN");
            currentLogType_label.setText("AFTERNOON LOGIN");
            TimeInOut_label.setText(Open[1]);
        }else if(Open[0].equals("Ao")){
            setClose(Min_label,"MORNING LOGIN");
            setClose(Mout_label,"MORNING LOGOUT");
            setClose(Ain_label,"AFTERNOON LOGIN");
            setOpen(Aout_label,"AFTERNOON LOGOUT OPEN");
            currentLogType_label.setText("AFTERNOON LOGOUT");
            TimeInOut_label.setText(Open[1]);
        }else{
            setClose(Min_label,"MORNING LOGIN");
            setClose(Mout_label,"MORNING LOGOUT");
            setClose(Ain_label,"AFTERNOON LOGIN");
            setClose(Aout_label,"AFTERNOON LOGOUT");
            currentLogType_label.setText("NOT SET");
            TimeInOut_label.setText("00:00");
        }
    }
    
    public void setOpen(JLabel open, String Text){
        open.setBackground(new Color(4,8,114));
        open.setForeground(new Color(255,255,255));
        open.setText(Text);
    }
    
    public void setClose(JLabel close, String Text){
        close.setBackground(new Color(255,255,255));
        close.setForeground(new Color(4,8,114));
        close.setText(Text);
    }
    
    public void dataOverview_display() throws ClassNotFoundException, SQLException{
        NumberOfPersonnel = DQ.Counting("SELECT * FROM smartlogdb.personnels");
        NumberOfOfficials = DQ.Counting("SELECT * FROM departments Where Department = 'Barangay Official'");
        int NumberOfStaff = NumberOfPersonnel - NumberOfOfficials;
        
        NumberOfStaff_label.setText(String.valueOf(NumberOfStaff));
        NumberOfOfficials_label.setText(String.valueOf(NumberOfOfficials));
    }
    
    public void getCurrentUser() throws ClassNotFoundException, SQLException{
        System.out.println("Getting Current User");
        String[] data = DQ.getCurrentUser();
        User_name.setText(data[3]);
        //System.out.println("name: "+data[3]);
        String Position = DQ.fetch("departments", " Where IDnumber = '"+data[6]+"'", "Position");
        User_Position.setText(Position);
        //System.out.println("Position: "+Position);
        CurrentUser = data[5];
        //System.out.println("Account ID: "+data[5]);
        CurrentUser_IDnum = data[6];
        //System.out.println("ID Number: "+data[6]);
    }
    
    public void FetchDepartments() throws ClassNotFoundException, SQLException{
        String[] department = DQ.getDepartments();
        Deptmodel.removeAllElements();
        for(int i=0;i<department.length;i++){
            Deptmodel.addElement(department[i]);
        }
        Department_in.setModel(Deptmodel);
    }
    
    public boolean infoBox(String Info, String titleBar, int Buttons){
        boolean answer = false;
        int dialogResult = JOptionPane.showConfirmDialog(null,Info,titleBar, Buttons);
        if(dialogResult == 0){
            answer = true;
        }else{
            answer = false;
        }
        return answer;
    }
    
    public boolean inTimeRange(LocalTime In, LocalTime Out){
        boolean isOK;
        if(In.isAfter(Out) || In.equals(Out)){isOK = false;}
        else{ isOK = false;}
        return isOK;
    }
    
    public boolean verify_action(String Action) throws ClassNotFoundException, SQLException{
        
        String User_Preveledge = DQ.User_Preveledge(CurrentUser);
        boolean actionGrant = false;
        /*
        Level 1 = log preveledge
        Level 2 = Create Read Update preveledge
        Level 3 = Create Read Update Delete preveledge
        */
        
        boolean confirm = infoBox("Do you confirm to proceed?","Action Warning",YES_NO_OPTION);
        if(confirm){
            if("Level 3".equals(User_Preveledge) && ("Create".equals(Action) || "Update".equals(Action) || "Delete".equals(Action))){
                actionGrant = true;
            }else if("Level 2".equals(User_Preveledge) && ("Create".equals(Action) || "Update".equals(Action))){
                actionGrant = true;
            }else{
                infoBox("Sorry you dont have the privilege of making this action. \nSeek personnels with higher privilege or contact your IT developer."
                        ,"Attention",WARNING_MESSAGE);
                actionGrant = false;
            }
        }
        return actionGrant;
    }
    
    public void tableHeader_custom(JTable table){
        JTableHeader Theader = table.getTableHeader();
        Theader.setFont(new Font("Century Gothic",Font.BOLD,14));
        Theader.setBackground(new Color(4,8,114));
        Theader.setForeground(new Color(255,255,255));
    }
    
    public void tableColumnSize(JTable table, int ColumnIndex, int width){
        table.getColumnModel().getColumn(ColumnIndex).setPreferredWidth(width);
        table.getColumnModel().getColumn(ColumnIndex).setMaxWidth(width);
        table.getColumnModel().getColumn(ColumnIndex).setMinWidth(width);
    }
    
    public void wholeMonthAttendanceRecords(boolean GO) throws ClassNotFoundException, SQLException, ParseException{
        SimpleDateFormat Df = new SimpleDateFormat("MMMM-yyyy");
        String monthInput = Df.format(monthMM_in.getDate());
        String MY[] = monthInput.split("-");
        String id = idNumberMM_in.getText();
        UnderTime[0]=0;
        UnderTime[1]=0;
        String Month;
        
        if("January".equalsIgnoreCase(MY[0])){Month="01";}
        else if("February".equalsIgnoreCase(MY[0])){Month="02";}
        else if("March".equalsIgnoreCase(MY[0])){Month="03";}
        else if("April".equalsIgnoreCase(MY[0])){Month="04";}
        else if("May".equalsIgnoreCase(MY[0])){Month="05";}
        else if("June".equalsIgnoreCase(MY[0])){Month="06";}
        else if("July".equalsIgnoreCase(MY[0])){Month="07";}
        else if("August".equalsIgnoreCase(MY[0])){Month="08";}
        else if("September".equalsIgnoreCase(MY[0])){Month="09";}
        else if("October".equalsIgnoreCase(MY[0])){Month="10";}
        else if("November".equalsIgnoreCase(MY[0])){Month="11";}
        else if("December".equalsIgnoreCase(MY[0])){Month="12";}
        else{Month="01";}
        
        String searchFrom = MY[1]+"-"+Month+"-01";
        //Get the week dates
        LocalDate localDate = LocalDate.parse(searchFrom);
        String DaynameOfFirstday = String.valueOf(localDate.getDayOfWeek());
        System.out.println("day name: "+DaynameOfFirstday);
        lastday = String.valueOf(localDate.with(TemporalAdjusters.lastDayOfMonth()));
        String cut[] = lastday.split("-");
        lastdayOftheMonth = Integer.parseInt(cut[2]);
        RDsearchFrom[0] = MY[1];RDsearchFrom[1] = Month;RDsearchFrom[2] = "01";
        
        int Y = 1;
        System.out.println("last day: "+cut[2]);
        
        if(GO){
            DefaultTableModel MMdcm = new DefaultTableModel(new Object[]{"DATE","MORNING IN","MORNING OUT","AFTERNOON IN","AFTERNOON OUT","UNDERTIME HOURS","UNDERTIME MINUTES"},0);
            String Mi[] = new String[31],Mo[]= new String[31],Ai[]= new String[31],Ao[]= new String[31],Th[]= new String[31],Tm[]= new String[31];
            String ql,rl,sl,tl,ul,vl,wl,xl,yl,zl,b1,c1,d1,e1,f1,g1,h1,i1,j1,kl,ll,ml,nl,ol,pl,aal,abl,acl,adl,ael,afl;
            b1 = (String) LogDate17.getModel().getSelectedItem();
            c1 = (String) LogDate18.getModel().getSelectedItem();
            d1 = (String) LogDate19.getModel().getSelectedItem();
            e1 = (String) LogDate20.getModel().getSelectedItem();
            f1 = (String) LogDate21.getModel().getSelectedItem();
            g1 = (String) LogDate22.getModel().getSelectedItem();
            h1 = (String) LogDate23.getModel().getSelectedItem();
            i1 = (String) LogDate24.getModel().getSelectedItem();
            j1 = (String) LogDate25.getModel().getSelectedItem();
            kl = (String) LogDate26.getModel().getSelectedItem();
            ll = (String) LogDate29.getModel().getSelectedItem();
            ml = (String) LogDate27.getModel().getSelectedItem();
            nl = (String) LogDate28.getModel().getSelectedItem();
            ol = (String) LogDate30.getModel().getSelectedItem();
            pl = (String) LogDate31.getModel().getSelectedItem();
            ql = (String) LogDate32.getModel().getSelectedItem();
            rl = (String) LogDate63.getModel().getSelectedItem();
            sl = (String) LogDate64.getModel().getSelectedItem();
            tl = (String) LogDate65.getModel().getSelectedItem();
            ul = (String) LogDate66.getModel().getSelectedItem();
            vl = (String) LogDate67.getModel().getSelectedItem();
            wl = (String) LogDate68.getModel().getSelectedItem();
            xl = (String) LogDate69.getModel().getSelectedItem();
            yl = (String) LogDate70.getModel().getSelectedItem();
            zl = (String) LogDate71.getModel().getSelectedItem();
            aal = (String) LogDate72.getModel().getSelectedItem();
            abl = (String) LogDate75.getModel().getSelectedItem();
            acl = (String) LogDate73.getModel().getSelectedItem();
            adl = (String) LogDate74.getModel().getSelectedItem();
            ael = (String) LogDate76.getModel().getSelectedItem();
            afl = (String) LogDate77.getModel().getSelectedItem();
            
            String[] Lognames = {b1,c1,d1,e1,f1,g1,h1,i1,j1,kl,ll,ml,nl,ol,pl,ql,rl,sl,tl,ul,vl,wl,xl,yl,zl,aal,abl,acl,adl,ael,afl};
            Y=1;
            for(int x=0;x<lastdayOftheMonth;x++){
                String d = String.valueOf(Y);
                if(String.valueOf(Y).length() < 2){d="0"+d;}
                String day = MY[0]+" "+d+" "+MY[1];
                
                    Mi[x] = DQ.fetch("select * from checkinout where Check_type='Mi' and IDnumber='"+id+"' and Date='"+day+"' and Log_Name='"+Lognames[x]+"'", "LogTime");
                    Mo[x] = DQ.fetch("select * from checkinout where Check_type='Mo' and IDnumber='"+id+"' and Date='"+day+"' and Log_Name='"+Lognames[x]+"'", "LogTime");
                    Ai[x] = DQ.fetch("select * from checkinout where Check_type='Ai' and IDnumber='"+id+"' and Date='"+day+"' and Log_Name='"+Lognames[x]+"'", "LogTime");
                    Ao[x] = DQ.fetch("select * from checkinout where Check_type='Ao' and IDnumber='"+id+"' and Date='"+day+"' and Log_Name='"+Lognames[x]+"'", "LogTime");
                    System.out.println(day+": "+Lognames[x]+" > "+Mi[x]+" "+Mo[x]+" "+Ai[x]+" "+Ao[x]+" ");
                    String LogCycle = DQ.fetch("select * from dailylogschedule where Date='"+day+"' and schName='"+Lognames[x]+"'", "LogIO_cycle");

                    if(Mi[x] == null || Mi[x].isEmpty() || Mi[x].isBlank()){Mi[x] = "00:00 AM";}
                    if(Mo[x] == null || Mo[x].isEmpty() || Mo[x].isBlank()){Mo[x] = "00:00 AM";Mi[x] = "00:00 AM";}
                    if(Ai[x] == null || Ai[x].isEmpty() || Ai[x].isBlank()){Ai[x] = "00:00 PM";}
                    if(Ao[x] == null || Ao[x].isEmpty() || Ao[x].isBlank()){Ao[x] = "00:00 PM";Ai[x] = "00:00 PM";}

                    if("Morning In".equals(LogCycle) || "Afternoon In".equals(LogCycle)){Th[x] = "8";}
                    else{
                        int getTimeM[] = timeAtt(Mi[x],Mo[x],"AM");
                        int getTimeA[] = timeAtt(Ai[x],Ao[x],"PM");

                        int Htime = getTimeM[1] + getTimeA[1];
                        int Mtime = getTimeM[2] + getTimeA[2];

                        int catchT[] = getUnderTime(day,Lognames[x],Htime,Mtime);
                        //int Etime = getTimeM[2] + getTimeA[2];
                        //int total = Stime + Etime;
                            Th[x] = String.valueOf(Math.abs(catchT[0]));
                            Tm[x] = String.valueOf(Math.abs(catchT[1]));

                        UnderTime[0] = Math.abs(UnderTime[0]+catchT[0]);
                        UnderTime[1] = Math.abs(UnderTime[1]+catchT[1]);
                }
                Y++;
            }

            int ddd = 1;
            for(int x=0;x<lastdayOftheMonth;x++){
                    
                    if("00:00 AM".equals(Mi[x])){Mi[x] = "";}
                    if("00:00 AM".equals(Mo[x])){Mo[x] = "";}
                    if("00:00 PM".equals(Ai[x])){Ai[x] = "";}
                    if("00:00 PM".equals(Ao[x])){Ao[x] = "";}
                    MMdcm.addRow(new Object[]{ddd,Mi[x],Mo[x],Ai[x],Ao[x],Th[x],Tm[x]});
                    ddd++;
            }
        
            DTRtableMM.setModel(MMdcm);
            tableHeader_custom(DTRtableMM);
            /*DTRtableMM.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tableColumnSize(DTRtableMM, 0, 45);
            for(int x=1;x<6;x++){
                tableColumnSize(DTRtableMM, x, 127);
            }*/
            DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
            cellRender.setHorizontalAlignment(JLabel.CENTER);
            for(int z=0;z<7;z++){
                DTRtableMM.getColumnModel().getColumn(z).setCellRenderer(cellRender);
            }
        }else{
            DefaultTableModel dcm = new DefaultTableModel(new Object[]{"DATE","MORNING IN","MORNING OUT","AFTERNOON IN","AFTERNOON OUT","UNDERTIME HOURS","UNDERTIME MINUTES"},0);
            int dd = 1;
            for(int x=0;x<lastdayOftheMonth;x++){
                    dcm.addRow(new Object[]{dd,"","","","","",""});
                    dd++;
            }
            DTRtableMM.setModel(dcm);
            logNamesMM();
            tableHeader_custom(DTRtableMM);
            DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
            cellRender.setHorizontalAlignment(JLabel.CENTER);
            for(int z=0;z<6;z++){
                DTRtableMM.getColumnModel().getColumn(z).setCellRenderer(cellRender);
            }
        }
        
    }
    
    
    public void halfMonthAttendanceRecords(boolean go) throws ClassNotFoundException, SQLException, ParseException{
        SimpleDateFormat Df = new SimpleDateFormat("MMMM-yyyy");
        String monthInput = Df.format(monthMDR_in.getDate());
        String MY[] = monthInput.split("-");
        String id = idMDR_in.getText();
        String Mset = setMDR_in.getText();
        
        String Month;
        
        if("January".equalsIgnoreCase(MY[0])){Month="01";}
        else if("February".equalsIgnoreCase(MY[0])){Month="02";}
        else if("March".equalsIgnoreCase(MY[0])){Month="03";}
        else if("April".equalsIgnoreCase(MY[0])){Month="04";}
        else if("May".equalsIgnoreCase(MY[0])){Month="05";}
        else if("June".equalsIgnoreCase(MY[0])){Month="06";}
        else if("July".equalsIgnoreCase(MY[0])){Month="07";}
        else if("August".equalsIgnoreCase(MY[0])){Month="08";}
        else if("September".equalsIgnoreCase(MY[0])){Month="09";}
        else if("October".equalsIgnoreCase(MY[0])){Month="10";}
        else if("November".equalsIgnoreCase(MY[0])){Month="11";}
        else if("December".equalsIgnoreCase(MY[0])){Month="12";}
        else{Month="01";}
        
        String searchFrom = MY[1]+"-"+Month+"-01";
        //Get the week dates
        LocalDate localDate = LocalDate.parse(searchFrom);
        String DaynameOfFirstday = String.valueOf(localDate.getDayOfWeek());
        System.out.println("day name: "+DaynameOfFirstday);
        lastday = String.valueOf(localDate.with(TemporalAdjusters.lastDayOfMonth()));
        String cut[] = lastday.split("-");
        lastdayOftheMonth = Integer.parseInt(cut[2]);
        System.out.println("last day: "+cut[2]);
        
        int Y=1, w=15;
        if(Integer.parseInt(Mset) == 2){
            Y=16;
            w=lastdayOftheMonth;
        }
        int FinalY = Y;
        if(go){
            DefaultTableModel dcm = new DefaultTableModel(new Object[]{"DATE","MORNING IN","MORNING OUT","AFTERNOON IN","AFTERNOON OUT","TOTAL HOURS"},0);
            String Mi[] = new String[17],Mo[]= new String[17],Ai[]= new String[17],Ao[]= new String[17],Th[]= new String[17];
            String p1,b1,c1,d1,e1,f1,g1,h1,i1,j1,k1,l1,m1,n1,o1,a1 = (String) LogDate1.getModel().getSelectedItem();
            b1 = (String) LogDate2.getModel().getSelectedItem();
            c1 = (String) LogDate3.getModel().getSelectedItem();
            d1 = (String) LogDate4.getModel().getSelectedItem();
            e1 = (String) LogDate5.getModel().getSelectedItem();
            f1 = (String) LogDate6.getModel().getSelectedItem();
            g1 = (String) LogDate7.getModel().getSelectedItem();
            h1 = (String) LogDate8.getModel().getSelectedItem();
            i1 = (String) LogDate9.getModel().getSelectedItem();
            j1 = (String) LogDate10.getModel().getSelectedItem();
            k1 = (String) LogDate11.getModel().getSelectedItem();
            l1 = (String) LogDate12.getModel().getSelectedItem();
            m1 = (String) LogDate13.getModel().getSelectedItem();
            n1 = (String) LogDate14.getModel().getSelectedItem();
            o1 = (String) LogDate15.getModel().getSelectedItem();
            p1 = (String) LogDate16.getModel().getSelectedItem();
            
            String[] Lognames = {a1, b1, c1, d1, e1, f1, g1, h1, i1, j1, k1, l1, m1, n1, o1, p1};
            
            for(int x=0;x<16;x++){
                String d = String.valueOf(Y);
                if(String.valueOf(Y).length() < 2){d="0"+d;}
                String day = MY[0]+" "+d+" "+MY[1];
                Mi[x] = DQ.fetch("select * from checkinout where Check_type='Mi' and IDnumber='"+id+"' and Date='"+day+"' and Log_Name='"+Lognames[x]+"'", "LogTime");
                Mo[x] = DQ.fetch("select * from checkinout where Check_type='Mo' and IDnumber='"+id+"' and Date='"+day+"' and Log_Name='"+Lognames[x]+"'", "LogTime");
                Ai[x] = DQ.fetch("select * from checkinout where Check_type='Ai' and IDnumber='"+id+"' and Date='"+day+"' and Log_Name='"+Lognames[x]+"'", "LogTime");
                Ao[x] = DQ.fetch("select * from checkinout where Check_type='Ao' and IDnumber='"+id+"' and Date='"+day+"' and Log_Name='"+Lognames[x]+"'", "LogTime");
                String LogCycle = DQ.fetch("select * from dailylogschedule where Date='"+day+"' and schName='"+Lognames[x]+"'", "LogIO_cycle");
                
                if(Mi[x] == null || Mi[x].isEmpty() || Mi[x].isBlank()){Mi[x] = "00:00 AM";}
                if(Mo[x] == null || Mo[x].isEmpty() || Mo[x].isBlank()){Mo[x] = "00:00 AM"; Mi[x] = "00:00 AM";}
                if(Ai[x] == null || Ai[x].isEmpty() || Ai[x].isBlank()){Ai[x] = "00:00 PM";}
                if(Ao[x] == null || Ao[x].isEmpty() || Ao[x].isBlank()){Ao[x] = "00:00 PM"; Ai[x] = "00:00 PM";}
                
                if("Morning In".equals(LogCycle) || "Afternoon In".equals(LogCycle)){Th[x] = "8";}
                else{
                    int getTimeM[] = timeAtt(Mi[x],Mo[x],"AM");
                    int getTimeA[] = timeAtt(Ai[x],Ao[x],"PM");

                    int Stime = getTimeM[1] + getTimeA[1];
                    //int Etime = getTimeM[2] + getTimeA[2];
                    //int total = Stime + Etime;
                    if(Stime<0){Th[x] = "0";}
                    else{
                        Th[x] = String.valueOf(Stime);
                    }
                }
                Y++;
            }

            for(int x=0;x<17;x++){
                if(FinalY <= w){
                    if("00:00 AM".equals(Mi[x])){Mi[x] = "";}
                    if("00:00 AM".equals(Mo[x])){Mo[x] = "";}
                    if("00:00 PM".equals(Ai[x])){Ai[x] = "";}
                    if("00:00 PM".equals(Ao[x])){Ao[x] = "";}
                    dcm.addRow(new Object[]{FinalY,Mi[x],Mo[x],Ai[x],Ao[x],Th[x]});
                    FinalY++;
                }
            }
            DTRtable.setModel(dcm);
            tableHeader_custom(DTRtable);
            DTRtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tableColumnSize(DTRtable, 0, 45);
            for(int x=1;x<6;x++){
                tableColumnSize(DTRtable, x, 127);
            }
            DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
            cellRender.setHorizontalAlignment(JLabel.CENTER);
            for(int z=0;z<6;z++){
                DTRtable.getColumnModel().getColumn(z).setCellRenderer(cellRender);
            }
        }else{
            DefaultTableModel dcm = new DefaultTableModel(new Object[]{"DATE","MORNING IN","MORNING OUT","AFTERNOON IN","AFTERNOON OUT","TOTAL HOURS"},0);
        for(int x=0;x<17;x++){
                if(FinalY <= w){
                    dcm.addRow(new Object[]{FinalY,"","","","",""});
                    FinalY++;
                }
            }
            DTRtable.setModel(dcm);
            logNames15PerDate();
            tableHeader_custom(DTRtable);
            DTRtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tableColumnSize(DTRtable, 0, 45);
            for(int x=1;x<6;x++){
                tableColumnSize(DTRtable, x, 127);
            }
            DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
            cellRender.setHorizontalAlignment(JLabel.CENTER);
            for(int z=0;z<6;z++){
                DTRtable.getColumnModel().getColumn(z).setCellRenderer(cellRender);
            }
        }
    }
    
    //Getting the total Hours
    public int[] timeAtt(String Time, String Time2, String AP){
        int[] time = new int[5];
        String st = Time.substring(0,Time.length()-6); //H
        String st2 = Time.substring(3,Time.length()-3); //M
        String et = Time2.substring(0,Time2.length()-6); //H
        String et2 = Time2.substring(3,Time2.length()-3); //M
        
        time[1] = Math.abs(Integer.parseInt(st) - Integer.parseInt(et));
        time[2] = ((60 - Integer.parseInt(st2)) + Integer.parseInt(et2)) % 60;
                
        //System.out.println("TotalHours: "+time[1]+":"+time[2]); 
               
        return time;
    }
    
    public int[] getUnderTime(String date, String SchedName, int Hours, int min) throws ClassNotFoundException, SQLException{
        
        String inM = DQ.fetch("SELECT * FROM smartlogdb.dailylogschedule where Date='"+date+"' and schName='"+SchedName+"'", "StartTime_1");
        String outM = DQ.fetch("SELECT * FROM smartlogdb.dailylogschedule where Date='"+date+"' and schName='"+SchedName+"'", "EndTime_1");
        String inA = DQ.fetch("SELECT * FROM smartlogdb.dailylogschedule where Date='"+date+"' and schName='"+SchedName+"'", "StartTime_2");
        String outA = DQ.fetch("SELECT * FROM smartlogdb.dailylogschedule where Date='"+date+"' and schName='"+SchedName+"'", "EndTime_2");
        String in1 = "0", out1="0", in2 ="0", out2="0";
        
        if(inM.isEmpty()){}else{in1 = inM.substring(0,inM.length()-6);}
        if(outM.isEmpty()){}else{out1 = outM.substring(0,outM.length()-6);}
        if(inA.isEmpty()){}else{in2 = inM.substring(0,inA.length()-6);}
        if(outA.isEmpty()){}else{out2 = outM.substring(0,outA.length()-6);}
        
        int totalH = Math.abs(Integer.parseInt(in1) - Integer.parseInt(out1)) + Math.abs(Integer.parseInt(in2) - Integer.parseInt(out2));
        
        if(inM.isEmpty()){}else{in1 = inM.substring(3,inM.length()-3);}
        if(outM.isEmpty()){}else{out1 = outM.substring(3,outM.length()-3);}
        if(inA.isEmpty()){}else{in2=inA.substring(3,inA.length()-3);}
        if(outA.isEmpty()){}else{out2 = outM.substring(3,outA.length()-3);}
        
        int totalM = ((((60 - Integer.parseInt(in1)) + Integer.parseInt(out1)) % 60)+(((60 - Integer.parseInt(in2)) + Integer.parseInt(out2)) % 60))%60;
        
        int cH = totalH - Hours;
        int cM = totalM - min;
        
        int[] HM = {cH,cM};
        
        return HM;
    }
    
    public void logNamesMM() throws ClassNotFoundException, SQLException, ParseException{
        
       DefaultComboBoxModel allMpdLogs = new DefaultComboBoxModel(),allMpdLogs2 = new DefaultComboBoxModel(),
                            allMpdLogs3 = new DefaultComboBoxModel(),allMpdLogs4 = new DefaultComboBoxModel(),
                            allMpdLogs5 = new DefaultComboBoxModel(),allMpdLogs6 = new DefaultComboBoxModel(),
                            allMpdLogs7 = new DefaultComboBoxModel(),allMpdLogs8 = new DefaultComboBoxModel(),
                            allMpdLogs9 = new DefaultComboBoxModel(),allMpdLogs10 = new DefaultComboBoxModel(),
                            allMpdLogs11 = new DefaultComboBoxModel(),allMpdLogs12 = new DefaultComboBoxModel(),
                            allMpdLogs13 = new DefaultComboBoxModel(),allMpdLogs14 = new DefaultComboBoxModel(),
                            allMpdLogs15 = new DefaultComboBoxModel(),allMpdLogs16 = new DefaultComboBoxModel(),
                            allMpdLogs17 = new DefaultComboBoxModel(),allMpdLogs18 = new DefaultComboBoxModel(),
                            allMpdLogs19 = new DefaultComboBoxModel(),allMpdLogs20 = new DefaultComboBoxModel(),
                            allMpdLogs21 = new DefaultComboBoxModel(),allMpdLogs22 = new DefaultComboBoxModel(),
                            allMpdLogs23 = new DefaultComboBoxModel(),allMpdLogs24 = new DefaultComboBoxModel(),
                            allMpdLogs25 = new DefaultComboBoxModel(),allMpdLogs26 = new DefaultComboBoxModel(),
                            allMpdLogs27 = new DefaultComboBoxModel(),allMpdLogs28 = new DefaultComboBoxModel(),
                            allMpdLogs29 = new DefaultComboBoxModel(),allMpdLogs30 = new DefaultComboBoxModel(),
                            allMpdLogs31 = new DefaultComboBoxModel();
        
        SimpleDateFormat Df = new SimpleDateFormat("MMMM-yyyy");
        String monthYYYY = Df.format(monthMM_in.getDate());
        String cutMY[] = monthYYYY.split("-");
        String Month;
        if("January".equalsIgnoreCase(cutMY[0])){Month="01";}
        else if("February".equalsIgnoreCase(cutMY[0])){Month="02";}
        else if("March".equalsIgnoreCase(cutMY[0])){Month="03";}
        else if("April".equalsIgnoreCase(cutMY[0])){Month="04";}
        else if("May".equalsIgnoreCase(cutMY[0])){Month="05";}
        else if("June".equalsIgnoreCase(cutMY[0])){Month="06";}
        else if("July".equalsIgnoreCase(cutMY[0])){Month="07";}
        else if("August".equalsIgnoreCase(cutMY[0])){Month="08";}
        else if("September".equalsIgnoreCase(cutMY[0])){Month="09";}
        else if("October".equalsIgnoreCase(cutMY[0])){Month="10";}
        else if("November".equalsIgnoreCase(cutMY[0])){Month="11";}
        else if("December".equalsIgnoreCase(cutMY[0])){Month="12";}
        else{Month="01";}
        
        String searchFrom = cutMY[1]+"-"+Month+"-01";
        //Get the week dates
        LocalDate localDate = LocalDate.parse(searchFrom);
        SimpleDateFormat compF = new SimpleDateFormat("yyyy-mm-dd");
        Date dateCurrent = new Date(System.currentTimeMillis());
        String DaynameOfFirstday = String.valueOf(localDate.getDayOfWeek());
        System.out.println("day name: "+DaynameOfFirstday);
        lastday = String.valueOf(localDate.with(TemporalAdjusters.lastDayOfMonth()));
        String cut[] = lastday.split("-");
        lastdayOftheMonth = Integer.parseInt(cut[2]);
        
        Date dd = dateCurrent;
        Date ct = compF.parse(cutMY[1]+"-"+Month+"-01");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 1
            String Log1[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 01 "+cutMY[1]+"'", "schName");
            allMpdLogs.removeAllElements();for (String Log11 : Log1) {if (!"".equals(Log11)) {allMpdLogs.addElement(Log11);}}
            LogDate17.setModel(allMpdLogs);    
            System.out.println("Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 01 "+cutMY[1]+"'");
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-02");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 2
            String Log2[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 02 "+cutMY[1]+"'", "schName");
            allMpdLogs2.removeAllElements();for (String LogOne1 : Log2) {if(!"".equals(LogOne1)){ allMpdLogs2.addElement(LogOne1);}}
            LogDate18.setModel(allMpdLogs2);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-03");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 3
            String Log3[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 03 "+cutMY[1]+"'", "schName");
            allMpdLogs3.removeAllElements();for (String LogOne1 : Log3) {if(!"".equals(LogOne1)){ allMpdLogs3.addElement(LogOne1);}}
            LogDate19.setModel(allMpdLogs3); 
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-04");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 4
            String Log4[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 04 "+cutMY[1]+"'", "schName");
            allMpdLogs4.removeAllElements();for (String LogOne1 : Log4) {if(!"".equals(LogOne1)){ allMpdLogs4.addElement(LogOne1);}}
            LogDate20.setModel(allMpdLogs4);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-05");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 5
            String Log5[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 05 "+cutMY[1]+"'", "schName");
            allMpdLogs5.removeAllElements();for (String LogOne1 : Log5) {if(!"".equals(LogOne1)){ allMpdLogs5.addElement(LogOne1);}}
            LogDate21.setModel(allMpdLogs5);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-06");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 6
            String Log6[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 06 "+cutMY[1]+"'", "schName");
            allMpdLogs6.removeAllElements();for (String LogOne1 : Log6) {if(!"".equals(LogOne1)){ allMpdLogs6.addElement(LogOne1);}}
            LogDate22.setModel(allMpdLogs6);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-07");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 7
            String Log7[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 07 "+cutMY[1]+"'", "schName");
            allMpdLogs7.removeAllElements();for (String LogOne1 : Log7) {if(!"".equals(LogOne1)){ allMpdLogs7.addElement(LogOne1);}}
            LogDate23.setModel(allMpdLogs7);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-08");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 8
            String Log8[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 08 "+cutMY[1]+"'", "schName");
            allMpdLogs8.removeAllElements();for (String LogOne1 : Log8) {if(!"".equals(LogOne1)){ allMpdLogs8.addElement(LogOne1);}}
            LogDate24.setModel(allMpdLogs8);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-09");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 9
            String Log9[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 09 "+cutMY[1]+"'", "schName");
            allMpdLogs9.removeAllElements();for (String LogOne1 : Log9) {if(!"".equals(LogOne1)){ allMpdLogs9.addElement(LogOne1);}}
            LogDate25.setModel(allMpdLogs9);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-10");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 10
            String Log10[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 10 "+cutMY[1]+"'", "schName");
            allMpdLogs10.removeAllElements();for (String LogOne1 : Log10) {if(!"".equals(LogOne1)){ allMpdLogs10.addElement(LogOne1);}}
            LogDate26.setModel(allMpdLogs10);  
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-11");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 11
            String Log11[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 11 "+cutMY[1]+"'", "schName");
            allMpdLogs11.removeAllElements();for (String LogOne1 : Log11) {if(!"".equals(LogOne1)){ allMpdLogs11.addElement(LogOne1);}}
            LogDate29.setModel(allMpdLogs11);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-12");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 12
            String Log12[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 12 "+cutMY[1]+"'", "schName");
            allMpdLogs12.removeAllElements();for (String LogOne1 : Log12) {if(!"".equals(LogOne1)){ allMpdLogs12.addElement(LogOne1);}}
            LogDate27.setModel(allMpdLogs12);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-13");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 13
            String Log13[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 13 "+cutMY[1]+"'", "schName");
            allMpdLogs13.removeAllElements();for (String LogOne1 : Log13) {if(!"".equals(LogOne1)){ allMpdLogs13.addElement(LogOne1);}}
            LogDate28.setModel(allMpdLogs13);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-14");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 14
            String Log14[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 14 "+cutMY[1]+"'", "schName");
            allMpdLogs14.removeAllElements();for (String LogOne1 : Log14) {if (!"".equals(LogOne1)) {allMpdLogs14.addElement(LogOne1);}}
            LogDate30.setModel(allMpdLogs14);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-15");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 15
            String Log15[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 15 "+cutMY[1]+"'", "schName");
            allMpdLogs15.removeAllElements();for (String LogOne1 : Log15) {if(!"".equals(LogOne1)){ allMpdLogs15.addElement(LogOne1);}}
            LogDate31.setModel(allMpdLogs15);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-16");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 16
            String Log16[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 16 "+cutMY[1]+"'", "schName");
            allMpdLogs16.removeAllElements();for (String LogOne1 : Log16) {if(!"".equals(LogOne1)){ allMpdLogs16.addElement(LogOne1);}}
            LogDate32.setModel(allMpdLogs16);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-17");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 17
            String Log17[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 17 "+cutMY[1]+"'", "schName");
            allMpdLogs17.removeAllElements();for (String LogOne1 : Log17) {if(!"".equals(LogOne1)){ allMpdLogs17.addElement(LogOne1);}}
            LogDate63.setModel(allMpdLogs17);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-18");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 18
            String Log18[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 18 "+cutMY[1]+"'", "schName");
            allMpdLogs18.removeAllElements();for (String LogOne1 : Log18) {if(!"".equals(LogOne1)){ allMpdLogs18.addElement(LogOne1);}}
            LogDate64.setModel(allMpdLogs18);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-19");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 19
            String Log19[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 19 "+cutMY[1]+"'", "schName");
            allMpdLogs19.removeAllElements();for (String LogOne1 : Log19) {if(!"".equals(LogOne1)){ allMpdLogs19.addElement(LogOne1);}}
            LogDate65.setModel(allMpdLogs19);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-20");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 20
            String Log20[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 20 "+cutMY[1]+"'", "schName");
            allMpdLogs20.removeAllElements();for (String LogOne1 : Log20) {if(!"".equals(LogOne1)){ allMpdLogs20.addElement(LogOne1);}}
            LogDate66.setModel(allMpdLogs20);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-21");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 21
            String Log21[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 21 "+cutMY[1]+"'", "schName");
            allMpdLogs21.removeAllElements();for (String LogOne1 : Log21) {if(!"".equals(LogOne1)){ allMpdLogs21.addElement(LogOne1);}}
            LogDate67.setModel(allMpdLogs21);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-22");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 4
            String Log22[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 22 "+cutMY[1]+"'", "schName");
            allMpdLogs22.removeAllElements();for (String LogOne1 : Log22) {if(!"".equals(LogOne1)){ allMpdLogs22.addElement(LogOne1);}}
            LogDate68.setModel(allMpdLogs22);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-23");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 23
            String Log23[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 23 "+cutMY[1]+"'", "schName");
            allMpdLogs23.removeAllElements();for (String LogOne1 : Log23) {if(!"".equals(LogOne1)){ allMpdLogs23.addElement(LogOne1);}}
            LogDate69.setModel(allMpdLogs23);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-24");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 24
            String Log24[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 24 "+cutMY[1]+"'", "schName");
            allMpdLogs24.removeAllElements();for (String LogOne1 : Log24) {if(!"".equals(LogOne1)){ allMpdLogs24.addElement(LogOne1);}}
            LogDate70.setModel(allMpdLogs24);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-25");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 25
            String Log25[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 25 "+cutMY[1]+"'", "schName");
            allMpdLogs25.removeAllElements();for (String LogOne1 : Log25) {if(!"".equals(LogOne1)){ allMpdLogs25.addElement(LogOne1);}}
            LogDate71.setModel(allMpdLogs25);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-26");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 26
            String Log26[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 26 "+cutMY[1]+"'", "schName");
            allMpdLogs26.removeAllElements();for (String LogOne1 : Log26) {if(!"".equals(LogOne1)){ allMpdLogs26.addElement(LogOne1);}}
            LogDate72.setModel(allMpdLogs26);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-27");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 27
            String Log27[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 27 "+cutMY[1]+"'", "schName");
            allMpdLogs27.removeAllElements();for (String LogOne1 : Log27) {if(!"".equals(LogOne1)){ allMpdLogs27.addElement(LogOne1);}}
            LogDate75.setModel(allMpdLogs27);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-28");
        if(ct.before(dd) || ct.equals(dd)){
        //Setting date 28
            String Log28[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 28 "+cutMY[1]+"'", "schName");
            allMpdLogs28.removeAllElements();for (String LogOne1 : Log28) {if(!"".equals(LogOne1)){ allMpdLogs28.addElement(LogOne1);}}
            LogDate73.setModel(allMpdLogs28);
        }
        ct = compF.parse(cutMY[1]+"-"+Month+"-29");
        if(ct.before(dd) || ct.equals(dd)){
            //Setting date 29
                String Log29[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 29 "+cutMY[1]+"'", "schName");
                allMpdLogs29.removeAllElements();for (String LogOne1 : Log29) {if(!"".equals(LogOne1)){ allMpdLogs29.addElement(LogOne1);}}
                LogDate74.setModel(allMpdLogs29);
            ct = compF.parse(cutMY[1]+"-"+Month+"-30");
        if(ct.before(dd) || ct.equals(dd)){
            //Setting date 30
                String Log30[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 30 "+cutMY[1]+"'", "schName");
                allMpdLogs30.removeAllElements();for (String LogOne1 : Log30) {if(!"".equals(LogOne1)){ allMpdLogs30.addElement(LogOne1);}}
                LogDate76.setModel(allMpdLogs30); 
            
            ct = compF.parse(cutMY[1]+"-"+Month+"-31");
        if(ct.before(dd) || ct.equals(dd)){
                //Setting date 31
                    String Log31[] = DQ.arrayFetch("dailylogschedule", "Select * from smartlogdb.dailylogschedule where Date='"+cutMY[0]+" 31 "+cutMY[1]+"'", "schName");
                    allMpdLogs31.removeAllElements();for (String LogOne1 : Log31) {if(!"".equals(LogOne1)){ allMpdLogs31.addElement(LogOne1);}}
                    LogDate77.setModel(allMpdLogs31);
            }}
        }
        
    }
    
    public void logNames15PerDate() throws ClassNotFoundException, SQLException{
        DefaultComboBoxModel a = new DefaultComboBoxModel(),b = new DefaultComboBoxModel(),c = new DefaultComboBoxModel(),d = new DefaultComboBoxModel();
        DefaultComboBoxModel e = new DefaultComboBoxModel(),f = new DefaultComboBoxModel(),g = new DefaultComboBoxModel(),h = new DefaultComboBoxModel();
        DefaultComboBoxModel i = new DefaultComboBoxModel(),j = new DefaultComboBoxModel(),k = new DefaultComboBoxModel(),l = new DefaultComboBoxModel();
        DefaultComboBoxModel m = new DefaultComboBoxModel(),n = new DefaultComboBoxModel(),o = new DefaultComboBoxModel(),p = new DefaultComboBoxModel();
        
        String set15 = setMDR_in.getText();
        SimpleDateFormat Df = new SimpleDateFormat("MMMM-yyyy");
        String monthYYYY = Df.format(monthMDR_in.getDate());
        String cutMY[] = monthYYYY.split("-");
        String Month;
        if("January".equalsIgnoreCase(cutMY[0])){Month="01";}
        else if("February".equalsIgnoreCase(cutMY[0])){Month="02";}
        else if("March".equalsIgnoreCase(cutMY[0])){Month="03";}
        else if("April".equalsIgnoreCase(cutMY[0])){Month="04";}
        else if("May".equalsIgnoreCase(cutMY[0])){Month="05";}
        else if("June".equalsIgnoreCase(cutMY[0])){Month="06";}
        else if("July".equalsIgnoreCase(cutMY[0])){Month="07";}
        else if("August".equalsIgnoreCase(cutMY[0])){Month="08";}
        else if("September".equalsIgnoreCase(cutMY[0])){Month="09";}
        else if("October".equalsIgnoreCase(cutMY[0])){Month="10";}
        else if("November".equalsIgnoreCase(cutMY[0])){Month="11";}
        else if("December".equalsIgnoreCase(cutMY[0])){Month="12";}
        else{Month="01";}
        
        String searchFrom = cutMY[1]+"-"+Month+"-01";
        //Get the week dates
        LocalDate localDate = LocalDate.parse(searchFrom);
        String DaynameOfFirstday = String.valueOf(localDate.getDayOfWeek());
        System.out.println("day name: "+DaynameOfFirstday);
        lastday = String.valueOf(localDate.with(TemporalAdjusters.lastDayOfMonth()));
        String cut[] = lastday.split("-");
        lastdayOftheMonth = Integer.parseInt(cut[2]);
        
        int limit;
        if("02".equals(set15) || "2".equals(set15)){limit = lastdayOftheMonth;}
        else{limit = 15;}
        
        String aa = String.valueOf(DTRtable.getModel().getValueAt(0, 0));//r c
        if(aa.length() < 2){aa = "0"+aa;}
        String date = cutMY[0]+" "+aa+" "+cutMY[1];
            String LogOne[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+date+"'", "schName");
            a.removeAllElements();
            for (String LogOne1 : LogOne) {
                if(LogOne1 == null && "".equals(LogOne1)){} else {
                    a.addElement(LogOne1);
                }
            }
            LogDate1.setModel(a);
            
        aa = String.valueOf(DTRtable.getModel().getValueAt(1, 0));
        if(aa.length() < 2){aa = "0"+aa;}
        date = cutMY[0]+" "+aa+" "+cutMY[1];
            String bb[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+date+"'", "schName");
            b.removeAllElements();
            for (String LogOne1 : bb) {
                if(LogOne1 == null && "".equals(LogOne1)){} else {
                    b.addElement(LogOne1);
                }
            }
            LogDate2.setModel(b);
            
        aa = String.valueOf(DTRtable.getModel().getValueAt(2, 0));
        if(aa.length() < 2){aa = "0"+aa;}
        date = cutMY[0]+" "+aa+" "+cutMY[1];
            String cc[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+date+"'", "schName");
           c.removeAllElements();
            for (String LogOne1 : cc) {
                if(LogOne1 == null && "".equals(LogOne1)){} else {
                    c.addElement(LogOne1);
                }
            }
            LogDate3.setModel(c);
        
        aa = String.valueOf(DTRtable.getModel().getValueAt(3, 0));
        if(aa.length() < 2){aa = "0"+aa;}
        date = cutMY[0]+" "+aa+" "+cutMY[1];
            String dd[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+date+"'", "schName");
           d.removeAllElements();
            for (String LogOne1 : dd) {
                if(LogOne1 == null && "".equals(LogOne1)){} else {
                    d.addElement(LogOne1);
                }
            }
            LogDate4.setModel(d);
        
        aa = String.valueOf(DTRtable.getModel().getValueAt(4, 0));
        if(aa.length() < 2){aa = "0"+aa;}
        date = cutMY[0]+" "+aa+" "+cutMY[1];
            String ee[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+date+"'", "schName");
          e.removeAllElements();
            for (String LogOne1 : ee) {
                if(LogOne1 == null && "".equals(LogOne1)){} else {
                    e.addElement(LogOne1);
                }
            }
            LogDate5.setModel(e);
        
        aa = String.valueOf(DTRtable.getModel().getValueAt(5, 0));
        if(aa.length() < 2){aa = "0"+aa;}
        date = cutMY[0]+" "+aa+" "+cutMY[1];
            String ff[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+date+"'", "schName");
           f.removeAllElements();
            for (String LogOne1 : ff) {
                if(LogOne1 == null && "".equals(LogOne1)){} else {
                    f.addElement(LogOne1);
                }
            }
            LogDate6.setModel(f);
            
        aa = String.valueOf(DTRtable.getModel().getValueAt(6, 0));
        if(aa.length() < 2){aa = "0"+aa;}
        date = cutMY[0]+" "+aa+" "+cutMY[1];
            String gg[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+date+"'", "schName");
            g.removeAllElements();
            for (String LogOne1 : gg) {
                if(LogOne1 == null && "".equals(LogOne1)){} else {
                    g.addElement(LogOne1);
                }
            }
            LogDate7.setModel(g);
            
        aa = String.valueOf(DTRtable.getModel().getValueAt(7, 0));
        if(aa.length() < 2){aa = "0"+aa;}
        date = cutMY[0]+" "+aa+" "+cutMY[1];
            String hh[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+date+"'", "schName");
           h.removeAllElements();
            for (String LogOne1 : hh) {
                if(LogOne1 == null && "".equals(LogOne1)){} else {
                    h.addElement(LogOne1);
                }
            }
            LogDate8.setModel(h);  
            
        aa = String.valueOf(DTRtable.getModel().getValueAt(8, 0));
        if(aa.length() < 2){aa = "0"+aa;}
        date = cutMY[0]+" "+aa+" "+cutMY[1];
            String ii[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+date+"'", "schName");
           i.removeAllElements();
            for (String LogOne1 : ii) {
                if(LogOne1 == null && "".equals(LogOne1)){} else {
                    i.addElement(LogOne1);
                }
            }
            LogDate9.setModel(i);
            
        aa = String.valueOf(DTRtable.getModel().getValueAt(9, 0));
        if(aa.length() < 2){aa = "0"+aa;}
        date = cutMY[0]+" "+aa+" "+cutMY[1];
            String jj[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+date+"'", "schName");
           j.removeAllElements();
            for (String LogOne1 : jj) {
                if(LogOne1 == null && "".equals(LogOne1)){} else {
                    j.addElement(LogOne1);
                }
            }
            LogDate10.setModel(j);
            
        aa = String.valueOf(DTRtable.getModel().getValueAt(10, 0));
        if(aa.length() < 2){aa = "0"+aa;}
        date = cutMY[0]+" "+aa+" "+cutMY[1];
            String kk[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+date+"'", "schName");
           k.removeAllElements();
            for (String LogOne1 : kk) {
                if(LogOne1 == null && "".equals(LogOne1)){} else {
                    k.addElement(LogOne1);
                }
            }
            LogDate11.setModel(k);
            
        aa = String.valueOf(DTRtable.getModel().getValueAt(11, 0));
        if(aa.length() < 2){aa = "0"+aa;}
        date = cutMY[0]+" "+aa+" "+cutMY[1];
            String ll[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+date+"'", "schName");
           l.removeAllElements();
            for (String LogOne1 : ll) {
                if(LogOne1 == null && "".equals(LogOne1)){} else {
                    l.addElement(LogOne1);
                }
            }
            LogDate12.setModel(l);
            
        aa = String.valueOf(DTRtable.getModel().getValueAt(12, 0));
        if(aa.length() < 2){aa = "0"+aa;}
        date = cutMY[0]+" "+aa+" "+cutMY[1];
            String mm[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+date+"'", "schName");
           m.removeAllElements();
            for (String LogOne1 : mm) {
                if(LogOne1 == null && "".equals(LogOne1)){} else {
                    m.addElement(LogOne1);
                }
            }
            LogDate13.setModel(m);
            
        aa = String.valueOf(DTRtable.getModel().getValueAt(13, 0));
        if(aa.length() < 2){aa = "0"+aa;}
        date = cutMY[0]+" "+aa+" "+cutMY[1];
            String nn[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+date+"'", "schName");
           n.removeAllElements();
            for (String LogOne1 : nn) {
                if(LogOne1 == null && "".equals(LogOne1)){} else {
                    n.addElement(LogOne1);
                }
            }
            LogDate14.setModel(n);
            
        aa = String.valueOf(DTRtable.getModel().getValueAt(14, 0));
        if(aa.length() < 2){aa = "0"+aa;}
        date = cutMY[0]+" "+aa+" "+cutMY[1];
            String oo[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+date+"'", "schName");
            o.removeAllElements();
            for (String LogOne1 : oo) {
                if(LogOne1 == null && "".equals(LogOne1)){} else {
                    o.addElement(LogOne1);
                }
            }
            LogDate15.setModel(o);
        
        if(DTRtable.getModel().getRowCount() == 16){
            aa = String.valueOf(DTRtable.getModel().getValueAt(15, 0));
            LogDate16.setVisible(true);
            if(aa.length() < 2){aa = "0"+aa;}
            date = cutMY[0]+" "+aa+" "+cutMY[1];
                String pp[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+date+"'", "schName");
                p.removeAllElements();
                for (String LogOne1 : pp) {
                    if(LogOne1 == null && "".equals(LogOne1)){} else {
                    p.addElement(LogOne1);
                }
                }
                LogDate16.setModel(p);
        }else{LogDate16.setVisible(false);}
    }
    
    
    public void logNamesPerDate() throws ClassNotFoundException, SQLException{
        String searchMonth = DateWDR_in.getText();
        String searchYear = YearWDR_in.getText();
        
        if(searchMonth.isEmpty() || searchYear.isEmpty()){
            infoBox("Fill in Month and Year.", "SEARCH ERROR", CLOSED_OPTION);
        }else{
            try{
                DefaultComboBoxModel one_LogDatemodel = new DefaultComboBoxModel();
                DefaultComboBoxModel two_LogDatemodel = new DefaultComboBoxModel();
                DefaultComboBoxModel three_LogDatemodel = new DefaultComboBoxModel();
                DefaultComboBoxModel four_LogDatemodel = new DefaultComboBoxModel();
                DefaultComboBoxModel five_LogDatemodel = new DefaultComboBoxModel();
                DefaultComboBoxModel six_LogDatemodel = new DefaultComboBoxModel();
                DefaultComboBoxModel seven_LogDatemodel = new DefaultComboBoxModel();
                String date;

                date = one.getText();
                if(!("".equals(date))){
                    if(date.length() < 2){date = "0"+date;}
                    String LogOne[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+searchMonth+" "+date+" "+searchYear+"'", "schName");
                    one_LogDatemodel.removeAllElements();
                    for (String LogOne1 : LogOne) {
                        if(!"".equals(LogOne1)){ one_LogDatemodel.addElement(LogOne1);}
                    }
                    one_log.setModel(one_LogDatemodel);
                }

                date = two.getText();
                if(!("".equals(date))){
                    if(date.length() < 2){date = "0"+date;}
                    String LogOne[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+searchMonth+" "+date+" "+searchYear+"'", "schName");
                    two_LogDatemodel.removeAllElements();
                    for (String LogOne1 : LogOne) {
                        if(!"".equals(LogOne1)){two_LogDatemodel.addElement(LogOne1);}
                    }
                    two_log.setModel(two_LogDatemodel);
                }

                date = three.getText();
                if(!("".equals(date))){
                    if(date.length() < 2){date = "0"+date;}
                    String LogOne[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+searchMonth+" "+date+" "+searchYear+"'", "schName");
                    three_LogDatemodel.removeAllElements();
                    for (String LogOne1 : LogOne) {
                        if(!"".equals(LogOne1)){three_LogDatemodel.addElement(LogOne1);}
                    }
                    three_log.setModel(three_LogDatemodel);
                }

                date = four.getText();
                if(!("".equals(date))){
                    if(date.length() < 2){date = "0"+date;}
                    String LogOne[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+searchMonth+" "+date+" "+searchYear+"'", "schName");
                    four_LogDatemodel.removeAllElements();
                    for (String LogOne1 : LogOne) {
                        if(!"".equals(LogOne1)){four_LogDatemodel.addElement(LogOne1);}
                    }
                    four_log.setModel(four_LogDatemodel);
                }

                date = five.getText();
                if(!("".equals(date))){
                    if(date.length() < 2){date = "0"+date;}
                    String LogOne[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+searchMonth+" "+date+" "+searchYear+"'", "schName");
                    five_LogDatemodel.removeAllElements();
                    for (String LogOne1 : LogOne) {
                        if(!"".equals(LogOne1)){five_LogDatemodel.addElement(LogOne1);}
                    }
                    five_log.setModel(five_LogDatemodel);
                }

                date = six.getText();
                if(!("".equals(date))){
                    if(date.length() < 2){date = "0"+date;}
                    String LogOne[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+searchMonth+" "+date+" "+searchYear+"'", "schName");
                    six_LogDatemodel.removeAllElements();
                    for (String LogOne1 : LogOne) {
                        if(!"".equals(LogOne1)){six_LogDatemodel.addElement(LogOne1);}
                    }
                    six_log.setModel(six_LogDatemodel);
                }

                date = seven.getText();
                if(!("".equals(date))){
                    if(date.length() < 2){date = "0"+date;}
                    String LogOne[] = DQ.arrayFetch("dailylogschedule", "Select * from dailylogschedule where Date='"+searchMonth+" "+date+" "+searchYear+"'", "schName");
                    seven_LogDatemodel.removeAllElements();
                    for (String LogOne1 : LogOne) {
                        if(!"".equals(LogOne1)){seven_LogDatemodel.addElement(LogOne1);}
                    }
                    seven_log.setModel(seven_LogDatemodel);
                }
            }catch(DateTimeParseException e){infoBox("Make sure all data input is correct.", "SEARCH ERROR", CLOSED_OPTION);}
        }
    }
    
    
    public void weeklyAttendanceRecords(String dateWeek) throws ClassNotFoundException, SQLException{
        
        String searchMonth = DateWDR_in.getText();
        String searchYear = YearWDR_in.getText();
        String searchID = IDnumberWDR_in.getText();
        String Month;
        
        if("January".equalsIgnoreCase(searchMonth)){Month="01";}
        else if("February".equalsIgnoreCase(searchMonth)){Month="02";}
        else if("March".equalsIgnoreCase(searchMonth)){Month="03";}
        else if("April".equalsIgnoreCase(searchMonth)){Month="04";}
        else if("May".equalsIgnoreCase(searchMonth)){Month="05";}
        else if("June".equalsIgnoreCase(searchMonth)){Month="06";}
        else if("July".equalsIgnoreCase(searchMonth)){Month="07";}
        else if("August".equalsIgnoreCase(searchMonth)){Month="08";}
        else if("September".equalsIgnoreCase(searchMonth)){Month="09";}
        else if("October".equalsIgnoreCase(searchMonth)){Month="10";}
        else if("November".equalsIgnoreCase(searchMonth)){Month="11";}
        else if("December".equalsIgnoreCase(searchMonth)){Month="12";}
        else{Month="01";}
        
        String searchFrom = searchYear+"-"+Month+"-"+dateWeek;
        //Get the week dates
        LocalDate localDate = LocalDate.parse(searchFrom);
        String DaynameOfFirstday = String.valueOf(localDate.getDayOfWeek());
        System.out.println("day name: "+DaynameOfFirstday);
        int first = Integer.parseInt(dateWeek);
        lastday = String.valueOf(localDate.with(TemporalAdjusters.lastDayOfMonth()));
        String cut[] = lastday.split("-");
        lastdayOftheMonth = Integer.parseInt(cut[2]);
        System.out.println("last day: "+cut[2]);
        
        int date1,date2,date3,date4,date5,date6,date7;
        String day1,day2,day3,day4,day5,day6,day7;
        switch (DaynameOfFirstday) {
            case "SUNDAY":
                date1 = first;
                date2 = date1+1;
                date3=date2+1;
                date4=date3+1;
                date5=date4+1;
                date6=date5+1;
                date7=date6+1;
                day1 = String.valueOf(date1);
                day2 = String.valueOf(date2);
                day3 = String.valueOf(date3);
                day4 = String.valueOf(date4);
                day5 = String.valueOf(date5);
                day6 = String.valueOf(date6);
                day7 = String.valueOf(date7);
                break;
            case "MONDAY":
                date2 = first;
                date3=date2+1;
                date4=date3+1;
                date5=date4+1;
                date6=date5+1;
                date7=date6+1;
                day1 = "0";
                day2 = String.valueOf(date2);
                day3 = String.valueOf(date3);
                day4 = String.valueOf(date4);
                day5 = String.valueOf(date5);
                day6 = String.valueOf(date6);
                day7 = String.valueOf(date7);
                break;
            case "TUESDAY":
                date3=first;
                date4=date3+1;
                date5=date4+1;
                date6=date5+1;
                date7=date6+1;
                day1 = "0";
                day2 = "0";
                day3 = String.valueOf(date3);
                day4 = String.valueOf(date4);
                day5 = String.valueOf(date5);
                day6 = String.valueOf(date6);
                day7 = String.valueOf(date7);
                break;
            case "WEDNESDAY":
                date4=first;
                date5=date4+1;
                date6=date5+1;
                date7=date6+1;
                day1 = "0";
                day2 = "0";
                day3 = "0";
                day4 = String.valueOf(date4);
                day5 = String.valueOf(date5);
                day6 = String.valueOf(date6);
                day7 = String.valueOf(date7);
                break;
            case "THURSDAY":
                date5=first;
                date6=date5+1;
                date7=date6+1;
                day1 = "0";
                day2 = "0";
                day3 = "0";
                day4 = "0";
                day5 = String.valueOf(date5);
                day6 = String.valueOf(date6);
                day7 = String.valueOf(date7);
                break;
            case "FRIDAY":
                date6=first;
                date7=date6+1;
                day1 = "0";
                day2 = "0";
                day3 = "0";
                day4 = "0";
                day5 = "0";
                day6 = String.valueOf(date6);
                day7 = String.valueOf(date7);
                break;
            case "SATURDAY":
                date7=1;
                day1 = "0";
                day2 = "0";
                day3 = "0";
                day4 = "0";
                day5 = "0";
                day6 = "0";
                day7 = String.valueOf(date7);
                break;
            default:
                day1 = "0";
                day2 = "0";
                day3 = "0";
                day4 = "0";
                day5 = "0";
                day6 = "0";
                day7 = "0";
                break;
        }
        int ii = 0;
        
            if(checkLimit(day1) || "0".equals(day1)){one.setText("");}else{one.setText(day1);}
            if(checkLimit(day2) || "0".equals(day2)){two.setText("");}else{two.setText(day2);}
            if(checkLimit(day3) || "0".equals(day3)){three.setText("");}else{three.setText(day3);}
            if(checkLimit(day4) || "0".equals(day4)){four.setText("");}else{four.setText(day4);}
            if(checkLimit(day5) || "0".equals(day5)){five.setText("");}else{five.setText(day5);}
            if(checkLimit(day6) || "0".equals(day6)){six.setText("");}else{six.setText(day6);}
            if(checkLimit(day7) || "0".equals(day7)){seven.setText("");}else{seven.setText(day7);}
            
            weekNum.setText("WEEK "+weekNumber);
            
            String[] dates = {day1,day2,day3,day4,day5,day6,day7};
            String[] logName = { String.valueOf(one_log.getModel().getSelectedItem()),String.valueOf(two_log.getModel().getSelectedItem()),String.valueOf(three_log.getModel().getSelectedItem()),String.valueOf(four_log.getModel().getSelectedItem()),
                                 String.valueOf(five_log.getModel().getSelectedItem()),String.valueOf(six_log.getModel().getSelectedItem()),String.valueOf(seven_log.getModel().getSelectedItem())};
            ReportsDTM = new DefaultTableModel(new String[]{"ID NUMBER","LOG","AM","PM","AM","PM","AM","PM","AM","PM","AM","PM","AM","PM","AM","PM"},0);
            //fetch Personnels
            String IDnums[] = DQ.arrayFetch("personnels", "Select * from personnels", "IDnumber");
            if("ID NUMBER".equals(searchID)){
                for(int i=0;i<IDnums.length;i++){

                    String MiD1[] = new String[15], AiD1[] = new String[15];
                    for(int k=0;k<7;k++){ //get all Mi and Ai per ID
                        String Log;

                        if(!"".equals(logName[k])){ Log = " and Log_Name='"+logName[k]+"'";}
                        else{Log ="";}

                        if("0".equals(dates[k])){ MiD1[k] = ""; AiD1[k] = "";}
                        else{
                            if(dates[k].length() < 2){dates[k] = "0"+dates[k];}
                            String D = searchMonth + " " + dates[k] + " " + searchYear;
                            String A = DQ.fetch("Select * from checkinout where Date='"+D+"' and Check_type='Mi' and IDnumber='"+IDnums[i]+"'"+Log+"", "LogTime");
                            String B = DQ.fetch("Select * from checkinout where Date='"+D+"' and Check_type='Ai' and IDnumber='"+IDnums[i]+"'"+Log+"", "LogTime");
                            
                            if("".equals(A) || A==null){
                                MiD1[k] = A;
                            }else{
                                String ACut[] = A.split("\\s");
                                MiD1[k] = ACut[0];
                            }
                            
                            if("".equals(B) || B==null){
                                AiD1[k] = B;
                            }else{
                                String BCut[] = B.split("\\s");
                                AiD1[k] = BCut[0];
                            }
                        }
                    }
                    ReportsDTM.addRow(new Object[]{IDnums[i],"IN",MiD1[0],AiD1[0],MiD1[1],AiD1[1],MiD1[2],AiD1[2],MiD1[3],AiD1[3],MiD1[4],AiD1[4],MiD1[5],AiD1[5],MiD1[6],AiD1[6],MiD1[7],AiD1[7]});

                    for(int j = 0; j< dates.length ; j++){//get all Mo and Ao per ID
                        String Log;

                        if(!"".equals(logName[j])){ Log = " and Log_Name='"+logName[j]+"'";}
                        else{Log ="";}

                        if("0".equals(dates[j])){ MiD1[j] = ""; AiD1[j] = "";}
                        else{
                            if(dates[j].length() < 2){dates[j] = "0"+dates[j];}
                            String D = searchMonth + " " + dates[j] + " " + searchYear;
                            String A = DQ.fetch("Select * from checkinout where Date='"+D+"' and Check_type='Mo' and IDnumber='"+IDnums[i]+"'"+Log+"", "LogTime");
                            String B = DQ.fetch("Select * from checkinout where Date='"+D+"' and Check_type='Ao' and IDnumber='"+IDnums[i]+"'"+Log+"", "LogTime");
                            if("".equals(A) || A==null){
                                MiD1[j] = A;
                            }else{
                                String ACut[] = A.split("\\s");
                                MiD1[j] = ACut[0];
                            }
                            
                            if("".equals(B) || B==null){
                                AiD1[j] = B;
                            }else{
                                String BCut[] = B.split("\\s");
                                AiD1[j] = BCut[0];
                            }
                        
                        }
                    }
                    ReportsDTM.addRow(new Object[]{IDnums[i],"OUT",MiD1[0],AiD1[0],MiD1[1],AiD1[1],MiD1[2],AiD1[2],MiD1[3],AiD1[3],MiD1[4],AiD1[4],MiD1[5],AiD1[5],MiD1[6],AiD1[6],MiD1[7],AiD1[7]});
                }
            }else{
                boolean exist = false;
                for(int x=0;x<IDnums.length;x++){ if(IDnums[x].equals(searchID)){exist = true; break;}}
                if(exist){
                    String MiD1[] = new String[15], AiD1[] = new String[15];
                    for(int k=0;k<7;k++){ //get all Mi and Ai per ID
                        String Log;

                        if(!"".equals(logName[k])){ Log = " and Log_Name='"+logName[k]+"'";}
                        else{Log ="";}

                        if("0".equals(dates[k])){ MiD1[k] = ""; AiD1[k] = "";}
                        else{
                            if(dates[k].length() < 2){dates[k] = "0"+dates[k];}
                            String D = searchMonth + " " + dates[k] + " " + searchYear;
                            String A = DQ.fetch("Select * from checkinout where Date='"+D+"' and Check_type='Mi' and IDnumber='"+searchID+"'"+Log+"", "LogTime");
                            String B = DQ.fetch("Select * from checkinout where Date='"+D+"' and Check_type='Ai' and IDnumber='"+searchID+"'"+Log+"", "LogTime");
                            if("".equals(A) || A==null){
                                MiD1[k] = A;
                            }else{
                                String ACut[] = A.split("\\s");
                                MiD1[k] = ACut[0];
                            }
                            
                            if("".equals(B) || B==null){
                                AiD1[k] = B;
                            }else{
                                String BCut[] = B.split("\\s");
                                AiD1[k] = BCut[0];
                            }
                        }
                    }
                    ReportsDTM.addRow(new Object[]{searchID,"IN",MiD1[0],AiD1[0],MiD1[1],AiD1[1],MiD1[2],AiD1[2],MiD1[3],AiD1[3],MiD1[4],AiD1[4],MiD1[5],AiD1[5],MiD1[6],AiD1[6],MiD1[7],AiD1[7]});

                    for(int j = 0; j< dates.length ; j++){//get all Mo and Ao per ID
                        String Log;

                        if(!"".equals(logName[j])){ Log = " and Log_Name='"+logName[j]+"'";}
                        else{Log ="";}

                        if("0".equals(dates[j])){ MiD1[j] = ""; AiD1[j] = "";}
                        else{
                            if(dates[j].length() < 2){dates[j] = "0"+dates[j];}
                            String D = searchMonth + " " + dates[j] + " " + searchYear;
                            String A = DQ.fetch("Select * from checkinout where Date='"+D+"' and Check_type='Mo' and IDnumber='"+searchID+"'"+Log+"", "LogTime");
                            String B = DQ.fetch("Select * from checkinout where Date='"+D+"' and Check_type='Ao' and IDnumber='"+searchID+"'"+Log+"", "LogTime");
                            if("".equals(A) || A==null){
                                MiD1[j] = A;
                            }else{
                                String ACut[] = A.split("\\s");
                                MiD1[j] = ACut[0];
                            }
                            
                            if("".equals(B) || B==null){
                                AiD1[j] = B;
                            }else{
                                String BCut[] = B.split("\\s");
                                AiD1[j] = BCut[0];
                            }
                        }
                    }
                    ReportsDTM.addRow(new Object[]{searchID,"OUT",MiD1[0],AiD1[0],MiD1[1],AiD1[1],MiD1[2],AiD1[2],MiD1[3],AiD1[3],MiD1[4],AiD1[4],MiD1[5],AiD1[5],MiD1[6],AiD1[6],MiD1[7],AiD1[7]});
               }else{
                    
                }
            }
            WeeklyReport_table.setModel(ReportsDTM);
            tableHeader_custom(WeeklyReport_table);
            WeeklyReport_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tableColumnSize(WeeklyReport_table, 1, 40);
            tableColumnSize(WeeklyReport_table, 0, 90);
            for(int x=2;x<16;x++){
                tableColumnSize(WeeklyReport_table, x, 52);
            }
            DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
            cellRender.setHorizontalAlignment(JLabel.CENTER);
            for(int z=2;z<16;z++){
                WeeklyReport_table.getColumnModel().getColumn(z).setCellRenderer(cellRender);
            }
            
    }
    
    public boolean checkLimit(String value){
        int val = Integer.parseInt(value);
        return val > lastdayOftheMonth;
    }
    
    public void dailyAttendanceRecords() throws ClassNotFoundException, SQLException{
        tableHeader_custom(DailyReport_table);
        SimpleDateFormat df = new SimpleDateFormat("MMMM dd yyyy");
        String searchDate = df.format(DateSDR_in.getDate());
        String searchID = IDnumberSDR_in.getText();
        String searchLog = (String) SDRLogName_in.getModel().getSelectedItem();
        String byID = "";
        String LogType = DQ.fetch("dailylogschedule", " Where schName='"+searchLog+"' and Date='"+searchDate+"'", "LogIO_cycle");
        String IDmatched = DQ.fetch("personnels", " where IDnumber='"+searchID+"'", "IDnumber");
        
        if("".equals(searchLog) || searchLog == null){
            ReportsDTM = new DefaultTableModel(new String[]{""},0);
            ReportsDTM.addRow(new Object[]{"DATA DON'T EXIST"});
            DailyReport_table.setModel(ReportsDTM);
        }
        else{
            if(IDmatched == null && !"ID Number".equals(searchID)){
                ReportsDTM = new DefaultTableModel(new String[]{""},0);
                ReportsDTM.addRow(new Object[]{"DATA DON'T EXIST"});
                DailyReport_table.setModel(ReportsDTM);
            }else{
                if("Morning In".equals(LogType)){
                    if(!"ID Number".equals(searchID)){
                        ReportsDTM = new DefaultTableModel(new String[]{"ID NUMBER","NAME","MORNING IN"},0);
                        byID = " and IDnumber='"+searchID+"'";
                        String name = DQ.fetch("Select concat(Lname,', ',Fname,' ',MI) as Name from personnels where IDnumber='"+searchID+"'", "Name");  
                        String time = DQ.fetch("Select * from checkinout Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Mi'"+byID+"", "LogTime");
                        ReportsDTM.addRow(new Object[]{searchID,name,time});
                        
                        DailyReport_table.setModel(ReportsDTM);
                        tableHeader_custom(DailyReport_table);
                        DailyReport_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(JLabel.CENTER);
                        DailyReport_table.getColumnModel().getColumn(2).setCellRenderer(cellRender);
                        
                    }else{

                        ReportsDTM = new DefaultTableModel(new String[]{"ID NUMBER","NAME","MORNING IN"},0);
                        String allLogID[] = DQ.arrayFetch("personnels","Select * from personnels", "IDnumber");
                        int i;
                        String Name[] = new String[allLogID.length];
                        String Time[] = new String[allLogID.length];
                        if(!(allLogID.length == 0)){
                            for(i=0;i<allLogID.length;i++){
                                Name[i] = DQ.fetch("Select concat(Lname,', ',Fname,' ',MI) as Name from personnels where IDnumber='"+allLogID[i]+"'", "Name");
                                Time[i] = DQ.fetch("Select * from checkinout where IDnumber='"+allLogID[i]+"' and Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Mi'", "LogTime");
                            }

                            for(i=0;i<allLogID.length;i++){
                                 ReportsDTM.addRow(new Object[]{allLogID[i],Name[i],Time[i]});
                            }
                        }
                        
                        DailyReport_table.setModel(ReportsDTM);
                        tableHeader_custom(DailyReport_table);
                        DailyReport_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(JLabel.CENTER);
                        DailyReport_table.getColumnModel().getColumn(2).setCellRenderer(cellRender);
                        
                    }
                }else if("Afternoon In".equals(LogType)){
                    if(!"ID Number".equals(searchID)){
                        ReportsDTM = new DefaultTableModel(new String[]{"ID NUMBER","NAME","AFTERNOON IN"},0);
                        byID = " and IDnumber='"+searchID+"'";
                        String name = DQ.fetch("Select concat(Lname,', ',Fname,' ',MI) as Name from personnels where IDnumber='"+searchID+"'", "Name");  
                        String time = DQ.fetch("Select * from checkinout Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Ai'"+byID+"", "LogTime");
                        ReportsDTM.addRow(new Object[]{searchID,name,time});
                        
                        DailyReport_table.setModel(ReportsDTM);
                        tableHeader_custom(DailyReport_table);
                        DailyReport_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(JLabel.CENTER);
                        DailyReport_table.getColumnModel().getColumn(2).setCellRenderer(cellRender);
                        
                    }else{

                        ReportsDTM = new DefaultTableModel(new String[]{"ID NUMBER","NAME","AFTERNOON IN"},0);
                        String allLogID[] = DQ.arrayFetch("personnels","Select * from personnels", "IDnumber");
                        int i;
                        String Name[] = new String[allLogID.length];
                        String Time[] = new String[allLogID.length];
                        if(!(allLogID.length == 0)){
                            for(i=0;i<allLogID.length;i++){
                                Name[i] = DQ.fetch("Select concat(Lname,', ',Fname,' ',MI) as Name from personnels where IDnumber='"+allLogID[i]+"'", "Name");
                                Time[i] = DQ.fetch("Select * from checkinout where IDnumber='"+allLogID[i]+"' and Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Ai'", "LogTime");
                            }

                            for(i=0;i<allLogID.length;i++){
                                 ReportsDTM.addRow(new Object[]{allLogID[i],Name[i],Time[i]});
                            }
                        }
                        
                        DailyReport_table.setModel(ReportsDTM);
                        tableHeader_custom(DailyReport_table);
                        DailyReport_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(JLabel.CENTER);
                        DailyReport_table.getColumnModel().getColumn(2).setCellRenderer(cellRender);
                        
                    }
                }else if("Morning In-Out".equals(LogType)){
                    if(!"ID Number".equals(searchID)){
                        ReportsDTM = new DefaultTableModel(new String[]{"ID NUMBER","NAME","MORNING IN","MORNING OUT"},0);
                        byID = " and IDnumber='"+searchID+"'";
                        String name = DQ.fetch("Select concat(Lname,', ',Fname,' ',MI) as Name from personnels where IDnumber='"+searchID+"'", "Name");  
                        String timein = DQ.fetch("Select * from checkinout Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Mi'"+byID+"", "LogTime");
                        String timeout = DQ.fetch("Select * from checkinout Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Mo'"+byID+"", "LogTime");
                        ReportsDTM.addRow(new Object[]{searchID,name,timein,timeout});
                        
                        DailyReport_table.setModel(ReportsDTM);
                        tableHeader_custom(DailyReport_table);
                        DailyReport_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(JLabel.CENTER);
                        DailyReport_table.getColumnModel().getColumn(2).setCellRenderer(cellRender);
                        DailyReport_table.getColumnModel().getColumn(3).setCellRenderer(cellRender);
                        
                    }else{
                        ReportsDTM = new DefaultTableModel(new String[]{"ID NUMBER","NAME","MORNING IN","MORNING OUT"},0);
                        int numberOfPersonnels = DQ.Counting("Select * from personnels");
                        String allIDDnumber[] = DQ.arrayFetch("personnels","Select * from personnels", "IDnumber");
                        String Login, Name, Logout;

                        for(int i=0;i<numberOfPersonnels;i++){
                            Name = DQ.fetch("Select concat(Lname,', ',Fname,' ',MI) as Name from personnels where IDnumber='"+allIDDnumber[i]+"'", "Name");  
                            Login = DQ.fetch("checkinout","Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Mi' and IDnumber='"+allIDDnumber[i]+"'", "LogTime");
                            Logout = DQ.fetch("checkinout","Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Mo' and IDnumber='"+allIDDnumber[i]+"'", "LogTime");
                            ReportsDTM.addRow(new Object[]{allIDDnumber[i],Name,Login,Logout});
                        }
                        
                        DailyReport_table.setModel(ReportsDTM);
                        tableHeader_custom(DailyReport_table);
                        DailyReport_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(JLabel.CENTER);
                        DailyReport_table.getColumnModel().getColumn(2).setCellRenderer(cellRender);
                        DailyReport_table.getColumnModel().getColumn(3).setCellRenderer(cellRender);
                        
                    }
                }else if("Afternoon In-Out".equals(LogType)){
                    if(!"ID Number".equals(searchID)){
                        ReportsDTM = new DefaultTableModel(new String[]{"ID NUMBER","NAME","AFTERNOON IN","AFTERNOON OUT"},0);
                        byID = " and IDnumber='"+searchID+"'";
                        String name = DQ.fetch("Select concat(Lname,', ',Fname,' ',MI) as Name from personnels where IDnumber='"+searchID+"'", "Name");  
                        String timein = DQ.fetch("Select * from checkinout Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Ai'"+byID+"", "LogTime");
                        String timeout = DQ.fetch("Select * from checkinout Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Ao'"+byID+"", "LogTime");
                        ReportsDTM.addRow(new Object[]{searchID,name,timein,timeout});
                        
                        DailyReport_table.setModel(ReportsDTM);
                        tableHeader_custom(DailyReport_table);
                        DailyReport_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(JLabel.CENTER);
                        DailyReport_table.getColumnModel().getColumn(2).setCellRenderer(cellRender);
                        DailyReport_table.getColumnModel().getColumn(3).setCellRenderer(cellRender);
                        
                    }else{
                        ReportsDTM = new DefaultTableModel(new String[]{"ID NUMBER","NAME","AFTERNOON IN","AFTERNOON OUT"},0);
                        int numberOfPersonnels = DQ.Counting("Select * from personnels");
                        String allIDDnumber[] = DQ.arrayFetch("personnels","Select * from personnels", "IDnumber");
                        String Login, Name, Logout;

                        for(int i=0;i<numberOfPersonnels;i++){
                            Name = DQ.fetch("Select concat(Lname,', ',Fname,' ',MI) as Name from personnels where IDnumber='"+allIDDnumber[i]+"'", "Name");  
                            Login = DQ.fetch("Select * from checkinout Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Ai' and IDnumber='"+allIDDnumber[i]+"'", "LogTime");
                            Logout = DQ.fetch("Select * from checkinout Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Ao' and IDnumber='"+allIDDnumber[i]+"'", "LogTime");
                            ReportsDTM.addRow(new Object[]{allIDDnumber[i],Name,Login,Logout});
                        }
                        
                        DailyReport_table.setModel(ReportsDTM);
                        tableHeader_custom(DailyReport_table);
                        DailyReport_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(JLabel.CENTER);
                        DailyReport_table.getColumnModel().getColumn(2).setCellRenderer(cellRender);
                        DailyReport_table.getColumnModel().getColumn(3).setCellRenderer(cellRender);
                        
                    }
                }else if("Morning In and Afternoon Out".equals(LogType)){
                    if(!"ID Number".equals(searchID)){
                        ReportsDTM = new DefaultTableModel(new String[]{"ID NUMBER","NAME","MORNING IN","AFTERNOON OUT"},0);
                        byID = " and IDnumber='"+searchID+"'";
                        String name = DQ.fetch("Select concat(Lname,', ',Fname,' ',MI) as Name from personnels where IDnumber='"+searchID+"'", "Name");  
                        String timein = DQ.fetch("Select * from checkinout Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Mi'"+byID+"", "LogTime");
                        String timeout = DQ.fetch("Select * from checkinout Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Ao'"+byID+"", "LogTime");
                        ReportsDTM.addRow(new Object[]{searchID,name,timein,timeout});
                        
                        DailyReport_table.setModel(ReportsDTM);
                        tableHeader_custom(DailyReport_table);
                        DailyReport_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(JLabel.CENTER);
                        DailyReport_table.getColumnModel().getColumn(2).setCellRenderer(cellRender);
                        DailyReport_table.getColumnModel().getColumn(3).setCellRenderer(cellRender);
                        
                        
                    }else{
                        ReportsDTM = new DefaultTableModel(new String[]{"ID NUMBER","NAME","AFTERNOON IN","AFTERNOON OUT"},0);
                        int numberOfPersonnels = DQ.Counting("Select * from personnels");
                        String allIDDnumber[] = DQ.arrayFetch("personnels","Select * from personnels", "IDnumber");
                        String Login, Name, Logout;

                        for(int i=0;i<numberOfPersonnels;i++){
                            Name = DQ.fetch("Select concat(Lname,', ',Fname,' ',MI) as Name from personnels where IDnumber='"+allIDDnumber[i]+"'", "Name");  
                            Login = DQ.fetch("checkinout","Select * from checkinout Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Mi' and IDnumber='"+allIDDnumber[i]+"'", "LogTime");
                            Logout = DQ.fetch("checkinout","Select * from checkinout Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Ao' and IDnumber='"+allIDDnumber[i]+"'", "LogTime");
                            ReportsDTM.addRow(new Object[]{allIDDnumber[i],Name,Login,Logout});
                        }
                        
                        DailyReport_table.setModel(ReportsDTM);
                        tableHeader_custom(DailyReport_table);
                        DailyReport_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(JLabel.CENTER);
                        DailyReport_table.getColumnModel().getColumn(2).setCellRenderer(cellRender);
                        DailyReport_table.getColumnModel().getColumn(3).setCellRenderer(cellRender);
                        
                    }
                }else if("Morning In-Out and Afternoon In-Out".equals(LogType)){
                    if(!"ID Number".equals(searchID)){
                        ReportsDTM = new DefaultTableModel(new String[]{"ID NUMBER","NAME","MORNING IN","MORNING OUT","AFTERNOON IN","AFTERNOON OUT"},0);
                        byID = " and IDnumber='"+searchID+"'";
                        String name = DQ.fetch("Select concat(Lname,', ',Fname,' ',MI) as Name from personnels where IDnumber='"+searchID+"'", "Name");  
                        String Mtimein = DQ.fetch("Select * from checkinout Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Mi'"+byID+"", "LogTime");
                        String Mtimeout = DQ.fetch("Select * from checkinout Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Mo'"+byID+"", "LogTime");                
                        String Atimein = DQ.fetch("Select * from checkinout Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Ai'"+byID+"", "LogTime");
                        String Atimeout = DQ.fetch("Select * from checkinout Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Ao'"+byID+"", "LogTime");
                        ReportsDTM.addRow(new Object[]{searchID,name,Mtimein,Mtimeout,Atimein,Atimeout});
                        
                        DailyReport_table.setModel(ReportsDTM);
                        tableHeader_custom(DailyReport_table);
                        DailyReport_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        tableColumnSize(DailyReport_table, 0, 177);
                        tableColumnSize(DailyReport_table, 1, 200);
                        for(int x=1;x<6;x++){
                        tableColumnSize(DailyReport_table, x, 125);
                        }
                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(JLabel.CENTER);

                        for(int z=2;z<6;z++){
                            DailyReport_table.getColumnModel().getColumn(z).setCellRenderer(cellRender);
                        }
                    }else{
                        ReportsDTM = new DefaultTableModel(new String[]{"ID NUMBER","NAME","MORNING IN","MORNING OUT","AFTERNOON IN","AFTERNOON OUT"},0);
                        int numberOfPersonnels = DQ.Counting("Select * from personnels");
                        String allIDDnumber[] = DQ.arrayFetch("personnels","Select * from personnels", "IDnumber");
                        String MLogin, ALogin, Name, MLogout, ALogout;

                        for(int i=0;i<numberOfPersonnels;i++){
                            Name = DQ.fetch("Select concat(Lname,', ',Fname,' ',MI) as Name from personnels where IDnumber='"+allIDDnumber[i]+"'", "Name");  
                            MLogin = DQ.fetch("checkinout","Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Mi' and IDnumber='"+allIDDnumber[i]+"'", "LogTime");
                            MLogout = DQ.fetch("checkinout","Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Mo' and IDnumber='"+allIDDnumber[i]+"'", "LogTime");
                            ALogin = DQ.fetch("checkinout","Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Ai' and IDnumber='"+allIDDnumber[i]+"'", "LogTime");
                            ALogout = DQ.fetch("checkinout","Where Log_Name='"+searchLog+"' and Date='"+searchDate+"' and Check_type='Ao' and IDnumber='"+allIDDnumber[i]+"'", "LogTime");

                            ReportsDTM.addRow(new Object[]{allIDDnumber[i],Name,MLogin,MLogout,ALogin,ALogout});
                        }
                        
                        DailyReport_table.setModel(ReportsDTM);
                        tableHeader_custom(DailyReport_table);
                        DailyReport_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        tableColumnSize(DailyReport_table, 0, 177);
                        tableColumnSize(DailyReport_table, 1, 200);
                        for(int x=1;x<6;x++){
                        tableColumnSize(DailyReport_table, x, 125);
                        }
                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(JLabel.CENTER);

                        for(int z=2;z<6;z++){
                            DailyReport_table.getColumnModel().getColumn(z).setCellRenderer(cellRender);
                        }
                    }
                }else{
                        DefaultTableModel ReportsDTMnull = new DefaultTableModel(new String[]{""},0);
                        ReportsDTMnull.addRow(new Object[]{"NO DATA"});
                        DailyReport_table.setModel(ReportsDTMnull);
                }
            }
        }
    }         
    
    public void DisplayDailySchedule(){
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"CRN","DATE","NAME","WORK STATUS","ENTRY","STATUS"}, 0);
        
         try {
                Class.forName("com.mysql.cj.jdbc.Driver");
        
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartlogdb?useTimezone=true&serverTimezone=UTC&useSSL=false","root","Anonsawon27");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM dailylogschedule");
                
                while(rs.next()){
                    String ID = rs.getString("sch_ID");
                    String Date = rs.getString("Date");
                    String Name = rs.getString("schName");
                    String Wsate = rs.getString("Work_status");
                    String LC = rs.getString("LogIO_cycle");
                    String Stat = rs.getString("Status");
                    dtm.addRow(new Object[]{ID,Date,Name,Wsate,LC,Stat});
                }
                Schedule_table.setModel(dtm);
                tableHeader_custom(Schedule_table);
                Schedule_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tableColumnSize(Schedule_table, 0, 50);
                tableColumnSize(Schedule_table, 1, 200);
                tableColumnSize(Schedule_table, 2, 150);
                tableColumnSize(Schedule_table, 3, 100);
                tableColumnSize(Schedule_table, 4, 300);
                tableColumnSize(Schedule_table, 5, 100);
         }catch (ClassNotFoundException | SQLException ex){
                    System.out.println(ex);
                }
    }
    
    public void Display_LeaveRegister(String cmd){
        
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"CRN","ID NUMBER","LEAVE EXCUSED","DATE FILED","START","END"}, 0);
        
         try {
                Class.forName("com.mysql.cj.jdbc.Driver");
        
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartlogdb?useTimezone=true&serverTimezone=UTC&useSSL=false","root","Anonsawon27");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM leaveclass"+cmd);
                
                while(rs.next()){
                    String ID = rs.getString("LeaveID");
                    String Ex = rs.getString("LeaveName");
                    String IDn = rs.getString("IDnumber");
                    String DFiled = rs.getString("DateFiled");
                    String Ds = rs.getString("StartDate");
                    String De = rs.getString("EndDate");
                    dtm.addRow(new Object[]{ID,IDn,Ex,DFiled,Ds,De});
                }
                Leave_table.setModel(dtm);
                tableHeader_custom(Leave_table);
                Leave_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tableColumnSize(Leave_table, 0, 50);
                tableColumnSize(Leave_table, 1, 100);
                tableColumnSize(Leave_table, 2, 200);
                tableColumnSize(Leave_table, 3, 150);
                tableColumnSize(Leave_table, 4, 150);
                tableColumnSize(Leave_table, 5, 150);
         }catch (ClassNotFoundException | SQLException ex){
                    System.out.println(ex);
                }
    }
    
    public void AllPersonnels(){
    
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"ID","NAME","GENDER","AGE","POSITION","BT NAME","BT ADDRESS"}, 0);
        
         try {
                Class.forName("com.mysql.cj.jdbc.Driver");
        
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartlogdb?useTimezone=true&serverTimezone=UTC&useSSL=false","root","Anonsawon27");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT personnels.IDnumber, personnels.Lname, personnels.HireDate, "
                        + "personnels.Fname, personnels.MI, personnels.Gender, personnels.Age, personnels.Preveledge,"
                        + "departments.Position, devices.BTname, devices.BTaddress, personnels.Password FROM personnels "
                        + "INNER JOIN devices ON devices.Device_ID = personnels.Device_ID "
                        + "INNER JOIN departments ON departments.id_Departments = personnels.Dpt_ID");
                
                while(rs.next()){
                    String ID = rs.getString("personnels.IDnumber");
                    String Ln = rs.getString("personnels.Lname");
                    String Fn = rs.getString("personnels.Fname");
                    String Mi = rs.getString("personnels.MI");
                    String Gndr = rs.getString("personnels.Gender");
                    String Age = rs.getString("personnels.Age");
                    //String Pswrd = rs.getString("personnels.Password");
                    String Name = Ln+", "+Fn+" "+Mi+".";
                    if(" ".equals(Mi)||"".equals(Mi)){Name = Ln+", "+Fn+" "+Mi+"";}
                    String Pos = rs.getString("departments.Position");
                    String BTn = rs.getString("devices.BTname");
                    String BTa = rs.getString("devices.BTaddress"); 
                    
                    dtm.addRow(new Object[]{ID,Name,Gndr,Age,Pos,BTn,BTa});
                }
                Personnels_table.setModel(dtm);
                tableHeader_custom(Personnels_table);
                Personnels_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tableColumnSize(Personnels_table, 0, 100);
                tableColumnSize(Personnels_table, 1, 200);
                tableColumnSize(Personnels_table, 2, 90);
                tableColumnSize(Personnels_table, 3, 50);
                tableColumnSize(Personnels_table, 4, 145);
                tableColumnSize(Personnels_table, 5, 150);
                tableColumnSize(Personnels_table, 6, 150);
                
         }catch (ClassNotFoundException | SQLException ex){
                    System.out.println(ex);
                }
    }
    
    public void perDepartmentView(){
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Department","IDnumber","Position"}, 0);
        String Department = DepartmentName_in.getText();
        if(Department.isEmpty()){
            infoBox("No Department selected","ACTION ERROR",CLOSED_OPTION);
        }else{
         try {
                Class.forName("com.mysql.cj.jdbc.Driver");
        
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartlogdb?useTimezone=true&serverTimezone=UTC&useSSL=false","root","Anonsawon27");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM departments where Department = '"+Department+"'");
                
                while(rs.next()){
                    String DVD = rs.getString("Department");
                    String DVIDn = rs.getString("IDnumber");
                    String DVP = rs.getString("Position");
                    dtm.addRow(new Object[]{DVD,DVIDn,DVP});
                }
                Departments_table.setModel(dtm);
         }catch (ClassNotFoundException | SQLException ex){
                    System.out.println(ex);
                }
        }
    }
    
    public void DepartmentPositionView(String condition){
        DefaultTableModel DPTdtm = new DefaultTableModel(new String[]{"CRN","DEPARTMENT","POSITION"}, 0);
        
         try {
                Class.forName("com.mysql.cj.jdbc.Driver");
        
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartlogdb?useTimezone=true&serverTimezone=UTC&useSSL=false","root","Anonsawon27");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM positions"+condition+"");
                
                while(rs.next()){
                    String crn = rs.getString("Id");
                    String dpt = rs.getString("Department");
                    String dptP = rs.getString("Position");
                    
                    int psrnls = DQ.Counting("SELECT * FROM smartlogdb.departments where Department = '"+dpt+"' and Position = '"+dptP+"'");
                    String psrnl = String.valueOf(psrnls);
                    
                    DPTdtm.addRow(new Object[]{crn,dpt,dptP});
                }
                Departments_table.setModel(DPTdtm);
                tableHeader_custom(Departments_table);
                Departments_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tableColumnSize(Departments_table, 0, 50);
                tableColumnSize(Departments_table, 1, 200);
                tableColumnSize(Departments_table, 2, 248);
         }catch (ClassNotFoundException | SQLException ex){
                    System.out.println(ex);
                }
    }
    
    public void all_Logs(){
        
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"CRN","LOG NAME","DATE","STATUS"}, 0);
        
         try {
                Class.forName("com.mysql.cj.jdbc.Driver");
        
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartlogdb?useTimezone=true&serverTimezone=UTC&useSSL=false","root","Anonsawon27");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM dailylogschedule");
                
                while(rs.next()){
                    String LogID = rs.getString("sch_ID");
                    String LName = rs.getString("schName");
                    String LTime = rs.getString("Date");
                    String Lstat = rs.getString("Status");
                    dtm.addRow(new Object[]{LogID,LName,LTime,Lstat});
                }
                Logs_table.setModel(dtm);
                        tableHeader_custom(Logs_table);
                        Logs_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        tableColumnSize(Logs_table, 0, 50);
                        tableColumnSize(Logs_table, 1, 200);
                        tableColumnSize(Logs_table, 2, 200);
                        tableColumnSize(Logs_table, 3, 125);
                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(JLabel.CENTER);

                        for(int z=2;z<4;z++){
                            Logs_table.getColumnModel().getColumn(z).setCellRenderer(cellRender);
                        }
         }catch (ClassNotFoundException | SQLException ex){
                    System.out.println(ex);
                }
    }
    
    public void displayDepartments(){
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"CRN","DEPARTMENT","PERSONNELS","HEAD"}, 0);
        
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
        
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartlogdb?useTimezone=true&serverTimezone=UTC&useSSL=false","root","Anonsawon27");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM departmentclass");
                
                
                while(rs.next()){
                    String I = rs.getString("dept_ID");
                    String D = rs.getString("Department");
                    
                    int psrnls = DQ.Counting("SELECT * FROM smartlogdb.departments where Department = '"+D+"'");
                    String P = String.valueOf(psrnls);
                    
                    String H = rs.getString("Head");
                    dtm.addRow(new Object[]{I,D,P,H});
                }
                
                Departments_table.setModel(dtm);
                tableHeader_custom(Departments_table);
                Departments_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tableColumnSize(Departments_table, 0, 50);
                tableColumnSize(Departments_table, 1, 150);
                tableColumnSize(Departments_table, 2, 100);
                tableColumnSize(Departments_table, 3, 200);
                
         }catch (ClassNotFoundException | NullPointerException| SQLException ex){
                    System.out.println(ex);
         }
    }
   
    public void Shiftting(){
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"CRN","SHIFT DAY","ID NUMBER","PERSONNEL","START TIME","END TIME"}, 0);
        
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
        
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartlogdb?useTimezone=true&serverTimezone=UTC&useSSL=false","root","Anonsawon27");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM shiftclass");
                
                while(rs.next()){
                    String sI = rs.getString("Shift_ID");
                    String sD = rs.getString("Shift_Day");
                    String sID = rs.getString("IDnumber");
                    String sP = rs.getString("Personnel");
                    String sT = rs.getString("Start_Time");
                    String eT = rs.getString("End_Time");
                    dtm.addRow(new Object[]{sI,sD,sID,sP,sT,eT});
                }
                
                Shifts_table.setModel(dtm);
                tableHeader_custom(Shifts_table);
                Shifts_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tableColumnSize(Shifts_table, 0, 50);
                tableColumnSize(Shifts_table, 1, 100);
                tableColumnSize(Shifts_table, 2, 150);
                tableColumnSize(Shifts_table, 3, 200);
                tableColumnSize(Shifts_table, 4, 100);
                tableColumnSize(Shifts_table, 5, 100);
                
         }catch (ClassNotFoundException | NullPointerException| SQLException ex){
                    System.out.println(ex);
         }
    }
    
    private void Dashboard_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dashboard_btnActionPerformed
        Attendance_pane.setVisible(false);
        Personnels_pane.setVisible(false);
        Reports_pane.setVisible(false);
        About_pane.setVisible(false);
        Dashboard_pane.setVisible(true);
    }//GEN-LAST:event_Dashboard_btnActionPerformed

    private void Attendance_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Attendance_btnActionPerformed
        Dashboard_pane.setVisible(false);
        Personnels_pane.setVisible(false);
        Reports_pane.setVisible(false);
        About_pane.setVisible(false);
        Attendance_pane.setVisible(true);
        
       // AllAttendanceRecords();
       all_Logs();
       
       tableHeader_custom(Logs_table);
       tableHeader_custom(Leave_table);
       tableHeader_custom(Departments_table);
       tableHeader_custom(Shifts_table);
       tableHeader_custom(Schedule_table);
    }//GEN-LAST:event_Attendance_btnActionPerformed

    private void Reports_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Reports_btnActionPerformed
        Dashboard_pane.setVisible(false);
        Attendance_pane.setVisible(false);
        Personnels_pane.setVisible(false);
        About_pane.setVisible(false);
        Reports_pane.setVisible(true);
        
        Calendar cal = Calendar.getInstance();
        DateSDR_in.setDate(cal.getTime());
        weekNumber = 1;
        Logsmodel.removeAllElements();
        try {
            String SDRdate = String.valueOf(DateSDR_in.getDate());
            String Logs[] = DQ.arrayDataFetch("dailylogschedule", "dailylogschedule", " Where Date='"+SDRdate+"'", "schName");
            for(int i=0;i<Logs.length;i++){
                Logsmodel.addElement(Logs[i]);
            }
            SDRLogName_in.setModel(Logsmodel);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableHeader_custom(DailyReport_table);
        try {
            dailyAttendanceRecords();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_Reports_btnActionPerformed

    private void Personnels_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Personnels_btnActionPerformed
        Dashboard_pane.setVisible(false);
        Attendance_pane.setVisible(false);
        Reports_pane.setVisible(false);
        About_pane.setVisible(false);
        Personnels_pane.setVisible(true);
        
        prompt_Message.setVisible(false);
        tableHeader_custom(Personnels_table);
        try {
            //Display personnels on table next is department menu
            AllPersonnels();
            FetchDepartments();
            
            String dpt = (String) Department_in.getModel().getSelectedItem();
            DP_model.removeAllElements();
        
            String[] Positions = DQ.arrayDataFetch("positions where Department = '"+dpt+"'","positions", " where Department = '"+dpt+"'", "Position");
            int i=0;
            try{
                do{
                    DP_model.addElement(Positions[i]);
                    i++;
                }while(i<Positions.length);
            }catch(ArrayIndexOutOfBoundsException ex){}
            Position_in.setModel(DP_model);
            Date d = new Date();
            HireDate_in.setDate(d);
            
        } catch (ClassNotFoundException | SQLException | ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Personnels_btnActionPerformed

    private void AboutUs_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutUs_btnActionPerformed
        Dashboard_pane.setVisible(false);
        Attendance_pane.setVisible(false);
        Personnels_pane.setVisible(false);
        Reports_pane.setVisible(false);
        About_pane.setVisible(true);
        
    }//GEN-LAST:event_AboutUs_btnActionPerformed

    private void LogOut_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOut_btnActionPerformed
        try {
            boolean confirm = infoBox("Are you sure you want to log out?","WARNING",YES_NO_OPTION);
            if(confirm){
                DQ.CRUDE("update smartlogdb.personnels set Status = null where IDnumber = '"+CurrentUser+"'");
                this.setVisible(false);
                LF.setVisible(true);
            }
        } catch (ClassNotFoundException | SQLException ex) {}
        System.out.println("User Logged status updated.");
        
    }//GEN-LAST:event_LogOut_btnActionPerformed

    private void weeklyR_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weeklyR_btnActionPerformed
        dailyR_panel.setVisible(false);
        KensinaR_panel.setVisible(false);
        monthlyR_panel.setVisible(false);
        weeklyR_panel.setVisible(true);
        
        KensinaR_btn.setBackground(new Color(255,255,255));
        KensinaR_btn.setForeground(new Color(4,8,114));
        dailyR_btn.setBackground(new Color(255,255,255));
        dailyR_btn.setForeground(new Color(4,8,114));
        monthlyR_btn.setBackground(new Color(255,255,255));
        monthlyR_btn.setForeground(new Color(4,8,114));
        weeklyR_btn.setBackground(new Color(4,8,114));
        weeklyR_btn.setForeground(new Color(255,255,255));
        
        Calendar cal = Calendar.getInstance();
        DateWDR_in.setText(new SimpleDateFormat("MMMM").format(cal.getTime()));
        YearWDR_in.setText(new SimpleDateFormat("yyyy").format(cal.getTime()));
        
        try {
            weeklyAttendanceRecords("01");
            weekNumber = 1;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if("".equals(one.getText())){
            prev_btn.setEnabled(false);
        }else{prev_btn.setEnabled(true);}
    }//GEN-LAST:event_weeklyR_btnActionPerformed

    private void KensinaR_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KensinaR_btnActionPerformed
            monthlyR_panel.setVisible(false);
            dailyR_panel.setVisible(false);
            weeklyR_panel.setVisible(false);
            KensinaR_panel.setVisible(true);
            
            dailyR_btn.setBackground(new Color(255,255,255));
            dailyR_btn.setForeground(new Color(4,8,114));
            weeklyR_btn.setBackground(new Color(255,255,255));
            weeklyR_btn.setForeground(new Color(4,8,114));
            monthlyR_btn.setBackground(new Color(255,255,255));
            monthlyR_btn.setForeground(new Color(4,8,114));
            KensinaR_btn.setBackground(new Color(4,8,114));
            KensinaR_btn.setForeground(new Color(255,255,255));
            
            searchByLogDTR_btn.setEnabled(false);
            
    }//GEN-LAST:event_KensinaR_btnActionPerformed

    private void dailyR_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dailyR_btnActionPerformed
        weeklyR_panel.setVisible(false);
        KensinaR_panel.setVisible(false);
        monthlyR_panel.setVisible(false);
        dailyR_panel.setVisible(true);
        
        //button appearance
        KensinaR_btn.setBackground(new Color(255,255,255));
        KensinaR_btn.setForeground(new Color(4,8,114));
        weeklyR_btn.setBackground(new Color(255,255,255));
        weeklyR_btn.setForeground(new Color(4,8,114));
        dailyR_btn.setBackground(new Color(4,8,114));
        dailyR_btn.setForeground(new Color(255,255,255));
        monthlyR_btn.setForeground(new Color(4,8,114));
        monthlyR_btn.setBackground(new Color(255,255,255));
        
        Calendar cal = Calendar.getInstance();
        DateSDR_in.setDate(cal.getTime());
        Logsmodel.removeAllElements();
        try {
            String SDRdate = String.valueOf(DateSDR_in.getDate());
            String Logs[] = DQ.arrayDataFetch("dailylogschedule", "dailylogschedule", " Where Date='"+SDRdate+"'", "schName");
            for(int i=0;i<Logs.length;i++){
                Logsmodel.addElement(Logs[i]);
            }
            SDRLogName_in.setModel(Logsmodel);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableHeader_custom(DailyReport_table);
        try {
            dailyAttendanceRecords();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_dailyR_btnActionPerformed

    private void Delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_btnActionPerformed
        
        try {
            String IDtoDelete = ID_in.getText();
            if(IDtoDelete.isEmpty()){
                infoBox("Nothing is seleted to delete.","Error", CLOSED_OPTION);
            }else{
                boolean allow = verify_action("Delete");
                if(allow){
                    try {
                        DQ.CRUDE("Delete from smartlogdb.checkinout where CheckIO_ID>0 and IDnumber = '"+IDtoDelete+"'");
                        DQ.CRUDE("Delete from smartlogdb.devices where IDnumber = '"+IDtoDelete+"'");
                        DQ.CRUDE("Delete from departments where IDnumber = '"+IDtoDelete+"'");
                        DQ.CRUDE("Delete from personnels where IDnumber = '"+IDtoDelete+"'");

                        AllPersonnels();

                        prompt_Message.setVisible(true);
                        prompt_Message.setText("User "+IDtoDelete+" deleted successfuly.");
                        prompt_Message.setForeground(Color.green);
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{}
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Delete_btnActionPerformed

    private void PersonnelSearch_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PersonnelSearch_btnActionPerformed
        String ID_num = PersonnelSearch_in.getText();
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"ID","NAME","GENDER","AGE","POSITION","BT NAME","BT ADDRESS"}, 0);
        //String ID = null,Ln = null,Fn= null,Mi= null,Gndr= null,Age= null,Pswrd= null,Name= null,Psn= null,BTn= null,BTa= null;
        
        if("All ID Number".equals(ID_num)){
            AllPersonnels();
        }else{
            /*try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartlogdb?useTimezone=true&serverTimezone=UTC&useSSL=false","root","Anonsawon27");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM personnels WHERE IDnumber = '"+ID_num+"'");
            
            while(rs.next()){
            ID = rs.getString("IDnumber");
            Ln = rs.getString("Lname");
            Fn = rs.getString("Fname");
            Mi = rs.getString("MI");
            Gndr = rs.getString("Gender");
            Age = rs.getString("Age");
            Pswrd = rs.getString("Password");
            Name = Ln+", "+Fn+" "+Mi+".";
            }
            
            rs = stmt.executeQuery("SELECT * FROM devices WHERE IDnumber = '"+ID_num+"'");
            while(rs.next()){
            //Psn = rs.getString("Position");
            BTn = rs.getString("BTname");
            BTa = rs.getString("BTaddress");
            }
            
            rs = stmt.executeQuery("SELECT * FROM departments WHERE IDnumber = '"+ID_num+"'");
            while(rs.next()){
            Psn = rs.getString("Position");
            }
            
            dtm.addRow(new Object[]{ID,Name,Gndr,Age,Psn,BTn,BTa,Pswrd});
            
            Personnels_table.setModel(dtm);*/
        
         try {
                Class.forName("com.mysql.cj.jdbc.Driver");
        
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartlogdb?useTimezone=true&serverTimezone=UTC&useSSL=false","root","Anonsawon27");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT personnels.IDnumber, personnels.Lname, personnels.HireDate, "
                        + "personnels.Fname, personnels.MI, personnels.Gender, personnels.Age, personnels.Preveledge,"
                        + "departments.Position, devices.BTname, devices.BTaddress, personnels.Password FROM personnels "
                        + "INNER JOIN devices ON devices.Device_ID = personnels.Device_ID "
                        + "INNER JOIN departments ON departments.id_Departments = personnels.Dpt_ID Where personnels.IDnumber = '"+ID_num+"'");
                
                while(rs.next()){
                    String ID = rs.getString("personnels.IDnumber");
                    String Ln = rs.getString("personnels.Lname");
                    String Fn = rs.getString("personnels.Fname");
                    String Mi = rs.getString("personnels.MI");
                    String Gndr = rs.getString("personnels.Gender");
                    String Age = rs.getString("personnels.Age");
                    //String Pswrd = rs.getString("personnels.Password");
                    String Name = Ln+", "+Fn+" "+Mi+".";
                    if(" ".equals(Mi)||"".equals(Mi)){Name = Ln+", "+Fn+" "+Mi+"";}
                    String Pos = rs.getString("departments.Position");
                    String BTn = rs.getString("devices.BTname");
                    String BTa = rs.getString("devices.BTaddress"); 
                    
                    dtm.addRow(new Object[]{ID,Name,Gndr,Age,Pos,BTn,BTa});
                }
                Personnels_table.setModel(dtm);
                tableHeader_custom(Personnels_table);
                Personnels_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tableColumnSize(Personnels_table, 0, 100);
                tableColumnSize(Personnels_table, 1, 200);
                tableColumnSize(Personnels_table, 2, 90);
                tableColumnSize(Personnels_table, 3, 50);
                tableColumnSize(Personnels_table, 4, 145);
                tableColumnSize(Personnels_table, 5, 150);
                tableColumnSize(Personnels_table, 6, 150);
                
            }catch (ClassNotFoundException | SQLException ex){
                       System.out.println(ex);
            }
        }
    }//GEN-LAST:event_PersonnelSearch_btnActionPerformed

    private void ID_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ID_inActionPerformed
        
    }//GEN-LAST:event_ID_inActionPerformed

    private void BTscan_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTscan_btnActionPerformed
        
        try { 
            bluetoothQueries.Pairing();
        } catch (BluetoothStateException | InterruptedException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        BT_name = bluetoothQueries.BTdevice_name();
        BT_add = bluetoothQueries.BTdevice_address();
        //model.removeAllElements();
        for(int i=0;i<BT_name.length;i++){
            if("".equals(BT_name[i]) || BT_name[i] == null){}
            else{model.addElement(BT_name[i]);}
        }
        BTname.setModel(model);
    }//GEN-LAST:event_BTscan_btnActionPerformed

    private void Update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Update_btnActionPerformed
        String A,B1,B2,B3,C,D,E,F,G,H,I,J,K,commandPass;
        A = ID_in.getText();
        B1 = Lname_in.getText();
        B2 = Fname_in.getText();
        B3 = MI_in.getText();
        C = (String) Gender_in.getSelectedItem();
        D = Age_in.getText();
        E = (String) Position_in.getModel().getSelectedItem();
        F = (String) BTname.getSelectedItem();
        G = BTaddress.getText();
        H = new String(PersonnelPassword_in.getPassword());
        SimpleDateFormat Df = new SimpleDateFormat("MMMM dd yyyy");
        I = Df.format(HireDate_in.getDate());
        J = (String) Preveledge_in.getSelectedItem();
        K = (String) Department_in.getSelectedItem();
        String N[] = J.split("\\s");
        
        if("M.I.".equals(B3)){ B3 = " ";}
        if("DEVICE NAME".equals(F)){F="";}
        
        if(A.isEmpty() || B1.equalsIgnoreCase("Last name") || B2.equalsIgnoreCase("first name") || C.isEmpty() || H.isEmpty()){
            prompt_Message.setVisible(true);
            prompt_Message.setText("Fill in all fields");
            prompt_Message.setForeground(Color.red);
        }else{
            if(Integer.parseInt(D) < 18 || Integer.parseInt(D) > 70){}
            else{
                try {
                    String UserPreveledge[] = DQ.User_Preveledge(CurrentUser_IDnum).split("\\s");
                    boolean allow = verify_action("Update");
                    if(allow){
                        if(Integer.valueOf(UserPreveledge[1]) >= Integer.valueOf(N[1])){

                            if(H.length() < 8 || H.length() > 16){
                                    prompt_Message.setVisible(true);
                                    prompt_Message.setText("Password must be 8 to 16 characters.");
                                    prompt_Message.setForeground(Color.red);
                                }else{
                                    commandPass = "UPDATE personnels SET Lname = '"+B1+"', Fname = '"+B2+"', Preveledge = '"+J+"',"
                                                + "MI = '"+B3+"', Gender = '"+C+"', Age = '"+D+"', Password = '"+H+"', HireDate = '"+I+"' WHERE IDnumber = '"+A+"'";
                                    DQ.CRUDE(commandPass);

                                    commandPass = "UPDATE devices SET BTname = '"+F+"', BTaddress = '"+G+"' WHERE IDnumber = '"+A+"'";
                                    DQ.CRUDE(commandPass);

                                    commandPass = "UPDATE departments SET Position = '"+E+"', Department = '"+K+"' WHERE IDnumber = '"+A+"'";
                                    DQ.CRUDE(commandPass);

                                    prompt_Message.setVisible(true);
                                    prompt_Message.setText("Updating user "+A+" successful.");
                                    prompt_Message.setForeground(Color.green);

                                    AllPersonnels();
                            }
                        }else{
                            prompt_Message.setVisible(true);
                            prompt_Message.setText("Un-authorized update to user preveledge: "+J);
                            prompt_Message.setForeground(Color.red);
                        }
                    }else{}
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_Update_btnActionPerformed

    private void SearchSDR_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchSDR_btnActionPerformed
        try {
                SimpleDateFormat df = new SimpleDateFormat("MMMM dd yyyy");
                String searchDate = df.format(DateSDR_in.getDate());
                String searchLog = String.valueOf(SDRLogName_in.getModel().getSelectedItem());

                if(searchDate.isEmpty() || searchLog.isEmpty()){
                    infoBox("Fill in all field.","Error",WARNING_MESSAGE);
                }else{
                        dailyAttendanceRecords();
                }
            } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
                infoBox("Fill in all fields.","ERROR",CLOSED_OPTION);
                Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_SearchSDR_btnActionPerformed

    private void fwd_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fwd_btnActionPerformed
        int prev = Integer.parseInt(seven.getText()) + 1;
        int limit = prev + 7;
        weekNumber = weekNumber + 1;
        if(limit > lastdayOftheMonth){
            fwd_btn.setEnabled(false);
        }else{
            fwd_btn.setEnabled(true);
            prev_btn.setEnabled(true);
        }
        
        if(String.valueOf(prev).length() < 2){
            try {
                weeklyAttendanceRecords("0"+String.valueOf(prev));
                logNamesPerDate();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                weeklyAttendanceRecords(String.valueOf(prev));
                logNamesPerDate();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_fwd_btnActionPerformed

    private void SearchWDR_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchWDR_btnActionPerformed
        try {
            weeklyAttendanceRecords("01");
            weekNumber = 1;
            logNamesPerDate();
            if("".equals(one.getText())){
                prev_btn.setEnabled(false);
            }else{prev_btn.setEnabled(true);}
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SearchWDR_btnActionPerformed

    private void prev_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prev_btnActionPerformed
        int prev = Integer.parseInt(one.getText());
        int solve = prev - 7;
        String val = String.valueOf(solve);
        weekNumber = weekNumber - 1;
        if(solve <= 1){
            prev_btn.setEnabled(false);
        }else{prev_btn.setEnabled(true); fwd_btn.setEnabled(true);}
        
        if(Integer.parseInt(val) < 1){ val = "1";}
        
        if(val.length() > 1){
            try {
                weeklyAttendanceRecords(String.valueOf(val));
                System.out.println("Back at : " + val);
                logNamesPerDate();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                if(solve == 0){
                    val = "01";
                }
                weeklyAttendanceRecords("0"+String.valueOf(val));
                System.out.println("Back at : 0" + val);
                logNamesPerDate();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_prev_btnActionPerformed

    private void halfMonthSearch_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_halfMonthSearch_btnActionPerformed
        try{
            String name = nameMDR_in.getText();
            String ID = idMDR_in.getText();
            SimpleDateFormat df = new SimpleDateFormat("MMMM yyyy");
            String MonthYear = df.format(monthMDR_in.getDate());
            DefaultTableModel de = (DefaultTableModel)DTRtable.getModel();
            JRDataSource datasource = new JRTableModelDataSource(de);
            String reportSource = "C:/Users/Acer/Desktop/Final Capstone Project/SmartLog_System/src/Reports/DTRreport.jrxml";
            
            JasperReport jr = JasperCompileManager.compileReport(reportSource);
            
            Map params = new HashMap<String, Object>();
            params.put("personnelName", name);
            params.put("personnelID", ID);
            params.put("MonthYear", MonthYear);
            
            JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
            
            JasperViewer.viewReport(jp, false);
        }catch (JRException e){Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, e);}
        
    }//GEN-LAST:event_halfMonthSearch_btnActionPerformed

    private void searchDTR_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchDTR_btnActionPerformed
        try {
            String id = idMDR_in.getText();
            String name = nameMDR_in.getText();
            String c = String.valueOf(monthMDR_in.getDate());
            if(id.isEmpty()||c.isEmpty()){infoBox("All fields are required","Error",CLOSED_OPTION);searchByLogDTR_btn.setEnabled(false);}
            else{
                if(name.isEmpty()){
                    idMDR_in.setForeground(Color.red);
                    searchByLogDTR_btn.setEnabled(false);
                }else{
                    halfMonthAttendanceRecords(false);
                    searchByLogDTR_btn.setEnabled(true);
                }
            }
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchDTR_btnActionPerformed

    private void setMDR_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setMDR_inActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_setMDR_inActionPerformed

    private void idMDR_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idMDR_inActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idMDR_inActionPerformed

    private void nameMDR_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameMDR_inActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameMDR_inActionPerformed

    private void IDnumberWDR_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDnumberWDR_inActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDnumberWDR_inActionPerformed

    private void Lname_inFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Lname_inFocusGained
        if("Last Name".equals(Lname_in.getText())){
            Lname_in.setText("");
        }else{
        
        }
    }//GEN-LAST:event_Lname_inFocusGained

    private void Lname_inFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Lname_inFocusLost
        if("".equals(Lname_in.getText())){
            Lname_in.setText("Last Name");
        }else{
        
        }
    }//GEN-LAST:event_Lname_inFocusLost

    private void Fname_inFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Fname_inFocusGained
        if("First Name".equals(Fname_in.getText())){
            Fname_in.setText("");
        }else{
        
        }
    }//GEN-LAST:event_Fname_inFocusGained

    private void Fname_inFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Fname_inFocusLost
        if("".equals(Fname_in.getText())){
            Fname_in.setText("First Name");
        }else{
        
        }
    }//GEN-LAST:event_Fname_inFocusLost

    private void MI_inFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MI_inFocusGained
        if("M.I.".equals(MI_in.getText())){
            MI_in.setText("");
        }else{
        
        }
    }//GEN-LAST:event_MI_inFocusGained

    private void MI_inFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MI_inFocusLost
        if("".equals(MI_in.getText())){
            MI_in.setText("M.I.");
        }else{
        
        }
    }//GEN-LAST:event_MI_inFocusLost

    private void PersonnelSearch_inFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PersonnelSearch_inFocusGained
        if("All ID Number".equals(PersonnelSearch_in.getText())){
            PersonnelSearch_in.setText("");
        }else{
        
        }
    }//GEN-LAST:event_PersonnelSearch_inFocusGained

    private void PersonnelSearch_inFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PersonnelSearch_inFocusLost
        if("".equals(PersonnelSearch_in.getText())){
            PersonnelSearch_in.setText("All ID Number");
        }else{
        
        }
    }//GEN-LAST:event_PersonnelSearch_inFocusLost

    private void Add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_btnActionPerformed
        String A,B1,B2,B3,C,D,E,F,G,H,I,J,K,L,commandPass;
        A = ID_in.getText();
        B1 = Lname_in.getText();
        B2 = Fname_in.getText();
        B3 = MI_in.getText();
        D = (String) Gender_in.getModel().getSelectedItem();
        C = Age_in.getText();
        F = (String) BTname.getSelectedItem();
        G = BTaddress.getText();
        H = new String(PersonnelPassword_in.getPassword());
        SimpleDateFormat Df = new SimpleDateFormat("MMMM dd yyyy");
        I = Df.format(HireDate_in.getDate());
        J = (String) Department_in.getSelectedItem();
        K = (String) Preveledge_in.getSelectedItem();
        L = (String) Position_in.getModel().getSelectedItem();
        
        String N[] = K.split("\\s");
        
        if("M.I.".equals(B3)){B3 = "";}
        if("DEVICE NAME".equals(F)){ F = "";}
        
        if(A.isEmpty() || B1.equalsIgnoreCase("Last name") || B2.equalsIgnoreCase("first name") || C.isEmpty() || H.isEmpty()){
            prompt_Message.setVisible(true);
            prompt_Message.setText("Fill in all fields");
            prompt_Message.setForeground(Color.red);
        }else{
            try {
                if(Integer.parseInt(C) < 18 || Integer.parseInt(C) > 70){
                    prompt_Message.setVisible(true);
                    prompt_Message.setText("Age error");
                    prompt_Message.setForeground(Color.red);
                }
                else{
                    boolean Duplicate = DQ.dataDuplicateSearch(A);
                    boolean allow = verify_action("Create");
                    if(allow){
                        if(Duplicate == false){
                            String UserPreveledge[] = DQ.User_Preveledge(CurrentUser_IDnum).split("\\s");
                            if(Integer.valueOf(UserPreveledge[1]) >= Integer.valueOf(N[1])){

                                if(H.length() < 8 || H.length() > 16){
                                    prompt_Message.setVisible(true);
                                    prompt_Message.setText("Password must be 8 to 16 characters.");
                                    prompt_Message.setForeground(Color.red);
                                }else{
                                    commandPass = "INSERT INTO devices(IDnumber,BTname,BTaddress)"
                                                + "values('"+A+"','"+F+"','"+G+"')";
                                    DQ.CRUDE(commandPass);
                                    String DvID = DQ.fetch("devices", " WHERE IDnumber = '"+A+"'", "Device_ID");
                                    System.out.println("Adding device Successful");

                                    commandPass = "INSERT INTO departments(IDnumber,Department,Position)"
                                                + "values('"+A+"','"+J+"','"+L+"')";
                                    DQ.CRUDE(commandPass);
                                    String DptID = DQ.fetch("departments", " WHERE IDnumber = '"+A+"'", "id_Departments");
                                    System.out.println("Adding to the department Successful");

                                    commandPass = "INSERT INTO personnels(IDnumber, Lname, Fname, MI, Age, Gender, HireDate, Preveledge, Password, Device_ID, Dpt_ID)"
                                                + "values('"+A+"','"+B1+"','"+B2+"','"+B3+"','"+C+"','"+D+"','"+I+"','"+K+"','"+H+"','"+DvID+"','"+DptID+"')";
                                    DQ.CRUDE(commandPass);
                                    System.out.println("Adding personnel Successful");

                                    prompt_Message.setVisible(true);
                                    prompt_Message.setText("Adding user "+A+" successful.");
                                    prompt_Message.setForeground(Color.green);

                                    AllPersonnels();
                                }
                            }else{
                                prompt_Message.setVisible(true);
                                prompt_Message.setText("Un-authorized to add User with a preveledge of "+K);
                                prompt_Message.setForeground(Color.red);
                            }
                        }else{
                            prompt_Message.setVisible(true);
                            prompt_Message.setText("User ID "+A+" already exist.");
                            prompt_Message.setForeground(Color.red);
                        }
                    }
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_Add_btnActionPerformed

    private void Personnels_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Personnels_tableMouseClicked
        DefaultTableModel dtm = (DefaultTableModel) Personnels_table.getModel();
        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.removeAllElements();
        String ID = (String) dtm.getValueAt(Personnels_table.getSelectedRow(),0); 
        try {
            FetchDepartments();
            String[] data = DQ.getUser(ID);
            cb.addElement(data[8]);
            SimpleDateFormat df = new SimpleDateFormat("MMMM dd yyyy");
            Department_in.getModel().setSelectedItem(data[12]);
            ID_in.setText(ID);
            Lname_in.setText(data[2]);
            Fname_in.setText(data[3]);
            MI_in.setText(data[4]);
            Gender_in.getModel().setSelectedItem(data[5]);
            Age_in.setText(data[6]);
            Position_in.getModel().setSelectedItem(data[7]);
            BTname.setModel(cb);
            BTname.getModel().setSelectedItem(data[8]);
            BTaddress.setText(data[9]);
            PersonnelPassword_in.setText(data[10]);
            Date ddd = df.parse(data[11]);
            HireDate_in.setDate(ddd);
            Preveledge_in.getModel().setSelectedItem(data[13]);
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Personnels_tableMouseClicked

    private void ID_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ID_inKeyTyped
        char in = evt.getKeyChar();
        if(((in < '0') || (in > '9')) && (in != '\b')){
            evt.consume();
        }
    }//GEN-LAST:event_ID_inKeyTyped

    private void BTnameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BTnameItemStateChanged
        String Item = (String) BTname.getModel().getSelectedItem();
        int add = 0;
        for(int i=0;i<BT_name.length;i++){
            if(Item.equals(BT_name[i])){
                add = i;
            }else{}
        }
        BTaddress.setText(BT_add[add]);
    }//GEN-LAST:event_BTnameItemStateChanged

    private void BTnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTnameActionPerformed

    private void IDnumberSDR_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDnumberSDR_inActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDnumberSDR_inActionPerformed

    private void LOGS_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOGS_btnActionPerformed
        LOG_panel.setVisible(true);
        DEPARTMENTS_panel.setVisible(false);
        SHIFTS_panel.setVisible(false);
        SCHEDULE_panel.setVisible(false);
        LEAVE_panel.setVisible(false);
        
        LOGS_btn.setForeground(new Color(255,255,255));
        LOGS_btn.setBackground(new Color(4,8,114));
        DEPARTMENTS_btn.setBackground(new Color(255,255,255));
        DEPARTMENTS_btn.setForeground(new Color(4,8,114));
        SHIFTS_btn.setBackground(new Color(255,255,255));
        SHIFTS_btn.setForeground(new Color(4,8,114));
        SCHEDULE_btn.setBackground(new Color(255,255,255));
        SCHEDULE_btn.setForeground(new Color(4,8,114));
        LEAVE_btn.setBackground(new Color(255,255,255));
        LEAVE_btn.setForeground(new Color(4,8,114));
        
        DepartmentHP = "Head";
        // Displaying the Logs on table
        all_Logs();
        
    }//GEN-LAST:event_LOGS_btnActionPerformed

    private void LEAVE_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LEAVE_btnActionPerformed
        LOG_panel.setVisible(false);
        DEPARTMENTS_panel.setVisible(false);
        SHIFTS_panel.setVisible(false);
        SCHEDULE_panel.setVisible(false);
        LEAVE_panel.setVisible(true);
        
        LOGS_btn.setBackground(new Color(255,255,255));
        LOGS_btn.setForeground(new Color(4,8,114));
        DEPARTMENTS_btn.setBackground(new Color(255,255,255));
        DEPARTMENTS_btn.setForeground(new Color(4,8,114));
        SHIFTS_btn.setBackground(new Color(255,255,255));
        SHIFTS_btn.setForeground(new Color(4,8,114));
        SCHEDULE_btn.setBackground(new Color(255,255,255));
        SCHEDULE_btn.setForeground(new Color(4,8,114));
        LEAVE_btn.setForeground(new Color(255,255,255));
        LEAVE_btn.setBackground(new Color(4,8,114));
        
        
       Display_LeaveRegister("");
    }//GEN-LAST:event_LEAVE_btnActionPerformed

    private void SCHEDULE_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SCHEDULE_btnActionPerformed
        LOG_panel.setVisible(false);
        DEPARTMENTS_panel.setVisible(false);
        SHIFTS_panel.setVisible(false);
        SCHEDULE_panel.setVisible(true);
        LEAVE_panel.setVisible(false);
        
        LOGS_btn.setBackground(new Color(255,255,255));
        LOGS_btn.setForeground(new Color(4,8,114));
        DEPARTMENTS_btn.setBackground(new Color(255,255,255));
        DEPARTMENTS_btn.setForeground(new Color(4,8,114));
        SHIFTS_btn.setBackground(new Color(255,255,255));
        SHIFTS_btn.setForeground(new Color(4,8,114));
        SCHEDULE_btn.setForeground(new Color(255,255,255));
        SCHEDULE_btn.setBackground(new Color(4,8,114));
        LEAVE_btn.setBackground(new Color(255,255,255));
        LEAVE_btn.setForeground(new Color(4,8,114));
        
        ScheduleMorningIn_Pin.setText("");
        ScheduleMorningOut_Pin.setText("");
        ScheduleAfternoonIn_Pin.setText("");
        ScheduleAfternoonOut_Pin.setText("");
        DisplayDailySchedule();
        
    }//GEN-LAST:event_SCHEDULE_btnActionPerformed

    private void SHIFTS_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SHIFTS_btnActionPerformed
        LOG_panel.setVisible(false);
        DEPARTMENTS_panel.setVisible(false);
        SHIFTS_panel.setVisible(true);
        SCHEDULE_panel.setVisible(false);
        LEAVE_panel.setVisible(false);
        
        LOGS_btn.setBackground(new Color(255,255,255));
        LOGS_btn.setForeground(new Color(4,8,114));
        DEPARTMENTS_btn.setBackground(new Color(255,255,255));
        DEPARTMENTS_btn.setForeground(new Color(4,8,114));
        SHIFTS_btn.setForeground(new Color(255,255,255));
        SHIFTS_btn.setBackground(new Color(4,8,114));
        SCHEDULE_btn.setBackground(new Color(255,255,255));
        SCHEDULE_btn.setForeground(new Color(4,8,114));
        LEAVE_btn.setBackground(new Color(255,255,255));
        LEAVE_btn.setForeground(new Color(4,8,114));
        
        Shiftting();
    }//GEN-LAST:event_SHIFTS_btnActionPerformed

    private void DEPARTMENTS_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DEPARTMENTS_btnActionPerformed
        LOG_panel.setVisible(false);
        DEPARTMENTS_panel.setVisible(true);
        SHIFTS_panel.setVisible(false);
        SCHEDULE_panel.setVisible(false);
        LEAVE_panel.setVisible(false);
        
        LOGS_btn.setBackground(new Color(255,255,255));
        LOGS_btn.setForeground(new Color(4,8,114));
        DEPARTMENTS_btn.setForeground(new Color(255,255,255));
        DEPARTMENTS_btn.setBackground(new Color(4,8,114));
        SHIFTS_btn.setBackground(new Color(255,255,255));
        SHIFTS_btn.setForeground(new Color(4,8,114));
        SCHEDULE_btn.setBackground(new Color(255,255,255));
        SCHEDULE_btn.setForeground(new Color(4,8,114));
        LEAVE_btn.setBackground(new Color(255,255,255));
        LEAVE_btn.setForeground(new Color(4,8,114));
        
        displayDepartments();
        
        //set predisplay
        dptPositions_btn.setForeground(new Color(4,8,114));
        dptPositions_btn.setBackground(new Color(255,255,255));
        dptHead_btn.setBackground(new Color(4,8,114));
        dptHead_btn.setForeground(new Color(255,255,255));
        
        dptPositions_panel.setVisible(false);
        dptHead_panel.setVisible(true);
        
        DepartmentHP = "Head";
    }//GEN-LAST:event_DEPARTMENTS_btnActionPerformed

    private void LogDelete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogDelete_btnActionPerformed
        String LID = LogID_in.getText();
        String LogName = LogName_in.getText();
        String Date = LogDate_in.getText();
        
        try {
            boolean verify_action = verify_action("Delete");
            if(verify_action){
                boolean confirm = infoBox("All data Log entry of \""+LogName+"\" will be deleted.\nThere is NO UNDO if this action is committed.\n Do you confirm to proceed?","Warning",YES_NO_OPTION);
                if(confirm){
                    DQ.CRUDE("Delete from dailylogschedule where sch_ID = '"+LID+"'");
                    DQ.CRUDE("Delete from checkinout where Log_Name = '"+LogName+"' and Date = '"+Date+"'");
                    infoBox("Deletion Successfull","Notification",CLOSED_OPTION);
                }else{}
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        all_Logs();
    }//GEN-LAST:event_LogDelete_btnActionPerformed

    private void DepartmentDelete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepartmentDelete_btnActionPerformed
        String CRN = DepartmentID_in.getText();
        
        if(CRN.isEmpty()){
        infoBox("Nothing selected.","ACTION ERROR",CLOSED_OPTION);
        }else{
        try {
            boolean verify_action = verify_action("Delete");
            if(verify_action){
                DQ.CRUDE("Delete from departmentclass where dept_ID = '"+CRN+"'");
                infoBox("Deletion Successful","Notification",CLOSED_OPTION);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        perDepartmentView();}
    }//GEN-LAST:event_DepartmentDelete_btnActionPerformed

    private void DepartmentView_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepartmentView_btnActionPerformed
        DepartmentHP = "";
        perDepartmentView();
    }//GEN-LAST:event_DepartmentView_btnActionPerformed

    private void ShiftDelete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShiftDelete_btnActionPerformed
        String SId = ShiftCRN_in.getText();
        
        if(SId.isEmpty()){
            infoBox("Selected nothing.","ACTION ERROR",CLOSED_OPTION);
        }else{
        try {
            String commandPass = "DELETE FROM shiftclass WHERE Shift_ID = '"+SId+"'";
            boolean VA = verify_action("Delete"); 
            if(VA){
                DQ.CRUDE(commandPass);
                infoBox("Deleting Shift successful","Notification",CLOSED_OPTION);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Shiftting();
        }
    }//GEN-LAST:event_ShiftDelete_btnActionPerformed

    private void ShiftUpdate_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShiftUpdate_btnActionPerformed
        String Sday = (String) ShiftDay_in.getSelectedItem();
        String SIdn =  ShiftIdNumber_in.getText();
        String SPsnl = ShiftPersonnel_in.getText();
        String STime = ShiftTimeStart_in.getText();
        String ETime = ShiftTimeEnd_in.getText();
        String SId = ShiftCRN_in.getText();
        String Status = "OFF";
        boolean stat = activateShift_checkbox.getModel().isSelected();
        if(stat){
            Status = "ON";
        }
        
        if(Sday.isEmpty() || SIdn.isEmpty() || SPsnl.isEmpty() || STime.isEmpty() || ETime.isEmpty()){
            infoBox("Fill in all fields.","ACTION ERROR",CLOSED_OPTION);
        }else{
        try {
            String commandPass = "UPDATE shiftclass SET IDnumber = '"+SIdn+"', Personnel = '"+SPsnl+"', Shift_Day = '"+Sday+"'"
                        +", Start_Time = '"+STime+"', End_Time = '"+ETime+"', Status = '"+Status+"' WHERE Shift_ID = '"+SId+"'";
            boolean VA = verify_action("Update");
            if(VA){
                DQ.CRUDE(commandPass);
                infoBox("Updating Shift details successful","Notification",CLOSED_OPTION);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Shiftting();}
    }//GEN-LAST:event_ShiftUpdate_btnActionPerformed

    private void ShiftAdd_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShiftAdd_btnActionPerformed
        String Sday =  (String) ShiftDay_in.getSelectedItem();
        String SIdn =  ShiftIdNumber_in.getText();
        String SPsnl = ShiftPersonnel_in.getText();
        String STime = ShiftTimeStart_in.getText();
        String ETime = ShiftTimeEnd_in.getText();
        String Status = "OFF";
        boolean stat = activateShift_checkbox.getModel().isSelected();
        if(stat){
            Status = "ON";
        }
        
        if(Sday.isEmpty() || SIdn.isEmpty() || SPsnl.isEmpty() || STime.isEmpty() || ETime.isEmpty()){
            infoBox("Fill in all fields.","ACTION ERROR",CLOSED_OPTION);
        }else{
        
            String commandPass = "INSERT INTO shiftclass(IDnumber, Personnel, shift_Day, Start_Time, End_Time,Status)"
                                + "values('"+SIdn+"','"+SPsnl+"','"+Sday+"','"+STime+"','"+ETime+"','"+Status+"')";
            try {
                boolean VA = verify_action("Create");
                if(VA){
                    DQ.CRUDE(commandPass);
                    infoBox("Shift Added successfuly","Notification",CLOSED_OPTION);
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
            Shiftting();
        }
    }//GEN-LAST:event_ShiftAdd_btnActionPerformed

    public int Timecutter(String toCut){
        int T = toCut.length() - 2;
        
        return T;
    }
    
    public boolean timeParser(String toCut, String toCut2){
        int T = Integer.parseInt(toCut.substring(0,toCut.length() - 3));
        int T2 = Integer.parseInt(toCut2.substring(0,toCut2.length() - 3));
        
        return T<T2;
    }
    
    private void ScheduleAdd_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ScheduleAdd_btnActionPerformed
        try {
            String cmd = null;
            String SN = ScheduleName_in.getText();
            String SDT = (String) ScheduleDayType_in.getSelectedItem();

            SimpleDateFormat Df = new SimpleDateFormat("MMMM dd yyyy");
            String FD = Df.format(ScheduleDate_dc.getDate());

            String SD = FD;
            String SWS = (String) ScheduleWorkStatus_cb.getSelectedItem();
            String SLE = (String) ScheduleLogEntry_cb.getSelectedItem();
            String in, O, in2, o2;
            try{
             in = ScheduleMorningIn_Pin.getText().substring(0, Timecutter(ScheduleMorningIn_Pin.getText()));
            }catch(StringIndexOutOfBoundsException ex){in = "00:00:00";}
            try{
             O = ScheduleMorningOut_Pin.getText().substring(0,Timecutter(ScheduleMorningOut_Pin.getText()));
            }catch(StringIndexOutOfBoundsException ex){O = "00:00:00";}
            try{
            in2 = ScheduleAfternoonIn_Pin.getText().substring(0,Timecutter(ScheduleAfternoonIn_Pin.getText()));
            }catch(StringIndexOutOfBoundsException ex){in2 = "00:00:00";}
            try{
            o2 = ScheduleAfternoonOut_Pin.getText().substring(0,Timecutter(ScheduleAfternoonOut_Pin.getText()));
            }catch(StringIndexOutOfBoundsException ex){o2 = "00:00:00";}
            
            
            if(SN.isEmpty() || SDT.isEmpty() || FD.isEmpty() || SWS.equalsIgnoreCase("choose working status") || SLE.equalsIgnoreCase("choose log cycle")){
                infoBox("Please Fill out all fields.","Error",CLOSED_OPTION);
            }else{
                
                if("".equals(SN)){
                    infoBox("Schedule name can't be empty","Error",CLOSED_OPTION);
                }else{
                    boolean verifiedTime = false;
                    //Time validator
                    if(SLE.equalsIgnoreCase("Afternoon in-Out")){
                        verifiedTime = timeParser(in2,o2);
                    }else if(SLE.equalsIgnoreCase("Morning In-Out")){
                        verifiedTime = timeParser(in,O);
                    }else if(SLE.equalsIgnoreCase("Morning in-out and Afternoon in-out")){
                        verifiedTime = timeParser(in2,o2) && timeParser(in,O);
                    }else{verifiedTime=true;}
                    
                    if(verifiedTime){
                            String SMi = in, SMo = O, SAi = in2, SAo = o2;

                                cmd = "INSERT INTO dailylogschedule(schName, Date, StartTime_1, EndTime_1, StartTime_2, EndTime_2, LogIO_cycle, Work_status, Day_Type, Status)"
                                                    + "values('"+SN+"','"+SD+"','"+SMi+"','"+SMo+"','"+SAi+"','"+SAo+"','"+SLE+"','"+SWS+"','"+SDT+"','SCHEDULED')";
                                boolean notduplicate = DQ.isDuplicate("Select * from dailylogschedule where schName='"+SN+"' and Date='"+SD+"'");
                                if(notduplicate){
                                    boolean VA = verify_action("Create");
                                    if(VA){
                                        DQ.CRUDE(cmd);
                                        infoBox("Schedule rendered Successfuly","Notification",CLOSED_OPTION);
                                    }
                                }else{
                                    infoBox("schedule name already exist. Input different one.","Error",CLOSED_OPTION);
                                }

                         }else{infoBox("scheduled time irrelevant.","Error",CLOSED_OPTION);}
                       }
                }
            } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
                Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
                infoBox("Error adding schedule. Make sure all the data is filled in and correctly.","Error",CLOSED_OPTION);
            }
        DisplayDailySchedule();
    }//GEN-LAST:event_ScheduleAdd_btnActionPerformed

    private void ScheduleUpdate_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ScheduleUpdate_btnActionPerformed
        String SN = ScheduleName_in.getText();
        String SDT = (String) ScheduleDayType_in.getSelectedItem();
        
        SimpleDateFormat Df = new SimpleDateFormat("MMMM dd yyyy");
        String FD = Df.format(ScheduleDate_dc.getDate());
        
        String SD = FD;
        String SWS = (String) ScheduleWorkStatus_cb.getSelectedItem();
        String SLE = (String) ScheduleLogEntry_cb.getSelectedItem();
        
        String in;
        String O;
        String in2;
        String o2;
        
        try{
         in = ScheduleMorningIn_Pin.getText().substring(0, Timecutter(ScheduleMorningIn_Pin.getText()));
        }catch(StringIndexOutOfBoundsException ex){in = "00:00:00";}
        try{
         O = ScheduleMorningOut_Pin.getText().substring(0,Timecutter(ScheduleMorningOut_Pin.getText()));
        }catch(StringIndexOutOfBoundsException ex){O = "00:00:00";}
        try{
        in2 = ScheduleAfternoonIn_Pin.getText().substring(0,Timecutter(ScheduleAfternoonIn_Pin.getText()));
        }catch(StringIndexOutOfBoundsException ex){in2 = "00:00:00";}
        try{
        o2 = ScheduleAfternoonOut_Pin.getText().substring(0,Timecutter(ScheduleAfternoonOut_Pin.getText()));
        }catch(StringIndexOutOfBoundsException ex){o2 = "00:00:00";}
        
        
        String SMi = in;
        String SMo = O;
        String SAi = in2;
        String SAo = o2;
        
        String ID = ScheduleCRN_in.getText();
        
        try {
            String cmd = "UPDATE dailylogschedule SET schName = '"+SN+"', Date = '"+SD+"', StartTime_1 = '"+SMi+"', EndTime_1 = '"+SMo+"', StartTime_2 = '"+SAi+"', "
                + "EndTime_2 = '"+SAo+"', LogIO_cycle = '"+SLE+"', Work_status = '"+SWS+"', Day_Type = '"+SDT+"' WHERE sch_ID = '"+ID+"'";
            
            boolean VA = verify_action("Create");
            if(VA){
                DQ.CRUDE(cmd);
                infoBox("Schedule rendered Successfuly","Notification",CLOSED_OPTION);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        DisplayDailySchedule();
    }//GEN-LAST:event_ScheduleUpdate_btnActionPerformed

    private void ScheduleDelete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ScheduleDelete_btnActionPerformed
        
        String ID = ScheduleCRN_in.getText();
        
        try {
            String cmd = "DELETE FROM dailylogschedule WHERE sch_ID = '"+ID+"'";
            
            boolean VA = verify_action("Delete");
            if(VA){
                DQ.CRUDE(cmd);
                infoBox("Schedule deleted Successfuly","Notification",CLOSED_OPTION);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        DisplayDailySchedule();
    }//GEN-LAST:event_ScheduleDelete_btnActionPerformed

    private void LeaveDelete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeaveDelete_btnActionPerformed
        
        if(selectedLeave.isEmpty() || selectedLeave=="" || selectedLeave.isBlank()){
            infoBox("Nothing selected.","ACTION ERROR",CLOSED_OPTION);
        }else{
        try {
            String cmd = "DELETE FROM leaveclass WHERE LeaveID '"+selectedLeave+"'";
            
            boolean VA = verify_action("Delete");
            if(VA){
                DQ.CRUDE(cmd);
                infoBox("Leave deleted Successfuly","Notification",CLOSED_OPTION);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Display_LeaveRegister("");}
    }//GEN-LAST:event_LeaveDelete_btnActionPerformed

    private void LeaveUpdate_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeaveUpdate_btnActionPerformed
        String P = LeavePersonnel_in.getText();
        
        SimpleDateFormat Df = new SimpleDateFormat("MMMM dd yyyy");
        String FD = Df.format(LeaveDateFiled_dc.getDate());
        String SD = FD;
        String Lsd = Df.format(LeaveDateStart_dc.getDate());
        String Led = Df.format(LeaveDateEnd_dc.getDate());
        
        String Lid = LeaveIdNumber_in.getText();
        String Lex = LeaveExcuse_in.getText();
        String Ls = LeaveStatus_in.getText();
        
        String cmd = "UPDATE leaveclass SET Leavename = '"+Lex+"', IDnumber = '"+Lid+"', Personnel = '"+P+"', "
                   + "StartDate = '"+Lsd+"', EndDate = '"+Led+"', State = '"+Ls+"', DateFiled = '"+SD+"' WHERE LeaveID = '"+selectedLeave+"'";
        
        if(SD.isEmpty() || Lsd.isEmpty() || Led.isEmpty() || Lid.isEmpty() || Lex.isEmpty()){
            infoBox("Fill in all fields.","ACTION ERROR",CLOSED_OPTION);
        }else{
        try {
            boolean VA = verify_action("Update");
            if(VA){
                DQ.CRUDE(cmd);
                infoBox("Leave updated Successfuly","Notification",CLOSED_OPTION);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Display_LeaveRegister("");}
    }//GEN-LAST:event_LeaveUpdate_btnActionPerformed

    private void LeaveAdd_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeaveAdd_btnActionPerformed
        try{
            String P = LeavePersonnel_in.getText();
            SimpleDateFormat Df = new SimpleDateFormat("MMMM dd yyyy");
            String FD = Df.format(LeaveDateFiled_dc.getDate());
            String SD = FD;
            String Lsd = Df.format(LeaveDateStart_dc.getDate());
            String Led = Df.format(LeaveDateEnd_dc.getDate());
            String Lid = LeaveIdNumber_in.getText();
            String Lex = LeaveExcuse_in.getText();
        
        if(SD.isEmpty() || Lsd.isEmpty() || Led.isEmpty() || Lid.isEmpty() || Lex.isEmpty()){
            infoBox("Fill in all fields.","ACTION ERROR",CLOSED_OPTION);
        }else{
        String cmd = "INSERT INTO leaveclass(Leavename, IDnumber, Personnel, StartDate, EndDate, State, DateFiled)"
                            + "values('"+Lex+"','"+Lid+"','"+P+"','"+Lsd+"','"+Led+"','Scheduled','"+SD+"')";
        try {
            boolean VA = verify_action("Create");
            if(VA){
                DQ.CRUDE(cmd);
                infoBox("Leave added Successfuly","Notification",CLOSED_OPTION);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Display_LeaveRegister("");}
        }catch(NullPointerException e){infoBox("Fill in all fields.","ERROR",CLOSED_OPTION);}
    }//GEN-LAST:event_LeaveAdd_btnActionPerformed

    private void Logs_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Logs_tableMouseClicked
        DefaultTableModel dtm = (DefaultTableModel) Logs_table.getModel();
        String LogID = (String) dtm.getValueAt(Logs_table.getSelectedRow(),0);
        String[] data;
        try {
            data = DQ.getSchedules(" WHERE sch_ID = '"+LogID+"'");
            LogID_in.setText(LogID);
            LogName_in.setText(data[2]);
            LogDate_in.setText(data[3]);
            LogDayType_in.setText(data[12]);
            LogWorkStatus_in.setText(data[11]);
            LogEntry_in.setText(data[10]);
            LogStatus_in.setText(data[13]);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Logs_tableMouseClicked

    private void Departments_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Departments_tableMouseClicked
        
            if("Head".equals(DepartmentHP)){
                DefaultTableModel dtm = (DefaultTableModel) Departments_table.getModel();
                String DptID = (String) dtm.getValueAt(Departments_table.getSelectedRow(),0);
                String Dpt = (String) dtm.getValueAt(Departments_table.getSelectedRow(),1);
                String Psnl = (String) dtm.getValueAt(Departments_table.getSelectedRow(),2);
                String Head = (String) dtm.getValueAt(Departments_table.getSelectedRow(),3);

                DepartmentID_in.setText(DptID);
                DepartmentName_in.setText(Dpt);
                DepartmentPersonnels_in.setText(Psnl);
                DepartmentHead_in.setText(Head);
            }else if("Position".equals(DepartmentHP)){
                DefaultTableModel dtm = (DefaultTableModel) Departments_table.getModel();
                String DptID = (String) dtm.getValueAt(Departments_table.getSelectedRow(),0);
                String Dpt = (String) dtm.getValueAt(Departments_table.getSelectedRow(),1);
                String Psnl = (String) dtm.getValueAt(Departments_table.getSelectedRow(),2);

                PositionID_in.setText(DptID);
                PositionDepartmentName_in.getModel().setSelectedItem(Dpt);
                DepartmentPositionName_in.setText(Psnl);
            }
        
    }//GEN-LAST:event_Departments_tableMouseClicked

    private void DepartmentUpdate_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepartmentUpdate_btnActionPerformed
        String DN = DepartmentName_in.getText();
        String DH = DepartmentHead_in.getText();
        String CRN = DepartmentID_in.getText();
        
        String[] IDs;
        String[] POS_IDs;
        
        if(DN.isEmpty() || DH.isEmpty() || CRN.isEmpty()){
            if(CRN.isEmpty()){
            infoBox("Select from table.","ACTION ERROR",CLOSED_OPTION);
            }else{infoBox("Fill in all fields.","ACTION ERROR",CLOSED_OPTION);}
        }else{
        try {
            boolean verify_action = verify_action("Update");
            if(verify_action){
                
                //Fetching Department previous name
                String Prev_DptName = DQ.fetch("departmentclass"," WHERE dept_ID = '"+CRN+"'", "Department");
                
                //Updating departmentsclass
                DQ.CRUDE("Update departmentclass SET Department = '"+DN+"', Head = '"+DH+"' WHERE dept_ID = '"+CRN+"'");
                System.out.println("Department Class updated");
                
                IDs = DQ.arrayDataFetch("departments Where Department = '"+Prev_DptName+"'","departments", " Where Department = '"+Prev_DptName+"'", "id_Departments");
                //Updating departments
                for(int i=0;i<IDs.length;i++){
                DQ.CRUDE("Update departments SET Department = '"+DN+"' WHERE id_Departments = '"+IDs[i]+"'");
                System.out.println("ID: "+IDs[i]);
                }
                System.out.println("Departments updated");         
                
                POS_IDs = DQ.arrayDataFetch("positions where Department = '"+Prev_DptName+"'","positions", " Where Department = '"+Prev_DptName+"'", "Id");
                //Updating positions
                for(int i=0;i<POS_IDs.length;i++){
                    DQ.CRUDE("Update positions SET Department = '"+DN+"' WHERE Id = '"+POS_IDs[i]+"'");
                    System.out.println("ID: "+POS_IDs[i]);
                }
                System.out.println("Positions updated");
                
                infoBox("Update Successfull","Notification",CLOSED_OPTION);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        perDepartmentView();}
    }//GEN-LAST:event_DepartmentUpdate_btnActionPerformed

    private void Shifts_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Shifts_tableMouseClicked
        DefaultTableModel dtm = (DefaultTableModel) Shifts_table.getModel();
        String Sid = (String) dtm.getValueAt(Shifts_table.getSelectedRow(),0);
        String Sday = (String) dtm.getValueAt(Shifts_table.getSelectedRow(),1);
        String SIDn = (String) dtm.getValueAt(Shifts_table.getSelectedRow(),2);
        String SPsnl = (String) dtm.getValueAt(Shifts_table.getSelectedRow(),3);
        String STime = (String) dtm.getValueAt(Shifts_table.getSelectedRow(),4);
        String ETime = (String) dtm.getValueAt(Shifts_table.getSelectedRow(),5);
        String Pos = null;
        String status = "OFF";
        try {
            Pos = DQ.fetch("departments", " WHERE IDnumber = '"+SIDn+"'", "Position");
            status = DQ.fetch("shiftclass", " Where Shift_ID = '"+Sid+"'", "Status");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if("ON".equals(status)){
            activateShift_checkbox.getModel().setSelected(true);
        }else{
            activateShift_checkbox.getModel().setSelected(false);
        }
        
        ShiftCRN_in.setText(Sid);
        ShiftDay_in.getModel().setSelectedItem(Sday);
        ShiftIdNumber_in.setText(SIDn);
        ShiftPersonnel_in.setText(SPsnl);
        ShiftTimeStart_in.setText(STime);
        ShiftTimeEnd_in.setText(ETime);
        ShiftPosition_in.setText(Pos);
    }//GEN-LAST:event_Shifts_tableMouseClicked

    private void ShiftIdNumber_inKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ShiftIdNumber_inKeyPressed
        
    }//GEN-LAST:event_ShiftIdNumber_inKeyPressed

    private void Schedule_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Schedule_tableMouseClicked
        DefaultTableModel dtm = (DefaultTableModel) Schedule_table.getModel();
        String CRN = (String) dtm.getValueAt(Schedule_table.getSelectedRow(),0);
        
        try {
            String[] data = DQ.getSchedules(" WHERE sch_ID = '"+CRN+"'");
            ScheduleCRN_in.setText(CRN);
            ScheduleName_in.setText(data[2]);
            ScheduleDayType_in.getModel().setSelectedItem(data[12]);
            String d = data[3];
            Date d2 = new SimpleDateFormat("MMMM dd yyyy").parse(d);
            ScheduleDate_dc.setDate(d2);
            ScheduleWorkStatus_cb.getModel().setSelectedItem(data[11]);
            ScheduleLogEntry_cb.getModel().setSelectedItem(data[10]);
            ScheduleMorningIn_Pin.setText(data[4]);
            ScheduleMorningOut_Pin.setText(data[5]);
            ScheduleAfternoonIn_Pin.setText(data[6]);
            ScheduleAfternoonOut_Pin.setText(data[7]);
        
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Schedule_tableMouseClicked

    private void ScheduleLogEntry_cbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ScheduleLogEntry_cbItemStateChanged
        String ST = (String) ScheduleLogEntry_cb.getSelectedItem();
        
        if(null == ST){
            ScheduleMorningIn_Pin.setEnabled(false);
            ScheduleMorningOut_Pin.setEnabled(false);
            ScheduleAfternoonIn_Pin.setEnabled(false);
            ScheduleAfternoonOut_Pin.setEnabled(false);
            ScheduleMorningIn_Pin.setText("");
            ScheduleMorningOut_Pin.setText("");
            ScheduleAfternoonIn_Pin.setText("");
            ScheduleAfternoonOut_Pin.setText("");
            
        }else switch (ST) {
            case "Morning In":
                ScheduleMorningIn_Pin.setEnabled(true);
                ScheduleMorningOut_Pin.setEnabled(false);
                ScheduleAfternoonIn_Pin.setEnabled(false);
                ScheduleAfternoonOut_Pin.setEnabled(false);
                ScheduleMorningOut_Pin.setText("");
                ScheduleAfternoonIn_Pin.setText("");
                ScheduleAfternoonOut_Pin.setText("");
                //Mi,Mio,Ai,Aio,MiAo,MioAio
                LogCycle_type = "Mi";
                break;
            case "Afternoon In":
                ScheduleMorningIn_Pin.setEnabled(false);
                ScheduleMorningOut_Pin.setEnabled(false);
                ScheduleAfternoonIn_Pin.setEnabled(true);
                ScheduleAfternoonOut_Pin.setEnabled(false);
                ScheduleMorningIn_Pin.setText("");
                ScheduleMorningOut_Pin.setText("");
                ScheduleAfternoonOut_Pin.setText("");
                //Mi,Mio,Ai,Aio,MiAo,MioAio
                LogCycle_type = "Ai";
                break;
            case "Morning In-Out":
                ScheduleMorningIn_Pin.setEnabled(true);
                ScheduleMorningOut_Pin.setEnabled(true);
                ScheduleAfternoonIn_Pin.setEnabled(false);
                ScheduleAfternoonOut_Pin.setEnabled(false);
                ScheduleAfternoonIn_Pin.setText("");
                ScheduleAfternoonOut_Pin.setText("");
                //Mi,Mio,Ai,Aio,MiAo,MioAio
                LogCycle_type = "Mio";
                break;
            case "Afternoon In-Out":
                ScheduleMorningIn_Pin.setEnabled(false);
                ScheduleMorningOut_Pin.setEnabled(false);
                ScheduleAfternoonIn_Pin.setEnabled(true);
                ScheduleAfternoonOut_Pin.setEnabled(true);
                ScheduleMorningIn_Pin.setText("");
                ScheduleMorningOut_Pin.setText("");
                //Mi,Mio,Ai,Aio,MiAo,MioAio
                LogCycle_type = "Aio";
                break;
            case "Morning In and Afternoon Out":
                ScheduleMorningIn_Pin.setEnabled(true);
                ScheduleMorningOut_Pin.setEnabled(false);
                ScheduleAfternoonIn_Pin.setEnabled(false);
                ScheduleAfternoonOut_Pin.setEnabled(true);
                ScheduleMorningOut_Pin.setText("");
                ScheduleAfternoonIn_Pin.setText("");
                //Mi,Mio,Ai,Aio,MiAo,MioAio
                LogCycle_type = "MiAo";
                break;
            case "Morning In-Out and Afternoon In-Out":
                ScheduleMorningIn_Pin.setEnabled(true);
                ScheduleMorningOut_Pin.setEnabled(true);
                ScheduleAfternoonIn_Pin.setEnabled(true);
                ScheduleAfternoonOut_Pin.setEnabled(true);
                //Mi,Mio,Ai,Aio,MiAo,MioAio
                LogCycle_type = "MioAio";
                break;
            default:
                ScheduleMorningIn_Pin.setEnabled(false);
                ScheduleMorningOut_Pin.setEnabled(false);
                ScheduleAfternoonIn_Pin.setEnabled(false);
                ScheduleAfternoonOut_Pin.setEnabled(false);
                ScheduleMorningIn_Pin.setText("");
                ScheduleMorningOut_Pin.setText("");
                ScheduleAfternoonIn_Pin.setText("");
                ScheduleAfternoonOut_Pin.setText("");
                //Mi,Mio,Ai,Aio,MiAo,MioAio
                LogCycle_type = null;
                break;
        }
    }//GEN-LAST:event_ScheduleLogEntry_cbItemStateChanged

    private void ScheduleWorkStatus_cbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ScheduleWorkStatus_cbItemStateChanged
        String ST = (String) ScheduleWorkStatus_cb.getSelectedItem();
        if("Working Day".equals(ST)){
            ScheduleLogEntry_cb.setEnabled(true);
        }else if("Non-Working Day".equals(ST)){
            ScheduleLogEntry_cb.setEnabled(true);
        }else{
            ScheduleLogEntry_cb.setEnabled(false);
        }
    }//GEN-LAST:event_ScheduleWorkStatus_cbItemStateChanged

    private void Leave_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Leave_tableMouseClicked
        DefaultTableModel dtm = (DefaultTableModel) Leave_table.getModel();
        String CRN = (String) dtm.getValueAt(Leave_table.getSelectedRow(),0); 
        try {
            String[] data = DQ.getLeaveRegister(" WHERE LeaveID = '"+CRN+"'");
            LeavePersonnel_in.setText(data[8]);
            
            Date d2 = new SimpleDateFormat("MMMM dd yyyy").parse(data[7]);
            Date d3 = new SimpleDateFormat("MMMM dd yyyy").parse(data[4]);
            Date d4 = new SimpleDateFormat("MMMM dd yyyy").parse(data[5]);
            LeaveDateFiled_dc.setDate(d2);
            LeaveIdNumber_in.setText(data[3]);
            LeaveExcuse_in.setText(data[2]);
            
            LeaveDateStart_dc.setDate(d3);
            LeaveDateEnd_dc.setDate(d4);
            LeaveStatus_in.setText(data[6]);
            
            selectedLeave = CRN;
                    
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Leave_tableMouseClicked

    private void LeaveIdNumber_inKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LeaveIdNumber_inKeyPressed
        
    }//GEN-LAST:event_LeaveIdNumber_inKeyPressed

    private void DepartmentAdd_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepartmentAdd_btnActionPerformed
        String Dpt = DepartmentName_in.getText();
        String Head = DepartmentHead_in.getText();
        if(Dpt.isEmpty() || Head.isEmpty()){
            infoBox("Fill in all fields.","ACTION ERROR",CLOSED_OPTION);
        }else{
        try {
            boolean V = verify_action("Create");
            if(V){
                String match = DQ.fetch("Select * from departmentclass where Department='"+Dpt+"'", "Department");
                
                if(match.equalsIgnoreCase(Dpt)){
                    infoBox("Department already Exist.","ACTION ERROR",CLOSED_OPTION);
                }else{
                DQ.CRUDE("INSERT INTO departmentclass(Department, Head) values('"+Dpt+"','"+Head+"')");
                infoBox("Department added Successfuly","Notification",CLOSED_OPTION);}
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        perDepartmentView();}
    }//GEN-LAST:event_DepartmentAdd_btnActionPerformed

    private void ScheduleMorningIn_PinFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ScheduleMorningIn_PinFocusLost
        LocalTime Min = LocalTime.parse(ScheduleMorningIn_Pin.getText());
        LocalTime Mout = LocalTime.parse(ScheduleMorningOut_Pin.getText());
        
        boolean withinTimeRange = inTimeRange(Min,Mout);
        if(withinTimeRange){ScheduleMorningOut_Pin.setText("");}
        else{}
    }//GEN-LAST:event_ScheduleMorningIn_PinFocusLost

    private void ScheduleMorningIn_PinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ScheduleMorningIn_PinPropertyChange
        
    }//GEN-LAST:event_ScheduleMorningIn_PinPropertyChange

    private void ScheduleAfternoonOut_PinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ScheduleAfternoonOut_PinPropertyChange
       
    }//GEN-LAST:event_ScheduleAfternoonOut_PinPropertyChange

    private void ShiftIdNumber_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ShiftIdNumber_inKeyTyped
        String ID = ShiftIdNumber_in.getText();
        char in = evt.getKeyChar();
        if(((in < '0') || (in > '9')) && (in != '\b')){
            evt.consume();
        }else{
            try {
            String Personnel = DQ.fetch("select concat(Lname,', ',Fname,' ',MI,'.') as name from personnels where IDnumber='"+ID+in+"'", "name");
            String Position = DQ.fetch("departments","WHERE IDnumber ='"+ID+in+"'","Position");
            ShiftPersonnel_in.setText(Personnel);
            ShiftPosition_in.setText(Position);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_ShiftIdNumber_inKeyTyped

    private void ShiftIdNumber_inPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ShiftIdNumber_inPropertyChange
        
    }//GEN-LAST:event_ShiftIdNumber_inPropertyChange

    private void LeaveIdNumber_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LeaveIdNumber_inKeyTyped
        String ID = LeaveIdNumber_in.getText();
        char in = evt.getKeyChar();
        if(((in < '0') || (in > '9')) && (in != '\b')){
            evt.consume();
        }else{
            try {
            char t = evt.getKeyChar();
            String Personnel = DQ.fetch("select concat(Lname,', ',Fname,' ',MI,'.') as name from personnels where IDnumber='"+ID+t+"'", "name");
            LeavePersonnel_in.setText(Personnel);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_LeaveIdNumber_inKeyTyped

    private void Age_inKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Age_inKeyPressed
        
    }//GEN-LAST:event_Age_inKeyPressed

    private void Age_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Age_inKeyTyped
        try{
            char in = evt.getKeyChar();
            if(((in < '0') || (in > '9')) && (in != '\b')){
                evt.consume();
            }else{
                String c = Age_in.getText()+in;
                if(c.length() > 2){evt.consume();}
                else{
                    if(Integer.parseInt(c) > 70 || Integer.parseInt(c) < 18){Age_in.setForeground(Color.red);}
                    else{Age_in.setForeground(Color.black);}
                }
            }
        }catch(NumberFormatException e){}
    }//GEN-LAST:event_Age_inKeyTyped

    private void ScheduleMorningOut_PinFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ScheduleMorningOut_PinFocusLost
        LocalTime Min = LocalTime.parse(ScheduleMorningIn_Pin.getText());
        LocalTime Mout = LocalTime.parse(ScheduleMorningOut_Pin.getText());
        
        boolean withinTimeRange = inTimeRange(Mout,Min);
        if(withinTimeRange){}
        else{ScheduleMorningOut_Pin.setText("");}      
    }//GEN-LAST:event_ScheduleMorningOut_PinFocusLost

    private void ScheduleAfternoonIn_PinFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ScheduleAfternoonIn_PinFocusLost
        
    }//GEN-LAST:event_ScheduleAfternoonIn_PinFocusLost

    private void ScheduleAfternoonOut_PinFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ScheduleAfternoonOut_PinFocusLost
        
    }//GEN-LAST:event_ScheduleAfternoonOut_PinFocusLost

    private void IDnumberSDR_inFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_IDnumberSDR_inFocusLost
        String IDnum = IDnumberSDR_in.getText();
        if("".equals(IDnum)){
            IDnumberSDR_in.setText("ID Number");
        }
    }//GEN-LAST:event_IDnumberSDR_inFocusLost

    private void cmdNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNextActionPerformed
        if (month == 12) {
            month = 1;
            year++;
        } else {
            month++;
        }
        slide.show(new PanelDate(month, year), PanelSlide.AnimateType.TO_LEFT);
        showMonthYear();
    }//GEN-LAST:event_cmdNextActionPerformed

    private void cmdBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBackActionPerformed
        if (month == 1) {
            month = 12;
            year--;
        } else {
            month--;
        }
        slide.show(new PanelDate(month, year), PanelSlide.AnimateType.TO_RIGHT);
        showMonthYear();
    }//GEN-LAST:event_cmdBackActionPerformed

    private void dptHead_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dptHead_btnActionPerformed
        dptPositions_btn.setForeground(new Color(4,8,114));
        dptPositions_btn.setBackground(new Color(255,255,255));
        dptHead_btn.setBackground(new Color(4,8,114));
        dptHead_btn.setForeground(new Color(255,255,255));
        
        dptPositions_panel.setVisible(false);
        dptHead_panel.setVisible(true);
        
        DepartmentHP = "Head";
        displayDepartments();
        
    }//GEN-LAST:event_dptHead_btnActionPerformed

    private void dptPositions_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dptPositions_btnActionPerformed
        dptPositions_btn.setBackground(new Color(4,8,114));
        dptPositions_btn.setForeground(new Color(255,255,255));
        dptHead_btn.setForeground(new Color(4,8,114));
        dptHead_btn.setBackground(new Color(255,255,255));
        
        dptHead_panel.setVisible(false);
        dptPositions_panel.setVisible(true);
        
        DepartmentHP = "Position";
        DepartmentPositionView("");
        
        try {
            String[] department = DQ.getDepartments();
            for(int i=0;i<department.length;i++){
            DeptPositionmodel.addElement(department[i]);
        }
        PositionDepartmentName_in.setModel(DeptPositionmodel);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_dptPositions_btnActionPerformed

    private void DepartmentPositionDelete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepartmentPositionDelete_btnActionPerformed
        String Cr = PositionID_in.getText();
        String Dpt = (String) PositionDepartmentName_in.getModel().getSelectedItem();
        String Pos = DepartmentPositionName_in.getText();
        if(Dpt.isEmpty() || Pos.isEmpty()){
            infoBox("Fill in all fields.","ACTION ERROR",CLOSED_OPTION);
        }else{
        try {
            boolean VA = verify_action("Delete");
            if(VA){
                DQ.CRUDE("delete from positions WHERE Id = '"+Cr+"' and Department = '"+Dpt+"'");
                infoBox("Department position deleted successfuly","Notification",CLOSED_OPTION);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        displayDepartments();
        }
    }//GEN-LAST:event_DepartmentPositionDelete_btnActionPerformed

    private void DepartmentPositionUpdate_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepartmentPositionUpdate_btnActionPerformed
        
        String Cr = PositionID_in.getText();
        String Dpt = (String) PositionDepartmentName_in.getModel().getSelectedItem();
        String Pos = DepartmentPositionName_in.getText();
        
        if(Dpt.isEmpty() || Pos.isEmpty()){
            infoBox("Fill in all fields.","ACTION ERROR",CLOSED_OPTION);
        }else{
        try {
            boolean VA = verify_action("Update");
            if(VA){
                DQ.CRUDE("UPDATE positions SET Position = '"+Pos+"' WHERE Id = '"+Cr+"' and Department = '"+Dpt+"'");
                infoBox("Department position updated successfuly","Notification",CLOSED_OPTION);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }//GEN-LAST:event_DepartmentPositionUpdate_btnActionPerformed

    private void DepartmentAddPosition_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepartmentAddPosition_btnActionPerformed
        String Dpt = (String) PositionDepartmentName_in.getModel().getSelectedItem();
        String Pos = DepartmentPositionName_in.getText();
        
        if(Dpt.isEmpty() || Pos.isEmpty()){
            infoBox("Fill in all fields.","ACTION ERROR",CLOSED_OPTION);
        }else{
            try {
                boolean VA = verify_action("Create");
                
                String match = DQ.fetch("Select * from positions where Department='"+Dpt+"' and Position='"+Pos+"'", "Position");
                if(match.equalsIgnoreCase(Pos)){
                    infoBox("Position already exist","Notification",CLOSED_OPTION);
                }else{
                if(VA){
                    DQ.CRUDE("INSERT INTO positions(Department, Position) values('"+Dpt+"','"+Pos+"')");
                    infoBox("Position registered successfuly","Notification",CLOSED_OPTION);
                }}
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
            displayDepartments();
        }
    }//GEN-LAST:event_DepartmentAddPosition_btnActionPerformed

    private void PositionDepartmentName_inItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_PositionDepartmentName_inItemStateChanged
        String dpt = (String) PositionDepartmentName_in.getModel().getSelectedItem();
        DepartmentHP = "Position";
        DepartmentPositionView(" Where department = '"+dpt+"'");
    }//GEN-LAST:event_PositionDepartmentName_inItemStateChanged

    private void UseLog_checkboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_UseLog_checkboxItemStateChanged
       
    }//GEN-LAST:event_UseLog_checkboxItemStateChanged

    private void useLogSave_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useLogSave_btnActionPerformed
        String Log_status = LogStatus_in.getText();
        String schID = LogID_in.getText();
        String LogDate = LogDate_in.getText();
        boolean checked = UseLog_checkbox.getModel().isSelected();

        Date Current_date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("MMMM dd yyyy");
        String dateNow = df.format(Current_date);
        

        try {
            Second_display SD = new Second_display();
            if(dateNow.equals(LogDate)){
                boolean VA = verify_action("Update");
                if(VA){
                    if(checked){
                        if("DONE".equals(Log_status)){
                            infoBox("Sorry but you can't use attendance Log which is already 'DONE'","REQUEST ERROR",CLOSED_OPTION);
                            this.dispose();
                        }else{
                            String inUse = DQ.fetch("dailylogschedule", " Where Status = 'IN-USE'", "sch_ID");
                            if(inUse == null){
                                DQ.CRUDE("Update dailylogschedule set Status = 'IN-USE' where sch_ID = '"+schID+"'");
                                infoBox("CRN: "+schID+" attendance Log is set for today successfuly.","Notification",CLOSED_OPTION);
                                if(!SD.isVisible()){ 
                                    SD.setVisible(true);
                                    SD.start_process();
                                    Att.attendanceProtocol();
                                    this.dispose();
                                }else{ 
                                    SD.dispose();
                                    SD.start_process();
                                    Att.attendanceProtocol();
                                    SD.setVisible(true);
                                    this.dispose();
                                }
                            }else{
                                    boolean yes = infoBox("Changing attendance log will close the system and you \nneed to open it again and set the attendance log.","Warning",OK_CANCEL_OPTION);
                                    if(yes){
                                        System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }else{
                infoBox("Sorry but you can't use attendance Log with different date as today. "
                    + "Use Log Schedule which has a the same date as today or edit the date of "
                    + "the Log in Schedule tab.","REQUEST ERROR",CLOSED_OPTION);
                this.dispose();
            }
        } catch (ClassNotFoundException | SQLException | ParseException | IOException  ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        all_Logs();
    }//GEN-LAST:event_useLogSave_btnActionPerformed

    private void Department_inItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Department_inItemStateChanged
        
    }//GEN-LAST:event_Department_inItemStateChanged

    private void Department_inMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Department_inMouseClicked
        
    }//GEN-LAST:event_Department_inMouseClicked

    private void DateWDR_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateWDR_inActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DateWDR_inActionPerformed

    private void YearWDR_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YearWDR_inActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_YearWDR_inActionPerformed

    private void YearWDR_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_YearWDR_inKeyTyped
        char in = evt.getKeyChar();
        if(((in < '0') || (in > '9')) && (in != '\b')){
            evt.consume();
        }
    }//GEN-LAST:event_YearWDR_inKeyTyped

    private void DateWDR_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DateWDR_inKeyTyped
        char in = evt.getKeyChar();
        if(Character.isLetter(in) || Character.isISOControl(in)){
        }else{
            evt.consume();
        }
    }//GEN-LAST:event_DateWDR_inKeyTyped

    private void IDnumberWDR_inFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_IDnumberWDR_inFocusLost
        String c = IDnumberWDR_in.getText();
        if("".equals(c)){IDnumberWDR_in.setText("ID NUMBER");}
        else{}
    }//GEN-LAST:event_IDnumberWDR_inFocusLost

    private void IDnumberWDR_inFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_IDnumberWDR_inFocusGained
        String c = IDnumberWDR_in.getText();
        if("ID NUMBER".equals(c)){IDnumberWDR_in.setText("");}
        else{}
    }//GEN-LAST:event_IDnumberWDR_inFocusGained

    private void GoWDR_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GoWDR_btnActionPerformed
        String[] Dte = { one.getText(),two.getText(),three.getText(),four.getText(),five.getText(),six.getText(),seven.getText()};
        for(int i=0;i<Dte.length;i++){
            if("".equals(Dte[i]) || Dte[i].isEmpty() || Dte[i].isBlank()){}
            else{
                if(String.valueOf(Dte[i]).length() < 2){
                    try {
                        weeklyAttendanceRecords("0"+String.valueOf(Dte[i]));
                        //logNamesPerDate();
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    try {
                        weeklyAttendanceRecords(String.valueOf(Dte[i]));
                        //logNamesPerDate();
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }
        }
    }//GEN-LAST:event_GoWDR_btnActionPerformed

    private void DepartmentPositionName_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DepartmentPositionName_inKeyTyped
        
    }//GEN-LAST:event_DepartmentPositionName_inKeyTyped

    private void LeaveExcuse_inKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LeaveExcuse_inKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LeaveExcuse_inKeyPressed

    private void LeaveExcuse_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LeaveExcuse_inKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_LeaveExcuse_inKeyTyped

    private void PersonnelPassword_inFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PersonnelPassword_inFocusLost
        String P = new String(PersonnelPassword_in.getPassword());
        if(P.length() < 8 || P.length() > 16){
            prompt_Message.setVisible(true);
            prompt_Message.setText("Password must be 8 to 16 characters.");
            prompt_Message.setForeground(Color.red);
        }else{}
    }//GEN-LAST:event_PersonnelPassword_inFocusLost

    private void IDnumberWDR_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IDnumberWDR_inKeyTyped
        char in = evt.getKeyChar();
        if(((in < '0') || (in > '9')) && (in != '\b')){
            evt.consume();
        }
    }//GEN-LAST:event_IDnumberWDR_inKeyTyped

    private void IDnumberSDR_inFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_IDnumberSDR_inFocusGained
        String ID = IDnumberSDR_in.getText();
        if("ID Number".equals(ID)){
            IDnumberSDR_in.setText("");
        }
    }//GEN-LAST:event_IDnumberSDR_inFocusGained

    
    private void printDailyReport_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printDailyReport_btnActionPerformed
            String DRCb= String.valueOf(jComboBox1.getModel().getSelectedItem());
            
            if(DRCb.equalsIgnoreCase("GENERAL REPORT")){printDailyReport();}
            else if(DRCb.equalsIgnoreCase("ABSENT SUMMARY REPORT")){printDailyAbsentReport();}
            else if(DRCb.equalsIgnoreCase("TARDINESS SUMMARY REPORT")){printDailyTardinessReport();}
            else{}
    }//GEN-LAST:event_printDailyReport_btnActionPerformed
    
    public void MAio(){
        try{
            String LognameSearch = (String) SDRLogName_in.getModel().getSelectedItem();
            SimpleDateFormat df = new SimpleDateFormat("MMMM dd yyyy");
            String MonthYear = df.format(DateSDR_in.getDate());
            DefaultTableModel de = (DefaultTableModel)DailyReport_table.getModel();
            JRDataSource datasource = new JRTableModelDataSource(de);
            String reportSource = "C:/Users/Acer/Desktop/Final Capstone Project/SmartLog_System/src/Reports/DailyReportMAio.jrxml";
            
            JasperReport jr = JasperCompileManager.compileReport(reportSource);
            
            Map params = new HashMap<String, Object>();
            params.put("logName", LognameSearch);
            params.put("Date", MonthYear);
            
            JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
            
            JasperViewer.viewReport(jp, false);
        }catch (JRException e){Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, e);}
        
    }
    
    public void ONE(String logtype){
        try{
            String LognameSearch = (String) SDRLogName_in.getModel().getSelectedItem();
            SimpleDateFormat df = new SimpleDateFormat("MMMM dd yyyy");
            String MonthYear = df.format(DateSDR_in.getDate());
            DefaultTableModel de = (DefaultTableModel)DailyReport_table.getModel();
            JRDataSource datasource = new JRTableModelDataSource(de);
            String reportSource = "C:/Users/Acer/Desktop/Final Capstone Project/SmartLog_System/src/Reports/DailyReportONE.jrxml";
            
            JasperReport jr = JasperCompileManager.compileReport(reportSource);
            
            Map params = new HashMap<String, Object>();
            params.put("logName", LognameSearch);
            params.put("Date", MonthYear);
            params.put("Logtype", logtype);
            
            JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
            
            JasperViewer.viewReport(jp, false);
        }catch (JRException e){Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, e);}
        
    }
    
    public void IO(String logtype){
        try{
            String LognameSearch = (String) SDRLogName_in.getModel().getSelectedItem();
            SimpleDateFormat df = new SimpleDateFormat("MMMM dd yyyy");
            String MonthYear = df.format(DateSDR_in.getDate());
            DefaultTableModel de = (DefaultTableModel)DailyReport_table.getModel();
            JRDataSource datasource = new JRTableModelDataSource(de);
            String reportSource = "C:/Users/Acer/Desktop/Final Capstone Project/SmartLog_System/src/Reports/DailyReportIO.jrxml";
            
            JasperReport jr = JasperCompileManager.compileReport(reportSource);
            
            Map params = new HashMap<String, Object>();
            params.put("logName", LognameSearch);
            params.put("Date", MonthYear);
            params.put("Logtype", logtype);
            
            JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
            
            JasperViewer.viewReport(jp, false);
        }catch (JRException e){Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, e);}
        
    }
    
    public void MIMOaiao(){
        try{
            String LognameSearch = (String) SDRLogName_in.getModel().getSelectedItem();
            SimpleDateFormat df = new SimpleDateFormat("MMMM dd yyyy");
            String MonthYear = df.format(DateSDR_in.getDate());
            DefaultTableModel de = (DefaultTableModel)DailyReport_table.getModel();
            JRDataSource datasource = new JRTableModelDataSource(de);
            String reportSource = "C:/Users/Acer/Desktop/Final Capstone Project/SmartLog_System/src/Reports/DailyreportMIOAIO.jrxml";
            
            JasperReport jr = JasperCompileManager.compileReport(reportSource);
            
            Map params = new HashMap<String, Object>();
            params.put("logName", LognameSearch);
            params.put("Date", MonthYear);
            
            JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
            
            JasperViewer.viewReport(jp, false);
        }catch (JRException e){Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, e);}
        
    }
    
    private void idMDR_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idMDR_inKeyTyped
        char in = evt.getKeyChar();
        if(((in < '0') || (in > '9')) && (in != '\b')){
            evt.consume();
        }else{
            try {
            char t = evt.getKeyChar();
            String id = idMDR_in.getText();
            String name = DQ.fetch("select concat(Lname,', ',Fname,' ',MI,'.') as name from personnels where IDnumber='"+id+t+"'", "name");
            if(!"".equals(name)){
                nameMDR_in.setText(name);
            }else{
                nameMDR_in.setText("");
            }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_idMDR_inKeyTyped

    private void setMDR_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_setMDR_inKeyTyped
        char in = evt.getKeyChar();
        if(((in < '0') || (in > '9')) && (in != '\b')){
            evt.consume();
        }else{
            String c = setMDR_in.getText()+in;
            if(c.length() > 2){evt.consume();}
            else{
                String d = setMDR_in.getText();
                if("0".equals(d) || "".equals(d)){
                    if(in > '2'){evt.consume();}
                }else{evt.consume();}
            }
        }
    }//GEN-LAST:event_setMDR_inKeyTyped

    private void searchByLogDTR_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByLogDTR_btnActionPerformed
        try {
            String id = idMDR_in.getText();
            String name = nameMDR_in.getText();
            String c = String.valueOf(monthMDR_in.getDate());
            if(id.isEmpty()||c.isEmpty()){infoBox("All fields are required","Error",CLOSED_OPTION);}
            else{
                if(name.isEmpty()){
                    idMDR_in.setForeground(Color.red);
                }else{
                    halfMonthAttendanceRecords(true);
                }
            }
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchByLogDTR_btnActionPerformed

    private void idMDR_inFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idMDR_inFocusGained
        idMDR_in.setForeground(Color.black);
    }//GEN-LAST:event_idMDR_inFocusGained

    private void Position_inItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Position_inItemStateChanged
        
    }//GEN-LAST:event_Position_inItemStateChanged

    private void Department_inKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Department_inKeyPressed
        
    }//GEN-LAST:event_Department_inKeyPressed

    private void Department_inFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Department_inFocusLost
        String dpt = (String) Department_in.getModel().getSelectedItem();
        DP_model.removeAllElements();
        try {
            String[] Positions = DQ.arrayDataFetch("positions where Department = '"+dpt+"'","positions", " where Department = '"+dpt+"'", "Position");
            int i=0;
            do{
                DP_model.addElement(Positions[i]);
                i++;
            }while(i<Positions.length);

            Position_in.setModel(DP_model);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Department_inFocusLost

    private void Lname_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Lname_inKeyTyped
        char in = evt.getKeyChar();
        if(Character.isLetter(in) || Character.isISOControl(in)){
        }else if(in == ' '){}
        else{
            evt.consume();
        }
    }//GEN-LAST:event_Lname_inKeyTyped

    private void Fname_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Fname_inKeyTyped
        char in = evt.getKeyChar();
        if(Character.isLetter(in) || Character.isISOControl(in)){
        }else if(in == ' '){}
        else{
            evt.consume();
        }
    }//GEN-LAST:event_Fname_inKeyTyped

    private void MI_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MI_inKeyTyped
        String M = MI_in.getText();
        char in = evt.getKeyChar();
        
        if(Character.isLetter(in) || Character.isISOControl(in)){
            if(M.length() > 1){evt.consume();}
        }else{
            evt.consume();
        }
    }//GEN-LAST:event_MI_inKeyTyped

    private void PersonnelSearch_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PersonnelSearch_inKeyTyped
        char in = evt.getKeyChar();
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"ID","NAME","GENDER","AGE","POSITION","BT NAME","BT ADDRESS"}, 0);
        if(((in < '0') || (in > '9')) && (in != '\b')){
            evt.consume();
        }else{
            try {
                String ID_num = PersonnelSearch_in.getText() + in;
                Class.forName("com.mysql.cj.jdbc.Driver");
        
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartlogdb?useTimezone=true&serverTimezone=UTC&useSSL=false","root","Anonsawon27");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT personnels.IDnumber, personnels.Lname, personnels.HireDate, "
                        + "personnels.Fname, personnels.MI, personnels.Gender, personnels.Age, personnels.Preveledge,"
                        + "departments.Position, devices.BTname, devices.BTaddress, personnels.Password FROM personnels "
                        + "INNER JOIN devices ON devices.Device_ID = personnels.Device_ID "
                        + "INNER JOIN departments ON departments.id_Departments = personnels.Dpt_ID Where personnels.IDnumber LIKE '%"+ID_num+"%'");
                
                while(rs.next()){
                    String ID = rs.getString("personnels.IDnumber");
                    String Ln = rs.getString("personnels.Lname");
                    String Fn = rs.getString("personnels.Fname");
                    String Mi = rs.getString("personnels.MI");
                    String Gndr = rs.getString("personnels.Gender");
                    String Age = rs.getString("personnels.Age");
                    //String Pswrd = rs.getString("personnels.Password");
                    String Name = Ln+", "+Fn+" "+Mi+".";
                    if(" ".equals(Mi)||"".equals(Mi)){Name = Ln+", "+Fn+" "+Mi+"";}
                    String Pos = rs.getString("departments.Position");
                    String BTn = rs.getString("devices.BTname");
                    String BTa = rs.getString("devices.BTaddress"); 
                    
                    dtm.addRow(new Object[]{ID,Name,Gndr,Age,Pos,BTn,BTa});
                }
                Personnels_table.setModel(dtm);
                tableHeader_custom(Personnels_table);
                Personnels_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tableColumnSize(Personnels_table, 0, 100);
                tableColumnSize(Personnels_table, 1, 200);
                tableColumnSize(Personnels_table, 2, 90);
                tableColumnSize(Personnels_table, 3, 50);
                tableColumnSize(Personnels_table, 4, 145);
                tableColumnSize(Personnels_table, 5, 150);
                tableColumnSize(Personnels_table, 6, 150);
                
            }catch (ClassNotFoundException | SQLException ex){
                       System.out.println(ex);
            }
        }
    }//GEN-LAST:event_PersonnelSearch_inKeyTyped

    private void Preveledge_inItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Preveledge_inItemStateChanged
        String level = (String) Preveledge_in.getModel().getSelectedItem();
        prompt_Message.setForeground(new Color(4,8,114));
        prompt_Message.setVisible(true);
        if("Level 1".equals(level)){prompt_Message.setText("Grant access to View only.");}
        else if("Level 2".equals(level)){prompt_Message.setText("Grant access to View, Add, and Update data.");}
        else if("Level 3".equals(level)){prompt_Message.setText("Grant access to View, Add, Update, and Delete data.");}
        else{prompt_Message.setVisible(false);}
    }//GEN-LAST:event_Preveledge_inItemStateChanged

    
    private void printPersonnelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printPersonnelsActionPerformed
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartlogdb?useTimezone=true&serverTimezone=UTC&useSSL=false","root","Anonsawon27");
                JasperDesign jasdi = JRXmlLoader.load("C:\\Users\\Acer\\Desktop\\Final Capstone Project\\SmartLog_System\\src\\Reports\\listPersonnels.jrxml");
                String sql = "SELECT personnels.IDnumber, Concat(personnels.Lname,', ', \n" +
                             "personnels.Fname,' ', personnels.MI,'.') as Name, personnels.Gender, personnels.Age,\n" +
                             "departments.Position FROM personnels\n" +
                             "INNER JOIN departments ON departments.id_Departments = personnels.Dpt_ID";
                JRDesignQuery newQuery = new JRDesignQuery();
                newQuery.setText(sql);
                jasdi.setQuery(newQuery);
                JasperReport js = JasperCompileManager.compileReport(jasdi);
                JasperPrint jp = JasperFillManager.fillReport(js,null,con);
                //JasperExportManager.exportReportToPdf(jp);//exportReportToHtmlfile(jp,ore);
                JasperViewer.viewReport(jp,false);
        } catch (JRException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printPersonnelsActionPerformed

    private void DateSDR_inPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DateSDR_inPropertyChange
        
    }//GEN-LAST:event_DateSDR_inPropertyChange

    private void DateSDR_inFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DateSDR_inFocusLost
        
    }//GEN-LAST:event_DateSDR_inFocusLost

    private void DateSDR_inKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DateSDR_inKeyPressed
        
    }//GEN-LAST:event_DateSDR_inKeyPressed

    private void goDailyReport_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goDailyReport_btnActionPerformed
        try{
        SimpleDateFormat Df = new SimpleDateFormat("MMMM dd yyyy");
        String dteLOG = Df.format(DateSDR_in.getDate());
        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        
            cb.removeAllElements();
            try {
                String logs[] = DQ.arrayFetch("dailylogschedule where Date = '"+dteLOG+"'", "SELECT * FROM dailylogschedule where Date = '"+dteLOG+"'", "schName");
                int i=0;
                while(i<logs.length){
                    if(logs[i]==null){}
                    else{cb.addElement(logs[i]);}
                    i++;
                }

                SDRLogName_in.setModel(cb);

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(NullPointerException e){infoBox("Fill in date.","ERROR",CLOSED_OPTION);}
        
    }//GEN-LAST:event_goDailyReport_btnActionPerformed

    private void LeaveIdNumberSearch_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeaveIdNumberSearch_btnActionPerformed
        String byID = LeaveIdNumber_in.getText();
        
        try{
            SimpleDateFormat df = new SimpleDateFormat("MMMM");
            String M = df.format(LeaveMonth_in.getDate());
            SimpleDateFormat ydf = new SimpleDateFormat("YYYY");
            String MM = ydf.format(LeaveYear_in.getDate());
            /*String MM[] = MMM.split("-");
            String M = MM[0];*/
            
            if(M.isEmpty() || MM.isEmpty()){
                infoBox("Fill in both month and year.","ERROR",CLOSED_OPTION);
            }else{
                if(M == null || M.isBlank() || M.isEmpty()){ M="";}
                else{M = "StartDate LIKE '"+M+"%"+MM+"'";}

                System.out.println("Like: "+M);

                if(byID == null || byID.isBlank() || byID.isEmpty()){ byID="";}
                else{byID = " Where IDnumber='"+byID+"'";}

                if((byID == null || byID.isBlank() || byID.isEmpty()) && (M == null || M.isBlank() || M.isEmpty())){
                    Display_LeaveRegister("");
                }else if(!(byID == null || byID.isBlank() || byID.isEmpty()) && (M == null || M.isBlank() || M.isEmpty())){
                    Display_LeaveRegister(byID);
                }else if((byID == null || byID.isBlank() || byID.isEmpty()) && !(M == null || M.isBlank() || M.isEmpty())){
                    Display_LeaveRegister(" Where "+M);
                }else{
                    Display_LeaveRegister(byID+ " and "+M);
                }
            }
        }catch(NullPointerException e){
            if(!(byID == null || byID.isBlank() || byID.isEmpty())){
                Display_LeaveRegister(" Where IDnumber='"+byID+"'");
            }else{ infoBox("Fill in both month and year.","ERROR",CLOSED_OPTION);Display_LeaveRegister("");}
            
        }
    }//GEN-LAST:event_LeaveIdNumberSearch_btnActionPerformed

    private void LeavePrint_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeavePrint_btnActionPerformed
                   
         
            try{
                SimpleDateFormat df = new SimpleDateFormat("MMMM");
                String Month = df.format(LeaveMonth_in.getDate());
                SimpleDateFormat ydf = new SimpleDateFormat("YYYY");
                String MM = ydf.format(LeaveYear_in.getDate()); 

                if(Month.isEmpty() || MM.isEmpty()){
                infoBox("Fill in both month and year.","ERROR",CLOSED_OPTION);
                }else{
                    try{
                    DefaultTableModel de = (DefaultTableModel)Leave_table.getModel();
                    JRDataSource datasource = new JRTableModelDataSource(de);
                    String reportSource = "C:/Users/Acer/Desktop/Final Capstone Project/SmartLog_System/src/Reports/leaveReport.jrxml";

                    JasperReport jr = JasperCompileManager.compileReport(reportSource);

                    Map params = new HashMap<String, Object>();
                    params.put("Month", "Month: "+Month);

                    JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);

                    JasperViewer.viewReport(jp, false);
                    }catch (JRException e){Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, e);}
                }
            }catch(NullPointerException e){
                    try{
                    DefaultTableModel de = (DefaultTableModel)Leave_table.getModel();
                    JRDataSource datasource = new JRTableModelDataSource(de);
                    String reportSource = "C:/Users/Acer/Desktop/Final Capstone Project/SmartLog_System/src/Reports/leaveReport.jrxml";

                    JasperReport jr = JasperCompileManager.compileReport(reportSource);

                    Map params = new HashMap<String, Object>();
                    params.put("Month", "");

                    JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);

                    JasperViewer.viewReport(jp, false);
                    }catch (JRException v){Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, v);}
            }
         
    }//GEN-LAST:event_LeavePrint_btnActionPerformed

    public void printDailyAbsentReport(){
        DefaultTableModel absentModel = new DefaultTableModel(new Object[]{"ID Number", "Name", "M_Time","A_Time"},0);
        try {
            String Logname = (String) SDRLogName_in.getModel().getSelectedItem();
            SimpleDateFormat df = new SimpleDateFormat("MMMM dd yyyy");
            String LDate = df.format(DateSDR_in.getDate());
            
            //Fetch logCycle
            String LogType = DQ.fetch("Select * from dailylogschedule where schName = '"+Logname+"' and Date = '"+LDate+"'", "LogIO_cycle");
        
            //Fetch all IDnumber
            String[] AllID = DQ.arrayFetch("smartlogdb.personnels","SELECT * FROM smartlogdb.personnels", "IDnumber");
            String[] MinTime = new String[AllID.length],
                     AinTime = new String[AllID.length],
                     MoutTime = new String[AllID.length],
                     AoutTime = new String[AllID.length],
                    namesOfEachID = new String[AllID.length];
            String[] MlateTime = new String[AllID.length],
                     AlateTime = new String[AllID.length];
            String Morningabsent[] = new String[AllID.length];
            String Afternoonabsent[] = new String[AllID.length];
            
            //fetch each Fullname per IDnumber
            for(int i=0;i<AllID.length;i++){
                namesOfEachID[i] = DQ.fetch("SELECT concat(Lname,', ',Fname,' ',MI) as Fullname FROM smartlogdb.personnels where IDnumber = '"+AllID[i]+"'", "Fullname");
            }
            
            if("Morning In-Out and Afternoon In-Out".equalsIgnoreCase(LogType) || 
               "Morning In-Out".equalsIgnoreCase(LogType) ||
               "Morning In".equalsIgnoreCase(LogType)){
                
                for(int i=0;i<AllID.length;i++){
                    if("Morning In".equalsIgnoreCase(LogType)){
                        //Fetch data perID afternoon

                            MinTime[i] = DQ.fetch("SELECT * FROM smartlogdb.checkinout where IDnumber='"+ AllID[i]+"' and Log_Name='"+Logname+"' and Date='"+LDate+"' and Check_type='Mi'","LogTime");

                            Morningabsent[i] = "";
                            if("".equals(MinTime[i]) || MinTime[i].isEmpty()){
                             Morningabsent[i] = "ABSENT";
                            }
                    }else{
                        //Fetch data perID morning
                            MinTime[i] = DQ.fetch("SELECT * FROM smartlogdb.checkinout where IDnumber='"+ AllID[i]+"' and Log_Name='"+Logname+"' and Date='"+LDate+"' and Check_type='Mi'","LogTime");
                            MoutTime[i] = DQ.fetch("SELECT * FROM smartlogdb.checkinout where IDnumber='"+ AllID[i]+"' and Log_Name='"+Logname+"' and Date='"+LDate+"' and Check_type='Mo'","LogTime");

                            Morningabsent[i] = "";
                            if(("".equals(MinTime[i]) || MinTime[i].isEmpty())&&("".equals(MoutTime[i]) || MoutTime[i].isEmpty())){
                             Morningabsent[i] = "ABSENT";
                            }else if((!"".equals(MinTime[i]) || !MinTime[i].isEmpty())&&("".equals(MoutTime[i]) || MoutTime[i].isEmpty())){
                             Morningabsent[i] = "ABSENT";
                            }
                        }
                }
            }
            
            if("Morning In-Out and Afternoon In-Out".equalsIgnoreCase(LogType) || 
               "Afternoon In-Out".equalsIgnoreCase(LogType) ||
               "Afternoon In".equalsIgnoreCase(LogType)){
                
                for(int i=0;i<AllID.length;i++){
                    if("Afternoon In".equalsIgnoreCase(LogType)){
                        //Fetch data perID afternoon

                            AinTime[i] = DQ.fetch("SELECT * FROM smartlogdb.checkinout where IDnumber='"+ AllID[i]+"' and Log_Name='"+Logname+"' and Date='"+LDate+"' and Check_type='Ai'","LogTime");

                            Afternoonabsent[i] = "";
                            if("".equals(AinTime[i]) || AinTime[i].isEmpty()){
                             Afternoonabsent[i] = "ABSENT";
                            }else if((!"".equals(AinTime[i]) || !AinTime[i].isEmpty())&&("".equals(AoutTime[i]) || AoutTime[i].isEmpty())){
                             Afternoonabsent[i] = "ABSENT";
                            }
                    }
                    else{
                        //Fetch data perID afternoon
                            AinTime[i] = DQ.fetch("SELECT * FROM smartlogdb.checkinout where IDnumber='"+ AllID[i]+"' and Log_Name='"+Logname+"' and Date='"+LDate+"' and Check_type='Ai'","LogTime");
                            AoutTime[i] = DQ.fetch("SELECT * FROM smartlogdb.checkinout where IDnumber='"+ AllID[i]+"' and Log_Name='"+Logname+"' and Date='"+LDate+"' and Check_type='Ao'","LogTime");

                            Afternoonabsent[i] = "";
                            if(("".equals(AinTime[i]) || AinTime[i].isEmpty())&&("".equals(AoutTime[i]) || AoutTime[i].isEmpty())){
                             Afternoonabsent[i] = "ABSENT";
                            }else if((!"".equals(AinTime[i]) || !AinTime[i].isEmpty())&&("".equals(AoutTime[i]) || AoutTime[i].isEmpty())){
                             Afternoonabsent[i] = "ABSENT";
                            }
                        }
                }
                
            }
            
            //add to model
            for(int i=0;i<AllID.length;i++){
                try{
                if(("".equals(Morningabsent[i]) || Morningabsent[i].isEmpty()) && ("".equals(Afternoonabsent[i]) || Afternoonabsent[i].isEmpty())){
                }else{absentModel.addRow(new Object[]{AllID[i],namesOfEachID[i],Morningabsent[i],Afternoonabsent[i]});}
                }catch(ArrayIndexOutOfBoundsException e){
               }
            }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        try{
            String LognameSearch = (String) SDRLogName_in.getModel().getSelectedItem();
            SimpleDateFormat df = new SimpleDateFormat("MMMM dd yyyy");
            String MonthYear = df.format(DateSDR_in.getDate());
            JRDataSource datasource = new JRTableModelDataSource(absentModel);
            String reportSource = "C:/Users/Acer/Desktop/Final Capstone Project/SmartLog_System/src/Reports/DailyAbsentReport.jrxml";
            
            //Fetch logCycle
            String LogType = DQ.fetch("Select * from dailylogschedule where schName = '"+LognameSearch+"' and Date = '"+MonthYear+"'", "LogIO_cycle");
        
            JasperReport jr = JasperCompileManager.compileReport(reportSource);
            
            Map params = new HashMap<String, Object>();
            params.put("logName", LognameSearch);
            params.put("Date", MonthYear);
            params.put("LogType", LogType);
            
            JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
            
            JasperViewer.viewReport(jp, false);
        }catch (JRException e){Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, e);} catch (ClassNotFoundException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void printDailyTardinessReport(){
        DefaultTableModel lateModel = new DefaultTableModel(new Object[]{"ID Number", "Name", "M_inTime","M_lateTime","A_inTime","A_lateTime"},0);
        try {
            String Logname = (String) SDRLogName_in.getModel().getSelectedItem();
            SimpleDateFormat df = new SimpleDateFormat("MMMM dd yyyy");
            String LDate = df.format(DateSDR_in.getDate());
            
            //Fetch logtype
            String logType = DQ.fetch("SELECT * FROM smartlogdb.dailylogschedule where schName='"+Logname+"' and Date='"+LDate+"'","LogIO_cycle");
            //Fetch all IDnumber
            String[] AllID = DQ.arrayFetch("smartlogdb.personnels","SELECT * FROM smartlogdb.personnels", "IDnumber");
            String[] MinTime = new String[AllID.length],
                    AinTime = new String[AllID.length],
                    namesOfEachID = new String[AllID.length];
            String[] MlateTime = new String[AllID.length],
                     AlateTime = new String[AllID.length];
            //fetch each Fullname per IDnumber
            for(int i=0;i<AllID.length;i++){
                namesOfEachID[i] = DQ.fetch("SELECT concat(Lname,', ',Fname,' ',MI) as Fullname FROM smartlogdb.personnels where IDnumber = '"+AllID[i]+"'", "Fullname");
            }
            
            if("Morning In-Out and Afternoon In-Out".equalsIgnoreCase(logType) || 
               "Morning In-Out".equalsIgnoreCase(logType) ||
               "Morning In".equalsIgnoreCase(logType)){
                //Fetch data perID morning
                for(int i=0;i<AllID.length;i++){
                    MinTime[i] = DQ.fetch("SELECT * FROM smartlogdb.checkinout where IDnumber='"+ AllID[i]+"' and Log_Name='"+Logname+"' and Date='"+LDate+"' and Check_type='Mi'","LogTime");
                    }
                //Fetch data perID Morning late
                for(int i=0;i<MlateTime.length;i++){
                    MlateTime[i] = DQ.fetch("SELECT * FROM smartlogdb.checkinout where IDnumber='"+ AllID[i]+"' and Log_Name='"+Logname+"' and Date='"+LDate+"' and Check_type='Mi'","LateTime");
                    try{
                    if("".equals(MlateTime[i]) || MlateTime[i].isEmpty()){
                        MlateTime[i] = "0";
                    }}catch(ArrayIndexOutOfBoundsException|NullPointerException e){
                        MlateTime[i] = "0";
                    }
                }
            }
            
            if("Morning In-Out and Afternoon In-Out".equalsIgnoreCase(logType) || 
               "Afternoon In-Out".equalsIgnoreCase(logType) ||
               "Afternoon In".equalsIgnoreCase(logType)){
                //Fetch data perID afternoon
                for(int i=0;i<AllID.length;i++){
                    AinTime[i] = DQ.fetch("SELECT * FROM smartlogdb.checkinout where IDnumber='"+ AllID[i]+"' and Log_Name='"+Logname+"' and Date='"+LDate+"' and Check_type='Ai'","LogTime");
                    }
                //Fetch data perID afternoon late
                for(int i=0;i<AlateTime.length;i++){
                    AlateTime[i] = DQ.fetch("SELECT * FROM smartlogdb.checkinout where IDnumber='"+ AllID[i]+"' and Log_Name='"+Logname+"' and Date='"+LDate+"' and Check_type='Ai'","LateTime");
                    try{
                    if("".equals(AlateTime[i]) || AlateTime[i].isEmpty()){
                        AlateTime[i] = "0";
                    }}catch(ArrayIndexOutOfBoundsException|NullPointerException e){
                        AlateTime[i] = "0";
                    }
                }
            }
            
            //add to model
            for(int i=0;i<AllID.length;i++){
                try{
                if(("0".equals(MlateTime[i]) && "0".equals(AlateTime[i])) || (MlateTime[i].isEmpty() && AlateTime[i].isEmpty())){}
                else{
                    lateModel.addRow(new Object[]{AllID[i],namesOfEachID[i],MinTime[i],MlateTime[i],AinTime[i],AlateTime[i]});
                }
                }catch(ArrayIndexOutOfBoundsException e){
               }
            }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        try{
            String LognameSearch = (String) SDRLogName_in.getModel().getSelectedItem();
            SimpleDateFormat df = new SimpleDateFormat("MMMM dd yyyy");
            String MonthYear = df.format(DateSDR_in.getDate());
            JRDataSource datasource = new JRTableModelDataSource(lateModel);
            String reportSource = "C:/Users/Acer/Desktop/Final Capstone Project/SmartLog_System/src/Reports/DailyLateReport.jrxml";
            
            //Fetch info of log
            String fetchMTL = DQ.fetch("SELECT * FROM smartlogdb.dailylogschedule where schName='"+LognameSearch+"' and Date='"+MonthYear+"'", "StartTime_1");
            String fetchATL = DQ.fetch("SELECT * FROM smartlogdb.dailylogschedule where schName='"+LognameSearch+"' and Date='"+MonthYear+"'", "StartTime_2");
            //Fetch logtype
            String logType = DQ.fetch("SELECT * FROM smartlogdb.dailylogschedule where schName='"+LognameSearch+"' and Date='"+MonthYear+"'","LogIO_cycle");
            
            JasperReport jr = JasperCompileManager.compileReport(reportSource);
            
            Map params = new HashMap<String, Object>();
            params.put("logName", LognameSearch);
            params.put("Date", MonthYear);
            params.put("AlogTime", fetchATL);
            params.put("MlogTime", fetchMTL);
            params.put("LogType",logType);
            
            JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
            
            JasperViewer.viewReport(jp, false);
        }catch (JRException e){Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, e);} catch (ClassNotFoundException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void printDailyReport(){
        try {
            String Lname = (String) SDRLogName_in.getModel().getSelectedItem();
            SimpleDateFormat df = new SimpleDateFormat("MMMM dd yyyy");
            String LDate = df.format(DateSDR_in.getDate());
            String LogType = DQ.fetch("Select * from dailylogschedule where schName = '"+Lname+"' and Date = '"+LDate+"'", "LogIO_cycle");
        
            if("Morning In".equals(LogType)){ONE("MORNING");}
            else if("Afternoon In".equals(LogType)){ONE("AFTERNOON");}
            else if("Morning In-Out".equals(LogType)){ IO("MORNING");}
            else if("Afternoon In-Out".equals(LogType)){IO("AFTERNOON");}
            else if("Morning In and Afternoon Out".equals(LogType)){MAio();}
            else if("Morning In-Out and Afternoon In-Out".equals(LogType)){MIMOaiao();}
            else{}
        
        } catch (ClassNotFoundException | SQLException ex) {
            infoBox("No data found.","GENERATING REPORT ERROR",CLOSED_OPTION);
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printWeeklyAbsent() throws ClassNotFoundException, SQLException{
        DefaultTableModel absentModel = new DefaultTableModel(new Object[]{"ID Number", "Name", "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"},0);
        String[] Dte = { one.getText(),two.getText(),three.getText(),four.getText(),five.getText(),six.getText(),seven.getText()};
        String MM = DateWDR_in.getText(),
               YY = YearWDR_in.getText();
        String[] logName = { String.valueOf(one_log.getModel().getSelectedItem()),String.valueOf(two_log.getModel().getSelectedItem()),String.valueOf(three_log.getModel().getSelectedItem()),String.valueOf(four_log.getModel().getSelectedItem()),
                                 String.valueOf(five_log.getModel().getSelectedItem()),String.valueOf(six_log.getModel().getSelectedItem()),String.valueOf(seven_log.getModel().getSelectedItem())};
        
        //Fetch all IDnumber
        String[] AllID = DQ.arrayFetch("smartlogdb.personnels","SELECT * FROM smartlogdb.personnels", "IDnumber");
        String[] namesOfEachID = new String[AllID.length];
        
        //fetch each Fullname per IDnumber
            for(int i=0;i<AllID.length;i++){
                namesOfEachID[i] = DQ.fetch("SELECT concat(Lname,', ',Fname,' ',MI) as Fullname FROM smartlogdb.personnels where IDnumber = '"+AllID[i]+"'", "Fullname");
            }
       String[][] dataM = new String[AllID.length][7];
       String[][] dataA = new String[AllID.length][7];
       for(int j=0;j<AllID.length;j++){
        //Repeat for 7 times - one week
        int s=0;
        
        for(int i=0;i<7;i++){
            String MlogTime = "", AlogTime = "", MologTime = "", AologTime = "";
            //Fetch logtype
            if(Dte[i].length()<2){Dte[i] = "0"+Dte[i];}
            String logType = DQ.fetch("SELECT * FROM smartlogdb.dailylogschedule where schName='"+logName[i]+"' and Date='"+MM+" "+Dte[i]+" "+YY+"'","LogIO_cycle");
            String doneLog = DQ.fetch("SELECT * FROM smartlogdb.dailylogschedule where schName='"+logName[i]+"' and Date='"+MM+" "+Dte[i]+" "+YY+"'","Status");
            if(doneLog.equalsIgnoreCase("SCHEDULED")){
                logType = "";
            }
            
            if("".equals(logType) || logType.isEmpty()){}
            else{
                if("Morning In-Out and Afternoon In-Out".equalsIgnoreCase(logType) || 
                    "Morning In-Out".equalsIgnoreCase(logType) ||
                    "Morning In".equalsIgnoreCase(logType)){
                     //Fetch data perID Morning late
                     if(Dte[i].length()<2){Dte[i] = "0"+Dte[i];}
                     
                     if("Morning In".equalsIgnoreCase(logType)){
                         MlogTime = DQ.fetch("SELECT * FROM smartlogdb.checkinout where IDnumber='"+ AllID[j]+"' and Log_Name='"+logName[i]+"' and Date='"+MM+" "+Dte[i]+" "+YY+"' and Check_type='Mi'","LogTime");
                         try{
                         if("".equals(MlogTime) || MlogTime.isEmpty()){
                             MlogTime = "X";
                         }else{MlogTime = "/";}}catch(ArrayIndexOutOfBoundsException e){
                             MlogTime = "X";
                         }
                     }else{
                     
                         MlogTime = DQ.fetch("SELECT * FROM smartlogdb.checkinout where IDnumber='"+ AllID[j]+"' and Log_Name='"+logName[i]+"' and Date='"+MM+" "+Dte[i]+" "+YY+"' and Check_type='Mi'","LogTime");
                         MologTime = DQ.fetch("SELECT * FROM smartlogdb.checkinout where IDnumber='"+ AllID[j]+"' and Log_Name='"+logName[i]+"' and Date='"+MM+" "+Dte[i]+" "+YY+"' and Check_type='Mo'","LogTime");
                         
                         try{
                         if("".equals(MlogTime) || MlogTime.isEmpty()){
                             MlogTime = "X";
                         }else if((!"".equals(MlogTime) || !MlogTime.isEmpty()) && ("".equals(MologTime) || MologTime.isEmpty())){
                             MlogTime = "X";
                         }else{MlogTime = "/";}}catch(ArrayIndexOutOfBoundsException e){
                             MlogTime = "X";
                         }
                     }
                     }
                 }
                
                if("Morning In-Out and Afternoon In-Out".equalsIgnoreCase(logType) || 
                    "Afternoon In-Out".equalsIgnoreCase(logType) ||
                    "Afternoon In".equalsIgnoreCase(logType)){
                    if(Dte[i].length()<2){Dte[i] = "0"+Dte[i];}
                    
                    if("Afternoon In".equalsIgnoreCase(logType)){
                        AlogTime = DQ.fetch("SELECT * FROM smartlogdb.checkinout where IDnumber='"+ AllID[j]+"' and Log_Name='"+logName[i]+"' and Date='"+MM+" "+Dte[i]+" "+YY+"' and Check_type='Ai'","LogTime");
                         try{
                         if("".equals(AlogTime) || AlogTime.isEmpty()){
                             AlogTime = "X";
                         }else{AlogTime = "/";}}catch(ArrayIndexOutOfBoundsException e){
                             AlogTime = "X";
                         }
                    }else{
                         AlogTime = DQ.fetch("SELECT * FROM smartlogdb.checkinout where IDnumber='"+ AllID[j]+"' and Log_Name='"+logName[i]+"' and Date='"+MM+" "+Dte[i]+" "+YY+"' and Check_type='Ai'","LogTime");
                         AologTime = DQ.fetch("SELECT * FROM smartlogdb.checkinout where IDnumber='"+ AllID[j]+"' and Log_Name='"+logName[i]+"' and Date='"+MM+" "+Dte[i]+" "+YY+"' and Check_type='Ao'","LogTime");
                         try{
                         if("".equals(AlogTime) || AlogTime.isEmpty()){
                             AlogTime = "X";
                         }else if((!"".equals(AlogTime) || !AlogTime.isEmpty()) && ("".equals(AologTime) || AologTime.isEmpty())){
                             AlogTime = "X";
                         }else{AlogTime = "/";}}catch(ArrayIndexOutOfBoundsException e){
                             AlogTime = "X";
                         }
                    }
                   }
                
                     dataM[j][i] = MlogTime;
                     dataA[j][i] = AlogTime;
                 }
                 
                 if((dataM[j][s]==""||dataM[j][s].isEmpty()) && (dataA[j][s]==""||dataA[j][s].isEmpty())&&
                    (dataM[j][s+1]==""||dataA[j][s+1].isEmpty())&& (dataA[j][s+1]==""||dataA[j][s+1].isEmpty())&&
                    (dataM[j][s+2]==""||dataA[j][s+2].isEmpty())&& (dataA[j][s+2]==""||dataM[j][s+2].isEmpty())&&
                    (dataM[j][s+3]==""||dataM[j][s+3].isEmpty())&& (dataA[j][s+3]==""||dataM[j][s+3].isEmpty())&&
                    (dataM[j][s+4]==""||dataM[j][s+4].isEmpty())&& (dataA[j][s+4]==""||dataM[j][s+4].isEmpty())&&
                    (dataM[j][s+5]==""||dataM[j][s+5].isEmpty())&& (dataA[j][s+5]==""||dataM[j][s+5].isEmpty())&&
                    (dataM[j][s+6]==""||dataM[j][s+6].isEmpty())&& (dataA[j][s+6]==""||dataM[j][s+6].isEmpty())){}
                 else{
                 //Add everything on model per ID
                 absentModel.addRow(new Object[]{AllID[j],namesOfEachID[j],dataM[j][s],dataA[j][s]
                                                                        ,dataM[j][s+1],dataA[j][s+1]
                                                                        ,dataM[j][s+2],dataA[j][s+2]
                                                                        ,dataM[j][s+3],dataA[j][s+3]
                                                                        ,dataM[j][s+4],dataA[j][s+4]
                                                                        ,dataM[j][s+5],dataA[j][s+5]
                                                                        ,dataM[j][s+6],dataA[j][s+6]});
                 }s++;
            }
        
        //For jasper Report
        try{
            String MonthYear = DateWDR_in.getText() +" - " + YearWDR_in.getText();
            String week = weekNum.getText();
            JRDataSource datasource = new JRTableModelDataSource(absentModel);
            String reportSource = "C:/Users/Acer/Desktop/Final Capstone Project/SmartLog_System/src/Reports/WeeklyAbsentReport.jrxml";
            
            JasperReport jr = JasperCompileManager.compileReport(reportSource);
            
            Map params = new HashMap<String, Object>();
            params.put("WeekNumber", week);
            params.put("MonthYear", MonthYear);
            params.put("ONE", Dte[0]);
            params.put("TWO", Dte[1]);
            params.put("THREE", Dte[2]);
            params.put("FOUR", Dte[3]);
            params.put("FIVE", Dte[4]);
            params.put("SIX", Dte[5]);
            params.put("SEVEN", Dte[6]);
            
            JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
            
            JasperViewer.viewReport(jp, false);
        }catch (JRException e){Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, e);}
        
    }
    
    
    public void printWeeklyTardy() throws ClassNotFoundException, SQLException{
        DefaultTableModel lateModel = new DefaultTableModel(new Object[]{"ID Number", "Name", "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"},0);
        String[] Dte = { one.getText(),two.getText(),three.getText(),four.getText(),five.getText(),six.getText(),seven.getText()};
        String MM = DateWDR_in.getText(),
               YY = YearWDR_in.getText();
        String[] logName = { String.valueOf(one_log.getModel().getSelectedItem()),String.valueOf(two_log.getModel().getSelectedItem()),String.valueOf(three_log.getModel().getSelectedItem()),String.valueOf(four_log.getModel().getSelectedItem()),
                                 String.valueOf(five_log.getModel().getSelectedItem()),String.valueOf(six_log.getModel().getSelectedItem()),String.valueOf(seven_log.getModel().getSelectedItem())};
        
        //Fetch all IDnumber
        String[] AllID = DQ.arrayFetch("smartlogdb.personnels","SELECT * FROM smartlogdb.personnels", "IDnumber");
        String[] namesOfEachID = new String[AllID.length];
        
        //fetch each Fullname per IDnumber
            for(int i=0;i<AllID.length;i++){
                namesOfEachID[i] = DQ.fetch("SELECT concat(Lname,', ',Fname,' ',MI) as Fullname FROM smartlogdb.personnels where IDnumber = '"+AllID[i]+"'", "Fullname");
            }
       String[][] dataM = new String[AllID.length][7];
       String[][] dataA = new String[AllID.length][7];
       for(int j=0;j<AllID.length;j++){
        //Repeat for 7 times - one week
        int s=0;
        for(int i=0;i<7;i++){
            String MlateTime = "", AlateTime = "";
            //Fetch logtype
            if(Dte[i].length()<2){Dte[i] = "0"+Dte[i];}
            String logType = DQ.fetch("SELECT * FROM smartlogdb.dailylogschedule where schName='"+logName[i]+"' and Date='"+MM+" "+Dte[i]+" "+YY+"'","LogIO_cycle");
            if("".equals(logType) || logType.isEmpty()){}
            else{
                if("Morning In-Out and Afternoon In-Out".equalsIgnoreCase(logType) || 
                    "Morning In-Out".equalsIgnoreCase(logType) ||
                    "Morning In".equalsIgnoreCase(logType)){
                     //Fetch data perID Morning late
                         MlateTime = DQ.fetch("SELECT * FROM smartlogdb.checkinout where IDnumber='"+ AllID[j]+"' and Log_Name='"+logName[i]+"' and Date='"+MM+" "+Dte[i]+" "+YY+"' and Check_type='Mi'","LateTime");
                         try{
                         if("".equals(MlateTime) || MlateTime.isEmpty()){
                             MlateTime = "0";
                         }}catch(ArrayIndexOutOfBoundsException|NullPointerException e){
                             MlateTime = "0";
                         }
                     }
                 }
                
                if("Morning In-Out and Afternoon In-Out".equalsIgnoreCase(logType) || 
                    "Afternoon In-Out".equalsIgnoreCase(logType) ||
                    "Afternoon In".equalsIgnoreCase(logType)){
                         AlateTime = DQ.fetch("SELECT * FROM smartlogdb.checkinout where IDnumber='"+ AllID[j]+"' and Log_Name='"+logName[i]+"' and Date='"+MM+" "+Dte[i]+" "+YY+"' and Check_type='Ai'","LateTime");
                         try{
                         if("".equals(AlateTime) || AlateTime.isEmpty()){
                             AlateTime = "0";
                         }}catch(ArrayIndexOutOfBoundsException|NullPointerException e){
                             AlateTime = "0";
                         }
                     }
                
                     dataM[j][i] = MlateTime;
                     dataA[j][i] = AlateTime;
                 }
                 
                 if((dataM[j][s]=="0"||dataM[j][s].isEmpty()) && (dataA[j][s]=="0"||dataA[j][s].isEmpty())&&
                    (dataM[j][s+1]=="0"||dataA[j][s+1].isEmpty())&& (dataA[j][s+1]=="0"||dataA[j][s+1].isEmpty())&&
                    (dataM[j][s+2]=="0"||dataA[j][s+2].isEmpty())&& (dataA[j][s+2]=="0"||dataM[j][s+2].isEmpty())&&
                    (dataM[j][s+3]=="0"||dataM[j][s+3].isEmpty())&& (dataA[j][s+3]=="0"||dataM[j][s+3].isEmpty())&&
                    (dataM[j][s+4]=="0"||dataM[j][s+4].isEmpty())&& (dataA[j][s+4]=="0"||dataM[j][s+4].isEmpty())&&
                    (dataM[j][s+5]=="0"||dataM[j][s+5].isEmpty())&& (dataA[j][s+5]=="0"||dataM[j][s+5].isEmpty())&&
                    (dataM[j][s+6]=="0"||dataM[j][s+6].isEmpty())&& (dataA[j][s+6]=="0"||dataM[j][s+6].isEmpty())){}
                 else{
                 //Add everything on model per ID
                 lateModel.addRow(new Object[]{AllID[j],namesOfEachID[j],dataM[j][s],dataA[j][s]
                                                                        ,dataM[j][s+1],dataA[j][s+1]
                                                                        ,dataM[j][s+2],dataA[j][s+2]
                                                                        ,dataM[j][s+3],dataA[j][s+3]
                                                                        ,dataM[j][s+4],dataA[j][s+4]
                                                                        ,dataM[j][s+5],dataA[j][s+5]
                                                                        ,dataM[j][s+6],dataA[j][s+6]});
                 }s++;
            }
        
        //For jasper Report
        try{
            String MonthYear = DateWDR_in.getText() +" - " + YearWDR_in.getText();
            String week = weekNum.getText();
            JRDataSource datasource = new JRTableModelDataSource(lateModel);
            String reportSource = "C:/Users/Acer/Desktop/Final Capstone Project/SmartLog_System/src/Reports/WeeklyTardyReport.jrxml";
            
            JasperReport jr = JasperCompileManager.compileReport(reportSource);
            
            Map params = new HashMap<String, Object>();
            params.put("WeekNumber", week);
            params.put("MonthYear", MonthYear);
            params.put("ONE", Dte[0]);
            params.put("TWO", Dte[1]);
            params.put("THREE", Dte[2]);
            params.put("FOUR", Dte[3]);
            params.put("FIVE", Dte[4]);
            params.put("SIX", Dte[5]);
            params.put("SEVEN", Dte[6]);
            
            JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
            
            JasperViewer.viewReport(jp, false);
        }catch (JRException e){Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, e);}
        
    }
    
    
    public void printWeeklyReport() throws ClassNotFoundException, SQLException{
        DefaultTableModel WeeklyModel = new DefaultTableModel(new Object[]{"ID Number", "Name", "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"},0);
        String[] Dte = { one.getText(),two.getText(),three.getText(),four.getText(),five.getText(),six.getText(),seven.getText()};
        String MM = DateWDR_in.getText(),
               YY = YearWDR_in.getText();
        String[] logName = { String.valueOf(one_log.getModel().getSelectedItem()),String.valueOf(two_log.getModel().getSelectedItem()),String.valueOf(three_log.getModel().getSelectedItem()),String.valueOf(four_log.getModel().getSelectedItem()),
                                 String.valueOf(five_log.getModel().getSelectedItem()),String.valueOf(six_log.getModel().getSelectedItem()),String.valueOf(seven_log.getModel().getSelectedItem())};
        
        //Fetch all IDnumber
        String[] AllID = DQ.arrayFetch("smartlogdb.personnels","SELECT * FROM smartlogdb.personnels", "IDnumber");
        String[] namesOfEachID = new String[AllID.length];
        
        //fetch each Fullname per IDnumber
            for(int i=0;i<AllID.length;i++){
                namesOfEachID[i] = DQ.fetch("SELECT concat(Lname,', ',Fname,' ',MI) as Fullname FROM smartlogdb.personnels where IDnumber = '"+AllID[i]+"'", "Fullname");
            }
       String[][] dataM = new String[AllID.length][7];
       String[][] dataA = new String[AllID.length][7];
       for(int j=0;j<AllID.length;j++){
        //Repeat for 7 times - one week
        int s=0;
        for(int i=0;i<7;i++){
            String MTime = "", ATime = "";
            if(Dte[i].length()<2){Dte[i] = "0"+Dte[i];}
            //Fetch logtype
            String logType = DQ.fetch("SELECT * FROM smartlogdb.dailylogschedule where schName='"+logName[i]+"' and Date='"+MM+" "+Dte[i]+" "+YY+"'","LogIO_cycle");
            if("".equals(logType) || logType.isEmpty()){}
            else{
                if("Morning In-Out and Afternoon In-Out".equalsIgnoreCase(logType) || 
                    "Morning In-Out".equalsIgnoreCase(logType) ||
                    "Morning In".equalsIgnoreCase(logType)){
                     //Fetch data perID Morning late
                         MTime = DQ.fetch("SELECT * FROM smartlogdb.checkinout where IDnumber='"+ AllID[j]+"' and Log_Name='"+logName[i]+"' and Date='"+MM+" "+Dte[i]+" "+YY+"' and Check_type='Mi'","LogTime");
                         try{
                         if("".equals(MTime) || MTime.isEmpty()){
                             MTime = "";
                         }}catch(ArrayIndexOutOfBoundsException e){
                             MTime = "";
                         }
                     }
                 }
                
                if("Morning In-Out and Afternoon In-Out".equalsIgnoreCase(logType) || 
                    "Afternoon In-Out".equalsIgnoreCase(logType) ||
                    "Afternoon In".equalsIgnoreCase(logType)){
                         ATime = DQ.fetch("SELECT * FROM smartlogdb.checkinout where IDnumber='"+ AllID[j]+"' and Log_Name='"+logName[i]+"' and Date='"+MM+" "+Dte[i]+" "+YY+"' and Check_type='Ai'","LogTime");
                         try{
                         if("".equals(ATime) || ATime.isEmpty()){
                             ATime = "";
                         }}catch(ArrayIndexOutOfBoundsException e){
                             ATime = "";
                         }
                     }
                
                     dataM[j][i] = MTime;
                     dataA[j][i] = ATime;
                 }
                 
                 //Add everything on model per ID
                 WeeklyModel.addRow(new Object[]{AllID[j],namesOfEachID[j],dataM[j][s],dataA[j][s]
                                                                        ,dataM[j][s+1],dataA[j][s+1]
                                                                        ,dataM[j][s+2],dataA[j][s+2]
                                                                        ,dataM[j][s+3],dataA[j][s+3]
                                                                        ,dataM[j][s+4],dataA[j][s+4]
                                                                        ,dataM[j][s+5],dataA[j][s+5]
                                                                        ,dataM[j][s+6],dataA[j][s+6]});
                 s++;
            }
        
        //For jasper Report
        try{
            String MonthYear = DateWDR_in.getText() +" - " + YearWDR_in.getText();
            String week = weekNum.getText();
            JRDataSource datasource = new JRTableModelDataSource(WeeklyModel);
            String reportSource = "C:/Users/Acer/Desktop/Final Capstone Project/SmartLog_System/src/Reports/WeeklyGeneralReport.jrxml";
            
            JasperReport jr = JasperCompileManager.compileReport(reportSource);
            
            Map params = new HashMap<String, Object>();
            params.put("WeekNumber", week);
            params.put("MonthYear", MonthYear);
            params.put("ONE", Dte[0]);
            params.put("TWO", Dte[1]);
            params.put("THREE", Dte[2]);
            params.put("FOUR", Dte[3]);
            params.put("FIVE", Dte[4]);
            params.put("SIX", Dte[5]);
            params.put("SEVEN", Dte[6]);
            
            JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
            
            JasperViewer.viewReport(jp, false);
        }catch (JRException e){Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, e);}
        
    }
    
    private void WeeklyReportPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WeeklyReportPrintActionPerformed
        try {
            String printWhat = String.valueOf(PLcomboBox.getModel().getSelectedItem());
            if(printWhat.equalsIgnoreCase("Absent Summary Report")){
                printWeeklyAbsent();
            }else if(printWhat.equalsIgnoreCase("Late Summary Report")){
                printWeeklyTardy();
            }else if(printWhat.equalsIgnoreCase("General Report")){
                printWeeklyReport();
            }else{}
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_WeeklyReportPrintActionPerformed

    private void MonthPrint_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MonthPrint_btnActionPerformed
        //For jasper Report
        try{
            DefaultTableModel MMmodel = (DefaultTableModel) DTRtableMM.getModel();
            SimpleDateFormat df = new SimpleDateFormat("MMMM yyyy");
            String MonthYear = df.format(monthMM_in.getDate());
            String Pname = nameMM_in.getText();
            JRDataSource datasource = new JRTableModelDataSource(MMmodel);
            String SR[] = SRdays(RDsearchFrom[0],RDsearchFrom[1],RDsearchFrom[2]);
            String reportSource = "C:/Users/Acer/Desktop/Final Capstone Project/SmartLog_System/src/Reports/DTRmonthReport.jrxml";
            
            JasperReport jr = JasperCompileManager.compileReport(reportSource);
            String hrs = String.valueOf(UnderTime[0]);
            String mns = String.valueOf(UnderTime[1]);
            Map params = new HashMap<String, Object>();
            params.put("personnelName",Pname);
            params.put("MonthYear", MonthYear);
            params.put("Hours",hrs);
            params.put("Minutes", mns);
            params.put("Rdays", SR[1]);
            params.put("Sdays", SR[0]);
            
            JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
            
            JasperViewer.viewReport(jp, false);
        }catch (JRException e){Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, e);}
        
    }//GEN-LAST:event_MonthPrint_btnActionPerformed

    public String[] SRdays(String yy, String mm, String dd){
        if(mm.length()<2){ mm="0"+mm;}
        LocalDate localDate = LocalDate.parse(yy+"-"+mm+"-"+dd);
        lastday = String.valueOf(localDate.with(TemporalAdjusters.lastDayOfMonth()));
        String cut[] = lastday.split("-");
        lastdayOftheMonth = Integer.parseInt(cut[2]);
        int SS = 0, RD = 0;
        
        int date = 1;
        
        for(int i=0;i<lastdayOftheMonth;i++){
            String dateS = String.valueOf(date);
            if(dateS.length()<2){dateS = "0"+dateS;}
            String searchF = yy+"-"+mm+"-"+dateS;
            localDate = LocalDate.parse(searchF);
            String dayName = String.valueOf(localDate.getDayOfWeek());
            if(dayName.equalsIgnoreCase("Saturday")){//|| dayName.equalsIgnoreCase("Sunday")
                   SS = SS+1;
            }else if(dayName.equalsIgnoreCase("Sunday")){
            }else{RD=RD+1;}
            date++;
        }
        String[] rRS = {String.valueOf(SS),String.valueOf(RD)};
        return rRS;
    }
    
    private void idNumberMM_inFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idNumberMM_inFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_idNumberMM_inFocusGained

    private void idNumberMM_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idNumberMM_inActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idNumberMM_inActionPerformed

    private void idNumberMM_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idNumberMM_inKeyTyped
        char in = evt.getKeyChar();
        if(((in < '0') || (in > '9')) && (in != '\b')){
            evt.consume();
        }else{
            try {
            char t = evt.getKeyChar();
            String id = idNumberMM_in.getText();
            String name = DQ.fetch("select concat(Lname,', ',Fname,' ',MI,'.') as name from personnels where IDnumber='"+id+t+"'", "name");
            if(!"".equals(name)){
                nameMM_in.setText(name);
            }else{
                nameMM_in.setText("");
            }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_idNumberMM_inKeyTyped

    private void nameMM_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameMM_inActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameMM_inActionPerformed

    private void searchMM_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchMM_btnActionPerformed
        try {
            String id = idNumberMM_in.getText();
            String name = nameMM_in.getText();
            String c = String.valueOf(monthMM_in.getDate());
            if(id.isEmpty()||c.isEmpty()){infoBox("All fields are required","Error",CLOSED_OPTION);searchByLogMM_btn.setEnabled(false);}
            else{
                if(name.isEmpty()){
                    idNumberMM_in.setForeground(Color.red);
                    searchByLogMM_btn.setEnabled(false);
                }else{
                    logNamesMM();
                    wholeMonthAttendanceRecords(false);
                    searchByLogMM_btn.setEnabled(true);
                }
            }
        } catch (ClassNotFoundException | SQLException  ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchMM_btnActionPerformed

    private void searchByLogMM_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByLogMM_btnActionPerformed
         try {
            String id = idNumberMM_in.getText();
            String name = nameMM_in.getText();
            String c = String.valueOf(monthMM_in.getDate());
            if(id.isEmpty()||c.isEmpty()){infoBox("All fields are required","Error",CLOSED_OPTION);searchByLogMM_btn.setEnabled(false);}
            else{
                if(name.isEmpty()){
                    idNumberMM_in.setForeground(Color.red);
                    searchByLogMM_btn.setEnabled(false);
                }else{
                    wholeMonthAttendanceRecords(true);
                    searchByLogMM_btn.setEnabled(true);
                }
            }
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchByLogMM_btnActionPerformed

    private void monthlyR_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthlyR_btnActionPerformed
        dailyR_panel.setVisible(false);
        KensinaR_panel.setVisible(false);
        weeklyR_panel.setVisible(false);
        monthlyR_panel.setVisible(true);
        
        KensinaR_btn.setBackground(new Color(255,255,255));
        KensinaR_btn.setForeground(new Color(4,8,114));
        dailyR_btn.setBackground(new Color(255,255,255));
        dailyR_btn.setForeground(new Color(4,8,114));
        weeklyR_btn.setBackground(new Color(255,255,255));
        weeklyR_btn.setForeground(new Color(4,8,114));
        monthlyR_btn.setBackground(new Color(4,8,114));
        monthlyR_btn.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_monthlyR_btnActionPerformed

    private void LogDate27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogDate27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LogDate27ActionPerformed

    private void LogDate73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogDate73ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LogDate73ActionPerformed

    private void thisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());   //  today
        month = calendar.get(Calendar.MONTH) + 1;
        year = calendar.get(Calendar.YEAR);
    }

    private void showMonthYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DATE, 1);
        SimpleDateFormat df = new SimpleDateFormat("MMMM yyyy");
        lbMonthYear.setText(df.format(calendar.getTime()));
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel A_in;
    private javax.swing.JPanel A_out;
    private javax.swing.JButton AboutUs_btn;
    private javax.swing.JPanel About_pane;
    private javax.swing.JButton Add_btn;
    private javax.swing.JTextField Age_in;
    private javax.swing.JLabel Ain_label;
    private javax.swing.JLabel Aout_label;
    private javax.swing.JButton Attendance_btn;
    private javax.swing.JPanel Attendance_pane;
    private javax.swing.JLabel BG;
    private javax.swing.JTextField BTaddress;
    private javax.swing.JComboBox<String> BTname;
    private javax.swing.JPanel BTreg_panel;
    private javax.swing.JButton BTscan_btn;
    private javax.swing.JPanel CARD_LAYOUT;
    private javax.swing.JPanel Calendar_Panel;
    private javax.swing.JButton DEPARTMENTS_btn;
    private javax.swing.JPanel DEPARTMENTS_panel;
    private javax.swing.JTable DTRtable;
    private javax.swing.JTable DTRtableMM;
    private javax.swing.JTable DailyReport_table;
    private javax.swing.JButton Dashboard_btn;
    private javax.swing.JPanel Dashboard_pane;
    private com.toedter.calendar.JDateChooser DateSDR_in;
    private javax.swing.JTextField DateWDR_in;
    private javax.swing.JLabel Date_label;
    private javax.swing.JLabel Date_lbl1;
    private javax.swing.JLabel Day_label;
    private javax.swing.JButton Delete_btn;
    private javax.swing.JButton DepartmentAddPosition_btn;
    private javax.swing.JButton DepartmentAdd_btn;
    private javax.swing.JButton DepartmentDelete_btn;
    private javax.swing.JTextField DepartmentHead_in;
    private javax.swing.JTextField DepartmentID_in;
    private javax.swing.JTextField DepartmentName_in;
    private javax.swing.JTextField DepartmentPersonnels_in;
    private javax.swing.JButton DepartmentPositionDelete_btn;
    private javax.swing.JTextField DepartmentPositionName_in;
    private javax.swing.JButton DepartmentPositionUpdate_btn;
    private javax.swing.JButton DepartmentUpdate_btn;
    private javax.swing.JButton DepartmentView_btn;
    private javax.swing.JComboBox<String> Department_in;
    private javax.swing.JTable Departments_table;
    private javax.swing.JTextField Fname_in;
    private javax.swing.JComboBox<String> Gender_in;
    private javax.swing.JButton GoWDR_btn;
    private com.toedter.calendar.JDateChooser HireDate_in;
    private javax.swing.JTextField ID_in;
    private javax.swing.JTextField IDnumberSDR_in;
    private javax.swing.JTextField IDnumberWDR_in;
    private javax.swing.JButton KensinaR_btn;
    private javax.swing.JPanel KensinaR_panel;
    private javax.swing.JButton LEAVE_btn;
    private javax.swing.JPanel LEAVE_panel;
    private javax.swing.JButton LOGS_btn;
    private javax.swing.JPanel LOG_panel;
    private javax.swing.JButton LeaveAdd_btn;
    private com.toedter.calendar.JDateChooser LeaveDateEnd_dc;
    private com.toedter.calendar.JDateChooser LeaveDateFiled_dc;
    private com.toedter.calendar.JDateChooser LeaveDateStart_dc;
    private javax.swing.JButton LeaveDelete_btn;
    private javax.swing.JTextField LeaveExcuse_in;
    private javax.swing.JButton LeaveIdNumberSearch_btn;
    private javax.swing.JTextField LeaveIdNumber_in;
    private com.toedter.calendar.JDateChooser LeaveMonth_in;
    private javax.swing.JTextField LeavePersonnel_in;
    private javax.swing.JButton LeavePrint_btn;
    private javax.swing.JTextField LeaveStatus_in;
    private javax.swing.JButton LeaveUpdate_btn;
    private com.toedter.calendar.JDateChooser LeaveYear_in;
    private javax.swing.JTable Leave_table;
    private javax.swing.JTextField Lname_in;
    private javax.swing.JComboBox<String> LogDate1;
    private javax.swing.JComboBox<String> LogDate10;
    private javax.swing.JComboBox<String> LogDate11;
    private javax.swing.JComboBox<String> LogDate12;
    private javax.swing.JComboBox<String> LogDate13;
    private javax.swing.JComboBox<String> LogDate14;
    private javax.swing.JComboBox<String> LogDate15;
    private javax.swing.JComboBox<String> LogDate16;
    private javax.swing.JComboBox<String> LogDate17;
    private javax.swing.JComboBox<String> LogDate18;
    private javax.swing.JComboBox<String> LogDate19;
    private javax.swing.JComboBox<String> LogDate2;
    private javax.swing.JComboBox<String> LogDate20;
    private javax.swing.JComboBox<String> LogDate21;
    private javax.swing.JComboBox<String> LogDate22;
    private javax.swing.JComboBox<String> LogDate23;
    private javax.swing.JComboBox<String> LogDate24;
    private javax.swing.JComboBox<String> LogDate25;
    private javax.swing.JComboBox<String> LogDate26;
    private javax.swing.JComboBox<String> LogDate27;
    private javax.swing.JComboBox<String> LogDate28;
    private javax.swing.JComboBox<String> LogDate29;
    private javax.swing.JComboBox<String> LogDate3;
    private javax.swing.JComboBox<String> LogDate30;
    private javax.swing.JComboBox<String> LogDate31;
    private javax.swing.JComboBox<String> LogDate32;
    private javax.swing.JComboBox<String> LogDate4;
    private javax.swing.JComboBox<String> LogDate5;
    private javax.swing.JComboBox<String> LogDate6;
    private javax.swing.JComboBox<String> LogDate63;
    private javax.swing.JComboBox<String> LogDate64;
    private javax.swing.JComboBox<String> LogDate65;
    private javax.swing.JComboBox<String> LogDate66;
    private javax.swing.JComboBox<String> LogDate67;
    private javax.swing.JComboBox<String> LogDate68;
    private javax.swing.JComboBox<String> LogDate69;
    private javax.swing.JComboBox<String> LogDate7;
    private javax.swing.JComboBox<String> LogDate70;
    private javax.swing.JComboBox<String> LogDate71;
    private javax.swing.JComboBox<String> LogDate72;
    private javax.swing.JComboBox<String> LogDate73;
    private javax.swing.JComboBox<String> LogDate74;
    private javax.swing.JComboBox<String> LogDate75;
    private javax.swing.JComboBox<String> LogDate76;
    private javax.swing.JComboBox<String> LogDate77;
    private javax.swing.JComboBox<String> LogDate8;
    private javax.swing.JComboBox<String> LogDate9;
    private javax.swing.JTextField LogDate_in;
    private javax.swing.JTextField LogDayType_in;
    private javax.swing.JButton LogDelete_btn;
    private javax.swing.JTextField LogEntry_in;
    private javax.swing.JTextField LogID_in;
    private javax.swing.JTextField LogName_in;
    private javax.swing.JButton LogOut_btn;
    private javax.swing.JPanel LogPerDates;
    private javax.swing.JTextField LogStatus_in;
    private javax.swing.JTextField LogWorkStatus_in;
    private javax.swing.JLabel Logo;
    private javax.swing.JTable Logs_table;
    private javax.swing.JTextField MI_in;
    private javax.swing.JPanel M_in;
    private javax.swing.JPanel M_out;
    private javax.swing.JLabel Min_label;
    private javax.swing.JButton MonthPrint_btn;
    private javax.swing.JPanel MonthlyDTRviewer;
    private javax.swing.JLabel Mout_label;
    private javax.swing.JLabel NumberOfOfficials_bg;
    private javax.swing.JLabel NumberOfOfficials_label;
    private javax.swing.JLabel NumberOfPersonnels_bg;
    private javax.swing.JLabel NumberOfStaff_bg;
    private javax.swing.JLabel NumberOfStaff_label;
    private javax.swing.JComboBox<String> PLcomboBox;
    private javax.swing.JPasswordField PersonnelPassword_in;
    private javax.swing.JButton PersonnelSearch_btn;
    private javax.swing.JTextField PersonnelSearch_in;
    private javax.swing.JButton Personnels_btn;
    private javax.swing.JPanel Personnels_pane;
    private javax.swing.JTable Personnels_table;
    private javax.swing.JComboBox<String> PositionDepartmentName_in;
    private javax.swing.JTextField PositionID_in;
    private javax.swing.JComboBox<String> Position_in;
    private javax.swing.JComboBox<String> Preveledge_in;
    private javax.swing.JPanel RWeekly_container;
    private javax.swing.JButton Reports_btn;
    private javax.swing.JPanel Reports_pane;
    private javax.swing.JButton SCHEDULE_btn;
    private javax.swing.JPanel SCHEDULE_panel;
    private javax.swing.JComboBox<String> SDRLogName_in;
    private javax.swing.JButton SHIFTS_btn;
    private javax.swing.JPanel SHIFTS_panel;
    private javax.swing.JButton ScheduleAdd_btn;
    private com.github.lgooddatepicker.components.TimePicker ScheduleAfternoonIn_Pin;
    private com.github.lgooddatepicker.components.TimePicker ScheduleAfternoonOut_Pin;
    private javax.swing.JTextField ScheduleCRN_in;
    private com.toedter.calendar.JDateChooser ScheduleDate_dc;
    private javax.swing.JComboBox<String> ScheduleDayType_in;
    private javax.swing.JButton ScheduleDelete_btn;
    private javax.swing.JComboBox<String> ScheduleLogEntry_cb;
    private com.github.lgooddatepicker.components.TimePicker ScheduleMorningIn_Pin;
    private com.github.lgooddatepicker.components.TimePicker ScheduleMorningOut_Pin;
    private javax.swing.JTextField ScheduleName_in;
    private javax.swing.JButton ScheduleUpdate_btn;
    private javax.swing.JComboBox<String> ScheduleWorkStatus_cb;
    private javax.swing.JTable Schedule_table;
    private javax.swing.JButton SearchSDR_btn;
    private javax.swing.JButton SearchWDR_btn;
    private javax.swing.JButton ShiftAdd_btn;
    private javax.swing.JTextField ShiftCRN_in;
    private javax.swing.JComboBox<String> ShiftDay_in;
    private javax.swing.JButton ShiftDelete_btn;
    private javax.swing.JTextField ShiftIdNumber_in;
    private javax.swing.JTextField ShiftPersonnel_in;
    private javax.swing.JTextField ShiftPosition_in;
    private com.github.lgooddatepicker.components.TimePicker ShiftTimeEnd_in;
    private com.github.lgooddatepicker.components.TimePicker ShiftTimeStart_in;
    private javax.swing.JButton ShiftUpdate_btn;
    private javax.swing.JTable Shifts_table;
    private javax.swing.JLabel SideBar;
    private javax.swing.JLabel TimeInOut_label;
    private javax.swing.JLabel Time_label;
    private javax.swing.JButton Update_btn;
    private javax.swing.JCheckBox UseLog_checkbox;
    private javax.swing.JLabel User_Position;
    private javax.swing.JLabel User_name;
    private javax.swing.JButton WeeklyReportPrint;
    private javax.swing.JTable WeeklyReport_table;
    private javax.swing.JTextField YearWDR_in;
    private javax.swing.JCheckBox activateShift_checkbox;
    private Graph.Afternoon_in_Graph afternoon_in_Graph1;
    private Graph.Afternoon_out_Graph afternoon_out_Graph1;
    private javax.swing.JButton cmdBack;
    private javax.swing.JButton cmdNext;
    private javax.swing.JLabel currentAttendanceCycle_label;
    private javax.swing.JLabel currentAttendance_label;
    private javax.swing.JLabel currentLogType_label;
    private javax.swing.JButton dailyR_btn;
    private javax.swing.JPanel dailyR_panel;
    private javax.swing.JButton dptHead_btn;
    private javax.swing.JPanel dptHead_panel;
    private javax.swing.JButton dptPositions_btn;
    private javax.swing.JPanel dptPositions_panel;
    private javax.swing.JLabel five;
    private javax.swing.JComboBox<String> five_log;
    private javax.swing.JLabel four;
    private javax.swing.JComboBox<String> four_log;
    private javax.swing.JButton fwd_btn;
    private javax.swing.JButton goDailyReport_btn;
    private javax.swing.JButton halfMonthSearch_btn;
    private javax.swing.JTextField idMDR_in;
    private javax.swing.JTextField idNumberMM_in;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private java.awt.Label label7;
    private java.awt.Label label8;
    private java.awt.Label label9;
    private javax.swing.JLabel lbMonthYear;
    private com.toedter.calendar.JDateChooser monthMDR_in;
    private com.toedter.calendar.JDateChooser monthMM_in;
    private javax.swing.JButton monthlyR_btn;
    private javax.swing.JPanel monthlyR_panel;
    private Graph.Morning_in_Graph morning_in_Graph1;
    private Graph.Morning_out_Graph morning_out_Graph1;
    private javax.swing.JTextField nameMDR_in;
    private javax.swing.JTextField nameMM_in;
    private javax.swing.JLabel one;
    private javax.swing.JComboBox<String> one_log;
    private javax.swing.JButton prev_btn;
    private javax.swing.JButton printDailyReport_btn;
    private javax.swing.JButton printPersonnels;
    private javax.swing.JLabel prompt_Message;
    private javax.swing.JPanel reportsViewer_panel;
    private java.awt.ScrollPane scrollPane1;
    private javax.swing.JButton searchByLogDTR_btn;
    private javax.swing.JButton searchByLogMM_btn;
    private javax.swing.JButton searchDTR_btn;
    private javax.swing.JButton searchMM_btn;
    private javax.swing.JTextField setMDR_in;
    private javax.swing.JLabel seven;
    private javax.swing.JLabel seven2;
    private javax.swing.JLabel seven3;
    private javax.swing.JLabel seven4;
    private javax.swing.JLabel seven5;
    private javax.swing.JLabel seven6;
    private javax.swing.JLabel seven7;
    private javax.swing.JLabel seven8;
    private javax.swing.JComboBox<String> seven_log;
    private javax.swing.JLabel six;
    private javax.swing.JComboBox<String> six_log;
    private Graph.PanelSlide slide;
    private javax.swing.JLabel three;
    private javax.swing.JComboBox<String> three_log;
    private javax.swing.JLabel two;
    private javax.swing.JComboBox<String> two_log;
    private javax.swing.JButton useLogSave_btn;
    private javax.swing.JLabel weekNum;
    private javax.swing.JButton weeklyR_btn;
    private javax.swing.JPanel weeklyR_panel;
    // End of variables declaration//GEN-END:variables
}
