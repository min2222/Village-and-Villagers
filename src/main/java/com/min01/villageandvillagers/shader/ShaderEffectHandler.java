package com.min01.villageandvillagers.shader;

import java.util.ArrayList;
import java.util.List;

import com.min01.villageandvillagers.VillageandVillagers;

import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = VillageandVillagers.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ShaderEffectHandler 
{
	public static final List<ShaderEffect> EFFECTS = new ArrayList<>();
	
	public static void addEffect(String effectName, Vec3 pos, int lifetime)
	{
		EFFECTS.add(new ShaderEffect(effectName, pos, lifetime));
	}
	
	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent event)
	{
        if(event.phase == TickEvent.Phase.END)
        {
        	new ArrayList<>(EFFECTS).forEach(t -> 
    		{
    			t.tick();
    		});
    		EFFECTS.removeIf(t -> !t.enabled);
        }
	}
	
	public static class ShaderEffect
	{
		public boolean enabled = true;
		public int lifetime;
		public String effectName;
		public Vec3 pos;
		
		public ShaderEffect(String effectName, Vec3 pos) 
		{
			this(effectName, pos, 0);
		}
		
		public ShaderEffect(String effectName, Vec3 pos, int lifetime) 
		{
			this.effectName = effectName;
			this.pos = pos;
			this.lifetime = lifetime;
		}
		
		public void tick()
		{
			if(this.lifetime <= 0)
			{
				this.enabled = false;
			}
			else
			{
				this.lifetime--;
			}
		}
	}
}
