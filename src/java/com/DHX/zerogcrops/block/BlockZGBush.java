package com.DHX.zerogcrops.block;

import java.util.Random;
import com.DHX.zerogcrops.creativetab.CreativeTabZGC;
import com.DHX.zerogcrops.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import static net.minecraftforge.common.EnumPlantType.*;

public class BlockZGBush extends BlockZGC implements IPlantable
  {
    protected BlockZGBush(Material material)
      {
        super(material);
        this.setTickRandomly(true);
        float f = 0.2F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
      }

    protected BlockZGBush()
      {
        this(Material.plants);
      }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
      {
        return super.canPlaceBlockAt(world, x, y, z) && this.canBlockStay(world, x, y, z);
      }

    /**
     * is the block grass, dirt or farmland
     */
    protected boolean canPlaceBlockOn(Block hostblock)
      {
        return hostblock == Blocks.grass || hostblock == Blocks.dirt || hostblock == ModBlocks.blockZGFarmland;
      }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborblock)
      {
        super.onNeighborBlockChange(world, x, y, z, neighborblock);
        this.checkAndDropBlock(world, x, y, z);
      }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random random)
      {
        this.checkAndDropBlock(world, x, y, z);
      }

    /**
     * checks if the block can stay, if not drop as item
     */
    protected void checkAndDropBlock(World world, int x, int y, int z)
      {
        if (!this.canBlockStay(world, x, y, z))
          {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, getBlockById(0), 0, 2);
          }
      }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
      {
        return  world.getBlock(x, y + 1, z).canSustainPlant(world, x, y + 1, z, ForgeDirection.DOWN, this);
      }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
      {
        return null;
      }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube()
      {
        return false;
      }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock()
      {
        return false;
      }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType()
      {
        return 1;
      }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
      {
        if (this == ModBlocks.blockZGWheat)          return Crop;
        if (this == ModBlocks.blockZGCarrot)        return Crop;
        if (this == Blocks.potatoes)       return Crop;
        if (this == Blocks.melon_stem)     return Crop;
        if (this == Blocks.pumpkin_stem)   return Crop;
        //if (this == Blocks.deadbush)       return Desert;
        if (this == Blocks.waterlily)      return Water;
        //if (this == Blocks.red_mushroom)   return Cave;
        //if (this == Blocks.brown_mushroom) return Cave;
        if (this == Blocks.nether_wart)    return Nether;
        if (this == Blocks.sapling)        return Plains;
        //if (this == Blocks.tallgrass)      return Plains;
        //if (this == Blocks.double_plant)   return Plains;
        //if (this == Blocks.red_flower)     return Plains;
        //if (this == Blocks.yellow_flower)  return Plains;
        return Plains;
      }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z)
      {
        return this;
      }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
      {
        return world.getBlockMetadata(x, y, z);
      }
  }
