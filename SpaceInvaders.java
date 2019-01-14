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
      t.moveCursor(i,line);
      //t.moveCursor(i,line);
      t.putCharacter(' ');
    }
  }
  public static ArrayList<Enemy> enemyCreation() {
    ArrayList<Enemy> ans = new ArrayList<Enemy>();
    Enemy e1 = new Enemy(1,1,10,5);
    ans.add(e1);
    Enemy e2 = new Enemy(1,1,11,5);
    ans.add(e2);
    Enemy e3 = new Enemy(1,1,12,5);
    ans.add(e3);
    Enemy e4 = new Enemy(1,1,13,5);
    ans.add(e4);
    Enemy e5 = new Enemy(1,1,10,6);
    ans.add(e5);
    Enemy e6 = new Enemy(1,1,11,6);
    ans.add(e6);
    Enemy e7 = new Enemy(1,1,12,6);
    ans.add(e7);
    Enemy e8 = new Enemy(1,1,13,6);
    ans.add(e8);
    Enemy e9 = new Enemy(1,1,10,7);
    ans.add(e9);
    Enemy e10 = new Enemy(1,1,11,7);
    ans.add(e10);
    Enemy e11 = new Enemy(1,1,12,7);
    ans.add(e11);
    Enemy e12 = new Enemy(1,1,13,7);
    ans.add(e12);
    return ans;
  }

  public static void main(String[] args){
    //sets up new private terminal that the game is going to run on
    Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();
		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);
    //timekeeping: Laser and enemy movement is dependent on this
    long tStart = System.currentTimeMillis();
		long lastSecond = 0;

    //other variables (change later)
		boolean running = true;
    int x = 25;
    int y = 39;
    User user = new User(1,1,x,y,1);
    ArrayList<Integer> lasers = new ArrayList<Integer>(); //keeps track of laser coordinates in the form of <x1,y1,x2,y2...>
    Barrier shields = new Barrier();
    ArrayList<Enemy> enemies = SpaceInvaders.enemyCreation();

    putString(0,0,terminal,"Press [esc] to exit");

  for(int p = 0; p < 40; p++){
      for(int w = 0; w < 100; w++){
        if(shields.barrierExists(w,p)){
          terminal.moveCursor(w,p);
          terminal.putCharacter('#');
        }
      }
    }

    while(running){

      //key input reading and what to do on keypress (player movement/shooting)
      Key key = terminal.readInput();
      if (key != null) {
        if (key.getKind() == Key.Kind.Escape) {//closes program
          terminal.exitPrivateMode();
          System.exit(0);
        }
        if(key.getKind() == Key.Kind.ArrowRight){//moves right
          if (x <= 98) {
            terminal.moveCursor(user.getXPos(),user.getYPos());
            terminal.putCharacter(' ');
            user.move(1);
            terminal.moveCursor(user.getXPos(),user.getYPos());
            terminal.putCharacter('-');
            x++;
          }
        }
        if(key.getKind() == Key.Kind.ArrowLeft){//moves left
          if (x >= 1) {
            terminal.moveCursor(user.getXPos(),user.getYPos());
            terminal.putCharacter(' ');
            user.move(3);
            terminal.moveCursor(user.getXPos(),user.getYPos());
            terminal.putCharacter('-');
            x--;
          }
        }
        if(key.getKind() == Key.Kind.ArrowUp){//shoots laser upward
          lasers.add(user.getXPos());
          lasers.add(user.getYPos());
          terminal.moveCursor(user.getXPos(), user.getYPos());
          terminal.putCharacter('^');
        }
      }



      long tEnd = System.currentTimeMillis();
      long millis = tEnd - tStart;
      if (millis/300 > lastSecond) {
        lastSecond = millis/300;
        for (int i = 0; i < lasers.size(); i+=2) {
          terminal.moveCursor(lasers.get(i),lasers.get(i+1));
          terminal.putCharacter(' ');
          if (lasers.get(i+1) == 1 || shields.barrierExists(lasers.get(i),lasers.get(i+1))) {
            shields.destroy(lasers.get(i),lasers.get(i+1));
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
