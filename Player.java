package chapter10;

public interface Player
{

  /**
   * Returns the name of this player, used for console output.
   */
  public String getName();

  /**
   * This method returns the hand the player would like to play.
   */
  public Hand getNextHand();

  /**
   * This method tells the player what the other players hand was.
   */
  public void setOpponentsLastHand(Hand hand);

}
