package Classes;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.darkenedfusion.CooldownManager;

public class LightListener implements Listener {
	
	// Light Orb Effect
	@EventHandler
	public void onLightOrb(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		if(damager instanceof Player) {
			Player player = (Player) event.getDamager();
			if(player.getInventory().getItemInMainHand().getType().equals(Material.GOLD_NUGGET)) {
				if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Light Orb")) {
					double randDouble = Math.random();
					if(randDouble <= 0.10D) {
						LivingEntity victim = (LivingEntity) event.getEntity();
						victim.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 50, 3));
					}
					
				}
			}
		}
	}
	
	//Light Blast launch arrow
	@EventHandler
	public void onLightBlast(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		   if(player.getInventory().getItemInMainHand().getType().equals(Material.GOLD_INGOT)) {
			   if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Light Blast")) {
        	if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
        		if(!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.LIGHTBLAST)) {
        			Projectile arrow = player.launchProjectile(Arrow.class);
        			arrow.setGravity(false);
        			
        			
        			
        			CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.LIGHTBLAST, 2);
        		}
        	}
        }
	  }
	}	

	//Light Blast Arrow Effect
	@EventHandler
	public void onLightLand(ProjectileHitEvent event) {
		if(event.getEntity() instanceof Arrow) {
			Arrow arrow = (Arrow) event.getEntity();
			if(event.getEntity().getShooter() instanceof Player) {
				Player player = (Player) event.getEntity().getShooter();	
			if(player.getInventory().getItemInMainHand().getType().equals(Material.GOLD_INGOT)) {
			
			AreaEffectCloud lightCircle = (AreaEffectCloud) arrow.getWorld().spawnEntity(arrow.getLocation(), EntityType.AREA_EFFECT_CLOUD);
			lightCircle.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, 60, 2), true);
			lightCircle.setRadius(2);
			lightCircle.setWaitTime(0);
			lightCircle.setDuration(60);
			lightCircle.setCustomName("Light Circle");
			lightCircle.setCustomNameVisible(false);
			lightCircle.setParticle(Particle.REDSTONE, new Particle.DustOptions(Color.YELLOW, 1));
			}
		}
		}
	}
	
}
