package ru.gdg_siberia.instant_app_tutorial.interactor.city;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
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
        Observable<Integer> citiesPicturesIdsObservable = Observable.fromArray(citiesPicturesIds);

        return Observable.zip(citiesNamesObservable, citiesPicturesIdsObservable, new BiFunction<String, Integer, City>() {
            @Override
            public City apply(String s, Integer integer) throws Exception {
                return new City(s, integer);
            }
        })
                .toList()
                .toObservable();
    }

}