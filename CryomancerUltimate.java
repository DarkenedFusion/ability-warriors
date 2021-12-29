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

public class CryomancerUltimate implements Listener {
	
	private static HashMap<UUID, Double> totalDamage = new HashMap<UUID, Double>();
	
	//Cryomancer Ultimate Hit Detection
	@EventHandler
	public void onEnoughHitsCryomancer(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		if(!(damager instanceof Player)) {
			return;
		}
		Player player = (Player) event.getDamager();
		if(!(player.getInventory().getItemInMainHand().getType() == Material.IRON_SWORD)) {
			return;
		}
		if(!player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA + "Frozen Dagger")) {
			return;
		}
		totalDamage.merge(player.getUniqueId(), event.getDamage(),  Double::sum);
		double damage = totalDamage.getOrDefault(player.getUniqueId(), 0.0);
		if (damage >= 50.0) {
			if(!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.GLACIERFREEZE)) {
				ItemStack gFreeze = new ItemStack(Material.FIREWORK_STAR);
				ItemMeta oMeta = gFreeze.getItemMeta();
				oMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "GLACIER" + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "FREEZE");
				List<String> olore = new ArrayList<String>();
				oMeta.setUnbreakable(true);
				olore.add(ChatColor.GRAY + "Covers the body with powerful ice.");
				olore.add("");
				olore.add(ChatColor.GOLD + "Ultimate Ability:");
				olore.add(ChatColor.GRAY + "Glacier Freeze: Gives Resistance II for 20 seconds.");
				oMeta.setLore(olore);
				gFreeze.setItemMeta(oMeta);
				
				ItemStack chargedFreeze = new ItemStack(Material.PACKED_ICE);
				ItemMeta cMeta = chargedFreeze.getItemMeta();
				cMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "GLACIER" + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "FREEZE");
				List<String> clore = new ArrayList<String>();
				cMeta.setUnbreakable(true);
				cMeta.addEnchant(Enchantment.DURABILITY, 1, false);
				cMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				clore.add(ChatColor.GRAY + "Covers the body with powerful ice.");
				clore.add("");
				clore.add(ChatColor.GOLD + "Ultimate Ability:");
				clore.add(ChatColor.GRAY + "Glacier Freeze: Gives Resistance II for 20 seconds.");
				cMeta.setLore(clore);
				chargedFreeze.setItemMeta(cMeta);
				
				if(player.getInventory().contains(gFreeze)) {
					player.getInventory().remove(gFreeze);
					player.getInventory().addItem(chargedFreeze);
					totalDamage.put(player.getUniqueId(), 0.0);
					
				} else {
					return;
				}
			
			 CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.GLACIERFREEZE, 60);
		}
	}
	}
	
	
	//Cryomancer Ultimate Effects
	@EventHandler
	public void onCryoUltimateClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		   if(player.getInventory().getItemInMainHand().getType().equals(Material.PACKED_ICE)) {
			   if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "" + ChatColor.BOLD + "GLACIER" + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "FREEZE")) {
  	if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
  			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 1));

  			ItemStack sUlt = new ItemStack(Material.FIREWORK_STAR);
				ItemMeta oMeta = sUlt.getItemMeta();
				oMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "GLACIER" + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "FREEZE");
				List<String> olore = new ArrayList<String>();
				oMeta.setUnbreakable(true);
				olore.add(ChatColor.GRAY + "Covers the body with powerful ice.");
				olore.add("");
				olore.add(ChatColor.GOLD + "Ultimate Ability:");
				olore.add(ChatColor.GRAY + "Glacier Freeze: Gives Resistance II for 20 seconds");
				oMeta.setLore(olore);
				sUlt.setItemMeta(oMeta);
				if(player.getInventory().contains(Material.PACKED_ICE)) {
				player.getInventory().remove(Material.PACKED_ICE);
				player.getInventory().addItem(sUlt);
				}
				
				

  		}
  	}
  }
	  }
	
}
