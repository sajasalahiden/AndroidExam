package com.exam.android2.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.exam.android2.R;

/**
 * TabFragment — a simple fragment used for each of the three tabs.
 *
 * Receives its tab number via a Bundle argument and displays it
 * so the user can clearly see which tab they are on.
 */
public class TabFragment extends Fragment {

    private static final String ARG_TAB_NUMBER = "tab_number";

    /**
     * Factory method — creates a TabFragment with the given tab number.
     */
    public static TabFragment newInstance(int tabNumber) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TAB_NUMBER, tabNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvTabLabel = view.findViewById(R.id.tv_tab_label);

        int tabNumber = (getArguments() != null)
                ? getArguments().getInt(ARG_TAB_NUMBER, 1)
                : 1;

        String[] tabNames = getResources().getStringArray(R.array.tab_names);
        // tabNames index is 0-based; tabNumber is 1-based.
        if (tabNumber >= 1 && tabNumber <= tabNames.length) {
            tvTabLabel.setText(tabNames[tabNumber - 1]);
        }
    }
}
