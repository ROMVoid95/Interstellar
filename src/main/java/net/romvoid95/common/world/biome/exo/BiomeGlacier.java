package net.romvoid95.common.world.biome.exo;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenIceSpike;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.romvoid95.common.world.biome.BiomeSpace;

public class BiomeGlacier extends BiomeSpace implements WorldGenConstants {

	public static final WorldGenBlockBlob COBBLESTONE_BOULDER_FEATURE = new WorldGenBlockBlob(Blocks.COBBLESTONE, 1);
	public static final WorldGenIceSpike  ICE_SPIKE_FEATURE           = new WorldGenIceSpike();

	public final boolean isSpikes;

	public BiomeGlacier(boolean isSpikes) {
		super(getProperties(isSpikes));
		this.isSpikes             = isSpikes;
		this.topBlock             = Blocks.SNOW.getDefaultState();
		this.fillerBlock          = Blocks.ICE.getDefaultState();
		decorator.treesPerChunk   = 1;
		decorator.extraTreeChance = 4;
		this.spawnableCreatureList.clear();

	}

	public static BiomeProperties getProperties (boolean isSpikes) {
		BiomeProperties properties;
		if (isSpikes) {
			properties = new BiomeProperties("Glacier Spikes");
			properties.setBaseBiome("glacier");
		}
		else {
			properties = new BiomeProperties("Glacier");
			properties.setBaseBiome("ice_flats");
		}
		properties.setTemperature(0.0F);
		properties.setRainfall(0.5F);
		properties.setSnowEnabled();
		properties.setBaseHeight(1.5F);
		properties.setHeightVariation(0.5F);
		return properties;
	}

	@Override
	public void decorate (World worldIn, Random rand, BlockPos pos) {
		if (net.minecraftforge.event.terraingen.TerrainGen
				.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.ROCK)) {
			int genChance = rand.nextInt(9);
			if (genChance == 0) {
				int      k6       = rand.nextInt(16) + 8;
				int      l        = rand.nextInt(16) + 8;
				BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
				COBBLESTONE_BOULDER_FEATURE.generate(worldIn, rand, blockpos);
			}
		}
		if (this.isSpikes && net.minecraftforge.event.terraingen.TerrainGen
				.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.ICE)) {
			int genChance = rand.nextInt(3);
			if (genChance == 0) {
				int j = rand.nextInt(16) + 8;
				int k = rand.nextInt(16) + 8;
				ICE_SPIKE_FEATURE.generate(worldIn, rand, worldIn.getHeight(pos.add(j, 0, k)));
			}
		}
		super.decorate(worldIn, rand, pos);
	}

	public WorldGenAbstractTree getRandomTreeFeature (Random rand) {
		return new WorldGenTaiga2(false);
	}
}