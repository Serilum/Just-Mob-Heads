<h2>Just Mob Heads</h2>
<p><a href="https://github.com/Serilum/Just-Mob-Heads"><img src="https://serilum.com/assets/data/logo/just-mob-heads.png"></a></p><h2>Download</h2>
<p>You can download Just Mob Heads on CurseForge and Modrinth:</p><p>&nbsp;&nbsp;CurseForge: &nbsp;&nbsp;<a href="https://curseforge.com/minecraft/mc-mods/just-mob-heads">https://curseforge.com/minecraft/mc-mods/just-mob-heads</a><br>&nbsp;&nbsp;Modrinth: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="https://modrinth.com/mod/just-mob-heads">https://modrinth.com/mod/just-mob-heads</a></p>
<h2>Issue Tracker</h2>
<p>To keep a better overview of all mods, the issue tracker is located in a separate repository.<br>&nbsp;&nbsp;For issues, ideas, suggestions or anything else, please follow this link:</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;-> <a href="https://serilum.com/url/issue-tracker">Issue Tracker</a></p>
<h2>Pull Requests</h2>
<p>Because of the way mod loader files are bundled into one jar, some extra information is needed to do a PR.<br>&nbsp;&nbsp;A wiki page entry about it is available here:</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;-> <a href="https://serilum.com/url/pull-requests">Pull Request Information</a></p>
<h2>Mod Description</h2>
<p style="text-align:center"><a href="https://serilum.com/" rel="nofollow"><img src="https://github.com/Serilum/.cdn/raw/main/description/header/header.png" alt="" width="838" height="400"></a></p>
<p style="text-align:center"><a href="https://curseforge.com/members/serilum/projects" rel="nofollow"><img src="https://raw.githubusercontent.com/Serilum/.data-workflow/main/badges/svg/curseforge.svg" width="200"></a> <a href="https://modrinth.com/user/Serilum" rel="nofollow"><img src="https://raw.githubusercontent.com/Serilum/.data-workflow/main/badges/svg/modrinth.svg" width="200"></a> <a href="https://patreon.com/serilum" rel="nofollow"><img src="https://raw.githubusercontent.com/Serilum/.data-workflow/main/badges/svg/patreon.svg" width="200"></a> <a href="https://youtube.com/@serilum" rel="nofollow"><img src="https://raw.githubusercontent.com/Serilum/.data-workflow/main/badges/svg/youtube.svg" width="200"></a></p>
<p><strong><span style="font-size:24px">Requires the library mod&nbsp;<a style="font-size:24px" href="https://curseforge.com/minecraft/mc-mods/collective" rel="nofollow">Collective</a>.<br></span></strong><br><picture><img src="https://github.com/Serilum/.cdn/raw/main/projects/just-mob-heads/a.jpg" width="1142" height="208"></picture><br><br> <strong>&nbsp; &nbsp; &nbsp;This mod is part of <span style="color:#008000"><a style="color:#008000" href="https://curseforge.com/minecraft/modpacks/the-vanilla-experience" rel="nofollow">The Vanilla Experience</a></span>.</strong><br><span style="font-size:18px">Just Mob Heads is a minimalistic mod that adds a configurable chance for mobs to drop their head on death. You can also generate every available mob head via commands. Mob heads will keep their name after placement and pickup with the mod installed.<br><br>There are config options available to make mob heads only drop on player kill or only by charged creepers.</span><br><br><br>I've also got a&nbsp;<strong><span style="color:#f90"><a style="color:#f90" href="https://minecraft.curseforge.com/projects/just-player-heads" rel="nofollow">Just Player Heads</a></span></strong>&nbsp;mod available!<br><br><span style="font-size:24px"><strong>1.21 ready!</strong></span><br><picture><img src="https://github.com/Serilum/.cdn/raw/main/projects/just-mob-heads/i.png" width="1147" height="591"></picture><br><br><br><strong><span style="font-size:20px">Configurable:</span> <span style="color:#008000;font-size:14px"><a style="color:#008000" href="https://github.com/Serilum/.information/wiki/how-to-configure-mods" rel="nofollow">(&nbsp;how do I configure?&nbsp;)</a></span><br></strong><span style="font-size:16px"><em>The mod allows configuration via two config files. The general one is located in <strong>./config/justmobheads-common.toml</strong> and contains:</em></span><br><span style="font-size:12px"><strong><br>mobSpecificDropChances</strong>&nbsp;(default = true): If enabled, overrides the 'overallDropChance' variable with the specific values.</span><br><span style="font-size:12px"><strong>enableStandardHeads</strong>&nbsp;(default = false): If enabled, allows Creepers, Skeletons and Zombies to drop their heads.</span><br><span style="font-size:12px"><strong>enableLootingEnchant</strong>&nbsp;(default = true): If enabled, the looting enchant will have an effect on the drop chance.<br><strong>onlyAdultMobsDropTheirHead</strong>&nbsp;(default = true): If enabled, only adult tameable mobs will have a chance to drop their head on death.<br></span><br><span style="font-size:12px"><strong>overallDropChance</strong>&nbsp;(default = 0.1, min 0.0001, max 1.0): Sets the chance of a mob dropping its head if 'mobSpecificDropChances' is disabled.</span><br><span style="font-size:12px"><strong>creeperSkeletonZombieDropChance</strong>&nbsp;(default = 0.1, min 0.0001, max 1.0): Sets head drop chance for Zombies, Skeletons and Creepers if 'enableStandardHeads' is enabled.</span><br><br><strong>onlyDropHeadsByChargedCreeper</strong>&nbsp;(default = false):&nbsp;When enabled, only drops mob heads if the source on death is a charged creeper. This overwrites the onlyDropHeadsByPlayerKill value.<br><strong>onlyDropHeadsByPlayerKill</strong>&nbsp;(default = false): When enabled, only drops mob heads if the source on death is from a player.<br><br><br><span style="font-size:14px"><em><span style="font-size:16px">The second config file is located in <strong>./config/justmobheads/headchances.txt</strong>. This file contains the default drop chances for all mob head drops (1.19 data):</span><br></em><span style="font-size:12px">On first load, the existing values are checked for anything missing (e.g. after an update). If that's the case, it'll be appended to the end of the file.</span></span></p>
<div class="spoiler">
<p>"all_black_cat" : 0.5,<br>"allay" : 0.5,<br>"armorer" : 0.1,<br>"bat" : 0.25,<br>"bee" : 0.1,<br>"black_and_white_rabbit" : 0.1,<br>"black_cat" : 0.5,<br>"black_horse" : 0.1,<br>"black_rabbit" : 0.1,<br>"black_sheep" : 0.025,<br>"blaze" : 0.01,<br>"blue_axolotl" : 0.5,<br>"blue_parrot" : 0.75,<br>"blue_sheep" : 0.025,<br>"british_shorthair_cat" : 0.5,<br>"brown_horse" : 0.1,<br>"brown_llama" : 0.05,<br>"brown_mooshroom" : 0.05,<br>"brown_rabbit" : 0.1,<br>"brown_sheep" : 0.025,<br>"brown_trader_llama" : 0.25,<br>"butcher" : 0.1,<br>"calico_cat" : 0.5,<br>"cartographer" : 0.1,<br>"cave_spider" : 0.01,<br>"charged_creeper" : 1.0,<br>"chestnut_horse" : 0.1,<br>"chicken" : 0.05,<br>"cleric" : 0.1,<br>"cod" : 0.1,<br>"cold_frog" : 0.1,<br>"cow" : 0.05,<br>"creamy_horse" : 0.1,<br>"creamy_llama" : 0.05,<br>"creamy_trader_llama" : 0.25,<br>"cyan_axolotl" : 0.5,<br>"cyan_parrot" : 0.75,<br>"cyan_sheep" : 0.025,<br>"dark_brown_horse" : 0.1,<br>"dolphin" : 0.2,<br>"donkey" : 0.1,<br>"drowned" : 0.05,<br>"elder_guardian" : 0.3,<br>"enderman" : 0.02,<br>"endermite" : 0.02,<br>"evoker" : 0.02,<br>"farmer" : 0.1,<br>"fisherman" : 0.1,<br>"fletcher" : 0.1,<br>"ghast" : 0.1,<br>"glow_squid" : 0.2,<br>"goat" : 0.05,<br>"gold_axolotl" : 0.5,<br>"gold_rabbit" : 0.1,<br>"gray_horse" : 0.1,<br>"gray_llama" : 0.05,<br>"gray_parrot" : 0.75,<br>"gray_sheep" : 0.025,<br>"gray_trader_llama" : 0.25,<br>"green_parrot" : 0.75,<br>"green_sheep" : 0.025,<br>"guardian" : 0.02,<br>"hoglin" : 0.1,<br>"husk" : 0.03,<br>"illusioner" : 0.25,<br>"iron_golem" : 0.02,<br>"jeb_sheep" : 0.2,<br>"jellie_cat" : 0.5,<br>"killer_rabbit" : 0.1,<br>"leatherworker" : 0.1,<br>"librarian" : 0.1,<br>"light_blue_sheep" : 0.025,<br>"light_gray_sheep" : 0.025,<br>"lime_sheep" : 0.025,<br>"lucy_axolotl" : 0.5,<br>"magenta_sheep" : 0.025,<br>"magma_cube" : 0.05,<br>"mason" : 0.1,<br>"mooshroom" : 0.05,<br>"mule" : 0.1,<br>"nitwit" : 0.1,<br>"ocelot" : 0.1,<br>"orange_sheep" : 0.025,<br>"panda" : 0.5,<br>"persian_cat" : 0.5,<br>"phantom" : 0.1,<br>"pig" : 0.05,<br>"piglin" : 0.05,<br>"piglin_brute" : 0.1,<br>"pillager" : 0.2,<br>"pink_sheep" : 0.025,<br>"polar_bear" : 0.2,<br>"pufferfish" : 0.1,<br>"purple_sheep" : 0.025,<br>"ragdoll_cat" : 0.5,<br>"ravager" : 0.2,<br>"red_cat" : 0.5,<br>"red_fox" : 0.25,<br>"red_parrot" : 0.75,<br>"red_sheep" : 0.025,<br>"salmon" : 0.1,<br>"salt_and_pepper_rabbit" : 0.1,<br>"shepherd" : 0.1,<br>"shulker" : 0.05,<br>"siamese_cat" : 0.5,<br>"silverfish" : 0.05,<br>"skeleton_horse" : 0.25,<br>"slime" : 0.05,<br>"snow_fox" : 0.25,<br>"snow_golem" : 0.1,<br>"snowman" : 0.05,<br>"spider" : 0.05,<br>"squid" : 0.05,<br>"stray" : 0.1,<br>"strider" : 0.1,<br>"tabby_cat" : 0.5,<br>"tadpole" : 0.05,<br>"temperate_frog" : 0.1,<br>"toast_rabbit" : 0.5,<br>"toolsmith" : 0.1,<br>"tropical_fish" : 0.1,<br>"turtle" : 0.1,<br>"vex" : 0.25,<br>"villager" : 0.1,<br>"vindicator" : 0.25,<br>"wandering_trader" : 1.0,<br>"warden" : 1.0,<br>"warm_frog" : 0.1,<br>"weaponsmith" : 0.1,<br>"white_cat" : 0.5,<br>"white_horse" : 0.1,<br>"white_llama" : 0.05,<br>"white_rabbit" : 0.1,<br>"white_sheep" : 0.025,<br>"white_trader_llama" : 0.25,<br>"wild_axolotl" : 0.5,<br>"witch" : 0.05,<br>"wither" : 1.0,<br>"wolf" : 0.05,<br>"yellow_sheep" : 0.025,<br>"zoglin" : 0.1,<br>"zombie_armorer" : 0.1,<br>"zombie_butcher" : 0.1,<br>"zombie_cartographer" : 0.1,<br>"zombie_cleric" : 0.1,<br>"zombie_farmer" : 0.1,<br>"zombie_fisherman" : 0.1,<br>"zombie_fletcher" : 0.1,<br>"zombie_horse" : 0.25,<br>"zombie_leatherworker" : 0.1,<br>"zombie_librarian" : 0.1,<br>"zombie_mason" : 0.1,<br>"zombie_nitwit" : 0.1,<br>"zombie_shepherd" : 0.1,<br>"zombie_toolsmith" : 0.1,<br>"zombie_villager" : 0.1,<br>"zombie_weaponsmith" : 0.1,<br>"zombified_piglin" : 0.1,</p>
</div>
<p><br><br><span style="text-decoration:underline;font-size:18px"><strong>Commands</strong></span><br><em><strong>/jmh reload</strong></em> - Reloads all changes made to the "./config/justmobheads/headchances.txt" config file.<br><em><strong>/jmh head list</strong></em> - Lists all mobnames of available mob heads.<br><em><strong>/jmh head &lt;mobname&gt; &lt;amount&gt;</strong></em> - Generates &lt;amount&gt; of heads from &lt;mobname&gt;.<br><br><strong><span style="font-size:14px">Command examples:</span><br></strong></p>
<div class="spoiler">
<p><picture><img src="https://github.com/Serilum/.cdn/raw/main/projects/just-mob-heads/c.png" width="334" height="59"></picture><br><picture><img src="https://github.com/Serilum/.cdn/raw/main/projects/just-mob-heads/d.png" width="1130" height="459"></picture><br><br><picture><img src="https://github.com/Serilum/.cdn/raw/main/projects/just-mob-heads/e.png" width="410" height="57"></picture><br><picture><img src="https://github.com/Serilum/.cdn/raw/main/projects/just-mob-heads/f.png" width="825" height="196"></picture></p>
</div>
<p><br><br><strong><span style="font-size:18px">Texture values instead of generation via player-names</span><br></strong>The mod uses texture values to generate the mob heads. This means that there won't be any lag, which player-name generated heads usually cause.<strong><br></strong><br><strong><span style="font-size:18px">Pure Vanilla Resources</span><br></strong><span style="font-size:12px">Because the mod does not add any custom blocks, you may remove the mod and still have the heads previously gathered in-game.<br><br></span><strong><span style="font-size:18px">When does a head drop?</span><br></strong>Whenever the player kills a mob, there is a chance that they drop their head. There are pre-defined chances in the config file, but you may alter them to your liking. It'll be dropped at their death location.<br><br><strong><span style="font-size:18px">How many mobs are added?</span><br></strong>Every mob has been added. When Minecraft updates, the newly added mobs receive a head drop as well. You can see the entire mob head list above under configurable.<br><br><strong><span style="font-size:18px">What about Creepers, Skeletons and Zombies?</span><br></strong>By default the standard heads do not drop, because you can collect them via a charged creeper. You can however enable the option within the config file for all three to drop their respective head on death just as any other mob.<br><br><strong><span style="font-size:18px">Does Looting have an effect?</span><br></strong>The Looting enchant has an effect on the head drop chance.&nbsp;This can be enabled/disabled within the config file. The increased chance is&nbsp;3.5% at level I, 4.5% at level II, and 5.5% at level III and so forth. It also works with looting levels higher than 3.</p>
<p><br>------------------<br><br><span style="font-size:24px"><strong>You may freely use this mod in any modpack, as long as the download remains hosted within the CurseForge or Modrinth ecosystem.</strong></span><br><br><span style="font-size:18px"><a style="font-size:18px;color:#008000" href="https://serilum.com/" rel="nofollow">Serilum.com</a> contains an overview and more information on all mods available.</span><br><br><span style="font-size:14px">Comments are disabled as I'm unable to keep track of all the separate pages on each mod.</span><span style="font-size:14px"><br>For issues, ideas, suggestions or anything else there is the&nbsp;<a style="font-size:14px;color:#008000" href="https://github.com/Serilum/.issue-tracker" rel="nofollow">Github repo</a>. Thanks!</span><span style="font-size:6px"><br><br></span></p>
<p style="text-align:center"><a href="https://serilum.com/donate" rel="nofollow"><img src="https://github.com/Serilum/.cdn/raw/main/description/projects/support.svg" alt="" width="306" height="50"></a></p>