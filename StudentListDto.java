package J4_ex1;

/**----------------------------------------------------------------------*
 *■■■StudentListDtoクラス■■■
 *概要：DTO（uzuz_memberテーブル）
 *----------------------------------------------------------------------**/
public class StudentListDto {

	//----------------------------------------------------------------
	//フィールド
	//----------------------------------------------------------------
	private String student_name   ;  //名前
	private String gender        ;  //性別（男性：1／女性：2）
	private int    age           ;  //年齢
	private int    career_mon     ;  //職歴
	private String branch_name    ;  //登録支店
	private String course_name    ;  //受講講座

	//----------------------------------------------------------------
	//getter/setter
	//----------------------------------------------------------------

	//getter/setter（対象フィールド：studentName）
	public String getStudentName() {return student_name;}
	public void setStudentName(String student_name) {this.student_name = student_name;}

	//getter/setter（対象フィールド：gender）
	public  String getGender() {
		String tmpGender = "";
		switch(gender) {
		case "1": tmpGender = "男性";
			break;
		case "2": tmpGender = "女性";
			break;
		}
		return tmpGender;
	}

	public void setGender(String gender) {this.gender = gender;}

	//getter/setter（対象フィールド：age）
	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}

	//getter/setter（対象フィールド：careerMon）
	public String getCareerMon() {
		String tmpCareerMon = "";
		if(career_mon % 12 == 0) {
			tmpCareerMon = Integer.toString(career_mon / 12) + "年";
		} else if(career_mon < 12) {
			tmpCareerMon = Integer.toString(career_mon) + "か月";
		} else {
			tmpCareerMon = Integer.toString(career_mon / 12) + "年" + Integer.toString(career_mon % 12) + "か月";
		}
		return tmpCareerMon;
	}
	public void setCareerMon(int career_mon) {this.career_mon = career_mon;}

	//getter/setter（対象フィールド：branchName）
	public String getBranchName() {return branch_name;}
	public void setBranchName(String branch_name) {this.branch_name = branch_name;}

	//getter/setter（対象フィールド：courseName）
	public String getCourseName() {
		String tmpCourseName = "";
		if(course_name != null) {
			tmpCourseName = course_name;
		} else {
			tmpCourseName = "受講なし";
		}
		return tmpCourseName;
	}
	public void setCourseName(String course_name) {this.course_name = course_name;}
}