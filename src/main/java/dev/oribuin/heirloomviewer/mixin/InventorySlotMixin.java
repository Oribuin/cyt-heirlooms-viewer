package dev.oribuin.heirloomviewer.mixin;

import dev.oribuin.heirloomviewer.util.Heirloom;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(AbstractContainerScreen.class)
public class InventorySlotMixin extends Screen {

    protected InventorySlotMixin(Component title) {
        super(title);
    }

    /**
     * Draw an item inside an item with a specified opacity if an heirloom was found
     *
     * @param graphics The drawing context
     * @param slot    The slot the heirloom is found in
     * @param mouseX  The current mouse x position
     * @param mouseY  The current mouse y position
     * @param ci      Whether the event should be canceled
     */
    @Inject(method = "extractSlot", at = @At("HEAD"))
    public void drawSlot(GuiGraphicsExtractor graphics, Slot slot, int mouseX, int mouseY, CallbackInfo ci) {
        ItemStack stack = slot.getItem();
        if (stack.isEmpty()) return;

        Heirloom heirloom = Heirloom.getQuality(stack);
        if (heirloom == null) return;

        Color color = heirloom.getColor();
        if (color == null) return; 

        graphics.fill(slot.x, slot.y, slot.x + 16, slot.y + 16, color.getRGB());
    }

}
