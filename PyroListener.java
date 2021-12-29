package Classes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import io.github.darkenedfusion.CooldownManager;

public class PyroListener implements Listener {
	
	
	@EventHandler
	public void onRightClickThrower(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		   if(player.getInventory().getItemInMainHand().getType().equals(Material.BLAZE_ROD)) {
			   if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "Flamethrower")) {
				   if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
					   if(!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.FLAMETHROWER)) {
					   player.launchProjectile(Fireball.class);
					   
					   CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.FLAMETHROWER, 3);
				   }
			   }
			   }
		   }
			  
     	}
	
	
	

	@EventHandler
	public void onFlameThrower(ProjectileHitEvent event) {
		if(event.getEntity() instanceof Fireball) {
			if(event.getEntity().getShooter() instanceof Player) {
				Player player = (Player) event.getEntity().getShooter();
			if(player.getInventory().getItemInMainHand().getType().equals(Material.BLAZE_ROD)) {
				if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "Flamethrower")) {
					Fireball fireball = (Fireball) event.getEntity();
					fireball.getWorld().strikeLightning(fireball.getLocation());
					fireball.getWorld().strikeLightning(fireball.getLocation());
					fireball.getWorld().strikeLightning(fireball.getLocation());
					fireball.getWorld().strikeLightning(fireball.getLocation());
				}
			}
		}
	
	}
	
	}

}
