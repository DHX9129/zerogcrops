package com.DHX.zerogcrops.renderers;

import cpw.mods.fml.client.registry.RenderingRegistry;

public final class ModRenderers
{
    public static int rendererCrop;

    public static void registerAll() {
        RenderCrop renderCrop = new RenderCrop();
        rendererCrop = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(rendererCrop, renderCrop);
    }
}
