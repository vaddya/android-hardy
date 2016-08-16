package ru.vaddya.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by Vadim on 8/10/2016.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
