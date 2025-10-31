package com.min01.villageandvillagers.item;

import com.min01.villageandvillagers.entity.VillageEntities;
import com.min01.villageandvillagers.entity.misc.EntityScarecrow;

import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ScarecrowItem extends Item
{
	public ScarecrowItem() 
	{
		super(new Item.Properties());
	}

	@Override
	public InteractionResult useOn(UseOnContext p_41427_) 
	{
		Player player = p_41427_.getPlayer();
		ItemStack stack = p_41427_.getItemInHand();
		Level level = p_41427_.getLevel();
		BlockPos pos = p_41427_.getClickedPos();
		if(!player.getAbilities().instabuild)
		{
			stack.shrink(1);
		}
		EntityScarecrow scarecrow = new EntityScarecrow(VillageEntities.SCARECROW.get(), level);
		scarecrow.setPos(Vec3.atBottomCenterOf(pos.above()));
		scarecrow.setOwner(player);
		scarecrow.lookAt(Anchor.EYES, player.getEyePosition());
		level.addFreshEntity(scarecrow);
		return InteractionResult.SUCCESS;
	}
}
