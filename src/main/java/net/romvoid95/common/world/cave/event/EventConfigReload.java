package net.romvoid95.common.world.cave.event;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.romvoid95.common.world.cave.config.BCSettings;

/**
 * Synchronize changes to the config.
 * Should be registered to the {@code EVENT_BUS} on the client side.
 */
public class EventConfigReload {
    /**
     * Keeps Better Caves config settings synchronized
     */
    @SubscribeEvent
    public void onConfigReload(ConfigChangedEvent.OnConfigChangedEvent event) {
        // Only mess with config syncing if it is this mod being changed
        if (BCSettings.MOD_ID.equals(event.getModID()))
            ConfigManager.sync(BCSettings.MOD_ID, Config.Type.INSTANCE);
    }


}
