package me.amc.entities;

import java.text.DecimalFormat;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class CustomEntity implements Listener {

	private String name;
	private ItemStack helmet;
	private ItemStack chestplate;
	private ItemStack leggings;
	private ItemStack boots;
	private ItemStack handItem;
	private double maxHealth;
	private boolean showHealth;
	private EntityType type;
	private String fullName;
	
	private DecimalFormat df = new DecimalFormat("0.0"); 

	public CustomEntity(EntityType type, String name, double maxHealth, boolean showHealth) {

		this.type = type;
		this.name = name;
		this.maxHealth = maxHealth;
		this.showHealth = showHealth;

		this.fullName = name;
	}

	public ItemStack getHelmet() {
		return helmet;
	}

	public void setHelmet(ItemStack helmet) {
		this.helmet = helmet;
	}

	public ItemStack getChestplate() {
		return chestplate;
	}

	public void setChestplate(ItemStack chestplate) {
		this.chestplate = chestplate;
	}

	public ItemStack getLeggings() {
		return leggings;
	}

	public void setLeggings(ItemStack leggings) {
		this.leggings = leggings;
	}

	public ItemStack getBoots() {
		return boots;
	}

	public void setBoots(ItemStack boots) {
		this.boots = boots;
	}

	public ItemStack getHandItem() {
		return handItem;
	}

	public void setHandItem(ItemStack handItem) {
		this.handItem = handItem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(double maxHealth) {
		this.maxHealth = maxHealth;
	}

	public boolean isShowHealth() {
		return showHealth;
	}

	public void setShowHealth(boolean showHealth) {
		this.showHealth = showHealth;
	}

	public EntityType getType() {
		return type;
	}

	public void setType(EntityType type) {
		this.type = type;
	}

	public void spawnEntityAt(World world, Location location) {
		Entity entity = (Entity) world.spawnEntity(location.add(0, 2, 0), type);

		
		
		fullName = name + ChatColor.RED + " HP: " + df.format(maxHealth);

		entity.setCustomName(fullName);
		entity.setCustomNameVisible(true);

		if (entity instanceof LivingEntity) {
			LivingEntity lEntity = (LivingEntity) entity;
			
			lEntity.setMaxHealth(maxHealth);
			lEntity.setHealth(maxHealth);

			if (helmet != null)
				lEntity.getEquipment().setHelmet(helmet);
			if (chestplate != null)
				lEntity.getEquipment().setChestplate(chestplate);
			if (leggings != null)
				lEntity.getEquipment().setLeggings(leggings);
			if (boots != null)
				lEntity.getEquipment().setBoots(boots);
			/*
			 * If you are using an older spigot/bukkit version change
			 * .setItemInMainHand(...) with .setItemInHand(...)
			 */
			if (handItem != null)
				lEntity.getEquipment().setItemInMainHand(handItem);

		} else {
			System.out.println("Cannot put items on " + entity + " because it is not a LivingEntity");
		}

	}

	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		Entity entity = event.getEntity();

		if (entity.getType() == type) {
			if(entity.getCustomName() == null)  return;
			
			if (entity.getCustomName().startsWith(name)) {
				
				if(entity instanceof LivingEntity) {
					LivingEntity lEntity = (LivingEntity) entity;
					
					
					fullName = name + ChatColor.RED + " HP: " + df.format(lEntity.getHealth());
					lEntity.setCustomName(fullName);
				}
			}
		}
	}

	

}
