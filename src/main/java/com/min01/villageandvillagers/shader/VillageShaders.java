package com.min01.villageandvillagers.shader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.min01.villageandvillagers.VillageandVillagers;

import net.minecraft.client.renderer.PostChain;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;

public class VillageShaders implements ResourceManagerReloadListener 
{
	protected static final List<ExtendedPostChain> SHADERS = new ArrayList<>();

	protected static ExtendedPostChain DISTORTION;
	protected static ExtendedPostChain SHOCKWAVE;

	@Override
	public void onResourceManagerReload(ResourceManager manager)
	{
		this.clear();
		try
		{
			init(manager);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void init(ResourceManager manager) throws IOException
	{
		DISTORTION = add(new ExtendedPostChain(VillageandVillagers.MODID, "distortion"));
		SHOCKWAVE = add(new ExtendedPostChain(VillageandVillagers.MODID, "shockwave"));
	}

	public void clear()
	{
		SHADERS.forEach(PostChain::close);
		SHADERS.clear();
	}

	public static ExtendedPostChain add(ExtendedPostChain shader)
	{
		SHADERS.add(shader);
		return shader;
	}
	
	public static ExtendedPostChain getDistortion()
	{
		return DISTORTION;
	}
	
	public static ExtendedPostChain getShockwave()
	{
		return SHOCKWAVE;
	}
}
