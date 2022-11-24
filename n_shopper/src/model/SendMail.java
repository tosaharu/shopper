package model;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

public class SendMail {
//	public static boolean sendOneTimePass(String mailTo,String oneTimePass){
//
//		final String mailFrom = "shopper.webmaster@gmail.com";
//		final String password = "swkauunyyldzuxki";
//
//		Properties props = new Properties();
//		//SMTPサーバの設定。ここではgoogleのSMTPサーバーを設定
//		props.setProperty("mail.smtp.host","smtp.gmail.com");
//		//SSL用にポート番号を変更
//		props.setProperty("mail.smtp.port", "587");
//		//タイムアウト設定
//		props.setProperty("mail.smtp.connectiontimeout", "60000");
//		props.setProperty("mail.smtp.timeout", "60000");
//
//		//認証
//		props.setProperty("mail.smtp.auth", "true");
//		//SSLを使用するとこはこの設定が必要
//		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		props.setProperty("mail.smtp.socketFactory.fallback", "false");
//		props.setProperty("mail.smtp.socketFactory.port","465");
//
//		//propsに設定した情報を使用して、sessionの作成
//		final Session session = Session.getInstance(props, new Authenticator() {
//		    protected PasswordAuthentication getPasswordAuthentication() {
//		        //ここで自分のgmailアカウントとパスワードを設定する
//		        return new PasswordAuthentication(mailFrom,password);
//		    }
//		});
//
//		//先ほどの変数sessionを設定する
//		MimeMessage contentMessage = new MimeMessage(session);
//		//メールのコンテンツ情報
//		String mailContents = "パスワードは\r\n"
//				+ oneTimePass + "\r\n"
//				+ "です。";
//		try {
//		    //送信元アドレス、表示名、文字コードを設定
//		    Address addFrom = new InternetAddress(mailFrom, mailFrom, "UTF-8");
//		    contentMessage.setFrom(addFrom);
//		    //送信先アドレス、表示名、文字コードを設定
//		    Address addTo = new InternetAddress(mailTo,mailTo,"UTF-8");
//		    contentMessage.addRecipient(Message.RecipientType.TO, addTo);
//		    //件名を設定
//		    contentMessage.setSubject("ワンタイムパスワードの送信","UTF-8");
//		    //メールのコンテンツタイプを指定している。この場合はHTMLメールになる
//		    contentMessage.setHeader("Content-Type", "text/plain; charset=UTF-8");
//		    //メールのコンテンツを設定している
//		    contentMessage.setContent(mailContents, "text/plain; charset=UTF-8");
//		    //日付等の設定
//		    contentMessage.setSentDate(new Date());
//		} catch (MessagingException e) {
//		    e.printStackTrace();
//		    return false;
//		} catch (UnsupportedEncodingException e) {
//		    e.printStackTrace();
//		    return false;
//		}
//
//		//メール送信
//		try {
//		    //先ほどのcontentMessageを設定する
//		    Transport.send(contentMessage);
//		    return true;
//		} catch (AuthenticationFailedException e) {
//		    //認証失敗
//		    e.printStackTrace();
//		    return false;
//		} catch (MessagingException e) {
//		    //smtpサーバへの接続失敗
//		    e.printStackTrace();
//		    return false;
//		}
//
//
//	}

	public static void sendOneTimePass(String toAddress,String pass) {
		 final String address = "shopper.webmaster@gmail.com";
		 final String password = "swkauunyyldzuxki";

	   try {
	    // プロパティの設定
	    Properties props = System.getProperties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");

	    Session session = Session.getInstance(props);
	    Message msg = new MimeMessage(session);

	    // 送信元メールアドレスのセット
	    msg.setFrom(new InternetAddress(address));

	    // 送信先メールアドレスのセット
	    msg.setRecipients(Message.RecipientType.TO,
	     InternetAddress.parse(toAddress, false));

	    // メールタイトル
	    msg.setSubject("ワンタイムパスワードの送信");

	    // メール本文
	    msg.setText(pass);

	    // メール送信
	    SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
	    try {
	     t.connect("smtp.gmail.com", address, password);
	     t.sendMessage(msg, msg.getAllRecipients());
	    } finally {
	     t.close();
	    }

	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	  }


}
