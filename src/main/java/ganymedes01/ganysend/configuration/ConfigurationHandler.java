package ganymedes01.ganysend.configuration;

import ganymedes01.ganysend.GanysEnd;
import ganymedes01.ganysend.integration.Integration;
import ganymedes01.ganysend.integration.ModIntegrator;
import ganymedes01.ganysend.lib.ModIDs;
import ganymedes01.ganysend.lib.Reference;
import ganymedes01.ganysend.lib.Strings;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * Gany's End
 *
 * @author ganymedes01
 *
 */

public class ConfigurationHandler {

	public static ConfigurationHandler INSTANCE = new ConfigurationHandler();
	public Configuration configFile;
	public String[] usedCategories = { Configuration.CATEGORY_GENERAL, "enchantments", "mod integration" };

	private boolean configBoolean(String name, boolean requiresRestart, boolean def) {
		return configFile.get(Configuration.CATEGORY_GENERAL, name, def).setRequiresMcRestart(requiresRestart).getBoolean(def);
	}

	private int configEnch(String ench, int def) {
		int config = configFile.get("Enchantments", ench, def).setRequiresMcRestart(true).getInt(def);
		return config > 0 ? config : def;
	}

	private boolean configIntegrationBoolean(String modID) {
		return configFile.get("Mod Integration", "Integrate " + modID, true).setRequiresMcRestart(true).getBoolean(true);
	}

	public void init(File file) {
		configFile = new Configuration(file);

		syncConfigs();
	}

	private void syncConfigs() {
		// Enchantments
		ModIDs.IMPERVIOUSNESS_ID = configEnch(Strings.IMPERVIOUSNESS_NAME, 100);

		// Mod Integration
		for (Integration integration : ModIntegrator.modIntegrations)
			integration.setShouldIntegrate(configIntegrationBoolean(integration.getModID()));

		// Others
		GanysEnd.togglerShouldMakeSound = configBoolean(Strings.TOGGLERS_SHOULD_MAKE_SOUND, false, GanysEnd.togglerShouldMakeSound);
		GanysEnd.shouldDoVersionCheck = configBoolean(Strings.SHOULD_DO_VERSION_CHECK, true, GanysEnd.shouldDoVersionCheck);
		GanysEnd.activateShifters = configBoolean(Strings.ACTIVATE_SHIFTERS, true, GanysEnd.activateShifters);
		GanysEnd.enableRandomHeadDrop = configBoolean(Strings.ENABLE_RANDOM_HEAD_DROP, false, GanysEnd.enableRandomHeadDrop);
		GanysEnd.enableTimeManipulator = configBoolean(Strings.ENABLE_TIME_MANIPULATOR, true, GanysEnd.enableTimeManipulator);
		GanysEnd.enableEnderBag = configBoolean(Strings.ENABLE_ENDER_BAG, true, GanysEnd.enableEnderBag);
		GanysEnd.enableRawEndiumRecipe = configBoolean(Strings.ENABLE_RAW_ENDIUM_RECIPE, true, GanysEnd.enableRawEndiumRecipe);

		if (configFile.hasChanged())
			configFile.save();
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (Reference.MOD_ID.equals(eventArgs.modID))
			syncConfigs();
	}
}