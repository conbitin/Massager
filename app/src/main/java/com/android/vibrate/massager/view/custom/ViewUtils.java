/*
 * Copyright (c) 2023
 * Create by  on 4/21/23, 6:39 PM
 * Last modified 4/21/23, 6:39 PM
 */

package com.android.vibrate.massager.view.custom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class ViewUtils {
    public static void setAlphaExit(final View parentView) {
        if (parentView == null) {
            Log.i("ViewUtils", "View was removed from window");
            return;
        }
        parentView.setVisibility(View.VISIBLE);
        parentView.setAlpha(1);
        parentView.animate()
                .setDuration(1000)
                .alpha(0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(final Animator animation) {
                        super.onAnimationEnd(animation);
                        parentView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationStart(final Animator animation) {
                        super.onAnimationStart(animation);
                    }
                }).start();
    }

    public static void setAlphaEnter(final View parentView) {
        if (parentView == null || !parentView.isAttachedToWindow()) {
            Log.i("ViewUtils", "View was removed from window");
            return;
        }
        parentView.setVisibility(View.VISIBLE);
        parentView.setAlpha(0f);
        parentView.animate()
                .setDuration(750)
                .alpha(1f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(final Animator animation) {
                        super.onAnimationEnd(animation);

                    }

                    @Override
                    public void onAnimationStart(final Animator animation) {
                        super.onAnimationStart(animation);
                    }
                }).start();
    }

    public static void runAnimationDrawable(final boolean isEnable, final View view) {
        try {
            final AnimationDrawable loadingAnimation = (AnimationDrawable) view.getBackground();
            if (isEnable) loadingAnimation.start();
            else loadingAnimation.stop();
        } catch (final Exception e) {}
    }


    public static void showFragment(final FragmentActivity activity, final Fragment fragment, final int containerId) {
        if (activity == null || fragment == null || fragment.isVisible()) return;
        final FragmentManager fm = activity.getSupportFragmentManager();
        fm.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .add(containerId, fragment)
                .commit();
    }

    public static void hideFragment(final FragmentActivity activity, final Fragment fragment) {
        if (activity == null || fragment == null || fragment.isHidden()) return;
        final FragmentManager fm = activity.getSupportFragmentManager();
        fm.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .remove(fragment)
                .commit();
    }
}

