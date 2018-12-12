package me.limeglass.sky.elements.askyblock.expressions;

import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.IASkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

public class ExprIslandPopulation extends SimplePropertyExpression<SkyblockIsland, Number> {

	static {
		if (Sky.getSkyblock().isASkyBlock())
			register(ExprIslandPopulation.class, Number.class, "[island] [villager] population", "islands");
	}
	
	@Override
	@Nullable
	public Number convert(SkyblockIsland island) {
		return ((IASkyBlockIsland) island).getIsland().getPopulation();
	}
	
	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "island population";
	}
	
}
