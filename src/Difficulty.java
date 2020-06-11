public class Difficulty {
    private int minGap;
    private int speed;

    public Difficulty(){
        reset();
    }

    public void increase(int score){
        if(score == 8){
            speed = 60;
        }
        if(score == 16){
            speed = 40;
        }
        if(score == 20){
            speed = 30;
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
        speed = 80;
    }


}
