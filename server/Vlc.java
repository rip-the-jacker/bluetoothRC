import static java.awt.event.KeyEvent.*;

class Vlc
{
  final int shift = VK_SHIFT;
  final int alt = VK_ALT;
  final int ctrl = VK_CONTROL;
  final int up = VK_UP;
  final int down = VK_DOWN;
  final int left = VK_LEFT;
  final int right = VK_RIGHT;
  RemoteController remote;
  String [] actions = {"volume-up","volume-down","xshort-jump","xshort-backjump","short-jump","short-backjump","long-jump","long-backjump","play","stop","next","previous"};
  int [] [] keyCodes = {{ctrl,up}, {ctrl,down}, {shift,right}, {shift,left}, {alt,right}, {alt,left}, {ctrl,right}, {ctrl,left}, {VK_SPACE}, {'s'}, {'n'}, {'p'}};
  
  public Vlc(RemoteController rc)
  {
    remote = rc;
  }
  
  public void act(String action)
  {
    for(int i = 0; i < actions.length; i++)
    {
      if(action.equals(actions[i]))
      {
	switch(keyCodes[i].length)
	{
	  case 1: remote.type(keyCodes[i][0]);
	  break;
	  case 2: remote.type(keyCodes[i][1], keyCodes[i][0]);
	  break;
	  default:System.out.println("unknown vlc action");
	}
      }
    }
  }
}
