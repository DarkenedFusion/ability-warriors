package Classes;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.darkenedfusion.CooldownManager;
import io.github.darkenedfusion.Main;

public class CryomancerListener implements Listener {
	
	//Frigid Staff
	@EventHandler
	public void onFrigid(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		   if(player.getInventory().getItemInMainHand().getType().equals(Material.STICK)) {
			   if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Frigid Staff")) {
        	if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
        		if(!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ICEBOMB)) {
        			ItemStack bomb = new ItemStack(Material.BLUE_ICE, 1);
        			Entity drop = player.getWorld().dropItemNaturally(player.getLocation(), bomb);
        			drop.setVelocity(player.getLocation().getDirection().multiply(2));
        			new BukkitRunnable() {

        				
        				@Override
        				public void run() {
        					player.getWorld().createExplosion(drop.getLocation(), 3, false);
        					AreaEffectCloud slowCircle = (AreaEffectCloud) drop.getWorld().spawnEntity(drop.getLocation(), EntityType.AREA_EFFECT_CLOUD);
        					slowCircle.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 60, 2), true);
        					slowCircle.setRadius(3);
        					slowCircle.setWaitTime(0);
        					slowCircle.setDuration(60);
        					slowCircle.setCustomName("Slow Circle");
        					slowCircle.setCustomNameVisible(false);
        					slowCircle.setParticle(Particle.REDSTONE, new Particle.DustOptions(Color.AQUA, 1));
        				}
        				
        				
        			}.runTaskLater(Main.getInstance(), 40);
        			
        			
        			
        			
        			CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ICEBOMB, 3);
        		}
        	}
        }
	  }
	}

}
