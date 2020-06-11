public class Difficulty {
    private int minGap;
    private int speed;

    public Difficulty(){
        reset();
    }

    public void increase(int score){
        if(score == 8){
            minGap = 30;
        }
        if(score == 14){
            speed = 45;
        }
        if(score == 16){
            minGap = 20;
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
        minGap = 60;
        speed = 60;
    }


}
