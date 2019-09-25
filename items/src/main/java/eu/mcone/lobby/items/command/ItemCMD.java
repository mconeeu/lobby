package eu.mcone.lobby.items.command;

import eu.mcone.coresystem.api.bukkit.command.CoreCommand;
import eu.mcone.gamesystem.api.enums.Category;
import eu.mcone.gamesystem.api.enums.Item;
import eu.mcone.gamesystem.api.game.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemCMD extends CoreCommand {

    public ItemCMD() {
        super("item", "lobby.item.settings", "items");
    }


    @Override
    public boolean onCommand(CommandSender commandSender, String[] args) {
        Player p = (Player) commandSender;

        if (args.length == 0) {
            LobbyPlugin.getInstance().getMessager().send(p, "cBitte benutze §4/item add | remove <Spieler> <item-name>");
        } else if (args.length == 3) {
            if (args[0].equalsIgnoreCase("add")) {
                Player t = Bukkit.getServer().getPlayer(args[1]);
                if (t == null) {
                    LobbyPlugin.getInstance().getMessager().send(p, "§4Der Spieler ist Offline!");
                } else {
                    GamePlayer gp = LobbyPlugin.getInstance().getGamePlayer(t.getUniqueId());
                    for (Item item : Item.values()) {
                        if (item.name().equalsIgnoreCase(args[2])) {
                            if (!item.getCategory().equals(Category.EXCLUSIVE)) {
                                if (!gp.hasItem(item)) {
                                    gp.addItem(item);
                                    if (p.getName().equalsIgnoreCase(t.getName())) {
                                        LobbyPlugin.getInstance().getMessager().send(p, "§2Du hast das Item §a" + item.name() + " §2vom Spieler §a" + t.getName() + " §2hinzugefügt");
                                    } else {
                                        LobbyPlugin.getInstance().getMessager().send(p, "§2Du hast das Item §a" + item.name() + " §2vom Spieler §a" + t.getName() + " §2hinzugefügt");
                                        LobbyPlugin.getInstance().getMessager().send(t, "§2Du hast das Item §a" + item.name() + " §2vom Spieler §a" + p.getName() + " §2bekommen!");
                                    }
                                    t.closeInventory();
                                    if (p.hasPermission("lobby.silenthub")) {
                                        p.getInventory().setItem(3, null);
                                    } else {
                                        p.getInventory().setItem(2, null);
                                    }
                                } else {
                                    LobbyPlugin.getInstance().getMessager().send(p, "§4Der Spieler besitzt dieses Item bereits!");

                                }
                            } else {
                                LobbyPlugin.getInstance().getMessager().send(p, "§4Dieses Item kann nicht gesetzt werden §ndu otter");
                            }
                            return true;
                        }
                    }

                    LobbyPlugin.getInstance().getMessager().send(p, "§4Das Item mit dem Name §l" + args[2] + "§4existiert nicht!");
                }
            } else if (args[0].equalsIgnoreCase("remove")) {
                Player t = Bukkit.getServer().getPlayer(args[1]);

                if (t != null) {
                    GamePlayer gp = LobbyPlugin.getInstance().getGamePlayer(t.getUniqueId());

                    for (Item item : Item.values()) {
                        if (item.name().equalsIgnoreCase(args[2])) {
                            if (gp.hasItem(item)) {
                                gp.removeItem(item);
                                if (t.getName().equalsIgnoreCase(p.getName())) {
                                    LobbyPlugin.getInstance().getMessager().send(p, "§2Du hast das Item §a" + item.name() + " §2vom Spieler §a" + t.getName() + " §2entfernt");
                                } else {
                                    LobbyPlugin.getInstance().getMessager().send(p, "§2Du hast das Item §a" + item.name() + " §2vom Spieler §a" + t.getName() + " §2entfernt");
                                    LobbyPlugin.getInstance().getMessager().send(t, "§2Du hast das Item §a" + item.name() + " §2vom Spieler §a" + p.getName() + " §2entfernt bekommen*!");
                                }
                                t.closeInventory();
                                if (p.hasPermission("lobby.silenthub")) {
                                    p.getInventory().setItem(3, null);
                                } else {
                                    p.getInventory().setItem(2, null);
                                }
                            } else {
                                LobbyPlugin.getInstance().getMessager().send(p, "§4Der Spieler hat das Item noch nicht!");
                            }
                            return true;
                        }

                    }
                    LobbyPlugin.getInstance().getMessager().send(p, "§4Das Item mit dem Name §l" + args[2] + "§4existiert nicht!");
                } else {
                    LobbyPlugin.getInstance().getMessager().send(p, "§4Der Spieler ist Offline!");
                }
            } else {
                LobbyPlugin.getInstance().getMessager().send(p, "cBitte benutze §4/item add | remove <Spieler> <item-name>");
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("list")) {
                LobbyPlugin.getInstance().getMessager().send(p, "http://systems.gitlab.onegaming.group/gamesystem/eu/mcone/gamesystem/api/enums/Item.html");
            }
        } else {
            LobbyPlugin.getInstance().getMessager().send(p, "cBitte benutze §4/item add | remove <Spieler> <item-name>");
        }


        return false;
    }
}
