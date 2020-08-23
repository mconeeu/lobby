package eu.mcone.lobby.story.inventory.office;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.inventory.anvil.AnvilSlot;
import eu.mcone.coresystem.api.bukkit.inventory.anvil.CoreAnvilInventory;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.story.LobbyStory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SecretaryInventory extends CoreInventory {

    private static final CoreAnvilInventory ANVIL_INVENTORY = CoreSystem.getInstance().createAnvilInventory(event -> {
        String name = event.getName();

        Player p = event.getPlayer();
        Player t = Bukkit.getPlayer(name);

        if (t != null && !t.equals(event.getPlayer())) {
            if (event.getSlot().equals(AnvilSlot.OUTPUT)) {
                LobbyStory.getInstance().getOfficeManager().inviteToOffice(p, t);

                p.playSound(p.getLocation(), Sound.NOTE_STICKS, 1, 1);
                p.closeInventory();
            }
        } else {
            LobbyPlugin.getInstance().getMessenger().sendError(p, "Dieser Spieler !["+name+"] ist nicht online!");
        }
    }).setItem(
            AnvilSlot.INPUT_LEFT,
            new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13)
                    .displayName("?")
                    .create()
    );

    public SecretaryInventory(Player p) {
        super("§8» §b§lSekretärin §8| §fMenü", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);

        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.TRIPWIRE_HOOK, 1, 0).displayName("§fZum Büro einladen")
                .lore("§fLade einen Spieler zu deinen Büro ein")
                .create(), e -> {
            ANVIL_INVENTORY.open(p);
        });

        openInventory();
    }
}
