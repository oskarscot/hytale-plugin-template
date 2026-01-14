package scot.oskar.hytaletemplate.components;

import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import javax.annotation.Nullable;

public class PoisonComponent implements Component<EntityStore> {

  private float damagePerTick;
  private float tickInterval;
  private int remainingTicks;
  private float elapsedTime;

  public PoisonComponent() {
    this(5f, 1.0f, 10);
  }

  public PoisonComponent(float damagePerTick, float tickInterval, int totalTicks) {
    this.damagePerTick = damagePerTick;
    this.tickInterval = tickInterval;
    this.remainingTicks = totalTicks;
    this.elapsedTime = 0f;
  }

  public PoisonComponent(PoisonComponent other) {
    this.damagePerTick = other.damagePerTick;
    this.tickInterval = other.tickInterval;
    this.remainingTicks = other.remainingTicks;
    this.elapsedTime = other.elapsedTime;
  }

  @Nullable
  @Override
  public Component<EntityStore> clone() {
    return new PoisonComponent(this);
  }

  public float getDamagePerTick() {
    return damagePerTick;
  }

  public float getTickInterval() {
    return tickInterval;
  }

  public int getRemainingTicks() {
    return remainingTicks;
  }

  public float getElapsedTime() {
    return elapsedTime;
  }

  public void addElapsedTime(float dt) {
    this.elapsedTime += dt;
  }

  public void resetElapsedTime() {
    this.elapsedTime = 0f;
  }

  public void decrementRemainingTicks() {
    this.remainingTicks--;
  }

  public boolean isExpired() {
    return this.remainingTicks <= 0;
  }
}