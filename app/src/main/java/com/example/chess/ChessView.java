package com.example.chess;

import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;

public class ChessView extends View {
 codex/create-low-poly-2d-chess-game-eswoll
    interface GameOverListener { void onGameOver(String winner); }
    private final GameOverListener gameOverListener;
    private final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final char[][] board = new char[8][8];
    private boolean whiteTurn = true;
    private boolean gameOver = false;
    private int selectedRow = -1, selectedCol = -1;
    private String message = "White to move";

    public ChessView(Context context, GameOverListener gameOverListener) { super(context); this.gameOverListener = gameOverListener; }

    interface StatusListener { void onStatus(String text); }
    private final StatusListener status;
    private final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final char[][] board = new char[8][8];
    private boolean whiteTurn = true;
    private int selectedRow = -1, selectedCol = -1;

    public ChessView(Context context, StatusListener status) { super(context); this.status = status; }
 main

    public void resetGame() {
        String[] start = {"rnbqkbnr", "pppppppp", "........", "........", "........", "........", "PPPPPPPP", "RNBQKBNR"};
        for (int r = 0; r < 8; r++) for (int c = 0; c < 8; c++) board[r][c] = start[r].charAt(c);
 codex/create-low-poly-2d-chess-game-eswoll
        whiteTurn = true; gameOver = false; selectedRow = selectedCol = -1; announce("White to move • pass the phone after each turn"); invalidate();

        whiteTurn = true; selectedRow = selectedCol = -1; announce("White to move • pass the phone after each turn"); invalidate();
 main
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
 codex/create-low-poly-2d-chess-game-eswoll
        float left = 0f, top = 0f, cellW = getWidth() / 8f, cellH = getHeight() / 8f, pieceSize = Math.min(cellW, cellH);
        p.setStyle(Paint.Style.FILL); p.setColor(0xff243044); canvas.drawRect(0, 0, getWidth(), getHeight(), p);
        drawLowPolyBackdrop(canvas);
        for (int r = 0; r < 8; r++) for (int c = 0; c < 8; c++) drawSquare(canvas, left + c * cellW, top + r * cellH, cellW, cellH, (r + c) % 2 == 0);
        if (selectedRow >= 0) { p.setStyle(Paint.Style.STROKE); p.setStrokeWidth(8); p.setColor(0xffffd166); canvas.drawRect(left + selectedCol * cellW + 4, top + selectedRow * cellH + 4, left + (selectedCol + 1) * cellW - 4, top + (selectedRow + 1) * cellH - 4, p); }
        p.setTextAlign(Paint.Align.CENTER); p.setTypeface(Typeface.create(Typeface.SERIF, Typeface.BOLD)); p.setTextSize(pieceSize * .58f);
        for (int r = 0; r < 8; r++) for (int c = 0; c < 8; c++) if (board[r][c] != '.') drawPiece(canvas, board[r][c], left + (c + .5f) * cellW, top + (r + .66f) * cellH, pieceSize);
        drawStatus(canvas, pieceSize);

        float size = Math.min(getWidth(), getHeight() - 20f), left = (getWidth() - size) / 2f, top = 20f, cell = size / 8f;
        p.setStyle(Paint.Style.FILL); p.setColor(0xff243044); canvas.drawRect(0, 0, getWidth(), getHeight(), p);
        drawLowPolyBackdrop(canvas);
        for (int r = 0; r < 8; r++) for (int c = 0; c < 8; c++) drawSquare(canvas, left + c * cell, top + r * cell, cell, (r + c) % 2 == 0);
        if (selectedRow >= 0) { p.setStyle(Paint.Style.STROKE); p.setStrokeWidth(8); p.setColor(0xffffd166); canvas.drawRect(left + selectedCol * cell + 4, top + selectedRow * cell + 4, left + (selectedCol + 1) * cell - 4, top + (selectedRow + 1) * cell - 4, p); }
        p.setTextAlign(Paint.Align.CENTER); p.setTypeface(Typeface.create(Typeface.SERIF, Typeface.BOLD)); p.setTextSize(cell * .58f);
        for (int r = 0; r < 8; r++) for (int c = 0; c < 8; c++) if (board[r][c] != '.') drawPiece(canvas, board[r][c], left + (c + .5f) * cell, top + (r + .66f) * cell, cell);
 main
    }

    private void drawLowPolyBackdrop(Canvas c) {
        int[] colors = {0xff182033, 0xff263b55, 0xff31445f, 0xff1e6f69};
        for (int i = 0; i < 10; i++) { p.setColor(colors[i % colors.length]); Path path = new Path(); path.moveTo(i * getWidth() / 6f, 0); path.lineTo((i + 2) * getWidth() / 6f, getHeight()); path.lineTo((i - 1) * getWidth() / 6f, getHeight()); path.close(); c.drawPath(path, p); }
    }

 codex/create-low-poly-2d-chess-game-eswoll
    private void drawSquare(Canvas c, float x, float y, float w, float h, boolean light) {
        p.setStyle(Paint.Style.FILL); p.setColor(light ? 0xfff2d49b : 0xff6f4d38); Path poly = new Path();
        poly.moveTo(x, y); poly.lineTo(x + w, y + h * .08f); poly.lineTo(x + w * .92f, y + h); poly.lineTo(x + w * .05f, y + h * .92f); poly.close(); c.drawPath(poly, p);
        p.setColor(light ? 0x22ffffff : 0x22000000); c.drawLine(x, y, x + w, y + h, p);

    private void drawSquare(Canvas c, float x, float y, float s, boolean light) {
        p.setStyle(Paint.Style.FILL); p.setColor(light ? 0xfff2d49b : 0xff6f4d38); Path poly = new Path();
        poly.moveTo(x, y); poly.lineTo(x + s, y + s * .08f); poly.lineTo(x + s * .92f, y + s); poly.lineTo(x + s * .05f, y + s * .92f); poly.close(); c.drawPath(poly, p);
        p.setColor(light ? 0x22ffffff : 0x22000000); c.drawLine(x, y, x + s, y + s, p);
 main
    }

    private void drawPiece(Canvas c, char piece, float x, float baseline, float cell) {
        boolean white = Character.isUpperCase(piece); p.setColor(white ? 0xfffff8df : 0xff222735); p.setStyle(Paint.Style.FILL);
        c.drawCircle(x, baseline - cell * .24f, cell * .28f, p); p.setColor(white ? 0xffc9823a : 0xff78c6bf); p.setStyle(Paint.Style.STROKE); p.setStrokeWidth(cell * .04f); c.drawCircle(x, baseline - cell * .24f, cell * .28f, p);
        p.setStyle(Paint.Style.FILL); p.setColor(white ? 0xff3a2a1e : 0xfffff2cf); c.drawText(String.valueOf(symbol(piece)), x, baseline - cell * .05f, p);
    }

    private char symbol(char p) { switch (Character.toLowerCase(p)) { case 'k': return '♚'; case 'q': return '♛'; case 'r': return '♜'; case 'b': return '♝'; case 'n': return '♞'; default: return '♟'; } }

 codex/create-low-poly-2d-chess-game-eswoll
    private void drawStatus(Canvas canvas, float pieceSize) {
        p.setStyle(Paint.Style.FILL); p.setColor(0xcc182033); canvas.drawRect(0, 0, getWidth(), pieceSize * .55f, p);
        p.setColor(0xfffff2cf); p.setTextSize(pieceSize * .22f); p.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)); p.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(message, getWidth() / 2f, pieceSize * .36f, p);
    }

    @Override public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() != MotionEvent.ACTION_DOWN || gameOver) return true;
        float cellW = getWidth() / 8f, cellH = getHeight() / 8f;
        int c = (int)(e.getX() / cellW), r = (int)(e.getY() / cellH); if (r < 0 || r > 7 || c < 0 || c > 7) return true;

    @Override public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() != MotionEvent.ACTION_DOWN) return true;
        float size = Math.min(getWidth(), getHeight() - 20f), left = (getWidth() - size) / 2f, top = 20f, cell = size / 8f;
        int c = (int)((e.getX() - left) / cell), r = (int)((e.getY() - top) / cell); if (r < 0 || r > 7 || c < 0 || c > 7) return true;
 main
        char tapped = board[r][c];
        if (selectedRow < 0) { if (tapped != '.' && isWhite(tapped) == whiteTurn) select(r, c); return true; }
        if (r == selectedRow && c == selectedCol) { selectedRow = selectedCol = -1; invalidate(); return true; }
        if (tapped != '.' && isWhite(tapped) == whiteTurn) { select(r, c); return true; }
        if (legal(selectedRow, selectedCol, r, c)) move(r, c); else announce("Illegal move • " + (whiteTurn ? "White" : "Black") + " to move"); invalidate(); return true;
    }

    private void select(int r, int c) { selectedRow = r; selectedCol = c; announce((whiteTurn ? "White" : "Black") + " selected " + Character.toUpperCase(board[r][c])); invalidate(); }
    private boolean isWhite(char piece) { return Character.isUpperCase(piece); }

    private void move(int r, int c) {
        char moving = board[selectedRow][selectedCol], captured = board[r][c]; board[r][c] = moving; board[selectedRow][selectedCol] = '.';
        if (moving == 'P' && r == 0) board[r][c] = 'Q'; if (moving == 'p' && r == 7) board[r][c] = 'q'; selectedRow = selectedCol = -1;
 codex/create-low-poly-2d-chess-game-eswoll
        if (Character.toLowerCase(captured) == 'k') { gameOver = true; String winner = whiteTurn ? "White" : "Black"; announce(winner + " wins!"); gameOverListener.onGameOver(winner); } else { whiteTurn = !whiteTurn; announce((whiteTurn ? "White" : "Black") + " to move • offline local 2-player"); }

        if (Character.toLowerCase(captured) == 'k') announce((whiteTurn ? "White" : "Black") + " wins! Tap New same-device match."); else { whiteTurn = !whiteTurn; announce((whiteTurn ? "White" : "Black") + " to move • offline local 2-player"); }
 main
    }

    private boolean legal(int sr, int sc, int tr, int tc) {
        char piece = board[sr][sc], target = board[tr][tc]; if (target != '.' && isWhite(target) == isWhite(piece)) return false;
        int dr = tr - sr, dc = tc - sc, adr = Math.abs(dr), adc = Math.abs(dc); char type = Character.toLowerCase(piece);
        if (type == 'p') { int dir = isWhite(piece) ? -1 : 1, start = isWhite(piece) ? 6 : 1; return (dc == 0 && target == '.' && (dr == dir || (sr == start && dr == 2 * dir && board[sr + dir][sc] == '.'))) || (adr == 1 && dr == dir && target != '.'); }
        if (type == 'n') return adr * adc == 2;
        if (type == 'k') return adr <= 1 && adc <= 1;
        if (type == 'b') return adr == adc && clear(sr, sc, tr, tc);
        if (type == 'r') return (dr == 0 || dc == 0) && clear(sr, sc, tr, tc);
        if (type == 'q') return (adr == adc || dr == 0 || dc == 0) && clear(sr, sc, tr, tc);
        return false;
    }

    private boolean clear(int sr, int sc, int tr, int tc) {
        int stepR = Integer.compare(tr, sr), stepC = Integer.compare(tc, sc), r = sr + stepR, c = sc + stepC;
        while (r != tr || c != tc) { if (board[r][c] != '.') return false; r += stepR; c += stepC; } return true;
    }
 codex/create-low-poly-2d-chess-game-eswoll
    private void announce(String text) { message = "Chess • " + text; }

    private void announce(String text) { status.onStatus("Chess • " + text); }
 main
}
