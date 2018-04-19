package lwjgl.game;

public class LogicThread {
    private Thread logicThread;

    public LogicThread(Thread logicThread,String name,boolean isDaemon){
        this.logicThread = logicThread;
        logicThread.setDaemon(isDaemon);
        logicThread.setName(name);
        logicThread.start();
    }
    public void sleepThis(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        logicThread.interrupt();
    }
    public void join(){
        try {
            logicThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void join(int millis){
        try {
            logicThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void join(int millis,int nanos){
        try {
            logicThread.join(millis,nanos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
