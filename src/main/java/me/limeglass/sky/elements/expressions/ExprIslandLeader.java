package me.limeglass.sky.elements.expressions;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.ASkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Island Leader")
@Description("Returns the leader of the islands.")
public class ExprIslandLeader extends SimplePropertyExpression<SkyblockIsland, OfflinePlayer> {

	static {
		register(ExprIslandLeader.class, OfflinePlayer.class, "island (owner|leader)", "islands");
	}

	@Override
	@Nullable
	public OfflinePlayer convert(SkyblockIsland island) {
		return island.getLeader();
	}

	@Override
	public Class<? extends OfflinePlayer> getReturnType() {
		return OfflinePlayer.class;
	}

	@Override
	protected String getPropertyName() {
		return "island leader";
	}

	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET && Sky.getSkyblock().getPluginType() == SkyblockPlugin.ASKYBLOCK)
			return CollectionUtils.array(OfflinePlayer.class);
		return null;
	}

	@Override
	public void change(Event e, @Nullable Object[] delta, ChangeMode mode) {
		if (delta != null) {
			OfflinePlayer player = (OfflinePlayer) delta[0];
			for (SkyblockIsland island : getExpr().getArray(e)) {
				if (island instanceof ASkyBlockIsland) {
					((ASkyBlockIsland) island).setLeader(player);
				}
			}
		}
	}

}
