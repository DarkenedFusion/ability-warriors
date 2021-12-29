package Misc;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class DoubleJump implements Listener {
	
	@EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE)
            return;
        event.setCancelled(true);
        player.setAllowFlight(false);
        player.setFlying(false);
        Location loc = player.getLocation();
        Vector v = loc.getDirection().multiply(.8f).setY(.8);
        player.setVelocity(v);
       
       
       
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if ((player.getGameMode() != GameMode.CREATIVE)
                && (player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR)
                && ((player.getGameMode() != GameMode.CREATIVE)
                        && (player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.GRASS) &&

                        (!player.isFlying()))) {
            player.setAllowFlight(true);
        }
    }

}

