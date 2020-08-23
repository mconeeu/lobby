/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.items.LobbyItem;
import eu.mcone.lobby.api.player.*;
import eu.mcone.lobby.api.player.settings.JoinPlayerVisibility;
import eu.mcone.lobby.api.player.settings.LobbySettings;
import eu.mcone.lobby.api.player.settings.SpawnPoint;
import eu.mcone.lobby.api.player.settings.SpawnVillage;
import eu.mcone.lobby.api.player.scoreboard.SidebarObjective;
import eu.mcone.lobby.scheduler.WorldRealTimeScheduler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class LobbySettingsInventory extends CoreInventory {

    public LobbySettingsInventory(Player p) {
        super("§8» §c§lLobby Einstellung", p, InventorySlot.ROW_6, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
        LobbySettings settings = lp.getSettings();

        setItem(InventorySlot.ROW_4_SLOT_2, new ItemBuilder(Material.TRIPWIRE_HOOK, 1, 0).displayName("§f§lJoinTyp").create());
        setItem(InventorySlot.ROW_5_SLOT_2, settings.getJoinPlayerVisibility().getItem().create(), e -> {
            if (p.hasPermission("lobby.silenthub.joinspawn")) {
                switch (settings.getJoinPlayerVisibility()) {
                    case NO_PREFERENCE: {
                        settings.setJoinPlayerVisibility(JoinPlayerVisibility.SILENTLOBBY);
                        setSettings(p, lp);
                        break;
                    }
                    case SILENTLOBBY: {
                        settings.setJoinPlayerVisibility(JoinPlayerVisibility.PLAYERHIDER);
                        setSettings(p, lp);
                        break;
                    }
                    case PLAYERHIDER: {
                        settings.setJoinPlayerVisibility(JoinPlayerVisibility.NO_PREFERENCE);
                        setSettings(p, lp);
                        break;
                    }
                }
            } else {
                switch (settings.getJoinPlayerVisibility()) {
                    case NO_PREFERENCE: {
                        settings.setJoinPlayerVisibility(JoinPlayerVisibility.PLAYERHIDER);
                        setSettings(p, lp);
                        break;
                    }
                    case PLAYERHIDER: {
                        settings.setJoinPlayerVisibility(JoinPlayerVisibility.NO_PREFERENCE);
                        setSettings(p, lp);
                        break;
                    }
                }
            }
        });


        setItem(InventorySlot.ROW_4_SLOT_6, new ItemBuilder(Material.PAPER, 1, 0).displayName("§f§lLobbyGames Einladungen").create());
        if (settings.isLobbyGamesInvite()) {
            setItem(InventorySlot.ROW_5_SLOT_6, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oAndere Spieler können dich", "§7§ozu LobbyGames einladen").create(), e -> {
                settings.setLobbyGamesInvite(false);
                setSettings(p, lp);
            });
        } else {
            setItem(InventorySlot.ROW_5_SLOT_6, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oAndere Spieler können dich nicht", "§7§ozu LobbyGames einladen").create(), e -> {
                settings.setLobbyGamesInvite(true);
                setSettings(p, lp);
            });
        }

        setItem(InventorySlot.ROW_4_SLOT_8, new ItemBuilder(Material.LEATHER_HELMET, 1, 0).displayName("§f§lTragen lassen").create());
        if (settings.isStacking()) {
            setItem(InventorySlot.ROW_5_SLOT_8, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oAndere Spieler können dich", "§7§ohin und her tragen").create(), e -> {
                settings.setStacking(false);
                setSettings(p, lp);
            });
        } else {
            setItem(InventorySlot.ROW_5_SLOT_8, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oAndere Spieler können dich nicht", "§7§ohin und her tragen").create(), e -> {
                settings.setStacking(true);
                setSettings(p, lp);
            });
        }

        setItem(InventorySlot.ROW_4_SLOT_4, new ItemBuilder(Material.SIGN, 1, 0).displayName("§f§lScoreboard anzeigen").create());
        if (settings.isScoreboard()) {
            setItem(InventorySlot.ROW_5_SLOT_4, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um nicht mehr das Scoreboard", "§7§oauf der rechten Seite zu sehen").create(), e -> {
                settings.setScoreboard(false);
                CoreSystem.getInstance().getCorePlayer(p).getScoreboard().getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
                setSettings(p, lp);
            });
        } else {
            setItem(InventorySlot.ROW_5_SLOT_4, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um das Scoreboard", "§7§oauf der rechte Seite zu sehen").create(), e -> {
                CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
                settings.setScoreboard(true);
                cp.getScoreboard().setNewObjective(new SidebarObjective());
                setSettings(p, lp);
            });
        }


        setItem(InventorySlot.ROW_2_SLOT_1, new ItemBuilder(Material.COMPASS, 1, 0).

                displayName("§f§lTeleport Animation abspielen").

                create());
        if (settings.isAllowAnimation()) {
            setItem(InventorySlot.ROW_3_SLOT_1, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um die Animation", "§7§onicht zu sehen und um", "§7§osofort telepotiert zu werden").create(), e -> {
                settings.setAllowAnimation(false);
                setSettings(p, lp);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_1, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um die Animation", "§7§ozu sehen").create(), e -> {
                settings.setAllowAnimation(true);
                setSettings(p, lp);
            });
        }

        setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.CHEST, 1, 0).

                displayName("§f§lInventar Animation abspielen").

                create());
        if (settings.isInventoryAnimation()) {
            setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um die Animation", "§7§onicht zu sehen und damit", "§7§osich das Inventar sofort öffnet").create(), e -> {
                settings.setInventoryAnimation(false);
                setSettings(p, lp);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um die Inventar Animation", "§7§ozu sehen").create(), e -> {
                settings.setInventoryAnimation(true);
                setSettings(p, lp);
            });
        }

        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.WATCH, 1, 0).

                displayName("§f§lTag-Nacht Zyklus").

                create());
        if (settings.isRealTime()) {
            setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lEchtzeit").lore("§7§oKlicke um in der Lobby", "§7§oimmer Tag zu haben").create(), e -> {
                settings.setRealTime(false);
                setSettings(p, lp);

                p.resetPlayerTime();
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§e§lImmer Tag").lore("§7§oKlicke um die Zeit in der", "§7§oLobby an die echte Zeit", "§7§oanzupassen").create(), e -> {
                settings.setRealTime(true);
                setSettings(p, lp);

                WorldRealTimeScheduler.setCurrentRealTime(lp);
            });
        }

        setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.NETHER_STAR, 1, 0).displayName("§f§lSpawn Ort").create());
        setItem(InventorySlot.ROW_3_SLOT_4, settings.getSpawnPoint().getItem().create(), e ->

        {
            switch (settings.getSpawnPoint()) {
                case SPAWN: {
                    settings.setSpawnPoint(SpawnPoint.LAST_LOCATION);
                    setSettings(p, lp);
                    break;
                }
                case LAST_LOCATION: {
                    if (lp.hasLobbyItem(LobbyItem.OFFICE_CARD_BRONZE) || lp.hasLobbyItem(LobbyItem.OFFICE_CARD_SILVER) || lp.hasLobbyItem(LobbyItem.OFFICE_CARD_GOLD)) {
                        settings.setSpawnPoint(SpawnPoint.OFFICE);
                    } else {
                        settings.setSpawnPoint(SpawnPoint.SPAWN);
                    }

                    setSettings(p, lp);
                    break;
                }
                case OFFICE: {
                    settings.setSpawnPoint(SpawnPoint.SPAWN);
                    setSettings(p, lp);
                    break;
                }
            }
        });

        if (settings.getSpawnPoint().

                equals(SpawnPoint.SPAWN)) {
            setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.LEAVES, 1, 0).displayName("§f§lDorfspawn Ort").create());
            setItem(InventorySlot.ROW_3_SLOT_5, settings.getSpawnVillage().getItem().create(), e -> {
                switch (settings.getSpawnVillage()) {
                    case RANDOM: {
                        settings.setSpawnVillage(SpawnVillage.RAISEN);
                        setSettings(p, lp);
                        break;
                    }
                    case RAISEN: {
                        settings.setSpawnVillage(SpawnVillage.SKYLECK);
                        setSettings(p, lp);
                        break;
                    }
                    case SKYLECK: {
                        settings.setSpawnVillage(SpawnVillage.RANDOM);
                        setSettings(p, lp);
                        break;
                    }
                }
            });
        } else {
            setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.LEAVES, 1, 0).displayName("§f§lDorfspawn Ort").create());
            setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.INK_SACK, 1, 8).displayName("§cNicht verfügbar").create());
        }

        setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.FIREWORK_CHARGE, 1, 0).displayName("§f§lGadgets").create());
        GamePlayer gp = lp.getGamePlayer();
        if (gp.getSettings().isEnableGadgets()) {
            setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um die Gadgets", "§7§ofür dich selbst zu deaktivieren", "§7§odann erhälst du keine Gadgets", "§7§oEffekte mehr und du selbst kannst", "§7§okeine mehr ausrüsten").create(), e -> {
                gp.getSettings().setEnableGadgets(false);
                new LobbySettingsInventory(p);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um die Gadgets für dich zu aktivieren", "§7§ound um die Effekte der anderen zu sehen").create(), e -> {
                gp.getSettings().setEnableGadgets(true);
                new LobbySettingsInventory(p);
            });
        }

        setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.STORAGE_MINECART, 1, 0).

                displayName("§f§lTauschen").create());
        if (gp.getSettings().isEnableTraiding()) {
            setItem(InventorySlot.ROW_3_SLOT_7, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um das Tauschen von", "§7§oRucksack Items zu deaktivieren").create(), e -> {
                gp.getSettings().setEnableTraiding(false);
                new LobbySettingsInventory(p);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_7, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um das Tauschen von", "§7§oRucksack Items zu aktivieren").create(), e -> {
                gp.getSettings().setEnableTraiding(true);
                new LobbySettingsInventory(p);
            });
        }


        setItem(InventorySlot.ROW_2_SLOT_8, new ItemBuilder(Material.LEATHER_BOOTS, 1, 0).displayName("§f§lErhalte Rang Schuhe").create());
        if (settings.isRankBoots()) {
            setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um keine Rang Schuhe zu erhalten", "§7§owenn du den Server beitritts!").create(), e -> {
                settings.setRankBoots(false);
                player.getInventory().setBoots(null);
                setSettings(p, lp);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um Rang Schuhe zu erhalten", "§7§owenn du den Server beitritts!").create(), e -> {
                settings.setRankBoots(true);
                LobbyPlugin.getInstance().getBackpackManager().setRankBoots(p);
                setSettings(p, lp);
            });
        }

        setItem(InventorySlot.ROW_2_SLOT_9, new ItemBuilder(Material.NOTE_BLOCK, 1, 0).displayName("§f§lHotbar wechsel Sound").create());
        if (settings.isHotbarChangeSound()) {
            setItem(InventorySlot.ROW_3_SLOT_9, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um keinen Hotbar wechsel", "§7§oSound mehr zu erhalten!").create(), e -> {
                settings.setHotbarChangeSound(false);
                setSettings(p, lp);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_9, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um wieder einen Hotbar wechsel", "§7§oSound zu erhalten").create(), e -> {
                settings.setHotbarChangeSound(true);
                setSettings(p, lp);
            });
        }


        setItem(InventorySlot.ROW_6_SLOT_9, BACK_ITEM, e ->
                Bukkit.dispatchCommand(p, "profile"));

        openInventory();
    }

    private void setSettings(Player p, LobbyPlayer lp) {
        lp.saveData();
        p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
        new LobbySettingsInventory(p);
    }

}

