package com.piron1991.prospectors;

import com.piron1991.prospectors.handler.ConfigHandler;
import com.piron1991.prospectors.init.InitItems;
import com.piron1991.prospectors.init.InitRecipe;
import com.piron1991.prospectors.reference.Reference;
import com.piron1991.prospectors.utilities.BlockDataHolder;
import com.piron1991.prospectors.utilities.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)


public class Prospectors {

    @Mod.Instance(Reference.MOD_ID)
    public static Prospectors instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.preinit(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigHandler());

        InitItems.init();

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        InitRecipe.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        ConfigHandler.postInit();
    if (ConfigHandler.debugMode){
        for(BlockDataHolder ore:ConfigHandler.oreArray){
            LogHelper.info(ore.toString());
        }
    }
    }

}
