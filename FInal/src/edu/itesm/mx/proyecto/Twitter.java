package edu.itesm.mx.proyecto;
import javax.swing.*;

import twitter4j.conf.*;
import twitter4j.*;

import javax.swing.*;
import twitter4j.conf.*;
import twitter4j.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class Twitter<T> extends JFrame{


	private JButton consulta;
	private twitter4j.Twitter twitter;

	public Twitter(){
		super("Twitter");
	}
	public CircularDoubleLinkedList consultaTwitter(String con){
		CircularDoubleLinkedList<String> c=new CircularDoubleLinkedList();
		configuraTwitter();
		try {
			Query query = new Query(con);
			QueryResult result;
			int i = 0;
			do{
				result = twitter.search(query);

				List<Status> tweets = result.getTweets();

				for (Status tweet : tweets) {
					String cambio=tweet.getText();
					c.addLast(cambio);
				}
				i++;
			} while((query = result.nextQuery()) != null && i < 3);
		}
		catch (Exception te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
		}
		return c;
	}

	public DefaultListModel consultaTwitter(String con,DefaultListModel<String> m){
		configuraTwitter();
		try {
			Query query = new Query(con);
			QueryResult result;
			int i = 0;
			do{
				result = twitter.search(query);

				List<Status> tweets = result.getTweets();

				for (Status tweet : tweets) {
					String cambio="@" + tweet.getUser().getScreenName() + " - " + tweet.getText();
					m.addElement(cambio);
				}
				i++;
			} while((query = result.nextQuery()) != null && i < 3);
		}
		catch (Exception te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
		}
		return m;
	}

	public void configuraTwitter(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("QYtvCScBLH0ZYdbBC9dVJsbw7")
		.setOAuthConsumerSecret("0eb6kmnE6gR16ZTcGQMSDBCNJyUFCzDPcTABC6fK9zSuUWqGDN")
		.setOAuthAccessToken("41387638-0pubNqust6sqimkcTPcy4jSaiA4lkhKgd2HQY0avi")
		.setOAuthAccessTokenSecret("mxAGPW1Z4san5RgAt4aQ9G1kFWLh2T4d9kNoKvP8L0uwa");
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
	}


}

