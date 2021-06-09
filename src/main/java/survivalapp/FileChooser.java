package survivalapp;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class FileChooser {
    private Set<String> allowedFileTypes;
    private File choseFile = null;

    public FileChooser() {
        allowedFileTypes = new HashSet<String>();
        allowedFileTypes.add("xml");
    }

    public void openDialog(String path) {
        JFileChooser chooser = new JFileChooser(path);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "XML files", allowedFileTypes.toArray(new String[0]));
        chooser.setFileFilter(filter);
        int returnStateOfChooser = chooser.showOpenDialog(null);
        if (returnStateOfChooser == JFileChooser.APPROVE_OPTION)
            choseFile = chooser.getSelectedFile();
        checkChoseFileType();
    }

    private void checkChoseFileType() {
        if (choseFile == null)
            return;
        String fileName = choseFile.getName();
        String fileType = fileName.substring(fileName.lastIndexOf('.')+1);
        if (!allowedFileTypes.contains(fileType))
            choseFile = null;
    }

    public boolean hadChoseFile() {
        return choseFile != null;
    }

    public String getChoseFilePath() {
        return choseFile.getAbsolutePath();
    }
}
