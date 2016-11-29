package cl.adachersoft.dotaheroeslist.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cl.adachersoft.dotaheroeslist.FavoritesFragment;
import cl.adachersoft.dotaheroeslist.HeroesFragment;
import cl.adachersoft.dotaheroeslist.R;

/**
 * Created by cristian on 28-11-2016.
 */

public class PageAdapter extends FragmentPagerAdapter {

    private Context context;

    public PageAdapter(FragmentManager fm) {
        super(fm);

    }

    public PageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HeroesFragment.newInstance();
            case 1:
                return FavoritesFragment.newInstance();
            default:
                return HeroesFragment.newInstance();

        }

    }

    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.Heroes);
            case 1:
                return context.getString(R.string.Favorite);

            default:
                return context.getString(R.string.Heroes);
        }


    }
}