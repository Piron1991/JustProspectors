package com.piron1991.prospectors.utilities;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.util.Objects;


/**
 * Created by WhoCares on 2016-01-17.
 */
public class BlockDataHolder {

    private String oredict;
    private Block block;
    private int meta;
    private int value;
    private ItemStack stack;

    public BlockDataHolder(String oreDictName, Block block, int metaValue, int worth){
        this.oredict=oreDictName;
        this.block=block;
        this.meta=metaValue;
        this.value=worth;
        this.stack=new ItemStack(block,1,metaValue);
    }

    public String getOredict() {
        return oredict;
    }

    public Block getBlock() {
        return block;
    }

    public int getMeta() {
        return meta;
    }

    public int getValue() {
        return value;
    }

    public ItemStack getStack() {
        return stack;
    }


    public boolean contains(Block _block,int _meta){
        return this.block==_block && this.meta==_meta;
    }
    public boolean contains(ItemStack _stack){return this.stack==_stack;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockDataHolder that = (BlockDataHolder) o;
        return getMeta() == that.getMeta() &&
                getValue() == that.getValue() &&
                Objects.equals(getOredict(), that.getOredict()) &&
                Objects.equals(getBlock(), that.getBlock()) &&
                Objects.equals(getStack(), that.getStack());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOredict(), getBlock(), getMeta(), getValue(), getStack());
    }

    @Override
    public String toString() {
        return "Blocks data saved{" +
                "oredict='" + oredict + '\'' +
                ", block=" + block +
                ", meta=" + meta +
                ", value=" + value +
                ", stack=" + stack +
                '}';
    }
}
