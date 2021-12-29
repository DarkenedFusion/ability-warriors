package Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
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
import org.bukkit.scheduler.BukkitRunnable;

import io.github.darkenedfusion.CooldownManager;
import io.github.darkenedfusion.Main;

public class ValkyrieUltimate implements Listener {
	
	private static HashMap<UUID, Double> totalDamage = new HashMap<UUID, Double>();
	
	
	//Valkyrie Ultimate Hit Detection
	@EventHandler
	public void onEnoughHitsValkyrie(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		if(!(damager instanceof Player)) {
			return;
		}
		Player player = (Player) event.getDamager();
		if(!(player.getInventory().getItemInMainHand().getType() == Material.GOLDEN_AXE)) {
			return;
		}
		if(!player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Valkyries Axe")) {
			return;
		}
		totalDamage.merge(player.getUniqueId(), event.getDamage(),  Double::sum);
		double damage = totalDamage.getOrDefault(player.getUniqueId(), 0.0);
		if (damage >= 50.0) {
			if(!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.CALAMITY)) {
				ItemStack vUlt = new ItemStack(Material.FIREWORK_STAR);
				ItemMeta oMeta = vUlt.getItemMeta();
				oMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Cala" + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "mity");
				List<String> olore = new ArrayList<String>();
				oMeta.setUnbreakable(true);
				olore.add(ChatColor.GRAY + "That is a mad boy");
				olore.add("");
				olore.add(ChatColor.GOLD + "Ultimate Ability:");
				olore.add(ChatColor.GRAY + "Calamity: Explodes the ground and gives the player speed 3");
				oMeta.setLore(olore);
				vUlt.setItemMeta(oMeta);
				
				ItemStack chargedV = new ItemStack(Material.RED_DYE);
				ItemMeta cMeta = chargedV.getItemMeta();
				cMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Cala" + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "mity");
				List<String> clore = new ArrayList<String>();
				cMeta.setUnbreakable(true);
				cMeta.addEnchant(Enchantment.DURABILITY, 1, false);
				cMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				clore.add(ChatColor.GRAY + "That is a mad boy");
				clore.add("");
				clore.add(ChatColor.GOLD + "Ultimate Ability:");
				clore.add(ChatColor.GRAY + "Calamity: Explodes the ground and gives the player speed 3");
				cMeta.setLore(clore);
				chargedV.setItemMeta(cMeta);
				
				if(player.getInventory().contains(vUlt)) {
					player.getInventory().remove(vUlt);
					player.getInventory().addItem(chargedV);
					totalDamage.put(player.getUniqueId(), 0.0);
					
				} else {
					return;
				}
			
			 CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.CALAMITY, 60);
		}
	}
	}
	
	
	//Calamity Effects
	@EventHandler
	public void onValkyrieUltimateClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		   if(player.getInventory().getItemInMainHand().getType().equals(Material.RED_DYE)) {
			   if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Cala" + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "mity")) {
				   if(event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
     			
     			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 3));
     			
     			
     			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 254));
				player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 30, 244));
				new BukkitRunnable() {
					double phi = 0;
					Location loc = player.getLocation();
					
					

					@Override
					public void run() {
						phi += Math.PI/10;
						for(double theta = 0; theta <= 2*Math.PI; theta +=Math.PI/40) {
							double r = 1.5;
							double x = r*Math.cos(theta)*Math.sin(phi);
							double y = r*Math.cos(phi) + 1.5;
							double z = r*Math.sin(theta)*Math.sin(phi);
							loc.add(x,y,z);
							player.getWorld().spawnParticle(Particle.DRAGON_BREATH, loc, 0, 0, 0, 0, 1);
							loc.subtract(x,y,z);
						}
						for(Entity e : loc.getChunk().getEntities()) {
							if(e.getLocation().distance(loc) < 5.0) {
								if(!e.equals(player)) {
									e.getWorld().createExplosion(e.getLocation(), 3);
									player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.5F, 0.5F);
								}
							}
						}
						
					
						
						
						if(phi > Math.PI) {
							this.cancel();
							
							}
					
				}
			
						
			
					
					
				}.runTaskTimer(Main.getInstance(), 0, 1);
     		
     			
     			
     			AreaEffectCloud uValkCircle = (AreaEffectCloud) player.getWorld().spawnEntity(player.getLocation(), EntityType.AREA_EFFECT_CLOUD);
     			uValkCircle.setRadius(10);
     			uValkCircle.setWaitTime(0);
     			uValkCircle.setDuration(100);
     			uValkCircle.setCustomName("Ultimate Light Circle");
     			uValkCircle.setCustomNameVisible(false);
     			uValkCircle.setParticle(Particle.REDSTONE, new Particle.DustOptions(Color.FUCHSIA, 1));
     			
     			
     			
     			ItemStack vUlt = new ItemStack(Material.FIREWORK_STAR);
				ItemMeta oMeta = vUlt.getItemMeta();
				oMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Cala" + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "mity");
				List<String> olore = new ArrayList<String>();
				oMeta.setUnbreakable(true);
				olore.add(ChatColor.GRAY + "That is a mad boy");
				olore.add("");
				olore.add(ChatColor.GOLD + "Ultimate Ability:");
				olore.add(ChatColor.GRAY + "Calamity: Explodes the ground and gives the player speed 3");
				oMeta.setLore(olore);
				vUlt.setItemMeta(oMeta);
				player.getInventory().remove(Material.RED_DYE);
     			player.getInventory().addItem(vUlt);
     

     		}
     	}
     }
	  }

}
