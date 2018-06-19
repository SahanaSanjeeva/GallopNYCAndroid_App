package org.gallopnyc.com.gallopnyc;

/**
 * Created by SahanaSanjeeva on 11/30/17.
 */

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalPaymentDetails;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

public class DonateActivity  extends Fragment {
    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_NO_NETWORK;

    private static final String CONFIG_CLIENT_ID = "AZo-Ytk0kCAZfJfDoD_jDJ9wto3QWlcRtGsKjT69ZG_J6Aq1SOtsWx164MkRqoQUQfUHuC3kQtBilczv";

    private static final int REQUEST_CODE_PAYMENT = 1;
    private static final int REQUEST_CODE_FUTURE_PAYMENT = 2;
    private static final int REQUEST_CODE_PROFILE_SHARING = 3;

    Button donateBtnTop, donateBtnBtm;
    EditText donateAmtTop, donateAmtBtm;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)
            .clientId(CONFIG_CLIENT_ID);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3donate, container, false);

        donateAmtTop = (EditText) rootView.findViewById(R.id.donateAmount);
        donateAmtBtm = (EditText) rootView.findViewById(R.id.donateAmountBtm);
        donateBtnTop = (Button) rootView.findViewById(R.id.donateBtnTop);
        donateBtnBtm = (Button) rootView.findViewById(R.id.donateBtnBtm);

        super.onCreate(savedInstanceState);

        Intent intent = new Intent(getActivity(), PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        getActivity().startService(intent);

        donateBtnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePayment(v);
            }
        });

        donateBtnBtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePayment(v);
            }
        });
        return rootView;
    }

    public void makePayment(View pressedView) {
        String donateAmt = "0.00";

        if (pressedView.getId() == R.id.donateBtnTop) {
            donateAmt = donateAmtTop.getText().toString();
        } else {
            donateAmt = donateAmtBtm.getText().toString();
        }

        PayPalPayment payment = new PayPalPayment(new BigDecimal(donateAmt), "USD", "Amount being donated",
                PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(getActivity(), PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(intent, REQUEST_CODE_PAYMENT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {
                    Log.i("JSON Response", confirm.toJSONObject().toString(4));

                    String paymentDetails = confirm.toJSONObject().toString(4);
                    Toast.makeText(
                            getActivity().getApplicationContext(),
                            "PaymentConfirmation info received from PayPal", Toast.LENGTH_LONG)
                            .show();

                    donateAmtTop.setText("");
                    donateAmtBtm.setText("");
                } catch (JSONException e) {
                    Log.e("JSON Exception", "an extremely unlikely failure occurred: ", e);
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("Cancelled Result", "The user canceled.");
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Log.i("Invalid Result Extras", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
        }
    }

    @Override
    public void onDestroy() {
        getActivity().stopService(new Intent(getActivity(), PayPalService.class));
        super.onDestroy();
    }
}
