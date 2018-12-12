package me.limeglass.sky.elements.uskyblock.expressions;

import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.IuSkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

public class ExprIslandName extends SimplePropertyExpression<SkyblockIsland, String> {

	static {
		if (!Sky.getSkyblock().isASkyBlock())
			register(ExprIslandName.class, String.class, "[island] name", "islands");
	}
	
	@Override
	@Nullable
	public String convert(SkyblockIsland island) {
		return ((IuSkyBlockIsland) island).getIsland().getName();
	}
	
	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "island name";
	}
	
}
