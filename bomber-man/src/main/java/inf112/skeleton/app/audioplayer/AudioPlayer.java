package inf112.skeleton.app.audioplayer;

import java.util.logging.*;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import inf112.skeleton.app.utilities.Constants;

/**
 * AudioPlayer class for handling sound effects.
 * Loads all files in resources/AudioFiles, will give warning for files not of recognizable format.
 */
public class AudioPlayer {
    
    Logger logger = Logger.getLogger(getClass().getName());
    HashMap<String,Sound> sounds;
    Music themeMusic;

    
    public AudioPlayer(){
        sounds = new HashMap<>();

        //initialize Sounds
        addSound(Constants.SFX_EXPLOSION);
        addSound(Constants.SFX_HURT);
        addSound(Constants.SFX_SUCCESS);


        //initialize main music theme
        this.themeMusic = Gdx.audio.newMusic(Gdx.files.internal(Constants.MUSIC_STRING));
        this.themeMusic.setLooping(true);
        this.themeMusic.setVolume(0.1f);
    }

    /**
     * Method for adding sound files to AudioPlayer object.
     * @param fileName, complete filename of wanted sound. Must be in resources/AudioFiles to be recognized.
     * @return true if sound was added, false if exception was thrown.
     */
    boolean addSound(String fileName){
        try{
            Sound sound = Gdx.audio.newSound(Gdx.files.internal("AudioFiles/"+fileName));
            this.sounds.put(fileName, sound);
            return true;
        } catch (Exception E) {
            logger.warning("Could not load "+ fileName + " because " + E + " was thrown.");
            return false;
        }
    }

    /**
     * Play a sound via AudioPlayer.
     * If fileName is not in loaded sounds, a warning will be given in the console, and nothing will play.
     * @param fileName, complete filename of wanted sound. Must be in resources/AudioFiles to be recognized.
     * @return, true if sound was played 
     */
    public boolean playSound(String fileName){
        Sound sound = this.sounds.get(fileName);
        if(sound == null){
            logger.warning(fileName + " not found.");
            return false;
        } 
        sound.play();
        return true;
    }

    /**
     * Dispose of all sound objects. Call before termination of AudioPlayer Object.
     */
    public void dispose(){
        for (Sound sound : sounds.values()){
            sound.dispose();
        }
        this.themeMusic.dispose();
    }

    /**
     * Set theme status, true for playing, false for not.
     * @param value, true to play 
     */
    public void setPlayTheme(boolean value) { 
        if(value){
            this.themeMusic.play();
        } else {
            this.themeMusic.pause();
        }
    }

    /**
     * Method for setting music volume. Give value between 0 and 100.
     * @param value, int value from 0 to 100.
     * @return, true if change happened, false if not.
     */
    public boolean setThemeMusicVolume(int value){
        if(value<0 || value>100){
            logger.warning("Invalid value.");
            return false;
        }
        float valueFloat = (float) value/100;
        this.themeMusic.setVolume(valueFloat);
        return true;
    }
    
}
