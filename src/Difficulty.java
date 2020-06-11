public class Difficulty {
    private int minGap;
    private int speed;

    public Difficulty(){
        reset();
    }

    public void increase(int score){
        if(score == 8){
            speed = 72;
        }
        if(score == 22){
            speed = 48;
        }
        if(score == 44){
            speed = 24;
        }
    }

    public int getMinGap() {
        return minGap;
    }
    public void setMinGap(int minGap) {
        this.minGap = minGap;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void reset(){
        minGap = 4;
        speed = 96;
    }


}
