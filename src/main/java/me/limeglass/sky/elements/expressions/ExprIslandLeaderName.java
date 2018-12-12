package me.limeglass.sky.elements.expressions;

import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

public class ExprIslandLeaderName extends SimplePropertyExpression<SkyblockIsland, String> {

	static {
		register(ExprIslandLeaderName.class, String.class, "[island] (owner|leader)['s] name", "islands");
	}
	
	@Override
	@Nullable
	public String convert(SkyblockIsland island) {
		return island.getLeaderName();
	}
	
	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "island leader name";
	}
	
}
