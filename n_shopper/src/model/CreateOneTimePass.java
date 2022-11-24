package model;

import java.util.Random;

/**
 * 新規登録・パスワード再設定時に使用する6桁数字のワンタイムパスワードを発行する処理
 * @author Haruka Sato
 *
 */
public class CreateOneTimePass {
	public static String create() {
		Random rnd = new Random();

		int one = rnd.nextInt(10);
		int two = rnd.nextInt(10);
		int three = rnd.nextInt(10);
		int four = rnd.nextInt(10);
		int five = rnd.nextInt(10);
		int six = rnd.nextInt(10);

        StringBuilder sb = new StringBuilder();
        sb.append(one).append(two).append(three).append(four).append(five).append(six);
        return sb.toString();

	}

}
