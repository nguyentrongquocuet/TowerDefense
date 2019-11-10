package towerdefense;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class PlayMusic implements Runnable {
	Screen screen;
	Thread thread;
	public PlayMusic(Screen screen) {
		this.screen=screen;
		thread= new Thread(this);
		thread.start();
	}
	public static void playMusic(String s) {
		InputStream music;
		try {
			music = new FileInputStream(new File(s));
			AudioStream audioStream= new AudioStream(music);
			AudioPlayer.player.start(audioStream);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error");
		}
	}
	@Override
	public void run() {
		playMusic("res\\Sound\\gameSound.wav");
	}
}
