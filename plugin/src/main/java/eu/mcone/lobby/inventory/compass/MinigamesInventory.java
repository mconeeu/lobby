package eu.mcone.lobby.inventory.compass;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.gamemode.Gamemode;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.items.manager.OfficeManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

public class MinigamesInventory extends CoreInventory {

    static {
        CoreSystem.getInstance().getCooldownSystem().setCustomCooldownFor(MinigamesInventory.class, 3);
    }

    public MinigamesInventory(Player p) {
        super("§8» §3§lMinigames", p, InventorySlot.ROW_5, InventoryOption.FILL_EMPTY_SLOTS);

        if (!CoreSystem.getInstance().getCooldownSystem().addAndCheck(CoreSystem.getInstance(), this.getClass(), p.getUniqueId()))
            return;

        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);


        setItem(InventorySlot.ROW_5_SLOT_1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_5_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).displayName("§8//§oMCONE§8//").create());

        setItem(InventorySlot.ROW_5_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_5_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_5_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).displayName("§8//§oMCONE§8//").create());


        setItem(InventorySlot.ROW_5_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_5_SLOT_9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).displayName("§8//§oMCONE§8//").create());


        setItem(InventorySlot.ROW_4_SLOT_1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

        setItem(InventorySlot.ROW_4_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_4_SLOT_3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_4_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_4_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

        setItem(InventorySlot.ROW_4_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_4_SLOT_7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_4_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_4_SLOT_9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

        openInventory();

        Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
            p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);

            setItem(InventorySlot.ROW_1_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).displayName("§8//§oMCONE§8//").create());
            setItem(InventorySlot.ROW_1_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).displayName("§8//§oMCONE§8//").create());

            Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
                setItem(InventorySlot.ROW_1_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
                setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
                setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
                setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());

                Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                    p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
                    setItem(InventorySlot.ROW_1_SLOT_3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_1_SLOT_7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_3_SLOT_7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());

                    setItem(InventorySlot.ROW_1_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_1_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
                    Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                        setItem(InventorySlot.ROW_1_SLOT_1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
                        setItem(InventorySlot.ROW_3_SLOT_1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
                        setItem(InventorySlot.ROW_1_SLOT_9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
                        setItem(InventorySlot.ROW_3_SLOT_9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
                        Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                            p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
                            Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                                setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.NETHER_STAR, 1, 0)
                                                .displayName("§f§lSpawn")
                                                .lore("§7§oZurück zum Lobby Spawn.", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                                .create(),
                                        e -> {
                                            player.closeInventory();
                                            LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("Spawn");

                                        });
                            }, 2L);

                            setItem(InventorySlot.ROW_2_SLOT_9, new ItemBuilder(Gamemode.SKYPVP.getItem(), 1, 0)
                                            .displayName(Gamemode.SKYPVP.getLabel())
                                            .lore("§7§oFinde deine Gegner auf einer Sky-Map", "§7§ound töte sie, um Coins zu erhalten!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                            .create(),
                                    e -> {
                                        player.closeInventory();
                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("skypvp");

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
                                            .lore("§7§oTreffe dich mit Freunden auf dem ", "§7§oFestival/Community und", "§7§oreise mit einem gültigen Ticket hin!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                            .create(),
                                    e -> {
                                        player.closeInventory();
                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("community");

                                    });

                            setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Gamemode.BEDWARS.getItem(), 1, 0)
                                            .displayName(Gamemode.BEDWARS.getLabel())
                                            .lore("§7§oBaue Betten von anderen Spielern ab", "§7§ound start mit Kits durch!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                            .create(),
                                    e -> {
                                        player.closeInventory();
                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("bedwars");

                                        if (p.getWorld().getName().equalsIgnoreCase("Office")) {
                                            OfficeManager.unVanishPlayer(p);
                                        }
                                    }
                            );

                            p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
                            setItem(InventorySlot.ROW_2_SLOT_1, new ItemBuilder(Gamemode.MINEWAR.getItem(), 1, 0)
                                            .displayName(Gamemode.MINEWAR.getLabel())
                                            .lore("§7§oBaue Erze ab und erhalte", "§7§ocoole Items und töte Gegner!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                            .create(),
                                    e -> {
                                        player.closeInventory();
                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("minewar");

                                        if (p.getWorld().getName().equalsIgnoreCase("Office")) {
                                            OfficeManager.unVanishPlayer(p);
                                        }
                                    }
                            );

                            setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Gamemode.BUILD.getItem(), 1, 0)
                                            .displayName(Gamemode.BUILD.getLabel())
                                            .lore("§7§oBuild Server. Überzeuge uns von deinen Baukünsten", "§7§ound werde Builder im MC ONE Team!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                            .create(),
                                    e -> {
                                        player.closeInventory();
                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("build");

                                    });

                            setItem(InventorySlot.ROW_2_SLOT_8, new ItemBuilder(Gamemode.KNOCKIT.getItem(), 1, 0)
                                            .displayName(Gamemode.KNOCKIT.getLabel())
                                            .enchantment(Enchantment.KNOCKBACK, 1)
                                            .lore("§7§oSchlage die Gegner von der Plattform um Coins", "§7§ozu erhalten!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                            .itemFlags(ItemFlag.HIDE_ENCHANTS)
                                            .create(),
                                    e -> {
                                        player.closeInventory();
                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("knockit");

                                    });


                            setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.DEAD_BUSH, 1, 0)
                                            .displayName("§aTrashwars")
                                            .lore("§7§oKämpfe um den Sieg mit einem Müll", "§7§oInventar auf einer Insel voller Gegner", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                            .create(),
                                    e -> {
                                        player.closeInventory();
                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("trashwars");

                                    });

                            setItem(InventorySlot.ROW_3_SLOT_6, new Skull(p.getName(), 1).toItemBuilder()
                                            .displayName("§5Festival / Community")
                                            .lore("§7§oTreffe dich mit Freunden auf dem ", "§7§oFestival/Community und", "§7§oreise mit einem gültigen Ticket hin!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                            .create(),
                                    e -> {
                                        player.closeInventory();
                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("community");

                                    });

                            Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {

                                p.playSound(p.getLocation(), Sound.CLICK, 1, 1);

                                setItem(InventorySlot.ROW_5_SLOT_3, new ItemBuilder(Material.IRON_SWORD, 1, 0)
                                                .displayName("§fLobby-Games")
                                                .lore("§7§oKämpfe um den Sieg mit einem Müll", "§7§oInventar auf einer Insel voller Gegner", "", "§8» §f§nLinksklick§8 | §7§oÖffnen")
                                                .create(),
                                        e -> {
                                            new LobbyGamesInventory(p);
                                            player.playSound(p.getLocation(), Sound.NOTE_STICKS, 1, 1);
                                        });

                                setItem(InventorySlot.ROW_5_SLOT_7, new ItemBuilder(Material.BOOK, 1, 0)
                                                .displayName("§fLobby-Orte")
                                                .lore("§7§oKämpfe um den Sieg mit einem Müll", "§7§oInventar auf einer Insel voller Gegner", "", "§8» §f§nLinksklick§8 | §7§oÖffnen")
                                                .create(),
                                        e -> {
                                            player.playSound(p.getLocation(), Sound.NOTE_STICKS, 1, 1);
                                            new LobbyPlacesInventory(p);


                                        });
                            }, 1L);
                        }, 2L);
                    }, 2L);
                }, 2L);
            }, 2L);
        }, 2L);


    }
}
