package pitao.sherwin.com.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MainActivity extends ActionBarActivity {
    Game game = new Game();
    GridView gridViewPlayer;
    TextView you,opp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        you = (TextView) findViewById(R.id.txtyou);
        opp = (TextView) findViewById(R.id.txtopp);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setPositiveButton("Computer (X)", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                game.setAttacker(Game.Player.X);

                Random firstmove = new Random();



               Log.d("move", "" + firstmove.nextInt(8));
                Log.d("move1","" + game.chooseMove());
            }
        });
        builder.setNegativeButton("You (O)", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                game.setAttacker(Game.Player.O);
            }
        });


        final AlertDialog dialog = builder.create();
        dialog.setMessage("Who Starts?");
        dialog.show();

        gridViewPlayer = (GridView) findViewById(R.id.gridView);
        refreshBoard();

        gridViewPlayer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ImageView imageView = ((GridViewAdapter.ViewHolder) view.getTag()).img;
/*

                    Toast.makeText(MainActivity.this, imageView.getId()+"", Toast.LENGTH_SHORT).show();
*/

                    imageView.setImageResource(game.getDrawableId());
                    game.getNextAttacker();
                    game.setValueOnBoard(i);

                    if (game.Won()) {

                        // Toast.makeText(MainActivity.this,game.getAttacker() + " Win the game",Toast.LENGTH_SHORT).show();
                        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText(game.getAttacker() + " Win the game!")
                                .setContentText("Play Again?")
                                .setConfirmText("Yes")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        refreshBoard();
                                        sDialog
                                                .setTitleText(game.getAttacker() + " Your turn!")
                                                .setConfirmText("OK")
                                                .setConfirmClickListener(null);
                                    }
                                })
                                .show();
                        if (game.getAttacker() == Game.Player.X) {
                            opp.setText((Integer.parseInt(opp.getText().toString()) + 1) + "");
                        } else if (game.getAttacker() == Game.Player.O) {
                            you.setText((Integer.parseInt(you.getText().toString()) + 1) + "");
                        }
                        game.InitBoard();
                    }else if(game.isDraw()){
                        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Game Draw!")
                                .setContentText("Play Again?")
                                .setConfirmText("Yes")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        refreshBoard();
                                        sDialog
                                                .setTitleText(game.getAttacker() + " Your turn!")
                                                .setConfirmText("OK")
                                                .setConfirmClickListener(null);
                                    }
                                })
                                .show();
                        game.InitBoard();
                    }
                }

        });

        Button btnShow = (Button) findViewById(R.id.button);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // game.ShowBoard();
                refreshBoard();
                game.InitBoard();
                opp.setText("0");
                you.setText("0");
            }
        });

    }

    private void refreshBoard(){
        gridViewPlayer.setAdapter(new GridViewAdapter(MainActivity.this, new int[]{R.drawable.ic_blank, R.drawable.ic_blank, R.drawable.ic_blank,
                R.drawable.ic_blank, R.drawable.ic_blank, R.drawable.ic_blank,
                R.drawable.ic_blank, R.drawable.ic_blank, R.drawable.ic_blank}));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
