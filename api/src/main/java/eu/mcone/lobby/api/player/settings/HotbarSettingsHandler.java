package eu.mcone.lobby.api.player.settings;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.PlayerInventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.hotbar.HotbarCategory;
import eu.mcone.lobby.api.player.hotbar.HotbarSettings;
import eu.mcone.lobby.api.player.hotbar.items.HotbarItem;
import eu.mcone.lobby.api.player.vanish.VanishPlayerVisibility;
import org.bukkit.entity.Player;

import java.util.Objects;

public class HotbarSettingsHandler implements HotbarSettings {

    @Override
    public void updateInventory(Player p, LobbyPlayer lp) {
        for (HotbarCategory cat : HotbarCategory.values()) {
            int slot = lp.getSettings().calculateSlots().get(cat).getSlot();

            p.getInventory().setItem(PlayerInventorySlot.HOTBAR_SLOT_3, null);

            if (!cat.equals(HotbarCategory.PLAYER_HIDER)) {
                HotbarItem hotbarItem = Objects.requireNonNull(cat.getItem(lp));
                ItemBuilder item = cat.equals(HotbarCategory.PROFILE) && hotbarItem.isMainItem()
                        ? eu.mcone.lobby.api.player.HotbarItem.getProfile(!lp.getCorePlayer().isNicked() ? lp.getCorePlayer().getSkin() : lp.getCorePlayer().getNick().getSkinInfo())
                        : hotbarItem.getItem();

                p.getInventory().setItem(slot, item.displayName(cat.getDisplayName()).create());
            } else {
                if (LobbyPlugin.getInstance().getVanishManager().isInSilentLobby(p)) {
                    p.getInventory().setItem(slot, eu.mcone.lobby.api.player.HotbarItem.LOBBY_HIDER_UNAVAILABLE_SILENT_LOBBY);
                } else if (LobbyPlugin.getInstance().getVanishManager().isInOffice(p)) {
                    p.getInventory().setItem(slot, eu.mcone.lobby.api.player.HotbarItem.LOBBY_HIDER_UNAVAILABLE_OFFICE);
                } else {
                    p.getInventory().setItem(slot, LobbyPlugin.getInstance().getVanishManager().getVanishPlayerVisibility(p).getItem());
                }
            }
        }

        if (p.hasPermission("system.bungee.nick")) {
            p.getInventory().setItem(6, CoreSystem.getInstance().getCorePlayer(p).isNicked() ? eu.mcone.lobby.api.player.HotbarItem.NICK_ENABLED : eu.mcone.lobby.api.player.HotbarItem.NICK_DISABLED);
        }

        if (!LobbyPlugin.getInstance().getVanishManager().isInSilentLobby(p) && !LobbyPlugin.getInstance().getVanishManager().isInOffice(p) && LobbyPlugin.getInstance().getVanishManager().getVanishPlayerVisibility(p).equals(VanishPlayerVisibility.EVERYBODY)) {
            LobbyPlugin.getInstance().getBackpackManager().setCurrentBackpackItem(LobbyPlugin.getInstance().getGamePlayer(p), false);
        }
    }

}
