package pitao.sherwin.com.tictactoe;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Android on 10/6/2015.
 */
public class Game{

    public Player Attacker;
    public Game(){
        InitBoard();
    }

    public void getNextAttacker(){
        Attacker = (Attacker == Player.O) ? Player.X:Player.O;
    }
    public Player getAttacker(){
        return (Attacker == Player.O) ? Player.X:Player.O;
    }
    public void setAttacker(Player player){
        Attacker = player;
    }

    public int getDrawableId(){
        if(Attacker == Player.O){
            return R.drawable.ic_o;
        }
        return R.drawable.ic_x;
    }

    public void setValueOnBoard(int cell){
        Board.set(cell,(Attacker == Player.O) ? Player.X:Player.O);
    }
    public void InitBoard(){
       Board.clear();

       for(int cell = 0; cell<9; cell++){
           Board.add(Player.S);
       }
    }

    public void ShowBoard(){
            System.out.print(Board);
            Log.d("Board",Board.toString());
    }
    private ArrayList<Player> Board = new ArrayList<>();

    public enum Player
    {
        X,
        O,
        S //Panggulo
    }

    public boolean Won(){
        if(checkStraight() || checkDiagonal()){
           return true;
        }
        return false;
    }

    private boolean checkStraight(){

        if(Board.get(0) == Board.get(1) && Board.get(0) == Board.get(2)){
            if(Board.get(0) != Player.S){
                return true;
            }
        }
        if(Board.get(3) == Board.get(4) && Board.get(3) == Board.get(5)){
            if(Board.get(3) != Player.S){
                return true;
            }
        }

        if(Board.get(6) == Board.get(7) && Board.get(6) == Board.get(8)){
            if(Board.get(6) != Player.S){
                return true;
            }
        }
        if(Board.get(0) == Board.get(3) && Board.get(0) == Board.get(6)){
            if(Board.get(0) != Player.S){
                return true;
            }
        }
        if(Board.get(2) == Board.get(5) && Board.get(2) == Board.get(8)){
            if(Board.get(2) != Player.S){
                return true;
            }
        }
        if(Board.get(1) == Board.get(4) && Board.get(1) == Board.get(7)){
            if(Board.get(1) != Player.S){
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonal(){
        if(Board.get(0) == Board.get(4) && Board.get(0) == Board.get(8)){
            if(Board.get(0) != Player.S){
                return true;
            }
        }
        if(Board.get(2) == Board.get(4) && Board.get(2) == Board.get(6)){
            if(Board.get(2) != Player.S){
                return true;
            }
        }
        return false;
    }
}
