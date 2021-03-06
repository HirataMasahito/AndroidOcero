package common;

public class Common {
	/** 盤X最大列数 */
	public static final int X_MAX_LEN = 8;
	/** 盤X最低列数 */
	public static final int X_MIN_LEN = 0;

	/** 盤Y最大列数 */
	public static final int Y_MAX_LEN = 8;
	/** 盤Y最低列数 */
	public static final int Y_MIN_LEN = 0;

	public static final int[] MOVE_TO = {};

	/**
	 * オセロの石の状態
	 */
	public static enum Stone {
		BLACK("黒"), WHITE("白"), NONE("無");

		// メンバ変数の定義
		// このメンバ変数は必須です。
		private String name;

		/**
		 * このメソッドも必須です。
		 *
		 * @return enum型のvalue部分を返却します。
		 */
		public String getName() {
			return name;
		}

		// コンストラクタの実装
		private Stone(String name) {
			this.name = name;
		}

		public static Stone reverseStone(Stone stone) {
			Stone retStone = NONE;
			switch (stone) {
			case BLACK:
				retStone = WHITE;
				break;
			case WHITE:
				retStone = BLACK;
			}
			return retStone;

		}

		// メソッドのオーバーライド
		public String toString() {
			return name;
		}
	}

}
