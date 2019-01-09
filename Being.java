public abstract class Being implements HasLocation{
  private int health;
  private int attack;
  private int xPos;
  private int yPos;
  public Being(int h, int a, int x, int y) {
    health = h;
    attack = a;
    xPos = x;
    yPos = y;
  }
  public int getXPos() {
    return xPos;
  }
  public int getYPos() {
    return yPos;
  }
  public int getHealth() {
    return health;
  }
  public int getAttack() {
    return attack;
  }
  public void setXPos(int next) {
    xPos = next;
  }
  public void setYPos(int next) {
    yPos = next;
  }
  public void changeHealth(int num) {
    health += num;
  }
  public void changeDamage(int num) {
    attack += num;
  }
  public abstract boolean isDead();
  public void attacked(Laser l) {
    health -= l.getDamage();
  }
  public abstract void move();
  public abstract Laser shoot();
}
