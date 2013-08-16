package cpw.mods.fml.client.registry;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public interface ISimpleBlockRenderingHandler
{
    void renderInventoryBlock(Block var1, int var2, int var3, RenderBlocks var4);

    boolean renderWorldBlock(IBlockAccess var1, int var2, int var3, int var4, Block var5, int var6, RenderBlocks var7);

    boolean shouldRender3DInInventory();

    int getRenderId();
}
