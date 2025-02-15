package net.dorianpb.cem.external.renderers;

import net.dorianpb.cem.external.models.CemGiantZombieModel;
import net.dorianpb.cem.internal.api.CemRenderer;
import net.dorianpb.cem.internal.models.CemModelRegistry;
import net.dorianpb.cem.internal.util.CemRegistryManager;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.GiantEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.util.Identifier;

public class CemGiantZombieRenderer extends GiantEntityRenderer implements CemRenderer{
	private final        CemModelRegistry          registry;
	
	public CemGiantZombieRenderer(EntityRendererFactory.Context context){
		super(context, 6F);
		this.registry = CemRegistryManager.getRegistry(getType());
		try{
			this.model = new CemGiantZombieModel(registry);
			if(registry.hasShadowRadius()){
				this.shadowRadius = registry.getShadowRadius();
			}
		} catch(Exception e){
			modelError(e);
		}
	}
	
	private static EntityType<? extends Entity> getType(){
		return EntityType.GIANT;
	}
	
	@Override
	public String getId(){
		return getType().toString();
	}
	
	@Override
	public Identifier getTexture(GiantEntity entity){
		if(this.registry != null && this.registry.hasTexture()){
			return this.registry.getTexture();
		}
		return super.getTexture(entity);
	}
}