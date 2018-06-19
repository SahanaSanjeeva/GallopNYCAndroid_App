package org.gallopnyc.com.gallopnyc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by SahanaSanjeeva on 2/27/18.
 */

public class GridContactAdapter extends BaseAdapter{
    private final Context mContext;
    private final String[] mAddress;
    //private final String[] mLati;
    //private final String[] mLongi;

    public GridContactAdapter(Context context, String[] address) {
        this.mContext = context;
        this.mAddress = address;
       // this.mLati = lati;
       // this.mLongi = longi;
    }

    @Override
    public int getCount() {
        return mAddress.length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String addressText = mAddress[position];
        //final LatLng locPos = new LatLng(Double.parseDouble(mLati[position]), Double.parseDouble(mLongi[position]));

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.contact_grid, null);
        }

        final TextView addressTextView = (TextView)convertView.findViewById(R.id.textView28);
        final Button directionsButton = (Button)convertView.findViewById(R.id.button5);
        /*directionsButton.setOnClickListener(new View.OnClickListener() {
                                                public void onClick(View v) {
                                                    Intent myIntent=new Intent(mContext,DirectionsMap.class);
                                                    mContext.startActivity(myIntent);


                                                    /*mapFragment.getMapAsync(new OnMapReadyCallback() {
                                                        @Override
                                                        public void onMapReady(GoogleMap googleMap) {
                                                            googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

                                                            googleMap.addMarker(new MarkerOptions()
                                                                    .position(locPos)
                                                                    .title(addressText)
                                                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                                                        }
                                                    });*/
        //                                        }
        //                                    });

        addressTextView.setText(addressText);

        return convertView;
    }
}
