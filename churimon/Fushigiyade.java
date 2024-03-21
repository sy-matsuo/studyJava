package churimon;

class Fushigiyade extends Monster3 {

	//------------コンストラクタ------------
	//コンストラクタ1[引数なし]
	Fushigiyade(){
		super.setCharacter("フシギヤデ");
	}
	//コンストラクタ2[引数1:トレーナー, 引数2:なまえ]
	Fushigiyade(String tr, String nm){
		super(tr,nm);
		super.setCharacter("フシギヤデ");
	}
	//コンストラクタ3[引数1:トレーナー, 引数2:なまえ, 引数3:レベル]
	Fushigiyade(String tr, String nm, int lev){
		super(tr,nm,lev);
		super.setCharacter("フシギヤデ");
	}

	//------------メソッド------------
	//levelUpメソッド
	//[引数:レベル（int型）/戻り値:なし/機能:引数を元にフィールドの値を更新する]
	public void levelUp(int lvUp){
		setLv(getLv() + 1 * lvUp )  ;
		setHpMax( getHpMax() + 31  * lvUp )  ;
		setAtk( getAtk() + 6 * lvUp )  ;
		setDef( getDef() + 7 * lvUp )  ;
		setSpd( getSpd() + 8 * lvUp )  ;
		setHp( getHpMax() )             ;
	}
}