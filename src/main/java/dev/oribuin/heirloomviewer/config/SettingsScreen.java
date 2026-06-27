package dev.oribuin.heirloomviewer.config;

import dev.oribuin.heirloomviewer.HeirloomViewerMod;
import me.lilac.camellia.config.SettingHolder;
import me.lilac.camellia.screen.ConfigScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.Screen;

@Environment(EnvType.CLIENT)
public class SettingsScreen extends ConfigScreen {

    public SettingsScreen(Screen parent) {
        super(parent, HeirloomViewerMod.MODID);
    }

    @Override
    public SettingHolder getClientSettings() {
        return SettingsConfig.INSTANCE;
    }

    @Override
    public SettingHolder getServerSettings() {
        return SettingsConfig.INSTANCE;
    }

}