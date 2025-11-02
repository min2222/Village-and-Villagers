package com.min01.villageandvillagers.item;

import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import com.min01.villageandvillagers.entity.VillageEntities;
import com.min01.villageandvillagers.entity.misc.EntityRushingCow;
import com.min01.villageandvillagers.misc.VillageArmPoses;
import com.min01.villageandvillagers.util.VillageUtil;

import net.minecraft.client.model.HumanoidModel.ArmPose;
import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class CowbellItem extends Item
{
	public CowbellItem() 
	{
		super(new Item.Properties().durability(500));
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_)
	{
		ItemStack stack = p_41433_.getItemInHand(p_41434_);
		stack.hurtAndBreak(1, p_41433_, t -> 
		{
			t.broadcastBreakEvent(p_41434_);
		});
		p_41433_.getCooldowns().addCooldown(this, 200);
		p_41433_.playSound(SoundEvents.BELL_BLOCK);
		
		for(int i = 0; i < 10; i++)
		{
			int yRot = p_41432_.random.nextInt(35);
			Vec3 lookPos = VillageUtil.getLookPos(new Vec2(0.0F, p_41433_.getYRot() + yRot), p_41433_.position(), 0, 0, -40);
			Vec3 spreadPos = VillageUtil.getSpreadPosition(p_41432_, lookPos, 4);
			Vec3 groundPos = VillageUtil.getGroundPos(p_41432_, spreadPos.x, p_41433_.getY() + 10, spreadPos.z).add(0.0F, 1.0F, 0.0F);
			Vec3 wantedPos = VillageUtil.getLookPos(new Vec2(0.0F, p_41433_.getYRot() + yRot), p_41433_.position(), 0, 0, 20);
			EntityRushingCow cow = new EntityRushingCow(VillageEntities.RUSHING_COW.get(), p_41432_);
			cow.setOwner(p_41433_);
			cow.setPos(groundPos);
			cow.setWantedPos(VillageUtil.getGroundPos(p_41432_, wantedPos.x, p_41433_.getY() + 10, wantedPos.z).add(0.0F, 1.0F, 0.0F));
			cow.lookAt(Anchor.FEET, cow.getWantedPos());
			p_41432_.addFreshEntity(cow);
		}
		
		return super.use(p_41432_, p_41433_, p_41434_);
	}
	
	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer)
	{
		consumer.accept(new IClientItemExtensions() 
		{
			@Override
			public @Nullable ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) 
			{
				return VillageArmPoses.COWBELL;
			}
		});
	}
}
