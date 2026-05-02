package com.nohotbaritemname.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

public class NoHotbarItemNameClient implements ClientModInitializer {
	private static final String MOD_NAME = "NoHotbarItemName";
	private static final KeyMapping.Category KEY_CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath("nohotbaritemname", "keybinds"));
	private static boolean enabled = true;
	private static KeyMapping toggleKey;

	public static boolean isEnabled() {
		return enabled;
	}

	@Override
	public void onInitializeClient() {
		toggleKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
			"key.nohotbaritemname.toggle",
			InputConstants.Type.KEYSYM,
			GLFW.GLFW_KEY_UNKNOWN,
			KEY_CATEGORY
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (toggleKey.consumeClick()) {
				enabled = !enabled;
				if (client.player != null) {
					String state = enabled ? "on" : "off";
					client.player.displayClientMessage(Component.literal(MOD_NAME + " (" + state + ")"), false);
				}
			}
		});
	}
}