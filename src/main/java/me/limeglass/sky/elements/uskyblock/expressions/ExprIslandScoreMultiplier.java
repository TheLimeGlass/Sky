package me.limeglass.sky.elements.uskyblock.expressions;

import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.IuSkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

public class ExprIslandScoreMultiplier extends SimplePropertyExpression<SkyblockIsland, Number> {

	static {
		if (!Sky.getSkyblock().isASkyBlock())
			register(ExprIslandScoreMultiplier.class, Number.class, "[island] score multiplier", "islands");
	}
	
	@Override
	@Nullable
	public Number convert(SkyblockIsland island) {
		return ((IuSkyBlockIsland) island).getIsland().getScoreMultiplier();
	}
	
	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "island score multiplier";
	}
	
}
