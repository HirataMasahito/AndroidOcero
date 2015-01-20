package app.layout;

import game.ai.AiBase;
import game.ai.impl.*;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import app.manager.GameManager;

import common.Common.Stone;

public class app extends Activity {

	private GameManager gc;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TableLayout tableLayout = (TableLayout) findViewById(R.id.TableLayout01);
		 AiBase player1 = new Human(Stone.BLACK);
		 AiBase player2 = new Human(Stone.WHITE);
		gc = new GameManager(this,tableLayout, player1, player2);
		gc.GameStart();

	}


}