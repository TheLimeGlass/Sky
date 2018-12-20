package me.limeglass.sky.elements.askyblock.expressions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.IASkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Island Password")
@Description({"Returns the password of the island.", "ASkyBlock exclusive"})
@Examples({
        "if password of {_island} is string-argument:",
        "\tteleport player to spawn of {_island}"
})
public class ExprIslandPassword extends SimplePropertyExpression<SkyblockIsland, String> {

	static {
		if (Sky.getSkyblock().getPluginType() == SkyblockPlugin.ASKYBLOCK)
			register(ExprIslandPassword.class, String.class, "[island] password", "islands");
	}
	
	@Override
	@Nullable
	public String convert(SkyblockIsland island) {
		return ((IASkyBlockIsland) island).getIsland().getPassword();
	}
	
	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "island password";
	}
	
	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(String.class);
		return null;
	}
	
	@Override
	public void change(Event e, @Nullable Object[] delta, ChangeMode mode) {
		if (delta != null) {
			String password = (String) delta[0];
			for (SkyblockIsland island : getExpr().getArray(e)) {
				if (island instanceof IASkyBlockIsland) {
					((IASkyBlockIsland) island).getIsland().setPassword(password);
				}
			}
		}
	}
	
}
