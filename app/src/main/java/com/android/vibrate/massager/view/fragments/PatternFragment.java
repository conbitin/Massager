/*
 * Copyright (c) 2023
 * Create by  on 4/21/23, 11:20 AM
 * Last modified 4/21/23, 11:20 AM
 */

package com.android.vibrate.massager.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.vibrate.massager.R;
import com.android.vibrate.massager.core.AppObservable;
import com.android.vibrate.massager.core.Event;
import com.android.vibrate.massager.databinding.FragmentPatternBinding;
import com.android.vibrate.massager.databinding.PatternItemBinding;
import com.android.vibrate.massager.engine.Pattern;
import com.android.vibrate.massager.engine.PatternProvider;
import com.android.vibrate.massager.engine.VibrationEngine;
import com.android.vibrate.massager.view.model.AppViewModel;

import java.util.List;

public class PatternFragment extends BaseFragment {
    private FragmentPatternBinding mBinding;
    private PatternAdapter mPatternAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentPatternBinding.inflate(inflater, container, false);
        mBinding.patternGridView.setAdapter(mPatternAdapter = new PatternAdapter(getActivity(), R.layout.pattern_item, PatternProvider.getInstance().getPatterns(), mAppViewModel));
        mAppViewModel.selectedPatternData().observe(getViewLifecycleOwner(), pattern -> {
            if (pattern != null && pattern.type == Pattern.TYPE_NORMAL || pattern.type == Pattern.TYPE_CUSTOM) {
                mBinding.selectedPatternName.setText(pattern.title);
            } else {
                mBinding.selectedPatternName.setText(getString(R.string.undefined));
            }
        });
        return mBinding.getRoot();
    }

    @Override
    public void handleEvent(Event event) {
        super.handleEvent(event);
        switch (event.getEventCode()) {
            case Event.EVENT_SUCCESS_PATTERN_CREATE:
                mPatternAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    private static class PatternAdapter extends ArrayAdapter<Pattern> {
        AppViewModel viewModel;

        public PatternAdapter(@NonNull Context context, int resource, @NonNull List list, AppViewModel viewModel) {
            super(context, resource, list);
            this.viewModel = viewModel;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            PatternItemBinding binding = null;
            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                view = inflater.inflate(R.layout.pattern_item, viewGroup, false);
            }
            Pattern pattern = getItem(i);
            binding = PatternItemBinding.bind(view);
            binding.patternImage.setImageResource(pattern.pic);
            binding.patternLock.setVisibility(pattern.type == Pattern.TYPE_LOCK ? View.VISIBLE : View.INVISIBLE);
            binding.patternName.setText(pattern.title);
            binding.getRoot().setOnClickListener(item -> {
                if (pattern.type == Pattern.TYPE_NORMAL || pattern.type == Pattern.TYPE_CUSTOM) {
                    viewModel.updateVibrationState(true);
                    viewModel.updateSelectedPattern(pattern);
                    VibrationEngine.getInstance().playPattern(pattern.pattern);
                } else if (pattern.type == Pattern.TYPE_CREATE) {
                    viewModel.updateVibrationState(false);
                    VibrationEngine.getInstance().stop();
                    AppObservable.getInstance().notifyObservers(Event.of(Event.EVENT_SHOW_CREATE_VIBRATION));
                } else if (pattern.type == Pattern.TYPE_LOCK) {
                    viewModel.updateVibrationState(false);
                    VibrationEngine.getInstance().stop();
                    AppObservable.getInstance().notifyObservers(Event.of(Event.EVENT_SHOW_UNLOCK_EVERYTHING, pattern.title));
                }
            });
            return view;
        }
    }
}
