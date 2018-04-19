package lwjgl.GLFW;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * This class represents a GLFW window.
 */
public class Window {

    /**
     * Stores the window handle.
     */
    public final long id;

    /**
     * Key callback for the window.
     */
    private final GLFWKeyCallback keyCallback;
    private final GLFWErrorCallback errorCallback;
    private final GLFWVidMode notFullScreenVidMode;

    /**
     * Shows if vsync is enabled.
     */
    private boolean vsync;

    /**
     * Creates a GLFW window and its OpenGL context with the specified width,
     * height and title.
     *
     * @param width  Width of the drawing area
     * @param height Height of the drawing area
     * @param title  Title of the window
     * @param vsync  Set to true, if you want v-sync
     */
    public Window(int width, int height, CharSequence title, boolean vsync,GLFWErrorCallback glfwErrorCallback , GLFWKeyCallback glfwKeyCallback) {
        this.vsync = vsync;
        this.errorCallback = glfwErrorCallback;
        this.keyCallback = glfwKeyCallback;
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        id = glfwCreateWindow(width, height, title, NULL, NULL);
        if ( id == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(id, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(id, pWidth, pHeight);

            // Get the resolution of the primary monitor
            notFullScreenVidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    id,
                    (notFullScreenVidMode.width() - pWidth.get(0)) / 2,
                    (notFullScreenVidMode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(id);
        // Enable v-sync
        if (vsync) {
            glfwSwapInterval(1);
        }
        glfwSetErrorCallback(errorCallback);
        glfwSetKeyCallback(id,keyCallback);
        // Make the window visible
        glfwShowWindow(id);
    }
    /**
     * Returns if the window is closing.
     *
     * @return true if the window should close, else false
     */
    public boolean isClosing() {
        return glfwWindowShouldClose(id);
    }

    /**
     * Sets the window title
     *
     * @param title New window title
     */
    public void setTitle(CharSequence title) {
        glfwSetWindowTitle(id, title);
    }

    /**
     * Updates the screen.
     */
    public void update() {
        glfwSwapBuffers(id);
        glfwPollEvents();
    }
    /**
     * @param refreshRate 刷新率
     * @param width,height 分辨率
     * */
    public void setFullScreen(boolean isFullScreen,int xpos,int ypos,int width,int height,int refreshRate){
        if (isFullScreen) {
            glfwSetWindowMonitor(id, glfwGetPrimaryMonitor(), xpos, ypos, width, height, refreshRate);
        } else {
                glfwSetWindowMonitor(id, NULL, 0 , 0,notFullScreenVidMode.width(), notFullScreenVidMode.height(), notFullScreenVidMode.refreshRate());
                setWindowToCenter();
        }
    }
    /**
     * Destroys the window an releases its callbacks.
     */
    public void destroy() {
        glfwDestroyWindow(id);
        keyCallback.free();
    }

    /**
     * Setter for v-sync.
     *
     * @param vsync Set to true to enable v-sync
     */
    public void setVSync(boolean vsync) {
        this.vsync = vsync;
        if (vsync) {
            glfwSwapInterval(1);
        } else {
            glfwSwapInterval(0);
        }
    }

    /**
     * Check if v-sync is enabled.
     *
     * @return true if v-sync is enabled
     */
    public boolean isVSyncEnabled() {
        return this.vsync;
    }
    public void setWindowToCenter(){
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(id, pWidth, pHeight);
            // Center the window
            glfwSetWindowPos(
                    id,
                    (notFullScreenVidMode.width() - pWidth.get(0)) / 2,
                    (notFullScreenVidMode.height() - pHeight.get(0)) / 2
            );
        }
    }

}
