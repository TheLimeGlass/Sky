package me.limeglass.sky.elements.expressions;

import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock;

@Name("Player Island")
@Description("Returns the islands of the players.")
@Examples("set {_island} to player's island")
@Since("1.0.0")
public class ExprIslandOfPlayer extends PropertyExpression<OfflinePlayer, SkyblockIsland> {

	static {
		Skript.registerExpression(ExprIslandOfPlayer.class, SkyblockIsland.class, ExpressionType.PROPERTY,
				"[the] island[s] of %offlineplayers% [in %-world%]",
				"%offlineplayers%'[s] island[s] [in %-world%]"
		);
	}

	private Expression<World> world;

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		setExpr((Expression<? extends OfflinePlayer>) exprs[0]);
		world = (Expression<World>) exprs[1];
		return true;
	}

	@Override
	public Class<? extends SkyblockIsland> getReturnType() {
		return SkyblockIsland.class;
	}

	@Override
	protected SkyblockIsland[] get(Event event, OfflinePlayer[] source) {
		Skyblock skyblock = Sky.getSkyblock();
		World world = this.world.getSingle(event);
		return get(source, player -> {
			return skyblock.getIslandOf(world, player);
		});
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "islands of " + getExpr().toString(event, debug) + (world != null ? " in " + world.toString(event, debug) : "");
	}

}
