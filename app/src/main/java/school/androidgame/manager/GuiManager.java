package school.androidgame.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import school.androidgame.Entities.Player;
import school.androidgame.GamePanel;
import school.androidgame.Gui.Health;
import school.androidgame.Gui.NextPlayerColorHudElement;
import school.androidgame.Gui.PlayerPoints;
import school.androidgame.Gui.ToolbarTop;
import school.androidgame.Utils.Vector2D;
import school.androidgame.Utils.bitmap.colors.BitmapColor;

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

    public GuiManager(GameManager game) {
        this.game = game;
        this.player = game.player;
        this.toolbarTop = new ToolbarTop();

        int toolbarTopHeight = this.toolbarTop.getHeight();
        int toolbarTopCenterPoint = toolbarTopHeight / 2;
        this.health = new Health(10, toolbarTopCenterPoint);
        this.playerPoints = new PlayerPoints(GamePanel.WIDTH / 2, toolbarTopCenterPoint);
        this.nextPlayerColorHudElement = new NextPlayerColorHudElement(
                new Vector2D((int) (GamePanel.WIDTH * 0.75), toolbarTopCenterPoint),
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
        return this.game.player.getBitmapColorRepository().getBitmapColorAtNextIndex();
    }
}
