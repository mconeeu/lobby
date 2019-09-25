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
import eu.mcone.gamesystem.api.enums.Item;
import eu.mcone.gamesystem.api.game.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.event.LobbyPlayerLoadedEvent;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.items.manager.OfficeManager;
import eu.mcone.lobby.util.PlayerHider;
import eu.mcone.lobby.util.PlayerSpawnLocation;
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

        PlayerHider.playerJoined(p);

        p.playEffect(p.getLocation(), org.bukkit.Effect.HAPPY_VILLAGER, 5);
        p.playSound(p.getLocation(), Sound.FIREWORK_TWINKLE, 2.0F, 5.0F);

        loadLobbyPlayer(p, LobbyPlayerLoadedEvent.Reason.JOINED);
        p.setWalkSpeed(0.2F);
    }

    public static void loadLobbyPlayer(Player p, LobbyPlayerLoadedEvent.Reason reason) {
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        LOADING_MSG.send(p);

        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.setGameMode(GameMode.SURVIVAL);
        p.getActivePotionEffects().clear();

        p.setMaxHealth(20);
        p.setHealth(20);
        p.setFoodLevel(20);

        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 0));

        if (p.hasPermission("mcone.premium")) p.setAllowFlight(true);
        p.setFlying(false);

        p.getInventory().setItem(0, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus").create());
        p.getInventory().setItem(1, new ItemBuilder(Material.NETHER_STAR, 1, 0).displayName("§3§lLobby-Wechsler §8» §7§oWähle deine Lobby").create());
        if (p.hasPermission("lobby.silenthub")) {
            p.getInventory().setItem(7, new ItemBuilder(Material.INK_SACK, 1, 2).displayName("§7§oLädt...").create());
        }

        p.getInventory().setItem(4, new ItemBuilder(Material.COMPASS, 1, 0).displayName("§3§lNavigator §8» §7§oWähle einen Spielmodus").create());

        if (p.hasPermission("system.bungee.nick")) {
            p.getInventory().setItem(6, cp.isNicked() ?
                    new ItemBuilder(Material.NAME_TAG, 1, 0).displayName("§a§lNicken §8» §7§oAktiviert").lore("§7§oKlicke zum deaktivieren").create() :
                    new ItemBuilder(Material.NAME_TAG, 1, 0).displayName("§c§lNicken §8» §7§oDeaktiviert").lore("§7§oKlicke zum aktivieren").create()
            );
        }
        p.getInventory().setItem(7, new ItemBuilder(Material.INK_SACK, 1, 2).displayName("§7§oLädt...").create());
        p.getInventory().setItem(8, new ItemBuilder(Material.INK_SACK, 1, 2).displayName("§7§oLädt...").create());


        cp.getScoreboard().setNewObjective(new SidebarObjective());
        switch (cp.getMainGroup()) {
            case PREMIUM:
                p.getInventory().setBoots(Item.PREMIUM_BOOTS.getItemStack());
                break;
            case PREMIUMPLUS:
                p.getInventory().setBoots(Item.PREMIUM_PLUS_BOOTS.getItemStack());
                break;
            case YOUTUBER:
                p.getInventory().setBoots(Item.YOUTUBER_BOOTS.getItemStack());
                break;
            case JRSUPPORTER:
                p.getInventory().setBoots(Item.JR_SUPPORTER_BOOTS.getItemStack());
                break;
            case SUPPORTER:
                p.getInventory().setBoots(Item.SUPPORTER_BOOTS.getItemStack());
                break;
            case MODERATOR:
                p.getInventory().setBoots(Item.MODERATOR_BOOTS.getItemStack());
                break;
            case SRMODERATOR:
                p.getInventory().setBoots(Item.SR_MODERATOR_BOOTS.getItemStack());
                break;
            case BUILDER:
                p.getInventory().setBoots(Item.BUILDER_BOOTS.getItemStack());
                break;
            case DEVELOPER:
                p.getInventory().setBoots(Item.DEVELOPER_BOOTS.getItemStack());
                break;
            case ADMIN:
                p.getInventory().setBoots(Item.ADMIN_BOOTS.getItemStack());
                break;
        }

        Bukkit.getScheduler().runTask(LobbyPlugin.getInstance(), () -> {
            LobbyPlayer lp = new LobbyPlayer(cp);
            GamePlayer gp = LobbyPlugin.getInstance().getGamePlayer(p.getUniqueId());
            Bukkit.getPluginManager().callEvent(new LobbyPlayerLoadedEvent(lp, reason));
            LOADING_SUCCESS_MSG.send(p);

            if (reason.equals(LobbyPlayerLoadedEvent.Reason.JOINED)) {
                if (!lp.getSettings().getSpawnLocation().equalsIgnoreCase(PlayerSpawnLocation.LAST_LOGIN.toString())) {
                    if (lp.getSettings().isSpawnInSilentLobby()) {
                        SilentLobbyUtils.activateSilentLobby(p);
                        LobbyPlugin.getInstance().getMessager().send(p, "§2Du bist in der §aPrivaten Lobby§2 gespawnt. Hier bist du vollkommen ungestört!");
                    }

                    if (lp.getSettings().getSpawnLocation().equalsIgnoreCase(PlayerSpawnLocation.SPAWN.toString())) {
                        PlayerSpawnLocation.SPAWN.getWorld().teleportSilently(p, "spawn");
                    } else if (lp.getSettings().getSpawnLocation().equalsIgnoreCase(PlayerSpawnLocation.OFFICE.toString())) {
                        if (gp.hasItem(Item.OFFICE_CARD_BRONZE)) {
                            LobbyWorld.OFFICE.getWorld().teleportSilently(p, OfficeManager.OfficeType.BRONZE_OFFICE.getSpawnLocation());
                        } else if (gp.hasItem(Item.OFFICE_CARD_SILVER)) {
                            LobbyWorld.OFFICE.getWorld().teleportSilently(p, OfficeManager.OfficeType.SILVER_OFFICE.getSpawnLocation());
                        } else if (gp.hasItem(Item.OFFICE_CARD_GOLD)) {
                            LobbyWorld.OFFICE.getWorld().teleportSilently(p, OfficeManager.OfficeType.GOLD_OFFICE.getSpawnLocation());
                        }
                    }
                }
            }

            if (!gp.hasItem(Item.BANKCARD_PREMIUM)) {
                if (p.hasPermission("mcone.premium")) {
                    if (!gp.hasItem(Item.BANKCARD)) {
                        gp.addItem(Item.BANKCARD_PREMIUM);
                    } else {
                        gp.removeItem(Item.BANKCARD);
                        gp.addItem(Item.BANKCARD_PREMIUM);
                    }

                }
            } else {
                if (p.hasPermission("mcone.premium")) {
                    if (gp.hasItem(Item.BANKCARD)) {
                        gp.removeItem(Item.BANKCARD);
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

}
