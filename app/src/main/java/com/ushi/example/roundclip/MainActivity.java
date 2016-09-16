package com.ushi.example.roundclip;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.SeekBar;

import com.ushi.example.roundclip.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setSeekChangedListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, progress, getResources().getDisplayMetrics());
                binding.crop.setCornerRadius(px);
                binding.text.setText(getString(R.string.format_text, progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // ignore
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // ignore
            }
        });
    }
}
