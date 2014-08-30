package com.DHX.zerogcrops.client.handlers;

import com.DHX.zerogcrops.client.settings.KeyBindings;
import com.DHX.zerogcrops.reference.Key;
import com.DHX.zerogcrops.utility.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;


public class KeyInputEventHandler
  {
    private static Key getPressedKeybinding()
      {
        if (KeyBindings.charge.isPressed())
          {
            return Key.CHARGE;
          }
        else if (KeyBindings.release.isPressed())
          {
            return Key.RELEASE;
          }
        return Key.UNKNOWN;
      }
    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
      {
        LogHelper.info(getPressedKeybinding());
      }
  }
