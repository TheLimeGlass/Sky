package me.limeglass.sky.elements.expressions;

import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Island Name")
@Description("Returns the names of the islands.")
public class ExprIslandName extends SimplePropertyExpression<SkyblockIsland, String> {

	static {
		if (Sky.getSkyblock().getPluginType() != SkyblockPlugin.SKYBLOCKEARTH)
			register(ExprIslandName.class, String.class, "[island] name", "islands");
	}
	
	@Override
	@Nullable
	public String convert(SkyblockIsland island) {
		return island.getName();
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
