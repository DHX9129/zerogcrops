package com.DHX.zerogcrops.block;

import com.DHX.zerogcrops.creativetab.CreativeTabZGC;
import com.DHX.zerogcrops.init.ModBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BlockZGFarmland extends BlockZGC
  {
    @SideOnly(Side.CLIENT)
    private IIcon wettex;
    @SideOnly(Side.CLIENT)
    private IIcon drytex;

    public BlockZGFarmland()
      {
        super(Material.ground);
        this.setTickRandomly(true);
        this.setBlockBounds(0.0F, 0.0625F, 0.0F, 1.0F, 1.0F, 1.0F);
        this.setLightOpacity(255);
        this.setHardness(0.6F);
        this.setStepSound(Block.soundTypeGravel);
        this.setBlockTextureName("farmland");
      }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
      {
        return AxisAlignedBB
            .getBoundingBox((double) x, (double) y, (double) z, (double) (x + 1), (double) (y + 1), (double) (z + 1));
      }

    public boolean isOpaqueCube()
      {
        return false;
      }

    public boolean renderAsNormalBlock()
      {
        return false;
      }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
      {
        return side == 0 ? (meta > 0 ? this.wettex : this.drytex) : Blocks.dirt.getBlockTextureFromSide(meta);
      }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
      {
        super.onNeighborBlockChange(world, x, y, z, block);
        if(world.getBlock(x, y - 1, z).getMaterial().isSolid())
          {
            world.setBlock(x, y, z, Blocks.dirt);
          }
      }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random)
      {
        if(! this.saturation(world, x, y, z) && ! world.canLightningStrikeAt(x, y + 1, z))
          {
            int l = world.getBlockMetadata(x, y, z);

            if(l > 0)
              {
                world.setBlockMetadataWithNotify(x, y, z, l - 1, 2);
              } else if(! this.vegitation(world, x, y, z))
              {
                world.setBlock(x, y, z, Blocks.dirt);
              }
          } else
          {
            world.setBlockMetadataWithNotify(x, y, z, 7, 2);
          }
      }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
      {
        return Blocks.dirt.getItemDropped(0, p_149650_2_, p_149650_3_);
      }

    private boolean saturation(World world, int x, int y, int z)
      {
        for(int l = x - 4; l <= x + 4; ++ l)
          {
            for(int i1 = y - 1; i1 <= y; ++ i1)
              {
                for(int j1 = z - 4; j1 <= z + 4; ++ j1)
                  {
                    if(world.getBlock(l, i1, j1).getMaterial() == Material.water)
                      {
                        return true;
                      }
                  }
              }
          }

        return false;
      }

    private boolean vegitation(World world, int x, int y, int z)
      {
        byte b0 = 0;

        for(int l = x - b0; l <= x + b0; ++ l)
          {
            for(int i1 = z - b0; i1 <= z + b0; ++ i1)
              {
                Block block = world.getBlock(x, y - 1, z);
                if(block instanceof IPlantable &&
                    this.canSustainPlant(world, x, y, z, ForgeDirection.DOWN, (IPlantable) block))
                  {
                    return true;
                  }
              }
          }
        return false;
      }

    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z)
      {
        return Item.getItemFromBlock(Blocks.dirt);
      }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
      {
        this.wettex = iconRegister.registerIcon(this.getTextureName() + "_wet");
        this.drytex = iconRegister.registerIcon(this.getTextureName() + "_dry");
      }
/*    @Override
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
      {
        Block plant = plantable.getPlant(world, x, y - 1, z);
        EnumPlantType plantType = plantable.getPlantType(world, x, y - 1, z);

        if (plant == Blocks.cactus && this == Blocks.cactus)
          {
            return true;
          }

        if (plant == Blocks.reeds && this == Blocks.reeds)
          {
            return true;
          }

        if (plantable instanceof BlockZGBush && ((BlockZGBush)plantable).canPlaceBlockOn(this))
          {
            return true;
          }

        switch (plantType)
          {
            //case Desert: return this == Blocks.sand;
            case Nether: return this == Blocks.soul_sand;
            case Crop:   return this == ModBlocks.blockZGFarmland;
            case Cave:   return isSideSolid(world, x, y, z, ForgeDirection.DOWN);
            //case Plains: return this == Blocks.grass || this == Blocks.dirt || this == Blocks.farmland;
            case Water:  return world.getBlock(x, y, z).getMaterial() == Material.water && world.getBlockMetadata(x, y, z) == 0;
           *//* case Beach:
              boolean isBeach = this == Blocks.grass || this == Blocks.dirt || this == Blocks.sand;
              boolean hasWater = (world.getBlock(x - 1, y, z    ).getMaterial() == Material.water ||
                  world.getBlock(x + 1, y, z    ).getMaterial() == Material.water ||
                  world.getBlock(x,     y, z - 1).getMaterial() == Material.water ||
                  world.getBlock(x,     y, z + 1).getMaterial() == Material.water);
              return isBeach && hasWater;*//*
          }

        return false;
      }*/
  }
