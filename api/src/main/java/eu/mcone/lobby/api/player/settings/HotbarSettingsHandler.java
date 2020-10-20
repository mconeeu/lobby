package eu.mcone.lobby.api.player.settings;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.hotbar.HotbarGeneralCategorys;
import eu.mcone.lobby.api.player.hotbar.HotbarSettings;
import eu.mcone.lobby.api.player.vanish.VanishPlayerVisibility;
import org.bukkit.entity.Player;

public class HotbarSettingsHandler implements HotbarSettings {

    @Override
    public void updateInventory(Player p, LobbyPlayer lp) {
        for (HotbarGeneralCategorys cat : HotbarGeneralCategorys.values()) {
            int slot = lp.getSettings().calculateSlots().get(cat).getSlot();

            p.getInventory().setItem(2, null);

            if (!cat.equals(HotbarGeneralCategorys.PLAYER_HIDER)) {
                ItemBuilder item = cat.equals(HotbarGeneralCategorys.PROFILE) && cat.getItem(lp).isMainItem() ? HotbarItem.getProfile(lp.getCorePlayer().getSkin()) : cat.getItem(lp).getItem();
                item.displayName(cat.getDisplayName());

                p.getInventory().setItem(slot, item.create());
            } else {
                if (LobbyPlugin.getInstance().getVanishManager().isInSilentLobby(p)) {
                    p.getInventory().setItem(slot, HotbarItem.LOBBY_HIDER_UNAVAILABLE_SILENT_LOBBY);
                } else if (LobbyPlugin.getInstance().getVanishManager().isInOffice(p)) {
                    p.getInventory().setItem(slot, HotbarItem.LOBBY_HIDER_UNAVAILABLE_OFFICE);
                } else {
                    p.getInventory().setItem(slot, LobbyPlugin.getInstance().getVanishManager().getVanishPlayerVisibility(p).getItem());
                }
            }
        }

        if (p.hasPermission("system.bungee.nick")) {
            p.getInventory().setItem(6, CoreSystem.getInstance().getCorePlayer(p).isNicked() ? HotbarItem.NICK_ENABLED : HotbarItem.NICK_DISABLED);
        }

        if (!LobbyPlugin.getInstance().getVanishManager().isInSilentLobby(p) && !LobbyPlugin.getInstance().getVanishManager().isInOffice(p) && LobbyPlugin.getInstance().getVanishManager().getVanishPlayerVisibility(p).equals(VanishPlayerVisibility.EVERYBODY)) {
            LobbyPlugin.getInstance().getBackpackManager().setCurrentBackpackItem(LobbyPlugin.getInstance().getGamePlayer(p));
        }

        p.getActivePotionEffects().clear();

    }

}
