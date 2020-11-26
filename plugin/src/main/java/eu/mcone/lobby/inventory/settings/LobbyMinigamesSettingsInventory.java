package eu.mcone.lobby.inventory.settings;

import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.coresystem.api.bukkit.inventory.settings.SettingsInventory;
import eu.mcone.coresystem.api.bukkit.inventory.settings.settings.BooleanSetting;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.settings.LobbySettings;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LobbyMinigamesSettingsInventory extends SettingsInventory {

    public LobbyMinigamesSettingsInventory(Player p) {
        super("§8» §c§lLobby Games", p, e -> new LobbySettingsInventory(p));
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
        LobbySettings settings = lp.getSettings();

        addSetting(
                new BooleanSetting(new ItemBuilder(Material.COMPASS, 1, 0).displayName("§f§lTeleport Animation abspielen").create(), null)
                        .onChoose((player, result) -> {
                            settings.setAllowAnimation(result);
                            setSettings(p, lp);
                        })
                        .optionFinder(player -> settings.isAllowAnimation())
                        .setEnabledDescription("§7§oKlicke um die Animation", "§7§ozu überspringen und um", "§7§osofort telepotiert zu werden")
                        .setDisabledDescription("§7§oKlicke um die Animation", "§7§ozu sehen")
                        .create()
        );

        addSetting(
                new BooleanSetting(new ItemBuilder(Material.NOTE_BLOCK, 1, 0).displayName("§f§lHotbar wechsel Sound").create(), null)
                        .onChoose((player, result) -> {
                            settings.setHotbarChangeSound(result);
                            setSettings(p, lp);
                        })
                        .optionFinder(player -> settings.isHotbarChangeSound())
                        .setEnabledDescription("§7§oKlicke um keinen Hotbar wechsel", "§7§oSound mehr zu erhalten!")
                        .setDisabledDescription("§7§oKlicke um wieder einen Hotbar wechsel", "§7§oSound zu erhalten")
                        .create()
        );

        addSetting(
                new BooleanSetting(new ItemBuilder(Material.CHEST, 1, 0).displayName("§f§lInventar Animation abspielen").create(), null)
                        .onChoose((player, result) -> {
                            settings.setInventoryAnimation(result);
                            setSettings(p, lp);
                        })
                        .optionFinder(player -> settings.isInventoryAnimation())
                        .setEnabledDescription("§7§oKlicke um die Animation", "§7§onicht zu sehen und damit", "§7§osich das Inventar sofort öffnet")
                        .setDisabledDescription("§7§oKlicke um die Inventar Animation", "§7§ozu sehen")
                        .create()
        );

        addSetting(
                new BooleanSetting(new ItemBuilder(Material.WATCH, 1, 0).displayName("§f§lTag-Nacht Zyklus").create(), null)
                        .onChoose((player, result) -> {
                            settings.setRealTime(result);
                            setSettings(p, lp);
                        })
                        .optionFinder(player -> settings.isRealTime())
                        .setEnabledDescription("§7§oKlicke um in der Lobby", "§7§oimmer Tag zu haben")
                        .setDisabledDescription("§7§oKlicke um die Zeit in der", "§7§oLobby an die echte Zeit", "§7§oanzupassen")
                        .create()
        );

        openInventory();
    }

    private void setSettings(Player p, LobbyPlayer lp) {
        lp.saveData();
        Sound.click(p);
        new LobbyMinigamesSettingsInventory(p);
    }
}
