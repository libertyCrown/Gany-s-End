package ganymedes01.ganysend.core.utils;

import ganymedes01.ganysend.items.ModItems;
import ganymedes01.ganysend.lib.SkullTypes;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Gany's End
 * 
 * @author ganymedes01
 * 
 */

public class HeadsHelper {

	public static boolean useTwilightForrestMobs = false;
	public static boolean useThermalExpansionMobs = false;

	public static final ItemStack getHeadfromEntity(EntityLivingBase target) {
		if (target.isChild())
			return null;

		if (useTwilightForrestMobs) {
			ItemStack head = getTFMobHead(EntityList.getEntityString(target));
			if (head != null)
				return head;
		}

		if (useThermalExpansionMobs) {
			ItemStack head = getTEMobHead(EntityList.getEntityString(target));
			if (head != null)
				return head;
		}

		if (target instanceof EntityMob) {
			if (target instanceof EntityCreeper)
				return new ItemStack(Item.skull.itemID, 1, 4);
			else if (target instanceof EntitySkeleton) {
				int type = ((EntitySkeleton) target).getSkeletonType();
				if (type == 1) // Wither
					return new ItemStack(Item.skull, 1, 1);
				else if (type == 0) // Normal
					return new ItemStack(Item.skull, 1, 0);
			} else if (target instanceof EntityZombie) {
				if (target instanceof EntityPigZombie)
					return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.pigman.ordinal());
				else if (((EntityZombie) target).isVillager())
					return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.zombieVillager.ordinal());
				else
					return new ItemStack(Item.skull, 1, 2);
			} else if (target instanceof EntityEnderman)
				return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.enderman.ordinal());
			else if (target instanceof EntityBlaze)
				return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.blaze.ordinal());
			else if (target instanceof EntitySpider) {
				if (target instanceof EntityCaveSpider)
					return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.caveSpider.ordinal());
				else
					return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.spider.ordinal());
			} else if (target instanceof EntityWitch)
				return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.witch.ordinal());
			else if (target instanceof EntityWither)
				return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.wither.ordinal());
		} else if (target instanceof EntityPlayer) {
			ItemStack stack = new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.player.ordinal());
			stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setString("SkullOwner", ((EntityPlayer) target).username);
			return stack;
		} else if (target instanceof EntityAnimal) {
			if (target instanceof EntityPig)
				return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.pig.ordinal());
			else if (target instanceof EntityCow) {
				if (target instanceof EntityMooshroom)
					return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.mooshroom.ordinal());
				else
					return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.cow.ordinal());
			} else if (target instanceof EntitySheep)
				return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.sheep.ordinal());
			else if (target instanceof EntityWolf)
				return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.wolf.ordinal());
			else if (target instanceof EntityChicken)
				return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.chicken.ordinal());
		} else if (target instanceof EntityVillager)
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.villager.ordinal());
		else if (target instanceof EntityIronGolem)
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.ironGolem.ordinal());
		else if (target instanceof EntitySquid)
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.squid.ordinal());
		else if (target instanceof EntityGhast)
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.ghast.ordinal());

		return null;
	}

	private static ItemStack getTFMobHead(String mobName) {
		if (mobName == null)
			return null;
		if (mobName.equals("TwilightForest.Forest Bunny"))
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.bunny.ordinal());
		else if (mobName.equals("TwilightForest.Penguin"))
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.penguin.ordinal());
		else if (mobName.equals("TwilightForest.Bighorn Sheep"))
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.bighorn.ordinal());
		else if (mobName.equals("TwilightForest.Wild Deer"))
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.wildDeer.ordinal());
		else if (mobName.equals("TwilightForest.Wild Boar"))
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.wildBoar.ordinal());
		else if (mobName.equals("TwilightForest.Redcap") || mobName.equals("TwilightForest.Redcap Sapper"))
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.redcap.ordinal());
		else if (mobName.equals("TwilightForest.Skeleton Druid"))
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.druid.ordinal());
		else if (mobName.equals("TwilightForest.Hedge Spider"))
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.hedgeSpider.ordinal());
		else if (mobName.equals("TwilightForest.Twilight Lich"))
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.lich.ordinal());
		else if (mobName.equals("TwilightForest.Mist Wolf"))
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.mistWolf.ordinal());
		else if (mobName.equals("TwilightForest.Mini Ghast"))
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.miniGhast.ordinal());
		else if (mobName.equals("TwilightForest.Tower Ghast"))
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.guardGhast.ordinal());
		else if (mobName.equals("TwilightForest.King Spider"))
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.kingSpider.ordinal());
		else if (mobName.equals("TwilightForest.Kobold"))
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.kobold.ordinal());
		else if (mobName.equals("TwilightForest.Fire Beetle"))
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.fireBeetle.ordinal());
		else if (mobName.equals("TwilightForest.Slime Beetle"))
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.slimeBeetle.ordinal());
		else if (mobName.equals("TwilightForest.Pinch Beetle"))
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.pinchBeetle.ordinal());
		else if (mobName.equals("TwilightForest.Tower Golem"))
			return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.towerGolem.ordinal());
		else
			return null;
	}

	private static ItemStack getTEMobHead(String mobName) {
		if (mobName != null)
			if (mobName.equals("Blizz"))
				return new ItemStack(ModItems.itemNewSkull, 1, SkullTypes.blizz.ordinal());
		return null;
	}
}