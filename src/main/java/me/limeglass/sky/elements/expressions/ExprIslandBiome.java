package me.limeglass.sky.elements.expressions;

import org.bukkit.block.Biome;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.ASkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Island Biome")
@Description("Returns the biomes of the islands.")
@Examples("set biome of player's island to desert")
public class ExprIslandBiome extends SimplePropertyExpression<SkyblockIsland, Biome> {

	static {
		register(ExprIslandBiome.class, Biome.class, "[island] biome", "islands");
	}
	
	@Override
	@Nullable
	public Biome convert(SkyblockIsland island) {
		return island.getBiome();
	}
	
	@Override
	public Class<? extends Biome> getReturnType() {
		return Biome.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "island biome";
	}
	
	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET && Sky.getSkyblock().getPluginType() == SkyblockPlugin.ASKYBLOCK)
			return CollectionUtils.array(Biome.class);
		return null;
	}
	
	@Override
	public void change(Event e, @Nullable Object[] delta, ChangeMode mode) {
		if (delta != null) {
			Biome biome = (Biome) delta[0];
			for (SkyblockIsland island : getExpr().getArray(e)) {
				if (island instanceof ASkyBlockIsland) {
					((ASkyBlockIsland) island).getIsland().setBiome(biome);
				}
			}
		}
	}
	
}
