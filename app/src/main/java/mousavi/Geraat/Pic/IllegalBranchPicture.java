package mousavi.Geraat.Pic;

public class IllegalBranchPicture extends BasePicture {

    public IllegalBranchPicture(String filepath, String filename) {
        super(filepath, filename);
    }

    @Override
    public Integer Picturetype() {
        return 0;
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
