package com.ggj.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ggj.model.ActorBase;

public class ExtendedStageController extends Stage {
  public ExtendedStageController(Viewport viewport)
  {
    super(viewport);
  }

  @Override
  public void addActor(Actor actor)
  {
    super.addActor(actor);
    if(actor instanceof ActorBase)
    {
      ActorBase actorBase = (ActorBase)actor;
      int id = ObjectController.register(actorBase);
      actorBase.setId(id);
    }
  }
  
  void removeActor(ActorBase actor)
  {
    ObjectController.unregister(actor);
    actor = null;
  }
}
