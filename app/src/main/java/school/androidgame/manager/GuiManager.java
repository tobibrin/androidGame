package school.androidgame.manager;

import android.graphics.Canvas;

import school.androidgame.GamePanel;
import school.androidgame.gui.NextPlayerColorHudElement;
import school.androidgame.entities.player.Player;
import school.androidgame.gui.Health;
import school.androidgame.gui.PlayerPoints;
import school.androidgame.gui.ToolbarTop;
import school.androidgame.utils.Vector2D;
import school.androidgame.utils.bitmap.colors.BitmapColor;

/**
 * Created by kezab on 28.11.17.
 */

public class GuiManager {
    private GameManager game;
    private PlayerPoints playerPoints;
    private Health health;
    private Player player;
    private ToolbarTop toolbarTop;
    private NextPlayerColorHudElement nextPlayerColorHudElement;

    public GuiManager(GameManager game){
        this.game = game;
        this.player = game.getPlayer();
        this.toolbarTop = new ToolbarTop();

        int toolbarTopCenterPoint = ToolbarTop.HEIGHT / 2;
        this.health = new Health(10, toolbarTopCenterPoint);
        this.playerPoints = new PlayerPoints(GamePanel.WIDTH / 2, toolbarTopCenterPoint);
        this.nextPlayerColorHudElement = new NextPlayerColorHudElement(
                new Vector2D((int) (GamePanel.WIDTH - 10), toolbarTopCenterPoint),
                this.getNextPlayerBitmapColor().getBitmap(), 3000);
    }

    public void update(float dt) {
        this.health.update(this.player.getHealth());
        this.playerPoints.update(this.player.getPoints());
        this.nextPlayerColorHudElement.setPreviewColorBitmap(this.getNextPlayerBitmapColor().getBitmap());
        this.nextPlayerColorHudElement.setTimeLeft(dt);
    }

    public void draw(Canvas canvas) {
        this.toolbarTop.draw(canvas);
        this.health.draw(canvas);
        this.playerPoints.draw(canvas);
        this.nextPlayerColorHudElement.draw(canvas);
    }

    private BitmapColor getNextPlayerBitmapColor() {
        return this.game.getPlayer().getBitmapColorRepository().getBitmapColorAtNextIndex();
    }
}
