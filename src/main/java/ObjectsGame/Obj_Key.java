package ObjectsGame;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Obj_Key extends SuperObject{

    public Obj_Key() throws IOException {
        name = "key";
        image = ImageIO.read(new File("data/key.png"));
    }
}
