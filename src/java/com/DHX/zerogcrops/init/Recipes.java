package com.DHX.zerogcrops.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class Recipes
  {
    public static void init()
      {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.zghoe),"ww "," s "," s ",'s', "stickWood",'w',new ItemStack(ModBlocks.blockZGWater)));
      }
  }
