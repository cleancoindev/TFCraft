package com.dunk.tfc.Entities.Mobs;

import com.dunk.tfc.Core.TFC_MobData;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.world.World;

public class EntityCaveSpiderTFC extends EntitySpider
{
	public EntityCaveSpiderTFC(World par1World)
	{
		super(par1World);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(TFC_MobData.CAVE_SPIDER_DAMAGE);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(TFC_MobData.CAVE_SPIDER_HEALTH);//MaxHealth
	}
}
