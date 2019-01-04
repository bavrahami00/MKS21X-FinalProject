public class User extends Being {
  private int lives;
  public User(int h, int a, int x, int y, int l) {
    super(h,a,x,y);
    lives = l;
  }
  public boolean isDead() {
    return (lives == 0 && health == 0);
  }
  public void move() {}
  public Laser shoot() {
    Laser l = new Laser(attack,xPos,yPos,this);
    return l;
  }
}
