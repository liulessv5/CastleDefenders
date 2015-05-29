package com.gollum.castledefenders.common.entities;

import net.minecraft.world.World;

import com.gollum.castledefenders.ModCastleDefenders;
import com.gollum.castledefenders.inits.ModBlocks;
import com.gollum.core.common.config.type.MobCapacitiesConfigType;

public class EntityArcher2 extends EntityArcher {

	public EntityArcher2(World world) {
		super(world);
		this.blockSpawnId = ModBlocks.blockArcher2.blockID;
	}
	
	/**
	 * @return les capacitées du mod
	 */
	protected MobCapacitiesConfigType getCapacities () { return ModCastleDefenders.config.archer2Capacities; }
	
}