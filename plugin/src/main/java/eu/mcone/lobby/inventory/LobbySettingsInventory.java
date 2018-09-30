/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.util.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.LobbySettings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class LobbySettingsInventory extends CoreInventory {

    public LobbySettingsInventory(Player p) {
        super("§8» §c§lLobby Einstellung", p, InventorySlot.ROW_4, Option.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());
        LobbySettings settings = lp.getSettings();

        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.TNT, 1, 0).displayName("§f§lSpawne in deiner Privaten Lobby").create());

        if (settings.isSilentHubActivatedOnJoin()) {
            setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke zum deaktivieren").create(), e -> {
                settings.setSilentHubActivatedOnJoin(false);
                setSettings(settings, p, lp);
                p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke zum aktivieren").create(), e -> {
                settings.setSilentHubActivatedOnJoin(true);
                setSettings(settings, p, lp);
                p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            });
        }


        setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.HARD_CLAY, 1, 0).displayName("§f§lTraiding").create());

        if (settings.isAllowTrading()) {
            setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke zum deaktivieren").create(), e -> {
                settings.setAllowTrading(false);
                setSettings(settings, p, lp);
                p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke zum aktivieren").create(), e -> {
                settings.setAllowTrading(true);
                setSettings(settings, p, lp);
                p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            });
        }

        setItem(InventorySlot.ROW_4_SLOT_9, new ItemBuilder(Material.IRON_DOOR, 1, 0).displayName("§7§l↩ Zurück").create(), e -> Bukkit.dispatchCommand(p, "profile"));

        openInventory();
    }

    private void setSettings(LobbySettings settings, Player p, LobbyPlayer lp) {
        lp.setSettings(settings);
        lp.updateSettings();

        new LobbySettingsInventory(p);
    }

}

