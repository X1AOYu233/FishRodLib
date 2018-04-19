import lwjgl.GLFW.Window;
import lwjgl.game.LogicThread;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import static java.lang.Thread.sleep;
import static lwjgl.OpenGL.GLManager.Draw2DfColor;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Tester {

    // The window handle
    private Window window;
    private boolean isFullScreen=false;



    private void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window.id);
        glfwDestroyWindow(window.id);

        // Terminate GLFW and free the error callback
        glfwTerminate();
    }

    private void init() {
        window = new Window(1920, 1080, "a", true, null, new GLFWKeyCallback() {
            @Override
            public void invoke(long windowID, int key, int scancode, int action, int mods) {
                if (key==GLFW_KEY_F11&!isFullScreen){
                    isFullScreen=true;
                    System.out.println(glfwGetWindowMonitor(windowID));
                    window.setFullScreen(isFullScreen,0,0,1920,1080,30);
                }
                if (key==GLFW_KEY_F12){
                    isFullScreen=false;
                    System.out.println(glfwGetWindowMonitor(windowID));
                    window.setFullScreen(isFullScreen,100,100   ,1920,1080,30);
                }
            }
        });
        new LogicThread(new Thread(()->{

        }),
        "logicThread",
        false);
        GL.createCapabilities();
    }

    private void loop() {
        long sleepTime = 1000/50;

        while (!glfwWindowShouldClose(window.id)) {
            render();

            try {
                sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private  void render(){
        // This line is critical for LWJGL's interpretation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.


        // Set the clear color

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.

            /* Get width and height to calcualte the ratio */
            /* Rewind buffers for next get */

            /* Set viewport and clear screen */
            glClear(GL_COLOR_BUFFER_BIT);

            /* Set ortographic projection */
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glMatrixMode(GL_MODELVIEW);

            /* Rotate matrix */
            glLoadIdentity();

            /* Render triangle */
            /*glColor4b()最高上限为127之后的和minecraft的效果倍数的计算方式一样，
             *所以在使用glColor4b()编辑时设置亮度为127,而在glColor4ub时不存在负数
             * 所以不受影响
             */
            Draw2DfColor(-900, -30,20, (float) 30,new Color(255, 100, 0, 161),8);
            Draw2DfColor(-1920/2, -540,1920, (float) 60,new Color(144, 255, 78, 255),8);
            Draw2DfColor(-1920/2, -480,1920, (float) 10,new Color(15, 127, 0, 255),8);
            Draw2DfColor(-1920/2, -540,1920, (float) 30,new Color(129, 110, 57, 255),8);

            /* Swap buffers and poll Events */
            glfwSwapBuffers(window.id);
            glfwPollEvents();

            /* Flip buffers for next loop */
    }
    public static void main(String[] args) {
        new Tester().run();
    }

}