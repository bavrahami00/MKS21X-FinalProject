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
  public static ArrayList<Enemy> enemyCreation() {
    ArrayList<Enemy> ans = new ArrayList<Enemy>();
    Enemy e;
    for (int p = 25; p <= 75; p+=2) {
      for (int m = 5; m < 11; m+=2) {
        e = new Enemy(p,m);
        ans.add(e);
      }
    }
    return ans;
  }
  public static void enemyDrawing(Terminal t, ArrayList<Enemy> enemies) {
    for (int p = 0; p < enemies.size(); p++) {
      t.moveCursor(enemies.get(p).getXPos(),enemies.get(p).getYPos());
      t.putCharacter('E');
    }
  }

  public static int findexOf(ArrayList<Enemy> arr, int xp, int yp) {
    for (int p = 0; p < arr.size(); p++) {
      if (arr.get(p).getXPos() == xp && arr.get(p).getYPos() == yp) {
        return p;
      }
    }
    return -1;
  }
  public static void barrierCreation(Terminal t, Barrier shields) {
    for(int p = 30; p < 35; p++){
      for(int w = 0; w < 100; w++){
        if(shields.barrierExists(w,p)){
          t.moveCursor(w,p);
          t.putCharacter('#');
        }
      }
    }
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
    long lastesecond = 0;
    long lastmil = -500;
    long millis = 0;
    long powerTime = 0;

    //other variables (change later)
		boolean running = true;
    int x = 25; //x-coordinate of middle "="
    int y = 39;//y-coordinate of middle "="
    boolean mover = true;
    boolean doesLose = true;
    int shootTime = 500;
    Random r = new Random();
    boolean toggleInvincible = false;
    int score = 0;
    int level = 1;
    int power = -1;
    Powerup o = new Powerup(1,1,1);

    User user = new User(x,y,3);
    ArrayList<Integer> lasers = new ArrayList<Integer>(); //keeps track of laser coordinates in the form of <x1,y1,x2,y2...>
    ArrayList<Integer> enemyLasers = new ArrayList<Integer>(); // same thing but for enemy lasers
    Barrier shields = new Barrier();
    ArrayList<Enemy> enemies = SpaceInvaders.enemyCreation();

    putString(0,0,terminal,"Press [esc] to exit");
    SpaceInvaders.enemyDrawing(terminal,enemies);
    SpaceInvaders.barrierCreation(terminal,shields);

    while(running){
      if (enemies.size() == 0) {
        enemies = SpaceInvaders.enemyCreation();
        SpaceInvaders.enemyDrawing(terminal,enemies);
        score += 100*level;
        level++;
      }

      putString(x-2,y,terminal,"<===>");
      terminal.moveCursor(x,y-1);
      terminal.putCharacter('^');

      //key input reading and what to do on keypress (player movement/shooting)
      Key key = terminal.readInput();

      if (key != null) {
        if (key.getKind() == Key.Kind.Escape) {//closes program
          terminal.exitPrivateMode();
          System.exit(0);
        }
        if(key.getKind() == Key.Kind.ArrowRight){//moves right
          if (x <= 96) {
            clearLine(38,terminal,size);
            clearLine(39,terminal,size);
            user.move(1);
            x++;
          }
        }
        if(key.getKind() == Key.Kind.ArrowLeft){//moves left
          if (x >= 3) {
            clearLine(38,terminal,size);
            clearLine(39,terminal,size);
            user.move(3);
            x--;
          }
        }
        if (key.getKind() == Key.Kind.ArrowUp && millis - lastmil >= shootTime){//shoots laser upward
          lastmil = System.currentTimeMillis() - tStart;
          lasers.add(user.getXPos());
          lasers.add(user.getYPos()-1);
        }
        if(key.getKind() == Key.Kind.F1){
          toggleInvincible = true;
        }
        if(key.getKind() == Key.Kind.PageUp){
          user.addLife();
        }
        if(key.getKind() == Key.Kind.PageDown){
          user.loseLife();
        }
      }

      long tEnd = System.currentTimeMillis();
      millis = tEnd - tStart;

      //POWERUP CREATION AND MOVEMENT
      if (millis / 70 > powerTime) {
        powerTime = millis / 70;
        if (power == -1) {
          if (r.nextInt(100) < 5) {
            o = new Powerup(99,1,r.nextInt()%5);
            power = 99;
            terminal.moveCursor(99,1);
            terminal.putCharacter('P');
          }
        }
        else {
          terminal.moveCursor(power,1);
          terminal.putCharacter(' ');
          if (power == 10) {
            power = -1;
          }
          else {
            power = o.move();
            terminal.moveCursor(power,1);
            terminal.putCharacter('P');
          }
        }
      }

      //ENEMY SHOOTING CODE
      if (millis/75 > lastSecond) {
        lastSecond = millis/75;
        SpaceInvaders.barrierCreation(terminal,shields);
        for (int p = 0; p < enemies.size(); p++) {
          if (enemies.get(p).isOnEdge(enemies)) {
            if (r.nextInt() % (250/level) == 0) {
              enemyLasers.add(enemies.get(p).getXPos());
              enemyLasers.add(enemies.get(p).getYPos()+1);
            }
          }
        }

        //ENEMY LASER INTERACTIONS
        for (int i = 0; i < enemyLasers.size(); i+=2) {
          terminal.moveCursor(enemyLasers.get(i),enemyLasers.get(i+1));
          terminal.putCharacter(' ');

          //checks if laser is at top or touches barrier (destroys laser if true)
          if (enemyLasers.get(i+1) >= 39 || shields.barrierExists(enemyLasers.get(i),enemyLasers.get(i+1))) {
            shields.destroy(enemyLasers.get(i),enemyLasers.get(i+1));
            enemyLasers.remove(i);
            enemyLasers.remove(i);
            i -= 2;
          }
          else if((enemyLasers.get(i) > x-3 && enemyLasers.get(i) < x+3) && enemyLasers.get(i+1) == y-1 && toggleInvincible == false){
            if (doesLose) {
              user.loseLife();
            }
            enemyLasers.remove(i);
            enemyLasers.remove(i);
            i -= 2;
          }
          else { //moves laser up
            terminal.moveCursor(enemyLasers.get(i),enemyLasers.get(i+1)+1);
            terminal.putCharacter('v');
            enemyLasers.set(i+1,enemyLasers.get(i+1)+1);
          }
        }

        //LASER INTERACTIONS
        for (int i = 0; i < lasers.size(); i+=2) {
          terminal.moveCursor(lasers.get(i),lasers.get(i+1));
          terminal.putCharacter(' ');
          int index = SpaceInvaders.findexOf(enemies,lasers.get(i),lasers.get(i+1));

          //checks if laser is at top or touches barrier (destroys laser if true)
          if (lasers.get(i+1) == 1 || shields.barrierExists(lasers.get(i),lasers.get(i+1)) || index != -1 || ((lasers.get(i+1) == 2 || lasers.get(i+1) == 3 || lasers.get(i+1) == 4) && power == lasers.get(i))) {
            if (lasers.get(i+1) == 2 || lasers.get(i+1) == 3 || lasers.get(i+1) == 4) {
              int tim = o.implement(user,score,shields,shootTime);
              //SpaceInvaders.putString(30,0,terminal,""+tim);
              terminal.moveCursor(power,1);
              terminal.putCharacter(' ');
              power = -1;
            }
            shields.destroy(lasers.get(i),lasers.get(i+1));
            lasers.remove(i);
            lasers.remove(i);
            i -= 2;
            if (index != -1) {
              enemies.remove(index);
              score += 10;
            }
          }
          else { //moves laser up
            terminal.moveCursor(lasers.get(i),lasers.get(i+1)-1);
            terminal.putCharacter('^');
            lasers.set(i+1,lasers.get(i+1)-1);
          }
        }
      }

      //ENEMY MOVEMENT
      if (millis/500 > lastesecond) {
        lastesecond = millis/500;
        if (mover) {
          boolean isRight = false;
          for (int p = 0; p < enemies.size(); p++) {
            terminal.moveCursor(enemies.get(p).getXPos(),enemies.get(p).getYPos());
            terminal.putCharacter(' ');
            if (enemies.get(p).getXPos() == 99) {
              isRight = true;
            }
          }
          if (!isRight) {
            for (int p = 0; p < enemies.size(); p++) {
              enemies.get(p).setXPos(enemies.get(p).getXPos()+1);
            }
          }
          else {
            mover = false;
            for (int p = 0; p < enemies.size(); p++) {
              enemies.get(p).setYPos(enemies.get(p).getYPos()+6);
            }
          }
        }
        else {
          boolean isLeft = false;
          for (int p = 0; p < enemies.size(); p++) {
            terminal.moveCursor(enemies.get(p).getXPos(),enemies.get(p).getYPos());
            terminal.putCharacter(' ');
            if (enemies.get(p).getXPos() == 0) {
              isLeft = true;
            }
          }
          if (!isLeft) {
            for (int p = 0; p < enemies.size(); p++) {
              enemies.get(p).setXPos(enemies.get(p).getXPos()-1);
            }
          }
          else {
            mover = true;
            for (int p = 0; p < enemies.size(); p++) {
              enemies.get(p).setYPos(enemies.get(p).getYPos()+6);
            }
          }
        }

        //draws enemies after move
        for (int p = 0; p < enemies.size(); p++) {
          terminal.moveCursor(enemies.get(p).getXPos(),enemies.get(p).getYPos());
          terminal.putCharacter('E');
        }
      }

      //stuff that goes at the top
      SpaceInvaders.putString(0,2,terminal,"Score: "+score);
      SpaceInvaders.putString(0,1,terminal,"Lives: "+user.getLives());
      SpaceInvaders.putString(0,3,terminal,"Level: "+level);

      if(user.getLives() == 0){
        running = false;
      }
      if (level == 10 && enemies.size() == 0) {
        running = true;
      }
    }
    terminal.clearScreen();
    if (level == 10 && enemies.size() == 0) {
      SpaceInvaders.putString(30,15,terminal,"You won!");
    }
    else {
      SpaceInvaders.putString(30,15,terminal,"You lost!");
    }
    SpaceInvaders.putString(30,20,terminal,"Score = "+score);
    millis = 0;
    long last = System.currentTimeMillis();
    while (millis - last < 2500) {
      millis = System.currentTimeMillis();
    }
    terminal.exitPrivateMode();
  }
}
