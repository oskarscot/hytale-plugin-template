package scot.oskar.hytaletemplate;

import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import javax.annotation.Nonnull;
import scot.oskar.hytaletemplate.commnands.ExampleCommand;
import scot.oskar.hytaletemplate.event.ExampleEvent;

public final class ExamplePlugin extends JavaPlugin {

  public ExamplePlugin(@Nonnull JavaPluginInit init) {
    super(init);
  }

  @Override
  protected void setup() {
    this.getCommandRegistry().registerCommand(new ExampleCommand());
    this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, ExampleEvent::onPlayerReady);
  }
}
