package us.universalpvp.iguns.explosives;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import us.universalpvp.iguns.events.explosives.*;
import us.universalpvp.iguns.iGunsMain;

/**
 * Created by avigh on 8/15/2016.
 */
public class ExplosivesFactory implements Listener {

    private final ExplosivesManager manager;
    private final iGunsMain main;

    public ExplosivesFactory(iGunsMain main) {
        main.getServer().getPluginManager().registerEvents(this, main);

        this.main = main;
        this.manager = main.getExplosivesManager();
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        final Player p = e.getPlayer();

        manager.getRegisteredExplosives().stream().filter(explosive -> e.getItemDrop().getItemStack()
                .isSimilar(explosive.getBukkitItemStack()))
                .filter(explosive -> explosive.getType() == ExplosiveType.DROPABLE).forEach(explosive -> {

            Bukkit.getPluginManager().callEvent(new ExplosiveDropEvent());

            new BukkitRunnable() {

                @Override
                public void run() {

                    if (e.getItemDrop().isOnGround()) {
                        Bukkit.getPluginManager().callEvent(new ExplosiveTimerStartEvent());
                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                Bukkit.getPluginManager().callEvent(new ExplosiveTimerEndEvent());

                                ExplosionSize.createExplosion(explosive.getSize(), p, e.getItemDrop().getLocation()
                                        , explosive.isIncendiary());

                                explosive.onDrop(e);

                                Bukkit.getPluginManager().callEvent(new ExplosiveExplodeEvent());
                            }
                        }.runTaskLater(main, explosive.getCountdownTime());
                    }
                }
            }.runTaskTimer(main, 0, 5);

        });
    }


    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        final Player p = e.getPlayer();

        manager.getRegisteredExplosives().stream().filter(explosive -> e.getItemInHand()
                .isSimilar(explosive.getBukkitItemStack()))
                .filter(explosive -> explosive.getType() == ExplosiveType.PLACABLE).forEach(explosive -> {

            Bukkit.getPluginManager().callEvent(new ExplosivePlaceEvent());
            Bukkit.getPluginManager().callEvent(new ExplosiveTimerStartEvent());

            new BukkitRunnable() {

                @Override
                public void run() {
                    Bukkit.getPluginManager().callEvent(new ExplosiveTimerEndEvent());

                    ExplosionSize.createExplosion(explosive.getSize(), p, e.getBlockPlaced().getLocation()
                            , explosive.isIncendiary());

                    explosive.onPlace(e);

                    Bukkit.getPluginManager().callEvent(new ExplosiveExplodeEvent());
                }
            }.runTaskLater(main, explosive.getCountdownTime());
        });
    }

    @EventHandler
    public void onThrow(PlayerInteractEvent e) {
        final Player p = e.getPlayer();

        manager.getRegisteredExplosives().stream().filter(explosive -> e.getItem()
                .isSimilar(explosive.getBukkitItemStack()))
                .filter(explosive -> explosive.getType() == ExplosiveType.THROWABLE)
                .filter(explosive -> e.getAction() == Action.RIGHT_CLICK_BLOCK).forEach(explosive -> {

            Item item = p.getWorld().dropItem(p.getLocation(), explosive.getBukkitItemStack());
            item.setVelocity(p.getLocation().getDirection().multiply(explosive.getType().getVelocity()));

            Bukkit.getPluginManager().callEvent(new ExplosiveThrowEvent());

            new BukkitRunnable() {

                @Override
                public void run() {

                    if (item.isOnGround()) {
                        Bukkit.getPluginManager().callEvent(new ExplosiveTimerStartEvent());
                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                Bukkit.getPluginManager().callEvent(new ExplosiveTimerEndEvent());

                                ExplosionSize.createExplosion(explosive.getSize(), p, item.getLocation()
                                        , explosive.isIncendiary());

                                explosive.onThrow(e);

                                Bukkit.getPluginManager().callEvent(new ExplosiveExplodeEvent());
                            }
                        }.runTaskLater(main, explosive.getCountdownTime());
                    }
                }
            }.runTaskTimer(main, 0, 5);
        });
    }
}
