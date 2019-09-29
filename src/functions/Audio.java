package functions;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio implements Runnable {
	
	private String songName;
	private Boolean stop = false;
	
	private Thread thread;
	private AudioInputStream audioStream;
	private SourceDataLine audioLine;
	
	public Audio(String songName){
		this.songName = songName;
		thread = new Thread(this, "Audio");
		thread.start();
	}
	
	private void playMusic(){
		File audioFile = new File(songName);
		
		try {
			audioStream = AudioSystem.getAudioInputStream(audioFile);
			AudioFormat format = audioStream.getFormat();
			
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
			audioLine = (SourceDataLine) AudioSystem.getLine(info);
			
			audioLine.open(format);
			audioLine.start();
			
			int BUFFERSIZE = 4096;
			byte[] bytesBuffer = new byte[BUFFERSIZE];
			int bytesRead = -1;
			
			System.out.println("Playing: " + songName);
			while((bytesRead = audioStream.read(bytesBuffer)) != -1 && !stop){
				audioLine.write(bytesBuffer, 0, bytesRead);
			}
			
			audioLine.drain();
			audioLine.close();
			audioStream.close();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		
		/*try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	}
	
	public void stopAudio(){
		stop = true;
		audioLine.stop();
		audioLine.drain();
		audioLine.close();
		
		try {
			audioStream.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void run() {
		playMusic();
	}
}
