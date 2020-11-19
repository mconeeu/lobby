package eu.mcone.lobby.items.liveevents.events;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.liveevent.LiveEvent;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

public class Asteroid extends LiveEvent {

    public Asteroid() {
        super("asteroid", new GregorianCalendar(2020, Calendar.DECEMBER, 1, 16, 0).getTime());
    }

    @Override
    public void onStartEvent() {
        /*  PRE */
        //WEB
        Location pre_cobweb_1 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-1");
        Location pre_cobweb_2 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-2");
        Location pre_cobweb_3 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-3");
        Location pre_cobweb_4 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-4");
        Location pre_cobweb_5 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-5");
        Location pre_cobweb_6 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-6");
        Location pre_cobweb_7 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-7");
        Location pre_cobweb_8 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-8");
        Location pre_cobweb_9 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-9");
        Location pre_cobweb_10 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-10");

        //BEDROCK
        Location pre_bedrock_10 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-11");
        Location pre_bedrock_12 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-12");
        Location pre_bedrock_13 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-13");
        Location pre_bedrock_14 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-14");
        Location pre_bedrock_15 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-15");
        Location pre_bedrock_16 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-16");
        Location pre_bedrock_17 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-17");
        Location pre_bedrock_18 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-18");
        Location pre_bedrock_19 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-19");
        Location pre_bedrock_20 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-20");

        //ENDSTONE
        Location pre_endstone_21 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-21");
        Location pre_endstone_22 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-22");
        Location pre_endstone_23 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-23");
        Location pre_endstone_24 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-24");
        Location pre_endstone_25 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-25");
        Location pre_endstone_26 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-26");
        Location pre_endstone_27 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-pre-27");


        /*  FINAL */
        //COBBLE
        Location block1 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-1");
        Location block2 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-14");

        //BEDROCK
        Location bedrock1 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-10");
        Location bedrock2 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-11");
        Location bedrock3 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-12");
        Location bedrock4 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-13");

        //OBSIDIAN
        Location block3 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-3");

        //ENDSTONE
        Location endstone1 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-15");
        Location endstone2 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-16");
        Location endstone3 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-17");
        Location endstone4 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-18");
        Location endstone5 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-19");

        //DIRT
        Location dirt1 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-2");

        //COBWEB
        Location cobweb1 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-6");
        Location cobweb2 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-7");

        //PODZOL
        Location podzol1 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-3");
        Location podzol2 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-4");
        Location podzol5 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-5");


        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fUnbekannte Person §8|§7 Bei der Bank wird es passieren...");
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10, 1, false, false));
            p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 60, 1, false, false));
            Sound.play(p, org.bukkit.Sound.WITHER_SPAWN);
            Sound.play(p, org.bukkit.Sound.WITHER_IDLE);

            p.resetPlayerTime();
            p.setPlayerTime(14000, false);

            Location loc = p.getTargetBlock((Set<Material>) null, 50).getLocation();

            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutSpawnEntityWeather(new EntityLightning(((CraftPlayer) p).getHandle().getWorld(), loc.getX(), loc.getY(), loc.getZ(), false, false)));
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutNamedSoundEffect("ambient.weather.thunder", loc.getX(), loc.getY(), loc.getZ(), 10000.0F, 63));


            Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                Sound.play(p, org.bukkit.Sound.WITHER_SPAWN);
                Sound.play(p, org.bukkit.Sound.WITHER_IDLE);

                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutSpawnEntityWeather(new EntityLightning(((CraftPlayer) p).getHandle().getWorld(), loc.getX(), loc.getY(), loc.getZ(), false, false)));
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutNamedSoundEffect("ambient.weather.thunder", loc.getX(), loc.getY(), loc.getZ(), 10000.0F, 63));

                Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                    Sound.play(p, org.bukkit.Sound.WITHER_SPAWN);
                    Sound.play(p, org.bukkit.Sound.WITHER_IDLE);

                    Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 15, 1, false, false));
                        p.setPlayerTime(14000, false);
                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fUnbekannte Person §8|§7 Hahaha die Insel wird nicht lange so schön bleiben....");

                        p.setAllowFlight(true);
                        p.setFlying(true);


                        Vector v = p.getLocation().getDirection().multiply(2).setY(2);
                        p.setVelocity(v);


                        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutSpawnEntityWeather(new EntityLightning(((CraftPlayer) p).getHandle().getWorld(), loc.getX(), loc.getY(), loc.getZ(), false, false)));
                        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutNamedSoundEffect("ambient.weather.thunder", loc.getX(), loc.getY(), loc.getZ(), 10000.0F, 63));

                        Location loc1 = bedrock1.getBlock().getLocation();
                        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutSpawnEntityWeather(new EntityLightning(((CraftPlayer) p).getHandle().getWorld(), loc1.getX(), loc1.getY(), loc1.getZ(), false, false)));
                        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutNamedSoundEffect("ambient.weather.thunder", loc1.getX(), loc1.getY(), loc1.getZ(), 10000.0F, 63));


                        p.spigot().playEffect(pre_bedrock_17, Effect.EXPLOSION_LARGE, 1, 1, 1, 1, 1, 3, 20, 42);
                        p.spigot().playEffect(pre_cobweb_5, Effect.EXPLOSION_LARGE, 1, 1, 1, 1, 1, 3, 20, 42);
                        Sound.play(p, org.bukkit.Sound.EXPLODE);


                        Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {

                            p.spigot().playEffect(pre_cobweb_5, Effect.SMALL_SMOKE, 1, 1, 1, 1, 1, 3, 20, 42);
                            p.spigot().playEffect(pre_cobweb_5, Effect.FLAME, 1, 1, 1, 1, 1, 3, 20, 42);
                            Sound.play(p, org.bukkit.Sound.FIRE);
                            Sound.play(p, org.bukkit.Sound.FIREWORK_BLAST);

                            pre_cobweb_1.getBlock().setType(Material.WEB);
                            pre_cobweb_2.getBlock().setType(Material.WEB);
                            pre_cobweb_3.getBlock().setType(Material.WEB);
                            pre_cobweb_4.getBlock().setType(Material.WEB);
                            pre_cobweb_5.getBlock().setType(Material.WEB);
                            pre_cobweb_6.getBlock().setType(Material.WEB);
                            pre_cobweb_7.getBlock().setType(Material.WEB);
                            pre_cobweb_8.getBlock().setType(Material.WEB);
                            pre_cobweb_9.getBlock().setType(Material.WEB);
                            pre_cobweb_10.getBlock().setType(Material.WEB);
                            pre_bedrock_10.getBlock().setType(Material.BEDROCK);
                            pre_bedrock_12.getBlock().setType(Material.BEDROCK);
                            pre_bedrock_13.getBlock().setType(Material.BEDROCK);
                            pre_bedrock_14.getBlock().setType(Material.BEDROCK);
                            pre_bedrock_15.getBlock().setType(Material.BEDROCK);
                            pre_bedrock_16.getBlock().setType(Material.BEDROCK);
                            pre_bedrock_17.getBlock().setType(Material.BEDROCK);
                            pre_bedrock_18.getBlock().setType(Material.BEDROCK);
                            pre_bedrock_19.getBlock().setType(Material.BEDROCK);
                            pre_bedrock_20.getBlock().setType(Material.BEDROCK);
                            pre_endstone_21.getBlock().setType(Material.ENDER_STONE);
                            pre_endstone_22.getBlock().setType(Material.ENDER_STONE);
                            pre_endstone_23.getBlock().setType(Material.ENDER_STONE);
                            pre_endstone_24.getBlock().setType(Material.ENDER_STONE);
                            pre_endstone_25.getBlock().setType(Material.ENDER_STONE);
                            pre_endstone_26.getBlock().setType(Material.ENDER_STONE);
                            pre_endstone_27.getBlock().setType(Material.ENDER_STONE);


                            Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {

                                pre_cobweb_1.getBlock().setType(Material.AIR);
                                pre_cobweb_2.getBlock().setType(Material.AIR);
                                pre_cobweb_3.getBlock().setType(Material.AIR);
                                pre_cobweb_4.getBlock().setType(Material.AIR);
                                pre_cobweb_5.getBlock().setType(Material.AIR);
                                pre_cobweb_6.getBlock().setType(Material.AIR);
                                pre_cobweb_7.getBlock().setType(Material.AIR);
                                pre_cobweb_8.getBlock().setType(Material.AIR);
                                pre_cobweb_9.getBlock().setType(Material.AIR);
                                pre_cobweb_10.getBlock().setType(Material.AIR);
                                pre_bedrock_10.getBlock().setType(Material.AIR);
                                pre_bedrock_12.getBlock().setType(Material.AIR);
                                pre_bedrock_13.getBlock().setType(Material.AIR);
                                pre_bedrock_14.getBlock().setType(Material.AIR);
                                pre_bedrock_15.getBlock().setType(Material.AIR);
                                pre_bedrock_16.getBlock().setType(Material.AIR);
                                pre_bedrock_17.getBlock().setType(Material.AIR);
                                pre_bedrock_18.getBlock().setType(Material.AIR);
                                pre_bedrock_19.getBlock().setType(Material.AIR);
                                pre_bedrock_20.getBlock().setType(Material.AIR);
                                pre_endstone_21.getBlock().setType(Material.AIR);
                                pre_endstone_22.getBlock().setType(Material.AIR);
                                pre_endstone_23.getBlock().setType(Material.AIR);
                                pre_endstone_24.getBlock().setType(Material.AIR);
                                pre_endstone_25.getBlock().setType(Material.AIR);
                                pre_endstone_26.getBlock().setType(Material.AIR);
                                pre_endstone_27.getBlock().setType(Material.AIR);


                                PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.MOB_APPEARANCE, false, 1, 1, 1, 1, 1, 1, 0, 1);
                                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
                                Sound.play(p, org.bukkit.Sound.FIREWORK_BLAST);
                                Sound.play(p, org.bukkit.Sound.ENDERMAN_DEATH);

                                p.spigot().playEffect(block1, Effect.EXPLOSION_LARGE, 1, 1, 1, 1, 1, 3, 20, 42);
                                p.spigot().playEffect(endstone1, Effect.EXPLOSION_LARGE, 1, 1, 1, 1, 1, 3, 20, 10);

                                block1.getBlock().setType(Material.COBBLESTONE);
                                block2.getBlock().setType(Material.COBBLESTONE);
                                podzol1.getBlock().setType(Material.DIRT);
                                podzol2.getBlock().setType(Material.DIRT);
                                podzol5.getBlock().setType(Material.COBBLESTONE);
                                dirt1.getBlock().setType(Material.DIRT);
                                cobweb1.getBlock().setType(Material.WEB);
                                cobweb2.getBlock().setType(Material.WEB);
                                bedrock1.getBlock().setType(Material.BEDROCK);
                                bedrock2.getBlock().setType(Material.BEDROCK);
                                bedrock3.getBlock().setType(Material.BEDROCK);
                                bedrock4.getBlock().setType(Material.BEDROCK);
                                endstone1.getBlock().setType(Material.ENDER_STONE);
                                endstone2.getBlock().setType(Material.ENDER_STONE);
                                endstone3.getBlock().setType(Material.ENDER_STONE);
                                endstone4.getBlock().setType(Material.ENDER_STONE);
                                endstone5.getBlock().setType(Material.ENDER_STONE);
                                block3.getBlock().setType(Material.OBSIDIAN);

                                Sound.play(p, org.bukkit.Sound.EXPLODE);
                                Sound.play(p, org.bukkit.Sound.FIREWORK_LAUNCH);

                                Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {


                                    Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                        p.resetPlayerTime();
                                        p.setPlayerTime(1000, false);
                                        p.setFlying(false);
                                        if (!p.hasPermission("mcone.premium")) p.setAllowFlight(false);
                                    }, 30);
                                }, 30);
                            }, 90);
                        }, 5);
                    }, 60);
                }, 50);
            }, 160);
        }
    }

    @Override
    public void onRemoveEvent() {
        /*  FINAL */
        //COBBLE
        Location block1 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-1");
        Location block2 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-14");

        //BEDROCK
        Location bedrock1 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-10");
        Location bedrock2 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-11");
        Location bedrock3 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-12");
        Location bedrock4 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-13");

        //OBSIDIAN
        Location block3 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-3");

        //ENDSTONE
        Location endstone1 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-15");
        Location endstone2 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-16");
        Location endstone3 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-17");
        Location endstone4 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-18");
        Location endstone5 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-19");

        //DIRT
        Location dirt1 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-2");

        //COBWEB
        Location cobweb1 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-6");
        Location cobweb2 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-7");

        //PODZOL
        Location podzol1 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-3");
        Location podzol2 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-4");
        Location podzol5 = CoreSystem.getInstance().getWorldManager().getWorld(LobbyWorld.ONE_ISLAND.getName()).getBlockLocation("liveevent-5");

        block1.getBlock().setType(Material.AIR);
        block2.getBlock().setType(Material.AIR);
        podzol1.getBlock().setType(Material.AIR);
        podzol2.getBlock().setType(Material.AIR);
        podzol5.getBlock().setType(Material.AIR);
        dirt1.getBlock().setType(Material.AIR);
        cobweb1.getBlock().setType(Material.AIR);
        cobweb2.getBlock().setType(Material.AIR);
        bedrock1.getBlock().setType(Material.AIR);
        bedrock2.getBlock().setType(Material.AIR);
        bedrock3.getBlock().setType(Material.AIR);
        bedrock4.getBlock().setType(Material.AIR);
        endstone1.getBlock().setType(Material.AIR);
        endstone2.getBlock().setType(Material.AIR);
        endstone3.getBlock().setType(Material.AIR);
        endstone4.getBlock().setType(Material.AIR);
        endstone5.getBlock().setType(Material.AIR);
        block3.getBlock().setType(Material.AIR);
    }
}