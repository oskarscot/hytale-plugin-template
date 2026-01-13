package scot.oskar.hytaletemplate.commands;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.util.EventTitleUtil;
import javax.annotation.Nonnull;

public class ExampleCommand extends CommandBase {

  public ExampleCommand() {
    super("test", "Super test command!");
  }

  @Override
  protected void executeSync(@Nonnull CommandContext commandContext) {
    Player player = commandContext.senderAs(Player.class);
    player.getWorld().execute(() -> {
      EventTitleUtil.showEventTitleToPlayer(player.getPlayerRef(), Message.raw("It's modded!"), Message.raw("Yeppers"), true);
    });
  }
}
