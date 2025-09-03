package com.min01.villageandvillagers.compat.jer;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Supplier;
import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.villager.AbstractCombatVillager;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import jeresources.compatibility.CompatBase;
import jeresources.entry.AbstractVillagerEntry;
import jeresources.util.VillagersHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;

public class SpecialVillagerEntry extends AbstractVillagerEntry<AbstractCombatVillager>
{
    private final VillagerProfession profession;
    private final Supplier<EntityType<? extends AbstractCombatVillager>> supplier;

    public SpecialVillagerEntry(Supplier<EntityType<? extends AbstractCombatVillager>> supplier, VillagerProfession profession, Int2ObjectMap<VillagerTrades.ItemListing[]> itemListings)
    {
        super(itemListings);
        this.supplier = supplier;
        this.profession = profession;
    }

    @Override
    public String getName() 
    {
        return this.profession.toString();
    }

    @Override
    public String getDisplayName() 
    {
        return "entity.minecraft.villager." + VillageandVillagers.MODID + "." + this.profession.toString();
    }

    public VillagerProfession getProfession()
    {
        return this.profession;
    }

    @Override
    public AbstractCombatVillager getVillagerEntity()
    {
        if(this.entity == null) 
        {
            this.entity = this.supplier.get().create(CompatBase.getLevel());
        }
        return this.entity;
    }

    @Override
    public List<ItemStack> getPois() 
    {
        return VillagersHelper.getPoiBlocks(this.profession.heldJobSite()).stream().map(blockstate -> new ItemStack(blockstate.getBlock())).collect(Collectors.toList());
    }

    @Override
    public boolean hasPois() 
    {
        return !VillagersHelper.getPoiBlocks(this.profession.heldJobSite()).isEmpty();
    }

    @Override
    public boolean hasLevels()
    {
        return true;
    }
}