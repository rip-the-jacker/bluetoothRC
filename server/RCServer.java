
// Notes:
// 	1.Set CLASSPATH to the bluecove jar.(gpl bluecove jar also in case of linux).
// 	2.Linux - Install libbluetooth-dev or similar(Devel files for linux BlueZ stack).


import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.L2CAPConnection;
import javax.bluetooth.L2CAPConnectionNotifier;
import javax.bluetooth.LocalDevice;
import javax.microedition.io.Connector;

public class RCServer
{
  L2CAPConnection connection;
  public static void main(String [] args)
  {
    RCServer rcs = new RCServer();
    rcs.initiate();
  }
  
  public void initiate()
  {
    
    try
    {
      String rcServiceId = "406ba3fea7eb11e092fc63f4474a4f1d";//BluetoothRC app UUID.(using uuidgen in bash :P)
      String rcServiceUrl = "btl2cap://localhost:" + rcServiceId + ";name=Bluetooth RC;authenticate=false";
      L2CAPConnectionNotifier notifier = (L2CAPConnectionNotifier)Connector.open(rcServiceUrl);
      System.out.println("Waiting......");
      
      while(true)
      {
	connection = notifier.acceptAndOpen();
	connectClient();
      }
      
    }
    
    catch(Exception e)
    {
      e.printStackTrace();
    }
    
  }
  
  public void connectClient()
  {
    RCProto rcp = new RCProto();
    try
    {
      System.out.println("Connected");
      boolean listening = true;     
      while(listening)
      {
	byte [] inbuf;
	if(connection.ready())
	{
	  inbuf = new byte[1000];
	  connection.receive(inbuf);
	  String s = new String(inbuf);
	  System.out.println(s.trim());
	  rcp.decide(s.trim());
	}
      }
    }
    
    catch(Exception e)
    {
      System.out.println("Dont forget to install libbluetooth-dev or similar " + e);
    }
  }
  
}
