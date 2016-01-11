package com.piron1991.prospectors.init;

import com.piron1991.prospectors.client.items.*;
import cpw.mods.fml.common.registry.GameRegistry;


public class InitItems {

    //public static final ItemBase myItemVar = new ItemClass();
    public static final ItemBase valueProspector = new ValueProspector();
    public static final ItemBase crapProspector = new CrapProspector();
    public static final ItemBase perfectProspector = new PerfectProspector();

    public static void init() {
        // GameRegistry.registerItem(myItemVar, "myItemName");
        GameRegistry.registerItem(valueProspector, "valueProspector");
        GameRegistry.registerItem(crapProspector, "crapProspector");
        GameRegistry.registerItem(perfectProspector, "perfectProspector");
    }
}
