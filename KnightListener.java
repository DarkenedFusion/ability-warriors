package Classes;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.md_5.bungee.api.ChatColor;

public class KnightListener implements Listener {
	
	//Knight Sword Ability
	@EventHandler
	public void onKnightSword(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		if(damager instanceof Player) {
			Player player = (Player) event.getDamager();
			if(player.getInventory().getItemInMainHand().getType().equals(Material.STONE_SWORD)) {
					double randDouble = Math.random();
					if(randDouble <= 0.05D) {
						LivingEntity victim = (LivingEntity) event.getEntity();
						victim.getWorld().strikeLightning(victim.getLocation());
					}
					
				}
			}
		
	}

}
