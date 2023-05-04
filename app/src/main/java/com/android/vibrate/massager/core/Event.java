package com.android.vibrate.massager.core;

/*
 * Created by  on 9/11/21 10:07 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 8/19/21 9:05 AM
 */

import android.os.Bundle;

public class Event {
    int eventCode;
    String textValue = "";
    Bundle data;

    public int getEventCode() {
        return this.eventCode;
    }

    public void setEventCode(final int eventCode) {
        this.eventCode = eventCode;
    }

    public Bundle getData() {
        return this.data;
    }

    public void setData(final Bundle data) {
        this.data = data;
    }

    private Event(final int eventCode) {
        this.eventCode = eventCode;
    }
    private Event(final int eventCode, final String textValue) {
        this.eventCode = eventCode;
        this.textValue = textValue;
    }

    private Event(final int eventCode, final Bundle data) {
        this.eventCode = eventCode;
        this.data = data;
    }

    public String getTextValue() {
        return this.textValue;
    }

    public void setTextValue(final String textValue) {
        this.textValue = textValue;
    }

    public static Event of(final int eventCode) {
        return new Event(eventCode);
    }

    public static Event of(final int eventCode, final String textValue) {
        return new Event(eventCode, textValue);
    }

    public static Event of(final int eventCode, final Bundle data) {
        return new Event(eventCode, data);
    }

    private static final int EVENT_BASE                     = 0;

    public static final int EVENT_SHOW_UNLOCK_EVERYTHING    = Event.EVENT_BASE + 1;
    public static final int EVENT_SHOW_UNLOCK_PATTERN       = Event.EVENT_BASE + 2;
    public static final int EVENT_SHOW_NOT_VIBRATION        = Event.EVENT_BASE + 3;
    public static final int EVENT_SHOW_RATE_THE_APP         = Event.EVENT_BASE + 4;
    public static final int EVENT_SHOW_SHARE_THE_APP        = Event.EVENT_BASE + 5;
    public static final int EVENT_SHOW_SEND_FEEDBACK        = Event.EVENT_BASE + 6;
    public static final int EVENT_SHOW_MUSIC                = Event.EVENT_BASE + 7;
    public static final int EVENT_TOGGLE_START_VIBRATION    = Event.EVENT_BASE + 8;

    public static final int EVENT_SHOW_CREATE_VIBRATION     = Event.EVENT_BASE + 9;
    public static final int EVENT_SUCCESS_PATTERN_CREATE    = Event.EVENT_BASE + 10;
    public static final int EVENT_START_RECORDING_PATTERN   = Event.EVENT_BASE + 11;
}
