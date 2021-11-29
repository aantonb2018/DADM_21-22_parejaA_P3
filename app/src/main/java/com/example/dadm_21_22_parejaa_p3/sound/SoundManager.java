package com.example.dadm_21_22_parejaa_p3.sound;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;

import java.io.IOException;
import java.util.HashMap;

public final class SoundManager {

	private static final int MAX_STREAMS = 10;
	private static final float DEFAULT_MUSIC_VOLUME = 0.4f;

	private HashMap<GameEvent, Integer> soundsMap;
	
	private Context context;
	private SoundPool soundPool;
	private MediaPlayer bgPlayer;

	private String themes[] = {"sfx/bgm_menu.mp3", "sfx/bgm_levelone.mp3", "sfx/bgm_leveltwo.mp3", "sfx/bgm_victory.mp3", "sfx/bgm_lose.mp3"};
	private int themeSelect = 0;

	private int length;

	public SoundManager(Context context) {
		this.context = context;
		loadSounds();
		loadMusic();
	}

	private void loadEventSound(Context context, GameEvent event, String... filename) {
		try {
			AssetFileDescriptor descriptor = context.getAssets().openFd("sfx/" + filename[0]);
			int soundId = soundPool.load(descriptor, 1);
			soundsMap.put(event, soundId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void playSoundForGameEvent(GameEvent event) {
		Integer soundId = soundsMap.get(event);
		if (soundId != null) {
			// Left Volume, Right Volume, priority (0 == lowest), loop (0 == no) and rate (1.0 normal playback rate)
			//System.out.println("Sonido");
			soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
		}
	}

	private void loadSounds() {
		createSoundPool();
		soundsMap = new HashMap<GameEvent, Integer>();
		loadEventSound(context, GameEvent.AsteroidHit, "sfx_boom.wav");
		loadEventSound(context, GameEvent.SpaceshipHit, "sfx_hit.wav");
		loadEventSound(context, GameEvent.LaserFired, "sfx_blaster.wav");
	}

	private void loadMusic() {
		try {
			// Important to not reuse it. It can be on a strange state
			bgPlayer = new MediaPlayer();
			AssetFileDescriptor afd = context.getAssets().openFd(themes[themeSelect]);
			bgPlayer.setDataSource(afd.getFileDescriptor(),
					afd.getStartOffset(), afd.getLength());
			bgPlayer.setLooping(true);
			bgPlayer.setVolume(DEFAULT_MUSIC_VOLUME, DEFAULT_MUSIC_VOLUME);
			bgPlayer.prepare();
			bgPlayer.start();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createSoundPool() {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
			soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
		}
		else {
			AudioAttributes audioAttributes = new AudioAttributes.Builder()
					.setUsage(AudioAttributes.USAGE_GAME)
					.setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
					.build();
			soundPool = new SoundPool.Builder()
					.setAudioAttributes(audioAttributes)
					.setMaxStreams(MAX_STREAMS)
					.build();
		}
	}

	private void unloadSounds() {
		soundPool.release();
		soundPool = null;
		soundsMap.clear();		
	}

	private void unloadMusic() {
		bgPlayer.stop();
		bgPlayer.release();
	}

	public void changeTheme(int newTheme){
		themeSelect = newTheme;
		unloadMusic();
		loadMusic();
	}

	public void pauseMusic(){
		bgPlayer.pause();
		length = bgPlayer.getCurrentPosition();
	}

	public void resumeMusic(){
		bgPlayer.start();
		bgPlayer.seekTo(length);
	}
}
