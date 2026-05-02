package com.nohotbaritemname.client.mixin;

import com.nohotbaritemname.client.NoHotbarItemNameClient;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class ExampleClientMixin {
	@Inject(method = "renderSelectedItemName", at = @At("HEAD"), cancellable = true)
	private void nohotbaritemname$cancelHeldItemTooltip(GuiGraphics guiGraphics, CallbackInfo ci) {
		if (NoHotbarItemNameClient.isEnabled()) {
			ci.cancel();
		}
	}
}