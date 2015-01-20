package app.manager;

import game.ai.AiBase;
import game.othello.Bord;

import java.util.ArrayList;

import android.content.Context;
import android.os.Handler;
import android.widget.TableLayout;
import app.listener.BordClickListener;
import app.listener.BtnClickListener;

import common.Pos;

/**
 * ゲーム進行を管理する
 *
 * @author 本体
 *
 */
public class GameManager {
	private ArrayList<AiBase> PlayerList;

	private Bord GAME_BORD;

	private boolean passFlg = false;
	private int gameCnt = 0;

	private ScreenManager sm;

	private boolean gameStartFlg;

	Handler mHandler = new Handler();

	public GameManager() {
		PlayerList = new ArrayList<AiBase>();
		GAME_BORD = new Bord();
		gameStartFlg = false;
	}

	public GameManager(AiBase Player1, AiBase Player2) {
		this();
		PlayerList.add(Player1);
		PlayerList.add(Player2);
	}

	public GameManager(Context context, TableLayout tableLayout, AiBase Player1, AiBase Player2) {
		this(Player1, Player2);

		sm = new ScreenManager(context, tableLayout);

		BordClickListener bcl = new BordClickListener(this);
		BtnClickListener btl = new BtnClickListener(this);

		sm.initLayout(bcl, btl);
	}

	public void GameStart() {
		gameStartFlg = true;
		passFlg = false;
		gameCnt = 0;

		GAME_BORD.BordInit();

		sm.drawBord(GAME_BORD);
	}

	public void GameReset() {
		gameStartFlg = true;
		passFlg = false;
		gameCnt = 1;
		PlayerList.set(0, sm.getBAiMode());
		PlayerList.set(1, sm.getWAiMode());
		GAME_BORD.BordInit();

		sm.drawBord(GAME_BORD);
	}

	public AiBase getNaePlayer() {
		return PlayerList.get(gameCnt % 2);
	}

	public Bord getBord() {
		return GAME_BORD;
	}

	public boolean isGameStart() {
		return gameStartFlg;
	}

	public void NextTurn() {

		Pos setHand = new Pos();

		AiBase turnPlayer = PlayerList.get(gameCnt % 2);

		setHand = turnPlayer.WhereSet(GAME_BORD);
		if (setHand == null) {
			passFlg = true;
		} else {
			GAME_BORD.DoSet(setHand, turnPlayer.getMyColor(), true);
			passFlg = false;
		}
		gameCnt++;

		sm.drawBord(GAME_BORD);

	}


}
