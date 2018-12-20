package me.limeglass.sky.elements.expressions;

import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

@Name("Island Level")
@Description("Returns the level of the islands.")
public class ExprIslandLevel extends SimplePropertyExpression<SkyblockIsland, Number> {

	static {
		register(ExprIslandLevel.class, Number.class, "[island] level", "islands");
	}
	
	@Override
	@Nullable
	public Number convert(SkyblockIsland island) {
		return island.getLevel();
	}
	
	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "island level";
	}
	
}
