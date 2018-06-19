package org.gallopnyc.com.gallopnyc;

/**
 * Created by SahanaSanjeeva on 11/30/17.
 */

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class ContactActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab6contact, container, false);

        Resources res = getResources();
        String[] address = res.getStringArray(R.array.gridaddress);
        //String[] latCoord = res.getStringArray(R.array.loclatdoub);
        //String[] longCoord = res.getStringArray(R.array.loclongdoub);
        Context context = getActivity();

        GridView gridView = (GridView)rootView.findViewById(R.id.gridView1);
        GridContactAdapter contactsAdapter = new GridContactAdapter(context, address);
        gridView.setAdapter(contactsAdapter);

        return rootView;
    }
}
