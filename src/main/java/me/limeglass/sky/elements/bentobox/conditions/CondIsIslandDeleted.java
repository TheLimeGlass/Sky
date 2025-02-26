package me.limeglass.sky.elements.bentobox.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.BentoBoxIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Is Island Deleted")
@Description("Checks if a BentoBox island is deleted.")
@Since("1.0")
public class CondIsIslandDeleted extends PropertyCondition<BentoBoxIsland> {

	static {
		if (Sky.getSkyblock().getPluginType() == SkyblockPlugin.BENTOBOX)
			register(CondIsIslandDeleted.class, "deleted", "island");
	}

	@Override
	public boolean check(BentoBoxIsland island) {
		return island.getIsland().isDeleted();
	}

	@Override
	protected String getPropertyName() {
		return "deleted";
	}

}
