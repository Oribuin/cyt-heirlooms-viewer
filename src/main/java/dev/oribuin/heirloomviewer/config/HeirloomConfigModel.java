package dev.oribuin.heirloomviewer.config;

import dev.oribuin.heirloomviewer.HeirloomViewerMod;
import dev.oribuin.heirloomviewer.util.Heirloom;
import io.netty.util.IllegalReferenceCountException;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.RangeConstraint;
import io.wispforest.owo.ui.core.Color;

import java.awt.*;


@Modmenu(modId = "cyt-heirlooms-viewer")
@Config(
        name = "heirloom-config",
        wrapperName = "HeirloomConfig"
)
public class HeirloomConfigModel {

    @RangeConstraint(min = 1, max = 100)
    public int opacity = 70;
    public Color common = Color.ofRgb(0x4e5657);
    public Color uncommon = Color.ofRgb(0x69b869);
    public Color rare = Color.ofRgb(0x54abd1);
    public Color epic = Color.ofRgb(0xa767cf);
    public Color legendary = Color.ofRgb(0xfa8532);
    
    /**
     * yes this is ugly, I KNOW. gets the heirloom config option from the heirloom
     *
     * @param heirloom The provided heirloom
     * @return The returning config if available
     */
    public static java.awt.Color from(Heirloom heirloom) {
        Color option = switch (heirloom) {
            case COMMON -> HeirloomViewerMod.CONFIG.common();
            case UNCOMMON -> HeirloomViewerMod.CONFIG.uncommon();
            case RARE -> HeirloomViewerMod.CONFIG.rare();
            case EPIC -> HeirloomViewerMod.CONFIG.epic();
            case LEGENDARY -> HeirloomViewerMod.CONFIG.legendary();
            case null -> null;
        };

        if (option == null) return null;
        
        try {
            return new java.awt.Color(option.red(), option.green(), option.blue(), (float) HeirloomViewerMod.CONFIG.opacity() / 100);
        } catch (IllegalArgumentException ex) {
            return new java.awt.Color(option.red(), option.green(), option.blue());
        }
    }

}
