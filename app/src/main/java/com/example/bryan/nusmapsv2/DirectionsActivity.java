package com.example.bryan.nusmapsv2;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.Calendar;

public class DirectionsActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {
    private static final String LOG_TAG = "DirectionsActivity";
    private static final int GOOGLE_API_CLIENT_ID = 0;
    private static final int test = 3;
    private static final int testCar = 4;
    private AutoCompleteTextView editOrigin;
    private AutoCompleteTextView editDestination;
    private Button btnWalk;
    private Button btnCar;
    private GoogleApiClient mGoogleApiClient;
    private PlaceArrayAdapter mPlaceArrayAdapter;
    private static final LatLngBounds NUS = new LatLngBounds(
            new LatLng(1.289090, 103.767136), new LatLng(1.310065, 103.786232));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);
        mGoogleApiClient = new GoogleApiClient.Builder(DirectionsActivity.this)
                .addApi(Places.GEO_DATA_API)
                .enableAutoManage(this, GOOGLE_API_CLIENT_ID, this)
                .addConnectionCallbacks(this)
                .build();

        //Handling the buttons
        editOrigin = (AutoCompleteTextView) findViewById(R.id.startingPoint);
        editDestination = (AutoCompleteTextView) findViewById(R.id.destination);
        btnWalk = (Button) findViewById(R.id.walk);
        btnCar = (Button) findViewById(R.id.buttonCar);

        mPlaceArrayAdapter = new PlaceArrayAdapter(this, android.R.layout.simple_list_item_1,
                NUS, null);

        editOrigin.setOnItemClickListener(mAutocompleteClickListener);
        editOrigin.setAdapter(mPlaceArrayAdapter);

        editDestination.setOnItemClickListener(mAutocompleteClickListener);
        editDestination.setAdapter(mPlaceArrayAdapter);

        //initialise walking button
        btnWalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(editOrigin.getText().toString().trim())) {
                    Toast.makeText(DirectionsActivity.this, "Enter the starting point", Toast.LENGTH_SHORT).show();
                } else if ("".equals(editDestination.getText().toString().trim())) {
                    Toast.makeText(DirectionsActivity.this, "Enter the destination point", Toast.LENGTH_SHORT).show();
                } else {
                    final Intent intent = new Intent(DirectionsActivity.this, MapsActivity.class); //if both starting point and destination not empty, create intent to mapsactivity
                    intent.putExtra("FROM", editOrigin.getText().toString().trim());
                    intent.putExtra("TO", editDestination.getText().toString().trim());
                    intent.putExtra("TEST", test);   //test = 3

                    DirectionsActivity.this.startActivity(intent);
                }
            }
        });

        //initialise car button
        btnCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(editOrigin.getText().toString().trim())) {
                    Toast.makeText(DirectionsActivity.this, "Enter the starting point", Toast.LENGTH_SHORT).show();
                } else if ("".equals(editDestination.getText().toString().trim())) {
                    Toast.makeText(DirectionsActivity.this, "Enter the destination point", Toast.LENGTH_SHORT).show();
                } else {
                    final Intent intent = new Intent(DirectionsActivity.this, MapsActivity.class); //if both starting point and destination not empty, create intent to mapsactivity
                    intent.putExtra("FROM", editOrigin.getText().toString().trim());
                    intent.putExtra("TO", editDestination.getText().toString().trim());
                    intent.putExtra("TEST", testCar);

                    DirectionsActivity.this.startActivity(intent);
                }
            }
        });
    }

    private AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final PlaceArrayAdapter.PlaceAutocomplete item = mPlaceArrayAdapter.getItem(position);
            final String placeId = String.valueOf(item.placeId);
            Log.i(LOG_TAG, "Selected: " + item.description);
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
            Log.i(LOG_TAG, "Fetching details for ID: " + item.placeId);
        }
    };

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                Log.e(LOG_TAG, "Place query did not complete. Error: " +
                        places.getStatus().toString());
                return;
            }
            // Selecting the first object buffer.
            final Place place = places.get(0);
            CharSequence attributions = places.getAttributions();
/**
            mNameTextView.setText(Html.fromHtml(place.getName() + ""));
            mAddressTextView.setText(Html.fromHtml(place.getAddress() + ""));
            mIdTextView.setText(Html.fromHtml(place.getId() + ""));
            mPhoneTextView.setText(Html.fromHtml(place.getPhoneNumber() + ""));
            mWebTextView.setText(place.getWebsiteUri() + "");
            if (attributions != null) {
                mAttTextView.setText(Html.fromHtml(attributions.toString()));
            }
 */
        }
    };

    @Override
    public void onConnected(Bundle bundle) {
        mPlaceArrayAdapter.setGoogleApiClient(mGoogleApiClient);
        Log.i(LOG_TAG, "Google Places API connected.");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(LOG_TAG, "Google Places API connection failed with error code: "
                + connectionResult.getErrorCode());

        Toast.makeText(this,
                "Google Places API connection failed with error code:" +
                        connectionResult.getErrorCode(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mPlaceArrayAdapter.setGoogleApiClient(null);
        Log.e(LOG_TAG, "Google Places API connection suspended.");
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
        }
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }


}
