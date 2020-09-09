package net.romvoid95.common.world.biome.exo;

import java.util.Random;

import net.minecraft.init.Biomes;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.romvoid95.common.world.biome.BiomeSpace;
import net.romvoid95.common.world.biome.features.TraverseTreeGenerator;

public class BiomeAutumnalWoodedHills extends BiomeSpace implements WorldGenConstants {

	public static BiomeProperties properties = new BiomeProperties("Autumnal Wooded Hills");

	public BiomeAutumnalWoodedHills() {
		super(properties);
		decorator.treesPerChunk   = 10;
		decorator.flowersPerChunk = 4;
		decorator.grassPerChunk   = 6;
		properties.setTemperature(Biomes.FOREST.getDefaultTemperature());
		properties.setRainfall(Biomes.FOREST.getRainfall());
		properties.setBaseHeight(Biomes.EXTREME_HILLS.getBaseHeight());
		properties.setHeightVariation(Biomes.EXTREME_HILLS.getHeightVariation());
	}

	@Override
	public int getModdedBiomeGrassColor (int original) {
		return super.getModdedBiomeGrassColor(0xFFD6C23D);
	}

	@Override
	public int getSkyColorByTemp (float currentTemperature) {
		//        if (TraverseConfig.disableCustomSkies)
		//            return super.getSkyColorByTemp(currentTemperature);
		//        else
		return 0xFFEFECD9;
	}

	@Override
	public int getModdedBiomeFoliageColor (int original) {
		return super.getModdedBiomeFoliageColor(0xFFD2D31F);
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature (Random rand) {
		int num = rand.nextInt(5);
		if (num == 0) {
			return new TraverseTreeGenerator(true, 4, OAK_LOG, RED_AUTUMNAL_LEAVES);
		}
		else if (num == 1) {
			return new TraverseTreeGenerator(true, 4, OAK_LOG, BROWN_AUTUMNAL_LEAVES);
		}
		else if (num == 2) {
			return new TraverseTreeGenerator(true, 4, OAK_LOG, ORANGE_AUTUMNAL_LEAVES);
		}
		else if (num == 3) {
			return new TraverseTreeGenerator(true, 4, OAK_LOG, YELLOW_AUTUMNAL_LEAVES);
		}
		else {
			return TREE_FEATURE;
		}
	}
}
