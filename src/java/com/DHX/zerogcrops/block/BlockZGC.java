package com.DHX.zerogcrops.block;

import com.DHX.zerogcrops.creativetab.CreativeTabZGC;
import com.DHX.zerogcrops.init.ModBlocks;
import com.DHX.zerogcrops.reference.Reference;
import com.DHX.zerogcrops.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;

public class BlockZGC extends Block
  {
    public BlockZGC(Material material)
      {
        super(material);
        this.setCreativeTab(CreativeTabZGC.ZGC_Tab);
      }

    public BlockZGC()
      {
        this(Material.rock);
      }

    @Override
    public String getUnlocalizedName()
      {
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":",
            getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
      }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
      {
        blockIcon =
            iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
      }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
      {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
      }

    @Override
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
           /* case Beach:
              boolean isBeach = this == Blocks.grass || this == Blocks.dirt || this == Blocks.sand;
              boolean hasWater = (world.getBlock(x - 1, y, z    ).getMaterial() == Material.water ||
                  world.getBlock(x + 1, y, z    ).getMaterial() == Material.water ||
                  world.getBlock(x,     y, z - 1).getMaterial() == Material.water ||
                  world.getBlock(x,     y, z + 1).getMaterial() == Material.water);
              return isBeach && hasWater;*/
          }

        return false;
      }
  }
