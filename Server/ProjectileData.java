public class ProjectileData {
    private int x, y, distanceRemaining = 100, speed = 10;
    boolean isAlive;
    private float angle;

    public ProjectileData(int x, int y, float angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.isAlive = true;
    }

    public void update() {
        // Updates the bullet location and sprite orientation
        int deltaX = (int)(Math.cos(Math.toRadians(this.angle)) * this.speed);
        int deltaY = (int)(Math.sin(Math.toRadians(this.angle)) * this.speed);

        if(--distanceRemaining < 0) {
            this.isAlive = false;
            return;
        }

        this.x += deltaX;
        this.y += deltaY;
    }

    public String toString() {
        return "";
    }
}