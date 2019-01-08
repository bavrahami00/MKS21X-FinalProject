public class User extends Being {
  private int health;
  private int attack;
  private int xPos;
  private int yPos;
  private int lives;
  public User(int h, int a, int x, int y, int l) {
    super(h,a,x,y);
    lives = l;
  }
  public boolean isDead() {
    return (lives == 0 && health == 0);
  }
  public void move() {}
  public void move(int dir) {
    if (dir == 0) {
      yPos--;
    }
    if (dir == 1) {
      xPos++;
    }
    if (dir == 2) {
      yPos++;
    }
    if (dir == 3) {
      xPos--;
    }
  }
  public Laser shoot() {
    Laser l = new Laser(attack,xPos,yPos,this);
    return l;
  }
  public void changeLife(int num) {
    lives += num;
  }
  public void changeDamage(int num) {
    attack += num;
  }
  public void changeHealth(int num) {
    health += num;
  }
}
