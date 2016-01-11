package com.piron1991.prospectors.creativeTab;

import com.piron1991.prospectors.init.InitItems;
import com.piron1991.prospectors.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTab {

    public static final CreativeTabs testTab = new CreativeTabs(Reference.MOD_ID.toLowerCase()) {

        @Override
        public Item getTabIconItem() {
            //return item from registry
            return InitItems.valueProspector;
        }
    };


}
