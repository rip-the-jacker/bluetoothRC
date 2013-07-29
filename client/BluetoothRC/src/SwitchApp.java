import javax.microedition.lcdui.*;

class SwitchApp implements CommandListener
{
  BluetoothRC parent;
  String [] switchButton = {"Switch"};
  List switching = new List("Switch", List.IMPLICIT,switchButton, null);
  
  SwitchApp(BluetoothRC p)
  {
    parent = p;
    switching.addCommand(new Command("Back",Command.BACK,1));
    Command switcher = new Command("Switch", Command.ITEM, 1);
    switching.setSelectCommand(switcher);
    parent.display.setCurrent(switching);
    switching.setCommandListener(this);
    parent.rsf.send("switchstart");
  }
  
  public void commandAction(Command c, Displayable d)
  {
    if(c.getLabel().equals("Back"))
    {
      parent.rsf.send("switchend");
      parent.display.setCurrent(parent.mainList);
    }
    else if(c.getLabel().equals("Switch"))
    {
      parent.rsf.send("switch");
    }
  }
}
