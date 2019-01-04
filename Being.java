public abstract class Being {
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
  public abstract boolean isDead();
  public abstract void attack(Laser l);
  public abstract void move();
  public abstract Laser shoot();
}
