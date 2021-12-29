package Classes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.darkenedfusion.CooldownManager;

public class ZeusListener implements Listener {
	
	//Lightning Bolt Move
	@EventHandler
	public void onLightningBolt(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		if(damager instanceof Player) {
			Player player = (Player) event.getDamager();
			if(player.getInventory().getItemInMainHand().getType().equals(Material.END_ROD)) {
				if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Lightning Bolt")) {
					double randDouble = Math.random();
					if(randDouble <= 0.35D) {
						LivingEntity victim = (LivingEntity) event.getEntity();
						victim.getWorld().strikeLightning(victim.getLocation());
						victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0));
						player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20, 0));
					}
					
				}
			}
		}
	}

	
	
	//Icarus Feather Move
	@EventHandler
    public void onIcarusFeather(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.getInventory().getItemInMainHand().getType() == Material.FEATHER &&
                player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Icarus Feather") &&
                (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                !CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ICARUS)) {

            player.teleport(player.getLocation().add(new Vector(0, 20, 0)));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100, 0));

            CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ICARUS, 10);
        }
    }
	
	
}
