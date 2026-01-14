package scot.oskar.hytaletemplate.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import java.awt.Color;
import javax.annotation.Nonnull;
import scot.oskar.hytaletemplate.ExamplePlugin;
import scot.oskar.hytaletemplate.components.PoisonComponent;

public class ExampleCommand extends AbstractPlayerCommand {

  public ExampleCommand() {
    super("test", "Super test command!");
  }

  @Override
  protected void execute(@Nonnull CommandContext commandContext, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
    Player player = store.getComponent(ref, Player.getComponentType());
    PoisonComponent poison = new PoisonComponent(3f, 0.5f, 8);
    store.addComponent(ref, ExamplePlugin.get().getPoisonComponentType(), poison);
    player.sendMessage(Message.raw("You have been poisoned!").color(Color.GREEN).bold(true));
  }

}
