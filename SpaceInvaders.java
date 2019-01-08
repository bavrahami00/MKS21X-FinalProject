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

    int x = 50;
    int y = 50;

    while(running){
      //lots of stuff to go here
      Key key = terminal.readInput();

      if (key != null) {
        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          System.exit(0);
        }
        if(key.getKind() == Key.Kind.ArrowRight){
          terminal.moveCursor(x,y);
          terminal.putCharacter('A');
          x += 2;
        }
        if(key.getKind() == Key.Kind.ArrowLeft){
          terminal.moveCursor(x,y);
          terminal.putCharacter('B');
          if (x >= 2) {
            x -= 2;
          }
        }
        if(key.getKind() == Key.Kind.ArrowUp){
          terminal.moveCursor(x,y);
          terminal.putCharacter('C');
          if (y >= 1) {
            y--;
          }
        }
        if(key.getKind() == Key.Kind.ArrowDown){
          terminal.moveCursor(x,y);
          terminal.putCharacter('D');
          y++;
        }
      }
      long tEnd = System.currentTimeMillis();
      long millis = tEnd - tStart;
    }
  }
}
