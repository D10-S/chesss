package com.example.chess;

import android.app.Activity;
 codex/create-low-poly-2d-chess-game-xqj34w

 codex/create-low-poly-2d-chess-game-eswoll
 main
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    private ChessView chessView;

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        chessView = new ChessView(this, this::showWinnerDialog);
        setContentView(chessView);
        chessView.resetGame();
    }

    private void showWinnerDialog(String winner) {
        new AlertDialog.Builder(this)
                .setTitle(winner + " wins!")
                .setMessage("Game over. Start a rematch?")
                .setCancelable(false)
                .setPositiveButton("Rematch", (dialog, which) -> chessView.resetGame())
                .show();
    }
 codex/create-low-poly-2d-chess-game-xqj34w


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
 main
 main
}
