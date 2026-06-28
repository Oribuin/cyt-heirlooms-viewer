package dev.oribuin.heirloomviewer.util;

import dev.oribuin.heirloomviewer.config.SettingsConfig;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.awt.*;
import java.util.function.Supplier;

public enum Heirloom { // because it is fun to watch it smack the screen?
    COMMON(SettingsConfig.COMMON::get, Color .decode("#4e5657")), 
    UNCOMMON(SettingsConfig.UNCOMMON::get, Color.decode("#69b869")), 
    RARE(SettingsConfig.RARE::get, Color.decode("#54abd1")), 
    EPIC(SettingsConfig.EPIC::get, Color.decode("#a767cf")), 
    LEGENDARY(SettingsConfig.LEGENDARY::get, Color.decode("#fa8532"));

    private final @NotNull Supplier<@Nullable Color> supplier;
    private final @NotNull Color backup;

    Heirloom(@NonNull Supplier<@Nullable Color> supplier, @NotNull Color backup) {
        this.supplier = supplier;
        this.backup = backup;
    }

    public Color getColor() {
        Color retrieved = this.supplier.get();
        if (retrieved == null) retrieved = this.backup;
        
        try {
            return new Color(retrieved.getRed(), retrieved.getGreen(), retrieved.getBlue(), retrieved.getAlpha());
        } catch (IllegalArgumentException ex) {
            System.out.printf("Failed to get color[%s,%s,%s %s]:" + ex.getMessage() + "\n", retrieved.getRed(), retrieved.getGreen(), retrieved.getBlue(), 70);
            return null;
        }
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

        DataComponentMap componentMap = stack.getComponents();
        if (componentMap.isEmpty()) return null;

        CustomData nbtComponent = componentMap.get(DataComponents.CUSTOM_DATA);
        if (nbtComponent == null || nbtComponent.isEmpty()) return null;
        CompoundTag nbtCompound = nbtComponent.copyTag();
        
        Tag bukkitValues = nbtCompound.get("PublicBukkitValues");
        if (bukkitValues == null) return null;

        String heirloomName = bukkitValues.asCompound().map(x -> x.get("mangoheirlooms:tier")).flatMap(Tag::asString).orElse(null);

        if (heirloomName == null) return null;
        for (Heirloom heirloom : Heirloom.values()) {
            if (!heirloomName.equalsIgnoreCase(heirloom.name())) continue;
            return heirloom;
        }

        return null;
    }


}
