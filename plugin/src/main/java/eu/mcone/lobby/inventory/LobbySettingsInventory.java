/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.LobbySettings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class LobbySettingsInventory extends CoreInventory {

    public LobbySettingsInventory(Player p) {
        super("§8» §c§lLobby Einstellung", p, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());
        LobbySettings settings = lp.getSettings();


        setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.CHEST, 1, 0).displayName("§f§lTraiding").create());

        if (settings.isAllowTrading()) {
            setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um das Handeln", "§7§omit anderen Spielern", "§7§ozu deaktivieren").create(), e -> {
                settings.setAllowTrading(false);
                setSettings(p, lp);
                p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um das Handeln", "§7§omit anderen Spielern", "§7§ozu aktivieren").create(), e -> {
                settings.setAllowTrading(true);
                setSettings(p, lp);
                p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            });
        }


        if (p.hasPermission("lobby.silenthub")) {
            setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.TNT, 1, 0).displayName("§f§lSpawne in deiner Privaten Lobby").create());

            if (settings.isSilentHubActivatedOnJoin()) {
                setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um das Spawnen", "§7§oin der privaten Lobby", "§7§ozu aktivieren").create(), e -> {
                    settings.setSilentHubActivatedOnJoin(false);
                    setSettings(p, lp);
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                });
            } else {
                setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um das Spawnen", "§7§oin der privaten Lobby", "§7§ozu aktivieren").create(), e -> {
                    settings.setSilentHubActivatedOnJoin(true);
                    setSettings(p, lp);
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                });
            }
        }


        setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.NETHER_STAR, 1, 0).displayName("§f§lBeim Joinen zum Spawn teleportieren").create());

        if (settings.isTeleportOnJoin()) {
            setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um beim joinen nicht", "§7§ozum Spawn teleportiert zu", "§7§owerden").create(), e -> {
                settings.setTeleportOnJoin(false);
                setSettings(p, lp);
                p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um beim joinen", "§7§ozum Spawn teleportiert zu", "§7§owerden").create(), e -> {
                settings.setTeleportOnJoin(true);
                setSettings(p, lp);
                p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            });
        }


        setItem(InventorySlot.ROW_4_SLOT_9, new ItemBuilder(Material.IRON_DOOR, 1, 0).displayName("§7§l↩ Zurück").create(), e -> Bukkit.dispatchCommand(p, "profile"));

        openInventory();
    }

    private void setSettings(Player p, LobbyPlayer lp) {
        lp.saveData();
        new LobbySettingsInventory(p);
    }

}

