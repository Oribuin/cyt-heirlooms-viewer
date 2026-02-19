package dev.oribuin.heirloomviewer.util;

import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public enum Heirloom { // because it is fun to watch it smack the screen?
    COMMON(Color.decode("#4e5657")),
    UNCOMMON(Color.decode("#69b869")),
    RARE(Color.decode("#54abd1")),
    EPIC(Color.decode("#a767cf")),
    LEGENDARY(Color.decode("#fa8532"));

    private final Color color;

    Heirloom(Color color) {
        this.color = color;
    }

    public Color getColor(int opacity) {
        return new Color(
                this.color.getRed(), 
                this.color.getGreen(),
                this.color.getBlue(),
                opacity
        );
    }

    /**
     * Retrieve an heirloom quality from an itemstack
     *
     * @param stack The stack to drop
     * @return The resulting heirloom
     */
    @Nullable
    public static Heirloom getQuality(@Nullable ItemStack stack) {
        if (stack == null) return null;

        ComponentMap componentMap = stack.getComponents();
        if (componentMap.isEmpty()) return null;

        NbtComponent nbtComponent = componentMap.get(DataComponentTypes.CUSTOM_DATA);
        if (nbtComponent == null || nbtComponent.isEmpty()) return null;
        NbtCompound nbtCompound = nbtComponent.copyNbt();

        NbtElement bukkitValues = nbtCompound.get("PublicBukkitValues");
        if (bukkitValues == null) return null;

        String heirloomName = bukkitValues.asCompound()
                .map(x -> x.get("mangoheirlooms:tier"))
                .flatMap(NbtElement::asString)
                .orElse(null);

        if (heirloomName == null) return null;
        for (Heirloom heirloom : Heirloom.values()) {
            if (!heirloomName.equalsIgnoreCase(heirloom.name())) continue;
            return heirloom;
        }

        return null;
    }


}
