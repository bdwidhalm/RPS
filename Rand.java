package chapter10;

import java.util.Random;

public class Rand implements Player
{
  private Hand lastHand;
//random number generator
 private static final Random randomNumbers = new Random();

  public Rand()
  {
    lastHand = Hand.PAPER;
  }

  @Override
  public String getName()
  {
    return this.getClass().getSimpleName();
  }

  @Override
  public Hand getNextHand()
  {
    // pick randomly

    int random = 1 + randomNumbers.nextInt(3);
    
    if (random == 1)
    {
      lastHand = Hand.ROCK;
    }
    else if (random == 2)
    {
      lastHand = Hand.PAPER;
    }
    else if (random == 3)
    {
      lastHand = Hand.SCISSORS;
    }

    return lastHand;
  }

  @Override
  public void setOpponentsLastHand(Hand hand)
  {
    // TODO Auto-generated method stub
  }

}