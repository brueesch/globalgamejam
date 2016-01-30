package com.ggj.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ggj.model.Background;
import com.ggj.model.Element;
import com.ggj.model.Enemy;
import com.ggj.model.Player;
import com.ggj.model.Rock;

public class GameController implements Screen {

  private ScreenViewport screenviewport;
  private Stage stage;
  private OrthographicCamera camera;
  private Player player;
  private Array<Enemy> enemies;
  private Rock skyscraper;
  private GameInterface game_interface;
  private Background background;

  public GameController(final GlobalGameJam globalgamejam) {
    loadConfigs();
    setUpGame();
    createWorld();
  }

  @Override
  public void show() {
    // TODO Auto-generated method stub

  }

  @Override
  public void render(float delta) {
    float delta_time = Gdx.graphics.getDeltaTime();
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.getBatch().setProjectionMatrix(camera.combined);
    stage.act(delta_time);
    stage.draw();
    game_interface.draw((SpriteBatch) stage.getBatch());
  }

  @Override
  public void resize(int width, int height) {
    // TODO Auto-generated method stub

  }

  @Override
  public void pause() {
    // TODO Auto-generated method stub

  }

  @Override
  public void resume() {
    // TODO Auto-generated method stub

  }

  @Override
  public void hide() {
    // TODO Auto-generated method stub

  }

  @Override
  public void dispose() {
    // TODO Auto-generated method stub

  }

  private void createWorld() {
    background = new Background();
    player = new Player(new Vector2(100, 100));
    enemies = new Array<Enemy>();
    enemies.add(new Enemy(Element.Fire, new Vector2(500, 100)));
    enemies.add(new Enemy(Element.Water, new Vector2(440, 100)));
    stage.addActor(background);
    stage.addActor(player);
    for (Enemy enemy : enemies) {
      stage.addActor(enemy);
    }
    skyscraper = new Rock(GameConfig.SKYSCRAPER_LEVELS,
        GameConfig.SKYSCRAPER_POSITION);
    stage.addActor(skyscraper);
    game_interface = new GameInterface();
  }

  private void setUpGame() {
    screenviewport = new ScreenViewport();
    stage = new ExtendedStageController(screenviewport);
    Gdx.input.setInputProcessor(stage);
    camera = (OrthographicCamera) stage.getCamera();
  }

  private void loadConfigs() {
    new GameConfig();
  }
}
