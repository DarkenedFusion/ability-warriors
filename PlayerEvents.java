package Misc;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffectType;

public class PlayerEvents implements Listener {
	
	private static HashMap<UUID, Double> totalDamage = new HashMap<UUID, Double>();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if(event.getPlayer() instanceof Player) {
			Player player = event.getPlayer();
			player.setMaxHealth(100);
			player.setHealth(100);
			player.getInventory().clear();
		}
	}
	
	@EventHandler
	public void onOtherPlayerDeath(PlayerDeathEvent event) {
		if(event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity().getKiller();
			LivingEntity target = (LivingEntity) event.getEntity();
			if(target instanceof Player) {
				if(player instanceof Player) {
					return;
				}
			}
			if(target.isDead()) {
				player.setHealth(player.getHealth() + 50);
			}
			if(player.getHealth() >= 100) {
				player.setHealth(100);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		if(event.getPlayer() instanceof Player) {
			Player player = event.getPlayer();
			player.getInventory().clear();
			player.setMaxHealth(100);
			player.setHealth(100);
			
		}
	}
	
	@EventHandler
	public void onPlayerDie(EntityDeathEvent event) {
		if(event.getEntity() instanceof Player) {
		Player player = (Player) event.getEntity();
		totalDamage.put(player.getUniqueId(), 0.0);
		if(player.hasPotionEffect(PotionEffectType.SLOW)) {
			player.removePotionEffect(PotionEffectType.SLOW);
		}
		if(player.hasPotionEffect(PotionEffectType.WEAKNESS)) {
			player.removePotionEffect(PotionEffectType.WEAKNESS);
		}
		}
	}

}
