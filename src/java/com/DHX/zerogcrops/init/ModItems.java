package com.DHX.zerogcrops.init;

import com.DHX.zerogcrops.item.ZGHoe;
import com.DHX.zerogcrops.item.ZGSeedFood;
import com.DHX.zerogcrops.item.ZGSeeds;
import com.DHX.zerogcrops.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
  {
    public static final Item zghoe = new ZGHoe(Item.ToolMaterial.EMERALD);
    public static final Item zgseeds = new ZGSeeds(ModBlocks.blockZGWheat,ModBlocks.blockZGFarmland);
    public static final Item zgcarrot = new ZGSeedFood(4, 0.6F, ModBlocks.blockZGCarrot, ModBlocks.blockZGFarmland).setUnlocalizedName("carrot").setTextureName("minecraft:carrot");
    public static final Item zgpotato = new ZGSeedFood(1, 0.3F, ModBlocks.blockZGPotato, ModBlocks.blockZGFarmland).setUnlocalizedName("potato").setTextureName("minecraft:potato");
    public static void init()
      {
        GameRegistry.registerItem(zghoe, "zghoe");
        GameRegistry.registerItem(zgseeds,"zgseeds");
        GameRegistry.registerItem(zgcarrot,"zgcarrot");
        GameRegistry.registerItem(zgpotato,"zgpotato");
      }
  }
