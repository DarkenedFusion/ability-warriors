package Classes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class FishermanListener implements Listener {
	
	//Water Gun Effect
	@EventHandler
	public void onShootwGun(ProjectileHitEvent event) {
		if(event.getEntity() instanceof Arrow) {
			if(event.getEntity().getShooter() instanceof Player) {
				Player player = (Player) event.getEntity().getShooter();
			if(player.getInventory().getItemInMainHand().getType().equals(Material.BOW)) {
				if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_BLUE + "Water Gun")) {
			Arrow arrow = (Arrow) event.getEntity();
				arrow.setGravity(false);
				arrow.setDamage(1);
				arrow.getWorld().spawnEntity(arrow.getLocation(), EntityType.PUFFERFISH);
			
			}
		}
		}
	}
	}

}
