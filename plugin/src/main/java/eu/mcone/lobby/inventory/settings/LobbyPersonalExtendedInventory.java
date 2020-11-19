package eu.mcone.lobby.inventory.settings;

import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.settings.LobbySettings;
import eu.mcone.lobby.api.player.settings.SoundManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LobbyPersonalExtendedInventory extends CoreInventory {

    public static final ItemStack LEFT_ITEM = Skull.fromUrl("http://textures.minecraft.net/texture/3ebf907494a935e955bfcadab81beafb90fb9be49c7026ba97d798d5f1a23", 1).toItemBuilder().displayName("§7Vorherige Seite").lore(new String[]{"", "§8» §f§nLinksklick§8 | §7§oMehr Anzeigen"}).create();

    public LobbyPersonalExtendedInventory(Player p) {
        super("§8» §c§lPersönlich erweitert", p, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
        LobbySettings settings = lp.getSettings();


        setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.STORAGE_MINECART, 1, 0).displayName("§f§lTauschen").create());
        GamePlayer gp = lp.getGamePlayer();
        if (gp.getSettings().isEnableTraiding()) {
            setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um das Tauschen von", "§7§oRucksack Items zu deaktivieren").create(), e -> {
                gp.getSettings().setEnableTraiding(false);
                new LobbyPersonalExtendedInventory(p);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um das Tauschen von", "§7§oRucksack Items zu aktivieren").create(), e -> {
                gp.getSettings().setEnableTraiding(true);
                new LobbyPersonalExtendedInventory(p);
            });
        }

        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.NOTE_BLOCK, 1, 0).displayName("§f§lRuhe Modus").create());
        if (settings.isDoNotDisturb()) {
            setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um den Stum Modus", "§7§ozu deaktiveren").create(), e -> {
                settings.setDoNotDisturb(false);
                setSettings(p, lp);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um den Stum Modus", "§7§ozu aktivieren").create(), e -> {
                settings.setDoNotDisturb(true);
                setSettings(p, lp);
            });
        }

        setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.RECORD_7, 1, 0).displayName("§f§lNavigator Ton").create());
        setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.NOTE_BLOCK, 1, 0).displayName("Navigator: " + settings.getNavigatorSound().getSoundName()).lore("", "§8» §f§nLinksklick§8 | §7§oÄndern").create(), e -> {
            switch (settings.getNavigatorSound()) {
                case ORB_PICKUP: {
                    settings.setNavigatorSound(SoundManager.NOTE_BASS);
                    setSettings(p, lp);
                    break;
                }
                case NOTE_BASS: {
                    settings.setNavigatorSound(SoundManager.NOTE_BASS_GUITAR);
                    setSettings(p, lp);
                    break;
                }
                case NOTE_BASS_GUITAR: {
                    settings.setNavigatorSound(SoundManager.FIZZ);
                    setSettings(p, lp);
                    break;
                }
                case FIZZ: {
                    settings.setNavigatorSound(SoundManager.SWIM);
                    setSettings(p, lp);
                    break;
                }
                case SWIM: {
                    settings.setNavigatorSound(SoundManager.VILLAGER_DEATH);
                    setSettings(p, lp);
                    break;
                }
                case VILLAGER_DEATH: {
                    settings.setNavigatorSound(SoundManager.EAT);
                    setSettings(p, lp);
                    break;
                }
                case EAT: {
                    settings.setNavigatorSound(SoundManager.ANVIL_BREAK);
                    setSettings(p, lp);
                    break;
                }
                case ANVIL_BREAK: {
                    settings.setNavigatorSound(SoundManager.CHICKEN_EGG_POP);
                    setSettings(p, lp);
                    break;
                }
                case CHICKEN_EGG_POP: {
                    settings.setNavigatorSound(SoundManager.ORB_PICKUP);
                    setSettings(p, lp);
                    break;
                }
            }
        });

        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.RECORD_3, 1, 0).displayName("§f§lError Ton").create());
        setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.NOTE_BLOCK, 1, 0).displayName("Error: " + settings.getErrorSound().getSoundName()).lore("", "§8» §f§nLinksklick§8 | §7§oÄndern").create(), e -> {
            switch (settings.getErrorSound()) {
                case SLIME_ATTACK: {
                    settings.setErrorSound(SoundManager.DIG_GRAVEL);
                    setSettings(p, lp);
                    break;
                }
                case DIG_GRAVEL: {
                    settings.setErrorSound(SoundManager.CHEST_CLOSE);
                    setSettings(p, lp);
                    break;
                }
                case CHEST_CLOSE: {
                    settings.setErrorSound(SoundManager.DOOR_CLOSE);
                    setSettings(p, lp);
                    break;
                }
                case DOOR_CLOSE: {
                    settings.setErrorSound(SoundManager.STEP_SAND);
                    setSettings(p, lp);
                    break;
                }
                case STEP_SAND: {
                    settings.setErrorSound(SoundManager.NOTE_SNARE_DRUM);
                    setSettings(p, lp);
                    break;
                }
                case NOTE_SNARE_DRUM: {
                    settings.setErrorSound(SoundManager.PISTON);
                    setSettings(p, lp);
                    break;
                }
                case PISTON: {
                    settings.setErrorSound(SoundManager.CLICK);
                    setSettings(p, lp);
                    break;
                }
                case CLICK: {
                    settings.setErrorSound(SoundManager.SLIME_ATTACK);
                    setSettings(p, lp);
                    break;
                }
            }
        });


        if (!settings.isDoNotDisturb()) {
            setItem(InventorySlot.ROW_4_SLOT_5, new ItemBuilder(Material.INK_SACK, 1, 7).displayName("§aError Sound testen...").create(), e -> {
                for (int i = 0; i <= 4; i++) {
                    Sound.error(player);
                }
            });

            setItem(InventorySlot.ROW_4_SLOT_4, new ItemBuilder(Material.INK_SACK, 1, 7).displayName("§aNavigator Sound testen...").create(), e -> {
                for (int i = 0; i <= 4; i++) {
                    Sound.done(p);
                }
            });
        } else {
            setItem(InventorySlot.ROW_4_SLOT_5, new ItemBuilder(Material.BARRIER, 1, 7).displayName("§cRuheModus ist aktiviert").create());
            setItem(InventorySlot.ROW_4_SLOT_4, new ItemBuilder(Material.BARRIER, 1, 7).displayName("§cRuheModus ist aktiviert").create());
        }


        setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.NOTE_BLOCK, 1, 0).displayName("§f§lHotbar wechsel Sound").create());
        if (settings.isHotbarChangeSound()) {
            setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um keinen Hotbar wechsel", "§7§oSound mehr zu erhalten!").create(), e -> {
                settings.setHotbarChangeSound(false);
                setSettings(p, lp);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um wieder einen Hotbar wechsel", "§7§oSound zu erhalten").create(), e -> {
                settings.setHotbarChangeSound(true);
                setSettings(p, lp);
            });
        }


        setItem(InventorySlot.ROW_4_SLOT_8, LEFT_ITEM, e ->
                new LobbyPersonalSettingsInventory(p));

        setItem(InventorySlot.ROW_4_SLOT_9, BACK_ITEM, e ->
                new LobbySettingsInventory(p));

        openInventory();
    }

    private void setSettings(Player p, LobbyPlayer lp) {
        lp.saveData();
        Sound.click(p);
        new LobbyPersonalExtendedInventory(p);
    }
}
