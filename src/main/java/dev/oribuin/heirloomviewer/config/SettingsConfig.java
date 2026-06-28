package dev.oribuin.heirloomviewer.config;

import dev.oribuin.heirloomviewer.HeirloomViewerMod;
import me.lilac.camellia.config.Setting;
import me.lilac.camellia.config.SettingBuilder;
import me.lilac.camellia.config.SettingHolder;
import me.lilac.camellia.config.Util;
import me.lilac.camellia.input.ColorInput;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SettingsConfig implements SettingHolder {

    public static final SettingHolder INSTANCE = new SettingsConfig();
    private static final List<Setting<?>> SETTINGS = new ArrayList<>();

    public static final Setting<Color> COMMON = create(SettingBuilder.of(
                            "heirloom_common", Util.HEX_COLOR_CODEC, decode("#4e5657", 178)
                    )
                    .tab("client")
                    .section("background-colors")
                    .input(new ColorInput<>(true))
    );
    public static final Setting<Color> UNCOMMON = create(SettingBuilder.of(
                            "heirloom_uncommon", Util.HEX_COLOR_CODEC, decode("#69b869", 178)
                    )
                    .tab("client")
                    .section("background-colors")
                    .input(new ColorInput<>(true))
    );
    public static final Setting<Color> RARE = create(SettingBuilder.of(
                            "heirloom_rare", Util.HEX_COLOR_CODEC, decode("#54abd1", 178)
                    )
                    .tab("client")
                    .section("background-colors")
                    .input(new ColorInput<>(true))
    );
    public static final Setting<Color> EPIC = create(SettingBuilder.of(
                            "heirloom_epic", Util.HEX_COLOR_CODEC, decode("#a767cf", 178)
                    )
                    .tab("client")
                    .section("background-colors")
                    .input(new ColorInput<>(true))
    );
    public static final Setting<Color> LEGENDARY = create(SettingBuilder.of(
                            "heirloom_legendary", Util.HEX_COLOR_CODEC, decode("#fa8532", 178)
                    )
                    .tab("client")
                    .section("background-colors")
                    .input(new ColorInput<>(true))
    );

    /**
     * Decode a color with an assigned opacity
     *
     * @param hex   The hex code
     * @param alpha The alpha number
     * @return The color with assigned alpha
     */
    private static Color decode(String hex, int alpha) {
        Color decode = Color.decode(hex);
        return new Color(decode.getRed(), decode.getGreen(), decode.getBlue(), alpha);
    }

    @Override
    public List<Setting<?>> get() {
        return Collections.unmodifiableList(SETTINGS);
    }

    private static <T> Setting<T> create(Setting.Builder<T> builder) {
        Setting<T> setting = builder.build(HeirloomViewerMod.getInstance()); // Replace with your mod instance.
        SETTINGS.add(setting);
        return setting;
    }

    @Override
    public void save() {
        HeirloomViewerMod.getInstance().getOrCreateConfig().save(); // Replace with your main class.
    }

    @Override
    public void reload() {
        HeirloomViewerMod.getInstance().getOrCreateConfig().reload(); // Replace with your main class.
    }
}