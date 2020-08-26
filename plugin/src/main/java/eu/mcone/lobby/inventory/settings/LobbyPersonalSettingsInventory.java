package eu.mcone.lobby.inventory.settings;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.scoreboard.SidebarObjective;
import eu.mcone.lobby.api.player.settings.JoinPlayerVisibility;
import eu.mcone.lobby.api.player.settings.LobbySettings;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;

public class LobbyPersonalSettingsInventory extends CoreInventory {

    public static final ItemStack RIGHT_ITEM = Skull.fromUrl("http://textures.minecraft.net/texture/1b6f1a25b6bc199946472aedb370522584ff6f4e83221e5946bd2e41b5ca13b", 1).toItemBuilder().displayName("§7Nächste Seite").lore(new String[]{"", "§8» §f§nLinksklick§8 | §7§oMehr Anzeigen"}).create();

    public LobbyPersonalSettingsInventory(Player p) {
        super("§8» §c§lPersönlich", p, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
        LobbySettings settings = lp.getSettings();


        setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.TRIPWIRE_HOOK, 1, 0).displayName("§f§lJoinTyp").create());
        setItem(InventorySlot.ROW_3_SLOT_2, settings.getJoinPlayerVisibility().getItem().create(), e -> {
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

        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.LEATHER_HELMET, 1, 0).displayName("§f§lTragen lassen").create());
        if (settings.isStacking()) {
            setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oAndere Spieler können dich", "§7§ohin und her tragen").create(), e -> {
                settings.setStacking(false);
                setSettings(p, lp);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oAndere Spieler können dich nicht", "§7§ohin und her tragen").create(), e -> {
                settings.setStacking(true);
                setSettings(p, lp);
            });
        }

        setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.SIGN, 1, 0).displayName("§f§lScoreboard anzeigen").create());
        if (settings.isScoreboard()) {
            setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um nicht mehr das Scoreboard", "§7§oauf der rechten Seite zu sehen").create(), e -> {
                settings.setScoreboard(false);
                CoreSystem.getInstance().getCorePlayer(p).getScoreboard().getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
                setSettings(p, lp);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um das Scoreboard", "§7§oauf der rechte Seite zu sehen").create(), e -> {
                CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
                settings.setScoreboard(true);
                cp.getScoreboard().setNewObjective(new SidebarObjective());
                setSettings(p, lp);
            });
        }

        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.COMPASS, 1, 0).displayName("§f§lTeleport Animation abspielen").create());
        if (settings.isAllowAnimation()) {
            setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um die Animation", "§7§onicht zu sehen und um", "§7§osofort telepotiert zu werden").create(), e -> {
                settings.setAllowAnimation(false);
                setSettings(p, lp);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um die Animation", "§7§ozu sehen").create(), e -> {
                settings.setAllowAnimation(true);
                setSettings(p, lp);
            });
        }

        setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.LEATHER_BOOTS, 1, 0).displayName("§f§lErhalte Rang Schuhe").create());
        if (settings.isRankBoots()) {
            setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um keine Rang Schuhe zu erhalten", "§7§owenn du den Server beitritts!").create(), e -> {
                settings.setRankBoots(false);
                player.getInventory().setBoots(null);
                setSettings(p, lp);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um Rang Schuhe zu erhalten", "§7§owenn du den Server beitritts!").create(), e -> {
                settings.setRankBoots(true);
                LobbyPlugin.getInstance().getBackpackManager().setRankBoots(p);
                setSettings(p, lp);
            });
        }

        setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.CHEST, 1, 0).displayName("§f§lInventar Animation abspielen").create());
        if (settings.isInventoryAnimation()) {
            setItem(InventorySlot.ROW_3_SLOT_7, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um die Animation", "§7§onicht zu sehen und damit", "§7§osich das Inventar sofort öffnet").create(), e -> {
                settings.setInventoryAnimation(false);
                setSettings(p, lp);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_7, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um die Inventar Animation", "§7§ozu sehen").create(), e -> {
                settings.setInventoryAnimation(true);
                setSettings(p, lp);
            });
        }

        setItem(InventorySlot.ROW_2_SLOT_8, new ItemBuilder(Material.FIREWORK_CHARGE, 1, 0).displayName("§f§lGadgets").create());
        GamePlayer gp = lp.getGamePlayer();
        if (gp.getSettings().isEnableGadgets()) {
            setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um die Gadgets", "§7§ofür dich selbst zu deaktivieren", "§7§odann erhälst du keine Gadgets", "§7§oEffekte mehr und du selbst kannst", "§7§okeine mehr ausrüsten").create(), e -> {
                gp.getSettings().setEnableGadgets(false);
                new LobbyPersonalSettingsInventory(p);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um die Gadgets für dich zu aktivieren", "§7§ound um die Effekte der anderen zu sehen").create(), e -> {
                gp.getSettings().setEnableGadgets(true);
                new LobbyPersonalSettingsInventory(p);
            });
        }

        setItem(InventorySlot.ROW_4_SLOT_8, RIGHT_ITEM, e ->
                new LobbyPersonalExtendedInventory(p));

        setItem(InventorySlot.ROW_4_SLOT_9, BACK_ITEM, e ->
                new LobbySettingsInventory(p));

        openInventory();
    }

    private void setSettings(Player p, LobbyPlayer lp) {
        lp.saveData();
        LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.CHICKEN_EGG_POP);
        new LobbyPersonalSettingsInventory(p);
    }
}
