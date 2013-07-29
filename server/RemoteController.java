import java.awt.Robot;//heart of the program - the robot interface
import static java.awt.event.KeyEvent.*;//for all the keycodes

class RemoteController
{ 
  Robot robo;
  public RemoteController()
  {
    try
    {
      robo = new Robot();
    }
    catch(Exception e)
    {
      System.out.println("Cant start Remote Controller!!");
    }
  }
  
  public void krunner()
  {
  	System.out.println("ubuntuuuuuu...");
  	robo.keyPress(VK_WINDOWS);
  	robo.keyRelease(VK_WINDOWS);
  	robo.delay(100);
  }
  
  public void runner()
  {
    robo.keyPress(VK_ALT);
    robo.delay(50);
    robo.keyPress(VK_F2);
    robo.keyRelease(VK_F2);
    robo.delay(50);
    robo.keyRelease(VK_ALT);
    robo.delay(100);
  }
  
  public void typeWord(String word)
  {
    char [] letters = word.toCharArray();
    for(int i = 0; i < letters.length; i++)
    {
      if(!Character.isLetterOrDigit(letters[i]))
      {
	this.type(letters[i]);
	robo.delay(200);
	continue;
      }
      if(Character.isUpperCase(letters[i]))
      {
	robo.keyPress(VK_SHIFT);
      }
      robo.keyPress(Character.toUpperCase(letters[i]));
      robo.keyRelease(Character.toUpperCase(letters[i]));
      if(Character.isUpperCase(letters[i]))
      {
	robo.keyRelease(VK_SHIFT);
      }
      robo.delay(200);
    }
  }
  
  public void type(int key)
  {
    if(ConstantMapping.isSecondary(key))
    {
      robo.keyPress(VK_SHIFT);
    }
    robo.keyPress(ConstantMapping.unicodeToEvent(key));
    robo.keyRelease(ConstantMapping.unicodeToEvent(key));
    if(ConstantMapping.isSecondary(key))
    {
      robo.keyRelease(VK_SHIFT);
    }
  }
  
  public void type(int key, int modifier)
  {
    robo.keyPress(modifier);
    robo.keyPress(key);
    robo.keyRelease(key);
    robo.keyRelease(modifier);
  }
  
  public void type(int key, int modifier1, int modifier2)
  {
    robo.keyPress(modifier1);
    robo.keyPress(modifier2);
    robo.keyPress(key);
    robo.keyRelease(key);
    robo.keyRelease(modifier2);
    robo.keyRelease(modifier1);
  }
  
  public void execute(String word)
  {
    this.typeWord(word);
    robo.delay(500);
    robo.keyPress(VK_ENTER);
    robo.keyRelease(VK_ENTER);
    robo.delay(3000);
  }
  
  public void launch(String word)
  {
    runner();
    System.out.println("started runner");
    execute(word);
  }
  
  public void doSwitch()
  {
    robo.keyPress(VK_TAB);
    robo.keyRelease(VK_TAB);
  }
  
  public void startSwitch()
  {
    robo.keyPress(VK_ALT);
  }
  
  public void endSwitch()
  {
    robo.keyRelease(VK_ALT);
  }
}
