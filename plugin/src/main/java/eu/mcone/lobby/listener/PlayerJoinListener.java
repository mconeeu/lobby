/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.event.LabyModPlayerJoinEvent;
import eu.mcone.coresystem.api.bukkit.gamemode.Gamemode;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.coresystem.api.bukkit.npc.NPC;
import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.util.CoreActionBar;
import eu.mcone.coresystem.api.core.labymod.LabyModEmote;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.event.LobbyPlayerLoadedEvent;
import eu.mcone.lobby.api.player.HotbarItems;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.items.manager.OfficeManager;
import eu.mcone.lobby.util.PlayerHider;
import eu.mcone.lobby.util.SidebarObjective;
import eu.mcone.lobby.util.SilentLobbyUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerJoinListener implements Listener {

    private final static CoreActionBar LOADING_MSG = CoreSystem.getInstance().createActionBar().message("§7§oDeine Daten werden geladen...");
    private final static CoreActionBar LOADING_SUCCESS_MSG = CoreSystem.getInstance().createActionBar().message("§2§oDeine Daten wurden geladen!");

    @EventHandler
    public void on(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        e.setJoinMessage(null);
        preloadLobbyPlayer(p, CoreSystem.getInstance().getCorePlayer(p.getUniqueId()));

        LobbyPlayer lp = new LobbyPlayer(CoreSystem.getInstance().getCorePlayer(p));
        LobbyPlugin.getInstance().registerGamePlayer(lp);

        PlayerHider.playerJoined(p);

        p.playEffect(p.getLocation(), org.bukkit.Effect.HAPPY_VILLAGER, 5);
        p.playSound(p.getLocation(), Sound.FIREWORK_TWINKLE, 2.0F, 5.0F);

        loadLobbyPlayer(p, lp, LobbyPlayerLoadedEvent.Reason.JOINED);
        p.setWalkSpeed(0.2F);
    }

    public static void preloadLobbyPlayer(Player p, CorePlayer cp) {
        LOADING_MSG.send(p);
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 0));

        setLobbyItems(p);

        cp.getScoreboard().setNewObjective(new SidebarObjective());
    }

    public static void loadLobbyPlayer(Player p, LobbyPlayer lp, LobbyPlayerLoadedEvent.Reason reason) {
        Bukkit.getScheduler().runTask(LobbyPlugin.getInstance(), () -> {
            Bukkit.getPluginManager().callEvent(new LobbyPlayerLoadedEvent(lp, reason));
            LOADING_SUCCESS_MSG.send(p);

            if (reason.equals(LobbyPlayerLoadedEvent.Reason.JOINED)) {
                switch (lp.getSettings().getSpawnPoint()) {
                    case SILENT_LOBBY: {
                        SilentLobbyUtils.activateSilentLobby(p);
                        LobbyPlugin.getInstance().getMessager().send(p, "§2Du bist in der §aPrivaten Lobby§2 gespawnt. Hier bist du vollkommen ungestört!");
                    }
                    case SPAWN: {
                        LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn");
                        break;
                    }
                    case OFFICE: {
                        if (LobbyItem.OFFICE_CARD_BRONZE.has(lp)) {
                            LobbyWorld.OFFICE.getWorld().teleportSilently(p, OfficeManager.OfficeType.BRONZE_OFFICE.getSpawnLocation());
                        } else if (LobbyItem.OFFICE_CARD_SILVER.has(lp)) {
                            LobbyWorld.OFFICE.getWorld().teleportSilently(p, OfficeManager.OfficeType.SILVER_OFFICE.getSpawnLocation());
                        } else if (LobbyItem.OFFICE_CARD_GOLD.has(lp)) {
                            LobbyWorld.OFFICE.getWorld().teleportSilently(p, OfficeManager.OfficeType.GOLD_OFFICE.getSpawnLocation());
                        }
                    }
                }
            }

            if (!LobbyItem.BANKCARD_PREMIUM.has(lp)) {
                if (p.hasPermission("mcone.premium")) {
                    if (!LobbyItem.BANKCARD.has(lp)) {
                        LobbyItem.BANKCARD_PREMIUM.add(lp);
                    } else {
                        LobbyItem.BANKCARD.remove(lp);
                        LobbyItem.BANKCARD_PREMIUM.add(lp);
                    }

                } else {
                    if (LobbyItem.BANKCARD_PREMIUM.has(lp)) {
                        LobbyItem.BANKCARD_PREMIUM.remove(lp);
                    }
                }
            } else {
                if (p.hasPermission("mcone.premium")) {
                    if (LobbyItem.BANKCARD.has(lp)) {
                        LobbyItem.BANKCARD.remove(lp);
                    }
                }
            }

            p.getInventory().setItem(8, new Skull(p.getName(), 1).toItemBuilder().displayName("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde").create());
        });
    }

    @EventHandler
    public void on(LabyModPlayerJoinEvent e) {
        for (Gamemode gm : Gamemode.values()) {
            NPC npc = LobbyWorld.ONE_ISLAND.getWorld().getNPC(gm.getName().toLowerCase());

            if (npc != null) {
                ((PlayerNpc) npc).playLabymodEmote(LabyModEmote.T_POSE, e.getPlayer());
            }
        }
    }

    public static void setLobbyItems(Player p) {
        p.getInventory().clear();

        p.getInventory().setArmorContents(null);
        p.setGameMode(GameMode.SURVIVAL);
        p.removePotionEffect(PotionEffectType.INVISIBILITY);
        p.getActivePotionEffects().clear();

        p.setMaxHealth(20);
        p.setHealth(20);
        p.setFoodLevel(20);

        if (p.hasPermission("mcone.premium")) p.setAllowFlight(true);
        p.setFlying(false);

        if (SilentLobbyUtils.isActivatedSilentHub(p)) {
            p.getInventory().setItem(0, new ItemBuilder(Material.INK_SACK, 1, 8).displayName("§7§lSpieler Verstecken §8» §7§oIn der Privaten Lobby deaktiviert").create());
        } else if (PlayerHider.players.contains(p)) {
            p.getInventory().setItem(0, HotbarItems.SHOW_PLAYERS);
        } else {
            p.getInventory().setItem(0, HotbarItems.HIDE_PLAYERS);
        }
        p.getInventory().setItem(1, HotbarItems.LOBBY_CHANGER);

        p.getInventory().setItem(4, HotbarItems.COMPASS);

        if (p.hasPermission("system.bungee.nick")) {
            p.getInventory().setItem(6, CoreSystem.getInstance().getCorePlayer(p).isNicked() ? HotbarItems.DEACTIVATE_NICK : HotbarItems.ACTIVATE_NICK);
        }

        p.getInventory().setItem(7, HotbarItems.BACKPACK);

        if (p.hasPermission("lobby.silenthub")) {
            p.getInventory().setItem(2, HotbarItems.PRIVATE_LOBBY);
        }

        p.getInventory().setItem(8, new Skull(p.getName(), 1).toItemBuilder().displayName("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde").create());

        LobbyPlugin.getInstance().getBackpackManager().setRankBoots(p);
    }

}
