package com.tddrampup.fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.TextView;

import com.tddrampup.R;
import com.tddrampup.models.Address;
import com.tddrampup.models.Listing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.api.ANDROID.assertThat;

/**
 * Created by WX009-PC on 2/21/14.
 */
@RunWith(RobolectricTestRunner.class)

public class DetailFragmentTest{
    private Activity activity;
    private DetailFragment detailFragment;
    private Listing listing;
    private TextView nameTextView;
    private TextView locationTextView;
    private TextView websiteTextView;

    private Listing createListing() {
        Listing one = new Listing();
        Address addOne = new Address();
        addOne.setCity("Toronto");
        addOne.setStreet("Street");
        one.setName("One");
        one.setAddress(addOne);
        one.setMerchantUrl("www.herpaderp.com");
        return one;
    }

    private void addFragment() {
        FragmentManager fragmentManager = activity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        listing = createListing();
        detailFragment = new DetailFragment(listing);
        fragmentTransaction.add(detailFragment, null);
        fragmentTransaction.commit();
    }


    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(Activity.class).create().visible().get();
        addFragment();
        nameTextView = (TextView) detailFragment.getView().findViewById(R.id.name_detail_fragment);
        locationTextView = (TextView) detailFragment.getView().findViewById(R.id.location_detail_fragment);
        websiteTextView = (TextView) detailFragment.getView().findViewById(R.id.website_detail_fragment);

    }

    @Test
    public void getName_shouldMatchListing() throws Exception {
        assertThat(nameTextView).hasText("One");
    }

    @Test
    public void getLocation_shouldMatchListing() throws Exception {
        assertThat(locationTextView).hasText(listing.getAddress().getStreet() + ", " + listing.getAddress().getCity() + ", " + listing.getAddress().getProv() + ", " + listing.getAddress().getPcode());
    }

    @Test
    public void getWebsite_shouldMatchListing() throws Exception {
        assertThat(websiteTextView).hasText("www.herpaderp.com");
    }
}
