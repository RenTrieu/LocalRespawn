Local Respawn:
--------------
A configurable Minecraft Spigot Plugin that allows for players to respawn 
within a radius of their death location. Written for Spigot 1.16.4 API.

Installation:
-------------
Place LocalRespawn.jar in the Spigot plugins folder.
When the server starts, the plugin will be enabled.
There are no commands.

Function:
---------
Upon installation, when players die, they will respawn at a random
coordinate within a defined radius from their death position given that 
the spawn location is safe.

Config File:
------------
__DeathRespawnRadius:__ The max distance from the death location which a player 
                    can respawn.

__LocalRespawnRetries:__ The amount of times the plugin will try to find a safe 
                     respawn location within DeathRespawnRadius. If this 
                     number is exhausted and no safe respawn location is 
                     found, the player will respawn at the world spawn.

__SpawnOnWater:__ Whether or not players can respawn on water.