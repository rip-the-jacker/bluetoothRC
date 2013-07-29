import javax.bluetooth.*;
import java.util.Vector;
import javax.microedition.io.*;
import javax.microedition.lcdui.TextBox;

public class RCServiceFinder implements DiscoveryListener
{
  private Vector remoteDevs = new Vector();
  String rcServiceUrl = null;
  boolean connected = true;
  L2CAPConnection connection;
  boolean discovering = true;
  public int vectorSize = 0;
  
  public void send(String tx)
  {
    try
    {
      connection.send(tx.getBytes());
    }
    catch(Exception e)
    {}
  }
  
  public void deviceDiscovery(TextBox text) throws Exception
  {
    LocalDevice localDevice = LocalDevice.getLocalDevice(); 
    DiscoveryAgent columbus = localDevice.getDiscoveryAgent();
    text.setString("Device Discovery initiated\n");
    int iteration = 0;
    while(vectorSize == 0 && iteration < 3)
    {
      columbus.startInquiry(DiscoveryAgent.GIAC, this);
      int start = text.size() + 1;
      while(discovering)
      {
        for(int i = 1; i < 10; i++)
        {
	  text.insert(".", text.size());
	  try
	  {
	    Thread.sleep(600);
	  }
	  catch(Exception e)
	  {
	  
	  }
        }
        text.delete(start, text.size() - start);
      }
      text.setString(vectorSize + " Devices found");
      try
      {
	Thread.sleep(600);
      }
      catch(Exception e)
      {
	  
      }
    }
  }
    
  public void deviceDiscovered(RemoteDevice remoteDev, DeviceClass dc)
  {
    try
    {
      remoteDevs.addElement(remoteDev);
    }
    catch(Exception ex)
    {
    }
  }
  
  public void serviceDiscovery(TextBox text) throws Exception
  {
    try
    {
      UUID [] uuids = new UUID[1];
      uuids[0] = new UUID("406ba3fea7eb11e092fc63f4474a4f1d",false);
      //uuids[1] = new UUID(0x0003);
      //uuids[2] = new UUID(0x0003);
      int attribs [] = {0x0100};
      LocalDevice localDevice = LocalDevice.getLocalDevice();
      DiscoveryAgent columbus = localDevice.getDiscoveryAgent();
      for(int l = 0;l<remoteDevs.size();l++)
      {
	discovering = true;
        columbus.searchServices(attribs, uuids, (RemoteDevice)(remoteDevs.elementAt(l)), this);
	text.setString("Service Discovery initiated\n");
	int start = text.size() + 1;
        while(discovering)
	{
	  for(int i = 1; i < 10; i++)
	  {
	    text.insert(".", text.size());
	    Thread.sleep(600);
	  }
	  text.delete(start, text.size() - start);
	}
      }
    }
    catch(Exception e)
    {
    }
  }
  
  public void servicesDiscovered(int id,ServiceRecord[] sr)
  {
    rcServiceUrl = sr[0].getConnectionURL(0, false);
  }
  
  public void inquiryCompleted(int dt)
  {
    discovering = false;
    vectorSize = remoteDevs.size();
  }
  
  public void serviceSearchCompleted(int id, int resp)
  {
    discovering = false;
    try
    {
      connection = (L2CAPConnection)Connector.open(rcServiceUrl);
    }
    catch(Exception e)
    {
    }
  }
}
