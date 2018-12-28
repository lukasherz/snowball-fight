/*
 * Copyright (c) 2018. Lukas Herz
 */

package de.lukasherz.snowballFight;

import de.lukasherz.snowballFight.events.SnowballMovesListener;
import de.maltekp.json.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class SnowballFight extends JavaPlugin {
    private SnowballFight plugin;
    private SnowballMovesListener snowballMovesListener;
    private JsonFile jsonFile = new JsonFile(this.getDataFolder().getAbsolutePath() + File.separator + "config.json");

    @Override
    public void onEnable() {
        plugin = this;

        createConfig();

        snowballMovesListener = new SnowballMovesListener(jsonFile);

        Bukkit.getPluginManager().registerEvents(snowballMovesListener, plugin);
    }

    public void createConfig() {
        if (jsonFile.read() == null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addDouble("velocity_factor", 0.3);
            jsonObject.addBoolean("headshot", true);

            jsonFile.saveJsonObject(jsonObject);
        }
    }
}
