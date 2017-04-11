package exercise05;

public interface IDriver {
	public String readNextLine();
	public IMove readNextMove(Boolean wasPreviousMoveInvalid);
	public void renderGame();
}
