package witt.anthony;
import java.applet.Applet;


public class WrapperApplet extends Applet {

    public void start() {
       new Thread("application main Thread") {
          public void run() { runApplication(); }
       }.start();
    }

    private void runApplication() {
       MainC.main(new String[0]);
    }

}
