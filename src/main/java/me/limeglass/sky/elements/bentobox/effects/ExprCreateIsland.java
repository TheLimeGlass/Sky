package me.limeglass.sky.elements.bentobox.effects;

import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.BentoBoxIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.api.events.island.IslandEvent.Reason;
import world.bentobox.bentobox.api.user.User;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.managers.IslandsManager;
import world.bentobox.bentobox.managers.island.NewIsland;
import world.bentobox.bentobox.managers.island.NewIsland.Builder;

@Name("Create Island")
@Description({
	"Creates a new island at a location or at a location and for a player or.",
	"recreates a new island if the provided expression is an island. BentoBox syntax."
})
@Examples({
	"a new island for player with blueprint \"skyblock\"",
	"a island at {_location} for event-offlineplayer",
})
@RequiredPlugins("BentoBox")
public class ExprCreateIsland extends SimpleExpression<BentoBoxIsland> {

	static {
		if (Sky.getSkyblock().getPluginType() == SkyblockPlugin.BENTOBOX)
			Skript.registerExpression(ExprCreateIsland.class, BentoBoxIsland.class, ExpressionType.SIMPLE,
					"[a] new island for %offlineplayer% [with blueprint %-string%]",
					"[a] new island at %location% [for %-offlineplayer%]"
			);
	}

	@Nullable private Expression<Location> location;
	@Nullable private Expression<String> blueprint;
	@Nullable private Expression<?> object;

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		if (matchedPattern == 0) {
			object = exprs[1];
			blueprint = (Expression<String>) exprs[2];
		} else if (matchedPattern == 1) {
			location = (Expression<Location>) exprs[0];
			object = exprs[0];
		}
		return true;
	}

	@Override
	protected BentoBoxIsland @Nullable [] get(Event event) {
		BentoBox bento = BentoBox.getInstance();
		if (location != null) {
			IslandsManager islandManager = bento.getIslandsManager();
			if (object == null) {
				Island island = islandManager.createIsland(location.getSingle(event));
				return new BentoBoxIsland[] {new BentoBoxIsland(island)};
			}
			if (object.getSingle(event) instanceof OfflinePlayer offlineplayer) {
				Island island = islandManager.createIsland(location.getSingle(event), offlineplayer.getUniqueId());
				return new BentoBoxIsland[] {new BentoBoxIsland(island)};
			}
			assert false;
			return null;
		}
		Builder builder = NewIsland.builder();
		builder.reason(Reason.CREATE);
		if (object instanceof OfflinePlayer offlineplayer) {
			User user = bento.getPlayers().getUser(offlineplayer.getUniqueId());
			builder.player(user);
		}
		if (blueprint != null)
			builder.name(blueprint.getSingle(event));
		try {
			return new BentoBoxIsland[] {new BentoBoxIsland(builder.build())};
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "new island";
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends BentoBoxIsland> getReturnType() {
		return BentoBoxIsland.class;
	}

}
