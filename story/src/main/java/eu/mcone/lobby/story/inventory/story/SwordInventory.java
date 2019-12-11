package eu.mcone.lobby.story.inventory.story;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.BankProgress;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SwordInventory extends CoreInventory {


    public SwordInventory(Player p) {
        super("§f§lSicherheitstruhe", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getGamePlayer(p);

        setItem(InventorySlot.ROW_2_SLOT_5, LobbyItem.IRON_SWORD.getItemStack(), e -> {
            if (!LobbyItem.IRON_SWORD.has(lp)) {
                LobbyItem.IRON_SWORD.add(lp);
                lp.setBankProgress(BankProgress.BANK_ROBBERY_START);
                Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                    p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8|§7 Endlich hast du das Schwert jetzt komm schnell zurück ins Büro damit wir denn Bankraub starten können!");
                }, 20L);
            } else {
                LobbyPlugin.getInstance().getMessager().send(p, "§4Du besitzt diese Item bereits!");
            }
            p.closeInventory();
        });

        openInventory();
    }
}
