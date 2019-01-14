public class User extends Being {
  private int lives;
  public User(int h, int a, int x, int y, int l) {
    super(h,a,x,y);
    lives = l;
  }
  public boolean isDead() {
    return (lives == 0 && getHealth() == 0);
  }
  public void move(int dir) {
    if (dir == 1) {
      setXPos(getXPos()+1);
    }
    if (dir == 3) {
      setXPos(getXPos()-1);
    }
  }
  public void changeLife(int num) {
    lives += num;
  }
}
