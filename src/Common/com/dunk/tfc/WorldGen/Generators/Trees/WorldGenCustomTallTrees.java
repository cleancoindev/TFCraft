package com.dunk.tfc.WorldGen.Generators.Trees;

import java.util.Random;

import com.dunk.tfc.Blocks.Flora.BlockBranch;
import com.dunk.tfc.Blocks.Vanilla.BlockCustomLeaves;
import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.WorldGen.Generators.WorldGenForests;
import com.dunk.tfc.api.TFCBlocks;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCustomTallTrees extends WorldGenTreeBase
{

	public WorldGenCustomTallTrees(boolean flag, int id, boolean sap)
	{
		super(flag, id, sap);
	}

	protected boolean generateRandomBranches(World world, Random rand, int xCoord, int yCoord, int zCoord,
			int[] currentDirection, int numBranches, int remainingDistance)
	{
		if (remainingDistance < 1)
		{
			return true;
		}
		boolean[] validDirections = new boolean[] { false, false, false, false, false, false, false, false, false,
				false, false, false, false, false, false, false };

		int numValidDirections = 0;

		if (currentDirection[0] != 1)
		{
			if (currentDirection[2] != 1)
			{
				validDirections[0] = true;
				numValidDirections++;
				validDirections[0 + 8] = true;
				numValidDirections++;
			}
			validDirections[1] = true;
			numValidDirections++;
			validDirections[1 + 8] = true;
			numValidDirections++;
			if (currentDirection[2] != -1)
			{
				validDirections[2] = true;
				numValidDirections++;
				validDirections[2 + 8] = true;
				numValidDirections++;
			}
		}
		if (currentDirection[2] != 1)
		{
			if (currentDirection[0] != -1)
			{
				validDirections[3] = true;
				numValidDirections++;
				validDirections[3 + 8] = true;
				numValidDirections++;
			}
			validDirections[4] = true;
			numValidDirections++;
			validDirections[4 + 8] = true;
			numValidDirections++;
		}
		if (currentDirection[2] != -1)
		{
			if (currentDirection[0] != -1)
			{
				validDirections[5] = true;
				numValidDirections++;
				validDirections[5 + 8] = true;
				numValidDirections++;
			}
			validDirections[6] = true;
			numValidDirections++;
			validDirections[6 + 8] = true;
			numValidDirections++;
		}
		if (currentDirection[0] != -1)
		{
			validDirections[7] = true;
			numValidDirections++;
			validDirections[7 + 8] = true;
			numValidDirections++;
		}
		numBranches = Math.min(numBranches, numValidDirections);
		boolean placedBranch = false;
		int[] curDir = null;
		Block theBranch = null;
		for (int i = 0; i < numBranches; i++)
		{
			int currentRemainingDistance = remainingDistance;
			if (numValidDirections == 0)
			{
				break;
			}
			curDir = null;

			boolean ignoreBranching = false;
			if (currentRemainingDistance > 1)
			{
				// 50/50 chance whether we keep going straight or start
				// branching.
				ignoreBranching = rand.nextBoolean();
			}

			if (!ignoreBranching)
			{
				int index = rand.nextInt(directions.length);
				while (!validDirections[index])
				{
					index = rand.nextInt(directions.length);
				}
				validDirections[index] = false;
				numValidDirections--;
				curDir = directions[index];
			}
			else
			{
				curDir = currentDirection;
			}

			if (curDir[0] * curDir[2] != 0 || curDir[0] * curDir[1] != 0 || curDir[1] * curDir[2] != 0)
			{
				if (shouldSubtractDistance(rand))
				{
					currentRemainingDistance--;
				}
			}
			if (world.getBlock(xCoord + curDir[0], yCoord + curDir[1], zCoord + curDir[2]).isReplaceable(world,
					xCoord + curDir[0], yCoord + curDir[1], zCoord + curDir[2]) || world.getBlock(xCoord + curDir[0],
							yCoord + curDir[1], zCoord + curDir[2]) instanceof BlockCustomLeaves)
			{
				theBranch = null;
				if (curDir[1] == 1)
				{
					if (curDir[0] == -1)
					{
						if (curDir[2] == -1)
						{
							theBranch = currentRemainingDistance > 1 ? TFCBlocks.branch_XyZ : TFCBlocks.branchEnd_XyZ;
						}
						else if (curDir[2] == 0)
						{
							theBranch = currentRemainingDistance > 1 ? TFCBlocks.branch_Xy_ : TFCBlocks.branchEnd_Xy_;
						}
						else
						{
							theBranch = currentRemainingDistance > 1 ? TFCBlocks.branch_Xyz : TFCBlocks.branchEnd_Xyz;
						}
					}
					else if (curDir[0] == 0)
					{
						if (curDir[2] == -1)
						{
							theBranch = currentRemainingDistance > 1 ? TFCBlocks.branch__yZ : TFCBlocks.branchEnd__yZ;
						}
						else
						{
							theBranch = currentRemainingDistance > 1 ? TFCBlocks.branch__yz : TFCBlocks.branchEnd__yz;
						}
					}
					else
					{
						if (curDir[2] == -1)
						{
							theBranch = currentRemainingDistance > 1 ? TFCBlocks.branch_xyZ : TFCBlocks.branchEnd_xyZ;
						}
						else if (curDir[2] == 0)
						{
							theBranch = currentRemainingDistance > 1 ? TFCBlocks.branch_xy_ : TFCBlocks.branchEnd_xy_;
						}
						else
						{
							theBranch = currentRemainingDistance > 1 ? TFCBlocks.branch_xyz : TFCBlocks.branchEnd_xyz;
						}
					}
				}
				else if (curDir[1] == 0)
				{
					if (curDir[0] == -1)
					{
						if (curDir[2] == -1)
						{
							theBranch = currentRemainingDistance > 1 ? TFCBlocks.branch_X_Z : TFCBlocks.branchEnd_X_Z;
						}
						else if (curDir[2] == 0)
						{
							theBranch = currentRemainingDistance > 1 ? TFCBlocks.branch_X__ : TFCBlocks.branchEnd_X__;
						}
						else
						{
							theBranch = currentRemainingDistance > 1 ? TFCBlocks.branch_X_z : TFCBlocks.branchEnd_X_z;
						}
					}
					else if (curDir[0] == 0)
					{
						if (curDir[2] == -1)
						{
							theBranch = currentRemainingDistance > 1 ? TFCBlocks.branch___Z : TFCBlocks.branchEnd___Z;
						}
						else
						{
							theBranch = currentRemainingDistance > 1 ? TFCBlocks.branch___z : TFCBlocks.branchEnd___z;
						}
					}
					else
					{
						if (curDir[2] == -1)
						{
							theBranch = currentRemainingDistance > 1 ? TFCBlocks.branch_x_Z : TFCBlocks.branchEnd_x_Z;
						}
						else if (curDir[2] == 0)
						{
							theBranch = currentRemainingDistance > 1 ? TFCBlocks.branch_x__ : TFCBlocks.branchEnd_x__;
						}
						else
						{
							theBranch = currentRemainingDistance > 1 ? TFCBlocks.branch_x_z : TFCBlocks.branchEnd_x_z;
						}
					}
				}
				if (block2)
				{
					theBranch = TFC_Core.getSecondBranch(theBranch);
				}
				// If the branch directly below is the same, it'll look ugly, so
				// skip it.
				if (world.getBlock(xCoord + curDir[0], yCoord - 1 + curDir[1],
						zCoord + curDir[2]) == theBranch && !ignoreBranching)
				{
					i--;
					continue;
				}
				// We only want to place this branch here if this branch can
				// continue.
				if (generateRandomBranches(world, rand, xCoord + curDir[0], yCoord + curDir[1], zCoord + curDir[2],
						curDir, 3, currentRemainingDistance - 1))
				{
					world.setBlock(xCoord + curDir[0], yCoord + curDir[1], zCoord + curDir[2], theBranch, treeId, 2);
					if (((BlockBranch) theBranch).isEnd())
					{
						placeLeaves(world, rand, xCoord + curDir[0], yCoord + curDir[1], zCoord + curDir[2]);
					}
					placedBranch = true;
				}
			}
		}
		return placedBranch;
	}

	public boolean placeLeaves(World world, Random random, int xCoord, int yCoord, int zCoord)
	{
		int radius = 3;
		boolean lost = BlockBranch.shouldLoseLeaf(world, xCoord, yCoord, zCoord, random, block2);
		boolean defLost = BlockBranch.shouldDefinitelyLoseLeaf(world, xCoord, yCoord, zCoord, block2);
		for (int i = -(radius + 1); i <= (radius + 1); i++)
		{
			for (int j = 0; j <= (radius + 1); j++)
			{
				for (int k = -(radius + 1); k <= (radius + 1); k++)
				{
					if (i * i + j * j + k * k < radius * radius && world.getBlock(xCoord + i, yCoord + j, zCoord + k).isReplaceable(world, xCoord + i, yCoord + j,
							zCoord + k) &&  !defLost && (!lost || random
									.nextInt(3) == 0))
					{
						world.setBlock(xCoord + i, yCoord + j, zCoord + k, leafBlock, treeId, 2);
						if (!fromSapling && !conversionMatrix[16 + (xCoord + i) - tempSourceX][16 + (zCoord + k) - tempSourceZ])
						{
							convertGrassToDirt(world, xCoord + i, yCoord + j, zCoord + k, height);
							conversionMatrix[16 + (xCoord + i) - tempSourceX][16 + (zCoord + k) - tempSourceZ] = true;
						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean generate(World world, Random random, int xCoord, int yCoord, int zCoord)
	{
		int distFromEdge = 4;
		// We don't want to generate too close to chunk boundaries, because that
		// gets out of hand
		if ((xCoord % 16 > 16 - distFromEdge || Math.abs(
				xCoord) % 16 < distFromEdge || zCoord % 16 > 16 - distFromEdge || Math.abs(zCoord) % 16 < distFromEdge)&&!fromSapling)
		{
			return false;
		}
		height = random.nextInt(5) + 6;
		this.tempSourceX = xCoord;
		this.tempSourceZ = zCoord;

		conversionMatrix = new boolean[32][32];

		if (yCoord < 1 || yCoord + height + 1 > world.getHeight())
		{
			return false;
		}

		boolean flag = true;
		for (int y = yCoord; y <= yCoord + 1 + height; y++)
		{
			byte byte0 = 1;
			if (y == yCoord)
			{
				byte0 = 0;
			}
			if (y >= yCoord + 1 + height - 2)
			{
				byte0 = 2;
			}
			Block j3;
			for (int x = xCoord - byte0; x <= xCoord + byte0 && flag; x++)
			{
				for (int z = zCoord - byte0; z <= zCoord + byte0 && flag; z++)
				{
					if (y >= 0 && y + height < world.getHeight())
					{
						j3 = world.getBlock(x, y, z);
						if (!j3.isAir(world, x, y, z) && !j3.canBeReplacedByLeaves(world, x, y, z) && !(j3 instanceof BlockCustomLeaves))
							flag = false;
					}
					else
					{
						flag = false;
					}
				}
			}
		}

		if (!flag)
			return false;

		if (!(TFC_Core.isSoil(world.getBlock(xCoord, yCoord - 1, zCoord))) || yCoord >= world.getHeight() - height - 1)
			return false;

		if (block2)
		{
			for (int l1 = 0; l1 < height; l1++)
			{
				setBlockAndNotifyAdequately(world, xCoord, yCoord + l1, zCoord, TFCBlocks.branch2__y_, treeId);				
			}
		}
		else
		{
			for (int l1 = 0; l1 < height; l1++)
			{
				setBlockAndNotifyAdequately(world, xCoord, yCoord + l1, zCoord, TFCBlocks.branch__y_, treeId);
			}
		}

		int extraBranchLength = 3;
		boolean hasBranches = false;
		
		hasBranches |= generateRandomBranches(world, random, xCoord, yCoord + height - 1 - random.nextInt(3), zCoord,
				new int[] { -1, 1, -1 }, 1, random.nextInt(3) + extraBranchLength);
		hasBranches |= generateRandomBranches(world, random, xCoord, yCoord + height - 1 - random.nextInt(3), zCoord,
				new int[] { 1, 1, -1 }, 1, random.nextInt(3) + extraBranchLength);
		hasBranches |= generateRandomBranches(world, random, xCoord, yCoord + height - 1 - random.nextInt(3), zCoord,
				new int[] { 1, 1, 1 }, 1, random.nextInt(3) + extraBranchLength);
		hasBranches |= generateRandomBranches(world, random, xCoord, yCoord + height - 1 - random.nextInt(3), zCoord,
				new int[] { -1, 1, 1 }, 1, random.nextInt(3) + extraBranchLength);

		for (int l1 = 0; l1 < height; l1++)
		{
			if (!hasBranches)
			{
				world.setBlockToAir(xCoord, yCoord + l1, zCoord);
			}
			else
			{
				tryDoMoss(world, random, xCoord, yCoord + l1, zCoord);
			}
		}
		
		return true;
	}
}
