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

		System.out.println(m1.useWaza());

		m1.setWaza("かみつく", "5.0");
		System.out.println(m1);
		System.out.println(m1.useWaza());

		m1.damaged(m1.useWaza());
		System.out.println(m1);

		m1.damaged(m1.useWaza());
		System.out.println(m1);

		m1.damaged(m1.useWaza());
		System.out.println(m1);

	}
}