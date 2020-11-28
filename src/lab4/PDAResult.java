package lab4;

public class PDAResult {
    public int consumedLength;
    public int stackCount;
    public boolean success;

    public PDAResult(int consumedLength, int stackCount, boolean success) {
        this.consumedLength = consumedLength;
        this.stackCount = stackCount;
        this.success = success;
    }
}
