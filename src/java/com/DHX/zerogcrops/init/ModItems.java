package com.DHX.zerogcrops.init;

import com.DHX.zerogcrops.item.ZGHoe;
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

    public static void init()
      {
        GameRegistry.registerItem(zghoe, "zghoe");
        GameRegistry.registerItem(zgseeds,"zgseeds");
      }
  }
