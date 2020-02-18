/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.util;

import eu.mcone.gameapi.api.GameAPI;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.HotbarItems;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerHiderManager implements eu.mcone.lobby.api.player.PlayerHiderManager {

    public ArrayList<Player> players;
    private HashMap<String, Long> time;

    public PlayerHiderManager() {
        this.players = new ArrayList<>();
        this.time = new HashMap<>();
    }

    @Override
    public void hidePlayers(Player p) {
        if (time.containsKey(p.getName())) {
            long diff = (System.currentTimeMillis() - time.get(p.getName())) / 10L / 60L;
            int cooldown = 1;
            if (diff < cooldown) {
                LobbyPlugin.getInstance().getMessager().send(p, "§7Du musst kurz warte um den Player hider wieder benutzen zu können");
                return;
            }
        }

        for (Player all : Bukkit.getOnlinePlayers()) {
            p.hidePlayer(all);
        }

        players.add(p);

        GameAPI.getInstance().getGamePlayer(p).setEffectsVisible(false);
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
        p.playSound(p.getLocation(), Sound.LAVA_POP, 1.0F, 1.0F);
        p.playEffect(p.getLocation(), Effect.FIREWORKS_SPARK, 1);
        p.getInventory().setItem(0, HotbarItems.SHOW_PLAYERS);
        LobbyPlugin.getInstance().getMessager().send(p, "§7Du siehst nun §ckeine §7Spieler mehr.");
        time.put(p.getName(), System.currentTimeMillis());
    }

    @Override
    public void showPlayers(Player p) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            p.showPlayer(all);
        }

        players.remove(p);

        GameAPI.getInstance().getGamePlayer(p).setEffectsVisible(true);
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
        p.playSound(p.getLocation(), Sound.LAVA_POP, 1.0F, 1.0F);
        p.playEffect(p.getLocation(), Effect.FIREWORKS_SPARK, 1);
        p.getInventory().setItem(0, HotbarItems.HIDE_PLAYERS);
        LobbyPlugin.getInstance().getMessager().send(p, "§7Du siehst nun §aalle §7Spieler wieder.");
        time.put(p.getName(), System.currentTimeMillis());
    }

    @Override
    public void playerJoined(Player j) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (players.contains(j)) {
                j.hidePlayer(p);
            }
        }
    }

    @Override
    public boolean isHidden(Player p) {
        return players.contains(p);
    }

}
