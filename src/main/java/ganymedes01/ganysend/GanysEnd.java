package ganymedes01.ganysend;

import ganymedes01.ganysend.configuration.ConfigurationHandler;
import ganymedes01.ganysend.core.handlers.InterModComms;
import ganymedes01.ganysend.core.proxy.CommonProxy;
import ganymedes01.ganysend.creativetab.CreativeTabEnd;
import ganymedes01.ganysend.enchantment.ModEnchants;
import ganymedes01.ganysend.integration.Integration;
import ganymedes01.ganysend.integration.ModIntegrator;
import ganymedes01.ganysend.integration.ThaumcraftManager;
import ganymedes01.ganysend.lib.Reference;
import ganymedes01.ganysend.network.PacketHandler;
import ganymedes01.ganysend.recipes.ModRecipes;
import ganymedes01.ganysend.world.EndWorldGenerator;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.FlowerEntry;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Gany's End
 *
 * @author ganymedes01
 *
 */

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION_NUMBER, dependencies = Reference.DEPENDENCIES, guiFactory = Reference.GUI_FACTORY_CLASS)
public class GanysEnd {

	@Instance(Reference.MOD_ID)
	public static GanysEnd instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	public static CreativeTabs endTab = new CreativeTabEnd();

	public static boolean togglerShouldMakeSound = true;
	public static boolean shouldDoVersionCheck = true;
	public static boolean activateShifters = true;
	public static boolean enableTimeManipulator = true;
	public static boolean enableRandomHeadDrop = true;
	public static boolean enableVanillaHeadsDrop = true;
	public static boolean enableEnderBag = true;
	public static boolean enableRawEndiumRecipe = false;
	public static String[] others = { "Jeb_Jeb", "KingPurpleRaptor", "DylanGK" };
	public static String[] modders = { "ganymedes01", "Pokefenn", "chylex", "vadis365", "CrazyPants", "Kihira", "Country_Gamer" };
	public static String[] youtubers = { "Sjin", "Xephos", "LividCoffee", "Rythian", "Zoeya", "TheStrippin", "inthelittlewood", "Quetzz", "Blorph", "xbony2", "CaptainSparklez", "AntVenom", "CavemanFilms", "Fosler", "BevoLJ", "Sips_", "Honeydew", "TobyTurner", "corjaantje" };
	public static String[] mojang = { "Notch", "jeb_", "C418", "Dinnerbone", "Grumm", "Searge_DP", "EvilSeph", "TheMogMiner" };
	public static String[] mindCrack = { "adlingtont", "AnderZEL", "Arkas", "Aureylian", "AvidyaZEN", "BdoubleO100", "BlameTC", "Coestar", "Docm77", "Etho", "generikb", "Guude", "jsano19", "kurtmac", "mcgamer", "Mhykol", "Millbee", "Nebris", "Pakratt0013", "paulsoaresjr", "PauseUnpause", "Pyro_0", "SethBling", "sevadus", "Vechs_", "VintageBeef", "W92Baj", "Zisteau" };
	public static String[] forgeCraft = { "Mithion", "RWTema", "WayofFlowingTime", "TTFTCUTS", "bspkrs", "futureamnet", "azanor", "chicken_bones", "Cloudhunter", "CovertJaguar", "cpw11", "dan200", "direwolf20", "Eloraam", "florastar", "ohaiiChun", "jadedcat", "KingLemmingCoFH", "Krapht", "LexManos", "TheMattaBase", "mDiyo", "Myrathi", "Morvelaira", "Pahimar", "sfPlayer1", "ProfMobius", "Rorax", "Sacheverell", "sirsengir", "slowpoke101", "Soaryn", "x3n0ph0b3", "XCompWiz", "Vswe", "Vazkii",
		"ZeldoKavira", "neptunepink", "EddieRuckus" };

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModIntegrator.preInit();

		ConfigurationHandler.INSTANCE.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.MASTER + File.separator + Reference.MOD_ID + ".cfg"));

		GameRegistry.registerWorldGenerator(new EndWorldGenerator(), 0);

		ModBlocks.init();
		ModItems.init();
		ModRecipes.init();
		ModEnchants.init();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		PacketHandler.init();

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

		proxy.registerEvents();
		proxy.registerTileEntities();
		proxy.registerRenderers();

		ModIntegrator.init();

		if (GanysEnd.enableEnderBag)
			try {
				Block blockEnderChest = (Block) Class.forName("codechicken.enderstorage.EnderStorage").getDeclaredField("blockEnderChest").get(null);
				for (int i = 0; i < 0x1000; i++)
					OreDictionary.registerOre("enderChest", new ItemStack(blockEnderChest, 1, i));
			} catch (Exception e) {
			}
	}

	@SuppressWarnings("unchecked")
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		ModIntegrator.postInit();

		try {
			for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray()) {
				Field f = BiomeGenBase.class.getDeclaredField("flowers");
				f.setAccessible(true);
				for (FlowerEntry entry : (List<FlowerEntry>) f.get(biome))
					if (entry.block != null)
						OreDictionary.registerOre("dayGemMaterial", new ItemStack(entry.block, 1, entry.metadata));
			}
		} catch (Exception e) {
		}
	}

	@EventHandler
	public void postPostInit(FMLServerAboutToStartEvent event) {
		for (Integration integration : ModIntegrator.modIntegrations)
			if (integration.shouldIntegrate() && integration instanceof ThaumcraftManager) {
				((ThaumcraftManager) integration).postPostInit();
				return;
			}
	}

	@EventHandler
	public void processIMCRequests(IMCEvent event) {
		InterModComms.processIMC(event);
	}
}