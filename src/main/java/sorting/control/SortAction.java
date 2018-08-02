package sorting.control;

/**
 *
 * <ol>
 *     <li>增加 resumeAndExit 控制信号 暂停</li>
 * </ol>
 * @author evan
 * create-date 2018/7/31
 */
public interface SortAction {

    /**
     * 暂停
     */
    void pause();

    /**
     * 继续
     */
    void resume();

    /**
     * 释放信号
     */
    void resumeAndExit();
}
