package com.example.notify.Student;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewpagerAdapter extends FragmentStatePagerAdapter
{
    List<String> listtitle=new ArrayList<>();
    List<Fragment>listfragment=new ArrayList<>();

    public ViewpagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public ViewpagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listfragment.get(position);
    }

    @Override
    public int getCount() {
        return listtitle.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return listtitle.get(position);
    }

    public void addfragment(Fragment fragment,String title)
    {
        listfragment.add(fragment);
        listtitle.add(title);
    }
}
