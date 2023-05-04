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

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    private final Runnable mPlayCompletedRunnable = () -> {
        this.binding.patternCreatorStatus.setText(R.string.done);
        this.binding.positiveButton.setText(R.string.save);
        this.binding.positiveButton.setAlpha(1.0f);
        this.binding.positiveButton.setEnabled(true);
        //binding.patternCreatorControl.setVisibility(View.GONE);
    };

    public CreationPatternDialog(@NonNull final Activity activity) {
        super(activity);
        this.setContentView((this.binding = DialogPatternCreatorBinding.inflate(activity.getLayoutInflater())).getRoot());
        this.binding.backButton.setOnClickListener(view -> {
            this.dismiss();
        });
        this.binding.negativeButton.setOnClickListener(view -> {
            this.dismiss();
        });
        this.binding.positiveButton.setOnClickListener(view -> {
            if (!this.isStoppedRecording) {
                if (this.waveForm != null && !this.waveForm.isEmpty()) {
                    this.isStoppedRecording = true;
                    VibrationEngine.getInstance().playPatternNoRepeat(this.getRecordingPattern());
                    this.binding.patternCreatorStatus.setText(R.string.playing);
                    this.binding.textPatternControlStatus.setText(R.string.again);
                    this.binding.imgPatternControlStatus.setSelected(true);

                    this.binding.positiveButton.setText(R.string.save);
                    this.binding.positiveButton.setEnabled(false);
                    this.binding.positiveButton.setAlpha(0.5f);

                    this.mHandler.removeCallbacks(this.mPlayCompletedRunnable);
                    this.mHandler.postDelayed(this.mPlayCompletedRunnable, this.getDuration());
                }
            } else {
                final long[] recordedPattern = this.getRecordingPattern();
                if (recordedPattern != null && recordedPattern.length > 0) {
                    final Pattern pattern = new Pattern();
                    pattern.title = this.binding.inputName.getText().toString();
                    pattern.type = Pattern.TYPE_CUSTOM;
                    pattern.pattern = recordedPattern;
                    PatternCreationLoader.getInstance().saveCreatedPattern(pattern);
                    AppObservable.getInstance().notifyObservers(Event.of(Event.EVENT_SUCCESS_PATTERN_CREATE));
                    this.dismiss();
                }
            }
        });
        this.binding.creatorPatternView.setOnTouchListener(this.onTouchListener);


        this.binding.patternCreatorControl.setOnClickListener(view -> {
            if (!this.isStoppedRecording) {
                if (this.waveForm != null && !this.waveForm.isEmpty()) {
                    this.isStoppedRecording = true;
                    VibrationEngine.getInstance().playPatternNoRepeat(this.getRecordingPattern());
                    this.binding.patternCreatorStatus.setText(R.string.playing);
                    this.binding.textPatternControlStatus.setText(R.string.again);
                    this.binding.imgPatternControlStatus.setSelected(true);

                    this.binding.positiveButton.setText(R.string.save);
                    this.binding.positiveButton.setEnabled(false);
                    this.binding.positiveButton.setAlpha(0.5f);

                    this.mHandler.removeCallbacks(this.mPlayCompletedRunnable);
                    this.mHandler.postDelayed(this.mPlayCompletedRunnable, this.getDuration());
                }
            } else {
                if (this.waveForm != null && !this.waveForm.isEmpty()) {
                    VibrationEngine.getInstance().playPatternNoRepeat(this.getRecordingPattern());
                    this.binding.patternCreatorStatus.setText(R.string.playing);

                    this.binding.positiveButton.setText(R.string.save);
                    this.binding.positiveButton.setEnabled(false);
                    this.binding.positiveButton.setAlpha(0.5f);

                    this.mHandler.removeCallbacks(this.mPlayCompletedRunnable);
                    this.mHandler.postDelayed(this.mPlayCompletedRunnable, this.getDuration());
                }
            }
        });
        this.setCancelable(true);
    }

    @Override
    public void onAttachedToWindow() {
        AppObservable.getInstance().addObserver(this);
        super.onAttachedToWindow();
    }

    @Override
    public void update(final Observable observable, final Object data) {
        if (data == null || !(data instanceof Event)) return;
        final Event event = (Event) data;
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
        final long[] pattern = new long[this.waveForm.size()];
        for (int i = 0; i < this.waveForm.size(); i++) {
            pattern[i] = this.waveForm.get(i);
        }
        return pattern;
    }

    public long getDuration() {
       long sum = 0;
        for (int i = 0; i < this.waveForm.size(); i++) {
            sum += this.waveForm.get(i);
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

    boolean isStoppedRecording;

    private final View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            if (CreationPatternDialog.this.isStoppedRecording) return false;
            Log.i("CreatorPatternView", "onTouch " + CreationPatternDialog.this.started + " action: " + motionEvent.getAction() + " timeUp: " + CreationPatternDialog.this.timeUp + " timeDown:" + CreationPatternDialog.this.timeDown);
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    //add break time
                    CreationPatternDialog.this.timeDown = System.currentTimeMillis();
                    if (CreationPatternDialog.this.started) {
                        if (CreationPatternDialog.this.waveForm.isEmpty()) {
                            CreationPatternDialog.this.waveForm.add(0l);
                        } else {
                            CreationPatternDialog.this.waveForm.add(CreationPatternDialog.this.timeDown - CreationPatternDialog.this.timeUp);
                        }
                        VibrationEngine.getInstance().vibrate();
                        CreationPatternDialog.this.binding.patternCreatorControl.setVisibility(View.VISIBLE);
                        CreationPatternDialog.this.binding.textPatternControlStatus.setText(R.string.stop);
                        CreationPatternDialog.this.binding.imgPatternControlStatus.setSelected(false);

                        CreationPatternDialog.this.binding.positiveButton.setText(R.string.play);
                        CreationPatternDialog.this.binding.positiveButton.setEnabled(true);
                        CreationPatternDialog.this.binding.positiveButton.setAlpha(1.0f);
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    //add vibration time
                    CreationPatternDialog.this.timeUp = System.currentTimeMillis();
                    if (CreationPatternDialog.this.started) {
                        VibrationEngine.getInstance().stop();
                        CreationPatternDialog.this.waveForm.add(CreationPatternDialog.this.timeUp - CreationPatternDialog.this.timeDown);
                    } else {
                        if (CreationPatternDialog.this.timeUp - CreationPatternDialog.this.timeDown >= 2000l) {
                            CreationPatternDialog.this.started = true;
                            CreationPatternDialog.this.binding.patternCreatorStatus.setText(CreationPatternDialog.this.getContext().getString(R.string.recording));
                            CreationPatternDialog.this.binding.patternCreatorSecond.setVisibility(View.GONE);
                        }
                    }
                    break;
            }

            return false;
        }
    };
}
