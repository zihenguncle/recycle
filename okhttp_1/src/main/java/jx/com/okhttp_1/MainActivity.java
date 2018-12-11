package jx.com.okhttp_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import jx.com.okhttp_1.recycle.RecycleGridView;
import jx.com.okhttp_1.recycle.RecycleLinearView;
import jx.com.okhttp_1.recycle.RecycleStaggeredView;

public class MainActivity extends AppCompatActivity {

    private Button button_recycler_linear,button_recycler_grid,button_recycler_flow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_recycler_linear = findViewById(R.id.button_recycler_linear);
        button_recycler_grid = findViewById(R.id.button_recycler_grid);
        button_recycler_flow = findViewById(R.id.button_recycler_flow);
        button_recycler_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RecycleLinearView.class));

            }
        });
        button_recycler_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RecycleGridView.class));
            }
        });
        button_recycler_flow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RecycleStaggeredView.class));
            }
        });
    }
}
