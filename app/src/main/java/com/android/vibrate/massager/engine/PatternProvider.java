/*
 * Copyright (c) 2023
 * Create by  on 4/23/23, 2:21 PM
 * Last modified 4/23/23, 2:21 PM
 */

package com.android.vibrate.massager.engine;

import com.android.vibrate.massager.R;
import com.android.vibrate.massager.core.App;

import java.util.ArrayList;

public class PatternProvider {
    private static final String TAG = PatternProvider.class.getSimpleName();
    private static PatternProvider sInstance;

    private ArrayList<Pattern> mPatterns = new ArrayList<>();
    private Pattern mDefaultPattern = null;


    private PatternProvider() {
        prepareList();
    }

    public static PatternProvider getInstance() {
        if (sInstance == null) {
            synchronized (PatternProvider.class) {
                if (sInstance == null) {
                    sInstance = new PatternProvider();
                }
            }
        }
        return sInstance;
    }

    public ArrayList<Pattern> getPatterns() {
        return mPatterns;
    }

    public void refreshListAfterCreateSuccess() {
        mPatterns.removeAll(PatternCreationLoader.getInstance().getAllCreatedPattern());
        mPatterns.addAll(1, PatternCreationLoader.getInstance().getAllCreatedPattern());
    }

    public Pattern getDefaultPattern() {
        return mDefaultPattern;
    }

    public void prepareList() {
        mPatterns.clear();

        /*create pattern button*/
        Pattern vibrationPatternAdd = new Pattern();
        vibrationPatternAdd.title = App.self().getString(R.string.add);
        vibrationPatternAdd.pic = R.drawable.ic_pattern_add;
        vibrationPatternAdd.type = Pattern.TYPE_CREATE;
        vibrationPatternAdd.pattern = new long[]{};
        mPatterns.add(vibrationPatternAdd);

        //all created pattern
        mPatterns.addAll(PatternCreationLoader.getInstance().getAllCreatedPattern());

        //preloaded pattern
        Pattern vibrationPattern1 = new Pattern();
        vibrationPattern1.title = "Hurricane";
        vibrationPattern1.pic = R.drawable.hurricane;
        vibrationPattern1.pattern = new long[]{0, 100, 25, 5, 100};
        vibrationPattern1.type = Pattern.TYPE_NORMAL;

        mDefaultPattern = vibrationPattern1;


        mPatterns.add(vibrationPattern1);
        Pattern vibrationPattern2 = new Pattern();
        vibrationPattern2.title = "Whirlpool";
        vibrationPattern2.pic = R.drawable.hurricane2;
        vibrationPattern2.type = Pattern.TYPE_NORMAL;
        vibrationPattern2.pattern = new long[]{0, 30, 20, 30, 20, 30, 20, 30, 20, 30, 20, 30, 300, 30, 30, 30, 30};
        mPatterns.add(vibrationPattern2);
        Pattern vibrationPattern3 = new Pattern();
        vibrationPattern3.title = "Storm";
        vibrationPattern3.pic = R.drawable.wave;
        vibrationPattern3.type = Pattern.TYPE_NORMAL;
        vibrationPattern3.pattern = new long[]{0, 30, 50, 30, 50, 30, 50, 30, 50, 30, 50};
        mPatterns.add(vibrationPattern3);
        Pattern vibrationPattern4 = new Pattern();
        vibrationPattern4.title = "Waterfall";
        vibrationPattern4.pic = R.drawable.waterfall;
        vibrationPattern4.type = Pattern.TYPE_NORMAL;
        vibrationPattern4.pattern = new long[]{0, 30, 30, 30, 30, 30, 30, 30, 30, 30, 400};
        mPatterns.add(vibrationPattern4);
        Pattern vibrationPattern9 = new Pattern();
        vibrationPattern9.title = "Volcano";
        vibrationPattern9.pic = R.drawable.volcano;
        vibrationPattern9.pattern = new long[]{0, 50, 30, 50, 30, 50, 50, 50, 200, 50, 30, 50, 30, 50, 400};
        vibrationPattern9.type = Pattern.TYPE_NORMAL;
        mPatterns.add(vibrationPattern9);
        Pattern vibrationPattern29 = new Pattern();
        vibrationPattern29.title = "Rainbow";
        vibrationPattern29.pic = R.drawable.rainbow;
        vibrationPattern29.type = Pattern.TYPE_NORMAL;
        vibrationPattern29.pattern = new long[]{159, 28, 309, 300, 81, 48, 129, 286, 168, 30, 251, 369};
        mPatterns.add(vibrationPattern29);


        Pattern vibrationPattern5 = new Pattern();
        vibrationPattern5.title = "Explosion";
        vibrationPattern5.pic = R.drawable.explosion;
        mPatterns.add(vibrationPattern5);
        Pattern vibrationPattern6 = new Pattern();
        vibrationPattern6.title = "Balance";
        vibrationPattern6.pic = R.drawable.stones;
        mPatterns.add(vibrationPattern6);
        Pattern vibrationPattern7 = new Pattern();
        vibrationPattern7.title = "Wind";
        vibrationPattern7.pic = R.drawable.wind;
        mPatterns.add(vibrationPattern7);
        Pattern vibrationPattern8 = new Pattern();
        vibrationPattern8.title = "Snowflake";
        vibrationPattern8.pic = R.drawable.snowflake;
        mPatterns.add(vibrationPattern8);

        Pattern vibrationPattern10 = new Pattern();
        vibrationPattern10.title = "Fire";
        vibrationPattern10.pic = R.drawable.danger;
        mPatterns.add(vibrationPattern10);
        Pattern vibrationPattern11 = new Pattern();
        vibrationPattern11.title = "Landslide";
        vibrationPattern11.pic = R.drawable.landslide;
        mPatterns.add(vibrationPattern11);
        Pattern vibrationPattern12 = new Pattern();
        vibrationPattern12.title = "Rain";
        vibrationPattern12.pic = R.drawable.rain;
        mPatterns.add(vibrationPattern12);
        Pattern vibrationPattern13 = new Pattern();
        vibrationPattern13.title = "Sun";
        vibrationPattern13.pic = R.drawable.sun;
        mPatterns.add(vibrationPattern13);
        Pattern vibrationPattern14 = new Pattern();
        vibrationPattern14.title = "Collision";
        vibrationPattern14.pic = R.drawable.collision;
        mPatterns.add(vibrationPattern14);
        Pattern vibrationPattern15 = new Pattern();
        vibrationPattern15.title = "Infinity";
        vibrationPattern15.pic = R.drawable.infinity;
        mPatterns.add(vibrationPattern15);
        Pattern vibrationPattern16 = new Pattern();
        vibrationPattern16.title = "Meteorite";
        vibrationPattern16.pic = R.drawable.meteorite;
        mPatterns.add(vibrationPattern16);
        Pattern vibrationPattern17 = new Pattern();
        vibrationPattern17.title = "Moon";
        vibrationPattern17.pic = R.drawable.moon;
        mPatterns.add(vibrationPattern17);
        Pattern vibrationPattern18 = new Pattern();
        vibrationPattern18.title = "Infinity2";
        vibrationPattern18.pic = R.drawable.infinity;
        mPatterns.add(vibrationPattern18);
        Pattern vibrationPattern19 = new Pattern();
        vibrationPattern19.title = "Diamond";
        vibrationPattern19.pic = R.drawable.diamond;
        mPatterns.add(vibrationPattern19);
        Pattern vibrationPattern20 = new Pattern();
        vibrationPattern20.title = "Star";
        vibrationPattern20.pic = R.drawable.star;
        mPatterns.add(vibrationPattern20);
        Pattern vibrationPattern21 = new Pattern();
        vibrationPattern21.title = "Rocket";
        vibrationPattern21.pic = R.drawable.rocket;
        mPatterns.add(vibrationPattern21);
        Pattern vibrationPattern22 = new Pattern();
        vibrationPattern22.title = "Blow";
        vibrationPattern22.pic = R.drawable.blow;
        mPatterns.add(vibrationPattern22);
        Pattern vibrationPattern23 = new Pattern();
        vibrationPattern23.title = "Bomb";
        vibrationPattern23.pic = R.drawable.bomb;
        mPatterns.add(vibrationPattern23);
        Pattern vibrationPattern24 = new Pattern();
        vibrationPattern24.title = "Flash";
        vibrationPattern24.pic = R.drawable.thunder;
        mPatterns.add(vibrationPattern24);
        Pattern vibrationPattern25 = new Pattern();
        vibrationPattern25.title = "Splash";
        vibrationPattern25.pic = R.drawable.splash;
        mPatterns.add(vibrationPattern25);
        Pattern vibrationPattern26 = new Pattern();
        vibrationPattern26.title = "Thunder";
        vibrationPattern26.pic = R.drawable.thunder1;
        mPatterns.add(vibrationPattern26);
        Pattern vibrationPattern27 = new Pattern();
        vibrationPattern27.title = "Drop";
        vibrationPattern27.pic = R.drawable.drop;
        mPatterns.add(vibrationPattern27);
        Pattern vibrationPattern28 = new Pattern();
        vibrationPattern28.title = "Vortex";
        vibrationPattern28.pic = R.drawable.vortex;
        mPatterns.add(vibrationPattern28);

        Pattern vibrationPattern30 = new Pattern();
        vibrationPattern30.title = "Candy";
        vibrationPattern30.pic = R.drawable.candy;
        mPatterns.add(vibrationPattern30);
        Pattern vibrationPattern31 = new Pattern();
        vibrationPattern31.title = "Octopus";
        vibrationPattern31.pic = R.drawable.octopus;
        mPatterns.add(vibrationPattern31);
        Pattern vibrationPattern32 = new Pattern();
        vibrationPattern32.title = "Punch";
        vibrationPattern32.pic = R.drawable.punch;
        mPatterns.add(vibrationPattern32);
        Pattern vibrationPattern33 = new Pattern();
        vibrationPattern33.title = "Roll";
        vibrationPattern33.pic = R.drawable.roll;
        mPatterns.add(vibrationPattern33);
        Pattern vibrationPattern34 = new Pattern();
        vibrationPattern34.title = "Leaf";
        vibrationPattern34.pic = R.drawable.leaf;
        mPatterns.add(vibrationPattern34);
        Pattern vibrationPattern35 = new Pattern();
        vibrationPattern35.title = "Galaxy";
        vibrationPattern35.pic = R.drawable.galaxy;
        mPatterns.add(vibrationPattern35);
        Pattern vibrationPattern36 = new Pattern();
        vibrationPattern36.title = "Blizzard";
        vibrationPattern36.pic = R.drawable.blizzard;
        mPatterns.add(vibrationPattern36);
        Pattern vibrationPattern37 = new Pattern();
        vibrationPattern37.title = "Forest";
        vibrationPattern37.pic = R.drawable.forest;
        mPatterns.add(vibrationPattern37);
        Pattern vibrationPattern38 = new Pattern();
        vibrationPattern38.title = "Drum";
        vibrationPattern38.pic = R.drawable.drum;
        mPatterns.add(vibrationPattern38);
        Pattern vibrationPattern39 = new Pattern();
        vibrationPattern39.title = "Mountains";
        vibrationPattern39.pic = R.drawable.mountains;
        mPatterns.add(vibrationPattern39);
        Pattern vibrationPattern40 = new Pattern();
        vibrationPattern40.title = "Jungle";
        vibrationPattern40.pic = R.drawable.jungle;
        mPatterns.add(vibrationPattern40);
        Pattern vibrationPattern41 = new Pattern();
        vibrationPattern41.title = "Space";
        vibrationPattern41.pic = R.drawable.space;
        mPatterns.add(vibrationPattern41);
        Pattern vibrationPattern42 = new Pattern();
        vibrationPattern42.title = "Hypnosis";
        vibrationPattern42.pic = R.drawable.hypnosis;
        mPatterns.add(vibrationPattern42);
        Pattern vibrationPattern43 = new Pattern();
        vibrationPattern43.title = "Tropic";
        vibrationPattern43.pic = R.drawable.tropic;
        mPatterns.add(vibrationPattern43);
        Pattern vibrationPattern44 = new Pattern();
        vibrationPattern44.title = "Desert";
        vibrationPattern44.pic = R.drawable.desert;
        mPatterns.add(vibrationPattern44);
        Pattern vibrationPattern45 = new Pattern();
        vibrationPattern45.title = "Whale";
        vibrationPattern45.pic = R.drawable.whale;
        mPatterns.add(vibrationPattern45);
        Pattern vibrationPattern46 = new Pattern();
        vibrationPattern46.title = "Eagle";
        vibrationPattern46.pic = R.drawable.eagle;
        mPatterns.add(vibrationPattern46);
    }
}
