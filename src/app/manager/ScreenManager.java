package app.manager;

import game.ai.AiBase;
import game.ai.impl.MaxStone;
import game.ai.impl.MaxStoneR;
import game.ai.impl.OneWay;
import game.ai.impl.SteadyR;
import game.othello.Bord;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import app.layout.R;
import app.listener.BordClickListener;
import app.listener.BtnClickListener;

import common.Common;
import common.Common.Stone;
import common.Pos;

public class ScreenManager {

	private static float bmpWidth = 64;

	private static int LOG_ROW_ID = 8;
	private static int POINT_ID = 0;

	private static int BTN_ROW_ID = 9;
	private static int BTN_ID = 0;

	private static int LST_ROW_ID = 10;
	private static int LST_ID = 0;

	private Bitmap NONE_BMP;
	private Bitmap BLACK_BMP;
	private Bitmap WHITE_BMP;

	private Context context;
	private TableLayout tableLayout;

	private ArrayAdapter<AiBase> arAdp;

	public ScreenManager(Context context, TableLayout tableLayout) {
		this.context = context;
		this.tableLayout = tableLayout;

		NONE_BMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.none);
		BLACK_BMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.black);
		WHITE_BMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.white);

		arAdp = new ArrayAdapter<AiBase>(context, android.R.layout.simple_spinner_item);
		arAdp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		arAdp.add(new OneWay(Stone.WHITE));
		arAdp.add(new MaxStone(Stone.WHITE));
		arAdp.add(new MaxStoneR(Stone.WHITE));
		arAdp.add(new SteadyR(Stone.WHITE));

	}

	private Bitmap getStoneBmp(Stone color) {
		switch (color) {
		case BLACK:
			return BLACK_BMP;
		case WHITE:
			return WHITE_BMP;
		default:
			return NONE_BMP;
		}
	}

	/**
	 * 画面のレイアウトを初期化する
	 */
	public void initLayout(BordClickListener bcl, BtnClickListener btl) {

		// ボードを表示する
		for (int y = 0; y < Common.Y_MAX_LEN; y++) {
			TableRow tableRow = new TableRow(context);
			tableLayout.getChildAt(y);
			tableRow.setId(y);
			for (int x = 0; x < Common.X_MAX_LEN; x++) {

				ImageView iView = new ImageView(context);
				iView.setId(x);
				iView.setOnClickListener(bcl);
				tableRow.addView(iView);
			}

			tableLayout.addView(tableRow);
		}

		// メッセージ欄を表示する

		TableRow tableRow = new TableRow(context);
		TextView tv = new TextView(context);

		TableRow.LayoutParams trl = new TableRow.LayoutParams();
		trl.span = Common.X_MAX_LEN;

		tableRow.setId(LOG_ROW_ID);
		tv.setText("MSG");
		tv.setTextScaleX(5);
		tv.setId(POINT_ID);
		tableRow.addView(tv, trl);
		tableLayout.addView(tableRow);

		// リセットボタン
		TableRow tableRow2 = new TableRow(context);
		Button btn = new Button(context);
		btn.setText("RESET");
		btn.setOnClickListener(btl);
		btn.setId(BTN_ID);
		tableRow2.setId(BTN_ROW_ID);

		tableRow2.addView(btn, trl);
		tableLayout.addView(tableRow2);

		TableRow tableRow3 = new TableRow(context);
		Spinner spn = new Spinner(context);

		tableRow3.setId(LST_ROW_ID);
		spn.setId(LST_ID);
		spn.setAdapter(arAdp);

		tableRow3.addView(spn, trl);
		tableLayout.addView(tableRow3);

	}

	public void drawBord(Bord bord) {

		// 画像の拡大サイズを取得
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();
		float xSize = ((float) disp.getWidth() / (float) 8) / bmpWidth;

		Pos workPos = new Pos();
		for (int y = 0; y < Common.Y_MAX_LEN; y++) {
			TableRow tableRow = (TableRow) tableLayout.getChildAt(y);

			workPos.setY(y);

			for (int x = 0; x < Common.X_MAX_LEN; x++) {
				workPos.setX(x);

				Bitmap pBmp = getStoneBmp(bord.getColor(workPos));

				ImageView iView = (ImageView) tableRow.getChildAt(x);

				iView.setImageBitmap(pBmp);

				// ImageViewのMatrixに拡大率を指定
				Matrix m = iView.getImageMatrix();
				m.reset();
				m.postScale(xSize, xSize);
				iView.setImageMatrix(m);

			}
		}

		// ポイント表示
		TableRow tr = (TableRow) tableLayout.getChildAt(LOG_ROW_ID);
		TextView tv = (TextView) tr.getChildAt(POINT_ID);

		int blackCnt = bord.GetCount(Stone.BLACK);
		int whiteCnt = bord.GetCount(Stone.WHITE);
		tv.setText("黒:" + blackCnt + "白:" + whiteCnt);

	}

	public AiBase getAiMode() {
		TableRow tr = (TableRow) tableLayout.getChildAt(LST_ROW_ID);
		Spinner sp = (Spinner) tr.getChildAt(LST_ID);

		AiBase retAi = (AiBase) sp.getSelectedItem();

		return retAi;

	}

}
