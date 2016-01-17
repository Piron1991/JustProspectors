package com.piron1991.prospectors.handler;

import com.piron1991.prospectors.reference.Reference;
import com.piron1991.prospectors.utilities.BlockDataHolder;
import com.piron1991.prospectors.utilities.LogHelper;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;

import java.io.File;
import java.util.*;

public class ConfigHandler {

    public static Configuration config;

    public static String[] oreList;
    public static HashSet<BlockDataHolder> oreArray = new HashSet<BlockDataHolder>(30);
    public static boolean debugMode;
    public static int xz_size;
    public static int y_size;


    public static void preinit(File configFile) {

        if (config == null) {
            config = new Configuration(configFile);}
        loadConfig();


    }


    @SubscribeEvent
    public void OnConfigChangeEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals(Reference.MOD_ID)) {
            loadConfig();
        }

    }

    private static void loadConfig() {
        oreList = config.get("oreList","Oredict names for propecting.Put names below from least to most valuable ore",Reference.oreList).getStringList();
        debugMode = config.get("Debug mode","Debug",false).getBoolean();
        xz_size=config.get("Tool ranges","XZ_size:",3).getInt();
        y_size=config.get("Tool ranges","Y_height",5).getInt();

        if (config.hasChanged()) {
            config.save();
        }

    }

    public static void postInit(){
        setScannableOres();
    }

    private static void setScannableOres(){
        ArrayList<ItemStack> stacks;
        Integer value = 1;

        for (String ore:oreList) {

            stacks = OreDictionary.getOres(ore);

            for (ItemStack stack :stacks) {

                Block block = Block.getBlockFromItem(stack.getItem());
                int meta = stack.getItemDamage();
                BlockDataHolder data = new BlockDataHolder(ore,block,meta,value);

                if (!oreArray.contains(data)){
                    oreArray.add(data);
                }
            }
            value++;
        }
    }

}

