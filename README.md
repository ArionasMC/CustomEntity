# CustomEntity
A class to create easily simple entities for Spigot.

Tutorial (You can also find a tutorial here: https://www.spigotmc.org/threads/create-simple-custom-mobs-without-nms.201465/)

Ok let's start.

First we must create a CustomEntity Object
```Java
CustomEntity customZombie = new CustomEntity(EntityType.ZOMBIE, "MyZombie", 100.0D, true);
```

Now add it some weapon and armors
```Java
customZombie.setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
customZombie.setHandItem(new ItemStack(Material.IRON_AXE));
```

And for the end we should register our CustomEntity's listener.
On Main file on 'onEnable' method add
```Java
getServer().getPluginManager().registerEvents(customZombie, myPlugin);
```

And you have just created a custom zombie !

Now if you want to spawn it just do this
```Java
customZombie.spawnEntityAt(world, location);
```
