package me.limeglass.sky.elements.expressions;

import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.challenges.SkyblockChallenge;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Challenge Name")
@Description("Returns the name of the challenge.")
public class ExprChallengeName extends SimplePropertyExpression<SkyblockChallenge, String> {

	static {
		if (Sky.getSkyblock().getPluginType() != SkyblockPlugin.SKYBLOCKEARTH)
			register(ExprChallengeName.class, String.class, "[challenge] name", "challenges");
	}
	
	@Override
	@Nullable
	public String convert(SkyblockChallenge challenge) {
		return challenge.getName();
	}
	
	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "challenge name";
	}
	
}
