package app.listener;

import game.ai.impl.Human;

import java.util.ArrayList;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TableRow;
import app.manager.GameManager;

import common.Pos;

public class BordClickListener implements OnClickListener {

	private GameManager gc;

	public BordClickListener(GameManager gc) {
		this.gc = gc;
	}

	public void onClick(View view) {
		if (gc.getNaePlayer() instanceof Human) {
			// 人の場合は、手入力を行う
			Human player = (Human) gc.getNaePlayer();

			ArrayList<Pos> pList = gc.getBord().getCanSetList(player.getMyColor());
			if (pList.size() > 0) {

				// おいた場所の座標を取得する
				ImageView iv = (ImageView) view;
				TableRow tr = (TableRow) iv.getParent();
				iv.getId();

				Pos setPos = new Pos(iv.getId(), tr.getId());
				if (!player.getSet(gc.getBord(), setPos)) {
					return;
				}
			} else {
				player.getSet(gc.getBord(), null);
			}

			gc.NextTurn();
		}
		gc.NextTurn();
	}

}
