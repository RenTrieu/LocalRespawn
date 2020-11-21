#Local Respawn
A configurable Minecraft Spigot Plugin that allows for players to respawn 
within a radius of their death location. Written for Spigot 1.16.4 API.


Configuration:
--------------
DeathRespawnRadius: The max distance from the death location which a player 
                    can respawn.
LocalRespawnRetries: The amount of times the plugin will try to find a safe 
                     respawn location within DeathRespawnRadius. If this 
                     number is exhausted and no safe respawn location is 
                     found, the player will respawn at the world spawn.
SpawnOnWater: Whether or not players can respawn on water.