package com.DHX.zerogcrops.init;

import com.DHX.zerogcrops.block.BlockZGCrops;
import com.DHX.zerogcrops.block.BlockZGFarmland;
import com.DHX.zerogcrops.block.BlockZGWater;
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
    public static Block blockZGFarmland;
    public static final Block blockZGWheat = new BlockZGCrops().setBlockName("crops").setBlockTextureName("wheat");

    public static void init()
      {
        //fluidblocks
        flZGWater = new Fluid("zgwater").setDensity(- 1000);
        FluidRegistry.registerFluid(flZGWater);
        fluidZGWater = FluidRegistry.getFluid("zgwater");
        blockZGWater = new BlockZGWater(fluidZGWater, Material.water)
            .setBlockName(String.format(Reference.MOD_ID.toLowerCase() + ":blockzgwater"));
        GameRegistry.registerBlock(blockZGWater, "blockzgwater");
        //blocks
        blockZGFarmland = new BlockZGFarmland();
        GameRegistry.registerBlock(blockZGFarmland, "blockzgfarmland");
        GameRegistry.registerBlock(blockZGWheat, "blockzgwheat");
      }
  }
