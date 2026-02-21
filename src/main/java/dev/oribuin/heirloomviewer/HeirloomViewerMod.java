package dev.oribuin.heirloomviewer;

import dev.oribuin.heirloomviewer.config.HeirloomConfig;
import net.fabricmc.api.ClientModInitializer;

public class HeirloomViewerMod implements ClientModInitializer {

    public static final String MODID = "cyt-heirlooms-viewer";
    public static final HeirloomConfig CONFIG = HeirloomConfig.createAndLoad();

    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {

    }
}
