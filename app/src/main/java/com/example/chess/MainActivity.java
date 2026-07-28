package com.example.chess;

import android.app.Activity;
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
}
