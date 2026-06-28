package dev.oribuin.heirloomviewer.config;

import com.mojang.serialization.Codec;
import dev.oribuin.heirloomviewer.HeirloomViewerMod;
import me.lilac.camellia.config.Setting;
import me.lilac.camellia.config.SettingBuilder;
import me.lilac.camellia.config.SettingHolder;
import me.lilac.camellia.config.Util;
import me.lilac.camellia.input.ColorInput;
import me.lilac.camellia.input.NumberInput;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SettingsConfig implements SettingHolder {

    public static final SettingHolder INSTANCE = new SettingsConfig();
    private static final List<Setting<?>> SETTINGS = new ArrayList<>();

    public static final Setting<Integer> OPACITY = create(
            SettingBuilder.of("background_opacity", Codec.INT, 70)
                    .tab("client")
                    .input(new NumberInput<>(1, 100))
    );

    public static final Setting<Color> COMMON = create(SettingBuilder.of(
                            "heirloom_common", Util.HEX_COLOR_CODEC, Color.decode("#4e5657")
                    )
                    .tab("client")
                    .section("background-colors")
                    .input(new ColorInput<>(false))
    );
    public static final Setting<Color> UNCOMMON = create(SettingBuilder.of(
                            "heirloom_uncommon", Util.HEX_COLOR_CODEC, Color.decode("#69b869")
                    )
                    .tab("client")
                    .section("background-colors")
                    .input(new ColorInput<>(true))
    );
    public static final Setting<Color> RARE = create(SettingBuilder.of(
                            "heirloom_rare", Util.HEX_COLOR_CODEC, Color.decode("#54abd1")
                    )
                    .tab("client")
                    .section("background-colors")
                    .input(new ColorInput<>(false))
    );
    public static final Setting<Color> EPIC = create(SettingBuilder.of(
                            "heirloom_epic", Util.HEX_COLOR_CODEC, Color.decode("#a767cf")
                    )
                    .tab("client")
                    .section("background-colors")
                    .input(new ColorInput<>(false))
    );
    public static final Setting<Color> LEGENDARY = create(SettingBuilder.of(
                            "heirloom_legendary", Util.HEX_COLOR_CODEC, Color.decode("#fa8532")
                    )
                    .tab("client")
                    .section("background-colors")
                    .input(new ColorInput<>(false))
    );

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