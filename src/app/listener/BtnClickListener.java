package app.listener;

import android.view.View;
import android.view.View.OnClickListener;
import app.manager.GameManager;

public class BtnClickListener implements OnClickListener {

	private GameManager gc;

	public BtnClickListener(GameManager gc) {
		this.gc = gc;
	}

	public void onClick(View view) {
		//まずはボタンを取得
		//Button btn = (Button)view;

		if (gc.isGameStart()){
			// ゲームを初期化する
			gc.GameReset();
		}



	}

}
