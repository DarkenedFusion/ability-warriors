package Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.darkenedfusion.CooldownManager;

public class TurtleUltimate implements Listener {
	
	private static HashMap<UUID, Double> totalDamage = new HashMap<UUID, Double>();
	
	
	@EventHandler
	public void onEnoughHitsTurtle(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		if(!(damager instanceof Player)) {
			return;
		}
		Player player = (Player) event.getDamager();
		if(!(player.getInventory().getItemInMainHand().getType() == Material.STONE_SWORD)) {
			return;
		}
		if(!player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Turtle Bite")) {
			return;
		}
		totalDamage.merge(player.getUniqueId(), event.getDamage(),  Double::sum);
		double damage = totalDamage.getOrDefault(player.getUniqueId(), 0.0);
		if (damage >= 50.0) {
			if(!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.SHELLBREAK)) {
				ItemStack sBreak = new ItemStack(Material.FIREWORK_STAR);
				ItemMeta oMeta = sBreak.getItemMeta();
				oMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "SHELL" + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "BREAK");
				List<String> olore = new ArrayList<String>();
				oMeta.setUnbreakable(true);
				olore.add(ChatColor.GRAY + "You're the fastest turtle alive!");
				olore.add("");
				olore.add(ChatColor.GOLD + "Ultimate Ability:");
				olore.add(ChatColor.GRAY + "Shell Break: Gives all players slowness");
				olore.add(ChatColor.GRAY + "while you get speed!");
				oMeta.setLore(olore);
				sBreak.setItemMeta(oMeta);
				
				ItemStack chargedBreak = new ItemStack(Material.SCUTE);
				ItemMeta cMeta = chargedBreak.getItemMeta();
				cMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "SHELL" + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "BREAK");
				List<String> clore = new ArrayList<String>();
				cMeta.setUnbreakable(true);
				cMeta.addEnchant(Enchantment.DURABILITY, 1, false);
				cMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				clore.add(ChatColor.GRAY + "You're the fastest turtle alive!");
				clore.add("");
				clore.add(ChatColor.GOLD + "Ultimate Ability:");
				clore.add(ChatColor.GRAY + "Shell Break: Gives all players slowness");
				clore.add(ChatColor.GRAY + "while you get speed!");
				cMeta.setLore(clore);
				chargedBreak.setItemMeta(cMeta);
				
				if(player.getInventory().contains(sBreak)) {
					player.getInventory().remove(sBreak);
					player.getInventory().addItem(chargedBreak);
					totalDamage.put(player.getUniqueId(), 0.0);
					
				} else {
					return;
				}
			
			 CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.SHELLBREAK, 60);
		}
	}
	}
	
	@EventHandler
	public void onShadowTransmissionClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		   if(player.getInventory().getItemInMainHand().getType().equals(Material.SCUTE)) {
			   if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "" + ChatColor.BOLD + "SHELL" + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "BREAK")) {
  	if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
  		
  			
  			for(Player p : player.getWorld().getPlayers()) {
  					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 5));
  				}

				
  			
  			if(player.hasPotionEffect(PotionEffectType.SLOW)) {
				player.removePotionEffect(PotionEffectType.SLOW);
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 3));
			}
  			
  			Location loc = player.getLocation();
  				

  			int points = 5; // the amount of points the polygon should have.
  			for (int iteration = 0; iteration < points; iteration++) {
  			  double angle = 360.0 / points * iteration;
  			  double nextAngle = 360.0 / points * (iteration + 1); // the angle for the next point.
  			  angle = Math.toRadians(angle);
  			  nextAngle = Math.toRadians(nextAngle); // convert to radians.
  			  double x = Math.cos(angle);
  			  double z = Math.sin(angle);
  			  double x2 = Math.cos(nextAngle);
  			  double z2 = Math.sin(nextAngle);
  			  double deltaX = x2 - x; // get the x-difference between the points.
  			  double deltaZ = z2 - z; // get the z-difference between the points.
  			  double distance = Math.sqrt((deltaX - x) * (deltaX - x) + (deltaZ - z) * (deltaZ - z));
  			  for (double d = 0; d < distance - .1; d += .1) { // we subtract .1 from the distance because otherwise it would make 1 step too many.
  			    loc.add(x + deltaX * d, 0, z + deltaZ * d);
  			    player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, loc, 50);
  			    loc.subtract(x + deltaX * d, 0, z + deltaZ * d);
  			  }
  			}
  				
  				
  				
  				
  				
  			ItemStack sUlt = new ItemStack(Material.FIREWORK_STAR);
				ItemMeta oMeta = sUlt.getItemMeta();
				oMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "SHELL" + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "BREAK");
				List<String> olore = new ArrayList<String>();
				oMeta.setUnbreakable(true);
				olore.add(ChatColor.GRAY + "You're the fastest turtle alive!");
				olore.add("");
				olore.add(ChatColor.GOLD + "Ultimate Ability:");
				olore.add(ChatColor.GRAY + "Shell Break: Gives all players slowness");
				olore.add(ChatColor.GRAY + "while you get speed!");
				oMeta.setLore(olore);
				sUlt.setItemMeta(oMeta);
				if(player.getInventory().contains(Material.SCUTE)) {
				player.getInventory().remove(Material.SCUTE);
				player.getInventory().addItem(sUlt);
				}
				
				

  		}
  	}
  }
	  }

}
