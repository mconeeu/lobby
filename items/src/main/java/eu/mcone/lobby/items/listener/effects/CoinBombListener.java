/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.listener.effects;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CoinBombListener implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent e) {
        if (e.hasItem() && e.getItem().equals(Item.COINBOMB.getItemStack()) && (e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_BLOCK))) {
            Player p = e.getPlayer();
            LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

            p.sendMessage("§7Du hast die Coin Bombe erfolgreich gezündet");
            lp.removeItem(Item.COINBOMB);
            p.getInventory().remove(p.getItemInHand());

            Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () ->
                    p.sendMessage("§8[§7§l!§8] §fServer §8» §eEine Coin Bombe wurde von " + p.getName() + " gezündet sie startet in §43 Sekunden"), 20L);

            Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () ->
                    p.sendMessage("§8[§7§l!§8] §fServer §8» §eEine Coin Bombe wurde von " + p.getName() + " gezündet sie startet in §42 Sekunden"), 40L);

            Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () ->
                    p.sendMessage("§8[§7§l!§8] §fServer §8» §eEine Coin Bombe wurde von " + p.getName() + " gezündet sie startet in §41 Sekunden"), 60L);

            Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
                    player.playEffect(p.getLocation(), Effect.LAVA_POP, 1);
                    player.playEffect(p.getLocation(), Effect.LAVA_POP, 2);
                    player.sendMessage("§8[§7§l!§8] §fServer §8» §eDie Coinbome ist explodiert jeder bekommnt §5§l70 Coins");

                    CoreSystem.getInstance().getCorePlayer(player).addCoins(70);

                    lp.removeItem(Item.COINBOMB);
                    p.getInventory().remove(p.getItemInHand());
                }
            }, 80L);
        }
    }

}
