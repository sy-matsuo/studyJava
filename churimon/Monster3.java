/**
 *
 */
package churimon;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author shuhei.matsuo
 *
 */
public class Monster3 {

	//------------フィールド------------
	private String character ;  // 種族
	private String trainer ;       // トレーナー
	private String name ;        // なまえ
	private int lv ;                      // レベル
	private int hp ;                     // HP
	private int atk ;                    // こうげき
	private int def ;                    // ぼうぎょ
	private int spd ;                    // すばやさ
	private int hpMax ;                  // HP初期値
	private String wazaNm ;    // わざ（なまえ）
	private String wazaDmgRate ;      // わざ（ダメージ倍率）

	//------------フィールド(定数)------------
	final String WAZA_DMG_RATE_REGEXP = "^[0-9]+\\.[0-9]$"  ;  //バリデーションチェックで使用する正規表現
	final String DMG_CORRECTION_120 = "120" ;                     //ダメージ計算の補正で使う数字
	final String DMG_CORRECTION_1   = "1"   ;                     //ダメージ計算の補正で使う数字

	//------------コンストラクタ------------
	//コンストラクタ1[引数なし]
	Monster3() {

		this.character = "(unknown)";     // 種族
		this.trainer = "(wild)";          // トレーナー
		this.name = "(noname)";           // なまえ
		this.lv = 1;                      // レベル
		this.hp = 80;                     // HP
		this.atk = 15;                    // こうげき
		this.def = 10;                    // ぼうぎょ
		this.spd = 10;                    // すばやさ
		this.hpMax = 80;                  // HP初期値
		this.wazaNm = "たいあたり";       // わざ（なまえ）
		this.wazaDmgRate = "1.0";         // わざ（ダメージ倍率）

	}

	//コンストラクタ2[引数1:トレーナー, 引数2:なまえ]
	Monster3(String tr, String nm) {

		this();                       // コンストラクタ1呼び出し
		this.trainer = tr;            // トレーナー
		this.name = nm;               // なまえ

	}

	//コンストラクタ3[引数1:トレーナー, 引数2:なまえ, 引数3:レベル]
	Monster3(String tr, String nm, int lev) {
		this(tr, nm);                       // コンストラクタ2呼び出し

		if(lev > 1){
			levelUp(lev - 1);
		}
	}

	@Override
	public String toString() {
		return "character:" + this.character + " / trainer=" + this.trainer + " / name=" + this.name
				+ " / lv=" + this.lv + " / hp=" + this.hp + " / atk=" + this.atk + " / def=" + this.def
				+ " / spd=" + this.spd + " / hpMax=" + this.hpMax + " / wazaNm=" + this.wazaNm
				+ " / wazaDmgRate=" + this.wazaDmgRate;
	}

	public void levelUp(int lvUp) {

		this.lv += lvUp * 1;
		this.hpMax += lvUp * 30;
		this.atk += lvUp * 5;
		this.def += lvUp * 5;
		this.spd += lvUp * 5;
		this.hp = this.hpMax;

	}

	public void setWaza(String nm, String dmr) {

		String str = dmr;

			if(str.matches(WAZA_DMG_RATE_REGEXP)) {
				this.wazaNm = nm;
				this.wazaDmgRate = dmr;
			} else {
				System.out.println("[ERROR]わざの設定に失敗しました");
			}
	}

	public String getStatus() {

		String status = "[ " + this.name + " lv" + this.lv + " HP" + this.hp + "/" + this.hpMax + " ]";

		return status;
	}

	public int useWaza() {
		BigDecimal b1 = new BigDecimal(String.valueOf(this.atk));
		BigDecimal b2 = new BigDecimal(this.wazaDmgRate);
		int orgDm = (b1.multiply(b2)).intValue();

		return orgDm;
	}

	//damagedメソッド
	//[引数:値渡しされたダメージ（int型）/戻り値:実際に受けるダメージ（int型）/機能:実際に受けるダメージを計算してHPから減算する]
	public int damaged(int orgDm) {
		BigDecimal bdOrgDm   = new BigDecimal(orgDm);
		BigDecimal bdDmCr120 = new BigDecimal(DMG_CORRECTION_120);
		BigDecimal bdDmCr1   = new BigDecimal(DMG_CORRECTION_1);
		BigDecimal bdDef     = new BigDecimal(def);

		//ダメージ減算率：1 / (1＋ぼうぎょ÷120)  ※小数第３位切り捨て
		BigDecimal dmRate = bdDmCr1.divide( bdDmCr1.add( bdDef.divide(bdDmCr120, 2, RoundingMode.DOWN) ), 2, RoundingMode.DOWN );

		//BigDecimalで計算した値の小数点以下を切り捨て、BigDecimal型→int型へと変換
		//実際に受けるダメージ計算：受け取ったダメージ値×ダメージ減算率
		int dmg  = ( bdOrgDm.multiply( dmRate ) ).intValue();

		if( hp > dmg ) {
			hp -= dmg;
		} else {
			hp = 0;
		}

		return dmg;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getSpd() {
		return spd;
	}

	public void setSpd(int spd) {
		this.spd = spd;
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public String getWazaNm() {
		return wazaNm;
	}

	public void setWazaNm(String wazaNm) {
		this.wazaNm = wazaNm;
	}

	public String getWazaDmgRate() {
		return wazaDmgRate;
	}

	public void setWazaDmgRate(String wazaDmgRate) {
		this.wazaDmgRate = wazaDmgRate;
	}




}
