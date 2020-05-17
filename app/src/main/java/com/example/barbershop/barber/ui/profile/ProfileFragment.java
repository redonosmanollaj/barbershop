package com.example.barbershop.barber.ui.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.barbershop.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private ImageView ivAvatar;
    private TextView tvFullname;
    private TabLayout profileTabLayout;
    private ViewPager profileViewPager;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_barber_profile,container,false);

        String userName = getArguments().getString("name");
        tvFullname = root.findViewById(R.id.tv_fullname);
        tvFullname.setText(userName);

        profileTabLayout = root.findViewById(R.id.tablayout_profile);

        profileViewPager = root.findViewById(R.id.profile_viewpager);
        setupViewPager();

        profileTabLayout.setupWithViewPager(profileViewPager);


        return root;
    }

    private void setupViewPager(){
        TabAdapter tabAdapter = new TabAdapter(getChildFragmentManager());
        tabAdapter.addFragment(new InfoFragment(),getString(R.string.info_tab));
        tabAdapter.addFragment(new ReviewsFragment(),getString(R.string.reviews_tab));
        tabAdapter.addFragment(new ServicesFragment(),getString(R.string.services_tab));
        profileViewPager.setAdapter(tabAdapter);
    }

    class TabAdapter extends FragmentPagerAdapter{

        List<Fragment> fragmentList = new ArrayList<>();
        List<String> fragmentTitleList = new ArrayList<>();

        public TabAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }

        private void addFragment(Fragment fragment, String title){
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }
    }


}
