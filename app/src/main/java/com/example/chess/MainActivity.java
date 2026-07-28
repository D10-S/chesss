package com.example.chess;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private ChessView chessView;
    private TextView status;

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(0xff1d2433);
        status = new TextView(this);
        status.setTextColor(0xfffff2cf); status.setTextSize(18); status.setPadding(24,18,24,12);
        chessView = new ChessView(this, this::setStatus);
        Button reset = new Button(this); reset.setText("New same-device match"); reset.setOnClickListener(v -> chessView.resetGame());
        root.addView(status, new LinearLayout.LayoutParams(-1, -2));
        root.addView(chessView, new LinearLayout.LayoutParams(-1, 0, 1));
        root.addView(reset, new LinearLayout.LayoutParams(-1, -2));
        setContentView(root);
        chessView.resetGame();
    }

    private void setStatus(String text) { status.setText(text); }
}
