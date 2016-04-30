package com.bobcat4848.AntiClicker;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

public class ClickEvent implements Listener {

    public static HashMap<String, Integer> clicker = new HashMap<>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (player.hasPermission("ac.bypass") || player.isOp()) {
            return;
        }

        if (e.getAction().equals(Action.LEFT_CLICK_AIR) ||
                e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (clicker.containsKey(player.getName())) {
                clicker.put(player.getName(), clicker.get(player.getName()) + 1);
            } else {
                clicker.put(player.getName(), 1);
            }
        }
    }
}
