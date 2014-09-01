package com.DHX.zerogcrops.init;

import com.DHX.zerogcrops.block.*;
import com.DHX.zerogcrops.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModBlocks
  {
    public static Fluid fluidZGWater;
    public static Block blockZGWater;
    private static Fluid flZGWater;
    public static Block blockZGFarmland = new BlockZGFarmland().setBlockName("blockzgfarmland");
    public static Block blockZGCarrot = new BlockZGCarrot().setBlockName("blockzgcarrot").setBlockTextureName("carrots");
      public static Block blockZGPotato = new BlockZGPotato().setBlockName("blockzgpotato").setBlockTextureName("potatoes");
    public static Block blockZGWheat = new BlockZGCrops().setBlockName("crops").setBlockTextureName("wheat");

    public static void init()
      {
        //fluidblocks
        flZGWater = new Fluid("zgwater").setDensity(-1000);
        FluidRegistry.registerFluid(flZGWater);
        fluidZGWater = FluidRegistry.getFluid("zgwater");
        blockZGWater = new BlockZGWater(fluidZGWater, Material.water).setBlockName(String.format(Reference.MOD_ID.toLowerCase() + ":blockzgwater"));
        GameRegistry.registerBlock(blockZGWater, "blockzgwater");
        //blocks
        GameRegistry.registerBlock(blockZGFarmland, "blockzgfarmland");
        GameRegistry.registerBlock(blockZGCarrot, "blockzgcarrot");
          GameRegistry.registerBlock(blockZGPotato, "blockzgpotato");
        GameRegistry.registerBlock(blockZGWheat, "blockzgwheat");
      }
  }
