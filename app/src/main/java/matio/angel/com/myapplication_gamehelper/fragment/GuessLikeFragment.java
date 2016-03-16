package matio.angel.com.myapplication_gamehelper.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import matio.angel.com.myapplication_gamehelper.R;

/**
 * Created by Angel on 2016/1/18.
 */
public class GuessLikeFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.framelayout, container, false);
        return view;
    }
}
