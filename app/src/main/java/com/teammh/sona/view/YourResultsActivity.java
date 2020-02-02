package com.teammh.sona.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.teammh.sona.R;

import java.util.ArrayList;

import static android.graphics.Color.WHITE;

public class YourResultsActivity extends AppCompatActivity {

    public static final float MAX = 12, MIN = 1f;
    public static final int NB_QUALITIES = 3;
    private RadarChart chart;
    private TextView result, recommend;
    private String[] qualities = new String[] {"Happiness", "Depression", "Anxiety"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_results);
        chart = findViewById(R.id.chart);
        result = findViewById(R.id.result);
        recommend = findViewById(R.id.recommends);
        chart.setBackgroundColor(WHITE);
        chart.getDescription().setEnabled(false);
        chart.setWebLineWidth(1f);
        chart.setWebColor(Color.BLACK);
        chart.setWebLineWidth(1f);
        chart.setWebColorInner(Color.BLACK);
        chart.setWebAlpha(100);

        setData();

        chart.animateXY(1400, 1400, Easing.EaseInOutQuad, Easing.EaseInOutQuad);
        XAxis xAxis = chart.getXAxis();
        xAxis.setTextSize(9f);
        xAxis.setXOffset(0);
        xAxis.setYOffset(0);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(){
            @Override
            public String getFormattedValue(float value, AxisBase axis){
                return qualities[(int) value % qualities.length];
            }
        });

        xAxis.setTextColor(Color.BLACK);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(qualities));
        YAxis yAxis = chart.getYAxis();
        yAxis.setLabelCount(NB_QUALITIES, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(MIN);
        yAxis.setAxisMaximum(MAX);
        yAxis.setDrawLabels(false);

        Legend l = chart.getLegend();
        l.setTextSize(15f);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.BLACK);
    }


    private void setData(){
        ArrayList<RadarEntry> user = new ArrayList<>();
        float val = 0;
        for(int i=0;i<NB_QUALITIES;i++){
            float mood_factor = (int)(Math.random() * MAX) + MIN;
            user.add(new RadarEntry(mood_factor));
            val+=mood_factor;
        }

        val/=3;
        if(val>9 && val<=13){
            result.setText(getResources().getString(R.string.result_string) + " "  + getResources().getString(R.string.output_4));
            recommend.setText(getResources().getString(R.string.recommends) + " "+ getResources().getString(R.string.rec4));
        }
        else if(val>6 && val<=9){
            result.setText(getResources().getString(R.string.result_string) + " " + getResources().getString(R.string.output_3));
            recommend.setText(getResources().getString(R.string.recommends) + " " + getResources().getString(R.string.rec3));
        }
        else if(val>4 && val<=6){
            result.setText(getResources().getString(R.string.result_string) + " " + getResources().getString(R.string.output_2));
            recommend.setText(getResources().getString(R.string.recommends) + " " + getResources().getString(R.string.rec2));
        }
        else{
            result.setText(getResources().getString(R.string.result_string) + " " + getResources().getString(R.string.output_1));
            recommend.setText(getResources().getString(R.string.recommends) + " " + getResources().getString(R.string.rec1));
        }

        RadarDataSet set1 = new RadarDataSet(user, "Mayank");
        set1.setColor(Color.RED);
        set1.setFillColor(Color.RED);
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightIndicators(true);
        set1.setDrawHighlightCircleEnabled(true);

        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set1);

        RadarData data = new RadarData(sets);
        data.setValueTextSize(8f);
        data.setDrawValues(true);
        data.setValueTextColor(Color.BLACK);

        chart.setData(data);
        chart.invalidate();
    }
}
