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
import org.bukkit.scheduler.BukkitRunnable;

import io.github.darkenedfusion.CooldownManager;
import io.github.darkenedfusion.Main;

public class FishermanUltimate implements Listener {
	
	private static HashMap<UUID, Double> totalDamage = new HashMap<UUID, Double>();
	
	//Fisherman Ultimate Hit Detection
	@EventHandler
	public void onEnoughHitsFisherman(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		if(!(damager instanceof Player)) {
			return;
		}
		Player player = (Player) event.getDamager();
		if(!(player.getInventory().getItemInMainHand().getType() == Material.TRIDENT)) {
			return;
		}
		if(!player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Gills")) {
			return;
		}
		totalDamage.merge(player.getUniqueId(), event.getDamage(),  Double::sum);
		double damage = totalDamage.getOrDefault(player.getUniqueId(), 0.0);
		if (damage >= 50.0) {
			if(!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.OCEANICWRATH)) {
				ItemStack oWrath = new ItemStack(Material.FIREWORK_STAR);
				ItemMeta oMeta = oWrath.getItemMeta();
				oMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "OCEANIC" + ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "WRATH");
				List<String> olore = new ArrayList<String>();
				oMeta.setUnbreakable(true);
				olore.add(ChatColor.GRAY + "Drag all your enemies under the sea!");
				olore.add("");
				olore.add(ChatColor.GOLD + "Ultimate Ability:");
				olore.add(ChatColor.GRAY + "Oceanic Wrath: Drowns and blinds all players!");
				oMeta.setLore(olore);
				oWrath.setItemMeta(oMeta);
				
				ItemStack chargedWrath = new ItemStack(Material.BLUE_DYE);
				ItemMeta cMeta = chargedWrath.getItemMeta();
				cMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "OCEANIC" + ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "WRATH");
				List<String> clore = new ArrayList<String>();
				cMeta.setUnbreakable(true);
				cMeta.addEnchant(Enchantment.DURABILITY, 1, false);
				cMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				clore.add(ChatColor.GRAY + "Drag all your enemies under the sea!");
				clore.add("");
				clore.add(ChatColor.GOLD + "Ultimate Ability:");
				clore.add(ChatColor.GRAY + "Oceanic Wrath: Drowns and blinds all players!");
				cMeta.setLore(clore);
				chargedWrath.setItemMeta(cMeta);
				
				if(player.getInventory().contains(oWrath)) {
					player.getInventory().remove(oWrath);
					player.getInventory().addItem(chargedWrath);
					totalDamage.put(player.getUniqueId(), 0.0);
					
				} else {
					return;
				}
			
			 CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.OCEANICWRATH, 60);
		}
	}
	}
	
	//Fisherman Ultimate Effects
	@EventHandler
	public void onFishermanUltimateClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		   if(player.getInventory().getItemInMainHand().getType().equals(Material.BLUE_DYE)) {
			   if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "" + ChatColor.BOLD + "OCEANIC" + ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "WRATH")) {
  	if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
  		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 0));
  		
  		ItemStack fRod = new ItemStack(Material.FISHING_ROD);
        ItemMeta fMeta = fRod.getItemMeta();
        fMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        fMeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
        fMeta.setDisplayName(ChatColor.BLUE + "Oceanic" + ChatColor.DARK_BLUE + "Rod");
        List<String> fLore = new ArrayList<String>();
        fMeta.setUnbreakable(true);
        fLore.add(ChatColor.GRAY + "Smack your opponents with a rod!");
        fLore.add("");
        fLore.add(ChatColor.GOLD + "Ultimate Ability:");
        fLore.add(ChatColor.GRAY + "Oceanic Rod: Knockback and Sharpness!");
        fMeta.setLore(fLore);
        fRod.setItemMeta(fMeta);
        player.getInventory().addItem(fRod);
          
                      new BukkitRunnable() {

                        @Override
                        public void run() {
                            player.getInventory().remove(fRod);
                            
                        }
                          
                      }.runTaskLater(Main.getInstance(), 200L);
  			
  				
  					
  				}

  			ItemStack sUlt = new ItemStack(Material.FIREWORK_STAR);
				ItemMeta oMeta = sUlt.getItemMeta();
				oMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "OCEANIC" + ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "WRATH");
				List<String> olore = new ArrayList<String>();
				oMeta.setUnbreakable(true);
				olore.add(ChatColor.GRAY + "Drag all your enemies under the sea!");
				olore.add("");
				olore.add(ChatColor.GOLD + "Ultimate Ability:");
				olore.add(ChatColor.GRAY + "Oceanic Wrath: Drowns and blinds all players!");
				oMeta.setLore(olore);
				sUlt.setItemMeta(oMeta);
				if(player.getInventory().contains(Material.BLUE_DYE)) {
				player.getInventory().remove(Material.BLUE_DYE);
				player.getInventory().addItem(sUlt);
				}
				
				

  		}
  	}
  }
}
