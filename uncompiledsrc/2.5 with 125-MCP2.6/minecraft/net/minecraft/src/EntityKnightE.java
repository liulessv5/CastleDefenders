package net.minecraft.src;

import java.util.List;

public class EntityKnightE extends EntityMob
{
    private static final ItemStack defaultHeldItem;

    /** How much damage this mob's attacks deal */
    protected int attackStrength;

    public EntityKnightE(World world)
    {
        super(world);
        texture = "/KnightE.png";
        health = 15;
        moveSpeed = 0.3F;
        attackStrength = 6;
        getNavigator().setBreakDoors(true);
        tasks.addTask(1, new EntityAIAttackOnCollide(this, net.minecraft.src.EntityPlayer.class, moveSpeed, true));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, net.minecraft.src.EntityDefender.class, moveSpeed, true));
        tasks.addTask(3, new EntityAIWander(this, moveSpeed));
        targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, net.minecraft.src.EntityPlayer.class, 16F, 0, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, net.minecraft.src.EntityDefender.class, 16F, 0, true));
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }

    /**
     * Returns the item that this EntityLiving is holding, if any.
     */
    public ItemStack getHeldItem()
    {
        return defaultHeldItem;
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return 0;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        if (worldObj.countEntities(getClass()) >= 10)
        {
            return false;
        }
        else
        {
            int i = MathHelper.floor_double(posX);
            int j = MathHelper.floor_double(boundingBox.minY);
            int k = MathHelper.floor_double(posZ);
            int l = worldObj.getBlockId(i, j - 1, k);
            List list = worldObj.getEntitiesWithinAABB(net.minecraft.src.EntityKnightE.class, AxisAlignedBB.getBoundingBoxFromPool(posX, posY, posZ, posX + 1.0D, posY + 1.0D, posZ + 1.0D).expand(2D, 2D, 2D));
            return worldObj.getBlockId(i, j - 1, k) == 231 && worldObj.checkIfAABBIsClear(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).size() == 0 && list.isEmpty();
        }
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 0;
    }

    public int getMaxHealth()
    {
        return 25;
    }

    static
    {
        defaultHeldItem = new ItemStack(Item.swordSteel, 1);
    }
}
