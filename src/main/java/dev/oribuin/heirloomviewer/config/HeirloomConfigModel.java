package dev.oribuin.heirloomviewer.config;

import dev.oribuin.heirloomviewer.HeirloomViewerMod;
import dev.oribuin.heirloomviewer.util.Heirloom;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.RangeConstraint;
import io.wispforest.owo.ui.core.Color;


@Modmenu(modId = "cyt-heirlooms-viewer")
@Config(
        name = "heirloom-config",
        wrapperName = "HeirloomConfig"
)
public class HeirloomConfigModel {

    public HeirloomOption common = new HeirloomOption(decode("#4e5657"), 200);
    public HeirloomOption uncommon = new HeirloomOption(decode("#69b869"), 200);
    public HeirloomOption rare = new HeirloomOption(decode("#54abd1"), 200);
    public HeirloomOption epic = new HeirloomOption(decode("#a767cf"), 200);
    public HeirloomOption legendary = new HeirloomOption(decode("#fa8532"), 200);

    public static final class HeirloomOption {

        public Color color;

        @RangeConstraint(min = 1, max = 255)
        public int opacity;

        public HeirloomOption(Color color, int opacity) {
            this.color = color;
            this.opacity = opacity;
        }

        public java.awt.Color getAwt() {
            return new java.awt.Color(
                    (int) this.color.red(),
                    (int) this.color.green(),
                    (int) this.color.blue(),
                    this.opacity
            );
        }
    }

    /**
     * yes this is ugly, I KNOW. gets the heirloom config option from the heirloom
     *
     * @param heirloom The provided heirloom
     * @return The returning config if available
     */
    public static java.awt.Color from(Heirloom heirloom) {
        HeirloomOption option = switch (heirloom) {
            case COMMON -> HeirloomViewerMod.CONFIG.common();
            case UNCOMMON -> HeirloomViewerMod.CONFIG.uncommon();
            case RARE -> HeirloomViewerMod.CONFIG.rare();
            case EPIC -> HeirloomViewerMod.CONFIG.epic();
            case LEGENDARY -> HeirloomViewerMod.CONFIG.legendary();
            case null -> null;
        };

        if (option == null) return null;
        return option.getAwt();
    }

    public static io.wispforest.owo.ui.core.Color decode(String hex) {
        try {
            java.awt.Color color = java.awt.Color.decode(hex);
            return new io.wispforest.owo.ui.core.Color(color.getRed(), color.getGreen(), color.getBlue());
        } catch (NumberFormatException ignored) {
            return io.wispforest.owo.braid.core.Color.AQUA.toOwoUi();
        }
    }
}
