package io.github.darkenedfusion;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.scheduler.BukkitRunnable;



public class CooldownManager {
	
	private static final HashMap<UUID, HashMap<CustomEffects, CooldownInfo>> cooldowns = new HashMap<>();

    public enum CustomEffects {
        FLAMETHROWER,
        OVERHEAT,
        LIGHTBLAST,
        FROZENSTING,
        ICARUS,
        ICEBOMB,
        ABSOLUTELIGHT,
        TRANSMISSION,
        ARMOR,
        GUARD,
        CALAMITY,
        HOLYTHUNDER,
        GLACIERFREEZE,
        OCEANICWRATH,
        SHELLBREAK,
        FSPELL,
        ISPELL,
        ESPELL,
        ASPELL,
        ICEWAND,
        WAND,
        ELEMENTALBURST,
        
        
        
        
        

    }

    public static class CooldownInfo {
        private final long StartTime;
        private final long Cooldown;

        public CooldownInfo(long cooldown) {
            this.StartTime = System.currentTimeMillis();
            this.Cooldown = cooldown;
        }

        public long getStartTime() {
            return StartTime;
        }

        public long getCooldown() {
            return Cooldown;
        }
    }

    static {
        new BukkitRunnable() {
            public void run() {

                HashMap<UUID, HashMap<CustomEffects, CooldownInfo>> cooldownList = new HashMap<>(cooldowns);

                for (UUID playerId : cooldownList.keySet()) {
                    HashMap<CustomEffects, CooldownInfo> cooldownInfoList = cooldownList.get(playerId);

                    for (CustomEffects effect : cooldownInfoList.keySet()) {
                        CooldownInfo cooldownInfo = cooldownInfoList.get(effect);

                        long time = System.currentTimeMillis() - cooldownInfo.getStartTime();

                        if (time  >= (1000 * cooldownInfo.getCooldown()))
                            removeCooldown(playerId, effect);
                    }
                }
            }
        }.runTaskTimerAsynchronously(Main.getInstance(), 20L, 20L);
    }

    public static void setCooldown(UUID playerId, CustomEffects effect, long cooldown) {
        HashMap<CustomEffects, CooldownInfo> cooldownList = cooldowns.getOrDefault(playerId, new HashMap<>());
        cooldownList.put(effect, new CooldownInfo(cooldown));
        cooldowns.put(playerId, cooldownList);
    }

    public static boolean hasCooldown(UUID playerId, CustomEffects effect) {
        if (!cooldowns.containsKey(playerId))
            return false;
        return cooldowns.get(playerId).containsKey(effect);
    }

    public static int getCooldown(UUID playerId, CustomEffects effect) {

        if (!cooldowns.containsKey(playerId))
            return 0;

        if (!cooldowns.get(playerId).containsKey(effect))
            return 0;

        return Math.toIntExact((System.currentTimeMillis() - cooldowns.get(playerId).get(effect).getStartTime()) / 1000);
    }

    public static void removeCooldown(UUID playerId, CustomEffects effect) {
        HashMap<CustomEffects, CooldownInfo> cooldownList = cooldowns.getOrDefault(playerId, new HashMap<>());

        cooldownList.remove(effect);
        cooldowns.remove(playerId);
        if (cooldownList.size() > 0)
            cooldowns.put(playerId, cooldownList);
    }
}
