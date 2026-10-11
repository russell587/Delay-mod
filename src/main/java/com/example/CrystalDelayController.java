package com.example;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;

public final class CrystalDelayController {
    private CrystalDelayController() {
    }

    public static void onCrystalUse(ServerPlayer player) {
        // TODO: implement crystal-use delay logic here.
    }

    public static void onEntityDamage(Player victim, DamageSource source, float damageTaken) {
        // TODO: implement damage-triggered delay logic here.
    }

    public static void tick(ServerLevel world) {
        // TODO: implement world tick logic here.
    }
}
