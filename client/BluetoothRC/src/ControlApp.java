import javax.microedition.lcdui.*;

class ControlApp implements CommandListener
{
  BluetoothRC parent;
  String [] apps = {"Vlc"};
  List appList = new List("Apps", List.IMPLICIT,apps, null);
  
  ControlApp(BluetoothRC p)
  {
    parent = p;
    appList.addCommand(new Command("Back",Command.BACK,1));
    Command selection = new Command("Selection", Command.ITEM, 1);
    appList.setSelectCommand(selection);
    parent.display.setCurrent(appList);
    appList.setCommandListener(this);
  }
  
  public void commandAction(Command c, Displayable disp)
  {
    if(c.getLabel().equals("Back"))
    {
      parent.display.setCurrent(parent.mainList);
    }
    else if(c.getLabel().equals("Selection"))
    {
      List l = (List)disp;
      switch(l.getSelectedIndex())
      {
		case 0: new Vlc(parent);
		break;
		default:parent.display.setCurrent(new Alert("Sorry!!","This feature has not been implemented yet",null,null), appList);
	  }		
    }
  }
}
