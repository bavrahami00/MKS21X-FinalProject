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

  private static void clearLine(int line, Terminal t, TerminalSize size){
    t.moveCursor(0,line);
    for(int i = 0; i < size.getColumns(); i++){
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

    int x = 25;
    int y = 25;
    User user = new User(1,1,x,y,1);
    ArrayList<Integer> lasers = new ArrayList<Integer>();

    int playerx = 0;
    int playery = 40;

    while(running){

      for(int p = 30; y < 34; y++){
      for(int i = 0; i < size.getColumns();i++){
        if((i >= 5 && i <= 15) || (i >= 30 && i <= 40) || (i >= 55 && i <= 65) || (i >= 80 & i <= 90)){
        terminal.moveCursor(i,y);
        terminal.putCharacter('#');
      }}}

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
          terminal.moveCursor(user.getXPos(),user.getYPos());
          terminal.putCharacter(' ');
          user.move(1);
          terminal.moveCursor(user.getXPos(),user.getYPos());
          terminal.putCharacter('-');
          x++;
        }
      }
        if(key.getKind() == Key.Kind.ArrowLeft){
          terminal.moveCursor(user.getXPos(),user.getYPos());
          terminal.putCharacter(' ');
          user.move(3);
          terminal.moveCursor(user.getXPos(),user.getYPos());
          terminal.putCharacter('-');
          if (x >= 1) {
            x--;
          }
        }
        if(key.getKind() == Key.Kind.ArrowUp){
          lasers.add(user.getXPos());
          lasers.add(user.getYPos());
          terminal.moveCursor(user.getXPos(), user.getYPos());
          terminal.putCharacter('^');
        }
      }
      terminal.moveCursor(playerx,playery);
      clearLine(40,terminal,size);
      clearLine(playery - 2,terminal,size);

      terminal.moveCursor(playerx,playery);
      clearLine(40,terminal,size);
      clearLine(playery - 2,terminal,size);
      if(playerx > 0){
      playerx--;

      long tEnd = System.currentTimeMillis();
      long millis = tEnd - tStart;
      if (millis/300 > lastSecond) {
        lastSecond = millis/300;
        for (int i = 0; i < lasers.size(); i+=2) {
          terminal.moveCursor(lasers.get(i),lasers.get(i+1));
          terminal.putCharacter(' ');
          if (lasers.get(i+1) == 1) {
            lasers.remove(i);
            lasers.remove(i);
            i -= 2;
          }
          else {
            terminal.moveCursor(lasers.get(i),lasers.get(i+1)-1);
            terminal.putCharacter('^');
            lasers.set(i+1,lasers.get(i+1)-1);
          }
        }
        terminal.moveCursor(user.getXPos(),user.getYPos());
        terminal.putCharacter('-');
      }
      SpaceInvaders.putString(30,0,terminal,""+millis/1000);
    }
  }
}
