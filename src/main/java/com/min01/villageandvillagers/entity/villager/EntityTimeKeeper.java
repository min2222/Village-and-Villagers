package com.min01.villageandvillagers.entity.villager;

import com.min01.villageandvillagers.misc.SpecialVillagerProfessions;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.Level;

public class EntityTimeKeeper extends AbstractCombatVillager
{
	public EntityTimeKeeper(EntityType<? extends Villager> p_35267_, Level p_35268_) 
	{
		super(p_35267_, p_35268_);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
    			.add(Attributes.MAX_HEALTH, 120.0F)
    			.add(Attributes.MOVEMENT_SPEED, 0.45F)
        		.add(Attributes.ATTACK_DAMAGE, 15.0F)
        		.add(Attributes.FOLLOW_RANGE, 64.0F)
        		.add(Attributes.ARMOR, 5.0F)
        		.add(Attributes.ARMOR_TOUGHNESS, 5.0F);
    }
	
	@Override
	protected void registerGoals() 
	{
		super.registerGoals();
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(this.getTarget() != null)
		{
			this.getLookControl().setLookAt(this.getTarget(), 30.0F, 30.0F);
		}
	}
	
	@Override
	public VillagerProfession getProfession()
	{
		return SpecialVillagerProfessions.TIME_KEEPER.get();
	}
}
