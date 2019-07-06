import java.awt.*;

/**
 * @author tortoiselala
 */
public class Constants {
    // 系统屏幕大小：系統屏幕寬度
    public final static int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    // 系统屏幕大小：系统屏幕高度
    public final static int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    // 主窗口默认大小：宽度
    public final static int MAIN_WINDOW_WIDTH = SCREEN_WIDTH * 3 / 4;
    // 主窗口默认大小：高度
    public final static int MAIN_WINDOW_HEIGHT = SCREEN_HEIGHT * 3 / 4;

    // 主窗口默认位置：主窗口默认横向坐标
    public final static int MAIN_WINDOW_X_POSITION = SCREEN_WIDTH / 2 - MAIN_WINDOW_WIDTH / 2;
    // 主窗口默认位置：主窗口默认纵向坐标
    public final static int MAIN_WINDOW_Y_POSITION = SCREEN_HEIGHT / 2 - MAIN_WINDOW_HEIGHT / 2;
}
