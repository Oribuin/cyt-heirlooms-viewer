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

    public static final Setting<Double> OPACITY = create(
            SettingBuilder.of("background_opacity", Codec.DOUBLE, 70.0)
                    .input(new NumberInput<>(1, 100))
    );

    //     COMMON(Color.decode("#4e5657")), 
    //    UNCOMMON(Color.decode("#69b869")), 
    //    RARE(Color.decode("#54abd1")), 
    //    EPIC(Color.decode("#a767cf")), 
    //    LEGENDARY(Color.decode("#fa8532"));
    public static final Setting<Color> COMMON = create(SettingBuilder.of(
                            "heirloom_common", Util.HEX_COLOR_CODEC, Color.decode("#4e5657")
                    )
                    .section("background-colors")
                    .input(new ColorInput<>(false))
    );
    public static final Setting<Color> UNCOMMON = create(SettingBuilder.of(
                            "heirloom_uncommon", Util.HEX_COLOR_CODEC, Color.decode("#69b869")
                    )
                    .section("background-colors")
                    .input(new ColorInput<>(false))
    );
    public static final Setting<Color> RARE = create(SettingBuilder.of(
                            "heirloom_rare", Util.HEX_COLOR_CODEC, Color.decode("#54abd1")
                    )
                    .section("background-colors")
                    .input(new ColorInput<>(false))
    );
    public static final Setting<Color> EPIC = create(SettingBuilder.of(
                            "heirloom_epic", Util.HEX_COLOR_CODEC, Color.decode("#a767cf")
                    )
                    .section("background-colors")
                    .input(new ColorInput<>(false))
    );
    public static final Setting<Color> LEGENDARY = create(SettingBuilder.of(
                            "heirloom_legendary", Util.HEX_COLOR_CODEC, Color.decode("#fa8532")
                    )
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