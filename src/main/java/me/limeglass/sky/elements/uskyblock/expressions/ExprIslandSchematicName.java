package me.limeglass.sky.elements.uskyblock.expressions;

import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.uSkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

public class ExprIslandSchematicName extends SimplePropertyExpression<SkyblockIsland, String> {

	static {
		if (Sky.getSkyblock().getPluginType() == SkyblockPlugin.USKYBLOCK)
			register(ExprIslandSchematicName.class, String.class, "[island] schematic name", "islands");
	}
	
	@Override
	@Nullable
	public String convert(SkyblockIsland island) {
		return ((uSkyBlockIsland) island).getIsland().getSchematicName();
	}
	
	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "island schematic name";
	}
	
}
