package eu.mcone.lobby.story.inventory.story;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.gamesystem.api.enums.Item;
import eu.mcone.gamesystem.api.game.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class CaptainInventory extends CoreInventory {

    public CaptainInventory(Player p) {
        super("§f§lWähle dein Ziel", p, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lobbyPlayer = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());
        GamePlayer gamePlayer = LobbyPlugin.getInstance().getGamePlayer(p.getUniqueId());


        setItem(InventorySlot.ROW_2_SLOT_3, new Skull(p.getName(), 1).toItemBuilder().displayName("§5§lCommunity / Festival").lore("§7§oTreffe deine Freunde oder Yotuber\nauf dem Festival").create(), e -> {
            if (p.getItemInHand().equals(Item.BOAT_PASS.getItemStack())) {
                p.getInventory().remove(p.getItemInHand());
                gamePlayer.removeItem(Item.BOAT_PASS);
                CoreSystem.getInstance().getChannelHandler().createSetRequest(p, "CONNECT", "Community");
                p.closeInventory();
            } else {
                p.closeInventory();
                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Nimm das Ticket in die Hand, du Fisch Gesicht!");
            }
        });

        setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.SAND).displayName("§f§lParadise-Island").lore("§7§oMache Urlaub und entspanne \nauf der schönen Insel \nmitten im Paradise!").create(), e -> {

            //if (lobbyPlayer.getProgressId() < Progress.MARVIN_KILL.getId()) {
            if (p.getItemInHand().equals(Item.BOAT_PASS.getItemStack())) {
                p.getInventory().remove(p.getItemInHand());
                gamePlayer.removeItem(Item.BOAT_PASS);
                LobbyWorld.PARADISE_ISLAND.getWorld().teleportSilently(p, "spawn");
                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Du bist nun in Paradise Island");
                p.closeInventory();
            } else {
                p.closeInventory();
                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Nimm das Ticket in die Hand, du Fisch Gesicht!");
            }
            // if (lobbyPlayer.getProgressId() == 9) {
            //  p.sendMessage("§8[§7§l!§8] §fServer §8» §fFunkgerät §8|§7 Bringg Bringgg  Hallo " + p.getName() + "§7 ich sehe das du auf der Insel bist und wollte so mit fragen ob du Sparow gefunden hab? Ich schreib dir einfach in ein paar minuten zurück.");
            // }
            // } else {
            //LobbyWorld.DESTROYED_PARADISE_ISLAND.getWorld().teleportSilently(p, "spawn");
            // p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Du bist nun im zerstörten Paradise Island");
        });

        openInventory();

    }
}
