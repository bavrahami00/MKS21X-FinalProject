import java.util.*;
public class Enemy extends Being {
  public Enemy(int x, int y) {
    super(x,y);
  }
  public boolean isOnEdge(ArrayList<Enemy> enemies) {
    Enemy e = new Enemy(getXPos(),getYPos()+2);
    Enemy f = new Enemy(getXPos(),getYPos()+4);
    for (int p = 0; p < enemies.size(); p++) {
      if (enemies.get(p).equals(e) || enemies.get(p).equals(f)) {
        return false;
      }
    }
    return true;
  }
  public boolean isDead() {
    return false;
  }
  public boolean equals(Enemy e) {
    return getXPos() == e.getXPos() && getYPos() == e.getYPos();
  }
}
