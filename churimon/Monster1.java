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
public class Monster1 {

	//------------フィールド------------
	String character = "(unknown)";  // 種族
	String trainer = "(wild)";       // トレーナー
	String name = "(noname)";        // なまえ
	int lv = 1;                      // レベル
	int hp = 80;                     // HP
	int atk = 15;                    // こうげき
	int def = 10;                    // ぼうぎょ
	int spd = 10;                    // すばやさ
	int hpMax = 80;                  // HP初期値
	String wazaNm = "たいあたり";    // わざ（なまえ）
	String wazaDmgRate = "1.0";      // わざ（ダメージ倍率）

	//------------フィールド(定数)------------
	final String WAZA_DMG_RATE_REGEXP = "^[0-9]+\\.[0-9]$"  ;  //バリデーションチェックで使用する正規表現
	final String DMG_CORRECTION_120 = "120" ;                     //ダメージ計算の補正で使う数字
	final String DMG_CORRECTION_1   = "1"   ;                     //ダメージ計算の補正で使う数字


	@Override
	public String toString() {
		return "character:" + this.character + " / trainer=" + this.trainer + " / name=" + this.name
				+ " / lv=" + this.lv + " / hp=" + this.hp + " / atk=" + this.atk + " / def=" + this.def
				+ " / spd=" + this.spd + " / hpMax=" + this.hpMax + " / wazaNm=" + this.wazaNm
				+ " / wazaDmgRate=" + this.wazaDmgRate;
	}

	void levelUp(int lvUp) {

		lv += lvUp * 1;
		hpMax += lvUp * 30;
		atk += lvUp * 5;
		def += lvUp * 5;
		spd += lvUp * 5;
		hp = this.hpMax;

	}

	void setWaza(String nm, String dmr) {

		String str = dmr;

			if(str.matches(WAZA_DMG_RATE_REGEXP)) {
				this.wazaNm = nm;
				this.wazaDmgRate = dmr;
			} else {
				System.out.println("[ERROR]わざの設定に失敗しました");
			}
	}

	String getStatus() {

		String status = "[ " + this.name + " lv" + this.lv + " HP" + this.hp + "/" + this.hpMax + " ]";

		return status;
	}

	int useWaza() {
		BigDecimal b1 = new BigDecimal(String.valueOf(this.atk));
		BigDecimal b2 = new BigDecimal(this.wazaDmgRate);
		int orgDm = (b1.multiply(b2)).intValue();

		return orgDm;
	}

	//damagedメソッド
	//[引数:値渡しされたダメージ（int型）/戻り値:実際に受けるダメージ（int型）/機能:実際に受けるダメージを計算してHPから減算する]
	int damaged(int orgDm) {
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



}
