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
import org.bukkit.scheduler.BukkitRunnable;

import io.github.darkenedfusion.CooldownManager;
import io.github.darkenedfusion.Main;

public class KnightUltimate implements Listener {
	
	private static HashMap<UUID, Double> totalDamage = new HashMap<UUID, Double>();
	
	//Knight Ultimate Hit Detection
	@EventHandler
	public void onEnoughHitsKnight(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		if(!(damager instanceof Player)) {
			return;
		}
		Player player = (Player) event.getDamager();
		if(!(player.getInventory().getItemInMainHand().getType() == Material.STONE_SWORD)) {
			return;
		}
		if(!player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Knights Sword")) {
			return;
		}
		totalDamage.merge(player.getUniqueId(), event.getDamage(),  Double::sum);
		double damage = totalDamage.getOrDefault(player.getUniqueId(), 0.0);
		if (damage >= 50.0) {
			if(!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.GUARD)) {
				ItemStack kUlt = new ItemStack(Material.FIREWORK_STAR);
				ItemMeta oMeta = kUlt.getItemMeta();
				oMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Gu" + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "ard");
				List<String> olore = new ArrayList<String>();
				oMeta.setUnbreakable(true);
				olore.add(ChatColor.GRAY + "A knights true potential");
				olore.add("");
				olore.add(ChatColor.GOLD + "Ultimate Ability:");
				olore.add(ChatColor.GRAY + "Guard: Adds 3 levels of protection for 10 seconds");
				oMeta.setLore(olore);
				kUlt.setItemMeta(oMeta);
				
				ItemStack chargedK = new ItemStack(Material.IRON_INGOT);
				ItemMeta cMeta = chargedK.getItemMeta();
				cMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Gu" + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "ard");
				List<String> clore = new ArrayList<String>();
				cMeta.setUnbreakable(true);
				cMeta.addEnchant(Enchantment.DURABILITY, 1, false);
				cMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				clore.add(ChatColor.GRAY + "A knights true potential");
				clore.add("");
				clore.add(ChatColor.GOLD + "Ultimate Ability:");
				clore.add(ChatColor.GRAY + "Guard: Adds 3 levels of protection for 10 seconds");
				cMeta.setLore(clore);
				chargedK.setItemMeta(cMeta);
				
				if(player.getInventory().contains(kUlt)) {
					player.getInventory().remove(kUlt);
					player.getInventory().addItem(chargedK);
					totalDamage.put(player.getUniqueId(), 0.0);
					
				} else {
					return;
				}
			
			 CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.GUARD, 60);
		}
	}
	}

	
	//Knight Ultimate Effects
	@EventHandler
	public void onKnightUltimateClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		   if(player.getInventory().getItemInMainHand().getType().equals(Material.IRON_INGOT)) {
			   if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "" + ChatColor.BOLD + "Gu" + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "ard")) {
     	if(event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
     			
     			player.getEquipment().getChestplate().addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
     			player.getEquipment().getLeggings().addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
     			player.getEquipment().getBoots().addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
     			
     			
     				new BukkitRunnable() {

						@Override
						public void run() {
							player.getEquipment().getChestplate().removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
			     			player.getEquipment().getLeggings().removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
			     			player.getEquipment().getBoots().removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
						}
     					
     				}.runTaskLater(Main.getInstance(), 200L);
     			
     			
     			
     			
     			ItemStack kUlt = new ItemStack(Material.FIREWORK_STAR);
				ItemMeta oMeta = kUlt.getItemMeta();
				oMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Gu" + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "ard");
				List<String> olore = new ArrayList<String>();
				oMeta.setUnbreakable(true);
				olore.add(ChatColor.GRAY + "A knights true potential");
				olore.add("");
				olore.add(ChatColor.GOLD + "Ultimate Ability:");
				olore.add(ChatColor.GRAY + "Guard: Adds 3 levels of protection for 10 seconds");
				oMeta.setLore(olore);
				kUlt.setItemMeta(oMeta);
				player.getInventory().remove(Material.IRON_INGOT);
     			player.getInventory().addItem(kUlt);
     

     		}
     	}
     }
	  }
	
}
