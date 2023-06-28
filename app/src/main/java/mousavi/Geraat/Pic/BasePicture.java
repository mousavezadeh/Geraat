package mousavi.Geraat.Pic;

public abstract class BasePicture {
     String filepath;
     String filename;
    BasePicture(String filepath,String filename){
        this.filename=filename;
        this.filepath=filepath;
    }
    public abstract Integer Picturetype();
    public abstract String filename();
    public  abstract String filepath();

}
