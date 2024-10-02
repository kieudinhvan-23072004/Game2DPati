package ObjectsGame;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Obj_Chest extends SuperObject {

    public Obj_Chest() throws IOException {
        name = "chest";
        image = ImageIO.read(new File("data/chest.png"));
    }
}
