package Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
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

public class ZeusUltimate implements Listener {
	
	private static HashMap<UUID, Double> totalDamage = new HashMap<UUID, Double>();
	
	
	//Zeus Ultimate Hit Detection
	@EventHandler
	public void onEnoughHitsZeus(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		if(!(damager instanceof Player)) {
			return;
		}
		Player player = (Player) event.getDamager();
		if(!(player.getInventory().getItemInMainHand().getType() == Material.END_ROD)) {
			return;
		}
		if(!player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Lightning Bolt")) {
			return;
		}
		totalDamage.merge(player.getUniqueId(), event.getDamage(),  Double::sum);
		double damage = totalDamage.getOrDefault(player.getUniqueId(), 0.0);
		if (damage >= 50.0) {
			if(!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.HOLYTHUNDER)) {
				ItemStack zUlt = new ItemStack(Material.FIREWORK_STAR);
				ItemMeta oMeta = zUlt.getItemMeta();
				oMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Holy" + ChatColor.GOLD + "" + ChatColor.BOLD + "Thunder");
				List<String> olore = new ArrayList<String>();
				oMeta.setUnbreakable(true);
				olore.add(ChatColor.GRAY + "Thou has been smitten!");
				olore.add("");
				olore.add(ChatColor.GOLD + "Ultimate Ability:");
				olore.add(ChatColor.GRAY + "Holy Thunder: Smites all players!");
				oMeta.setLore(olore);
				zUlt.setItemMeta(oMeta);
				
				ItemStack chargedZeus = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
				ItemMeta cMeta = chargedZeus.getItemMeta();
				cMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Holy" + ChatColor.GOLD + "" + ChatColor.BOLD + "Thunder");
				List<String> clore = new ArrayList<String>();
				cMeta.setUnbreakable(true);
				cMeta.addEnchant(Enchantment.DURABILITY, 1, false);
				cMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				clore.add(ChatColor.GRAY + "Thou has been smitten!");
				clore.add("");
				clore.add(ChatColor.GOLD + "Ultimate Ability:");
				clore.add(ChatColor.GRAY + "Holy Thunder: Smites all players!");
				cMeta.setLore(clore);
				chargedZeus.setItemMeta(cMeta);
				
				if(player.getInventory().contains(zUlt)) {
					player.getInventory().remove(zUlt);
					player.getInventory().addItem(chargedZeus);
					totalDamage.put(player.getUniqueId(), 0.0);
					
				} else {
					return;
				}
			
			 CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.HOLYTHUNDER, 60);
		}
	}
	}
	
	//Zeus Ultimate Effect
	@EventHandler
	public void onZeusUltimateClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		   if(player.getInventory().getItemInMainHand().getType().equals(Material.YELLOW_STAINED_GLASS_PANE)) {
			   if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "" + ChatColor.BOLD + "Holy" + ChatColor.GOLD + "" + ChatColor.BOLD + "Thunder")) {
  	if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
  		player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 5));
  		player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 40, 0));
  			
  				for(Player p : player.getWorld().getPlayers()) {
  						AreaEffectCloud uZeusCircle = (AreaEffectCloud) p.getWorld().spawnEntity(p.getLocation(), EntityType.AREA_EFFECT_CLOUD);
  	  					uZeusCircle.setRadius(3);
  	  					uZeusCircle.setWaitTime(0);
  	  					uZeusCircle.setDuration(100);
  	  					uZeusCircle.setCustomName("Ultimate Zeus Circle");
  	  					uZeusCircle.setCustomNameVisible(false);
  	  					uZeusCircle.setParticle(Particle.REDSTONE, new Particle.DustOptions(Color.ORANGE, 1));
  	  					p.getWorld().strikeLightning(p.getLocation());
  	  					p.getWorld().strikeLightning(p.getLocation());
  	  					p.getWorld().strikeLightning(p.getLocation());
  	  					p.getWorld().strikeLightning(p.getLocation());
  	  					p.setHealth(p.getHealth() - 20.0);
  					}
  					
  				}

  			ItemStack sUlt = new ItemStack(Material.FIREWORK_STAR);
				ItemMeta oMeta = sUlt.getItemMeta();
				oMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Holy" + ChatColor.GOLD + "" + ChatColor.BOLD + "Thunder");
				List<String> olore = new ArrayList<String>();
				oMeta.setUnbreakable(true);
				olore.add(ChatColor.GRAY + "Thou has been smitten!");
				olore.add("");
				olore.add(ChatColor.GOLD + "Ultimate Ability:");
				olore.add(ChatColor.GRAY + "Holy Thunder: Smites all players!");
				oMeta.setLore(olore);
				sUlt.setItemMeta(oMeta);
				if(player.getInventory().contains(Material.YELLOW_STAINED_GLASS_PANE)) {
				player.getInventory().remove(Material.YELLOW_STAINED_GLASS_PANE);
				player.getInventory().addItem(sUlt);
				}
				
				

  		}
  	}
  }
	  
	
	
}
