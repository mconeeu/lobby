package eu.mcone.lobby.items.inventory.office.secretary;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.inventory.anvil.AnvilSlot;
import eu.mcone.coresystem.api.bukkit.inventory.anvil.CoreAnvilInventory;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SecretaryInventory extends CoreInventory {


    public static ArrayList<Player> isInviting = new ArrayList<>();

    private static final CoreAnvilInventory ANVIL_INVENTORY = CoreSystem.getInstance().createAnvilInventory(event -> {
        String name = event.getName();

        Player player = event.getPlayer();
        Player target = Bukkit.getPlayer(name);

        if (target != null && !target.equals(event.getPlayer())) {

            if (event.getSlot().equals(AnvilSlot.OUTPUT)) {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.NOTE_STICKS, 1, 1);
                LobbyPlugin.getInstance().getMessenger().send(player, "Du hast§f " + target.getName() + "§7 zu deinem Büro eingeladen!");
                isInviting.add(player);
                target.spigot().sendMessage(
                        new ComponentBuilder("")
                                .append("\n§fDer Spieler §l" + player.getName() + " §fhat dich zu seinem Büro eingeladen")
                                .append("\n")
                                .append("[Zum Büro telepotieren]")
                                .color(ChatColor.GREEN)
                                .bold(true)
                                .event(new HoverEvent(
                                        HoverEvent.Action.SHOW_TEXT,
                                        new ComponentBuilder("§7§oKlicke hier, um dich zum Büro zu teleportieren").create()
                                ))
                                .event(new ClickEvent(
                                        ClickEvent.Action.RUN_COMMAND,
                                        "/office " + player.getName()
                                ))
                                .create()
                );
            }
        } else {
            LobbyPlugin.getInstance().getMessenger().send(player, "§4Dieser Spieler ist nicht Online!");
        }
    }).setItem(AnvilSlot.INPUT_LEFT, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("?").create());

    public SecretaryInventory(Player p) {
        super("§8» §b§lSekretärin §8| §fMenü", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);


        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.TRIPWIRE_HOOK, 1, 0).displayName("§fZum Büro einladen")
                .lore("§fLade einen Spieler zu deinen Büro ein")
                .create(), e -> {

            if (LobbyPlugin.getInstance().getSilentLobbyManager().isActivatedSilentHub(p)) {
                new PrivateAcceptInventory(p);
                return;
            }

            ANVIL_INVENTORY.open(p);
        });


        openInventory();
    }
}
