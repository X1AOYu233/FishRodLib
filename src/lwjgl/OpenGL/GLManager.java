package lwjgl.OpenGL;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;

public class GLManager {
    private static int WindowWidth=glfwGetVideoMode(glfwGetPrimaryMonitor()).width();
    private static int WindowHeight=glfwGetVideoMode(glfwGetPrimaryMonitor()).height();
    /**
     * 以屏幕中心为原点，向右上角叠加宽高
     * @param x 绘画起始x位置
     * @param y 绘画起始x位置
     * @param width 绘画宽度
     * @param height 绘画高度
     * @param color 绘画颜色
     * @param Mode GLBegin()中的模式，可用参数为：
     *             GL_POINTS,GL_LINES,GL_LINE_STRIP,GL_LINE_LOOP,GL_TRIANGLES,GL_TRIANGLE_STRIP,GL_TRIANGLE_FAN,GL_QUADS,GL_QUAD_STRIP,GL_POLYGON
     * */
    public static void Draw2DfColor(float x, float y, float width, float height, Color color, int Mode){
        {
            float DrawPixelsX= x/(WindowWidth/2);
            float DrawPixelsY= y/(WindowHeight/2);
            float DrawPixelsWidth= width/(WindowWidth/2);
            float DrawPixelsHeight= height/(WindowHeight/2);
            glBegin(Mode);
                glColor4ub((byte)(color.getRed()),(byte) (color.getGreen()),(byte) (color.getBlue()),(byte) color.getAlpha());
                glVertex2f(DrawPixelsX,DrawPixelsY);
                glVertex2f(DrawPixelsX+DrawPixelsWidth,DrawPixelsY);
                glVertex2f(DrawPixelsX,DrawPixelsY+DrawPixelsHeight);
                glVertex2f(DrawPixelsX+DrawPixelsWidth,DrawPixelsY+DrawPixelsHeight);
            glEnd();
        }
    }

}
