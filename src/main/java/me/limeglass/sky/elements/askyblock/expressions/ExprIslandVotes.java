package me.limeglass.sky.elements.askyblock.expressions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.ASkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Island Votes")
@Description({"Returns the amount of votes for the islands.", "ASkyBlock exclusive"})
@RequiredPlugins("ASkyBlock")
public class ExprIslandVotes extends SimplePropertyExpression<SkyblockIsland, Integer> {

	static {
		if (Sky.getSkyblock().getPluginType() == SkyblockPlugin.ASKYBLOCK)
			register(ExprIslandVotes.class, Integer.class, "[island] votes", "islands");
	}

	@Override
	@Nullable
	public Integer convert(SkyblockIsland island) {
		return ((ASkyBlockIsland) island).getIsland().getVotes();
	}

	@Override
	public Class<? extends Integer> getReturnType() {
		return Integer.class;
	}

	@Override
	protected String getPropertyName() {
		return "island votes";
	}

	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Number.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		int votes = (Integer) delta[0];
		for (SkyblockIsland island : getExpr().getArray(event)) {
			if (island instanceof ASkyBlockIsland)
				((ASkyBlockIsland) island).getIsland().setVotes(votes);
		}
	}

}
