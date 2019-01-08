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

  private static void clearLine(int line, Terminal t, TerminalSize size){
    for(int i = 0; i < size.getColumns(); i++){
      t.moveCursor(i,line);
      t.putCharacter(' ');
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

    int playerx = 0;
    int playery = 40;

    while(running){

      terminal.moveCursor(playerx, playery);
      terminal.putCharacter('<');
      terminal.moveCursor(playerx + 1, playery);
      terminal.putCharacter('=');
      terminal.moveCursor(playerx + 2, playery);
      terminal.putCharacter('=');
      terminal.moveCursor(playerx + 3, playery);
      terminal.putCharacter('=');
      terminal.moveCursor(playerx + 4, playery);
      terminal.putCharacter('=');
      terminal.moveCursor(playerx + 5, playery);
      terminal.putCharacter('=');
      terminal.moveCursor(playerx + 6, playery);
      terminal.putCharacter('>');
      terminal.moveCursor(playerx + 3, playery - 2);
      terminal.putCharacter('^');


      Key key = terminal.readInput();

      if (key != null) {
        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          System.exit(0);
        }
        if(key.getKind() == Key.Kind.ArrowRight){
          terminal.moveCursor(playerx,playery);
          clearLine(40,terminal,size);
          clearLine(playery - 2,terminal,size);
          if(playerx + 7 < size.getColumns()) //CHANGE)
          {
          playerx++;
        }
      }
        if(key.getKind() == Key.Kind.ArrowLeft){
          terminal.moveCursor(playerx,playery);
          clearLine(40,terminal,size);
          clearLine(playery - 2,terminal,size);
          if(playerx > 0){
          playerx--;
        }
      }

    }
    long tEnd = System.currentTimeMillis();
    long millis = tEnd - tStart;
  }

}
}
