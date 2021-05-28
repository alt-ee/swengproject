package datastorage;

import java.net.URL;

public class ImageButton extends ButtonDataStorage {
        private final URL fileLocation;

        public ImageButton(int XPos, int YPos, int width, int height, int id, URL fileLocation) {
            super(XPos, YPos, width, height, id);
            this.fileLocation = fileLocation;
        }

        public URL getFileLocation() {
            return fileLocation;
        }
}
