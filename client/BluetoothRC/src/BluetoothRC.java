import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.*;

public class BluetoothRC extends MIDlet implements CommandListener
{
  TextBox initiator = new TextBox("Initializing",null,200,TextField.UNEDITABLE);
  String [] options = {"Launch new app", "Control apps", "Switch apps", "Type"};
  List mainList = new List("Options", List.IMPLICIT, options, null);
  RCServiceFinder rsf;
  Display display;
  
  public BluetoothRC()
  {
    display = Display.getDisplay(this);
    mainList.addCommand(new Command("Exit",Command.EXIT,1));
    Command selection = new Command("Selection", Command.ITEM, 1);
    mainList.setSelectCommand(selection);
    mainList.setCommandListener(this);
    rsf = new RCServiceFinder();
  }
  
  protected void startApp()
  {
    try
    {
      display.setCurrent(initiator);
      rsf.deviceDiscovery(initiator);
      rsf.serviceDiscovery(initiator);
      display.setCurrent(mainList);
    }
    catch(Exception e)
    {
      
    }
  }
  
  protected void pauseApp()
  {}
  
  protected void destroyApp(boolean unconditional)
  {
    notifyDestroyed();
  }
  
  public void commandAction(Command c, Displayable disp)
  {
    if(c.getLabel().equals("Exit"))
    {
      destroyApp(true);
    }
    
    else if(c.getLabel().equals("Selection"))
    {
      List l = (List)disp;
      switch(l.getSelectedIndex())
      {
		case 0: new LaunchApp(this);
		break;
		case 1: new ControlApp(this);
		break;
		case 2: new SwitchApp(this);
		break;
		case 3: new TypeWord(this);
		break;
		default:display.setCurrent(new Alert("Sorry!!","This feature has not been implemented yet",null,null), mainList);
      }
    }
    
  }
}
