package scot.oskar.hytaletemplate;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import javax.annotation.Nonnull;
import scot.oskar.hytaletemplate.commands.ExampleCommand;
import scot.oskar.hytaletemplate.components.PoisonComponent;
import scot.oskar.hytaletemplate.event.ChatFormatter;
import scot.oskar.hytaletemplate.event.ExampleEvent;
import scot.oskar.hytaletemplate.systems.PoisonSystem;

public final class ExamplePlugin extends JavaPlugin {

  private static ExamplePlugin instance;
  private ComponentType<EntityStore, PoisonComponent> poisonComponent;

  public ExamplePlugin(@Nonnull JavaPluginInit init) {
    super(init);
    instance = this;
  }

  @Override
  protected void setup() {
    this.getCommandRegistry().registerCommand(new ExampleCommand());
    this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, ExampleEvent::onPlayerReady);
    this.getEventRegistry().registerGlobal(PlayerChatEvent.class, ChatFormatter::onPlayerChat);

    this.poisonComponent = this.getEntityStoreRegistry()
        .registerComponent(PoisonComponent.class, PoisonComponent::new);
    this.getEntityStoreRegistry().registerSystem(new PoisonSystem(this.poisonComponent));
  }

  public ComponentType<EntityStore, PoisonComponent> getPoisonComponentType() {
    return poisonComponent;
  }

  public static ExamplePlugin get() {
    return instance;
  }
}
