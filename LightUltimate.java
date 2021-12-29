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
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.darkenedfusion.CooldownManager;

public class LightUltimate implements Listener {

	private static HashMap<UUID, Double> totalDamage = new HashMap<UUID, Double>();
	
	//Damage Storage for Light Ultimate
	@EventHandler
	public void onEnoughHitsLight(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		if(!(damager instanceof Player)) {
			return;
		}
		Player player = (Player) event.getDamager();
		if(!(player.getInventory().getItemInMainHand().getType() == Material.GOLD_NUGGET)) {
			return;
		}
		if(!player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Light Orb")) {
			return;
		}
		totalDamage.merge(player.getUniqueId(), event.getDamage(),  Double::sum);
		double damage = totalDamage.getOrDefault(player.getUniqueId(), 0.0);
		if (damage >= 50.0) {
			if(!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ABSOLUTELIGHT)) {
				ItemStack aLight = new ItemStack(Material.FIREWORK_STAR);
	            ItemMeta oMeta = aLight.getItemMeta();
	            oMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Absolute" + ChatColor.GOLD + "" + ChatColor.BOLD + "Light");
	            List<String> olore = new ArrayList<String>();
	            oMeta.setUnbreakable(true);
	            olore.add(ChatColor.GRAY + "Its too bright!");
	            olore.add("");
	            olore.add(ChatColor.GOLD + "Ultimate Ability:");
	            olore.add(ChatColor.GRAY + "Right Clicking creates a huge light blast!");
	            oMeta.setLore(olore);
	            aLight.setItemMeta(oMeta);
				
				ItemStack chargedLight = new ItemStack(Material.GOLD_BLOCK);
				ItemMeta cMeta = chargedLight.getItemMeta();
				cMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Absolute" + ChatColor.GOLD + "" + ChatColor.BOLD + "Light");
				List<String> clore = new ArrayList<String>();
				cMeta.setUnbreakable(true);
				cMeta.addEnchant(Enchantment.DURABILITY, 1, false);
				cMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				clore.add(ChatColor.GRAY + "Its too bright!");
				clore.add("");
				clore.add(ChatColor.GOLD + "Ultimate Ability:");
				clore.add(ChatColor.GRAY + "Right Clicking creates a huge light blast!");
				cMeta.setLore(clore);
				chargedLight.setItemMeta(cMeta);
				
				if(player.getInventory().contains(aLight)) {
					player.getInventory().remove(aLight);
					player.getInventory().addItem(chargedLight);
					totalDamage.put(player.getUniqueId(), 0.0);
					
				} else {
					return;
				}
			
			 CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ABSOLUTELIGHT, 60);
		}
	}
	}
	
	//Absolute Light Move
	@EventHandler
	public void onLightUltimateClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		   if(player.getInventory().getItemInMainHand().getType().equals(Material.GOLD_BLOCK)) {
			   if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "" + ChatColor.BOLD + "Absolute" + ChatColor.GOLD + "" + ChatColor.BOLD + "Light")) {
     	if(event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
     			
     			
     			player.launchProjectile(Arrow.class);
     			
     			
     			ItemStack aLight = new ItemStack(Material.FIREWORK_STAR);
				ItemMeta oMeta = aLight.getItemMeta();
				oMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Absolute" + ChatColor.GOLD + "" + ChatColor.BOLD + "Light");
				List<String> olore = new ArrayList<String>();
				oMeta.setUnbreakable(true);
				olore.add(ChatColor.GRAY + "Its too bright!");
				olore.add("");
				olore.add(ChatColor.GOLD + "Ultimate Ability:");
				olore.add(ChatColor.GRAY + "Right Clicking creates a huge light blast!");
				oMeta.setLore(olore);
				aLight.setItemMeta(oMeta);
				player.getInventory().remove(Material.GOLD_BLOCK);
     			player.getInventory().addItem(aLight);
     

     		}
     	}
     }
	  }
	
	//Absolute Light Move Effects
	@EventHandler
	public void onLightUltimateLand(ProjectileHitEvent event) {
		if(event.getEntity() instanceof Arrow) {
			Arrow arrow = (Arrow) event.getEntity();
			if(event.getEntity().getShooter() instanceof Player) {
				Player player = (Player) event.getEntity().getShooter();	
				System.out.println("detects shooter");
			if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "" + ChatColor.BOLD + "Absolute" + ChatColor.GOLD + "" + ChatColor.BOLD + "Light")) {
				
			AreaEffectCloud ulightCircle = (AreaEffectCloud) arrow.getWorld().spawnEntity(arrow.getLocation(), EntityType.AREA_EFFECT_CLOUD);
			ulightCircle.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, 60, 4), true);
			ulightCircle.setRadius(5);
			ulightCircle.setWaitTime(0);
			ulightCircle.setDuration(100);
			ulightCircle.setCustomName("Ultimate Light Circle");
			ulightCircle.setCustomNameVisible(false);
			ulightCircle.setParticle(Particle.REDSTONE, new Particle.DustOptions(Color.ORANGE, 2));
			}
		}
		}
	}
	

}
