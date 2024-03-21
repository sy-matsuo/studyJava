package churimon;

class Hitokake extends Monster3 {

	//------------コンストラクタ------------
	//コンストラクタ1[引数なし]
	Hitokake(){
		super.setCharacter("ヒトカケ");
	}
	//コンストラクタ2[引数1:トレーナー, 引数2:なまえ]
	Hitokake(String tr, String nm){
		super(tr,nm);
		super.setCharacter("ヒトカケ");
	}
	//コンストラクタ3[引数1:トレーナー, 引数2:なまえ, 引数3:レベル]
	Hitokake(String tr, String nm, int lev){
		super(tr,nm,lev);
		super.setCharacter("ヒトカケ");
	}

	//------------メソッド------------
	//levelUpメソッド
	//[引数:レベル（int型）/戻り値:なし/機能:引数を元にフィールドの値を更新する]
	public void levelUp(int lvUp){
		setLv(getLv() + 1 * lvUp )  ;
		setHpMax( getHpMax() + 29  * lvUp )  ;
		setAtk( getAtk() + 8 * lvUp )  ;
		setDef( getDef() + 5 * lvUp )  ;
		setSpd( getSpd() + 9 * lvUp )  ;
		setHp( getHpMax() )             ;
	}
}