package ObjectsGame;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Obj_boots extends SuperObject{

    public Obj_boots() throws IOException {
        name = "boots";
        image = ImageIO.read(new File("data/boots.png"));
    }
}
