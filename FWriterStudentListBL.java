package J4_ex1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**----------------------------------------------------------------------*
 *■■■FWriterStudentListBLクラス■■■
 *概要：ビジネスロジック（ユーザーの情報抽出）
 *----------------------------------------------------------------------**/
public class FWriterStudentListBL {

	//定数
	private final String COMMA = ","; //コンマ
	final String FILE_PATH = "C:\\WorkSpace\\J4-EX_1.csv";      //書き出し用ファイルのパス情報
	final String NEW_LINE  = System.getProperty("line.separator");    //改行

	String str = "";

	/**----------------------------------------------------------------------*
	 *■extractionメソッド
	 *概要　：対象のユーザー情報を抽出し、コマンドライン上に表示する
	 *引数　：対象のユーザーID
	 *戻り値：なし
	 *----------------------------------------------------------------------**/
	public void extractAll () {

		//-------------------------------------------
		//データベースへの接続を実施
		//-------------------------------------------

		//DAOクラスをインスタンス化＆指定のIDと合致するデータを抽出するよう依頼
		StudentListDao dao = new StudentListDao();
		List<StudentListDto> extractedDtoList = dao.selectMemberInfoAll();

		//-------------------------------------------
		//抽出したユーザー情報をコマンドライン上に表示
		//-------------------------------------------
		if(extractedDtoList != null){

			for(int i = 0 ; i < extractedDtoList.size() ; i++){

				//1レコード分のデータを取得＆加工（各カラムをコンマ綴りで結合）
				StringBuffer rsbuf = new StringBuffer();
				rsbuf.append(extractedDtoList.get(i).getStudentName());
				rsbuf.append(COMMA);
				rsbuf.append(extractedDtoList.get(i).getGender());
				rsbuf.append(COMMA);
				rsbuf.append(extractedDtoList.get(i).getAge());
				rsbuf.append(COMMA);
				rsbuf.append(extractedDtoList.get(i).getCareerMon());
				rsbuf.append(COMMA);
				rsbuf.append(extractedDtoList.get(i).getBranchName());
				rsbuf.append(COMMA);
				rsbuf.append(extractedDtoList.get(i).getCourseName());

				//加工作成した1レコード分のデータを表示
				System.out.println(rsbuf.toString());
				str += rsbuf.toString() + NEW_LINE;

			}
		} else {
			System.out.println("[INFO]該当のユーザー情報を取得できませんでした" ) ;
		}

	}

	public void writeFile() {

		try{
			//書き出し用ファイルの読み込み
			File file = new File( FILE_PATH );

			//書き出し用ファイルをFileWriterにセット
			FileWriter fileWriter = new FileWriter(file);

			//書き出しを実行
			fileWriter.write("#受講生一覧--");
			fileWriter.write( NEW_LINE );
			fileWriter.write("名前");
			fileWriter.write( COMMA );
			fileWriter.write("性別");
			fileWriter.write( COMMA );
			fileWriter.write("年齢");
			fileWriter.write( COMMA );
			fileWriter.write("職歴");
			fileWriter.write( COMMA );
			fileWriter.write("登録支店");
			fileWriter.write( COMMA );
			fileWriter.write("受講講座");
			fileWriter.write( NEW_LINE );
			fileWriter.write( str );
			fileWriter.write( NEW_LINE );

			//書き出し用ファイルを閉じる
			fileWriter.close();

		}catch(IOException e){
			System.out.println(e);
		}

	}





}