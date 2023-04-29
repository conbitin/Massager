/*
 * Copyright (c) 2023
 * Create by  on 4/25/23, 1:46 PM
 * Last modified 4/25/23, 1:46 PM
 */

package com.android.vibrate.massager.view.dialogs;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import com.android.vibrate.massager.R;
import com.android.vibrate.massager.core.AppObservable;
import com.android.vibrate.massager.core.Event;
import com.android.vibrate.massager.databinding.DialogPatternCreatorBinding;
import com.android.vibrate.massager.engine.Pattern;
import com.android.vibrate.massager.engine.PatternCreationLoader;
import com.android.vibrate.massager.engine.VibrationEngine;

import java.util.ArrayList;
import java.util.Observable;

public class CreationPatternDialog extends BaseDialog {
    DialogPatternCreatorBinding binding;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private Runnable mPlayCompletedRunnable = () -> {
        binding.patternCreatorStatus.setText(R.string.done);
        binding.positiveButton.setText(R.string.save);
        binding.positiveButton.setAlpha(1.0f);
        binding.positiveButton.setEnabled(true);
        //binding.patternCreatorControl.setVisibility(View.GONE);
    };

    public CreationPatternDialog(@NonNull Activity activity) {
        super(activity);
        setContentView((binding = DialogPatternCreatorBinding.inflate(activity.getLayoutInflater())).getRoot());
        binding.backButton.setOnClickListener(view -> {
            dismiss();
        });
        binding.negativeButton.setOnClickListener(view -> {
            dismiss();
        });
        binding.positiveButton.setOnClickListener(view -> {
            if (!isStoppedRecording) {
                if (waveForm != null && !waveForm.isEmpty()) {
                    isStoppedRecording = true;
                    VibrationEngine.getInstance().playPatternNoRepeat(getRecordingPattern());
                    binding.patternCreatorStatus.setText(R.string.playing);
                    binding.textPatternControlStatus.setText(R.string.again);
                    binding.imgPatternControlStatus.setSelected(true);

                    binding.positiveButton.setText(R.string.save);
                    binding.positiveButton.setEnabled(false);
                    binding.positiveButton.setAlpha(0.5f);

                    mHandler.removeCallbacks(mPlayCompletedRunnable);
                    mHandler.postDelayed(mPlayCompletedRunnable, getDuration());
                }
            } else {
                long[] recordedPattern = getRecordingPattern();
                if (recordedPattern != null && recordedPattern.length > 0) {
                    Pattern pattern = new Pattern();
                    pattern.title = binding.inputName.getText().toString();
                    pattern.type = Pattern.TYPE_CUSTOM;
                    pattern.pattern = recordedPattern;
                    PatternCreationLoader.getInstance().saveCreatedPattern(pattern);
                    AppObservable.getInstance().notifyObservers(Event.of(Event.EVENT_SUCCESS_PATTERN_CREATE));
                    dismiss();
                }
            }
        });
        binding.creatorPatternView.setOnTouchListener(onTouchListener);


        binding.patternCreatorControl.setOnClickListener(view -> {
            if (!isStoppedRecording) {
                if (waveForm != null && !waveForm.isEmpty()) {
                    isStoppedRecording = true;
                    VibrationEngine.getInstance().playPatternNoRepeat(getRecordingPattern());
                    binding.patternCreatorStatus.setText(R.string.playing);
                    binding.textPatternControlStatus.setText(R.string.again);
                    binding.imgPatternControlStatus.setSelected(true);

                    binding.positiveButton.setText(R.string.save);
                    binding.positiveButton.setEnabled(false);
                    binding.positiveButton.setAlpha(0.5f);

                    mHandler.removeCallbacks(mPlayCompletedRunnable);
                    mHandler.postDelayed(mPlayCompletedRunnable, getDuration());
                }
            } else {
                if (waveForm != null && !waveForm.isEmpty()) {
                    VibrationEngine.getInstance().playPatternNoRepeat(getRecordingPattern());
                    binding.patternCreatorStatus.setText(R.string.playing);

                    binding.positiveButton.setText(R.string.save);
                    binding.positiveButton.setEnabled(false);
                    binding.positiveButton.setAlpha(0.5f);

                    mHandler.removeCallbacks(mPlayCompletedRunnable);
                    mHandler.postDelayed(mPlayCompletedRunnable, getDuration());
                }
            }
        });
        setCancelable(true);
    }

    @Override
    public void onAttachedToWindow() {
        AppObservable.getInstance().addObserver(this);
        super.onAttachedToWindow();
    }

    @Override
    public void update(Observable observable, Object data) {
        if (data == null || !(data instanceof Event)) return;
        Event event = (Event) data;
        switch (event.getEventCode()) {
            case Event.EVENT_START_RECORDING_PATTERN:

                break;
        }
    }

    @Override
    public void onDetachedFromWindow() {
        AppObservable.getInstance().deleteObserver(this);
        super.onDetachedFromWindow();
    }


    ArrayList<Long> waveForm = new ArrayList<>();
    boolean started;
    long timeDown;
    long timeUp;

    public long[] getRecordingPattern() {
        long[] pattern = new long[waveForm.size()];
        for (int i = 0; i < waveForm.size(); i++) {
            pattern[i] = waveForm.get(i);
        }
        return pattern;
    }

    public long getDuration() {
       long sum = 0;
        for (int i = 0; i < waveForm.size(); i++) {
            sum += waveForm.get(i);
        }
        return sum;
    }


    @Override
    public void dismiss() {
        VibrationEngine.getInstance().stop();
        super.dismiss();
    }

    @Override
    public void cancel() {
        VibrationEngine.getInstance().stop();
        super.cancel();
    }

    boolean isStoppedRecording = false;

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (isStoppedRecording) return false;
            Log.i("CreatorPatternView", "onTouch " + started + " action: " + motionEvent.getAction() + " timeUp: " + timeUp + " timeDown:" + timeDown);
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    //add break time
                    timeDown = System.currentTimeMillis();
                    if (started) {
                        if (waveForm.isEmpty()) {
                            waveForm.add(0l);
                        } else {
                            waveForm.add(timeDown - timeUp);
                        }
                        VibrationEngine.getInstance().vibrate();
                        binding.patternCreatorControl.setVisibility(View.VISIBLE);
                        binding.textPatternControlStatus.setText(R.string.stop);
                        binding.imgPatternControlStatus.setSelected(false);

                        binding.positiveButton.setText(R.string.play);
                        binding.positiveButton.setEnabled(true);
                        binding.positiveButton.setAlpha(1.0f);
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    //add vibration time
                    timeUp = System.currentTimeMillis();
                    if (started) {
                        VibrationEngine.getInstance().stop();
                        waveForm.add(timeUp - timeDown);
                    } else {
                        if (timeUp - timeDown >= 2000l) {
                            started = true;
                            binding.patternCreatorStatus.setText(getContext().getString(R.string.recording));
                            binding.patternCreatorSecond.setVisibility(View.GONE);
                        }
                    }
                    break;
            }

            return false;
        }
    };
}
