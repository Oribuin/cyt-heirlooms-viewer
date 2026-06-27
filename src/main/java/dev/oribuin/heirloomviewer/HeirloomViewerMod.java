package dev.oribuin.heirloomviewer;

import dev.oribuin.heirloomviewer.config.SettingsConfig;
import me.lilac.camellia.config.Config;
import me.lilac.camellia.config.ConfigHolder;
import me.lilac.camellia.sync.ConfigSync;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;

public class HeirloomViewerMod implements ClientModInitializer, ConfigHolder {

    public static final String MODID = "cyt-heirlooms-viewer";
    private static HeirloomViewerMod instance;
    private Config config;
    
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        instance = this;
        this.config = this.getOrCreateConfig();
        ConfigSync.init(SettingsConfig.INSTANCE); // Required to sync the config.
    }

    public static HeirloomViewerMod getInstance() {
        return instance;
    }

    @Override
    public Config getOrCreateConfig() {
        if (this.config != null)
            return this.config;

        File folder = FabricLoader.getInstance().getConfigDir().resolve(MODID).toFile();
        folder.mkdirs();

        File file = new File(folder, "heirloom-viewer.json"); // The name of the config file
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return Config.builder(file).settings(SettingsConfig.INSTANCE).build();
    }
}
