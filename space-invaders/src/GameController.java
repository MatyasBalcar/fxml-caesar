import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.*;

public class GameController {
    @FXML private Canvas gameCanvas;


    public final int maxAmountOfBulletsConst = 30 ;
    public int currentAmountOfBullets = 0;

    private final int sizeX = 275;
    private final int sizeY = 750;

    private int score = 0;

    private GraphicsContext gc;
    private Player player;
    private List<Enemy> enemies = new ArrayList<>();
    private List<Bullet> bullets = new ArrayList<>();
    private Set<KeyCode> keysPressed = new HashSet<>();


    Random rand = new Random();

    @FXML
    public void initialize() {
        gc = gameCanvas.getGraphicsContext2D();

        player = new Player(sizeX, sizeY, 50, 20);

        for (int i = 0; i < 5; i++) {
            enemies.add(new Enemy(100 + i * 80, 100, 40, 20));
        }

        // Wait until the canvas is added to the scene
        javafx.application.Platform.runLater(() -> {
            gameCanvas.getScene().setOnKeyPressed(e -> keysPressed.add(e.getCode()));
            gameCanvas.getScene().setOnKeyReleased(e -> keysPressed.remove(e.getCode()));
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        };
        timer.start();
    }


    private void update() {
        System.out.println(score);
        // Movement
        if (keysPressed.contains(KeyCode.LEFT)) {
            player.move(-5);
        }
        if (keysPressed.contains(KeyCode.RIGHT)) {
            player.move(5);
        }
        if (keysPressed.contains(KeyCode.SPACE) && currentAmountOfBullets < maxAmountOfBulletsConst) {
            bullets.add(new Bullet(player.getX() + 20, player.getY()));
            currentAmountOfBullets++;


        }

        for (Enemy enemy : enemies) {
            enemy.move(1);

        }

        // Update bullets
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.update();
            if (bullet.getY() < 0) removeBullet(bulletIterator);

            for (Enemy enemy : enemies) {
                if (enemy.collidesWith(bullet)) {
                    killEnemy(enemy);
                    removeBullet(bulletIterator);
                    break;
                }
            }
        }
    }

    private void removeBullet (Iterator<Bullet> iterator){
        iterator.remove();
        currentAmountOfBullets--;
    }

    private void killEnemy(Enemy enemy){
        enemies.remove(enemy);
        //Adds a new enemy
        enemies.add(new Enemy(100 + rand.nextInt(4) * 80, 100 + rand.nextInt(8) * 40, 40, 20));
        score ++;

    }

    private void render() {
        gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        // Draw player
        gc.setFill(Color.BLUE);
        gc.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());

        // Draw enemies
        gc.setFill(Color.RED);
        for (Enemy enemy : enemies) {
            gc.fillRect(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
        }

        // Draw bullets
        gc.setFill(Color.YELLOW);
        for (Bullet bullet : bullets) {
            gc.fillRect(bullet.getX(), bullet.getY(), 5, 10);
        }
    }
}
