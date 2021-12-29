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

public class ShadowListener implements Listener {
	
	
	//Wither Stone Effect
	@EventHandler
	public void onShadowHit(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		if(damager instanceof Player) {
			Player player = (Player) event.getDamager();
			if(player.getInventory().getItemInMainHand().getType().equals(Material.COAL)) {
				LivingEntity victim = (LivingEntity) event.getEntity();
				victim.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
			}
		}
	}

}
