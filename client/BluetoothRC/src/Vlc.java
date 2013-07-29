import javax.microedition.lcdui.*;

class Vlc implements CommandListener
{
  BluetoothRC parent;
  String [] actions = {"volume-up","volume-down","xshort-jump","xshort-backjump","short-jump","short-backjump","long-jump","long-backjump","play","stop","next","previous"};
  List actionList = new List("Actions", List.IMPLICIT,actions, null);
  
  Vlc(BluetoothRC p)
  {
    parent = p;
    actionList.addCommand(new Command("Back",Command.BACK,1));
    Command selection = new Command("Selection", Command.ITEM, 1);
    actionList.setSelectCommand(selection);
    parent.display.setCurrent(actionList);
    actionList.setCommandListener(this);
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
      String command = l.getString(l.getSelectedIndex());
      parent.rsf.send("vlc_" + command);	
    }
  }
}
