package me.limeglass.sky.elements.uskyblock.expressions;

import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.IuSkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

public class ExprIslandPartySize extends SimplePropertyExpression<SkyblockIsland, Number> {

	static {
		if (!Sky.getSkyblock().isASkyBlock())
			register(ExprIslandPartySize.class, Number.class, "[island] party size", "islands");
	}
	
	@Override
	@Nullable
	public Number convert(SkyblockIsland island) {
		return ((IuSkyBlockIsland) island).getIsland().getPartySize();
	}
	
	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "island party size";
	}
	
}
