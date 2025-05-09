import component.*;
import component.impl.*;

public class Demo {
    public static void main(String[] args) {
        Amplifier amplifier = new HomeAmplifier();
        PopcornPopper popper = new HomePopcornPopper();
        Projector projector = new HomeProjector();
        Screen screen = new HomeScreen();
        StreamingPlayer player = new HomeStreamingPlayer();

        HomeTheaterFacade theaterFacade = new HomeTheaterFacade(amplifier, player, projector, screen, popper);
        theaterFacade.watchMovie("Run Forrest Run");
        System.out.println();
        theaterFacade.endMovie();
    }
}
