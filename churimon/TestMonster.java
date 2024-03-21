package churimon;

//検証用クラス
class TestMonster {
	public static void main (String[] args) {

		Monster1 m1 = new Monster1();
		System.out.println(m1);

		m1.levelUp(5);
		System.out.println(m1);

		m1.levelUp(10);
		System.out.println(m1);

		m1.setWaza("かみつく", "12");
		System.out.println(m1);

		System.out.println(m1.getStatus());

	}
}