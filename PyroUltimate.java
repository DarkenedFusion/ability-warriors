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

public class PyroUltimate implements Listener {
	
	
	
	private static HashMap<UUID, Double> totalDamage = new HashMap<UUID, Double>();
	
	@EventHandler
	public void onEnoughHitsPyro(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		if(!(damager instanceof Player)) {
			return;
		}
		Player player = (Player) event.getDamager();
		if(!(player.getInventory().getItemInMainHand().getType() == Material.FLINT_AND_STEEL)) {
			return;
		}
		if(!player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Lighter")) {
			return;
		}
		totalDamage.merge(player.getUniqueId(), event.getDamage(),  Double::sum);
		double damage = totalDamage.getOrDefault(player.getUniqueId(), 0.0);
		if (damage >= 50.0) {
			if(!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.OVERHEAT)) {
				ItemStack oHeat = new ItemStack(Material.FIREWORK_STAR);
				ItemMeta oMeta = oHeat.getItemMeta();
				oMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Over" + ChatColor.GOLD + "" + ChatColor.BOLD + "heat");
				List<String> olore = new ArrayList<String>();
				oMeta.setUnbreakable(true);
				olore.add(ChatColor.GRAY + "Dear lord thats a lot of fire!");
				olore.add("");
				olore.add(ChatColor.GOLD + "Ultimate Ability:");
				olore.add(ChatColor.GRAY + "Right Clicking summons hell!");
				oMeta.setLore(olore);
				oHeat.setItemMeta(oMeta);
				
				ItemStack chargedHeat = new ItemStack(Material.FIRE_CHARGE);
				ItemMeta cMeta = chargedHeat.getItemMeta();
				cMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Over" + ChatColor.GOLD + "" + ChatColor.BOLD + "heat");
				List<String> clore = new ArrayList<String>();
				cMeta.setUnbreakable(true);
				cMeta.addEnchant(Enchantment.DURABILITY, 1, false);
				cMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				clore.add(ChatColor.GRAY + "Dear lord thats a lot of fire!");
				clore.add("");
				clore.add(ChatColor.GOLD + "Ultimate Ability:");
				clore.add(ChatColor.GRAY + "Right Clicking summons hell!");
				cMeta.setLore(clore);
				chargedHeat.setItemMeta(cMeta);
				
				if(player.getInventory().contains(oHeat)) {
					player.getInventory().remove(oHeat);
					player.getInventory().addItem(chargedHeat);
					totalDamage.put(player.getUniqueId(), 0.0);
					
				} else {
					return;
				}
			
			 CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.OVERHEAT, 60);
		}
	}
	}
	
	@EventHandler
	public void onpyroUltimateClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		   if(player.getInventory().getItemInMainHand().getType().equals(Material.FIRE_CHARGE)) {
			   if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Over" + ChatColor.GOLD + "" + ChatColor.BOLD + "heat")) {
     	if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
     			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 70, 4));
     			player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, 1));
     			
     			
     			for(Player p : player.getWorld().getPlayers()) {
     				p.setFireTicks(100);
     				p.getWorld().createExplosion(p.getLocation(), 1);
     				
     			}
     			Location location = player.getLocation();
     			
     			for (int degree = 0; degree < 360; degree++) {
     			    double radians = Math.toRadians(degree);
     			    double x = Math.cos(radians);
     			    double z = Math.sin(radians);
     			    location.add(x,0,z);
     			    player.getWorld().spawnParticle(Particle.FLAME, location, 1);
     			    location.subtract(x,0,z);
     			}
     			
     			
     			
     			
     			
     			ItemStack oHeat = new ItemStack(Material.FIREWORK_STAR);
				ItemMeta oMeta = oHeat.getItemMeta();
				oMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Over" + ChatColor.GOLD + "" + ChatColor.BOLD + "heat");
				List<String> olore = new ArrayList<String>();
				oMeta.setUnbreakable(true);
				olore.add(ChatColor.GRAY + "Dear lord thats a lot of fire!");
				olore.add("");
				olore.add(ChatColor.GOLD + "Ultimate Ability:");
				olore.add(ChatColor.GRAY + "Right Clicking summons hell!");
				oMeta.setLore(olore);
				oHeat.setItemMeta(oMeta);
				player.getInventory().remove(Material.FIRE_CHARGE);
     			player.getInventory().addItem(oHeat);
     

     		}
     	}
     }
	  }
	
	
	
	    
	    
	    
	    
	
	
	
	
	
	
	}
