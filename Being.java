public abstract class Being implements HasLocation{
  private int health;
  private Laser attack;
  private int xPos;
  private int yPos;
  public Being(int h, Laser l, int x, int y) {
    health = h;
    attack = l;
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
  public abstract void attack(Laser l);
  public abstract void move();
  public abstract Laser shoot();
}
