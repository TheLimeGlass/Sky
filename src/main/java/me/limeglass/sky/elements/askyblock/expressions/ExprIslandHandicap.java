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

@Name("Island Level Handicap")
@Description({"Returns the islands handicap level.", "ASkyBlock exclusive"})
public class ExprIslandHandicap extends SimplePropertyExpression<SkyblockIsland, Number> {

	static {
		if (Sky.getSkyblock().isASkyBlock())
			register(ExprIslandHandicap.class, Number.class, "[island] [level] handicap", "islands");
	}
	
	@Override
	@Nullable
	public Number convert(SkyblockIsland island) {
		return ((IASkyBlockIsland) island).getIsland().getLevelHandicap();
	}
	
	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "island handicap";
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
			Number level = (Number) delta[0];
			for (SkyblockIsland island : getExpr().getArray(e)) {
				if (island instanceof IASkyBlockIsland) {
					((IASkyBlockIsland) island).getIsland().setLevelHandicap(level.intValue());
				}
			}
		}
	}
	
}
