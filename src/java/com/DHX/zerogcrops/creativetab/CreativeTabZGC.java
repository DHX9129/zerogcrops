package com.DHX.zerogcrops.creativetab;

import com.DHX.zerogcrops.init.ModItems;
import com.DHX.zerogcrops.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabZGC
  {
    public static final CreativeTabs ZGC_Tab = new CreativeTabs(Reference.MOD_ID.toLowerCase())
    {
      @Override
      public Item getTabIconItem()
        {
          return ModItems.zghoe;
        }
    };
  }
