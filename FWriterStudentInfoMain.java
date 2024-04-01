package J4_ex1;

/**----------------------------------------------------------------------*
 *■■■FWriterStudentInfoMainクラス■■■
 *概要：メイン（ユーザーの情報抽出）
 *----------------------------------------------------------------------**/
public class FWriterStudentInfoMain {
	public static void main (String[] args) {

		//ビジネスロジック（ユーザーの情報抽出）クラスのインスタンス化＆メソッド起動
		FWriterStudentListBL blSel = new FWriterStudentListBL();
		blSel.extractAll();
		blSel.writeFile();

	}
}
