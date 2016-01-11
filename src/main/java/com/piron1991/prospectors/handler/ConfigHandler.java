package com.piron1991.prospectors.handler;

import com.piron1991.prospectors.reference.Reference;
import com.piron1991.prospectors.utilities.LogHelper;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;

import java.io.File;
import java.util.ArrayList;

public class ConfigHandler {

    public static Configuration config;

    private static String[] oreList;
    public static ArrayList<Block> blocks = new ArrayList<Block>(30);

    public static void preinit(File configFile) {

        if (config == null) {
            config = new Configuration(configFile);}
        loadConfig();
        setScannableOres();
    }


    @SubscribeEvent
    public void OnConfigChangeEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals(Reference.MOD_ID)) {
            loadConfig();
        }

    }

    private static void loadConfig() {
        oreList = config.get("oreList","Oredict names for propecting.Put names below from least to most valuable ore",Reference.oreList).getStringList();
        if (config.hasChanged()) {
            config.save();
        }


    }

    private static void setScannableOres(){
        LogHelper.info("Adding blocks: ");
        ArrayList<ItemStack> stacks;
        for (String ore:oreList) {
            stacks = OreDictionary.getOres(ore);
            for (ItemStack stack :stacks) {
                Block temp = Block.getBlockFromItem(stack.getItem());
                if (!blocks.contains(temp) ){
                    blocks.add(temp);
                }
            }
        }
    }

}

