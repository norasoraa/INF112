package inf112.skeleton.app.audioplayer;

import static inf112.skeleton.app.testUtilities.TestHelpMethods.GetMockListener;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

import inf112.skeleton.app.utilities.Constants;

public class AudioPlayerTest {

    private HeadlessApplicationConfiguration config;
    private ApplicationListener listener;
    private AudioPlayer audioPlayer;

    @BeforeEach
    void setup(){
        config = new HeadlessApplicationConfiguration();
        listener = GetMockListener(config);
        new HeadlessApplication(listener, config); // Needed for Explosion.startTimer()
        audioPlayer = new AudioPlayer();
    }

    @Test
    void audioPlayerSanityTest(){
        //Check if audioplayer was initialized.
        assertNotEquals(null, audioPlayer.logger);
        assertNotEquals(null, audioPlayer.themeMusic);
        assertNotEquals(null, audioPlayer.sounds);
    }

    @Test 
    void setMusicVolumeTest(){
        audioPlayer.setThemeMusicVolume(30);
        //TODO
        // audioPlayer.
    }

    @Test 
    void initilizeConstants(){
        //Check if constants are playable.
        assertTrue(audioPlayer.playSound(Constants.SFX_EXPLOSION));
        assertTrue(audioPlayer.playSound(Constants.SFX_SUCCESS));
        assertTrue(audioPlayer.playSound(Constants.SFX_HURT));
    }
}
