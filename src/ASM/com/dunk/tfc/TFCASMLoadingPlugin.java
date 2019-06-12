package com.dunk.tfc;

import java.io.File;
import java.util.Map;

import com.dunk.tfc.ASM.Transform.TF_EntityLeashKnot;
import com.dunk.tfc.ASM.Transform.TF_EntityPlayer;
import com.dunk.tfc.ASM.Transform.TF_EntityPlayerMP;
import com.dunk.tfc.ASM.Transform.TF_EntityRenderer;
import com.dunk.tfc.ASM.Transform.TF_RenderGlobal;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@TransformerExclusions({ "com.dunk.tfc.ASM" })
public class TFCASMLoadingPlugin implements IFMLLoadingPlugin
{
	public static boolean runtimeDeobf;
	public static File location;

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{
				TF_EntityRenderer.class.getName(),
				TF_RenderGlobal.class.getName(),
				TF_EntityLeashKnot.class.getName(),
				TF_EntityPlayerMP.class.getName(),
				TF_EntityPlayer.class.getName()};
	}

	@Override
	public String getModContainerClass() {
		return TerraFirmaCraftCore.class.getName();
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		runtimeDeobf = (Boolean) data.get("runtimeDeobfuscationEnabled");
		location = (File) data.get("coremodLocation");
	}

}
