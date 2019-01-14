import java.util.*;
public class Enemy extends Being {
  public Enemy(int h, int a, int x, int y) {
    super(h,a,x,y);
  }
  public boolean isOnEdge(ArrayList<Enemy> all) {
    Enemy e = new Enemy(1,1,getXPos(),getYPos()+1);
    return !all.contains(e);
  }
  public boolean isDead() {
    return (getHealth() == 0);
  }
  public boolean equals(Enemy e) {
    return getXPos() == e.getXPos() && getYPos() == e.getYPos();
  }
}
