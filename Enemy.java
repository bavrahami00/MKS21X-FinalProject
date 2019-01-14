import java.util.*;
public class Enemy extends Being {
  public Enemy(int h, int a, int x, int y) {
    super(h,a,x,y);
  }
  public boolean isOnEdge(ArrayList<Enemy> all) {
    for (int x = 0; x < all.size(); x++) {
      if (getXPos() + 1 == all.get(x).getXPos() && getYPos() + 1 == all.get(x).getYPos()) {
        return false;
      }
    }
    return true;
  }
  public boolean isDead() {
    return (getHealth() == 0);
  }
}
