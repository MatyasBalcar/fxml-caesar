public class Enemy {
    private double x, y, width, height;

    private final double speedConstant = 0.25;

    public Enemy(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean collidesWith(Bullet bullet) {
        return bullet.getX() < x + width &&
                bullet.getX() + 5 > x &&
                bullet.getY() < y + height &&
                bullet.getY() + 10 > y;
    }

    public void move(int dy){
        y += dy * speedConstant;

    }

    // Getters
    public double getX() { return x; }
    public double getY() { return y; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
}
