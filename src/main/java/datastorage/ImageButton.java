package datastorage;

import java.net.URL;
import java.util.Objects;

public class ImageButton extends ButtonDataStorage {
        private final URL fileLocation;

        public ImageButton(int XPos, int YPos, int width, int height, String id, URL fileLocation, Target target) {
            super(XPos, YPos, width, height, id, target);
            this.fileLocation = fileLocation;
        }

        public URL getFileLocation() {
            return fileLocation;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ImageButton that = (ImageButton) o;
        return fileLocation.equals(that.fileLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fileLocation);
    }
}
