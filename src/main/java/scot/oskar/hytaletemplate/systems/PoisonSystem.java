package scot.oskar.hytaletemplate.systems;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.SystemGroup;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.server.core.modules.entity.damage.Damage;
import com.hypixel.hytale.server.core.modules.entity.damage.DamageCause;
import com.hypixel.hytale.server.core.modules.entity.damage.DamageModule;
import com.hypixel.hytale.server.core.modules.entity.damage.DamageSystems;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import scot.oskar.hytaletemplate.components.PoisonComponent;

public class PoisonSystem extends EntityTickingSystem<EntityStore> {

  private final ComponentType<EntityStore, PoisonComponent> poisonComponentType;

  public PoisonSystem(ComponentType<EntityStore, PoisonComponent> poisonComponentType) {
    this.poisonComponentType = poisonComponentType;
  }

  @Override
  public void tick(float dt, int index, @Nonnull ArchetypeChunk<EntityStore> archetypeChunk,
      @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer) {

    PoisonComponent poison = archetypeChunk.getComponent(index, poisonComponentType);
    Ref<EntityStore> ref = archetypeChunk.getReferenceTo(index);

    poison.addElapsedTime(dt);

    if (poison.getElapsedTime() >= poison.getTickInterval()) {
      poison.resetElapsedTime();

      Damage damage = new Damage(Damage.NULL_SOURCE, DamageCause.OUT_OF_WORLD, poison.getDamagePerTick());
      DamageSystems.executeDamage(ref, commandBuffer, damage);

      poison.decrementRemainingTicks();
    }

    if (poison.isExpired()) {
      commandBuffer.removeComponent(ref, poisonComponentType);
    }
  }

  @Nullable
  @Override
  public SystemGroup<EntityStore> getGroup() {
    return DamageModule.get().getGatherDamageGroup();
  }

  @Nonnull
  @Override
  public Query<EntityStore> getQuery() {
    return Query.and(this.poisonComponentType);
  }
}