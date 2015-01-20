package game.ai.impl;

import game.ai.AiBase;
import game.othello.Bord;

import common.Common.Stone;
import common.Pos;

public class MaxStone extends AiBase {

	public MaxStone(Stone MyColor) {
		super(MyColor);
	}

	@Override
	public Pos WhereSet(Bord bord) {
		return bord.SearchMaxPos(getMyColor());
	}

	@Override
	public String toString() {

		return "MaxStone";
	}

}
