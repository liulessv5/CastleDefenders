package mods.castledefenders.common;

import java.lang.annotation.Annotation;
import java.util.logging.Logger;

import mods.castledefenders.common.blocks.BlockArcher;
import mods.castledefenders.common.blocks.BlockArcher2;
import mods.castledefenders.common.blocks.BlockArcherM;
import mods.castledefenders.common.blocks.BlockEArcher;
import mods.castledefenders.common.blocks.BlockEKnight;
import mods.castledefenders.common.blocks.BlockEMage;
import mods.castledefenders.common.blocks.BlockHealer;
import mods.castledefenders.common.blocks.BlockKnight;
import mods.castledefenders.common.blocks.BlockKnight2;
import mods.castledefenders.common.blocks.BlockMage;
import mods.castledefenders.common.blocks.BlockMerc;
import mods.castledefenders.common.entities.EntityArcher;
import mods.castledefenders.common.entities.EntityKnight;
import mods.castledefenders.common.entities.EntityKnight2;
import mods.castledefenders.common.items.ItemMedallion;
import mods.castledefenders.common.tileentities.TileEntityBlockArcher;
import mods.castledefenders.common.tileentities.TileEntityBlockArcher2;
import mods.castledefenders.common.tileentities.TileEntityBlockArcherM;
import mods.castledefenders.common.tileentities.TileEntityBlockEArcher;
import mods.castledefenders.common.tileentities.TileEntityBlockEKnight;
import mods.castledefenders.common.tileentities.TileEntityBlockEMage;
import mods.castledefenders.common.tileentities.TileEntityBlockHealer;
import mods.castledefenders.common.tileentities.TileEntityBlockKnight;
import mods.castledefenders.common.tileentities.TileEntityBlockKnight2;
import mods.castledefenders.common.tileentities.TileEntityBlockMage;
import mods.castledefenders.common.tileentities.TileEntityBlockMerc;
import mods.castledefenders.utils.ConfigLoader;
import mods.castledefenders.utils.ConfigProp;
import mods.castledefenders.utils.VersionChecker;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "CastleDefenders", name = "Castle Defenders", version = "3.0.0 [Build Smeagol]", acceptedMinecraftVersions = "1.6.4")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class ModCastleDefenders {
	
	@Instance("ModCastleDefenders")
	public static ModCastleDefenders instance;
	
	@SidedProxy(clientSide = "mods.castledefenders.common.ClientProxyCastleDefenders", serverSide = "mods.castledefenders.common.CommonProxyCastleDefenders")
	public static CommonProxyCastleDefenders proxy;
	
	@ConfigProp (info = "Display version checker message")
	public static boolean versionChecker = true;
	
	// Gestion des logs
	public static Logger log;
	
	// Tab du mode creative
	public static CastleDefendersTabs tabCastleDefenders;
	
	// Liste des blocks
	public static Block blockKnight;
	public static Block blockKnight2;
	public static Block blockArcher;
	public static Block blockArcher2;
	public static Block blockMerc;
	public static Block blockArcherM;
	public static Block blockMage;
	public static Block blockHealer;
	public static Block blockEKnight;
	public static Block blockEArcher;
	public static Block blockEMage;
	
	// Liste des items
	public static Item itemMedallion;
	
	// Liste des IDs
	@ConfigProp(group = "Blocks Ids") public static int blockKnightID  = 1238;
	@ConfigProp(group = "Blocks Ids") public static int blockKnight2ID = 1240;
	@ConfigProp(group = "Blocks Ids") public static int blockArcherID  = 1239;
	@ConfigProp(group = "Blocks Ids") public static int blockArcher2ID = 1241;
	@ConfigProp(group = "Blocks Ids") public static int blockMercID    = 1234;
	@ConfigProp(group = "Blocks Ids") public static int blockArcherMID = 1232;
	@ConfigProp(group = "Blocks Ids") public static int blockMageID    = 1235;
	@ConfigProp(group = "Blocks Ids") public static int blockHealerID  = 1242;
	@ConfigProp(group = "Blocks Ids") public static int blockEKnightID = 1237;
	@ConfigProp(group = "Blocks Ids") public static int blockEArcherID = 1236;
	@ConfigProp(group = "Blocks Ids") public static int blockEMageID   = 1233;
	
	@ConfigProp(group = "Items Ids") public static int medallionID    = 13001;
	
	@ConfigProp(group = "Mobs Ids") public static int knightID   = -31;
	@ConfigProp(group = "Mobs Ids") public static int knight2ID  = -32;
	@ConfigProp(group = "Mobs Ids") public static int archerID   = -30;
	@ConfigProp(group = "Mobs Ids") public static int archer2ID  = -33;
	@ConfigProp(group = "Mobs Ids") public static int mercID     = -29;
	@ConfigProp(group = "Mobs Ids") public static int archerMID  = -25;
	@ConfigProp(group = "Mobs Ids") public static int mageID     = -13;
	@ConfigProp(group = "Mobs Ids") public static int healerID   = -34;
	@ConfigProp(group = "Mobs Ids") public static int eKnightID  = -28;
	@ConfigProp(group = "Mobs Ids") public static int eArcherID  = -27;
	@ConfigProp(group = "Mobs Ids") public static int eMageID    = -26;
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		
		// Creation du logger
		log = event.getModLog();
		
		ConfigLoader configLoader = new ConfigLoader(this.getClass(), event);
		configLoader.loadConfig();
		
	}
	
	/** 2 **/
	@EventHandler
	public void load(FMLInitializationEvent event) {
		
		// Execution du renderer en fonction du serveur ou du client
		proxy.registerRenderers();
		
		// Creation du checker de version
		VersionChecker.getInstance(this.versionChecker).check(this);

		tabCastleDefenders = new CastleDefendersTabs("CastleDefender");
		LanguageRegistry.instance().addStringLocalization("itemGroup.CastleDefender", "en_US", "Castle Defender");

		//Initialisation des items
		this.initItems();
		
		//Initialisation des blocks
		this.initBlocks ();

		// Nom des TileEntities
		this.initTileEntities ();
		
		// Ajout des recettes
		this.initRecipes ();
		
		// Enregistrement des Mobs
		this.initMobs ();
		
		this.tabCastleDefenders.setIcon(this.blockArcherM);
		
	}

	/**
	 * Initialisation des items
	 */
	public void initItems () {

		this.itemMedallion = (new ItemMedallion(this.medallionID)).setUnlocalizedName("MedallionCD");
		GameRegistry.registerItem(this.itemMedallion, "Medallion", this.getModid());
		LanguageRegistry.addName(this.itemMedallion, "Medallion");
	}
	
	/**
	 * Initialisation des blocks
	 */
	public void initBlocks () {

		// Création des blocks
		this.blockKnight  = (new BlockKnight(this.blockKnightID))  .setUnlocalizedName("BlockKnight") .setHardness(2.0F).setResistance(5.0F);
		this.blockKnight2 = (new BlockKnight2(this.blockKnight2ID)).setUnlocalizedName("BlockKnight2").setHardness(2.0F).setResistance(5.0F);
		this.blockArcher  = (new BlockArcher(this.blockArcherID))  .setUnlocalizedName("BlockArcher") .setHardness(2.0F).setResistance(5.0F);
		this.blockArcher2 = (new BlockArcher2(this.blockArcher2ID)).setUnlocalizedName("BlockArcher2").setHardness(2.0F).setResistance(5.0F);
		this.blockMerc    = (new BlockMerc(this.blockMercID))      .setUnlocalizedName("BlockMerc")   .setHardness(2.0F).setResistance(5.0F);
		this.blockArcherM = (new BlockArcherM(this.blockArcherMID)).setUnlocalizedName("BlockArcherM").setHardness(2.0F).setResistance(5.0F);
		this.blockMage    = (new BlockMage(this.blockMageID))      .setUnlocalizedName("BlockMage")   .setHardness(2.0F).setResistance(5.0F);
		this.blockHealer  = (new BlockHealer(this.blockHealerID))  .setUnlocalizedName("BlockHealer") .setHardness(2.0F).setResistance(5.0F);
		this.blockEKnight = (new BlockEKnight(this.blockEKnightID)).setUnlocalizedName("BlockEKnight").setHardness(2.0F).setResistance(5.0F);
		this.blockEArcher = (new BlockEArcher(this.blockEArcherID)).setUnlocalizedName("BlockEArcher").setHardness(2.0F).setResistance(5.0F);
		this.blockEMage   = (new BlockEMage(this.blockEMageID))    .setUnlocalizedName("BlockEMage")  .setHardness(2.0F).setResistance(5.0F);
		
		
		// Enregistrement des blocks
		GameRegistry.registerBlock(this.blockKnight , "Knight Spawner");
		GameRegistry.registerBlock(this.blockKnight2, "Knight Spawner - Level 2");
		GameRegistry.registerBlock(this.blockArcher , "Archer Spawner");
		GameRegistry.registerBlock(this.blockArcher2, "Archer Spawner - Level 2");
		GameRegistry.registerBlock(this.blockMerc   , "Merc Spawner");
		GameRegistry.registerBlock(this.blockArcherM, "Merc Archer Spawner");
		GameRegistry.registerBlock(this.blockMage   , "Mage Spawner");
		GameRegistry.registerBlock(this.blockHealer , "Healer Spawner");
		GameRegistry.registerBlock(this.blockEKnight, "Enemy Knight Spawner");
		GameRegistry.registerBlock(this.blockEArcher, "Enemy Archer Spawner");
		GameRegistry.registerBlock(this.blockEMage  , "Enemy Mage Spawner");
		
		// Nom des blocks
		LanguageRegistry.addName(this.blockKnight , "Knight Spawner");
		LanguageRegistry.addName(this.blockKnight2, "Knight Spawner - Level 2");
		LanguageRegistry.addName(this.blockArcher , "Archer Spawner");
		LanguageRegistry.addName(this.blockArcher2, "Archer Spawner - Level 2");
		LanguageRegistry.addName(this.blockMerc   , "Merc Spawner");
		LanguageRegistry.addName(this.blockArcherM, "Merc Archer Spawner");
		LanguageRegistry.addName(this.blockMage   , "Mage Spawner");
		LanguageRegistry.addName(this.blockHealer , "Mage Healer");
		LanguageRegistry.addName(this.blockEKnight, "Enemy Knight Spawner");
		LanguageRegistry.addName(this.blockEArcher, "Enemy Archer Spawner");
		LanguageRegistry.addName(this.blockEMage  , "Enemy Mage Spawner");
	}
	
	/**
	 * // Nom des TileEntities
	 */
	private void initTileEntities () {
		GameRegistry.registerTileEntity(TileEntityBlockKnight.class , "Knight Block");
		GameRegistry.registerTileEntity(TileEntityBlockKnight2.class, "Knight Block2");
		GameRegistry.registerTileEntity(TileEntityBlockArcher.class , "BlockArcher");
		GameRegistry.registerTileEntity(TileEntityBlockArcher2.class, "BlockArcher2");
		GameRegistry.registerTileEntity(TileEntityBlockMerc.class   , "Merc Block");
		GameRegistry.registerTileEntity(TileEntityBlockArcherM.class, "Merc Archer Block");
		GameRegistry.registerTileEntity(TileEntityBlockMage.class   , "Mage Block");
		GameRegistry.registerTileEntity(TileEntityBlockHealer.class , "Hearler Block");
		GameRegistry.registerTileEntity(TileEntityBlockEKnight.class, "Enemy Knight Block");
		GameRegistry.registerTileEntity(TileEntityBlockEArcher.class, "Enemy Archer Block");
		GameRegistry.registerTileEntity(TileEntityBlockEMage.class  , "Enemy Mage Block");
	}
	
	/**
	 * Ajout des recettes
	 */
	private void initRecipes () {
		GameRegistry.addRecipe(new ItemStack(this.blockKnight, 1), new Object[] {" X ", "XYX", " X ", 'X', Item.ingotIron, 'Y', Item.swordIron});
		GameRegistry.addRecipe(new ItemStack(this.blockArcher, 1), new Object[] {" X ", "XYX", " X ", 'X', Item.ingotIron, 'Y', Item.bow});
		GameRegistry.addRecipe(new ItemStack(this.blockMage, 1), new Object[] {"   ", " X ", " Y ", 'X', itemMedallion, 'Y', this.blockEMage});

		GameRegistry.addRecipe(new ItemStack(this.blockMerc, 1), new Object[] {" Z ", "XYX", " Z ", 'X', Item.swordWood   , 'Y', this.itemMedallion, 'Z', Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(this.blockMerc, 1), new Object[] {" Z ", "XYX", " Z ", 'X', Item.swordStone  , 'Y', this.itemMedallion, 'Z', Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(this.blockMerc, 1), new Object[] {" Z ", "XYX", " Z ", 'X', Item.swordIron   , 'Y', this.itemMedallion, 'Z', Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(this.blockMerc, 1), new Object[] {" Z ", "XYX", " Z ", 'X', Item.swordGold   , 'Y', this.itemMedallion, 'Z', Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(this.blockMerc, 1), new Object[] {" Z ", "XYX", " Z ", 'X', Item.swordDiamond, 'Y', this.itemMedallion, 'Z', Item.ingotIron});

		GameRegistry.addRecipe(new ItemStack(this.blockArcherM, 1), new Object[] {" Z ", "XYX", " Z ", 'X', Item.bow   , 'Y', this.itemMedallion, 'Z', Item.ingotIron});
	}
	
	/**
	 * Enregistrement des Mobs
	 */
	private void initMobs () {
		this.registerMob(EntityKnight.class , "Knight", "Knight"           , this.knightID , 0x000000);
		this.registerMob(EntityKnight2.class, "Knight2", "Knight - Level 2", this.knight2ID, 0x00FFFC);
		this.registerMob(EntityArcher.class , "Archer", "Archer"           , this.archerID , 0x500000);
	}
	
	/**
	 * Enregistre un mob
	 * @param entityClass
	 * @param name
	 * @param id
	 * @param spawn
	 */
	private void registerMob (Class entityClass, String name, String languageName, int id, int color) {
		
		EntityRegistry.registerGlobalEntityID(entityClass, name, EntityRegistry.findGlobalUniqueEntityId(), 0xFFFFFF, color);
		
		// 30 est traking range : trackingRange The range at which MC will send tracking updates
		// 1 est la frequence : The frequency of tracking updates
		// true est l'envoie de la msie a jour de la velocité : sendsVelocityUpdates Whether to send velocity information packets as well
		EntityRegistry.registerModEntity(entityClass, name, id, this, 30, 1, true);
		
		// Pop dans les biomes
		EntityRegistry.addSpawn(entityClass, 10, 0, 0, EnumCreatureType.creature, new BiomeGenBase[] {
			BiomeGenBase.desert, BiomeGenBase.desertHills,
			BiomeGenBase.extremeHills,
			BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest,
			BiomeGenBase.forestHills, BiomeGenBase.frozenOcean,
			BiomeGenBase.frozenRiver, BiomeGenBase.hell,
			BiomeGenBase.iceMountains, BiomeGenBase.icePlains,
			BiomeGenBase.jungle, BiomeGenBase.jungleHills,
			BiomeGenBase.mushroomIsland,
			BiomeGenBase.mushroomIslandShore, BiomeGenBase.ocean,
			BiomeGenBase.plains, BiomeGenBase.river,
			BiomeGenBase.sky, BiomeGenBase.swampland,
			BiomeGenBase.taiga, BiomeGenBase.taigaHills
		});
		
		// Ajout de la langue
		LanguageRegistry.instance().addStringLocalization("entity."+name+".name", languageName);
		
	}
	
	/**
	 * Renvoie le modID du MOD
	 * @return String
	 */
	public String getModid () {
		String modid = "Error";
		
		for (Annotation annotation : this.getClass().getAnnotations()) {
			if (annotation instanceof Mod) {
				modid = ((Mod)annotation).modid();
			}
		}
		
		return modid;
	}
	
}