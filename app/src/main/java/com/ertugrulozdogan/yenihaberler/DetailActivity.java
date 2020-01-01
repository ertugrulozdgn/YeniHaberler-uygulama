package com.ertugrulozdogan.yenihaberler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.ertugrulozdogan.yenihaberler.Adapter.getItemIndex;
import static com.ertugrulozdogan.yenihaberler.MainActivity.globalResponse;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView titleDetail = findViewById(R.id.title);
        titleDetail.setText(globalResponse.get(getItemIndex).getTitle());

        TextView createdDetail = findViewById(R.id.created_at);
        createdDetail.setText(globalResponse.get(getItemIndex).getCreatedAt());

        TextView summaryDetail = findViewById(R.id.summary);
        summaryDetail.setText(globalResponse.get(getItemIndex).getSummary());

        TextView contentDetail = findViewById(R.id.content);
        contentDetail.setText(globalResponse.get(getItemIndex).getContent());
    }


}
