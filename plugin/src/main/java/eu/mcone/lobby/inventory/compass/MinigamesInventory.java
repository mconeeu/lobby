package eu.mcone.lobby.inventory.compass;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.coresystem.api.bukkit.gamemode.Gamemode;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.settings.SpawnVillage;
import eu.mcone.lobby.inventory.ServerInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;

import java.util.Random;

public class MinigamesInventory extends CompassInventory {

    static {
        CoreSystem.getInstance().getCooldownSystem().setCustomCooldownFor(MinigamesInventory.class, 3);
    }

    public MinigamesInventory(Player p) {
        super("§8» §3§lMinigames", p, InventorySlot.ROW_5, InventoryOption.FILL_EMPTY_SLOTS);
        if (!CoreSystem.getInstance().getCooldownSystem().addAndCheck(getClass(), p.getUniqueId()))
            return;

         Sound.done(p);

        setItem(InventorySlot.ROW_5_SLOT_1, SILVER_PLACEHOLDER);
        setItem(InventorySlot.ROW_5_SLOT_2, SILVER_PLACEHOLDER);

        setItem(InventorySlot.ROW_5_SLOT_4, SILVER_PLACEHOLDER);
        setItem(InventorySlot.ROW_5_SLOT_6, SILVER_PLACEHOLDER);


        setItem(InventorySlot.ROW_5_SLOT_8, SILVER_PLACEHOLDER);
        setItem(InventorySlot.ROW_5_SLOT_9, SILVER_PLACEHOLDER);


        setItem(InventorySlot.ROW_4_SLOT_1, PLACEHOLDER_ITEM);

        setItem(InventorySlot.ROW_4_SLOT_2, PLACEHOLDER_ITEM);
        setItem(InventorySlot.ROW_4_SLOT_3, PLACEHOLDER_ITEM);
        setItem(InventorySlot.ROW_4_SLOT_4, PLACEHOLDER_ITEM);
        setItem(InventorySlot.ROW_4_SLOT_5, PLACEHOLDER_ITEM);

        setItem(InventorySlot.ROW_4_SLOT_6, PLACEHOLDER_ITEM);
        setItem(InventorySlot.ROW_4_SLOT_7, PLACEHOLDER_ITEM);
        setItem(InventorySlot.ROW_4_SLOT_8, PLACEHOLDER_ITEM);
        setItem(InventorySlot.ROW_4_SLOT_9, PLACEHOLDER_ITEM);

        Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
             Sound.done(p);

            setItem(InventorySlot.ROW_1_SLOT_4, SILVER_PLACEHOLDER);
            setItem(InventorySlot.ROW_1_SLOT_6, SILVER_PLACEHOLDER);

            Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                Sound.done(p);

                setItem(InventorySlot.ROW_1_SLOT_5, CYAN_PLACEHOLDER);
                setItem(InventorySlot.ROW_2_SLOT_4, CYAN_PLACEHOLDER);
                setItem(InventorySlot.ROW_2_SLOT_6, CYAN_PLACEHOLDER);
                setItem(InventorySlot.ROW_3_SLOT_5, CYAN_PLACEHOLDER);

                Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                    Sound.done(p);

                    setItem(InventorySlot.ROW_1_SLOT_3, LIGHT_BLUE_PLACEHOLDER);
                    setItem(InventorySlot.ROW_3_SLOT_3, LIGHT_BLUE_PLACEHOLDER);
                    setItem(InventorySlot.ROW_1_SLOT_7, LIGHT_BLUE_PLACEHOLDER);
                    setItem(InventorySlot.ROW_3_SLOT_7, LIGHT_BLUE_PLACEHOLDER);

                    setItem(InventorySlot.ROW_1_SLOT_2, CYAN_PLACEHOLDER);
                    setItem(InventorySlot.ROW_3_SLOT_2, CYAN_PLACEHOLDER);
                    setItem(InventorySlot.ROW_1_SLOT_8, CYAN_PLACEHOLDER);
                    setItem(InventorySlot.ROW_3_SLOT_8, CYAN_PLACEHOLDER);

                    Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                         Sound.done(p);

                        setItem(InventorySlot.ROW_1_SLOT_1, LIGHT_BLUE_PLACEHOLDER);
                        setItem(InventorySlot.ROW_3_SLOT_1, LIGHT_BLUE_PLACEHOLDER);
                        setItem(InventorySlot.ROW_1_SLOT_9, LIGHT_BLUE_PLACEHOLDER);
                        setItem(InventorySlot.ROW_3_SLOT_9, LIGHT_BLUE_PLACEHOLDER);

                        Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                            Sound.done(p);

                            setItem(InventorySlot.ROW_2_SLOT_9, new ItemBuilder(Gamemode.SKYPVP.getItem(), 1, 0)
                                            .displayName(Gamemode.SKYPVP.getLabel())
                                            .lore("§7§oFinde deine Gegner auf einer Sky-Map", "§7§ound töte sie, um Coins zu erhalten!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren", "§8» §f§nRechtsklick§8 | §7§oSchnellbeitritt")
                                            .create(),
                                    e -> {
                                        if (e.getClick().equals(ClickType.RIGHT)) {
                                            new ServerInventory(p, Gamemode.SKYPVP);
                                        } else if (e.getClick().equals(ClickType.LEFT)) {
                                            player.closeInventory();
                                            LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("skypvp");
                                        }
                                    });

                            setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.REDSTONE_COMPARATOR, 1, 0)
                                            .displayName("§eReplay")
                                            .lore("§7§oSchaue dir mit deinen Freunden", "§7§odeinen alten Spielrunden an!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                            .create(),
                                    e -> {
                                        player.closeInventory();
                                        CoreSystem.getInstance().getChannelHandler().createSetRequest(p, "CONNECT", "replay");
                                    });

                            setItem(InventorySlot.ROW_3_SLOT_6, new Skull(p.getName(), 1).toItemBuilder()
                                            .displayName("§5Festival / Community")
                                            .lore("§7§oTreffe dich mit Freunden auf dem ", "§7§oFestival/Community aber", "§7§oreise mit einem gültigen Ticket hin!", "", "§8» §f§nRechtsklick§8 | §7§oSchnellbeitritt")
                                            .create(),
                                    e -> {
                                        player.closeInventory();
                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("community");
                                    });

                            setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Gamemode.BEDWARS.getItem(), 1, 0)
                                            .displayName(Gamemode.BEDWARS.getLabel())
                                            .lore("§7§oBaue Betten von anderen Spielern ab", "§7§ound start mit Kits durch!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren", "§8» §f§nRechtsklick§8 | §7§oSchnellbeitritt")
                                            .create(),
                                    e -> {
                                        if (e.getClick().equals(ClickType.RIGHT)) {
                                            new ServerInventory(p, Gamemode.BEDWARS);
                                        } else if (e.getClick().equals(ClickType.LEFT)) {
                                            player.closeInventory();
                                            LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("bedwars");
                                        }
                                    });

                            setItem(InventorySlot.ROW_2_SLOT_1, new ItemBuilder(Gamemode.MINEWAR.getItem(), 1, 0)
                                            .displayName(Gamemode.MINEWAR.getLabel())
                                            .lore("§7§oBaue Erze ab und erhalte", "§7§ocoole Items und töte Gegner!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren", "§8» §f§nRechtsklick§8 | §7§oSchnellbeitritt")
                                            .create(),
                                    e -> {
                                        if (e.getClick().equals(ClickType.RIGHT)) {
                                            new ServerInventory(p, Gamemode.MINEWAR);
                                        } else if (e.getClick().equals(ClickType.LEFT)) {
                                            player.closeInventory();
                                            LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("minewar");
                                        }
                                    });

                            setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Gamemode.BUILD.getItem(), 1, 0)
                                            .displayName(Gamemode.BUILD.getLabel())
                                            .lore("§7§oBuild Server. Überzeuge uns von deinen Baukünsten", "§7§ound werde Builder im MC ONE Team!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren", "§8» §f§nRechtsklick§8 | §7§oSchnellbeitritt")
                                            .create(),
                                    e -> {
                                        if (e.getClick().equals(ClickType.RIGHT)) {
                                            new ServerInventory(p, Gamemode.BUILD);
                                        } else if (e.getClick().equals(ClickType.LEFT)) {
                                            player.closeInventory();
                                            LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("build");
                                        }
                                    });

                            setItem(InventorySlot.ROW_2_SLOT_8, new ItemBuilder(Gamemode.KNOCKIT.getItem(), 1, 0)
                                            .displayName(Gamemode.KNOCKIT.getLabel())
                                            .enchantment(Enchantment.KNOCKBACK, 1)
                                            .lore("§7§oSchlage die Gegner von der Plattform um Coins", "§7§ozu erhalten!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren", "§8» §f§nRechtsklick§8 | §7§oSchnellbeitritt")
                                            .itemFlags(ItemFlag.HIDE_ENCHANTS)
                                            .create(),
                                    e -> {
                                        if (e.getClick().equals(ClickType.RIGHT)) {
                                            new ServerInventory(p, Gamemode.KNOCKIT);
                                        } else if (e.getClick().equals(ClickType.LEFT)) {
                                            player.closeInventory();
                                            LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("knockit");
                                        }
                                    });

                            setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.DEAD_BUSH, 1, 0)
                                            .displayName("§aTrashwars")
                                            .lore("§7§oKämpfe um den Sieg mit einem Müll", "§7§oInventar auf einer Insel voller Gegner", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren", "§8» §f§nRechtsklick§8 | §7§oSchnellbeitritt")
                                            .create(),
                                    e -> {
                                        if (e.getClick().equals(ClickType.RIGHT)) {
                                            new ServerInventory(p, Gamemode.TRASHWARS);
                                        } else if (e.getClick().equals(ClickType.LEFT)) {
                                            player.closeInventory();
                                            LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("trashwars");
                                        }
                                    });

                            CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(p);
                            setItem(InventorySlot.ROW_3_SLOT_6, new Skull(corePlayer.bukkit().getName(), 1).toItemBuilder()
                                            .displayName("§5Festival / Community")
                                            .lore("§7§oTreffe dich mit Freunden auf dem ", "§7§oFestival/Community und", "§7§oreise mit einem gültigen Ticket hin!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                            .create(),
                                    e -> {
                                        player.closeInventory();
                                        CoreSystem.getInstance().getChannelHandler().createSetRequest(p, "CONNECT", "Community");
                                    });

                            Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                                Sound.done(p);

                                setItem(InventorySlot.ROW_5_SLOT_3, new ItemBuilder(Material.IRON_SWORD, 1, 0)
                                                .displayName("§fLobby-Games")
                                                .lore("§7§oSpiele in der Lobby kleine Minigames", "§7§owie zum Beispiel Fangen", "", "§8» §f§nLinksklick§8 | §7§oÖffnen")
                                                .create(),
                                        e -> {
                                            new LobbyGamesInventory(p);
                                            Sound.click(p);
                                        });

                                setItem(InventorySlot.ROW_5_SLOT_5, new ItemBuilder(Material.EMERALD, 1, 0)
                                                .displayName("§fEvents")
                                                .lore("§7§oSpiele ein Community Event", "§7§omit deinen Freunden!", "", "§8» §f§nLinksklick§8 | §7§oÖffnen")
                                                .create(),
                                        e -> {
                                            new EventInventory(p);
                                            Sound.click(p);
                                        });

                                setItem(InventorySlot.ROW_5_SLOT_7, new ItemBuilder(Material.BOOK, 1, 0)
                                                .displayName("§fLobby-Orte")
                                                .lore("§7§oSchaue dir unsere Lobby-OneIsland genauer an", "§7§ound reise zu verschiedene Orten", "", "§8» §f§nLinksklick§8 | §7§oÖffnen")
                                                .create(),
                                        e -> {
                                            Sound.click(p);
                                            new LobbyPlacesInventory(p);
                                        });

                                Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                                    setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.NETHER_STAR, 1, 0)
                                                    .displayName("§f§lSpawn")
                                                    .lore("§7§oZurück zum Lobby Spawn.", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren", "§8» §f§nRechtsklick§8 | §7§oZum anderen Dorf telepotieren")
                                                    .create(),
                                            e -> {
                                                player.closeInventory();
                                                LobbyPlayer lobbyPlayer = LobbyPlugin.getInstance().getLobbyPlayer(p);

                                                if (e.getClick().isRightClick()) {
                                                    if (lobbyPlayer.getSettings().getSpawnVillage().equals(SpawnVillage.RAISEN)) {
                                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("spawn2");
                                                    } else if (lobbyPlayer.getSettings().getSpawnVillage().equals(SpawnVillage.SKYLECK)) {
                                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("spawn");
                                                    } else {
                                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("spawn");
                                                    }
                                                } else if (e.getClick().isLeftClick()) {
                                                    if (lobbyPlayer.getSettings().getSpawnVillage().equals(SpawnVillage.RAISEN)) {
                                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("spawn");
                                                    } else if (lobbyPlayer.getSettings().getSpawnVillage().equals(SpawnVillage.SKYLECK)) {
                                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("spawn2");
                                                    } else {
                                                        int randomeSpawn = getRandomNumberInRange(1, 3);
                                                        if (randomeSpawn == 1) {
                                                            LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("spawn2");
                                                        } else {
                                                            LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("spawn");
                                                        }
                                                    }
                                                }
                                            });
                                }, 1L);
                            }, 1L);
                        }, 2L);
                    }, 2L);
                }, 2L);
            }, 2L);
        }, 2L);

        openInventory();
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
