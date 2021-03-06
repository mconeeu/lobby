package eu.mcone.lobby.story.inventory.office;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Msg;
import eu.mcone.coresystem.api.bukkit.facades.Sound;
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
import org.bukkit.entity.Player;

public class SecretaryInventory extends CoreInventory {

    private static final CoreAnvilInventory ANVIL_INVENTORY = CoreSystem.getInstance().createAnvilInventory(event -> {
        if (event.getSlot().equals(AnvilSlot.OUTPUT)) {
            String name = event.getName().replace(" ", "");

            Player p = event.getPlayer();
            Player t = Bukkit.getPlayer(name);

            if (t != null && !t.equals(event.getPlayer())) {
                if (!LobbyPlugin.getInstance().getVanishManager().isInSilentLobby(p)) {
                    LobbyStory.getInstance().getOfficeManager().inviteToOffice(p, t);
                    Sound.click(p);
                    p.closeInventory();
                } else {
                    new ConfirmSilentLobbyQuitInventory(p, t, ConfirmSilentLobbyQuitInventory.Target.OWNER, () -> {
                        LobbyPlugin.getInstance().getVanishManager().quitSilentLobby(p);

                        LobbyStory.getInstance().getOfficeManager().inviteToOffice(p, t);
                        Sound.click(p);
                        p.closeInventory();
                    });
                }
            } else {
                Msg.sendError(p, "Dieser Spieler ![" + name + "] ist nicht online!");
            }
        }
    }).setItem(
            AnvilSlot.INPUT_LEFT,
            new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13)
                    .displayName(" ")
                    .create()
    );

    public SecretaryInventory(Player p) {
        super("??8?? ??b??lSekret??rin ??8| ??fMen??", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);

        setItem(
                InventorySlot.ROW_2_SLOT_5,
                new ItemBuilder(Material.TRIPWIRE_HOOK, 1, 0).displayName("??fZum B??ro einladen")
                        .lore("??fLade einen Spieler zu deinen B??ro ein")
                        .create(),
                e -> ANVIL_INVENTORY.open(p)
        );

        openInventory();
    }
}
