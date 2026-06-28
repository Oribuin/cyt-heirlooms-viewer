package dev.oribuin.heirloomviewer.config;

import dev.oribuin.heirloomviewer.HeirloomViewerMod;
import me.lilac.camellia.config.Setting;
import me.lilac.camellia.config.SettingHolder;
import me.lilac.camellia.screen.ConfigScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.PlaceOnWaterBlockItem;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class SettingsScreen extends ConfigScreen {

    public SettingsScreen(Screen parent) {
        super(parent, HeirloomViewerMod.MOD_ID);
    }

    @Override
    public SettingHolder getClientSettings() {
        return SettingsConfig.INSTANCE;
    }

    @Override
    public SettingHolder getServerSettings() {
        return new EmptySettingsHolder();
    }
    
    public static class EmptySettingsHolder implements SettingHolder {

        @Override
        public List<Setting<?>> get() {
            return new ArrayList<>();
        }

        @Override
        public void save() {
        }

        @Override
        public void reload() {
        }
    }

}