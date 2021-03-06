package eu.mcone.lobby.inventory.compass;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.games.pvp.Catch;
import eu.mcone.lobby.api.games.pvp.GunGame;
import eu.mcone.lobby.api.games.pvp.OneHit;
import eu.mcone.lobby.games.LobbyGames;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class LobbyGamesInventory extends CompassInventory {

    static {
        CoreSystem.getInstance().getCooldownSystem().setCustomCooldownFor(LobbyGamesInventory.class, 3);
    }

    public LobbyGamesInventory(Player p) {
        super("§8» §3§lLobby-Games", p, InventorySlot.ROW_5, InventoryOption.FILL_EMPTY_SLOTS);
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

            setItem(InventorySlot.ROW_1_SLOT_4, LIGHT_BLUE_PLACEHOLDER);
            setItem(InventorySlot.ROW_1_SLOT_5, LIGHT_BLUE_PLACEHOLDER);
            setItem(InventorySlot.ROW_1_SLOT_6, LIGHT_BLUE_PLACEHOLDER);

            setItem(InventorySlot.ROW_3_SLOT_4, LIGHT_BLUE_PLACEHOLDER);
            setItem(InventorySlot.ROW_3_SLOT_5, LIGHT_BLUE_PLACEHOLDER);
            setItem(InventorySlot.ROW_3_SLOT_6, LIGHT_BLUE_PLACEHOLDER);

            setItem(InventorySlot.ROW_2_SLOT_3, LIGHT_BLUE_PLACEHOLDER);
            setItem(InventorySlot.ROW_2_SLOT_4, LIGHT_BLUE_PLACEHOLDER);
            setItem(InventorySlot.ROW_2_SLOT_6, LIGHT_BLUE_PLACEHOLDER);
            setItem(InventorySlot.ROW_2_SLOT_7, LIGHT_BLUE_PLACEHOLDER);

            Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                Sound.done(p);

                setItem(InventorySlot.ROW_1_SLOT_1, CYAN_PLACEHOLDER);
                setItem(InventorySlot.ROW_2_SLOT_1, CYAN_PLACEHOLDER);
                setItem(InventorySlot.ROW_3_SLOT_1, CYAN_PLACEHOLDER);

                setItem(InventorySlot.ROW_1_SLOT_9, CYAN_PLACEHOLDER);
                setItem(InventorySlot.ROW_2_SLOT_9, CYAN_PLACEHOLDER);
                setItem(InventorySlot.ROW_3_SLOT_9, CYAN_PLACEHOLDER);

                Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                    Sound.done(p);

                    setItem(InventorySlot.ROW_1_SLOT_2, CYAN_PLACEHOLDER);
                    setItem(InventorySlot.ROW_1_SLOT_3, CYAN_PLACEHOLDER);
                    setItem(InventorySlot.ROW_1_SLOT_7, CYAN_PLACEHOLDER);
                    setItem(InventorySlot.ROW_1_SLOT_8, CYAN_PLACEHOLDER);

                    setItem(InventorySlot.ROW_3_SLOT_2, CYAN_PLACEHOLDER);
                    setItem(InventorySlot.ROW_3_SLOT_3, CYAN_PLACEHOLDER);
                    setItem(InventorySlot.ROW_3_SLOT_7, CYAN_PLACEHOLDER);
                    setItem(InventorySlot.ROW_3_SLOT_8, CYAN_PLACEHOLDER);

                    Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                        Sound.done(p);

                        setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.STICK, 1, 0)
                                        .displayName("§eFangen §8| §fLobbygame")
                                        .lore("§7§oFange andere Spieler", "§7§ooder laufe vor anderen weg", "", "§8» §f§nLinksklick§8 | §7§oSpielen")
                                        .create(),

                                e -> {
                                    p.closeInventory();
                                    LobbyGames.getInstance().getGame(Catch.class).joinGame(p);
                                });

                        setItem(InventorySlot.ROW_2_SLOT_8, new ItemBuilder(Material.DIAMOND_BOOTS, 1, 0)
                                        .displayName("§bJumpAndRun §8| §fLobbygame")
                                        .lore("§7§oBesuche die JumpAndRun Tafel", "§7§oUm alle Jump and Runs zu sehen", "§7§oUnd um sie zu starten", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren", "§8» §f§nRechtsklick§8 | §7§oSchnellbeitritt")
                                        .create(),

                                e -> {
                                    if (e.getClick().equals(ClickType.RIGHT)) {
                                        new JumpNRunInventory(p);
                                    } else if (e.getClick().equals(ClickType.LEFT)) {
                                        player.closeInventory();
                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("jumpandrun-board");
                                    }
                                });

                        Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                            Sound.done(p);

                            setItem(InventorySlot.ROW_5_SLOT_3, new ItemBuilder(Material.NETHER_STAR, 1, 0)
                                            .displayName("§fMinigames")
                                            .lore("§7§oSchaue dir unsere Spielmodi an", "§7§ound telepotiere dich zu ihnen!", "", "§8» §f§nLinksklick§8 | §7§oÖffnen")
                                            .create(),
                                    e -> {
                                        new MinigamesInventory(p);
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
                                setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.IRON_SWORD, 1, 0)
                                                .displayName("§cOne-Hit §8| §fLobbygame")
                                                .lore("§7§oSpiele ein bekannten Modi", "§7§omit deinen Freunden auf der Lobby", "", "§8» §f§nSpieler | §7§o" + LobbyGames.getInstance().getGame(OneHit.class).getPlaying().size(), "§8» §f§nLinksklick§8 | §7§oSpielen")
                                                .create(),
                                        e -> {
                                            p.closeInventory();
                                            LobbyGames.getInstance().getGame(OneHit.class).joinGame(p);
                                        });

                                setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.WOOD_AXE, 1, 0)
                                                .displayName("§5Gungame §8| §fLobbygame")
                                                .lore("§7§oSchlage deine Gegner ins Wasser", "§7§ound steige Level auf!", "", "§8» §f§nSpieler | §7§o" + LobbyGames.getInstance().getGame(GunGame.class).getPlaying().size(), "§8» §f§nLinksklick§8 | §7§oSpielen")
                                                .create(),
                                        e -> {
                                            p.closeInventory();
                                            LobbyGames.getInstance().getGame(GunGame.class).joinGame(p);
                                        });

                                setItem(InventorySlot.ROW_2_SLOT_5, LIGHT_BLUE_PLACEHOLDER);
                            }, 1L);
                        }, 1L);
                    }, 2L);
                }, 2L);
            }, 2L);
        }, 2L);

        openInventory();
    }

}