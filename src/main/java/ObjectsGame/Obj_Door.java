package ObjectsGame;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Obj_Door extends SuperObject{

    public Obj_Door() throws IOException {
        name = "door";
        image = ImageIO.read(new File("data/door.png"));
        collison = true;
    }
}
