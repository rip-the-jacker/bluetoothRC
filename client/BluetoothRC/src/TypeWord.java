import javax.microedition.lcdui.*;

class TypeWord implements CommandListener
{
  BluetoothRC parent;
  Form appForm = new Form("Type Word");
  TextField typeField = new TextField("Type","",50,TextField.ANY);
  
  TypeWord(BluetoothRC p)
  {
    parent = p;
    appForm.append(typeField);
    appForm.addCommand(new Command("Back",Command.CANCEL,1));
    appForm.addCommand(new Command("Type",Command.OK,1));
    appForm.addCommand(new Command("Execute", Command.BACK,1));
    parent.display.setCurrent(appForm);
    appForm.setCommandListener(this);
  }
  
  public void commandAction(Command c, Displayable d)
  {
    if(c.getLabel().equals("Back"))
    {
      parent.display.setCurrent(parent.mainList);
    }
    else if(c.getLabel().equals("Execute"))
    {
      parent.rsf.send("execute_" + typeField.getString());
    }
    else if(c.getLabel().equals("Type"))
    {
      parent.rsf.send("typeword_" + typeField.getString());
    }
  }
}
