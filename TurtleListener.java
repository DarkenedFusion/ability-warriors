package Classes;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;

public class TurtleListener implements Listener {
	
	@EventHandler
	public void onTurtleHit(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		if(damager instanceof Player) {
			Player player = (Player) event.getDamager();
			if(player.getInventory().getItemInMainHand().getType().equals(Material.STONE_SWORD)) {
				if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Turtle Bite")) {
					double randDouble = Math.random();
					if(randDouble <= 0.05D) {
						LivingEntity victim = (LivingEntity) event.getEntity();
						victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 3));
					}
					
				}
			}
		}
	
	}
	




	
	
}