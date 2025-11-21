package Main.worldBuilder;

import Main.character.enemy.Enemy;
import Main.character.enemy.subclasses.*;
import Main.styles.textColor.TextColorHub;
import Main.character.player.Player;

public class WorldMap {
       
        public WorldMap() { }
        
        static String lucbanDescription = "Lucban is renowned across the land for its Pahiyas Festival, a vibrant celebration where homes are covered in\n" + 
                                          "woven leaves, fruits, and radiant harvest ornaments. The locals believe the festival honors the Spirit of Abundance,\n" + 
                                          "a guardian who blesses their crops every year. But recently, elders whisper of subtle changes during the festival—decorations\n" + 
                                          "flicker with unnatural light, and some offerings vanish overnight without a trace.\n" + 
                                          "Though the music stays cheerful, something old and hungry is watching from the mountains.";
        
        static String lucenaDescription = "The port city of Lucena thrives as the busiest trade center in the region, where caravans, ships, and adventurers converge.\n" + 
                                          "The city’s prosperity comes from its deep-water harbor and its proud guild of merchants who control routes across the western seas.\n" + 
                                          "However, beneath its lively marketplaces lies a swelling tension: unknown goods are circulating in the black market,\n" + 
                                          "and foreign ships bearing unfamiliar flags are seen more frequently at dusk. The city’s leaders suspect\n" + 
                                          "a hidden power is moving pieces behind the scenes.";

        static String sariayaDescription = "Sariaya’s grand ancestral mansions and prosperous plantations once made it the pride of the Heartland.\n" + 
                                           "But this era of comfort is collapsing. A fearsome foe—a massive bestial creature or a marauding clan—now\n" + 
                                           "threatens the gates, driving townsfolk into panic. Every night, lanterns are lit in the old mansions,\n" + 
                                           "not as celebration, but as a desperate signal for help. Rumors say the enemy seeks something buried in\n" + 
                                           "one of Sariaya’s ancient estates… something the town hid long ago.";

        static String candelariaDescription = "Known for its warm hospitality, Candelaria sits quietly at the crossroads of major routes. Caravans from all corners of\n" + 
                                              "the province gather here for shelter, storytelling, and safe passage. Though peaceful on the surface, Candelaria carries a heavy\n" + 
                                              "burden: travelers who rest here often speak of shadowy figures trailing them, and merchants report that someone—or something—is\n" + 
                                              "tracking the movement of rare cargo. The town is safe… but only because the danger lurks just outside its borders.";
        
        static String tiaongDescription = "Tiaong is a place of rolling greens and fertile soil, blessed with perfect weather and gentle winds. Yet its beauty masks secrets\n" + 
                                        "older than any nearby settlement. Strange stone markers, overgrown with vines, lie scattered across its fields. Farmers discover symbols\n" + 
                                        "etched beneath their plows. At night, glowing orbs drift between bamboo groves. The people of Tiaong believe the land itself is alive and\n" + 
                                        "that something beneath the earth is slowly awakening.";

        static String gumacaDescription = "A thriving riverside community, Gumaca is home to the famous Fishing Guild, renowned for taming the swift currents and monstrous river creatures.\n" + 
                                   "The guild’s techniques are passed down through generations, and their mastery keeps the town fed and flourishing.\n" + 
                                   "But recently, the river runs deeper and darker than usual. Veteran fishers claim the current isn’t natural anymore and\n" + 
                                   "that the “River Mother,” a deity of old tales, may be stirring.";

        static String lopezDescription = "Lopez is a peaceful farming town defined by lush, endless fields. Its people live slow, gentle lives, guided by the rhythm of planting and harvest.\n" + 
                                         "Yet, beneath this simplicity lies a profound silence: ancient farming shrines scattered across the fields have begun to crack, and crops are growing\n" + 
                                         "in unusual patterns. The townsfolk believe that the Harvest Spirits beings that once protected Lopez are leaving warnings hidden in the soil.";

        static String calauagDescription = "Calauag is a historic town now ruled with iron discipline by a powerful warlord known as Bansagan the Steel-Tide. His fortified hall stands\n" + 
                                           "upon an old battleground said to be drenched in the spirits of fallen warriors. While Calauag remains orderly and prosperous, its peace is built\n" + 
                                           "on fear. The warlord constantly prepares for a conflict he claims is inevitable—\n" + 
                                           "“…because a shadow from the east is coming.”\n" + 
                                           "Only a few know what he truly means.";

        static String infantaDescription = "Infanta exists at the mercy of relentless storms that crash upon its shores year-round. Houses are built on high stilts, and the sound of thunder is\n" + 
                                           "as common as birdsong. The people have learned to survive in constant rain, guided by weather shamans who interpret the sky. But lately, storms grow\n" + 
                                           "unnaturally strong, hitting in patterns the shamans do not recognize. Something out at sea is shaping the weather… and it’s moving closer.";

        static String realDescription = "Real marks the edge of civilization a cliffside town that overlooks a vast, unforgiving sea. The area is known for eerie mists, abandoned watchtowers,\n" + 
                                        "and remnants of old outposts swallowed by the forest. Locals say that beyond Real lies the end of the known world, where uncharted waters and ancient\n" + 
                                        "entities roam. Few who venture eastward ever return, and those who do speak of colossal shapes beneath the waves and voices that echo from the deep.\n" + 
                                        "Real is a place for the brave… or the desperate.";

    public static Town buildWestPath(Player player) {

        Town lucban = new Town("Lucban", lucbanDescription,
                new Enemy[]{new TanimGuardian(player), new HabhabBandit(player), new LanggonisaLord(player)}, null);

        Town lucena = new Town("Lucena", lucenaDescription,
                new Enemy[]{new PortRat(player), new LucenaPirate(player)}, new HarborSentinel(player));

        Town sariaya = new Town("Sariaya", sariayaDescription,
                new Enemy[]{new BakeryGremlin(player), new HeritageWraith(player), new CoconutBrigade(player)}, null);

        Town candelaria = new Town("Candelaria", candelariaDescription,
                new Enemy[]{new BibingkaElemental(player), new SweetVendor(player), new FlamingCandelarian(player)}, null);

        Town tiaong = new Town("Tiaong", tiaongDescription,
                new Enemy[]{new PugonPhantom(player), new TiaongKnight(player)}, new DonMariano(player));

        // Link sequence
        lucban.setNextTown(lucena);
        lucena.setNextTown(sariaya);
        sariaya.setNextTown(candelaria);
        candelaria.setNextTown(tiaong);

        return lucban;
    }

    public static Town buildEastPath(Player player) {
        Town gumaca = new Town("Gumaca", gumacaDescription,
                new Enemy[]{new FestivalMask(), new KipingGolem(), new Sirena()}, null);

        Town lopez = new Town("Lopez", lopezDescription,
                new Enemy[]{new SumanMimic(), new LopezWolf()}, new OldTrainSpirit());

        Town calauag = new Town("Calauag", calauagDescription,
                new Enemy[]{new FisherRogue(), new SeaWidow(), new CalauagBandit()}, null);

        Town infanta = new Town("Infanta", infantaDescription,
                new Enemy[]{new ForestNymph(), new HoneyGuardian(), new InfantaShade()}, null);

        Town real = new Town("Real", realDescription,
                new Enemy[]{new WaveFiend(), new RealSpecter()}, new QueenAmihan());

        // Link sequence
        gumaca.setNextTown(lopez);
        lopez.setNextTown(calauag);
        calauag.setNextTown(infanta);
        infanta.setNextTown(real);

        return gumaca;
    }
}
