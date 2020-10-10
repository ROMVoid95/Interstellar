package net.romvoid95.common.world.cave.carver;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import lombok.experimental.UtilityClass;
import net.romvoid95.common.world.cave.util.CaveUtils;

@UtilityClass
public class CarverUtils {

	private static final IBlockState AIR = Blocks.AIR.getDefaultState();
	private static final IBlockState SAND = Blocks.SAND.getDefaultState();
	private static final IBlockState SANDSTONE = Blocks.SANDSTONE.getDefaultState();
	private static final IBlockState REDSANDSTONE = Blocks.RED_SANDSTONE.getDefaultState();
	private static final IBlockState GRAVEL = Blocks.GRAVEL.getDefaultState();
	private static final IBlockState ANDESITE = Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE);
	private static final ImmutableSet<IBlockState> DEBUG_BLOCKS = ImmutableSet.of(Blocks.GOLD_BLOCK.getDefaultState(), Blocks.PLANKS.getDefaultState(), Blocks.COBBLESTONE.getDefaultState(), Blocks.REDSTONE_BLOCK.getDefaultState(), Blocks.EMERALD_BLOCK.getDefaultState(), Blocks.BRICK_BLOCK.getDefaultState());

	/**
	 * Digs out the current block, default implementation removes stone, filler, and top block.
	 * Sets the block to lavaBlockState if y is less then the liquidAltitude in the Config, and air other wise.
	 * If setting to air, it also checks to see if we've broken the surface, and if so,
	 * tries to make the floor the biome's top block.
	 *
	 * @param world the world this block is in
	 * @param primer the ChunkPrimer containing the block
	 * @param blockPos The block's position
	 * @param airBlockState the BlockState to use for air.
	 * @param liquidBlockState the BlockState to use for liquids. May be null if in buffer zone between liquid regions
	 * @param liquidAltitude altitude at and below which air is replaced with liquidBlockState
	 */
	public static void digBlock(World world, ChunkPrimer primer, BlockPos blockPos, IBlockState airBlockState, IBlockState liquidBlockState, int liquidAltitude, boolean replaceGravel) {
		int localX = CaveUtils.getLocal(blockPos.getX());
		int localZ = CaveUtils.getLocal(blockPos.getZ());
		int y = blockPos.getY();

		IBlockState blockState = primer.getBlockState(localX, y, localZ);
		IBlockState blockStateAbove = primer.getBlockState(localX, y + 1, localZ);
		Biome biome = world.getBiome(blockPos);
		Block biomeTopBlock = biome.topBlock.getBlock();
		Block biomeFillerBlock = biome.fillerBlock.getBlock();

		// Only continue if the block is replaceable
		if (canReplaceBlock(blockState, blockStateAbove) || (blockState.getBlock() == biomeTopBlock) || (blockState.getBlock() == biomeFillerBlock)) {
			if ((airBlockState == AIR) && (y <= liquidAltitude)) { // Replace any block below the liquid altitude with the liquid block passed in
				if (liquidBlockState != null) {
					primer.setBlockState(localX, y, localZ, liquidBlockState);
				}
			}
			else {
				// Check for adjacent water blocks to avoid breaking into lakes or oceans
				if ((airBlockState == AIR) && isWaterAdjacent(primer, blockPos)) {
					return;
				}

				// Adjust block below if block removed is biome top block
				if (isTopBlock(world, primer, blockPos) && canReplaceBlock(primer.getBlockState(localX, y - 1, localZ), AIR)) {
					primer.setBlockState(localX, y - 1, localZ, biome.topBlock);
				}

				// Replace floating sand with sandstone
				if (blockStateAbove == SAND) {
					primer.setBlockState(localX, y + 1, localZ, SANDSTONE);
				} else if (blockStateAbove == SAND.withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND)) {
					primer.setBlockState(localX, y + 1, localZ, REDSANDSTONE);
				}

				// Replace floating gravel with andesite, if enabled
				if (replaceGravel && (blockStateAbove == GRAVEL)) {
					primer.setBlockState(localX, y + 1, localZ, ANDESITE);
				}

				// Replace this block with air, effectively "digging" it out
				primer.setBlockState(localX, y, localZ, airBlockState);
			}
		}
	}

	public static void digBlock(World world, ChunkPrimer primer, BlockPos blockPos, IBlockState liquidBlockState, int liquidAltitude, boolean replaceGravel) {
		digBlock(world, primer, blockPos, Blocks.AIR.getDefaultState(), liquidBlockState, liquidAltitude, replaceGravel);
	}

	public static void digBlock(World world, ChunkPrimer primer, int x, int y, int z, IBlockState liquidBlockState, int liquidAltitude, boolean replaceGravel) {
		digBlock(world, primer, new BlockPos(x, y, z), liquidBlockState, liquidAltitude, replaceGravel);
	}

	public static void digBlock(World world, ChunkPrimer primer, int x, int y, int z, IBlockState airBlockState, IBlockState liquidBlockState, int liquidAltitude, boolean replaceGravel) {
		digBlock(world, primer, new BlockPos(x, y, z), airBlockState, liquidBlockState, liquidAltitude, replaceGravel);
	}

	/**
	 * DEBUG method for visualizing cave systems. Used as a replacement for the {@code digBlock} method if the
	 * debugVisualizer config option is enabled.
	 * @param primer Chunk containing the block
	 * @param blockPos block position
	 * @param blockState The blockState to set dug out blocks to
	 */
	public static void debugDigBlock(ChunkPrimer primer, BlockPos blockPos, IBlockState blockState, boolean digBlock) {
		int localX = CaveUtils.getLocal(blockPos.getX());
		int localZ = CaveUtils.getLocal(blockPos.getZ());
		int y = blockPos.getY();

		if (DEBUG_BLOCKS.contains(primer.getBlockState(localX, y, localZ))) {
			return;
		}

		if (digBlock) {
			primer.setBlockState(localX, y, localZ, blockState);
		} else {
			primer.setBlockState(localX, y, localZ, Blocks.AIR.getDefaultState());
		}
	}

	public static void debugDigBlock(ChunkPrimer primer, int x, int y, int z, IBlockState blockState, boolean digBlock) {
		debugDigBlock(primer, new BlockPos(x, y, z), blockState, digBlock);
	}

	/**
	 * Determine if the block at the specified location is the designated top block for the biome.
	 *
	 * @param world the Minecraft world this block is in
	 * @param primer the ChunkPrimer containing the block
	 * @param blockPos The block's position
	 * @return true if this block is the same type as the biome's designated top block
	 */
	public static boolean isTopBlock(World world, ChunkPrimer primer, BlockPos blockPos) {
		int localX = CaveUtils.getLocal(blockPos.getX());
		int localZ = CaveUtils.getLocal(blockPos.getZ());
		int y = blockPos.getY();
		Biome biome = world.getBiome(blockPos);
		IBlockState blockState = primer.getBlockState(localX, y, localZ);
		return blockState == biome.topBlock;
	}

	/**
	 * Determines if the Block of a given IBlockState is suitable to be replaced during cave generation.
	 * Basically returns true for most common worldgen blocks (e.g. stone, dirt, sand), false if the block is air.
	 *
	 * @param blockState the block's IBlockState
	 * @param blockStateAbove the IBlockState of the block above this one
	 * @return true if the blockState can be replaced
	 */
	public static boolean canReplaceBlock(IBlockState blockState, IBlockState blockStateAbove) {
		Block block = blockState.getBlock();

		// Avoid damaging trees
		if ((block == Blocks.LEAVES)
				|| (block == Blocks.LEAVES2)
				|| (block == Blocks.LOG)
				|| (block == Blocks.LOG2)) {
			return false;
		}

		// Avoid digging out under trees
		if ((blockStateAbove == Blocks.LOG.getDefaultState())
				|| (blockStateAbove == Blocks.LOG2.getDefaultState())) {
			return false;
		}

		// Don't mine bedrock
		if (blockState == Blocks.BEDROCK.getDefaultState()) {
			return false;
		}

		// Accept stone-like blocks added from other mods
		if (blockState.getMaterial() == Material.ROCK) {
			return true;
		}

		// Mine-able blocks
		if ((block == Blocks.STONE)
				|| (block == Blocks.DIRT)
				|| (block == Blocks.GRASS)
				|| (block == Blocks.HARDENED_CLAY)
				|| (block == Blocks.STAINED_HARDENED_CLAY)
				|| (block == Blocks.SANDSTONE)
				|| (block == Blocks.RED_SANDSTONE)
				|| (block == Blocks.MYCELIUM)
				|| (block  == Blocks.SNOW_LAYER)) {
			return true;
		}

		// Only accept gravel and sand if water is not directly above it
		return ((block == Blocks.SAND) || (block == Blocks.GRAVEL))
				&& (blockStateAbove.getMaterial() != Material.WATER);
	}

	private static boolean isWaterAdjacent(ChunkPrimer primer, BlockPos blockPos) {
		int localX = CaveUtils.getLocal(blockPos.getX());
		int localZ = CaveUtils.getLocal(blockPos.getZ());
		int y = blockPos.getY();

		return (primer.getBlockState(localX, y + 1, localZ).getMaterial() == Material.WATER)
				|| ((localX < 15) && (primer.getBlockState(localX + 1, y, localZ).getMaterial() == Material.WATER))
				|| ((localX > 0) && (primer.getBlockState(localX - 1, y, localZ).getMaterial() == Material.WATER))
				|| ((localZ < 15) && (primer.getBlockState(localX, y, localZ + 1).getMaterial() == Material.WATER))
				|| ((localZ > 0) && (primer.getBlockState(localX, y, localZ - 1).getMaterial() == Material.WATER));
	}
}
