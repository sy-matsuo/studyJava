package J4_ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**----------------------------------------------------------------------*
 *■■■StudentListDaoクラス■■■
 *概要：DAO（uzuz_memberテーブル）
 *----------------------------------------------------------------------**/
public class StudentListDao {

	//-------------------------------------------
	//データベースへの接続情報
	//-------------------------------------------

	//JDBCドライバの相対パス
	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	//接続先のデータベース
	String JDBC_URL    = "jdbc:mysql://localhost/test_db?characterEncoding=UTF-8";

	//接続するユーザー名
	String USER_ID     = "test_user";

	//接続するユーザーのパスワード
	String USER_PASS   = "test_pass";

	//----------------------------------------------------------------
	//メソッド
	//----------------------------------------------------------------

	/**----------------------------------------------------------------------*
	 *■selectMemberInfoAllメソッド
	 *概要　：「uzuz_member」テーブルのレコードをすべて抽出する
	 *引数　：なし
	 *戻り値：抽出データ（List<StudentListDto>型）
	 *----------------------------------------------------------------------**/
	public List<StudentListDto> selectMemberInfoAll(){

		//-------------------------------------------
		//JDBCドライバのロード
		//-------------------------------------------
		try {
			Class.forName(DRIVER_NAME);       //JDBCドライバをロード＆接続先として指定
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//-------------------------------------------
		//SQL発行
		//-------------------------------------------

		//JDBCの接続に使用するオブジェクトを宣言
		//※finallyブロックでも扱うためtryブロック内で宣言してはいけないことに注意
		Connection        con = null ;   // Connection（DB接続情報）格納用変数
		PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数
		ResultSet         rs  = null ;   // ResultSet（SQL抽出結果）格納用変数

		//抽出データ（List型）格納用変数
		//※最終的にreturnするため、tryブロック内で宣言してはいけないことに注意
		List<StudentListDto> dtoList = new ArrayList<StudentListDto>();

		try {

			//-------------------------------------------
			//接続の確立（Connectionオブジェクトの取得）
			//-------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//-------------------------------------------
			//SQL文の送信 ＆ 結果の取得
			//-------------------------------------------

			//発行するSQL文の生成（SELECT）
			StringBuffer buf = new StringBuffer();
			buf.append(" select          ");
			buf.append("  student_name , ");
			buf.append("  gender ,       ");
			buf.append("  age ,          ");
			buf.append("  career_mon ,   ");
			buf.append("  branch_name ,  ");
			buf.append("  course_name    ");
			buf.append(" from            ");
			buf.append("  (              ");
			buf.append(" uzuz_student a  ");
			buf.append(" inner join      ");
			buf.append("  branch b       ");
			buf.append(" on              ");
			buf.append(" a.BRANCH_ID     ");
			buf.append(" =               ");
			buf.append(" b.BRANCH_ID     ");
			buf.append(" )               ");
			buf.append(" left outer join ");
			buf.append("  course c       ");
			buf.append(" on              ");
			buf.append(" a.COURSE_ID     ");
			buf.append(" =               ");
			buf.append(" c.COURSE_ID     ");
			buf.append(" ;               ");

			//PreparedStatement（SQL発行用オブジェクト）を生成＆発行するSQLをセット
			ps = con.prepareStatement(buf.toString());

			//SQL文の送信＆戻り値としてResultSet（SQL抽出結果）を取得
			rs = ps.executeQuery();

			//ResultSetオブジェクトから1レコード分のデータをDTOに格納
			while(rs.next()){
				StudentListDto dto = new StudentListDto();
				dto.setStudentName( rs.getString( "student_name" ) );
				dto.setGender(      rs.getString( "gender"      ) );
				dto.setAge(         rs.getInt(    "age"         ) );
				dto.setCareerMon(   rs.getInt(    "career_mon"   ) );
				dto.setBranchName(  rs.getString( "branch_name"  ) );
				dto.setCourseName(  rs.getString( "course_name"  ) );
				dtoList.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			//-------------------------------------------
			//接続の解除
			//-------------------------------------------

			//ResultSetオブジェクトの接続解除
			if (rs != null) {    //接続が確認できている場合のみ実施
				try {
					rs.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//PreparedStatementオブジェクトの接続解除
			if (ps != null) {    //接続が確認できている場合のみ実施
				try {
					ps.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//Connectionオブジェクトの接続解除
			if (con != null) {    //接続が確認できている場合のみ実施
				try {
					con.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		//抽出データを戻す
		return dtoList;
	}
}