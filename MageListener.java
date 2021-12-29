package Classes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import io.github.darkenedfusion.CooldownManager;
import io.github.darkenedfusion.Main;
import net.md_5.bungee.api.ChatColor;

public class MageListener implements Listener {
	
	@EventHandler
	public void onMageRightClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(player.getInventory().getItemInMainHand().getType().equals(Material.BLAZE_ROD)) {
				if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Mage Staff")) {
					if(!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.WAND)) {
					//Fire Staff
					ItemStack mStaff = new ItemStack(Material.BLAZE_ROD);
					ItemMeta gMeta = mStaff.getItemMeta();
					gMeta.setDisplayName(ChatColor.GOLD + "Mage Staff");
					gMeta.addEnchant(Enchantment.DURABILITY, 1, false);
					gMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					gMeta.setUnbreakable(true);
					List<String> glore = new ArrayList<String>();
					glore.add(ChatColor.GRAY + "Right Click To Switch Mode!");
					glore.add("");
					glore.add(ChatColor.GOLD + "Item Ability:");
					glore.add(ChatColor.GRAY + "Fire Mode");
					gMeta.setLore(glore);
					mStaff.setItemMeta(gMeta);
					//Ice Staff
					ItemStack mStaffIce = new ItemStack(Material.BLAZE_ROD);
					ItemMeta gdMeta = mStaffIce.getItemMeta();
					gdMeta.setDisplayName(ChatColor.AQUA + "Mage Staff");
					gdMeta.addEnchant(Enchantment.DURABILITY, 1, false);
					gdMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					gdMeta.setUnbreakable(true);
					List<String> gdlore = new ArrayList<String>();
					gdlore.add(ChatColor.GRAY + "Right Click To Switch Mode!");
					gdlore.add("");
					gdlore.add(ChatColor.GOLD + "Item Ability:");
					gdlore.add(ChatColor.GRAY + "Ice Mode");
					gdMeta.setLore(gdlore);
					mStaffIce.setItemMeta(gdMeta);
					//Earth Staff
					ItemStack mStaffEarth = new ItemStack(Material.BLAZE_ROD);
					ItemMeta g1Meta = mStaffEarth.getItemMeta();
					g1Meta.setDisplayName(ChatColor.GREEN + "Mage Staff");
					g1Meta.addEnchant(Enchantment.DURABILITY, 1, false);
					g1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					g1Meta.setUnbreakable(true);
					List<String> g3lore = new ArrayList<String>();
					g3lore.add(ChatColor.GRAY + "Right Click To Switch Mode!");
					g3lore.add("");
					g3lore.add(ChatColor.GOLD + "Item Ability:");
					g3lore.add(ChatColor.GRAY + "Earth Mode");
					g1Meta.setLore(g3lore);
					mStaffEarth.setItemMeta(g1Meta);

					//Air Staff
					ItemStack mStaffAir = new ItemStack(Material.BLAZE_ROD);
					ItemMeta gd1Meta = mStaffAir.getItemMeta();
					gd1Meta.setDisplayName(ChatColor.WHITE + "Mage Staff");
					gd1Meta.addEnchant(Enchantment.DURABILITY, 1, false);
					gd1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					gd1Meta.setUnbreakable(true);
					List<String> gd4lore = new ArrayList<String>();
					gd4lore.add(ChatColor.GRAY + "Right Click To Switch Mode!");
					gd4lore.add("");
					gd4lore.add(ChatColor.GOLD + "Item Ability:");
					gd4lore.add(ChatColor.GRAY + "Air Mode");
					gd1Meta.setLore(gd4lore);
					mStaffAir.setItemMeta(gd1Meta);
					

					if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Mage Staff")) {
						player.getInventory().remove(mStaff);
						player.getInventory().addItem(mStaffIce);
						try {
							wait(5);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
					if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Mage Staff")) {
						player.getInventory().remove(mStaffIce);
						player.getInventory().addItem(mStaffEarth);
						try {
							wait(5);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Mage Staff")) {
						player.getInventory().remove(mStaffEarth);
						player.getInventory().addItem(mStaffAir);
						try {
							wait(5);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Mage Staff")) {
						player.getInventory().remove(mStaffAir);
						player.getInventory().addItem(mStaff);
						try {
							wait(5);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.WAND, 5);
				}
				}
			}
		}
		//Fire Wand Spell
		if(event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Mage Staff")) {
				  if(!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.FSPELL)) {
		                new BukkitRunnable() {

		                    Vector dir = player.getEyeLocation().getDirection().normalize();
		                    Location loc = player.getEyeLocation();
		                    double t = 0;
		                    @Override
		                    public void run() {
		                        t += 1.0;
		                        double x = dir.getX() * t;
		                        double y = dir.getY() * t;
		                        double z = dir.getZ() * t;
		                        loc.add(x,y,z);
		                        player.getWorld().spawnParticle(Particle.FLAME, loc, 0, 0, 0, 0, 5);
		                        
		                        //If particles hit a solid block
		                        if (loc.getBlock().getType().isSolid()){
		                        	
		                        	
		                        	
		                        	for (int degree = 0; degree < 360; degree++) {
		                 			    double radians = Math.toRadians(degree);
		                 			    double x1 = Math.cos(radians);
		                 			    double z1 = Math.sin(radians);
		                 			    loc.add(x1,0,z1);
		                 			    player.getWorld().spawnParticle(Particle.FLAME, loc, 1);
		                 			    loc.subtract(x1,0,z1);
		                 			}
		                        }
		                        //Hit detection
		                        for(Entity e : loc.getWorld().getEntities()) {
		                            if(e.getLocation().distance(loc) < 1.0) {
		                                if(!e.equals(player)) {
		                                    Damageable d = (Damageable) e;
		                                    d.damage(5, player);
		                                    d.setFireTicks(40);
		                                }
		                            }
		                        }
		                        
		                        
		                        loc.subtract(x,y,z);
		                        if(t > 40) {
		                            this.cancel();
		                        }
		                    }
		                    
		                }.runTaskTimer(Main.getInstance(), 0, 1);
		                
		                CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.FSPELL, 2);
		            }
			}
			//Ice Wand Spell
			if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Mage Staff")) {
				 if(!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ISPELL)) {
				  new BukkitRunnable() {

						 Vector dir = player.getEyeLocation().getDirection().normalize();
		                    Location loc = player.getEyeLocation();
		                    double t = 0;
		                    @Override
		                    public void run() {
		                        t += 1.0;
		                        double x = dir.getX() * t;
		                        double y = dir.getY() * t;
		                        double z = dir.getZ() * t;
		                        loc.add(x,y,z);
		                        player.getWorld().spawnParticle(Particle.REDSTONE, loc, 0, 0, 0, 0, 5, new Particle.DustOptions(Color.AQUA, 1));
		                        
		                        //If particles hit a solid block
		                        
		                        //Hit detection
		                        for(Entity e : loc.getWorld().getEntities()) {
		                            if(e.getLocation().distance(loc) < 1.0) {
		                                if(!e.equals(player)) {
		                                    Damageable d = (Damageable) e;
		                                    d.damage(5, player);
		                                    
		                                }
		                            }
		                        }
		                        
		                        for(Entity e : loc.getWorld().getEntities()) {
		                            if(e.getLocation().distance(loc) < 1.0) {
		                                if(!e.equals(player)) {
		                                    LivingEntity l = (LivingEntity) e;
		                                    l.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0));
		                                    
		                                }
		                            }
		                        }
		                        
		                        
		                        loc.subtract(x,y,z);
		                        if(t > 40) {
		                            this.cancel();
		                        }
		                    }
		                    
		                }.runTaskTimer(Main.getInstance(), 0, 1);
				
		                CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ISPELL, 2);
				 }
				
				
			}
			
			//Earth Spell
			if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Mage Staff")) {
				
				 if(!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ESPELL)) {
				  new BukkitRunnable() {

						 Vector dir = player.getEyeLocation().getDirection().normalize();
		                    Location loc = player.getEyeLocation();
		                    double t = 0;
		                    @Override
		                    public void run() {
		                        t += 1.0;
		                        double x = dir.getX() * t;
		                        double y = dir.getY() * t;
		                        double z = dir.getZ() * t;
		                        loc.add(x,y,z);
		                        player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, loc, 0, 0, 0, 0, 5);
		                        
		                        //If particles hit a solid block
		                        
		                        //Hit detection
		                        for(Entity e : loc.getWorld().getEntities()) {
		                            if(e.getLocation().distance(loc) < 1.0) {
		                                if(!e.equals(player)) {
		                                    Damageable d = (Damageable) e;
		                                    d.damage(5, player);
		                                    
		                                }
		                            }
		                        }
		                        
		                        for(Entity e : loc.getWorld().getEntities()) {
		                            if(e.getLocation().distance(loc) < 1.0) {
		                                if(!e.equals(player)) {
		                                    LivingEntity l = (LivingEntity) e;
		                                    l.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 40, 0));
		                                    
		                                }
		                            }
		                        }
		                        
		                        
		                        loc.subtract(x,y,z);
		                        if(t > 40) {
		                            this.cancel();
		                        }
		                    }
		                    
		                }.runTaskTimer(Main.getInstance(), 0, 1);
		                CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ESPELL, 2);
				 }
			}
			
			//Air Spell
			if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Mage Staff")) {
				 if(!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ASPELL)) {
				  new BukkitRunnable() {

						 Vector dir = player.getEyeLocation().getDirection().normalize();
		                    Location loc = player.getEyeLocation();
		                    double t = 0;
		                    @Override
		                    public void run() {
		                        t += 1.0;
		                        double x = dir.getX() * t;
		                        double y = dir.getY() * t;
		                        double z = dir.getZ() * t;
		                        loc.add(x,y,z);
		                        player.getWorld().spawnParticle(Particle.REDSTONE, loc, 0, 0, 0, 0, 5, new Particle.DustOptions(Color.WHITE, 1));
		                        
		                        //If particles hit a solid block
		                        
		                        //Hit detection
		                        for(Entity e : loc.getWorld().getEntities()) {
		                            if(e.getLocation().distance(loc) < 1.0) {
		                                if(!e.equals(player)) {
		                                    Damageable d = (Damageable) e;
		                                    d.damage(5, player);
		                                    Vector v = d.getVelocity().multiply(new Vector(0, 2, 0));
		                                    d.setVelocity(v);
		                                    
		                                }
		                            }
		                        }
		                        
		                        
		                        
		                        loc.subtract(x,y,z);
		                        if(t > 40) {
		                            this.cancel();
		                        }
		                    }
		                    
		                }.runTaskTimer(Main.getInstance(), 0, 1);
		                CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ASPELL, 2);
				 }
			}
			}
		}
	
	}
	




	
	
