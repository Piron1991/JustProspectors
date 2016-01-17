package com.piron1991.prospectors.client.items;

import com.piron1991.prospectors.handler.ConfigHandler;
import com.piron1991.prospectors.utilities.BlockDataHolder;
import com.piron1991.prospectors.utilities.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by WhoCares on 2016-01-10.
 */
public class PerfectProspector extends ItemBase{
    private Random rand = new Random();
    public PerfectProspector() {
        super();
        this.setUnlocalizedName("perfectProspector");
        this.setMaxDamage(50);
        this.setMaxStackSize(1);

    }


    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int face, float hit_x, float hit_y, float hit_z) {
        if (!world.isRemote) {

            Block testedBlock;
            Integer val = 0;
            int temp_x = x;
            int temp_y = y;
            int temp_z = z;
            BlockDataHolder data = null;

            if (itemstack.hasTagCompound()) {
                NBTTagCompound tag = itemstack.getTagCompound();

                Block _block = Block.getBlockById(tag.getInteger("block"));
                int _meta = tag.getByte("meta");

                for (BlockDataHolder _data:ConfigHandler.oreArray)
                {
                    if (_data.contains(_block,_meta))
                    {
                        data=_data;
                        break;
                    }
                }
                if (data != null) {
                    for (int k = z - ConfigHandler.xz_size; k <= z + ConfigHandler.xz_size; k++) {
                        for (int i = x - ConfigHandler.xz_size; i <= x + ConfigHandler.xz_size; i++) {
                            for (int j = y; j <= y + ConfigHandler.y_size; j++) {
                                testedBlock = world.getBlock(i, j, k);
                                int meta = world.getBlockMetadata(i, j, k);
                                for(BlockDataHolder _data:ConfigHandler.oreArray){
                                if (_data.contains(testedBlock,meta)) {
                                    if (_data.getOredict().equals(data.getOredict())){
                                        temp_x = i;
                                        temp_y = j;
                                        temp_z = k;
                                        val = 1;
                                        break;
                                    }
                                }}
                            }if (val == 1) break;
                        }if (val == 1) break;
                    }
                }
                if (val == 1) {
                    player.addChatComponentMessage(
                            new ChatComponentText(
                                    getProperLocalization(data.getOredict()) + " found around :"
                            )
                    );

                    player.addChatComponentMessage(
                            new ChatComponentText(
                                    "x: "
                                            + (temp_x + rand.nextInt(11) - 5)
                                            + " y: "
                                            + (temp_y + rand.nextInt(7) - 3)
                                            + " z: "
                                            + (temp_z + rand.nextInt(11) - 5)
                            )
                    );
                } else {
                    player.addChatComponentMessage(new ChatComponentText("No ores found"));
                }
                itemstack.damageItem(1,player);



            }else{
                player.addChatComponentMessage(new ChatComponentText("No block selected,craft with ore."));
            }

        }

        return true;
    }
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
        super.addInformation(stack, player, list, b);
        String oreName = getProperLocalization(stack);
        list.add("Finds selected ore.");
        list.add( oreName.equals(stack.getDisplayName())?"No ore selected":oreName);


    }
}




