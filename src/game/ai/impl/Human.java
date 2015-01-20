package game.ai.impl;

import game.ai.AiBase;
import game.othello.Bord;

import common.Common.Stone;
import common.Pos;

public class Human extends AiBase {

	public Human(Stone MyColor) {
		super(MyColor);
	}

	@Override
	public String toString() {

		return "Human";
	}

	private Pos setPos;

	@Override
	public Pos WhereSet(Bord bord) {
		return setPos;
	}


	public boolean getSet(Bord bord,Pos inputPos){
		boolean retFlg  = false;

		if (inputPos == null){
			retFlg = true;
			setPos = inputPos;

		}else if( bord.CanSet(inputPos, getMyColor())){
			retFlg = true;
			setPos = inputPos;
		}
		return retFlg ;

	}

}
