package game.builder;

public abstract class GameCharacteristic {

    protected int player;
    protected int time;
    protected int respawntime;
    protected int catchtime;
    protected int catchspeed;
    protected int flag;
    protected int radius;
    protected int spawn;
    protected int point;
    protected int killpoint;
    protected int flagpoint;
    protected boolean open;
    protected String map;

    public int getPlayer() {
        return player;
    }

    public int getTime() {
        return time;
    }

    public int getRespawntime() {
        return respawntime;
    }

    public int getCatchtime() {
        return catchtime;
    }

    public int getCatchspeed() {
        return catchspeed;
    }

    public int getFlag() {
        return flag;
    }

    public int getRadius() {
        return radius;
    }

    public int getSpawn() {
        return spawn;
    }

    public int getPoint() {
        return point;
    }

    public int getKillpoint() {
        return killpoint;
    }

    public int getFlagpoint() {
        return flagpoint;
    }

    public boolean isOpen() {
        return open;
    }

    public String getMap() {
        return map;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setRespawntime(int respawntime) {
        this.respawntime = respawntime;
    }

    public void setCatchtime(int catchtime) {
        this.catchtime = catchtime;
    }

    public void setCatchspeed(int catchspeed) {
        this.catchspeed = catchspeed;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setSpawn(int spawn) {
        this.spawn = spawn;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setKillpoint(int killpoint) {
        this.killpoint = killpoint;
    }

    public void setFlagpoint(int flagpoint) {
        this.flagpoint = flagpoint;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setMap(String map) {
        this.map = map;
    }
}
