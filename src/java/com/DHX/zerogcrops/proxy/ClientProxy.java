package com.DHX.zerogcrops.proxy;

import com.DHX.zerogcrops.client.settings.KeyBindings;
import com.DHX.zerogcrops.renderers.ModRenderers;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
  {
    @Override
    public void registerKeyBindings()
      {
        ClientRegistry.registerKeyBinding(KeyBindings.charge);
        ClientRegistry.registerKeyBinding(KeyBindings.release);
      }

      @Override
      public int getRenderType()
      {
          return ModRenderers.rendererCrop;
      }
      @Override
      public void registerRenderers()
      {
          ModRenderers.registerAll();
      }

  }
