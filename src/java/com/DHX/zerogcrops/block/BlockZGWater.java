package com.DHX.zerogcrops.block;

import com.DHX.zerogcrops.creativetab.CreativeTabZGC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockZGWater extends BlockFluidClassic
  {
    @SideOnly(Side.CLIENT)
    protected IIcon stillIcon;
    @SideOnly(Side.CLIENT)
    protected IIcon flowingIcon;

    public BlockZGWater(Fluid fluid, Material material)
      {
        super(fluid, material);
        this.setBlockName("blockzgwater");
        this.setCreativeTab(CreativeTabZGC.ZGC_Tab);
      }

    @Override
    public IIcon getIcon(int side, int meta)
      {
        return (side == 0 || side == 1) ? stillIcon : flowingIcon;
      }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register)
      {
        stillIcon = register.registerIcon("minecraft:water_still");
        flowingIcon = register.registerIcon("minecraft:water_flow");
      }

    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z)
      {
        if(world.getBlock(x, y, z).getMaterial().isLiquid())
          {
            return false;
          }
        return super.canDisplace(world, x, y, z);
      }

    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z)
      {
        if(world.getBlock(x, y, z).getMaterial().isLiquid())
          {
            return false;
          }
        return super.displaceIfPossible(world, x, y, z);
      }

  }
