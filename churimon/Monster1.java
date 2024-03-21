/**
 *
 */
package churimon;

/**
 * @author shuhei.matsuo
 *
 */
public class Monster1 {

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


	final String WAZA_DMG_RATE_REGEXP = "^[0-9]+\\.[0-9]$"  ;  //バリデーションチェックで使用する正規表現


	@Override
	public String toString() {
		return "character:" + this.character + " / trainer=" + this.trainer + " / name=" + this.name
				+ " / lv=" + this.lv + " / hp=" + this.hp + " / atk=" + this.atk + " / def=" + this.def
				+ " / spd=" + this.spd + " / hpMax=" + this.hpMax + " / wazaNm=" + this.wazaNm
				+ " / wazaDmgRate=" + this.wazaDmgRate;
	}

	void levelUp(int lvUp) {

		this.lv += lvUp * 1;
		this.hpMax += lvUp * 30;
		this.atk += lvUp * 5;
		this.def += lvUp * 5;
		this.spd += lvUp * 5;
		this.hp = this.hpMax;

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
		BigDecimal b1 = ;

		return;
	}


}
