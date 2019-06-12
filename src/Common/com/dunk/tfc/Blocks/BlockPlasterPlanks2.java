package com.dunk.tfc.Blocks;

import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.Constant.Global;

import net.minecraft.block.material.Material;

public class BlockPlasterPlanks2 extends BlockPlasterPlanks {

	public BlockPlasterPlanks2(Material material) {
		super(material);
		woodNames = new String[Global.WOOD_ALL.length-16];
		System.arraycopy(Global.WOOD_ALL, 16, woodNames, 0, Global.WOOD_ALL.length-16);
		replacedBlock = TFCBlocks.planks2;
	}

}
