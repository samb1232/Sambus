package com.samb1232.sambus.states;

import com.samb1232.sambus.GamePanel;
import com.samb1232.sambus.entity.Enemy;
import com.samb1232.sambus.entity.Player;
import com.samb1232.sambus.graphics.Font;
import com.samb1232.sambus.graphics.Sprite;
import com.samb1232.sambus.tiles.TileManager;
import com.samb1232.sambus.utils.*;

import java.awt.*;

public class PlayState extends GameState {

    private Font font;
    private Player player;
    private Enemy enemy;
    private TileManager tm;
    private Camera cam;

    public static Vector2f map;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        final int MAP_POS_X = 0;
        final int MAP_POS_Y = 0;
        final int PLAYER_SIZE = 64;
        final int ENEMY_SIZE = 64;

        map = new Vector2f(MAP_POS_X, MAP_POS_Y);
        Vector2f.setWorldVar(map.x, map.y);

        cam = new Camera(new AABB(new Vector2f((GamePanel.width - 800) / 2, (GamePanel.height - 600) / 2), 800, 600));

        tm = new TileManager("tile/sambusTileMap.xml", cam);

        font = new Font("font/font.png", 10, 10);
        player = new Player(new Sprite("entity/linkFormatted.png"),
                new Vector2f(MAP_POS_X + (GamePanel.width / 2) - (PLAYER_SIZE / 2),
                        MAP_POS_Y + (GamePanel.height / 2) - (PLAYER_SIZE / 2)), PLAYER_SIZE);

        enemy = new Enemy(new Sprite("entity/littlegirl.png", 48, 48),
                new Vector2f(MAP_POS_X + (GamePanel.width / 2) - (ENEMY_SIZE / 2) + 150,
                MAP_POS_Y + (GamePanel.height / 2) - (ENEMY_SIZE / 2) + 150), ENEMY_SIZE);

        cam.target(player);
    }

    @Override
    public void update() {
        Vector2f.setWorldVar(map.x, map.y);
        player.update(enemy);
        enemy.update(player);
        cam.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        player.input(key, mouse);
        cam.input(mouse, key);
    }

    @Override
    public void render(Graphics2D g) {
        tm.render(g);
        Sprite.drawArray(g, font, GamePanel.oldFrameCount + " FPS",
                new Vector2f(GamePanel.width - (5 * 32), 16), 32, 32, 22, 0);
        player.render(g);
        enemy.render(g);
        cam.render(g);
    }
}
