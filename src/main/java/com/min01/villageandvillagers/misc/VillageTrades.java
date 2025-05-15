package com.min01.villageandvillagers.misc;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.min01.villageandvillagers.item.VillageItems;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

public class VillageTrades
{
	public static final Map<VillagerProfession, Int2ObjectMap<VillagerTrades.ItemListing[]>> TRADES = Util.make(Maps.newHashMap(), (p_35633_) -> 
	{
		p_35633_.put(VillagerProfession.FARMER, toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]
				{
					new VillagerTrades.EmeraldForItems(Items.WHEAT, 20, 16, 2), 
					new VillagerTrades.EmeraldForItems(Items.POTATO, 26, 16, 2), 
					new VillagerTrades.EmeraldForItems(Items.CARROT, 22, 16, 2), 
					new VillagerTrades.EmeraldForItems(Items.BEETROOT, 15, 16, 2), 
					new VillagerTrades.ItemsForEmeralds(Items.BREAD, 1, 6, 16, 1)
				}, 2, new VillagerTrades.ItemListing[]
				{
					new VillagerTrades.EmeraldForItems(Blocks.PUMPKIN, 6, 12, 10),
					new VillagerTrades.ItemsForEmeralds(Items.PUMPKIN_PIE, 1, 4, 5), 
					new VillagerTrades.ItemsForEmeralds(Items.APPLE, 1, 4, 16, 5)
				}, 3, new VillagerTrades.ItemListing[]
				{
					new VillagerTrades.ItemsForEmeralds(Items.COOKIE, 3, 18, 10),
					new VillagerTrades.EmeraldForItems(Blocks.MELON, 4, 12, 20)
				}, 4, new VillagerTrades.ItemListing[]
				{
					new VillagerTrades.ItemsForEmeralds(Blocks.CAKE, 1, 1, 12, 15)
				}, 5, new VillagerTrades.ItemListing[]
				{
					new VillagerTrades.ItemsForEmeralds(Items.GOLDEN_CARROT, 3, 3, 30),
					new VillagerTrades.ItemsForEmeralds(Items.GLISTERING_MELON_SLICE, 4, 3, 30)
				})));
		p_35633_.put(VillageProfessions.TIME_KEEPER.get(), toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]
				{
					new VillagerTrades.ItemsForEmeralds(VillageItems.GEAR.get(), 8, 3, 100, 1),
				}, 2, new VillagerTrades.ItemListing[]
				{
					new EmeraldForItems(VillageItems.TIME_JELLY.get(), 1, 10, 2, 15),
					new EmeraldForItems(VillageItems.TIME_CRYSTAL.get(), 1, 10, 10, 40)
				}, 3, new VillagerTrades.ItemListing[]
				{
					new VillagerTrades.ItemsForEmeralds(VillageItems.MONOCLE.get(), 10, 1, 1, 5)
				}
				, 4, new VillagerTrades.ItemListing[]
				{
					new VillagerTrades.ItemsForEmeralds(VillageItems.POCKET_WATCH.get(), 40, 1, 1, 10)
				}
				, 5, new VillagerTrades.ItemListing[]
				{
					new VillagerTrades.ItemsForEmeralds(VillageItems.TIME_JELLY.get(), 15, 1, 20, 2),
					new VillagerTrades.ItemsForEmeralds(VillageItems.TIME_CRYSTAL.get(), 40, 1, 10, 10)
				})));
	});
	
	public static Int2ObjectMap<VillagerTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> p_35631_)
	{
		return new Int2ObjectOpenHashMap<>(p_35631_);
	}
	
	public static class EmeraldForItems implements VillagerTrades.ItemListing 
	{
		private final Item item;
		private final int cost;
		private final int maxUses;
		private final int villagerXp;
		private final int emeraldCount;
		private final float priceMultiplier;

		public EmeraldForItems(ItemLike p_35657_, int p_35658_, int p_35659_, int p_35660_, int emeraldCount) 
		{
			this.item = p_35657_.asItem();
			this.cost = p_35658_;
			this.maxUses = p_35659_;
			this.villagerXp = p_35660_;
			this.emeraldCount = emeraldCount;
			this.priceMultiplier = 0.05F;
		}

		@Override
		public MerchantOffer getOffer(Entity p_219682_, RandomSource p_219683_)
		{
			ItemStack itemstack = new ItemStack(this.item, this.cost);
			return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD, this.emeraldCount), this.maxUses, this.villagerXp, this.priceMultiplier);
		}
	}
}
