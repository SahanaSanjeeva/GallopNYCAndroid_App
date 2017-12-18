package org.gallopnyc.com.gallopnyc;

/**
 * Created by SahanaSanjeeva on 11/30/17.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DonateActivity  extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3donate, container, false);

        return rootView;
    }
}
