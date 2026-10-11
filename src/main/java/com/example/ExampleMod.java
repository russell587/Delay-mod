package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback; // not used, kept for compatibility with older loader variants if present
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.resources.Identifier;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	public static final String MOD_ID = "modid";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Delay-mod initialized");

		UseItemCallback.EVENT.register((player, world, hand) -> {
			if (world.isClient) {
				return ActionResult.PASS;
			}

			ItemStack stack = player.getStackInHand(hand);
			if (stack.isOf(Items.END_CRYSTAL)) {
				CrystalDelayController.onCrystalUse((ServerPlayerEntity) player);
			}

			return ActionResult.PASS;
		});

		net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents.AFTER_HURT.register((living, source, damageTaken, handled) -> {
			if (living instanceof PlayerEntity victim) {
				CrystalDelayController.onEntityDamage(victim, source, damageTaken);
			}
		});

		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerWorld world : server.getWorlds()) {
				CrystalDelayController.tick(world);
			}
		});
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
