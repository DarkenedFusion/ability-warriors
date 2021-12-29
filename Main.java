package io.github.darkenedfusion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import Classes.CryomancerListener;
import Classes.CryomancerUltimate;
import Classes.FishermanListener;
import Classes.FishermanUltimate;
import Classes.KnightListener;
import Classes.KnightUltimate;
import Classes.LightListener;
import Classes.LightUltimate;
import Classes.MageListener;
import Classes.PyroListener;
import Classes.PyroUltimate;
import Classes.ShadowListener;
import Classes.ShadowUltimate;
import Classes.TurtleListener;
import Classes.TurtleUltimate;
import Classes.ValkyrieUltimate;
import Classes.ZeusListener;
import Classes.ZeusUltimate;
import Misc.DoubleJump;
import Misc.PlayerEvents;

@SuppressWarnings("unused")
public class Main extends JavaPlugin implements Listener {
	//Full class variables
	
	public Inventory inv;
	
	private static Main instance;
	
	private static HashMap<UUID, Double> totalDamage = new HashMap<UUID, Double>();

	
	@Override
	public void onEnable() {
		instance = this;
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getServer().getPluginManager().registerEvents(new PyroListener(), this);
		this.getServer().getPluginManager().registerEvents(new PyroUltimate(), this);
		this.getServer().getPluginManager().registerEvents(new LightListener(), this);
		this.getServer().getPluginManager().registerEvents(new LightUltimate(), this);
		this.getServer().getPluginManager().registerEvents(new ShadowListener(), this);
		this.getServer().getPluginManager().registerEvents(new ShadowUltimate(), this);
		this.getServer().getPluginManager().registerEvents(new KnightListener(), this);
		this.getServer().getPluginManager().registerEvents(new KnightUltimate(), this);
		this.getServer().getPluginManager().registerEvents(new ValkyrieUltimate(), this);
		this.getServer().getPluginManager().registerEvents(new ZeusListener(), this);
		this.getServer().getPluginManager().registerEvents(new ZeusUltimate(), this);
		this.getServer().getPluginManager().registerEvents(new FishermanListener(), this);
		this.getServer().getPluginManager().registerEvents(new FishermanUltimate(), this);
		this.getServer().getPluginManager().registerEvents(new CryomancerListener(), this);
		this.getServer().getPluginManager().registerEvents(new CryomancerUltimate(), this);
		this.getServer().getPluginManager().registerEvents(new MageListener(), this);
		this.getServer().getPluginManager().registerEvents(new TurtleListener(), this);
		this.getServer().getPluginManager().registerEvents(new TurtleUltimate(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
		this.getServer().getPluginManager().registerEvents(new DoubleJump(), this);
		classGUI();
 
		
	}
	
	public static Main getInstance() {
        return instance;
    }

	
	//Class selector command
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(label.equalsIgnoreCase("class")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("No no no and no");
				return true;
			}
			Player player = (Player) sender;
			//opens gui
			player.openInventory(inv);
			return true;
			
		}
		return false;
    }
	
	@EventHandler
	public void onDrag(InventoryDragEvent event) {
		event.setCancelled(true);
	}
	
	
	//GUI STUFF
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if(!event.getInventory().equals(inv))
			return;
		if(event.getCurrentItem() == null) return;
		if(event.getCurrentItem().getItemMeta() == null) return;
		if(event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
		
		event.setCancelled(true);
		
		Player player = (Player) event.getWhoClicked();
		if(event.getSlot() == 1) {
			//Pyro ==========================================================================================
			player.getInventory().clear();
			
			ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
			meta.setUnbreakable(true);
			meta.setColor(Color.RED);
			chestplate.setItemMeta(meta);
			player.getEquipment().setChestplate(chestplate);
			
			ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta lmeta = (LeatherArmorMeta) leggings.getItemMeta();
			lmeta.setColor(Color.RED);
			lmeta.setUnbreakable(true);
			leggings.setItemMeta(lmeta);
			player.getEquipment().setLeggings(leggings);
			
			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta bmeta = (LeatherArmorMeta) boots.getItemMeta();
			bmeta.setColor(Color.RED);
			bmeta.setUnbreakable(true);
			boots.setItemMeta(bmeta);
			player.getEquipment().setBoots(boots);
			
			
			
			
			player.sendMessage("You have picked Pyro!");
			
			//Pyro Weapons ==========================================================================================s
			//Lighter ==========================================================================================
			ItemStack lighter = new ItemStack(Material.FLINT_AND_STEEL);
			ItemMeta lMeta = lighter.getItemMeta();
			lMeta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
			lMeta.setDisplayName(ChatColor.GOLD + "Lighter");
			lMeta.setUnbreakable(true);
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "A classic lighter, it's pretty lit");
			lore.add("");
			lore.add(ChatColor.GOLD + "Item Ability:");
			lore.add(ChatColor.GRAY + "Right Clicking lights fire!");
			lMeta.setLore(lore);
			lighter.setItemMeta(lMeta);
			player.getInventory().addItem(lighter);
			player.getInventory().setHeldItemSlot(0);
			
			
			
			//Flamethrower ==========================================================================================
			ItemStack flamethrower = new ItemStack(Material.BLAZE_ROD);
			ItemMeta fmeta = flamethrower.getItemMeta();
			fmeta.setDisplayName(ChatColor.RED + "Flamethrower");
			List<String> flore = new ArrayList<String>();
			fmeta.setUnbreakable(true);
			flore.add(ChatColor.GRAY + "What's more to say? It's a flamethrower");
			flore.add("");
			flore.add(ChatColor.GOLD + "Item Ability:");
			flore.add(ChatColor.GRAY + "Right Clicking creates a fireball!");
			fmeta.setLore(flore);
			flamethrower.setItemMeta(fmeta);
			player.getInventory().addItem(flamethrower);
			player.getInventory().setHeldItemSlot(1);
			
			//ULTIMATE OVERHEAT ==========================================================================================
			ItemStack oHeat = new ItemStack(Material.FIREWORK_STAR);
			ItemMeta oMeta = oHeat.getItemMeta();
			oMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Over" + ChatColor.GOLD + "" + ChatColor.BOLD + "heat");
			List<String> olore = new ArrayList<String>();
			oMeta.setUnbreakable(true);
			olore.add(ChatColor.GRAY + "Dear lord thats a lot of fire!");
			olore.add("");
			olore.add(ChatColor.GOLD + "Ultimate Ability:");
			olore.add(ChatColor.GRAY + "Right Clicking summons hell!");
			oMeta.setLore(olore);
			oHeat.setItemMeta(oMeta);
			player.getInventory().addItem(oHeat);
			player.getInventory().setHeldItemSlot(2);
			
			
			
			
			
		}
		 // Light ==========================================================================================
		if(event.getSlot() == 3) {
			player.getInventory().clear();
			
			
			ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
			meta.setUnbreakable(true);
			meta.setColor(Color.YELLOW);
			chestplate.setItemMeta(meta);
			player.getEquipment().setChestplate(chestplate);
			
			ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta lgmeta = (LeatherArmorMeta) leggings.getItemMeta();
			lgmeta.setColor(Color.YELLOW);
			lgmeta.setUnbreakable(true);
			leggings.setItemMeta(lgmeta);
			player.getEquipment().setLeggings(leggings);
			
			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta bmeta = (LeatherArmorMeta) boots.getItemMeta();
			bmeta.setColor(Color.YELLOW);
			bmeta.setUnbreakable(true);
			boots.setItemMeta(bmeta);
			player.getEquipment().setBoots(boots);
			
			//Light Weapons ==========================================================================================
			//Light Orb ==========================================================================================
			ItemStack lOrb = new ItemStack(Material.GOLD_NUGGET);
			ItemMeta lmeta = lOrb.getItemMeta();
			lmeta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
			lmeta.setDisplayName(ChatColor.YELLOW + "Light Orb");
			List<String> llore = new ArrayList<String>();
			lmeta.setUnbreakable(true);
			llore.add(ChatColor.GRAY + "You live to see another day... Oh wait..");
			llore.add("");
			llore.add(ChatColor.GOLD + "Item Ability:");
			llore.add(ChatColor.GRAY + "Gives a 10% chance to blind enemies.");
			lmeta.setLore(llore);
			lOrb.setItemMeta(lmeta);
			player.getInventory().addItem(lOrb);
			player.getInventory().setHeldItemSlot(0);
			
			//Light Blast ==========================================================================================
			ItemStack lBlast = new ItemStack(Material.GOLD_INGOT);
			ItemMeta lMeta = lBlast.getItemMeta();
			lMeta.setDisplayName(ChatColor.YELLOW + "Light Blast");
			List<String> lblore = new ArrayList<String>();
			lMeta.setUnbreakable(true);
			lblore.add(ChatColor.GRAY + "Pew Pew");
			lblore.add("");
			lblore.add(ChatColor.GOLD + "Item Ability:");
			lblore.add(ChatColor.GRAY + "Right Clicking shoots a blast of light!");
			lMeta.setLore(lblore);
			lBlast.setItemMeta(lMeta);
			player.getInventory().addItem(lBlast);
			player.getInventory().setHeldItemSlot(1);
			
			//ULTIMATE ABSOLUTE LIGHT ==========================================================================================
			ItemStack aLight = new ItemStack(Material.FIREWORK_STAR);
			ItemMeta oMeta = aLight.getItemMeta();
			oMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Absolute" + ChatColor.GOLD + "" + ChatColor.BOLD + "Light");
			List<String> olore = new ArrayList<String>();
			oMeta.setUnbreakable(true);
			olore.add(ChatColor.GRAY + "Its too bright!");
			olore.add("");
			olore.add(ChatColor.GOLD + "Ultimate Ability:");
			olore.add(ChatColor.GRAY + "Right Clicking creates a huge light blast!");
			oMeta.setLore(olore);
			aLight.setItemMeta(oMeta);
			player.getInventory().addItem(aLight);
			player.getInventory().setHeldItemSlot(2);
			
		}
		//Shadow ==========================================================================================
		if(event.getSlot() == 5) {
			player.getInventory().clear();
			
			ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
			meta.setUnbreakable(true);
			meta.setColor(Color.BLACK);
			chestplate.setItemMeta(meta);
			player.getEquipment().setChestplate(chestplate);
			
			ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta lmeta = (LeatherArmorMeta) leggings.getItemMeta();
			lmeta.setColor(Color.BLACK);
			lmeta.setUnbreakable(true);
			leggings.setItemMeta(lmeta);
			player.getEquipment().setLeggings(leggings);
			
			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta bmeta = (LeatherArmorMeta) boots.getItemMeta();
			bmeta.setColor(Color.BLACK);
			bmeta.setUnbreakable(true);
			boots.setItemMeta(bmeta);
			player.getEquipment().setBoots(boots);
			
			ItemStack hArrow = new ItemStack(Material.TIPPED_ARROW, 24);
			PotionMeta shmeta = (PotionMeta) hArrow.getItemMeta();
			shmeta.setBasePotionData(new PotionData(PotionType.INSTANT_DAMAGE));
			hArrow.setItemMeta(shmeta);
			
			
			
			
			
			//Shadow Weapons ==========================================================================================
			
			//Wither Stone ==========================================================================================
			ItemStack wStone = new ItemStack(Material.COAL);
			ItemMeta wiMeta = wStone.getItemMeta();
			wiMeta.setDisplayName(ChatColor.GRAY + "Wither Stone");
			List<String> wlore = new ArrayList<String>();
			wiMeta.setUnbreakable(true);
			wiMeta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
			wlore.add(ChatColor.GRAY + "Idea by an idiot!");
			wlore.add("");
			wlore.add(ChatColor.GOLD + "Item Ability:");
			wlore.add(ChatColor.GRAY + "Gives Wither 1 for 2 Seconds.");
			wiMeta.setLore(wlore);
			wStone.setItemMeta(wiMeta);
			player.getInventory().addItem(wStone);
			player.getInventory().setHeldItemSlot(1);
			
			//Shadow Bow ==========================================================================================
			ItemStack wBow = new ItemStack(Material.BOW);
			ItemMeta wMeta = wBow.getItemMeta();
			wMeta.setDisplayName(ChatColor.DARK_GRAY + "Wither Bow");
			List<String> lore = new ArrayList<String>();
			wMeta.setUnbreakable(true);
			wMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
			lore.add(ChatColor.GRAY + "A bow infused with shadows.");
			lore.add("");
			lore.add(ChatColor.GOLD + "Item Ability:");
			lore.add(ChatColor.GRAY + "It's a bow. Not that hard to use.");
			wMeta.setLore(lore);
			wBow.setItemMeta(wMeta);
			player.getInventory().addItem(wBow);
			player.getInventory().setHeldItemSlot(0);
			player.getInventory().addItem(hArrow);
			
			
			//ULTIMATE SHADOW ==========================================================================================
			ItemStack sUlt = new ItemStack(Material.FIREWORK_STAR);
			ItemMeta oMeta = sUlt.getItemMeta();
			oMeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Trans" + ChatColor.BLACK + "" + ChatColor.BOLD + "mission");
			List<String> olore = new ArrayList<String>();
			oMeta.setUnbreakable(true);
			olore.add(ChatColor.GRAY + "Where'd he go?");
			olore.add("");
			olore.add(ChatColor.GOLD + "Ultimate Ability:");
			olore.add(ChatColor.GRAY + "Gives player invisibility for 5 seconds!");
			oMeta.setLore(olore);
			sUlt.setItemMeta(oMeta);
			player.getInventory().addItem(sUlt);
			player.getInventory().setHeldItemSlot(2);
			
			//Knight ==========================================================================================
			
		}
			if(event.getSlot() == 7) {
			player.getInventory().clear();
			
			ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
			meta.setUnbreakable(true);
			meta.setColor(Color.GRAY);
			chestplate.setItemMeta(meta);
			player.getEquipment().setChestplate(chestplate);
			
			ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta lmeta = (LeatherArmorMeta) leggings.getItemMeta();
			lmeta.setColor(Color.GRAY);
			lmeta.setUnbreakable(true);
			leggings.setItemMeta(lmeta);
			player.getEquipment().setLeggings(leggings);
			
			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta bmeta = (LeatherArmorMeta) boots.getItemMeta();
			bmeta.setColor(Color.GRAY);
			bmeta.setUnbreakable(true);
			boots.setItemMeta(bmeta);
			player.getEquipment().setBoots(boots);
			
			
			//Knight Weapons ==========================================================================================
			//Knight Sword ==========================================================================================
			ItemStack knightSword = new ItemStack(Material.STONE_SWORD);
			ItemMeta knightMeta = knightSword.getItemMeta();
			knightMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
			knightMeta.setDisplayName(ChatColor.AQUA + "Knights Sword");
			knightMeta.setUnbreakable(true);
			List<String> klore = new ArrayList<String>();
			klore.add(ChatColor.GRAY + "A legendary blade!");
			klore.add("");
			klore.add(ChatColor.GOLD + "Item Ability:");
			klore.add(ChatColor.GRAY + "Has a 5% Chance to Strike Lightning!");
			knightMeta.setLore(klore);
			knightSword.setItemMeta(knightMeta);
			player.getInventory().addItem(knightSword);
			
			//Knights Bow ==========================================================================================
			ItemStack knightBow = new ItemStack(Material.BOW);
			ItemStack knightArrow = new ItemStack(Material.ARROW, 32);
			ItemMeta knightbowMeta = knightBow.getItemMeta();
			knightbowMeta.setDisplayName("Knights Bow");
			knightbowMeta.setUnbreakable(true);
			List<String> kblore = new ArrayList<String>();
			kblore.add(ChatColor.GRAY + "A pure bow!");
			kblore.add("");
			kblore.add(ChatColor.GOLD + "Item Ability:");
			kblore.add(ChatColor.GRAY + "Has a 10% Chance to Heal the Shooter on Hit!");
			knightbowMeta.setLore(kblore);
			knightBow.setItemMeta(knightbowMeta);
			player.getInventory().addItem(knightBow);
			player.getInventory().addItem(knightArrow);
			
			//ULTIMATE KNIGHT ==========================================================================
			ItemStack kUlt = new ItemStack(Material.FIREWORK_STAR);
			ItemMeta oMeta = kUlt.getItemMeta();
			oMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Gu" + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "ard");
			List<String> olore = new ArrayList<String>();
			oMeta.setUnbreakable(true);
			olore.add(ChatColor.GRAY + "A knights true potential");
			olore.add("");
			olore.add(ChatColor.GOLD + "Ultimate Ability:");
			olore.add(ChatColor.GRAY + "Guard: Adds 3 levels of protection for 10 seconds");
			oMeta.setLore(olore);
			kUlt.setItemMeta(oMeta);
			player.getInventory().addItem(kUlt);
			player.getInventory().setHeldItemSlot(2);
			
			
			
			}
			
			//Valkyrie =========================================================================================================
			if(event.getSlot() == 9) {
				player.getInventory().clear();
				
				ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
				LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
				meta.setUnbreakable(true);
				meta.setColor(Color.FUCHSIA);
				chestplate.setItemMeta(meta);
				player.getEquipment().setChestplate(chestplate);
				
				ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
				LeatherArmorMeta lmeta = (LeatherArmorMeta) leggings.getItemMeta();
				lmeta.setColor(Color.FUCHSIA);
				lmeta.setUnbreakable(true);
				leggings.setItemMeta(lmeta);
				player.getEquipment().setLeggings(leggings);
				
				ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
				LeatherArmorMeta bmeta = (LeatherArmorMeta) boots.getItemMeta();
				bmeta.setColor(Color.FUCHSIA);
				bmeta.setUnbreakable(true);
				boots.setItemMeta(bmeta);
				player.getEquipment().setBoots(boots);
				
				
				//Valkyrie Weapons ===========================================================================================
				//Valkyries Axe ===============================================================================================
				ItemStack valkyrieAxe = new ItemStack(Material.GOLDEN_AXE);
				ItemMeta valkyrieMeta = valkyrieAxe.getItemMeta();
				AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage",
						5.0, Operation.ADD_NUMBER, EquipmentSlot.HAND);
				valkyrieMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
				AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed",  -3.00, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
				valkyrieMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier2);
				valkyrieMeta.addEnchant(Enchantment.DIG_SPEED, 5, true);
				valkyrieMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Valkyries Axe");
				valkyrieMeta.setUnbreakable(true);
				List<String> vlore = new ArrayList<String>();
				vlore.add(ChatColor.GRAY + "An otherwordly axe!");
				vlore.add("");
				vlore.add(ChatColor.GOLD + "Item Ability:");
				vlore.add(ChatColor.GRAY + "It's legit just an axe.");
				valkyrieMeta.setLore(vlore);
				valkyrieAxe.setItemMeta(valkyrieMeta);
				player.getInventory().addItem(valkyrieAxe);
				
				//Musket ============================================================================================
				ItemStack valkyrieMusket = new ItemStack(Material.CROSSBOW);
				ItemStack vArrow = new ItemStack(Material.ARROW, 16);
				vArrow.getItemMeta().setDisplayName("Bullet");
				vArrow.setItemMeta(vArrow.getItemMeta());
				ItemMeta vMeta = valkyrieMusket.getItemMeta();
				vMeta.addEnchant(Enchantment.PIERCING, 3, true);
				vMeta.addEnchant(Enchantment.QUICK_CHARGE, 1, true);
				vMeta.setDisplayName("Valkyries Musket");
				vMeta.setUnbreakable(true);
				List<String> v2lore = new ArrayList<String>();
				v2lore.add(ChatColor.GRAY + "I guess valkyries have guns now.");
				v2lore.add("");
				v2lore.add(ChatColor.GOLD + "Item Ability:");
				v2lore.add(ChatColor.GRAY + "Just hit right click my dude.");
				vMeta.setLore(v2lore);
				valkyrieMusket.setItemMeta(vMeta);
				player.getInventory().addItem(valkyrieMusket);
				player.getInventory().addItem(vArrow);
				
				
				//ULTIMATE VALKYRIE ===================================================================================
				ItemStack vUlt = new ItemStack(Material.FIREWORK_STAR);
				ItemMeta oMeta = vUlt.getItemMeta();
				oMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Cala" + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "mity");
				List<String> olore = new ArrayList<String>();
				oMeta.setUnbreakable(true);
				olore.add(ChatColor.GRAY + "That is a mad boy");
				olore.add("");
				olore.add(ChatColor.GOLD + "Ultimate Ability:");
				olore.add(ChatColor.GRAY + "Calamity: Explodes the ground and gives the player speed 3");
				oMeta.setLore(olore);
				vUlt.setItemMeta(oMeta);
				player.getInventory().addItem(vUlt);
				player.getInventory().setHeldItemSlot(2);
				
				
				
				
			}
			//Zeus Class ====================================================================================================
			
			if(event.getSlot() == 11) {
				player.getInventory().clear();
				
				ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
				LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
				meta.setUnbreakable(true);
				meta.setColor(Color.ORANGE);
				chestplate.setItemMeta(meta);
				player.getEquipment().setChestplate(chestplate);
				
				ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
				LeatherArmorMeta lmeta = (LeatherArmorMeta) leggings.getItemMeta();
				lmeta.setColor(Color.ORANGE);
				lmeta.setUnbreakable(true);
				leggings.setItemMeta(lmeta);
				player.getEquipment().setLeggings(leggings);
				
				ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
				LeatherArmorMeta bmeta = (LeatherArmorMeta) boots.getItemMeta();
				bmeta.setColor(Color.ORANGE);
				bmeta.setUnbreakable(true);
				boots.setItemMeta(bmeta);
				player.getEquipment().setBoots(boots);
				
				
				//Zeus Weapons  =====================================================================================
				//Lightning Bolt  =====================================================================================
				ItemStack lBolt = new ItemStack(Material.END_ROD);
				ItemMeta boltMeta = lBolt.getItemMeta();
				boltMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
				boltMeta.setDisplayName(ChatColor.YELLOW + "Lightning Bolt");
				boltMeta.setUnbreakable(true);
				List<String> vlore = new ArrayList<String>();
				vlore.add(ChatColor.GRAY + "A powerful weapon forged in the depths of hell!");
				vlore.add("");
				vlore.add(ChatColor.GOLD + "Item Ability:");
				vlore.add(ChatColor.GRAY + "Thunder Strike: Summons lightning on hit!");
				boltMeta.setLore(vlore);
				lBolt.setItemMeta(boltMeta);
				player.getInventory().addItem(lBolt);
				
				
				//Icarus Feather =====================================================================================
				ItemStack iFeather = new ItemStack(Material.FEATHER);
				ItemMeta iMeta = iFeather.getItemMeta();
				iMeta.addEnchant(Enchantment.KNOCKBACK, 3, true);
				iMeta.setDisplayName(ChatColor.GOLD + "Icarus Feather");
				iMeta.setUnbreakable(true);
				List<String> flore = new ArrayList<String>();
				flore.add(ChatColor.GRAY + "A feather torn from the wings of Icarus");
				flore.add("");
				flore.add(ChatColor.GOLD + "Item Ability:");
				flore.add(ChatColor.GRAY + "Fly: Teleports the player up 10 blocks and gives them slow falling.");
				iMeta.setLore(flore);
				iFeather.setItemMeta(iMeta);
				player.getInventory().addItem(iFeather);
				
				//ULTIMATE HOLY THUNDER ==============================================================================
				ItemStack zUlt = new ItemStack(Material.FIREWORK_STAR);
				ItemMeta oMeta = zUlt.getItemMeta();
				oMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Holy" + ChatColor.GOLD + "" + ChatColor.BOLD + "Thunder");
				List<String> olore = new ArrayList<String>();
				oMeta.setUnbreakable(true);
				olore.add(ChatColor.GRAY + "Thou has been smitten!");
				olore.add("");
				olore.add(ChatColor.GOLD + "Ultimate Ability:");
				olore.add(ChatColor.GRAY + "Holy Thunder: Smites all players!");
				oMeta.setLore(olore);
				zUlt.setItemMeta(oMeta);
				player.getInventory().addItem(zUlt);
				player.getInventory().setHeldItemSlot(2);
				
			}
			
			//Fisherman Class ============================================================================================
			if(event.getSlot() == 13) {
				player.getInventory().clear();
				
				ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
				LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
				meta.setUnbreakable(true);
				meta.setColor(Color.BLUE);
				chestplate.setItemMeta(meta);
				player.getEquipment().setChestplate(chestplate);
				
				ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
				LeatherArmorMeta lmeta = (LeatherArmorMeta) leggings.getItemMeta();
				lmeta.setColor(Color.BLUE);
				lmeta.setUnbreakable(true);
				leggings.setItemMeta(lmeta);
				player.getEquipment().setLeggings(leggings);
				
				ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
				LeatherArmorMeta bmeta = (LeatherArmorMeta) boots.getItemMeta();
				bmeta.setColor(Color.BLUE);
				bmeta.setUnbreakable(true);
				boots.setItemMeta(bmeta);
				player.getEquipment().setBoots(boots);
				
				//Fisherman Weapons =====================================================================================
				// Gills ===============================================================================================
				ItemStack gills = new ItemStack(Material.TRIDENT);
				ItemMeta gMeta = gills.getItemMeta();
				AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage",
						3.0, Operation.ADD_NUMBER, EquipmentSlot.HAND);
				gMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
				gMeta.addEnchant(Enchantment.RIPTIDE, 2, true);
				gMeta.setDisplayName(ChatColor.BLUE + "Gills");
				gMeta.setUnbreakable(true);
				List<String> flore = new ArrayList<String>();
				flore.add(ChatColor.GRAY + "A torn gill from a legendary fish");
				flore.add("");
				flore.add(ChatColor.GOLD + "Item Ability:");
				flore.add(ChatColor.GRAY + "Storm: Makes it rain for 5 seconds");
				gMeta.setLore(flore);
				gills.setItemMeta(gMeta);
				player.getInventory().addItem(gills);
				
				//Water Gun =========================================================================================
				ItemStack wGun = new ItemStack(Material.BOW);
				ItemStack wArrow = new ItemStack(Material.ARROW, 5);
				ItemMeta wMeta = wGun.getItemMeta();
				wMeta.setDisplayName(ChatColor.DARK_BLUE + "Water Gun");
				wMeta.setUnbreakable(true);
				List<String> wlore = new ArrayList<String>();
				wlore.add(ChatColor.GRAY + "A gun that shoots a pufferfish...?");
				wlore.add("");
				wlore.add(ChatColor.GOLD + "Item Ability:");
				wlore.add(ChatColor.GRAY + "Pufferfish: Summons in a pufferfish");
				wMeta.setLore(wlore);
				wGun.setItemMeta(wMeta);
				player.getInventory().addItem(wGun);
				player.getInventory().addItem(wArrow);
				
				//ULTIMATE OCEANIC WRATH ==========================================================================
				ItemStack fUlt = new ItemStack(Material.FIREWORK_STAR);
				ItemMeta oMeta = fUlt.getItemMeta();
				oMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "OCEANIC" + ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "WRATH");
				List<String> olore = new ArrayList<String>();
				oMeta.setUnbreakable(true);
				olore.add(ChatColor.GRAY + "Drag all your enemies under the sea!");
				olore.add("");
				olore.add(ChatColor.GOLD + "Ultimate Ability:");
				olore.add(ChatColor.GRAY + "Oceanic Wrath: Drowns and blinds all players!");
				oMeta.setLore(olore);
				fUlt.setItemMeta(oMeta);
				player.getInventory().addItem(fUlt);
				player.getInventory().setHeldItemSlot(2);
				
			}
			
			//Cryomancer
		if(event.getSlot() == 15) {
			player.getInventory().clear();
			
			ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
			meta.setUnbreakable(true);
			meta.setColor(Color.AQUA);
			chestplate.setItemMeta(meta);
			player.getEquipment().setChestplate(chestplate);
			
			ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta lmeta = (LeatherArmorMeta) leggings.getItemMeta();
			lmeta.setColor(Color.AQUA);
			lmeta.setUnbreakable(true);
			leggings.setItemMeta(lmeta);
			player.getEquipment().setLeggings(leggings);
			
			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta bmeta = (LeatherArmorMeta) boots.getItemMeta();
			bmeta.setColor(Color.AQUA);
			bmeta.setUnbreakable(true);
			boots.setItemMeta(bmeta);
			player.getEquipment().setBoots(boots);
			
			//Cryomancer Weapons
			//Frigid Staff
			ItemStack fStaff = new ItemStack(Material.STICK);
			ItemMeta fMeta = fStaff.getItemMeta();
			fMeta.setDisplayName(ChatColor.AQUA + "Frigid Staff");
			fMeta.addEnchant(Enchantment.DURABILITY, 1, false);
			fMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			fMeta.setUnbreakable(true);
			List<String> wlore = new ArrayList<String>();
			wlore.add(ChatColor.GRAY + "A magical frozen stick!");
			wlore.add("");
			wlore.add(ChatColor.GOLD + "Item Ability:");
			wlore.add(ChatColor.GRAY + "Ice Bomb: Shoots a block of ice that explodes on contact.");
			fMeta.setLore(wlore);
			fStaff.setItemMeta(fMeta);
			player.getInventory().addItem(fStaff);
			
			//Frozen Dagger
			ItemStack fSting = new ItemStack(Material.IRON_SWORD);
			ItemMeta fsMeta = fSting.getItemMeta();
			AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage",
					3.0, Operation.ADD_NUMBER, EquipmentSlot.HAND);
			fsMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
			fsMeta.setDisplayName(ChatColor.DARK_AQUA + "Frozen Dagger");
			fsMeta.setUnbreakable(true);
			List<String> wslore = new ArrayList<String>();
			wslore.add(ChatColor.GRAY + "An icy sting! OOF!");
			wslore.add("");
			wslore.add(ChatColor.GOLD + "Item Ability:");
			wslore.add(ChatColor.GRAY + "Frostbite: Has a 25% chance to give the target frostbite");
			fsMeta.setLore(wslore);
			fSting.setItemMeta(fsMeta);
			player.getInventory().addItem(fSting);
			
			//ULTIMATE GLACIER FREEZE
			ItemStack fUlt = new ItemStack(Material.FIREWORK_STAR);
			ItemMeta oMeta = fUlt.getItemMeta();
			oMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "GLACIER" + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "FREEZE");
			List<String> olore = new ArrayList<String>();
			oMeta.setUnbreakable(true);
			olore.add(ChatColor.GRAY + "Covers the body with powerful ice.");
			olore.add("");
			olore.add(ChatColor.GOLD + "Ultimate Ability:");
			olore.add(ChatColor.GRAY + "Glacier Freeze: Gives Resistance II for 20 seconds.");
			oMeta.setLore(olore);
			fUlt.setItemMeta(oMeta);
			player.getInventory().addItem(fUlt);
			player.getInventory().setHeldItemSlot(2);
		}
		
		if(event.getSlot() == 17) {
			player.getInventory().clear();
			
			
			
			ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
			meta.setUnbreakable(true);
			meta.setColor(Color.GREEN);
			chestplate.setItemMeta(meta);
			player.getEquipment().setChestplate(chestplate);
			
			ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta lmeta = (LeatherArmorMeta) leggings.getItemMeta();
			lmeta.setColor(Color.GREEN);
			lmeta.setUnbreakable(true);
			leggings.setItemMeta(lmeta);
			player.getEquipment().setLeggings(leggings);
			
			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta bmeta = (LeatherArmorMeta) boots.getItemMeta();
			bmeta.setColor(Color.GREEN);
			bmeta.setUnbreakable(true);
			boots.setItemMeta(bmeta);
			player.getEquipment().setBoots(boots);
			
			
			
			//Turtle Weapons
			// Turtle Bite
			ItemStack tBite = new ItemStack(Material.STONE_SWORD);
			ItemMeta gMeta = tBite.getItemMeta();
			gMeta.setDisplayName(ChatColor.GREEN + "Turtle Bite");
			gMeta.addEnchant(Enchantment.KNOCKBACK, 1, false);
			gMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			gMeta.setUnbreakable(true);
			List<String> glore = new ArrayList<String>();
			glore.add(ChatColor.GRAY + "Ouch!");
			glore.add("");
			glore.add(ChatColor.GOLD + "Item Ability:");
			glore.add(ChatColor.GRAY + "Has a 5% chance to give an enemy slowness.");
			gMeta.setLore(glore);
			tBite.setItemMeta(gMeta);
			player.getInventory().addItem(tBite);
			
			//Helmet of Fortitude
			ItemStack hoF = new ItemStack(Material.TURTLE_HELMET);
			ItemMeta wzMeta = hoF.getItemMeta();
			AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.movementSpeed",  -0.02, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
			wzMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier);
			wzMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
			wzMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
			wzMeta.addEnchant(Enchantment.PROTECTION_FIRE, 1, true);
			wzMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 1, true);
			wzMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			wzMeta.setDisplayName(ChatColor.DARK_GREEN + "Helmet of Fortitude");
			wzMeta.setUnbreakable(true);
			List<String> wslore = new ArrayList<String>();
			wslore.add(ChatColor.GRAY + "That's a hard hat.");
			wslore.add("");
			wslore.add(ChatColor.GOLD + "Item Ability:");
			wslore.add(ChatColor.GRAY + "A super tanky helmet that gives slowness.");
			wzMeta.setLore(wslore);
			hoF.setItemMeta(wzMeta);
			player.getInventory().addItem(hoF);
			
			//ULTIMATE Shell Break
			ItemStack sbUlt = new ItemStack(Material.FIREWORK_STAR);
			ItemMeta oMeta = sbUlt.getItemMeta();
			oMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "SHELL" + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "BREAK");
			List<String> olore = new ArrayList<String>();
			oMeta.setUnbreakable(true);
			olore.add(ChatColor.GRAY + "You're the fastest turtle alive!");
			olore.add("");
			olore.add(ChatColor.GOLD + "Ultimate Ability:");
			olore.add(ChatColor.GRAY + "Shell Break: Gives all players slowness");
			olore.add(ChatColor.GRAY + "while you get speed!");
			oMeta.setLore(olore);
			sbUlt.setItemMeta(oMeta);
			player.getInventory().addItem(sbUlt);
			player.getInventory().setHeldItemSlot(2);
			
		}
		
		if(event.getSlot() == 19) {
			player.getInventory().clear();
			
			ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
			meta.setUnbreakable(true);
			meta.setColor(Color.WHITE);
			chestplate.setItemMeta(meta);
			player.getEquipment().setChestplate(chestplate);
			
			ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta lmeta = (LeatherArmorMeta) leggings.getItemMeta();
			lmeta.setColor(Color.WHITE);
			lmeta.setUnbreakable(true);
			leggings.setItemMeta(lmeta);
			player.getEquipment().setLeggings(leggings);
			
			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta bmeta = (LeatherArmorMeta) boots.getItemMeta();
			bmeta.setColor(Color.WHITE);
			bmeta.setUnbreakable(true);
			boots.setItemMeta(bmeta);
			player.getEquipment().setBoots(boots);
			
			//Mage Staff
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
			player.getInventory().addItem(mStaff);
			
			//ULTIMATE ELEMENTAL BLAST
			ItemStack sbUlt = new ItemStack(Material.FIREWORK_STAR);
			ItemMeta oMeta = sbUlt.getItemMeta();
			oMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "ELEMENTAL" + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "BLAST");
			List<String> olore = new ArrayList<String>();
			oMeta.setUnbreakable(true);
			olore.add(ChatColor.GRAY + "Combine all the elements into 1 terrifying blast!");
			olore.add("");
			olore.add(ChatColor.GOLD + "Ultimate Ability:");
			olore.add(ChatColor.GRAY + "Elemental Blast: A elemental blast");
			oMeta.setLore(olore);
			sbUlt.setItemMeta(oMeta);
			player.getInventory().addItem(sbUlt);
			player.getInventory().setHeldItemSlot(2);
			
		}
			
		
		
		if(event.getSlot() == 26) {
			//Quit
			player.closeInventory();
			
		}
		return;
	
	}
	
	
	
	
	//Inside the GUI Stuff
	public void classGUI() {
		inv = Bukkit.createInventory(null, 27, "Class Selector");
		
		ItemStack pyroItem = new ItemStack(Material.RED_CONCRETE);
		ItemMeta pyroMeta = pyroItem.getItemMeta();
		
		ItemStack lightItem = new ItemStack(Material.GOLD_BLOCK);
		ItemMeta lightMeta = lightItem.getItemMeta();
		
		ItemStack closeItem = new ItemStack(Material.BARRIER);
		ItemMeta closeMeta = closeItem.getItemMeta();
		
		ItemStack shadowItem = new ItemStack(Material.BLACK_CONCRETE);
		ItemMeta shadowMeta = shadowItem.getItemMeta();
		
		ItemStack knightItem = new ItemStack(Material.IRON_BLOCK);
		ItemMeta knightMeta = knightItem.getItemMeta();
		
		
		ItemStack valkyrieItem = new ItemStack(Material.IRON_AXE);
		ItemMeta valkyrieMeta = valkyrieItem.getItemMeta();
		
		ItemStack zeusItem = new ItemStack(Material.ORANGE_DYE);
		ItemMeta zeusMeta = zeusItem.getItemMeta();
		
		ItemStack fishItem = new ItemStack(Material.FISHING_ROD);
		ItemMeta fishMeta = fishItem.getItemMeta();
		
		ItemStack iceItem = new ItemStack(Material.ICE);
		ItemMeta iceMeta = fishItem.getItemMeta();
		
		ItemStack turtleItem = new ItemStack(Material.TURTLE_EGG);
		ItemMeta turtleMeta = turtleItem.getItemMeta();
		
		ItemStack mageItem = new ItemStack(Material.ENDER_EYE);
		ItemMeta mageMeta = mageItem.getItemMeta();

		
		
		//Pyro Class
		pyroMeta.setDisplayName(ChatColor.GOLD + "Pyro");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "A well rounded class that deals decent damage");
		pyroMeta.setLore(lore);
		pyroItem.setItemMeta(pyroMeta);
		inv.setItem(1, pyroItem);
		
		//Light Class
		lightMeta.setDisplayName(ChatColor.YELLOW + "Light");
		List<String> lore2 = new ArrayList<String>();
		lore2.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "They call me an angel, I call them deceased.");
		lightMeta.setLore(lore2);
		lightItem.setItemMeta(lightMeta);
		inv.setItem(3, lightItem);
		
		//Close Button
		closeMeta.setDisplayName(ChatColor.RED + "Close");
		closeItem.setItemMeta(closeMeta);
		inv.setItem(26, closeItem);
		
		//Shadow Class
		shadowMeta.setDisplayName(ChatColor.DARK_GRAY + "Shadow");
		List<String> lore3 = new ArrayList<String>();
		lore3.add(ChatColor.GRAY + "You will die, very soon");
		shadowMeta.setLore(lore3);
		shadowItem.setItemMeta(shadowMeta);
		inv.setItem(5, shadowItem);
		
		//Knight Class
		knightMeta.setDisplayName(ChatColor.WHITE + "Knight");
		List<String> lore4 = new ArrayList<String>();
		lore4.add(ChatColor.GRAY + "A sharp sword, a noble figure.");
		knightMeta.setLore(lore4);
		knightItem.setItemMeta(knightMeta);
		inv.setItem(7, knightItem);
		
		//Valkyrie Class
		valkyrieMeta.setDisplayName(ChatColor.DARK_AQUA + "Valkyrie");
		List<String> lore5 = new ArrayList<String>();
		lore5.add(ChatColor.GRAY + "Axe go brrr!");
		valkyrieMeta.setLore(lore5);
		valkyrieItem.setItemMeta(valkyrieMeta);
		inv.setItem(9, valkyrieItem);
		
		//Zeus Class
		zeusMeta.setDisplayName(ChatColor.GOLD + "Zeus");
		List<String> lore6 = new ArrayList<String>();
		lore6.add(ChatColor.GRAY + "God of the sky, lightning, and thunder!");
		zeusMeta.setLore(lore6);
		zeusItem.setItemMeta(zeusMeta);
		inv.setItem(11, zeusItem);
		
		//Fisherman Class
		fishMeta.setDisplayName(ChatColor.BLUE + "Fisherman");
		List<String> lore7 = new ArrayList<String>();
		lore7.add(ChatColor.GRAY + "Just Keep Fishing! Just Keep Fishing!");
		fishMeta.setLore(lore7);
		fishItem.setItemMeta(fishMeta);
		inv.setItem(13, fishItem);

		//Cryomancer Class
		iceMeta.setDisplayName(ChatColor.AQUA + "Cryomancer");
		List<String> lore8 = new ArrayList<String>();
		lore8.add(ChatColor.GRAY + "Freezing…… Cold……");
		iceMeta.setLore(lore8);
		iceItem.setItemMeta(iceMeta);
		inv.setItem(15, iceItem);
		
		//Turtle Class
		turtleMeta.setDisplayName(ChatColor.GREEN + "Turtle");
		List<String> lore9 = new ArrayList<String>();
		lore9.add(ChatColor.GRAY + "Faster then you might think!");
		turtleMeta.setLore(lore9);
		turtleItem.setItemMeta(turtleMeta);
		inv.setItem(17, turtleItem);
		
		//Mage Class
		mageMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Mage");
		List<String> lore10 = new ArrayList<String>();
		lore10.add(ChatColor.GRAY + "Now with 100% more magic!");
		mageMeta.setLore(lore10);
		mageItem.setItemMeta(mageMeta);
		inv.setItem(19, mageItem);
		
		
				
		
	}
	
	
	
	

}
