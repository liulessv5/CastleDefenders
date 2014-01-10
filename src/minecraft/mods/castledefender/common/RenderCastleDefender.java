package mods.castledefender.common;

import java.util.Hashtable;

import mods.castledefender.common.entities.EntityKnight;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderCastleDefender extends RenderBiped {
	
	/**
	 * Cache des location de texture
	 */
	private static Hashtable<String, ResourceLocation> cacheResource = new Hashtable<String, ResourceLocation>();
	
	protected String name;
	
	public RenderCastleDefender(ModelBiped biped, float tailleOmbre, String name) {
		super(biped, tailleOmbre);
		this.name = name;
	}
	
	private ResourceLocation getResource(String name) {
		
		ResourceLocation texture;
		
		if (cacheResource.containsKey(name)) {
			texture = cacheResource.get(name);
		} else {
			texture = new ResourceLocation("castledefender:"+name+".png");
			cacheResource.put(name, texture);
		}
		
		return texture;
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return getResource(name);
	}

}