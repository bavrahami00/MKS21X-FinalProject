import java.util.*;
import com.googlecode.lanterna.terminal.Terminal.SGR;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.input.CharacterPattern;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.KeyMappingProfile;

public class SpaceInvaders{

//from terminal demo
  public static void putString(int r, int c,Terminal t, String s){
		t.moveCursor(r,c);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}
	}

  public static void main(String[] args){

    Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();

		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);

    long tStart = System.currentTimeMillis();
		long lastSecond = 0;
    putString(0,0,terminal,"Press [esc] to exit");

		boolean running = true;

    int x = 25;
    int y = 25;
    User user = new User(1,1,x,y,1);
    ArrayList<Integer> lasers = new ArrayList<Integer>();

    while(running){
      //lots of stuff to go here
      Key key = terminal.readInput();

      if (key != null) {
        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          System.exit(0);
        }
        if(key.getKind() == Key.Kind.ArrowRight){
          user.move(1);
          user.move(1);
          terminal.moveCursor(user.getXPos(),user.getYPos());
          terminal.putCharacter('a');
          x += 2;
        }
        if(key.getKind() == Key.Kind.ArrowLeft){
          user.move(3);
          user.move(3);
          terminal.moveCursor(user.getXPos(),user.getYPos());
          terminal.putCharacter('b');
          if (x >= 2) {
            x -= 2;
          }
        }
        if(key.getKind() == Key.Kind.ArrowUp){
          lasers.add(user.getXPos());
          lasers.add(user.getYPos());
          terminal.moveCursor(user.getXPos(), user.getYPos());
          terminal.putCharacter('^');
        }
      }
      long tEnd = System.currentTimeMillis();
      long millis = tEnd - tStart;
      if (millis/1000 > lastSecond) {
        lastSecond = millis/1000;
        for (int i = 0; i < lasers.size(); i+=2) {
          terminal.moveCursor(lasers.get(i),lasers.get(i+1));
          terminal.putCharacter(' ');
          terminal.moveCursor(lasers.get(i),lasers.get(i+1)-1);
          terminal.putCharacter('^');
        }
      }
      SpaceInvaders.putString(30,0,terminal,""+millis/1000);
    }
  }
}
