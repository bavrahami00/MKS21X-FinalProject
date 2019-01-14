public class Enemy extends Being {
  public Enemy(int h, int a, int x, int y) {
    super(h,a,x,y);
  }
  public boolean isOnEdge(Enemy[] all) {
    for (int x = 0; x < all.length; x++) {
      if (getXPos() + 1 == all[x].getXPos() && getYPos() + 1 == all[x].getYPos()) {
        return false;
      }
    }
    return true;
  }
  public boolean isDead() {
    return (getHealth() == 0);
  }
}
