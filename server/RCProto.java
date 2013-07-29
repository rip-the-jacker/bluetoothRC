class RCProto
{
  public void decide(String rx)
  {
    RemoteController rc = new RemoteController();
    String command = tokenize(rx);
    
    if(command.equals("typeword"))
    {
      rc.typeWord(rx.substring("typeword".length() + 1));// +1 is to omit the _ underscore that is defined in the protocol(eg. typeword_foo)
    }
    else if(command.equals("execute"))
    {
      rc.execute(rx.substring("execute".length() + 1));// +1 is to omit the _ underscore that is defined in the protocol(eg. execute_foo)
    }
    else if(command.equals("launch"))
    {
      rc.launch(rx.substring("launch".length() + 1));// +1 is to omit the _ underscore that is defined in the protocol(eg. launch_foo)
    }
    else if(command.equals("switch"))
    {
      rc.doSwitch();
    }
    else if(command.equals("switchstart"))
    {
      rc.startSwitch();
    }
    else if(command.equals("switchend"))
    {
      rc.endSwitch();
    }
    else if(command.equals("vlc"))
    {
      Vlc vlc = new Vlc(rc);
      vlc.act(rx.substring("vlc".length()+1));// +1 is to omit the _ underscore that is defined in the protocol(eg. vlc_foo)
    }
    
  }
  
  public String tokenize(String s)
  {
    String [] tokens = s.split("_");
    return tokens[0];
  }
}
