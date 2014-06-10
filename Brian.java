package chapter10;

public class Brian implements Player
{
  private Hand lastHand;

  public Brian()
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
    // Always pick paper, rock, scissors, paper, rock, scissors, paper, rock, scissors, paper, rock, scissors, paper, rock, scissors, etc...
    if (Hand.PAPER.equals(lastHand))
    {
      lastHand = Hand.ROCK;
    }
    else if (Hand.ROCK.equals(lastHand))
    {
      lastHand = Hand.SCISSORS;
    }
    else if (Hand.SCISSORS.equals(lastHand))
    {
      lastHand = Hand.PAPER;
    }

    return lastHand;
  }

  @Override
  public void setOpponentsLastHand(Hand hand)
  {
    // TODO Auto-generated method stub
  }

}
