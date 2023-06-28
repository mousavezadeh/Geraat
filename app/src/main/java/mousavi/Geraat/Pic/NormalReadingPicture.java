package mousavi.Geraat.Pic;

public class NormalReadingPicture extends BasePicture {

    public NormalReadingPicture(String filepath, String filename) {
        super(filepath, filename);
    }

    @Override
    public Integer Picturetype() {
        return 1;
    }

    @Override
    public String filename() {
        return this.filename;
    }

    @Override
    public String filepath() {
        return this.filepath;
    }
}
