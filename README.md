# Hytale Example Plugin

This is an example Hytale plugin, you need to manually include the server jar as a dependency until the maven repo is live.

## Commands

Commands seem to be quite simple following this formar:

```java
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

```

## Events

To register an event you need to use the EventRegistry and use the ```registerGlobal(Class<?> event, Consumer<EventClass> consumer);``` method like this
```java
this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, ExampleEvent::onPlayerReady);
```

```java
public class ExampleEvent {

  public static void onPlayerReady(PlayerReadyEvent event) {
    Player player = event.getPlayer();
    player.sendMessage(Message.raw("Welcome " + player.getDisplayName()));
  }

}

```