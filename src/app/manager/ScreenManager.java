package app.manager;

import game.ai.AiBase;
import game.ai.impl.HiranoTest;
import game.ai.impl.Human;
import game.ai.impl.Ikeda;
import game.ai.impl.MH_Spart;
import game.ai.impl.MaxStone;
import game.ai.impl.MaxStoneR;
import game.ai.impl.NoSafety;
import game.ai.impl.OneWay;
import game.ai.impl.SN_Ai;
import game.ai.impl.SteadyR;
import game.ai.impl.Uchida1;
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
	private static int LST_ID = 1;

	private static int LST_ROW_B_ID = 11;
	private static int LST_B_ID = 1;

	private Bitmap NONE_BMP;
	private Bitmap BLACK_BMP;
	private Bitmap WHITE_BMP;

	private Context context;
	private TableLayout tableLayout;

	private ArrayAdapter<AiBase> arAdpW;
	private ArrayAdapter<AiBase> arAdpB;

	public ScreenManager(Context context, TableLayout tableLayout) {
		this.context = context;
		this.tableLayout = tableLayout;

		NONE_BMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.none);
		BLACK_BMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.black);
		WHITE_BMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.white);

		arAdpW = new ArrayAdapter<AiBase>(context, android.R.layout.simple_spinner_item);
		arAdpW.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		arAdpW.add(new Human(Stone.WHITE));
		arAdpW.add(new OneWay(Stone.WHITE));
		arAdpW.add(new MaxStone(Stone.WHITE));
		arAdpW.add(new MaxStoneR(Stone.WHITE));
		arAdpW.add(new SteadyR(Stone.WHITE));
		arAdpW.add(new NoSafety(Stone.WHITE));
		arAdpW.add(new MH_Spart(Stone.WHITE));
		arAdpW.add(new Uchida1(Stone.WHITE));
		arAdpW.add(new SN_Ai(Stone.WHITE));
		arAdpW.add(new Ikeda(Stone.WHITE));
		arAdpW.add(new HiranoTest(Stone.WHITE));

		arAdpB = new ArrayAdapter<AiBase>(context, android.R.layout.simple_spinner_item);
		arAdpB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		arAdpB.add(new Human(Stone.BLACK));
		arAdpB.add(new OneWay(Stone.BLACK));
		arAdpB.add(new MaxStone(Stone.BLACK));
		arAdpB.add(new MaxStoneR(Stone.BLACK));
		arAdpB.add(new SteadyR(Stone.BLACK));
		arAdpB.add(new NoSafety(Stone.BLACK));
		arAdpB.add(new MH_Spart(Stone.BLACK));
		arAdpB.add(new Uchida1(Stone.BLACK));
		arAdpB.add(new SN_Ai(Stone.BLACK));
		arAdpB.add(new Ikeda(Stone.BLACK));
		arAdpB.add(new HiranoTest(Stone.BLACK));

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
		TableRow.LayoutParams trl2= new TableRow.LayoutParams();
		trl2.span = Common.X_MAX_LEN-1;
		TableRow.LayoutParams trl3= new TableRow.LayoutParams();
		trl3.span = 1;

		tableRow.setId(LOG_ROW_ID);
		tv.setText("MSG");
		tv.setTextSize(25);
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

		TableRow tableRow3= new TableRow(context);
		TextView tvB = new TextView(context);
		Spinner spnB = new Spinner(context);
		tvB.setText  ("黒：");
		tvB.setId(POINT_ID);
		tableRow3.addView(tvB, trl3);

		tableRow3.setId(LST_ROW_B_ID);
		spnB.setId(LST_B_ID);
		spnB.setAdapter(arAdpB);

		tableRow3.addView(spnB, trl2);
		tableLayout.addView(tableRow3);


		TableRow tableRow4 = new TableRow(context);
		Spinner spn = new Spinner(context);

		TextView tvW = new TextView(context);
		tvW.setText  ("白：");
		tvW.setId(POINT_ID);
		tableRow4.addView(tvW, trl3);

		tableRow4.setId(LST_ROW_ID);
		spn.setId(LST_ID);
		spn.setAdapter(arAdpW);

		tableRow4.addView(spn, trl2);
		tableLayout.addView(tableRow4);


	}

	public void drawBord(Bord bord) {

		// 画像の拡大サイズを取得
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();
		float xSize = ((float) disp.getWidth() / (float) 8) / bmpWidth ;

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
		tv.setText("黒:" + blackCnt + "\r\n白:" + whiteCnt);

	}

	public AiBase getBAiMode() {
		TableRow tr = (TableRow) tableLayout.getChildAt(LST_ROW_B_ID);
		Spinner sp = (Spinner) tr.getChildAt(LST_B_ID);
		AiBase retAi = (AiBase) sp.getSelectedItem();
		return retAi;
	}

	public AiBase getWAiMode() {
		TableRow tr = (TableRow) tableLayout.getChildAt(LST_ROW_ID);
		Spinner sp = (Spinner) tr.getChildAt(LST_ID);
		AiBase retAi = (AiBase) sp.getSelectedItem();
		return retAi;
	}

}
