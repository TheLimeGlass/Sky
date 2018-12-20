package me.limeglass.sky.elements.askyblock.expressions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.IASkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Island Votes")
@Description({"Returns the amount of votes for the islands.", "ASkyBlock exclusive"})
public class ExprIslandVotes extends SimplePropertyExpression<SkyblockIsland, Number> {

	static {
		if (Sky.getSkyblock().getPluginType() == SkyblockPlugin.ASKYBLOCK)
			register(ExprIslandVotes.class, Number.class, "[island] votes", "islands");
	}
	
	@Override
	@Nullable
	public Number convert(SkyblockIsland island) {
		return ((IASkyBlockIsland) island).getIsland().getVotes();
	}
	
	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
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
	public void change(Event e, @Nullable Object[] delta, ChangeMode mode) {
		if (delta != null) {
			Number votes = (Number) delta[0];
			for (SkyblockIsland island : getExpr().getArray(e)) {
				if (island instanceof IASkyBlockIsland) {
					((IASkyBlockIsland) island).getIsland().setVotes(votes.intValue());
				}
			}
		}
	}
	
}
