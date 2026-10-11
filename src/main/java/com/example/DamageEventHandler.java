package com.example;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public final class DamageEventHandler {
    private DamageEventHandler() {
    }

    public static void register() {
        ServerLivingEntityEvents.AFTER_DAMAGE.register((LivingEntity entity, DamageSource source, float amount) -> {
            if (entity instanceof ServerPlayer player) {
                CrystalDelayController.onEntityDamage(player, source, amount);
            }
        });
    }
}
