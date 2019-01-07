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

    putString(0,0,terminal, "Press [esc] to exit");

		boolean running = true;

    int playerx = 0;
    int playery = 0;

    while(running){
      //lots of stuff to go here
      Key key = terminal.readInput();

      if (key != null) {
        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          System.exit(0);
        }
      }
        if(key.getKind() == Key.Kind.ArrowRight){
          terminal.moveCursor(x,y);
          terminal.putCharacter(' ');
          if(x == 1 //CHANGE){
          x++;
        }}
        if(key.getKind() == Key.Kind.ArrowLeft){
          terminal.moveCursor(x,y);
          terminal.putCharacter(' ');
          if(x > 0){
          x--
        }}

    }

  }

}
