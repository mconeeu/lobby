package eu.mcone.lobby.inventory.compass;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class LobbyGamesInventory extends CoreInventory {

    static {
        CoreSystem.getInstance().getCooldownSystem().setCustomCooldownFor(LobbyGamesInventory.class, 3);
    }

    public LobbyGamesInventory(Player p) {
        super("§8» §3§lLobby-Games", p, InventorySlot.ROW_5, InventoryOption.FILL_EMPTY_SLOTS);

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

            setItem(InventorySlot.ROW_1_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
            setItem(InventorySlot.ROW_1_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
            setItem(InventorySlot.ROW_1_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());

            setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
            setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
            setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());

            setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
            setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
            setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
            setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());

            Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
                setItem(InventorySlot.ROW_1_SLOT_1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
                setItem(InventorySlot.ROW_2_SLOT_1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
                setItem(InventorySlot.ROW_3_SLOT_1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());

                setItem(InventorySlot.ROW_1_SLOT_9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
                setItem(InventorySlot.ROW_2_SLOT_9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
                setItem(InventorySlot.ROW_3_SLOT_9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());

                Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                    p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
                    setItem(InventorySlot.ROW_1_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_1_SLOT_3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_1_SLOT_7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_1_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());

                    setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_3_SLOT_7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());

                    Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                        p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
                        Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                            setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.IRON_SWORD, 1, 0)
                                            .displayName("§cOne-Hit §8| §fLobbygame")
                                            .lore("§7§oSpiele ein bekannten Modi", "§7§omit deinen Freunden auf der Lobby", "", "§8» §f§nLinksklick§8 | §7§oSpielen")
                                            .create(),

                                    e -> {
                                        p.closeInventory();
                                        LobbyPlugin.getInstance().getOneHitManager().setStart(p);
                                    });

                        }, 2L);

                        setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.STICK, 1, 0)
                                        .displayName("§eFangen §8| §fLobbygame")
                                        .lore("§7§oFange andere Spieler", "§7§ooder laufe vor anderen weg", "", "§8» §f§nLinksklick§8 | §7§oSpielen")
                                        .create(),

                                e -> {
                                    p.closeInventory();
                                    LobbyPlugin.getInstance().getCatchManager().setStart(p);
                                });

                        setItem(InventorySlot.ROW_2_SLOT_8, new ItemBuilder(Material.DIAMOND_BOOTS, 1, 0)
                                        .displayName("§bJumpAndRun §8| §fLobbygame")
                                        .lore("§7§oBesuche die JumpAndRun Tafel", "§7§oUm alle Jump and Runs zu sehen", "§7§oUnd um sie zu starten", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),

                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("jumpandrun-board");

                                });

                        Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {

                            p.playSound(p.getLocation(), Sound.CLICK, 1, 1);

                            setItem(InventorySlot.ROW_5_SLOT_3, new ItemBuilder(Material.NETHER_STAR, 1, 0)
                                            .displayName("§fMinigames")
                                            .lore("§7§oKämpfe um den Sieg mit einem Müll", "§7§oInventar auf einer Insel voller Gegner", "", "§8» §f§nLinksklick§8 | §7§oÖffnen")
                                            .create(),
                                    e -> {
                                        new MinigamesInventory(p);
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


    }
}