package net.minecraft.src;

import java.util.Random;

public class BlockkniSpawner extends BlockContainer
{
    int on;

    protected BlockkniSpawner(int i, int j)
    {
        super(i, j, Material.rock);
        on = 1;
    }

    /**
     * Returns the TileEntity used by this block.
     */
    public TileEntity getBlockEntity()
    {
        return new TileEntitykniSpawner();
    }

    public int idDropped(int i, Random random)
    {
        return 238;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 1;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }
}
