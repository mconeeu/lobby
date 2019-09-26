package eu.mcone.lobby.items.listener.effects;

import eu.mcone.gamesystem.api.enums.Item;
import eu.mcone.lobby.api.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class CobwebGub implements Listener {


    //TODO Blockdaten saven because: sonst kann man die Map zerstören!
    @EventHandler
    public void on(PlayerInteractEvent e) {
        if (e.hasItem() && e.getItem().equals(Item.COBWEBGUN.getItemStack()) && (e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_AIR))) {
            Player p = e.getPlayer();

            org.bukkit.entity.Item item = p.getLocation().getWorld().dropItem(p.getEyeLocation(),
                    new ItemStack(Material.FIREWORK_CHARGE));
            if (p.hasPermission("lobby.silenthub")) {
                p.getInventory().setItem(3, null);
            } else {
                p.getInventory().setItem(2, null);
            }

            Vector v = p.getLocation().getDirection().multiply(1);
            item.setVelocity(v);

            Block oldItemDown = item.getLocation().getWorld().getBlockAt(item.getLocation().add(0, -1, 0));
            Block oldItemUp = item.getLocation().getWorld().getBlockAt(item.getLocation());

            System.out.println("olditemdown: " + oldItemDown + ": " + item.getLocation().add(0, -1, 0) + ", olditemup: " + oldItemUp + ": " + item.getLocation());

            if ((oldItemDown.getType().equals(Material.GRASS) || oldItemDown.getType().equals(Material.AIR)) && oldItemUp.getType().equals(Material.AIR)) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(LobbyPlugin.getInstance(), new Runnable() {

                    @Override
                    public void run() {
                        item.getWorld().createExplosion(item.getLocation().getX(), item.getLocation().getY(),
                                item.getLocation().getZ(), 3, false, false);


                    /*Block yplus1 = item.getLocation().getWorld().getBlockAt(new Location(item.getWorld(), item.getLocation().getBlockX(), item.getLocation().getBlockY() + 1, item.getLocation().getBlockZ()));
                    Block startair = item.getLocation().getWorld().getBlockAt(new Location(item.getWorld(), item.getLocation().getBlockX(), item.getLocation().getBlockY(), item.getLocation().getBlockZ()));
                     */

                        item.getLocation().getWorld().getBlockAt(item.getLocation())
                                .setType(Material.WEB);
                        item.getLocation().getWorld().getBlockAt(item.getLocation().add(0, 1, 0))
                                .setType(Material.WEB);

                        item.getWorld().playEffect(item.getLocation(), Effect.FIREWORKS_SPARK, 1);
                        item.getWorld().playSound(item.getLocation(), Sound.GLASS, 1, 1);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(LobbyPlugin.getInstance(), new Runnable() {

                            @Override
                            public void run() {
                                item.getWorld().playSound(item.getLocation(), Sound.FIREWORK_TWINKLE, 1, 1);
                            }
                        }, 3);
                    }
                }, 20);

                p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                Bukkit.getScheduler().scheduleSyncDelayedTask(LobbyPlugin.getInstance(), new Runnable() {

                    @Override
                    public void run() {
                        p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(LobbyPlugin.getInstance(), new Runnable() {

                            @Override
                            public void run() {
                                p.playSound(p.getLocation(), Sound.CLICK, 1, 1);

                                Bukkit.getScheduler().scheduleSyncDelayedTask(LobbyPlugin.getInstance(), new Runnable() {

                                    @Override
                                    public void run() {
                                        System.out.println("olditemdown: " + oldItemDown + ", olditemup: " + oldItemUp);

                                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                                        item.getLocation().getWorld().getBlockAt(item.getLocation())
                                                .setType(oldItemDown.getType());
                                        item.getLocation().getWorld().getBlockAt(item.getLocation().add(0, 1, 0))
                                                .setType(oldItemUp.getType());
                                        Bukkit.getScheduler().scheduleSyncDelayedTask(LobbyPlugin.getInstance(), new Runnable() {

                                            @Override
                                            public void run() {
                                                p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                                                if (p.hasPermission("lobby.silenthub")) {
                                                    p.getInventory().setItem(3, Item.COBWEBGUN.getItemStack());
                                                } else {
                                                    p.getInventory().setItem(2, Item.COBWEBGUN.getItemStack());
                                                }

                                            }
                                        }, 33);
                                    }
                                }, 18);
                            }
                        }, 10);
                    }
                }, 10);
            } else {
                LobbyPlugin.getInstance().getMessager().send(p, "§4Die Cobweb gun funktioniert hier nicht (Block im Weg!)");
                p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                if (p.hasPermission("lobby.silenthub")) {
                    p.getInventory().setItem(3, Item.COBWEBGUN.getItemStack());
                } else {
                    p.getInventory().setItem(2, Item.COBWEBGUN.getItemStack());
                }
            }
        }
    }


}
