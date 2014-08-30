package com.DHX.zerogcrops.item;


import com.DHX.zerogcrops.creativetab.CreativeTabZGC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class ZGSeeds extends ItemZGC implements IPlantable
  {
    private Block plantBlock;
    private Block soilBlockID;

    public ZGSeeds(Block CropBlock, Block soilBlock)
      {
        this.plantBlock = CropBlock;
        this.soilBlockID = soilBlock;
        this.setCreativeTab(CreativeTabZGC.ZGC_Tab);
        this.setUnlocalizedName("zgseeds");
      }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x,
                             int y, int z, int side, float p_77648_8_, float p_77648_9_,
                             float p_77648_10_)
      {
        if(side != 0)
          {
            return false;
          } else if(player.canPlayerEdit(x, y, z, side, itemStack) &&
            player.canPlayerEdit(x, y - 1, z, side, itemStack))
          {
            if(world.getBlock(x, y, z)
                .canSustainPlant(world, x, y, z, ForgeDirection.DOWN, this) &&
                world.isAirBlock(x, y - 1, z))
              {
                world.setBlock(x, y - 1, z, this.plantBlock);
                -- itemStack.stackSize;
                return true;
              } else
              {
                return false;
              }
          } else
          {
            return false;
          }
      }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
      {
        return plantBlock == Blocks.nether_wart ? EnumPlantType.Nether : EnumPlantType.Crop;
      }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z)
      {
        return plantBlock;
      }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
      {
        return 0;
      }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
      {
        itemIcon = iconRegister.registerIcon("minecraft:seeds_wheat");

      }
  }
