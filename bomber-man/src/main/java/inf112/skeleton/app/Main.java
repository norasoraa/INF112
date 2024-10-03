package inf112.skeleton.app;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        DisplayMode mode = Lwjgl3ApplicationConfiguration.getDisplayMode();
        cfg.setWindowedMode(
            (int) (mode.width * 0.8), 
            (int) (mode.height * 0.8)); 
        cfg.setWindowPosition(-1, -1);  // Centers the window on your screen
        new Lwjgl3Application(new BomberMan(), cfg);
    }
}