package com.samb1232.sambus.states;

import com.samb1232.sambus.GamePanel;
import com.samb1232.sambus.entity.Player;
import com.samb1232.sambus.graphics.Font;
import com.samb1232.sambus.graphics.Sprite;
import com.samb1232.sambus.tiles.TileManager;
import com.samb1232.sambus.utils.KeyHandler;
import com.samb1232.sambus.utils.MouseHandler;
import com.samb1232.sambus.utils.Vector2f;

import java.awt.*;

public class PlayState extends GameState {

    private Font font;
    private Player player;
    private TileManager tm;

    public static Vector2f map;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        final int MAP_POS_X = 0;
        final int MAP_POS_Y = 0;
        final int PLAYER_SIZE = 64;

        map = new Vector2f(MAP_POS_X, MAP_POS_Y);
        Vector2f.setWorldVar(map.x, map.y);

        tm = new TileManager("tile/sambusTileMap.xml");

        font = new Font("font/font.png", 10, 10);
        player = new Player(new Sprite("entity/linkFormatted.png"),
                new Vector2f(MAP_POS_X + (GamePanel.width / 2) - (PLAYER_SIZE / 2),
                        MAP_POS_Y + (GamePanel.height / 2) - (PLAYER_SIZE / 2)), PLAYER_SIZE);
    }

    @Override
    public void update() {
        Vector2f.setWorldVar(map.x, map.y);
        player.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        player.input(key, mouse);
    }

    @Override
    public void render(Graphics2D g) {
        tm.render(g);
        Sprite.drawArray(g, font, GamePanel.oldFrameCount + " FPS",
                new Vector2f(GamePanel.width - (5 * 32), 16), 32, 32, 22, 0);
        player.render(g);
    }
}
