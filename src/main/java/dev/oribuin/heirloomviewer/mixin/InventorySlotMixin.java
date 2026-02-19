package dev.oribuin.heirloomviewer.mixin;

import dev.oribuin.heirloomviewer.util.Heirloom;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HandledScreen.class)
public class InventorySlotMixin extends Screen {

    protected InventorySlotMixin(Text title) {
        super(title);
    }

    /**
     * Draw an item inside an item with a specified opacity if an heirloom was found
     *
     * @param context The drawing context
     * @param slot    The slot the heirloom is found in
     * @param mouseX  The current mouse x position
     * @param mouseY  The current mouse y position
     * @param ci      Whether the event should be canceled
     */
    @Inject(method = "drawSlot", at = @At("HEAD"))
    public void drawSlot(DrawContext context, Slot slot, int mouseX, int mouseY, CallbackInfo ci) {
        ItemStack stack = slot.getStack();
        if (stack.isEmpty()) return;

        Heirloom heirloom = Heirloom.getQuality(stack);
        if (heirloom == null) return;

//        Color color = HeirloomConfig.from(heirloom);
//        if (color == null) return; 

        context.fill(slot.x, slot.y, slot.x + 16, slot.y + 16, heirloom.getColor(180).getRGB());
    }

}
