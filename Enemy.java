import java.util.*;
public class Enemy extends Being {
  public Enemy(int h, int a, int x, int y) {
    super(h,a,x,y);
  }
  public boolean isOnEdge(ArrayList<Enemy> enemies) {
    Enemy e = new Enemy(1,1,getXPos(),getYPos()+2);
    Enemy f = new Enemy(1,1,getXPos(),getYPos()+4);
    for (int p = 0; p < enemies.size(); p++) {
      if (enemies.get(p).equals(e) || enemies.get(p).equals(f)) {
        return false;
      }
    }
    return true;
  }
  public boolean isDead() {
    return (getHealth() == 0);
  }
  public boolean equals(Enemy e) {
    return getXPos() == e.getXPos() && getYPos() == e.getYPos();
  }
}
