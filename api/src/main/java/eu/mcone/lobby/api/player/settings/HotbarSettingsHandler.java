package eu.mcone.lobby.api.player.settings;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.hotbar.HotbarSettings;
import eu.mcone.lobby.api.player.hotbar.items.HotbarItemEnum;
import org.bukkit.entity.Player;

public class HotbarSettingsHandler implements HotbarSettings {


    private final String compassDisplayName = HotbarItem.COMPASS.getItemMeta().getDisplayName();
    private final String lobbySwitcherDisplayName = HotbarItem.LOBBY_CHANGER.getItemMeta().getDisplayName();
    private final String backpackDisplayName = HotbarItem.BACKPACK.getItemMeta().getDisplayName();

    @Override
    public void updateInventory(Player p, LobbyPlayer lp) {

        if (lp.getSettings().getLobbySwitcherItem().getItem() != null) {
            p.getInventory().setItem(lp.getSettings().getLobbySwitcherSlot(), lp.getSettings().getLobbySwitcherItem().getItem().displayName(lobbySwitcherDisplayName).create());
        }
        if (lp.getSettings().getCompassItem().getItem() != null) {
            p.getInventory().setItem(lp.getSettings().getCompassSlot(), lp.getSettings().getCompassItem().getItem().displayName(compassDisplayName).create());
        }

        p.getInventory().setItem(3, null);
        p.getInventory().setItem(2, null);

        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        p.getActivePotionEffects().clear();


        //SILENTLOBBY
        if (LobbyPlugin.getInstance().getVanishManager().isInSilentLobby(p)) {
            p.getInventory().setItem(lp.getSettings().getPlayerHiderSlot(), HotbarItem.LOBBY_HIDER_UNAVAILABLE_SILENT_LOBBY);
        } else {

            if (p.hasPermission("lobby.silenthub")) {
                if (lp.getSettings().isHotbarSilentHub()) {
                    if (lp.getSettings().isHotbarSilentHub()) {
                        p.getInventory().setItem(lp.getSettings().getSilentlobbySlot(), HotbarItem.SILENT_LOBBY_JOIN);
                    }
                } else {
                    p.getInventory().setItem(lp.getSettings().getSilentlobbySlot(), null);
                }
            }


            p.getInventory().setItem(lp.getSettings().getPlayerHiderSlot(), LobbyPlugin.getInstance().getVanishManager().getVanishPlayerVisibility(p).getItem());
        }


        //NICK
        if (p.hasPermission("system.bungee.nick")) {
            p.getInventory().setItem(lp.getSettings().getNickSlot(), CoreSystem.getInstance().getCorePlayer(p).isNicked() ? HotbarItem.NICK_ENABLED : HotbarItem.NICK_DISABLED);
        }

        //BACKPACK
        if (lp.getSettings().getBackpackItem().getItem() != null) {
            p.getInventory().setItem(lp.getSettings().getBackpackSlot(), lp.getSettings().getBackpackItem().getItem().displayName(backpackDisplayName).create());
        }

        //GADGETS
        GamePlayer gp = LobbyPlugin.getInstance().getGamePlayer(p);
        if (lp.getSettings().isHotbarSilentHub()) {
            gp.setLastUsedBackPackItemInventar(3, 2);
        } else {
            gp.setLastUsedBackPackItemInventar(2, 2);
        }


        //PROFILE
        if (lp.getSettings().getProfileItem().getItem().equals(HotbarItemEnum.SKULL.getItem())) {
            p.getInventory().setItem(
                    lp.getSettings().getProfileSlot(),
                    HotbarItem.getProfile(cp.getSkin())
            );
        } else {
            String profileDisplayNAme = HotbarItem.PROFILE_DISPLAY_NAME;
            p.getInventory().setItem(
                    lp.getSettings().getProfileSlot(),
                    lp.getSettings().getProfileItem().getItem().displayName(profileDisplayNAme).create()
            );
        }

    }

}

