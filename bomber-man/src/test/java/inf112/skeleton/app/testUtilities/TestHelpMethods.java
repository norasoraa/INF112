package inf112.skeleton.app.testUtilities;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.*;

public class TestHelpMethods {
   
   public static ApplicationListener GetMockListener(HeadlessApplicationConfiguration config2) {
      ApplicationListener l = new ApplicationListener() { 
         @Override
         public void create() {}

         @Override
         public void resize(int width, int height) {}

         @Override
         public void render() {}

         @Override
         public void pause() {}

         @Override
         public void resume() {}

         @Override
         public void dispose() {}
      };
      return l;
   }
}