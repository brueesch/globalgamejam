package com.ggj.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ggj.model.Background;
import com.ggj.model.Element;
import com.ggj.model.Enemy;
import com.ggj.model.Player;
import com.ggj.model.Rock;

public class GameController implements Screen {

  private FitViewport screenviewport;
  private ExtendedStageController stage;
  private OrthographicCamera camera;
  private GameInterface game_interface;
  private Background background;
  private LevelController levelController;
  private Player player;
  private Rock rock;

  public GameController(final GlobalGameJam globalgamejam) {
    loadConfigs();
    setUpGame();
    createWorld();
    startSound();
    startGame();
  }

  @Override
  public void show() {
    // TODO Auto-generated method stub

  }

  @Override
  public void render(float delta) {
    float delta_time = Gdx.graphics.getDeltaTime();
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.getBatch().setProjectionMatrix(camera.combined);
    stage.act(delta_time);
    background.draw(stage.getBatch(), Gdx.graphics.getDeltaTime());
    stage.draw();
    game_interface.draw((SpriteBatch) stage.getBatch());
  }

  @Override
  public void resize(int width, int height) {
    screenviewport.update(width, height);
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
    rock = new Rock(GameConfig.ROCK_LEVELS,
        GameConfig.ROCK_POSITION);
    player = new Player(new Vector2(75, rock.getHeight()+32*GameConfig.SCALE));
    levelController = new LevelController();
    ObjectController.setLevelController(levelController);
    stage.addActor(rock);
    stage.addActor(player);
    game_interface = new GameInterface();
    ObjectController.setInterface(game_interface);
    
    //make window full sized
    Gdx.graphics.setWindowedMode(Gdx.graphics.getDisplayMode().width, Gdx.graphics.getDisplayMode().height);
  }
  
  private void startGame()
  {
    levelController.Reset();
    levelController.Start(1);
  }

  private void setUpGame() {
    screenviewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    screenviewport.apply();
    stage = new ExtendedStageController(screenviewport);
    ObjectController.setStage(stage);
    ObjectController.setViewPort(screenviewport);
    
    Gdx.input.setInputProcessor(stage);
    camera = (OrthographicCamera) stage.getCamera();
  }

  private void loadConfigs() {
    new GameConfig();
  }

  private void startSound() {
    GameSound.BACKGROUND_SOUND.setLooping(true);
    GameSound.BACKGROUND_SOUND.setVolume(0.2f);
    GameSound.BACKGROUND_SOUND.play();
  }
}
