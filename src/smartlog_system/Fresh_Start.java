
package smartlog_system;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

public class Fresh_Start extends javax.swing.JFrame{

    Database_Queries DQ = new Database_Queries();
    
    public Fresh_Start() {
        initComponents();
        ShowTime();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Date_label = new javax.swing.JLabel();
        Time_label = new javax.swing.JLabel();
        Day_label = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        MI_in = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        info = new javax.swing.JLabel();
        Age_in = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Fname_in = new javax.swing.JTextField();
        ID_in = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Gender_in = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        ConfirmPassword_in = new javax.swing.JPasswordField();
        Password_in = new javax.swing.JPasswordField();
        SubmitForm_btn = new javax.swing.JButton();
        Lname_in = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Date_label.setBackground(new java.awt.Color(255, 255, 255));
        Date_label.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        Date_label.setForeground(new java.awt.Color(4, 8, 114));
        Date_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Date_label.setText("July 8, 2021");
        jPanel1.add(Date_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 70, 210, 30));

        Time_label.setBackground(new java.awt.Color(255, 255, 255));
        Time_label.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        Time_label.setForeground(new java.awt.Color(4, 8, 114));
        Time_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Time_label.setText("3:00 PM");
        jPanel1.add(Time_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 70, 90, 30));

        Day_label.setBackground(new java.awt.Color(255, 255, 255));
        Day_label.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        Day_label.setForeground(new java.awt.Color(4, 8, 114));
        Day_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Day_label.setText("Thursday");
        jPanel1.add(Day_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 70, 110, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(4, 8, 114));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("NAME");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 310, -1));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(4, 153, 19));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("SIGN UP ");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 330, 60));

        MI_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        MI_in.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        MI_in.setText("MI");
        MI_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
        jPanel2.add(MI_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 30, 30));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(4, 8, 114));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("PASSWORD");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 310, -1));

        info.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        info.setForeground(new java.awt.Color(4, 8, 114));
        info.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        info.setText("CONFIRM PASSWORD");
        jPanel2.add(info, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 310, -1));

        Age_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Age_in.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Age_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Age_in.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Age_inFocusLost(evt);
            }
        });
        Age_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Age_inKeyTyped(evt);
            }
        });
        jPanel2.add(Age_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, 30, 30));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(4, 8, 114));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("AGE");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, 30, -1));

        Fname_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Fname_in.setText("FIRST NAME");
        Fname_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
        jPanel2.add(Fname_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 120, 30));

        ID_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ID_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ID_in.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ID_inFocusGained(evt);
            }
        });
        ID_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ID_inKeyTyped(evt);
            }
        });
        jPanel2.add(ID_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 310, 30));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(4, 8, 114));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("GENDER");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 80, -1));

        Gender_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Gender_in.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MALE", "FEMALE" }));
        Gender_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(Gender_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 140, 30));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(4, 8, 114));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("ID NUMBER");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 310, -1));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(4, 8, 114));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("POSITION");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 120, -1));

        jTextField10.setEditable(false);
        jTextField10.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField10.setText("ADMINISTRATOR");
        jTextField10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 120, 30));

        ConfirmPassword_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ConfirmPassword_in.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ConfirmPassword_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ConfirmPassword_in.setName(""); // NOI18N
        ConfirmPassword_in.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ConfirmPassword_inFocusLost(evt);
            }
        });
        jPanel2.add(ConfirmPassword_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 310, 30));

        Password_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Password_in.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Password_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(Password_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 310, 30));

        SubmitForm_btn.setText("SUBMIT");
        SubmitForm_btn.setBackground(new java.awt.Color(4, 8, 114));
        SubmitForm_btn.setBorderPainted(false);
        SubmitForm_btn.setFocusPainted(false);
        SubmitForm_btn.setFocusable(false);
        SubmitForm_btn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        SubmitForm_btn.setForeground(new java.awt.Color(255, 255, 255));
        SubmitForm_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitForm_btnActionPerformed(evt);
            }
        });
        jPanel2.add(SubmitForm_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 310, 40));

        Lname_in.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Lname_in.setText("LAST NAME");
        Lname_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
        jPanel2.add(Lname_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 140, 30));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(4, 8, 114));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("CONFIRM PASSWORD");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 310, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(805, 137, 332, 440));

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

     
    public void enroll() throws ClassNotFoundException, SQLException{
        String ID = ID_in.getText(),
               Fname = Fname_in.getText(),
               Lname = Lname_in.getText(),
               MI = MI_in.getText(),
               Age = Age_in.getText(),
               Gend = (String) Gender_in.getModel().getSelectedItem(),
               CPass = new String(ConfirmPassword_in.getPassword());
        
                                String commandPass = "INSERT INTO devices(IDnumber,BTname,BTaddress)"
                                            + "values('"+ID+"','','')";
                                DQ.CRUDE(commandPass);
                                
                                String DvID = DQ.fetch("devices", " WHERE IDnumber = '"+ID+"'", "Device_ID");
                                System.out.println("Adding device Successful");

                                commandPass = "INSERT INTO departmentclass(Department,Head)"
                                            + "values('System','"+Lname+", "+Fname+" "+MI+".')";
                                DQ.CRUDE(commandPass);
                                
                                commandPass = "INSERT INTO departments(IDnumber,Department,Position)"
                                            + "values('"+ID+"','System','Administrator')";
                                DQ.CRUDE(commandPass);
                                
                                String DptID = DQ.fetch("departments", " WHERE IDnumber = '"+ID+"'", "id_Departments");
                                System.out.println("Adding to the department Successful");
                                
        String cmnd = "Insert into smartlogdb.personnels set IDnumber='"+ID+"',Lname='"+Lname+"',Fname='"+Fname+"',MI='"+MI+"',Age='"+Age+"',Gender='"+Gend+"',Device_ID='"+DvID+"',Dpt_ID='"+DptID+"',Preveledge='Level 3',Password='"+CPass+"' ";
        
        DQ.CRUDE(cmnd);
        this.hide();
        LogIn_Frame LF = new LogIn_Frame();
        LF.setVisible(true);
    }
    
    public boolean hasEmpty(){
    
        String ID = ID_in.getText(),
               Fname = Fname_in.getText(),
               Lname = Lname_in.getText(),
               MI = MI_in.getText(),
               Age = Age_in.getText(),
               Pass = new String(Password_in.getPassword()),
               CPass = new String(ConfirmPassword_in.getPassword());
        
        return "".equals(ID)|| 
                "FIRST NAME".equals(Fname)||
                "LAST NAME".equals(Lname)||
                "MI".equals(MI)||
                "".equals(Age)||
                "".equals(Pass)||
                "".equals(CPass);
    }
    
    
    private void MI_inFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MI_inFocusGained
        String MI = MI_in.getText();
        if("MI".equals(MI)){
            MI_in.setText("");
        }
    }//GEN-LAST:event_MI_inFocusGained

    private void MI_inFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MI_inFocusLost
        String MI = MI_in.getText();
        if("".equals(MI)){
            MI_in.setText("MI");
        }
    }//GEN-LAST:event_MI_inFocusLost

    private void MI_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MI_inKeyTyped
        char in = evt.getKeyChar();
        if(Character.isLetter(in) || Character.isISOControl(in)){
        }else{
            evt.consume();
        }
    }//GEN-LAST:event_MI_inKeyTyped

    private void Age_inFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Age_inFocusLost
        String x = Age_in.getText();
        if(Integer.valueOf(x) < 10){
            info.setVisible(true);
            info.setText("Underage.");
            info.setForeground(Color.RED);
        }else if(Integer.valueOf(x) > 99){
            info.setVisible(true);
            info.setText("Overage");
            info.setForeground(Color.RED);
        }else{}
    }//GEN-LAST:event_Age_inFocusLost

    private void Age_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Age_inKeyTyped
        char in = evt.getKeyChar();
        if(((in < '0') || (in > '9')) && (in != '\b')){
            evt.consume();
        }
    }//GEN-LAST:event_Age_inKeyTyped

    private void Fname_inFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Fname_inFocusGained
        String Fn = Fname_in.getText();
        if("FIRST NAME".equals(Fn)){
            Fname_in.setText("");
        }
    }//GEN-LAST:event_Fname_inFocusGained

    private void Fname_inFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Fname_inFocusLost
        String Fn = Fname_in.getText();
        if("".equals(Fn)){
            Fname_in.setText("FIRST NAME");
        }
    }//GEN-LAST:event_Fname_inFocusLost

    private void Fname_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Fname_inKeyTyped
        char in = evt.getKeyChar();
        if(Character.isLetter(in) || Character.isISOControl(in)){
        }else{
            evt.consume();
        }
    }//GEN-LAST:event_Fname_inKeyTyped

    private void ID_inFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ID_inFocusGained
        info.setVisible(false);
    }//GEN-LAST:event_ID_inFocusGained

    private void ID_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ID_inKeyTyped
        char in = evt.getKeyChar();
        if(((in < '0') || (in > '9')) && (in != '\b')){
            evt.consume();
        }
    }//GEN-LAST:event_ID_inKeyTyped

    private void ConfirmPassword_inFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ConfirmPassword_inFocusLost
        String Pass = new String(Password_in.getPassword()),
        CPass = new String(ConfirmPassword_in.getPassword());

        if(!(Pass.equals(CPass))){
            info.setVisible(true);
            info.setText("Password don't matched.");
            info.setForeground(Color.RED);
        }
    }//GEN-LAST:event_ConfirmPassword_inFocusLost

    private void SubmitForm_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitForm_btnActionPerformed
    try {
        String Pass = new String(Password_in.getPassword()),
        CPass = new String(ConfirmPassword_in.getPassword());
        
        boolean hasEmptyField = hasEmpty();
        
            if(hasEmptyField == true){
                info.setVisible(true);
                info.setText("Fill in all fields.");
                info.setForeground(Color.RED);
            }else{
                if(Pass.length() < 8 || Pass.length() > 16){
                    info.setVisible(true);
                    info.setText("Password range 8 to 16 Characters");
                    info.setForeground(Color.RED);
                }else{
                    if(Pass.equals(CPass)){
                        System.out.println("Trying to register administrator.");
                        enroll();
                        System.out.println("Administrator Registered.");
                    }else{
                        info.setVisible(true);
                        info.setText("Password don't matched.");
                        info.setForeground(Color.RED);
                        System.out.println("P unmatched.");
                    }
                }
            }
      } catch (ClassNotFoundException | SQLException ex) {}
    }//GEN-LAST:event_SubmitForm_btnActionPerformed

    private void Lname_inFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Lname_inFocusGained
        String Ln = Lname_in.getText();
        if("LAST NAME".equals(Ln)){
            Lname_in.setText("");
        }
    }//GEN-LAST:event_Lname_inFocusGained

    private void Lname_inFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Lname_inFocusLost
        String Ln = Lname_in.getText();
        if("".equals(Ln)){
            Lname_in.setText("LAST NAME");
        }
    }//GEN-LAST:event_Lname_inFocusLost

    private void Lname_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Lname_inKeyTyped
        char in = evt.getKeyChar();
        if(Character.isLetter(in) || Character.isISOControl(in)){
        }else{
            evt.consume();
        }
    }//GEN-LAST:event_Lname_inKeyTyped

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
    private javax.swing.JTextField Age_in;
    private javax.swing.JPasswordField ConfirmPassword_in;
    private javax.swing.JLabel Date_label;
    private javax.swing.JLabel Day_label;
    private javax.swing.JTextField Fname_in;
    private javax.swing.JComboBox<String> Gender_in;
    private javax.swing.JTextField ID_in;
    private javax.swing.JTextField Lname_in;
    private javax.swing.JTextField MI_in;
    private javax.swing.JPasswordField Password_in;
    private javax.swing.JButton SubmitForm_btn;
    private javax.swing.JLabel Time_label;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel info;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField10;
    // End of variables declaration//GEN-END:variables
}
