package me.limeglass.sky.elements.askyblock.expressions;

import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.IASkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Island Population")
@Description({"Returns the villager population on the islands.", "ASkyBlock exclusive"})
public class ExprIslandPopulation extends SimplePropertyExpression<SkyblockIsland, Number> {

	static {
		if (Sky.getSkyblock().getPluginType() == SkyblockPlugin.ASKYBLOCK)
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
