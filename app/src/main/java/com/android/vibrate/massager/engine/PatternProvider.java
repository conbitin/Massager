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

    private final ArrayList<Pattern> mPatterns = new ArrayList<>();
    private Pattern mDefaultPattern;


    private PatternProvider() {
        this.prepareList();
    }

    public static PatternProvider getInstance() {
        if (PatternProvider.sInstance == null) {
            synchronized (PatternProvider.class) {
                if (PatternProvider.sInstance == null) {
                    PatternProvider.sInstance = new PatternProvider();
                }
            }
        }
        return PatternProvider.sInstance;
    }

    public ArrayList<Pattern> getPatterns() {
        return this.mPatterns;
    }

    public void refreshListAfterCreateSuccess() {
        this.mPatterns.removeAll(PatternCreationLoader.getInstance().getAllCreatedPattern());
        this.mPatterns.addAll(1, PatternCreationLoader.getInstance().getAllCreatedPattern());
    }

    public Pattern getDefaultPattern() {
        return this.mDefaultPattern;
    }

    public void prepareList() {
        this.mPatterns.clear();

        /*create pattern button*/
        final Pattern vibrationPatternAdd = new Pattern();
        vibrationPatternAdd.title = App.self().getString(R.string.add);
        vibrationPatternAdd.pic = R.drawable.ic_pattern_add;
        vibrationPatternAdd.type = Pattern.TYPE_CREATE;
        vibrationPatternAdd.pattern = new long[]{};
        this.mPatterns.add(vibrationPatternAdd);

        //all created pattern
        this.mPatterns.addAll(PatternCreationLoader.getInstance().getAllCreatedPattern());

        //preloaded pattern
        final Pattern vibrationPattern1 = new Pattern();
        vibrationPattern1.title = "Hurricane";
        vibrationPattern1.pic = R.drawable.hurricane;
        vibrationPattern1.pattern = new long[]{0, 100, 25, 5, 100};
        vibrationPattern1.type = Pattern.TYPE_NORMAL;

        this.mDefaultPattern = vibrationPattern1;


        this.mPatterns.add(vibrationPattern1);
        final Pattern vibrationPattern2 = new Pattern();
        vibrationPattern2.title = "Whirlpool";
        vibrationPattern2.pic = R.drawable.hurricane2;
        vibrationPattern2.type = Pattern.TYPE_NORMAL;
        vibrationPattern2.pattern = new long[]{0, 30, 20, 30, 20, 30, 20, 30, 20, 30, 20, 30, 300, 30, 30, 30, 30};
        this.mPatterns.add(vibrationPattern2);
        final Pattern vibrationPattern3 = new Pattern();
        vibrationPattern3.title = "Storm";
        vibrationPattern3.pic = R.drawable.wave;
        vibrationPattern3.type = Pattern.TYPE_NORMAL;
        vibrationPattern3.pattern = new long[]{0, 30, 50, 30, 50, 30, 50, 30, 50, 30, 50};
        this.mPatterns.add(vibrationPattern3);
        final Pattern vibrationPattern4 = new Pattern();
        vibrationPattern4.title = "Waterfall";
        vibrationPattern4.pic = R.drawable.waterfall;
        vibrationPattern4.type = Pattern.TYPE_NORMAL;
        vibrationPattern4.pattern = new long[]{0, 30, 30, 30, 30, 30, 30, 30, 30, 30, 400};
        this.mPatterns.add(vibrationPattern4);
        final Pattern vibrationPattern9 = new Pattern();
        vibrationPattern9.title = "Volcano";
        vibrationPattern9.pic = R.drawable.volcano;
        vibrationPattern9.pattern = new long[]{0, 50, 30, 50, 30, 50, 50, 50, 200, 50, 30, 50, 30, 50, 400};
        vibrationPattern9.type = Pattern.TYPE_NORMAL;
        this.mPatterns.add(vibrationPattern9);
        final Pattern vibrationPattern29 = new Pattern();
        vibrationPattern29.title = "Rainbow";
        vibrationPattern29.pic = R.drawable.rainbow;
        vibrationPattern29.type = Pattern.TYPE_NORMAL;
        vibrationPattern29.pattern = new long[]{159, 28, 309, 300, 81, 48, 129, 286, 168, 30, 251, 369};
        this.mPatterns.add(vibrationPattern29);


        final Pattern vibrationPattern5 = new Pattern();
        vibrationPattern5.title = "Explosion";
        vibrationPattern5.pic = R.drawable.explosion;
        this.mPatterns.add(vibrationPattern5);
        final Pattern vibrationPattern6 = new Pattern();
        vibrationPattern6.title = "Balance";
        vibrationPattern6.pic = R.drawable.stones;
        this.mPatterns.add(vibrationPattern6);
        final Pattern vibrationPattern7 = new Pattern();
        vibrationPattern7.title = "Wind";
        vibrationPattern7.pic = R.drawable.wind;
        this.mPatterns.add(vibrationPattern7);
        final Pattern vibrationPattern8 = new Pattern();
        vibrationPattern8.title = "Snowflake";
        vibrationPattern8.pic = R.drawable.snowflake;
        this.mPatterns.add(vibrationPattern8);

        final Pattern vibrationPattern10 = new Pattern();
        vibrationPattern10.title = "Fire";
        vibrationPattern10.pic = R.drawable.danger;
        this.mPatterns.add(vibrationPattern10);
        final Pattern vibrationPattern11 = new Pattern();
        vibrationPattern11.title = "Landslide";
        vibrationPattern11.pic = R.drawable.landslide;
        this.mPatterns.add(vibrationPattern11);
        final Pattern vibrationPattern12 = new Pattern();
        vibrationPattern12.title = "Rain";
        vibrationPattern12.pic = R.drawable.rain;
        this.mPatterns.add(vibrationPattern12);
        final Pattern vibrationPattern13 = new Pattern();
        vibrationPattern13.title = "Sun";
        vibrationPattern13.pic = R.drawable.sun;
        this.mPatterns.add(vibrationPattern13);
        final Pattern vibrationPattern14 = new Pattern();
        vibrationPattern14.title = "Collision";
        vibrationPattern14.pic = R.drawable.collision;
        this.mPatterns.add(vibrationPattern14);
        final Pattern vibrationPattern15 = new Pattern();
        vibrationPattern15.title = "Infinity";
        vibrationPattern15.pic = R.drawable.infinity;
        this.mPatterns.add(vibrationPattern15);
        final Pattern vibrationPattern16 = new Pattern();
        vibrationPattern16.title = "Meteorite";
        vibrationPattern16.pic = R.drawable.meteorite;
        this.mPatterns.add(vibrationPattern16);
        final Pattern vibrationPattern17 = new Pattern();
        vibrationPattern17.title = "Moon";
        vibrationPattern17.pic = R.drawable.moon;
        this.mPatterns.add(vibrationPattern17);
        final Pattern vibrationPattern18 = new Pattern();
        vibrationPattern18.title = "Infinity2";
        vibrationPattern18.pic = R.drawable.infinity;
        this.mPatterns.add(vibrationPattern18);
        final Pattern vibrationPattern19 = new Pattern();
        vibrationPattern19.title = "Diamond";
        vibrationPattern19.pic = R.drawable.diamond;
        this.mPatterns.add(vibrationPattern19);
        final Pattern vibrationPattern20 = new Pattern();
        vibrationPattern20.title = "Star";
        vibrationPattern20.pic = R.drawable.star;
        this.mPatterns.add(vibrationPattern20);
        final Pattern vibrationPattern21 = new Pattern();
        vibrationPattern21.title = "Rocket";
        vibrationPattern21.pic = R.drawable.rocket;
        this.mPatterns.add(vibrationPattern21);
        final Pattern vibrationPattern22 = new Pattern();
        vibrationPattern22.title = "Blow";
        vibrationPattern22.pic = R.drawable.blow;
        this.mPatterns.add(vibrationPattern22);
        final Pattern vibrationPattern23 = new Pattern();
        vibrationPattern23.title = "Bomb";
        vibrationPattern23.pic = R.drawable.bomb;
        this.mPatterns.add(vibrationPattern23);
        final Pattern vibrationPattern24 = new Pattern();
        vibrationPattern24.title = "Flash";
        vibrationPattern24.pic = R.drawable.thunder;
        this.mPatterns.add(vibrationPattern24);
        final Pattern vibrationPattern25 = new Pattern();
        vibrationPattern25.title = "Splash";
        vibrationPattern25.pic = R.drawable.splash;
        this.mPatterns.add(vibrationPattern25);
        final Pattern vibrationPattern26 = new Pattern();
        vibrationPattern26.title = "Thunder";
        vibrationPattern26.pic = R.drawable.thunder1;
        this.mPatterns.add(vibrationPattern26);
        final Pattern vibrationPattern27 = new Pattern();
        vibrationPattern27.title = "Drop";
        vibrationPattern27.pic = R.drawable.drop;
        this.mPatterns.add(vibrationPattern27);
        final Pattern vibrationPattern28 = new Pattern();
        vibrationPattern28.title = "Vortex";
        vibrationPattern28.pic = R.drawable.vortex;
        this.mPatterns.add(vibrationPattern28);

        final Pattern vibrationPattern30 = new Pattern();
        vibrationPattern30.title = "Candy";
        vibrationPattern30.pic = R.drawable.candy;
        this.mPatterns.add(vibrationPattern30);
        final Pattern vibrationPattern31 = new Pattern();
        vibrationPattern31.title = "Octopus";
        vibrationPattern31.pic = R.drawable.octopus;
        this.mPatterns.add(vibrationPattern31);
        final Pattern vibrationPattern32 = new Pattern();
        vibrationPattern32.title = "Punch";
        vibrationPattern32.pic = R.drawable.punch;
        this.mPatterns.add(vibrationPattern32);
        final Pattern vibrationPattern33 = new Pattern();
        vibrationPattern33.title = "Roll";
        vibrationPattern33.pic = R.drawable.roll;
        this.mPatterns.add(vibrationPattern33);
        final Pattern vibrationPattern34 = new Pattern();
        vibrationPattern34.title = "Leaf";
        vibrationPattern34.pic = R.drawable.leaf;
        this.mPatterns.add(vibrationPattern34);
        final Pattern vibrationPattern35 = new Pattern();
        vibrationPattern35.title = "Galaxy";
        vibrationPattern35.pic = R.drawable.galaxy;
        this.mPatterns.add(vibrationPattern35);
        final Pattern vibrationPattern36 = new Pattern();
        vibrationPattern36.title = "Blizzard";
        vibrationPattern36.pic = R.drawable.blizzard;
        this.mPatterns.add(vibrationPattern36);
        final Pattern vibrationPattern37 = new Pattern();
        vibrationPattern37.title = "Forest";
        vibrationPattern37.pic = R.drawable.forest;
        this.mPatterns.add(vibrationPattern37);
        final Pattern vibrationPattern38 = new Pattern();
        vibrationPattern38.title = "Drum";
        vibrationPattern38.pic = R.drawable.drum;
        this.mPatterns.add(vibrationPattern38);
        final Pattern vibrationPattern39 = new Pattern();
        vibrationPattern39.title = "Mountains";
        vibrationPattern39.pic = R.drawable.mountains;
        this.mPatterns.add(vibrationPattern39);
        final Pattern vibrationPattern40 = new Pattern();
        vibrationPattern40.title = "Jungle";
        vibrationPattern40.pic = R.drawable.jungle;
        this.mPatterns.add(vibrationPattern40);
        final Pattern vibrationPattern41 = new Pattern();
        vibrationPattern41.title = "Space";
        vibrationPattern41.pic = R.drawable.space;
        this.mPatterns.add(vibrationPattern41);
        final Pattern vibrationPattern42 = new Pattern();
        vibrationPattern42.title = "Hypnosis";
        vibrationPattern42.pic = R.drawable.hypnosis;
        this.mPatterns.add(vibrationPattern42);
        final Pattern vibrationPattern43 = new Pattern();
        vibrationPattern43.title = "Tropic";
        vibrationPattern43.pic = R.drawable.tropic;
        this.mPatterns.add(vibrationPattern43);
        final Pattern vibrationPattern44 = new Pattern();
        vibrationPattern44.title = "Desert";
        vibrationPattern44.pic = R.drawable.desert;
        this.mPatterns.add(vibrationPattern44);
        final Pattern vibrationPattern45 = new Pattern();
        vibrationPattern45.title = "Whale";
        vibrationPattern45.pic = R.drawable.whale;
        this.mPatterns.add(vibrationPattern45);
        final Pattern vibrationPattern46 = new Pattern();
        vibrationPattern46.title = "Eagle";
        vibrationPattern46.pic = R.drawable.eagle;
        this.mPatterns.add(vibrationPattern46);
    }
}
