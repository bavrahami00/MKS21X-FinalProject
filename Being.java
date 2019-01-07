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
  public abstract boolean isDead();
  public void attacked(Laser l) {
    health -= l.getDamage();
  }
  public abstract void move();
  public abstract Laser shoot();
}
