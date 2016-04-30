package com.bobcat4848.AntiClicker;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

public class AntiClicker extends JavaPlugin {

    private AntiClicker plugin;
    private int cpsMax;

    public void onEnable() {
        plugin = this;

        saveDefaultConfig();
        cpsMax = getConfig().getInt("cpsMax");

        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
        new BukkitRunnable() {
            public void run() {
                for (Map.Entry<String, Integer> entry : ClickEvent.clicker.entrySet()) {
                    String playerName = entry.getKey();
                    int clicks = entry.getValue();

                    if (clicks > cpsMax) {
                        for (String s : getConfig().getStringList("Commands")) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("{player}", playerName).replace("{cpsMax}", "" + cpsMax));
                        }
                    }
                    ClickEvent.clicker.remove(playerName);
                }
            }
        }.runTaskTimer(plugin, 0, 20);


    }
}
