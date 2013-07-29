import javax.microedition.lcdui.*;

class LaunchApp implements CommandListener
{
  BluetoothRC parent;
  Form appForm = new Form("Launch Application");
  TextField launchField = new TextField("Launch","",50,TextField.ANY);
  
  LaunchApp(BluetoothRC p)
  {
    parent = p;
    appForm.append(launchField);
    appForm.addCommand(new Command("Back",Command.BACK,1));
    appForm.addCommand(new Command("Launch",Command.OK,1));
    parent.display.setCurrent(appForm);
    appForm.setCommandListener(this);
  }
  
  public void commandAction(Command c, Displayable d)
  {
    if(c.getLabel().equals("Back"))
    {
      parent.display.setCurrent(parent.mainList);
    }
    else if(c.getLabel().equals("Launch"))
    {
      parent.rsf.send("launch_" + launchField.getString());
    }
  }
}
