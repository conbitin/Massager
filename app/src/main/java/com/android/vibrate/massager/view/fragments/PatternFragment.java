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
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        this.mBinding = FragmentPatternBinding.inflate(inflater, container, false);
        this.mBinding.patternGridView.setAdapter(this.mPatternAdapter = new PatternAdapter(this.getActivity(), R.layout.pattern_item, PatternProvider.getInstance().getPatterns(), this.mAppViewModel));
        this.mAppViewModel.selectedPatternData().observe(this.getViewLifecycleOwner(), pattern -> {
            if (pattern != null && pattern.type == Pattern.TYPE_NORMAL || pattern.type == Pattern.TYPE_CUSTOM) {
                this.mBinding.selectedPatternName.setText(pattern.title);
            } else {
                this.mBinding.selectedPatternName.setText(this.getString(R.string.undefined));
            }
        });
        return this.mBinding.getRoot();
    }

    @Override
    public void handleEvent(final Event event) {
        super.handleEvent(event);
        switch (event.getEventCode()) {
            case Event.EVENT_SUCCESS_PATTERN_CREATE:
                this.mPatternAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.mBinding = null;
    }

    private static class PatternAdapter extends ArrayAdapter<Pattern> {
        AppViewModel viewModel;

        public PatternAdapter(@NonNull final Context context, final int resource, @NonNull final List list, final AppViewModel viewModel) {
            super(context, resource, list);
            this.viewModel = viewModel;
        }

        @Override
        public View getView(final int i, View view, final ViewGroup viewGroup) {
            PatternItemBinding binding = null;
            if (view == null) {
                final LayoutInflater inflater = LayoutInflater.from(this.getContext());
                view = inflater.inflate(R.layout.pattern_item, viewGroup, false);
            }
            final Pattern pattern = this.getItem(i);
            binding = PatternItemBinding.bind(view);
            binding.patternImage.setImageResource(pattern.pic);
            binding.patternLock.setVisibility(pattern.type == Pattern.TYPE_LOCK ? View.VISIBLE : View.INVISIBLE);
            binding.patternName.setText(pattern.title);
            binding.getRoot().setOnClickListener(item -> {
                if (pattern.type == Pattern.TYPE_NORMAL || pattern.type == Pattern.TYPE_CUSTOM) {
                    this.viewModel.updateVibrationState(true);
                    this.viewModel.updateSelectedPattern(pattern);
                    VibrationEngine.getInstance().playPattern(pattern.pattern);
                } else if (pattern.type == Pattern.TYPE_CREATE) {
                    this.viewModel.updateVibrationState(false);
                    VibrationEngine.getInstance().stop();
                    AppObservable.getInstance().notifyObservers(Event.of(Event.EVENT_SHOW_CREATE_VIBRATION));
                } else if (pattern.type == Pattern.TYPE_LOCK) {
                    this.viewModel.updateVibrationState(false);
                    VibrationEngine.getInstance().stop();
                    AppObservable.getInstance().notifyObservers(Event.of(Event.EVENT_SHOW_UNLOCK_EVERYTHING, pattern.title));
                }
            });
            return view;
        }
    }
}
