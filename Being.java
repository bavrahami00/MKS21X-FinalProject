public abstract class Being implements HasLocation{
  private int xPos;
  private int yPos;
  public Being(int x, int y) {
    xPos = x;
    yPos = y;
  }
  public int getXPos() {
    return xPos;
  }
  public int getYPos() {
    return yPos;
  }
  public void setXPos(int next) {
    xPos = next;
  }
  public void setYPos(int next) {
    yPos = next;
  }
  public abstract boolean isDead();
}
