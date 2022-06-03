
package bluetooth;

import java.io.BufferedReader;
import smartlog_system.attendance_Protocol;
import smartlog_system.Database_Queries;

import javax.obex.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

public class OBEXPutServer{
    
    private static final UUID MY_UUID = new UUID("5ba27d666c6d40fc83321c45f996142e",false); 
    static attendance_Protocol Protocol = new attendance_Protocol();
    static int i = 1;
    SessionNotifier serverConnection;
    StreamConnectionNotifier connect;
    Database_Queries DQ = new Database_Queries();
    
    ExecutorService executor = Executors.newCachedThreadPool();
    
    StreamConnection connection = null;
    
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
    
    public void startServer() throws BluetoothStateException, IOException, InterruptedException, SQLException, ClassNotFoundException{
        
       
        StreamConnectionNotifier server;
        try{
        //display local device address and name
        LocalDevice localDevice = LocalDevice.getLocalDevice();
        /*System.out.println("Address: "+localDevice.getBluetoothAddress());
        System.out.println("Name: "+localDevice.getFriendlyName());*/
        }catch(BluetoothStateException e){
            infoBox("Turn on Bluetooth to start server for login."
                        ,"ERROR",WARNING_MESSAGE);
        }
        
        try{
           
            String url = "btspp://localhost:" + MY_UUID.toString() + ";authenticate=false;encrypt=false";
            server = (StreamConnectionNotifier) Connector.open(url);
            int i = 1;
            while(i == 1){
                System.out.println("Waiting for connection...");
                connection = server.acceptAndOpen();
                RemoteDevice dev = RemoteDevice.getRemoteDevice(connection);
                System.out.println("Remote device address: "+dev.getBluetoothAddress());
                System.out.println("Remote device name: "+dev.getFriendlyName(true));
               
                try{
                        System.out.println("Waiting for input.");
                        InputStream inputStream = connection.openInputStream();
                        StringBuilder buf = new StringBuilder();
                        int data;
                        while((data = inputStream.read()) != -1){
                            buf.append((char) data);
                        }   System.out.println("Received: "+buf.toString());
                        
                        String cut[] = String.valueOf(buf).split("\\s");
                        String ID = cut[0];
                        String Password = cut[1];
                        String CheckType = cut[2];
                        
                        /*InputStream inStream=connection.openInputStream();
                        BufferedReader bReader=new BufferedReader(new InputStreamReader(inStream));
                        String lineRead=bReader.readLine();
                        System.out.println(lineRead);
                        
                        String cut[] = lineRead.split("\\s");
                        String ID = cut[0];
                        String Password = cut[1];
                        String CheckType = cut[2];*/
                        
                        System.out.println("ID:" + ID);
                        System.out.println("Password: " + Password);
                        System.out.println("Check_Type: " + CheckType);
                        
                        //Checking MCaddress if registered match
                        boolean deviceRegistered = DQ.verifyDevice(ID, dev.getBluetoothAddress());
                        String alreadyLog = Protocol.duplicate_Log(ID, Password, CheckType);
                        String msg;
                        
                        if("".equals(alreadyLog) || alreadyLog == null){
                            if(!deviceRegistered){
                                System.out.println("Device is not registered for this account!");}
                            else{
                                Protocol.attendanceLogging_Protocol(ID,Password,CheckType);
                                msg = "Success";
                            }
                        }else{
                            msg = "Error Log:"+ ID;
                            System.out.println("Account Already logged.");
                        }
                        
                        //send response to spp client
                        /*OutputStream outStream=connection.openOutputStream();
                        try (PrintWriter pWriter = new PrintWriter(new OutputStreamWriter(outStream))) {
                            pWriter.write("Success");
                            pWriter.flush();
                        }*/
                }catch(IOException | ClassNotFoundException | SQLException e){
                    e.printStackTrace();
                }
            }
            
        }catch(BluetoothStateException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void send(StreamConnection connection) throws IOException{
    
                OutputStream outputStream = connection.openOutputStream();
                String msg = "10:00";
                byte[] msgBuffer = msg.getBytes();
                outputStream.write(msgBuffer);
                outputStream.flush();
                System.out.println("Sent feedback: "+msg);
                
                connection.close();
    }
    
}

