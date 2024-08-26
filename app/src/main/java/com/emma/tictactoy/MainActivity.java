package com.emma.tictactoy;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 1;
    boolean winner = false;
    ArrayList<Integer> player1 = new ArrayList<>();
    ArrayList<Integer> player2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }

    public void btn_click(View view) {
        Button selectedBtn = (Button) view;
        selectedBtn.setText("X");
        selectedBtn.setBackgroundColor(Color.RED);
        int cell = 0;

        int id = selectedBtn.getId();
        if (id == R.id.button) {
            cell = 1;
        } else if (id == R.id.button2) {
            cell = 2;
        } else if (id == R.id.button3) {
            cell = 3;
        } else if (id == R.id.button4) {
            cell = 4;
        } else if (id == R.id.button5) {
            cell = 5;
        } else if (id == R.id.button6) {
            cell = 6;
        } else if (id == R.id.button7) {
            cell = 7;
        } else if (id == R.id.button8) {
            cell = 8;
        } else if (id == R.id.button9) {
            cell = 9;
        }
        playGame(cell, selectedBtn);
    }

    void playGame(int cell, Button selectedBtn) {
        Log.d("Player", cell + "");
        if (activePlayer == 1) {
            selectedBtn.setText("X");
            selectedBtn.setBackgroundColor(Color.RED);
            player1.add(cell);
            activePlayer = 2;
            this.autoPlay();
        } else if (activePlayer == 2) {
            selectedBtn.setText("O");
            selectedBtn.setBackgroundColor(Color.GREEN);
            activePlayer = 1;
            player2.add(cell);
        }
        selectedBtn.setEnabled(false);
        CheckWinner();
        if (this.winner) {
            this.resetGame();
        }
    }

    void CheckWinner() {
        ArrayList<Integer> winningPositions;
        for (int i = 1; i <= 2; i++) {
            ArrayList<Integer> player;
            if (i == 1) {
                player = new ArrayList<>(player1);
            } else {
                player = new ArrayList<>(player2);
            }

            // Check rows
            for (int j = 1; j <= 7; j += 3) {
                winningPositions = new ArrayList<>();

                for (int k = 0; k < 3; k++) {
                    winningPositions.add(j + k);
                }
                if (player.containsAll(winningPositions)) {
                    Log.d("Winner", "Player " + i + " wins");
                    Toast.makeText(this, "Player " + i + " wins", Toast.LENGTH_SHORT).show();
                    this.winner = true;
                    return;
                }
            }

            // Check columns
            for (int j = 1; j <= 3; j++) {
                winningPositions = new ArrayList<>();
                for (int k = 0; k < 3; k++) {
                    winningPositions.add(j + k * 3);
                }
                if (player.containsAll(winningPositions)) {
                    Log.d("Winner", "Player " + i + " wins");
                    Toast.makeText(this, "Player " + i + " wins", Toast.LENGTH_SHORT).show();
                    this.winner = true;
                    return;
                }
            }

            // Check diagonals
            winningPositions = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                winningPositions.add(1 + j * 4);
            }
            if (player.containsAll(winningPositions)) {
                Log.d("Winner", "Player " + i + " wins");
                Toast.makeText(this, "Player " + i + " wins", Toast.LENGTH_SHORT).show();
                this.winner = true;
                return;
            }

            winningPositions = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                winningPositions.add(3 + j * 2);
            }
            if (player.containsAll(winningPositions)) {
                Log.d("Winner", "Player " + i + " wins");
                Toast.makeText(this, "Player " + i + " wins", Toast.LENGTH_SHORT).show();
                this.winner = true;
                return;
            }
        }
        if (player1.size() + player2.size() == 9) {
            Toast.makeText(this, "It's a draw", Toast.LENGTH_SHORT).show();
            this.winner = true;
        }

    }

    void autoPlay() {
        ArrayList<Integer> emptyCells = new ArrayList<>();
        for (int cellId = 1; cellId <= 9; cellId++) {
            if (!(player1.contains(cellId) || player2.contains(cellId))) {
                emptyCells.add(cellId);
            }
        }
        if (emptyCells.isEmpty()) {
            return;
        }
        int randomIndex = (int) (Math.random() * emptyCells.size());
        int cell = emptyCells.get(randomIndex);
        Button selectedBtn;
        switch (cell) {
            case 1:
                selectedBtn = findViewById(R.id.button);
                break;
            case 2:
                selectedBtn = findViewById(R.id.button2);
                break;
            case 3:
                selectedBtn = findViewById(R.id.button3);
                break;
            case 4:
                selectedBtn = findViewById(R.id.button4);
                break;
            case 5:
                selectedBtn = findViewById(R.id.button5);
                break;
            case 6:
                selectedBtn = findViewById(R.id.button6);
                break;
            case 7:
                selectedBtn = findViewById(R.id.button7);
                break;
            case 8:
                selectedBtn = findViewById(R.id.button8);
                break;
            case 9:
                selectedBtn = findViewById(R.id.button9);
                break;
            default:
                selectedBtn = findViewById(R.id.button);
                break;
        }
        playGame(cell, selectedBtn);
    }

    void resetGame() {
        activePlayer = 1;
        player1.clear();
        player2.clear();

        Button button = findViewById(R.id.button);
        button.setText("");
        button.setBackgroundColor(Color.BLUE);
        button.setEnabled(true);

        button = findViewById(R.id.button2);
        button.setText("");
        button.setBackgroundColor(Color.BLUE);
        button.setEnabled(true);

        button = findViewById(R.id.button3);
        button.setText("");
        button.setBackgroundColor(Color.BLUE);
        button.setEnabled(true);

        button = findViewById(R.id.button4);
        button.setText("");
        button.setBackgroundColor(Color.BLUE);
        button.setEnabled(true);

        button = findViewById(R.id.button5);
        button.setText("");
        button.setBackgroundColor(Color.BLUE);
        button.setEnabled(true);

        button = findViewById(R.id.button6);
        button.setText("");
        button.setBackgroundColor(Color.BLUE);
        button.setEnabled(true);

        button = findViewById(R.id.button7);
        button.setText("");
        button.setBackgroundColor(Color.BLUE);
        button.setEnabled(true);

        button = findViewById(R.id.button8);
        button.setText("");
        button.setBackgroundColor(Color.BLUE);
        button.setEnabled(true);

        button = findViewById(R.id.button9);
        button.setText("");
        button.setBackgroundColor(Color.BLUE);
        button.setEnabled(true);

        this.winner = false;
    }
}