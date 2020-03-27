package com.example.chess;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView[][] DisplayBoard = new TextView[8][8];
    private TextView[][] DisplayBoardBackground = new TextView[8][8];
    private int[][] board = new int[8][8];
    private int[][] oldBoard = new int[8][8];
    private final int PAWN = 1;
    private final int BISHOP = 2;
    private final int KNIGHT = 3;
    private final int ROOK = 4;
    private final int QUEEN = 5;
    private final int KING = 6;
    private int selectedPiece;
    private int[] selected = {-1,-1};
    private int[] cr = {-1,-1};
    private boolean isWhiteTurn = true;
    private boolean isSelected = false;

    /*
    OLD BOARD IS THE UNUPDATED VERSION OF BOARD
    BOARD WILL BE USED TO CHECK IF A MOVE IS VALID OR NOT
    IF IT'S NOT OLD BOARD WILL BE USED TO UPDATE BOARD
    UPDATE THE DISPLAY BOARD AFTER EVERYTHING IS VALIDATED
    TODOS: CREATE A CHECK CHECKER AND A CHECKMATE CHECKER
           CREATE A UTILITY CLASS TO CONTAIN SAID STUFF
           CREATE A POSSIBLE MOVE GENERATOR
     */

    private void initialize(){
        DisplayBoard[0][0] = (TextView) findViewById(R.id.R00);
        DisplayBoardBackground[0][0] = (TextView) findViewById(R.id.R000);
        DisplayBoard[1][0] = (TextView) findViewById(R.id.R10);
        DisplayBoardBackground[1][0] = (TextView) findViewById(R.id.R010);
        DisplayBoard[2][0] = (TextView) findViewById(R.id.R20);
        DisplayBoardBackground[2][0] = (TextView) findViewById(R.id.R020);
        DisplayBoard[3][0] = (TextView) findViewById(R.id.R30);
        DisplayBoardBackground[3][0] = (TextView) findViewById(R.id.R030);
        DisplayBoard[4][0] = (TextView) findViewById(R.id.R40);
        DisplayBoardBackground[4][0] = (TextView) findViewById(R.id.R040);
        DisplayBoard[5][0] = (TextView) findViewById(R.id.R50);
        DisplayBoardBackground[5][0] = (TextView) findViewById(R.id.R050);
        DisplayBoard[6][0] = (TextView) findViewById(R.id.R60);
        DisplayBoardBackground[6][0] = (TextView) findViewById(R.id.R060);
        DisplayBoard[7][0] = (TextView) findViewById(R.id.R70);
        DisplayBoardBackground[7][0] = (TextView) findViewById(R.id.R070);

        DisplayBoard[0][1] = (TextView) findViewById(R.id.R01);
        DisplayBoardBackground[0][1] = (TextView) findViewById(R.id.R001);
        DisplayBoard[1][1] = (TextView) findViewById(R.id.R11);
        DisplayBoardBackground[1][1] = (TextView) findViewById(R.id.R011);
        DisplayBoard[2][1] = (TextView) findViewById(R.id.R21);
        DisplayBoardBackground[2][1] = (TextView) findViewById(R.id.R021);
        DisplayBoard[3][1] = (TextView) findViewById(R.id.R31);
        DisplayBoardBackground[3][1] = (TextView) findViewById(R.id.R031);
        DisplayBoard[4][1] = (TextView) findViewById(R.id.R41);
        DisplayBoardBackground[4][1] = (TextView) findViewById(R.id.R041);
        DisplayBoard[5][1] = (TextView) findViewById(R.id.R51);
        DisplayBoardBackground[5][1] = (TextView) findViewById(R.id.R051);
        DisplayBoard[6][1] = (TextView) findViewById(R.id.R61);
        DisplayBoardBackground[6][1] = (TextView) findViewById(R.id.R061);
        DisplayBoard[7][1] = (TextView) findViewById(R.id.R71);
        DisplayBoardBackground[7][1] = (TextView) findViewById(R.id.R071);

        DisplayBoard[0][2] = (TextView) findViewById(R.id.R02);
        DisplayBoardBackground[0][2] = (TextView) findViewById(R.id.R002);
        DisplayBoard[1][2] = (TextView) findViewById(R.id.R12);
        DisplayBoardBackground[1][2] = (TextView) findViewById(R.id.R012);
        DisplayBoard[2][2] = (TextView) findViewById(R.id.R22);
        DisplayBoardBackground[2][2] = (TextView) findViewById(R.id.R022);
        DisplayBoard[3][2] = (TextView) findViewById(R.id.R32);
        DisplayBoardBackground[3][2] = (TextView) findViewById(R.id.R032);
        DisplayBoard[4][2] = (TextView) findViewById(R.id.R42);
        DisplayBoardBackground[4][2] = (TextView) findViewById(R.id.R042);
        DisplayBoard[5][2] = (TextView) findViewById(R.id.R52);
        DisplayBoardBackground[5][2] = (TextView) findViewById(R.id.R052);
        DisplayBoard[6][2] = (TextView) findViewById(R.id.R62);
        DisplayBoardBackground[6][2] = (TextView) findViewById(R.id.R062);
        DisplayBoard[7][2] = (TextView) findViewById(R.id.R72);
        DisplayBoardBackground[7][2] = (TextView) findViewById(R.id.R072);

        DisplayBoard[0][3] = (TextView) findViewById(R.id.R03);
        DisplayBoardBackground[0][3] = (TextView) findViewById(R.id.R003);
        DisplayBoard[1][3] = (TextView) findViewById(R.id.R13);
        DisplayBoardBackground[1][3] = (TextView) findViewById(R.id.R013);
        DisplayBoard[2][3] = (TextView) findViewById(R.id.R23);
        DisplayBoardBackground[2][3] = (TextView) findViewById(R.id.R023);
        DisplayBoard[3][3] = (TextView) findViewById(R.id.R33);
        DisplayBoardBackground[3][3] = (TextView) findViewById(R.id.R033);
        DisplayBoard[4][3] = (TextView) findViewById(R.id.R43);
        DisplayBoardBackground[4][3] = (TextView) findViewById(R.id.R043);
        DisplayBoard[5][3] = (TextView) findViewById(R.id.R53);
        DisplayBoardBackground[5][3] = (TextView) findViewById(R.id.R053);
        DisplayBoard[6][3] = (TextView) findViewById(R.id.R63);
        DisplayBoardBackground[6][3] = (TextView) findViewById(R.id.R063);
        DisplayBoard[7][3] = (TextView) findViewById(R.id.R73);
        DisplayBoardBackground[7][3] = (TextView) findViewById(R.id.R073);

        DisplayBoard[0][4] = (TextView) findViewById(R.id.R04);
        DisplayBoardBackground[0][4] = (TextView) findViewById(R.id.R004);
        DisplayBoard[1][4] = (TextView) findViewById(R.id.R14);
        DisplayBoardBackground[1][4] = (TextView) findViewById(R.id.R014);
        DisplayBoard[2][4] = (TextView) findViewById(R.id.R24);
        DisplayBoardBackground[2][4] = (TextView) findViewById(R.id.R024);
        DisplayBoard[3][4] = (TextView) findViewById(R.id.R34);
        DisplayBoardBackground[3][4] = (TextView) findViewById(R.id.R034);
        DisplayBoard[4][4] = (TextView) findViewById(R.id.R44);
        DisplayBoardBackground[4][4] = (TextView) findViewById(R.id.R044);
        DisplayBoard[5][4] = (TextView) findViewById(R.id.R54);
        DisplayBoardBackground[5][4] = (TextView) findViewById(R.id.R054);
        DisplayBoard[6][4] = (TextView) findViewById(R.id.R64);
        DisplayBoardBackground[6][4] = (TextView) findViewById(R.id.R064);
        DisplayBoard[7][4] = (TextView) findViewById(R.id.R74);
        DisplayBoardBackground[7][4] = (TextView) findViewById(R.id.R074);

        DisplayBoard[0][5] = (TextView) findViewById(R.id.R05);
        DisplayBoardBackground[0][5] = (TextView) findViewById(R.id.R005);
        DisplayBoard[1][5] = (TextView) findViewById(R.id.R15);
        DisplayBoardBackground[1][5] = (TextView) findViewById(R.id.R015);
        DisplayBoard[2][5] = (TextView) findViewById(R.id.R25);
        DisplayBoardBackground[2][5] = (TextView) findViewById(R.id.R025);
        DisplayBoard[3][5] = (TextView) findViewById(R.id.R35);
        DisplayBoardBackground[3][5] = (TextView) findViewById(R.id.R035);
        DisplayBoard[4][5] = (TextView) findViewById(R.id.R45);
        DisplayBoardBackground[4][5] = (TextView) findViewById(R.id.R045);
        DisplayBoard[5][5] = (TextView) findViewById(R.id.R55);
        DisplayBoardBackground[5][5] = (TextView) findViewById(R.id.R055);
        DisplayBoard[6][5] = (TextView) findViewById(R.id.R65);
        DisplayBoardBackground[6][5] = (TextView) findViewById(R.id.R065);
        DisplayBoard[7][5] = (TextView) findViewById(R.id.R75);
        DisplayBoardBackground[7][5] = (TextView) findViewById(R.id.R075);

        DisplayBoard[0][6] = (TextView) findViewById(R.id.R06);
        DisplayBoardBackground[0][6] = (TextView) findViewById(R.id.R006);
        DisplayBoard[1][6] = (TextView) findViewById(R.id.R16);
        DisplayBoardBackground[1][6] = (TextView) findViewById(R.id.R016);
        DisplayBoard[2][6] = (TextView) findViewById(R.id.R26);
        DisplayBoardBackground[2][6] = (TextView) findViewById(R.id.R026);
        DisplayBoard[3][6] = (TextView) findViewById(R.id.R36);
        DisplayBoardBackground[3][6] = (TextView) findViewById(R.id.R036);
        DisplayBoard[4][6] = (TextView) findViewById(R.id.R46);
        DisplayBoardBackground[4][6] = (TextView) findViewById(R.id.R046);
        DisplayBoard[5][6] = (TextView) findViewById(R.id.R56);
        DisplayBoardBackground[5][6] = (TextView) findViewById(R.id.R056);
        DisplayBoard[6][6] = (TextView) findViewById(R.id.R66);
        DisplayBoardBackground[6][6] = (TextView) findViewById(R.id.R066);
        DisplayBoard[7][6] = (TextView) findViewById(R.id.R76);
        DisplayBoardBackground[7][6] = (TextView) findViewById(R.id.R076);

        DisplayBoard[0][7] = (TextView) findViewById(R.id.R07);
        DisplayBoardBackground[0][7] = (TextView) findViewById(R.id.R007);
        DisplayBoard[1][7] = (TextView) findViewById(R.id.R17);
        DisplayBoardBackground[1][7] = (TextView) findViewById(R.id.R017);
        DisplayBoard[2][7] = (TextView) findViewById(R.id.R27);
        DisplayBoardBackground[2][7] = (TextView) findViewById(R.id.R027);
        DisplayBoard[3][7] = (TextView) findViewById(R.id.R37);
        DisplayBoardBackground[3][7] = (TextView) findViewById(R.id.R037);
        DisplayBoard[4][7] = (TextView) findViewById(R.id.R47);
        DisplayBoardBackground[4][7] = (TextView) findViewById(R.id.R047);
        DisplayBoard[5][7] = (TextView) findViewById(R.id.R57);
        DisplayBoardBackground[5][7] = (TextView) findViewById(R.id.R057);
        DisplayBoard[6][7] = (TextView) findViewById(R.id.R67);
        DisplayBoardBackground[6][7] = (TextView) findViewById(R.id.R067);
        DisplayBoard[7][7] = (TextView) findViewById(R.id.R77);
        DisplayBoardBackground[7][7] = (TextView) findViewById(R.id.R077);
        makeBoard();
        updateDisplayBoard();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View main = findViewById(R.id.activity_main);
        View root = main.getRootView();
        root.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        Log.w("Test","Test");
        initialize();
        Button restart = (Button) findViewById(R.id.restartButton);
        restart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                makeBoard();
                updateNewBoard();
                updateOldBoard();
                updateDisplayBoard();
            }
        });
        Button undo = (Button) findViewById(R.id.undoButton);
        undo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                updateNewBoard();
                updateDisplayBoard();
                int[] tempS  = {-1,-1};
                selected = tempS;
                isWhiteTurn = !isWhiteTurn;
                isSelected = false;
            }
        });
    }

    @Override
    public void onClick(View v){
        int r = -1;
        int c = -1;
        switch (v.getId()) {
            case R.id.R00:
                r = 0;
                c = 0;
                break;
            case R.id.R10:
                r = 1;
                c = 0;
                break;
            case R.id.R20:
                r = 2;
                c = 0;
                break;
            case R.id.R30:
                r = 3;
                c = 0;
                break;
            case R.id.R40:
                r = 4;
                c = 0;
                break;
            case R.id.R50:
                r = 5;
                c = 0;
                break;
            case R.id.R60:
                r = 6;
                c = 0;
                break;
            case R.id.R70:
                r = 7;
                c = 0;
                break;
            case R.id.R01:
                r = 0;
                c = 1;
                break;
            case R.id.R11:
                r = 1;
                c = 1;
                break;
            case R.id.R21:
                r = 2;
                c = 1;
                break;
            case R.id.R31:
                r = 3;
                c = 1;
                break;
            case R.id.R41:
                r = 4;
                c = 1;
                break;
            case R.id.R51:
                r = 5;
                c = 1;
                break;
            case R.id.R61:
                r = 6;
                c = 1;
                break;
            case R.id.R71:
                r = 7;
                c = 1;
                break;
            case R.id.R02:
                r = 0;
                c = 2;
                break;
            case R.id.R12:
                r = 1;
                c = 2;
                break;
            case R.id.R22:
                r = 2;
                c = 2;
                break;
            case R.id.R32:
                r = 3;
                c = 2;
                break;
            case R.id.R42:
                r = 4;
                c = 2;
                break;
            case R.id.R52:
                r = 5;
                c = 2;
                break;
            case R.id.R62:
                r = 6;
                c = 2;
                break;
            case R.id.R72:
                r = 7;
                c = 2;
                break;
            case R.id.R03:
                r = 0;
                c = 3;
                break;
            case R.id.R13:
                r = 1;
                c = 3;
                break;
            case R.id.R23:
                r = 2;
                c = 3;
                break;
            case R.id.R33:
                r = 3;
                c = 3;
                break;
            case R.id.R43:
                r = 4;
                c = 3;
                break;
            case R.id.R53:
                r = 5;
                c = 3;
                break;
            case R.id.R63:
                r = 6;
                c = 3;
                break;
            case R.id.R73:
                r = 7;
                c = 3;
                break;
            case R.id.R04:
                r = 0;
                c = 4;
                break;
            case R.id.R14:
                r = 1;
                c = 4;
                break;
            case R.id.R24:
                r = 2;
                c = 4;
                break;
            case R.id.R34:
                r = 3;
                c = 4;
                break;
            case R.id.R44:
                r = 4;
                c = 4;
                break;
            case R.id.R54:
                r = 5;
                c = 4;
                break;
            case R.id.R64:
                r = 6;
                c = 4;
                break;
            case R.id.R74:
                r = 7;
                c = 4;
                break;
            case R.id.R05:
                r = 0;
                c = 5;
                break;
            case R.id.R15:
                r = 1;
                c = 5;
                break;
            case R.id.R25:
                r = 2;
                c = 5;
                break;
            case R.id.R35:
                r = 3;
                c = 5;
                break;
            case R.id.R45:
                r = 4;
                c = 5;
                break;
            case R.id.R55:
                r = 5;
                c = 5;
                break;
            case R.id.R65:
                r = 6;
                c = 5;
                break;
            case R.id.R75:
                r = 7;
                c = 5;
                break;
            case R.id.R06:
                r = 0;
                c = 6;
                break;
            case R.id.R16:
                r = 1;
                c = 6;
                break;
            case R.id.R26:
                r = 2;
                c = 6;
                break;
            case R.id.R36:
                r = 3;
                c = 6;
                break;
            case R.id.R46:
                r = 4;
                c = 6;
                break;
            case R.id.R56:
                r = 5;
                c = 6;
                break;
            case R.id.R66:
                r = 6;
                c = 6;
                break;
            case R.id.R76:
                r = 7;
                c = 6;
                break;
            case R.id.R07:
                r = 0;
                c = 7;
                break;
            case R.id.R17:
                r = 1;
                c = 7;
                break;
            case R.id.R27:
                r = 2;
                c = 7;
                break;
            case R.id.R37:
                r = 3;
                c = 7;
                break;
            case R.id.R47:
                r = 4;
                c = 7;
                break;
            case R.id.R57:
                r = 5;
                c = 7;
                break;
            case R.id.R67:
                r = 6;
                c = 7;
                break;
            case R.id.R77:
                r = 7;
                c = 7;
                break;
        }
        Log.w("isWhiteTurn",isWhiteTurn+"");
        Log.w("board[c][r]",board[c][r]+"");
        Log.w("c&r",c+" "+r);
        Log.v("Selected",selected[0]+" "+selected[1]);
        if ( ( isWhiteTurn && (board[c][r] > 0 || isSelected ) )
                || ( !isWhiteTurn && (board[c][r] < 0 || isSelected ) ) ){
            if ( selected[0] == -1 && selected[1] == -1) {
                switch (v.getId()) {
                    case R.id.R00:
                        selected[0] = 0;
                        selected[1] = 0;
                        break;
                    case R.id.R10:
                        selected[0] = 1;
                        selected[1] = 0;
                        break;
                    case R.id.R20:
                        selected[0] = 2;
                        selected[1] = 0;
                        break;
                    case R.id.R30:
                        selected[0] = 3;
                        selected[1] = 0;
                        break;
                    case R.id.R40:
                        selected[0] = 4;
                        selected[1] = 0;
                        break;
                    case R.id.R50:
                        selected[0] = 5;
                        selected[1] = 0;
                        break;
                    case R.id.R60:
                        selected[0] = 6;
                        selected[1] = 0;
                        break;
                    case R.id.R70:
                        selected[0] = 7;
                        selected[1] = 0;
                        break;
                    case R.id.R01:
                        selected[0] = 0;
                        selected[1] = 1;
                        break;
                    case R.id.R11:
                        selected[0] = 1;
                        selected[1] = 1;
                        break;
                    case R.id.R21:
                        selected[0] = 2;
                        selected[1] = 1;
                        break;
                    case R.id.R31:
                        selected[0] = 3;
                        selected[1] = 1;
                        break;
                    case R.id.R41:
                        selected[0] = 4;
                        selected[1] = 1;
                        break;
                    case R.id.R51:
                        selected[0] = 5;
                        selected[1] = 1;
                        break;
                    case R.id.R61:
                        selected[0] = 6;
                        selected[1] = 1;
                        break;
                    case R.id.R71:
                        selected[0] = 7;
                        selected[1] = 1;
                        break;
                    case R.id.R02:
                        selected[0] = 0;
                        selected[1] = 2;
                        break;
                    case R.id.R12:
                        selected[0] = 1;
                        selected[1] = 2;
                        break;
                    case R.id.R22:
                        selected[0] = 2;
                        selected[1] = 2;
                        break;
                    case R.id.R32:
                        selected[0] = 3;
                        selected[1] = 2;
                        break;
                    case R.id.R42:
                        selected[0] = 4;
                        selected[1] = 2;
                        break;
                    case R.id.R52:
                        selected[0] = 5;
                        selected[1] = 2;
                        break;
                    case R.id.R62:
                        selected[0] = 6;
                        selected[1] = 2;
                        break;
                    case R.id.R72:
                        selected[0] = 7;
                        selected[1] = 2;
                        break;
                    case R.id.R03:
                        selected[0] = 0;
                        selected[1] = 3;
                        break;
                    case R.id.R13:
                        selected[0] = 1;
                        selected[1] = 3;
                        break;
                    case R.id.R23:
                        selected[0] = 2;
                        selected[1] = 3;
                        break;
                    case R.id.R33:
                        selected[0] = 3;
                        selected[1] = 3;
                        break;
                    case R.id.R43:
                        selected[0] = 4;
                        selected[1] = 3;
                        break;
                    case R.id.R53:
                        selected[0] = 5;
                        selected[1] = 3;
                        break;
                    case R.id.R63:
                        selected[0] = 6;
                        selected[1] = 3;
                        break;
                    case R.id.R73:
                        selected[0] = 7;
                        selected[1] = 3;
                        break;
                    case R.id.R04:
                        selected[0] = 0;
                        selected[1] = 4;
                        break;
                    case R.id.R14:
                        selected[0] = 1;
                        selected[1] = 4;
                        break;
                    case R.id.R24:
                        selected[0] = 2;
                        selected[1] = 4;
                        break;
                    case R.id.R34:
                        selected[0] = 3;
                        selected[1] = 4;
                        break;
                    case R.id.R44:
                        selected[0] = 4;
                        selected[1] = 4;
                        break;
                    case R.id.R54:
                        selected[0] = 5;
                        selected[1] = 4;
                        break;
                    case R.id.R64:
                        selected[0] = 6;
                        selected[1] = 4;
                        break;
                    case R.id.R74:
                        selected[0] = 7;
                        selected[1] = 4;
                        break;
                    case R.id.R05:
                        selected[0] = 0;
                        selected[1] = 5;
                        break;
                    case R.id.R15:
                        selected[0] = 1;
                        selected[1] = 5;
                        break;
                    case R.id.R25:
                        selected[0] = 2;
                        selected[1] = 5;
                        break;
                    case R.id.R35:
                        selected[0] = 3;
                        selected[1] = 5;
                        break;
                    case R.id.R45:
                        selected[0] = 4;
                        selected[1] = 5;
                        break;
                    case R.id.R55:
                        selected[0] = 5;
                        selected[1] = 5;
                        break;
                    case R.id.R65:
                        selected[0] = 6;
                        selected[1] = 5;
                        break;
                    case R.id.R75:
                        selected[0] = 7;
                        selected[1] = 5;
                        break;
                    case R.id.R06:
                        selected[0] = 0;
                        selected[1] = 6;
                        break;
                    case R.id.R16:
                        selected[0] = 1;
                        selected[1] = 6;
                        break;
                    case R.id.R26:
                        selected[0] = 2;
                        selected[1] = 6;
                        break;
                    case R.id.R36:
                        selected[0] = 3;
                        selected[1] = 6;
                        break;
                    case R.id.R46:
                        selected[0] = 4;
                        selected[1] = 6;
                        break;
                    case R.id.R56:
                        selected[0] = 5;
                        selected[1] = 6;
                        break;
                    case R.id.R66:
                        selected[0] = 6;
                        selected[1] = 6;
                        break;
                    case R.id.R76:
                        selected[0] = 7;
                        selected[1] = 6;
                        break;
                    case R.id.R07:
                        selected[0] = 0;
                        selected[1] = 7;
                        break;
                    case R.id.R17:
                        selected[0] = 1;
                        selected[1] = 7;
                        break;
                    case R.id.R27:
                        selected[0] = 2;
                        selected[1] = 7;
                        break;
                    case R.id.R37:
                        selected[0] = 3;
                        selected[1] = 7;
                        break;
                    case R.id.R47:
                        selected[0] = 4;
                        selected[1] = 7;
                        break;
                    case R.id.R57:
                        selected[0] = 5;
                        selected[1] = 7;
                        break;
                    case R.id.R67:
                        selected[0] = 6;
                        selected[1] = 7;
                        break;
                    case R.id.R77:
                        selected[0] = 7;
                        selected[1] = 7;
                        break;
                }
                if ( board[selected[1]][selected[0]] == 0 ) {
                    selected[0] = -1;
                    selected[1] = -1;
                }
                isSelected = true;
            } else if ( !(selected[0] == r && selected[1] == c) ) {
                Log.v("board[s1][s0]",board[selected[1]][selected[0]]+"");
                if ( ( c == 0 || c == 7 ) && Math.abs(board[selected[1]][selected[0]]) == 1 ) {
                    Log.v("Reached","true");
                    updateOldBoard();
                    togglePawnPromotion();
                    TextView pawnKnight = findViewById(R.id.PawnKnight);
                    TextView pawnBishop = findViewById(R.id.PawnBishop);
                    TextView pawnRook = findViewById(R.id.PawnRook);
                    TextView pawnQueen = findViewById(R.id.PawnQueen);
                    cr[0] = c;
                    cr[1] = r;
                    pawnKnight.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            board[selected[1]][selected[0]] = 3*board[selected[1]][selected[0]];
                            togglePawnPromotion();
                            updateBoard(selected[1], selected[0], cr[0], cr[1]);
                            updateDisplayBoard();
                            selected[0] = -1;
                            selected[1] = -1;
                            isWhiteTurn = !isWhiteTurn;
                            isSelected = false;
                        }
                    });
                    pawnBishop.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            board[selected[1]][selected[0]] = 2*board[selected[1]][selected[0]];
                            togglePawnPromotion();
                            updateBoard(selected[1], selected[0], cr[0], cr[1]);
                            updateDisplayBoard();
                            selected[0] = -1;
                            selected[1] = -1;
                            isWhiteTurn = !isWhiteTurn;
                            isSelected = false;
                        }
                    });
                    pawnRook.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            board[selected[1]][selected[0]] = 4*board[selected[1]][selected[0]];
                            togglePawnPromotion();
                            updateBoard(selected[1], selected[0], cr[0], cr[1]);
                            updateDisplayBoard();
                            selected[0] = -1;
                            selected[1] = -1;
                            isWhiteTurn = !isWhiteTurn;
                            isSelected = false;
                        }
                    });
                    pawnQueen.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            board[selected[1]][selected[0]] = 5*board[selected[1]][selected[0]];
                            togglePawnPromotion();
                            updateBoard(selected[1], selected[0], cr[0], cr[1]);
                            updateDisplayBoard();
                            selected[0] = -1;
                            selected[1] = -1;
                            isWhiteTurn = !isWhiteTurn;
                            isSelected = false;
                        }
                    });
                    Log.v("boardAtSelected",""+board[selected[1]][selected[0]]);
                } else {
                    updateBoard(selected[1], selected[0], c, r);
                    //updateBoard(0,0,4,4);
                    updateDisplayBoard();
                    selected[0] = -1;
                    selected[1] = -1;
                    isWhiteTurn = !isWhiteTurn;
                    isSelected = false;
                }
                updateDisplayBoard();
            }
        }
        Log.v("Selected",selected[0]+" "+selected[1]);
    }


    public void makeBoard(){
        int[][] temp = {{-4,-3,-2,-5,-6,-2,-3,-4},
                        {-1,-1,-1,-1,-1,-1,-1,-1},
                        {0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0},
                        {1,1,1,1,1,1,1,1},
                        {4,3,2,5,6,2,3,4}};
        board = temp;
        int[][] temp2 = {{-4,-3,-2,-5,-6,-2,-3,-4},
                        {-1,-1,-1,-1,-1,-1,-1,-1},
                        {0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0},
                        {1,1,1,1,1,1,1,1},
                        {4,3,2,5,6,2,3,4}};
        oldBoard = temp2;
        int[] tempS  = {-1,-1};
        selected = tempS;
        isWhiteTurn = true;
        isSelected = false;
    }

    public void updateBoard(int oldRow, int oldCol, int newRow, int newCol ){
        updateOldBoard();
        board[newRow][newCol] = board[oldRow][oldCol];
        board[oldRow][oldCol] = 0;
    }

    public void updateOldBoard(){
        for ( int i = 0; i < 8; i++){
            oldBoard[i] = Arrays.copyOf(board[i], 8);
        }
    }

    public void updateNewBoard(){
        for ( int i = 0; i < 8; i++ ){
            board[i] = Arrays.copyOf(oldBoard[i],8);
        }
    }

    public void updateDisplayBoard(){
        for ( int c = 0; c < 8; c++ ){
            for ( int r = 0; r < 8; r++ ){
                if ( board[r][c] == 0 )
                    DisplayBoard[c][r].setBackgroundResource(0);
                else if ( board[r][c] == 1 )
                    DisplayBoard[c][r].setBackgroundResource(R.drawable.wpawn);
                else if ( board[r][c] == -1 )
                    DisplayBoard[c][r].setBackgroundResource(R.drawable.bpawn);
                else if ( board[r][c] == 2 )
                    DisplayBoard[c][r].setBackgroundResource(R.drawable.wbishop);
                else if ( board[r][c] == -2 )
                    DisplayBoard[c][r].setBackgroundResource(R.drawable.bbishop);
                else if ( board[r][c] == 3 )
                    DisplayBoard[c][r].setBackgroundResource(R.drawable.wknight);
                else if ( board[r][c] == -3 )
                    DisplayBoard[c][r].setBackgroundResource(R.drawable.bknight);
                else if ( board[r][c] == 4 )
                    DisplayBoard[c][r].setBackgroundResource(R.drawable.wrook);
                else if ( board[r][c] == -4 )
                    DisplayBoard[c][r].setBackgroundResource(R.drawable.brook);
                else if ( board[r][c] == 5 )
                    DisplayBoard[c][r].setBackgroundResource(R.drawable.wqueen);
                else if ( board[r][c] == -5 )
                    DisplayBoard[c][r].setBackgroundResource(R.drawable.bqueen);
                else if ( board[r][c] == 6 )
                    DisplayBoard[c][r].setBackgroundResource(R.drawable.wking);
                else if ( board[r][c] == -6 )
                    DisplayBoard[c][r].setBackgroundResource(R.drawable.bking);
            }
        }
    }

    public boolean isCheckmate(){
        return false;
    }

    public boolean isCheck(){
        return false;
    }

    public boolean isValid(int r, int c, int piece){

        return true;
    }

    public void togglePawnPromotion(){
        LinearLayout promotion = (LinearLayout) findViewById(R.id.pawnPromotion);
        GridLayout layout =  findViewById(R.id.gridLayout);
        GridLayout layout2 =  findViewById(R.id.gridLayout2);
        if ( promotion.getVisibility() == View.VISIBLE) {
            promotion.setVisibility(View.INVISIBLE);
            layout.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);
        } else {
            promotion.setVisibility(View.VISIBLE);
            layout.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.INVISIBLE);
        }
    }
}
