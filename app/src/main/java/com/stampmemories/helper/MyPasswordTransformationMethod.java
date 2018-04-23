package com.stampmemories.helper;

import android.support.annotation.NonNull;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

/**
 * Created by AppsterBiz on 06-Mar-18.
 */


public class MyPasswordTransformationMethod extends PasswordTransformationMethod {

    @NonNull
    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        return new PasswordCharSequence(source);
    }

    private class PasswordCharSequence implements CharSequence {

        private CharSequence mSource;

        PasswordCharSequence(CharSequence source) {
            mSource = source;
        }

        public char charAt(int index) {
            return '.';
        }

        public int length() {
            return mSource.length();
        }

        public CharSequence subSequence(int start, int end) {
            return mSource.subSequence(start, end); // Return default
        }
    }
}