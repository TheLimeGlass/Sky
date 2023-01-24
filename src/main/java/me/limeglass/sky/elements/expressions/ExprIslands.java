package me.limeglass.sky.elements.expressions;

import java.util.Arrays;

import org.bukkit.World;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Islands Of World")
@Description("Returns the islands of the defined worlds.")
@Since("1.0.4")
public class ExprIslands extends PropertyExpression<World, SkyblockIsland> {

	static {
		if (Sky.getSkyblock().getPluginType() != SkyblockPlugin.USKYBLOCK)
			Skript.registerExpression(ExprIslands.class, SkyblockIsland.class, ExpressionType.PROPERTY,
					"[the] islands (in|of) %worlds%",
					"%worlds%'[s] islands"
			);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		setExpr((Expression<? extends World>) exprs[0]);
		return true;
	}

	@Override
	protected SkyblockIsland[] get(Event event, World[] source) {
		return Arrays.stream(source)
				.flatMap(world -> Sky.getSkyblock().getIslandsOf(world).stream())
				.toArray(SkyblockIsland[]::new);
	}

	@Override
	public Class<? extends SkyblockIsland> getReturnType() {
		return SkyblockIsland.class;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "islands of " + getExpr().toString(event, debug);
	}

}
