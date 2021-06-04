package datastorage;

import java.net.URL;

public class ImageButton extends ButtonDataStorage {
        private final URL fileLocation;

        public ImageButton(int XPos, int YPos, int width, int height, String id, URL fileLocation, Target target) {
            super(XPos, YPos, width, height, id, target);
            this.fileLocation = fileLocation;
        }

        public URL getFileLocation() {
            return fileLocation;
        }
}
