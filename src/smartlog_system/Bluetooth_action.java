
package smartlog_system;

import com.intel.bluetooth.RemoteDeviceHelper;
import java.io.IOException;
import java.util.Vector;
import javax.bluetooth.*;

public class Bluetooth_action {
    
    public static final Vector devicesDiscovered = new Vector();
    public static int Counter = 0;
    public static String[][] Devices = new String[50][50];
    
    private String[] BT_add = new String[50];
    private String[] BT_name = new String[50];
    
    public String[] BTdevice_address(){
        return BT_add;
    }
    
    public String[] BTdevice_name(){
        return BT_name;
    }
    
    public void Pairing() throws BluetoothStateException, InterruptedException{
        final Object inquiryCompletedEvent = new Object();
        //Scanner Scan = new Scanner(System.in);
        
        devicesDiscovered.clear();
        
        DiscoveryListener listener = new DiscoveryListener(){
            
            @Override
            public void deviceDiscovered(RemoteDevice rd, DeviceClass dc) {
                
                devicesDiscovered.addElement(rd);
                
                try {
                        System.out.println(Counter+" Status: Unknown\n");
                        
                        Devices[Counter][0] = rd.getFriendlyName(false);
                        Devices[Counter][1] = rd.getBluetoothAddress();
                        
                        BT_name[Counter] = rd.getFriendlyName(false);
                        BT_add[Counter] = rd.getBluetoothAddress();
                        
                        System.out.println(""+Devices[Counter][0]+"\n"+Devices[Counter][1]+"\n-----");
                        
                        Counter++;
                    }catch (IOException cantGetDeviceName) {}
            }

            @Override
            public void servicesDiscovered(int i, ServiceRecord[] srs) {}

            @Override
            public void serviceSearchCompleted(int i, int i1) {}

            @Override
            public void inquiryCompleted(int i) {
                System.out.println("Device Inquiry Completed!");
                synchronized(inquiryCompletedEvent){
                    inquiryCompletedEvent.notifyAll();
                }
            }
        };
        synchronized(inquiryCompletedEvent){
            boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, listener);
            if(started){
                System.out.println("Wait for device inquiry to complete...");
                inquiryCompletedEvent.wait();
                System.out.println(devicesDiscovered.size() + " device found\n\n");
            }
        }
        
        //return Devices;
    }
    
    public void BT_Auth(int device_ID){
                try {
                    //authenticating
                    RemoteDeviceHelper.authenticate((RemoteDevice) devicesDiscovered.get(device_ID),"1234");
                    System.out.println("Autentication Sent");
                } catch (IOException CantAuthenticate) {
                    System.out.println("Error Authentication\n"+CantAuthenticate);
                }
    }
    
}
