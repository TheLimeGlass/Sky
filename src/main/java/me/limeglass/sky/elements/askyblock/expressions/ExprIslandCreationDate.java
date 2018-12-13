package me.limeglass.sky.elements.askyblock.expressions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.util.Date;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.IASkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

@Name("Island Creation Date")
@Description({"Returns the creation date of the islands.", "ASkyBlock exclusive"})
@Examples("set {_difference} to the difference between now and creation date of {_island}")
public class ExprIslandCreationDate extends SimplePropertyExpression<SkyblockIsland, Date> {

	static {
		if (Sky.getSkyblock().isASkyBlock())
			register(ExprIslandCreationDate.class, Date.class, "[island] creation date", "islands");
	}
	
	@Override
	@Nullable
	public Date convert(SkyblockIsland island) {
		return new Date(((IASkyBlockIsland) island).getIsland().getCreatedDate());
	}
	
	@Override
	public Class<? extends Date> getReturnType() {
		return Date.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "island creation date";
	}
	
	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Date.class);
		return null;
	}
	
	@Override
	public void change(Event e, @Nullable Object[] delta, ChangeMode mode) {
		if (delta != null) {
			Date date = (Date) delta[0];
			for (SkyblockIsland island : getExpr().getArray(e)) {
				if (island instanceof IASkyBlockIsland) {
					((IASkyBlockIsland) island).getIsland().setCreatedDate(date.getTimestamp());
				}
			}
		}
	}
	
}
