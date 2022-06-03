
package smartlog_system;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.Timer;

public class LogIn_Frame extends javax.swing.JFrame{

    Database_Queries DQ = new Database_Queries();
    static attendance_Protocol Protocol = new attendance_Protocol();
    private String LogType = "Administrator";
            
    public LogIn_Frame() {
        initComponents();
        input_status_label.setVisible(false);
        ShowTime();
        LogType = "Administrator";
        unSelected(Alternativelog_btn);
        isSelected(AdminLog_btn);
        LogType_in.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Date_label = new javax.swing.JLabel();
        Time_label = new javax.swing.JLabel();
        Day_label = new javax.swing.JLabel();
        Username_input = new javax.swing.JTextField();
        AdminLog_btn = new javax.swing.JButton();
        Alternativelog_btn = new javax.swing.JButton();
        LogType_in = new javax.swing.JComboBox<>();
        LogIn_btn = new javax.swing.JButton();
        input_status_label = new javax.swing.JLabel();
        Password_input = new javax.swing.JPasswordField();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Date_label.setBackground(new java.awt.Color(255, 255, 255));
        Date_label.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        Date_label.setForeground(new java.awt.Color(4, 8, 114));
        Date_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Date_label.setText("July 8, 2021");
        jPanel1.add(Date_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, 210, 30));

        Time_label.setBackground(new java.awt.Color(255, 255, 255));
        Time_label.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        Time_label.setForeground(new java.awt.Color(4, 8, 114));
        Time_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Time_label.setText("3:00 PM");
        jPanel1.add(Time_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 40, 90, 30));

        Day_label.setBackground(new java.awt.Color(255, 255, 255));
        Day_label.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        Day_label.setForeground(new java.awt.Color(4, 8, 114));
        Day_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Day_label.setText("Thursday");
        jPanel1.add(Day_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 40, 110, 30));

        Username_input.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        Username_input.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Username_input.setBorder(null);
        Username_input.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Username_inputFocusGained(evt);
            }
        });
        Username_input.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Username_inputKeyTyped(evt);
            }
        });
        jPanel1.add(Username_input, new org.netbeans.lib.awtextra.AbsoluteConstraints(834, 331, 271, 40));

        AdminLog_btn.setBackground(new java.awt.Color(4, 153, 19));
        AdminLog_btn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        AdminLog_btn.setForeground(new java.awt.Color(4, 153, 19));
        AdminLog_btn.setText("DASHBOARD");
        AdminLog_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 153, 19)));
        AdminLog_btn.setFocusPainted(false);
        AdminLog_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminLog_btnActionPerformed(evt);
            }
        });
        jPanel1.add(AdminLog_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 260, 170, 20));

        Alternativelog_btn.setBackground(new java.awt.Color(4, 153, 19));
        Alternativelog_btn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Alternativelog_btn.setForeground(new java.awt.Color(255, 255, 255));
        Alternativelog_btn.setText("ATTENDANCE LOG");
        Alternativelog_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(4, 153, 19)));
        Alternativelog_btn.setFocusPainted(false);
        Alternativelog_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Alternativelog_btnActionPerformed(evt);
            }
        });
        jPanel1.add(Alternativelog_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 260, 172, 20));

        LogType_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        LogType_in.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Morning In", "Morning Out", "Afternoon In", "Afternoon Out" }));
        LogType_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(LogType_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(831, 560, 276, -1));

        LogIn_btn.setBackground(new java.awt.Color(4, 8, 114));
        LogIn_btn.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        LogIn_btn.setForeground(new java.awt.Color(255, 255, 255));
        LogIn_btn.setText("LOG IN");
        LogIn_btn.setBorder(null);
        LogIn_btn.setBorderPainted(false);
        LogIn_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogIn_btnActionPerformed(evt);
            }
        });
        jPanel1.add(LogIn_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(834, 502, 271, 40));

        input_status_label.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        input_status_label.setForeground(new java.awt.Color(204, 0, 0));
        input_status_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        input_status_label.setText("* wrong ID and password");
        jPanel1.add(input_status_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 475, 320, 20));

        Password_input.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        Password_input.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Password_input.setToolTipText("");
        Password_input.setBorder(null);
        jPanel1.add(Password_input, new org.netbeans.lib.awtextra.AbsoluteConstraints(834, 425, 272, 40));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/LOG IN.png"))); // NOI18N
        bg.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void isSelected(JButton btn){
        btn.setForeground(new Color(255,255,255));
        btn.setBackground(new Color(4,153,19));
    }
    
    public static void unSelected(JButton btn){
        btn.setForeground(new Color(4,153,19));
        btn.setBackground(new Color(255,255,255));
    }
    
    private void LogIn_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogIn_btnActionPerformed
        String User = Username_input.getText();
        String Pswrd = String.valueOf(Password_input.getPassword());
        String Ltype = (String) LogType_in.getModel().getSelectedItem();
        String type = "";
        if(null != Ltype)switch (Ltype) {
            case "Morning In":
                type="Mi";
                break;
            case "Morning Out":
                type="Mo";
                break;
            case "Afternoon In":
                type="Ai";
                break;
            case "Afternoon Out":
                type="Ao";
                break;
            default:
                break;
        }
        
        try {
            if("Administrator".equals(LogType)){
                
                if(User.isEmpty() || Pswrd.isEmpty()){
                    input_status_label.setVisible(true);
                    input_status_label.setForeground(new Color(254,90,57));
                    input_status_label.setText("Fill in all fields.");
                }else{
                    boolean Verification = DQ.Authentication(User, Pswrd);

                    if(Verification == true){
                        System.out.println("Verified as True");
                        DQ.CRUDE("update personnels set Status = 'Current_User' where IDnumber = '"+User+"' and Password = '"+Pswrd+"'");
                        System.out.println("User status updated.");
                        this.hide();
                        Main_Frame MF = new Main_Frame();
                        MF.setVisible(true);
                    }else{
                        System.out.println("Virified as false");
                        input_status_label.setVisible(true);
                        input_status_label.setForeground(new Color(254,90,57));
                        input_status_label.setText("User ID and password incorrect.");
                    }
                }
            }else{
                if(User.isEmpty() || Pswrd.isEmpty()){
                    input_status_label.setVisible(true);
                    input_status_label.setForeground(new Color(254,90,57));
                    input_status_label.setText("Fill in all fields.");
                }else{
                
                    String accountMatchPassword = DQ.fetch("personnels", " Where IDnumber = '"+User+"' and Password='"+Pswrd+"'", "IDnumber");
                    String alreadyLog = Protocol.duplicate_Log(User, Pswrd, type);
                    String OpenLog[] = DQ.getCurrentUse_OpenLog();
                    String CutOpen[] = OpenLog[0].split("\\s");
                    boolean Open = false;
                    if(!(accountMatchPassword == null)){
                        for(int i=0;i<CutOpen.length;i++){
                            if (CutOpen[i].equals(type)) { 
                                Open = true;  break;
                            }
                        }

                        if(Open){
                            if("".equals(alreadyLog) || alreadyLog == null){
                                Protocol.attendanceLogging_Protocol(User, Pswrd, type);
                                System.out.println("Alternative Login Successful");
                                input_status_label.setVisible(true);
                                input_status_label.setForeground(new Color(4,153,19));
                                input_status_label.setText("User: "+User+" logged successful");
                            }else{
                                input_status_label.setVisible(true);
                                input_status_label.setForeground(new Color(254,90,57));
                                input_status_label.setText("User: "+User+" already logged.");
                            }
                        }else{
                            input_status_label.setVisible(true);
                            input_status_label.setForeground(new Color(254,90,57));
                            input_status_label.setText(Ltype+" is not open.");
                        }
                    }else{
                        input_status_label.setVisible(true);
                            input_status_label.setForeground(new Color(254,90,57));
                            input_status_label.setText("Incorrect ID and Password.");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException  ex) {
            System.out.println(ex);
        }
        
    }//GEN-LAST:event_LogIn_btnActionPerformed

    private void Username_inputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Username_inputFocusGained
        input_status_label.setVisible(false);
    }//GEN-LAST:event_Username_inputFocusGained

    private void Alternativelog_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Alternativelog_btnActionPerformed
        LogType = "Alternative";
        isSelected(Alternativelog_btn);
        unSelected(AdminLog_btn);
        LogType_in.setVisible(true);
    }//GEN-LAST:event_Alternativelog_btnActionPerformed

    private void AdminLog_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminLog_btnActionPerformed
        LogType = "Administrator";
        unSelected(Alternativelog_btn);
        isSelected(AdminLog_btn);
        LogType_in.setVisible(false);
    }//GEN-LAST:event_AdminLog_btnActionPerformed

    private void Username_inputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Username_inputKeyTyped
        char in = evt.getKeyChar();
        if(((in < '0') || (in > '9')) && (in != '\b')){
            evt.consume();
        }
    }//GEN-LAST:event_Username_inputKeyTyped

    public void ShowTime() {
        new Timer(0, (ActionEvent e) -> {
            Date d = new Date();
            SimpleDateFormat t = new SimpleDateFormat("hh:mm a");
            SimpleDateFormat dte = new SimpleDateFormat("MMMM dd, yyyy");
            SimpleDateFormat day = new SimpleDateFormat("EEEE");
        
            Time_label.setText(t.format(d));
            Day_label.setText(day.format(d));
            Date_label.setText(dte.format(d));
            
        }).start();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AdminLog_btn;
    private javax.swing.JButton Alternativelog_btn;
    private javax.swing.JLabel Date_label;
    private javax.swing.JLabel Day_label;
    private javax.swing.JButton LogIn_btn;
    private javax.swing.JComboBox<String> LogType_in;
    private javax.swing.JPasswordField Password_input;
    private javax.swing.JLabel Time_label;
    private javax.swing.JTextField Username_input;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel input_status_label;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
