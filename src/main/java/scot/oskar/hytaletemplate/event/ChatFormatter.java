package scot.oskar.hytaletemplate.event;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import java.awt.Color;

public class ChatFormatter {

  public static void onPlayerChat(PlayerChatEvent event) {
    PlayerRef sender = event.getSender();
    if(event.getContent().equalsIgnoreCase("poo")) {
      event.setCancelled(true);
      sender.sendMessage(Message.raw("Hey, you cannot say that!").color(Color.RED));
    }

    if(event.getContent().equalsIgnoreCase("you stink")) {
      event.setContent("i stink");
    }

    event.setFormatter((ignored, message) ->
        Message.join(
            Message.raw("[COOL] ").color(Color.RED),
            Message.raw(sender.getUsername()).color(Color.YELLOW),
            Message.raw(" : " + message).color(Color.PINK)
        ));

  }
}
