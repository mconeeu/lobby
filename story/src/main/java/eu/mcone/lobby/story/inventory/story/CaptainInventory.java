package eu.mcone.lobby.story.inventory.story;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.enums.StoryProgress;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class CaptainInventory extends CoreInventory {

    public CaptainInventory(Player p) {
        super("§f§lWähle dein Ziel", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);


        setItem(InventorySlot.ROW_2_SLOT_3, new Skull(p.getName(), 1).toItemBuilder().displayName("§5§lCommunity / Festival").lore("§7§oTreffe deine Freunde oder Creator", "§7§oauf dem Festival").create(), e -> {
            if (p.getItemInHand().equals(LobbyItem.BOAT_PASS.getItemStack())) {
                p.getInventory().remove(p.getItemInHand());
                lp.removeLobbyItem(LobbyItem.BOAT_PASS);
                CoreSystem.getInstance().getChannelHandler().createSetRequest(p, "CONNECT", "Community");
                p.closeInventory();
            } else {
                p.closeInventory();
                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Nimm das Ticket in die Hand, du Fisch Gesicht!");
            }
        });

        setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.SAND).displayName("§f§lParadise-Island").lore("§7§oMache Urlaub und entspanne", "§7§oauf der schönen Insel", "§7§omitten im Paradis!").create(), e -> {

            //NORMAL SPAWN NOTHING STORY

            if (lp.getProgressId() < StoryProgress.MARVIN_KILL.getId()) {
                if (p.getItemInHand().equals(LobbyItem.BOAT_PASS.getItemStack())) {
                    p.getInventory().remove(p.getItemInHand());
                    lp.removeLobbyItem(LobbyItem.BOAT_PASS);
                    LobbyWorld.PARADISE_ISLAND.getWorld().teleportSilently(p, "spawn");
                    p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Du bist nun in Paradise Island");
                    p.closeInventory();
                } else {
                    p.closeInventory();
                    p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Nimm das Ticket in die Hand, du Fisch Gesicht!");
                }
                //TODO: Umändern !!wenn!! Teil 2 eröffnet werden soll!!!!
             //   if (lp.getProgressId() == 9) {
            //        p.sendMessage("§8[§7§l!§8] §fServer §8» §fFunkgerät §8|§7 Bringg Bringgg  Hallo " + p.getName() + "§7 ich sehe das du auf der Insel bist und wollte somit fragen ob du Sparow gefunden hast? Ich schreib dir einfach in ein paar minuten zurück.");
           //     }

                // SPAWN CHAPTER 3

            } else if (lp.getProgressId() >= StoryProgress.EDWARD_LABOR_START.getId()) {
                if (p.getItemInHand().equals(LobbyItem.BOAT_PASS.getItemStack())) {
                    p.getInventory().remove(p.getItemInHand());
                    lp.removeLobbyItem(LobbyItem.BOAT_PASS);
                    LobbyWorld.PARADISE_ISLAND.getWorld().teleportSilently(p, "spawn");
                    p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Du bist nun in Paradise Island");
                    p.closeInventory();
                } else {
                    p.closeInventory();
                    p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Nimm das Ticket in die Hand, du Fisch Gesicht!");
                }

                //DESTROY

            } else {
                if (p.getItemInHand().equals(LobbyItem.BOAT_PASS.getItemStack())) {
                    p.getInventory().remove(p.getItemInHand());
                    lp.removeLobbyItem(LobbyItem.BOAT_PASS);
                    LobbyWorld.DESTROYED_PARADISE_ISLAND.getWorld().teleportSilently(p, "spawn");
                    p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Du bist nun im zerstörten Paradise Island");
                    p.closeInventory();
                } else {
                    p.closeInventory();
                    p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Nimm das Ticket in die Hand, du Fisch Gesicht!");
                }
            }


        });

        openInventory();

    }
}
