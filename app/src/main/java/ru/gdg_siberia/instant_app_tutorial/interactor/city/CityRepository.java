package ru.gdg_siberia.instant_app_tutorial.interactor.city;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function4;
import ru.gdg_siberia.instant_app_tutorial.R;
import ru.gdg_siberia.instant_app_tutorial.domain.City;
import ru.gdg_siberia.instant_app_tutorial.interactor.common.cache.BaseRepository;
import ru.gdg_siberia.instant_app_tutorial.interactor.scheduler.SchedulersProvider;

public class CityRepository extends BaseRepository {

    private static final String[] citiesNames = {
            "Ekaterinburg",
            "Kaliningrad",
            "Kazan",
            "Krasnoyarsk",
            "Makhachkala",
            "Moscow",
            "Nizhny Novgorod",
            "Novosibirsk",
            "Omsk",
            "Penza",
            "Perm",
            "Petrozavodsk",
            "Rostov",
            "Samara",
            "St.Petersburg",
            "Ufa",
            "Ulan-Ude",
            "Vladivostok",
            "Voronezh"
    };

    private static final String[] citiesUrls = {
            "ekb",
            "klg",
            "kzn",
            "kry",
            "mkh",
            "msc",
            "nn",
            "nsk",
            "oms",
            "pnz",
            "prm",
            "ptr",
            "rnd",
            "smr",
            "spb",
            "ufa",
            "uud",
            "vla",
            "vrn"
    };

    private static final String[] citiesDescriptions = {
            "Yekaterinburg, alternatively romanised as Ekaterinburg, is Russia's fourth-largest " +
                    "city after Moscow, Saint Petersburg, and Novosibirsk, with a population of" +
                    " 1,349,772 as of 2010. Yekaterinburg is the administrative centre of " +
                    "Sverdlovsk Oblast located in the middle of the Eurasian continent, on the " +
                    "border of Europe and Asia. Situated on the Iset River, the city was " +
                    "built in November 18, 1723, and named after Russian emperor Peter the Great's " +
                    "wife, Yekaterina, who later became Catherine I after Peter's death. In 1924, " +
                    "the city was named Sverdlovsk (Russian: Свердло́вск) after the Communist party " +
                    "leader Yakov Sverdlov, and in 1991 back to Yekaterinburg.\n \n Yekaterinburg " +
                    "is a modern city in Russia, and is the main industrial and cultural centre " +
                    "of the Ural Federal District. The city had experienced economic and population " +
                    "growth in the Contemporary Era and some of the tallest skyscrapers of Russia " +
                    "are built here.",
            "Kaliningrad (former German name: Königsberg; Russian: Кёнигсберг, " +
                    "tr. Kyonigsberg; Old Prussian: Twangste, Kunnegsgarbs, Knigsberg; " +
                    "Polish: Królewiec; Lithuanian: Karaliaučius) is the administrative center of " +
                    "Kaliningrad Oblast, a Russian semi-exclave between Poland and Lithuania on " +
                    "the Baltic Sea.\n \n In the Middle Ages, it was the site of Old Prussian " +
                    "settlement Twangste. In 1255, during the Northern Crusades, a new fortress " +
                    "was built by the Teutonic Knights. The city became the capital of the Duchy" +
                    " of Prussia and East Prussia (part of Germany). It was heavily damaged during " +
                    "World War II and its population fled or was removed by force when it became " +
                    "a Russian city. According to the 2010 Census, its population was 431,902.",
            "Kazan is the capital and largest city of the Republic of Tatarstan, Russia. With a" +
                    " population of 1,143,535, it is the eighth most populous city in Russia. Kazan " +
                    "lies at the confluence of the Volga and Kazanka Rivers in European Russia. The " +
                    "Kazan Kremlin is a World Heritage Site.\n \n In April 2009, the Russian Patent" +
                    " Office granted Kazan the right to brand itself as the \"Third Capital\" of " +
                    "Russia. In 2009 it was chosen as the \"Sports capital of Russia\" and " +
                    "it still is referred to as such. The city hosted the 2013 Summer " +
                    "Universiade, 2014 World Fencing Championships, the 2015 World Aquatics " +
                    "Championships, and is one of the host cities for the 2017 FIFA Confederations " +
                    "Cup and the 2018 FIFA World Cup.\n \n In 2015, Kazan was visited by 2.1 " +
                    "million tourists, which is a 20% increase in comparison with 2014. The Kazan " +
                    "Kremlin was visited by 1.5 million tourists in 2015 and hotel and entertainment " +
                    "complex with aquapark called \"Kazan Riviera\" was visited by 1 million tourists.",
            "Krasnoyarsk is a city and the administrative center of Krasnoyarsk Krai, Russia, " +
                    "located on the Yenisei River. It is the third largest city in Siberia after " +
                    "Novosibirsk and Omsk, with a population of 1,035,528 as of the 2010 Census. " +
                    "Krasnoyarsk is an important junction of the Trans-Siberian Railway and one of " +
                    "Russia's largest producers of aluminum.\n \n The city is notable for its " +
                    "nature landscapes; author Anton Chekhov judged Krasnoyarsk to be the most " +
                    "beautiful city in Siberia.",
            "Makhachkala is the capital city of the Republic of Dagestan, Russia. It is located on " +
                    "the western shore of the Caspian Sea and is home to the Makhachkala Grand " +
                    "Mosque, one of Russia’s largest. As of the 2010 Census, the city had a " +
                    "population of 572,076, making it the largest in the North Caucasus Federal " +
                    "District. The city has an ethnic plurality, with the Avars and Laks being the " +
                    "largest groups.\n \n Founded as a fortress of the Russian Empire in 1844 and " +
                    "given city status thirteen years later, the city bore the name of the Russian" +
                    " Tsar Peter the Great until 1921. Since the fall of the Soviet Union, " +
                    "Makhachkala has been affected by Islamist insurgents as spillover from " +
                    "the Second Chechen War when militants invaded Dagestan leading to a renewed " +
                    "conflict between the Russian Federation and militants in the North Caucasus region.",
            "Moscow is the capital and most populous city of Russia, with 13.2 million residents " +
                    "within the city limits and 17.1 million within the urban area. Moscow has the " +
                    "status of a Russian federal city.\n \n Moscow is a major political, economic, " +
                    "cultural, and scientific centre of Russia and Eastern Europe, as well as the " +
                    "largest city entirely on the European continent. By broader definitions Moscow " +
                    "is among the world's largest cities, being the 14th largest metro area, the " +
                    "18th largest agglomeration, the 15th largest urban area, and the 11th largest " +
                    "by population within city limits worldwide. According to Forbes 2013, Moscow" +
                    " has been ranked as the ninth most expensive city in the world by Mercer and " +
                    "has one of the world's largest urban economies, being ranked as an alpha global " +
                    "city according to the Globalization and World Cities Research Network, and " +
                    "is also one of the fastest growing tourist destinations in the world according" +
                    " to the MasterCard Global Destination Cities Index.\n \n Moscow is the " +
                    "northernmost and coldest megacity and metropolis on Earth. It is home to the " +
                    "Ostankino Tower, the tallest free standing structure in Europe; the Federation " +
                    "Tower, the tallest skyscraper in Europe; and the Moscow International " +
                    "Business Center. By its territorial expansion on July 1, 2012 southwest into " +
                    "the Moscow Oblast, the area of the capital more than doubled, going from 1,091 " +
                    "to 2,511 square kilometers (421 to 970 sq mi), and it gained an additional " +
                    "population of 233,000 people.\n \n Moscow is situated on the Moskva River " +
                    "in the Central Federal District of European Russia, making it Europe's most " +
                    "populated inland city. The city is well known for its architecture, " +
                    "particularly its historic buildings such as Saint Basil's Cathedral with its " +
                    "brightly coloured domes. With over 40 percent of its territory covered by " +
                    "greenery, it is one of the greenest capitals and major cities in Europe and" +
                    " the world, having the largest forest in an urban area within its borders—more " +
                    "than any other major city—even before its expansion in 2012. The city has " +
                    "served as the capital of a progression of states, from the medieval Grand " +
                    "Duchy of Moscow and the subsequent Tsardom of Russia to the Russian Empire " +
                    "to the Soviet Union and the contemporary Russian Federation.\n \n Moscow is " +
                    "the seat of power of the Government of Russia, being the site of the Moscow " +
                    "Kremlin, a medieval city-fortress that is today the residence for work of" +
                    " the President of Russia. The Moscow Kremlin and Red Square are also one of " +
                    "several World Heritage Sites in the city. Both chambers of the Russian " +
                    "parliament (the State Duma and the Federation Council) also sit in the city. " +
                    "Moscow is considered the centre of Russian culture, having served as the home " +
                    "of Russian artists, scientists and sports figures and because of the presence " +
                    "of museums, academic and political institutions and theatres.",
            "Nizhny Novgorod, colloquially shortened to Nizhny, is a city in the administrative " +
                    "center (capital) of Volga Federal District and Nizhny Novgorod Oblast in" +
                    " Russia. From 1932 to 1990, it was known as Gorky, after the writer Maxim " +
                    "Gorky, who was born there. The city is an important economic, transportation," +
                    " scientific, educational and cultural center in Russia and the vast" +
                    " Volga-Vyatka economic region, and is the main center of river tourism in" +
                    " Russia. In the historic part of the city there is a large number of " +
                    "universities, theaters, museums and churches. Nizhny Novgorod is located " +
                    "about 400 km east of Moscow, where the Oka empties into the Volga. Population: " +
                    "1,250,619 (2010 Census); 1,311,252 (2002 Census); 1,438,133  (1989 Census)." +
                    "\n \n The city was founded in 1221 by Prince Yuri II of Vladimir. " +
                    "In 1612 Kuzma Minin and Prince Dmitry Pozharsky organized an army for the" +
                    " liberation of Moscow from the Poles. In 1817 Nizhny Novgorod became a great " +
                    "trade center of the Russian Empire. In 1896 at a fair, an All-Russia Exhibition " +
                    "was organized.\n \n During the Soviet period, the city turned into an " +
                    "important industrial center. In particular, the Gorky Automobile Plant was " +
                    "constructed in this period. Then the city was given the nickname \"Russian " +
                    "Detroit\". During the World War II Gorky became the biggest provider of " +
                    "military equipment to the front. Due to this, the Luftwaffe constantly bombed " +
                    "the city from the air. The majority of the German bombs fell in the area of " +
                    "the Gorky Automobile Plant. Although almost all the production sites of the " +
                    "plant were completely destroyed, the citizens of Gorky reconstructed the " +
                    "factory after 100 days.\n \n After the war, Gorky became a \"closed city\" " +
                    "and remained one until after the dissolution of the Soviet Union in 1990. " +
                    "At that time the city was renamed Nizhny Novgorod once again.\n \n In 1985 " +
                    "the metro was opened. In 2016 Vladimir Putin opened the new 70th Anniversary" +
                    " of Victory Plant which is part of the Almaz-Antey Air and Space Defence " +
                    "Corporation.\n \n The Kremlin – the main center of the city – contains the " +
                    "main government agencies of the city and the Volga Federal District.\n \n" +
                    "The demonym for a Nizhny Novgorod resident is \"нижегородец\" " +
                    "(nizhegorodets) for male or \"нижегородка\" (nizhegorodka) for female, " +
                    "rendered in English as Nizhegorodian. Novgorodian is inappropriate; it refers " +
                    "to a resident of Novgorod, on the north-west.\n \n",
            "Novosibirsk is the third-most populous city in Russia after Moscow and St. Petersburg. " +
                    "It is the most populous city in Asian Russia, with a population of 1,473,754 " +
                    "as of the 2010 Census. It is the administrative center of Novosibirsk " +
                    "Oblast as well as of the Siberian Federal District.\n \n The city is " +
                    "located in the southwestern part of Siberia on the banks of the Ob " +
                    "River adjacent to the Ob River Valley, near the large water reservoir " +
                    "formed by the dam of the Novosibirsk Hydro Power Plant. It is split " +
                    "into ten districts and occupies an area of 502.1 square kilometres " +
                    "(193.9 sq mi).\n \n",
            "Omsk is a city and the administrative center of Omsk Oblast, Russia, located in " +
                    "southwestern Siberia 2,236 kilometers (1,389 mi) from Moscow. With a " +
                    "population of 1,154,116, it is Russia's second-largest city east of the Ural " +
                    "Mountains after Novosibirsk, and seventh by size nationally. Omsk acts as an " +
                    "essential transport node, serving as a train station for Trans-Siberian Railway" +
                    " and as a staging post for the Irtysh River.\n \n During the Imperial era, " +
                    "Omsk used to be the seat of the Governor General of Western Siberia and, " +
                    "later, of the Governor General of the Steppes. For a brief period during " +
                    "the Russian Civil War in 1918–1920, it served as the capital of the " +
                    "anti-Bolshevik Russian State and held the imperial gold reserves.\n \n Omsk " +
                    "is the administrative center of the Siberian Cossack Host. It also serves as " +
                    "the see of the bishop of Omsk and Tara, as well as the administrative seat of " +
                    "the Imam of Siberia.",
            "Penza is a city and the administrative center of Penza Oblast, Russia, located on " +
                    "the Sura River, 625 kilometers (388 mi) southeast of Moscow. Population: " +
                    "517,311 (2010 Census); 518,025 (2002 Census); 542,612 (1989 Census).\n \n",
            "Perm is a city and the administrative center of Perm Krai, Russia, located on the " +
                    "banks of the Kama River in the European part of Russia near the Ural " +
                    "Mountains.\n \n According to the 2010 Census, Perm's population is 991,162," +
                    " down from 1,001,653 recorded in the 2002 Census and 1,090,944 recorded in " +
                    "1989 Census. As of the 2010 Census, the city was the thirteenth most " +
                    "populous in Russia.\n \n From 1940 to 1957 it was named Molotovin honor of " +
                    "Vyacheslav Molotov.\n \n",
            "Petrozavodsk is the capital city of the Republic of Karelia, Russia, which stretches " +
                    "along the western shore of Lake Onega for some 27 kilometers (17 mi). " +
                    "Population: 261,987 (2010 Census); 266,160 (2002 Census); 269,485 " +
                    "(1989 Census).\n \n",
            "Rostov-on-Don is a port city and the administrative center of Rostov Oblast and the " +
                    "Southern Federal District of Russia. It lies in the southeastern part of the " +
                    "East European Plain on the Don River, 32 kilometers (20 mi) from the Sea of " +
                    "Azov. The southwestern suburbs of the city abut the Don River delta. " +
                    "Population: 1,089,261 (2010 Census); 1,068,267 (2002 Census); " +
                    "1,019,305 (1989 Census). \n",
            "Samara, known from 1935 to 1991 as Kuybyshev, is the sixth largest city in Russia and " +
                    "the administrative center of Samara Oblast. It is situated in the southeastern " +
                    "part of European Russia at the confluence of the Volga and Samara Rivers on the " +
                    "east bank of the Volga. The Volga acts as the city's western boundary; across " +
                    "the river are the Zhiguli Mountains, after which the local beer (Zhigulyovskoye) " +
                    "is named. The northern boundary is formed by the Sokolyi Hills and by the " +
                    "steppes in the south and east. The land within the city boundaries covers " +
                    "46,597 hectares (115,140 acres). Population: 1,164,685 (2010 Census);" +
                    " 1,157,880 (2002 Census); 1,254,460 (1989 Census). The metropolitan area " +
                    "of Samara-Tolyatti-Syzran within Samara Oblast contains a population of over " +
                    "three million.\n \n Formerly a closed city, Samara is now a large and " +
                    "important social, political, economic, industrial, and cultural centre " +
                    "in European Russia and hosted the European Union—Russia Summit in May 2007. " +
                    "It has a continental climate characterised by hot summers and cold winters. " +
                    "The life of Samara's citizens has always been intrinsically linked to the " +
                    "Volga River, which has not only served as the main commercial thoroughfare of " +
                    "Russia throughout several centuries, but also has great visual appeal. " +
                    "Samara's riverfront is considered one of the favourite recreation places " +
                    "both for local citizens and tourists. After the Soviet novelist Vasily Aksyonov " +
                    "visited Samara, he remarked: \"I am not sure where in the West one can find " +
                    "such a long and beautiful embankment.\"\n \n",
            "Saint Petersburg is Russia's second-largest city after Moscow, with five million " +
                    "inhabitants in 2012, and an important Russian port on the Baltic Sea. It " +
                    "is politically incorporated as a federal subject (a federal city). Situated on " +
                    "the Neva River, at the head of the Gulf of Finland on the Baltic Sea, it was" +
                    " founded by Tsar Peter the Great on May 27 1703. In 1914, the name " +
                    "was changed from Saint Petersburg to Petrograd, in 1924 to Leningrad " +
                    "and in 1991 back to Saint Petersburg. Between 1713 and 1728 and in 1732–1918," +
                    " Saint Petersburg was the capital of imperial Russia. In 1918," +
                    " the central government bodies moved to Moscow.\n \n Saint Petersburg is one " +
                    "of the modern cities of Russia, as well as its cultural capital. The Historic " +
                    "Centre of Saint Petersburg and Related Groups of Monuments constitute a UNESCO" +
                    " World Heritage Site. Saint Petersburg is home to The Hermitage, one of the " +
                    "largest art museums in the world. Many foreign consulates, international " +
                    "corporations, banks, and businesses have offices in Saint Petersburg.\n \n",
            "Ufa is the capital city of the Republic of Bashkortostan, Russia, and the industrial," +
                    " economic, scientific and cultural center of the republic. As of the 2010 " +
                    "Census, its population was 1,062,319, making it the eleventh most populous " +
                    "city in Russia.\n \n",
            "Ulan-Ude is the capital city of the Republic of Buryatia, Russia; it is located" +
                    " about 100 kilometers (62 mi) southeast of Lake Baikal on the Uda River at" +
                    " its confluence with the Selenga. According to the 2010 Census, 404,426 people " +
                    "lived in Ulan-Ude; up from 359,391 recorded in the 2002 Census," +
                    " making the city the third largest in eastern Siberia by population.\n \n",
            "Vladivostok (literally ruler of the east) is a city and the administrative centre of" +
                    " Primorsky Krai, Russia, located around the Golden Horn Bay, not far from " +
                    "Russia's borders with China and North Korea. The population of the city as of " +
                    "2016 is 606,653, up from 592,034 recorded in the 2010 Russian census. " +
                    "The city is the home port of the Russian Pacific Fleet and the largest Russian " +
                    "port on the Pacific Ocean.\n \n",
            "Voronezh is a city and the administrative center of Voronezh Oblast, Russia, " +
                    "straddling the Voronezh River and located 12 kilometers (7.5 mi) from where " +
                    "it flows into the Don. The city sits on the Southeastern Railway, which " +
                    "connects European Russia with the Urals and Siberia, the Caucasus and Ukraine," +
                    " and the M4 highway (Moscow–Voronezh–Rostov-on-Don-Novorossiysk). Its " +
                    "population in 2016 was estimated to be 1,032,895; up from 889,680 recorded " +
                    "in the 2010 Census.\n \n"
    };

    private static final Integer[] citiesPicturesIds = {
            R.drawable.ekaterinburg_pic,
            R.drawable.kaliningrad_pic,
            R.drawable.kazan_pic,
            R.drawable.krasnoyarsk_pic,
            R.drawable.makhachkala_pic,
            R.drawable.moscow_pic,
            R.drawable.nn_pic,
            R.drawable.novosibirsk_pic,
            R.drawable.omsk_pic,
            R.drawable.penza_pic,
            R.drawable.perm_pic,
            R.drawable.petrozavodsk_pic,
            R.drawable.rostov_pic,
            R.drawable.samara_pic,
            R.drawable.spb_pic,
            R.drawable.ufa_pic,
            R.drawable.ulan_ude_pic,
            R.drawable.vladivostok_pic,
            R.drawable.voronezh_pic};

    @Inject
    public CityRepository(SchedulersProvider schedulersProvider) {
        super(schedulersProvider);
    }

    public Observable<List<City>> getCities() {

        Observable<String> citiesNamesObservable = Observable.fromArray(citiesNames);
        Observable<String> citiesUrlsObservable = Observable.fromArray(citiesUrls);
        Observable<String> citiesDescriptionsObservable = Observable.fromArray(citiesDescriptions);
        Observable<Integer> citiesPicturesIdsObservable = Observable.fromArray(citiesPicturesIds);

        return Observable.zip(citiesNamesObservable,
                citiesUrlsObservable,
                citiesDescriptionsObservable,
                citiesPicturesIdsObservable,
                new Function4<String, String, String, Integer, City>() {
                    @Override
                    public City apply(String cityName, String cityUrl, String cityDescription, Integer pictureId) throws Exception {
                        Log.d("LOG", "1111 cityName = " + cityName);
                        Log.d("LOG", "1111 cityUrl = " + cityUrl);
                        Log.d("LOG", "1111 cityDescription = " + cityDescription);
                        Log.d("LOG", "1111 pictureId = " + pictureId);
                        Log.d("LOG", "1111 ////////////////////////////////");
                        return new City(cityName, cityUrl, cityDescription, pictureId);
                    }
                })
                .toList()
                .toObservable();
    }

    public Observable<City> getCityByUrl(String url) {
        return getCities()
                .flatMap(Observable::fromIterable)
                .filter(city -> city.getUrl().equals(url));
    }

}