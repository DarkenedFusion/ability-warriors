package Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
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

public class ShadowUltimate implements Listener {
	
	private static HashMap<UUID, Double> totalDamage = new HashMap<UUID, Double>();
	
	//Shadow Ultimate Hit Detection
	@EventHandler
	public void onEnoughHitsShadow(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		if(!(damager instanceof Player)) {
			return;
		}
		Player player = (Player) event.getDamager();
		if(!(player.getInventory().getItemInMainHand().getType() == Material.COAL)) {
			return;
		}
		if(!player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Wither Stone")) {
			return;
		}
		totalDamage.merge(player.getUniqueId(), event.getDamage(),  Double::sum);
		double damage = totalDamage.getOrDefault(player.getUniqueId(), 0.0);
		if (damage >= 50.0) {
			if(!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.TRANSMISSION)) {
				ItemStack sUlt = new ItemStack(Material.FIREWORK_STAR);
				ItemMeta oMeta = sUlt.getItemMeta();
				oMeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Trans" + ChatColor.BLACK + "" + ChatColor.BOLD + "mission" );
				List<String> olore = new ArrayList<String>();
				oMeta.setUnbreakable(true);
				olore.add(ChatColor.GRAY + "Where'd he go?");
				olore.add("");
				olore.add(ChatColor.GOLD + "Ultimate Ability:");
				olore.add(ChatColor.GRAY + "Gives player invisibility for 5 seconds!");
				oMeta.setLore(olore);
				sUlt.setItemMeta(oMeta);
				
				ItemStack chargedShadow = new ItemStack(Material.WITHER_SKELETON_SKULL);
				ItemMeta cMeta = chargedShadow.getItemMeta();
				cMeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Trans" + ChatColor.BLACK + "" + ChatColor.BOLD + "mission" );
				List<String> clore = new ArrayList<String>();
				cMeta.setUnbreakable(true);
				cMeta.addEnchant(Enchantment.DURABILITY, 1, false);
				cMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				clore.add(ChatColor.GRAY + "Where'd he go?");
				clore.add("");
				clore.add(ChatColor.GOLD + "Ultimate Ability:");
				clore.add(ChatColor.GRAY + "Gives player invisibility for 5 seconds!");
				cMeta.setLore(clore);
				chargedShadow.setItemMeta(cMeta);
				
				if(player.getInventory().contains(sUlt)) {
					player.getInventory().remove(sUlt);
					player.getInventory().addItem(chargedShadow);
					totalDamage.put(player.getUniqueId(), 0.0);
					
				} else {
					return;
				}
			
			 CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.TRANSMISSION, 60);
		}
	}
	}
	
	//Shadow Ultimate Move
	@EventHandler
	public void onShadowTransmissionClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		   if(player.getInventory().getItemInMainHand().getType().equals(Material.WITHER_SKELETON_SKULL)) {
			   if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Trans" + ChatColor.BLACK + "" + ChatColor.BOLD + "mission")) {
  	if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
  			
  			for(Player p : player.getWorld().getPlayers()) {
  					p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 3));
  					p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 1));
  				}

				
  				if(player.hasPotionEffect(PotionEffectType.BLINDNESS) || player.hasPotionEffect(PotionEffectType.WITHER)) {
  	  				player.removePotionEffect(PotionEffectType.BLINDNESS);
  	  			player.removePotionEffect(PotionEffectType.WITHER);
  	  			}
  				
  				
  				
  				
  				
  				
  				
  				
  				
  			ItemStack sUlt = new ItemStack(Material.FIREWORK_STAR);
				ItemMeta oMeta = sUlt.getItemMeta();
				oMeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Trans" + ChatColor.BLACK + "" + ChatColor.BOLD + "mission");
				List<String> olore = new ArrayList<String>();
				oMeta.setUnbreakable(true);
				olore.add(ChatColor.GRAY + "Where'd he go?");
				olore.add("");
				olore.add(ChatColor.GOLD + "Ultimate Ability:");
				olore.add(ChatColor.GRAY + "Gives player invisibility for 5 seconds!");
				oMeta.setLore(olore);
				sUlt.setItemMeta(oMeta);
				if(player.getInventory().contains(Material.WITHER_SKELETON_SKULL)) {
				player.getInventory().remove(Material.WITHER_SKELETON_SKULL);
				player.getInventory().addItem(sUlt);
				}
				
				

  		}
  	}
  }
	  }

}
